package com.sinotrans.gd.wlp.email.entity;

import java.util.List;

public class EmailMainContentEntity {
	private String address;
	private String title;
	private String operationType;
	private List<SendEmailDataEntity> detailList;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<SendEmailDataEntity> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<SendEmailDataEntity> detailList) {
		this.detailList = detailList;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	
}
