package com.sinotrans.gd.wlp.system.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class GetMenuGroupByUserCodeQueryItem extends BaseQueryItem {

	private String sysMenuGroupUuid;
	private String sysModuleUuid;
	private String preSysMenuGroupUuid;
	private Long menuGrpSeq;
	private String menuGrpCode;
	private String menuGrpName;
	private String menuGrpNameEn;
	private String remark;
	private String status;
	private String controlWord;
	private String officeCode;
	private Long recVer;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date modifyTime;

	@Column(name = "SYS_MENU_GROUP_UUID")
	public String getSysMenuGroupUuid() {
		return sysMenuGroupUuid;
	}

	public void setSysMenuGroupUuid(String sysMenuGroupUuid) {
		this.sysMenuGroupUuid = sysMenuGroupUuid;
		addValidField("sysMenuGroupUuid");
	}

	@Column(name = "SYS_MODULE_UUID")
	public String getSysModuleUuid() {
		return sysModuleUuid;
	}

	public void setSysModuleUuid(String sysModuleUuid) {
		this.sysModuleUuid = sysModuleUuid;
		addValidField("sysModuleUuid");
	}

	@Column(name = "PRE_SYS_MENU_GROUP_UUID")
	public String getPreSysMenuGroupUuid() {
		return preSysMenuGroupUuid;
	}

	public void setPreSysMenuGroupUuid(String preSysMenuGroupUuid) {
		this.preSysMenuGroupUuid = preSysMenuGroupUuid;
		addValidField("preSysMenuGroupUuid");
	}

	@Column(name = "MENU_GRP_SEQ")
	public Long getMenuGrpSeq() {
		return menuGrpSeq;
	}

	public void setMenuGrpSeq(Long menuGrpSeq) {
		this.menuGrpSeq = menuGrpSeq;
		addValidField("menuGrpSeq");
	}

	@Column(name = "MENU_GRP_CODE")
	public String getMenuGrpCode() {
		return menuGrpCode;
	}

	public void setMenuGrpCode(String menuGrpCode) {
		this.menuGrpCode = menuGrpCode;
		addValidField("menuGrpCode");
	}

	@Column(name = "MENU_GRP_NAME")
	public String getMenuGrpName() {
		return menuGrpName;
	}

	public void setMenuGrpName(String menuGrpName) {
		this.menuGrpName = menuGrpName;
		addValidField("menuGrpName");
	}

	@Column(name = "MENU_GRP_NAME_EN")
	public String getMenuGrpNameEn() {
		return menuGrpNameEn;
	}

	public void setMenuGrpNameEn(String menuGrpNameEn) {
		this.menuGrpNameEn = menuGrpNameEn;
		addValidField("menuGrpNameEn");
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
