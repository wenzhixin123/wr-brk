package com.sinotrans.gd.wlp.basicdata.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class BasPanelByOtherQueryCondition extends BaseQueryCondition {

	private String officeCode;
	private String printStatus;
	private String panelPackageNo;
	private String panelPackageDesc;
	private String customerName;
	private String status;

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getPrintStatus() {
		return printStatus;
	}

	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}

	public String getPanelPackageNo() {
		return panelPackageNo;
	}

	public void setPanelPackageNo(String panelPackageNo) {
		this.panelPackageNo = panelPackageNo;
	}

	public String getPanelPackageDesc() {
		return panelPackageDesc;
	}

	public void setPanelPackageDesc(String panelPackageDesc) {
		this.panelPackageDesc = panelPackageDesc;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
