package com.sinotrans.gd.wlp.statistics.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class CommonGetItemMasterQueryItem extends BaseQueryItem {

	private Double unitQty;
	private Double volume;
	private Double grossWeight;
	private Double netWeight;

	@Column(name = "UNIT_QTY")
	public Double getUnitQty() {
		return unitQty;
	}

	public void setUnitQty(Double unitQty) {
		this.unitQty = unitQty;
		addValidField("unitQty");
	}

	@Column(name = "VOLUME")
	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
		addValidField("volume");
	}

	@Column(name = "GROSS_WEIGHT")
	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
		addValidField("grossWeight");
	}

	@Column(name = "NET_WEIGHT")
	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
		addValidField("netWeight");
	}

}
