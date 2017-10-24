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
 * Model class for 物料种类
 */
@Entity
@Table(name = "ITEM_KIND")
public class ItemKindModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "ItemKind";

	public static final class FieldNames {
		/**
		 * 货物种类UUID
		 */
		public static final String itemKindUuid = "itemKindUuid";
		/**
		 * 货物种类代码
		 */
		public static final String itemKindCode = "itemKindCode";
		/**
		 * 货物种类描述
		 */
		public static final String itemKindName = "itemKindName";
		/**
		 * itemKindNameEn
		 */
		public static final String itemKindNameEn = "itemKindNameEn";
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

	//货物种类UUID
	private String itemKindUuid;
	//货物种类代码
	private String itemKindCode;
	//货物种类描述
	private String itemKindName;
	//itemKindNameEn
	private String itemKindNameEn;
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
	 * Get 货物种类UUID
	 */
	@Column(name = "ITEM_KIND_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
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
	 * Get 货物种类代码
	 */
	@Column(name = "ITEM_KIND_CODE")
	public String getItemKindCode() {
		return itemKindCode;
	}

	/**
	 * Set 货物种类代码
	 */
	public void setItemKindCode(String itemKindCode) {
		this.itemKindCode = itemKindCode;
		addValidField(FieldNames.itemKindCode);
	}

	/**
	 * Get 货物种类描述
	 */
	@Column(name = "ITEM_KIND_NAME")
	public String getItemKindName() {
		return itemKindName;
	}

	/**
	 * Set 货物种类描述
	 */
	public void setItemKindName(String itemKindName) {
		this.itemKindName = itemKindName;
		addValidField(FieldNames.itemKindName);
	}

	/**
	 * Get itemKindNameEn
	 */
	@Column(name = "ITEM_KIND_NAME_EN")
	public String getItemKindNameEn() {
		return itemKindNameEn;
	}

	/**
	 * Set itemKindNameEn
	 */
	public void setItemKindNameEn(String itemKindNameEn) {
		this.itemKindNameEn = itemKindNameEn;
		addValidField(FieldNames.itemKindNameEn);
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
	private String prrmaryKeyName="itemKindUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
