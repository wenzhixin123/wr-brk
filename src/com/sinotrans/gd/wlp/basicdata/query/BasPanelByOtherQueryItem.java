package com.sinotrans.gd.wlp.basicdata.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class BasPanelByOtherQueryItem extends BaseQueryItem {

	private String basPanelUuid;
	private String basPanelTypeUuid;
	private String panelPackageNo;
	private String panelPackageDesc;
	private String customerCode;
	private String customerName;
	private String remark;
	private String officeCode;
	private Long recVer;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date modifyTime;
	private String status;
	private String controlWord;

	@Column(name = "BAS_PANEL_UUID")
	public String getBasPanelUuid() {
		return basPanelUuid;
	}

	public void setBasPanelUuid(String basPanelUuid) {
		this.basPanelUuid = basPanelUuid;
		addValidField("basPanelUuid");
	}

	@Column(name = "BAS_PANEL_TYPE_UUID")
	public String getBasPanelTypeUuid() {
		return basPanelTypeUuid;
	}

	public void setBasPanelTypeUuid(String basPanelTypeUuid) {
		this.basPanelTypeUuid = basPanelTypeUuid;
		addValidField("basPanelTypeUuid");
	}

	@Column(name = "PANEL_PACKAGE_NO")
	public String getPanelPackageNo() {
		return panelPackageNo;
	}

	public void setPanelPackageNo(String panelPackageNo) {
		this.panelPackageNo = panelPackageNo;
		addValidField("panelPackageNo");
	}

	@Column(name = "PANEL_PACKAGE_DESC")
	public String getPanelPackageDesc() {
		return panelPackageDesc;
	}

	public void setPanelPackageDesc(String panelPackageDesc) {
		this.panelPackageDesc = panelPackageDesc;
		addValidField("panelPackageDesc");
	}

	@Column(name = "CUSTOMER_CODE")
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
		addValidField("customerCode");
	}

	@Column(name = "CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
		addValidField("customerName");
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		addValidField("remark");
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

}
