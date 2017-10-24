package com.sinotrans.gd.wlp.common.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class HUBSodBySoUuidQueryItem extends BaseQueryItem {

	private String submitOrderDetailUuid;
	private String submitOrderUuid;
	private String submitOrderNo;
	private Long seqNo;
	private String billNo;
	private String batchNo;
	private String itemCode;
	private String itemSeqno;
	private String extItemCode;
	private String goodsDesc;
	private String marksNumber;
	private String model;
	private String spec;
	private String lengthUnitCode;
	private String lengthUnitDesc;
	private Double length;
	private Double width;
	private Double height;
	private Double qty;
	private String qtyUnitCode;
	private String qtyUnitDesc;
	private Double secondQty;
	private String secondUnitCode;
	private String secondUnitDesc;
	private Double thirdQty;
	private String thirdUnitCode;
	private String thirdUnitDesc;
	private String weightUnitCode;
	private String weightUnitDesc;
	private Double netWeight;
	private Double grossWeight;
	private String volumeUnitCode;
	private String volumeUnitDesc;
	private Double volume;
	private Double unitPrice;
	private Double totalPrice;
	private String currencyCode;
	private String currencyDesc;
	private String dangerCode;
	private Double deliveredQty;
	private Double confirmedQty;
	private String goodsKind;
	private String goodsNature;
	private String packageNo;
	private String packageType;
	private String bomCode;
	private String transactionStatus;
	private String transactionType;
	private String controlWord;
	private String remark;
	private String aux1;
	private String aux2;
	private String aux3;
	private String aux4;
	private String aux5;
	private String officeCode;
	private Long recVer;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date modifyTime;
	private Date productionDate;

	@Column(name = "SUBMIT_ORDER_DETAIL_UUID")
	public String getSubmitOrderDetailUuid() {
		return submitOrderDetailUuid;
	}

	public void setSubmitOrderDetailUuid(String submitOrderDetailUuid) {
		this.submitOrderDetailUuid = submitOrderDetailUuid;
		addValidField("submitOrderDetailUuid");
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

	@Column(name = "SEQ_NO")
	public Long getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
		addValidField("seqNo");
	}

	@Column(name = "BILL_NO")
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
		addValidField("billNo");
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

	@Column(name = "LENGTH_UNIT_DESC")
	public String getLengthUnitDesc() {
		return lengthUnitDesc;
	}

	public void setLengthUnitDesc(String lengthUnitDesc) {
		this.lengthUnitDesc = lengthUnitDesc;
		addValidField("lengthUnitDesc");
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

	@Column(name = "QTY")
	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
		addValidField("qty");
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

	@Column(name = "SECOND_QTY")
	public Double getSecondQty() {
		return secondQty;
	}

	public void setSecondQty(Double secondQty) {
		this.secondQty = secondQty;
		addValidField("secondQty");
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

	@Column(name = "THIRD_QTY")
	public Double getThirdQty() {
		return thirdQty;
	}

	public void setThirdQty(Double thirdQty) {
		this.thirdQty = thirdQty;
		addValidField("thirdQty");
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

	@Column(name = "NET_WEIGHT")
	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
		addValidField("netWeight");
	}

	@Column(name = "GROSS_WEIGHT")
	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
		addValidField("grossWeight");
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

	@Column(name = "VOLUME")
	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
		addValidField("volume");
	}

	@Column(name = "UNIT_PRICE")
	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
		addValidField("unitPrice");
	}

	@Column(name = "TOTAL_PRICE")
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
		addValidField("totalPrice");
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

	@Column(name = "DANGER_CODE")
	public String getDangerCode() {
		return dangerCode;
	}

	public void setDangerCode(String dangerCode) {
		this.dangerCode = dangerCode;
		addValidField("dangerCode");
	}

	@Column(name = "DELIVERED_QTY")
	public Double getDeliveredQty() {
		return deliveredQty;
	}

	public void setDeliveredQty(Double deliveredQty) {
		this.deliveredQty = deliveredQty;
		addValidField("deliveredQty");
	}

	@Column(name = "CONFIRMED_QTY")
	public Double getConfirmedQty() {
		return confirmedQty;
	}

	public void setConfirmedQty(Double confirmedQty) {
		this.confirmedQty = confirmedQty;
		addValidField("confirmedQty");
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

	@Column(name = "BOM_CODE")
	public String getBomCode() {
		return bomCode;
	}

	public void setBomCode(String bomCode) {
		this.bomCode = bomCode;
		addValidField("bomCode");
	}

	@Column(name = "TRANSACTION_STATUS")
	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
		addValidField("transactionStatus");
	}

	@Column(name = "TRANSACTION_TYPE")
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
		addValidField("transactionType");
	}

	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField("controlWord");
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		addValidField("remark");
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

	@Column(name = "AUX4")
	public String getAux4() {
		return aux4;
	}

	public void setAux4(String aux4) {
		this.aux4 = aux4;
		addValidField("aux4");
	}

	@Column(name = "AUX5")
	public String getAux5() {
		return aux5;
	}

	public void setAux5(String aux5) {
		this.aux5 = aux5;
		addValidField("aux5");
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

	@Column(name = "PRODUCTION_DATE")
	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
		addValidField("productionDate");
	}

}
