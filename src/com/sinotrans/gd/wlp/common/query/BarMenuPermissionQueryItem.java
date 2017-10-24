package com.sinotrans.gd.wlp.common.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class BarMenuPermissionQueryItem extends BaseQueryItem {


	private String menuItemSeq;
	private String menuItemUrl;
	private String menuItemName;
	private String menuItemCode;
	private String menuItemNameCn;
	
	@Column(name = "MENU_ITEM_NAME_CN")
	public String getMenuItemNameCn() {
		return menuItemNameCn;
	}

	public void setMenuItemNameCn(String menuItemNameCn) {
		this.menuItemNameCn = menuItemNameCn;
		addValidField("menuItemNameCn");
	}

	@Column(name = "MENU_ITEM_CODE")
	public String getMenuItemCode() {
		return menuItemCode;
	}

	public void setMenuItemCode(String menuItemCode) {
		this.menuItemCode = menuItemCode;
		addValidField("menuItemCode");
	}

	@Column(name = "MENU_ITEM_NAME")
	public String getMenuItemName() {
		return menuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
		addValidField("menuItemName");
	}

	@Column(name = "MENU_ITEM_SEQ")
	public String getMenuItemSeq() {
		return menuItemSeq;
	}

	public void setMenuItemSeq(String menuItemSeq) {
		this.menuItemSeq = menuItemSeq;
		addValidField("menuItemSeq");
	}

	@Column(name = "MENU_ITEM_URL")
	public String getMenuItemUrl() {
		return menuItemUrl;
	}

	public void setMenuItemUrl(String menuItemUrl) {
		this.menuItemUrl = menuItemUrl;
		addValidField("menuItemUrl");
	}

}
