package com.sinotrans.gd.wlp.scanner.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class KgCpReceQueryItem extends BaseQueryItem {

	private String batchNo;
	private Double failQty;
	private Double passQty;
	private Double confirmedQty;
	
	
	@Column(name = "CONFIRMED_QTY")
	public Double getConfirmedQty() {
		return confirmedQty;
	}

	public void setConfirmedQty(Double confirmedQty) {
		this.confirmedQty = confirmedQty;
		addValidField("confirmedQty");
	}

	@Column(name = "PASS_QTY")
	public Double getPassQty() {
		return passQty;
	}

	public void setPassQty(Double passQty) {
		this.passQty = passQty;
		addValidField("passQty");
	}

	@Column(name = "BATCH_NO")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
		addValidField("batchNo");
	}

	@Column(name = "FAIL_QTY")
	public Double getFailQty() {
		return failQty;
	}

	public void setFailQty(Double failQty) {
		this.failQty = failQty;
		addValidField("failQty");
	}


}
