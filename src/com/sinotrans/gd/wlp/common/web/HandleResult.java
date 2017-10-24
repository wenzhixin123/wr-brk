package com.sinotrans.gd.wlp.common.web;

public class HandleResult {
	private String HandleClass; // 处理类
	private String type; // 输出结果类型，JSON、XML、TEXT (暂时不用)
	private String result; // 输出结果
	private boolean canHandle; // 能否处理
	private boolean isResponsed; // 是否已经输出，ture为已经输出，不需再次输出

	public HandleResult() {
		super();
		this.canHandle = false;
		this.isResponsed = false;
		this.type = "JSON";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public boolean isCanHandle() {
		return canHandle;
	}

	public void setCanHandle(boolean canHandle) {
		this.canHandle = canHandle;
	}

	public boolean isResponsed() {
		return isResponsed;
	}

	public void setResponsed(boolean isResponsed) {
		this.isResponsed = isResponsed;
	}

	public String getHandleClass() {
		return HandleClass;
	}

	public void setHandleClass(String handleClass) {
		HandleClass = handleClass;
	}
}
