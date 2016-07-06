/*
 * Decompiled with CFR 0_115.
 * 
 * Could not load the following classes:
 *  javax.servlet.http.HttpServletResponse
 */
package com.cn.jdshow.common.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class SpringMVCUtil {
   
	
	public static void printWriter(HttpServletResponse response, Object content) {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try(PrintWriter writer = response.getWriter()) {
        	 writer.print(content.toString());
        } catch(Exception e) {
        	e.printStackTrace();
        }
       
    }
}
