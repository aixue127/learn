package com.cn.jdshow.dao.impl;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.cn.jdshow.dao.RoleFunctionDao;
import com.cn.jdshow.domain.RoleFunction;


@Repository("roleFunctionDao")
public class RoleFunctionDaoImpl extends BaseDaoImpl implements RoleFunctionDao{

	public List<RoleFunction> queryRoleFunctions(List<Integer> roleIds) throws Exception {
		return mongoTemplate.find(new Query(Criteria.where("role.$id").in(roleIds)), RoleFunction.class);
	}

}
