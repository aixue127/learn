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
 * @Description: 后天用户
 * @author mingxin.lu
 * @date 2016年6月12日 下午2:44:40   @version V1.0
 */
@Document(collection="User")
@TypeAlias("User")
public class User implements Serializable{
	@Id
	private int id;
	@Field("userName")
	private String userName;
	@Field("passWord")
	private String passWord;
	@Field("loginName")
	private String loginName;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
