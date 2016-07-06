package com.cn.jdshow.service;

import java.util.List;

import com.cn.jdshow.domain.Function;
import com.cn.jdshow.domain.RoleFunction;
import com.cn.jdshow.model.NormalZNodes;


/**
 * 
 * @Description: 角色权限控制
 * @author mingxin.lu
 * @date 2016年5月10日 上午11:58:42   @version V1.0
 */
public interface RoleFunctionService extends BaseService{
	
	/**
	 * 
	 * @Description: 根据登录用户，获取当前权限
	 * @param roleId
	 * @return
	 * @date 2016年5月11日 上午11:08:35
	 */
	List<Function> queryUserFunction(int roleId) throws Exception;
	
	/**
	 * 
	 * @Description: 保存角色对应的权限
	 * @param rfs
	 * @throws Exception
	 * @date 2016年5月11日 上午11:17:23
	 */
	void saveRoleFuns(List<RoleFunction> rfs) throws Exception;
	
	/**
	 * 
	 * @Description: 删除权限，同时删除与角色的关联关系
	 * @param funId
	 * @date 2016年5月17日 上午11:01:07
	 */
	void deleteFunction(String funId) throws Exception;
	
	/**
	 * 
	 * @Description: 删除角色，同时删除用户角色、角色权限对应关系
	 * @param roleId
	 * @throws Exception
	 * @date 2016年5月17日 下午1:43:25
	 */
	void deleteRole(String roleId) throws Exception;
	
	/**
	 * 
	 * @Description: 获取角色权限信息
	 * @param roleId
	 * @return
	 * @date 2016年5月19日 上午11:26:26
	 */
	List<NormalZNodes> queryRoleFuns(String roleId) throws Exception;
}
