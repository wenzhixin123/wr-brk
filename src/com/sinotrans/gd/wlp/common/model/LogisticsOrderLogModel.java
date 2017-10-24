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
 * Model class for 作业单操作记录
 * 录
 */
@Entity
@Table(name = "LOGISTICS_ORDER_LOG")
public class LogisticsOrderLogModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "LogisticsOrderLog";

	public static final class FieldNames {
		/**
		 * 操作记录的UUID
		 */
		public static final String logisticsOrderLogUuid = "logisticsOrderLogUuid";
		/**
		 * 作业单UUID
		 */
		public static final String logisticsOrderUuid = "logisticsOrderUuid";
		/**
		 * 操作类型
		 */
		public static final String transactionType = "transactionType";
		/**
		 * 操作描述
		 */
		public static final String workDesc = "workDesc";
		/**
		 * 操作时间
		 */
		public static final String workDate = "workDate";
		/**
		 * 操作人
		 */
		public static final String workPerson = "workPerson";
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

	//操作记录的UUID
	private String logisticsOrderLogUuid;
	//作业单UUID
	private String logisticsOrderUuid;
	//操作类型
	private String transactionType;
	//操作描述
	private String workDesc;
	//操作时间
	private Date workDate;
	//操作人
	private String workPerson;
	//备注
	private String remark;
	//状态
	private String status;
	//控制字
	private String controlWord;
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

	/**
	 * Get 操作记录的UUID
	 */
	@Column(name = "LOGISTICS_ORDER_LOG_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getLogisticsOrderLogUuid() {
		return logisticsOrderLogUuid;
	}

	/**
	 * Set 操作记录的UUID
	 */
	public void setLogisticsOrderLogUuid(String logisticsOrderLogUuid) {
		this.logisticsOrderLogUuid = logisticsOrderLogUuid;
		addValidField(FieldNames.logisticsOrderLogUuid);
	}

	/**
	 * Get 作业单UUID
	 */
	@Column(name = "LOGISTICS_ORDER_UUID")
	public String getLogisticsOrderUuid() {
		return logisticsOrderUuid;
	}

	/**
	 * Set 作业单UUID
	 */
	public void setLogisticsOrderUuid(String logisticsOrderUuid) {
		this.logisticsOrderUuid = logisticsOrderUuid;
		addValidField(FieldNames.logisticsOrderUuid);
	}

	/**
	 * Get 操作类型
	 */
	@Column(name = "TRANSACTION_TYPE")
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * Set 操作类型
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
		addValidField(FieldNames.transactionType);
	}

	/**
	 * Get 操作描述
	 */
	@Column(name = "WORK_DESC")
	public String getWorkDesc() {
		return workDesc;
	}

	/**
	 * Set 操作描述
	 */
	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
		addValidField(FieldNames.workDesc);
	}

	/**
	 * Get 操作时间
	 */
	@Column(name = "WORK_DATE")
	public Date getWorkDate() {
		return workDate;
	}

	/**
	 * Set 操作时间
	 */
	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
		addValidField(FieldNames.workDate);
	}

	/**
	 * Get 操作人
	 */
	@Column(name = "WORK_PERSON")
	public String getWorkPerson() {
		return workPerson;
	}

	/**
	 * Set 操作人
	 */
	public void setWorkPerson(String workPerson) {
		this.workPerson = workPerson;
		addValidField(FieldNames.workPerson);
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
	 * ：
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态
	 * ：
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
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="logisticsOrderLogUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
