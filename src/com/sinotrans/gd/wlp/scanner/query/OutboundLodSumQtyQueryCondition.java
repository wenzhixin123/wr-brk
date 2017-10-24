package com.sinotrans.gd.wlp.scanner.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class OutboundLodSumQtyQueryCondition extends BaseQueryCondition {

	public OutboundLodSumQtyQueryCondition(String logisticsOrderNo,
			String officeCode) {
		super();
		this.logisticsOrderNo = logisticsOrderNo;
		this.officeCode = officeCode;
	}

	private String logisticsOrderNo;
	private String officeCode;

	public String getLogisticsOrderNo() {
		return logisticsOrderNo;
	}

	public void setLogisticsOrderNo(String logisticsOrderNo) {
		this.logisticsOrderNo = logisticsOrderNo;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

}
