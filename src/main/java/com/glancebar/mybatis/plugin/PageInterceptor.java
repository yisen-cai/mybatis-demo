package com.glancebar.mybatis.plugin;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Intercepts(
        @Signature(
                type = Executor.class,
                method = "query",
                args = {
                        MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class
                }
        )
)
public class PageInterceptor implements Interceptor {

    private static final List<ResultMapping> EMPTY_RESULT_MAPPING = new ArrayList<>(0);
    private Dialect dialect;
    private Field additionalParameterField;

    /**
     * 拦截方法
     * @param invocation 可以获取拦截的方法，参数，目标类等
     * @return 调用原方法并返回
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object paramObject = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        // 判断是否需要分页，不需要直接返回结果
        if (!dialect.skip(ms.getId(), paramObject, rowBounds)) {
            ResultHandler resultHandler = (ResultHandler) args[3];
            // 当前目标对象
            Executor executor = (Executor) invocation.getTarget();
            // 获取生成的查询SQL
            BoundSql boundSql = ms.getBoundSql(paramObject);
            // 反射获取动态参数
            Map<String, Object> additionalParameters = (Map<String, Object>) additionalParameterField.get(boundSql);
            // 判断是否需要进行count查询
            if (dialect.beforeCount(ms.getId(), paramObject, rowBounds)) {
                // 根据当前的ms创建一个返回指为long类型的ms
                MappedStatement countMs = newMappedStatement(ms, Long.class);
                // 创建count查询的缓存key
                CacheKey countKey = executor.createCacheKey(
                        countMs,
                        paramObject,
                        RowBounds.DEFAULT,
                        boundSql
                );
                // 方言获取count sql
                String countSql = dialect.getCountSql(boundSql, paramObject, rowBounds, countKey);
                // 准备设置参数
                BoundSql countBoundSql = new BoundSql(
                        ms.getConfiguration(),
                        countSql,
                        boundSql.getParameterMappings(),
                        paramObject
                );
                for (String key :
                        additionalParameters.keySet()) {
                    countBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
                }
                // 执行count查询
                Object countResultList = executor.query(
                        countMs,
                        paramObject,
                        RowBounds.DEFAULT,
                        resultHandler,
                        countKey,
                        countBoundSql
                );
                Long count = (Long) ((List) countResultList).get(0);
                // 处理查询总数
                dialect.afterCount(count, paramObject, rowBounds);
                if (count == 0L) {
                    // 查询结果为空时直接返回空结果，不再进行分页等其他操作
                    return dialect.afterPage(new ArrayList(),
                            paramObject,
                            rowBounds);
                }
                if (dialect.beforePage(ms.getId(), paramObject, rowBounds)) {
                    // 生成分页的缓存key，之后的查询可以根据这个参数去进行命中
                    CacheKey pageKey = executor.createCacheKey(
                            ms,
                            paramObject,
                            rowBounds,
                            boundSql
                    );
                    // 调用方言获取分页sql
                    String pageSql = dialect.getPageSql(
                            boundSql,
                            paramObject,
                            rowBounds,
                            pageKey
                    );
                    // 准备设置参数
                    BoundSql pageBoundSql = new BoundSql(
                            ms.getConfiguration(),
                            pageSql,
                            boundSql.getParameterMappings(),
                            paramObject
                    );
                    // 设置动态参数
                    for (String key :
                            additionalParameters.keySet()) {
                        pageBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
                    }
                    // 执行分页查询
                    List resultList = executor.query(
                            ms,
                            paramObject,
                            RowBounds.DEFAULT,
                            resultHandler,
                            pageKey,
                            pageBoundSql
                    );
                    return dialect.afterPage(resultList, paramObject, rowBounds);
                }
            }
        }
        return invocation.proceed();
    }


    public MappedStatement newMappedStatement(MappedStatement ms, Class<?> resultType) {
        MappedStatement.Builder builder = new MappedStatement.Builder(
                ms.getConfiguration(),
                ms.getId() + "_Count",
                ms.getSqlSource(),
                ms.getSqlCommandType()
        );
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        // count查询返回值
        List<ResultMap> resultMaps = new ArrayList<>();
        ResultMap resultMap = new ResultMap.Builder(
                ms.getConfiguration(),
                ms.getId(),
                resultType,
                EMPTY_RESULT_MAPPING
        ).build();
        resultMaps.add(resultMap);
        builder.resultMaps(resultMaps);
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    /**
     * 创建拦截实现类时调用
     * @param target 被拦截对象
     * @return object
     */
    @Override
    public Object plugin(Object target) {
        // 通过MyBatis静态方法wrap动态代理拦截目标对象
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String dialectClass = properties.getProperty("dialect");
        try {
            // 反射获取自定义Dialect
            dialect = (Dialect) Class.forName(dialectClass).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        dialect.setProperties(properties);
        try {
            // 反射获取BoundSql中的additionalParameter属性
            additionalParameterField = BoundSql.class.getDeclaredField("additionalParameters");
            additionalParameterField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
