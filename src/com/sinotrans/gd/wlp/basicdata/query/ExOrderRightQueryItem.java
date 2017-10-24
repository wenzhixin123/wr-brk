package com.sinotrans.gd.wlp.basicdata.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@Entity
public class ExOrderRightQueryItem extends BaseQueryItem {

	private String logisticsOrderId;
	private String masterOrderId;
	private String portAreaCode;
	private String logisticsOrderNo;
	private String submitOrderUuid;
	private String agentConsigneeCode;
	private String cargoConsigneeCode;
	private String cargoConsigneeDesc;
	private String cargoControlCode;
	private String verifyCode;
	private Date logisticsOrderDate;
	private String orderNo;
	private String projectCode;
	private String contractNo;
	private String payerCode;
	private String fromSource;
	private String toTarget;
	private String orderDocumentNo;
	private String customerRefNo;
	private String deliveryType;
	private String truckFleetCode;
	private String pickupDepot;
	private String pickupCntrStatus;
	private String returnDepot;
	private String returnCntrStatus;
	private String cntrAdminCode;
	private String transactionType;
	private String transactionStatus;
	private String configCode;
	private String logisticsControlWord;
	private String shippingOrderNo;
	private String payType;
	private String deliveryDesc;
	private String remarks;
	private String portTranshipCode;
	private String portTranshipDesc;
	private String vesselCode;
	private String voyageNo;
	private String oceanVesselName;
	private String oceanVesselVoyage;
	private Date planExportDate;
	private String workSpecial;
	private String tractorNo;
	private String transactor;
	private String officeCode;
	private Long recVer;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date modifyTime;
	private String releasedId;
	private String returnAdminCode;
	private Date logisticsFeedinDate;
	private Date logisticsDeliveryDate;
	private String transactorTelNo;
	private String agentConsigneeDesc;
	private String agentConsigneeName;
	private String cntrAdminName;
	

	@Column(name = "LOGISTICS_ORDER_ID")
	public String getLogisticsOrderId() {
		return logisticsOrderId;
	}

	public void setLogisticsOrderId(String logisticsOrderId) {
		this.logisticsOrderId = logisticsOrderId;
		addValidField("logisticsOrderId");
	}

	@Column(name = "MASTER_ORDER_ID")
	public String getMasterOrderId() {
		return masterOrderId;
	}

	public void setMasterOrderId(String masterOrderId) {
		this.masterOrderId = masterOrderId;
		addValidField("masterOrderId");
	}

	@Column(name = "PORT_AREA_CODE")
	public String getPortAreaCode() {
		return portAreaCode;
	}

	public void setPortAreaCode(String portAreaCode) {
		this.portAreaCode = portAreaCode;
		addValidField("portAreaCode");
	}

	@Column(name = "LOGISTICS_ORDER_NO")
	public String getLogisticsOrderNo() {
		return logisticsOrderNo;
	}

	public void setLogisticsOrderNo(String logisticsOrderNo) {
		this.logisticsOrderNo = logisticsOrderNo;
		addValidField("logisticsOrderNo");
	}

	@Column(name = "SUBMIT_ORDER_UUID")
	public String getSubmitOrderUuid() {
		return submitOrderUuid;
	}

	public void setSubmitOrderUuid(String submitOrderUuid) {
		this.submitOrderUuid = submitOrderUuid;
		addValidField("submitOrderUuid");
	}

	@Column(name = "AGENT_CONSIGNEE_CODE")
	public String getAgentConsigneeCode() {
		return agentConsigneeCode;
	}

	public void setAgentConsigneeCode(String agentConsigneeCode) {
		this.agentConsigneeCode = agentConsigneeCode;
		addValidField("agentConsigneeCode");
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

	@Column(name = "CARGO_CONTROL_CODE")
	public String getCargoControlCode() {
		return cargoControlCode;
	}

	public void setCargoControlCode(String cargoControlCode) {
		this.cargoControlCode = cargoControlCode;
		addValidField("cargoControlCode");
	}

	@Column(name = "VERIFY_CODE")
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
		addValidField("verifyCode");
	}

	@Column(name = "LOGISTICS_ORDER_DATE")
	public Date getLogisticsOrderDate() {
		return logisticsOrderDate;
	}

	public void setLogisticsOrderDate(Date logisticsOrderDate) {
		this.logisticsOrderDate = logisticsOrderDate;
		addValidField("logisticsOrderDate");
	}

