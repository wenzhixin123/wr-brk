package com.sinotrans.gd.wlp.system.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class GetMenuItemByGroupUuidQueryCondition extends BaseQueryCondition {

	private String sysMenuGroupUuid;
	private String officeCode;
	private String userCode;
	private String status;

	public GetMenuItemByGroupUuidQueryCondition(String sysMenuGroupUuid,
			String officeCode, String userCode, String status) {
		super();
		this.sysMenuGroupUuid = sysMenuGroupUuid;
		this.officeCode = officeCode;
		this.userCode = userCode;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSysMenuGroupUuid() {
		return sysMenuGroupUuid;
	}

	public void setSysMenuGroupUuid(String sysMenuGroupUuid) {
		this.sysMenuGroupUuid = sysMenuGroupUuid;
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
