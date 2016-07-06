
package com.cn.jdshow.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.cn.jdshow.domain.Function;
import com.cn.jdshow.domain.Role;
import com.cn.jdshow.domain.RoleFunction;
import com.cn.jdshow.model.NormalZNodes;
import com.cn.jdshow.service.RoleFunctionService;
import com.cn.jdshow.vo.FunctionInputVO;
import com.cn.jdshow.vo.RoleInputVO;

/**
 * 
 * @Description: 用户角色权限管理
 * @author mingxin.lu
 * @date 2016年5月9日 下午3:28:56   @version V1.0
 */
@Controller
@RequestMapping("/jdshow/rolefun")
public class RoleFunctionController {

	@Autowired
	private RoleFunctionService roleFunctionService;
	
	/**
	 * 
	 * @Description: 去往权限查询页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @date 2016年5月14日 下午10:33:08
	 */
	@RequestMapping("/goQueryFunction")
	public String goQueryFunction(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "admin/functionQuery";
	}
	
	@RequestMapping("/queryFunction")
	@ResponseBody
	public void queryFunction(PageParams params, HttpServletRequest request, HttpServletResponse response) {
		try {
			PageGrid<Function> pageGrid = roleFunctionService.queryByNormalPage(params, Function.class, null);
			SpringMVCUtil.printWriter(response, JSON.toJSONString(pageGrid));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description: fuction保存
	 * @param request
	 * @param response
	 * @param vo
	 * @date 2016年5月16日 下午2:52:53
	 */
	@RequestMapping("/insOrUpdFun")
	@ResponseBody
	public void insOrUpdFun(HttpServletRequest request, HttpServletResponse response,FunctionInputVO vo) {
		try {
			Function f = new Function();
			f.setFunCode(vo.getFunCode());
			f.setFunName(vo.getFunName());
			f.setJdurl(vo.getJdurl());
			f.setOrderd(vo.getOrderd());
			if(StringUtils.isNoneBlank(vo.getParentId())) {
				Function pf = roleFunctionService.findById(Function.class, Integer.parseInt(vo.getParentId()));
				f.setParentFun(pf);
				f.setIsLeafd("1");
			} else {
				f.setIsLeafd("0");
			}
			if(StringUtils.isNoneBlank(vo.getId())) {
				f.setId(Integer.parseInt(vo.getId()));
				roleFunctionService.update(f, Function.class);
			} else {
				roleFunctionService.save(f);
			}
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.successResult("success")));
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("fail")));
		}
	}
	
	/**
	 * 
	 * @Description: 删除function
	 * @param request
	 * @param response
	 * @param id
	 * @date 2016年5月16日 下午6:29:09
	 */
	@RequestMapping("/deleteFun")
	@ResponseBody
	public void deleteFun(HttpServletRequest request, HttpServletResponse response,@RequestParam("id") String id) {
		try{
			if(StringUtils.isNotBlank(id)) {
				roleFunctionService.deleteFunction(id);
				SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.successResult("success")));
			}
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("fail")));
		}
		
	}
	
	
	/**
	 * 
	 * @Description: 去往角色查询页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @date 2016年5月14日 下午10:33:08
	 */
	@RequestMapping("/goQueryRole")
	public String goQueryRole(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "admin/roleQuery";
	}
	
	@RequestMapping("/queryRole")
	@ResponseBody
	public void queryRole(PageParams params, HttpServletRequest request, HttpServletResponse response) {
		try {
			PageGrid<Role> pageGrid = roleFunctionService.queryByNormalPage(params, Role.class, null);
			SpringMVCUtil.printWriter(response, JSON.toJSONString(pageGrid));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description: Role保存
	 * @param request
	 * @param response
	 * @param vo
	 * @date 2016年5月16日 下午2:52:53
	 */
	@RequestMapping("/insOrUpdRole")
	@ResponseBody
	public void insOrUpdRole(HttpServletRequest request, HttpServletResponse response,RoleInputVO vo) {
		try {
			Role r = new Role();
			r.setRoleCode(vo.getRoleCode());
			r.setRoleName(vo.getRoleName());
			if(StringUtils.isNoneBlank(vo.getId())) {
				r.setId(Integer.parseInt(vo.getId()));
				roleFunctionService.update(r, Role.class);
			} else {
				roleFunctionService.save(r);
			}
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.successResult("success")));
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("fail")));
		}
	}
	
	/**
	 * 
	 * @Description: 删除Role
	 * @param request
	 * @param response
	 * @param id
	 * @date 2016年5月17日 下午1:39:30
	 */
	@RequestMapping("/deleteRole")
	@ResponseBody
	public void deleteRole(HttpServletRequest request, HttpServletResponse response,@RequestParam("id") String id) {
		try{
			if(StringUtils.isNotBlank(id)) {
				roleFunctionService.deleteRole(id);
				SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.successResult("success")));
			}
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("fail")));
		}
		
	}
	
	/**
	 * 
	 * @Description: 获取角色权限信息
	 * @param request
	 * @param response
	 * @param roleId
	 * @date 2016年5月19日 上午11:23:14
	 */
	@RequestMapping("/queryRoleFuns")
	@ResponseBody
	public void queryRoleFuns(HttpServletRequest request, HttpServletResponse response,@RequestParam("roleId") String roleId) {
		try {
			List<NormalZNodes> nzl = roleFunctionService.queryRoleFuns(roleId);
			SpringMVCUtil.printWriter(response, JSON.toJSONString(nzl, new PropertyFilterForBean(new String[]{"iconSkin","isParent"})));
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("fail")));
		}
	}
	
	@RequestMapping("/saveRoleFuns")
	@ResponseBody
	public void saveRoleFuns(HttpServletRequest request, HttpServletResponse response,@RequestParam("roleId") String roleId, @RequestParam("ids") String ids) {
		try {
			// 获取角色
			Role r = roleFunctionService.findById(Role.class, Integer.parseInt(roleId));
			String[] aids = ids.split(",");
			List<RoleFunction> rfs = new ArrayList<>();
			for(String id : aids) {
				RoleFunction rf = new RoleFunction();
				rf.setId(MongoDBSource.getIdByCollectionName(RoleFunction.class.getSimpleName()));
				rf.setRole(r);
				rf.setFunction(roleFunctionService.findById(Function.class, Integer.parseInt(id)));
				rfs.add(rf);
			}
			roleFunctionService.saveRoleFuns(rfs);
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.successResult("success")));
		} catch(Exception e) {
			e.printStackTrace();
			SpringMVCUtil.printWriter(response, JSON.toJSONString(AjaxResult.failResult("fail")));
		}
	}
}
