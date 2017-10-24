package com.sinotrans.gd.wlp.system.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

public class PrintReportTemplateQueryCondition extends BaseQueryCondition {

	private String templateName;
	private String fileName;
	private String templateType;

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

}
