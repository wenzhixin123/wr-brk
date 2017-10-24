package com.sinotrans.gd.wlp.common.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class ChangeOfficeCodeQueryCondition extends BaseQueryCondition {

	private String socConfig;

	public String getSocConfig() {
		return socConfig;
	}

	public void setSocConfig(String socConfig) {
		this.socConfig = socConfig;
	}

}
