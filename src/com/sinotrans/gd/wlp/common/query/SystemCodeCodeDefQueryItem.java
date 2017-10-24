package com.sinotrans.gd.wlp.common.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class SystemCodeCodeDefQueryItem extends BaseQueryItem {

	private String codeValue;
	private String displayValue;
	private String displayValueEn;
	private String controlWord;
	private String remark;

	
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
		addValidField("remark");
	}
	
	@Column(name = "CODE_VALUE")
	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
		addValidField("codeValue");
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

	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField("controlWord");
	}

}
