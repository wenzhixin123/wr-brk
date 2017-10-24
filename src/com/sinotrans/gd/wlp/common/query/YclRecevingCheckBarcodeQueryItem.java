package com.sinotrans.gd.wlp.common.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class YclRecevingCheckBarcodeQueryItem extends BaseQueryItem {

	private String locationTaskUuid;
	private String logisticsOrderDetailUuid;
	private String inLogisticsOrderDetailUuid;
	private String inStockWorkUuid;
	private String locTaskNo;
	private Date locTaskDate;
	private String locTaskType;
	private String locTaskDesc;
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
	private String unitCode;
	private String unitDesc;
	private Double secondQty;
	private String secondUnitCode;
	private String secondUnitDesc;
	private Double thirdQty;
	private String thirdUnitCode;
	private String thirdUnitDesc;
	private String weightUnitCode;
	private String weightUnitDesc;
	private Double grossWeight;
	private Double netWeight;
	private String volumeUnitCode;
	private String volumeUnitDesc;
	private Double volume;
	private String controlWord;
	private String lastLocationTaskUuid;
	private String status;
	private String goodsKind;
	private String goodsNature;
	private String dangerCode;
	private String packageNo;
	private String packageType;
	private String sourceLotCode;
	private String targetLotCode;
	private String barcode;
	private String panelNo;
	private String wrhWorker;
	private String wrhCust;
	private String remark;
	private String aux1;
	private String aux2;
	private String aux3;
	private String aux4;
	private String aux5;
	private String officeCode;
	private String otherPkUuid;
	private Date productionDate;
	private String lodCw;
	
	@Column(name = "LOD_CW")
	public String getLodCw() {
		return lodCw;
	}

	public void setLodCw(String lodCw) {
		this.lodCw = lodCw;
		addValidField("lodCw");
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

	@Column(name = "IN_STOCK_WORK_UUID")
	public String getInStockWorkUuid() {
		return inStockWorkUuid;
	}

	public void setInStockWorkUuid(String inStockWorkUuid) {
		this.inStockWorkUuid = inStockWorkUuid;
		addValidField("inStockWorkUuid");
	}

	@Column(name = "LOC_TASK_NO")
	public String getLocTaskNo() {
		return locTaskNo;
	}

	public void setLocTaskNo(String locTaskNo) {
		this.locTaskNo = locTaskNo;
		addValidField("locTaskNo");
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

	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField("controlWord");
	}

	@Column(name = "LAST_LOCATION_TASK_UUID")
	public String getLastLocationTaskUuid() {
		return lastLocationTaskUuid;
	}

	public void setLastLocationTaskUuid(String lastLocationTaskUuid) {
		this.lastLocationTaskUuid = lastLocationTaskUuid;
		addValidField("lastLocationTaskUuid");
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		addValidField("status");
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

	@Column(name = "OTHER_PK_UUID")
	public String getOtherPkUuid() {
		return otherPkUuid;
	}

	public void setOtherPkUuid(String otherPkUuid) {
		this.otherPkUuid = otherPkUuid;
		addValidField("otherPkUuid");
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
