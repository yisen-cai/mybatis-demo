package com.glancebar.mybatis.plugin;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Properties;

/**
 * Dialect的MySQL实现
 */
public class MySqlDialect implements Dialect {

    private static final String COUNT_PREFIX = "select count(*) from ";

    @Override
    public boolean skip(String msId, Object paramObject, RowBounds rowBounds) {
        // 使用RowBounds分页
        // 没有RowBounds参数时，使用RowBounds.DEFAULT作为默认值
        return rowBounds == RowBounds.DEFAULT;
    }

    @Override
    public boolean beforeCount(String msId, Object paramObject, RowBounds rowBounds) {
        // 只有使用PageRowBounds才能记录总数
        return rowBounds instanceof PageRowBounds;
    }

    @Override
    public String getCountSql(BoundSql boundSql, Object paramObject, RowBounds rowBounds, CacheKey countKey) {
        // 查询总数，然后根据总数进行分页操作
        return COUNT_PREFIX + "(" + boundSql.getSql() + ") temp";
    }

    @Override
    public void afterCount(long count, Object paramObject, RowBounds rowBounds) {
        ((PageRowBounds) rowBounds).setTotal(count);
    }

    @Override
    public boolean beforePage(String msId, Object paramObject, RowBounds rowBounds) {
        return rowBounds != RowBounds.DEFAULT;
    }

    @Override
    public String getPageSql(BoundSql boundSql, Object paramObject, RowBounds rowBounds, CacheKey pageKey) {
        // pageKey会影响缓存，通过固定的RowBounds可保证二级缓存有效
        pageKey.update("RowBounds");
        return boundSql.getSql() + " limit " + rowBounds.getOffset() + "," + rowBounds.getLimit();
    }

    @Override
    public Object afterPage(List pageList, Object paramObject, RowBounds rowBounds) {
        // 直接返回查询结果
        return pageList;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
