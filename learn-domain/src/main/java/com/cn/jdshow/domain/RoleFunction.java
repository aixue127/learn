package com.cn.jdshow.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @Description: 角色权限关联
 * @author mingxin.lu
 * @date 2016年6月12日 下午2:44:20   @version V1.0
 */
@Document(collection="RoleFunction")
@TypeAlias("RoleFunction")
public class RoleFunction implements Serializable{
	@Id
	private int id;
	@DBRef(db="Role")
	private Role role;
	@DBRef(db="Function")
	private Function function;
	@Version Long version;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Function getFunction() {
		return function;
	}
	public void setFunction(Function function) {
		this.function = function;
	}
	
}
