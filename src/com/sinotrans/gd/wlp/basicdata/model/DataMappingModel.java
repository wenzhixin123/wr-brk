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
 * Model class for 代码映射表
 */
@Entity
@Table(name = "DATA_MAPPING")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class DataMappingModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "DataMapping";

	public static final class FieldNames {
		/**
		 * 主键
		 */
		public static final String dataMappingUuid = "dataMappingUuid";
		/**
		 * 数据类型
		 */
		public static final String dataType = "dataType";
		/**
		 * 数据来源方
		 */
		public static final String fromCuCode = "fromCuCode";
		/**
		 * 数据来源方描述
		 */
		public static final String fromCuName = "fromCuName";
		/**
		 * 数据目的方
		 */
		public static final String destCuCode = "destCuCode";
		/**
		 * 数据目的方描述
		 */
		public static final String destCuName = "destCuName";
		/**
		 * 源数据代码
		 */
		public static final String sourceDataCode = "sourceDataCode";
		/**
		 * 源数据描述
		 */
		public static final String sourceDataDesc = "sourceDataDesc";
		/**
		 * 需转换目的代码
		 */
		public static final String mcDataCode = "mcDataCode";
		/**
		 * 需转换目的代码描述
		 */
		public static final String mcDataDesc = "mcDataDesc";
		/**
		 * 默认值
		 */
		public static final String defaultValue = "defaultValue";
		/**
		 * 中心代码
		 */
		public static final String centerCode = "centerCode";
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

	//主键
	private String dataMappingUuid;
	//数据类型
	private String dataType;
	//数据来源方
	private String fromCuCode;
	//数据来源方描述
	private String fromCuName;
	//数据目的方
	private String destCuCode;
	//数据目的方描述
	private String destCuName;
	//源数据代码
	private String sourceDataCode;
	//源数据描述
	private String sourceDataDesc;
	//需转换目的代码
	private String mcDataCode;
	//需转换目的代码描述
	private String mcDataDesc;
	//默认值
	private String defaultValue;
	//中心代码
	private String centerCode;
	//状态
	private String status;
	//控制字
	private String controlWord;
	//备注
	private String remark;
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
	 * Get 主键
	 */
	@Column(name = "DATA_MAPPING_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getDataMappingUuid() {
		return dataMappingUuid;
	}

	/**
	 * Set 主键
	 */
	public void setDataMappingUuid(String dataMappingUuid) {
		this.dataMappingUuid = dataMappingUuid;
		addValidField(FieldNames.dataMappingUuid);
	}

	/**
	 * Get 数据类型
	 * ：
	 */
	@Column(name = "DATA_TYPE")
	public String getDataType() {
		return dataType;
	}

	/**
	 * Set 数据类型
	 * ：
	 */
	public void setDataType(String dataType) {
		this.dataType = dataType;
		addValidField(FieldNames.dataType);
	}

	/**
	 * Get 数据来源方
	 * ：
	 */
	@Column(name = "FROM_CU_CODE")
	public String getFromCuCode() {
		return fromCuCode;
	}

	/**
	 * Set 数据来源方
	 * ：
	 */
	public void setFromCuCode(String fromCuCode) {
		this.fromCuCode = fromCuCode;
		addValidField(FieldNames.fromCuCode);
	}

	/**
	 * Get 数据来源方描述
	 */
	@Column(name = "FROM_CU_NAME")
	public String getFromCuName() {
		return fromCuName;
	}

	/**
	 * Set 数据来源方描述
	 */
	public void setFromCuName(String fromCuName) {
		this.fromCuName = fromCuName;
		addValidField(FieldNames.fromCuName);
	}

	/**
	 * Get 数据目的方
	 */
	@Column(name = "DEST_CU_CODE")
	public String getDestCuCode() {
		return destCuCode;
	}

	/**
	 * Set 数据目的方
	 */
	public void setDestCuCode(String destCuCode) {
		this.destCuCode = destCuCode;
		addValidField(FieldNames.destCuCode);
	}

	/**
	 * Get 数据目的方描述
	 */
	@Column(name = "DEST_CU_NAME")
	public String getDestCuName() {
		return destCuName;
	}

	/**
	 * Set 数据目的方描述
	 */
	public void setDestCuName(String destCuName) {
		this.destCuName = destCuName;
		addValidField(FieldNames.destCuName);
	}

	/**
	 * Get 源数据代码
	 */
	@Column(name = "SOURCE_DATA_CODE")
	public String getSourceDataCode() {
		return sourceDataCode;
	}

	/**
	 * Set 源数据代码
	 */
	public void setSourceDataCode(String sourceDataCode) {
		this.sourceDataCode = sourceDataCode;
		addValidField(FieldNames.sourceDataCode);
	}

	/**
	 * Get 源数据描述
	 */
	@Column(name = "SOURCE_DATA_DESC")
	public String getSourceDataDesc() {
		return sourceDataDesc;
	}

	/**
	 * Set 源数据描述
	 */
	public void setSourceDataDesc(String sourceDataDesc) {
		this.sourceDataDesc = sourceDataDesc;
		addValidField(FieldNames.sourceDataDesc);
	}

	/**
	 * Get 需转换目的代码
	 */
	@Column(name = "MC_DATA_CODE")
	public String getMcDataCode() {
		return mcDataCode;
	}

	/**
	 * Set 需转换目的代码
	 */
	public void setMcDataCode(String mcDataCode) {
		this.mcDataCode = mcDataCode;
		addValidField(FieldNames.mcDataCode);
	}

	/**
	 * Get 需转换目的代码描述
	 */
	@Column(name = "MC_DATA_DESC")
	public String getMcDataDesc() {
		return mcDataDesc;
	}

	/**
	 * Set 需转换目的代码描述
	 */
	public void setMcDataDesc(String mcDataDesc) {
		this.mcDataDesc = mcDataDesc;
		addValidField(FieldNames.mcDataDesc);
	}

	/**
	 * Get 默认值
	 */
	@Column(name = "DEFAULT_VALUE")
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Set 默认值
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		addValidField(FieldNames.defaultValue);
	}

	/**
	 * Get 中心代码
	 */
	@Column(name = "CENTER_CODE")
	public String getCenterCode() {
		return centerCode;
	}

	/**
	 * Set 中心代码
	 */
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
		addValidField(FieldNames.centerCode);
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
	private String prrmaryKeyName="dataMappingUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
