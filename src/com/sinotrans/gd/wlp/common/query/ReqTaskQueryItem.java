package com.sinotrans.gd.wlp.common.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;
import com.sinotrans.gd.wlp.common.model.ReqTaskModel;

@Entity
public class ReqTaskQueryItem extends BaseQueryItem {

	private String reqTaskId;
	private String taskCode;
	private String taskStatus;
	private Date reqTime;
	private String taskTyp;
	private String podCode;
	private String podDir;
	private String priority;
	private String robotCode;
	private String clientCode;
	private String tokenCode;
	private String currentCallCode;
	//userCallCodePath
	private String userCallCodePath;
	private String userCallCode;

	@Column(name = "user_call_code")
	public String getUserCallCode() {
		return userCallCode;
	}

	public void setUserCallCode(String userCallCode) {
		this.userCallCode = userCallCode;
		addValidField(ReqTaskModel.FieldNames.userCallCode);
	}

	/**
	 * Get 请求时间
	 */
	@Column(name = "user_call_code_path")
	public String getUserCallCodePath() {
		return userCallCodePath;
	}

	public void setUserCallCodePath(String userCallCodePath) {
		this.userCallCodePath = userCallCodePath;
		addValidField(ReqTaskModel.FieldNames.userCallCodePath);
	}

	@Column(name = "current_call_code")
	public String getCurrentCallCode() {
		return currentCallCode;
	}

	public void setCurrentCallCode(String currentCallCode) {
		this.currentCallCode = currentCallCode;
		addValidField("currentCallCode");
	}

	@Column(name = "client_code")
	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
		addValidField("clientCode");
	}
	@Column(name = "token_code")
	public String getTokenCode() {
		return tokenCode;
	}

	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
		addValidField("tokenCode");
	}

	@Column(name = "req_task_id")
	public String getReqTaskId() {
		return reqTaskId;
	}

	public void setReqTaskId(String reqTaskId) {
		this.reqTaskId = reqTaskId;
		addValidField("reqTaskId");
	}

	@Column(name = "task_code")
	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
		addValidField("taskCode");
	}

	@Column(name = "task_status")
	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
		addValidField("taskStatus");
	}

	@Column(name = "req_time")
	public Date getReqTime() {
		return reqTime;
	}

	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
		addValidField("reqTime");
	}

	@Column(name = "task_typ")
	public String getTaskTyp() {
		return taskTyp;
	}

	public void setTaskTyp(String taskTyp) {
		this.taskTyp = taskTyp;
		addValidField("taskTyp");
	}

	@Column(name = "pod_code")
	public String getPodCode() {
		return podCode;
	}

	public void setPodCode(String podCode) {
		this.podCode = podCode;
		addValidField("podCode");
	}

	@Column(name = "pod_dir")
	public String getPodDir() {
		return podDir;
	}

	public void setPodDir(String podDir) {
		this.podDir = podDir;
		addValidField("podDir");
	}

	@Column(name = "priority")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
		addValidField("priority");
	}

	@Column(name = "robot_code")
	public String getRobotCode() {
		return robotCode;
	}

	public void setRobotCode(String robotCode) {
		this.robotCode = robotCode;
		addValidField("robotCode");
	}

}
