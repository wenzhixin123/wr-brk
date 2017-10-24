package com.sinotrans.gd.wlp.scanner.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class KgCpReceQueryCondition extends BaseQueryCondition {

	public KgCpReceQueryCondition(String orderNo, String officeCode) {
		super();
		this.orderNo = orderNo;
		this.officeCode = officeCode;
	}

	private String orderNo;
	private String officeCode;


	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


}
