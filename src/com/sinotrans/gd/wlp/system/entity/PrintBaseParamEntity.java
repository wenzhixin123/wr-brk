package com.sinotrans.gd.wlp.system.entity;

import java.util.List;

public class PrintBaseParamEntity {
	private String key;
	private String value;
	private List valueArray;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List getValueArray() {
		return valueArray;
	}
	public void setValueArray(List valueArray) {
		this.valueArray = valueArray;
	}
}
