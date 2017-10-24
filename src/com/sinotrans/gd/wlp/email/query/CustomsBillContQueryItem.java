package com.sinotrans.gd.wlp.email.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@Entity
public class CustomsBillContQueryItem extends BaseQueryItem {

	private String containerno;
	private String billladingno;
	private Date workdate;

	@Column(name = "CONTAINERNO")
	public String getContainerno() {
		return containerno;
	}

	public void setContainerno(String containerno) {
		this.containerno = containerno;
		addValidField("containerno");
	}

	@Column(name = "BILLLADINGNO")
	public String getBillladingno() {
		return billladingno;
	}

	public void setBillladingno(String billladingno) {
		this.billladingno = billladingno;
		addValidField("billladingno");
	}

	@Column(name = "WORKDATE")
	public Date getWorkdate() {
		return workdate;
	}

	public void setWorkdate(Date workdate) {
		this.workdate = workdate;
		addValidField("workdate");
	}

}
