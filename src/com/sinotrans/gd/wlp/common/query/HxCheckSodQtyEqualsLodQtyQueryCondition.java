package com.sinotrans.gd.wlp.common.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class HxCheckSodQtyEqualsLodQtyQueryCondition extends BaseQueryCondition {

	private String[] submitOrderUuid;
	private String officeCode;
	private String submitOrderDetailUuid;
	private String dpParameterName;

	
	public HxCheckSodQtyEqualsLodQtyQueryCondition() {
		super();
	}

	public HxCheckSodQtyEqualsLodQtyQueryCondition(String[] submitOrderUuid, String officeCode) {
		super();
		this.submitOrderUuid = submitOrderUuid;
		this.officeCode = officeCode;
	}
	
	public HxCheckSodQtyEqualsLodQtyQueryCondition(String[] submitOrderUuid,
			String officeCode, String dpParameterName) {
		super();
		this.submitOrderUuid = submitOrderUuid;
		this.officeCode = officeCode;
		this.dpParameterName = dpParameterName;
	}

	public String getDpParameterName() {
		return dpParameterName;
	}

	public void setDpParameterName(String dpParameterName) {
		this.dpParameterName = dpParameterName;
	}

	public String[] getSubmitOrderUuid() {
		return submitOrderUuid;
	}

	public void setSubmitOrderUuid(String[] submitOrderUuid) {
		this.submitOrderUuid = submitOrderUuid;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getSubmitOrderDetailUuid() {
		return submitOrderDetailUuid;
	}

	public void setSubmitOrderDetailUuid(String submitOrderDetailUuid) {
		this.submitOrderDetailUuid = submitOrderDetailUuid;
	}

}
