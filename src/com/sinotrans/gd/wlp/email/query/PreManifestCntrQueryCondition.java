package com.sinotrans.gd.wlp.email.query;

import java.util.Date;

import com.sinotrans.framework.core.query.BaseQueryCondition;

public class PreManifestCntrQueryCondition extends BaseQueryCondition {

	private String carriageCode;
	private Date manifestDateFrom;
	private Date manifestDateTo;

	public String getCarriageCode() {
		return carriageCode;
	}

	public void setCarriageCode(String carriageCode) {
		this.carriageCode = carriageCode;
	}

	public Date getManifestDateFrom() {
		return manifestDateFrom;
	}

	public void setManifestDateFrom(Date manifestDateFrom) {
		this.manifestDateFrom = manifestDateFrom;
	}

	public Date getManifestDateTo() {
		return manifestDateTo;
	}

	public void setManifestDateTo(Date manifestDateTo) {
		this.manifestDateTo = manifestDateTo;
	}

}
