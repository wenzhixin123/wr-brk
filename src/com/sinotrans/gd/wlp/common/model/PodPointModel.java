package com.sinotrans.gd.wlp.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sinotrans.framework.core.model.BaseModelClass;

/**
 * Model class for PodPoint
 */
@Entity
@Table(name = "pod_point")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PodPointModel extends BaseModelClass {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "PodPoint";

	public static final class FieldNames {
		/**
		 * reqCode
		 */
		public static final String reqCode = "reqCode";
		/**
		 * reqTime
		 */
		public static final String reqTime = "reqTime";
		/**
		 * clientCode
		 */
		public static final String clientCode = "clientCode";
		/**
		 * tokenCode
		 */
		public static final String tokenCode = "tokenCode";
		/**
		 * interfaceName
		 */
		public static final String interfaceName = "interfaceName";
		/**
		 * podCode
		 */
		public static final String podCode = "podCode";
		/**
		 * pointCode
		 */
		public static final String pointCode = "pointCode";
		/**
		 * indBind
		 */
		public static final String indBind = "indBind";
		/**
		 * podPointId
		 */
		public static final String podPointId = "podPointId";
	}

	//reqCode
	private String reqCode;
	//reqTime
	private Date reqTime;
	//clientCode
	private String clientCode;
	//tokenCode
	private String tokenCode;
	//interfaceName
	private String interfaceName;
	//podCode
	private String podCode;
	//pointCode
	private String pointCode;
	//indBind
	private String indBind;
	//podPointId
	private String podPointId;

	/**
	 * Get reqCode
	 */
	@Column(name = "req_code")
	public String getReqCode() {
		return reqCode;
	}

	/**
	 * Set reqCode
	 */
	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
		addValidField(FieldNames.reqCode);
	}

	/**
	 * Get reqTime
	 */
	@Column(name = "req_time")
	public Date getReqTime() {
		return reqTime;
	}

	/**
	 * Set reqTime
	 */
	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
		addValidField(FieldNames.reqTime);
	}

	/**
	 * Get clientCode
	 */
	@Column(name = "client_code")
	public String getClientCode() {
		return clientCode;
	}

	/**
	 * Set clientCode
	 */
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
		addValidField(FieldNames.clientCode);
	}

	/**
	 * Get tokenCode
	 */
	@Column(name = "token_code")
	public String getTokenCode() {
		return tokenCode;
	}

	/**
	 * Set tokenCode
	 */
	public void setTokenCode(String tokenCode) {
		this.tokenCode = tokenCode;
		addValidField(FieldNames.tokenCode);
	}

	/**
	 * Get interfaceName
	 */
	@Column(name = "interface_name")
	public String getInterfaceName() {
		return interfaceName;
	}

	/**
	 * Set interfaceName
	 */
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
		addValidField(FieldNames.interfaceName);
	}

	/**
	 * Get podCode
	 */
	@Column(name = "pod_code")
	public String getPodCode() {
		return podCode;
	}

	/**
	 * Set podCode
	 */
	public void setPodCode(String podCode) {
		this.podCode = podCode;
		addValidField(FieldNames.podCode);
	}

	/**
	 * Get pointCode
	 */
	@Column(name = "point_code")
	public String getPointCode() {
		return pointCode;
	}

	/**
	 * Set pointCode
	 */
	public void setPointCode(String pointCode) {
		this.pointCode = pointCode;
		addValidField(FieldNames.pointCode);
	}

	/**
	 * Get indBind
	 */
	@Column(name = "ind_bind")
	public String getIndBind() {
		return indBind;
	}

	/**
	 * Set indBind
	 */
	public void setIndBind(String indBind) {
		this.indBind = indBind;
		addValidField(FieldNames.indBind);
	}

	/**
	 * Get podPointId
	 */
	@Column(name = "pod_point_id")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getPodPointId() {
		return podPointId;
	}

	/**
	 * Set podPointId
	 */
	public void setPodPointId(String podPointId) {
		this.podPointId = podPointId;
		addValidField(FieldNames.podPointId);
	}

}
