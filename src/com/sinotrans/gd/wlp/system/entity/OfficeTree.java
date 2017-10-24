/**
 * 
 */
package com.sinotrans.gd.wlp.system.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class OfficeTree implements Serializable {
	private String id;
	private String text;
	private String state;
	private List<OfficeTree> children;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<OfficeTree> getChildren() {
		return children;
	}

	public void setChildren(List<OfficeTree> children) {
		this.children = children;
	}
}
