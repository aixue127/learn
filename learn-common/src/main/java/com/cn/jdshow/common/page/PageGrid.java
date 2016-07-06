/*
 * Decompiled with CFR 0_115.
 */
package com.cn.jdshow.common.page;

import java.util.List;

public class PageGrid<E> {
    private Integer total;
    private List<E> rows;

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<E> getRows() {
        return this.rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }
}
