package com.sinotrans.gd.wlp.email.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@Entity
public class ShippingDepartContQueryItem extends BaseQueryItem {

	private String containerno;
	private String shippingorderno;
	private String vesselname;
	private String voyageno;
	private Date datedepart;

	@Column(name = "CONTAINERNO")
	public String getContainerno() {
		return containerno;
	}

	public void setContainerno(String containerno) {
		this.containerno = containerno;
		addValidField("containerno");
	}

	@Column(name = "SHIPPINGORDERNO")
	public String getShippingorderno() {
		return shippingorderno;
	}

	public void setShippingorderno(String shippingorderno) {
		this.shippingorderno = shippingorderno;
		addValidField("shippingorderno");
	}

	@Column(name = "VESSELNAME")
	public String getVesselname() {
		return vesselname;
	}

	public void setVesselname(String vesselname) {
		this.vesselname = vesselname;
		addValidField("vesselname");
	}

	@Column(name = "VOYAGENO")
	public String getVoyageno() {
		return voyageno;
	}

	public void setVoyageno(String voyageno) {
		this.voyageno = voyageno;
		addValidField("voyageno");
	}

	@Column(name = "DATEDEPART")
	public Date getDatedepart() {
		return datedepart;
	}

	public void setDatedepart(Date datedepart) {
		this.datedepart = datedepart;
		addValidField("datedepart");
	}

}
