package com.sinotrans.gd.wlp.common.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class GetCodeDefQueryItem extends BaseQueryItem {

	private String displayValue;
	private String displayValueEn;
	private String remark;

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

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		addValidField("remark");
	}

}
