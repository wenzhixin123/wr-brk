package com.sinotrans.gd.wlp.basicdata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.sinotrans.framework.core.model.BaseModelClass;
import com.sinotrans.framework.core.model.OperationLog;

/**
 * Model class for 国家信息维护
 */
@Entity
@Table(name = "BAS_COUNT")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasCountModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasCount";

	public static final class FieldNames {
		/**
		 * 国家表UUID
		 */
		public static final String basCountryUuid = "basCountryUuid";
		/**
		 * 国家代码
		 */
		public static final String countryCode = "countryCode";
		/**
		 * 国家中文名称
		 */
		public static final String countryName = "countryName";
		/**
		 * 国家英文名称
		 */
		public static final String countryNameEn = "countryNameEn";
		/**
		 * 首都
		 */
		public static final String countryCapital = "countryCapital";
		/**
		 * 经度1
		 */
		public static final String longitude1 = "longitude1";
		/**
		 * 纬度1
		 */
		public static final String latitude1 = "latitude1";
		/**
		 * 经度2
		 */
		public static final String longitude2 = "longitude2";
		/**
		 * 纬度2
		 */
		public static final String latitude2 = "latitude2";
		/**
		 * 中心代码
		 */
		public static final String centerCode = "centerCode";
		/**
		 * 备注
		 */
		public static final String remark = "remark";
		/**
		 * 状态
		 */
		public static final String status = "status";
		/**
		 * 控制字
		 */
		public static final String controlWord = "controlWord";
		/**
		 * 公司（仓库）代码
		 */
		public static final String officeCode = "officeCode";
		/**
		 * 并发访问控制
		 */
		public static final String recVer = "recVer";
		/**
		 * 创建人
		 */
		public static final String creator = "creator";
		/**
		 * 创建时间
		 */
		public static final String createTime = "createTime";
		/**
		 * 修改人
		 */
		public static final String modifier = "modifier";
		/**
		 * 修改时间
		 */
		public static final String modifyTime = "modifyTime";
	}

	//国家表UUID
	private String basCountryUuid;
	//国家代码
	private String countryCode;
	//国家中文名称
	private String countryName;
	//国家英文名称
	private String countryNameEn;
	//首都
	private String countryCapital;
	//经度1
	private String longitude1;
	//纬度1
	private String latitude1;
	//经度2
	private String longitude2;
	//纬度2
	private String latitude2;
	//中心代码
	private String centerCode;
	//备注
	private String remark;
	//状态
	private String status;
	//控制字
	private String controlWord;
	//公司（仓库）代码
	private String officeCode;
	//并发访问控制
	private Long recVer;
	//创建人
	private String creator;
	//创建时间
	private Date createTime;
	//修改人
	private String modifier;
	//修改时间
	private Date modifyTime;

	/**
	 * Get 国家表UUID
	 */
	@Column(name = "BAS_COUNTRY_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getBasCountryUuid() {
		return basCountryUuid;
	}

	/**
	 * Set 国家表UUID
	 */
	public void setBasCountryUuid(String basCountryUuid) {
		this.basCountryUuid = basCountryUuid;
		addValidField(FieldNames.basCountryUuid);
	}

	/**
	 * Get 国家代码
	 */
	@Column(name = "COUNTRY_CODE")
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Set 国家代码
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
		addValidField(FieldNames.countryCode);
	}

	/**
	 * Get 国家中文名称
	 */
	@Column(name = "COUNTRY_NAME")
	public String getCountryName() {
		return countryName;
	}

	/**
	 * Set 国家中文名称
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
		addValidField(FieldNames.countryName);
	}

	/**
	 * Get 国家英文名称
	 */
	@Column(name = "COUNTRY_NAME_EN")
	public String getCountryNameEn() {
		return countryNameEn;
	}

	/**
	 * Set 国家英文名称
	 */
	public void setCountryNameEn(String countryNameEn) {
		this.countryNameEn = countryNameEn;
		addValidField(FieldNames.countryNameEn);
	}

	/**
	 * Get 首都
	 */
	@Column(name = "COUNTRY_CAPITAL")
	public String getCountryCapital() {
		return countryCapital;
	}

	/**
	 * Set 首都
	 */
	public void setCountryCapital(String countryCapital) {
		this.countryCapital = countryCapital;
		addValidField(FieldNames.countryCapital);
	}

	/**
	 * Get 经度1
	 */
	@Column(name = "LONGITUDE1")
	public String getLongitude1() {
		return longitude1;
	}

	/**
	 * Set 经度1
	 */
	public void setLongitude1(String longitude1) {
		this.longitude1 = longitude1;
		addValidField(FieldNames.longitude1);
	}

	/**
	 * Get 纬度1
	 */
	@Column(name = "LATITUDE1")
	public String getLatitude1() {
		return latitude1;
	}

	/**
	 * Set 纬度1
	 */
	public void setLatitude1(String latitude1) {
		this.latitude1 = latitude1;
		addValidField(FieldNames.latitude1);
	}

	/**
	 * Get 经度2
	 */
	@Column(name = "LONGITUDE2")
	public String getLongitude2() {
		return longitude2;
	}

	/**
	 * Set 经度2
	 */
	public void setLongitude2(String longitude2) {
		this.longitude2 = longitude2;
		addValidField(FieldNames.longitude2);
	}

	/**
	 * Get 纬度2
	 */
	@Column(name = "LATITUDE2")
	public String getLatitude2() {
		return latitude2;
	}

	/**
	 * Set 纬度2
	 */
	public void setLatitude2(String latitude2) {
		this.latitude2 = latitude2;
		addValidField(FieldNames.latitude2);
	}

	/**
	 * Get 中心代码
	 */
	@Column(name = "CENTER_CODE")
	public String getCenterCode() {
		return centerCode;
	}

	/**
	 * Set 中心代码
	 */
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
		addValidField(FieldNames.centerCode);
	}

	/**
	 * Get 备注
	 */
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	/**
	 * Set 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
		addValidField(FieldNames.remark);
	}

	/**
	 * Get 状态
	 * ：Active - 有效； Cancel - 作废
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态
	 * ：Active - 有效； Cancel - 作废
	 */
	public void setStatus(String status) {
		this.status = status;
		addValidField(FieldNames.status);
	}

	/**
	 * Get 控制字
	 * ：默认0
	 */
	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	/**
	 * Set 控制字
	 * ：默认0
	 */
	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField(FieldNames.controlWord);
	}

	/**
	 * Get 公司（仓库）代码
	 */
	@Column(name = "OFFICE_CODE")
	public String getOfficeCode() {
		return officeCode;
	}

	/**
	 * Set 公司（仓库）代码
	 */
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
		addValidField(FieldNames.officeCode);
	}

	/**
	 * Get 并发访问控制
	 */
	@Column(name = "REC_VER")
	@Version
	public Long getRecVer() {
		return recVer;
	}

	/**
	 * Set 并发访问控制
	 */
	public void setRecVer(Long recVer) {
		this.recVer = recVer;
		addValidField(FieldNames.recVer);
	}

	/**
	 * Get 创建人
	 */
	@Column(name = "CREATOR")
	public String getCreator() {
		return creator;
	}

	/**
	 * Set 创建人
	 */
	public void setCreator(String creator) {
		this.creator = creator;
		addValidField(FieldNames.creator);
	}

	/**
	 * Get 创建时间
	 */
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * Set 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		addValidField(FieldNames.createTime);
	}

	/**
	 * Get 修改人
	 */
	@Column(name = "MODIFIER")
	public String getModifier() {
		return modifier;
	}

	/**
	 * Set 修改人
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
		addValidField(FieldNames.modifier);
	}

	/**
	 * Get 修改时间
	 */
	@Column(name = "MODIFY_TIME")
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * Set 修改时间
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
		addValidField(FieldNames.modifyTime);
	}
	
	/**
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="basCountryUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
