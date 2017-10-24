package com.sinotrans.gd.wlp.statistics.query;

import java.util.Date;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class OutboundEditDetail2QueryCondition extends BaseQueryCondition {

//	private String transactionType;
	private String logisticsOrderNo;
	private Date orderDateStart;
	private Date orderDateEnd;
	private String batchNo;
	private String itemCode;
	private String marksNumber;
	private String projectCode;
	private String model;
	private String spec;
	private String goodsDesc;
	private String officeCode;
	private String urgentOrderNo;
	private String barcode;
	private String packageNo;
	private String flow;
	private String orderNo;
	private String[] goodsNature;
	private String cargoConsigneeCode;
	private String agentConsigneeCode;
	private String goodsNatureSD;
	private String []inAgentConsingneeCode;
	private String locAreaCode;
	private String warehouseCode;
	private String cargoControlCode;
	private String targetCustomerCode;
	private String warehouse;
	private String cw14;
	private String lotCode;
	private String transactionType;
	private String contractNo;
	private String modelSP;
    private String deliveryType;//入库作业项目
    private String []inCargoControlCode;//控货方
    
    public String[] getInCargoControlCode() {
		return inCargoControlCode;
	}

	public void setInCargoControlCode(String[] inCargoControlCode) {
		this.inCargoControlCode = inCargoControlCode;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	
	public String getModelSP() {
		return modelSP;
	}

	public void setModelSP(String modelSP) {
		this.modelSP = modelSP;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getLotCode() {
		return lotCode;
	}

	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getTargetCustomerCode() {
		return targetCustomerCode;
	}

	public void setTargetCustomerCode(String targetCustomerCode) {
		this.targetCustomerCode = targetCustomerCode;
	}

	public String getCargoControlCode() {
		return cargoControlCode;
	}

	public void setCargoControlCode(String cargoControlCode) {
		this.cargoControlCode = cargoControlCode;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getLocAreaCode() {
		return locAreaCode;
	}

	public void setLocAreaCode(String locAreaCode) {
		this.locAreaCode = locAreaCode;
	}

	public String[] getInAgentConsingneeCode() {
		return inAgentConsingneeCode;
	}

	public void setInAgentConsingneeCode(String[] inAgentConsingneeCode) {
		this.inAgentConsingneeCode = inAgentConsingneeCode;
	}

	public String getAgentConsigneeCode() {
		return agentConsigneeCode;
	}

	public void setAgentConsigneeCode(String agentConsigneeCode) {
		this.agentConsigneeCode = agentConsigneeCode;
	}

	public String getCargoConsigneeCode() {
		return cargoConsigneeCode;
	}

	public void setCargoConsigneeCode(String cargoConsigneeCode) {
		this.cargoConsigneeCode = cargoConsigneeCode;
	}

	public String[] getGoodsNature() {
		return goodsNature;
	}

	public void setGoodsNature(String[] goodsNature) {
		this.goodsNature = goodsNature;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

//	public String getTransactionType() {
//		return transactionType;
//	}
//
//	public void setTransactionType(String transactionType) {
//		this.transactionType = transactionType;
//	}

	public String getLogisticsOrderNo() {
		return logisticsOrderNo;
	}

	public void setLogisticsOrderNo(String logisticsOrderNo) {
		this.logisticsOrderNo = logisticsOrderNo;
	}

	public Date getOrderDateStart() {
		return orderDateStart;
	}

	public void setOrderDateStart(Date orderDateStart) {
		this.orderDateStart = orderDateStart;
	}

	public Date getOrderDateEnd() {
		return orderDateEnd;
	}

	public void setOrderDateEnd(Date orderDateEnd) {
		this.orderDateEnd = orderDateEnd;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getMarksNumber() {
		return marksNumber;
	}

	public void setMarksNumber(String marksNumber) {
		this.marksNumber = marksNumber;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getUrgentOrderNo() {
		return urgentOrderNo;
	}

	public void setUrgentOrderNo(String urgentOrderNo) {
		this.urgentOrderNo = urgentOrderNo;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getGoodsNatureSD() {
		return goodsNatureSD;
	}

	public void setGoodsNatureSD(String goodsNatureSD) {
		this.goodsNatureSD = goodsNatureSD;
	}

	public String getCw14() {
		return cw14;
	}

	public void setCw14(String cw14) {
		this.cw14 = cw14;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	
}
