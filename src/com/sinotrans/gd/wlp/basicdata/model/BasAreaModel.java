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
 * Model class for 区县维护
 */
@Entity
@Table(name = "BAS_AREA")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasAreaModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasArea";

	public static final class FieldNames {
		/**
		 * Phisical
		 */
		public static final String basAreaUuid = "basAreaUuid";
		/**
		 * 区县代码
		 */
		public static final String areaCode = "areaCode";
		/**
		 * 区县名称
		 */
		public static final String areaName = "areaName";
		/**
		 * AREA_NAME_EN
		 */
		public static final String areaNameEn = "areaNameEn";
		/**
		 * 城市代码
		 */
		public static final String cityCode = "cityCode";
		/**
		 * 城市中文名称
		 */
		public static final String cityName = "cityName";
		/**
		 * 城市英文名称
		 */
		public static final String cityNameEn = "cityNameEn";
		/**
		 * 所属省份
		 */
		public static final String provinceCode = "provinceCode";
		/**
		 * 所属省份
		 */
		public static final String provinceName = "provinceName";
		/**
		 * 所属国家
		 */
		public static final String countryCode = "countryCode";
		/**
		 * 所属国家
		 */
		public static final String countryName = "countryName";
		/**
		 * 邮政编码
		 */
		public static final String zipcode = "zipcode";
		/**
		 * 电话区号
		 */
		public static final String districtNum = "districtNum";
		/**
		 * 经度
		 */
		public static final String longitude = "longitude";
		/**
		 * 纬度
		 */
		public static final String latitude = "latitude";
		/**
		 * 备注
		 */
		public static final String remark = "remark";
		/**
		 * 状态
		 */
		public static final String status = "status";
		/**
		 * 中心代码
		 */
		public static final String centerCode = "centerCode";
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

	//Phisical
	private String basAreaUuid;
	//区县代码
	private String areaCode;
	//区县名称
	private String areaName;
	//AREA_NAME_EN
	private String areaNameEn;
	//城市代码
	private String cityCode;
	//城市中文名称
	private String cityName;
	//城市英文名称
	private String cityNameEn;
	//所属省份
	private String provinceCode;
	//所属省份
	private String provinceName;
	//所属国家
	private String countryCode;
	//所属国家
	private String countryName;
	//邮政编码
	private String zipcode;
	//电话区号
	private String districtNum;
	//经度
	private String longitude;
	//纬度
	private String latitude;
	//备注
	private String remark;
	//状态
	private String status;
	//中心代码
	private String centerCode;
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
	 * Get Phisical
	 * Primary Key
	 */
	@Column(name = "BAS_AREA_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getBasAreaUuid() {
		return basAreaUuid;
	}

	/**
	 * Set Phisical
	 * Primary Key
	 */
	public void setBasAreaUuid(String basAreaUuid) {
		this.basAreaUuid = basAreaUuid;
		addValidField(FieldNames.basAreaUuid);
	}

	/**
	 * Get 区县代码
	 */
	@Column(name = "AREA_CODE")
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * Set 区县代码
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
		addValidField(FieldNames.areaCode);
	}

	/**
	 * Get 区县名称
	 */
	@Column(name = "AREA_NAME")
	public String getAreaName() {
		return areaName;
	}

	/**
	 * Set 区县名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
		addValidField(FieldNames.areaName);
	}

	/**
	 * Get AREA_NAME_EN
	 */
	@Column(name = "AREA_NAME_EN")
	public String getAreaNameEn() {
		return areaNameEn;
	}

	/**
	 * Set AREA_NAME_EN
	 */
	public void setAreaNameEn(String areaNameEn) {
		this.areaNameEn = areaNameEn;
		addValidField(FieldNames.areaNameEn);
	}

	/**
	 * Get 城市代码
	 */
	@Column(name = "CITY_CODE")
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * Set 城市代码
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
		addValidField(FieldNames.cityCode);
	}

	/**
	 * Get 城市中文名称
	 */
	@Column(name = "CITY_NAME")
	public String getCityName() {
		return cityName;
	}

	/**
	 * Set 城市中文名称
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
		addValidField(FieldNames.cityName);
	}

	/**
	 * Get 城市英文名称
	 */
	@Column(name = "CITY_NAME_EN")
	public String getCityNameEn() {
		return cityNameEn;
	}

	/**
	 * Set 城市英文名称
	 */
	public void setCityNameEn(String cityNameEn) {
		this.cityNameEn = cityNameEn;
		addValidField(FieldNames.cityNameEn);
	}

	/**
	 * Get 所属省份
	 * ：省份编码
	 */
	@Column(name = "PROVINCE_CODE")
	public String getProvinceCode() {
		return provinceCode;
	}

	/**
	 * Set 所属省份
	 * ：省份编码
	 */
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
		addValidField(FieldNames.provinceCode);
	}

	/**
	 * Get 所属省份
	 * ：省份名称
	 */
	@Column(name = "PROVINCE_NAME")
	public String getProvinceName() {
		return provinceName;
	}

	/**
	 * Set 所属省份
	 * ：省份名称
	 */
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
		addValidField(FieldNames.provinceName);
	}

	/**
	 * Get 所属国家
	 * ：国家编码
	 */
	@Column(name = "COUNTRY_CODE")
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Set 所属国家
	 * ：国家编码
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
		addValidField(FieldNames.countryCode);
	}

	/**
	 * Get 所属国家
	 * ：国家名称
	 */
	@Column(name = "COUNTRY_NAME")
	public String getCountryName() {
		return countryName;
	}

	/**
	 * Set 所属国家
	 * ：国家名称
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
		addValidField(FieldNames.countryName);
	}

	/**
	 * Get 邮政编码
	 */
	@Column(name = "ZIPCODE")
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * Set 邮政编码
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
		addValidField(FieldNames.zipcode);
	}

	/**
	 * Get 电话区号
	 */
	@Column(name = "DISTRICT_NUM")
	public String getDistrictNum() {
		return districtNum;
	}

	/**
	 * Set 电话区号
	 */
	public void setDistrictNum(String districtNum) {
		this.districtNum = districtNum;
		addValidField(FieldNames.districtNum);
	}

	/**
	 * Get 经度
	 */
	@Column(name = "LONGITUDE")
	public String getLongitude() {
		return longitude;
	}

	/**
	 * Set 经度
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
		addValidField(FieldNames.longitude);
	}

	/**
	 * Get 纬度
	 */
	@Column(name = "LATITUDE")
	public String getLatitude() {
		return latitude;
	}

	/**
	 * Set 纬度
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
		addValidField(FieldNames.latitude);
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
	private String prrmaryKeyName="basAreaUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
