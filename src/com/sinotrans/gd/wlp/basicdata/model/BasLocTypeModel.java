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
 * Model class for 仓库货位类型
 */
@Entity
@Table(name = "BAS_LOC_TYPE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasLocTypeModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasLocType";

	public static final class FieldNames {
		/**
		 * 货位类型UUID
		 */
		public static final String basBasLocTypeUuid = "basBasLocTypeUuid";
		/**
		 * 货位类型代码
		 */
		public static final String locTypeCode = "locTypeCode";
		/**
		 * 货位类型名称
		 */
		public static final String locTypeName = "locTypeName";
		/**
		 * 货位类型描述
		 */
		public static final String locTypeNameEn = "locTypeNameEn";
		/**
		 * 优先级
		 */
		public static final String pri = "pri";
		/**
		 * 上架优先级
		 */
		public static final String putPri = "putPri";
		/**
		 * 拣货优先级
		 */
		public static final String pickPri = "pickPri";
		/**
		 * 最大堆放层数
		 */
		public static final String maxPalletFloor = "maxPalletFloor";
		/**
		 * 最大体积
		 */
		public static final String maxVoluem = "maxVoluem";
		/**
		 * 最大重量
		 */
		public static final String maxWeight = "maxWeight";
		/**
		 * 最大托盘数
		 */
		public static final String maxPalletQty = "maxPalletQty";
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
	}

	// 货位类型UUID
	private String basBasLocTypeUuid;
	// 货位类型代码
	private String locTypeCode;
	// 货位类型名称
	private String locTypeName;
	// 货位类型描述
	private String locTypeNameEn;
	// 优先级
	private Long pri;
	// 上架优先级
	private Long putPri;
	// 拣货优先级
	private Long pickPri;
	// 最大堆放层数
	private Long maxPalletFloor;
	// 最大体积
	private Double maxVoluem;
	// 最大重量
	private Double maxWeight;
	// 最大托盘数
	private Long maxPalletQty;
	// 长
	private Double length;
	// 宽
	private Double width;
	// 高
	private Double height;
	// 备注
	private String remark;
	// 状态
	private String status;
	// 控制字
	private String controlWord;
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

	/**
	 * Get 货位类型UUID
	 */
	@Column(name = "BAS_BAS_LOC_TYPE_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getBasBasLocTypeUuid() {
		return basBasLocTypeUuid;
	}

	/**
	 * Set 货位类型UUID
	 */
	public void setBasBasLocTypeUuid(String basBasLocTypeUuid) {
		this.basBasLocTypeUuid = basBasLocTypeUuid;
		addValidField(FieldNames.basBasLocTypeUuid);
	}

	/**
	 * Get 货位类型代码
	 */
	@Column(name = "LOC_TYPE_CODE")
	public String getLocTypeCode() {
		return locTypeCode;
	}

	/**
	 * Set 货位类型代码
	 */
	public void setLocTypeCode(String locTypeCode) {
		this.locTypeCode = locTypeCode;
		addValidField(FieldNames.locTypeCode);
	}

	/**
	 * Get 货位类型名称
	 */
	@Column(name = "LOC_TYPE_NAME")
	public String getLocTypeName() {
		return locTypeName;
	}

	/**
	 * Set 货位类型名称
	 */
	public void setLocTypeName(String locTypeName) {
		this.locTypeName = locTypeName;
		addValidField(FieldNames.locTypeName);
	}

	/**
	 * Get 货位类型描述
	 */
	@Column(name = "LOC_TYPE_NAME_EN")
	public String getLocTypeNameEn() {
		return locTypeNameEn;
	}

	/**
	 * Set 货位类型描述
	 */
	public void setLocTypeNameEn(String locTypeNameEn) {
		this.locTypeNameEn = locTypeNameEn;
		addValidField(FieldNames.locTypeNameEn);
	}

	/**
	 * Get 优先级
	 */
	@Column(name = "PRI")
	public Long getPri() {
		return pri;
	}

	/**
	 * Set 优先级
	 */
	public void setPri(Long pri) {
		this.pri = pri;
		addValidField(FieldNames.pri);
	}

	/**
	 * Get 上架优先级
	 */
	@Column(name = "PUT_PRI")
	public Long getPutPri() {
		return putPri;
	}

	/**
	 * Set 上架优先级
	 */
	public void setPutPri(Long putPri) {
		this.putPri = putPri;
		addValidField(FieldNames.putPri);
	}

	/**
	 * Get 拣货优先级
	 */
	@Column(name = "PICK_PRI")
	public Long getPickPri() {
		return pickPri;
	}

	/**
	 * Set 拣货优先级
	 */
	public void setPickPri(Long pickPri) {
		this.pickPri = pickPri;
		addValidField(FieldNames.pickPri);
	}

	/**
	 * Get 最大堆放层数
	 */
	@Column(name = "MAX_PALLET_FLOOR")
	public Long getMaxPalletFloor() {
		return maxPalletFloor;
	}

	/**
	 * Set 最大堆放层数
	 */
	public void setMaxPalletFloor(Long maxPalletFloor) {
		this.maxPalletFloor = maxPalletFloor;
		addValidField(FieldNames.maxPalletFloor);
	}

	/**
	 * Get 最大体积
	 */
	@Column(name = "MAX_VOLUEM")
	public Double getMaxVoluem() {
		return maxVoluem;
	}

	/**
	 * Set 最大体积
	 */
	public void setMaxVoluem(Double maxVoluem) {
		this.maxVoluem = maxVoluem;
		addValidField(FieldNames.maxVoluem);
	}

	/**
	 * Get 最大重量
	 */
	@Column(name = "MAX_WEIGHT")
	public Double getMaxWeight() {
		return maxWeight;
	}

	/**
	 * Set 最大重量
	 */
	public void setMaxWeight(Double maxWeight) {
		this.maxWeight = maxWeight;
		addValidField(FieldNames.maxWeight);
	}

	/**
	 * Get 最大托盘数
	 */
	@Column(name = "MAX_PALLET_QTY")
	public Long getMaxPalletQty() {
		return maxPalletQty;
	}

	/**
	 * Set 最大托盘数
	 */
	public void setMaxPalletQty(Long maxPalletQty) {
		this.maxPalletQty = maxPalletQty;
		addValidField(FieldNames.maxPalletQty);
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
	 * Get 状态 ：ACTIVE - 有效； Cancel - 作废
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态 ：ACTIVE - 有效； Cancel - 作废
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
	@Column(name = "CREATE_TIME")
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
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="basBasLocTypeUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
