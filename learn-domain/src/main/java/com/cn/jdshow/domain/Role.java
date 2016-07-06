package com.cn.jdshow.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 
 * @Description: 角色
 * @author mingxin.lu
 * @date 2016年6月12日 下午2:44:09   @version V1.0
 */
@Document(collection="Role")
@TypeAlias("Role")
public class Role  implements Serializable{
	
	@Id
	private int id;
	@Field("roleCode")
	private String roleCode;
	@Field("roleName")
	private String roleName;
	@Field("createDate")
	private Date createDate;
	@Field("modifyDate")
	private Date modifyDate;
	@Version Long version;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
