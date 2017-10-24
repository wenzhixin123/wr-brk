package com.sinotrans.gd.wlp.basicdata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

import com.sinotrans.framework.core.model.BaseModelClass;
import com.sinotrans.framework.core.model.OperationLog;

/**
 * Model class for 文件中心操作记录表
 */
@Entity
@Table(name = "DCS_OPERATE_LOG")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class DcsOperateLogModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "DcsOperateLog";
	/**
	 * 操作类型为print
	 */
	public static final String OPEATIONTYPE_PRINT="print";
	public static final String OPEATIONTYPE_MANIFEST_FILE="MANIFEST_FILE";

	public static final class FieldNames {
		/**
		 * 日志ID
		 */
		public static final String logUuid = "logUuid";
		/**
		 * 目标记录PK
		 */
		public static final String dataSourceUuid = "dataSourceUuid";
		/**
		 * 目标表名
		 */
		public static final String dateSourceEntity = "dateSourceEntity";
		/**
		 * 业务单号
		 */
		public static final String businessRefNo = "businessRefNo";
		/**
		 * 修改前值
		 */
		public static final String currentValue = "currentValue";
		/**
		 * 修改后值
		 */
		public static final String modifiedValue = "modifiedValue";
		/**
		 * 原业务记录的最后修改人
		 */
		public static final String currentModifier = "currentModifier";
		/**
		 * 原业务记录的最后修改时间
		 */
		public static final String currentModifyTime = "currentModifyTime";
		/**
		 * 操作类型
		 */
		public static final String opeationType = "opeationType";
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
		 * recVer
		 */
		public static final String recVer = "recVer";
		/**
		 * creator
		 */
		public static final String creator = "creator";
		/**
		 * createTime
		 */
		public static final String createTime = "createTime";
		/**
		 * modifier
		 */
		public static final String modifier = "modifier";
		/**
		 * 修改时间
		 */
		public static final String modifyTime = "modifyTime";
	}

	//日志ID
	private String logUuid;
	//目标记录PK
	private String dataSourceUuid;
	//目标表名
	private String dateSourceEntity;
	//业务单号
	private String businessRefNo;
	//修改前值
	private String currentValue;
	//修改后值
	private String modifiedValue;
	//原业务记录的最后修改人
	private String currentModifier;
	//原业务记录的最后修改时间
	private Date currentModifyTime;
	//操作类型
	private String opeationType;
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
	//recVer
	private Long recVer;
	//creator
	private String creator;
	//createTime
	private Date createTime;
	//modifier
	private String modifier;
	//修改时间
	private Date modifyTime;

	/**
	 * Get 日志ID
	 */
	@Column(name = "LOG_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getLogUuid() {
		return logUuid;
	}

	/**
	 * Set 日志ID
	 */
	public void setLogUuid(String logUuid) {
		this.logUuid = logUuid;
		addValidField(FieldNames.logUuid);
	}

	/**
	 * Get 目标记录PK
	 */
	@Column(name = "DATA_SOURCE_UUID")
	public String getDataSourceUuid() {
		return dataSourceUuid;
	}

	/**
	 * Set 目标记录PK
	 */
	public void setDataSourceUuid(String dataSourceUuid) {
		this.dataSourceUuid = dataSourceUuid;
		addValidField(FieldNames.dataSourceUuid);
	}

	/**
	 * Get 目标表名
	 */
	@Column(name = "DATE_SOURCE_ENTITY")
	public String getDateSourceEntity() {
		return dateSourceEntity;
	}

	/**
	 * Set 目标表名
	 */
	public void setDateSourceEntity(String dateSourceEntity) {
		this.dateSourceEntity = dateSourceEntity;
		addValidField(FieldNames.dateSourceEntity);
	}

	/**
	 * Get 业务单号
	 */
	@Column(name = "BUSINESS_REF_NO")
	public String getBusinessRefNo() {
		return businessRefNo;
	}

	/**
	 * Set 业务单号
	 */
	public void setBusinessRefNo(String businessRefNo) {
		this.businessRefNo = businessRefNo;
		addValidField(FieldNames.businessRefNo);
	}

	/**
	 * Get 修改前值
	 */
	@Column(name = "CURRENT_VALUE")
	@Type(type="org.springframework.orm.hibernate3.support.ClobStringType")
	public String getCurrentValue() {
		return currentValue;
	}

	/**
	 * Set 修改前值
	 */
	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
		addValidField(FieldNames.currentValue);
	}

	/**
	 * Get 修改后值
	 */
	@Column(name = "MODIFIED_VALUE")
	@Type(type="org.springframework.orm.hibernate3.support.ClobStringType")
	public String getModifiedValue() {
		return modifiedValue;
	}

	/**
	 * Set 修改后值
	 */
	public void setModifiedValue(String modifiedValue) {
		this.modifiedValue = modifiedValue;
		addValidField(FieldNames.modifiedValue);
	}

	/**
	 * Get 原业务记录的最后修改人
	 */
	@Column(name = "CURRENT_MODIFIER")
	public String getCurrentModifier() {
		return currentModifier;
	}

	/**
	 * Set 原业务记录的最后修改人
	 */
	public void setCurrentModifier(String currentModifier) {
		this.currentModifier = currentModifier;
		addValidField(FieldNames.currentModifier);
	}

	/**
	 * Get 原业务记录的最后修改时间
	 */
	@Column(name = "CURRENT_MODIFY_TIME")
	public Date getCurrentModifyTime() {
		return currentModifyTime;
	}

	/**
	 * Set 原业务记录的最后修改时间
	 */
	public void setCurrentModifyTime(Date currentModifyTime) {
		this.currentModifyTime = currentModifyTime;
		addValidField(FieldNames.currentModifyTime);
	}

	/**
	 * Get 操作类型
	 * ：两种“修改”、“删除”
	 */
	@Column(name = "OPEATION_TYPE")
	public String getOpeationType() {
		return opeationType;
	}

	/**
	 * Set 操作类型
	 * ：两种“修改”、“删除”
	 */
	public void setOpeationType(String opeationType) {
		this.opeationType = opeationType;
		addValidField(FieldNames.opeationType);
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
	 * Get recVer
	 */
	@Column(name = "REC_VER")
	@Version
	public Long getRecVer() {
		return recVer;
	}

	/**
	 * Set recVer
	 */
	public void setRecVer(Long recVer) {
		this.recVer = recVer;
		addValidField(FieldNames.recVer);
	}

	/**
	 * Get creator
	 */
	@Column(name = "CREATOR")
	public String getCreator() {
		return creator;
	}

	/**
	 * Set creator
	 */
	public void setCreator(String creator) {
		this.creator = creator;
		addValidField(FieldNames.creator);
	}

	/**
	 * Get createTime
	 */
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * Set createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		addValidField(FieldNames.createTime);
	}

	/**
	 * Get modifier
	 */
	@Column(name = "MODIFIER")
	public String getModifier() {
		return modifier;
	}

	/**
	 * Set modifier
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
	private String prrmaryKeyName="logUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
