package com.sinotrans.gd.wlp.system.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.common.service.LogisticsOrderManager;
import com.sinotrans.gd.wlp.common.web.EC_CommonUtil;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.OfficeTree;
import com.sinotrans.gd.wlp.system.model.SysMenuGroupModel;
import com.sinotrans.gd.wlp.system.model.SysMenuItemModel;
import com.sinotrans.gd.wlp.system.model.SysOfficeModel;
import com.sinotrans.gd.wlp.system.model.SysRoleUserModel;
import com.sinotrans.gd.wlp.system.model.SysUserModel;
import com.sinotrans.gd.wlp.system.query.GetMenuGroupByUserCodeQueryCondition;
import com.sinotrans.gd.wlp.system.query.GetMenuGroupByUserCodeQueryItem;
import com.sinotrans.gd.wlp.system.service.SysMenuGroupManager;
import com.sinotrans.gd.wlp.system.service.SysMenuItemManager;
import com.sinotrans.gd.wlp.system.service.SysOfficeManager;
import com.sinotrans.gd.wlp.system.service.SysRoleMenuItemManager;
import com.sinotrans.gd.wlp.system.service.SysRoleUserManager;
import com.sinotrans.gd.wlp.system.service.SysUserManager;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.JsonUtil;
import com.sinotrans.gd.wlp.util.StringUtil;

