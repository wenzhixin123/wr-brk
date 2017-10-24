package com.sinotrans.gd.wlp.email.entity;

import java.util.Date;

public class SendEmailDataEntity {
	private String vesselName;
	private String voyageNo;
	private String containerNo;
	private String billLadingNo;
	private String shippingOrderNo;
	private String customsLicenseNo;
	private String message;
	private Date workDate;
	private String operationType;
	public String getVesselName() {
		return vesselName;
	}
	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}
	public String getVoyageNo() {
		return voyageNo;
	}
	public void setVoyageNo(String voyageNo) {
		this.voyageNo = voyageNo;
	}
	public String getContainerNo() {
		return containerNo;
	}
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}
	public String getBillLadingNo() {
		return billLadingNo;
	}
	public void setBillLadingNo(String billLadingNo) {
		this.billLadingNo = billLadingNo;
	}
	public String getShippingOrderNo() {
		return shippingOrderNo;
	}
	public void setShippingOrderNo(String shippingOrderNo) {
		this.shippingOrderNo = shippingOrderNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getWorkDate() {
		return workDate;
	}
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getCustomsLicenseNo() {
		return customsLicenseNo;
	}
	public void setCustomsLicenseNo(String customsLicenseNo) {
		this.customsLicenseNo = customsLicenseNo;
	}
	
}
