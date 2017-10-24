package com.sinotrans.gd.wlp.common.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class CommonLodRsLtSwQueryItem extends BaseQueryItem {

	private String remainSinworkUuid;
	private String rsInLodUuid;
	private String unitCode;
	private String unitDesc;
	private String secondUnitCode;
	private String secondUnitDesc;
	private String thirdUnitCode;
	private String thirdUnitDesc;
	private String inStockWorkUuid;
	private Double remainQty;
	private Double remainSecondQty;
	private Double remainThirdQty;
	private Double remainGrossWeight;
	private Double remainNetWeight;
	private Double remainVolume;
	private String lotCode;
	private String batchNo;
	private String itemCode;
	private String itemSeqno;
	private String extItemCode;
	private String goodsDesc;
	private String marksNumber;
	private String packageNo;
	private String barcode;
	private String panelNo;
	private String goodsNature;
	private String goodsKind;
	private Date productionDate;
	private String officeCode;
	private Double length;
	private Double width;
	private Double height;
	private String locationTaskUuid;
	private String logisticsOrderDetailUuid;
	private String inLogisticsOrderDetailUuid;
	private Date locTaskDate;
	private String locTaskType;
	private String locTaskDesc;
	private String model;
	private String spec;
	private String lengthUnitCode;
	private String lengthUnitDesc;
	private Double ltQty;
	private String weightUnitCode;
	private String weightUnitDesc;
	private String volumeUnitCode;
	private String volumeUnitDesc;
	private String lastLocationTaskUuid;
	private String dangerCode;
	private String packageType;
	private String sourceLotCode;
	private String targetLotCode;
	private String ltBarcode;
	private String wrhWorker;
	private String wrhCust;
	private String remark;
	private String otherPkUuid;
	private String outLodUuid;
	private String logisticsOrderNo;
	private Double lodQty;
	private Double confirmedQty;
	private Double deliveredQty;
	
	@Column(name = "CONFIRMED_QTY")
	public Double getConfirmedQty() {
		return confirmedQty;
	}

	public void setConfirmedQty(Double confirmedQty) {
		this.confirmedQty = confirmedQty;
		addValidField("confirmedQty");
	}

	@Column(name = "DELIVERED_QTY")
	public Double getDeliveredQty() {
		return deliveredQty;
	}

	public void setDeliveredQty(Double deliveredQty) {
		this.deliveredQty = deliveredQty;
		addValidField("deliveredQty");
	}

	@Column(name = "LOGISTICS_ORDER_NO")
	public String getLogisticsOrderNo() {
		return logisticsOrderNo;
	}

	public void setLogisticsOrderNo(String logisticsOrderNo) {
		this.logisticsOrderNo = logisticsOrderNo;
		addValidField("logisticsOrderNo");
	}
	
	@Column(name = "OUT_LOD_UUID")
	public String getOutLodUuid() {
		return outLodUuid;
	}

	public void setOutLodUuid(String outLodUuid) {
		this.outLodUuid = outLodUuid;
		addValidField("outLodUuid");
	}

	@Column(name = "LOD_QTY")
	public Double getLodQty() {
		return lodQty;
	}

	public void setLodQty(Double lodQty) {
		this.lodQty = lodQty;
		addValidField("lodQty");
	}
	
	@Column(name = "REMAIN_SINWORK_UUID")
	public String getRemainSinworkUuid() {
		return remainSinworkUuid;
	}

	public void setRemainSinworkUuid(String remainSinworkUuid) {
		this.remainSinworkUuid = remainSinworkUuid;
		addValidField("remainSinworkUuid");
	}

	@Column(name = "RS_IN_LOD_UUID")
	public String getRsInLodUuid() {
		return rsInLodUuid;
	}

	public void setRsInLodUuid(String rsInLodUuid) {
		this.rsInLodUuid = rsInLodUuid;
		addValidField("rsInLodUuid");
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

	@Column(name = "IN_STOCK_WORK_UUID")
	public String getInStockWorkUuid() {
		return inStockWorkUuid;
	}

	public void setInStockWorkUuid(String inStockWorkUuid) {
		this.inStockWorkUuid = inStockWorkUuid;
		addValidField("inStockWorkUuid");
	}

	@Column(name = "REMAIN_QTY")
	public Double getRemainQty() {
		return remainQty;
	}

	public void setRemainQty(Double remainQty) {
		this.remainQty = remainQty;
		addValidField("remainQty");
	}

	@Column(name = "REMAIN_SECOND_QTY")
	public Double getRemainSecondQty() {
		return remainSecondQty;
	}

	public void setRemainSecondQty(Double remainSecondQty) {
		this.remainSecondQty = remainSecondQty;
		addValidField("remainSecondQty");
	}

	@Column(name = "REMAIN_THIRD_QTY")
	public Double getRemainThirdQty() {
		return remainThirdQty;
	}

	public void setRemainThirdQty(Double remainThirdQty) {
		this.remainThirdQty = remainThirdQty;
		addValidField("remainThirdQty");
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

	@Column(name = "LOT_CODE")
	public String getLotCode() {
		return lotCode;
	}

	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
		addValidField("lotCode");
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

	@Column(name = "PACKAGE_NO")
	public String getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
		addValidField("packageNo");
	}

	@Column(name = "BARCODE")
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
		addValidField("barcode");
	}

	@Column(name = "PANEL_NO")
	public String getPanelNo() {
		return panelNo;
	}

	public void setPanelNo(String panelNo) {
		this.panelNo = panelNo;
		addValidField("panelNo");
	}

	@Column(name = "GOODS_NATURE")
	public String getGoodsNature() {
		return goodsNature;
	}

	public void setGoodsNature(String goodsNature) {
		this.goodsNature = goodsNature;
		addValidField("goodsNature");
	}

	@Column(name = "GOODS_KIND")
	public String getGoodsKind() {
		return goodsKind;
	}

	public void setGoodsKind(String goodsKind) {
		this.goodsKind = goodsKind;
		addValidField("goodsKind");
	}

	@Column(name = "PRODUCTION_DATE")
	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
		addValidField("productionDate");
	}

	@Column(name = "OFFICE_CODE")
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
		addValidField("officeCode");
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

	@Column(name = "LOCATION_TASK_UUID")
	public String getLocationTaskUuid() {
		return locationTaskUuid;
	}

	public void setLocationTaskUuid(String locationTaskUuid) {
		this.locationTaskUuid = locationTaskUuid;
		addValidField("locationTaskUuid");
	}

	@Column(name = "LOGISTICS_ORDER_DETAIL_UUID")
	public String getLogisticsOrderDetailUuid() {
		return logisticsOrderDetailUuid;
	}

	public void setLogisticsOrderDetailUuid(String logisticsOrderDetailUuid) {
		this.logisticsOrderDetailUuid = logisticsOrderDetailUuid;
		addValidField("logisticsOrderDetailUuid");
	}

	@Column(name = "IN_LOGISTICS_ORDER_DETAIL_UUID")
	public String getInLogisticsOrderDetailUuid() {
		return inLogisticsOrderDetailUuid;
	}

	public void setInLogisticsOrderDetailUuid(String inLogisticsOrderDetailUuid) {
		this.inLogisticsOrderDetailUuid = inLogisticsOrderDetailUuid;
		addValidField("inLogisticsOrderDetailUuid");
	}

	@Column(name = "LOC_TASK_DATE")
	public Date getLocTaskDate() {
		return locTaskDate;
	}

	public void setLocTaskDate(Date locTaskDate) {
		this.locTaskDate = locTaskDate;
		addValidField("locTaskDate");
	}

	@Column(name = "LOC_TASK_TYPE")
	public String getLocTaskType() {
		return locTaskType;
	}

	public void setLocTaskType(String locTaskType) {
		this.locTaskType = locTaskType;
		addValidField("locTaskType");
	}

	@Column(name = "LOC_TASK_DESC")
	public String getLocTaskDesc() {
		return locTaskDesc;
	}

	public void setLocTaskDesc(String locTaskDesc) {
		this.locTaskDesc = locTaskDesc;
		addValidField("locTaskDesc");
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

	@Column(name = "LT_QTY")
	public Double getLtQty() {
		return ltQty;
	}

	public void setLtQty(Double ltQty) {
		this.ltQty = ltQty;
		addValidField("ltQty");
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

	@Column(name = "LAST_LOCATION_TASK_UUID")
	public String getLastLocationTaskUuid() {
		return lastLocationTaskUuid;
	}

	public void setLastLocationTaskUuid(String lastLocationTaskUuid) {
		this.lastLocationTaskUuid = lastLocationTaskUuid;
		addValidField("lastLocationTaskUuid");
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

	@Column(name = "SOURCE_LOT_CODE")
	public String getSourceLotCode() {
		return sourceLotCode;
	}

	public void setSourceLotCode(String sourceLotCode) {
		this.sourceLotCode = sourceLotCode;
		addValidField("sourceLotCode");
	}

	@Column(name = "TARGET_LOT_CODE")
	public String getTargetLotCode() {
		return targetLotCode;
	}

	public void setTargetLotCode(String targetLotCode) {
		this.targetLotCode = targetLotCode;
		addValidField("targetLotCode");
	}

	@Column(name = "LT_BARCODE")
	public String getLtBarcode() {
		return ltBarcode;
	}

	public void setLtBarcode(String ltBarcode) {
		this.ltBarcode = ltBarcode;
		addValidField("ltBarcode");
	}

	@Column(name = "WRH_WORKER")
	public String getWrhWorker() {
		return wrhWorker;
	}

	public void setWrhWorker(String wrhWorker) {
		this.wrhWorker = wrhWorker;
		addValidField("wrhWorker");
	}

	@Column(name = "WRH_CUST")
	public String getWrhCust() {
		return wrhCust;
	}

	public void setWrhCust(String wrhCust) {
		this.wrhCust = wrhCust;
		addValidField("wrhCust");
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		addValidField("remark");
	}

	@Column(name = "OTHER_PK_UUID")
	public String getOtherPkUuid() {
		return otherPkUuid;
	}

	public void setOtherPkUuid(String otherPkUuid) {
		this.otherPkUuid = otherPkUuid;
		addValidField("otherPkUuid");
	}

}
