package com.sinotrans.gd.wlp.common.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@Entity
public class MapAreaQueryItem extends BaseQueryItem {

	private String mapAreaId;
	private String areaCode;
	private Integer cooX;
	private Integer cooY;
	private String dataTyp;
	private String direction;
	private String mapDataCode;
	private String userCallCode;

	@Column(name = "map_area_id")
	public String getMapAreaId() {
		return mapAreaId;
	}

	public void setMapAreaId(String mapAreaId) {
		this.mapAreaId = mapAreaId;
		addValidField("mapAreaId");
	}

	@Column(name = "area_code")
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
		addValidField("areaCode");
	}

	@Column(name = "cooX")
	public Integer getCooX() {
		return cooX;
	}

	public void setCooX(Integer cooX) {
		this.cooX = cooX;
		addValidField("cooX");
	}

	@Column(name = "cooY")
	public Integer getCooY() {
		return cooY;
	}

	public void setCooY(Integer cooY) {
		this.cooY = cooY;
		addValidField("cooY");
	}

	@Column(name = "data_typ")
	public String getDataTyp() {
		return dataTyp;
	}

	public void setDataTyp(String dataTyp) {
		this.dataTyp = dataTyp;
		addValidField("dataTyp");
	}

	@Column(name = "direction")
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
		addValidField("direction");
	}

	@Column(name = "map_data_code")
	public String getMapDataCode() {
		return mapDataCode;
	}

	public void setMapDataCode(String mapDataCode) {
		this.mapDataCode = mapDataCode;
		addValidField("mapDataCode");
	}

	@Column(name = "user_call_code")
	public String getUserCallCode() {
		return userCallCode;
	}

	public void setUserCallCode(String userCallCode) {
		this.userCallCode = userCallCode;
		addValidField("userCallCode");
	}

}
