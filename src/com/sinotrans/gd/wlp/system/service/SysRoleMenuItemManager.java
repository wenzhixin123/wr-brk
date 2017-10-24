package com.sinotrans.gd.wlp.system.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.model.SysRoleMenuItemModel;

public interface SysRoleMenuItemManager extends BaseManager {

	SysRoleMenuItemModel get(String id);

	List<SysRoleMenuItemModel> getAll();

	List<SysRoleMenuItemModel> findByExample(SysRoleMenuItemModel example);

	SysRoleMenuItemModel save(SysRoleMenuItemModel model);

	List<SysRoleMenuItemModel> saveAll(Collection<SysRoleMenuItemModel> models);

	void remove(SysRoleMenuItemModel model);

	void removeAll(Collection<SysRoleMenuItemModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	public List<String> findItemIdByRole(List<String> sysRoleUuid);

	List<SysRoleMenuItemModel> getMenuitemall(String roleuuid);

	SinotransPageJson saveRoleMenuManager(String saveRoleMenuManagerall);
	
	String saveRoleMenuManagerNOBase64(String saveRoleMenuManagerall);

	/**
	 * 根据菜单项UUID验证是否已经被分组。
	 * 
	 * @param sysMenuItemUuid
	 * @return 返回结果("true")或者("false") 如果更具菜单UUID在菜单分组内查询到了结果。那么返回"true"
	 */
	String getValidationMenuItemAssociation(String sysMenuItemUuid);
}
