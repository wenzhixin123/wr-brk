package com.sinotrans.gd.wlp.system.entity;

import java.util.List;

import com.sinotrans.gd.wlp.system.query.ExtApiTokenQueryItem;

public class ExtApiTokenEntity extends ExtApiTokenQueryItem {
	private List<String> messages;
	private String resultStatus;
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	public String getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	
}
