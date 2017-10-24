package com.sinotrans.gd.wlp.basicdata.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class CodeDefBySpecialCodeTypeQueryCondition extends BaseQueryCondition {

	private String officeCode;
	private String codeValue;
	private String basCodeTypeUuid;
	private String status;
	private String modifiable;
	private String codeNumber;

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

	public String getBasCodeTypeUuid() {
		return basCodeTypeUuid;
	}

	public void setBasCodeTypeUuid(String basCodeTypeUuid) {
		this.basCodeTypeUuid = basCodeTypeUuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getModifiable() {
		return modifiable;
	}

	public void setModifiable(String modifiable) {
		this.modifiable = modifiable;
	}

	public String getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}

}
