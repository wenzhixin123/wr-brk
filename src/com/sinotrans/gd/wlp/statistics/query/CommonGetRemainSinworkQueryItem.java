package com.sinotrans.gd.wlp.statistics.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class CommonGetRemainSinworkQueryItem extends BaseQueryItem {

	private String inLogisticsOrderDetailUuid;
	private String inLogisticsOrderNo;
	private String billNo;
	private Long seqNo;
	private String model;
	private String spec;
	private String lengthUnitCode;
	private Double length;
	private Double width;
	private Double height;
	private String goodsKind;
	private String goodsNature;
	private String dangerCode;
	private String packageType;
	private String volumeUnitCode;
	private String volumeUnitDesc;
	private String weightUnitCode;
	private String weightUnitDesc;
	private Double unitPrice;
	private String currencyCode;
	private String currencyDesc;
	private String qtyUnitCode;
	private String qtyUnitDesc;
	private Double totalPrice;
	private String lengthUnitDesc;
	private String submitOrderDetailUuid;
	private Double qty;
	private String remainHoldUuid;
	private Double instockQty;
	private Double instockNetWeight;
	private Double instockGrossWeight;
	private Double instockVolume;
	private Date createTime;
	private String inStockWorkUuid;
	private Double remainSecondQty;
	private Double remainGrossWeight;
	private Double remainNetWeight;
	private Double remainVolume;
	private String packageNo;
	private String batchNo;
	private String itemCode;
	private String itemSeqno;
	private String extItemCode;
	private String goodsDesc;
	private String marksNumber;
	private String panelNo;
	private String barcode;
	private Double remainQty;
	private String lotCode;
	private Date productionDate;

	@Column(name = "IN_LOGISTICS_ORDER_DETAIL_UUID")
	public String getInLogisticsOrderDetailUuid() {
		return inLogisticsOrderDetailUuid;
	}

	public void setInLogisticsOrderDetailUuid(String inLogisticsOrderDetailUuid) {
		this.inLogisticsOrderDetailUuid = inLogisticsOrderDetailUuid;
		addValidField("inLogisticsOrderDetailUuid");
	}

	@Column(name = "IN_LOGISTICS_ORDER_NO")
	public String getInLogisticsOrderNo() {
		return inLogisticsOrderNo;
	}

	public void setInLogisticsOrderNo(String inLogisticsOrderNo) {
		this.inLogisticsOrderNo = inLogisticsOrderNo;
		addValidField("inLogisticsOrderNo");
	}

	@Column(name = "BILL_NO")
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
		addValidField("billNo");
	}

	@Column(name = "SEQ_NO")
	public Long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
		addValidField("seqNo");
	}

	@Column(name = "MODEL")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
		addValidField("model");
	}

	@Column(name = "SPEC")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
		addValidField("spec");
	}

	@Column(name = "LENGTH_UNIT_CODE")
	public String getLengthUnitCode() {
		return lengthUnitCode;
	}

	public void setLengthUnitCode(String lengthUnitCode) {
		this.lengthUnitCode = lengthUnitCode;
		addValidField("lengthUnitCode");
	}

	@Column(name = "LENGTH")
	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
		addValidField("length");
	}

	@Column(name = "WIDTH")
	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
		addValidField("width");
	}

	@Column(name = "HEIGHT")
	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
		addValidField("height");
	}

	@Column(name = "GOODS_KIND")
	public String getGoodsKind() {
		return goodsKind;
	}

	public void setGoodsKind(String goodsKind) {
		this.goodsKind = goodsKind;
		addValidField("goodsKind");
	}

	@Column(name = "GOODS_NATURE")
	public String getGoodsNature() {
		return goodsNature;
	}

	public void setGoodsNature(String goodsNature) {
		this.goodsNature = goodsNature;
		addValidField("goodsNature");
	}

	@Column(name = "DANGER_CODE")
	public String getDangerCode() {
		return dangerCode;
	}

	public void setDangerCode(String dangerCode) {
		this.dangerCode = dangerCode;
		addValidField("dangerCode");
	}

	@Column(name = "PACKAGE_TYPE")
	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
		addValidField("packageType");
	}

	@Column(name = "VOLUME_UNIT_CODE")
	public String getVolumeUnitCode() {
		return volumeUnitCode;
	}

	public void setVolumeUnitCode(String volumeUnitCode) {
		this.volumeUnitCode = volumeUnitCode;
		addValidField("volumeUnitCode");
	}

	@Column(name = "VOLUME_UNIT_DESC")
	public String getVolumeUnitDesc() {
		return volumeUnitDesc;
	}

	public void setVolumeUnitDesc(String volumeUnitDesc) {
		this.volumeUnitDesc = volumeUnitDesc;
		addValidField("volumeUnitDesc");
	}

	@Column(name = "WEIGHT_UNIT_CODE")
	public String getWeightUnitCode() {
		return weightUnitCode;
	}

	public void setWeightUnitCode(String weightUnitCode) {
		this.weightUnitCode = weightUnitCode;
		addValidField("weightUnitCode");
	}

	@Column(name = "WEIGHT_UNIT_DESC")
	public String getWeightUnitDesc() {
		return weightUnitDesc;
	}

	public void setWeightUnitDesc(String weightUnitDesc) {
		this.weightUnitDesc = weightUnitDesc;
		addValidField("weightUnitDesc");
	}

	@Column(name = "UNIT_PRICE")
	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
		addValidField("unitPrice");
	}

	@Column(name = "CURRENCY_CODE")
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
		addValidField("currencyCode");
	}

	@Column(name = "CURRENCY_DESC")
	public String getCurrencyDesc() {
		return currencyDesc;
	}

	public void setCurrencyDesc(String currencyDesc) {
		this.currencyDesc = currencyDesc;
		addValidField("currencyDesc");
	}

	@Column(name = "QTY_UNIT_CODE")
	public String getQtyUnitCode() {
		return qtyUnitCode;
	}

	public void setQtyUnitCode(String qtyUnitCode) {
		this.qtyUnitCode = qtyUnitCode;
		addValidField("qtyUnitCode");
	}

	@Column(name = "QTY_UNIT_DESC")
	public String getQtyUnitDesc() {
		return qtyUnitDesc;
	}

	public void setQtyUnitDesc(String qtyUnitDesc) {
		this.qtyUnitDesc = qtyUnitDesc;
		addValidField("qtyUnitDesc");
	}

	@Column(name = "TOTAL_PRICE")
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
		addValidField("totalPrice");
	}

	@Column(name = "LENGTH_UNIT_DESC")
	public String getLengthUnitDesc() {
		return lengthUnitDesc;
	}

	public void setLengthUnitDesc(String lengthUnitDesc) {
		this.lengthUnitDesc = lengthUnitDesc;
		addValidField("lengthUnitDesc");
	}

	@Column(name = "SUBMIT_ORDER_DETAIL_UUID")
	public String getSubmitOrderDetailUuid() {
		return submitOrderDetailUuid;
	}

	public void setSubmitOrderDetailUuid(String submitOrderDetailUuid) {
		this.submitOrderDetailUuid = submitOrderDetailUuid;
		addValidField("submitOrderDetailUuid");
	}

	@Column(name = "QTY")
	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
		addValidField("qty");
	}

	@Column(name = "REMAIN_HOLD_UUID")
	public String getRemainHoldUuid() {
		return remainHoldUuid;
	}

	public void setRemainHoldUuid(String remainHoldUuid) {
		this.remainHoldUuid = remainHoldUuid;
		addValidField("remainHoldUuid");
	}
	@Column(name = "INSTOCK_QTY")
	public Double getInstockQty() {
		return instockQty;
	}

	public void setInstockQty(Double instockQty) {
		this.instockQty = instockQty;
		addValidField("instockQty");
	}

	@Column(name = "INSTOCK_NET_WEIGHT")
	public Double getInstockNetWeight() {
		return instockNetWeight;
	}

	public void setInstockNetWeight(Double instockNetWeight) {
		this.instockNetWeight = instockNetWeight;
		addValidField("instockNetWeight");
	}

	@Column(name = "INSTOCK_GROSS_WEIGHT")
	public Double getInstockGrossWeight() {
		return instockGrossWeight;
	}

	public void setInstockGrossWeight(Double instockGrossWeight) {
		this.instockGrossWeight = instockGrossWeight;
		addValidField("instockGrossWeight");
	}

	@Column(name = "INSTOCK_VOLUME")
	public Double getInstockVolume() {
		return instockVolume;
	}

	public void setInstockVolume(Double instockVolume) {
		this.instockVolume = instockVolume;
		addValidField("instockVolume");
	}
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		addValidField("createTime");
	}

	@Column(name = "IN_STOCK_WORK_UUID")
	public String getInStockWorkUuid() {
		return inStockWorkUuid;
	}

	public void setInStockWorkUuid(String inStockWorkUuid) {
		this.inStockWorkUuid = inStockWorkUuid;
		addValidField("inStockWorkUuid");
	}

	@Column(name = "REMAIN_SECOND_QTY")
	public Double getRemainSecondQty() {
		return remainSecondQty;
	}

	public void setRemainSecondQty(Double remainSecondQty) {
		this.remainSecondQty = remainSecondQty;
		addValidField("remainSecondQty");
	}

	@Column(name = "REMAIN_GROSS_WEIGHT")
	public Double getRemainGrossWeight() {
		return remainGrossWeight;
	}

	public void setRemainGrossWeight(Double remainGrossWeight) {
		this.remainGrossWeight = remainGrossWeight;
		addValidField("remainGrossWeight");
	}

	@Column(name = "REMAIN_NET_WEIGHT")
	public Double getRemainNetWeight() {
		return remainNetWeight;
	}

	public void setRemainNetWeight(Double remainNetWeight) {
		this.remainNetWeight = remainNetWeight;
		addValidField("remainNetWeight");
	}

	@Column(name = "REMAIN_VOLUME")
	public Double getRemainVolume() {
		return remainVolume;
	}

	public void setRemainVolume(Double remainVolume) {
		this.remainVolume = remainVolume;
		addValidField("remainVolume");
	}

	@Column(name = "PACKAGE_NO")
	public String getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
		addValidField("packageNo");
	}

	@Column(name = "BATCH_NO")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
		addValidField("batchNo");
	}

	@Column(name = "ITEM_CODE")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
		addValidField("itemCode");
	}

	@Column(name = "ITEM_SEQNO")
	public String getItemSeqno() {
		return itemSeqno;
	}

	public void setItemSeqno(String itemSeqno) {
		this.itemSeqno = itemSeqno;
		addValidField("itemSeqno");
	}

	@Column(name = "EXT_ITEM_CODE")
	public String getExtItemCode() {
		return extItemCode;
	}

	public void setExtItemCode(String extItemCode) {
		this.extItemCode = extItemCode;
		addValidField("extItemCode");
	}

	@Column(name = "GOODS_DESC")
	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
		addValidField("goodsDesc");
	}

	@Column(name = "MARKS_NUMBER")
	public String getMarksNumber() {
		return marksNumber;
	}

	public void setMarksNumber(String marksNumber) {
		this.marksNumber = marksNumber;
		addValidField("marksNumber");
	}

	@Column(name = "PANEL_NO")
	public String getPanelNo() {
		return panelNo;
	}

	public void setPanelNo(String panelNo) {
		this.panelNo = panelNo;
		addValidField("panelNo");
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

	@Column(name = "LOT_CODE")
	public String getLotCode() {
		return lotCode;
	}

	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
		addValidField("lotCode");
	}

	@Column(name = "PRODUCTION_DATE")
	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
		addValidField("productionDate");
	}

}
