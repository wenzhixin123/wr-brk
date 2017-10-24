package com.sinotrans.gd.wlp.common.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class YclCheckBarcodeQueryCondition extends BaseQueryCondition {

	public YclCheckBarcodeQueryCondition() {
	}

	public YclCheckBarcodeQueryCondition(String orderNo, String barcode,
			String transactionStatus, String transactionType,
			String locTaskType, String officeCode) {
		this.orderNo = orderNo;
		this.barcode = barcode;
		this.transactionStatus = transactionStatus;
		this.transactionType = transactionType;
		this.locTaskType = locTaskType;
		this.officeCode = officeCode;
	}

	private String locTaskType;
	private String barcode;
	private String officeCode;
	private String transactionStatus;
	private String transactionType;
	private String orderNo;
	private String batchNo;
	
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getLocTaskType() {
		return locTaskType;
	}

	public void setLocTaskType(String locTaskType) {
		this.locTaskType = locTaskType;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
