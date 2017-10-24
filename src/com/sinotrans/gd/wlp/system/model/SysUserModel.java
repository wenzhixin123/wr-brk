package com.sinotrans.gd.wlp.system.model;

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
 * Model class for 用户信息表
 */
@Entity
@Table(name = "SYS_USER")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysUserModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "SysUser";

	public static final class FieldNames {
		/**
		 * UUID
		 */
		public static final String userUuid = "userUuid";
		/**
		 * 上级用户UUID
		 */
		public static final String preUserUuid = "preUserUuid";
		/**
		 * 用户编码
		 */
		public static final String userCode = "userCode";
		/**
		 * 用户名称
		 */
		public static final String userName = "userName";
		/**
		 * 用户英文名称
		 */
		public static final String userNameEn = "userNameEn";
		/**
		 * 职位描述
		 */
		public static final String position = "position";
		/**
		 * 名字
		 */
		public static final String firstName = "firstName";
		/**
		 * 中间名字
		 */
		public static final String middleName = "middleName";
		/**
		 * 姓
		 */
		public static final String lastName = "lastName";
		/**
		 * 用户类型
		 */
		public static final String userType = "userType";
		/**
		 * 所属部门
		 */
		public static final String departmentCode = "departmentCode";
		/**
		 * 性别
		 */
		public static final String sex = "sex";
		/**
		 * 个人签名
		 */
		public static final String personalInfo = "personalInfo";
		/**
		 * 个人头像
		 */
		public static final String personalPic = "personalPic";
		/**
		 * 身份证号
		 */
		public static final String idCard = "idCard";
		/**
		 * 婚姻状况
		 */
		public static final String marital = "marital";
		/**
		 * 联系地址
		 */
		public static final String contactAddress = "contactAddress";
		/**
		 * 本地名字
		 */
		public static final String nativeAddress = "nativeAddress";
		/**
		 * 联系地址
		 */
		public static final String homeAddress = "homeAddress";
		/**
		 * 教育程度
		 */
		public static final String education = "education";
		/**
		 * 联系电话
		 */
		public static final String telephone1 = "telephone1";
		/**
		 * 联系电话2
		 */
		public static final String telephone2 = "telephone2";
		/**
		 * 传真
		 */
		public static final String fax = "fax";
		/**
		 * 传呼
		 */
		public static final String bpcall = "bpcall";
		/**
		 * 移动电话
		 */
		public static final String mobile = "mobile";
		/**
		 * 邮编
		 */
		public static final String zipCode = "zipCode";
		/**
		 * 雇佣日期
		 */
		public static final String hireDate = "hireDate";
		/**
		 * 离职日期
		 */
		public static final String fireDate = "fireDate";
		/**
		 * 家庭电话
		 */
		public static final String homeTelphone = "homeTelphone";
		/**
		 * 生日
		 */
		public static final String birthDay = "birthDay";
		/**
		 * 电子邮件
		 */
		public static final String email = "email";
		/**
		 * 密码
		 */
		public static final String password = "password";
		/**
		 * 所属公司代码
		 */
		public static final String customerCode = "customerCode";
		/**
		 * 缺省语言
		 */
		public static final String defaultLang = "defaultLang";
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

	//UUID
	private String userUuid;
	//上级用户UUID
	private String preUserUuid;
	//用户编码
	private String userCode;
	//用户名称
	private String userName;
	//用户英文名称
	private String userNameEn;
	//职位描述
	private String position;
	//名字
	private String firstName;
	//中间名字
	private String middleName;
	//姓
	private String lastName;
	//用户类型
	private String userType;
	//所属部门
	private String departmentCode;
	//性别
	private String sex;
	//个人签名
	private String personalInfo;
	//个人头像
	private String personalPic;
	//身份证号
	private String idCard;
	//婚姻状况
	private String marital;
	//联系地址
	private String contactAddress;
	//本地名字
	private String nativeAddress;
	//联系地址
	private String homeAddress;
	//教育程度
	private String education;
	//联系电话
	private String telephone1;
	//联系电话2
	private String telephone2;
	//传真
	private String fax;
	//传呼
	private String bpcall;
	//移动电话
	private String mobile;
	//邮编
	private String zipCode;
	//雇佣日期
	private Date hireDate;
	//离职日期
	private Date fireDate;
	//家庭电话
	private String homeTelphone;
	//生日
	private Date birthDay;
	//电子邮件
	private String email;
	//密码
	private String password;
	//所属公司代码
	private String customerCode;
	//缺省语言
	private String defaultLang;
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
	 * Get UUID
	 */
	@Column(name = "USER_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getUserUuid() {
		return userUuid;
	}

	/**
	 * Set UUID
	 */
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
		addValidField(FieldNames.userUuid);
	}

	/**
	 * Get 上级用户UUID
	 */
	@Column(name = "PRE_USER_UUID")
	public String getPreUserUuid() {
		return preUserUuid;
	}

	/**
	 * Set 上级用户UUID
	 */
	public void setPreUserUuid(String preUserUuid) {
		this.preUserUuid = preUserUuid;
		addValidField(FieldNames.preUserUuid);
	}

	/**
	 * Get 用户编码
	 */
	@Column(name = "USER_CODE")
	public String getUserCode() {
		return userCode;
	}

	/**
	 * Set 用户编码
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
		addValidField(FieldNames.userCode);
	}

	/**
	 * Get 用户名称
	 */
	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	/**
	 * Set 用户名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
		addValidField(FieldNames.userName);
	}

	/**
	 * Get 用户英文名称
	 */
	@Column(name = "USER_NAME_EN")
	public String getUserNameEn() {
		return userNameEn;
	}

	/**
	 * Set 用户英文名称
	 */
	public void setUserNameEn(String userNameEn) {
		this.userNameEn = userNameEn;
		addValidField(FieldNames.userNameEn);
	}

	/**
	 * Get 职位描述
	 */
	@Column(name = "POSITION")
	public String getPosition() {
		return position;
	}

	/**
	 * Set 职位描述
	 */
	public void setPosition(String position) {
		this.position = position;
		addValidField(FieldNames.position);
	}

	/**
	 * Get 名字
	 */
	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set 名字
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		addValidField(FieldNames.firstName);
	}

	/**
	 * Get 中间名字
	 */
	@Column(name = "MIDDLE_NAME")
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Set 中间名字
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
		addValidField(FieldNames.middleName);
	}

	/**
	 * Get 姓
	 */
	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set 姓
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
		addValidField(FieldNames.lastName);
	}

	/**
	 * Get 用户类型
	 */
	@Column(name = "USER_TYPE")
	public String getUserType() {
		return userType;
	}

	/**
	 * Set 用户类型
	 */
	public void setUserType(String userType) {
		this.userType = userType;
		addValidField(FieldNames.userType);
	}

	/**
	 * Get 所属部门
	 */
	@Column(name = "DEPARTMENT_CODE")
	public String getDepartmentCode() {
		return departmentCode;
	}

	/**
	 * Set 所属部门
	 */
	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
		addValidField(FieldNames.departmentCode);
	}

	/**
	 * Get 性别
	 */
	@Column(name = "SEX")
	public String getSex() {
		return sex;
	}

	/**
	 * Set 性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
		addValidField(FieldNames.sex);
	}

	/**
	 * Get 个人签名
	 */
	@Column(name = "PERSONAL_INFO")
	public String getPersonalInfo() {
		return personalInfo;
	}

	/**
	 * Set 个人签名
	 */
	public void setPersonalInfo(String personalInfo) {
		this.personalInfo = personalInfo;
		addValidField(FieldNames.personalInfo);
	}

	/**
	 * Get 个人头像
	 */
	@Column(name = "PERSONAL_PIC")
	public String getPersonalPic() {
		return personalPic;
	}

	/**
	 * Set 个人头像
	 */
	public void setPersonalPic(String personalPic) {
		this.personalPic = personalPic;
		addValidField(FieldNames.personalPic);
	}

	/**
	 * Get 身份证号
	 */
	@Column(name = "ID_CARD")
	public String getIdCard() {
		return idCard;
	}

	/**
	 * Set 身份证号
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
		addValidField(FieldNames.idCard);
	}

	/**
	 * Get 婚姻状况
	 */
	@Column(name = "MARITAL")
	public String getMarital() {
		return marital;
	}

	/**
	 * Set 婚姻状况
	 */
	public void setMarital(String marital) {
		this.marital = marital;
		addValidField(FieldNames.marital);
	}

	/**
	 * Get 联系地址
	 */
	@Column(name = "CONTACT_ADDRESS")
	public String getContactAddress() {
		return contactAddress;
	}

	/**
	 * Set 联系地址
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
		addValidField(FieldNames.contactAddress);
	}

	/**
	 * Get 本地名字
	 */
	@Column(name = "NATIVE_ADDRESS")
	public String getNativeAddress() {
		return nativeAddress;
	}

	/**
	 * Set 本地名字
	 */
	public void setNativeAddress(String nativeAddress) {
		this.nativeAddress = nativeAddress;
		addValidField(FieldNames.nativeAddress);
	}

	/**
	 * Get 联系地址
	 */
	@Column(name = "HOME_ADDRESS")
	public String getHomeAddress() {
		return homeAddress;
	}

	/**
	 * Set 联系地址
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
		addValidField(FieldNames.homeAddress);
	}

	/**
	 * Get 教育程度
	 */
	@Column(name = "EDUCATION")
	public String getEducation() {
		return education;
	}

	/**
	 * Set 教育程度
	 */
	public void setEducation(String education) {
		this.education = education;
		addValidField(FieldNames.education);
	}

	/**
	 * Get 联系电话
	 */
	@Column(name = "TELEPHONE1")
	public String getTelephone1() {
		return telephone1;
	}

	/**
	 * Set 联系电话
	 */
	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
		addValidField(FieldNames.telephone1);
	}

	/**
	 * Get 联系电话2
	 */
	@Column(name = "TELEPHONE2")
	public String getTelephone2() {
		return telephone2;
	}

	/**
	 * Set 联系电话2
	 */
	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
		addValidField(FieldNames.telephone2);
	}

	/**
	 * Get 传真
	 */
	@Column(name = "FAX")
	public String getFax() {
		return fax;
	}

	/**
	 * Set 传真
	 */
	public void setFax(String fax) {
		this.fax = fax;
		addValidField(FieldNames.fax);
	}

	/**
	 * Get 传呼
	 */
	@Column(name = "BPCALL")
	public String getBpcall() {
		return bpcall;
	}

	/**
	 * Set 传呼
	 */
	public void setBpcall(String bpcall) {
		this.bpcall = bpcall;
		addValidField(FieldNames.bpcall);
	}

	/**
	 * Get 移动电话
	 */
	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	/**
	 * Set 移动电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
		addValidField(FieldNames.mobile);
	}

	/**
	 * Get 邮编
	 */
	@Column(name = "ZIP_CODE")
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Set 邮编
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
		addValidField(FieldNames.zipCode);
	}

	/**
	 * Get 雇佣日期
	 */
	@Column(name = "HIRE_DATE")
	public Date getHireDate() {
		return hireDate;
	}

	/**
	 * Set 雇佣日期
	 */
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
		addValidField(FieldNames.hireDate);
	}

	/**
	 * Get 离职日期
	 */
	@Column(name = "FIRE_DATE")
	public Date getFireDate() {
		return fireDate;
	}

	/**
	 * Set 离职日期
	 */
	public void setFireDate(Date fireDate) {
		this.fireDate = fireDate;
		addValidField(FieldNames.fireDate);
	}

	/**
	 * Get 家庭电话
	 */
	@Column(name = "HOME_TELPHONE")
	public String getHomeTelphone() {
		return homeTelphone;
	}

	/**
	 * Set 家庭电话
	 */
	public void setHomeTelphone(String homeTelphone) {
		this.homeTelphone = homeTelphone;
		addValidField(FieldNames.homeTelphone);
	}

	/**
	 * Get 生日
	 */
	@Column(name = "BIRTH_DAY")
	public Date getBirthDay() {
		return birthDay;
	}

	/**
	 * Set 生日
	 */
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
		addValidField(FieldNames.birthDay);
	}

	/**
	 * Get 电子邮件
	 */
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	/**
	 * Set 电子邮件
	 */
	public void setEmail(String email) {
		this.email = email;
		addValidField(FieldNames.email);
	}

	/**
	 * Get 密码
	 */
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	/**
	 * Set 密码
	 */
	public void setPassword(String password) {
		this.password = password;
		addValidField(FieldNames.password);
	}

	/**
	 * Get 所属公司代码
	 */
	@Column(name = "CUSTOMER_CODE")
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * Set 所属公司代码
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
		addValidField(FieldNames.customerCode);
	}

	/**
	 * Get 缺省语言
	 */
	@Column(name = "DEFAULT_LANG")
	public String getDefaultLang() {
		return defaultLang;
	}

	/**
	 * Set 缺省语言
	 */
	public void setDefaultLang(String defaultLang) {
		this.defaultLang = defaultLang;
		addValidField(FieldNames.defaultLang);
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
	private String prrmaryKeyName="userUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
