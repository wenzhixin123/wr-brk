package com.sinotrans.gd.wlp.system.query;


import com.sinotrans.framework.core.query.BaseQueryCondition;

public class SysJurisdictionQueryCondition extends BaseQueryCondition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8298812671193078787L;
	private String officeCode;
	private String userCode;
	private String btnCode;
	private String sysMenuItemUuid;
	private String sysMenuGroupUuid;
	private String[] btnCodeArr;
	private String sysMenuItemCode;
	private String buttonType;

	public String getSysMenuItemCode() {
		return sysMenuItemCode;
	}

	public void setSysMenuItemCode(String sysMenuItemCode) {
		this.sysMenuItemCode = sysMenuItemCode;
	}

	public String[] getBtnCodeArr() {
		return btnCodeArr;
	}

	public void setBtnCodeArr(String[] btnCodeArr) {
		this.btnCodeArr = btnCodeArr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getBtnCode() {
		return btnCode;
	}

	public void setBtnCode(String btnCode) {
		this.btnCode = btnCode;
	}

	public String getSysMenuItemUuid() {
		return sysMenuItemUuid;
	}

	public void setSysMenuItemUuid(String sysMenuItemUuid) {
		this.sysMenuItemUuid = sysMenuItemUuid;
	}

	public String getSysMenuGroupUuid() {
		return sysMenuGroupUuid;
	}

	public void setSysMenuGroupUuid(String sysMenuGroupUuid) {
		this.sysMenuGroupUuid = sysMenuGroupUuid;
	}

	public String getButtonType() {
		return buttonType;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}
	
	

}
