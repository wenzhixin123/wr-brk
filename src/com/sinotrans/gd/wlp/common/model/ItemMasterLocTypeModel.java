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
 * Model class for 物料存储货位数量表
 */
@Entity
@Table(name = "ITEM_MASTER_LOC_TYPE")

public class ItemMasterLocTypeModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "ItemMasterLocType";

	public static final class FieldNames {
		/**
		 * 物料存储货位数量UUID
		 */
		public static final String itemMasterLocTypeUuid = "itemMasterLocTypeUuid";
		/**
		 * 物料主表UUID
		 */
		public static final String itemMasterUuid = "itemMasterUuid";
		/**
		 * 货位类型代码
		 */
		public static final String locTypeCode = "locTypeCode";
		/**
		 * 货位类型名称
		 */
		public static final String locTypeName = "locTypeName";
		/**
		 * 货物包装单位
		 */
		public static final String stockUnitCode = "stockUnitCode";
		/**
		 * 存储数量
		 */
		public static final String stockQty = "stockQty";
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

	//物料存储货位数量UUID
	private String itemMasterLocTypeUuid;
	//物料主表UUID
	private String itemMasterUuid;
	//货位类型代码
	private String locTypeCode;
	//货位类型名称
	private String locTypeName;
	//货物包装单位
	private String stockUnitCode;
	//存储数量
	private Double stockQty;
	//备注
	private String remark;
	//状态
	private String status;
	//控制字
	private String controlWord;
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

	/**
	 * Get 物料存储货位数量UUID
	 */
	@Column(name = "ITEM_MASTER_LOC_TYPE_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getItemMasterLocTypeUuid() {
		return itemMasterLocTypeUuid;
	}

	/**
	 * Set 物料存储货位数量UUID
	 */
	public void setItemMasterLocTypeUuid(String itemMasterLocTypeUuid) {
		this.itemMasterLocTypeUuid = itemMasterLocTypeUuid;
		addValidField(FieldNames.itemMasterLocTypeUuid);
	}

	/**
	 * Get 物料主表UUID
	 */
	@Column(name = "ITEM_MASTER_UUID")
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
	 * Get 货物包装单位
	 */
	@Column(name = "STOCK_UNIT_CODE")
	public String getStockUnitCode() {
		return stockUnitCode;
	}

	/**
	 * Set 货物包装单位
	 */
	public void setStockUnitCode(String stockUnitCode) {
		this.stockUnitCode = stockUnitCode;
		addValidField(FieldNames.stockUnitCode);
	}

	/**
	 * Get 存储数量
	 */
	@Column(name = "STOCK_QTY")
	public Double getStockQty() {
		return stockQty;
	}

	/**
	 * Set 存储数量
	 */
	public void setStockQty(Double stockQty) {
		this.stockQty = stockQty;
		addValidField(FieldNames.stockQty);
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
	 * Get 控制字
	 * ：默认0
	 */
	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	/**
	 * Set 控制字
	 * ：默认0
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
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="itemMasterLocTypeUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
	
}
