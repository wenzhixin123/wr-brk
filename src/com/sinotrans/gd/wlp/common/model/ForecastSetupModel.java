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
 * Model class for 预约时间设置
 */
@Entity
@Table(name = "FORECAST_SETUP")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class ForecastSetupModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "ForecastSetup";

	public static final class FieldNames {
		/**
		 * cfsUuid
		 */
		public static final String cfsUuid = "cfsUuid";
		/**
		 * 类型
		 */
		public static final String type = "type";
		/**
		 * 客户代码
		 */
		public static final String customerCode = "customerCode";
		/**
		 * 客户描述
		 */
		public static final String customerDesc = "customerDesc";
		/**
		 * 开始时间
		 */
		public static final String startDate = "startDate";
		/**
		 * 结束时间
		 */
		public static final String endDate = "endDate";
		/**
		 * 可预约数
		 */
		public static final String forecastNumber = "forecastNumber";
		/**
		 * 已使用数
		 */
		public static final String usedNumber = "usedNumber";
		/**
		 * 时间段号
		 */
		public static final String timeNo = "timeNo";
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
		/**
		 * 状态
		 */
		public static final String status = "status";
	}

	//cfsUuid
	private String cfsUuid;
	//类型
	private String type;
	//客户代码
	private String customerCode;
	//客户描述
	private String customerDesc;
	//开始时间
	private Date startDate;
	//结束时间
	private Date endDate;
	//可预约数
	private Long forecastNumber;
	//已使用数
	private Long usedNumber;
	//时间段号
	private String timeNo;
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
	//状态
	private String status;

	/**
	 * Get cfsUuid
	 */
	@Column(name = "CFS_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getCfsUuid() {
		return cfsUuid;
	}

	/**
	 * Set cfsUuid
	 */
	public void setCfsUuid(String cfsUuid) {
		this.cfsUuid = cfsUuid;
		addValidField(FieldNames.cfsUuid);
	}

	/**
	 * Get 类型
	 * ：客户，时间段
	 */
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	/**
	 * Set 类型
	 * ：客户，时间段
	 */
	public void setType(String type) {
		this.type = type;
		addValidField(FieldNames.type);
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
	 * Get 客户描述
	 */
	@Column(name = "CUSTOMER_DESC")
	public String getCustomerDesc() {
		return customerDesc;
	}

	/**
	 * Set 客户描述
	 */
	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
		addValidField(FieldNames.customerDesc);
	}

	/**
	 * Get 开始时间
	 */
	@Column(name = "START_DATE")
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Set 开始时间
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		addValidField(FieldNames.startDate);
	}

	/**
	 * Get 结束时间
	 */
	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Set 结束时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		addValidField(FieldNames.endDate);
	}

	/**
	 * Get 可预约数
	 */
	@Column(name = "FORECAST_NUMBER")
	public Long getForecastNumber() {
		return forecastNumber;
	}

	/**
	 * Set 可预约数
	 */
	public void setForecastNumber(Long forecastNumber) {
		this.forecastNumber = forecastNumber;
		addValidField(FieldNames.forecastNumber);
	}

	/**
	 * Get 已使用数
	 */
	@Column(name = "USED_NUMBER")
	public Long getUsedNumber() {
		return usedNumber;
	}

	/**
	 * Set 已使用数
	 */
	public void setUsedNumber(Long usedNumber) {
		this.usedNumber = usedNumber;
		addValidField(FieldNames.usedNumber);
	}

	/**
	 * Get 时间段号
	 */
	@Column(name = "TIME_NO")
	public String getTimeNo() {
		return timeNo;
	}

	/**
	 * Set 时间段号
	 */
	public void setTimeNo(String timeNo) {
		this.timeNo = timeNo;
		addValidField(FieldNames.timeNo);
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
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="cfsUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
