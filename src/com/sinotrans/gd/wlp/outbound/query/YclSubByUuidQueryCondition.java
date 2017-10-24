package com.sinotrans.gd.wlp.outbound.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

public class YclSubByUuidQueryCondition extends BaseQueryCondition {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7314130704749001995L;
	private String submitOrderUuid;

	public String getSubmitOrderUuid() {
		return submitOrderUuid;
	}

	public void setSubmitOrderUuid(String submitOrderUuid) {
		this.submitOrderUuid = submitOrderUuid;
	}

}
