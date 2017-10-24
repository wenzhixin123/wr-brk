package com.sinotrans.gd.wlp.system.service.impl;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.system.query.SysJurisdictionQueryCondition;
import com.sinotrans.gd.wlp.system.query.SysJurisdictionQueryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.common.service.LogisticsOrderManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.system.entity.ModuleGroupMenuItemTrue;
import com.sinotrans.gd.wlp.system.model.SysMenuGroupModel;
import com.sinotrans.gd.wlp.system.model.SysMenuItemModel;
import com.sinotrans.gd.wlp.system.model.SysOfficeModel;
import com.sinotrans.gd.wlp.system.model.SysRoleUserModel;
import com.sinotrans.gd.wlp.system.model.SysUserModel;
import com.sinotrans.gd.wlp.system.model.SysViewButtonModel;
import com.sinotrans.gd.wlp.system.service.SysMenuGroupManager;
import com.sinotrans.gd.wlp.system.service.SysMenuItemManager;
import com.sinotrans.gd.wlp.system.service.SysOfficeManager;
import com.sinotrans.gd.wlp.system.service.SysRoleMenuItemManager;
import com.sinotrans.gd.wlp.system.service.SysRoleUserManager;
import com.sinotrans.gd.wlp.system.service.SysUserManager;
import com.sinotrans.gd.wlp.system.service.SysViewButtonManager;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.JsonUtil;
import com.sinotrans.gd.wlp.util.StringUtil;

import edu.emory.mathcs.backport.java.util.Collections;

