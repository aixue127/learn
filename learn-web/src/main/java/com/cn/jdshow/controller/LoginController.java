package com.cn.jdshow.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cn.jdshow.common.constant.UserConstant;
import com.cn.jdshow.common.util.AjaxResult;
import com.cn.jdshow.common.util.SpringMVCUtil;
import com.cn.jdshow.domain.User;
import com.cn.jdshow.service.UserService;
import com.cn.jdshow.vo.LoginInputVO;

/**
 * 
 * @Description: 用户登录管理
 * @author mingxin.lu
 * @date 2016年5月6日 下午2:26:59   @version V1.0
 */
@Controller
@RequestMapping("/jdshow/user")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	@ResponseBody
	public void login(HttpServletRequest request, HttpServletResponse response, LoginInputVO vo) {
		try {
			List<User> listUser = userService.queryInfoByVO(User.class, vo);
			User u =null;
			if(listUser != null && listUser.size() > 0) {
				u = listUser.get(0);
				// 登录成功，放入session
				HttpSession session = request.getSession();
				session.setAttribute(UserConstant.R_SESSION_USER, u.getId());
				
				SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.successResult("success")));
			} else {
				SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("fail")));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description: 登录页面
	 * @param request
	 * @param response
	 * @return
	 * @date 2016年5月10日 上午10:58:02
	 */
	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}
}
