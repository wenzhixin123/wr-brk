package com.sinotrans.gd.wlp.system.service.impl;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.common.service.LogisticsOrderManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.system.entity.ModuleGroupMenuItemTrue;
import com.sinotrans.gd.wlp.system.model.SysMenuGroupModel;
import com.sinotrans.gd.wlp.system.model.SysMenuItemModel;
import com.sinotrans.gd.wlp.system.model.SysOfficeModel;
import com.sinotrans.gd.wlp.system.model.SysRoleMenuItemModel;
import com.sinotrans.gd.wlp.system.model.SysRoleModel;
import com.sinotrans.gd.wlp.system.model.SysRoleUserModel;
import com.sinotrans.gd.wlp.system.query.SysRoleQueryCondition;
import com.sinotrans.gd.wlp.system.query.SysRoleQueryItem;
import com.sinotrans.gd.wlp.system.service.SysMenuGroupManager;
import com.sinotrans.gd.wlp.system.service.SysMenuItemManager;
import com.sinotrans.gd.wlp.system.service.SysOfficeManager;
import com.sinotrans.gd.wlp.system.service.SysRoleManager;
import com.sinotrans.gd.wlp.system.service.SysRoleMenuItemManager;
import com.sinotrans.gd.wlp.system.service.SysRoleUserManager;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.JsonUtil;

import edu.emory.mathcs.backport.java.util.Collections;

