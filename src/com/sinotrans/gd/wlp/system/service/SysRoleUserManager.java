package com.sinotrans.gd.wlp.system.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.system.model.SysRoleUserModel;

public interface SysRoleUserManager extends BaseManager {

	SysRoleUserModel get(String id);

	List<SysRoleUserModel> getAll();

	List<SysRoleUserModel> findByExample(SysRoleUserModel example);

	SysRoleUserModel save(SysRoleUserModel model);

	List<SysRoleUserModel> saveAll(Collection<SysRoleUserModel> models);

	void remove(SysRoleUserModel model);

	void removeAll(Collection<SysRoleUserModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	List<SysRoleUserModel> selectuserroleall(String roleuuid);

	String saveUserRoleManagerMangToMang(String userAll, String roleAll);

	/**
	 * 根据用户UUID验证是否该用户有分配过的权限。现在用于在用户页面验证来判断删除按钮是否屏蔽
	 * 
	 * @param uuidUser
	 * @return 返回为字符串("true")或者("false")如果该用户有权限为解除则返回"true"否则返回"false"
	 */
	String getUserValidationUR(String uuidUser);
}
