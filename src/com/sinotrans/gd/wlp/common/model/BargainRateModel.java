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
 * Model class for 合同费率信息
 */
@Entity
@Table(name = "BARGAIN_RATE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BargainRateModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BargainRate";

	public static final class FieldNames {
		/**
		 * 合同UUID
		 */
		public static final String bargainRateUuid = "bargainRateUuid";
		/**
		 * 合同UUID
		 */
		public static final String basBargainUuid = "basBargainUuid";
		/**
		 * 费率UUID
		 */
		public static final String chargeRateUuid = "chargeRateUuid";
		/**
		 * 合同名称
		 */
		public static final String bargainName = "bargainName";
		/**
		 * 费率名称
		 */
		public static final String chargeRateName = "chargeRateName";
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
	private String bargainRateUuid;
	//合同UUID
	private String basBargainUuid;
	//费率UUID
	private String chargeRateUuid;
	//合同名称
	private String bargainName;
	//费率名称
	private String chargeRateName;
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
	@Column(name = "BARGAIN_RATE_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getBargainRateUuid() {
		return bargainRateUuid;
	}

	/**
	 * Set 合同UUID
	 */
	public void setBargainRateUuid(String bargainRateUuid) {
		this.bargainRateUuid = bargainRateUuid;
		addValidField(FieldNames.bargainRateUuid);
	}

	/**
	 * Get 合同UUID
	 */
	@Column(name = "BAS_BARGAIN_UUID")
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
	 * Get 费率UUID
	 */
	@Column(name = "CHARGE_RATE_UUID")
	public String getChargeRateUuid() {
		return chargeRateUuid;
	}

	/**
	 * Set 费率UUID
	 */
	public void setChargeRateUuid(String chargeRateUuid) {
		this.chargeRateUuid = chargeRateUuid;
		addValidField(FieldNames.chargeRateUuid);
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
	 * Get 费率名称
	 */
	@Column(name = "CHARGE_RATE_NAME")
	public String getChargeRateName() {
		return chargeRateName;
	}

	/**
	 * Set 费率名称
	 */
	public void setChargeRateName(String chargeRateName) {
		this.chargeRateName = chargeRateName;
		addValidField(FieldNames.chargeRateName);
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
	private String prrmaryKeyName="bargainRateUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
