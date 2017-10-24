/**
 * 
 */
package com.sinotrans.gd.wlp.system.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单选择树形结构
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class ModuleGroupMenuItemTrue implements Serializable {
	private String id;
	private String text;
	private String state;
	private String uuid;
	private String url;
	private List<ModuleGroupMenuItemTrue> children;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<ModuleGroupMenuItemTrue> getChildren() {
		return children;
	}

	public void setChildren(List<ModuleGroupMenuItemTrue> children) {
		this.children = children;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
