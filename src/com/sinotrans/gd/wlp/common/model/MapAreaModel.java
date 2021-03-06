package com.sinotrans.gd.wlp.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sinotrans.framework.core.model.BaseModelClass;

/**
 * Model class for MapArea
 */
@Entity
@Table(name = "map_area")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class MapAreaModel extends BaseModelClass {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "MapArea";

	public static final class FieldNames {
		/**
		 * mapAreaId
		 */
		public static final String mapAreaId = "mapAreaId";
		/**
		 * mapDataCode
		 */
		public static final String mapDataCode = "mapDataCode";
		/**
		 * userCallCode
		 */
		public static final String userCallCode = "userCallCode";
		/**
		 * dataTyp
		 */
		public static final String dataTyp = "dataTyp";
		/**
		 * cooX
		 */
		public static final String cooX = "cooX";
		/**
		 * cooY
		 */
		public static final String cooY = "cooY";
		/**
		 * direction
		 */
		public static final String direction = "direction";
		/**
		 * areaCode
		 */
		public static final String areaCode = "areaCode";
	}

	//mapAreaId
	private String mapAreaId;
	//mapDataCode
	private String mapDataCode;
	//userCallCode
	private String userCallCode;
	//dataTyp
	private String dataTyp;
	//cooX
	private Integer cooX;
	//cooY
	private Integer cooY;
	//direction
	private String direction;
	//areaCode
	private String areaCode;

	/**
	 * Get mapAreaId
	 */
	@Column(name = "map_area_id")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getMapAreaId() {
		return mapAreaId;
	}

	/**
	 * Set mapAreaId
	 */
	public void setMapAreaId(String mapAreaId) {
		this.mapAreaId = mapAreaId;
		addValidField(FieldNames.mapAreaId);
	}

	/**
	 * Get mapDataCode
	 */
	@Column(name = "map_data_code")
	public String getMapDataCode() {
		return mapDataCode;
	}

	/**
	 * Set mapDataCode
	 */
	public void setMapDataCode(String mapDataCode) {
		this.mapDataCode = mapDataCode;
		addValidField(FieldNames.mapDataCode);
	}

	/**
	 * Get userCallCode
	 */
	@Column(name = "user_call_code")
	public String getUserCallCode() {
		return userCallCode;
	}

	/**
	 * Set userCallCode
	 */
	public void setUserCallCode(String userCallCode) {
		this.userCallCode = userCallCode;
		addValidField(FieldNames.userCallCode);
	}

	/**
	 * Get dataTyp
	 */
	@Column(name = "data_typ")
	public String getDataTyp() {
		return dataTyp;
	}

	/**
	 * Set dataTyp
	 */
	public void setDataTyp(String dataTyp) {
		this.dataTyp = dataTyp;
		addValidField(FieldNames.dataTyp);
	}

	/**
	 * Get cooX
	 */
	@Column(name = "cooX")
	public Integer getCooX() {
		return cooX;
	}

	/**
	 * Set cooX
	 */
	public void setCooX(Integer cooX) {
		this.cooX = cooX;
		addValidField(FieldNames.cooX);
	}

	/**
	 * Get cooY
	 */
	@Column(name = "cooY")
	public Integer getCooY() {
		return cooY;
	}

	/**
	 * Set cooY
	 */
	public void setCooY(Integer cooY) {
		this.cooY = cooY;
		addValidField(FieldNames.cooY);
	}

	/**
	 * Get direction
	 */
	@Column(name = "direction")
	public String getDirection() {
		return direction;
	}

	/**
	 * Set direction
	 */
	public void setDirection(String direction) {
		this.direction = direction;
		addValidField(FieldNames.direction);
	}

	/**
	 * Get areaCode
	 */
	@Column(name = "area_code")
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * Set areaCode
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
		addValidField(FieldNames.areaCode);
	}

}
