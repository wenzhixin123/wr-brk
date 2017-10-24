package com.sinotrans.gd.wlp.statistics.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class CommonGetRemainHoldQueryItem extends BaseQueryItem {

	private String inLogisticsOrderDetailUuid;
	private String inLogisticsOrderNo;
	private String billNo;
	private Long seqNo;
	private String goodsDesc;
	private String model;
	private String spec;
	private String lengthUnitCode;
	private Double length;
	private Double width;
	private Double height;
	private String goodsKind;
	private String goodsNature;
	private String dangerCode;
	private String packageNo;
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
	private String aux5;
	private String submitOrderDetailUuid;
	private Double qty;
	private String remainHoldUuid;
	private String itemCode;
	private String itemSeqno;
	private String batchNo;
	private String marksNumber;
	private String barcode;
	private Double instockQty;
	private Double instockNetWeight;
	private Double instockGrossWeight;
	private Double instockVolume;
	private String unitCode;
	private String unitDesc;
	private String secondUnitCode;
	private String secondUnitDesc;
	private String thirdUnitCode;
	private String thirdUnitDesc;
	private Double thirdQty;
	private Double remainQty;
	private Double holdQty;
	private Double secondQty;
	private Double grossWeight;
	private Double netWeight;
	private Double volume;
	private Double remainQtyHoldQtySum;
	private Double remainSecondQtyHoldSum;
	// 生产日期
	private Date productionDate;

	@Column(name = "PRODUCTION_DATE")
	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

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

	@Column(name = "GOODS_DESC")
	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
		addValidField("goodsDesc");
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

	@Column(name = "PACKAGE_NO")
	public String getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
		addValidField("packageNo");
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

	@Column(name = "AUX5")
	public String getAux5() {
		return aux5;
	}

	public void setAux5(String aux5) {
		this.aux5 = aux5;
		addValidField("aux5");
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

	@Column(name = "BATCH_NO")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
		addValidField("batchNo");
	}

	@Column(name = "MARKS_NUMBER")
	public String getMarksNumber() {
		return marksNumber;
	}

	public void setMarksNumber(String marksNumber) {
		this.marksNumber = marksNumber;
		addValidField("marksNumber");
	}

	@Column(name = "BARCODE")
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
		addValidField("barcode");
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

	@Column(name = "UNIT_CODE")
	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
		addValidField("unitCode");
	}

	@Column(name = "UNIT_DESC")
	public String getUnitDesc() {
		return unitDesc;
	}

	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc;
		addValidField("unitDesc");
	}

	@Column(name = "SECOND_UNIT_CODE")
	public String getSecondUnitCode() {
		return secondUnitCode;
	}

	public void setSecondUnitCode(String secondUnitCode) {
		this.secondUnitCode = secondUnitCode;
		addValidField("secondUnitCode");
	}

	@Column(name = "SECOND_UNIT_DESC")
	public String getSecondUnitDesc() {
		return secondUnitDesc;
	}

	public void setSecondUnitDesc(String secondUnitDesc) {
		this.secondUnitDesc = secondUnitDesc;
		addValidField("secondUnitDesc");
	}

	@Column(name = "THIRD_UNIT_CODE")
	public String getThirdUnitCode() {
		return thirdUnitCode;
	}

	public void setThirdUnitCode(String thirdUnitCode) {
		this.thirdUnitCode = thirdUnitCode;
		addValidField("thirdUnitCode");
	}

	@Column(name = "THIRD_UNIT_DESC")
	public String getThirdUnitDesc() {
		return thirdUnitDesc;
	}

	public void setThirdUnitDesc(String thirdUnitDesc) {
		this.thirdUnitDesc = thirdUnitDesc;
		addValidField("thirdUnitDesc");
	}

	@Column(name = "THIRD_QTY")
	public Double getThirdQty() {
		return thirdQty;
	}

	public void setThirdQty(Double thirdQty) {
		this.thirdQty = thirdQty;
		addValidField("thirdQty");
	}

	@Column(name = "REMAIN_QTY")
	public Double getRemainQty() {
		return remainQty;
	}

	public void setRemainQty(Double remainQty) {
		this.remainQty = remainQty;
		addValidField("remainQty");
	}

	@Column(name = "HOLD_QTY")
	public Double getHoldQty() {
		return holdQty;
	}

	public void setHoldQty(Double holdQty) {
		this.holdQty = holdQty;
		addValidField("holdQty");
	}

	@Column(name = "SECOND_QTY")
	public Double getSecondQty() {
		return secondQty;
	}

	public void setSecondQty(Double secondQty) {
		this.secondQty = secondQty;
		addValidField("secondQty");
	}

	@Column(name = "GROSS_WEIGHT")
	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
		addValidField("grossWeight");
	}

	@Column(name = "NET_WEIGHT")
	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
		addValidField("netWeight");
	}

	@Column(name = "VOLUME")
	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
		addValidField("volume");
	}

	@Column(name = "REMAIN_QTY_HOLD_QTY_SUM")
	public Double getRemainQtyHoldQtySum() {
		return remainQtyHoldQtySum;
	}

	public void setRemainQtyHoldQtySum(Double remainQtyHoldQtySum) {
		this.remainQtyHoldQtySum = remainQtyHoldQtySum;
		addValidField("remainQtyHoldQtySum");
	}

	@Column(name = "REMAIN_SECOND_QTY_HOLD_SUM")
	public Double getRemainSecondQtyHoldSum() {
		return remainSecondQtyHoldSum;
	}

	public void setRemainSecondQtyHoldSum(Double remainSecondQtyHoldSum) {
		this.remainSecondQtyHoldSum = remainSecondQtyHoldSum;
		addValidField("remainSecondQtyHoldSum");
	}

}
