package com.sinotrans.gd.wlp.system.service;

import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.system.model.SysUserModel;

public interface LoginManager extends BaseManager {

	/**
	 * 用户登录接口方法
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	SysUserModel login(String userName, String password);

	/**
	 * 取当前用户页面内授权的所有按钮权限
	 * @param url
	 * @return
	 */
	List<String> getGrantedButtonsForCurrentUser(String url);
	
	/**
	 * 取当前用户的统一身份验证ticket
	 * @param userName
	 * @return
	 */
	String getSSOTicket(String userName,String authCode);
}
