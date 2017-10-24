package com.sinotrans.gd.wlp.email.query;

import java.util.Date;

import com.sinotrans.framework.core.query.BaseQueryCondition;

public class ShippingDepartContQueryCondition extends BaseQueryCondition {

	private Date workDateFrom;
	private Date workDateTo;
	
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
	
}
