package com.sinotrans.gd.wlp.system.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@Entity
public class PrintReportTemplateQueryItem extends BaseQueryItem {

	private String reportTemplateUuid;
	private String templateCode;
	private String templateType;
	private String templateCategory;
	private String templateName;
	private String templateNameEn;
	private String fileName;
	private String fileVersion;
	private String remark;
	private String status;
	private String creator;
	private Date createTime;
	private String modifier;

	@Column(name = "REPORT_TEMPLATE_UUID")
	public String getReportTemplateUuid() {
		return reportTemplateUuid;
	}

	public void setReportTemplateUuid(String reportTemplateUuid) {
		this.reportTemplateUuid = reportTemplateUuid;
		addValidField("reportTemplateUuid");
	}

	@Column(name = "TEMPLATE_CODE")
	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
		addValidField("templateCode");
	}

	@Column(name = "TEMPLATE_TYPE")
	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
		addValidField("templateType");
	}

	@Column(name = "TEMPLATE_CATEGORY")
	public String getTemplateCategory() {
		return templateCategory;
	}

	public void setTemplateCategory(String templateCategory) {
		this.templateCategory = templateCategory;
		addValidField("templateCategory");
	}

	@Column(name = "TEMPLATE_NAME")
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
		addValidField("templateName");
	}

	@Column(name = "TEMPLATE_NAME_EN")
	public String getTemplateNameEn() {
		return templateNameEn;
	}

	public void setTemplateNameEn(String templateNameEn) {
		this.templateNameEn = templateNameEn;
		addValidField("templateNameEn");
	}

	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
		addValidField("fileName");
	}

	@Column(name = "FILE_VERSION")
	public String getFileVersion() {
		return fileVersion;
	}

	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
		addValidField("fileVersion");
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

}
