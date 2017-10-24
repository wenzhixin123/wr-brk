package com.sinotrans.gd.wlp.common.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class CommonLodRsLtSwQueryCondition extends BaseQueryCondition {

	private String officeCode;
	private String transactionType;
	private String logisticsOrderNo;
	private String barcode;
	private String barcodeOrPackageNo;
	private String lodUuid;
	private String inLodUuid;
	private String locTaskType;
	private String notTaskType;
	private String lodUuids;

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getLogisticsOrderNo() {
		return logisticsOrderNo;
	}

	public void setLogisticsOrderNo(String logisticsOrderNo) {
		this.logisticsOrderNo = logisticsOrderNo;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBarcodeOrPackageNo() {
		return barcodeOrPackageNo;
	}

	public void setBarcodeOrPackageNo(String barcodeOrPackageNo) {
		this.barcodeOrPackageNo = barcodeOrPackageNo;
	}

	public String getLodUuid() {
		return lodUuid;
	}

	public void setLodUuid(String lodUuid) {
		this.lodUuid = lodUuid;
	}

	public String getInLodUuid() {
		return inLodUuid;
	}

	public void setInLodUuid(String inLodUuid) {
		this.inLodUuid = inLodUuid;
	}

	public String getLocTaskType() {
		return locTaskType;
	}

	public void setLocTaskType(String locTaskType) {
		this.locTaskType = locTaskType;
	}

	public String getNotTaskType() {
		return notTaskType;
	}

	public void setNotTaskType(String notTaskType) {
		this.notTaskType = notTaskType;
	}

	public String getLodUuids() {
		return lodUuids;
	}

	public void setLodUuids(String lodUuids) {
		this.lodUuids = lodUuids;
	}

}
