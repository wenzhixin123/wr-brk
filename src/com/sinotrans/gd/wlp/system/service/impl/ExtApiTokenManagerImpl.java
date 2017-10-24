package com.sinotrans.gd.wlp.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeDefModel;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeDefManager;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeTypeManager;
import com.sinotrans.gd.wlp.system.entity.ExtApiTokenEntity;
import com.sinotrans.gd.wlp.system.model.SysOfficeModel;
import com.sinotrans.gd.wlp.system.model.SysUserModel;
import com.sinotrans.gd.wlp.system.service.ExtApiTokenManager;
import com.sinotrans.gd.wlp.system.service.SysOfficeManager;
import com.sinotrans.gd.wlp.system.service.SysUserManager;
import com.sinotrans.gd.wlp.system.web.ExtApiTokenWebVo;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.StringUtil;

@Service
public class ExtApiTokenManagerImpl extends BaseManagerImpl implements ExtApiTokenManager {

	@Autowired
	private BasCodeTypeManager basCodeTypeManager = ContextUtils
	.getBeanOfType(BasCodeTypeManager.class);
	
	@Autowired
	private BasCodeDefManager basCodeDefManager = ContextUtils
	.getBeanOfType(BasCodeDefManager.class);
	
	@Autowired
	private SysOfficeManager sysOfficeManager = ContextUtils
	.getBeanOfType(SysOfficeManager.class);
	
	@Autowired
	private SysUserManager sysUserManager= ContextUtils
	.getBeanOfType(SysUserManager.class);
	
	@Override
	public List<ExtApiTokenEntity> saveClient(
			List<ExtApiTokenWebVo> tokenWebVoList) {
		List<ExtApiTokenEntity> resultEntity = new ArrayList<ExtApiTokenEntity>();
		
		for(ExtApiTokenWebVo webVo : tokenWebVoList){
			String userCode = "";
			SysUserModel userModel = new SysUserModel();
			if(webVo.getUsercode()!=null&&!"".equals(webVo.getUsercode())){
				userModel.setUserCode(webVo.getUsercode());
				userModel.setStatus(CommonUtil.Active);
				List<SysUserModel> userModels = sysUserManager.findByExample(userModel);
				if(userModels != null && userModels.size()>0 ){
					userModel = userModels.get(0);
					userCode = userModel.getUserCode();
				}else{
					throw new ApplicationException("用户"+webVo.getUsercode()+"无效或者不存在,请先创建该用户!");
				}
			}
			
			if(userCode!=null&&!"".equals(userCode)){
				ExtApiTokenEntity tokenEntity = new ExtApiTokenEntity();
				BasCodeDefModel clientIdCodeModel = basCodeDefManager.getCodeDefModelByCodeTypeValue(CommonUtil.AUTH_CLIENT_ID, webVo.getUsercode(),null);
				BasCodeDefModel clientSecretModel = basCodeDefManager.getCodeDefModelByCodeTypeValue(CommonUtil.AUTH_CLIENT_SECRET, webVo.getUsercode(),null);
				if(((clientIdCodeModel != null && clientIdCodeModel.getBasCodeDefUuid() != null && !"".equals(clientIdCodeModel.getBasCodeDefUuid()))
						|| (clientSecretModel != null && clientSecretModel.getBasCodeDefUuid() != null && !"".equals(clientSecretModel.getBasCodeDefUuid())))
						&& webVo.getRowState().equals("Added")){
					throw new ApplicationException("该用户已注册Client!");
				}else{
					//save client code in DB
					if(webVo.getRowState().equals("Added")){
						if(clientIdCodeModel == null || clientIdCodeModel.getBasCodeDefUuid() == null || "".equals(clientIdCodeModel.getBasCodeDefUuid())){
							clientIdCodeModel.setDisplayValueEn(StringUtil.getRandomString(20));
							clientIdCodeModel.setStatus(webVo.getStatus());
							clientIdCodeModel = basCodeDefManager.save(clientIdCodeModel);
						}
						
						if(clientSecretModel == null || clientSecretModel.getBasCodeDefUuid() == null || "".equals(clientSecretModel.getBasCodeDefUuid())){
							clientSecretModel.setDisplayValueEn(StringUtil.getRandomString(20));
							clientSecretModel.setStatus(webVo.getStatus());
							clientSecretModel = basCodeDefManager.save(clientSecretModel);
						}
					}else if(webVo.getRowState().equals("ChangeSta")){
						clientIdCodeModel.setStatus(webVo.getStatus());
						clientIdCodeModel = basCodeDefManager.save(clientIdCodeModel);
						
						clientSecretModel.setStatus(webVo.getStatus());
						clientSecretModel = basCodeDefManager.save(clientSecretModel);
						
						BasCodeDefModel accessTokenModel = basCodeDefManager.getCodeDefModelByCodeTypeValue(CommonUtil.AUTH_ACCESS_TOKEN, webVo.getUsercode(),null);
						if(accessTokenModel != null && accessTokenModel.getBasCodeDefUuid() != null && !"".equals(accessTokenModel.getBasCodeDefUuid())){
							if(CommonUtil.Cancel.equals(webVo.getStatus())){
								accessTokenModel.setRowState("Deleted");
								basCodeDefManager.save(accessTokenModel);
							}else{
								accessTokenModel.setStatus(webVo.getStatus());
								basCodeDefManager.save(accessTokenModel);
							}
						}
						
						BasCodeDefModel expiredDateModel = basCodeDefManager.getCodeDefModelByCodeTypeValue(CommonUtil.AUTH_EXPIRED_DATE, webVo.getUsercode(),null);
						if(expiredDateModel != null && expiredDateModel.getBasCodeDefUuid() != null && !"".equals(expiredDateModel.getBasCodeDefUuid())){
							if(CommonUtil.Cancel.equals(webVo.getStatus())){
								expiredDateModel.setRowState("Deleted");
								basCodeDefManager.save(expiredDateModel);
							}else{
								expiredDateModel.setStatus(webVo.getStatus());
								basCodeDefManager.save(expiredDateModel);
							}
						}
					}else if(webVo.getRowState().equals("Reset")){
						clientIdCodeModel.setDisplayValueEn(StringUtil.getRandomString(20));
						clientIdCodeModel.setStatus(webVo.getStatus());
						clientIdCodeModel = basCodeDefManager.save(clientIdCodeModel);
						
						clientSecretModel.setDisplayValueEn(StringUtil.getRandomString(20));
						clientSecretModel.setStatus(webVo.getStatus());
						clientSecretModel = basCodeDefManager.save(clientSecretModel);
					}
					
					tokenEntity.setStatus(webVo.getStatus());
					tokenEntity.setUsercode(webVo.getUsercode());
					tokenEntity.setUsername(userModel.getUserName());
					List<SysOfficeModel> officeModels = sysOfficeManager.getOfficeUserList(userModel.getOfficeCode(), CommonUtil.Active);
					if(officeModels != null && officeModels.size()>0){
						tokenEntity.setCustomercode(officeModels.get(0).getCustomerCode());
					}
					
					tokenEntity.setClientid(clientIdCodeModel.getDisplayValueEn());
					tokenEntity.setClientsecret(clientSecretModel.getDisplayValueEn());
					resultEntity.add(tokenEntity);
				}
			}
		}
		
		return resultEntity;
	}

