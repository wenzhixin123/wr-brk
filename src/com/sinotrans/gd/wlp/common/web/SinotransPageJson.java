/**
 * 
 */
package com.sinotrans.gd.wlp.common.web;

import java.io.Serializable;

/**
 * @author sky
 * 
 *         系统页面Json
 * 
 */
@SuppressWarnings("serial")
public class SinotransPageJson implements Serializable {

	public SinotransPageJson() {
	}

	public SinotransPageJson(boolean result) {
		this.result = result;
	}

	public SinotransPageJson(boolean result, String msg) {
		this.result = result;
		this.msg = msg;
	}

	public SinotransPageJson(boolean result, String msg, String error) {
		this.result = result;
		this.msg = msg;
		this.error = error;
	}

	public SinotransPageJson(boolean result, String msg, String error,
			Object object) {
		this.result = result;
		this.msg = msg;
		this.error = error;
		this.object = object;
	}

	public SinotransPageJson(boolean result, Object object) {
		this.result = result;
		this.object = object;
	}

	/**
	 * 执行结果 失败 flase 成功 true
	 */
	private boolean result;

	/**
	 * 信息提示
	 */
	private String msg;
	/**
	 * 错误异常信息
	 */
	private String error;

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	private Object object;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
