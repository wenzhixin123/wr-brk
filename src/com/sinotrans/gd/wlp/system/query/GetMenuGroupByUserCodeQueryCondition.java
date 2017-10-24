package com.sinotrans.gd.wlp.system.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class GetMenuGroupByUserCodeQueryCondition extends BaseQueryCondition {

	private String status;
	private String officeCode;
	private String userCode;

	public GetMenuGroupByUserCodeQueryCondition() {
		super();
	}

	public GetMenuGroupByUserCodeQueryCondition(String status,
			String officeCode, String userCode) {
		super();
		this.status = status;
		this.officeCode = officeCode;
		this.userCode = userCode;
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
