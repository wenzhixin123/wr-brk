package com.sinotrans.gd.wlp.system.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.system.model.SysMenuItemModel;

public interface SysMenuItemManager extends BaseManager {

	SysMenuItemModel get(String id);

	List<SysMenuItemModel> getAll();

	List<SysMenuItemModel> findByExample(SysMenuItemModel example);

	SysMenuItemModel save(SysMenuItemModel model);

	List<SysMenuItemModel> saveAll(Collection<SysMenuItemModel> models);

	void remove(SysMenuItemModel model);

	void removeAll(Collection<SysMenuItemModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	List<BasOption> getOption(String code, String officeCode, String language);

	void getMenuitemTree();

	public List<SysMenuItemModel> findByIds(List<String> sysMenuItemUuid);

	/**
	 * 返回单纯的菜单列表。并且使用BasOption返回List转换为JSON后在前台页面用DWR调用
	 * 
	 * @return
	 */
	String getMenuItemModelMap();

	public List<SysMenuItemModel> getMenuItemByGroupUuid(String groupUuid);

	public List<SysMenuItemModel> getMenuItemByUserCode(String userCode,
			String officeCode) throws Exception;

	/**
	 * 用于在编辑页面验证菜单编码
	 * 
	 * @param userCode
	 * @return 返回 true 为已存在
	 */
	boolean getYanZhenUserCode(String userCode);

	/**
	 * 新增数据
	 * 
	 * @param menuitemjson
	 * @param officeCode
	 * @return
	 */
	String saveMenuItemOff(String menuitemjson, String officeCode);

	/**
	 * 修改数据
	 * 
	 * @param menuitemjson
	 * @param officeCode
	 * @return
	 */
	String updateMenuItemOff(String menuitemjson, String officeCode);

	public List<SysMenuItemModel> getMenuItemByGroupUuidAndUserCode(
			String groupUuid, String userCode, String officeCode)
			throws Exception;
}
