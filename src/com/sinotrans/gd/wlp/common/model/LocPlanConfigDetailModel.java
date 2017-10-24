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
 * Model class for 上架和拣货策略明细
 */
@Entity
@Table(name = "LOC_PLAN_CONFIG_DETAIL")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class LocPlanConfigDetailModel extends BaseModelClass implements
		OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "LocPlanConfigDetail";
 
	public static final class FieldNames {
		/**
		 * 上架和拣货策略明细UUID
		 */
		public static final String locPlanConfigDetailUuid = "locPlanConfigDetailUuid";
		/**
		 * 上架和拣货策略UUID
		 */
		public static final String locPlanConfigUuid = "locPlanConfigUuid";
		/**
		 * preLocPlanConfigDetailUui
		 */
		public static final String preLocPlanConfigDetailUui = "preLocPlanConfigDetailUui";
		/**
		 * 序号
		 */
		public static final String seqNo = "seqNo";
		/**
		 * 指定字段
		 */
		public static final String fieldCode = "fieldCode";
		/**
		 * 指定字段名称
		 */
		public static final String fieldName = "fieldName";
		/**
		 * 指定数值
		 */
		public static final String fieldValue = "fieldValue";
		/**
		 * 数值格式
		 */
		public static final String fieldFormat = "fieldFormat";
		/**
		 * 指定数值名称
		 */
		public static final String valueName = "valueName";
		/**
		 * 控制符（Y/空）
		 */
		public static final String andorFlag = "andorFlag";
		/**
		 * 策略标示
		 */
		public static final String planFlag = "planFlag";
		/**
		 * 约束标识
		 */
		public static final String controlFlag = "controlFlag";
		/**
		 * 运算符
		 */
		public static final String operationFlag = "operationFlag";
		/**
		 * 状态
		 */
		public static final String status = "status";
		/**
		 * 控制字
		 */
		public static final String controlWord = "controlWord";
		/**
		 * 备注
		 */
		public static final String remark = "remark";
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

	// 上架和拣货策略明细UUID
	private String locPlanConfigDetailUuid;
	// 上架和拣货策略UUID
	private String locPlanConfigUuid;
	// preLocPlanConfigDetailUui
	private String preLocPlanConfigDetailUui;
	// 序号
	private Long seqNo;
	// 指定字段
	private String fieldCode;
	// 指定字段名称
	private String fieldName;
	// 指定数值
	private String fieldValue;
	// 数值格式
	private String fieldFormat;
	// 指定数值名称
	private String valueName;
	// 控制符（Y/空）
	private String andorFlag;
	// 策略标示
	private String planFlag;
	// 约束标识
	private String controlFlag;
	// 运算符
	private String operationFlag;
	// 状态
	private String status;
	// 控制字
	private String controlWord;
	// 备注
	private String remark;
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
	 * Get 上架和拣货策略明细UUID
	 */
	@Column(name = "LOC_PLAN_CONFIG_DETAIL_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getLocPlanConfigDetailUuid() {
		return locPlanConfigDetailUuid;
	}

	/**
	 * Set 上架和拣货策略明细UUID
	 */
	public void setLocPlanConfigDetailUuid(String locPlanConfigDetailUuid) {
		this.locPlanConfigDetailUuid = locPlanConfigDetailUuid;
		addValidField(FieldNames.locPlanConfigDetailUuid);
	}

	/**
	 * Get 上架和拣货策略UUID
	 */
	@Column(name = "LOC_PLAN_CONFIG_UUID")
	public String getLocPlanConfigUuid() {
		return locPlanConfigUuid;
	}

	/**
	 * Set 上架和拣货策略UUID
	 */
	public void setLocPlanConfigUuid(String locPlanConfigUuid) {
		this.locPlanConfigUuid = locPlanConfigUuid;
		addValidField(FieldNames.locPlanConfigUuid);
	}

	/**
	 * Get preLocPlanConfigDetailUui
	 */
	@Column(name = "PRE_LOC_PLAN_CONFIG_DETAIL_UUI")
	public String getPreLocPlanConfigDetailUui() {
		return preLocPlanConfigDetailUui;
	}

	/**
	 * Set preLocPlanConfigDetailUui
	 */
	public void setPreLocPlanConfigDetailUui(String preLocPlanConfigDetailUui) {
		this.preLocPlanConfigDetailUui = preLocPlanConfigDetailUui;
		addValidField(FieldNames.preLocPlanConfigDetailUui);
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
	 * Get 指定字段
	 */
	@Column(name = "FIELD_CODE")
	public String getFieldCode() {
		return fieldCode;
	}

	/**
	 * Set 指定字段
	 */
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
		addValidField(FieldNames.fieldCode);
	}

	/**
	 * Get 指定字段名称
	 */
	@Column(name = "FIELD_NAME")
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * Set 指定字段名称
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
		addValidField(FieldNames.fieldName);
	}

	/**
	 * Get 指定数值
	 */
	@Column(name = "FIELD_VALUE")
	public String getFieldValue() {
		return fieldValue;
	}

	/**
	 * Set 指定数值
	 */
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
		addValidField(FieldNames.fieldValue);
	}

	/**
	 * Get 数值格式
	 */
	@Column(name = "FIELD_FORMAT")
	public String getFieldFormat() {
		return fieldFormat;
	}

	/**
	 * Set 数值格式
	 */
	public void setFieldFormat(String fieldFormat) {
		this.fieldFormat = fieldFormat;
		addValidField(FieldNames.fieldFormat);
	}

	/**
	 * Get 指定数值名称
	 */
	@Column(name = "VALUE_NAME")
	public String getValueName() {
		return valueName;
	}

	/**
	 * Set 指定数值名称
	 */
	public void setValueName(String valueName) {
		this.valueName = valueName;
		addValidField(FieldNames.valueName);
	}

	/**
	 * Get 控制符（Y/空）
	 */
	@Column(name = "ANDOR_FLAG")
	public String getAndorFlag() {
		return andorFlag;
	}

	/**
	 * Set 控制符（Y/空）
	 */
	public void setAndorFlag(String andorFlag) {
		this.andorFlag = andorFlag;
		addValidField(FieldNames.andorFlag);
	}

	/**
	 * Get 策略标示
	 */
	@Column(name = "PLAN_FLAG")
	public String getPlanFlag() {
		return planFlag;
	}

	/**
	 * Set 策略标示
	 */
	public void setPlanFlag(String planFlag) {
		this.planFlag = planFlag;
		addValidField(FieldNames.planFlag);
	}

	/**
	 * Get 约束标识
	 */
	@Column(name = "CONTROL_FLAG")
	public String getControlFlag() {
		return controlFlag;
	}

	/**
	 * Set 约束标识
	 */
	public void setControlFlag(String controlFlag) {
		this.controlFlag = controlFlag;
		addValidField(FieldNames.controlFlag);
	}

	/**
	 * Get 运算符
	 */
	@Column(name = "OPERATION_FLAG")
	public String getOperationFlag() {
		return operationFlag;
	}

	/**
	 * Set 运算符
	 */
	public void setOperationFlag(String operationFlag) {
		this.operationFlag = operationFlag;
		addValidField(FieldNames.operationFlag);
	}

	/**
	 * Get 状态 ：Active － 确认,Pending － 草稿,Cancel － 作废
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态 ：Active － 确认,Pending － 草稿,Cancel － 作废
	 */
	public void setStatus(String status) {
		this.status = status;
		addValidField(FieldNames.status);
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
	private String prrmaryKeyName="locPlanConfigDetailUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
