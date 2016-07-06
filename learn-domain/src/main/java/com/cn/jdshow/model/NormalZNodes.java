package com.cn.jdshow.model;

import java.io.Serializable;

/**
 * 
 * @Description: 普通树形结构类
 * @author mingxin.lu
 * @date 2016年5月18日 下午5:36:05   @version V1.0
 */
public class NormalZNodes implements Serializable{
	private int id;  // ID
	private int pId; // PID
	private String name; //node name
	private boolean open; //node is open?
	private String treeCode;
	public String getTreeCode() {
		return treeCode;
	}
	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}
	private boolean checked; //node is check ?
	private String iconSkin;
	private String icon;
	private boolean isParent;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getIconSkin() {
		if(this.isParent) {
			return "pIcon01";
		} else {
			return "icon06";
		}
	}
	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
}
