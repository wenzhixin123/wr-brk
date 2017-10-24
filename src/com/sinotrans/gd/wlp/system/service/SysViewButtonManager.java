package com.sinotrans.gd.wlp.system.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.ModuleGroupMenuItemTrue;
import com.sinotrans.gd.wlp.system.model.SysViewButtonModel;

public interface SysViewButtonManager extends BaseManager {

	SysViewButtonModel get(String id);

	List<SysViewButtonModel> getAll();

	List<SysViewButtonModel> findByExample(SysViewButtonModel example);

	SysViewButtonModel save(SysViewButtonModel model);

	List<SysViewButtonModel> saveAll(Collection<SysViewButtonModel> models);

	void remove(SysViewButtonModel model);

	void removeAll(Collection<SysViewButtonModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	List<ModuleGroupMenuItemTrue> getModuleGroupMenuitemTree();

	List<ModuleGroupMenuItemTrue> getModuleGroupTree();

	List<ModuleGroupMenuItemTrue> getModuleGroupOfficeNameTree();

	List<ModuleGroupMenuItemTrue> selectviewButtonall(String data[]);

	/**
	 * 用于验证编码是否重复
	 * 
	 * @param userCode
	 * @return
	 */
	boolean getYanZhenUserCode(String sysCode);

	/**
	 * 执行保存按钮信息。并且验证编码是否重复
	 * 
	 * @param viewButtonjson
	 * @return
	 */
	String saveViewButtonModel(String viewButtonjson, String officeCode);

	/**
	 * 修改按钮管理信息
	 * 
	 * @param viewButtonjson
	 * @param officeCode
	 */
	void updateViewButtonModel(String viewButtonjson, String officeCode);

	/*
	 * 多层次树形
	 */
	List<ModuleGroupMenuItemTrue> getModuleGroupMenuitemTree1();

	List<ModuleGroupMenuItemTrue> getModuleGroupMenuitemUserTree1(String UserId);
	
	String getUrlByButtonCode(String buttonCode);
	/**
	 * 获取权限的按钮
	 * 1、根据菜单UUID+用户+按钮
	 * 2、用户必须拥有此菜单权限才能拥有对应的按钮权限
	 * 3、根据按钮取得菜单信息
	 * @param svList
	 * @return
	 */
	SinotransPageJson getMenuItemButtonMap(List<SysViewButtonModel> svList);
}
