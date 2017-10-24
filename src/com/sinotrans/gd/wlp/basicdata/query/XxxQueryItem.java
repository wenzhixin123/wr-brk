package com.sinotrans.gd.wlp.basicdata.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@Entity
public class XxxQueryItem extends BaseQueryItem {

	private String head;
	private String basConUuid;
	private String countryCode;
	private String countryName;
	private String countryNameEn;
	private String countryCapital;
	private String longitude1;
	private String latitude1;
	private String longitude2;
	private String latitude2;
	private String centerCode;
	private String remark;
	private String status;
	private String controlWord;
	private String officeCode;
	private Long recVer;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date modifyTime;

	@Column(name = "HEAD")
	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
		addValidField("head");
	}

	@Column(name = "BAS_CON_UUID")
	public String getBasConUuid() {
		return basConUuid;
	}

	public void setBasConUuid(String basConUuid) {
		this.basConUuid = basConUuid;
		addValidField("basConUuid");
	}

	@Column(name = "COUNTRY_CODE")
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
		addValidField("countryCode");
	}

	@Column(name = "COUNTRY_NAME")
	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
		addValidField("countryName");
	}

	@Column(name = "COUNTRY_NAME_EN")
	public String getCountryNameEn() {
		return countryNameEn;
	}

	public void setCountryNameEn(String countryNameEn) {
		this.countryNameEn = countryNameEn;
		addValidField("countryNameEn");
	}

	@Column(name = "COUNTRY_CAPITAL")
	public String getCountryCapital() {
		return countryCapital;
	}

	public void setCountryCapital(String countryCapital) {
		this.countryCapital = countryCapital;
		addValidField("countryCapital");
	}

	@Column(name = "LONGITUDE1")
	public String getLongitude1() {
		return longitude1;
	}

	public void setLongitude1(String longitude1) {
		this.longitude1 = longitude1;
		addValidField("longitude1");
	}

	@Column(name = "LATITUDE1")
	public String getLatitude1() {
		return latitude1;
	}

	public void setLatitude1(String latitude1) {
		this.latitude1 = latitude1;
		addValidField("latitude1");
	}

	@Column(name = "LONGITUDE2")
	public String getLongitude2() {
		return longitude2;
	}

	public void setLongitude2(String longitude2) {
		this.longitude2 = longitude2;
		addValidField("longitude2");
	}

	@Column(name = "LATITUDE2")
	public String getLatitude2() {
		return latitude2;
	}

	public void setLatitude2(String latitude2) {
		this.latitude2 = latitude2;
		addValidField("latitude2");
	}

	@Column(name = "CENTER_CODE")
	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
		addValidField("centerCode");
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		addValidField("remark");
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		addValidField("status");
	}

	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField("controlWord");
	}

	@Column(name = "OFFICE_CODE")
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
		addValidField("officeCode");
	}

	@Column(name = "REC_VER")
	public Long getRecVer() {
		return recVer;
	}

	public void setRecVer(Long recVer) {
		this.recVer = recVer;
		addValidField("recVer");
	}

	@Column(name = "CREATOR")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
		addValidField("creator");
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		addValidField("createTime");
	}

	@Column(name = "MODIFIER")
	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
		addValidField("modifier");
	}

	@Column(name = "MODIFY_TIME")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
		addValidField("modifyTime");
	}

}
