package com.cn.jdshow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.stereotype.Service;

import com.cn.jdshow.dao.RoleFunctionDao;
import com.cn.jdshow.domain.Function;
import com.cn.jdshow.domain.Role;
import com.cn.jdshow.domain.RoleFunction;
import com.cn.jdshow.domain.UserRole;
import com.cn.jdshow.model.NormalZNodes;
import com.cn.jdshow.service.RoleFunctionService;
import com.mongodb.DBRef;


@Service("roleFunctionService")
public class RoleFunctionServiceImpl extends BaseServiceImpl implements RoleFunctionService{

	@Autowired
	private RoleFunctionDao roleFunctionDao;
	
	public List<Function> queryUserFunction(int userId) throws Exception {
		if(userId == 0) {
			return roleFunctionDao.findChilds(Function.class, -1, "parentFun");
		} else {
			// 根据登录用户获取所属角色
			Map<String, Object> sqlmap = new HashMap<>();
			sqlmap.put("user.$id", userId);
			List<UserRole> urs = roleFunctionDao.queryListBySqlmap(UserRole.class, sqlmap);
			
			List<Integer> roleIds = new ArrayList<>();
			for(UserRole ur : urs) {
				roleIds.add(ur.getRole().getId());
			}
			List<RoleFunction> rfs = roleFunctionDao.queryRoleFunctions(roleIds);
			List<Function> alyFuns = new ArrayList<Function>();
			List<Integer> alyIds = new ArrayList<Integer>();
			for(RoleFunction rf : rfs) {
				if(!alyIds.contains(rf.getFunction().getId())) {
					alyFuns.add(rf.getFunction());
					alyIds.add(rf.getFunction().getId());
				}
			}
			return alyFuns;
		}
	}
	
	public void saveRoleFuns(List<RoleFunction> rfs) throws Exception {
		// 删除原始记录
		Map<String, Object> sqlmap = new HashMap<>();
		sqlmap.put("role", new DBRef("Role",rfs.get(0).getRole().getId()));
		roleFunctionDao.deleteByMap(sqlmap, RoleFunction.class);
		
		BulkOperations bulks = roleFunctionDao.getBulkOperations(RoleFunction.class, true);
		bulks.insert(rfs);
		bulks.execute();
	}
	
	public void deleteFunction(String funId) throws Exception {
		roleFunctionDao.deleteById(Function.class, Integer.parseInt(funId));
		// 同时删除RoleFunction中的记录
		Map<String, Object> sqlmap = new HashMap<String, Object>();
		sqlmap.put("function", new DBRef("Function", Integer.parseInt(funId)));
		roleFunctionDao.deleteByMap(sqlmap, RoleFunction.class);
	}
	
	public void deleteRole(String roleId) throws Exception {
		roleFunctionDao.deleteById(Role.class, Integer.parseInt(roleId));
		Map<String, Object> sqlmap = new HashMap<String, Object>();
		sqlmap.put("role", new DBRef("Role", Integer.parseInt(roleId)));
		roleFunctionDao.deleteByMap(sqlmap, UserRole.class);
		roleFunctionDao.deleteByMap(sqlmap, RoleFunction.class);
	}
	
	public List<NormalZNodes> queryRoleFuns(String roleId) throws Exception {
		// 获取已拥有的function
		Map<String, Object> sqlmap = new HashMap<>();
		sqlmap.put("role", new DBRef("Role",Integer.parseInt(roleId)));
		List<RoleFunction> rfs = roleFunctionDao.queryListBySqlmap(RoleFunction.class, sqlmap);
		List<Integer> alyIds = new ArrayList<>();
		for(RoleFunction rf : rfs) {
			alyIds.add(rf.getFunction().getId());
		}
		List<Function> fs = roleFunctionDao.queryAll(Function.class);
		List<NormalZNodes> nzs = new ArrayList<>();
		for(Function f : fs) {
			NormalZNodes nz = new NormalZNodes();
			nz.setId(f.getId());
			nz.setName(f.getFunName());
			nz.setpId(f.getParentFun() != null?f.getParentFun().getId():0);
			if(alyIds.contains(f.getId())) {
				nz.setChecked(true);
			}
			nzs.add(nz);
		}
		return nzs;
	}
}
