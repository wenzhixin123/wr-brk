package com.sinotrans.gd.wlp.email.query;

import java.util.Date;

import com.sinotrans.framework.core.query.BaseQueryCondition;

public class NewManifestBillQueryCondition extends BaseQueryCondition {

	private String carriageCode;
	private Date sendDateFrom;
	private Date sendDateTo;

	public String getCarriageCode() {
		return carriageCode;
	}

	public void setCarriageCode(String carriageCode) {
		this.carriageCode = carriageCode;
	}

	public Date getSendDateFrom() {
		return sendDateFrom;
	}

	public void setSendDateFrom(Date sendDateFrom) {
		this.sendDateFrom = sendDateFrom;
	}

	public Date getSendDateTo() {
		return sendDateTo;
	}

	public void setSendDateTo(Date sendDateTo) {
		this.sendDateTo = sendDateTo;
	}

}
