package com.sinotrans.gd.wlp.system.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.system.model.SysRoleMenuButtonModel;

public interface SysRoleMenuButtonManager extends BaseManager {

	SysRoleMenuButtonModel get(String id);

	List<SysRoleMenuButtonModel> getAll();

	List<SysRoleMenuButtonModel> findByExample(SysRoleMenuButtonModel example);

	SysRoleMenuButtonModel save(SysRoleMenuButtonModel model);

	List<SysRoleMenuButtonModel> saveAll(
			Collection<SysRoleMenuButtonModel> models);

	void remove(SysRoleMenuButtonModel model);

	void removeAll(Collection<SysRoleMenuButtonModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	List<SysRoleMenuButtonModel> selectMenuButtonall(String roleuuid,
			String menuuuid);

	String saveRoleButtonOrMenuButton(String roleNews, String menuNews,
			String buttonNews, String rolemenujsons);
}
