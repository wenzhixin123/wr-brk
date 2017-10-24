package com.sinotrans.gd.wlp.system.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class SysRoleQueryItem extends BaseQueryItem {

	private String sysRoleUuid;
	private String roleCode;
	private String roleName;
	private String roleNameEn;
	private Integer canQueryallFlag;
	private Integer isVirtualFlag;
	private Date activeDate;
	private Date expiredDate;
	private Integer deletedFlag;
	private String roleType;
	private String remark;
	private String status;
	private String controlWord;
	private String officeCode;
	private Long recVer;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date modifyTime;

	@Column(name = "SYS_ROLE_UUID")
	public String getSysRoleUuid() {
		return sysRoleUuid;
	}

	public void setSysRoleUuid(String sysRoleUuid) {
		this.sysRoleUuid = sysRoleUuid;
		addValidField("sysRoleUuid");
	}

	@Column(name = "ROLE_CODE")
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
		addValidField("roleCode");
	}

	@Column(name = "ROLE_NAME")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
		addValidField("roleName");
	}

	@Column(name = "ROLE_NAME_EN")
	public String getRoleNameEn() {
		return roleNameEn;
	}

	public void setRoleNameEn(String roleNameEn) {
		this.roleNameEn = roleNameEn;
		addValidField("roleNameEn");
	}

	@Column(name = "CAN_QUERYALL_FLAG")
	public Integer getCanQueryallFlag() {
		return canQueryallFlag;
	}

	public void setCanQueryallFlag(Integer canQueryallFlag) {
		this.canQueryallFlag = canQueryallFlag;
		addValidField("canQueryallFlag");
	}

	@Column(name = "IS_VIRTUAL_FLAG")
	public Integer getIsVirtualFlag() {
		return isVirtualFlag;
	}

	public void setIsVirtualFlag(Integer isVirtualFlag) {
		this.isVirtualFlag = isVirtualFlag;
		addValidField("isVirtualFlag");
	}

	@Column(name = "ACTIVE_DATE")
	public Date getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
		addValidField("activeDate");
	}

	@Column(name = "EXPIRED_DATE")
	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
		addValidField("expiredDate");
	}

	@Column(name = "DELETED_FLAG")
	public Integer getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Integer deletedFlag) {
		this.deletedFlag = deletedFlag;
		addValidField("deletedFlag");
	}

	@Column(name = "ROLE_TYPE")
	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
		addValidField("roleType");
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
