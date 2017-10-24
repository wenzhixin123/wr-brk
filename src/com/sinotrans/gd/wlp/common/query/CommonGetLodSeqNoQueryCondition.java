package com.sinotrans.gd.wlp.common.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class CommonGetLodSeqNoQueryCondition extends BaseQueryCondition {

	private String logisticsOrderUuid;
	private String word;
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public CommonGetLodSeqNoQueryCondition() {
		super();
	}

	public CommonGetLodSeqNoQueryCondition(String logisticsOrderUuid) {
		super();
		this.logisticsOrderUuid = logisticsOrderUuid;
	}

	public String getLogisticsOrderUuid() {
		return logisticsOrderUuid;
	}

	public void setLogisticsOrderUuid(String logisticsOrderUuid) {
		this.logisticsOrderUuid = logisticsOrderUuid;
	}

}
