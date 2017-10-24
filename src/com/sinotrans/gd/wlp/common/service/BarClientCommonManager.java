package com.sinotrans.gd.wlp.common.service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import org.json.JSONException;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.model.BarcodeWorkModel;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.model.SysUserModel;

/**
 * @author hth
 * 
 * bar枪CS客户端服务类
 * 
 */
public interface BarClientCommonManager extends BaseManager {

	List<SysUserModel> findUserMessage(String userId);



	/**
	 * 配置验证officeCode
	 * 
	 * @param officeCode
	 * @return
	 */
	SinotransPageJson validConnection(String officeCode);

	/**
	 * 用户登录验证
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	SinotransPageJson validLogin(String userId, String password);

	/**
	 * 用户按钮授权验证
	 * @param userCode
	 * @param btnCode
	 * @param officeCode
	 * @return
	 */
	SinotransPageJson authorizedCodeLogin(String userCode, String password, String btnCode, String officeCode);
	
	/**
	 * 客户端-验证登录退出
	 * @param userId
	 * @param password
	 * @return
	 */
	SinotransPageJson loginExit(String userId, String password);
	
	/**
	 * 验证是否连通
	 * 
	 * @return
	 */
	SinotransPageJson checkConnection();

	/**
	 * 获取默认货位
	 * 
	 * @param controlWord
	 * @param officeCode
	 * @return
	 */
	SinotransPageJson getDefaultLotStock(String controlWord, String officeCode)
			throws Exception;

	
	/**
	 * 2级菜单按钮查询
	 * 
	 * @param menuName
	 * @param userCode
	 * @param officeCode
	 * @return
	 */
	public SinotransPageJson checkMenuPermission(String menuName,
			String userCode, String officeCode);



	SinotransPageJson barFindVersionByOfficeCode(String officeCode);
	
}