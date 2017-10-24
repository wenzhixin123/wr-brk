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
 * Model class for 结算单
 */
@Entity
@Table(name = "RECEIPTS_CHARGE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class ReceiptsChargeModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "ReceiptsCharge";

	public static final class FieldNames {
		/**
		 * UUID
		 */
		public static final String receiptsChargeUuid = "receiptsChargeUuid";
		/**
		 * 结算单号
		 */
		public static final String receiptsChargeNo = "receiptsChargeNo";
		/**
		 * 作业单UUID
		 */
		public static final String logisticsOrderUuid = "logisticsOrderUuid";
		/**
		 * 作业单号
		 */
		public static final String logisticsOrderNo = "logisticsOrderNo";
		/**
		 * 结算日期
		 */
		public static final String receiptDate = "receiptDate";
		/**
		 * 收付标识
		 */
		public static final String rpType = "rpType";
		/**
		 * 交易方代码
		 */
		public static final String businessCode = "businessCode";
		/**
		 * 交易方描述
		 */
		public static final String businessDesc = "businessDesc";
		/**
		 * 结算单位
		 */
		public static final String payerCode = "payerCode";
		/**
		 * 结算单位描述
		 */
		public static final String payerDesc = "payerDesc";
		/**
		 * 确认人
		 */
		public static final String confirmedPerson = "confirmedPerson";
		/**
		 * 确认时间
		 */
		public static final String confirmedDate = "confirmedDate";
		/**
		 * 确认描述
		 */
		public static final String confirmedNote = "confirmedNote";
		/**
		 * cancelPerson
		 */
		public static final String cancelPerson = "cancelPerson";
		/**
		 * 取消时间
		 */
		public static final String cancelDate = "cancelDate";
		/**
		 * 取消描述
		 */
		public static final String cancelNote = "cancelNote";
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

	//UUID
	private String receiptsChargeUuid;
	//结算单号
	private String receiptsChargeNo;
	//作业单UUID
	private String logisticsOrderUuid;
	//作业单号
	private String logisticsOrderNo;
	//结算日期
	private Date receiptDate;
	//收付标识
	private String rpType;
	//交易方代码
	private String businessCode;
	//交易方描述
	private String businessDesc;
	//结算单位
	private String payerCode;
	//结算单位描述
	private String payerDesc;
	//确认人
	private String confirmedPerson;
	//确认时间
	private Date confirmedDate;
	//确认描述
	private String confirmedNote;
	//cancelPerson
	private String cancelPerson;
	//取消时间
	private Date cancelDate;
	//取消描述
	private String cancelNote;
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
	 * Get UUID
	 */
	@Column(name = "RECEIPTS_CHARGE_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
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
	 * Get 作业单号
	 */
	@Column(name = "LOGISTICS_ORDER_NO")
	public String getLogisticsOrderNo() {
		return logisticsOrderNo;
	}

	/**
	 * Set 作业单号
	 */
	public void setLogisticsOrderNo(String logisticsOrderNo) {
		this.logisticsOrderNo = logisticsOrderNo;
		addValidField(FieldNames.logisticsOrderNo);
	}

	/**
	 * Get 结算日期
	 */
	@Column(name = "RECEIPT_DATE")
	public Date getReceiptDate() {
		return receiptDate;
	}

	/**
	 * Set 结算日期
	 */
	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
		addValidField(FieldNames.receiptDate);
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
	 * Get 交易方代码
	 */
	@Column(name = "BUSINESS_CODE")
	public String getBusinessCode() {
		return businessCode;
	}

	/**
	 * Set 交易方代码
	 */
	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
		addValidField(FieldNames.businessCode);
	}

	/**
	 * Get 交易方描述
	 */
	@Column(name = "BUSINESS_DESC")
	public String getBusinessDesc() {
		return businessDesc;
	}

	/**
	 * Set 交易方描述
	 */
	public void setBusinessDesc(String businessDesc) {
		this.businessDesc = businessDesc;
		addValidField(FieldNames.businessDesc);
	}

	/**
	 * Get 结算单位
	 */
	@Column(name = "PAYER_CODE")
	public String getPayerCode() {
		return payerCode;
	}

	/**
	 * Set 结算单位
	 */
	public void setPayerCode(String payerCode) {
		this.payerCode = payerCode;
		addValidField(FieldNames.payerCode);
	}

	/**
	 * Get 结算单位描述
	 */
	@Column(name = "PAYER_DESC")
	public String getPayerDesc() {
		return payerDesc;
	}

	/**
	 * Set 结算单位描述
	 */
	public void setPayerDesc(String payerDesc) {
		this.payerDesc = payerDesc;
		addValidField(FieldNames.payerDesc);
	}

	/**
	 * Get 确认人
	 */
	@Column(name = "CONFIRMED_PERSON")
	public String getConfirmedPerson() {
		return confirmedPerson;
	}

	/**
	 * Set 确认人
	 */
	public void setConfirmedPerson(String confirmedPerson) {
		this.confirmedPerson = confirmedPerson;
		addValidField(FieldNames.confirmedPerson);
	}

	/**
	 * Get 确认时间
	 */
	@Column(name = "CONFIRMED_DATE")
	public Date getConfirmedDate() {
		return confirmedDate;
	}

	/**
	 * Set 确认时间
	 */
	public void setConfirmedDate(Date confirmedDate) {
		this.confirmedDate = confirmedDate;
		addValidField(FieldNames.confirmedDate);
	}

	/**
	 * Get 确认描述
	 */
	@Column(name = "CONFIRMED_NOTE")
	public String getConfirmedNote() {
		return confirmedNote;
	}

	/**
	 * Set 确认描述
	 */
	public void setConfirmedNote(String confirmedNote) {
		this.confirmedNote = confirmedNote;
		addValidField(FieldNames.confirmedNote);
	}

	/**
	 * Get cancelPerson
	 */
	@Column(name = "CANCEL_PERSON")
	public String getCancelPerson() {
		return cancelPerson;
	}

	/**
	 * Set cancelPerson
	 */
	public void setCancelPerson(String cancelPerson) {
		this.cancelPerson = cancelPerson;
		addValidField(FieldNames.cancelPerson);
	}

	/**
	 * Get 取消时间
	 */
	@Column(name = "CANCEL_DATE")
	public Date getCancelDate() {
		return cancelDate;
	}

	/**
	 * Set 取消时间
	 */
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
		addValidField(FieldNames.cancelDate);
	}

	/**
	 * Get 取消描述
	 */
	@Column(name = "CANCEL_NOTE")
	public String getCancelNote() {
		return cancelNote;
	}

	/**
	 * Set 取消描述
	 */
	public void setCancelNote(String cancelNote) {
		this.cancelNote = cancelNote;
		addValidField(FieldNames.cancelNote);
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
	private String prrmaryKeyName="receiptsChargeUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
