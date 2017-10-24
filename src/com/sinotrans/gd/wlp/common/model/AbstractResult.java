package com.sinotrans.gd.wlp.common.model;

public class AbstractResult {
	
	public final static String SUCCESS="SUCCESS";
	
	public final static String FAIL="FAIL";
	
	// 记录错误信息
	private String errMsg;
	//是否成功
	private String status;
	
	
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
