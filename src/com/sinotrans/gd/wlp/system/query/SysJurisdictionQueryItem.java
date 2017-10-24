package com.sinotrans.gd.wlp.system.query;

import com.sinotrans.framework.core.query.BaseQueryItem;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SysJurisdictionQueryItem extends BaseQueryItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8316403059658832423L;
	private String menuItemName;
	private String officeCode;
	private String sysViewButtonUuid;
	private String sysMenuItemUuid;
	private String btnCode;
	private String btnName;
	private String btnNameEn;
	private String btnUrl;
	private String btnAction;
	private String remark;
	private String menuItemNameCn;
	private String menuItemCode;
	private String menuItemUrl;
	private String menuItemAction;
	//private String locPlanUuid;

/*	@Column(name = "LOC_PLAN_UUID")
	public String getLocPlanUuid() {
		return locPlanUuid;
	}

	public void setLocPlanUuid(String locPlanUuid) {
		this.locPlanUuid = locPlanUuid;
		addValidField("locPlanUuid");
	}*/
	
	@Column(name = "MENU_ITEM_NAME")
	public String getMenuItemName() {
		return menuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
		addValidField("menuItemName");
	}

	@Column(name = "OFFICE_CODE")
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
		addValidField("officeCode");
	}

	@Column(name = "SYS_VIEW_BUTTON_UUID")
	public String getSysViewButtonUuid() {
		return sysViewButtonUuid;
	}

	public void setSysViewButtonUuid(String sysViewButtonUuid) {
		this.sysViewButtonUuid = sysViewButtonUuid;
		addValidField("sysViewButtonUuid");
	}

	@Column(name = "SYS_MENU_ITEM_UUID")
	public String getSysMenuItemUuid() {
		return sysMenuItemUuid;
	}

	public void setSysMenuItemUuid(String sysMenuItemUuid) {
		this.sysMenuItemUuid = sysMenuItemUuid;
		addValidField("sysMenuItemUuid");
	}

	@Column(name = "BTN_CODE")
	public String getBtnCode() {
		return btnCode;
	}

	public void setBtnCode(String btnCode) {
		this.btnCode = btnCode;
		addValidField("btnCode");
	}

	@Column(name = "BTN_NAME")
	public String getBtnName() {
		return btnName;
	}

	public void setBtnName(String btnName) {
		this.btnName = btnName;
		addValidField("btnName");
	}

	@Column(name = "BTN_NAME_EN")
	public String getBtnNameEn() {
		return btnNameEn;
	}

	public void setBtnNameEn(String btnNameEn) {
		this.btnNameEn = btnNameEn;
		addValidField("btnNameEn");
	}

	@Column(name = "BTN_URL")
	public String getBtnUrl() {
		return btnUrl;
	}

	public void setBtnUrl(String btnUrl) {
		this.btnUrl = btnUrl;
		addValidField("btnUrl");
	}

	@Column(name = "BTN_ACTION")
	public String getBtnAction() {
		return btnAction;
	}

	public void setBtnAction(String btnAction) {
		this.btnAction = btnAction;
		addValidField("btnAction");
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		addValidField("remark");
	}

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
