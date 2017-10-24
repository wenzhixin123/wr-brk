package com.sinotrans.gd.wlp.system.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.ws.RequestWrapper;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.framework.core.support.servlet.Http;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.framework.core.util.JSONDataUtils;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeDefModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeTypeModel;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeDefManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeTypeManager;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.common.service.LogisticsOrderManager;
import com.sinotrans.gd.wlp.common.service.SQLQueryManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.ModuleGroupMenuItemTrue;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.system.model.SysOfficeModel;
import com.sinotrans.gd.wlp.system.model.SysUserModel;
import com.sinotrans.gd.wlp.system.query.OfficeCodeQueryCondition;
import com.sinotrans.gd.wlp.system.query.OfficeCodeQueryItem;
import com.sinotrans.gd.wlp.system.query.SysUserAdminQueryCondition;
import com.sinotrans.gd.wlp.system.query.SysUserNoRoleAdminQueryCondition;
import com.sinotrans.gd.wlp.system.query.SysUserNoRoleAdminQueryItem;
import com.sinotrans.gd.wlp.system.query.SysUserNoRoleQueryCondition;
import com.sinotrans.gd.wlp.system.query.SysUserNoRoleQueryItem;
import com.sinotrans.gd.wlp.system.query.SysUserQueryCondition;
import com.sinotrans.gd.wlp.system.query.SysUserQueryItem;
import com.sinotrans.gd.wlp.system.service.SysOfficeManager;
import com.sinotrans.gd.wlp.system.service.SysUserManager;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.JsonUtil;
import com.sinotrans.gd.wlp.util.ListUtil;
import com.sinotrans.gd.wlp.util.StringUtil;

