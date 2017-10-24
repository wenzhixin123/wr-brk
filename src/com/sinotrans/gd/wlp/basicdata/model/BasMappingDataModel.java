package com.sinotrans.gd.wlp.basicdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sinotrans.framework.core.model.BaseModelClass;

/**
 * Model class for BasMappingData
 */
@Entity
@Table(name = "BAS_MAPPING_DATA")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasMappingDataModel extends BaseModelClass {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasMappingData";

	public static final class FieldNames {
		/**
		 * basCode
		 */
		public static final String basCode = "basCode";
		/**
		 * basName
		 */
		public static final String basName = "basName";
		/**
		 * centerCode
		 */
		public static final String centerCode = "centerCode";
		/**
		 * centerName
		 */
		public static final String centerName = "centerName";
		/**
		 * tableName
		 */
		public static final String tableName = "tableName";
	}

	//basCode
	private String basCode;
	//basName
	private String basName;
	//centerCode
	private String centerCode;
	//centerName
	private String centerName;
	//tableName
	private String tableName;

	/**
	 * Get basCode
	 */
	@Column(name = "BAS_CODE")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getBasCode() {
		return basCode;
	}

	/**
	 * Set basCode
	 */
	public void setBasCode(String basCode) {
		this.basCode = basCode;
		addValidField(FieldNames.basCode);
	}

	/**
	 * Get basName
	 */
	@Column(name = "BAS_NAME")
	public String getBasName() {
		return basName;
	}

	/**
	 * Set basName
	 */
	public void setBasName(String basName) {
		this.basName = basName;
		addValidField(FieldNames.basName);
	}

	/**
	 * Get centerCode
	 */
	@Column(name = "CENTER_CODE")
	public String getCenterCode() {
		return centerCode;
	}

	/**
	 * Set centerCode
	 */
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
		addValidField(FieldNames.centerCode);
	}

	/**
	 * Get centerName
	 */
	@Column(name = "CENTER_NAME")
	public String getCenterName() {
		return centerName;
	}

	/**
	 * Set centerName
	 */
	public void setCenterName(String centerName) {
		this.centerName = centerName;
		addValidField(FieldNames.centerName);
	}

	/**
	 * Get tableName
	 */
	@Column(name = "TABLE_NAME")
	public String getTableName() {
		return tableName;
	}

	/**
	 * Set tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
		addValidField(FieldNames.tableName);
	}

}
