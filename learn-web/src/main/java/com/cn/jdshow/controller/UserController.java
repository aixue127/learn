package com.cn.jdshow.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cn.jdshow.common.fastjson.PropertyFilterForBean;
import com.cn.jdshow.common.mongodb.MongoDBSource;
import com.cn.jdshow.common.page.PageGrid;
import com.cn.jdshow.common.page.PageParams;
import com.cn.jdshow.common.util.AjaxResult;
import com.cn.jdshow.common.util.SpringMVCUtil;
import com.cn.jdshow.domain.Role;
import com.cn.jdshow.domain.User;
import com.cn.jdshow.domain.UserRole;
import com.cn.jdshow.model.NormalZNodes;
import com.cn.jdshow.service.UserService;
import com.cn.jdshow.vo.UserInputVO;

/**
 * 
 * @Description: 用户管理
 * @author mingxin.lu
 * @date 2016年5月9日 下午3:28:44   @version V1.0
 */
@Controller
@RequestMapping("/jdshow/user")
public class UserController {

	@Autowired
	private UserService userSerivce;
	
	@RequestMapping("/goQueryUser")
	public String goQueryUser(HttpServletRequest request, HttpServletResponse response) {
		return "admin/userQuery";
	}
	
	@RequestMapping("/queryUser")
	@ResponseBody
	public void queryUser(PageParams params, HttpServletRequest request, HttpServletResponse response) {
		try {
			PageGrid<User> pageGrid = userSerivce.queryByNormalPage(params, User.class, null);
			SpringMVCUtil.printWriter(response,  JSON.toJSONString(pageGrid));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/insOrUpdUser")
	@ResponseBody
	public void insOrUpdUser(HttpServletRequest request,HttpServletResponse response, UserInputVO vo) {
		try {
			if(StringUtils.isNotBlank(vo.getId())) {
				userSerivce.update(vo, User.class);
			} else {
				User u = new User();
				u.setLoginName(vo.getLoginName());
				u.setModifyDate(new Date());
				u.setPassWord(vo.getPassWord());
				u.setUserName(vo.getUserName());
				userSerivce.save(u);
			}
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("fail")));
		}
		SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.successResult("success")));
	}
	
	@RequestMapping("/deleteUser")
	@ResponseBody
	public void deleteUser(HttpServletRequest request,HttpServletResponse response,@RequestParam("id") String id) {
		try {
			userSerivce.deleteUser(id);
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.successResult("success")));
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("fail")));
		}
	}
	
	/**
	 * 
	 * @Description: 查询用户所用的角色
	 * @date 2016年5月17日 下午6:15:00
	 */
	@RequestMapping("/queryUserRole")
	@ResponseBody
	public void queryUserRole(HttpServletRequest request,HttpServletResponse response,
							@RequestParam("userId") String userId, @RequestParam("id") int id) {
		try {
			List<NormalZNodes> ats = userSerivce.queryUserRole(userId, id);
			SpringMVCUtil.printWriter(response, JSON.toJSONString(ats, new PropertyFilterForBean(new String[]{"iconSkin","isParent"})));
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("fail")));
		}
	}
	
	/**
	 * 
	 * @Description: 保存用户角色关系
	 * @param request
	 * @param response
	 * @param userId
	 * @param roleIds
	 * @date 2016年5月19日 上午10:31:43
	 */
	@RequestMapping("/saveUserRole")
	@ResponseBody
	public void saveUserRole(HttpServletRequest request,HttpServletResponse response,
				@RequestParam("userId") String userId,@RequestParam("roleIds") String roleIds) {
		try {
			User user = userSerivce.findById(User.class, Integer.parseInt(userId));
			List<UserRole> userRoles = new ArrayList<>();
			String[] aroleIds = roleIds.split(",");
			for(String r : aroleIds) {
				UserRole ur = new UserRole();
				ur.setId(MongoDBSource.getIdByCollectionName(UserRole.class.getSimpleName()));
				ur.setUser(user);
				ur.setRole(userSerivce.findById(Role.class, Integer.parseInt(r)));
				userRoles.add(ur);
			}
			userSerivce.saveUserRole(userRoles);
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.successResult("success")));
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("fail")));
		}
	}
}
