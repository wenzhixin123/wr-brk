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
 * Model class for 结算明细表
 */
@Entity
@Table(name = "RECEIPTS_CHARGE_DETAIL")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class ReceiptsChargeDetailModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "ReceiptsChargeDetail";

	public static final class FieldNames {
		/**
		 * UUID
		 */
		public static final String receiptsDetailUuid = "receiptsDetailUuid";
		/**
		 * UUID
		 */
		public static final String receiptsChargeUuid = "receiptsChargeUuid";
		/**
		 * 结算单号
		 */
		public static final String receiptsChargeNo = "receiptsChargeNo";
		/**
		 * 费率明细UUID
		 */
		public static final String chargeRateDetailUuid = "chargeRateDetailUuid";
		/**
		 * 序号
		 */
		public static final String seqNo = "seqNo";
		/**
		 * 收付标识
		 */
		public static final String rpType = "rpType";
		/**
		 * 费用描述
		 */
		public static final String chargeDesc = "chargeDesc";
		/**
		 * 费目代码
		 */
		public static final String chargeCode = "chargeCode";
		/**
		 * 费目名称
		 */
		public static final String chargeName = "chargeName";
		/**
		 * 业务数量
		 */
		public static final String qty = "qty";
		/**
		 * 计费数量
		 */
		public static final String chargeQty = "chargeQty";
		/**
		 * 包装单位描述
		 */
		public static final String unitDesc = "unitDesc";
		/**
		 * 包装单位代码
		 */
		public static final String unitCode = "unitCode";
		/**
		 * 费率
		 */
		public static final String rate = "rate";
		/**
		 * 原币种代码
		 */
		public static final String originalCurrency = "originalCurrency";
		/**
		 * 结算币种代码
		 */
		public static final String standardCurrency = "standardCurrency";
		/**
		 * 结算方式
		 */
		public static final String paymentType = "paymentType";
		/**
		 * 单价
		 */
		public static final String price = "price";
		/**
		 * 汇率
		 */
		public static final String exchangeRate = "exchangeRate";
		/**
		 * 折扣
		 */
		public static final String subDiscount = "subDiscount";
		/**
		 * 合计金额
		 */
		public static final String amount = "amount";
		/**
		 * 预估金额
		 */
		public static final String forecastAmount = "forecastAmount";
		/**
		 * 预付金额(原币)
		 */
		public static final String ppdAmount = "ppdAmount";
		/**
		 * 返单金额
		 */
		public static final String backAmount = "backAmount";
		/**
		 * 到付金额
		 */
		public static final String cllAmount = "cllAmount";
		/**
		 * 实际付款人代码
		 */
		public static final String payerCode = "payerCode";
		/**
		 * 实际付款人描述
		 */
		public static final String payerDesc = "payerDesc";
		/**
		 * 是否分摊（默认为0）
		 */
		public static final String isShare = "isShare";
		/**
		 * 是否内部结算（默认0）
		 */
		public static final String isInternal = "isInternal";
		/**
		 * 是否ORA确认（默认0）
		 */
		public static final String isOra = "isOra";
		/**
		 * 会计期
		 */
		public static final String oraDate = "oraDate";
		/**
		 * ora财务确认操作时间
		 */
		public static final String oraOptTime = "oraOptTime";
		/**
		 * ora财务确认人
		 */
		public static final String oraOptPerson = "oraOptPerson";
		/**
		 * 是否对账确认（默认为0）
		 */
		public static final String isComp = "isComp";
		/**
		 * 对账日期
		 */
		public static final String compDate = "compDate";
		/**
		 * 对账单号
		 */
		public static final String compNo = "compNo";
		/**
		 * 对账日期
		 */
		public static final String compOptTime = "compOptTime";
		/**
		 * 对账人
		 */
		public static final String compOptPerson = "compOptPerson";
		/**
		 * 税务发票号
		 */
		public static final String invoiceNo = "invoiceNo";
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

	//UUID
	private String receiptsDetailUuid;
	//UUID
	private String receiptsChargeUuid;
	//结算单号
	private String receiptsChargeNo;
	//费率明细UUID
	private String chargeRateDetailUuid;
	//序号
	private Long seqNo;
	//收付标识
	private String rpType;
	//费用描述
	private String chargeDesc;
	//费目代码
	private String chargeCode;
	//费目名称
	private String chargeName;
	//业务数量
	private Double qty;
	//计费数量
	private Double chargeQty;
	//包装单位描述
	private String unitDesc;
	//包装单位代码
	private String unitCode;
	//费率
	private Double rate;
	//原币种代码
	private String originalCurrency;
	//结算币种代码
	private String standardCurrency;
	//结算方式
	private String paymentType;
	//单价
	private Double price;
	//汇率
	private Double exchangeRate;
	//折扣
	private Double subDiscount;
	//合计金额
	private Double amount;
	//预估金额
	private Double forecastAmount;
	//预付金额(原币)
	private Double ppdAmount;
	//返单金额
	private Double backAmount;
	//到付金额
	private Double cllAmount;
	//实际付款人代码
	private String payerCode;
	//实际付款人描述
	private String payerDesc;
	//是否分摊（默认为0）
	private String isShare;
	//是否内部结算（默认0）
	private String isInternal;
	//是否ORA确认（默认0）
	private String isOra;
	//会计期
	private Date oraDate;
	//ora财务确认操作时间
	private Date oraOptTime;
	//ora财务确认人
	private String oraOptPerson;
	//是否对账确认（默认为0）
	private String isComp;
	//对账日期
	private Date compDate;
	//对账单号
	private String compNo;
	//对账日期
	private Date compOptTime;
	//对账人
	private String compOptPerson;
	//税务发票号
	private String invoiceNo;
	//状态
	private String status;
	//控制字
	private String controlWord;
	//备注
	private String remark;
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
	 * Get UUID
	 */
	@Column(name = "RECEIPTS_DETAIL_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getReceiptsDetailUuid() {
		return receiptsDetailUuid;
	}

	/**
	 * Set UUID
	 */
	public void setReceiptsDetailUuid(String receiptsDetailUuid) {
		this.receiptsDetailUuid = receiptsDetailUuid;
		addValidField(FieldNames.receiptsDetailUuid);
	}

	/**
	 * Get UUID
	 */
	@Column(name = "RECEIPTS_CHARGE_UUID")
	public String getReceiptsChargeUuid() {
		return receiptsChargeUuid;
	}

	/**
	 * Set UUID
	 */
	public void setReceiptsChargeUuid(String receiptsChargeUuid) {
		this.receiptsChargeUuid = receiptsChargeUuid;
		addValidField(FieldNames.receiptsChargeUuid);
	}

	/**
	 * Get 结算单号
	 */
	@Column(name = "RECEIPTS_CHARGE_NO")
	public String getReceiptsChargeNo() {
		return receiptsChargeNo;
	}

	/**
	 * Set 结算单号
	 */
	public void setReceiptsChargeNo(String receiptsChargeNo) {
		this.receiptsChargeNo = receiptsChargeNo;
		addValidField(FieldNames.receiptsChargeNo);
	}

	/**
	 * Get 费率明细UUID
	 */
	@Column(name = "CHARGE_RATE_DETAIL_UUID")
	public String getChargeRateDetailUuid() {
		return chargeRateDetailUuid;
	}

	/**
	 * Set 费率明细UUID
	 */
	public void setChargeRateDetailUuid(String chargeRateDetailUuid) {
		this.chargeRateDetailUuid = chargeRateDetailUuid;
		addValidField(FieldNames.chargeRateDetailUuid);
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
	 * Get 收付标识
	 * ：AR--应收、AP--应付、DD--代收代付
	 */
	@Column(name = "RP_TYPE")
	public String getRpType() {
		return rpType;
	}

	/**
	 * Set 收付标识
	 * ：AR--应收、AP--应付、DD--代收代付
	 */
	public void setRpType(String rpType) {
		this.rpType = rpType;
		addValidField(FieldNames.rpType);
	}

	/**
	 * Get 费用描述
	 */
	@Column(name = "CHARGE_DESC")
	public String getChargeDesc() {
		return chargeDesc;
	}

	/**
	 * Set 费用描述
	 */
	public void setChargeDesc(String chargeDesc) {
		this.chargeDesc = chargeDesc;
		addValidField(FieldNames.chargeDesc);
	}

	/**
	 * Get 费目代码
	 */
	@Column(name = "CHARGE_CODE")
	public String getChargeCode() {
		return chargeCode;
	}

	/**
	 * Set 费目代码
	 */
	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
		addValidField(FieldNames.chargeCode);
	}

	/**
	 * Get 费目名称
	 */
	@Column(name = "CHARGE_NAME")
	public String getChargeName() {
		return chargeName;
	}

	/**
	 * Set 费目名称
	 */
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
		addValidField(FieldNames.chargeName);
	}

	/**
	 * Get 业务数量
	 */
	@Column(name = "QTY")
	public Double getQty() {
		return qty;
	}

	/**
	 * Set 业务数量
	 */
	public void setQty(Double qty) {
		this.qty = qty;
		addValidField(FieldNames.qty);
	}

	/**
	 * Get 计费数量
	 */
	@Column(name = "CHARGE_QTY")
	public Double getChargeQty() {
		return chargeQty;
	}

	/**
	 * Set 计费数量
	 */
	public void setChargeQty(Double chargeQty) {
		this.chargeQty = chargeQty;
		addValidField(FieldNames.chargeQty);
	}

	/**
	 * Get 包装单位描述
	 */
	@Column(name = "UNIT_DESC")
	public String getUnitDesc() {
		return unitDesc;
	}

	/**
	 * Set 包装单位描述
	 */
	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc;
		addValidField(FieldNames.unitDesc);
	}

	/**
	 * Get 包装单位代码
	 */
	@Column(name = "UNIT_CODE")
	public String getUnitCode() {
		return unitCode;
	}

	/**
	 * Set 包装单位代码
	 */
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
		addValidField(FieldNames.unitCode);
	}

	/**
	 * Get 费率
	 */
	@Column(name = "RATE")
	public Double getRate() {
		return rate;
	}

	/**
	 * Set 费率
	 */
	public void setRate(Double rate) {
		this.rate = rate;
		addValidField(FieldNames.rate);
	}

	/**
	 * Get 原币种代码
	 */
	@Column(name = "ORIGINAL_CURRENCY")
	public String getOriginalCurrency() {
		return originalCurrency;
	}

	/**
	 * Set 原币种代码
	 */
	public void setOriginalCurrency(String originalCurrency) {
		this.originalCurrency = originalCurrency;
		addValidField(FieldNames.originalCurrency);
	}

	/**
	 * Get 结算币种代码
	 */
	@Column(name = "STANDARD_CURRENCY")
	public String getStandardCurrency() {
		return standardCurrency;
	}

	/**
	 * Set 结算币种代码
	 */
	public void setStandardCurrency(String standardCurrency) {
		this.standardCurrency = standardCurrency;
		addValidField(FieldNames.standardCurrency);
	}

	/**
	 * Get 结算方式
	 * ：CA--现金、CH--支票、TM--转帐、AD-- 预付款
	 */
	@Column(name = "PAYMENT_TYPE")
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * Set 结算方式
	 * ：CA--现金、CH--支票、TM--转帐、AD-- 预付款
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
		addValidField(FieldNames.paymentType);
	}

	/**
	 * Get 单价
	 */
	@Column(name = "PRICE")
	public Double getPrice() {
		return price;
	}

	/**
	 * Set 单价
	 */
	public void setPrice(Double price) {
		this.price = price;
		addValidField(FieldNames.price);
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
	 * Get 折扣
	 */
	@Column(name = "SUB_DISCOUNT")
	public Double getSubDiscount() {
		return subDiscount;
	}

	/**
	 * Set 折扣
	 */
	public void setSubDiscount(Double subDiscount) {
		this.subDiscount = subDiscount;
		addValidField(FieldNames.subDiscount);
	}

	/**
	 * Get 合计金额
	 */
	@Column(name = "AMOUNT")
	public Double getAmount() {
		return amount;
	}

	/**
	 * Set 合计金额
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
		addValidField(FieldNames.amount);
	}

	/**
	 * Get 预估金额
	 */
	@Column(name = "FORECAST_AMOUNT")
	public Double getForecastAmount() {
		return forecastAmount;
	}

	/**
	 * Set 预估金额
	 */
	public void setForecastAmount(Double forecastAmount) {
		this.forecastAmount = forecastAmount;
		addValidField(FieldNames.forecastAmount);
	}

	/**
	 * Get 预付金额(原币)
	 */
	@Column(name = "PPD_AMOUNT")
	public Double getPpdAmount() {
		return ppdAmount;
	}

	/**
	 * Set 预付金额(原币)
	 */
	public void setPpdAmount(Double ppdAmount) {
		this.ppdAmount = ppdAmount;
		addValidField(FieldNames.ppdAmount);
	}

	/**
	 * Get 返单金额
	 */
	@Column(name = "BACK_AMOUNT")
	public Double getBackAmount() {
		return backAmount;
	}

	/**
	 * Set 返单金额
	 */
	public void setBackAmount(Double backAmount) {
		this.backAmount = backAmount;
		addValidField(FieldNames.backAmount);
	}

	/**
	 * Get 到付金额
	 */
	@Column(name = "CLL_AMOUNT")
	public Double getCllAmount() {
		return cllAmount;
	}

	/**
	 * Set 到付金额
	 */
	public void setCllAmount(Double cllAmount) {
		this.cllAmount = cllAmount;
		addValidField(FieldNames.cllAmount);
	}

	/**
	 * Get 实际付款人代码
	 */
	@Column(name = "PAYER_CODE")
	public String getPayerCode() {
		return payerCode;
	}

	/**
	 * Set 实际付款人代码
	 */
	public void setPayerCode(String payerCode) {
		this.payerCode = payerCode;
		addValidField(FieldNames.payerCode);
	}

	/**
	 * Get 实际付款人描述
	 */
	@Column(name = "PAYER_DESC")
	public String getPayerDesc() {
		return payerDesc;
	}

	/**
	 * Set 实际付款人描述
	 */
	public void setPayerDesc(String payerDesc) {
		this.payerDesc = payerDesc;
		addValidField(FieldNames.payerDesc);
	}

	/**
	 * Get 是否分摊（默认为0）
	 * ： Y - 分摊
	 */
	@Column(name = "IS_SHARE")
	public String getIsShare() {
		return isShare;
	}

	/**
	 * Set 是否分摊（默认为0）
	 * ： Y - 分摊
	 */
	public void setIsShare(String isShare) {
		this.isShare = isShare;
		addValidField(FieldNames.isShare);
	}

	/**
	 * Get 是否内部结算（默认0）
	 * ：Y - 内部结算
	 */
	@Column(name = "IS_INTERNAL")
	public String getIsInternal() {
		return isInternal;
	}

	/**
	 * Set 是否内部结算（默认0）
	 * ：Y - 内部结算
	 */
	public void setIsInternal(String isInternal) {
		this.isInternal = isInternal;
		addValidField(FieldNames.isInternal);
	}

	/**
	 * Get 是否ORA确认（默认0）
	 * ： Y - oracle财务确认
	 */
	@Column(name = "IS_ORA")
	public String getIsOra() {
		return isOra;
	}

	/**
	 * Set 是否ORA确认（默认0）
	 * ： Y - oracle财务确认
	 */
	public void setIsOra(String isOra) {
		this.isOra = isOra;
		addValidField(FieldNames.isOra);
	}

	/**
	 * Get 会计期
	 */
	@Column(name = "ORA_DATE")
	public Date getOraDate() {
		return oraDate;
	}

	/**
	 * Set 会计期
	 */
	public void setOraDate(Date oraDate) {
		this.oraDate = oraDate;
		addValidField(FieldNames.oraDate);
	}

	/**
	 * Get ora财务确认操作时间
	 */
	@Column(name = "ORA_OPT_TIME")
	public Date getOraOptTime() {
		return oraOptTime;
	}

	/**
	 * Set ora财务确认操作时间
	 */
	public void setOraOptTime(Date oraOptTime) {
		this.oraOptTime = oraOptTime;
		addValidField(FieldNames.oraOptTime);
	}

	/**
	 * Get ora财务确认人
	 */
	@Column(name = "ORA_OPT_PERSON")
	public String getOraOptPerson() {
		return oraOptPerson;
	}

	/**
	 * Set ora财务确认人
	 */
	public void setOraOptPerson(String oraOptPerson) {
		this.oraOptPerson = oraOptPerson;
		addValidField(FieldNames.oraOptPerson);
	}

	/**
	 * Get 是否对账确认（默认为0）
	 * ：Y - 已对账
	 */
	@Column(name = "IS_COMP")
	public String getIsComp() {
		return isComp;
	}

	/**
	 * Set 是否对账确认（默认为0）
	 * ：Y - 已对账
	 */
	public void setIsComp(String isComp) {
		this.isComp = isComp;
		addValidField(FieldNames.isComp);
	}

	/**
	 * Get 对账日期
	 */
	@Column(name = "COMP_DATE")
	public Date getCompDate() {
		return compDate;
	}

	/**
	 * Set 对账日期
	 */
	public void setCompDate(Date compDate) {
		this.compDate = compDate;
		addValidField(FieldNames.compDate);
	}

	/**
	 * Get 对账单号
	 */
	@Column(name = "COMP_NO")
	public String getCompNo() {
		return compNo;
	}

	/**
	 * Set 对账单号
	 */
	public void setCompNo(String compNo) {
		this.compNo = compNo;
		addValidField(FieldNames.compNo);
	}

	/**
	 * Get 对账日期
	 */
	@Column(name = "COMP_OPT_TIME")
	public Date getCompOptTime() {
		return compOptTime;
	}

	/**
	 * Set 对账日期
	 */
	public void setCompOptTime(Date compOptTime) {
		this.compOptTime = compOptTime;
		addValidField(FieldNames.compOptTime);
	}

	/**
	 * Get 对账人
	 */
	@Column(name = "COMP_OPT_PERSON")
	public String getCompOptPerson() {
		return compOptPerson;
	}

	/**
	 * Set 对账人
	 */
	public void setCompOptPerson(String compOptPerson) {
		this.compOptPerson = compOptPerson;
		addValidField(FieldNames.compOptPerson);
	}

	/**
	 * Get 税务发票号
	 */
	@Column(name = "INVOICE_NO")
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * Set 税务发票号
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
		addValidField(FieldNames.invoiceNo);
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
	private String prrmaryKeyName="receiptsDetailUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
