/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  javax.servlet.http.HttpServletRequest
 */
package com.cn.jdshow.common.util;

import javax.servlet.http.HttpServletRequest;

public final class WebUtil {
    public static String getRequestUri(HttpServletRequest request) {
        String servltPath = request.getServletPath();
        return servltPath;
    }
}
