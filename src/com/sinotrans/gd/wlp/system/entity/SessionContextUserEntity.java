package com.sinotrans.gd.wlp.system.entity;

import org.springframework.beans.factory.annotation.Autowired;

import com.sinotrans.framework.core.support.springsecurity.AcegiDefaultUserDetails;
import com.sinotrans.framework.core.support.springsecurity.SessionContext;
import com.sinotrans.framework.core.util.ContextUtils;
import com.sinotrans.gd.wlp.report.entity.PrintSessionEntity;
import com.sinotrans.gd.wlp.system.service.SysOfficeManager;
import com.sinotrans.gd.wlp.util.OfficeCodeTypeEnum;

@SuppressWarnings("serial")
public class SessionContextUserEntity extends AcegiDefaultUserDetails {

	private String officeCode;

	// 所属公司代码
	private String customerCode;
	//增加Email,以便进行单点登录
	private String email;
	//增加ticket,以便进行单点登录
	private String ticket;
	//增加授权码，用于访问统一身份验证平台的登陆验证
	private String loginAuthCode;
	//增加授权码，用于访问统一身份验证平台的用户管理
	private String userAuthCode;
	
	
	private String userType;
	
	//所属仓码
	private String portAreaCode;
	//可办理码头
	private String singleAreaCode;
	
	private  OfficeCodeTypeEnum codeType;
	
	@Autowired
	private SysOfficeManager sysOfficeManager;
	
	//打印模块session参数
	private PrintSessionEntity printSessionEntity; 
	
	public String getPortAreaCode() {
		return portAreaCode;
	}

	public void setPortAreaCode(String portAreaCode) {
		this.portAreaCode = portAreaCode;
	}

	public String getSingleAreaCode() {
		return singleAreaCode;
	}

	public void setSingleAreaCode(String singleAreaCode) {
		this.singleAreaCode = singleAreaCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	/**
	 * 取得当前登陆用户信息
	 * 
	 * @return
	 */
	public static SessionContextUserEntity currentUser() {
		return (SessionContextUserEntity) SessionContext.getUser();
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	

	public String getLoginAuthCode() {
		return loginAuthCode;
	}

	public void setLoginAuthCode(String loginAuthCode) {
		this.loginAuthCode = loginAuthCode;
	}

	public String getUserAuthCode() {
		return userAuthCode;
	}

	public void setUserAuthCode(String userAuthCode) {
		this.userAuthCode = userAuthCode;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public SessionContextUserEntity(){
		if(this.sysOfficeManager == null){
			try{
				SysOfficeManager bean = (SysOfficeManager)ContextUtils.getBeanOfType(SysOfficeManager.class);
		 		this.sysOfficeManager = bean;	
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	private void _beforeCheck(){
		if(this.getCodeType() == null){
			for(OfficeCodeTypeEnum codeType : OfficeCodeTypeEnum.values()){
				try {
					if( this.sysOfficeManager.isMatchOfficeCodeType(codeType) ) {
						this.setCodeType(codeType);
						break;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 是超级管理员
	 * @return
	 */
	public boolean isSuperAdmin(){
		
		if( this.sysOfficeManager ==null ){
			return false;
		}
		
		this._beforeCheck();
		if( OfficeCodeTypeEnum.DOCC.equals(this.getCodeType())
				||OfficeCodeTypeEnum.INTERNAL_PORT.equals( this.getCodeType()) ) {
			return true;
		
		}
		return false;
	}
	
	/**
	 * 是控箱公司
	 * @return
	 */
	public boolean isCntrAdmin(){
		this._beforeCheck();
		if( OfficeCodeTypeEnum.EXTN.equals(this.getCodeType() ) && "CACOMP".equals( this.getOfficeCode() ) ) {
			return true;
		}
		return false;
	}

	public OfficeCodeTypeEnum getCodeType() {
		return codeType;
	}

	public void setCodeType(OfficeCodeTypeEnum codeType) {
		this.codeType = codeType;
	}

	public PrintSessionEntity getPrintSessionEntity() {
		if(printSessionEntity==null){
			PrintSessionEntity printSessionEntity = new PrintSessionEntity();
			this.setPrintSessionEntity(printSessionEntity);
			return printSessionEntity;
		}else{
			return printSessionEntity;
		}
	}

	public void setPrintSessionEntity(PrintSessionEntity printSessionEntity) {
		this.printSessionEntity = printSessionEntity;
	}

}
