package com.sinotrans.gd.wlp.system.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class ScanerMenuItemByUserCodeQueryItem extends BaseQueryItem {

	private String sysMenuItemUuid;
	private String sysMenuGroupUuid;
	private String menuItemName;
	private String menuItemNameCn;
	private String menuItemUrl;
	private Long menuItemSeq;
	private String status;

	@Column(name = "SYS_MENU_ITEM_UUID")
	public String getSysMenuItemUuid() {
		return sysMenuItemUuid;
	}

	public void setSysMenuItemUuid(String sysMenuItemUuid) {
		this.sysMenuItemUuid = sysMenuItemUuid;
		addValidField("sysMenuItemUuid");
	}

	@Column(name = "SYS_MENU_GROUP_UUID")
	public String getSysMenuGroupUuid() {
		return sysMenuGroupUuid;
	}

	public void setSysMenuGroupUuid(String sysMenuGroupUuid) {
		this.sysMenuGroupUuid = sysMenuGroupUuid;
		addValidField("sysMenuGroupUuid");
	}

	@Column(name = "MENU_ITEM_NAME")
	public String getMenuItemName() {
		return menuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
		addValidField("menuItemName");
	}

	@Column(name = "MENU_ITEM_NAME_CN")
	public String getMenuItemNameCn() {
		return menuItemNameCn;
	}

	public void setMenuItemNameCn(String menuItemNameCn) {
		this.menuItemNameCn = menuItemNameCn;
		addValidField("menuItemNameCn");
	}

	@Column(name = "MENU_ITEM_URL")
	public String getMenuItemUrl() {
		return menuItemUrl;
	}

	public void setMenuItemUrl(String menuItemUrl) {
		this.menuItemUrl = menuItemUrl;
		addValidField("menuItemUrl");
	}

	@Column(name = "MENU_ITEM_SEQ")
	public Long getMenuItemSeq() {
		return menuItemSeq;
	}

	public void setMenuItemSeq(Long menuItemSeq) {
		this.menuItemSeq = menuItemSeq;
		addValidField("menuItemSeq");
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		addValidField("status");
	}

}
