package com.sinotrans.gd.wlp.common.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class YclOutboundLtQueryCondition extends BaseQueryCondition {

	public YclOutboundLtQueryCondition() {
		super();
	}

	public YclOutboundLtQueryCondition(String logisticsOrderNo, String barcode,
			String transactionType, String status, String taskType,
			String cancelType, String officeCode) {
		super();
		this.logisticsOrderNo = logisticsOrderNo;
		this.barcode = barcode;
		this.transactionType = transactionType;
		this.status = status;
		this.taskType = taskType;
		this.cancelType = cancelType;
		this.officeCode = officeCode;
	}

	private String logisticsOrderNo;
	private String barcode;
	private String transactionType;
	private String status;
	private String taskType;
	private String cancelType;
	private String panelNo;
	private String officeCode;
	private String barcodeOrPanelNo;
	private String barcodeOrPackageNo;
	
	public String getBarcodeOrPackageNo() {
		return barcodeOrPackageNo;
	}

	public void setBarcodeOrPackageNo(String barcodeOrPackageNo) {
		this.barcodeOrPackageNo = barcodeOrPackageNo;
	}

	public String getBarcodeOrPanelNo() {
		return barcodeOrPanelNo;
	}

	public void setBarcodeOrPanelNo(String barcodeOrPanelNo) {
		this.barcodeOrPanelNo = barcodeOrPanelNo;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getCancelType() {
		return cancelType;
	}

	public void setCancelType(String cancelType) {
		this.cancelType = cancelType;
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

	public String getPanelNo() {
		return panelNo;
	}

	public void setPanelNo(String panelNo) {
		this.panelNo = panelNo;
	}

}
