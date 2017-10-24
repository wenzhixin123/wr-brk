package com.sinotrans.gd.wlp.common.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class YclRemainDetailQueryCondition extends BaseQueryCondition {

	private String officeCode;
	private String itemCode;
	private String basLocAreaUuid;

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getBasLocAreaUuid() {
		return basLocAreaUuid;
	}

	public void setBasLocAreaUuid(String basLocAreaUuid) {
		this.basLocAreaUuid = basLocAreaUuid;
	}

}