@Service
public class SysRoleManagerImpl extends BaseManagerImpl implements
		SysRoleManager {
	@Autowired
	private SysRoleMenuItemManager sysRoleMenuItemManager;
	@Autowired
	private SysRoleUserManager sysRoleUserManager;
	@Autowired
	private SysMenuGroupManager sysMenuGroupManager;
	@Autowired
	private SysMenuItemManager sysMenuItemManager;
	@Autowired
	private SysOfficeManager  sysOfficeManager;
	@Autowired
	private LogisticsOrderManager logisticsOrderManager;
	
	public SysRoleModel get(String id) {
		return this.dao.get(SysRoleModel.class, id);
	}

	public List<SysRoleModel> getAll() {
		return this.dao.getAll(SysRoleModel.class);
	}

	public List<SysRoleModel> findByExample(SysRoleModel mplexamplee) {
		return this.dao.findByExample(mplexamplee);
	}

	public SysRoleModel save(SysRoleModel model) {
		return this.dao.save(model);
	}

	public List<SysRoleModel> saveAll(Collection<SysRoleModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysRoleModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysRoleModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysRoleModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysRoleModel.class, ids);
	}

	public List<BasOption> getOption(String code, String officeCode,
			String language) {
		List<BasOption> optionList = new ArrayList<BasOption>();
		List<SysRoleModel> bcmList = this.getAll();
		for (SysRoleModel c : bcmList) {
			BasOption wo = new BasOption();
			wo.setKey(c.getSysRoleUuid());
			wo.setValue(c.getRoleName());
			optionList.add(wo);
		}
		return optionList;
	}

	public List<ModuleGroupMenuItemTrue> selectroleall() {
		List<SysRoleModel> sysroleall = this.getAll();
		List<ModuleGroupMenuItemTrue> mlgmitree = new ArrayList<ModuleGroupMenuItemTrue>();
		List<ModuleGroupMenuItemTrue> mlgmitrees = new ArrayList<ModuleGroupMenuItemTrue>();
		ModuleGroupMenuItemTrue smgmit = new ModuleGroupMenuItemTrue();
		smgmit.setId("0");
		smgmit.setText("角色");
		for (SysRoleModel sysrolemodel : sysroleall) {
			if (sysrolemodel.getStatus() != null
					&& sysrolemodel.getStatus().equals(CommonUtil.Active)) {
				ModuleGroupMenuItemTrue mgmit = new ModuleGroupMenuItemTrue();
				mgmit.setId(sysrolemodel.getSysRoleUuid());
				mgmit.setText(sysrolemodel.getRoleName());
				mlgmitree.add(mgmit);
			}
		}
		smgmit.setChildren(mlgmitree);
		mlgmitrees.add(smgmit);
		return mlgmitrees;
	}

	public void saveAddRole(String rolejson, String officeCode) {
		rolejson=logisticsOrderManager.getBase642Ojbect(rolejson);
		try {
			SysRoleModel sysRoleModel = (SysRoleModel) JsonUtil.jsonToBean(
					rolejson, SysRoleModel.class);
			if (getYanZhenUserCode(sysRoleModel.getRoleCode(), officeCode)) {
				throw new ApplicationException("该编码已存在、请审核后重新填写！"); // 抛出错误信息
			} else {
				sysRoleModel.setOfficeCode(officeCode);
				ContextUtils.getBeanOfType(SysRoleManager.class).save(
						sysRoleModel);
			}
		} catch (RuntimeException e) {
			// return e.getStackTrace().toString();
		}
		// return "操作成功";
	}

	public void saveUpdateRole(String rolejson, String officeCode) {
		rolejson=logisticsOrderManager.getBase642Ojbect(rolejson);
		SysRoleModel sysRoleModel = (SysRoleModel) JsonUtil.jsonToBean(
				rolejson, SysRoleModel.class);
		sysRoleModel.setRowState(BaseModel.ROW_STATE_MODIFIED);
		sysRoleModel.setOfficeCode(officeCode);
		ContextUtils.getBeanOfType(SysRoleManager.class).save(sysRoleModel);
		// void "操作成功";
	}

	/**
	 * 验证角色是否与（用户角色、角色菜单）有关联关系。现在用于判断是否可以删除（删除按钮的控制）
	 * 
	 * @param sysRoleUuid
	 *            角色uuid
	 * @return 返回是（"true"）或者（"false"）两种结果 验证如果有关系则返回"true"
	 */
	public String getRoleValidation(String sysRoleUuid) {
		SysRoleMenuItemModel sRmIm = new SysRoleMenuItemModel();
		sRmIm.setSysRoleUuid(sysRoleUuid);
		List<SysRoleMenuItemModel> sRmImList = sysRoleMenuItemManager
				.findByExample(sRmIm); // 此上三条验证是否有角色和菜单有关联
		if (sRmImList != null && sRmImList.size() > 0) {
			return "true";
		} else {
			SysRoleUserModel sRUm = new SysRoleUserModel();
			sRUm.setSysRoleUuid(sysRoleUuid);
			List<SysRoleUserModel> sRUmList = sysRoleUserManager
					.findByExample(sRUm);// 此上三条验证是否有角色和用户关联
			if (sRUmList != null && sRUmList.size() > 0) {
				return "true";
			} else {
				return "false";
			}
		}
	}

	/**
	 * 用于验证角色编码
	 * 
	 * @param userCode
	 * @return 返回 true 为已存在
	 */
	@Override
	public boolean getYanZhenUserCode(String sysCode, String officeCode) {
		SysRoleModel sys = new SysRoleModel();
		sys.setRoleCode(sysCode);
		sys.setOfficeCode(officeCode);
		List<SysRoleModel> userList = this.findByExample(sys);
		if (userList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 角色页面查询过滤Admin
	 */
	public SinotransDataGrid getRoldeleteAdmin(String status, String roleName,
			String roleNameEn, String roleType, String createTime_s,
			String createTime_e, PagingInfo pagingInfo, String officecode) {
		/*
		 * StringBuffer sb = new StringBuffer(); List<Object[]> resultList =
		 * new ArrayList<Object[]>(); SinotransDataGrid adb = null; sb.append("
		 * select * from SYS_ROLE sr"); sb.append(" where sr.role_code <>
		 * 'ADMIN'"); sb.append(" and sr.OFFICE_CODE ='" + officecod + "'"); if
		 * (!RcUtil.strIsEmpty(roleName)) { sb.append(" and sr.role_Name like
		 * '%" + roleName + "%'"); } if (!RcUtil.strIsEmpty(roleNameEn)) {
		 * sb.append(" and sr.role_Name_En like '%" + roleNameEn + "%'"); } if
		 * (!RcUtil.strIsEmpty(roleType)) { sb.append(" and sr.role_Type='" +
		 * roleType + "'"); } if (!RcUtil.strIsEmpty(createTime_s) &&
		 * !RcUtil.strIsEmpty(createTime_e)) { sb.append(" and sr.create_Time
		 * >=to_date('" + createTime_s + "','YYYY:MM:dd') and sr.create_Time
		 * <=to_date('" + createTime_e + "','YYYY:MM:dd')"); } resultList =
		 * sqlQueryManager.getSqlResultList(sb.toString(), ""); List<SysRoleModel>
		 * user = new ArrayList<SysRoleModel>(); for (Object[] bo : resultList) {
		 * if (bo != null) { SysRoleModel bonModel = new SysRoleModel(); if
		 * (bo[0] != null) bonModel.setSysRoleUuid(bo[0].toString()); if (bo[11] !=
		 * null) bonModel.setStatus(bo[11].toString()); if (bo[1] != null)
		 * bonModel.setRoleCode(bo[1].toString()); if (bo[2] != null)
		 * bonModel.setRoleName(bo[2].toString()); if (bo[3] != null)
		 * bonModel.setRoleNameEn(bo[3].toString()); //
		 * log.debug("*****"+bo[3].toString()); // Date
		 * activeDate=RcUtil.string2date(bo[6].toString(), //
		 * RcUtil.yyyy_MM_dd); //
		 * if(bo[6]!=null)bonModel.setActiveDate(activeDate); // Date
		 * expiredDate=RcUtil.string2date(bo[7].toString(), //
		 * RcUtil.yyyy_MM_dd_HH_mm_ss); //
		 * if(bo[7]!=null)bonModel.setExpiredDate(expiredDate); if (bo[9] !=
		 * null) bonModel.setRoleType(bo[9].toString()); if (bo[15] != null)
		 * bonModel.setCreator(bo[15].toString()); user.add(bonModel); } } adb =
		 * new SinotransDataGrid(user, pagingInfo.getTotalRows(), pagingInfo
		 * .getCurrentPage()); return adb;
		 */

		StringBuffer sb = new StringBuffer();
		List<SysRoleQueryItem> resultList = new ArrayList<SysRoleQueryItem>();
		SinotransDataGrid adb = new SinotransDataGrid();
		SysRoleQueryCondition condition = new SysRoleQueryCondition();
		condition.setStatus(status);
		condition.setOfficeCode(officecode);
		condition.setRoleName(roleName);
		condition.setRoleNameen(roleNameEn);
		condition.setRoleType(roleType);
		condition.setStartDate(RcUtil.string2date(createTime_s,
				RcUtil.yyyy_MM_dd));
		condition.setEndDate(RcUtil
				.string2date(createTime_e, RcUtil.yyyy_MM_dd));
		resultList = this.dao.query(condition, SysRoleQueryItem.class, sb
				.toString(), null, null, pagingInfo);
		if (resultList.size() > 0) {
			adb.setPage(pagingInfo.getCurrentPage());
			adb.setTotal(pagingInfo.getTotalRows());
			adb.setRows(resultList);
		}
		return adb;
	}

	/*
	 * 角色与权限页面查询过滤Admin
	 */
	public SinotransDataGrid getRolORdeleteAdmin(PagingInfo pagingInfo,
			String officecod) {
		List<SysRoleModel> user = new ArrayList<SysRoleModel>();
		SinotransDataGrid adb = null;
		user = (List<SysRoleModel>) this.dao.createCommonQuery(
				SysRoleModel.class).addCondition(
				Condition.ne("roleCode", CommonUtil.SYSTEM_ADMIN))
				.addCondition(Condition.eq("status", CommonUtil.Active))
				.addCondition(Condition.eq("officeCode", officecod))
				.setPagingInfo(pagingInfo).query();
		adb = new SinotransDataGrid(user, pagingInfo.getTotalRows(), pagingInfo
				.getTotalPages());
		return adb;
	}

	/*
	 * 查询角色列表(树形officecode)
	 */

	public List<ModuleGroupMenuItemTrue> selectroleoffice(String officeCode) {
		SysRoleModel office = new SysRoleModel();
		if (officeCode != null)
			office.setOfficeCode(officeCode);
		List<SysRoleModel> officeList = this.findByExample(office);
		// List<SysRoleModel> sysroleall = this.getAll();
		List<ModuleGroupMenuItemTrue> mlgmitree = new ArrayList<ModuleGroupMenuItemTrue>();
		List<ModuleGroupMenuItemTrue> mlgmitrees = new ArrayList<ModuleGroupMenuItemTrue>();
		ModuleGroupMenuItemTrue smgmit = new ModuleGroupMenuItemTrue();
		smgmit.setId("0");
		smgmit.setText("角色");
		for (SysRoleModel sysrolemodel : officeList) {
			if (sysrolemodel.getStatus() != null
					&& sysrolemodel.getStatus().equals(CommonUtil.Active)) {
				ModuleGroupMenuItemTrue mgmit = new ModuleGroupMenuItemTrue();
				mgmit.setId(sysrolemodel.getSysRoleUuid());
				mgmit.setText(sysrolemodel.getRoleName());
				mlgmitree.add(mgmit);
			}
		}
		if(mlgmitree.size()>0){
			Collections.sort(mlgmitree, new Comparator() {   
			    public int compare(Object a, Object b) {   
			        String one = ((ModuleGroupMenuItemTrue)a).getText();   
			        String two = ((ModuleGroupMenuItemTrue)b).getText();
			        return Collator.getInstance(Locale.CHINESE).compare(one, two);    
			    }   
			});
		}
		smgmit.setChildren(mlgmitree);
		mlgmitrees.add(smgmit);
		return mlgmitrees;
	}

	/*
	 * 查询角色列表(树形Admin)
	 */

	public List<ModuleGroupMenuItemTrue> selectrole() {
		List<SysRoleModel> sysroleall = this.getAll();
		List<ModuleGroupMenuItemTrue> mlgmitree = new ArrayList<ModuleGroupMenuItemTrue>();
		List<ModuleGroupMenuItemTrue> mlgmitrees = new ArrayList<ModuleGroupMenuItemTrue>();
		ModuleGroupMenuItemTrue smgmit = new ModuleGroupMenuItemTrue();
		smgmit.setId("0");
		smgmit.setText("角色");
		for (SysRoleModel sysrolemodel : sysroleall) {
			if (sysrolemodel.getStatus() != null
					&& sysrolemodel.getStatus().equals(CommonUtil.Active)) {
				ModuleGroupMenuItemTrue mgmit = new ModuleGroupMenuItemTrue();
				mgmit.setId(sysrolemodel.getSysRoleUuid());
				mgmit.setText(sysrolemodel.getRoleName());
				mlgmitree.add(mgmit);
			}
		}
		if(mlgmitree.size()>0){
			Collections.sort(mlgmitree, new Comparator() {   
			    public int compare(Object a, Object b) {   
			        String one = ((ModuleGroupMenuItemTrue)a).getText();   
			        String two = ((ModuleGroupMenuItemTrue)b).getText();
			        return Collator.getInstance(Locale.CHINESE).compare(one, two);    
			    }   
			});
		}
		smgmit.setChildren(mlgmitree);
		mlgmitrees.add(smgmit);
		return mlgmitrees;
	}
	
	/*
	 * (non-Javadoc)
	 * 可实现树形根据item的officecode的查询
	 * @see com.sinotrans.gd.wlp.system.service.SysViewButtonManager#getofficecodeModuleGroupMenuitemTree1()
	 */
	
	public List<ModuleGroupMenuItemTrue> getofficecodeModuleGroupMenuitemTree1(String uuid) {
		SysOfficeModel model=sysOfficeManager.get(uuid);
		SysMenuItemModel item=new SysMenuItemModel();
		item.setOfficeCode(model.getOfficeCode());
		List<SysMenuGroupModel> allOffice = sysMenuGroupManager.getAll();
		List<SysMenuItemModel> smimlist = sysMenuItemManager.findByExample(item);
		List<ModuleGroupMenuItemTrue> resultList = new ArrayList<ModuleGroupMenuItemTrue>();
		for (SysMenuGroupModel allOM : allOffice) {
			if (CommonUtil.Active.equals(allOM.getStatus())) {
				if (allOM.getPreSysMenuGroupUuid() == null
						|| "".equals(allOM.getPreSysMenuGroupUuid())) {// 为空的情况下把节点加入也就是根节点
					ModuleGroupMenuItemTrue ot = new ModuleGroupMenuItemTrue();
					String officeId = allOM.getSysMenuGroupUuid();
					ot.setId(officeId);// 标识加入是不根节点
					ot.setText(allOM.getMenuGrpName());// 加入名字
					ot.setChildren(recursive1(officeId, allOffice, smimlist));// 循环看看它是否有子节点
					resultList.add(ot);
				}
			}
		}
		return resultList;
	}
	

	/**
	 * 循环递归
	 * 
	 * @param officeId
	 * @param allOffice
	 * @return
	 */
	private List<ModuleGroupMenuItemTrue> recursive1(String officeId,
			List<SysMenuGroupModel> allOffice, List<SysMenuItemModel> smimlist) {
		List<ModuleGroupMenuItemTrue> subOfficeList = new ArrayList<ModuleGroupMenuItemTrue>();
		for (SysMenuGroupModel smgm : allOffice) {
			if (CommonUtil.Active.equals(smgm.getStatus())) {
			if (officeId.equals(smgm.getPreSysMenuGroupUuid())) {
				ModuleGroupMenuItemTrue ot = new ModuleGroupMenuItemTrue();
				String subOfficeId = smgm.getSysMenuGroupUuid();
				ot.setId(subOfficeId);
				ot.setText(smgm.getMenuGrpName());
				ot.setChildren(recursive1(subOfficeId, allOffice, smimlist));
				subOfficeList.add(ot);
			}
			}
		}
		for (SysMenuItemModel smim : smimlist) {
			if (CommonUtil.Active.equals(smim.getStatus())){
			if (officeId.equals(smim.getSysMenuGroupUuid())) {
				ModuleGroupMenuItemTrue ot = new ModuleGroupMenuItemTrue();
				// String subOfficeId = smim.getSysMenuGroupUuid();
				ot.setId(smim.getSysMenuItemUuid());
				ot.setText(smim.getMenuItemName());
				subOfficeList.add(ot);
			}
			}
		}
		return subOfficeList;
	}

}
