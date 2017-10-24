package com.sinotrans.gd.wlp.basicdata.query;

import java.util.Date;

import com.sinotrans.framework.core.query.BaseQueryCondition;

public class BasReleaseExcelTemplateQueryCondition extends BaseQueryCondition {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	private String linerCode;//船公司代码
	private Date createTime;//创建时间开始
	private Date endTime;//创建时间结束
	private String templateName;//模板名称
	private String status;//状态
	private String remark;//对应功能
	private String defId;//模板ID
	
	public String getLinerCode() {
		return linerCode;
	}
	public void setLinerCode(String linerCode) {
		this.linerCode = linerCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDefId() {
		return defId;
	}
	public void setDefId(String defId) {
		this.defId = defId;
	}
	
}
