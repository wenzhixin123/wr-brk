package com.sinotrans.gd.wlp.outbound.query;

import java.util.Date;

import com.sinotrans.framework.core.query.BaseQueryCondition;

public class YcloutBoundOrderQueryCondition extends BaseQueryCondition {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6048103920969637863L;
	private String officeCode;
	private String submitOrderNo;
	private String[] orderNo;
	private String spec;
	private String billNo;
	private String projectCode;
	private String itemCode;
	private String cargoConsigneeDesc;
	private String deliveryType;
	private Date dateBegin;
	private Date dateEnd;
	private String transactionStatus;
	private String daBatchNo;
	private String orderNoLike;
	private String kitting;
	private Date deliveryDateBegin;
	private Date deliveryDateEnd;
	private String workDemandTypeCode;
	
	
	
	public String getWorkDemandTypeCode() {
		return workDemandTypeCode;
	}

	public void setWorkDemandTypeCode(String workDemandTypeCode) {
		this.workDemandTypeCode = workDemandTypeCode;
	}

	public Date getDeliveryDateBegin() {
		return deliveryDateBegin;
	}

	public void setDeliveryDateBegin(Date deliveryDateBegin) {
		this.deliveryDateBegin = deliveryDateBegin;
	}

	public Date getDeliveryDateEnd() {
		return deliveryDateEnd;
	}

	public void setDeliveryDateEnd(Date deliveryDateEnd) {
		this.deliveryDateEnd = deliveryDateEnd;
	}

	public String getKitting() {
		return kitting;
	}

	public void setKitting(String kitting) {
		this.kitting = kitting;
	}

	public String getDaBatchNo() {
		return daBatchNo;
	}

	public void setDaBatchNo(String daBatchNo) {
		this.daBatchNo = daBatchNo;
	}

	public String getOrderNoLike() {
		return orderNoLike;
	}

	public void setOrderNoLike(String orderNoLike) {
		this.orderNoLike = orderNoLike;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getSubmitOrderNo() {
		return submitOrderNo;
	}

	public void setSubmitOrderNo(String submitOrderNo) {
		this.submitOrderNo = submitOrderNo;
	}

	public String[] getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String[] orderNo) {
		this.orderNo = orderNo;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getCargoConsigneeDesc() {
		return cargoConsigneeDesc;
	}

	public void setCargoConsigneeDesc(String cargoConsigneeDesc) {
		this.cargoConsigneeDesc = cargoConsigneeDesc;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

}
