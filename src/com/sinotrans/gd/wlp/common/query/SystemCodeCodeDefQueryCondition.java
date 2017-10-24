package com.sinotrans.gd.wlp.common.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class SystemCodeCodeDefQueryCondition extends BaseQueryCondition {

	private String typeCode;
	private String status;
	private String officeCode;
	private String typeValue;
	private String codeValue;

	public SystemCodeCodeDefQueryCondition(String typeCode,String status,String officeCode) {
		this.typeCode=typeCode;
		this.status=status;
		this.officeCode=officeCode;
	}
	
	public SystemCodeCodeDefQueryCondition(String typeCode,String status,String officeCode,String typeValue) {
		this.typeCode=typeCode;
		this.status=status;
		this.officeCode=officeCode;
		this.typeValue=typeValue;
	}
	
	public SystemCodeCodeDefQueryCondition() {
		// TODO Auto-generated constructor stub
	}
	
	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public String getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

}
