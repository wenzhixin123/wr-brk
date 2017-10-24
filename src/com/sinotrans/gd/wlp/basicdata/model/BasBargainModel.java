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
 * Model class for 合同信息
 */
@Entity
@Table(name = "BAS_BARGAIN")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasBargainModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasBargain";

	public static final class FieldNames {
		/**
		 * 合同UUID
		 */
		public static final String basBargainUuid = "basBargainUuid";
		/**
		 * 客户UUID
		 */
		public static final String basCustomerUuid = "basCustomerUuid";
		/**
		 * 客户代码
		 */
		public static final String customerCode = "customerCode";
		/**
		 * 合同号
		 */
		public static final String bargainCode = "bargainCode";
		/**
		 * 合同名称
		 */
		public static final String bargainName = "bargainName";
		/**
		 * 合同英文名称
		 */
		public static final String bargainNameEn = "bargainNameEn";
		/**
		 * 合同内容
		 */
		public static final String bargainDesc = "bargainDesc";
		/**
		 * 签订日期
		 */
		public static final String beginDate = "beginDate";
		/**
		 * 有效日期
		 */
		public static final String endDate = "endDate";
		/**
		 * 币别编码
		 */
		public static final String currencyCode = "currencyCode";
		/**
		 * 币别名称
		 */
		public static final String currencyName = "currencyName";
		/**
		 * 付款周期
		 */
		public static final String payperiod = "payperiod";
		/**
		 * 分期偿还金额
		 */
		public static final String amortization = "amortization";
		/**
		 * 信用额度
		 */
		public static final String insuranceRate = "insuranceRate";
		/**
		 * 开户银行
		 */
		public static final String bankDeposit = "bankDeposit";
		/**
		 * 银行账号
		 */
		public static final String bankAccount = "bankAccount";
		/**
		 * 开户行地址
		 */
		public static final String bankAddress = "bankAddress";
		/**
		 * 联系人
		 */
		public static final String contactPerson = "contactPerson";
		/**
		 * 联系电话
		 */
		public static final String tel = "tel";
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

	//合同UUID
	private String basBargainUuid;
	//客户UUID
	private String basCustomerUuid;
	//客户代码
	private String customerCode;
	//合同号
	private String bargainCode;
	//合同名称
	private String bargainName;
	//合同英文名称
	private String bargainNameEn;
	//合同内容
	private String bargainDesc;
	//签订日期
	private Date beginDate;
	//有效日期
	private Date endDate;
	//币别编码
	private String currencyCode;
	//币别名称
	private String currencyName;
	//付款周期
	private Double payperiod;
	//分期偿还金额
	private Double amortization;
	//信用额度
	private Double insuranceRate;
	//开户银行
	private String bankDeposit;
	//银行账号
	private String bankAccount;
	//开户行地址
	private String bankAddress;
	//联系人
	private String contactPerson;
	//联系电话
	private String tel;
	//备注
	private String remark;
	//状态
	private String status;
	//控制字
	private String controlWord;
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
	 * Get 合同UUID
	 */
	@Column(name = "BAS_BARGAIN_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getBasBargainUuid() {
		return basBargainUuid;
	}

	/**
	 * Set 合同UUID
	 */
	public void setBasBargainUuid(String basBargainUuid) {
		this.basBargainUuid = basBargainUuid;
		addValidField(FieldNames.basBargainUuid);
	}

	/**
	 * Get 客户UUID
	 */
	@Column(name = "BAS_CUSTOMER_UUID")
	public String getBasCustomerUuid() {
		return basCustomerUuid;
	}

	/**
	 * Set 客户UUID
	 */
	public void setBasCustomerUuid(String basCustomerUuid) {
		this.basCustomerUuid = basCustomerUuid;
		addValidField(FieldNames.basCustomerUuid);
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
	 * Get 合同号
	 */
	@Column(name = "BARGAIN_CODE")
	public String getBargainCode() {
		return bargainCode;
	}

	/**
	 * Set 合同号
	 */
	public void setBargainCode(String bargainCode) {
		this.bargainCode = bargainCode;
		addValidField(FieldNames.bargainCode);
	}

	/**
	 * Get 合同名称
	 */
	@Column(name = "BARGAIN_NAME")
	public String getBargainName() {
		return bargainName;
	}

	/**
	 * Set 合同名称
	 */
	public void setBargainName(String bargainName) {
		this.bargainName = bargainName;
		addValidField(FieldNames.bargainName);
	}

	/**
	 * Get 合同英文名称
	 */
	@Column(name = "BARGAIN_NAME_EN")
	public String getBargainNameEn() {
		return bargainNameEn;
	}

	/**
	 * Set 合同英文名称
	 */
	public void setBargainNameEn(String bargainNameEn) {
		this.bargainNameEn = bargainNameEn;
		addValidField(FieldNames.bargainNameEn);
	}

	/**
	 * Get 合同内容
	 */
	@Column(name = "BARGAIN_DESC")
	public String getBargainDesc() {
		return bargainDesc;
	}

	/**
	 * Set 合同内容
	 */
	public void setBargainDesc(String bargainDesc) {
		this.bargainDesc = bargainDesc;
		addValidField(FieldNames.bargainDesc);
	}

	/**
	 * Get 签订日期
	 */
	@Column(name = "BEGIN_DATE")
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * Set 签订日期
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
		addValidField(FieldNames.beginDate);
	}

	/**
	 * Get 有效日期
	 */
	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Set 有效日期
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		addValidField(FieldNames.endDate);
	}

	/**
	 * Get 币别编码
	 */
	@Column(name = "CURRENCY_CODE")
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * Set 币别编码
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
		addValidField(FieldNames.currencyCode);
	}

	/**
	 * Get 币别名称
	 */
	@Column(name = "CURRENCY_NAME")
	public String getCurrencyName() {
		return currencyName;
	}

	/**
	 * Set 币别名称
	 */
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
		addValidField(FieldNames.currencyName);
	}

	/**
	 * Get 付款周期
	 */
	@Column(name = "PAYPERIOD")
	public Double getPayperiod() {
		return payperiod;
	}

	/**
	 * Set 付款周期
	 */
	public void setPayperiod(Double payperiod) {
		this.payperiod = payperiod;
		addValidField(FieldNames.payperiod);
	}

	/**
	 * Get 分期偿还金额
	 */
	@Column(name = "AMORTIZATION")
	public Double getAmortization() {
		return amortization;
	}

	/**
	 * Set 分期偿还金额
	 */
	public void setAmortization(Double amortization) {
		this.amortization = amortization;
		addValidField(FieldNames.amortization);
	}

	/**
	 * Get 信用额度
	 */
	@Column(name = "INSURANCE_RATE")
	public Double getInsuranceRate() {
		return insuranceRate;
	}

	/**
	 * Set 信用额度
	 */
	public void setInsuranceRate(Double insuranceRate) {
		this.insuranceRate = insuranceRate;
		addValidField(FieldNames.insuranceRate);
	}

	/**
	 * Get 开户银行
	 */
	@Column(name = "BANK_DEPOSIT")
	public String getBankDeposit() {
		return bankDeposit;
	}

	/**
	 * Set 开户银行
	 */
	public void setBankDeposit(String bankDeposit) {
		this.bankDeposit = bankDeposit;
		addValidField(FieldNames.bankDeposit);
	}

	/**
	 * Get 银行账号
	 */
	@Column(name = "BANK_ACCOUNT")
	public String getBankAccount() {
		return bankAccount;
	}

	/**
	 * Set 银行账号
	 */
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
		addValidField(FieldNames.bankAccount);
	}

	/**
	 * Get 开户行地址
	 */
	@Column(name = "BANK_ADDRESS")
	public String getBankAddress() {
		return bankAddress;
	}

	/**
	 * Set 开户行地址
	 */
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
		addValidField(FieldNames.bankAddress);
	}

	/**
	 * Get 联系人
	 */
	@Column(name = "CONTACT_PERSON")
	public String getContactPerson() {
		return contactPerson;
	}

	/**
	 * Set 联系人
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
		addValidField(FieldNames.contactPerson);
	}

	/**
	 * Get 联系电话
	 */
	@Column(name = "TEL")
	public String getTel() {
		return tel;
	}

	/**
	 * Set 联系电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
		addValidField(FieldNames.tel);
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
	private String prrmaryKeyName="barcodeUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
