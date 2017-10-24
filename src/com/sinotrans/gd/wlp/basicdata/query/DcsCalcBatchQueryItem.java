package com.sinotrans.gd.wlp.basicdata.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@Entity
public class DcsCalcBatchQueryItem extends BaseQueryItem {

	private Integer notsendCount;
	private String calcBatchId;
	private String calcDataType;
	private String calcDataSource;
	private String calcBatchNo;
	private String logisticsOrderNo;
	private String calcOrderNo;
	private String accountingCustomerCode;
	private String accountingCustomerDesc;
	private String controlWord;
	private String opeType;
	private Date submitArapTime;
	private String officeCode;
	private Long recVer;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date modifyTime;

	@Column(name = "NOTSEND_COUNT")
	public Integer getNotsendCount() {
		return notsendCount;
	}

	public void setNotsendCount(Integer notsendCount) {
		this.notsendCount = notsendCount;
		addValidField("notsendCount");
	}

	@Column(name = "CALC_BATCH_ID")
	public String getCalcBatchId() {
		return calcBatchId;
	}

	public void setCalcBatchId(String calcBatchId) {
		this.calcBatchId = calcBatchId;
		addValidField("calcBatchId");
	}

	@Column(name = "CALC_DATA_TYPE")
	public String getCalcDataType() {
		return calcDataType;
	}

	public void setCalcDataType(String calcDataType) {
		this.calcDataType = calcDataType;
		addValidField("calcDataType");
	}

	@Column(name = "CALC_DATA_SOURCE")
	public String getCalcDataSource() {
		return calcDataSource;
	}

	public void setCalcDataSource(String calcDataSource) {
		this.calcDataSource = calcDataSource;
		addValidField("calcDataSource");
	}

	@Column(name = "CALC_BATCH_NO")
	public String getCalcBatchNo() {
		return calcBatchNo;
	}

	public void setCalcBatchNo(String calcBatchNo) {
		this.calcBatchNo = calcBatchNo;
		addValidField("calcBatchNo");
	}

	@Column(name = "LOGISTICS_ORDER_NO")
	public String getLogisticsOrderNo() {
		return logisticsOrderNo;
	}

	public void setLogisticsOrderNo(String logisticsOrderNo) {
		this.logisticsOrderNo = logisticsOrderNo;
		addValidField("logisticsOrderNo");
	}

	@Column(name = "CALC_ORDER_NO")
	public String getCalcOrderNo() {
		return calcOrderNo;
	}

	public void setCalcOrderNo(String calcOrderNo) {
		this.calcOrderNo = calcOrderNo;
		addValidField("calcOrderNo");
	}

	@Column(name = "ACCOUNTING_CUSTOMER_CODE")
	public String getAccountingCustomerCode() {
		return accountingCustomerCode;
	}

	public void setAccountingCustomerCode(String accountingCustomerCode) {
		this.accountingCustomerCode = accountingCustomerCode;
		addValidField("accountingCustomerCode");
	}

	@Column(name = "ACCOUNTING_CUSTOMER_DESC")
	public String getAccountingCustomerDesc() {
		return accountingCustomerDesc;
	}

	public void setAccountingCustomerDesc(String accountingCustomerDesc) {
		this.accountingCustomerDesc = accountingCustomerDesc;
		addValidField("accountingCustomerDesc");
	}

	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField("controlWord");
	}

	@Column(name = "OPE_TYPE")
	public String getOpeType() {
		return opeType;
	}

	public void setOpeType(String opeType) {
		this.opeType = opeType;
		addValidField("opeType");
	}

	@Column(name = "SUBMIT_ARAP_TIME")
	public Date getSubmitArapTime() {
		return submitArapTime;
	}

	public void setSubmitArapTime(Date submitArapTime) {
		this.submitArapTime = submitArapTime;
		addValidField("submitArapTime");
	}

	@Column(name = "OFFICE_CODE")
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
		addValidField("officeCode");
	}

	@Column(name = "REC_VER")
	public Long getRecVer() {
		return recVer;
	}

	public void setRecVer(Long recVer) {
		this.recVer = recVer;
		addValidField("recVer");
	}

	@Column(name = "CREATOR")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
		addValidField("creator");
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		addValidField("createTime");
	}

	@Column(name = "MODIFIER")
	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
		addValidField("modifier");
	}

	@Column(name = "MODIFY_TIME")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
		addValidField("modifyTime");
	}

}
