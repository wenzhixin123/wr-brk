package com.sinotrans.gd.wlp.basicdata.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class BasCodeTypeByTypeCodeAndCodeValueQueryCondition extends
		BaseQueryCondition {

	public BasCodeTypeByTypeCodeAndCodeValueQueryCondition() {
	}

	public BasCodeTypeByTypeCodeAndCodeValueQueryCondition(String codeValue,
			String typeCode, String officeCode) {
		this.codeValue = codeValue;
		this.typeCode = typeCode;
		this.officeCode = officeCode;
	}
	
	public BasCodeTypeByTypeCodeAndCodeValueQueryCondition(String codeValue,
			String typeCode, String officeCode, String controlWord,String displayValueEn) {
		this.codeValue = codeValue;
		this.typeCode = typeCode;
		this.officeCode = officeCode;
		this.controlWord = controlWord;
		this.displayValueEn = displayValueEn;
	}

	public BasCodeTypeByTypeCodeAndCodeValueQueryCondition(String codeValue,
			String typeCode, String officeCode, String controlWord) {
		this.codeValue = codeValue;
		this.typeCode = typeCode;
		this.officeCode = officeCode;
		this.controlWord = controlWord;
	}

	private String codeValue;
	private String typeCode;
	private String officeCode;
	private String controlWord;
	private String displayValueEn;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDisplayValueEn() {
		return displayValueEn;
	}

	public void setDisplayValueEn(String displayValueEn) {
		this.displayValueEn = displayValueEn;
	}

	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

}
