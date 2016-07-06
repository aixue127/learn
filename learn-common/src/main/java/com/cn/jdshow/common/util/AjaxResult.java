/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 */
package com.cn.jdshow.common.util;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;

public class AjaxResult extends HashMap<String, Object> {
    public static final String SYSTEM_ERROR = "系统错误，请稍后重试";

    public static AjaxResult successResult(String msg) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("success", true);
        ajaxResult.put("msg", msg);
        return ajaxResult;
    }

    public static AjaxResult failResult(String msg) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("success", false);
        ajaxResult.put("msg", msg);
        return ajaxResult;
    }

    public static AjaxResult objectResult(Object obj) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("success", true);
        ajaxResult.put("data", obj);
        return ajaxResult;
    }

    public static AjaxResult pageInfoResult(Object object, int totle) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put("rows", JSON.toJSONString(object));
        ajaxResult.put("total", totle);
        return ajaxResult;
    }
}
