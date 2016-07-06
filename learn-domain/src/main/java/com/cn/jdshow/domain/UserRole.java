package com.cn.jdshow.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @Description: 用户角色关联
 * @author mingxin.lu
 * @date 2016年6月12日 下午2:44:54   @version V1.0
 */
@Document(collection="UserRole")
@TypeAlias("UserRole")
public class UserRole implements Serializable{

	@Id
	private int id;
	@DBRef(db="User")
	private User user;
	@DBRef(db="Role")
	private Role role;
	@Version Long version;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
