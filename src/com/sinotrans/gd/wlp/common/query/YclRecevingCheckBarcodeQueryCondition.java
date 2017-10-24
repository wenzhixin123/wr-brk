package com.sinotrans.gd.wlp.common.query;

import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
@Entity
public class YclRecevingCheckBarcodeQueryCondition extends BaseQueryCondition {

	public YclRecevingCheckBarcodeQueryCondition() {
	}

	public YclRecevingCheckBarcodeQueryCondition(String orderNo,
			String barcode, String locTaskTyp, String officeCode) {
		this.officeCode = officeCode;
		this.locTaskType = locTaskTyp;
		this.orderNo = orderNo;
		this.barcode = barcode;
	}

	private String officeCode;
	private String locTaskType;
	private String locTaskTypes;
	private String orderNo;
	private String barcode;
	private String lodStatus;
	private String batchNo;
	private String startBarcode;
	private String endBarcode;
	private String marksNumber;
	
	public String getMarksNumber() {
		return marksNumber;
	}

	public void setMarksNumber(String marksNumber) {
		this.marksNumber = marksNumber;
	}

	public String getStartBarcode() {
		return startBarcode;
	}

	public void setStartBarcode(String startBarcode) {
		this.startBarcode = startBarcode;
	}

	public String getEndBarcode() {
		return endBarcode;
	}

	public void setEndBarcode(String endBarcode) {
		this.endBarcode = endBarcode;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getLocTaskTypes() {
		return locTaskTypes;
	}

	public void setLocTaskTypes(String locTaskTypes) {
		this.locTaskTypes = locTaskTypes;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getLocTaskType() {
		return locTaskType;
	}

	public void setLocTaskType(String locTaskType) {
		this.locTaskType = locTaskType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getLodStatus() {
		return lodStatus;
	}

	public void setLodStatus(String lodStatus) {
		this.lodStatus = lodStatus;
	}

}
