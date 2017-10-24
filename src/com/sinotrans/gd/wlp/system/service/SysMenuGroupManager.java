package com.sinotrans.gd.wlp.system.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.system.entity.OfficeTree;
import com.sinotrans.gd.wlp.system.model.SysMenuGroupModel;

public interface SysMenuGroupManager extends BaseManager {

	SysMenuGroupModel get(String id);

	List<SysMenuGroupModel> getAll();

	List<SysMenuGroupModel> findByExample(SysMenuGroupModel example);

	SysMenuGroupModel save(SysMenuGroupModel model);

	List<SysMenuGroupModel> saveAll(Collection<SysMenuGroupModel> models);

	void remove(SysMenuGroupModel model);

	void removeAll(Collection<SysMenuGroupModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	public List<SysMenuGroupModel> findByGroupId(List<String> sysMenuGroupUuid);

	public List<SysMenuGroupModel> findUserByGroupId(
			List<String> sysMenuGroupUuid);

	/**
	 * 返回菜单分组列表。并且使用BasOption返回List转换为JSON后在前台页面用DWR调用(列表是所有的菜单分组)
	 * 
	 * @return
	 */
	String getMenuGroupModelMap();

	List<OfficeTree> getOfficetree();

	List<OfficeTree> getFatherOfficetree();

	public List<SysMenuGroupModel> getMenuGroupByPreUuid(String preUuid);

	/**
	 * 用于在编辑页面验证菜单编码
	 * 
	 * @param userCode
	 * @return 返回 true 为已存在
	 */
	boolean getYanZhenUserCode(String userCode);

	String saveMenuGroupOff(String menuitemjson, String officeCode);
	
    List<SysMenuGroupModel> selectmenuGroup();
	/**
	 * 修改数据
	 * 
	 * @param menuitemjson
	 * @param officeCode
	 * @return
	 */
	String updateMenuGroupOff(String menuitemjson, String officeCode);

	List<OfficeTree> getMenuGroupTree();

	/*
	 * 根据用户的角色查询用户拥有的菜单模块
	 */
	List<OfficeTree> getUserModuleOfficetree(String User);

	/**
	 * 根据用户code查询bar枪的二级菜单
	 * 
	 * @param userCode
	 * @param officeCode
	 * @return
	 * @throws Exception
	 */
	public List<SysMenuGroupModel> getMenuGroupByUserCode(String userCode,
			String officeCode) throws Exception;
}
