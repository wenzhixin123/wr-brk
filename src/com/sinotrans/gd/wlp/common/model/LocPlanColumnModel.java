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
 * Model class for 字段名
 */
@Entity
@Table(name = "LOC_PLAN_COLUMN")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class LocPlanColumnModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "LocPlanColumn";

	public static final class FieldNames {
		/**
		 * 字段UUID
		 */
		public static final String locPlanColumnUuid = "locPlanColumnUuid";
		/**
		 * 序号
		 */
		public static final String seqNo = "seqNo";
		/**
		 * 表名
		 */
		public static final String tableName = "tableName";
		/**
		 * 指定字段
		 */
		public static final String columnCode = "columnCode";
		/**
		 * 指定字段名称
		 */
		public static final String columnName = "columnName";
		/**
		 * 策略类型
		 */
		public static final String configType = "configType";
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

	// 字段UUID
	private String locPlanColumnUuid;
	// 序号
	private Long seqNo;
	// 表名
	private String tableName;
	// 指定字段
	private String columnCode;
	// 指定字段名称
	private String columnName;
	// 策略类型
	private String configType;
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
	 * Get 字段UUID
	 */
	@Column(name = "LOC_PLAN_COLUMN_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getLocPlanColumnUuid() {
		return locPlanColumnUuid;
	}

	/**
	 * Set 字段UUID
	 */
	public void setLocPlanColumnUuid(String locPlanColumnUuid) {
		this.locPlanColumnUuid = locPlanColumnUuid;
		addValidField(FieldNames.locPlanColumnUuid);
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
	 * Get 表名
	 */
	@Column(name = "TABLE_NAME")
	public String getTableName() {
		return tableName;
	}

	/**
	 * Set 表名
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
		addValidField(FieldNames.tableName);
	}

	/**
	 * Get 指定字段
	 */
	@Column(name = "COLUMN_CODE")
	public String getColumnCode() {
		return columnCode;
	}

	/**
	 * Set 指定字段
	 */
	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
		addValidField(FieldNames.columnCode);
	}

	/**
	 * Get 指定字段名称
	 */
	@Column(name = "COLUMN_NAME")
	public String getColumnName() {
		return columnName;
	}

	/**
	 * Set 指定字段名称
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
		addValidField(FieldNames.columnName);
	}

	/**
	 * Get 策略类型 ：上架、拣货
	 */
	@Column(name = "CONFIG_TYPE")
	public String getConfigType() {
		return configType;
	}

	/**
	 * Set 策略类型 ：上架、拣货
	 */
	public void setConfigType(String configType) {
		this.configType = configType;
		addValidField(FieldNames.configType);
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
	private String prrmaryKeyName="locPlanColumnUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
