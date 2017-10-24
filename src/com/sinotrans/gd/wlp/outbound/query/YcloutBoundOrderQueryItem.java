package com.sinotrans.gd.wlp.outbound.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@Entity
public class YcloutBoundOrderQueryItem extends BaseQueryItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5412765576391402600L;
	private String transactionStatus;
	private String submitOrderUuid;
	private String submitOrderNo;
	private String orderNo;
	private Date submitDate;
	private String flow;
	private String deliveryType;
	private String remark;
	private String creator;
	private String telNo;
	private String aux1;
	private String aux2;
	private String aux3;
	private String workDemand;
	private String chargeDesc;
	private String transactionType;
	private Double sumqty;
	private String cargoConsigneeCode;
	private String cargoConsigneeDesc;
	private Double orderQty;
	private String configName;
	private Date deliveryDate;
	private String unloadPort;
	
	
	@Column(name="UNLOAD_PORT")
	public String getUnloadPort() {
		return unloadPort;
	}

	public void setUnloadPort(String unloadPort) {
		this.unloadPort = unloadPort;
		addValidField("unloadPort");
	}

	@Column(name="delivery_date")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
		addValidField("deliveryDate");
	}


	@Column(name = "order_Qty")
	public Double getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Double orderQty) {
		this.orderQty = orderQty;		
		addValidField("orderQty");
	}

	@Column(name = "config_Name")
	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
		addValidField("configName");
	}

	@Column(name = "TRANSACTION_STATUS")
	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
		addValidField("transactionStatus");
	}

	@Column(name = "SUBMIT_ORDER_UUID")
	public String getSubmitOrderUuid() {
		return submitOrderUuid;
	}

	public void setSubmitOrderUuid(String submitOrderUuid) {
		this.submitOrderUuid = submitOrderUuid;
		addValidField("submitOrderUuid");
	}

	@Column(name = "SUBMIT_ORDER_NO")
	public String getSubmitOrderNo() {
		return submitOrderNo;
	}

	public void setSubmitOrderNo(String submitOrderNo) {
		this.submitOrderNo = submitOrderNo;
		addValidField("submitOrderNo");
	}

	@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
		addValidField("orderNo");
	}

	@Column(name = "SUBMIT_DATE")
	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
		addValidField("submitDate");
	}

	@Column(name = "FLOW")
	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
		addValidField("flow");
	}

	@Column(name = "DELIVERY_TYPE")
	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
		addValidField("deliveryType");
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		addValidField("remark");
	}

	@Column(name = "CREATOR")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
		addValidField("creator");
	}

	@Column(name = "TEL_NO")
	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
		addValidField("telNo");
	}

	@Column(name = "AUX1")
	public String getAux1() {
		return aux1;
	}

	public void setAux1(String aux1) {
		this.aux1 = aux1;
		addValidField("aux1");
	}

	@Column(name = "AUX2")
	public String getAux2() {
		return aux2;
	}

	public void setAux2(String aux2) {
		this.aux2 = aux2;
		addValidField("aux2");
	}

	@Column(name = "AUX3")
	public String getAux3() {
		return aux3;
	}

	public void setAux3(String aux3) {
		this.aux3 = aux3;
		addValidField("aux3");
	}

	@Column(name = "WORK_DEMAND")
	public String getWorkDemand() {
		return workDemand;
	}

	public void setWorkDemand(String workDemand) {
		this.workDemand = workDemand;
		addValidField("workDemand");
	}


	@Column(name = "CHARGE_DESC")
	public String getChargeDesc() {
		return chargeDesc;
	}

	public void setChargeDesc(String chargeDesc) {
		this.chargeDesc = chargeDesc;
		addValidField("chargeDesc");
	}

	@Column(name = "TRANSACTION_TYPE")
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
		addValidField("transactionType");
	}

	@Column(name = "SUMQTY")
	public Double getSumqty() {
		return sumqty;
	}

	public void setSumqty(Double sumqty) {
		this.sumqty = sumqty;
		addValidField("sumqty");
	}

	@Column(name = "CARGO_CONSIGNEE_CODE")
	public String getCargoConsigneeCode() {
		return cargoConsigneeCode;
	}

	public void setCargoConsigneeCode(String cargoConsigneeCode) {
		this.cargoConsigneeCode = cargoConsigneeCode;
		addValidField("cargoConsigneeCode");
	}

	@Column(name = "CARGO_CONSIGNEE_DESC")
	public String getCargoConsigneeDesc() {
		return cargoConsigneeDesc;
	}
	
	
	public void setCargoConsigneeDesc(String cargoConsigneeDesc) {
		this.cargoConsigneeDesc = cargoConsigneeDesc;
		addValidField("cargoConsigneeDesc");
	}

}
