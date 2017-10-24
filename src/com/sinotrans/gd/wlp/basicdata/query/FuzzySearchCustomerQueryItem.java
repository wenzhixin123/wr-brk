package com.sinotrans.gd.wlp.basicdata.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class FuzzySearchCustomerQueryItem extends BaseQueryItem {

	private String basCustomerUuid;
	private String customerCode;
	private String customerName;
	private String customerNameEn;
	private String status;


	@Column(name = "BAS_CUSTOMER_UUID")
	public String getBasCustomerUuid() {
		return basCustomerUuid;
	}

	public void setBasCustomerUuid(String basCustomerUuid) {
		this.basCustomerUuid = basCustomerUuid;
		addValidField("basCustomerUuid");
	}

	@Column(name = "CUSTOMER_CODE")
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
		addValidField("customerCode");
	}

	@Column(name = "CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
		addValidField("customerName");
	}

	@Column(name = "CUSTOMER_NAME_EN")
	public String getCustomerNameEn() {
		return customerNameEn;
	}

	public void setCustomerNameEn(String customerNameEn) {
		this.customerNameEn = customerNameEn;
		addValidField("customerNameEn");
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		addValidField("status");
	}

}
