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
import com.sinotrans.gd.wlp.util.CustomDateSerializer;
import com.sinotrans.gd.wlp.util.CustomDateYMDHMSSerializer;

/**
 * Model class for 条码表
 */
@Entity
@Table(name = "BARCODE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BarcodeModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "Barcode";

	public static final class FieldNames {
		/**
		 * 条码表UUID
		 */
		public static final String barcodeUuid = "barcodeUuid";
		/**
		 * 上级条码UUID
		 */
		public static final String preBarcodeUuid = "preBarcodeUuid";
		/**
		 * 作业单明细UUID
		 */
		public static final String logisticsOrderDetailUuid = "logisticsOrderDetailUuid";
		/**
		 * 作业单号
		 */
		public static final String logisticsOrderNo = "logisticsOrderNo";
		/**
		 * 作业任务表UUID
		 */
		public static final String inStockWorkUuid = "inStockWorkUuid";
		/**
		 * 条码（包装条码、物料条码、托盘条码等）
		 */
		public static final String barcode = "barcode";
		/**
		 * 条码内容的描述
		 */
		public static final String barcodeDesc = "barcodeDesc";
		/**
		 * 其它条码
		 */
		public static final String extBarcode = "extBarcode";
		/**
		 * 型号
		 */
		public static final String model = "model";
		/**
		 * 规格
		 */
		public static final String spec = "spec";
		/**
		 * 托盘号
		 */
		public static final String panelNo = "panelNo";
		/**
		 * 数量（打印标签用）
		 */
		public static final String qty = "qty";
		/**
		 * 包装单位代码（打印标签用）
		 */
		public static final String qtyUnitCode = "qtyUnitCode";
		/**
		 * 包装单位描述（打印标签用）
		 */
		public static final String qtyUnitDesc = "qtyUnitDesc";
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
		 * 重量单位
		 */
		public static final String weightUnitCode = "weightUnitCode";
		/**
		 * 重量单位
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
		 * 体积单位
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
		 * 币种代码
		 */
		public static final String currencyCode = "currencyCode";
		/**
		 * 币种描述
		 */
		public static final String currencyDesc = "currencyDesc";
		/**
		 * 合计
		 */
		public static final String totalPrice = "totalPrice";
		/**
		 * 备注
		 */
		public static final String remark = "remark";
		/**
		 * 状态
		 */
		public static final String status = "status";
		/**
		 * 控制字
		 */
		public static final String controlWord = "controlWord";
		/**
		 * 创建日期
		 */
		public static final String creatorDate = "creatorDate";
		/**
		 * 作废日期
		 */
		public static final String cancelDate = "cancelDate";
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
		 * 生产日期
		 */
		public static final String productionDate = "productionDate";
		/**
		 * 批次号
		 */
		public static final String batchNo = "batchNo";
	}

	// 条码表UUID
	private String barcodeUuid;
	// 上级条码UUID
	private String preBarcodeUuid;
	// 作业单明细UUID
	private String logisticsOrderDetailUuid;
	// 作业单号
	private String logisticsOrderNo;
	// 作业任务表UUID
	private String inStockWorkUuid;
	// 条码（包装条码、物料条码、托盘条码等）
	private String barcode;
	// 条码内容的描述
	private String barcodeDesc;
	// 其它条码
	private String extBarcode;
	// 型号
	private String model;
	// 规格
	private String spec;
	// 托盘号
	private String panelNo;
	// 数量（打印标签用）
	private Double qty;
	// 包装单位代码（打印标签用）
	private String qtyUnitCode;
	// 包装单位描述（打印标签用）
	private String qtyUnitDesc;
	// 长度单位
	private String lengthUnitCode;
	// 长度单位
	private String lengthUnitDesc;
	// 长
	private Double length;
	// 宽
	private Double width;
	// 高
	private Double height;
	// 重量单位
	private String weightUnitCode;
	// 重量单位
	private String weightUnitDesc;
	// 净重
	private Double netWeight;
	// 毛重
	private Double grossWeight;
	// 体积单位
	private String volumeUnitCode;
	// 体积单位
	private String volumeUnitDesc;
	// VOLUME
	private Double volume;
	// 单价
	private Double unitPrice;
	// 币种代码
	private String currencyCode;
	// 币种描述
	private String currencyDesc;
	// 合计
	private Double totalPrice;
	// 备注
	private String remark;
	// 状态
	private String status;
	// 控制字
	private String controlWord;
	// 创建日期
	private Date creatorDate;
	// 作废日期
	private Date cancelDate;
	// 自定义字段1
	private String aux1;
	// 自定义字段2
	private String aux2;
	// 自定义字段3
	private String aux3;
	// 自定义字段4
	private String aux4;
	// 自定义字段5
	private String aux5;
	// 公司（仓库）代码
	private String officeCode;
	// 并发访问控制
	private Long recVer;
	// 创建人
	private String creator;
	// 创建时间
	private Date createTime;
	// 修改人
	private String modifier;
	// 修改时间
	private Date modifyTime;
	// 生产日期
	private Date productionDate;
	// 批次号
	private String batchNo;

	/**
	 * Get 条码表UUID
	 */
	@Column(name = "BARCODE_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getBarcodeUuid() {
		return barcodeUuid;
	}

	/**
	 * Set 条码表UUID
	 */
	public void setBarcodeUuid(String barcodeUuid) {
		this.barcodeUuid = barcodeUuid;
		addValidField(FieldNames.barcodeUuid);
	}

	/**
	 * Get 上级条码UUID
	 */
	@Column(name = "PRE_BARCODE_UUID")
	public String getPreBarcodeUuid() {
		return preBarcodeUuid;
	}

	/**
	 * Set 上级条码UUID
	 */
	public void setPreBarcodeUuid(String preBarcodeUuid) {
		this.preBarcodeUuid = preBarcodeUuid;
		addValidField(FieldNames.preBarcodeUuid);
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
	 * Get 作业单号
	 */
	@Column(name = "LOGISTICS_ORDER_NO")
	public String getLogisticsOrderNo() {
		return logisticsOrderNo;
	}

	/**
	 * Set 作业单号
	 */
	public void setLogisticsOrderNo(String logisticsOrderNo) {
		this.logisticsOrderNo = logisticsOrderNo;
		addValidField(FieldNames.logisticsOrderNo);
	}

	/**
	 * Get 作业任务表UUID
	 */
	@Column(name = "IN_STOCK_WORK_UUID")
	public String getInStockWorkUuid() {
		return inStockWorkUuid;
	}

	/**
	 * Set 作业任务表UUID
	 */
	public void setInStockWorkUuid(String inStockWorkUuid) {
		this.inStockWorkUuid = inStockWorkUuid;
		addValidField(FieldNames.inStockWorkUuid);
	}

	/**
	 * Get 条码（包装条码、物料条码、托盘条码等）
	 */
	@Column(name = "BARCODE")
	public String getBarcode() {
		return barcode;
	}

	/**
	 * Set 条码（包装条码、物料条码、托盘条码等）
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
		addValidField(FieldNames.barcode);
	}

	/**
	 * Get 条码内容的描述
	 */
	@Column(name = "BARCODE_DESC")
	public String getBarcodeDesc() {
		return barcodeDesc;
	}

	/**
	 * Set 条码内容的描述
	 */
	public void setBarcodeDesc(String barcodeDesc) {
		this.barcodeDesc = barcodeDesc;
		addValidField(FieldNames.barcodeDesc);
	}

	/**
	 * Get 其它条码
	 */
	@Column(name = "EXT_BARCODE")
	public String getExtBarcode() {
		return extBarcode;
	}

	/**
	 * Set 其它条码
	 */
	public void setExtBarcode(String extBarcode) {
		this.extBarcode = extBarcode;
		addValidField(FieldNames.extBarcode);
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
	 * Get 数量（打印标签用）
	 */
	@Column(name = "QTY")
	public Double getQty() {
		return qty;
	}

	/**
	 * Set 数量（打印标签用）
	 */
	public void setQty(Double qty) {
		this.qty = qty;
		addValidField(FieldNames.qty);
	}

	/**
	 * Get 包装单位代码（打印标签用）
	 */
	@Column(name = "QTY_UNIT_CODE")
	public String getQtyUnitCode() {
		return qtyUnitCode;
	}

	/**
	 * Set 包装单位代码（打印标签用）
	 */
	public void setQtyUnitCode(String qtyUnitCode) {
		this.qtyUnitCode = qtyUnitCode;
		addValidField(FieldNames.qtyUnitCode);
	}

	/**
	 * Get 包装单位描述（打印标签用）
	 */
	@Column(name = "QTY_UNIT_DESC")
	public String getQtyUnitDesc() {
		return qtyUnitDesc;
	}

	/**
	 * Set 包装单位描述（打印标签用）
	 */
	public void setQtyUnitDesc(String qtyUnitDesc) {
		this.qtyUnitDesc = qtyUnitDesc;
		addValidField(FieldNames.qtyUnitDesc);
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
	 * Get 重量单位
	 */
	@Column(name = "WEIGHT_UNIT_DESC")
	public String getWeightUnitDesc() {
		return weightUnitDesc;
	}

	/**
	 * Set 重量单位
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
	 * Get 体积单位
	 */
	@Column(name = "VOLUME_UNIT_DESC")
	public String getVolumeUnitDesc() {
		return volumeUnitDesc;
	}

	/**
	 * Set 体积单位
	 */
	public void setVolumeUnitDesc(String volumeUnitDesc) {
		this.volumeUnitDesc = volumeUnitDesc;
		addValidField(FieldNames.volumeUnitDesc);
	}

	/**
	 * Get VOLUME 体积(立方米)
	 */
	@Column(name = "VOLUME")
	public Double getVolume() {
		return volume;
	}

	/**
	 * Set VOLUME 体积(立方米)
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
	 * Get 状态 ：Active - 有效； Cancel - 作废
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态 ：Active - 有效； Cancel - 作废
	 */
	public void setStatus(String status) {
		this.status = status;
		addValidField(FieldNames.status);
	}

	/**
	 * Get 控制字 ：默认0
	 */
	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	/**
	 * Set 控制字 ：默认0
	 */
	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField(FieldNames.controlWord);
	}

	/**
	 * Get 创建日期
	 */
	@Column(name = "CREATOR_DATE")
	@JsonSerialize(using = CustomDateYMDHMSSerializer.class)
	public Date getCreatorDate() {
		return creatorDate;
	}

	/**
	 * Set 创建日期
	 */
	public void setCreatorDate(Date creatorDate) {
		this.creatorDate = creatorDate;
		addValidField(FieldNames.creatorDate);
	}

	/**
	 * Get 作废日期
	 */
	@Column(name = "CANCEL_DATE")
	public Date getCancelDate() {
		return cancelDate;
	}

	/**
	 * Set 作废日期
	 */
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
		addValidField(FieldNames.cancelDate);
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
	 * Get 生产日期
	 */
	@Column(name = "PRODUCTION_DATE")
	public Date getProductionDate() {
		return productionDate;
	}

	/**
	 * Set 生产日期
	 */
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
		addValidField(FieldNames.productionDate);
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
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="barcodeUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
