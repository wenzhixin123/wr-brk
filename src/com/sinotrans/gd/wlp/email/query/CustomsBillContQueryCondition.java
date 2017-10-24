package com.sinotrans.gd.wlp.email.query;

import java.util.Date;

import com.sinotrans.framework.core.query.BaseQueryCondition;

public class CustomsBillContQueryCondition extends BaseQueryCondition {

	private Date workDateFrom;
	private Date workDateTo;
	private String carriageCode;
	public Date getWorkDateFrom() {
		return workDateFrom;
	}
	public void setWorkDateFrom(Date workDateFrom) {
		this.workDateFrom = workDateFrom;
	}
	public Date getWorkDateTo() {
		return workDateTo;
	}
	public void setWorkDateTo(Date workDateTo) {
		this.workDateTo = workDateTo;
	}
	public String getCarriageCode() {
		return carriageCode;
	}
	public void setCarriageCode(String carriageCode) {
		this.carriageCode = carriageCode;
	}

	

}
