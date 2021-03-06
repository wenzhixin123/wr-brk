package com.sinotrans.gd.wlp.statistics.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class CommonGetRemainSinworkQueryCondition extends BaseQueryCondition {

	private String officeCode;
	private String[] submitOrderDetailUuid;

	public CommonGetRemainSinworkQueryCondition( String officeCode, String[] submitOrderDetailUuid) {
		super();
		this.officeCode = officeCode;
		this.submitOrderDetailUuid = submitOrderDetailUuid;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String[] getSubmitOrderDetailUuid() {
		return submitOrderDetailUuid;
	}

	public void setSubmitOrderDetailUuid(String[] submitOrderDetailUuid) {
		this.submitOrderDetailUuid = submitOrderDetailUuid;
	}

}
