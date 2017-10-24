package com.sinotrans.gd.wlp.system.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class GrantedUrlsQueryCondition extends BaseQueryCondition {

	private String userUuid;

	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}

}