	@Override
	public void deleteClient(ExtApiTokenWebVo tokenWebVo) {
		BasCodeDefModel clientIdCodeModel = basCodeDefManager.getCodeDefModelByCodeTypeValue(CommonUtil.AUTH_CLIENT_ID, tokenWebVo.getUsercode(),null);
		if(clientIdCodeModel != null && clientIdCodeModel.getBasCodeDefUuid() != null && !"".equals(clientIdCodeModel.getBasCodeDefUuid())){
			clientIdCodeModel.setRowState("Deleted");
			basCodeDefManager.save(clientIdCodeModel);
		}
		
		BasCodeDefModel clientSecretModel = basCodeDefManager.getCodeDefModelByCodeTypeValue(CommonUtil.AUTH_CLIENT_SECRET, tokenWebVo.getUsercode(),null);
		if(clientSecretModel != null && clientSecretModel.getBasCodeDefUuid() != null && !"".equals(clientSecretModel.getBasCodeDefUuid())){
			clientSecretModel.setRowState("Deleted");
			basCodeDefManager.save(clientSecretModel);
		}
		
		BasCodeDefModel accessTokenModel = basCodeDefManager.getCodeDefModelByCodeTypeValue(CommonUtil.AUTH_ACCESS_TOKEN, tokenWebVo.getUsercode(),null);
		if(accessTokenModel != null && accessTokenModel.getBasCodeDefUuid() != null && !"".equals(accessTokenModel.getBasCodeDefUuid())){
			accessTokenModel.setRowState("Deleted");
			basCodeDefManager.save(accessTokenModel);
		}
		
		BasCodeDefModel expiredDateModel = basCodeDefManager.getCodeDefModelByCodeTypeValue(CommonUtil.AUTH_EXPIRED_DATE, tokenWebVo.getUsercode(),null);
		if(expiredDateModel != null && expiredDateModel.getBasCodeDefUuid() != null && !"".equals(expiredDateModel.getBasCodeDefUuid())){
			expiredDateModel.setRowState("Deleted");
			basCodeDefManager.save(expiredDateModel);
		}
	}
	
}
