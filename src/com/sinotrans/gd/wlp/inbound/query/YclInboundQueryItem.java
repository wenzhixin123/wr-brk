package com.sinotrans.gd.wlp.inbound.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@Entity
public class YclInboundQueryItem extends BaseQueryItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5553937733301937438L;
	private String submitOrderUuid;
	private String transactionStatus;
	private String submitOrderNo;
	private String orderNo;
	private String contractNo;
	private String agentConsigneeCode;
	private String agentConsigneeDesc;
	private String deliveryType;
	private Date deliveryDate;
	private Date submitDate;
	private String unloadPort;
	private String flow;
	private String remark;
	private String configCode;
	private String cargoConsigneeCode;
	private String cargoConsigneeDesc;
	private String projectCode;
	private String transactionType;
	private String officeCode;
	private Long recVer;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date modifyTime;
	private String controlWord;
	private String aux3;
	private String telNo;
	private String functionary;
	private String aux1;
	private String aux2;
	private String billNo;
	private Integer qty;
	private String itemCode;

	@Column(name = "SUBMIT_ORDER_UUID")
	public String getSubmitOrderUuid() {
		return submitOrderUuid;
	}

	public void setSubmitOrderUuid(String submitOrderUuid) {
		this.submitOrderUuid = submitOrderUuid;
		addValidField("submitOrderUuid");
	}

	@Column(name = "TRANSACTION_STATUS")
	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
		addValidField("transactionStatus");
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

	@Column(name = "CONTRACT_NO")
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
		addValidField("contractNo");
	}

	@Column(name = "AGENT_CONSIGNEE_CODE")
	public String getAgentConsigneeCode() {
		return agentConsigneeCode;
	}

	public void setAgentConsigneeCode(String agentConsigneeCode) {
		this.agentConsigneeCode = agentConsigneeCode;
		addValidField("agentConsigneeCode");
	}

	@Column(name = "AGENT_CONSIGNEE_DESC")
	public String getAgentConsigneeDesc() {
		return agentConsigneeDesc;
	}

	public void setAgentConsigneeDesc(String agentConsigneeDesc) {
		this.agentConsigneeDesc = agentConsigneeDesc;
		addValidField("agentConsigneeDesc");
	}

	@Column(name = "DELIVERY_TYPE")
	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
		addValidField("deliveryType");
	}

	@Column(name = "DELIVERY_DATE")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
		addValidField("deliveryDate");
	}

	@Column(name = "SUBMIT_DATE")
	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
		addValidField("submitDate");
	}

	@Column(name = "UNLOAD_PORT")
	public String getUnloadPort() {
		return unloadPort;
	}

	public void setUnloadPort(String unloadPort) {
		this.unloadPort = unloadPort;
		addValidField("unloadPort");
	}

	@Column(name = "FLOW")
	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
		addValidField("flow");
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		addValidField("remark");
	}

	@Column(name = "CONFIG_CODE")
	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
		addValidField("configCode");
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

	@Column(name = "PROJECT_CODE")
	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
		addValidField("projectCode");
	}

	@Column(name = "TRANSACTION_TYPE")
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
		addValidField("transactionType");
	}

	@Column(name = "OFFICE_CODE")
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
		addValidField("officeCode");
	}

	@Column(name = "REC_VER")
	public Long getRecVer() {
		return recVer;
	}

	public void setRecVer(Long recVer) {
		this.recVer = recVer;
		addValidField("recVer");
	}

	@Column(name = "CREATOR")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
		addValidField("creator");
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		addValidField("createTime");
	}

	@Column(name = "MODIFIER")
	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
		addValidField("modifier");
	}

	@Column(name = "MODIFY_TIME")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
		addValidField("modifyTime");
	}

	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField("controlWord");
	}

	@Column(name = "AUX3")
	public String getAux3() {
		return aux3;
	}

	public void setAux3(String aux3) {
		this.aux3 = aux3;
		addValidField("aux3");
	}

	@Column(name = "TEL_NO")
	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
		addValidField("telNo");
	}

	@Column(name = "FUNCTIONARY")
	public String getFunctionary() {
		return functionary;
	}

	public void setFunctionary(String functionary) {
		this.functionary = functionary;
		addValidField("functionary");
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

	@Column(name = "BILL_NO")
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
		addValidField("billNo");
	}

	@Column(name = "QTY")
	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
		addValidField("qty");
	}

	@Column(name = "ITEM_CODE")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
		addValidField("itemCode");
	}

}