@Service
public class SysViewButtonManagerImpl extends BaseManagerImpl implements
		SysViewButtonManager {
	// private SysModuleManager smm = ContextUtils
	// .getBeanOfType(SysModuleManager.class);;
	private SysMenuGroupManager smgm = ContextUtils
			.getBeanOfType(SysMenuGroupManager.class);
	private SysMenuItemManager smim = ContextUtils
			.getBeanOfType(SysMenuItemManager.class);

	@Autowired
	private SysRoleUserManager sysRoleUserManager;
	@Autowired
	private SysUserManager sysUserManager;
	@Autowired
	private SysRoleMenuItemManager sysRoleMenuItemManager;
	@Autowired
	private SysMenuItemManager sysMenuItemManager;
	@Autowired
	private SysMenuGroupManager SysMenuGroupManager;
	@Autowired
	private SysOfficeManager sysOfficeManager;
	@Autowired
	private LogisticsOrderManager logisticsOrderManager;

	public SysViewButtonModel get(String id) {
		return this.dao.get(SysViewButtonModel.class, id);
	}

	public List<SysViewButtonModel> getAll() {
		return this.dao.getAll(SysViewButtonModel.class);
	}

	public List<SysViewButtonModel> findByExample(SysViewButtonModel example) {
		return this.dao.findByExample(example);
	}

	public SysViewButtonModel save(SysViewButtonModel model) {
		return this.dao.save(model);
	}

	public List<SysViewButtonModel> saveAll(
			Collection<SysViewButtonModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysViewButtonModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysViewButtonModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysViewButtonModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysViewButtonModel.class, ids);
	}

	/*
	 * 获取菜单分组树形结构（内容：id=uuid、text=name） 修改只显示有效状态的数据 (non-Javadoc)
	 * 
	 * @seecom.sinotrans.gd.wms.system.service.SysViewButtonManager#
	 * getModuleGroupMenuitemTree()
	 */
	public List<ModuleGroupMenuItemTrue> getModuleGroupMenuitemTree() {

		// List<SysModuleModel> smmlist = smm.getAll();
		List<SysMenuGroupModel> smgmlist = smgm.getAll();
		List<SysMenuItemModel> smimlist = smim.getAll();

		// List<ModuleGroupMenuItemTrue> mlgmitree = new
		// ArrayList<ModuleGroupMenuItemTrue>();

		List<ModuleGroupMenuItemTrue> mlgmitree1 = new ArrayList<ModuleGroupMenuItemTrue>();
		// for (SysModuleModel smm : smmlist) {//第一层
		// ModuleGroupMenuItemTrue mgmit = new ModuleGroupMenuItemTrue();
		// List<ModuleGroupMenuItemTrue> mlgmitree1 = new
		// ArrayList<ModuleGroupMenuItemTrue>();
		// mgmit.setId(smm.getSysModuleUuid());
		// mgmit.setText(CommonUtil.ZH_CN.equals("CN") ? smm.getModuleName()
		// : smm.getModuleNameEn());
		for (SysMenuGroupModel smgm : smgmlist) {// 第二层
			// ModuleGroupMenuItemTrue mgmit = new ModuleGroupMenuItemTrue();
			// if (smgm.getSysModuleUuid().equals(smm.getSysModuleUuid())) {
			if (smgm.getStatus() != null && !smgm.getStatus().equals("")
					&& smgm.getStatus().equals(CommonUtil.Active)) {// 判断为有效的菜单才给与加载
				ModuleGroupMenuItemTrue mgmit1 = new ModuleGroupMenuItemTrue();
				mgmit1.setId(smgm.getPreSysMenuGroupUuid());
				mgmit1.setText(CommonUtil.ZH_CN.equals("CN") ? smgm
						.getMenuGrpName() : smgm.getMenuGrpNameEn());
				List<ModuleGroupMenuItemTrue> mlgmitree2 = new ArrayList<ModuleGroupMenuItemTrue>();
				for (SysMenuItemModel smim : smimlist) {// 第三层
					if (smgm.getSysMenuGroupUuid().equals(
							smim.getSysMenuGroupUuid())) {
						if (smim.getStatus() != null
								&& !smgm.getStatus().equals("")
								&& smim.getStatus().equals(CommonUtil.Active)) {// 判断为有效的菜单才给与加载
							// if(smim.getStatus()!=null&&smim.getStatus().equals(CommonUtil.Active)){//在这里判断是否是有效的
							ModuleGroupMenuItemTrue mgmit2 = new ModuleGroupMenuItemTrue();
							mgmit2.setId(smim.getSysMenuItemUuid());
							mgmit2.setText(CommonUtil.ZH_CN.equals("CN") ? smim
									.getMenuItemName() : smim
									.getMenuItemNameCn());
							mlgmitree2.add(mgmit2);
							// }
						}
					}
				}
				mgmit1.setChildren(mlgmitree2);
				mlgmitree1.add(mgmit1);
			}
			// }
			// mgmit.setChildren(mlgmitree1);
		}
		// mlgmitree.add(mgmit);
		// }

		return mlgmitree1;
	}

	/*
	 * 获取菜单分组树形结构（内容：id=uuid、text=name） 修改只显示有效状态的数据 (non-Javadoc) 改进后多层树
	 * 修改人:肖于忠
	 * 
	 * @seecom.sinotrans.gd.wms.system.service.SysViewButtonManager#
	 * getModuleGroupMenuitemTree()
	 */
	public List<ModuleGroupMenuItemTrue> getModuleGroupMenuitemTree1() {
		List<SysMenuGroupModel> allOffice = smgm.getAll();
		List<SysMenuItemModel> smimlist = smim.getAll();
		List<SysOfficeModel> officeModel=sysOfficeManager.getAll();
		Map<String, String> officemap=new HashMap<String, String>();
		for (SysOfficeModel sysOfficeModel : officeModel) {
			officemap.put(sysOfficeModel.getOfficeCode(), sysOfficeModel.getOfficeName());
		}
		List<ModuleGroupMenuItemTrue> resultList = new ArrayList<ModuleGroupMenuItemTrue>();
		for (SysMenuGroupModel allOM : allOffice) {
			if (CommonUtil.Active.equals(allOM.getStatus())) {
				if (allOM.getPreSysMenuGroupUuid() == null
						|| "".equals(allOM.getPreSysMenuGroupUuid())) {// 为空的情况下把节点加入也就是根节点
					ModuleGroupMenuItemTrue ot = new ModuleGroupMenuItemTrue();
					String officeId = allOM.getSysMenuGroupUuid();
					ot.setId(officeId);// 标识加入是不根节点
					ot.setText(allOM.getMenuGrpName());// 加入名字
					ot.setChildren(recursive1(officeId, allOffice, smimlist,officemap));// 循环看看它是否有子节点
					resultList.add(ot);
				}
			}
		}
		if(resultList.size()>0){
			Collections.sort(resultList, new Comparator() {   
			    public int compare(Object a, Object b) {   
			        String one = ((ModuleGroupMenuItemTrue)a).getText();   
			        String two = ((ModuleGroupMenuItemTrue)b).getText();
			        return Collator.getInstance(Locale.CHINESE).compare(one, two);    
			    }   
			});
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
			List<SysMenuGroupModel> allOffice, List<SysMenuItemModel> smimlist,Map<String, String> officemap) {
		List<ModuleGroupMenuItemTrue> subOfficeList = new ArrayList<ModuleGroupMenuItemTrue>();
		for (SysMenuGroupModel smgm : allOffice) {
			if (CommonUtil.Active.equals(smgm.getStatus())) {
			if (officeId.equals(smgm.getPreSysMenuGroupUuid())) {
				ModuleGroupMenuItemTrue ot = new ModuleGroupMenuItemTrue();
				String subOfficeId = smgm.getSysMenuGroupUuid();
				ot.setId(subOfficeId);
				ot.setText(smgm.getMenuGrpName());
				ot.setChildren(recursive1(subOfficeId, allOffice, smimlist,officemap));
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
				ot.setText(smim.getMenuItemName()+"   /"+officemap.get(smim.getOfficeCode()));
				subOfficeList.add(ot);
			}
			}
		}
		if(subOfficeList.size()>0){
			Collections.sort(subOfficeList, new Comparator() {   
			    public int compare(Object a, Object b) {   
			        String one = ((ModuleGroupMenuItemTrue)a).getText();   
			        String two = ((ModuleGroupMenuItemTrue)b).getText();
			        return Collator.getInstance(Locale.CHINESE).compare(one, two);    
			    }   
			});
		}
		return subOfficeList;
	}

	/*
	 * 获取菜单分组树形结构（内容：id=officeCode、text=name） (non-Javadoc)
	 * 
	 * @seecom.sinotrans.gd.wms.system.service.SysViewButtonManager#
	 * getModuleGroupMenuitemTree()
	 */
	public List<ModuleGroupMenuItemTrue> getModuleGroupOfficeNameTree() {
		List<SysMenuGroupModel> smgmlist = smgm.getAll();
		List<SysMenuItemModel> smimlist = smim.getAll();
		List<ModuleGroupMenuItemTrue> mlgmitree1 = new ArrayList<ModuleGroupMenuItemTrue>();
		for (SysMenuGroupModel smgm : smgmlist) {// 第二层
			ModuleGroupMenuItemTrue mgmit1 = new ModuleGroupMenuItemTrue();
			mgmit1.setId(smgm.getOfficeCode());
			mgmit1.setText(CommonUtil.ZH_CN.equals("CN") ? smgm
					.getMenuGrpName() : smgm.getMenuGrpNameEn());
			List<ModuleGroupMenuItemTrue> mlgmitree2 = new ArrayList<ModuleGroupMenuItemTrue>();
			for (SysMenuItemModel smim : smimlist) {// 第三层
				if (smgm.getSysMenuGroupUuid().equals(
						smim.getSysMenuGroupUuid())) {
					ModuleGroupMenuItemTrue mgmit2 = new ModuleGroupMenuItemTrue();
					mgmit2.setId(smim.getOfficeCode());
					mgmit2.setText(CommonUtil.ZH_CN.equals("CN") ? smim
							.getMenuItemName() : smim.getMenuItemNameCn());
					mlgmitree2.add(mgmit2);
				}
			}
			mgmit1.setChildren(mlgmitree2);
			mlgmitree1.add(mgmit1);
		}
		return mlgmitree1;
	}

	/*
	 * 供给上级菜单选择信息（数据：只有菜单分组的数据。没有详细下级菜单信息） (non-Javadoc)
	 * 
	 * @see
	 * com.sinotrans.gd.wms.system.service.SysViewButtonManager#getModuleGroupTree
	 * ()
	 */
	public List<ModuleGroupMenuItemTrue> getModuleGroupTree() {

		List<SysMenuGroupModel> smgmlist = smgm.getAll();
		List<ModuleGroupMenuItemTrue> mlgmitree1 = new ArrayList<ModuleGroupMenuItemTrue>();
		for (SysMenuGroupModel smgm : smgmlist) {// 第二层
			ModuleGroupMenuItemTrue mgmit1 = new ModuleGroupMenuItemTrue();
			mgmit1.setId(smgm.getSysMenuGroupUuid());
			mgmit1.setText(CommonUtil.ZH_CN.equals("CN") ? smgm
					.getMenuGrpName() : smgm.getMenuGrpNameEn());
			mlgmitree1.add(mgmit1);
		}
		return mlgmitree1;
	}

	/*
	 * 根据菜单获取菜单下的按钮树形列表。条件(菜单项UUID)必备条件为有效状态 (non-Javadoc)
	 * 
	 * @see
	 * com.sinotrans.gd.wms.system.service.SysViewButtonManager#selectviewButtonall
	 * (java.lang.String[])
	 */
	public List<ModuleGroupMenuItemTrue> selectviewButtonall(String data[]) {
		SysViewButtonModel buttonModel = new SysViewButtonModel();
		buttonModel.setSysMenuItemUuid(data[0]);
		buttonModel.setStatus(CommonUtil.Active);
		List<SysViewButtonModel> sysViewButtonall = this
				.findByExample(buttonModel);
		List<ModuleGroupMenuItemTrue> mlgmitree = new ArrayList<ModuleGroupMenuItemTrue>();
		for (SysViewButtonModel sysViewButton : sysViewButtonall) {
			ModuleGroupMenuItemTrue mgmit = new ModuleGroupMenuItemTrue();
			mgmit.setId(sysViewButton.getSysViewButtonUuid());
			mgmit.setText(sysViewButton.getBtnName());
			mgmit.setUrl(sysViewButton.getBtnUrl());
			mlgmitree.add(mgmit);
		}
		return mlgmitree;
	}

	/**
	 * 用于验证编码是否重复
	 * 
	 * @param userCode
	 * @return 返回 true 为已存在
	 */
	@Override
	public boolean getYanZhenUserCode(String sysCode) {
		SysViewButtonModel sys = new SysViewButtonModel();
		sys.setBtnCode(StringUtil.toTrim(sysCode));
		List<SysViewButtonModel> userList = this.findByExample(sys);
		if (userList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 执行保存按钮信息。并且验证编码是否重复
	 * 
	 * @param viewButtonjson
	 * @return
	 */
	@Override
	public String saveViewButtonModel(String viewButtonjson, String officeCode) {
		viewButtonjson=logisticsOrderManager.getBase642Ojbect(viewButtonjson);
		SysViewButtonModel sysviewbuttonModel = (SysViewButtonModel) JsonUtil
				.jsonToBean(viewButtonjson, SysViewButtonModel.class);
		if (getYanZhenUserCode(sysviewbuttonModel.getBtnCode())) {
			throw new ApplicationException("该编码已存在、请审核后重新填写！"); // 抛出错误信息
		} else {
			sysviewbuttonModel.setOfficeCode(officeCode);
			sysviewbuttonModel.setRowState(BaseModel.ROW_STATE_ADDED);
			ContextUtils.getBeanOfType(SysViewButtonManager.class).save(
					sysviewbuttonModel);
		}
		return null;
	}

	/**
	 * 修改按钮管理信息
	 * 
	 * @param viewButtonjson
	 * @param officeCode
	 */
	@Override
	public void updateViewButtonModel(String viewButtonjson, String officeCode) {
		viewButtonjson=logisticsOrderManager.getBase642Ojbect(viewButtonjson);
		SysViewButtonModel SysviewbuttonModel = (SysViewButtonModel) JsonUtil
				.jsonToBean(viewButtonjson, SysViewButtonModel.class);
		SysviewbuttonModel.setOfficeCode(officeCode);
		SysviewbuttonModel.setRowState(BaseModel.ROW_STATE_MODIFIED);
		ContextUtils.getBeanOfType(SysViewButtonManager.class).save(
				SysviewbuttonModel);
	}

	/*
	 * 获取菜单分组树形结构（内容：id=uuid、text=name） 修改只显示有效状态的数据 (non-Javadoc) 改进后多层树
	 * (用户所拥有的菜单树形)
	 * 
	 * @seecom.sinotrans.gd.wms.system.service.SysViewButtonManager#
	 * getModuleGroupMenuitemTree()
	 */
	public List<ModuleGroupMenuItemTrue> getModuleGroupMenuitemUserTree1(
			String UserId) {
		List<SysMenuItemModel> menuItems = new ArrayList<SysMenuItemModel>();
		List<SysMenuGroupModel> menuGroups = new ArrayList<SysMenuGroupModel>();
		List<String> sysRoleUuid = new ArrayList<String>();
		List<String> sysMenuItemUuid = new ArrayList<String>();
		List<String> sysMenuGroupUuid = new ArrayList<String>();

		// 根据用户编码找出用户
		SysUserModel user = new SysUserModel();
		user.setUserCode(UserId);
		List<SysUserModel> users = sysUserManager.findByExample(user);

		// 找出该用户所拥有的角色
		List<SysRoleUserModel> roleUsers = null;
		if (!RcUtil.isEmpty(users) && users.size() > 0) {
			SysRoleUserModel roleUser = new SysRoleUserModel();
			roleUser.setUserUuid(users.get(0).getUserUuid());
			roleUsers = sysRoleUserManager.findByExample(roleUser);
		}

		// 根据角色获得menuItem
		if (!RcUtil.isEmpty(roleUsers) && roleUsers.size() > 0) {
			sysRoleUuid = new ArrayList<String>();
			sysMenuItemUuid = new ArrayList<String>();
			for (SysRoleUserModel srum : roleUsers) {
				sysRoleUuid.add(srum.getSysRoleUuid());
			}
			sysMenuItemUuid = sysRoleMenuItemManager
					.findItemIdByRole(sysRoleUuid);
			menuItems = sysMenuItemManager.findByIds(sysMenuItemUuid);
		}

		// 根据menuItem获得menuGroup
		if (!RcUtil.isEmpty(menuItems) && menuItems.size() > 0) {
			for (SysMenuItemModel smim : menuItems) {
				sysMenuGroupUuid.add(smim.getSysMenuGroupUuid());
			}
			menuGroups = SysMenuGroupManager
					.findUserByGroupId(sysMenuGroupUuid);
		}
		// List<SysMenuGroupModel> allOffice = smgm.getAll();
		// List<SysMenuItemModel> smimlist = smim.getAll();
		List<SysOfficeModel> officeModel=sysOfficeManager.getAll();
		Map<String, String> officemap=new HashMap<String, String>();
		for (SysOfficeModel sysOfficeModel : officeModel) {
			officemap.put(sysOfficeModel.getOfficeCode(), sysOfficeModel.getOfficeName());
		}
		List<ModuleGroupMenuItemTrue> resultList = new ArrayList<ModuleGroupMenuItemTrue>();
		for (SysMenuGroupModel allOM : menuGroups) {
			if (CommonUtil.Active.equals(allOM.getStatus())) {
				if (allOM.getPreSysMenuGroupUuid() == null
						|| "".equals(allOM.getPreSysMenuGroupUuid())) {// 为空的情况下把节点加入也就是根节点
					ModuleGroupMenuItemTrue ot = new ModuleGroupMenuItemTrue();
					String officeId = allOM.getSysMenuGroupUuid();
					ot.setId(officeId);// 标识加入是不根节点
					ot.setText(allOM.getMenuGrpName());// 加入名字
					ot.setChildren(userrecursive(officeId, menuGroups,
							menuItems,officemap));// 循环看看它是否有子节点
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
	private List<ModuleGroupMenuItemTrue> userrecursive(String officeId,
			List<SysMenuGroupModel> allOffice, List<SysMenuItemModel> smimlist,Map<String, String> officemap) {
		List<ModuleGroupMenuItemTrue> subOfficeList = new ArrayList<ModuleGroupMenuItemTrue>();
		for (SysMenuGroupModel smgm : allOffice) {
			if (officeId.equals(smgm.getPreSysMenuGroupUuid())) {
				ModuleGroupMenuItemTrue ot = new ModuleGroupMenuItemTrue();
				String subOfficeId = smgm.getSysMenuGroupUuid();
				ot.setId(subOfficeId);
				ot.setText(smgm.getMenuGrpName());
				ot.setChildren(userrecursive(subOfficeId, allOffice, smimlist,officemap));
				subOfficeList.add(ot);
			}
		}
		for (SysMenuItemModel smim : smimlist) {
			if (officeId.equals(smim.getSysMenuGroupUuid())) {
				ModuleGroupMenuItemTrue ot = new ModuleGroupMenuItemTrue();
				ot.setId(smim.getSysMenuItemUuid());
				ot.setText(smim.getMenuItemName()+"   /"+officemap.get(smim.getOfficeCode()));
				subOfficeList.add(ot);
			}
		}
		return subOfficeList;
	}

	@Override
	public String getUrlByButtonCode(String buttonCode) {
		SysViewButtonModel model = new SysViewButtonModel();
		model.setBtnCode(buttonCode);
		List<SysViewButtonModel> list = findByExample(model);
		if(list.size() > 0) {
			return list.get(0).getBtnUrl();
		}else {
		}
		return "#";
	}
	@Override
	public SinotransPageJson getMenuItemButtonMap(
			List<SysViewButtonModel> svList) {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] buttons = new String[svList.size()];
		for (int i = 0; i < svList.size(); i++) {
			SysViewButtonModel svModel = svList.get(i);
			buttons[i] = svModel.getBtnCode();
			map.put(svModel.getBtnCode(), "N");
		}
		SinotransPageJson spj = new SinotransPageJson();
		SysJurisdictionQueryCondition condition = new SysJurisdictionQueryCondition();
		condition.setOfficeCode(SessionContextUserEntity.currentUser()
				.getOfficeCode());
		condition.setUserCode(SessionContextUserEntity.currentUser()
				.getUserId());
		condition.setBtnCodeArr(buttons);
		List<SysJurisdictionQueryItem> sjqList = new ArrayList<SysJurisdictionQueryItem>();
		sjqList = this.dao.query(condition, SysJurisdictionQueryItem.class);
		for (int i = 0; i < sjqList.size(); i++) {
			SysJurisdictionQueryItem sjdModel = sjqList.get(i);
			map.put(sjdModel.getBtnCode(), sjdModel.getBtnCode());
		}
		spj.setObject(map);
		return spj;
	}
}
