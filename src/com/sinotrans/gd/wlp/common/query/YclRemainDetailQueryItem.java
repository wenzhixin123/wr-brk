package com.sinotrans.gd.wlp.common.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class YclRemainDetailQueryItem extends BaseQueryItem {

	private String itemCode;
	private String goodsDesc;
	private String extItemCode;
	private String lotCode;
	private String barcode;
	private Double remainQty;
	private Integer remainHoldQty;
	private String logisticsOrderNo;
	private String orderNo;
	private Date productionDate;
	private String cargoConsigneeDesc;
	private Date cutOffDate;
	private String inLogisticsOrderDetailUuid;
	private String goodsNature;
	private String locationTaskUuid;
	private String inStockWorkdUuid;
	private String remainSinworkUuid;
//	private String isHold;
	private String packageNo;
	private String batchNo;
	private String goodsKind;
	private String instockUnitDesc;
//	private String holdNo;
	private String panelNo;
	private String cargoControlDesc;
	private String customerName;
	
	@Column(name="CARGO_CONTROL_DESC")
	public String getCargoControlDesc() {
		return cargoControlDesc;
	}

	public void setCargoControlDesc(String cargoControlDesc) {
		this.cargoControlDesc = cargoControlDesc;
		addValidField("cargoControlDesc");
	}
	
	@Column(name="PANEL_NO")
	public String getPanelNo() {
		return panelNo;
	}

	public void setPanelNo(String panelNo) {
		this.panelNo = panelNo;
	}

	/*@Column(name="HOLD_NO")
	public String getHoldNo() {
		return holdNo;
	}

	public void setHoldNo(String holdNo) {
		this.holdNo = holdNo;
	}*/

	@Column(name="INSTOCK_UNIT_DESC")
	public String getInstockUnitDesc() {
		return instockUnitDesc;
	}

	public void setInstockUnitDesc(String instockUnitDesc) {
		this.instockUnitDesc = instockUnitDesc;
	}

	@Column(name="GOODS_KIND")
	public String getGoodsKind() {
		return goodsKind;
	}

	public void setGoodsKind(String goodsKind) {
		this.goodsKind = goodsKind;
	}

	@Column(name="BATCH_NO")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name="PACKAGE_NO")
	public String getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}

	/*@Column(name = "IS_HOLD")
	public String getIsHold() {
		return isHold;
	}

	public void setIsHold(String isHold) {
		this.isHold = isHold;
	}*/

	@Column(name = "REMAIN_SINWORK_UUID")
	public String getRemainSinworkUuid() {
		return remainSinworkUuid;
	}

	public void setRemainSinworkUuid(String remainSinworkUuid) {
		this.remainSinworkUuid = remainSinworkUuid;
	}

	@Column(name = "IN_STOCK_WORK_UUID")
	public String getInStockWorkdUuid() {
		return inStockWorkdUuid;
	}

	public void setInStockWorkdUuid(String inStockWorkdUuid) {
		this.inStockWorkdUuid = inStockWorkdUuid;
	}

	@Column(name = "GOODS_NATURE")
	public String getGoodsNature() {
		return goodsNature;
	}

	public void setGoodsNature(String goodsNature) {
		this.goodsNature = goodsNature;
	}

	@Column(name = "LOCATION_TASK_UUID")
	public String getLocationTaskUuid() {
		return locationTaskUuid;
	}

	public void setLocationTaskUuid(String locationTaskUuid) {
		this.locationTaskUuid = locationTaskUuid;
	}

	@Column(name = "IN_LOGISTICS_ORDER_DETAIL_UUID")
	public String getInLogisticsOrderDetailUuid() {
		return inLogisticsOrderDetailUuid;
	}

	public void setInLogisticsOrderDetailUuid(String inLogisticsOrderDetailUuid) {
		this.inLogisticsOrderDetailUuid = inLogisticsOrderDetailUuid;
	}

	@Column(name = "ITEM_CODE")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
		addValidField("itemCode");
	}

	@Column(name = "GOODS_DESC")
	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
		addValidField("goodsDesc");
	}

	@Column(name = "EXT_ITEM_CODE")
	public String getExtItemCode() {
		return extItemCode;
	}

	public void setExtItemCode(String extItemCode) {
		this.extItemCode = extItemCode;
		addValidField("extItemCode");
	}

	@Column(name = "LOT_CODE")
	public String getLotCode() {
		return lotCode;
	}

	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
		addValidField("lotCode");
	}

	@Column(name = "BARCODE")
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
		addValidField("barcode");
	}

	@Column(name = "REMAIN_QTY")
	public Double getRemainQty() {
		return remainQty;
	}

	public void setRemainQty(Double remainQty) {
		this.remainQty = remainQty;
		addValidField("remainQty");
	}

	@Column(name = "REMAIN_HOLD_QTY")
	public Integer getRemainHoldQty() {
		return remainHoldQty;
	}

	public void setRemainHoldQty(Integer remainHoldQty) {
		this.remainHoldQty = remainHoldQty;
		addValidField("remainHoldQty");
	}

	@Column(name = "LOGISTICS_ORDER_NO")
	public String getLogisticsOrderNo() {
		return logisticsOrderNo;
	}

	public void setLogisticsOrderNo(String logisticsOrderNo) {
		this.logisticsOrderNo = logisticsOrderNo;
		addValidField("logisticsOrderNo");
	}

	@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
		addValidField("orderNo");
	}

	@Column(name = "PRODUCTION_DATE")
	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
		addValidField("productionDate");
	}

	@Column(name = "CARGO_CONSIGNEE_DESC")
	public String getCargoConsigneeDesc() {
		return cargoConsigneeDesc;
	}

	public void setCargoConsigneeDesc(String cargoConsigneeDesc) {
		this.cargoConsigneeDesc = cargoConsigneeDesc;
		addValidField("cargoConsigneeDesc");
	}

	@Column(name = "CUT_OFF_DATE")
	public Date getCutOffDate() {
		return cutOffDate;
	}

	public void setCutOffDate(Date cutOffDate) {
		this.cutOffDate = cutOffDate;
		addValidField("cutOffDate");
	}

	@Column(name = "CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
		addValidField("customerName");
	}

}
