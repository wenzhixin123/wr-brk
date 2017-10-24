package com.sinotrans.gd.wlp.basicdata.query;

import java.util.Date;

import com.sinotrans.framework.core.query.BaseQueryCondition;

public class ExOrderRightQueryCondition extends BaseQueryCondition {

	private String agentConsigneeCode;
	private String pickupDepot;
	private String cntrAdminCode;
	private String deliveryType;
	private String projectCode;
	private Date createTime;
	private String transactionType;

	public String getAgentConsigneeCode() {
		return agentConsigneeCode;
	}

	public void setAgentConsigneeCode(String agentConsigneeCode) {
		this.agentConsigneeCode = agentConsigneeCode;
	}

	public String getPickupDepot() {
		return pickupDepot;
	}

	public void setPickupDepot(String pickupDepot) {
		this.pickupDepot = pickupDepot;
	}

	public String getCntrAdminCode() {
		return cntrAdminCode;
	}

	public void setCntrAdminCode(String cntrAdminCode) {
		this.cntrAdminCode = cntrAdminCode;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

}
