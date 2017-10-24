package com.sinotrans.gd.wlp.basicdata.model;

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
 * Model class for 汇率信息
 */
@Entity
@Table(name = "BAS_EXCHANGE_RATE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasExchangeRateModel extends BaseModelClass implements
		OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasExchangeRate";

	public static final class FieldNames {
		/**
		 * Phisical
		 */
		public static final String basExchangeRateUuid = "basExchangeRateUuid";
		/**
		 * 原币别
		 */
		public static final String currencyCode = "currencyCode";
		/**
		 * 币别名称
		 */
		public static final String exchangeCurrencyCode = "exchangeCurrencyCode";
		/**
		 * 汇率
		 */
		public static final String exchangeRate = "exchangeRate";
		/**
		 * 兑换起始时间
		 */
		public static final String activeStaDate = "activeStaDate";
		/**
		 * 兑换结束时间
		 */
		public static final String activeEndDate = "activeEndDate";
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

	// Phisical
	private String basExchangeRateUuid;
	// 原币别
	private String currencyCode;
	// 币别名称
	private String exchangeCurrencyCode;
	// 汇率
	private Double exchangeRate;
	// 兑换起始时间
	private Date activeStaDate;
	// 兑换结束时间
	private Date activeEndDate;
	// 备注
	private String remark;
	// 状态
	private String status;
	// 控制字
	private String controlWord;
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
	 * Get Phisical Primary Key
	 */
	@Column(name = "BAS_EXCHANGE_RATE_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getBasExchangeRateUuid() {
		return basExchangeRateUuid;
	}

	/**
	 * Set Phisical Primary Key
	 */
	public void setBasExchangeRateUuid(String basExchangeRateUuid) {
		this.basExchangeRateUuid = basExchangeRateUuid;
		addValidField(FieldNames.basExchangeRateUuid);
	}

	/**
	 * Get 原币别
	 */
	@Column(name = "CURRENCY_CODE")
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * Set 原币别
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
		addValidField(FieldNames.currencyCode);
	}

	/**
	 * Get 币别名称
	 */
	@Column(name = "EXCHANGE_CURRENCY_CODE")
	public String getExchangeCurrencyCode() {
		return exchangeCurrencyCode;
	}

	/**
	 * Set 币别名称
	 */
	public void setExchangeCurrencyCode(String exchangeCurrencyCode) {
		this.exchangeCurrencyCode = exchangeCurrencyCode;
		addValidField(FieldNames.exchangeCurrencyCode);
	}

	/**
	 * Get 汇率
	 */
	@Column(name = "EXCHANGE_RATE")
	public Double getExchangeRate() {
		return exchangeRate;
	}

	/**
	 * Set 汇率
	 */
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
		addValidField(FieldNames.exchangeRate);
	}

	/**
	 * Get 兑换起始时间
	 */
	@Column(name = "ACTIVE_STA_DATE")
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getActiveStaDate() {
		return activeStaDate;
	}

	/**
	 * Set 兑换起始时间
	 */
	public void setActiveStaDate(Date activeStaDate) {
		this.activeStaDate = activeStaDate;
		addValidField(FieldNames.activeStaDate);
	}

	/**
	 * Get 兑换结束时间
	 */
	@Column(name = "ACTIVE_END_DATE")
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getActiveEndDate() {
		return activeEndDate;
	}

	/**
	 * Set 兑换结束时间
	 */
	public void setActiveEndDate(Date activeEndDate) {
		this.activeEndDate = activeEndDate;
		addValidField(FieldNames.activeEndDate);
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
	 * Get 状态 ：Active - 有效； Cancel - 作废
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态 ：Active - 有效； Cancel - 作废
	 */
	public void setStatus(String status) {
		this.status = status;
		addValidField(FieldNames.status);
	}

	/**
	 * Get 控制字 ：默认0
	 */
	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	/**
	 * Set 控制字 ：默认0
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
	private String prrmaryKeyName="basExchangeRateUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
