package com.sinotrans.gd.wlp.system.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class SysUserNoRoleQueryItem extends BaseQueryItem {

	private String userUuid;
	private String preUserUuid;
	private String userCode;
	private String userName;
	private String userNameEn;
	private String position;
	private String firstName;
	private String middleName;
	private String lastName;
	private String userType;
	private String departmentCode;
	private String sex;
	private String personalInfo;
	private String personalPic;
	private String idCard;
	private String marital;
	private String contactAddress;
	private String nativeAddress;
	private String homeAddress;
	private String education;
	private String telephone1;
	private String telephone2;
	private String fax;
	private String bpcall;
	private String mobile;
	private String zipCode;
	private Date hireDate;
	private Date fireDate;
	private String homeTelphone;
	private Date birthDay;
	private String email;
	private String password;
	private String customerCode;
	private String defaultLang;
	private String remark;
	private String status;
	private String controlWord;
	private String officeCode;
	private Long recVer;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date modifyTime;

	@Column(name = "USER_UUID")
	public String getUserUuid() {
		return userUuid;
	}

	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
		addValidField("userUuid");
	}

	@Column(name = "PRE_USER_UUID")
	public String getPreUserUuid() {
		return preUserUuid;
	}

	public void setPreUserUuid(String preUserUuid) {
		this.preUserUuid = preUserUuid;
		addValidField("preUserUuid");
	}

	@Column(name = "USER_CODE")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
		addValidField("userCode");
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
		addValidField("userName");
	}

	@Column(name = "USER_NAME_EN")
	public String getUserNameEn() {
		return userNameEn;
	}

	public void setUserNameEn(String userNameEn) {
		this.userNameEn = userNameEn;
		addValidField("userNameEn");
	}

	@Column(name = "POSITION")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
		addValidField("position");
	}

	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		addValidField("firstName");
	}

	@Column(name = "MIDDLE_NAME")
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
		addValidField("middleName");
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		addValidField("lastName");
	}

	@Column(name = "USER_TYPE")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
		addValidField("userType");
	}

	@Column(name = "DEPARTMENT_CODE")
	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
		addValidField("departmentCode");
	}

	@Column(name = "SEX")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
		addValidField("sex");
	}

	@Column(name = "PERSONAL_INFO")
	public String getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(String personalInfo) {
		this.personalInfo = personalInfo;
		addValidField("personalInfo");
	}

	@Column(name = "PERSONAL_PIC")
	public String getPersonalPic() {
		return personalPic;
	}

	public void setPersonalPic(String personalPic) {
		this.personalPic = personalPic;
		addValidField("personalPic");
	}

	@Column(name = "ID_CARD")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
		addValidField("idCard");
	}

	@Column(name = "MARITAL")
	public String getMarital() {
		return marital;
	}

	public void setMarital(String marital) {
		this.marital = marital;
		addValidField("marital");
	}

	@Column(name = "CONTACT_ADDRESS")
	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
		addValidField("contactAddress");
	}

	@Column(name = "NATIVE_ADDRESS")
	public String getNativeAddress() {
		return nativeAddress;
	}

	public void setNativeAddress(String nativeAddress) {
		this.nativeAddress = nativeAddress;
		addValidField("nativeAddress");
	}

	@Column(name = "HOME_ADDRESS")
	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
		addValidField("homeAddress");
	}

	@Column(name = "EDUCATION")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
		addValidField("education");
	}

	@Column(name = "TELEPHONE1")
	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
		addValidField("telephone1");
	}

	@Column(name = "TELEPHONE2")
	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
		addValidField("telephone2");
	}

	@Column(name = "FAX")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
		addValidField("fax");
	}

	@Column(name = "BPCALL")
	public String getBpcall() {
		return bpcall;
	}

	public void setBpcall(String bpcall) {
		this.bpcall = bpcall;
		addValidField("bpcall");
	}

	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
		addValidField("mobile");
	}

	@Column(name = "ZIP_CODE")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
		addValidField("zipCode");
	}

	@Column(name = "HIRE_DATE")
	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
		addValidField("hireDate");
	}

	@Column(name = "FIRE_DATE")
	public Date getFireDate() {
		return fireDate;
	}

	public void setFireDate(Date fireDate) {
		this.fireDate = fireDate;
		addValidField("fireDate");
	}

	@Column(name = "HOME_TELPHONE")
	public String getHomeTelphone() {
		return homeTelphone;
	}

	public void setHomeTelphone(String homeTelphone) {
		this.homeTelphone = homeTelphone;
		addValidField("homeTelphone");
	}

	@Column(name = "BIRTH_DAY")
	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
		addValidField("birthDay");
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		addValidField("email");
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		addValidField("password");
	}

	@Column(name = "CUSTOMER_CODE")
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
		addValidField("customerCode");
	}

	@Column(name = "DEFAULT_LANG")
	public String getDefaultLang() {
		return defaultLang;
	}

	public void setDefaultLang(String defaultLang) {
		this.defaultLang = defaultLang;
		addValidField("defaultLang");
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
