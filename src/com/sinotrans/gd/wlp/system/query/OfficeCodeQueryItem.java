package com.sinotrans.gd.wlp.system.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class OfficeCodeQueryItem extends BaseQueryItem {

	private String officeUuid;
	private String officeCode;
	private String officeType;
	private String officeName;
	private String officeNameEn;
	private String preOfficeUuid;
	private String abbrev;
	private String businessRegisterNo;
	private String taxRegisterNo;
	private String contact;
	private String email;
	private String tel;
	private String fax;
	private String address;
	private Integer isInternal;
	private Integer isCustomer;
	private Integer isDept;
	private String xchgrName;
	private String customerCode;
	private String language;
	private String countryCode;
	private String provinceCode;
	private String cityCode;
	private String siteCode;
	private String remark;
	private String status;
	private Date cancelDate;
	private String controlWord;
	private Long recVer;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date modifyTime;

	@Column(name = "OFFICE_UUID")
	public String getOfficeUuid() {
		return officeUuid;
	}

	public void setOfficeUuid(String officeUuid) {
		this.officeUuid = officeUuid;
		addValidField("officeUuid");
	}

	@Column(name = "OFFICE_CODE")
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
		addValidField("officeCode");
	}

	@Column(name = "OFFICE_TYPE")
	public String getOfficeType() {
		return officeType;
	}

	public void setOfficeType(String officeType) {
		this.officeType = officeType;
		addValidField("officeType");
	}

	@Column(name = "OFFICE_NAME")
	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
		addValidField("officeName");
	}

	@Column(name = "OFFICE_NAME_EN")
	public String getOfficeNameEn() {
		return officeNameEn;
	}

	public void setOfficeNameEn(String officeNameEn) {
		this.officeNameEn = officeNameEn;
		addValidField("officeNameEn");
	}

	@Column(name = "PRE_OFFICE_UUID")
	public String getPreOfficeUuid() {
		return preOfficeUuid;
	}

	public void setPreOfficeUuid(String preOfficeUuid) {
		this.preOfficeUuid = preOfficeUuid;
		addValidField("preOfficeUuid");
	}

	@Column(name = "ABBREV")
	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
		addValidField("abbrev");
	}

	@Column(name = "BUSINESS_REGISTER_NO")
	public String getBusinessRegisterNo() {
		return businessRegisterNo;
	}

	public void setBusinessRegisterNo(String businessRegisterNo) {
		this.businessRegisterNo = businessRegisterNo;
		addValidField("businessRegisterNo");
	}

	@Column(name = "TAX_REGISTER_NO")
	public String getTaxRegisterNo() {
		return taxRegisterNo;
	}

	public void setTaxRegisterNo(String taxRegisterNo) {
		this.taxRegisterNo = taxRegisterNo;
		addValidField("taxRegisterNo");
	}

	@Column(name = "CONTACT")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
		addValidField("contact");
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		addValidField("email");
	}

	@Column(name = "TEL")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
		addValidField("tel");
	}

	@Column(name = "FAX")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
		addValidField("fax");
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		addValidField("address");
	}

	@Column(name = "IS_INTERNAL")
	public Integer getIsInternal() {
		return isInternal;
	}

	public void setIsInternal(Integer isInternal) {
		this.isInternal = isInternal;
		addValidField("isInternal");
	}

	@Column(name = "IS_CUSTOMER")
	public Integer getIsCustomer() {
		return isCustomer;
	}

	public void setIsCustomer(Integer isCustomer) {
		this.isCustomer = isCustomer;
		addValidField("isCustomer");
	}

	@Column(name = "IS_DEPT")
	public Integer getIsDept() {
		return isDept;
	}

	public void setIsDept(Integer isDept) {
		this.isDept = isDept;
		addValidField("isDept");
	}

	@Column(name = "XCHGR_NAME")
	public String getXchgrName() {
		return xchgrName;
	}

	public void setXchgrName(String xchgrName) {
		this.xchgrName = xchgrName;
		addValidField("xchgrName");
	}

	@Column(name = "CUSTOMER_CODE")
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
		addValidField("customerCode");
	}

	@Column(name = "LANGUAGE")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
		addValidField("language");
	}

	@Column(name = "COUNTRY_CODE")
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
		addValidField("countryCode");
	}

	@Column(name = "PROVINCE_CODE")
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
		addValidField("provinceCode");
	}

	@Column(name = "CITY_CODE")
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
		addValidField("cityCode");
	}

	@Column(name = "SITE_CODE")
	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
		addValidField("siteCode");
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

	@Column(name = "CANCEL_DATE")
	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
		addValidField("cancelDate");
	}

	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField("controlWord");
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
