package com.sinotrans.gd.wlp.basicdata.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class CodeDefBySpecialCodeTypeQueryItem extends BaseQueryItem {

	private String basCodeDefUuid;
	private String basCodeTypeUuid;
	private String codeValue;
	private Double codeNumber;
	private String displayValue;
	private String displayValueEn;
	private Integer modifiable;
	private String remark;
	private String status;
	private String centerCode;
	private String controlWord;
	private String officeCode;
	private Long recVer;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date modifyTime;

	@Column(name = "BAS_CODE_DEF_UUID")
	public String getBasCodeDefUuid() {
		return basCodeDefUuid;
	}

	public void setBasCodeDefUuid(String basCodeDefUuid) {
		this.basCodeDefUuid = basCodeDefUuid;
		addValidField("basCodeDefUuid");
	}

	@Column(name = "BAS_CODE_TYPE_UUID")
	public String getBasCodeTypeUuid() {
		return basCodeTypeUuid;
	}

	public void setBasCodeTypeUuid(String basCodeTypeUuid) {
		this.basCodeTypeUuid = basCodeTypeUuid;
		addValidField("basCodeTypeUuid");
	}

	@Column(name = "CODE_VALUE")
	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
		addValidField("codeValue");
	}

	@Column(name = "CODE_NUMBER")
	public Double getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(Double codeNumber) {
		this.codeNumber = codeNumber;
		addValidField("codeNumber");
	}

	@Column(name = "DISPLAY_VALUE")
	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
		addValidField("displayValue");
	}

	@Column(name = "DISPLAY_VALUE_EN")
	public String getDisplayValueEn() {
		return displayValueEn;
	}

	public void setDisplayValueEn(String displayValueEn) {
		this.displayValueEn = displayValueEn;
		addValidField("displayValueEn");
	}

	@Column(name = "MODIFIABLE")
	public Integer getModifiable() {
		return modifiable;
	}

	public void setModifiable(Integer modifiable) {
		this.modifiable = modifiable;
		addValidField("modifiable");
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		addValidField("remark");
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		addValidField("status");
	}

	@Column(name = "CENTER_CODE")
	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
		addValidField("centerCode");
	}

	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField("controlWord");
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
