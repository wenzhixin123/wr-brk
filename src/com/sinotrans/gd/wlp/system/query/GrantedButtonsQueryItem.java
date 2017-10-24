package com.sinotrans.gd.wlp.system.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class GrantedButtonsQueryItem extends BaseQueryItem {

	private String btnCode;

	@Column(name = "BTN_CODE")
	public String getBtnCode() {
		return btnCode;
	}

	public void setBtnCode(String btnCode) {
		this.btnCode = btnCode;
		addValidField("btnCode");
	}

}
