package com.cn.jdshow.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.cn.jdshow.vo.BaseVO;

/**
 * 
 * @Description: 菜单
 * @author mingxin.lu
 * @date 2016年6月12日 下午2:43:01   @version V1.0
 */
@Document(collection="Function")
@TypeAlias("Function")
public class Function implements Serializable {
	@Id
	private int id;
	@Field("funCode")
	private String funCode;
	@Field("funName")
	private String funName;
	@Field("jdurl")
	private String jdurl;
	@Field("createDate")
	private Date createDate;
	@Field("modifyDate")
	private Date modifyDate;
	@Field("orderd")
	private String orderd;
	@Field("isLeafd")
	private String isLeafd;
	@DBRef(db="Function")
	private Function parentFun;
	@Version Long version;
	
	@Transient
	private String isShow;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFunCode() {
		return funCode;
	}
	public void setFunCode(String funCode) {
		this.funCode = funCode;
	}
	public String getFunName() {
		return funName;
	}
	public void setFunName(String funName) {
		this.funName = funName;
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
	public String getJdurl() {
		return jdurl;
	}
	public void setJdurl(String jdurl) {
		this.jdurl = jdurl;
	}
	public Function getParentFun() {
		return parentFun;
	}
	public void setParentFun(Function parentFun) {
		this.parentFun = parentFun;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getOrderd() {
		return orderd;
	}
	public void setOrderd(String orderd) {
		this.orderd = orderd;
	}
	public String getIsLeafd() {
		return isLeafd;
	}
	public void setIsLeafd(String isLeafd) {
		this.isLeafd = isLeafd;
	}
	
}
