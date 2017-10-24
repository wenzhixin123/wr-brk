package com.sinotrans.gd.wlp.basicdata.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class BasLotStockQueryCondition extends BaseQueryCondition {

	private String basLocAreaUuid;
	private String basBasLocTypeUuid;
	private String lotCode;
	private String lotName;
	private String pri;
	private String status;
	private String officeCode;

	public String getBasLocAreaUuid() {
		return basLocAreaUuid;
	}

	public void setBasLocAreaUuid(String basLocAreaUuid) {
		this.basLocAreaUuid = basLocAreaUuid;
	}

	public String getBasBasLocTypeUuid() {
		return basBasLocTypeUuid;
	}

	public void setBasBasLocTypeUuid(String basBasLocTypeUuid) {
		this.basBasLocTypeUuid = basBasLocTypeUuid;
	}

	public String getLotCode() {
		return lotCode;
	}

	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
	}

	public String getLotName() {
		return lotName;
	}

	public void setLotName(String lotName) {
		this.lotName = lotName;
	}

	public String getPri() {
		return pri;
	}

	public void setPri(String pri) {
		this.pri = pri;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

}
