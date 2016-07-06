package com.cn.jdshow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.stereotype.Service;

import com.cn.jdshow.dao.UserDao;
import com.cn.jdshow.domain.Role;
import com.cn.jdshow.domain.User;
import com.cn.jdshow.domain.UserRole;
import com.cn.jdshow.model.NormalZNodes;
import com.cn.jdshow.service.UserService;
import com.mongodb.DBRef;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	public void saveUserRole(List<UserRole> userRoles) throws Exception {
		// 删除原始信息
		Map<String, Object> sqlmap = new HashMap<>();
		sqlmap.put("user", new DBRef("User",userRoles.get(0).getUser().getId()));
		userDao.deleteByMap(sqlmap, UserRole.class);
		
		BulkOperations bulks = userDao.getBulkOperations(UserRole.class, false);
		bulks.insert(userRoles).execute().getInsertedCount();
	}
	
	public void deleteUser(String userId) throws Exception {
		userDao.deleteById(User.class, Integer.parseInt(userId));
		Map<String, Object> sqlmap = new HashMap<>();
		sqlmap.put("user", new DBRef("User", Integer.parseInt(userId)));
	}
	
	public List<NormalZNodes> queryUserRole(String userId, int id) throws Exception {
		Map<String, Object> sqlmap = new HashMap<>();
		sqlmap.put("user", new DBRef("User", Integer.parseInt(userId)));
		List<UserRole> urs = userDao.queryListBySqlmap(UserRole.class, sqlmap);
		List<Integer> rIds = new ArrayList<>();
		for(UserRole ur : urs) {
			rIds.add(ur.getRole().getId());
		}
		List<Role> rs = userDao.queryAll(Role.class);
		return this.generatTreesRole(rs, rIds);
	}
	
	/**
	 * 
	 * @Description: 组织树形结构
	 * @param roles
	 * @param neddCheckId
	 * @return
	 * @date 2016年5月19日 上午9:49:09
	 */
	private List<NormalZNodes> generatTreesRole(List<Role> roles , List<Integer> neddCheckId) {
		List<NormalZNodes> treeList = new ArrayList<>();
		for(Role r : roles) {
			NormalZNodes rz = new NormalZNodes();
			rz.setId(r.getId());
			rz.setName(r.getRoleName());
			rz.setOpen(true);
			if(neddCheckId.contains(r.getId())) {
				rz.setChecked(true);
			} else {
				rz.setChecked(false);
			}
			rz.setpId(0);
			treeList.add(rz);
		}
		return treeList;
	}
}
