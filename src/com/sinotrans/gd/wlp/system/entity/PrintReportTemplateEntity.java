/**
 * 
 */
package com.sinotrans.gd.wlp.system.entity;

import java.io.UnsupportedEncodingException;

import com.sinotrans.gd.wlp.system.model.PrintReportTemplateModel;

/**
 * @author Sky
 * 
 */
@SuppressWarnings("serial")
public class PrintReportTemplateEntity extends PrintReportTemplateModel {

	private String templateString = "";

	private String templatecontentString = "";
	
	private String extraCondition1;

	public String getTemplateString() throws UnsupportedEncodingException {
		byte[] arrs = getTemplateContent();
		if (arrs != null && arrs.length > 0) {
			templateString = new String(arrs, "utf8");
		}
		return templateString;
	}

	public void setTemplateString(String templateString) {
		this.templateString = templateString;
	}

	public String getTemplatecontentString() {
		byte[] arrs = getTemplateContent();
		if (arrs != null && arrs.length > 0) {
			templatecontentString = new sun.misc.BASE64Encoder().encode(arrs);
		}
		return templatecontentString;
	}

	public void setTemplatecontentString(String templatecontentString) {
		this.templatecontentString = templatecontentString;
	}

	public String getExtraCondition1() {
		return extraCondition1;
	}

	public void setExtraCondition1(String extraCondition1) {
		this.extraCondition1 = extraCondition1;
	}

}
