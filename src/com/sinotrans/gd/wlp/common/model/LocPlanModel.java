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

/**
 * Model class for 上架和拣货计划
 */
@Entity
@Table(name = "LOC_PLAN")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class LocPlanModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "LocPlan";

	public static final class FieldNames {
		/**
		 * 上架和拣货计划单UUID
		 */
		public static final String locPlanUuid = "locPlanUuid";
		/**
		 * 上架和拣货策略UUID
		 */
		public static final String locPlanConfigUuid = "locPlanConfigUuid";
		/**
		 * 上架和拣货单号
		 */
		public static final String locPlanNo = "locPlanNo";
		/**
		 * locPlanType
		 */
		public static final String locPlanType = "locPlanType";
		/**
		 * 计划时间
		 */
		public static final String taskDate = "taskDate";
		/**
		 * 要求完成时间
		 */
		public static final String finishDate = "finishDate";
		/**
		 * 作废时间
		 */
		public static final String cancelDate = "cancelDate";
		/**
		 * 仓管员
		 */
		public static final String wrhWorker = "wrhWorker";
		/**
		 * 货物监管员（理货员）
		 */
		public static final String wrhCust = "wrhCust";
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
	}

	// 上架和拣货计划单UUID
	private String locPlanUuid;
	// 上架和拣货策略UUID
	private String locPlanConfigUuid;
	// 上架和拣货单号
	private String locPlanNo;
	// locPlanType
	private String locPlanType;
	// 计划时间
	private Date taskDate;
	// 要求完成时间
	private Date finishDate;
	// 作废时间
	private Date cancelDate;
	// 仓管员
	private String wrhWorker;
	// 货物监管员（理货员）
	private String wrhCust;
	// 状态
	private String status;
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
	 * Get 上架和拣货计划单UUID
	 */
	@Column(name = "LOC_PLAN_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getLocPlanUuid() {
		return locPlanUuid;
	}

	/**
	 * Set 上架和拣货计划单UUID
	 */
	public void setLocPlanUuid(String locPlanUuid) {
		this.locPlanUuid = locPlanUuid;
		addValidField(FieldNames.locPlanUuid);
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
	 * Get 上架和拣货单号
	 */
	@Column(name = "LOC_PLAN_NO")
	public String getLocPlanNo() {
		return locPlanNo;
	}

	/**
	 * Set 上架和拣货单号
	 */
	public void setLocPlanNo(String locPlanNo) {
		this.locPlanNo = locPlanNo;
		addValidField(FieldNames.locPlanNo);
	}

	/**
	 * Get locPlanType
	 */
	@Column(name = "LOC_PLAN_TYPE")
	public String getLocPlanType() {
		return locPlanType;
	}

	/**
	 * Set locPlanType
	 */
	public void setLocPlanType(String locPlanType) {
		this.locPlanType = locPlanType;
		addValidField(FieldNames.locPlanType);
	}

	/**
	 * Get 计划时间
	 */
	@Column(name = "TASK_DATE")
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getTaskDate() {
		return taskDate;
	}

	/**
	 * Set 计划时间
	 */
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
		addValidField(FieldNames.taskDate);
	}

	/**
	 * Get 要求完成时间
	 */
	@Column(name = "FINISH_DATE")
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getFinishDate() {
		return finishDate;
	}

	/**
	 * Set 要求完成时间
	 */
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
		addValidField(FieldNames.finishDate);
	}

	/**
	 * Get 作废时间
	 */
	@Column(name = "CANCEL_DATE")
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCancelDate() {
		return cancelDate;
	}

	/**
	 * Set 作废时间
	 */
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
		addValidField(FieldNames.cancelDate);
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
	@JsonSerialize(using = CustomDateSerializer.class)
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
	@JsonSerialize(using = CustomDateSerializer.class)
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
	private String prrmaryKeyName="locPlanUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
