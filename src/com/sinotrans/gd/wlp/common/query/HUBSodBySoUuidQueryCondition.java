package com.sinotrans.gd.wlp.common.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class HUBSodBySoUuidQueryCondition extends BaseQueryCondition {

	private String submitOrderUuid;

	public String getSubmitOrderUuid() {
		return submitOrderUuid;
	}

	public void setSubmitOrderUuid(String submitOrderUuid) {
		this.submitOrderUuid = submitOrderUuid;
	}

}
