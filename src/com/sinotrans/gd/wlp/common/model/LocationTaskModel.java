package com.sinotrans.gd.wlp.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.sinotrans.framework.core.model.BaseModelClass;
import com.sinotrans.framework.core.model.OperationLog;

/**
 * Model class for 库内作业任务表
 */
@Entity
@Table(name = "LOCATION_TASK")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class LocationTaskModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "LocationTask";

	public static final class FieldNames {
		/**
		 * 作业任务表UUID
		 */
		public static final String locationTaskUuid = "locationTaskUuid";
		/**
		 * 作业单明细UUID
		 */
		public static final String logisticsOrderDetailUuid = "logisticsOrderDetailUuid";
		/**
		 * 来源作业单货物明细UUID（库存）
		 */
		public static final String inLogisticsOrderDetailUuid = "inLogisticsOrderDetailUuid";
		/**
		 * 库存操作STOCK_WORK的UUID
		 */
		public static final String inStockWorkUuid = "inStockWorkUuid";
		/**
		 * 作业任务号
		 */
		public static final String locTaskNo = "locTaskNo";
		/**
		 * 作业时间
		 */
		public static final String locTaskDate = "locTaskDate";
		/**
		 * 作业类型（收货、上架、移位、加工等）
		 */
		public static final String locTaskType = "locTaskType";
		/**
		 * 作业描述
		 */
		public static final String locTaskDesc = "locTaskDesc";
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
		public static final String unitCode = "unitCode";
		/**
		 * 包装单位描述
		 */
		public static final String unitDesc = "unitDesc";
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
		 * 毛重
		 */
		public static final String grossWeight = "grossWeight";
		/**
		 * 净重
		 */
		public static final String netWeight = "netWeight";
		/**
		 * 体积单位
		 */
		public static final String volumeUnitCode = "volumeUnitCode";
		/**
		 * 体积单位描述
		 */
		public static final String volumeUnitDesc = "volumeUnitDesc";
		/**
		 * 体积
		 */
		public static final String volume = "volume";
		/**
		 * 控制字
		 */
		public static final String controlWord = "controlWord";
		/**
		 * 来源作业任务表UUID
		 */
		public static final String lastLocationTaskUuid = "lastLocationTaskUuid";
		/**
		 * 操作状态
		 */
		public static final String status = "status";
		/**
		 * 货物类型
		 */
		public static final String goodsKind = "goodsKind";
		/**
		 * 货物属性
		 */
		public static final String goodsNature = "goodsNature";
		/**
		 * 危险品UN代码
		 */
		public static final String dangerCode = "dangerCode";
		/**
		 * 包装编码（箱名号）
		 */
		public static final String packageNo = "packageNo";
		/**
		 * 包装类型（箱型）
		 */
		public static final String packageType = "packageType";
		/**
		 * 源货位
		 */
		public static final String sourceLotCode = "sourceLotCode";
		/**
		 * 目的货架
		 */
		public static final String targetLotCode = "targetLotCode";
		/**
		 * 货物条码
		 */
		public static final String barcode = "barcode";
		/**
		 * 托盘号
		 */
		public static final String panelNo = "panelNo";
		/**
		 * 仓管员
		 */
		public static final String wrhWorker = "wrhWorker";
		/**
		 * 货物监管员（理货员）
		 */
		public static final String wrhCust = "wrhCust";
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
		 * 其它单证UUID
		 */
		public static final String otherPkUuid = "otherPkUuid";
		/**
		 * 物料生产日期
		 */
		public static final String productionDate = "productionDate";
	}

	//作业任务表UUID
	private String locationTaskUuid;
	//作业单明细UUID
	private String logisticsOrderDetailUuid;
	//来源作业单货物明细UUID（库存）
	private String inLogisticsOrderDetailUuid;
	//库存操作STOCK_WORK的UUID
	private String inStockWorkUuid;
	//作业任务号
	private String locTaskNo;
	//作业时间
	private Date locTaskDate;
	//作业类型（收货、上架、移位、加工等）
	private String locTaskType;
	//作业描述
	private String locTaskDesc;
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
	private String unitCode;
	//包装单位描述
	private String unitDesc;
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
	//毛重
	private Double grossWeight;
	//净重
	private Double netWeight;
	//体积单位
	private String volumeUnitCode;
	//体积单位描述
	private String volumeUnitDesc;
	//体积
	private Double volume;
	//控制字
	private String controlWord;
	//来源作业任务表UUID
	private String lastLocationTaskUuid;
	//操作状态
	private String status;
	//货物类型
	private String goodsKind;
	//货物属性
	private String goodsNature;
	//危险品UN代码
	private String dangerCode;
	//包装编码（箱名号）
	private String packageNo;
	//包装类型（箱型）
	private String packageType;
	//源货位
	private String sourceLotCode;
	//目的货架
	private String targetLotCode;
	//货物条码
	private String barcode;
	//托盘号
	private String panelNo;
	//仓管员
	private String wrhWorker;
	//货物监管员（理货员）
	private String wrhCust;
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
	//其它单证UUID
	private String otherPkUuid;
	//物料生产日期
	private Date productionDate;

	/**
	 * Get 作业任务表UUID
	 */
	@Column(name = "LOCATION_TASK_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getLocationTaskUuid() {
		return locationTaskUuid;
	}

	/**
	 * Set 作业任务表UUID
	 */
	public void setLocationTaskUuid(String locationTaskUuid) {
		this.locationTaskUuid = locationTaskUuid;
		addValidField(FieldNames.locationTaskUuid);
	}

	/**
	 * Get 作业单明细UUID
	 */
	@Column(name = "LOGISTICS_ORDER_DETAIL_UUID")
	public String getLogisticsOrderDetailUuid() {
		return logisticsOrderDetailUuid;
	}

	/**
	 * Set 作业单明细UUID
	 */
	public void setLogisticsOrderDetailUuid(String logisticsOrderDetailUuid) {
		this.logisticsOrderDetailUuid = logisticsOrderDetailUuid;
		addValidField(FieldNames.logisticsOrderDetailUuid);
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
	 * Get 库存操作STOCK_WORK的UUID
	 */
	@Column(name = "IN_STOCK_WORK_UUID")
	public String getInStockWorkUuid() {
		return inStockWorkUuid;
	}

	/**
	 * Set 库存操作STOCK_WORK的UUID
	 */
	public void setInStockWorkUuid(String inStockWorkUuid) {
		this.inStockWorkUuid = inStockWorkUuid;
		addValidField(FieldNames.inStockWorkUuid);
	}

	/**
	 * Get 作业任务号
	 */
	@Column(name = "LOC_TASK_NO")
	public String getLocTaskNo() {
		return locTaskNo;
	}

	/**
	 * Set 作业任务号
	 */
	public void setLocTaskNo(String locTaskNo) {
		this.locTaskNo = locTaskNo;
		addValidField(FieldNames.locTaskNo);
	}

	/**
	 * Get 作业时间
	 */
	@Column(name = "LOC_TASK_DATE")
	public Date getLocTaskDate() {
		return locTaskDate;
	}

	/**
	 * Set 作业时间
	 */
	public void setLocTaskDate(Date locTaskDate) {
		this.locTaskDate = locTaskDate;
		addValidField(FieldNames.locTaskDate);
	}

	/**
	 * Get 作业类型（收货、上架、移位、加工等）
	 */
	@Column(name = "LOC_TASK_TYPE")
	public String getLocTaskType() {
		return locTaskType;
	}

	/**
	 * Set 作业类型（收货、上架、移位、加工等）
	 */
	public void setLocTaskType(String locTaskType) {
		this.locTaskType = locTaskType;
		addValidField(FieldNames.locTaskType);
	}

	/**
	 * Get 作业描述
	 */
	@Column(name = "LOC_TASK_DESC")
	public String getLocTaskDesc() {
		return locTaskDesc;
	}

	/**
	 * Set 作业描述
	 */
	public void setLocTaskDesc(String locTaskDesc) {
		this.locTaskDesc = locTaskDesc;
		addValidField(FieldNames.locTaskDesc);
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
	@Column(name = "UNIT_CODE")
	public String getUnitCode() {
		return unitCode;
	}

	/**
	 * Set 包装单位代码
	 */
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
		addValidField(FieldNames.unitCode);
	}

	/**
	 * Get 包装单位描述
	 */
	@Column(name = "UNIT_DESC")
	public String getUnitDesc() {
		return unitDesc;
	}

	/**
	 * Set 包装单位描述
	 */
	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc;
		addValidField(FieldNames.unitDesc);
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
	 * Get 体积
	 */
	@Column(name = "VOLUME")
	public Double getVolume() {
		return volume;
	}

	/**
	 * Set 体积
	 */
	public void setVolume(Double volume) {
		this.volume = volume;
		addValidField(FieldNames.volume);
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
	 * Get 来源作业任务表UUID
	 */
	@Column(name = "LAST_LOCATION_TASK_UUID")
	public String getLastLocationTaskUuid() {
		return lastLocationTaskUuid;
	}

	/**
	 * Set 来源作业任务表UUID
	 */
	public void setLastLocationTaskUuid(String lastLocationTaskUuid) {
		this.lastLocationTaskUuid = lastLocationTaskUuid;
		addValidField(FieldNames.lastLocationTaskUuid);
	}

	/**
	 * Get 操作状态
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 操作状态
	 */
	public void setStatus(String status) {
		this.status = status;
		addValidField(FieldNames.status);
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
	 * Get 源货位
	 */
	@Column(name = "SOURCE_LOT_CODE")
	public String getSourceLotCode() {
		return sourceLotCode;
	}

	/**
	 * Set 源货位
	 */
	public void setSourceLotCode(String sourceLotCode) {
		this.sourceLotCode = sourceLotCode;
		addValidField(FieldNames.sourceLotCode);
	}

	/**
	 * Get 目的货架
	 */
	@Column(name = "TARGET_LOT_CODE")
	public String getTargetLotCode() {
		return targetLotCode;
	}

	/**
	 * Set 目的货架
	 */
	public void setTargetLotCode(String targetLotCode) {
		this.targetLotCode = targetLotCode;
		addValidField(FieldNames.targetLotCode);
	}

	/**
	 * Get 货物条码
	 */
	@Column(name = "BARCODE")
	public String getBarcode() {
		return barcode;
	}

	/**
	 * Set 货物条码
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
		addValidField(FieldNames.barcode);
	}

	/**
	 * Get 托盘号
	 */
	@Column(name = "PANEL_NO")
	public String getPanelNo() {
		return panelNo;
	}

	/**
	 * Set 托盘号
	 */
	public void setPanelNo(String panelNo) {
		this.panelNo = panelNo;
		addValidField(FieldNames.panelNo);
	}

	/**
	 * Get 仓管员
	 */
	@Column(name = "WRH_WORKER")
	public String getWrhWorker() {
		return wrhWorker;
	}

	/**
	 * Set 仓管员
	 */
	public void setWrhWorker(String wrhWorker) {
		this.wrhWorker = wrhWorker;
		addValidField(FieldNames.wrhWorker);
	}

	/**
	 * Get 货物监管员（理货员）
	 */
	@Column(name = "WRH_CUST")
	public String getWrhCust() {
		return wrhCust;
	}

	/**
	 * Set 货物监管员（理货员）
	 */
	public void setWrhCust(String wrhCust) {
		this.wrhCust = wrhCust;
		addValidField(FieldNames.wrhCust);
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
	@Column(name = "CREATOR")
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
	@Column(name = "CREATE_TIME", updatable=false)
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
	 * Get 其它单证UUID
	 */
	@Column(name = "OTHER_PK_UUID")
	public String getOtherPkUuid() {
		return otherPkUuid;
	}

	/**
	 * Set 其它单证UUID
	 */
	public void setOtherPkUuid(String otherPkUuid) {
		this.otherPkUuid = otherPkUuid;
		addValidField(FieldNames.otherPkUuid);
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
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="locationTaskUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
