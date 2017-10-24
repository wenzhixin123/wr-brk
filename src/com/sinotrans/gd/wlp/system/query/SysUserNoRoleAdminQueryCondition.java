package com.sinotrans.gd.wlp.system.query;

import java.util.Date;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class SysUserNoRoleAdminQueryCondition extends BaseQueryCondition {

	private String[] officeCode;
	private String userName;
	private String userType;
	private Date startDate;
	private Date endDate;

	public String[] getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String[] officeCode) {
		this.officeCode = officeCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