	@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
		addValidField("orderNo");
	}

	@Column(name = "PROJECT_CODE")
	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
		addValidField("projectCode");
	}

	@Column(name = "CONTRACT_NO")
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
		addValidField("contractNo");
	}

	@Column(name = "PAYER_CODE")
	public String getPayerCode() {
		return payerCode;
	}

	public void setPayerCode(String payerCode) {
		this.payerCode = payerCode;
		addValidField("payerCode");
	}

	@Column(name = "FROM_SOURCE")
	public String getFromSource() {
		return fromSource;
	}

	public void setFromSource(String fromSource) {
		this.fromSource = fromSource;
		addValidField("fromSource");
	}

	@Column(name = "TO_TARGET")
	public String getToTarget() {
		return toTarget;
	}

	public void setToTarget(String toTarget) {
		this.toTarget = toTarget;
		addValidField("toTarget");
	}

	@Column(name = "ORDER_DOCUMENT_NO")
	public String getOrderDocumentNo() {
		return orderDocumentNo;
	}

	public void setOrderDocumentNo(String orderDocumentNo) {
		this.orderDocumentNo = orderDocumentNo;
		addValidField("orderDocumentNo");
	}

	@Column(name = "CUSTOMER_REF_NO")
	public String getCustomerRefNo() {
		return customerRefNo;
	}

	public void setCustomerRefNo(String customerRefNo) {
		this.customerRefNo = customerRefNo;
		addValidField("customerRefNo");
	}

	@Column(name = "DELIVERY_TYPE")
	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
		addValidField("deliveryType");
	}

	@Column(name = "TRUCK_FLEET_CODE")
	public String getTruckFleetCode() {
		return truckFleetCode;
	}

	public void setTruckFleetCode(String truckFleetCode) {
		this.truckFleetCode = truckFleetCode;
		addValidField("truckFleetCode");
	}

	@Column(name = "PICKUP_DEPOT")
	public String getPickupDepot() {
		return pickupDepot;
	}

	public void setPickupDepot(String pickupDepot) {
		this.pickupDepot = pickupDepot;
		addValidField("pickupDepot");
	}

	@Column(name = "PICKUP_CNTR_STATUS")
	public String getPickupCntrStatus() {
		return pickupCntrStatus;
	}

	public void setPickupCntrStatus(String pickupCntrStatus) {
		this.pickupCntrStatus = pickupCntrStatus;
		addValidField("pickupCntrStatus");
	}

	@Column(name = "RETURN_DEPOT")
	public String getReturnDepot() {
		return returnDepot;
	}

	public void setReturnDepot(String returnDepot) {
		this.returnDepot = returnDepot;
		addValidField("returnDepot");
	}

	@Column(name = "RETURN_CNTR_STATUS")
	public String getReturnCntrStatus() {
		return returnCntrStatus;
	}

	public void setReturnCntrStatus(String returnCntrStatus) {
		this.returnCntrStatus = returnCntrStatus;
		addValidField("returnCntrStatus");
	}

	@Column(name = "CNTR_ADMIN_CODE")
	public String getCntrAdminCode() {
		return cntrAdminCode;
	}

	public void setCntrAdminCode(String cntrAdminCode) {
		this.cntrAdminCode = cntrAdminCode;
		addValidField("cntrAdminCode");
	}

	@Column(name = "TRANSACTION_TYPE")
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
		addValidField("transactionType");
	}

	@Column(name = "TRANSACTION_STATUS")
	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
		addValidField("transactionStatus");
	}

	@Column(name = "CONFIG_CODE")
	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
		addValidField("configCode");
	}

	@Column(name = "LOGISTICS_CONTROL_WORD")
	public String getLogisticsControlWord() {
		return logisticsControlWord;
	}

	public void setLogisticsControlWord(String logisticsControlWord) {
		this.logisticsControlWord = logisticsControlWord;
		addValidField("logisticsControlWord");
	}

	@Column(name = "SHIPPING_ORDER_NO")
	public String getShippingOrderNo() {
		return shippingOrderNo;
	}

	public void setShippingOrderNo(String shippingOrderNo) {
		this.shippingOrderNo = shippingOrderNo;
		addValidField("shippingOrderNo");
	}

	@Column(name = "PAY_TYPE")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
		addValidField("payType");
	}

	@Column(name = "DELIVERY_DESC")
	public String getDeliveryDesc() {
		return deliveryDesc;
	}

	public void setDeliveryDesc(String deliveryDesc) {
		this.deliveryDesc = deliveryDesc;
		addValidField("deliveryDesc");
	}

	@Column(name = "REMARKS")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
		addValidField("remarks");
	}

	@Column(name = "PORT_TRANSHIP_CODE")
	public String getPortTranshipCode() {
		return portTranshipCode;
	}

	public void setPortTranshipCode(String portTranshipCode) {
		this.portTranshipCode = portTranshipCode;
		addValidField("portTranshipCode");
	}

	@Column(name = "PORT_TRANSHIP_DESC")
	public String getPortTranshipDesc() {
		return portTranshipDesc;
	}

	public void setPortTranshipDesc(String portTranshipDesc) {
		this.portTranshipDesc = portTranshipDesc;
		addValidField("portTranshipDesc");
	}

	@Column(name = "VESSEL_CODE")
	public String getVesselCode() {
		return vesselCode;
	}

	public void setVesselCode(String vesselCode) {
		this.vesselCode = vesselCode;
		addValidField("vesselCode");
	}

	@Column(name = "VOYAGE_NO")
	public String getVoyageNo() {
		return voyageNo;
	}

	public void setVoyageNo(String voyageNo) {
		this.voyageNo = voyageNo;
		addValidField("voyageNo");
	}

	@Column(name = "OCEAN_VESSEL_NAME")
	public String getOceanVesselName() {
		return oceanVesselName;
	}

	public void setOceanVesselName(String oceanVesselName) {
		this.oceanVesselName = oceanVesselName;
		addValidField("oceanVesselName");
	}

	@Column(name = "OCEAN_VESSEL_VOYAGE")
	public String getOceanVesselVoyage() {
		return oceanVesselVoyage;
	}

	public void setOceanVesselVoyage(String oceanVesselVoyage) {
		this.oceanVesselVoyage = oceanVesselVoyage;
		addValidField("oceanVesselVoyage");
	}

	@Column(name = "PLAN_EXPORT_DATE")
	public Date getPlanExportDate() {
		return planExportDate;
	}

	public void setPlanExportDate(Date planExportDate) {
		this.planExportDate = planExportDate;
		addValidField("planExportDate");
	}

	@Column(name = "WORK_SPECIAL")
	public String getWorkSpecial() {
		return workSpecial;
	}

	public void setWorkSpecial(String workSpecial) {
		this.workSpecial = workSpecial;
		addValidField("workSpecial");
	}

	@Column(name = "TRACTOR_NO")
	public String getTractorNo() {
		return tractorNo;
	}

	public void setTractorNo(String tractorNo) {
		this.tractorNo = tractorNo;
		addValidField("tractorNo");
	}

	@Column(name = "TRANSACTOR")
	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
		addValidField("transactor");
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

	@Column(name = "RELEASED_ID")
	public String getReleasedId() {
		return releasedId;
	}

	public void setReleasedId(String releasedId) {
		this.releasedId = releasedId;
		addValidField("releasedId");
	}

	@Column(name = "RETURN_ADMIN_CODE")
	public String getReturnAdminCode() {
		return returnAdminCode;
	}

	public void setReturnAdminCode(String returnAdminCode) {
		this.returnAdminCode = returnAdminCode;
		addValidField("returnAdminCode");
	}

	@Column(name = "LOGISTICS_FEEDIN_DATE")
	public Date getLogisticsFeedinDate() {
		return logisticsFeedinDate;
	}

	public void setLogisticsFeedinDate(Date logisticsFeedinDate) {
		this.logisticsFeedinDate = logisticsFeedinDate;
		addValidField("logisticsFeedinDate");
	}

	@Column(name = "LOGISTICS_DELIVERY_DATE")
	public Date getLogisticsDeliveryDate() {
		return logisticsDeliveryDate;
	}

	public void setLogisticsDeliveryDate(Date logisticsDeliveryDate) {
		this.logisticsDeliveryDate = logisticsDeliveryDate;
		addValidField("logisticsDeliveryDate");
	}

	@Column(name = "TRANSACTOR_TEL_NO")
	public String getTransactorTelNo() {
		return transactorTelNo;
	}

	public void setTransactorTelNo(String transactorTelNo) {
		this.transactorTelNo = transactorTelNo;
		addValidField("transactorTelNo");
	}

	@Column(name = "AGENT_CONSIGNEE_DESC")
	public String getAgentConsigneeDesc() {
		return agentConsigneeDesc;
	}

	public void setAgentConsigneeDesc(String agentConsigneeDesc) {
		this.agentConsigneeDesc = agentConsigneeDesc;
		addValidField("agentConsigneeDesc");
	}

	@Column(name = "AGENT_CONSIGNEE_NAME")
	public String getAgentConsigneeName() {
		return agentConsigneeName;
	}

	public void setAgentConsigneeName(String agentConsigneeName) {
		this.agentConsigneeName = agentConsigneeName;
		addValidField("agentConsigneeName");
	}

	@Column(name = "CNTR_ADMIN_NAME")
	public String getCntrAdminName() {
		return cntrAdminName;
	}

	public void setCntrAdminName(String cntrAdminName) {
		this.cntrAdminName = cntrAdminName;
		addValidField("cntrAdminName");
	}

}
