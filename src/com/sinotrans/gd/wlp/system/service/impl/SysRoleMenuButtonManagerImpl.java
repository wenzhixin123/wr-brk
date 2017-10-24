package com.sinotrans.gd.wlp.system.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.common.service.LogisticsOrderManager;
import com.sinotrans.gd.wlp.system.model.SysMenuItemModel;
import com.sinotrans.gd.wlp.system.model.SysRoleMenuButtonModel;
import com.sinotrans.gd.wlp.system.model.SysRoleMenuItemModel;
import com.sinotrans.gd.wlp.system.model.SysRoleModel;
import com.sinotrans.gd.wlp.system.model.SysViewButtonModel;
import com.sinotrans.gd.wlp.system.service.SysRoleMenuButtonManager;
import com.sinotrans.gd.wlp.system.service.SysRoleMenuItemManager;
import com.sinotrans.gd.wlp.util.JsonUtil;

@Service
public class SysRoleMenuButtonManagerImpl extends BaseManagerImpl implements
		SysRoleMenuButtonManager {
	@Autowired
	private LogisticsOrderManager logisticsOrderManager;

	public SysRoleMenuButtonModel get(String id) {
		return this.dao.get(SysRoleMenuButtonModel.class, id);
	}

	public List<SysRoleMenuButtonModel> getAll() {
		return this.dao.getAll(SysRoleMenuButtonModel.class);
	}

	public List<SysRoleMenuButtonModel> findByExample(
			SysRoleMenuButtonModel example) {
		return this.dao.findByExample(example);
	}

	public SysRoleMenuButtonModel save(SysRoleMenuButtonModel model) {
		return this.dao.save(model);
	}

	public List<SysRoleMenuButtonModel> saveAll(
			Collection<SysRoleMenuButtonModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysRoleMenuButtonModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysRoleMenuButtonModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysRoleMenuButtonModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysRoleMenuButtonModel.class, ids);
	}

	public List<SysRoleMenuButtonModel> selectMenuButtonall(String roleuuid,
			String menuuuid) {
		SysRoleMenuItemModel su = new SysRoleMenuItemModel();
		su.setSysRoleUuid(roleuuid);
		su.setSysMenuItemUuid(menuuuid);
		List<SysRoleMenuItemModel> SrMim = ContextUtils.getBeanOfType(
				SysRoleMenuItemManager.class).findByExample(su);
		SysRoleMenuButtonModel srm = new SysRoleMenuButtonModel();
		for (SysRoleMenuItemModel sysRoleMenuItemModel : SrMim) {
			srm.setSysRoleMenuItemUuid(sysRoleMenuItemModel
					.getSysRoleMenuItemUuid());
		}
		this.log.debug("下方是输出语句：" + srm.getSysRoleMenuButtonUuid() + " : "
				+ srm.getSysRoleMenuItemUuid() + " : "
				+ srm.getSysViewButtonUuid() + " :　");
		List<SysRoleMenuButtonModel> sMenum = new ArrayList<SysRoleMenuButtonModel>();
		if (srm.getSysRoleMenuItemUuid() != null) {
			sMenum = this.findByExample(srm);
		}
		this.log.debug("此处输出菜单和按钮数据大小：" + sMenum.size());
		return sMenum;
	}

	/*
	 * 保存角色与菜单 ———— 菜单与按钮的权限信息
	 * 
	 * (non-Javadoc)
	 * 
	 * @seecom.sinotrans.gd.wlp.system.service.SysRoleMenuButtonManager#
	 * saveRoleButtonOrMenuButton(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String saveRoleButtonOrMenuButton(String roleNews, String menuNews,
			String buttonNews, String rolemenujsons) {
		roleNews=logisticsOrderManager.getBase642Ojbect(roleNews);
		menuNews=logisticsOrderManager.getBase642Ojbect(menuNews);
		buttonNews=logisticsOrderManager.getBase642Ojbect(buttonNews);
		rolemenujsons=logisticsOrderManager.getBase642Ojbect(rolemenujsons);
		try {
			SysRoleModel[] sysRoleModelList = (SysRoleModel[]) JsonUtil
					.jsonToBean(roleNews, SysRoleModel[].class);
			SysMenuItemModel[] sysMenuItemModelList = (SysMenuItemModel[]) JsonUtil
					.jsonToBean(menuNews, SysMenuItemModel[].class);
			SysViewButtonModel[] sysViewButtonList = (SysViewButtonModel[]) JsonUtil
					.jsonToBean(buttonNews, SysViewButtonModel[].class);
			// 保存角色与菜单
			String boo = ContextUtils.getBeanOfType(
					SysRoleMenuItemManager.class).saveRoleMenuManagerNOBase64(
					rolemenujsons);
			if (!boo.equals("true")) {
				return "false";
			}

			SysRoleMenuItemModel sRoleMenuModel = new SysRoleMenuItemModel();
			sRoleMenuModel.setSysRoleUuid(sysRoleModelList[0].getSysRoleUuid());
			sRoleMenuModel.setSysMenuItemUuid(sysMenuItemModelList[0]
					.getSysMenuItemUuid());

			this.log.debug("传入_____________________________参数："
					+ sRoleMenuModel);

			List<SysRoleMenuItemModel> SrMim = ContextUtils.getBeanOfType(
					SysRoleMenuItemManager.class).findByExample(sRoleMenuModel);

			SysRoleMenuButtonModel srm = new SysRoleMenuButtonModel();
			for (SysRoleMenuItemModel sysRoleMenuItemModel : SrMim) {
				srm.setSysRoleMenuItemUuid(sysRoleMenuItemModel
						.getSysRoleMenuItemUuid());
			}
			this.log.debug(" ***************此下执行删除语句 : srm: " + SrMim.size());
			SysRoleMenuButtonModel roleuser = new SysRoleMenuButtonModel();
			roleuser.setSysRoleMenuItemUuid(srm.getSysRoleMenuItemUuid());

			List<SysRoleMenuButtonModel> sRoleButton = ContextUtils
					.getBeanOfType(SysRoleMenuButtonManager.class)
					.findByExample(roleuser);
			for (SysRoleMenuButtonModel sysRoleMenuButtonModel : sRoleButton) {
				try {
					ContextUtils.getBeanOfType(SysRoleMenuButtonManager.class)
							.remove(sysRoleMenuButtonModel);
				} catch (Exception e) {
					return "false";
				}
			}
			this.log.debug(" ****此下执行保存语句***********此上执行删除语句:" + roleuser);

			for (SysViewButtonModel sysViewButtonModel : sysViewButtonList) {
				SysRoleMenuButtonModel roleMenuButton = new SysRoleMenuButtonModel();
				roleMenuButton.setSysViewButtonUuid(sysViewButtonModel
						.getSysViewButtonUuid());
				roleMenuButton.setSysRoleMenuItemUuid(srm
						.getSysRoleMenuItemUuid());
				roleMenuButton.setOfficeCode(sysRoleModelList[0]
						.getOfficeCode());
				roleMenuButton.setStatus(sysRoleModelList[0].getStatus());
				try {
					this.save(roleMenuButton);
				} catch (Exception e) {
					return "false";
				}

			}
			this.log.debug(" ***************此上执行保存语句————————————————————");
		} catch (Exception e) {
			return "false";
		}
		return "true";
	}
}
