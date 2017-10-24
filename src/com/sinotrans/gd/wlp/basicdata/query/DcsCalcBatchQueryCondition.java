package com.sinotrans.gd.wlp.basicdata.query;

import java.util.Date;

import com.sinotrans.framework.core.query.BaseQueryCondition;

public class DcsCalcBatchQueryCondition extends BaseQueryCondition {

	private String logisticsOrderNo;
	private Date dateFrom;
	private Date dateTo;
	private String localOrderNo;
	private String creator;
	private String status;
	private String agentConsigneeCode;

	public String getLogisticsOrderNo() {
		return logisticsOrderNo;
	}

	public void setLogisticsOrderNo(String logisticsOrderNo) {
		this.logisticsOrderNo = logisticsOrderNo;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getLocalOrderNo() {
		return localOrderNo;
	}

	public void setLocalOrderNo(String localOrderNo) {
		this.localOrderNo = localOrderNo;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAgentConsigneeCode() {
		return agentConsigneeCode;
	}

	public void setAgentConsigneeCode(String agentConsigneeCode) {
		this.agentConsigneeCode = agentConsigneeCode;
	}

}
