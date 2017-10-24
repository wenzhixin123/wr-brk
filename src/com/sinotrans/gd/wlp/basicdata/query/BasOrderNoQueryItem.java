package com.sinotrans.gd.wlp.basicdata.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class BasOrderNoQueryItem extends BaseQueryItem {

	private String basOrderNoUuid;
	private String orderNo;
	private String orderType;
	private String orderDesc;
	private String remark;
	private String status;
	private String officeCode;

	@Column(name = "BAS_ORDER_NO_UUID")
	public String getBasOrderNoUuid() {
		return basOrderNoUuid;
	}

	public void setBasOrderNoUuid(String basOrderNoUuid) {
		this.basOrderNoUuid = basOrderNoUuid;
		addValidField("basOrderNoUuid");
	}

	@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
		addValidField("orderNo");
	}

	@Column(name = "ORDER_TYPE")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
		addValidField("orderType");
	}

	@Column(name = "ORDER_DESC")
	public String getOrderDesc() {
		return orderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
		addValidField("orderDesc");
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		addValidField("remark");
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		addValidField("status");
	}

	@Column(name = "OFFICE_CODE")
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
		addValidField("officeCode");
	}

}
