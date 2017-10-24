package com.sinotrans.gd.wlp.email.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@Entity
public class PreManifestCntrQueryItem extends BaseQueryItem {

	private String containerno;
	private String vesselname;
	private String shippingorderno;
	private String vesselcode;
	private String voyageno;
	private String customsLicenseNo;

	@Column(name = "CONTAINERNO")
	public String getContainerno() {
		return containerno;
	}

	public void setContainerno(String containerno) {
		this.containerno = containerno;
		addValidField("containerno");
	}

	@Column(name = "VESSELNAME")
	public String getVesselname() {
		return vesselname;
	}

	public void setVesselname(String vesselname) {
		this.vesselname = vesselname;
		addValidField("vesselname");
	}

	@Column(name = "SHIPPINGORDERNO")
	public String getShippingorderno() {
		return shippingorderno;
	}

	public void setShippingorderno(String shippingorderno) {
		this.shippingorderno = shippingorderno;
		addValidField("shippingorderno");
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

	@Column(name = "CUSTOMSLICENSENO")
	public String getCustomsLicenseNo() {
		return customsLicenseNo;
	}

	public void setCustomsLicenseNo(String customsLicenseNo) {
		this.customsLicenseNo = customsLicenseNo;
		addValidField("customsLicenseNo");
	}

}
