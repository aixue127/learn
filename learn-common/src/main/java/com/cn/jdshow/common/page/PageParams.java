/*
 * Decompiled with CFR 0_115.
 */
package com.cn.jdshow.common.page;

public class PageParams {
    private Integer page;
    private Integer rows;
    private Integer rowStart;
    private Integer rowEnd;

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return this.rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getRowStart() {
        if (this.page == null) {
            this.page = 1;
        }
        if (this.rows == null) {
            this.rows = 10;
        }
        this.rowStart = (this.page - 1) * this.rows;
        return this.rowStart;
    }

    public Integer getRowEnd() {
        this.rowEnd = this.getRowStart() + this.rows - 1;
        return this.rowEnd;
    }

    public void setRowStart(Integer rowStart) {
        this.rowStart = rowStart;
    }

    public void setRowEnd(Integer rowEnd) {
        this.rowEnd = rowEnd;
    }
}
