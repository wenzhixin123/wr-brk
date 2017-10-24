package com.sinotrans.gd.wlp.common.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class BarMenuPermissionQueryCondition extends BaseQueryCondition {

	public BarMenuPermissionQueryCondition(String menuName, String status , String officeCode, String userCode){
		this.menuName = menuName;
		this.status = status;
		this.officeCode = officeCode;
		this.userCode = userCode;
	}
	
	private String menuName;
	private String status;
	private String officeCode;
	private String userCode;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}
