package com.sinotrans.gd.wlp.basicdata.query;

import java.util.Date;

import com.sinotrans.framework.core.query.BaseQueryCondition;

public class DcsOperateLogNoPrintQueryCondition extends BaseQueryCondition {

	//日志ID
	private String logUuid;
	//目标记录PK
	private String dataSourceUuid;
	//目标表名
	private String dateSourceEntity;
	//业务单号
	private String businessRefNo;
	
	//修改后值
	private String modifiedValue;

	//操作类型
	private String opeationType;
	//
	private String creator;
	//createTime
	private Date createTime;
	public String getLogUuid() {
		return logUuid;
	}
	public void setLogUuid(String logUuid) {
		this.logUuid = logUuid;
	}
	public String getDataSourceUuid() {
		return dataSourceUuid;
	}
	public void setDataSourceUuid(String dataSourceUuid) {
		this.dataSourceUuid = dataSourceUuid;
	}
	public String getDateSourceEntity() {
		return dateSourceEntity;
	}
	public void setDateSourceEntity(String dateSourceEntity) {
		this.dateSourceEntity = dateSourceEntity;
	}
	public String getBusinessRefNo() {
		return businessRefNo;
	}
	public void setBusinessRefNo(String businessRefNo) {
		this.businessRefNo = businessRefNo;
	}
	public String getModifiedValue() {
		return modifiedValue;
	}
	public void setModifiedValue(String modifiedValue) {
		this.modifiedValue = modifiedValue;
	}
	public String getOpeationType() {
		return opeationType;
	}
	public void setOpeationType(String opeationType) {
		this.opeationType = opeationType;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	

}
