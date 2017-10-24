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
 * Model class for 仓库信息表
 */
@Entity
@Table(name = "BAS_WAREHOUSE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasWarehouseModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasWarehouse";

	public static final class FieldNames {
		/**
		 * 仓库UUID
		 */
		public static final String basWarehouseUuid = "basWarehouseUuid";
		/**
		 * basWarehouseTypeUuid
		 */
		public static final String basWarehouseTypeUuid = "basWarehouseTypeUuid";
		/**
		 * preBasWarehouseUuid
		 */
		public static final String preBasWarehouseUuid = "preBasWarehouseUuid";
		/**
		 * 仓库编号
		 */
		public static final String warehouseCode = "warehouseCode";
		/**
		 * 仓库名称
		 */
		public static final String warehouseName = "warehouseName";
		/**
		 * warehouseNameEn
		 */
		public static final String warehouseNameEn = "warehouseNameEn";
		/**
		 * 仓库容量(立方)
		 */
		public static final String warehouseCapacity = "warehouseCapacity";
		/**
		 * 仓库地址
		 */
		public static final String warehouseAddress = "warehouseAddress";
		/**
		 * 服务区域
		 */
		public static final String serviceArea = "serviceArea";
		/**
		 * 时效
		 */
		public static final String serviceAging = "serviceAging";
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
		 * 状态
		 */
		public static final String status = "status";
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

	// 仓库UUID
	private String basWarehouseUuid;
	// basWarehouseTypeUuid
	private String basWarehouseTypeUuid;
	// preBasWarehouseUuid
	private String preBasWarehouseUuid;
	// 仓库编号
	private String warehouseCode;
	// 仓库名称
	private String warehouseName;
	// warehouseNameEn
	private String warehouseNameEn;
	// 仓库容量(立方)
	private Double warehouseCapacity;
	// 仓库地址
	private String warehouseAddress;
	// 服务区域
	private String serviceArea;
	// 时效
	private Double serviceAging;
	// 控制字
	private String controlWord;
	// 备注
	private String remark;
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
	// 状态
	private String status;
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
	 * Get 仓库UUID
	 */
	@Column(name = "BAS_WAREHOUSE_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getBasWarehouseUuid() {
		return basWarehouseUuid;
	}

	/**
	 * Set 仓库UUID
	 */
	public void setBasWarehouseUuid(String basWarehouseUuid) {
		this.basWarehouseUuid = basWarehouseUuid;
		addValidField(FieldNames.basWarehouseUuid);
	}

	/**
	 * Get basWarehouseTypeUuid
	 */
	@Column(name = "BAS_WAREHOUSE_TYPE_UUID")
	public String getBasWarehouseTypeUuid() {
		return basWarehouseTypeUuid;
	}

	/**
	 * Set basWarehouseTypeUuid
	 */
	public void setBasWarehouseTypeUuid(String basWarehouseTypeUuid) {
		this.basWarehouseTypeUuid = basWarehouseTypeUuid;
		addValidField(FieldNames.basWarehouseTypeUuid);
	}

	/**
	 * Get preBasWarehouseUuid
	 */
	@Column(name = "PRE_BAS_WAREHOUSE_UUID")
	public String getPreBasWarehouseUuid() {
		return preBasWarehouseUuid;
	}

	/**
	 * Set preBasWarehouseUuid
	 */
	public void setPreBasWarehouseUuid(String preBasWarehouseUuid) {
		this.preBasWarehouseUuid = preBasWarehouseUuid;
		addValidField(FieldNames.preBasWarehouseUuid);
	}

	/**
	 * Get 仓库编号
	 */
	@Column(name = "WAREHOUSE_CODE")
	public String getWarehouseCode() {
		return warehouseCode;
	}

	/**
	 * Set 仓库编号
	 */
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
		addValidField(FieldNames.warehouseCode);
	}

	/**
	 * Get 仓库名称
	 */
	@Column(name = "WAREHOUSE_NAME")
	public String getWarehouseName() {
		return warehouseName;
	}

	/**
	 * Set 仓库名称
	 */
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
		addValidField(FieldNames.warehouseName);
	}

	/**
	 * Get warehouseNameEn
	 */
	@Column(name = "WAREHOUSE_NAME_EN")
	public String getWarehouseNameEn() {
		return warehouseNameEn;
	}

	/**
	 * Set warehouseNameEn
	 */
	public void setWarehouseNameEn(String warehouseNameEn) {
		this.warehouseNameEn = warehouseNameEn;
		addValidField(FieldNames.warehouseNameEn);
	}

	/**
	 * Get 仓库容量(立方)
	 */
	@Column(name = "WAREHOUSE_CAPACITY")
	public Double getWarehouseCapacity() {
		return warehouseCapacity;
	}

	/**
	 * Set 仓库容量(立方)
	 */
	public void setWarehouseCapacity(Double warehouseCapacity) {
		this.warehouseCapacity = warehouseCapacity;
		addValidField(FieldNames.warehouseCapacity);
	}

	/**
	 * Get 仓库地址
	 */
	@Column(name = "WAREHOUSE_ADDRESS")
	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	/**
	 * Set 仓库地址
	 */
	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
		addValidField(FieldNames.warehouseAddress);
	}

	/**
	 * Get 服务区域
	 */
	@Column(name = "SERVICE_AREA")
	public String getServiceArea() {
		return serviceArea;
	}

	/**
	 * Set 服务区域
	 */
	public void setServiceArea(String serviceArea) {
		this.serviceArea = serviceArea;
		addValidField(FieldNames.serviceArea);
	}

	/**
	 * Get 时效
	 */
	@Column(name = "SERVICE_AGING")
	public Double getServiceAging() {
		return serviceAging;
	}

	/**
	 * Set 时效
	 */
	public void setServiceAging(Double serviceAging) {
		this.serviceAging = serviceAging;
		addValidField(FieldNames.serviceAging);
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
	private String prrmaryKeyName="basWarehouseUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
	
}
