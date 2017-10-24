package com.sinotrans.gd.wlp.scanner.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class OutboundLtQueryCondition extends BaseQueryCondition {

	
	private String logisticsOrderNo;
	private String barcode;
	private String officeCode;

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getLogisticsOrderNo() {
		return logisticsOrderNo;
	}

	public void setLogisticsOrderNo(String logisticsOrderNo) {
		this.logisticsOrderNo = logisticsOrderNo;
	}

}
