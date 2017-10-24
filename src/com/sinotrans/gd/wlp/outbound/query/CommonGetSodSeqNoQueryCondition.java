package com.sinotrans.gd.wlp.outbound.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class CommonGetSodSeqNoQueryCondition extends BaseQueryCondition {

	private String submitOrderUuid;

	public CommonGetSodSeqNoQueryCondition() {
		super();
	}

	public CommonGetSodSeqNoQueryCondition(String submitOrderUuid) {
		super();
		this.submitOrderUuid = submitOrderUuid;
	}

	public String getSubmitOrderUuid() {
		return submitOrderUuid;
	}

	public void setSubmitOrderUuid(String submitOrderUuid) {
		this.submitOrderUuid = submitOrderUuid;
	}

}
