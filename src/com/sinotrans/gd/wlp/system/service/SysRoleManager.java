package com.sinotrans.gd.wlp.system.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.system.entity.ModuleGroupMenuItemTrue;
import com.sinotrans.gd.wlp.system.model.SysRoleModel;

public interface SysRoleManager extends BaseManager {

	SysRoleModel get(String id);

	List<SysRoleModel> getAll();

	List<SysRoleModel> findByExample(SysRoleModel example);

	SysRoleModel save(SysRoleModel model);

	List<SysRoleModel> saveAll(Collection<SysRoleModel> models);

	void remove(SysRoleModel model);

	void removeAll(Collection<SysRoleModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	List<BasOption> getOption(String code, String officeCode, String language);

	List<ModuleGroupMenuItemTrue> selectroleall();

	void saveAddRole(String rolejson, String officeCode);

	void saveUpdateRole(String rolejson, String officeCode);

	/**
	 * 验证角色是否与（用户角色、角色菜单）有关联关系。现在用于判断是否可以删除（删除按钮的控制）
	 * 
	 * @param sysRoleUuid
	 *            角色uuid
	 * @return 返回是（"true"）或者（"false"）两种结果 验证如果有关系则返回"true"
	 */
	String getRoleValidation(String sysRoleUuid);

	/**
	 * 用于验证角色编码是否重复
	 * 
	 * @param userCode
	 * @return
	 */
	boolean getYanZhenUserCode(String sysCode, String officeCode);

	/*
	 * 角色页面去过滤Admin
	 */
	SinotransDataGrid getRoldeleteAdmin(String status, String roleName,
			String roleNameEn, String roleType, String createTime_s,
			String createTime_e, PagingInfo pagingInfo, String officecod);

	SinotransDataGrid getRolORdeleteAdmin(PagingInfo pagingInfo,
			String officecod);

	/*
	 * 查询角色信息树形officecode
	 */
	List<ModuleGroupMenuItemTrue> selectroleoffice(String office);

	/*
	 * 查询角色信息树形Admin
	 */
	List<ModuleGroupMenuItemTrue> selectrole();
	
	List<ModuleGroupMenuItemTrue> getofficecodeModuleGroupMenuitemTree1(String code);
}
