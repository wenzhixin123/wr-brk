package com.sinotrans.gd.wlp.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.sinotrans.framework.core.model.BaseModelClass;
import com.sinotrans.framework.core.model.OperationLog;
import com.sinotrans.gd.wlp.util.CustomDateYMDHMSSerializer;

/**
 * Model class for 委托订单货物细项
 */
@Entity
@Table(name = "SUBMIT_ORDER_DETAIL")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SubmitOrderDetailModel extends BaseModelClass implements
		OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "SubmitOrderDetail";

	public static final class FieldNames {
		/**
		 * 委托单明细UUID
		 */
		public static final String submitOrderDetailUuid = "submitOrderDetailUuid";
		/**
		 * 委托单UUID
		 */
		public static final String submitOrderUuid = "submitOrderUuid";
		/**
		 * 委托单号
		 */
		public static final String submitOrderNo = "submitOrderNo";
		/**
		 * 序号
		 */
		public static final String seqNo = "seqNo";
		/**
		 * 运单号
		 */
		public static final String billNo = "billNo";
		/**
		 * 批次号
		 */
		public static final String batchNo = "batchNo";
		/**
		 * 货物编码
		 */
		public static final String itemCode = "itemCode";
		/**
		 * 物料序列号
		 */
		public static final String itemSeqno = "itemSeqno";
		/**
		 * 货物编码
		 */
		public static final String extItemCode = "extItemCode";
		/**
		 * 货物名称
		 */
		public static final String goodsDesc = "goodsDesc";
		/**
		 * 唛头
		 */
		public static final String marksNumber = "marksNumber";
		/**
		 * 型号
		 */
		public static final String model = "model";
		/**
		 * 规格
		 */
		public static final String spec = "spec";
		/**
		 * 长度单位
		 */
		public static final String lengthUnitCode = "lengthUnitCode";
		/**
		 * 长度单位
		 */
		public static final String lengthUnitDesc = "lengthUnitDesc";
		/**
		 * 长
		 */
		public static final String length = "length";
		/**
		 * 宽
		 */
		public static final String width = "width";
		/**
		 * 高
		 */
		public static final String height = "height";
		/**
		 * 数量
		 */
		public static final String qty = "qty";
		/**
		 * 包装单位代码
		 */
		public static final String qtyUnitCode = "qtyUnitCode";
		/**
		 * 包装单位描述
		 */
		public static final String qtyUnitDesc = "qtyUnitDesc";
		/**
		 * 第二包装数量
		 */
		public static final String secondQty = "secondQty";
		/**
		 * 第二包装单位代码
		 */
		public static final String secondUnitCode = "secondUnitCode";
		/**
		 * 第二包装单位描述
		 */
		public static final String secondUnitDesc = "secondUnitDesc";
		/**
		 * 第三包装数量
		 */
		public static final String thirdQty = "thirdQty";
		/**
		 * 第三包装单位代码
		 */
		public static final String thirdUnitCode = "thirdUnitCode";
		/**
		 * 第三包装单位描述
		 */
		public static final String thirdUnitDesc = "thirdUnitDesc";
		/**
		 * 重量单位
		 */
		public static final String weightUnitCode = "weightUnitCode";
		/**
		 * 重量单位描述
		 */
		public static final String weightUnitDesc = "weightUnitDesc";
		/**
		 * 净重
		 */
		public static final String netWeight = "netWeight";
		/**
		 * 毛重
		 */
		public static final String grossWeight = "grossWeight";
		/**
		 * 体积单位
		 */
		public static final String volumeUnitCode = "volumeUnitCode";
		/**
		 * 体积单位描述
		 */
		public static final String volumeUnitDesc = "volumeUnitDesc";
		/**
		 * VOLUME
		 */
		public static final String volume = "volume";
		/**
		 * 单价
		 */
		public static final String unitPrice = "unitPrice";
		/**
		 * 合计
		 */
		public static final String totalPrice = "totalPrice";
		/**
		 * 币种代码
		 */
		public static final String currencyCode = "currencyCode";
		/**
		 * 币种描述
		 */
		public static final String currencyDesc = "currencyDesc";
		/**
		 * 危险品UN代码
		 */
		public static final String dangerCode = "dangerCode";
		/**
		 * 交付数量
		 */
		public static final String deliveredQty = "deliveredQty";
		/**
		 * 确认数量
		 */
		public static final String confirmedQty = "confirmedQty";
		/**
		 * 货物类型
		 */
		public static final String goodsKind = "goodsKind";
		/**
		 * 货物属性
		 */
		public static final String goodsNature = "goodsNature";
		/**
		 * 包装编码（箱名号）
		 */
		public static final String packageNo = "packageNo";
		/**
		 * 包装类型（箱型）
		 */
		public static final String packageType = "packageType";
		/**
		 * bomCode
		 */
		public static final String bomCode = "bomCode";
		/**
		 * 操作状态
		 */
		public static final String transactionStatus = "transactionStatus";
		/**
		 * 操作类型
		 */
		public static final String transactionType = "transactionType";
		/**
		 * 控制字
		 */
		public static final String controlWord = "controlWord";
		/**
		 * 备注
		 */
		public static final String remark = "remark";
		/**
		 * 自定义字段1
		 */
		public static final String aux1 = "aux1";
		/**
		 * 自定义字段2
		 */
		public static final String aux2 = "aux2";
		/**
		 * 自定义字段3
		 */
		public static final String aux3 = "aux3";
		/**
		 * 自定义字段4
		 */
		public static final String aux4 = "aux4";
		/**
		 * 自定义字段5
		 */
		public static final String aux5 = "aux5";
		/**
		 * 公司（仓库）代码
		 */
		public static final String officeCode = "officeCode";
		/**
		 * 并发访问控制
		 */
		public static final String recVer = "recVer";
		/**
		 * 创建人
		 */
		public static final String creator = "creator";
		/**
		 * 创建时间
		 */
		public static final String createTime = "createTime";
		/**
		 * 修改人
		 */
		public static final String modifier = "modifier";
		/**
		 * 修改时间
		 */
		public static final String modifyTime = "modifyTime";
		/**
		 * 物料生产日期
		 */
		public static final String productionDate = "productionDate";
		/**
		 * 卖家ID
		 */
		public static final String userId = "userId";
		
		/**
		 * 来源作业单货物明细UUID（库存）
		 */
		public static final String inLogisticsOrderDetailUuid = "inLogisticsOrderDetailUuid";
		
		/**
		 * 装序
		 */
		public static final String loadSeqNo = "loadSeqNo";
	}

	//委托单明细UUID
	private String submitOrderDetailUuid;
	//委托单UUID
	private String submitOrderUuid;
	//来源作业单货物明细UUID（库存）
	private String inLogisticsOrderDetailUuid;
	//委托单号
	private String submitOrderNo;
	//序号
	private Long seqNo;
	//运单号
	private String billNo;
	//批次号
	private String batchNo;
	//货物编码
	private String itemCode;
	//物料序列号
	private String itemSeqno;
	//货物编码
	private String extItemCode;
	//货物名称
	private String goodsDesc;
	//唛头
	private String marksNumber;
	//型号
	private String model;
	//规格
	private String spec;
	//长度单位
	private String lengthUnitCode;
	//长度单位
	private String lengthUnitDesc;
	//长
	private Double length;
	//宽
	private Double width;
	//高
	private Double height;
	//数量
	private Double qty;
	//包装单位代码
	private String qtyUnitCode;
	//包装单位描述
	private String qtyUnitDesc;
	//第二包装数量
	private Double secondQty;
	//第二包装单位代码
	private String secondUnitCode;
	//第二包装单位描述
	private String secondUnitDesc;
	//第三包装数量
	private Double thirdQty;
	//第三包装单位代码
	private String thirdUnitCode;
	//第三包装单位描述
	private String thirdUnitDesc;
	//重量单位
	private String weightUnitCode;
	//重量单位描述
	private String weightUnitDesc;
	//净重
	private Double netWeight;
	//毛重
	private Double grossWeight;
	//体积单位
	private String volumeUnitCode;
	//体积单位描述
	private String volumeUnitDesc;
	//VOLUME
	private Double volume;
	//单价
	private Double unitPrice;
	//合计
	private Double totalPrice;
	//币种代码
	private String currencyCode;
	//币种描述
	private String currencyDesc;
	//危险品UN代码
	private String dangerCode;
	//交付数量
	private Double deliveredQty;
	//确认数量
	private Double confirmedQty;
	//货物类型
	private String goodsKind;
	//货物属性
	private String goodsNature;
	//包装编码（箱名号）
	private String packageNo;
	//包装类型（箱型）
	private String packageType;
	//bomCode
	private String bomCode;
	//操作状态
	private String transactionStatus;
	//操作类型
	private String transactionType;
	//控制字
	private String controlWord;
	//备注
	private String remark;
	//自定义字段1
	private String aux1;
	//自定义字段2
	private String aux2;
	//自定义字段3
	private String aux3;
	//自定义字段4
	private String aux4;
	//自定义字段5
	private String aux5;
	//公司（仓库）代码
	private String officeCode;
	//并发访问控制
	private Long recVer;
	//创建人
	private String creator;
	//创建时间
	private Date createTime;
	//修改人
	private String modifier;
	//修改时间
	private Date modifyTime;
	//物料生产日期
	private Date productionDate;
	//卖家ID
	private Long userId;
	  //装序
  	private Long loadSeqNo;

	/**
	 * Get 委托单明细UUID
	 */
	@Column(name = "SUBMIT_ORDER_DETAIL_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getSubmitOrderDetailUuid() {
		return submitOrderDetailUuid;
	}

	/**
	 * Set 委托单明细UUID
	 */
	public void setSubmitOrderDetailUuid(String submitOrderDetailUuid) {
		this.submitOrderDetailUuid = submitOrderDetailUuid;
		addValidField(FieldNames.submitOrderDetailUuid);
	}

	/**
	 * Get 委托单UUID
	 */
	@Column(name = "SUBMIT_ORDER_UUID")
	public String getSubmitOrderUuid() {
		return submitOrderUuid;
	}

	/**
	 * Set 委托单UUID
	 */
	public void setSubmitOrderUuid(String submitOrderUuid) {
		this.submitOrderUuid = submitOrderUuid;
		addValidField(FieldNames.submitOrderUuid);
	}

	/**
	 * Get 委托单号
	 */
	@Column(name = "SUBMIT_ORDER_NO")
	public String getSubmitOrderNo() {
		return submitOrderNo;
	}

	/**
	 * Set 委托单号
	 */
	public void setSubmitOrderNo(String submitOrderNo) {
		this.submitOrderNo = submitOrderNo;
		addValidField(FieldNames.submitOrderNo);
	}

	/**
	 * Get 序号
	 */
	@Column(name = "SEQ_NO")
	public Long getSeqNo() {
		return seqNo;
	}

	/**
	 * Set 序号
	 */
	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
		addValidField(FieldNames.seqNo);
	}

	/**
	 * Get 运单号
	 */
	@Column(name = "BILL_NO")
	public String getBillNo() {
		return billNo;
	}

	/**
	 * Set 运单号
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
		addValidField(FieldNames.billNo);
	}

	/**
	 * Get 批次号
	 */
	@Column(name = "BATCH_NO")
	public String getBatchNo() {
		return batchNo;
	}

	/**
	 * Set 批次号
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
		addValidField(FieldNames.batchNo);
	}

	/**
	 * Get 货物编码
	 */
	@Column(name = "ITEM_CODE")
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * Set 货物编码
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
		addValidField(FieldNames.itemCode);
	}

	/**
	 * Get 物料序列号
	 */
	@Column(name = "ITEM_SEQNO")
	public String getItemSeqno() {
		return itemSeqno;
	}

	/**
	 * Set 物料序列号
	 */
	public void setItemSeqno(String itemSeqno) {
		this.itemSeqno = itemSeqno;
		addValidField(FieldNames.itemSeqno);
	}

	/**
	 * Get 货物编码
	 */
	@Column(name = "EXT_ITEM_CODE")
	public String getExtItemCode() {
		return extItemCode;
	}

	/**
	 * Set 货物编码
	 */
	public void setExtItemCode(String extItemCode) {
		this.extItemCode = extItemCode;
		addValidField(FieldNames.extItemCode);
	}

	/**
	 * Get 货物名称
	 */
	@Column(name = "GOODS_DESC")
	public String getGoodsDesc() {
		return goodsDesc;
	}

	/**
	 * Set 货物名称
	 */
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
		addValidField(FieldNames.goodsDesc);
	}

	/**
	 * Get 唛头
	 */
	@Column(name = "MARKS_NUMBER")
	public String getMarksNumber() {
		return marksNumber;
	}

	/**
	 * Set 唛头
	 */
	public void setMarksNumber(String marksNumber) {
		this.marksNumber = marksNumber;
		addValidField(FieldNames.marksNumber);
	}

	/**
	 * Get 型号
	 */
	@Column(name = "MODEL")
	public String getModel() {
		return model;
	}

	/**
	 * Set 型号
	 */
	public void setModel(String model) {
		this.model = model;
		addValidField(FieldNames.model);
	}

	/**
	 * Get 规格
	 */
	@Column(name = "SPEC")
	public String getSpec() {
		return spec;
	}

	/**
	 * Set 规格
	 */
	public void setSpec(String spec) {
		this.spec = spec;
		addValidField(FieldNames.spec);
	}

	/**
	 * Get 长度单位
	 */
	@Column(name = "LENGTH_UNIT_CODE")
	public String getLengthUnitCode() {
		return lengthUnitCode;
	}

	/**
	 * Set 长度单位
	 */
	public void setLengthUnitCode(String lengthUnitCode) {
		this.lengthUnitCode = lengthUnitCode;
		addValidField(FieldNames.lengthUnitCode);
	}

	/**
	 * Get 长度单位
	 */
	@Column(name = "LENGTH_UNIT_DESC")
	public String getLengthUnitDesc() {
		return lengthUnitDesc;
	}

	/**
	 * Set 长度单位
	 */
	public void setLengthUnitDesc(String lengthUnitDesc) {
		this.lengthUnitDesc = lengthUnitDesc;
		addValidField(FieldNames.lengthUnitDesc);
	}

	/**
	 * Get 长
	 */
	@Column(name = "LENGTH")
	public Double getLength() {
		return length;
	}

	/**
	 * Set 长
	 */
	public void setLength(Double length) {
		this.length = length;
		addValidField(FieldNames.length);
	}

	/**
	 * Get 宽
	 */
	@Column(name = "WIDTH")
	public Double getWidth() {
		return width;
	}

	/**
	 * Set 宽
	 */
	public void setWidth(Double width) {
		this.width = width;
		addValidField(FieldNames.width);
	}

	/**
	 * Get 高
	 */
	@Column(name = "HEIGHT")
	public Double getHeight() {
		return height;
	}

	/**
	 * Set 高
	 */
	public void setHeight(Double height) {
		this.height = height;
		addValidField(FieldNames.height);
	}

	/**
	 * Get 数量
	 */
	@Column(name = "QTY")
	public Double getQty() {
		return qty;
	}

	/**
	 * Set 数量
	 */
	public void setQty(Double qty) {
		this.qty = qty;
		addValidField(FieldNames.qty);
	}

	/**
	 * Get 包装单位代码
	 */
	@Column(name = "QTY_UNIT_CODE")
	public String getQtyUnitCode() {
		return qtyUnitCode;
	}

	/**
	 * Set 包装单位代码
	 */
	public void setQtyUnitCode(String qtyUnitCode) {
		this.qtyUnitCode = qtyUnitCode;
		addValidField(FieldNames.qtyUnitCode);
	}

	/**
	 * Get 包装单位描述
	 */
	@Column(name = "QTY_UNIT_DESC")
	public String getQtyUnitDesc() {
		return qtyUnitDesc;
	}

	/**
	 * Set 包装单位描述
	 */
	public void setQtyUnitDesc(String qtyUnitDesc) {
		this.qtyUnitDesc = qtyUnitDesc;
		addValidField(FieldNames.qtyUnitDesc);
	}

	/**
	 * Get 第二包装数量
	 */
	@Column(name = "SECOND_QTY")
	public Double getSecondQty() {
		return secondQty;
	}

	/**
	 * Set 第二包装数量
	 */
	public void setSecondQty(Double secondQty) {
		this.secondQty = secondQty;
		addValidField(FieldNames.secondQty);
	}

	/**
	 * Get 第二包装单位代码
	 */
	@Column(name = "SECOND_UNIT_CODE")
	public String getSecondUnitCode() {
		return secondUnitCode;
	}

	/**
	 * Set 第二包装单位代码
	 */
	public void setSecondUnitCode(String secondUnitCode) {
		this.secondUnitCode = secondUnitCode;
		addValidField(FieldNames.secondUnitCode);
	}

	/**
	 * Get 第二包装单位描述
	 */
	@Column(name = "SECOND_UNIT_DESC")
	public String getSecondUnitDesc() {
		return secondUnitDesc;
	}

	/**
	 * Set 第二包装单位描述
	 */
	public void setSecondUnitDesc(String secondUnitDesc) {
		this.secondUnitDesc = secondUnitDesc;
		addValidField(FieldNames.secondUnitDesc);
	}

	/**
	 * Get 第三包装数量
	 */
	@Column(name = "THIRD_QTY")
	public Double getThirdQty() {
		return thirdQty;
	}

	/**
	 * Set 第三包装数量
	 */
	public void setThirdQty(Double thirdQty) {
		this.thirdQty = thirdQty;
		addValidField(FieldNames.thirdQty);
	}

	/**
	 * Get 第三包装单位代码
	 */
	@Column(name = "THIRD_UNIT_CODE")
	public String getThirdUnitCode() {
		return thirdUnitCode;
	}

	/**
	 * Set 第三包装单位代码
	 */
	public void setThirdUnitCode(String thirdUnitCode) {
		this.thirdUnitCode = thirdUnitCode;
		addValidField(FieldNames.thirdUnitCode);
	}

	/**
	 * Get 第三包装单位描述
	 */
	@Column(name = "THIRD_UNIT_DESC")
	public String getThirdUnitDesc() {
		return thirdUnitDesc;
	}

	/**
	 * Set 第三包装单位描述
	 */
	public void setThirdUnitDesc(String thirdUnitDesc) {
		this.thirdUnitDesc = thirdUnitDesc;
		addValidField(FieldNames.thirdUnitDesc);
	}

	/**
	 * Get 重量单位
	 */
	@Column(name = "WEIGHT_UNIT_CODE")
	public String getWeightUnitCode() {
		return weightUnitCode;
	}

	/**
	 * Set 重量单位
	 */
	public void setWeightUnitCode(String weightUnitCode) {
		this.weightUnitCode = weightUnitCode;
		addValidField(FieldNames.weightUnitCode);
	}

	/**
	 * Get 重量单位描述
	 */
	@Column(name = "WEIGHT_UNIT_DESC")
	public String getWeightUnitDesc() {
		return weightUnitDesc;
	}

	/**
	 * Set 重量单位描述
	 */
	public void setWeightUnitDesc(String weightUnitDesc) {
		this.weightUnitDesc = weightUnitDesc;
		addValidField(FieldNames.weightUnitDesc);
	}

	/**
	 * Get 净重
	 */
	@Column(name = "NET_WEIGHT")
	public Double getNetWeight() {
		return netWeight;
	}

	/**
	 * Set 净重
	 */
	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
		addValidField(FieldNames.netWeight);
	}

	/**
	 * Get 毛重
	 */
	@Column(name = "GROSS_WEIGHT")
	public Double getGrossWeight() {
		return grossWeight;
	}

	/**
	 * Set 毛重
	 */
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
		addValidField(FieldNames.grossWeight);
	}

	/**
	 * Get 体积单位
	 */
	@Column(name = "VOLUME_UNIT_CODE")
	public String getVolumeUnitCode() {
		return volumeUnitCode;
	}

	/**
	 * Set 体积单位
	 */
	public void setVolumeUnitCode(String volumeUnitCode) {
		this.volumeUnitCode = volumeUnitCode;
		addValidField(FieldNames.volumeUnitCode);
	}

	/**
	 * Get 体积单位描述
	 */
	@Column(name = "VOLUME_UNIT_DESC")
	public String getVolumeUnitDesc() {
		return volumeUnitDesc;
	}

	/**
	 * Set 体积单位描述
	 */
	public void setVolumeUnitDesc(String volumeUnitDesc) {
		this.volumeUnitDesc = volumeUnitDesc;
		addValidField(FieldNames.volumeUnitDesc);
	}

	/**
	 * Get VOLUME
	 * 体积(立方米)
	 */
	@Column(name = "VOLUME")
	public Double getVolume() {
		return volume;
	}

	/**
	 * Set VOLUME
	 * 体积(立方米)
	 */
	public void setVolume(Double volume) {
		this.volume = volume;
		addValidField(FieldNames.volume);
	}

	/**
	 * Get 单价
	 */
	@Column(name = "UNIT_PRICE")
	public Double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * Set 单价
	 */
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
		addValidField(FieldNames.unitPrice);
	}

	/**
	 * Get 合计
	 */
	@Column(name = "TOTAL_PRICE")
	public Double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * Set 合计
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
		addValidField(FieldNames.totalPrice);
	}

	/**
	 * Get 币种代码
	 */
	@Column(name = "CURRENCY_CODE")
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * Set 币种代码
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
		addValidField(FieldNames.currencyCode);
	}

	/**
	 * Get 币种描述
	 */
	@Column(name = "CURRENCY_DESC")
	public String getCurrencyDesc() {
		return currencyDesc;
	}

	/**
	 * Set 币种描述
	 */
	public void setCurrencyDesc(String currencyDesc) {
		this.currencyDesc = currencyDesc;
		addValidField(FieldNames.currencyDesc);
	}

	/**
	 * Get 危险品UN代码
	 */
	@Column(name = "DANGER_CODE")
	public String getDangerCode() {
		return dangerCode;
	}

	/**
	 * Set 危险品UN代码
	 */
	public void setDangerCode(String dangerCode) {
		this.dangerCode = dangerCode;
		addValidField(FieldNames.dangerCode);
	}

	/**
	 * Get 交付数量
	 */
	@Column(name = "DELIVERED_QTY")
	public Double getDeliveredQty() {
		return deliveredQty;
	}

	/**
	 * Set 交付数量
	 */
	public void setDeliveredQty(Double deliveredQty) {
		this.deliveredQty = deliveredQty;
		addValidField(FieldNames.deliveredQty);
	}

	/**
	 * Get 确认数量
	 */
	@Column(name = "CONFIRMED_QTY")
	public Double getConfirmedQty() {
		return confirmedQty;
	}

	/**
	 * Set 确认数量
	 */
	public void setConfirmedQty(Double confirmedQty) {
		this.confirmedQty = confirmedQty;
		addValidField(FieldNames.confirmedQty);
	}

	/**
	 * Get 货物类型
	 */
	@Column(name = "GOODS_KIND")
	public String getGoodsKind() {
		return goodsKind;
	}

	/**
	 * Set 货物类型
	 */
	public void setGoodsKind(String goodsKind) {
		this.goodsKind = goodsKind;
		addValidField(FieldNames.goodsKind);
	}

	/**
	 * Get 货物属性
	 */
	@Column(name = "GOODS_NATURE")
	public String getGoodsNature() {
		return goodsNature;
	}

	/**
	 * Set 货物属性
	 */
	public void setGoodsNature(String goodsNature) {
		this.goodsNature = goodsNature;
		addValidField(FieldNames.goodsNature);
	}

	/**
	 * Get 包装编码（箱名号）
	 */
	@Column(name = "PACKAGE_NO")
	public String getPackageNo() {
		return packageNo;
	}

	/**
	 * Set 包装编码（箱名号）
	 */
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
		addValidField(FieldNames.packageNo);
	}

	/**
	 * Get 包装类型（箱型）
	 */
	@Column(name = "PACKAGE_TYPE")
	public String getPackageType() {
		return packageType;
	}

	/**
	 * Set 包装类型（箱型）
	 */
	public void setPackageType(String packageType) {
		this.packageType = packageType;
		addValidField(FieldNames.packageType);
	}

	/**
	 * Get bomCode
	 */
	@Column(name = "BOM_CODE")
	public String getBomCode() {
		return bomCode;
	}

	/**
	 * Set bomCode
	 */
	public void setBomCode(String bomCode) {
		this.bomCode = bomCode;
		addValidField(FieldNames.bomCode);
	}

	/**
	 * Get 操作状态
	 */
	@Column(name = "TRANSACTION_STATUS")
	public String getTransactionStatus() {
		return transactionStatus;
	}

	/**
	 * Set 操作状态
	 */
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
		addValidField(FieldNames.transactionStatus);
	}

	/**
	 * Get 操作类型
	 */
	@Column(name = "TRANSACTION_TYPE")
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * Set 操作类型
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
		addValidField(FieldNames.transactionType);
	}

	/**
	 * Get 控制字
	 */
	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	/**
	 * Set 控制字
	 */
	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField(FieldNames.controlWord);
	}

	/**
	 * Get 备注
	 */
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	/**
	 * Set 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
		addValidField(FieldNames.remark);
	}

	/**
	 * Get 自定义字段1
	 */
	@Column(name = "AUX1")
	public String getAux1() {
		return aux1;
	}

	/**
	 * Set 自定义字段1
	 */
	public void setAux1(String aux1) {
		this.aux1 = aux1;
		addValidField(FieldNames.aux1);
	}

	/**
	 * Get 自定义字段2
	 */
	@Column(name = "AUX2")
	public String getAux2() {
		return aux2;
	}

	/**
	 * Set 自定义字段2
	 */
	public void setAux2(String aux2) {
		this.aux2 = aux2;
		addValidField(FieldNames.aux2);
	}

	/**
	 * Get 自定义字段3
	 */
	@Column(name = "AUX3")
	public String getAux3() {
		return aux3;
	}

	/**
	 * Set 自定义字段3
	 */
	public void setAux3(String aux3) {
		this.aux3 = aux3;
		addValidField(FieldNames.aux3);
	}

	/**
	 * Get 自定义字段4
	 */
	@Column(name = "AUX4")
	public String getAux4() {
		return aux4;
	}

	/**
	 * Set 自定义字段4
	 */
	public void setAux4(String aux4) {
		this.aux4 = aux4;
		addValidField(FieldNames.aux4);
	}

	/**
	 * Get 自定义字段5
	 */
	@Column(name = "AUX5")
	public String getAux5() {
		return aux5;
	}

	/**
	 * Set 自定义字段5
	 */
	public void setAux5(String aux5) {
		this.aux5 = aux5;
		addValidField(FieldNames.aux5);
	}

	/**
	 * Get 公司（仓库）代码
	 */
	@Column(name = "OFFICE_CODE")
	public String getOfficeCode() {
		return officeCode;
	}

	/**
	 * Set 公司（仓库）代码
	 */
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
		addValidField(FieldNames.officeCode);
	}

	/**
	 * Get 并发访问控制
	 */
	@Column(name = "REC_VER")
	@Version
	public Long getRecVer() {
		return recVer;
	}

	/**
	 * Set 并发访问控制
	 */
	public void setRecVer(Long recVer) {
		this.recVer = recVer;
		addValidField(FieldNames.recVer);
	}

	/**
	 * Get 创建人
	 */
	@Column(name = "CREATOR", updatable=false)
	public String getCreator() {
		return creator;
	}

	/**
	 * Set 创建人
	 */
	public void setCreator(String creator) {
		this.creator = creator;
		addValidField(FieldNames.creator);
	}

	/**
	 * Get 创建时间
	 */
	@Column(name = "CREATE_TIME", updatable=false )
	@JsonSerialize(using = CustomDateYMDHMSSerializer.class)
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * Set 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		addValidField(FieldNames.createTime);
	}

	/**
	 * Get 修改人
	 */
	@Column(name = "MODIFIER")
	public String getModifier() {
		return modifier;
	}

	/**
	 * Set 修改人
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
		addValidField(FieldNames.modifier);
	}

	/**
	 * Get 修改时间
	 */
	@Column(name = "MODIFY_TIME")
	@JsonSerialize(using = CustomDateYMDHMSSerializer.class)
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * Set 修改时间
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
		addValidField(FieldNames.modifyTime);
	}

	/**
	 * Get 物料生产日期
	 */
	@Column(name = "PRODUCTION_DATE")
	public Date getProductionDate() {
		return productionDate;
	}

	/**
	 * Set 物料生产日期
	 */
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
		addValidField(FieldNames.productionDate);
	}

	/**
	 * Get 卖家ID
	 * 一般情况下，货主ID和卖家ID相同 WLB
	 */
	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	/**
	 * Set 卖家ID
	 * 一般情况下，货主ID和卖家ID相同 WLB
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
		addValidField(FieldNames.userId);
	}

	
	/**
	 * Get 来源作业单货物明细UUID（库存）
	 */
	@Column(name = "IN_LOGISTICS_ORDER_DETAIL_UUID")
	public String getInLogisticsOrderDetailUuid() {
		return inLogisticsOrderDetailUuid;
	}

	/**
	 * Set 来源作业单货物明细UUID（库存）
	 */
	public void setInLogisticsOrderDetailUuid(String inLogisticsOrderDetailUuid) {
		this.inLogisticsOrderDetailUuid = inLogisticsOrderDetailUuid;
		addValidField(FieldNames.inLogisticsOrderDetailUuid);
	}
	
	/**
	 * Get 装序
	 */
	@Column(name = "LOAD_SEQ_NO")
	public Long getLoadSeqNo() {
		return loadSeqNo;
	}

	/**
	 * Set 装序
	 */
	public void setLoadSeqNo(Long loadSeqNo) {
		this.loadSeqNo = loadSeqNo;
		addValidField(FieldNames.loadSeqNo);
	}

	

	/**
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="submitOrderDetailUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}


}
