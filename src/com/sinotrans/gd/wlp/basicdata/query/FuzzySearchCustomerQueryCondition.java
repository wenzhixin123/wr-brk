package com.sinotrans.gd.wlp.basicdata.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class FuzzySearchCustomerQueryCondition extends BaseQueryCondition {

	private String key;

	private String customerCode;

	private String OfficeCode;


	public FuzzySearchCustomerQueryCondition() {
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getOfficeCode() {
		return OfficeCode;
	}

	public void setOfficeCode(String officeCode) {
		OfficeCode = officeCode;
	}

	

}
