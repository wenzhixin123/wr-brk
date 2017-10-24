package com.sinotrans.gd.wlp.system.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class GrantedUrlsQueryItem extends BaseQueryItem {

	private String menuItemUrl;
	private String menuItemAction;

	@Column(name = "MENU_ITEM_URL")
	public String getMenuItemUrl() {
		return menuItemUrl;
	}

	public void setMenuItemUrl(String menuItemUrl) {
		this.menuItemUrl = menuItemUrl;
		addValidField("menuItemUrl");
	}

	@Column(name = "MENU_ITEM_ACTION")
	public String getMenuItemAction() {
		return menuItemAction;
	}

	public void setMenuItemAction(String menuItemAction) {
		this.menuItemAction = menuItemAction;
		addValidField("menuItemAction");
	}

}
