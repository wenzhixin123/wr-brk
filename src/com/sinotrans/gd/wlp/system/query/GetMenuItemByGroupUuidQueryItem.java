package com.sinotrans.gd.wlp.system.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class GetMenuItemByGroupUuidQueryItem extends BaseQueryItem {

	private String sysMenuItemUuid;
	private String sysMenuGroupUuid;
	private Long menuItemSeq;
	private String menuItemCode;
	private String menuItemName;
	private String menuItemNameCn;
	private String menuItemUrl;
	private String menuItemAction;
	private String className;
	private Integer isDialog;
	private String remark;
	private String status;
	private String controlWord;
	private String officeCode;
	private Long recVer;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date modifyTime;

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

	@Column(name = "MENU_ITEM_SEQ")
	public Long getMenuItemSeq() {
		return menuItemSeq;
	}

	public void setMenuItemSeq(Long menuItemSeq) {
		this.menuItemSeq = menuItemSeq;
		addValidField("menuItemSeq");
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

	@Column(name = "MENU_ITEM_ACTION")
	public String getMenuItemAction() {
		return menuItemAction;
	}

	public void setMenuItemAction(String menuItemAction) {
		this.menuItemAction = menuItemAction;
		addValidField("menuItemAction");
	}

	@Column(name = "CLASS_NAME")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
		addValidField("className");
	}

	@Column(name = "IS_DIALOG")
	public Integer getIsDialog() {
		return isDialog;
	}

	public void setIsDialog(Integer isDialog) {
		this.isDialog = isDialog;
		addValidField("isDialog");
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		addValidField("remark");
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		addValidField("status");
	}

	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField("controlWord");
	}

	@Column(name = "OFFICE_CODE")
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
		addValidField("officeCode");
	}

	@Column(name = "REC_VER")
	public Long getRecVer() {
		return recVer;
	}

	public void setRecVer(Long recVer) {
		this.recVer = recVer;
		addValidField("recVer");
	}

	@Column(name = "CREATOR")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
		addValidField("creator");
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		addValidField("createTime");
	}

	@Column(name = "MODIFIER")
	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
		addValidField("modifier");
	}

	@Column(name = "MODIFY_TIME")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
		addValidField("modifyTime");
	}

}
