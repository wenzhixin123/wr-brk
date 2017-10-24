package com.sinotrans.gd.wlp.email.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@Entity
public class NewManifestBillQueryItem extends BaseQueryItem {

	private String billLadingNo;
	private String portareacode;
	private String vesselcode;
	private String voyageno;
	private Date sendtime;
	private String containerNo;
	private String shippingOrderNo;
	private String vesselName;

	@Column(name = "BILLLADINGNO")
	public String getBillLadingNo() {
		return billLadingNo;
	}

	public void setBillLadingNo(String billLadingNo) {
		this.billLadingNo = billLadingNo;
		addValidField("billLadingNo");
	}

	@Column(name = "PORTAREACODE")
	public String getPortareacode() {
		return portareacode;
	}

	public void setPortareacode(String portareacode) {
		this.portareacode = portareacode;
		addValidField("portareacode");
	}

	@Column(name = "VESSELCODE")
	public String getVesselcode() {
		return vesselcode;
	}

	public void setVesselcode(String vesselcode) {
		this.vesselcode = vesselcode;
		addValidField("vesselcode");
	}

	@Column(name = "VOYAGENO")
	public String getVoyageno() {
		return voyageno;
	}

	public void setVoyageno(String voyageno) {
		this.voyageno = voyageno;
		addValidField("voyageno");
	}

	@Column(name = "SENDTIME")
	public Date getSendtime() {
		return sendtime;
	}

	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
		addValidField("sendtime");
	}

	@Column(name = "CONTAINERNO")
	public String getContainerNo() {
		return containerNo;
	}

	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
		addValidField("containerNo");
	}

	@Column(name = "SHIPPINGORDERNO")
	public String getShippingOrderNo() {
		return shippingOrderNo;
	}

	public void setShippingOrderNo(String shippingOrderNo) {
		this.shippingOrderNo = shippingOrderNo;
		addValidField("shippingOrderNo");
	}

	@Column(name = "VESSELNAME")
	public String getVesselName() {
		return vesselName;
	}

	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
		addValidField("vesselName");
	}

}
