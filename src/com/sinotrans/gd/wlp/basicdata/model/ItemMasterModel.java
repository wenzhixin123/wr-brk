package com.sinotrans.gd.wlp.basicdata.model;

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
 * Model class for 物料资料主表
 */
@Entity
@Table(name = "ITEM_MASTER")
public class ItemMasterModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "ItemMaster";

	public static final class FieldNames {
		/**
		 * 物料主表UUID
		 */
		public static final String itemMasterUuid = "itemMasterUuid";
		/**
		 * 货物属性UUID
		 */
		public static final String itemNatureCode = "itemNatureCode";
		/**
		 * 货物种类UUID
		 */
		public static final String itemKindCode = "itemKindCode";
		/**
		 * 客户代码
		 */
		public static final String customerCode = "customerCode";
		/**
		 * 客户名称
		 */
		public static final String customerName = "customerName";
		/**
		 * 货物编码
		 */
		public static final String itemCode = "itemCode";
		/**
		 * 货物名称
		 */
		public static final String itemName = "itemName";
		/**
		 * 物料描述
		 */
		public static final String itemNameEn = "itemNameEn";
		/**
		 * 物料简称
		 */
		public static final String itemShortname = "itemShortname";
		/**
		 * 外部物料编码
		 */
		public static final String extItemCode = "extItemCode";
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
		 * 体积
		 */
		public static final String volume = "volume";
		/**
		 * 备注
		 */
		public static final String remark = "remark";
		/**
		 * 控制字
		 */
		public static final String controlWord = "controlWord";
		/**
		 * 状态
		 */
		public static final String status = "status";
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
		 * 商家代码
		 */
		public static final String cargoConsigneeCode = "cargoConsigneeCode";
		/**
		 * 商家名称
		 */
		public static final String cargoConsigneeDesc = "cargoConsigneeDesc";
		/**
		 * WLB商品ID
		 */
		public static final String itemId = "itemId";
		/**
		 * 商品版本
		 */
		public static final String itemVersion = "itemVersion";
	}

	//物料主表UUID
	private String itemMasterUuid;
	//货物属性UUID
	private String itemNatureCode;
	//货物种类UUID
	private String itemKindCode;
	//客户代码
	private String customerCode;
	//客户名称
	private String customerName;
	//货物编码
	private String itemCode;
	//货物名称
	private String itemName;
	//物料描述
	private String itemNameEn;
	//物料简称
	private String itemShortname;
	//外部物料编码
	private String extItemCode;
	//型号
	private String model;
	//规格
	private String spec;
	//长度单位
	private String lengthUnitCode;
	//长
	private Double length;
	//宽
	private Double width;
	//高
	private Double height;
	//重量单位
	private String weightUnitCode;
	//净重
	private Double netWeight;
	//毛重
	private Double grossWeight;
	//体积单位
	private String volumeUnitCode;
	//体积
	private Double volume;
	//备注
	private String remark;
	//控制字
	private String controlWord;
	//状态
	private String status;
	//作废日期
	private Date cancelDate;
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
	//商家代码
	private String cargoConsigneeCode;
	//商家名称
	private String cargoConsigneeDesc;
	//WLB商品ID
	private String itemId;
	//商品版本
	private String itemVersion;

	/**
	 * Get 物料主表UUID
	 */
	@Column(name = "ITEM_MASTER_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getItemMasterUuid() {
		return itemMasterUuid;
	}

	/**
	 * Set 物料主表UUID
	 */
	public void setItemMasterUuid(String itemMasterUuid) {
		this.itemMasterUuid = itemMasterUuid;
		addValidField(FieldNames.itemMasterUuid);
	}

	/**
	 * Get 货物属性CODE
	 */
	@Column(name = "ITEM_NATURE_CODE")
	public String getItemNatureCode() {
		return itemNatureCode;
	}

	/**
	 * Set 货物属性CODE
	 */
	public void setItemNatureCode(String itemNatureCode) {
		this.itemNatureCode = itemNatureCode;
		addValidField(FieldNames.itemNatureCode);
	}

	/**
	 * Get 货物种类CODE
	 */
	@Column(name = "ITEM_KIND_CODE")
	public String getItemKindCode() {
		return itemKindCode;
	}

	/**
	 * Set 货物种类CODE
	 */
	public void setItemKindCode(String itemKindCode) {
		this.itemKindCode = itemKindCode;
		addValidField(FieldNames.itemKindCode);
	}

	/**
	 * Get 客户代码
	 */
	@Column(name = "CUSTOMER_CODE")
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * Set 客户代码
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
		addValidField(FieldNames.customerCode);
	}

	/**
	 * Get 客户名称
	 */
	@Column(name = "CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Set 客户名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
		addValidField(FieldNames.customerName);
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
	 * Get 货物名称
	 */
	@Column(name = "ITEM_NAME")
	public String getItemName() {
		return itemName;
	}

	/**
	 * Set 货物名称
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
		addValidField(FieldNames.itemName);
	}

	/**
	 * Get 物料描述
	 */
	@Column(name = "ITEM_NAME_EN")
	public String getItemNameEn() {
		return itemNameEn;
	}

	/**
	 * Set 物料描述
	 */
	public void setItemNameEn(String itemNameEn) {
		this.itemNameEn = itemNameEn;
		addValidField(FieldNames.itemNameEn);
	}

	/**
	 * Get 物料简称
	 */
	@Column(name = "ITEM_SHORTNAME")
	public String getItemShortname() {
		return itemShortname;
	}

	/**
	 * Set 物料简称
	 */
	public void setItemShortname(String itemShortname) {
		this.itemShortname = itemShortname;
		addValidField(FieldNames.itemShortname);
	}

	/**
	 * Get 外部物料编码
	 */
	@Column(name = "EXT_ITEM_CODE")
	public String getExtItemCode() {
		return extItemCode;
	}

	/**
	 * Set 外部物料编码
	 */
	public void setExtItemCode(String extItemCode) {
		this.extItemCode = extItemCode;
		addValidField(FieldNames.extItemCode);
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
	 * Get 状态
	 * ：ACTIVE - 有效； Cancel - 作废
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态
	 * ：ACTIVE - 有效； Cancel - 作废
	 */
	public void setStatus(String status) {
		this.status = status;
		addValidField(FieldNames.status);
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
	 * Get 商家代码
	 */
	@Column(name = "CARGO_CONSIGNEE_CODE")
	public String getCargoConsigneeCode() {
		return cargoConsigneeCode;
	}

	/**
	 * Set 商家代码
	 */
	public void setCargoConsigneeCode(String cargoConsigneeCode) {
		this.cargoConsigneeCode = cargoConsigneeCode;
		addValidField(FieldNames.cargoConsigneeCode);
	}

	/**
	 * Get 商家名称
	 */
	@Column(name = "CARGO_CONSIGNEE_DESC")
	public String getCargoConsigneeDesc() {
		return cargoConsigneeDesc;
	}

	/**
	 * Set 商家名称
	 */
	public void setCargoConsigneeDesc(String cargoConsigneeDesc) {
		this.cargoConsigneeDesc = cargoConsigneeDesc;
		addValidField(FieldNames.cargoConsigneeDesc);
	}

	/**
	 * Get WLB商品ID
	 */
	@Column(name = "ITEM_ID")
	public String getItemId() {
		return itemId;
	}

	/**
	 * Set WLB商品ID
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
		addValidField(FieldNames.itemId);
	}

	/**
	 * Get 商品版本
	 * ，WLB允许货主修改商品属性描述，每修改一次就对应一个商品版本信息
	 */
	@Column(name = "ITEM_VERSION")
	public String getItemVersion() {
		return itemVersion;
	}

	/**
	 * Set 商品版本
	 * ，WLB允许货主修改商品属性描述，每修改一次就对应一个商品版本信息
	 */
	public void setItemVersion(String itemVersion) {
		this.itemVersion = itemVersion;
		addValidField(FieldNames.itemVersion);
	}
	/**
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="itemMasterUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
