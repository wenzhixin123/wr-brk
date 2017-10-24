package com.sinotrans.gd.wlp.common.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class GetCodeDefQueryCondition extends BaseQueryCondition {

	private String codeValue;
	private String typeCode;
	private String officeCode;

	public GetCodeDefQueryCondition(String codeValue, String typeCode,
			String officeCode) {
		super();
		this.codeValue = codeValue;
		this.typeCode = typeCode;
		this.officeCode = officeCode;
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