@Service
public class SysMenuGroupManagerImpl extends BaseManagerImpl implements
		SysMenuGroupManager {
	@Autowired
	private SysOfficeManager sysOfficeManager;
	@Autowired
	private SysRoleUserManager sysRoleUserManager;
	@Autowired
	private SysUserManager sysUserManager;
	@Autowired
	private SysRoleMenuItemManager sysRoleMenuItemManager;
	@Autowired
	private SysMenuItemManager sysMenuItemManager;
	@Autowired
	private LogisticsOrderManager logisticsOrderManager;

	public SysMenuGroupModel get(String id) {
		return this.dao.get(SysMenuGroupModel.class, id);
	}

	public List<SysMenuGroupModel> getAll() {
		return this.dao.getAll(SysMenuGroupModel.class);
	}

	public List<SysMenuGroupModel> findByExample(SysMenuGroupModel example) {
		return this.dao.findByExample(example);
	}

	public SysMenuGroupModel save(SysMenuGroupModel model) {
		return this.dao.save(model);
	}

	public List<SysMenuGroupModel> saveAll(Collection<SysMenuGroupModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysMenuGroupModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysMenuGroupModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysMenuGroupModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysMenuGroupModel.class, ids);
	}

	/**
	 * 这个只能查一级的菜单
	 */
	public List<SysMenuGroupModel> findByGroupId(List<String> sysMenuGroupUuid) {
		List<SysMenuGroupModel> result = new ArrayList<SysMenuGroupModel>();
		result = this.dao
				.createCommonQuery(SysMenuGroupModel.class)
				.addCondition(
						Condition.in("sysMenuGroupUuid", sysMenuGroupUuid
								.toArray()))
				.setOrderBy("menuGrpSeq")
				/*.addCondition(Condition.isNull("preSysMenuGroupUuid"))*/
				/*.addCondition(
						Condition
								.or(new Condition[] {
										Condition
												.eq(
														SysMenuGroupModel.FieldNames.controlWord,
														"00000000000000000000"),
										Condition
												.isNull(SysMenuGroupModel.FieldNames.controlWord) }))*/
				.query();
		return result;
	}

	/**
	 * 这个只能查多级的菜单
	 */
	public List<SysMenuGroupModel> findUserByGroupId(
			List<String> sysMenuGroupUuid) {
		List<SysMenuGroupModel> result = new ArrayList<SysMenuGroupModel>();
		result = this.dao.createCommonQuery(SysMenuGroupModel.class)
				.addCondition(
						Condition.in("sysMenuGroupUuid", sysMenuGroupUuid
								.toArray())).setOrderBy("menuGrpSeq").query();
		return result;
	}

	/**
	 * 返回菜单分组列表。并且使用BasOption返回List转换为JSON后在前台页面用DWR调用(列表是所有的菜单分组)
	 * 
	 * @return
	 */
	public String getMenuGroupModelMap() { // 修改了状态必须为有效时候才查询出来
		List<SysMenuGroupModel> result = new ArrayList<SysMenuGroupModel>();
		List<BasOption> menuItemList = new ArrayList<BasOption>();
		result = this.getAll();
		BasOption menu = new BasOption();
		menu.setKey("");
		menu.setValue("全部");
		menuItemList.add(menu);
		for (SysMenuGroupModel sysMenuItemModel : result) {
			if (sysMenuItemModel.getStatus() != null
					&& !sysMenuItemModel.getStatus().equals("")
					&& sysMenuItemModel.getStatus().equals(CommonUtil.Active)) {
				BasOption menuModel = new BasOption();
				menuModel.setKey(sysMenuItemModel.getSysMenuGroupUuid());
				menuModel.setValue(sysMenuItemModel.getMenuGrpName());
				menuItemList.add(menuModel);
			}
		}
		String menuJson = JsonUtil.list2Json(menuItemList);
		return menuJson;
	}

	/**
	 * 
	 * 查询出树形菜单格式***********************************************
	 */

	@Override
	public List<OfficeTree> getOfficetree() {
		List<SysMenuGroupModel> allOffice = this.getAll();
		List<OfficeTree> resultList = new ArrayList<OfficeTree>();
		for (SysMenuGroupModel allOM : allOffice) {
			if (allOM.getStatus() != null && !allOM.getStatus().equals("")
					&& allOM.getStatus().equals(CommonUtil.Active)) {
				if (allOM.getPreSysMenuGroupUuid() == null
						|| allOM.getPreSysMenuGroupUuid().equals("")) {// 为空的情况下把节点加入也就是根节点
					OfficeTree ot = new OfficeTree();
					String officeId = allOM.getSysMenuGroupUuid();
					ot.setId(allOM.getSysMenuGroupUuid());// 标识加入是不根节点
					ot.setText(allOM.getMenuGrpName());// 加入名字
					ot.setChildren(recursive(officeId, allOffice));// 循环看看它是否有子节点
					if (RcUtil.isEmpty(allOM.getPreSysMenuGroupUuid())) {// 不为空的情况下把吧id标识加入
						ot.setId(allOM.getSysMenuGroupUuid());//
						ot.setText(allOM.getMenuGrpName());
						resultList.add(ot);
					}
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
	private List<OfficeTree> recursive(String officeId,
			List<SysMenuGroupModel> allOffice) {
		List<OfficeTree> subOfficeList = new ArrayList<OfficeTree>();
		for (SysMenuGroupModel som : allOffice) {
			if (som.getStatus() != null && !som.getStatus().equals("")
					&& som.getStatus().equals(CommonUtil.Active)) {
				if (officeId.equals(som.getPreSysMenuGroupUuid())) {// 根节点不为空的情况下把id,name加入
					OfficeTree ot = new OfficeTree();
					String subOfficeId = som.getSysMenuGroupUuid();
					ot.setId(subOfficeId);
					ot.setText(som.getMenuGrpName());
					ot.setChildren(recursive(subOfficeId, allOffice));
					subOfficeList.add(ot);
				}
			}
		}
		return subOfficeList;
	}

	/**
	 * 根据preUuid获取二级菜单
	 * 
	 * @param preUuid
	 * @return
	 */
	public List<SysMenuGroupModel> getMenuGroupByPreUuid(String preUuid) {
		List<SysMenuGroupModel> result = new ArrayList<SysMenuGroupModel>();

		if (RcUtil.isEmpty(preUuid)) {
			List<SysMenuGroupModel> models = this.dao.createCommonQuery(
					SysMenuGroupModel.class).addCondition(
					Condition.eq("menuGrpName", "Bar枪操作管理")).query();
			if (!RcUtil.isEmpty(models) && models.size() > 0) {
				preUuid = models.get(0).getSysMenuGroupUuid();
			}
		}

		result = this.dao.createCommonQuery(SysMenuGroupModel.class)
				.addCondition(Condition.eq("preSysMenuGroupUuid", preUuid))
				.addCondition(Condition.eq("status", CommonUtil.Active))
				.setOrderBy("menuGrpSeq").query();

		return result;
	}

	/**
	 * 根据用户code查询bar枪的二级菜单
	 * 
	 * @param userCode
	 * @param officeCode
	 * @return
	 * @throws Exception
	 */
	public List<SysMenuGroupModel> getMenuGroupByUserCode(String userCode,
			String officeCode) throws Exception {
		List<SysMenuGroupModel> result = new ArrayList<SysMenuGroupModel>();

		GetMenuGroupByUserCodeQueryCondition condition = new GetMenuGroupByUserCodeQueryCondition(
				EC_CommonUtil.ACTIVE, officeCode, userCode);
		List<GetMenuGroupByUserCodeQueryItem> items = this.dao.query(condition,
				GetMenuGroupByUserCodeQueryItem.class);

		if (!RcUtil.isEmpty(items) && items.size() > 0) {
			for (GetMenuGroupByUserCodeQueryItem item : items) {
				SysMenuGroupModel model = new SysMenuGroupModel();
				BeanUtils.copyProperties(model, item);
				result.add(model);
			}
		}

		return result;
	}

	/**
	 * 用于在编辑页面验证菜单编码
	 * 
	 * @param userCode
	 * @return 返回 true 为已存在
	 */
	public boolean getYanZhenUserCode(String userCode) {
		SysMenuGroupModel user = new SysMenuGroupModel();
		user.setMenuGrpCode(StringUtil.toTrim(userCode));
		List<SysMenuGroupModel> userList = this.findByExample(user);
		if (userList != null && userList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 新增数据
	 * 
	 * @param menuitemjson
	 * @param officeCode
	 * @return
	 */
	@Override
	public String saveMenuGroupOff(String menuitemjson, String officeCode) {
		menuitemjson = logisticsOrderManager.getBase642Ojbect(menuitemjson);
		SysMenuGroupModel sysmenumodel = (SysMenuGroupModel) JsonUtil
				.jsonToBean(menuitemjson, SysMenuGroupModel.class);
		if (!getYanZhenUserCode(sysmenumodel.getMenuGrpCode())) {
			sysmenumodel.setRowState(BaseModel.ROW_STATE_ADDED);
			if (sysmenumodel.getOfficeCode() != null
					&& !sysmenumodel.getOfficeCode().equals("")) {
				SysOfficeModel sOff = sysOfficeManager.get(sysmenumodel
						.getOfficeCode());
				if (sOff != null && sOff.getOfficeUuid() != null
						&& !sOff.getOfficeUuid().equals("")) {
					sysmenumodel.setOfficeCode(sOff.getOfficeCode());
				}
			}
			sysmenumodel.setOfficeCode(sysmenumodel.getOfficeCode() != null
					&& !sysmenumodel.equals("") ? sysmenumodel.getOfficeCode()
					: officeCode);// 将所查询出来的officeCode加入到用户的officeCode的中
			this.save(sysmenumodel);
		} else {
			throw new ApplicationException("该编码已存在、请审核后重新填写！");
		}
		return JsonUtil.beanToJson(new SinotransPageJson(true, "保存成功!", "",
				null));
	}

	/**
	 * 修改数据
	 * 
	 * @param menuitemjson
	 * @param officeCode
	 * @return
	 */
	public String updateMenuGroupOff(String menuitemjson, String officeCode) {
		menuitemjson = logisticsOrderManager.getBase642Ojbect(menuitemjson);
		SysMenuGroupModel sysusermodel = (SysMenuGroupModel) JsonUtil
				.jsonToBean(menuitemjson, SysMenuGroupModel.class);
		sysusermodel.setRowState(BaseModel.ROW_STATE_MODIFIED);
		if (sysusermodel.getOfficeCode() != null
				&& !sysusermodel.getOfficeCode().equals("")) {
			SysOfficeModel sOff = sysOfficeManager.get(sysusermodel
					.getOfficeCode());
			if (sOff != null && sOff.getOfficeUuid() != null
					&& !sOff.getOfficeUuid().equals("")) {
				sysusermodel.setOfficeCode(sOff.getOfficeCode());
			}
		}
		sysusermodel.setOfficeCode(sysusermodel.getOfficeCode() != null
				&& !sysusermodel.equals("") ? sysusermodel.getOfficeCode()
				: officeCode);// 将所查询出来的officeCode加入到用户的officeCode的中
		ContextUtils.getBeanOfType(SysMenuGroupManager.class)
				.save(sysusermodel);
		return JsonUtil.beanToJson(new SinotransPageJson(true, "修改成功!", "",
				null));
	}

	/*
	 * 供给上级菜单选择信息（数据：只有菜单分组的数据。没有详细下级菜单信息）
	 * 
	 * 最顶级菜单
	 */
	@Override
	public List<OfficeTree> getMenuGroupTree() {
		List<SysMenuGroupModel> allOffice = this.getAll();
		List<OfficeTree> resultList = new ArrayList<OfficeTree>();
		OfficeTree oa = new OfficeTree();
		oa.setId("");// 标识加入是不根节点
		oa.setText("顶级菜单");// 加入名字
		resultList.add(oa);
		for (SysMenuGroupModel allOM : allOffice) {
			if (allOM.getStatus() != null && !allOM.getStatus().equals("")
					&& allOM.getStatus().equals(CommonUtil.Active)) {
				if (allOM.getPreSysMenuGroupUuid() == null
						|| allOM.getPreSysMenuGroupUuid().equals("")) {// 为空的情况下把节点加入也就是根节点
					OfficeTree ot = new OfficeTree();
					String officeId = allOM.getSysMenuGroupUuid();
					ot.setId(allOM.getSysMenuGroupUuid());// 标识加入是不根节点
					ot.setText(allOM.getMenuGrpName());// 加入名字
					ot.setChildren(recursive(officeId, allOffice));// 循环看看它是否有子节点
					if (RcUtil.isEmpty(allOM.getPreSysMenuGroupUuid())) {// 不为空的情况下把吧id标识加入
						ot.setId(allOM.getSysMenuGroupUuid());//
						ot.setText(allOM.getMenuGrpName());
						resultList.add(ot);
					}
				}
			}
		}
		return resultList;
	}

	/*
	 * 
	 * 根据用户的角色查询用户拥有的菜单模块
	 */
	@Override
	public List<OfficeTree> getUserModuleOfficetree(String UserId) {

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
			menuGroups = this.findUserByGroupId(sysMenuGroupUuid);
			// menuGroups=this.findByExample(sysMenuGroupUuid);
		}
		List<OfficeTree> resultList = new ArrayList<OfficeTree>();
		for (SysMenuGroupModel allOM : menuGroups) {
			if (allOM.getStatus() != null && !allOM.getStatus().equals("")
					&& allOM.getStatus().equals(CommonUtil.Active)) {
				if (allOM.getPreSysMenuGroupUuid() == null
						|| allOM.getPreSysMenuGroupUuid().equals("")) {// 为空的情况下把节点加入也就是根节点
					OfficeTree ot = new OfficeTree();
					String officeId = allOM.getSysMenuGroupUuid();
					ot.setId(allOM.getSysMenuGroupUuid());// 标识加入是不根节点
					ot.setText(allOM.getMenuGrpName());// 加入名字
					ot.setChildren(recursive(officeId, menuGroups));// 循环看看它是否有子节点
					if (RcUtil.isEmpty(allOM.getPreSysMenuGroupUuid())) {// 不为空的情况下把吧id标识加入
						ot.setId(allOM.getSysMenuGroupUuid());//
						ot.setText(allOM.getMenuGrpName());
						resultList.add(ot);
					}
				}
			}
		}
		return resultList;
	}

	// 有顶级节点
	@Override
	public List<OfficeTree> getFatherOfficetree() {
		List<OfficeTree> resultList = new ArrayList<OfficeTree>();
		// 为空的情况下把节点加入也就是根节点
		OfficeTree ot = new OfficeTree();
		ot.setId("");// 标识加入是不根节点
		ot.setText("顶级菜单");// 加入名字
		ot.setChildren(recursiveone());// 循环看看它是否有子节点
		resultList.add(ot);
		return resultList;
	}

	private List<OfficeTree> recursiveone() {
		List<OfficeTree> resultList = new ArrayList<OfficeTree>();
		List<SysMenuGroupModel> allOffice = this.getAll();
		for (SysMenuGroupModel allOM : allOffice) {
			if (allOM.getStatus() != null && !allOM.getStatus().equals("")
					&& allOM.getStatus().equals(CommonUtil.Active)) {
				if (allOM.getPreSysMenuGroupUuid() == null
						|| allOM.getPreSysMenuGroupUuid().equals("")) {
					OfficeTree ot = new OfficeTree();
					String officeId = allOM.getSysMenuGroupUuid();
					ot.setId(allOM.getSysMenuGroupUuid());// 标识加入是不根节点
					ot.setText(allOM.getMenuGrpName());// 加入名字
					ot.setChildren(recursive(officeId, allOffice));// 循环recursive
					// 而不是recursiveone
					if (RcUtil.isEmpty(allOM.getPreSysMenuGroupUuid())) {// 不为空的情况下把吧id标识加入
						ot.setId(allOM.getSysMenuGroupUuid());//
						ot.setText(allOM.getMenuGrpName());
						resultList.add(ot);
					}
				}
			}
		}
		return resultList;
	}

	@Override
	public List<SysMenuGroupModel> selectmenuGroup() {
		List<SysMenuGroupModel> Listmodel = new ArrayList<SysMenuGroupModel>();
		Listmodel = this.getAll();
		return Listmodel;
	}
}
