package com.sinotrans.gd.wlp.basicdata.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class BasCustomerQueryItem extends BaseQueryItem {

	private String basCustomerUuid;
	private String customerCode;
	private String customerName;
	private String customerNameEn;
	private String address1;
	private String address2;
	private String address4;
	private String address3;
	private Long postalCode;
	private String telNo1;
	private String telNo2;
	private String faxNo1;
	private String faxNo2;
	private String email;
	private String countryCode;
	private String provinceCode;
	private String cityCode;
	private String siteCode;
	private String status;
	private String centerCode;
	private String controlWord;
	private String remark;
	private String aux1;
	private String aux2;
	private String aux3;
	private String aux4;
	private String aux5;
	private String officeCode;
	private Long recVer;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date modifyTime;

	@Column(name = "BAS_CUSTOMER_UUID")
	public String getBasCustomerUuid() {
		return basCustomerUuid;
	}

	public void setBasCustomerUuid(String basCustomerUuid) {
		this.basCustomerUuid = basCustomerUuid;
		addValidField("basCustomerUuid");
	}

	@Column(name = "CUSTOMER_CODE")
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
		addValidField("customerCode");
	}

	@Column(name = "CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
		addValidField("customerName");
	}

	@Column(name = "CUSTOMER_NAME_EN")
	public String getCustomerNameEn() {
		return customerNameEn;
	}

	public void setCustomerNameEn(String customerNameEn) {
		this.customerNameEn = customerNameEn;
		addValidField("customerNameEn");
	}

	@Column(name = "ADDRESS1")
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
		addValidField("address1");
	}

	@Column(name = "ADDRESS2")
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
		addValidField("address2");
	}

	@Column(name = "ADDRESS4")
	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
		addValidField("address4");
	}

	@Column(name = "ADDRESS3")
	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
		addValidField("address3");
	}

	@Column(name = "POSTAL_CODE")
	public Long getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Long postalCode) {
		this.postalCode = postalCode;
		addValidField("postalCode");
	}

	@Column(name = "TEL_NO_1")
	public String getTelNo1() {
		return telNo1;
	}

	public void setTelNo1(String telNo1) {
		this.telNo1 = telNo1;
		addValidField("telNo1");
	}

	@Column(name = "TEL_NO_2")
	public String getTelNo2() {
		return telNo2;
	}

	public void setTelNo2(String telNo2) {
		this.telNo2 = telNo2;
		addValidField("telNo2");
	}

	@Column(name = "FAX_NO_1")
	public String getFaxNo1() {
		return faxNo1;
	}

	public void setFaxNo1(String faxNo1) {
		this.faxNo1 = faxNo1;
		addValidField("faxNo1");
	}

	@Column(name = "FAX_NO_2")
	public String getFaxNo2() {
		return faxNo2;
	}

	public void setFaxNo2(String faxNo2) {
		this.faxNo2 = faxNo2;
		addValidField("faxNo2");
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		addValidField("email");
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

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		addValidField("status");
	}

	@Column(name = "CENTER_CODE")
	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
		addValidField("centerCode");
	}

	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField("controlWord");
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		addValidField("remark");
	}

	@Column(name = "AUX1")
	public String getAux1() {
		return aux1;
	}

	public void setAux1(String aux1) {
		this.aux1 = aux1;
		addValidField("aux1");
	}

	@Column(name = "AUX2")
	public String getAux2() {
		return aux2;
	}

	public void setAux2(String aux2) {
		this.aux2 = aux2;
		addValidField("aux2");
	}

	@Column(name = "AUX3")
	public String getAux3() {
		return aux3;
	}

	public void setAux3(String aux3) {
		this.aux3 = aux3;
		addValidField("aux3");
	}

	@Column(name = "AUX4")
	public String getAux4() {
		return aux4;
	}

	public void setAux4(String aux4) {
		this.aux4 = aux4;
		addValidField("aux4");
	}

	@Column(name = "AUX5")
	public String getAux5() {
		return aux5;
	}

	public void setAux5(String aux5) {
		this.aux5 = aux5;
		addValidField("aux5");
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
