/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  javax.servlet.http.HttpSession
 *  org.springframework.web.servlet.HandlerInterceptor
 *  org.springframework.web.servlet.handler.HandlerInterceptorAdapter
 */
package com.cn.jdshow.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cn.jdshow.common.util.WebUtil;

public class LoginInterceptor
extends HandlerInterceptorAdapter implements HandlerInterceptor {
    private List<String> uris;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.preHandle(request, response, handler);
        String uri = WebUtil.getRequestUri(request);
        if (this.uris != null && this.uris.contains(uri)) {
            return true;
        }
        Object user_id = request.getSession().getAttribute("R_USER_ID_");
        if (user_id == null) {
            response.sendRedirect(String.valueOf(request.getContextPath()) + "/jdshow/user/toLogin");
            return false;
        }
        return true;
    }

    public List<String> getUris() {
        return this.uris;
    }

    public void setUris(List<String> uris) {
        this.uris = uris;
    }
}
