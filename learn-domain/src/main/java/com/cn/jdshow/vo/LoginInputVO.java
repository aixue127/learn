package com.cn.jdshow.vo;

/**
 * 
 * @Description: 登录VO
 * @author mingxin.lu
 * @date 2016年5月6日 下午2:17:35   @version V1.0
 */
public class LoginInputVO extends BaseVO{

	private String loginName;
	private String passWord;
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
