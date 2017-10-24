package com.sinotrans.gd.wlp.statistics.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class CommonGetItemMasterQueryCondition extends BaseQueryCondition {
	
	private String loAgentConsigneeCode;
	private String lodItemCode;
	
	public CommonGetItemMasterQueryCondition() {
		super();
	}
	public CommonGetItemMasterQueryCondition(String loAgentConsigneeCode,
			String lodItemCode) {
		super();
		this.loAgentConsigneeCode = loAgentConsigneeCode;
		this.lodItemCode = lodItemCode;
	}
	public String getLoAgentConsigneeCode() {
		return loAgentConsigneeCode;
	}
	public void setLoAgentConsigneeCode(String loAgentConsigneeCode) {
		this.loAgentConsigneeCode = loAgentConsigneeCode;
	}
	public String getLodItemCode() {
		return lodItemCode;
	}
	public void setLodItemCode(String lodItemCode) {
		this.lodItemCode = lodItemCode;
	}
	

}
