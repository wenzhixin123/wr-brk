package com.sinotrans.gd.wlp.common.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class YclRemainSearchQueryCondition extends BaseQueryCondition {

	private String officeCode;
	private String logisticsOrderNo;
	private String orderNo;
	private String warehouseCode;
	private String locAreaCode;
	private String barcode;
	private String goodsKind;
	private String cargoConsigneeCode;
	private String panelNo;
	private String deliveryType;
	private String deliveryOrderNo;
	private String goodsNature;
	private String lotCode;
	

	public String getLotCode() {
		return lotCode;
	}

	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
	}

	public String getGoodsNature() {
		return goodsNature;
	}

	public void setGoodsNature(String goodsNature) {
		this.goodsNature = goodsNature;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getDeliveryOrderNo() {
		return deliveryOrderNo;
	}

	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
	}

	public String getPanelNo() {
		return panelNo;
	}

	public void setPanelNo(String panelNo) {
		this.panelNo = panelNo;
	}

	public String getGoodsKind() {
		return goodsKind;
	}

	public void setGoodsKind(String goodsKind) {
		this.goodsKind = goodsKind;
	}

	public String getCargoConsigneeCode() {
		return cargoConsigneeCode;
	}

	public void setCargoConsigneeCode(String cargoConsigneeCode) {
		this.cargoConsigneeCode = cargoConsigneeCode;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

}
