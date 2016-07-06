package com.cn.jdshow.service;

import java.util.List;

import com.cn.jdshow.domain.UserRole;
import com.cn.jdshow.model.NormalZNodes;

public interface UserService extends BaseService {

	/**
	 * 
	 * @Description: 保存用户所属角色
	 * @param userRoles
	 * @throws Exception
	 * @date 2016年5月11日 上午11:46:28
	 */
	void saveUserRole(List<UserRole> userRoles) throws Exception;
	
	/**
	 * 
	 * @Description: 根据userId 删除用户信息，包含关联角色表
	 * @param userId
	 * @date 2016年5月17日 上午11:47:03
	 */
	void deleteUser(String userId) throws Exception;
	
	/**
	 * 
	 * @Description: 获取用户权限
	 * @param userId
	 * @return
	 * @throws Exception
	 * @date 2016年5月17日 下午6:17:56
	 */
	List<NormalZNodes> queryUserRole(String userId, int id) throws Exception;
}
