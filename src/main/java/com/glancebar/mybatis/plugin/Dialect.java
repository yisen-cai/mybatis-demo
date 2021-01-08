package com.glancebar.mybatis.plugin;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Properties;

public interface Dialect {

    /**
     * 跳过count和分页查询
     *
     * @param msId        执行的MyBatis方法全名
     * @param paramObject 方法参数
     * @param rowBounds   分页参数
     * @return true 跳过，返回默认查询结构，false则执行分页查询
     */
    boolean skip(String msId, Object paramObject, RowBounds rowBounds);


    /**
     * 执行分页前，返回true会进行count查询，返回false会继续下面的beforePage判断
     *
     * @param msId        执行的MyBatis方法全名
     * @param paramObject 方法参数
     * @param rowBounds   分页参数
     * @return true，分页
     */
    boolean beforeCount(String msId, Object paramObject, RowBounds rowBounds);


    String getCountSql(BoundSql boundSql, Object paramObject, RowBounds rowBounds, CacheKey countKey);


    /**
     * 执行完count后
     *
     * @param count       查询结果总数
     * @param paramObject 接口参数
     * @param rowBounds   分页参数
     */
    void afterCount(long count, Object paramObject, RowBounds rowBounds);


    /**
     * 执行分页前，返回true会进行分页查询，false进行默认查询
     *
     * @param msId        执行的MyBatis方法全名
     * @param paramObject 方法参数
     * @param rowBounds   分页参数
     * @return true
     */
    boolean beforePage(String msId, Object paramObject, RowBounds rowBounds);


    /**
     * 生成分页查询的Sql
     *
     * @param boundSql    绑定sql对象
     * @param paramObject 方法参数
     * @param rowBounds   分页参数
     * @param pageKey     分页缓存key
     * @return
     */
    String getPageSql(BoundSql boundSql, Object paramObject, RowBounds rowBounds, CacheKey pageKey);


    /**
     * 分页查询后，处理分页结果，拦截器中直接return该方法的返回值
     *
     * @param pageList    分页查询结果
     * @param paramObject 方法参数
     * @param rowBounds   分页参数
     * @return
     */
    Object afterPage(List pageList, Object paramObject, RowBounds rowBounds);


    /**
     * 设置参数
     *
     * @param properties 插件属性
     */
    void setProperties(Properties properties);
}
