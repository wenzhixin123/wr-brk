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
 * Model class for 基本费目
 */
@Entity
@Table(name = "BAS_CHARGE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasChargeModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasCharge";

	public static final class FieldNames {
		/**
		 * 基础费目UUID
		 */
		public static final String basChargeUuid = "basChargeUuid";
		/**
		 * 基础费目UUID
		 */
		public static final String basChargeTypeUuid = "basChargeTypeUuid";
		/**
		 * 费目代码
		 */
		public static final String chargeCode = "chargeCode";
		/**
		 * 费目名称
		 */
		public static final String chargeName = "chargeName";
		/**
		 * 费目英文名称
		 */
		public static final String chargeNameEn = "chargeNameEn";
		/**
		 * 状态
		 */
		public static final String status = "status";
		/**
		 * 控制字
		 */
		public static final String controlWord = "controlWord";
		/**
		 * 结算方式
		 */
		public static final String paymentType = "paymentType";
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

	//基础费目UUID
	private String basChargeUuid;
	//基础费目UUID
	private String basChargeTypeUuid;
	//费目代码
	private String chargeCode;
	//费目名称
	private String chargeName;
	//费目英文名称
	private String chargeNameEn;
	//状态
	private String status;
	//控制字
	private String controlWord;
	//结算方式
	private String paymentType;
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
	 * Get 基础费目UUID
	 */
	@Column(name = "BAS_CHARGE_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getBasChargeUuid() {
		return basChargeUuid;
	}

	/**
	 * Set 基础费目UUID
	 */
	public void setBasChargeUuid(String basChargeUuid) {
		this.basChargeUuid = basChargeUuid;
		addValidField(FieldNames.basChargeUuid);
	}

	/**
	 * Get 基础费目UUID
	 */
	@Column(name = "BAS_CHARGE_TYPE_UUID")
	public String getBasChargeTypeUuid() {
		return basChargeTypeUuid;
	}

	/**
	 * Set 基础费目UUID
	 */
	public void setBasChargeTypeUuid(String basChargeTypeUuid) {
		this.basChargeTypeUuid = basChargeTypeUuid;
		addValidField(FieldNames.basChargeTypeUuid);
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
	 * Get 费目英文名称
	 */
	@Column(name = "CHARGE_NAME_EN")
	public String getChargeNameEn() {
		return chargeNameEn;
	}

	/**
	 * Set 费目英文名称
	 */
	public void setChargeNameEn(String chargeNameEn) {
		this.chargeNameEn = chargeNameEn;
		addValidField(FieldNames.chargeNameEn);
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
	private String prrmaryKeyName="basChargeUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
