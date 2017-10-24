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
 * Model class for DcsCalcItem
 */
@Entity
@Table(name = "DCS_CALC_ITEM")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class DcsCalcItemModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "DcsCalcItem";

	public static final class FieldNames {
		/**
		 * 计收费项目ID
		 */
		public static final String calcItemId = "calcItemId";
		/**
		 * 费目代码
		 */
		public static final String calcItemCode = "calcItemCode";
		/**
		 * 费目描述
		 */
		public static final String calcItemName = "calcItemName";
		/**
		 * 分组代码
		 */
		public static final String itemGroupCode = "itemGroupCode";
		/**
		 * 分组描述
		 */
		public static final String itemGroupName = "itemGroupName";
		/**
		 * 费目备注
		 */
		public static final String itemRemark = "itemRemark";
		/**
		 * 控制字
		 */
		public static final String controlWord = "controlWord";
		/**
		 * 状态
		 */
		public static final String status = "status";
		/**
		 * 公司（组织）代码
		 */
		public static final String officeCode = "officeCode";
		/**
		 * 版本号
		 */
		public static final String recVer = "recVer";
		/**
		 * 创建人
		 */
		public static final String creator = "creator";
		/**
		 * 创建日
		 */
		public static final String createTime = "createTime";
		/**
		 * 修改人
		 */
		public static final String modifier = "modifier";
		/**
		 * 修改日
		 */
		public static final String modifyTime = "modifyTime";
	}

	//计收费项目ID
	private String calcItemId;
	//费目代码
	private String calcItemCode;
	//费目描述
	private String calcItemName;
	//分组代码
	private String itemGroupCode;
	//分组描述
	private String itemGroupName;
	//费目备注
	private String itemRemark;
	//控制字
	private String controlWord;
	//状态
	private String status;
	//公司（组织）代码
	private String officeCode;
	//版本号
	private Long recVer;
	//创建人
	private String creator;
	//创建日
	private Date createTime;
	//修改人
	private String modifier;
	//修改日
	private Date modifyTime;

	/**
	 * Get 计收费项目ID
	 */
	@Column(name = "CALC_ITEM_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getCalcItemId() {
		return calcItemId;
	}

	/**
	 * Set 计收费项目ID
	 */
	public void setCalcItemId(String calcItemId) {
		this.calcItemId = calcItemId;
		addValidField(FieldNames.calcItemId);
	}

	/**
	 * Get 费目代码
	 */
	@Column(name = "CALC_ITEM_CODE")
	public String getCalcItemCode() {
		return calcItemCode;
	}

	/**
	 * Set 费目代码
	 */
	public void setCalcItemCode(String calcItemCode) {
		this.calcItemCode = calcItemCode;
		addValidField(FieldNames.calcItemCode);
	}

	/**
	 * Get 费目描述
	 */
	@Column(name = "CALC_ITEM_NAME")
	public String getCalcItemName() {
		return calcItemName;
	}

	/**
	 * Set 费目描述
	 */
	public void setCalcItemName(String calcItemName) {
		this.calcItemName = calcItemName;
		addValidField(FieldNames.calcItemName);
	}

	/**
	 * Get 分组代码
	 */
	@Column(name = "ITEM_GROUP_CODE")
	public String getItemGroupCode() {
		return itemGroupCode;
	}

	/**
	 * Set 分组代码
	 */
	public void setItemGroupCode(String itemGroupCode) {
		this.itemGroupCode = itemGroupCode;
		addValidField(FieldNames.itemGroupCode);
	}

	/**
	 * Get 分组描述
	 */
	@Column(name = "ITEM_GROUP_NAME")
	public String getItemGroupName() {
		return itemGroupName;
	}

	/**
	 * Set 分组描述
	 */
	public void setItemGroupName(String itemGroupName) {
		this.itemGroupName = itemGroupName;
		addValidField(FieldNames.itemGroupName);
	}

	/**
	 * Get 费目备注
	 */
	@Column(name = "ITEM_REMARK")
	public String getItemRemark() {
		return itemRemark;
	}

	/**
	 * Set 费目备注
	 */
	public void setItemRemark(String itemRemark) {
		this.itemRemark = itemRemark;
		addValidField(FieldNames.itemRemark);
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
	 * ：Active - 有效； Cancel - 作废
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态
	 * ：Active - 有效； Cancel - 作废
	 */
	public void setStatus(String status) {
		this.status = status;
		addValidField(FieldNames.status);
	}

	/**
	 * Get 公司（组织）代码
	 */
	@Column(name = "OFFICE_CODE")
	public String getOfficeCode() {
		return officeCode;
	}

	/**
	 * Set 公司（组织）代码
	 */
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
		addValidField(FieldNames.officeCode);
	}

	/**
	 * Get 版本号
	 */
	@Column(name = "REC_VER")
	@Version
	public Long getRecVer() {
		return recVer;
	}

	/**
	 * Set 版本号
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
	 * Get 创建日
	 */
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * Set 创建日
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
	 * Get 修改日
	 */
	@Column(name = "MODIFY_TIME")
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * Set 修改日
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
		addValidField(FieldNames.modifyTime);
	}
	
	/**
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="calcDetailId";

	@Transient
	public String getPrrmaryKeyName() {
//		return prrmaryKeyName;//"calcBatchId";
		
		return "calcItemId";
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
