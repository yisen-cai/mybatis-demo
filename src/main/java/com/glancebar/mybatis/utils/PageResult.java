package com.glancebar.mybatis.utils;

import java.util.Collection;

/**
 * @author YISHEN CAI
 */
public class PageResult<T> {
    private long total;
    private boolean hasNext;
    private Collection<T> data;

    public PageResult(long total, boolean hasNext, Collection<T> data) {
        this.total = total;
        this.hasNext = hasNext;
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }
}
