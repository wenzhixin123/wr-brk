package com.sinotrans.gd.wlp.system.entity;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

@SuppressWarnings("serial")
public class PrintTemplateForm extends ActionForm {

	private FormFile template;
	private String fileName;
	private String templateName;
	private String templateType;
	private String fileVersion;
	private String templateNameEn;
	private String remark;
	private String reportTemplateUuid;
	private String templateCode;
	private String status;
	private String rowState;
	private String customerCode;
	private String templateCategory;
	private String printExtraCondition;
	private String controlWord;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getFileVersion() {
		return fileVersion;
	}

	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
	}

	public String getTemplateNameEn() {
		return templateNameEn;
	}

	public void setTemplateNameEn(String templateNameEn) {
		this.templateNameEn = templateNameEn;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReportTemplateUuid() {
		return reportTemplateUuid;
	}

	public void setReportTemplateUuid(String reportTemplateUuid) {
		this.reportTemplateUuid = reportTemplateUuid;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRowState() {
		return rowState;
	}

	public void setRowState(String rowState) {
		this.rowState = rowState;
	}

	public FormFile getTemplate() {
		return template;
	}

	public void setTemplate(FormFile template) {
		this.template = template;
	}

	public String getTemplateCategory() {
		return templateCategory;
	}

	public void setTemplateCategory(String templateCategory) {
		this.templateCategory = templateCategory;
	}

	public String getPrintExtraCondition() {
		return printExtraCondition;
	}

	public void setPrintExtraCondition(String printExtraCondition) {
		this.printExtraCondition = printExtraCondition;
	}

	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
	}

}
