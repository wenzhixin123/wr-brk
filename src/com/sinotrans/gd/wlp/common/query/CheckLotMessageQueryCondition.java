package com.sinotrans.gd.wlp.common.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class CheckLotMessageQueryCondition extends BaseQueryCondition {

	private String officeCode;
	private String[] lots;

	public CheckLotMessageQueryCondition(String officeCode, String[] lots) {
		super();
		this.officeCode = officeCode;
		this.lots = lots;
	}


	public String getOfficeCode() {
		return officeCode;
	}


	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}


	public String[] getLots() {
		return lots;
	}

	public void setLots(String[] lots) {
		this.lots = lots;
	}

}
