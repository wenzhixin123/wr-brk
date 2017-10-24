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
 * Model class for 区域或地点
 */
@Entity
@Table(name = "BAS_SITE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasSiteModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasSite";

	public static final class FieldNames {
		/**
		 * 地点UUID
		 */
		public static final String basSiteUuid = "basSiteUuid";
		/**
		 * 上一级地点UUID
		 */
		public static final String preBasSiteUuid = "preBasSiteUuid";
		/**
		 * 地点代码
		 */
		public static final String siteCode = "siteCode";
		/**
		 * 快速查找编码
		 */
		public static final String siteSearchCode = "siteSearchCode";
		/**
		 * 地点中文名称
		 */
		public static final String siteName = "siteName";
		/**
		 * 缩写名称
		 */
		public static final String siteShortName = "siteShortName";
		/**
		 * 地点英文名称
		 */
		public static final String siteNameEn = "siteNameEn";
		/**
		 * 地点类型
		 */
		public static final String siteType = "siteType";
		/**
		 * 城市代码
		 */
		public static final String otherUuid = "otherUuid";
		/**
		 * 邮政编码
		 */
		public static final String zipcode = "zipcode";
		/**
		 * 电话区号
		 */
		public static final String districtNum = "districtNum";
		/**
		 * 详细地址
		 */
		public static final String address = "address";
		/**
		 * 经度1
		 */
		public static final String longitude = "longitude";
		/**
		 * 纬度1
		 */
		public static final String latitude = "latitude";
		/**
		 * 优先排列序号
		 */
		public static final String seqNo = "seqNo";
		/**
		 * 是否自动审核
		 */
		public static final String isAuto = "isAuto";
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

	//地点UUID
	private String basSiteUuid;
	//上一级地点UUID
	private String preBasSiteUuid;
	//地点代码
	private String siteCode;
	//快速查找编码
	private String siteSearchCode;
	//地点中文名称
	private String siteName;
	//缩写名称
	private String siteShortName;
	//地点英文名称
	private String siteNameEn;
	//地点类型
	private String siteType;
	//城市代码
	private String otherUuid;
	//邮政编码
	private String zipcode;
	//电话区号
	private String districtNum;
	//详细地址
	private String address;
	//经度1
	private String longitude;
	//纬度1
	private String latitude;
	//优先排列序号
	private Long seqNo;
	//是否自动审核
	private String isAuto;
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
	 * Get 地点UUID
	 * D
	 */
	@Column(name = "BAS_SITE_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getBasSiteUuid() {
		return basSiteUuid;
	}

	/**
	 * Set 地点UUID
	 * D
	 */
	public void setBasSiteUuid(String basSiteUuid) {
		this.basSiteUuid = basSiteUuid;
		addValidField(FieldNames.basSiteUuid);
	}

	/**
	 * Get 上一级地点UUID
	 */
	@Column(name = "PRE_BAS_SITE_UUID")
	public String getPreBasSiteUuid() {
		return preBasSiteUuid;
	}

	/**
	 * Set 上一级地点UUID
	 */
	public void setPreBasSiteUuid(String preBasSiteUuid) {
		this.preBasSiteUuid = preBasSiteUuid;
		addValidField(FieldNames.preBasSiteUuid);
	}

	/**
	 * Get 地点代码
	 */
	@Column(name = "SITE_CODE")
	public String getSiteCode() {
		return siteCode;
	}

	/**
	 * Set 地点代码
	 */
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
		addValidField(FieldNames.siteCode);
	}

	/**
	 * Get 快速查找编码
	 */
	@Column(name = "SITE_SEARCH_CODE")
	public String getSiteSearchCode() {
		return siteSearchCode;
	}

	/**
	 * Set 快速查找编码
	 */
	public void setSiteSearchCode(String siteSearchCode) {
		this.siteSearchCode = siteSearchCode;
		addValidField(FieldNames.siteSearchCode);
	}

	/**
	 * Get 地点中文名称
	 */
	@Column(name = "SITE_NAME")
	public String getSiteName() {
		return siteName;
	}

	/**
	 * Set 地点中文名称
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
		addValidField(FieldNames.siteName);
	}

	/**
	 * Get 缩写名称
	 */
	@Column(name = "SITE_SHORT_NAME")
	public String getSiteShortName() {
		return siteShortName;
	}

	/**
	 * Set 缩写名称
	 */
	public void setSiteShortName(String siteShortName) {
		this.siteShortName = siteShortName;
		addValidField(FieldNames.siteShortName);
	}

	/**
	 * Get 地点英文名称
	 */
	@Column(name = "SITE_NAME_EN")
	public String getSiteNameEn() {
		return siteNameEn;
	}

	/**
	 * Set 地点英文名称
	 */
	public void setSiteNameEn(String siteNameEn) {
		this.siteNameEn = siteNameEn;
		addValidField(FieldNames.siteNameEn);
	}

	/**
	 * Get 地点类型
	 * ：COUNTRY - 国家、PROVINCE-省、CITY -城市、COUNTY - 县、TOWN - 镇、AREA - 区域
	 */
	@Column(name = "SITE_TYPE")
	public String getSiteType() {
		return siteType;
	}

	/**
	 * Set 地点类型
	 * ：COUNTRY - 国家、PROVINCE-省、CITY -城市、COUNTY - 县、TOWN - 镇、AREA - 区域
	 */
	public void setSiteType(String siteType) {
		this.siteType = siteType;
		addValidField(FieldNames.siteType);
	}

	/**
	 * Get 城市代码
	 */
	@Column(name = "OTHER_UUID")
	public String getOtherUuid() {
		return otherUuid;
	}

	/**
	 * Set 城市代码
	 */
	public void setOtherUuid(String otherUuid) {
		this.otherUuid = otherUuid;
		addValidField(FieldNames.otherUuid);
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
	 * Get 详细地址
	 */
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	/**
	 * Set 详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
		addValidField(FieldNames.address);
	}

	/**
	 * Get 经度1
	 */
	@Column(name = "LONGITUDE")
	public String getLongitude() {
		return longitude;
	}

	/**
	 * Set 经度1
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
		addValidField(FieldNames.longitude);
	}

	/**
	 * Get 纬度1
	 */
	@Column(name = "LATITUDE")
	public String getLatitude() {
		return latitude;
	}

	/**
	 * Set 纬度1
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
		addValidField(FieldNames.latitude);
	}

	/**
	 * Get 优先排列序号
	 */
	@Column(name = "SEQ_NO")
	public Long getSeqNo() {
		return seqNo;
	}

	/**
	 * Set 优先排列序号
	 */
	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
		addValidField(FieldNames.seqNo);
	}

	/**
	 * Get 是否自动审核
	 * ：Y - 自动审核
	 */
	@Column(name = "IS_AUTO")
	public String getIsAuto() {
		return isAuto;
	}

	/**
	 * Set 是否自动审核
	 * ：Y - 自动审核
	 */
	public void setIsAuto(String isAuto) {
		this.isAuto = isAuto;
		addValidField(FieldNames.isAuto);
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
	private String prrmaryKeyName="basSiteUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
