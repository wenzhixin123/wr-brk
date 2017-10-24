package com.sinotrans.gd.wlp.basicdata.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class BasReleaseExcelTemplateQueryItem extends BaseQueryItem {

	private String typeId;
	private String defId;
	private String status;
	private String templateName;
	private String linerCode;
	private String remark;
	private Date createTime;

	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		addValidField("createTime");
	}
	
	@Column(name = "TYPEID")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
		addValidField("typeId");
	}

	@Column(name = "DEFID")
	public String getDefId() {
		return defId;
	}

	public void setDefId(String defId) {
		this.defId = defId;
		addValidField("defId");
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		addValidField("status");
	}

	@Column(name = "TEMPLATENAME")
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
		addValidField("templateName");
	}

	@Column(name = "LINERCODE")
	public String getLinerCode() {
		return linerCode;
	}

	public void setLinerCode(String linerCode) {
		this.linerCode = linerCode;
		addValidField("linerCode");
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		addValidField("remark");
	}
}
