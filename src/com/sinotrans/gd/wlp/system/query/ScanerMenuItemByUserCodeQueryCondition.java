package com.sinotrans.gd.wlp.system.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class ScanerMenuItemByUserCodeQueryCondition extends BaseQueryCondition {

	private String userCode;
	private String officeCode;
	private String menuGrpName;
	private String status;

	public ScanerMenuItemByUserCodeQueryCondition(String userCode,
			String officeCode, String menuGrpName, String status) {
		super();
		this.userCode = userCode;
		this.officeCode = officeCode;
		this.menuGrpName = menuGrpName;
		this.status = status;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getMenuGrpName() {
		return menuGrpName;
	}

	public void setMenuGrpName(String menuGrpName) {
		this.menuGrpName = menuGrpName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
