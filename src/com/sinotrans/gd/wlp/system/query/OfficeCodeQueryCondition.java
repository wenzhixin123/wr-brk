package com.sinotrans.gd.wlp.system.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class OfficeCodeQueryCondition extends BaseQueryCondition {

	private String officeCode;

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

}
