/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.serializer.PropertyFilter
 */
package com.cn.jdshow.common.fastjson;

import com.alibaba.fastjson.serializer.PropertyFilter;

public class PropertyFilterForBean implements PropertyFilter {
    private String[] fields;

    public PropertyFilterForBean(String[] fields) {
        this.fields = fields;
    }

    public boolean apply(Object object, String name, Object value) {
        String[] arrstring = this.fields;
        int n = arrstring.length;
        int n2 = 0;
        while (n2 < n) {
            String field = arrstring[n2];
            if (field.equals(name)) {
                return false;
            }
            ++n2;
        }
        return true;
    }
}
