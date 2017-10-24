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
 * Model class for 区域与物料配置表
 */
@Entity
@Table(name = "LOC_ITEM_REF")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class LocItemRefModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "LocItemRef";

	public static final class FieldNames {
		/**
		 * 区域与物料配置表UUID
		 */
		public static final String locItemRefUuid = "locItemRefUuid";
		/**
		 * 仓库区域表UUID
		 */
		public static final String basLocAreaUuid = "basLocAreaUuid";
		/**
		 * 货物种类UUID
		 */
		public static final String itemKindUuid = "itemKindUuid";
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

	// 区域与物料配置表UUID
	private String locItemRefUuid;
	// 仓库区域表UUID
	private String basLocAreaUuid;
	// 货物种类UUID
	private String itemKindUuid;
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
	 * Get 区域与物料配置表UUID
	 */
	@Column(name = "LOC_ITEM_REF_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getLocItemRefUuid() {
		return locItemRefUuid;
	}

	/**
	 * Set 区域与物料配置表UUID
	 */
	public void setLocItemRefUuid(String locItemRefUuid) {
		this.locItemRefUuid = locItemRefUuid;
		addValidField(FieldNames.locItemRefUuid);
	}

	/**
	 * Get 仓库区域表UUID D
	 */
	@Column(name = "BAS_LOC_AREA_UUID")
	public String getBasLocAreaUuid() {
		return basLocAreaUuid;
	}

	/**
	 * Set 仓库区域表UUID D
	 */
	public void setBasLocAreaUuid(String basLocAreaUuid) {
		this.basLocAreaUuid = basLocAreaUuid;
		addValidField(FieldNames.basLocAreaUuid);
	}

	/**
	 * Get 货物种类UUID
	 */
	@Column(name = "ITEM_KIND_UUID")
	public String getItemKindUuid() {
		return itemKindUuid;
	}

	/**
	 * Set 货物种类UUID
	 */
	public void setItemKindUuid(String itemKindUuid) {
		this.itemKindUuid = itemKindUuid;
		addValidField(FieldNames.itemKindUuid);
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
	private String prrmaryKeyName="locItemRefUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
