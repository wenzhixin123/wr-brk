package com.sinotrans.gd.wlp.system.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.support.servlet.Http;
import com.sinotrans.framework.core.support.springsecurity.AcegiUserDetails;
import com.sinotrans.framework.core.support.springsecurity.AcegiUserDetailsService;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.system.model.SysMenuItemModel;
import com.sinotrans.gd.wlp.system.model.SysUserModel;
import com.sinotrans.gd.wlp.system.query.GrantedButtonsQueryCondition;
import com.sinotrans.gd.wlp.system.query.GrantedButtonsQueryItem;
import com.sinotrans.gd.wlp.system.query.GrantedUrlsQueryCondition;
import com.sinotrans.gd.wlp.system.query.GrantedUrlsQueryItem;
import com.sinotrans.gd.wlp.system.service.LoginManager;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class LoginManagerImpl extends BaseManagerImpl implements LoginManager,
		AcegiUserDetailsService {


	
	@Resource(name="arapTicketSvc")
	private Http arapTicketSvc;	
	
	public AcegiUserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {		
		List<SysUserModel> users = this.dao.createCommonQuery(
				SysUserModel.class).addCondition(
				Condition.eq(SysUserModel.FieldNames.userCode, username
						.toUpperCase()))
				.addCondition(
						Condition.eq(SysUserModel.FieldNames.status,
								CommonUtil.Active)).query();
		if (users == null || users.size() == 0) {
			throw new UsernameNotFoundException(username);
		}
		SysUserModel sysUserModel = users.get(0);
		SessionContextUserEntity userDetails = new SessionContextUserEntity();
		userDetails.setUserId(username);
		userDetails.setUsername(sysUserModel.getUserName());
		userDetails.setPassword(sysUserModel.getPassword());
		userDetails.setFullname(sysUserModel.getUserName());
		userDetails.setCustomerCode(sysUserModel.getCustomerCode());// 所属公司
		userDetails.setOfficeCode(sysUserModel.getOfficeCode());
		userDetails.setEmail(sysUserModel.getEmail());// 用户email
		userDetails.setUserType(sysUserModel.getUserType());
		// 获取授权码
		String loginAuthCode = "";
		/*DcsParametersModel model = new DcsParametersModel();
		model.setParameterName("LOGIN_AUTH_CODE");
		List<DcsParametersModel> loginParalist = dcsParametersManager.findByExample(model);
		if (loginParalist.size() > 0) {
			loginAuthCode = loginParalist.get(0).getParameterValue();
		}*/
		userDetails.setLoginAuthCode(loginAuthCode);// 授权码
		
		String userAuthCode = "";
		/*model = new DcsParametersModel();
		model.setParameterName("USER_AUTH_CODE");*/
		/*List<DcsParametersModel> userParaList = dcsParametersManager.findByExample(model);
		if (userParaList.size() > 0) {
			userAuthCode = userParaList.get(0).getParameterValue();
		}*/
		userDetails.setUserAuthCode(userAuthCode);// 授权码
		
		//访问统一身份验证平台获取ticket
		//userDetails.setTicket(getSSOTicket(sysUserModel.getUserCode(),loginAuthCode));// 用户ticket

		// granted urls
		List<String> grantedUrls = new ArrayList<String>();
		GrantedUrlsQueryCondition grantedUrlsQueryCondition = new GrantedUrlsQueryCondition();
		grantedUrlsQueryCondition.setUserUuid(sysUserModel.getUserUuid());
		List<GrantedUrlsQueryItem> grantedUrlsQueryResult =  this.dao.query
				(grantedUrlsQueryCondition, GrantedUrlsQueryItem.class);
		//this.dao.createCommonQuery(grantedUrlsQueryCondition, GrantedUrlsQueryItem.class);
		/*List<SysMenuItemModel> grantedUrlsQueryResult = this.dao.createCommonQuery(
				SysMenuItemModel.class).addCondition(
				Condition.eq(SysMenuItemModel.FieldNames.status, CommonUtil.Active))
				.query();*/
		for (GrantedUrlsQueryItem grantedUrlsQueryItem : grantedUrlsQueryResult) {
			String pageurl = grantedUrlsQueryItem.getMenuItemUrl();
			if (!pageurl.startsWith("/")) {
				pageurl = "/" + pageurl;
			}
			pageurl = pageurl + "*";
			grantedUrls.add(pageurl);
			String actionurl = grantedUrlsQueryItem.getMenuItemAction();
			if (StringUtils.isNotBlank(actionurl)) {
				grantedUrls.addAll(Arrays.asList(StringUtils.split(actionurl,
						",;")));
			}
		}
		userDetails.setGrantedUrls(grantedUrls);

		return userDetails;
	}

	@Override
	public SysUserModel login(String userName, String password) {
		Md5PasswordEncoder md5 = ContextUtils
				.getBeanOfType(Md5PasswordEncoder.class);
		SysUserModel userModel = null;

		String newPassword = md5.encodePassword(password, null);
		List<SysUserModel> users = this.dao.createCommonQuery(
				SysUserModel.class).addCondition(
				Condition.eq(SysUserModel.FieldNames.userCode, userName))
				.addCondition(
						Condition.eq(SysUserModel.FieldNames.status,
								CommonUtil.Active)).addCondition(
						Condition.eq(SysUserModel.FieldNames.password,
								newPassword)).query();
		if (users == null || users.size() == 0) {
			throw new UsernameNotFoundException(userName);
		} else {
			userModel = users.get(0);
		}
		return userModel;
	}

	@Override
	public List<String> getGrantedButtonsForCurrentUser(String url) {
		GrantedButtonsQueryCondition condition = new GrantedButtonsQueryCondition();
		condition.setUrl(url);
		condition.setUserCode(SessionContextUserEntity.currentUser()
				.getUserId());
		List<GrantedButtonsQueryItem> queryResult = this.dao.query(condition,
				GrantedButtonsQueryItem.class);
		List<String> result = new ArrayList<String>();
		for (GrantedButtonsQueryItem grantedButtonsQueryItem : queryResult) {
			result.add(grantedButtonsQueryItem.getBtnCode());
		}
		return result;
	}

	@Override
	public String getSSOTicket(String userName,String authCode) {
		String resultStr="";
		HttpClient httpClient =new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(3000);
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("username", userName);
			jsonObject.put("auth_code", authCode);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String arapTicketUrl = this.arapTicketSvc.getServletUrl();
		PostMethod method =new PostMethod(arapTicketUrl);
		
		NameValuePair pair=new NameValuePair("data", jsonObject.toString());
		NameValuePair[] pairs={pair};
		log.info(pair.toString());
		method.setRequestBody(pairs);
	
		try {
		
			int statusCode =httpClient.executeMethod(method);
			if (statusCode !=HttpStatus.SC_OK) {
				log.info("--------------------用户"+userName+"SSO认证失败");
				return null;
			}
			
			
			InputStream inputStream = method.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
			StringBuffer stringBuffer = new StringBuffer();
			while ((resultStr = br.readLine()) != null) {
				stringBuffer.append(resultStr);
			}
			resultStr = stringBuffer.toString();
			
			log.info("--------------------用户"+userName+"SSO认证成功，Ticket："+resultStr);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return resultStr;
	}
}
