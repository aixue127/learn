package com.cn.jdshow.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.cn.jdshow.common.fastjson.PropertyFilterForBean;
import com.cn.jdshow.domain.Function;
import com.cn.jdshow.service.RoleFunctionService;

/**
 * 
 * @Description: 进入后台首页
 * @author mingxin.lu
 * @date 2016年5月10日 上午11:20:39   @version V1.0
 */
@Controller
@RequestMapping("/jdshow")
public class IndexController {

	@Autowired
	private RoleFunctionService roleFunctionService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response , ModelMap model) {
		try {
			// 获取用户登录权限 
			List<Function> functions = roleFunctionService.queryUserFunction(0);
			model.put("functions", JSON.toJSONString(functions, new PropertyFilterForBean(new String[]{"createDate","modifyDate"})));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
}
