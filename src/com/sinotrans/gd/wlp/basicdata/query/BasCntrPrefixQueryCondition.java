package com.sinotrans.gd.wlp.basicdata.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

public class BasCntrPrefixQueryCondition extends BaseQueryCondition {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	private String cntrAdminCode;
	private String status;

	public String getCntrAdminCode() {
		return cntrAdminCode;
	}

	public void setCntrAdminCode(String cntrAdminCode) {
		this.cntrAdminCode = cntrAdminCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
