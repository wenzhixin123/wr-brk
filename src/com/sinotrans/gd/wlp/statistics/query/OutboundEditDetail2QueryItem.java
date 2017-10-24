package com.sinotrans.gd.wlp.statistics.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class OutboundEditDetail2QueryItem extends BaseQueryItem {

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
	private String itemCode;
	private String itemSeqno;
	private String batchNo;
	private String marksNumber;
	private String barcode;
	private String unitCode;
	private String unitDesc;
	private String secondUnitCode;
	private String secondUnitDesc;
	private String thirdUnitCode;
	private String thirdUnitDesc;
	private Double thirdQty;
	private Double qty;
	private Double secondQty;
	private Double grossWeight;
	private Double netWeight;
	private Double volume;
	private Double remainQtyHoldQtySum;
	private Double remainSecondQtyHoldSum;
	private String lengthUnitDesc;
	private String urgentOrderNo;
	private String qtyUnitDesc;
	private String qtyUnitCode;
	private String volumeUnitCode;
	private String volumeUnitDesc;
	private String weightUnitCode;
	private String weightUnitDesc;
	private String unitPrice;
	private String totalPrice;
	private String currencyCode;
	private String currencyDesc;
	private String remainHoldUuid;
	private String kindDesc;
	private String projectCode;
	private String projectName;
	private String flow;
	private String remark;
	private String extItemCode;
	private String createTime;
	private String aux2;
	private String aux1;
	private String cargoConsigneeDesc;
	private String orderNo;
	private String productionDate;
	private String cargoControlDesc;//2014年7月17日 19:22:29 深圳物流 + 控货方
	private String cargoControlCode;//2014年7月17日 19:22:29 深圳物流 + 控货方
	private String targetCustomerName;//2014年8月21日 20:25:30 wj+ 深圳物流·目标客户
	private Double rsLength;
	private Double rsWidth;
	private Double rsHeight;
	private String controlWord;
    private String cargoConsigneeCode;
    private Double remainQty;
    private String aux5;
    private String targetLotCode;
    
    
    @Column(name="TARGET_LOT_CODE")
    public String getTargetLotCode() {
		return targetLotCode;
	}

	public void setTargetLotCode(String targetLotCode) {
		this.targetLotCode = targetLotCode;
		addValidField("targetLotCode");
	}

	@Column(name="AUX5")
	public String getAux5() {
		return aux5;
	}

	public void setAux5(String aux5) {
		this.aux5 = aux5;
		addValidField("aux5");
	}

	@Column(name="REMAIN_QTY")
    public Double getRemainQty() {
		return remainQty;
	}

	public void setRemainQty(Double remainQty) {
		this.remainQty = remainQty;
		addValidField("remainQty");
	}
	
    @Column(name="CARGO_CONSIGNEE_CODE")
	public String getCargoConsigneeCode() {
		return cargoConsigneeCode;
	}

    public void setCargoConsigneeCode(String cargoConsigneeCode) {
		this.cargoConsigneeCode = cargoConsigneeCode;
		addValidField(cargoConsigneeCode);
	}

	@Column(name="TARGET_CUSTOMER_NAME")
	public String getTargetCustomerName() {
		return targetCustomerName;
	}

	public void setTargetCustomerName(String targetCustomerName) {
		this.targetCustomerName = targetCustomerName;
		addValidField(targetCustomerName);
	}
	
	@Column(name="CARGO_CONTROL_CODE")
	public String getCargoControlCode() {
		return cargoControlCode;
	}
	
	public void setCargoControlCode(String cargoControlCode) {
		this.cargoControlCode = cargoControlCode;
		addValidField(cargoControlCode);
	}
	
	@Column(name="CARGO_CONTROL_DESC")
	public String getCargoControlDesc() {
		return cargoControlDesc;
	}
	
	public void setCargoControlDesc(String cargoControlDesc) {
		this.cargoControlDesc = cargoControlDesc;
		addValidField("cargoControlDesc");
	}
	
	@Column(name="PRODUCTION_DATE")
	public String getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
		addValidField("productionDate");
	}
	@Column(name="ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
		addValidField("orderNo");
	}
	
	@Column(name="CARGO_CONSIGNEE_DESC")
	public String getCargoConsigneeDesc() {
		return cargoConsigneeDesc;
	}
	public void setCargoConsigneeDesc(String cargoConsigneeDesc) {
		this.cargoConsigneeDesc = cargoConsigneeDesc;
		addValidField("cargoConsigneeDesc");
		
	}
	@Column(name = "AUX1")
	public String getAux1() {
		return aux1;
	}
	public void setAux1(String aux1) {
		this.aux1 = aux1;
		addValidField("aux1");
	}
	
	@Column(name = "flow")
	public String getFlow() {
		return flow;
	}
	public void setFlow(String flow) {
		this.flow = flow;
		addValidField("flow");
	}
	
	@Column(name = "IN_LOGISTICS_ORDER_DETAIL_UUID")
	public String getInLogisticsOrderDetailUuid() {
		return inLogisticsOrderDetailUuid;
	}

	public void setInLogisticsOrderDetailUuid(String inLogisticsOrderDetailUuid) {
		this.inLogisticsOrderDetailUuid = inLogisticsOrderDetailUuid;
		addValidField("inLogisticsOrderDetailUuid");
	}
	@Column(name = "Project_Code")
	public String getProjectCode() {
		return projectCode;
	}
	
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
		addValidField("projectCode");
	}
	@Column(name = "projectName")
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
		addValidField("projectName");
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

	@Column(name = "QTY")
	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
		addValidField("qty");
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

	@Column(name = "qty_unit_desc")
	public String getQtyUnitDesc() {
		return qtyUnitDesc;
	}

	public void setQtyUnitDesc(String qtyUnitDesc) {
		this.qtyUnitDesc = qtyUnitDesc;
		addValidField("qtyUnitDesc");
	}

	@Column(name = "volume_unit_code")
	public String getVolumeUnitCode() {
		return volumeUnitCode;
	}

	public void setVolumeUnitCode(String volumeUnitCode) {
		this.volumeUnitCode = volumeUnitCode;
		addValidField("volumeUnitCode");
	}

	@Column(name = "volume_unit_desc")
	public String getVolumeUnitDesc() {
		return volumeUnitDesc;
	}

	public void setVolumeUnitDesc(String volumeUnitDesc) {
		this.volumeUnitDesc = volumeUnitDesc;
		addValidField("volumeUnitDesc");
	}

	@Column(name = "weight_unit_code")
	public String getWeightUnitCode() {
		return weightUnitCode;
	}

	public void setWeightUnitCode(String weightUnitCode) {
		this.weightUnitCode = weightUnitCode;
		addValidField("weightUnitCode");
	}

	@Column(name = "weight_unit_desc")
	public String getWeightUnitDesc() {
		return weightUnitDesc;
	}

	public void setWeightUnitDesc(String weightUnitDesc) {
		this.weightUnitDesc = weightUnitDesc;
		addValidField("weightUnitDesc");
	}

	@Column(name = "unit_price")
	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
		addValidField("unitPrice");
	}

	@Column(name = "currency_code")
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
		addValidField("currencyCode");
	}

	@Column(name = "currency_desc")
	public String getCurrencyDesc() {
		return currencyDesc;
	}

	public void setCurrencyDesc(String currencyDesc) {
		this.currencyDesc = currencyDesc;
		addValidField("currencyDesc");
	}

	@Column(name = "qty_unit_code")
	public String getQtyUnitCode() {
		return qtyUnitCode;
	}

	public void setQtyUnitCode(String qtyUnitCode) {
		this.qtyUnitCode = qtyUnitCode;
		addValidField("qtyUnitCode");
	}

	@Column(name = "total_price")
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
		addValidField("totalPrice");
	}

	@Column(name = "remain_hold_uuid")
	public String getRemainHoldUuid() {
		return remainHoldUuid;
	}

	public void setRemainHoldUuid(String remainHoldUuid) {
		this.remainHoldUuid = remainHoldUuid;
		addValidField("remainHoldUuid");
	}

	@Column(name = "kind_desc")
	public String getKindDesc() {
		return kindDesc;
	}

	public void setKindDesc(String kindDesc) {
		this.kindDesc = kindDesc;
		addValidField("kindDesc");
	}

	@Column(name = "Length_Unit_Desc")
	public String getLengthUnitDesc() {
		return lengthUnitDesc;
	}

	public void setLengthUnitDesc(String lengthUnitDesc) {
		this.lengthUnitDesc = lengthUnitDesc;
		addValidField("lengthUnitDesc");
	}
	
	@Column(name = "urgent_order_no")
	public String getUrgentOrderNo() {
		return urgentOrderNo;
	}
	
	public void setUrgentOrderNo(String urgentOrderNo) {
		this.urgentOrderNo = urgentOrderNo;
		addValidField("urgentOrderNo");
	}
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name = "EXT_ITEM_CODE")
	public String getExtItemCode() {
		return extItemCode;
	}
	public void setExtItemCode(String extItemCode) {
		this.extItemCode = extItemCode;
	}
	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name = "AUX2")
	public String getAux2() {
		return aux2;
	}
	public void setAux2(String aux2) {
		this.aux2 = aux2;
	}

	@Column(name = "RS_LENGTH")
	public Double getRsLength() {
		return rsLength;
	}

	public void setRsLength(Double rsLength) {
		this.rsLength = rsLength;
	}

	@Column(name = "RS_WIDTH")
	public Double getRsWidth() {
		return rsWidth;
	}

	public void setRsWidth(Double rsWidth) {
		this.rsWidth = rsWidth;
	}

	@Column(name = "RS_HEIGHT")
	public Double getRsHeight() {
		return rsHeight;
	}

	public void setRsHeight(Double rsHeight) {
		this.rsHeight = rsHeight;
	}

	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
	}

}
