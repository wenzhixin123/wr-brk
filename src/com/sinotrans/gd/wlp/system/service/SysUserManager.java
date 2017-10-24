package com.sinotrans.gd.wlp.system.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.ModuleGroupMenuItemTrue;
import com.sinotrans.gd.wlp.system.model.SysUserModel;

public interface SysUserManager extends BaseManager {

	SysUserModel get(String id);

	List<SysUserModel> getAll();

	List<SysUserModel> findByExample(SysUserModel example);

	SysUserModel save(SysUserModel model);

	List<SysUserModel> saveAll(Collection<SysUserModel> models);

	void remove(SysUserModel model);

	void removeAll(Collection<SysUserModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	List<ModuleGroupMenuItemTrue> seleuserall();

	List<ModuleGroupMenuItemTrue> seleuserEQoffice(String officegroup);

	/**
	 * 通过用户登录名称或者中文名称模糊搜索用户
	 * 
	 * @param code4Name
	 * @param language
	 *            语言
	 * @return
	 */
	List<BasOption> findUserByCode4Name(String code4Name, String language);

	/**
	 * 修改密码,需要加密
	 * 
	 * @param request中的
	 *            Map<String, String> params
	 * @return 更新密码后的用户对象
	 */
	String changePassword(Map<String, String> params);

	String savaUser(String userjson);
	
	public SinotransPageJson resetpassword(String usersid);

	String updateUser(String userjson, String User);

	/**
	 * 用于在编辑页面验证用户编码
	 * 
	 * @param userCode
	 * @return
	 */
	boolean getYanZhenUserCode(String userCode);

	boolean updateSysStatusOue(String loUuid, String status, String tableName,
			String statusName, String uuidName);

	/*
	 * 去掉Admin
	 */
	SinotransDataGrid getdeleteAdmin(String office, String userName,
			String userType,String roleCode, String createTime_s, String createTime_e,
			PagingInfo pagingInfo, String officecod,String biaoshi );

	/*
	 * Admin查询数据
	 */
	SinotransDataGrid getuserListAdmin(String office, String userName,
			String userType,String roleCode, String createTime_s, String createTime_e,
			PagingInfo pagingInfo, String officecod,String biaoshi );
	
	
	SinotransDataGrid getRolUserdeleteAdmin(String office,
			PagingInfo pagingInfo, String officecod);

	/*
	 * 查询所属公司
	 */
	SinotransDataGrid selectCompany(PagingInfo pagingInfo);
	
	
	String getUsername(String name);
	
	void uploadCreateMember(SysUserModel sysusermodel);
	
	void uploadUpdateMember(SysUserModel sysusermodel);
	
	void uploadStopUser(SysUserModel sysusermodel);
	
	String generateMemberInfo(SysUserModel sysusermodel);
	
	String getUserCustomerCode();
	
	String getEmailByUserCode(String userCode);
}
