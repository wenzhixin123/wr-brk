/**
 * 
 */
package com.sinotrans.gd.wlp.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.common.entity.BarLoginUserEntity;
import com.sinotrans.gd.wlp.common.model.BarVersionUpdateInfoModel;
import com.sinotrans.gd.wlp.common.query.BarMenuPermissionQueryCondition;
import com.sinotrans.gd.wlp.common.query.BarMenuPermissionQueryItem;
import com.sinotrans.gd.wlp.common.service.BarClientCommonManager;
import com.sinotrans.gd.wlp.common.service.LogisticsOrderDetailManager;
import com.sinotrans.gd.wlp.common.service.LogisticsOrderManager;
import com.sinotrans.gd.wlp.common.service.WmsCommonManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.model.SysOfficeModel;
import com.sinotrans.gd.wlp.system.model.SysUserModel;
import com.sinotrans.gd.wlp.util.CommonUtil;
/**
 * @author hth
 *  、 bar枪CS客户端服务实现类
 */
@Service
public class BarClientCommonManagerImpl extends BaseManagerImpl implements BarClientCommonManager {
	@Autowired
	private WmsCommonManager wmsCommonManager;
	@Autowired
	private LogisticsOrderDetailManager logisticsOrderDetailManager;
	@Autowired
	private LogisticsOrderManager logisticsOrderManager;
	
	
	@Override
	public List<SysUserModel> findUserMessage(String userId) {
		if (!RcUtil.isEmpty(userId)) {
			List<SysUserModel> users = dao
					.createCommonQuery(SysUserModel.class).addCondition(
							Condition.eq(SysUserModel.FieldNames.userCode,
									userId)).addCondition(
							Condition.eq(SysUserModel.FieldNames.status,
									CommonUtil.Active)).query();
			return users;
		}
		return null;
	}

	

	@Override
	public SinotransPageJson validConnection(String officeCode) {
		SinotransPageJson spj = new SinotransPageJson();
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setMsg("officeCode不能为空!");
			return spj;
		}
		List<SysOfficeModel> list = this.dao.createCommonQuery(
				SysOfficeModel.class).addCondition(
				Condition.eq(SysOfficeModel.FieldNames.officeCode, officeCode)).addCondition(
				Condition.eq(SysOfficeModel.FieldNames.status, CommonUtil.Active)).query();
		if (list.size() > 0) {
			spj.setMsg("此仓库代码有效!");
			spj.setResult(true);
		} else {
			spj.setResult(false);
			spj.setMsg("没有此仓库代码!");
		}
		return spj;
	}

	@Override
	public SinotransPageJson validLogin(String userId, String password) {
		SinotransPageJson spj = new SinotransPageJson();
		if(RcUtil.isEmpty(userId)) {
			spj.setResult(false);
			spj.setMsg("用户ID不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(password)) {
			spj.setResult(false);
			spj.setMsg("密码不能为空!");
			return spj;
		}
		Md5PasswordEncoder md5 = ContextUtils.getBeanOfType(Md5PasswordEncoder.class);
		SysUserModel userModel = null;
		String newPassword = md5.encodePassword(password, null);
		List<SysUserModel> userList = this.dao.createCommonQuery(SysUserModel.class)
				.addCondition(Condition.eq(SysUserModel.FieldNames.userCode, userId))
				.addCondition(Condition.eq(SysUserModel.FieldNames.status, CommonUtil.Active))
				.addCondition(Condition.eq(SysUserModel.FieldNames.password, newPassword)).query();
		if (!RcUtil.isEmpty(userList) && userList.size() > 0) {
			userModel = userList.get(0);
		}
		
		if (RcUtil.isEmpty(userModel)) {
			spj.setResult(false);
			spj.setMsg("用户名或密码错误!");
		} else {
			BarLoginUserEntity userEntity = new BarLoginUserEntity();
			userEntity.setUserUuid(userModel.getUserUuid());
			userEntity.setUserId(userModel.getUserCode());
			userEntity.setUserName(userModel.getUserName());
			userEntity.setUserType(userModel.getUserType());
			userEntity.setOfficeCode(userModel.getOfficeCode());
			spj.setResult(true);
			spj.setMsg("登录成功!");
			spj.setObject(userEntity);
		}
		return spj;
	}
	
	
	

	@Override
	public SinotransPageJson checkConnection() {
		SinotransPageJson spj = new SinotransPageJson();
		spj.setResult(true);
		return spj;
	}

	@Override
	public SinotransPageJson getDefaultLotStock(String controlWord,
			String officeCode) throws Exception {
		return null;
	}



	@Override
	public SinotransPageJson authorizedCodeLogin(String userCode,
			String password, String btnCode, String officeCode) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public SinotransPageJson loginExit(String userId, String password) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public SinotransPageJson checkMenuPermission(String menuName,
			String userCode, String officeCode) {
		SinotransPageJson spj = new SinotransPageJson();
		BarMenuPermissionQueryCondition condition =new BarMenuPermissionQueryCondition(menuName, CommonUtil.Active, officeCode, userCode);
		List<BarMenuPermissionQueryItem> itemList = this.dao.query(condition,
				BarMenuPermissionQueryItem.class);
		if (itemList != null && itemList.size() > 0) {
			spj.setResult(true);
			spj.setObject(itemList);
		} else {
			spj.setResult(false);
			spj.setObject(itemList);
			spj.setError("没有设置好系统权限!");
		}
		return spj;
	}
   
	@Override
	public SinotransPageJson barFindVersionByOfficeCode(String officeCode) {
		SinotransPageJson spj = new SinotransPageJson(false);
		List<BarVersionUpdateInfoModel> buList =this.dao.createCommonQuery(BarVersionUpdateInfoModel.class)
					.addCondition(Condition.eq(BarVersionUpdateInfoModel.FieldNames.officeCode, officeCode))
					.query();
		if (!RcUtil.isEmpty(buList) && buList.size() > 0) {
			spj.setResult(true);
			spj.setObject(buList.get(0));
		}
		return spj;
	}
	
	
}