package com.sinotrans.gd.wlp.basicdata.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class BarPalletQueryCondition extends BaseQueryCondition {

	public BarPalletQueryCondition(String palletNo, String officeCode) {
		super();
		this.palletNo = palletNo;
		this.officeCode = officeCode;
	}

	private String palletNo;
	private String officeCode;

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getPalletNo() {
		return palletNo;
	}

	public void setPalletNo(String palletNo) {
		this.palletNo = palletNo;
	}

}
