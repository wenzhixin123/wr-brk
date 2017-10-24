package com.sinotrans.gd.wlp.basicdata.entity;

import com.sinotrans.gd.wlp.basicdata.model.BasCustomerModel;

@SuppressWarnings("serial")
public class BasCustomerEntity extends BasCustomerModel {
	private String uuid;

	private String rownum;

	//控制字
	private String diyCtrWord;
	
	public String getDiyCtrWord() {
		return diyCtrWord;
	}

	public void setDiyCtrWord(String diyCtrWord) {
		this.diyCtrWord = diyCtrWord;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
