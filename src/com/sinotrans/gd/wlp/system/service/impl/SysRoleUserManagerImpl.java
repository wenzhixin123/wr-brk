package com.sinotrans.gd.wlp.system.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.common.service.LogisticsOrderManager;
import com.sinotrans.gd.wlp.system.entity.ModuleGroupMenuItemTrue;
import com.sinotrans.gd.wlp.system.model.SysRoleModel;
import com.sinotrans.gd.wlp.system.model.SysRoleUserModel;
import com.sinotrans.gd.wlp.system.model.SysUserModel;
import com.sinotrans.gd.wlp.system.service.SysRoleUserManager;
import com.sinotrans.gd.wlp.util.JsonUtil;
import com.sinotrans.gd.wlp.util.StringUtil;

@Service
public class SysRoleUserManagerImpl extends BaseManagerImpl implements
		SysRoleUserManager {

	@Autowired
	private LogisticsOrderManager logisticsOrderManager;

	public SysRoleUserModel get(String id) {
		return this.dao.get(SysRoleUserModel.class, id);
	}

	public List<SysRoleUserModel> getAll() {
		return this.dao.getAll(SysRoleUserModel.class);
	}

	public List<SysRoleUserModel> findByExample(SysRoleUserModel example) {
		return this.dao.findByExample(example);
	}

	public SysRoleUserModel save(SysRoleUserModel model) {
		return this.dao.save(model);
	}

	public List<SysRoleUserModel> saveAll(Collection<SysRoleUserModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(SysRoleUserModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<SysRoleUserModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(SysRoleUserModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(SysRoleUserModel.class, ids);
	}

	public List<SysRoleUserModel> selectuserroleall(String roleuuid) {
		SysRoleUserModel srm = new SysRoleUserModel();
		srm.setUserUuid(roleuuid);
		List<SysRoleUserModel> sMenum = this.findByExample(srm);
		List<ModuleGroupMenuItemTrue> mlgmitree = new ArrayList<ModuleGroupMenuItemTrue>();
		for (SysRoleUserModel sysrolemenu : sMenum) {
			ModuleGroupMenuItemTrue mgmit = new ModuleGroupMenuItemTrue();
			mgmit.setId(sysrolemenu.getSysRoleUuid());
			mlgmitree.add(mgmit);
		}
		return sMenum;
	}

	public String saveUserRoleManagerMangToMang(String userAll, String roleAll) {
		userAll=logisticsOrderManager.getBase642Ojbect(userAll);
		roleAll=logisticsOrderManager.getBase642Ojbect(roleAll);
		SysUserModel[] userArray = (SysUserModel[]) JsonUtil.jsonToBean(
				userAll, SysUserModel[].class);
		SysRoleModel[] roleArray = (SysRoleModel[]) JsonUtil.jsonToBean(
				roleAll, SysRoleModel[].class);

		this.log.debug(" ***************此下执行删除语句");
		for (SysUserModel sysroleuser : userArray) {
			SysRoleUserModel roleuser = new SysRoleUserModel();
			roleuser.setUserUuid(sysroleuser.getUserUuid());
			List<SysRoleUserModel> susermode = new ArrayList<SysRoleUserModel>();
			susermode = ContextUtils.getBeanOfType(SysRoleUserManager.class)
					.findByExample(roleuser);
			for (SysRoleUserModel sysRoleUserModel : susermode) {
				ContextUtils.getBeanOfType(SysRoleUserManager.class).remove(
						sysRoleUserModel);
			}
		}
		this.log.debug(" ***************此上执行删除语句");

		for (SysUserModel sysUserModel : userArray) {
			for (SysRoleModel sysRoleModel : roleArray) {
				SysRoleUserModel roleusersave = new SysRoleUserModel();
				roleusersave.setUserUuid(sysUserModel.getUserUuid());
				roleusersave.setSysRoleUuid(sysRoleModel.getSysRoleUuid());
				roleusersave.setOfficeCode(sysUserModel.getOfficeCode());
				this.dao.save(roleusersave);
			}
		}
		return null;
	}

	/**
	 * 根据用户UUID验证是否该用户有分配过的权限。现在用于在用户页面验证来判断删除按钮是否屏蔽
	 * 
	 * @param uuidUser
	 * @return 返回为字符串("true")或者("false")如果该用户有权限为解除则返回"true"否则返回"false"
	 */
	public String getUserValidationUR(String uuidUser) {
		SysRoleUserModel sruModel = new SysRoleUserModel();
		sruModel.setUserUuid(StringUtil.toTrim(uuidUser));
		List<SysRoleUserModel> roleUserList = this.findByExample(sruModel);
		if (roleUserList != null && roleUserList.size() > 0) {
			return "true";
		} else {
			return "false";
		}
	}
}
