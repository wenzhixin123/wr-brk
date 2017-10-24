package com.sinotrans.gd.wlp.system.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.common.service.LogisticsOrderManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.model.SysRoleMenuButtonModel;
import com.sinotrans.gd.wlp.system.model.SysRoleMenuItemModel;
import com.sinotrans.gd.wlp.system.service.SysRoleMenuButtonManager;
import com.sinotrans.gd.wlp.system.service.SysRoleMenuItemManager;
import com.sinotrans.gd.wlp.util.JsonUtil;

@Service
public class SysRoleMenuItemManagerImpl extends BaseManagerImpl implements
		SysRoleMenuItemManager {
	@Autowired
	private LogisticsOrderManager logisticsOrderManager;

	public SysRoleMenuItemModel get(String id) {
		return this.dao.get(SysRoleMenuItemModel.class, id);
	}

	public List<SysRoleMenuItemModel> getAll() {
		return this.dao.getAll(SysRoleMenuItemModel.class);
	}

	public List<SysRoleMenuItemModel> findByExample(SysRoleMenuItemModel example) {
		return this.dao.findByExample(example);
	}

	public SysRoleMenuItemModel save(SysRoleMenuItemModel model) {
		return this.dao.save(model);
	}

	public List<SysRoleMenuItemModel> saveAll(
			Collection<SysRoleMenuItemModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysRoleMenuItemModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysRoleMenuItemModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysRoleMenuItemModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysRoleMenuItemModel.class, ids);
	}

	public List<String> findItemIdByRole(List<String> sysRoleUuid) {
		List<SysRoleMenuItemModel> pos = new ArrayList<SysRoleMenuItemModel>();
		List<String> result = new ArrayList<String>();
		pos = this.dao.createCommonQuery(SysRoleMenuItemModel.class)
				.addCondition(
						Condition.in("sysRoleUuid", sysRoleUuid.toArray()))
				.query();
		if (!RcUtil.isEmpty(pos) && pos.size() > 0) {
			for (SysRoleMenuItemModel m : pos) {
				result.add(m.getSysMenuItemUuid());
			}
		}
		return result;
	}

	public List<SysRoleMenuItemModel> getMenuitemall(String roleuuid) {
		SysRoleMenuItemModel srm = new SysRoleMenuItemModel();
		srm.setSysRoleUuid(roleuuid);
		List<SysRoleMenuItemModel> sMenum = this.findByExample(srm);
		return sMenum;
	}

	/*
	 * 保存角色与菜单信息
	 * 
	 * (non-Javadoc)
	 * 
	 * @seecom.sinotrans.gd.wms.system.service.SysRoleMenuItemManager#
	 * saveRoleMenuManager(java.lang.String)  SinotransPageJson
	 */
	public SinotransPageJson saveRoleMenuManager(String saveRoleMenuManagerall) {
		saveRoleMenuManagerall=logisticsOrderManager.getBase642Ojbect(saveRoleMenuManagerall);
		SinotransPageJson spj=new SinotransPageJson(true);
		SysRoleMenuItemModel[] model=null;
//		try {
//			model= (SysRoleMenuItemModel[]) JsonUtil .jsonToBean(saveRoleMenuManagerall, SysRoleMenuItemModel[].class);// 把页面传过来的数组转换成Model数组形式
//		} catch (Exception e) {
//			this.log.debug("转换异常说明数据不能正常转换是数据在页面Base64传输的.如果正常说明是后台调用的.");
//		}
//		if(RcUtil.isEmpty(model)){
//			saveRoleMenuManagerall=logisticsOrderManager.getBase642Ojbect(saveRoleMenuManagerall);
//		}
		this.log.debug(" +++ :" + saveRoleMenuManagerall);
		model = (SysRoleMenuItemModel[]) JsonUtil
				.jsonToBean(saveRoleMenuManagerall,
						SysRoleMenuItemModel[].class);// 把页面传过来的数组转换成Model数组形式
		// 每个对象中有角色id、Officecode、menuitemuuid

		SysRoleMenuItemModel example = new SysRoleMenuItemModel();
		example.setSysRoleUuid(model[0].getSysRoleUuid());

		List<SysRoleMenuItemModel> rolemenuLise = this.findByExample(example);
		SysRoleMenuItemModel[] rolemenusize = rolemenuLise
				.toArray(new SysRoleMenuItemModel[rolemenuLise.size()]);

		for (SysRoleMenuItemModel roleMenuItem : rolemenusize) {
			for (int j = 0; j < model.length; j++) {
				if (roleMenuItem.getSysMenuItemUuid() != null
						&& model[j].getSysMenuItemUuid() != null) {
					if (roleMenuItem.getSysMenuItemUuid().equals(
							model[j].getSysMenuItemUuid())) {
						roleMenuItem.setSysMenuItemUuid("NULL");
						model[j].setSysMenuItemUuid("null");
					}
				}
			}
		}

		for (int i = 0; i < rolemenusize.length; i++) {
			if (rolemenusize[i].getSysMenuItemUuid() != null
					&& !rolemenusize[i].getSysMenuItemUuid().equals("NULL")) {
				this.log.debug("**删除数据");
				SysRoleMenuButtonModel sButton = new SysRoleMenuButtonModel();
				sButton.setSysRoleMenuItemUuid(rolemenusize[i]
						.getSysRoleMenuItemUuid());

				List<SysRoleMenuButtonModel> sb = ContextUtils.getBeanOfType(
						SysRoleMenuButtonManager.class).findByExample(sButton);// 根据菜单uuid查询出所有按钮管理信息

				for (SysRoleMenuButtonModel sysRoleMenuButtonModel : sb) {
					ContextUtils.getBeanOfType(SysRoleMenuButtonManager.class)
							.remove(sysRoleMenuButtonModel); // 执行删除该按钮关联
				}
				ContextUtils.getBeanOfType(SysRoleMenuItemManager.class)
						.remove(rolemenusize[i]); // 最后执行删除角色与菜单表的删除
			}
		}

		for (int i = 0; i < model.length; i++) {
			if (model[i].getSysMenuItemUuid() != null
					&& !model[i].getSysMenuItemUuid().equals("null")) {
				this.log.debug("** 新增数据");
				List<SysRoleMenuItemModel> rolemnuModel = this
						.findByExample(model[i]);
				if (rolemnuModel.size() == 0) {
					try {
						this.save(model[i]);
					} catch (RuntimeException e) {
						e.printStackTrace();
						spj.setResult(false);
						spj.setError("菜单分组不能被选择!");
					}
				} else {
					this.log.debug("** ： 保存已存在角色与菜单关系！");
				}
			}
		}
		return spj;
	}
	
	/*
	 * 保存角色与菜单信息(提供给角色与权限使用)此方法中不使用base64
	 * 
	 * (non-Javadoc)
	 * 
	 * @seecom.sinotrans.gd.wlp.system.service.SysRoleMenuItemManager#
	 * saveRoleMenuManager(java.lang.String)
	 */
	public String saveRoleMenuManagerNOBase64(String saveRoleMenuManagerall) {
		SysRoleMenuItemModel[] model=null;
//		try {
			model= (SysRoleMenuItemModel[]) JsonUtil .jsonToBean(saveRoleMenuManagerall, SysRoleMenuItemModel[].class);// 把页面传过来的数组转换成Model数组形式
//		} catch (Exception e) {
//			this.log.debug("转换异常说明数据不能正常转换是数据在页面Base64传输的.如果正常说明是后台调用的.");
//		}
//		if(RcUtil.isEmpty(model)){
//			saveRoleMenuManagerall=logisticsOrderManager.getBase642Ojbect(saveRoleMenuManagerall);
//		}
		this.log.debug(" +++ :" + saveRoleMenuManagerall);
		model = (SysRoleMenuItemModel[]) JsonUtil
				.jsonToBean(saveRoleMenuManagerall,
						SysRoleMenuItemModel[].class);// 把页面传过来的数组转换成Model数组形式
		// 每个对象中有角色id、Officecode、menuitemuuid

		SysRoleMenuItemModel example = new SysRoleMenuItemModel();
		
		//-------------------------2013-7-2--modify by chenyc--------------------------
		if(model[0].getSysRoleUuid()==null||model[0].getSysRoleUuid().equals("")||model[0].getSysRoleUuid().toUpperCase().equals("NULL")){
			return "";
		}
		//-----------------------------------------------------------------------------
		example.setSysRoleUuid(model[0].getSysRoleUuid());

		List<SysRoleMenuItemModel> rolemenuLise = this.findByExample(example);
		SysRoleMenuItemModel[] rolemenusize = rolemenuLise
				.toArray(new SysRoleMenuItemModel[rolemenuLise.size()]);

		for (SysRoleMenuItemModel roleMenuItem : rolemenusize) {
			for (int j = 0; j < model.length; j++) {
				if (roleMenuItem.getSysMenuItemUuid() != null
						&& model[j].getSysMenuItemUuid() != null) {
					if (roleMenuItem.getSysMenuItemUuid().equals(
							model[j].getSysMenuItemUuid())) {
						roleMenuItem.setSysMenuItemUuid("NULL");
						model[j].setSysMenuItemUuid("null");
					}
				}
			}
		}

		for (int i = 0; i < rolemenusize.length; i++) {
			if (rolemenusize[i].getSysMenuItemUuid() != null
					&& !rolemenusize[i].getSysMenuItemUuid().equals("NULL")) {
				this.log.debug("**删除数据");
				SysRoleMenuButtonModel sButton = new SysRoleMenuButtonModel();
				sButton.setSysRoleMenuItemUuid(rolemenusize[i]
						.getSysRoleMenuItemUuid());

				List<SysRoleMenuButtonModel> sb = ContextUtils.getBeanOfType(
						SysRoleMenuButtonManager.class).findByExample(sButton);// 根据菜单uuid查询出所有按钮管理信息

				for (SysRoleMenuButtonModel sysRoleMenuButtonModel : sb) {
					ContextUtils.getBeanOfType(SysRoleMenuButtonManager.class)
							.remove(sysRoleMenuButtonModel); // 执行删除该按钮关联
				}
				ContextUtils.getBeanOfType(SysRoleMenuItemManager.class)
						.remove(rolemenusize[i]); // 最后执行删除角色与菜单表的删除
			}
		}

		for (int i = 0; i < model.length; i++) {
			if (model[i].getSysMenuItemUuid() != null
					&& !model[i].getSysMenuItemUuid().equals("null")) {
				this.log.debug("** 新增数据");
				List<SysRoleMenuItemModel> rolemnuModel = this
						.findByExample(model[i]);
				if (rolemnuModel.size() == 0) {
					this.save(model[i]);
				} else {
					this.log.debug("** ： 保存已存在角色与菜单关系！");
				}
			}
		}
		return "true";
	}

	/**
	 * 根据菜单项UUID验证是否已经被分组。
	 * 
	 * @param sysMenuItemUuid
	 * @return 返回结果("true")或者("false") 如果更具菜单UUID在菜单分组内查询到了结果。那么返回"true"
	 */
	public String getValidationMenuItemAssociation(String sysMenuItemUuid) {
		List<SysRoleMenuItemModel> sMList = new ArrayList<SysRoleMenuItemModel>();
		SysRoleMenuItemModel sMiM = new SysRoleMenuItemModel();
		sMiM.setSysMenuItemUuid(sysMenuItemUuid);
		sMList = this.findByExample(sMiM);
		if (sMList != null && sMList.size() > 0) {
			return "true";
		} else {
			return "false";
		}
	}
}
