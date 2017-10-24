/**
 * 
 */
package com.sinotrans.gd.wlp.common.entity;

import java.io.Serializable;

/**
 * @author sky.huang
 * 
 *         页面展示层 Option 对象
 */
@SuppressWarnings("serial")
public class BasOption implements Serializable {

	/**
	 * SELECT OPTION KEY
	 */
	private String key;

	/**
	 * SELECT OPTION VALUE
	 */
	private String value;

	/**
	 * 默认是否选择
	 */
	private boolean selected;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public BasOption() {
	}

	public BasOption(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public BasOption(String key, String value, boolean selected) {
		this.key = key;
		this.value = value;
		this.selected = selected;
	}

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
}
