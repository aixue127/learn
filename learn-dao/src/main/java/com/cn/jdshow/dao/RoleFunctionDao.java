package com.cn.jdshow.dao;

import java.util.List;

import com.cn.jdshow.domain.RoleFunction;

/**
 * 
 * @Description: 角色权限控制
 * @author mingxin.lu
 * @date 2016年5月10日 上午11:58:42   @version V1.0
 */
public interface RoleFunctionDao extends BaseDao{
	
	/**
	 * 
	 * @Description: 获取RoleFunction更具RoleIds
	 * @param roleIds
	 * @return
	 * @throws Exception
	 * @date 2016年5月12日 下午2:19:58
	 */
	List<RoleFunction> queryRoleFunctions(List<Integer> roleIds) throws Exception;
	
}
