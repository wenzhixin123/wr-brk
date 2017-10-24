package com.sinotrans.gd.wlp.system.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

public class ExtApiTokenQueryCondition extends BaseQueryCondition {

	private String userCode;
	private String customerCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

}
