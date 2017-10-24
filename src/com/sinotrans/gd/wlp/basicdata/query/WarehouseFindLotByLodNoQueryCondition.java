package com.sinotrans.gd.wlp.basicdata.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class WarehouseFindLotByLodNoQueryCondition extends BaseQueryCondition {

	private String transactionType;
	private String basWarehouseUuid;
	private String officeCode;

	public String getBasWarehouseUuid() {
		return basWarehouseUuid;
	}

	public void setBasWarehouseUuid(String basWarehouseUuid) {
		this.basWarehouseUuid = basWarehouseUuid;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public WarehouseFindLotByLodNoQueryCondition(String transactionType,
			String basWarehouseUuid, String officeCode) {
		super();
		this.transactionType = transactionType;
		this.basWarehouseUuid = basWarehouseUuid;
		this.officeCode = officeCode;
	}
}
