package com.sinotrans.gd.wlp.basicdata.entity;

public class BasLottreeEntity {
	
private String id;
private String text;
private String preId;
private String remark;
private Object data;

public Object getData() {
	return data;
}
public void setData(Object data) {
	this.data = data;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}
public String getPreId() {
	return preId;
}
public void setPreId(String preId) {
	this.preId = preId;
}

}
