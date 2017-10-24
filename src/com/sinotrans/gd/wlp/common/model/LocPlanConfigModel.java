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
 * Model class for 上架和拣货策略
 */
@Entity
@Table(name = "LOC_PLAN_CONFIG")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class LocPlanConfigModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "LocPlanConfig";

	public static final class FieldNames {
		/**
		 * 上架和拣货策略UUID
		 */
		public static final String locPlanConfigUuid = "locPlanConfigUuid";
		/**
		 * 上架和拣货策略号
		 */
		public static final String configCode = "configCode";
		
		/**
		 * 关联策略
		 */
		public static final String nextConfigCode="nextConfigCode";
		
		/**
		 * 上架和拣货策略描述
		 */
		public static final String configName = "configName";
		/**
		 * configNameEn
		 */
		public static final String configNameEn = "configNameEn";
		/**
		 * 策略类型
		 */
		public static final String configType = "configType";
		/**
		 * 开始日期
		 */
		public static final String beginDate = "beginDate";
		/**
		 * 结束日期
		 */
		public static final String endDate = "endDate";
		/**
		 * 取消日期
		 */
		public static final String cancelDate = "cancelDate";
		/**
		 * 客户代码
		 */
		public static final String customerCode = "customerCode";
		/**
		 * 客户名称
		 */
		public static final String customerName = "customerName";
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

	// 上架和拣货策略UUID
	private String locPlanConfigUuid;
	// 上架和拣货策略号
	private String configCode;
	
	private String nextConfigCode;
	
	// 上架和拣货策略描述
	private String configName;
	// configNameEn
	private String configNameEn;
	// 策略类型
	private String configType;
	// 开始日期
	private Date beginDate;
	// 结束日期
	private Date endDate;
	// 取消日期
	private Date cancelDate;
	// 客户代码
	private String customerCode;
	// 客户名称
	private String customerName;
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
	 * Get 上架和拣货策略UUID
	 */
	@Column(name = "LOC_PLAN_CONFIG_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
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
	 * Get 上架和拣货策略号
	 */
	@Column(name = "CONFIG_CODE")
	public String getConfigCode() {
		return configCode;
	}

	/**
	 * Set 上架和拣货策略号
	 */
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
		addValidField(FieldNames.configCode);
	}
	
	@Column(name = "NEXT_CONFIG_CODE")
	public String getNextConfigCode() {
		return nextConfigCode;
	}

	public void setNextConfigCode(String nextConfigCode) {
		this.nextConfigCode = nextConfigCode;
		addValidField(FieldNames.nextConfigCode);
	}

	/**
	 * Get 上架和拣货策略描述
	 */
	@Column(name = "CONFIG_NAME")
	public String getConfigName() {
		return configName;
	}

	/**
	 * Set 上架和拣货策略描述
	 */
	public void setConfigName(String configName) {
		this.configName = configName;
		addValidField(FieldNames.configName);
	}

	/**
	 * Get configNameEn
	 */
	@Column(name = "CONFIG_NAME_EN")
	public String getConfigNameEn() {
		return configNameEn;
	}

	/**
	 * Set configNameEn
	 */
	public void setConfigNameEn(String configNameEn) {
		this.configNameEn = configNameEn;
		addValidField(FieldNames.configNameEn);
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
	 * Get 开始日期
	 */
	@Column(name = "BEGIN_DATE")
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * Set 开始日期
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
		addValidField(FieldNames.beginDate);
	}

	/**
	 * Get 结束日期
	 */
	@Column(name = "END_DATE")
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Set 结束日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		addValidField(FieldNames.endDate);
	}

	/**
	 * Get 取消日期
	 */
	@Column(name = "CANCEL_DATE")
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCancelDate() {
		return cancelDate;
	}

	/**
	 * Set 取消日期
	 */
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
		addValidField(FieldNames.cancelDate);
	}

	/**
	 * Get 客户代码
	 */
	@Column(name = "CUSTOMER_CODE")
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * Set 客户代码
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
		addValidField(FieldNames.customerCode);
	}

	/**
	 * Get 客户名称
	 */
	@Column(name = "CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Set 客户名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
		addValidField(FieldNames.customerName);
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
	private String prrmaryKeyName="locPlanConfigUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}


}