@Service
public class SysUserManagerImpl extends BaseManagerImpl implements
		SysUserManager {
	@Autowired
	private SysOfficeManager sysOfficeManager;
	@Autowired
	private SQLQueryManager sqlQueryManager;
	@Autowired
	private LogisticsOrderManager logisticsOrderManager;
	@Autowired
	private BasCodeTypeManager basCodeTypeManager;
	@Autowired
	private BasCodeDefManager basCodeDefManager;
	
	private static String NO_USER="用户不存在。";
	
	@Resource(name="arapMemberSvc")
	private Http arapMemberSvc;	
	
	public SysUserModel get(String id) {
		try {
			return this.dao.get(SysUserModel.class, id);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<SysUserModel> getAll() {
		return this.dao.getAll(SysUserModel.class);
	}

	public List<SysUserModel> findByExample(SysUserModel example) {
		return this.dao.findByExample(example);
	}

	public SysUserModel save(SysUserModel model) {
		return this.dao.save(model);
	}

	public List<SysUserModel> saveAll(Collection<SysUserModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysUserModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysUserModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysUserModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysUserModel.class, ids);
	}

	public List<ModuleGroupMenuItemTrue> seleuserall() {
		List<SysUserModel> sysuserall = this.getAll();
		List<ModuleGroupMenuItemTrue> mlgmitree = new ArrayList<ModuleGroupMenuItemTrue>();
		List<ModuleGroupMenuItemTrue> mlgmitrees = new ArrayList<ModuleGroupMenuItemTrue>();
		ModuleGroupMenuItemTrue smgmit = new ModuleGroupMenuItemTrue();
		smgmit.setId("0");
		smgmit.setText("员工");
		for (SysUserModel sysusermodel : sysuserall) {
			ModuleGroupMenuItemTrue mgmit = new ModuleGroupMenuItemTrue();
			mgmit.setId(sysusermodel.getUserUuid());
			mgmit.setText(sysusermodel.getUserName());
			mlgmitree.add(mgmit);
		}
		smgmit.setChildren(mlgmitree);
		mlgmitrees.add(smgmit);
		return mlgmitrees;
	}

	public List<ModuleGroupMenuItemTrue> seleuserEQoffice(String officegroup) {
		List<String> su = new ArrayList<String>();
		try {
			String[] officeid = officegroup.split(",");
			for (int i = 0; i < officeid.length; i++) {
				su.add(officeid[i]);
			}
		} catch (Exception e) {
			su.add(officegroup);
		}

		List<SysUserModel> userOfficeList = new ArrayList<SysUserModel>();
		userOfficeList = this.dao.createCommonQuery(SysUserModel.class)
				.addCondition(Condition.in("sysMenuItemUuid", su.toArray()))
				.setOrderBy("departmentCode").query();
		List<ModuleGroupMenuItemTrue> mlgmitree = new ArrayList<ModuleGroupMenuItemTrue>();
		ModuleGroupMenuItemTrue smgmit = new ModuleGroupMenuItemTrue();
		smgmit.setId("0");
		smgmit.setText("员工");
		mlgmitree.add(smgmit);
		for (SysUserModel sysusermodel : userOfficeList) {
			ModuleGroupMenuItemTrue mgmit = new ModuleGroupMenuItemTrue();
			mgmit.setId(sysusermodel.getUserUuid());
			mgmit.setText(sysusermodel.getUserName());
			mlgmitree.add(mgmit);
		}
		return mlgmitree;
	}

	@Override
	public List<BasOption> findUserByCode4Name(String code4Name, String language) {
		List<BasOption> optionList = new ArrayList<BasOption>();
		Condition condition = (CommonUtil.ZH_CN.equals(language)) ? Condition
				.ilikeAnywhere("userName", code4Name) : Condition
				.ilikeAnywhere("userNameEn", code4Name);
		PagingInfo pagin = new PagingInfo();
		pagin.setPageSize(10);// 最多查询十个人
		List<SysUserModel> userList = dao.createCommonQuery(SysUserModel.class)
				.setPagingInfo(pagin).addCondition(Condition.or(condition))
				.query();
		if (userList != null) {
			for (SysUserModel user : userList) {
				String userName = ((CommonUtil.ZH_CN.equals(language)) ? user
						.getUserName() : user.getUserNameEn());
				optionList.add(new BasOption(user.getUserUuid(), userName));
			}
		}
		return optionList;
	}

	@Override
	public String changePassword(Map<String, String> params) {
		String originalPsw = params.get("originalPsw");
		PasswordEncoder passWordEncoder =(PasswordEncoder)ContextUtils.getBean("passwordEncoder");
		originalPsw = passWordEncoder.encodePassword(originalPsw, null);
		String newPassword = params.get("newPassword");
		String userCode = params.get("userCode");
		String userName = params.get("userName");
		SysUserModel model = new SysUserModel();
		model.setUserCode(userCode);
		model.setUserName(userName);
		List<SysUserModel> modelList = this.dao.findByExample(model);
		if (modelList.size() == 1) {
			if (RcUtil.areEquals(modelList.get(0).getPassword(), originalPsw)) {
				newPassword = passWordEncoder.encodePassword(newPassword, null);
				modelList.get(0).setPassword(newPassword);
				if (!this.save(modelList.get(0)).getPassword().equals(
						originalPsw)) {
					return "true";
				}
			} else {
				return "原密码输入错误";
			}
		}
		if (modelList == null || modelList.size() == 0)
			return "错误:系统没找到相应的帐户";
		if (modelList.size() > 1)
			return "错误:找到相同的" + modelList.size() + "条账号";
		return "新密码不能与原密码一致,请重新修改!";
	}

	// 保存用户信息
	public String savaUser(String userjson) {
		userjson = logisticsOrderManager.getBase642Ojbect(userjson);
		SysUserModel sysusermodel = (SysUserModel) JsonUtil.jsonToBean(
				userjson, SysUserModel.class); // 讲JSON数据转换为Model数据
		if (getYanZhenUserCode(sysusermodel.getUserCode())) {
			throw new ApplicationException("该编码已存在、请审核后重新填写！");
		} else {
			sysusermodel.setUserCode(StringUtil.toTrim(sysusermodel
					.getUserCode()));
			sysusermodel.setRowState(BaseModel.ROW_STATE_ADDED);// 行状态
			PasswordEncoder passWordEncoder =(PasswordEncoder)ContextUtils.getBean("passwordEncoder");
			sysusermodel.setPassword(passWordEncoder.encodePassword(
					sysusermodel.getPassword(), null));// 加密用户密码
			SysOfficeModel office = sysOfficeManager.get(sysusermodel
					.getOfficeCode()); // 通过所选择的公司(仓库)UUID来查询出所在Model
			sysusermodel.setOfficeCode(office.getOfficeCode());// 将所查询出来的officeCode加入到用户的officeCode的中
			try {
				this.save(sysusermodel);
				//uploadCreateMember(sysusermodel);//将用户上传至统一身份验证平台
			} catch (RuntimeException e) {
				throw new ApplicationException("操作失败");
			}
		}
		return null;
	}

	// 保存用户信息
	public String updateUser(String userjson, String User) {
		userjson = logisticsOrderManager.getBase642Ojbect(userjson);
		// 根据用户编码找出用户
		SysUserModel user = new SysUserModel();
		user.setUserCode(User);
		List<SysUserModel> users = this.findByExample(user);
		SysUserModel us = this.get(users.get(0).getUserUuid());

		SysUserModel sysusermodel = (SysUserModel) JsonUtil.jsonToBean(
				userjson, SysUserModel.class);
		sysusermodel.setRowState(BaseModel.ROW_STATE_MODIFIED);
		// 用户在这里不能修改密码 所以给空值
		SysOfficeModel office = sysOfficeManager.get(sysusermodel
				.getOfficeCode()); // 通过所选择的公司(仓库)UUID来查询出所在Model
		sysusermodel.setOfficeCode(office.getOfficeCode());// 将所查询出来的officeCode加入到用户的officeCode的中
		if (!us.getStatus().equals("")
				&& sysusermodel.getUserCode().equals(us.getUserCode())) {
			if (sysusermodel.getStatus().equals(CommonUtil.Active)) {
				this.save(sysusermodel);
				uploadUpdateMember(sysusermodel);//将用户上传至统一身份验证平台
			} else {
				throw new ApplicationException("不能修改自己的状态！");
			}
		} else {
			this.save(sysusermodel);
			uploadUpdateMember(sysusermodel);//将用户上传至统一身份验证平台
		}
		return null;
	}

	/**
	 * 用于在编辑页面验证用户编码
	 * 
	 * @param userCode
	 * @return 返回 true 为已存在
	 */
	public boolean getYanZhenUserCode(String userCode) {
		SysUserModel user = new SysUserModel();
		user.setUserCode(userCode.trim());
		List<SysUserModel> userList = this.findByExample(user);
		if (userList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 根据传送参数（uuid、修改值、表名、修改列名、条件名）此方法暂时只用与、用户、角色、按钮、菜单四个状态的修改 并且只能是单一数据修改
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sinotrans.gd.wms.system.service.SysUserManager#updateSysStatusOue
	 * (java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	public boolean updateSysStatusOue(String uuid, String status,
			String tableName, String statusName, String uuidName) {
		String seleSys = "select sy.status from " + tableName + " sy where sy."
				+ uuidName + "='" + uuid + "'";
		List<Object[]> objl = sqlQueryManager.getSqlResultList(seleSys, "");
		String iso = "";
		if (objl == null || objl.get(0).length == 0) {
			// 如果根据UUID查询出现数据为空的话可能有其他用户在使用或者已被其他用户删除所以在报错误
			throw new ApplicationException("未获取到相应的对象！");
		} else {
			iso = objl.get(0)[0].toString();
		}
		if (status != null && !status.equals("")) {
			// 这里判断 页面传送的值是作废的话那么从数据库中查询出来的数据必须是有效的。否则报错。
			if (status.equals(CommonUtil.Cancel)
					&& iso.equals(CommonUtil.Active)) {
				// 如果满足条件则不处理 可以继续执行 有效数据可以作废
			} else if (status.equals(CommonUtil.Cancel)
					&& iso.equals(CommonUtil.Cancel)) {
				throw new ApplicationException("该数据已作废！");
			} else if (status.equals(CommonUtil.Cancel)
					&& iso.equals(CommonUtil.Pending)) {
				throw new ApplicationException("该数据未生效、作废无效！");
			} else if (status.equals(CommonUtil.Active)
					&& iso.equals(CommonUtil.Active)) {
				throw new ApplicationException("该数据已生效！");
			} else if (status.equals(CommonUtil.Active)
					&& iso.equals(CommonUtil.Pending)) {
				// 如果满足条件则不处理 可以继续执行 草稿数据可以生效
			} else if (status.equals(CommonUtil.Active)
					&& iso.equals(CommonUtil.Cancel)) {
				// 如果满足条件则不处理 可以继续执行 作废数据可以生效
			}
		}
		try {
			String loSql = "update " + tableName + " sys set sys." + statusName
					+ " = '" + status + "' where sys." + uuidName + " ='"
					+ uuid + "'";
			sqlQueryManager.executeSQL(loSql, "", true);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/*
	 * 用户页面查询去掉Admin
	 */
	public SinotransDataGrid getdeleteAdmin(String office, String userName,
			String userType, String roleCode, String createTime_s,
			String createTime_e, PagingInfo pagingInfo, String officecode,String biaoshi) {

		// List<SysUserModel> user = new ArrayList<SysUserModel>();
		// SinotransDataGrid adb = null;
		// user = (List<SysUserModel>) this.dao.createCommonQuery(
		// SysUserModel.class).addCondition(
		// Condition.ne("userCode", CommonUtil.SYSTEM_ADMIN))
		// .addCondition(Condition.eq("officeCode", officecod))
		// .setPagingInfo(pagingInfo).query();
		// adb = new SinotransDataGrid(user, pagingInfo.getTotalRows(),
		// pagingInfo
		// .getTotalPages());
		// return adb;
		/*
		 * StringBuffer sb = new StringBuffer(); List<Object[]> resultList = new
		 * ArrayList<Object[]>(); SinotransDataGrid adb = null; sb.append(
		 * " select USER_UUID,STATUS,USER_CODE,USER_NAME,USER_NAME_EN,CUSTOMER_CODE,USER_TYPE,POSITION,EMAIL,CREATE_TIME,MOBILE,EDUCATION from SYS_USER su"
		 * );
		 * 
		 * sb.append(" where su.user_code <> 'ADMIN'");
		 * sb.append(" and  su.OFFICE_CODE ='" + officecode + "'"); if
		 * (!RcUtil.strIsEmpty(userName)) {
		 * sb.append(" and su.user_name like '%" + userName + "%'"); } if
		 * (!RcUtil.strIsEmpty(userType)) { sb.append(" and su.USER_TYPE='" +
		 * userType + "'"); } if (!RcUtil.strIsEmpty(createTime_s) &&
		 * !RcUtil.strIsEmpty(createTime_e)) {
		 * sb.append(" and su.create_Time >=to_date('" + createTime_s +
		 * "','YYYY:MM:dd')  and su.create_Time <=to_date('" + createTime_e +
		 * "','YYYY:MM:dd')"); } resultList =
		 * sqlQueryManager.getSqlResultList(sb.toString(), "");
		 * List<SysUserModel> user = new ArrayList<SysUserModel>(); for
		 * (Object[] bo : resultList) { if (bo != null) { SysUserModel bonModel
		 * = new SysUserModel(); if (bo[0] != null)
		 * bonModel.setUserUuid(bo[0].toString()); if (bo[1] != null)
		 * bonModel.setStatus(bo[1].toString()); if (bo[2] != null)
		 * bonModel.setUserCode(bo[2].toString()); if (bo[3] != null)
		 * bonModel.setUserName(bo[3].toString()); if (bo[4] != null)
		 * bonModel.setUserNameEn(bo[4].toString()); if (bo[5] != null)
		 * bonModel.setCustomerCode(bo[5].toString()); if (bo[6] != null)
		 * bonModel.setUserType(bo[6].toString()); if (bo[7] != null)
		 * bonModel.setPosition(bo[7].toString()); if (bo[8] != null)
		 * bonModel.setEmail(bo[8].toString()); //
		 * if(bo[9]!=null)bonModel.setCreateTime(bo[9].toString()); if (bo[10]
		 * != null) bonModel.setMobile(bo[10].toString()); if (bo[11] != null)
		 * bonModel.setEducation(bo[11].toString()); user.add(bonModel); } } adb
		 * = new SinotransDataGrid(user, pagingInfo.getTotalRows(), pagingInfo
		 * .getCurrentPage()); return adb;
		 */
		SinotransDataGrid adb = new SinotransDataGrid();
		
		//点击了查询按钮 标识为OK  
		if("ok".equals(biaoshi)){
			OfficeCodeQueryCondition officecondition =new OfficeCodeQueryCondition();
			officecondition.setOfficeCode(officecode);
			List<OfficeCodeQueryItem> list=this.dao.query(officecondition, OfficeCodeQueryItem.class);
			String[] Str = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				OfficeCodeQueryItem officeCodeQueryItem = list.get(i);
				Str[i] =officeCodeQueryItem.getOfficeCode();
			}
			//判断是是否写了 按角色查询 条件不一样用了两个查询语句
			if (RcUtil.isEmpty(roleCode)) {
				StringBuffer sb = new StringBuffer();
				List<SysUserNoRoleQueryItem> resultList = new ArrayList<SysUserNoRoleQueryItem>();
				SysUserNoRoleQueryCondition condition = new SysUserNoRoleQueryCondition();
				condition.setOfficeCode(Str);
				condition.setUserName(userName);
				condition.setUserType(userType);
				condition.setStartDate(RcUtil.string2date(createTime_s,
						RcUtil.yyyy_MM_dd));
				condition.setEndDate(RcUtil.string2date(createTime_e,
						RcUtil.yyyy_MM_dd));
				resultList = this.dao.query(condition,
						SysUserNoRoleQueryItem.class, sb.toString(), null, null,
						pagingInfo);
				if (resultList.size() > 0) {
					adb.setPage(pagingInfo.getCurrentPage());
					adb.setTotal(pagingInfo.getTotalRows());
					adb.setRows(resultList);
				}

			} else {
				StringBuffer sb = new StringBuffer();
				List<SysUserQueryItem> resultList = new ArrayList<SysUserQueryItem>();

				SysUserQueryCondition condition = new SysUserQueryCondition();
				condition.setOfficeCode(Str);
				condition.setUserName(userName);
				condition.setUserType(userType);
				condition.setRoleCode(roleCode);
				condition.setStartDate(RcUtil.string2date(createTime_s,
						RcUtil.yyyy_MM_dd));
				condition.setEndDate(RcUtil.string2date(createTime_e,
						RcUtil.yyyy_MM_dd));
				resultList = this.dao.query(condition, SysUserQueryItem.class, sb
						.toString(), null, null, pagingInfo);
				if (resultList.size() > 0) {
					adb.setPage(pagingInfo.getCurrentPage());
					adb.setTotal(pagingInfo.getTotalRows());
					adb.setRows(resultList);
				}
			}
			
		}else{
			if(!RcUtil.isEmpty(office)){
				boolean bool=false;
				OfficeCodeQueryCondition officecondition1 =new OfficeCodeQueryCondition();
				officecondition1.setOfficeCode(officecode);
				List<OfficeCodeQueryItem> list1=this.dao.query(officecondition1, OfficeCodeQueryItem.class);
				for (OfficeCodeQueryItem officeCodeQueryItem : list1) {
					if(officeCodeQueryItem.getOfficeCode().equals(office)){
						bool=true;
					}
				}
				if(bool){
					String[] s={office};
					SysUserNoRoleQueryCondition officecondition =new SysUserNoRoleQueryCondition();
					officecondition.setOfficeCode(s);
					List<SysUserNoRoleQueryItem> list=this.dao.query(officecondition, SysUserNoRoleQueryItem.class);
					if (list.size() > 0) {
						adb.setPage(pagingInfo.getCurrentPage());
						adb.setTotal(pagingInfo.getTotalRows());
						adb.setRows(list);
					}
				}		
			}else{
				String[] s={officecode};
				SysUserNoRoleQueryCondition officecondition =new SysUserNoRoleQueryCondition();
				officecondition.setOfficeCode(s);
				List<SysUserNoRoleQueryItem> list=this.dao.query(officecondition, SysUserNoRoleQueryItem.class);
				if (list.size() > 0) {
					adb.setPage(pagingInfo.getCurrentPage());
					adb.setTotal(pagingInfo.getTotalRows());
					adb.setRows(list);
				}
			}
			
		}

		return adb;

	}

	/*
	 * 用户与角色页面查询过滤Admin
	 */
	public SinotransDataGrid getRolUserdeleteAdmin(String office,
			PagingInfo pagingInfo, String officecod) {
		List<SysUserModel> user = new ArrayList<SysUserModel>();
		SinotransDataGrid adb = null;
		if (RcUtil.strIsEmpty(office) || office.equals(officecod)) {
			user = (List<SysUserModel>) this.dao.createCommonQuery(
					SysUserModel.class).addCondition(
					Condition.ne("userCode", CommonUtil.SYSTEM_ADMIN))
					.addCondition(Condition.eq("status", CommonUtil.Active))
					.addCondition(Condition.eq("officeCode", officecod))
					.setPagingInfo(pagingInfo).query();
		} else {
			user = (List<SysUserModel>) this.dao.createCommonQuery(
					SysUserModel.class).addCondition(
					Condition.ne("userCode", CommonUtil.SYSTEM_ADMIN))
					.addCondition(Condition.eq("status", CommonUtil.Active))
					.addCondition(Condition.eq("officeCode", officecod))
					.addCondition(Condition.eq("officeCode", office))
					.setPagingInfo(pagingInfo).query();
		}

		adb = new SinotransDataGrid(user, pagingInfo.getTotalRows(), pagingInfo
				.getTotalPages());
		return adb;
	}

	/*
	 * 查询所属公司
	 */
	public SinotransDataGrid selectCompany(PagingInfo pagingInfo) {
		SinotransDataGrid adb = null;
		List<SysOfficeModel> SysOffice = sysOfficeManager.getAll();
		List<SysOfficeModel> Office = new ArrayList<SysOfficeModel>();
		for (SysOfficeModel sysOffice : SysOffice) {
			if (sysOffice.getPreOfficeUuid() == null
					|| sysOffice.getPreOfficeUuid().equals("")) {
				Office.add(sysOffice);
			}
		}
		adb = new SinotransDataGrid(Office, pagingInfo.getTotalRows(),
				pagingInfo.getTotalPages());
		return adb;
	}

	/*
	 * 重置密码(non-Javadoc)
	 * 
	 * @see
	 * com.sinotrans.gd.wms.system.service.SysUserManager#resetpassword(java
	 * .lang.String)
	 */
	public SinotransPageJson resetpassword(String resetpassword) {
		SinotransPageJson sinotransPageJson = new SinotransPageJson();
		SysUserModel sysusermodel = this.get(resetpassword);
		PasswordEncoder passWordEncoder =(PasswordEncoder)ContextUtils.getBean("passwordEncoder");
		sysusermodel.setPassword(passWordEncoder.encodePassword("888888", null));// 加密用户密码
		this.save(sysusermodel);
		sinotransPageJson.setMsg("重置成功,密码为888888");
		sinotransPageJson.setResult(true);
		return sinotransPageJson;
	}
	
	
	public SinotransDataGrid getuserListAdmin(String office, String userName,
			String userType, String roleCode, String createTime_s,
			String createTime_e, PagingInfo pagingInfo, String officecode,String biaoshi) {
			SinotransDataGrid adb = new SinotransDataGrid();
		
		//点击了查询按钮 标识为OK  
		if("ok".equals(biaoshi)){
			OfficeCodeQueryCondition officecondition =new OfficeCodeQueryCondition();
			officecondition.setOfficeCode(officecode);
			List<OfficeCodeQueryItem> list=this.dao.query(officecondition, OfficeCodeQueryItem.class);
			String[] Str = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				OfficeCodeQueryItem officeCodeQueryItem = list.get(i);
				Str[i] =officeCodeQueryItem.getOfficeCode();
			}
			//判断是是否写了 按角色查询 条件不一样用了两个查询语句
			if (RcUtil.isEmpty(roleCode)) {
				StringBuffer sb = new StringBuffer();
				List<SysUserNoRoleAdminQueryItem> resultList = new ArrayList<SysUserNoRoleAdminQueryItem>();
				SysUserNoRoleAdminQueryCondition condition = new SysUserNoRoleAdminQueryCondition();
				condition.setOfficeCode(Str);
				condition.setUserName(userName);
				condition.setUserType(userType);
				condition.setStartDate(RcUtil.string2date(createTime_s,
						RcUtil.yyyy_MM_dd));
				condition.setEndDate(RcUtil.string2date(createTime_e,
						RcUtil.yyyy_MM_dd));
				resultList = this.dao.query(condition,
						SysUserNoRoleAdminQueryItem.class, sb.toString(), null, null,
						pagingInfo);
				if (resultList.size() > 0) {
					adb.setPage(pagingInfo.getCurrentPage());
					adb.setTotal(pagingInfo.getTotalRows());
					adb.setRows(resultList);
				}

			} else {
				StringBuffer sb = new StringBuffer();
				List<SysUserQueryItem> resultList = new ArrayList<SysUserQueryItem>();

				SysUserAdminQueryCondition condition = new SysUserAdminQueryCondition();
				condition.setOfficeCode(Str);
				condition.setUserName(userName);
				condition.setUserType(userType);
				condition.setRoleCode(roleCode);
				condition.setStartDate(RcUtil.string2date(createTime_s,
						RcUtil.yyyy_MM_dd));
				condition.setEndDate(RcUtil.string2date(createTime_e,
						RcUtil.yyyy_MM_dd));
				resultList = this.dao.query(condition, SysUserQueryItem.class, sb
						.toString(), null, null, pagingInfo);
				if (resultList.size() > 0) {
					adb.setPage(pagingInfo.getCurrentPage());
					adb.setTotal(pagingInfo.getTotalRows());
					adb.setRows(resultList);
				}
			}
		}else{
			if(!RcUtil.isEmpty(office)){
				boolean bool=false;
				OfficeCodeQueryCondition officecondition1 =new OfficeCodeQueryCondition();
				officecondition1.setOfficeCode(officecode);
				List<OfficeCodeQueryItem> list1=this.dao.query(officecondition1, OfficeCodeQueryItem.class);
				for (OfficeCodeQueryItem officeCodeQueryItem : list1) {
					if(officeCodeQueryItem.getOfficeCode().equals(office)){
						bool=true;
					}
				}
				if(bool){
					String[] s={office};
					SysUserNoRoleAdminQueryCondition officecondition =new SysUserNoRoleAdminQueryCondition();
					officecondition.setOfficeCode(s);
					List<SysUserNoRoleAdminQueryItem> list=this.dao.query(officecondition, SysUserNoRoleAdminQueryItem.class);
					if (list.size() > 0) {
						adb.setPage(pagingInfo.getCurrentPage());
						adb.setTotal(pagingInfo.getTotalRows());
						adb.setRows(list);
					}
				}		
			}else{
				String[] s={officecode};
				SysUserNoRoleQueryCondition officecondition =new SysUserNoRoleQueryCondition();
				officecondition.setOfficeCode(s);
				List<SysUserNoRoleAdminQueryItem> list=this.dao.query(officecondition, SysUserNoRoleAdminQueryItem.class);
				if (list.size() > 0) {
					adb.setPage(pagingInfo.getCurrentPage());
					adb.setTotal(pagingInfo.getTotalRows());
					adb.setRows(list);
				}
			}
			
		}
		return adb;
	}

	@Override
	public String getUsername(String name) {
		SysUserModel model=new SysUserModel();
		model.setUserCode(name);
		List<SysUserModel> users= findByExample(model);
		if(users.size()>0){
			return users.get(0).getUserName();
		}
		return "";
	}

	/*
	 * 上传用户信息到统一身份验证平台(Create)
	 */
	@Override
	public void uploadCreateMember(SysUserModel sysusermodel) {
		
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		
		String arapMemberUrl = this.arapMemberSvc.getServletUrl();
		PostMethod method = new PostMethod(arapMemberUrl);
		NameValuePair[] pairs = new NameValuePair[2];
		pairs[0] = new NameValuePair("method", "create_member");
		pairs[1] = new NameValuePair("data", generateMemberInfo(sysusermodel));
		method.setRequestBody(pairs);
		
		try {
			
			int statusCode = httpClient.executeMethod(method);
			
			if (statusCode != HttpStatus.SC_OK) {
				System.out.println(method.getStatusLine());
				return;
			}
			
			InputStream inputStream = method.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			str = stringBuffer.toString();
			
			//byte[] cache = method.getResponseBody();
			
			System.out.println(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 上传用户信息到统一身份验证平台(Update)
	 */
	@Override
	public void uploadUpdateMember(SysUserModel sysusermodel) {
		
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		
		String arapMemberUrl = this.arapMemberSvc.getServletUrl();
		PostMethod method = new PostMethod(arapMemberUrl);
		
		try {
			NameValuePair[] pairs = new NameValuePair[2];
			pairs[0] = new NameValuePair("method", "update_member");
			pairs[1] = new NameValuePair("data", generateMemberInfo(sysusermodel));
			method.setRequestBody(pairs);
			
			int statusCode = httpClient.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.out.println(method.getStatusLine());
				return;
			}
			
			InputStream inputStream = method.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			StringBuffer stringBuffer = new StringBuffer();
			String str = "";
			while ((str = br.readLine()) != null) {
				stringBuffer.append(str);
			}
			str = stringBuffer.toString();
			
		    JSONObject jsonObj = new JSONObject(str);
			//byte[] cache = method.getResponseBody();
			String retMessage = jsonObj.getString("message");
			if(NO_USER.equals(retMessage)){
				this.uploadCreateMember(sysusermodel);
			}
			System.out.println(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


	}
	
	/*
	 * 上传用户信息到统一身份验证平台(Stop)
	 */
	@Override
	public void uploadStopUser(SysUserModel sysusermodel){
		
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		
		String arapMemberUrl = this.arapMemberSvc.getServletUrl();
		PostMethod method = new PostMethod(arapMemberUrl);
		method.setRequestBody(new NameValuePair[]{
			new NameValuePair("method", "stop_member")
			, new NameValuePair("data", "{\"sub_account\": " + sysusermodel.getUserCode() + ", \"auth_code\": "+SessionContextUserEntity.currentUser().getUserAuthCode()+"}")
		});
		
		try {
			
			int statusCode = httpClient.executeMethod(method);
			
			if (statusCode != HttpStatus.SC_OK) {
				System.out.println(method.getStatusLine());
				return;
			}
			
			byte[] cache = method.getResponseBody();
			
			System.out.println(new String(cache));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String generateMemberInfo(SysUserModel sysusermodel) {
	    String sex = sysusermodel.getSex().equals("M") ? "2" : "1";
	    JSONObject requestJson = new JSONObject();
	    JSONObject memberJson = new JSONObject();
	    String paraJson = "";
	    try {
			memberJson.put("name", sysusermodel.getUserName());
			memberJson.put("alias", "");
			memberJson.put("main_account", sysusermodel.getUserCode());
			memberJson.put("sub_account", sysusermodel.getUserCode());
			memberJson.put("code", sysusermodel.getUserCode());
			memberJson.put("sex", sex);
			memberJson.put("birthday", "");
			memberJson.put("telephone", "");
			memberJson.put("mobile", "");
			memberJson.put("email", sysusermodel.getEmail());
			memberJson.put("organization_code", "");
			memberJson.put("post_code", "");
			memberJson.put("level_code", "");
			memberJson.put("member_type", "");
			memberJson.put("describe", "");
			requestJson.put("member_info", memberJson);
			requestJson.put("auth_code", SessionContextUserEntity.currentUser().getUserAuthCode());
			paraJson = requestJson.toString();
			
	    } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
//		return "{" +
//				"\"member_info\": {" +
//				"\"name\" : " + sysusermodel.getUserName() + ", " +
//				"\"alias\" : \"\", " +
//				"\"main_account\" : " + sysusermodel.getUserCode() + "," +    			// 账号(统一身份认证平台账号) （NOT NULL）
//				"\"sub_account\" : " + sysusermodel.getUserCode() + ", " +		 	// 账号（关系系统账号）（NOT NULL）
//				"\"code\" : " + sysusermodel.getUserCode() + ", " +						// 员工编号（NOT NULL & UNIQUER）
//				"\"sex\" : " + sex + ", " +										// 性别
//				"\"birthday\" : \"\", " +				// 出生日期
//				"\"telephone\" : \"\", " +			// 办公电话
//				"\"mobile\" : \"\", " +				// 手机
//				"\"email\" : " + sysusermodel.getEmail() + ", " +				// E-mail（NOT NULL & UNIQUER）
//				"\"organization_code\" : \"\", " +	// 组织编号
//				"\"post_code\" : \"\", " +			// 岗位编号
//				"\"level_code\" : \"\", " +			// 职务级别编号
//				"\"member_type\" : \"\", " +		// 员工类型（内部:0 / 外部人员:1）
//				"\"describe\" : \"\"}" + 				// 描述
//				", \"auth_code\": "+SessionContextUserEntity.currentUser().getUserAuthCode()+" " +
//			"}";
	    
		return paraJson;
	}

	@Override
	public String getUserCustomerCode() {
		String customerCode = "";
		try {
			BasCodeTypeModel bcm=new BasCodeTypeModel();
			bcm.setTypeCode("DOCC_CUSTOMER_CODE");
			bcm.setStatus(CommonUtil.Active);
			List<BasCodeTypeModel> bcms=basCodeTypeManager.findByExample(bcm);
			BasCodeDefModel basCodeDefModel = new BasCodeDefModel();
			if(bcms.size() >0){
				basCodeDefModel.setBasCodeTypeUuid(bcms.get(0).getBasCodeTypeUuid());
				basCodeDefModel.setStatus(CommonUtil.Active);
				List<BasCodeDefModel> bcdm = basCodeDefManager.findByExample(basCodeDefModel);
				if(bcdm.size() > 0){
					basCodeDefModel = bcdm.get(0);
				}
			}
			
			boolean isDocc = sysOfficeManager.isMatchOfficeCodeTypeByString("9");
			if(isDocc && basCodeDefModel.getCodeValue() != null){
				customerCode = basCodeDefModel.getCodeValue();
			}else{
				customerCode = SessionContextUserEntity.currentUser().getCustomerCode();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customerCode;
	}

	@Override
	public String getEmailByUserCode(String userCode) {
		String email = "";
		List<SysUserModel> userList = this.dao.createCommonQuery(SysUserModel.class)
				.addCondition(Condition.eq(SysUserModel.FieldNames.userCode, userCode))
				.addCondition(Condition.eq(SysUserModel.FieldNames.status, CommonUtil.Active))
				.query();
		
		if(ListUtil.isNotEmpty(userList)){
			email = userList.get(0).getEmail();
		}
		return email;
	}
	
	
}
