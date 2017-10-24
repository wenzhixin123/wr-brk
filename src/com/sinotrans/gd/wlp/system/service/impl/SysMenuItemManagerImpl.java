package com.sinotrans.gd.wlp.system.service.impl;

import java.lang.reflect.InvocationTargetException;
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
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.system.model.SysMenuItemModel;
import com.sinotrans.gd.wlp.system.model.SysOfficeModel;
import com.sinotrans.gd.wlp.system.query.GetMenuItemByGroupUuidQueryCondition;
import com.sinotrans.gd.wlp.system.query.GetMenuItemByGroupUuidQueryItem;
import com.sinotrans.gd.wlp.system.query.ScanerMenuItemByUserCodeQueryCondition;
import com.sinotrans.gd.wlp.system.query.ScanerMenuItemByUserCodeQueryItem;
import com.sinotrans.gd.wlp.system.service.SysMenuItemManager;
import com.sinotrans.gd.wlp.system.service.SysOfficeManager;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.JsonUtil;
import com.sinotrans.gd.wlp.util.StringUtil;

@Service
public class SysMenuItemManagerImpl extends BaseManagerImpl implements
		SysMenuItemManager {
	@Autowired
	private SysOfficeManager sysOfficeManager;
	@Autowired
	private LogisticsOrderManager logisticsOrderManager;

	public SysMenuItemModel get(String id) {
		return this.dao.get(SysMenuItemModel.class, id);
	}

	public List<SysMenuItemModel> getAll() {
		return this.dao.getAll(SysMenuItemModel.class);
	}

	public List<SysMenuItemModel> findByExample(SysMenuItemModel example) {
		return this.dao.findByExample(example);
	}

	public SysMenuItemModel save(SysMenuItemModel model) {
		return this.dao.save(model);
	}

	public List<SysMenuItemModel> saveAll(Collection<SysMenuItemModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysMenuItemModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysMenuItemModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysMenuItemModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysMenuItemModel.class, ids);
	}

	public List<BasOption> getOption(String code, String officeCode,
			String language) {
		List<BasOption> optionList = new ArrayList<BasOption>();
		List<SysMenuItemModel> bcmList = this.getAll();
		for (SysMenuItemModel c : bcmList) {
			BasOption wo = new BasOption();
			wo.setKey(c.getSysMenuItemUuid());
			wo.setValue(c.getMenuItemCode());
			optionList.add(wo);
		}
		return optionList;
	}

	public void getMenuitemTree() {

	}

	public List<SysMenuItemModel> findByIds(List<String> sysMenuItemUuid) {
		List<SysMenuItemModel> result = new ArrayList<SysMenuItemModel>();
		result = this.dao.createCommonQuery(SysMenuItemModel.class)
				.addCondition(Condition.in("sysMenuItemUuid", sysMenuItemUuid.toArray()))
				.addCondition(Condition.eq(SysMenuItemModel.FieldNames.status, "Active"))
				.setOrderBy("menuItemSeq").query();
		return result;
	}

	/**
	 * 返回单纯的菜单列表。并且使用BasOption返回List转换为JSON后在前台页面用DWR调用(列表是所有的菜单项)
	 * 
	 * @return
	 */
	public String getMenuItemModelMap() {
		List<SysMenuItemModel> result = new ArrayList<SysMenuItemModel>();
		List<BasOption> menuItemList = new ArrayList<BasOption>();
		result = this.getAll();
		for (SysMenuItemModel sysMenuItemModel : result) {
			BasOption menuModel = new BasOption();
			menuModel.setKey(sysMenuItemModel.getSysMenuItemUuid());
			menuModel.setValue(sysMenuItemModel.getMenuItemName());
			menuItemList.add(menuModel);
		}
		String menu = JsonUtil.list2Json(menuItemList);
		return menu;
	}

	public List<SysMenuItemModel> getMenuItemByGroupUuid(String groupUuid) {
		List<SysMenuItemModel> result = new ArrayList<SysMenuItemModel>();

		if (!RcUtil.isEmpty(groupUuid)) {
			result = this.dao.createCommonQuery(SysMenuItemModel.class)
					.addCondition(Condition.eq("sysMenuGroupUuid", groupUuid))
					.query();
		}

		return result;
	}

	/**
	 * 根据用户获取bar枪操作的菜单 现在这里是专门给bar枪用的
	 */
	public List<SysMenuItemModel> getMenuItemByUserCode(String userCode,
			String officeCode) throws Exception {
		List<SysMenuItemModel> result = new ArrayList<SysMenuItemModel>();

		if (!RcUtil.isEmpty(userCode)) {
			List<ScanerMenuItemByUserCodeQueryItem> items = new ArrayList<ScanerMenuItemByUserCodeQueryItem>();
			ScanerMenuItemByUserCodeQueryCondition condition = new ScanerMenuItemByUserCodeQueryCondition(
					userCode, officeCode, "Bar枪操作管理", CommonUtil.Active);
			items = this.dao.query(condition,
					ScanerMenuItemByUserCodeQueryItem.class);

			if (!RcUtil.isEmpty(items) && items.size() > 0) {
				for (ScanerMenuItemByUserCodeQueryItem item : items) {
					SysMenuItemModel model = new SysMenuItemModel();
					BeanUtils.copyProperties(model, item);
					result.add(model);
				}
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
		SysMenuItemModel user = new SysMenuItemModel();
		user.setMenuItemCode(StringUtil.toTrim(userCode));
		List<SysMenuItemModel> userList = this.findByExample(user);
		if (userList.size() > 0) {
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
	public String saveMenuItemOff(String menuitemjson, String officeCode) {
		menuitemjson=logisticsOrderManager.getBase642Ojbect(menuitemjson);
		SysMenuItemModel sysmenumodel = (SysMenuItemModel) JsonUtil.jsonToBean(
				menuitemjson, SysMenuItemModel.class);
		if (!getYanZhenUserCode(sysmenumodel.getMenuItemCode())) {
			sysmenumodel.setRowState(BaseModel.ROW_STATE_ADDED);
			SysOfficeModel sOff = sysOfficeManager.get(sysmenumodel
					.getOfficeCode());
			if (sOff != null && sOff.getOfficeUuid() != null
					&& !sOff.getOfficeUuid().equals("")) {
				sysmenumodel.setOfficeCode(sOff.getOfficeCode());
			}
			sysmenumodel.setOfficeCode(sysmenumodel.getOfficeCode() != null
					&& !sysmenumodel.equals("") ? sysmenumodel.getOfficeCode()
					: officeCode);// 将所查询出来的officeCode加入到用户的officeCode的中
			this.save(sysmenumodel);
		} else {
			throw new ApplicationException("该编码已存在、请审核后重新填写！");
		}
		return null;
	}

	/**
	 * 修改数据
	 * 
	 * @param menuitemjson
	 * @param officeCode
	 * @return
	 */
	public String updateMenuItemOff(String menuitemjson, String officeCode) {
		menuitemjson=logisticsOrderManager.getBase642Ojbect(menuitemjson);
		SysMenuItemModel sysusermodel = (SysMenuItemModel) JsonUtil.jsonToBean(
				menuitemjson, SysMenuItemModel.class);
		sysusermodel.setRowState(BaseModel.ROW_STATE_MODIFIED);
		SysOfficeModel sOff = sysOfficeManager
				.get(sysusermodel.getOfficeCode());
		if (sOff != null && sOff.getOfficeUuid() != null
				&& !sOff.getOfficeUuid().equals("")) {
			sysusermodel.setOfficeCode(sOff.getOfficeCode());
		}
		sysusermodel.setOfficeCode(sysusermodel.getOfficeCode() != null
				&& !sysusermodel.equals("") ? sysusermodel.getOfficeCode()
				: officeCode);// 将所查询出来的officeCode加入到用户的officeCode的中
		ContextUtils.getBeanOfType(SysMenuItemManager.class).save(sysusermodel);
		return null;
	}

	/**
	 * 根据groupUuid与用户编号查菜单项
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public List<SysMenuItemModel> getMenuItemByGroupUuidAndUserCode(
			String groupUuid, String userCode, String officeCode)
			throws Exception {
		List<SysMenuItemModel> result = new ArrayList<SysMenuItemModel>();
		List<GetMenuItemByGroupUuidQueryItem> items = null;
		GetMenuItemByGroupUuidQueryCondition condition = new GetMenuItemByGroupUuidQueryCondition(
				groupUuid, officeCode, userCode, CommonUtil.Active);
		items = this.dao
				.query(condition, GetMenuItemByGroupUuidQueryItem.class);

		if (!RcUtil.isEmpty(items) && items.size() > 0) {
			for (GetMenuItemByGroupUuidQueryItem item : items) {
				SysMenuItemModel model = new SysMenuItemModel();
				BeanUtils.copyProperties(model, item);
				result.add(model);
			}
		}

		return result;
	}
}
