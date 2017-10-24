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
 * Model class for 客户联系人信息
 */
@Entity
@Table(name = "BAS_CONTACT")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasContactModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasContact";

	public static final class FieldNames {
		/**
		 * 主键
		 */
		public static final String basContactUuid = "basContactUuid";
		/**
		 * basCustomerUuid
		 */
		public static final String basCustomerUuid = "basCustomerUuid";
		/**
		 * 主键ID
		 */
		public static final String contactCode = "contactCode";
		/**
		 * contactName
		 */
		public static final String contactName = "contactName";
		/**
		 * 联系人
		 */
		public static final String contactNameEn = "contactNameEn";
		/**
		 * 邮件
		 */
		public static final String email = "email";
		/**
		 * 电话
		 */
		public static final String tel = "tel";
		/**
		 * 传真
		 */
		public static final String fax = "fax";
		/**
		 * 手机号码
		 */
		public static final String mobilephone = "mobilephone";
		/**
		 * 职位
		 */
		public static final String position = "position";
		/**
		 * 生日
		 */
		public static final String birthday = "birthday";
		/**
		 * 爱好
		 */
		public static final String hobby = "hobby";
		/**
		 * MSN/QQ号码
		 */
		public static final String msnQqNo = "msnQqNo";
		/**
		 * 联系人身份证号码
		 */
		public static final String idCard = "idCard";
		/**
		 * 是否默认
		 */
		public static final String isDefault = "isDefault";
		/**
		 * remark
		 */
		public static final String remark = "remark";
		/**
		 * 自定义字段3
		 */
		public static final String aux3 = "aux3";
		/**
		 * 自定义字段2
		 */
		public static final String aux2 = "aux2";
		/**
		 * 自定义字段1
		 */
		public static final String aux1 = "aux1";
		/**
		 * 自定义字段4
		 */
		public static final String aux4 = "aux4";
		/**
		 * 自定义字段5
		 */
		public static final String aux5 = "aux5";
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
		 * 创建时间
		 */
		public static final String createTime = "createTime";
		/**
		 * 创建人
		 */
		public static final String creator = "creator";
		/**
		 * 修改人
		 */
		public static final String modifier = "modifier";
		/**
		 * 修改时间
		 */
		public static final String modifyTime = "modifyTime";
	}

	// 主键
	private String basContactUuid;
	// basCustomerUuid
	private String basCustomerUuid;
	// 主键ID
	private String contactCode;
	// contactName
	private String contactName;
	// 联系人
	private String contactNameEn;
	// 邮件
	private String email;
	// 电话
	private String tel;
	// 传真
	private String fax;
	// 手机号码
	private String mobilephone;
	// 职位
	private String position;
	// 生日
	private String birthday;
	// 爱好
	private String hobby;
	// MSN/QQ号码
	private String msnQqNo;
	// 联系人身份证号码
	private String idCard;
	// 是否默认
	private Integer isDefault;
	// remark
	private String remark;
	// 自定义字段3
	private String aux3;
	// 自定义字段2
	private String aux2;
	// 自定义字段1
	private String aux1;
	// 自定义字段4
	private String aux4;
	// 自定义字段5
	private String aux5;
	// 状态
	private String status;
	// 控制字
	private String controlWord;
	// 公司（仓库）代码
	private String officeCode;
	// 并发访问控制
	private Long recVer;
	// 创建时间
	private Date createTime;
	// 创建人
	private String creator;
	// 修改人
	private String modifier;
	// 修改时间
	private Date modifyTime;

	/**
	 * Get 主键
	 */
	@Column(name = "BAS_CONTACT_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getBasContactUuid() {
		return basContactUuid;
	}

	/**
	 * Set 主键
	 */
	public void setBasContactUuid(String basContactUuid) {
		this.basContactUuid = basContactUuid;
		addValidField(FieldNames.basContactUuid);
	}

	/**
	 * Get basCustomerUuid
	 */
	@Column(name = "BAS_CUSTOMER_UUID")
	public String getBasCustomerUuid() {
		return basCustomerUuid;
	}

	/**
	 * Set basCustomerUuid
	 */
	public void setBasCustomerUuid(String basCustomerUuid) {
		this.basCustomerUuid = basCustomerUuid;
		addValidField(FieldNames.basCustomerUuid);
	}

	/**
	 * Get 主键ID
	 */
	@Column(name = "CONTACT_CODE")
	public String getContactCode() {
		return contactCode;
	}

	/**
	 * Set 主键ID
	 */
	public void setContactCode(String contactCode) {
		this.contactCode = contactCode;
		addValidField(FieldNames.contactCode);
	}

	/**
	 * Get contactName
	 */
	@Column(name = "CONTACT_NAME")
	public String getContactName() {
		return contactName;
	}

	/**
	 * Set contactName
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
		addValidField(FieldNames.contactName);
	}

	/**
	 * Get 联系人
	 */
	@Column(name = "CONTACT_NAME_EN")
	public String getContactNameEn() {
		return contactNameEn;
	}

	/**
	 * Set 联系人
	 */
	public void setContactNameEn(String contactNameEn) {
		this.contactNameEn = contactNameEn;
		addValidField(FieldNames.contactNameEn);
	}

	/**
	 * Get 邮件
	 */
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	/**
	 * Set 邮件
	 */
	public void setEmail(String email) {
		this.email = email;
		addValidField(FieldNames.email);
	}

	/**
	 * Get 电话
	 */
	@Column(name = "TEL")
	public String getTel() {
		return tel;
	}

	/**
	 * Set 电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
		addValidField(FieldNames.tel);
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
	 * Get 手机号码
	 */
	@Column(name = "MOBILEPHONE")
	public String getMobilephone() {
		return mobilephone;
	}

	/**
	 * Set 手机号码
	 */
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
		addValidField(FieldNames.mobilephone);
	}

	/**
	 * Get 职位
	 */
	@Column(name = "POSITION")
	public String getPosition() {
		return position;
	}

	/**
	 * Set 职位
	 */
	public void setPosition(String position) {
		this.position = position;
		addValidField(FieldNames.position);
	}

	/**
	 * Get 生日
	 */
	@Column(name = "BIRTHDAY")
	public String getBirthday() {
		return birthday;
	}

	/**
	 * Set 生日
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
		addValidField(FieldNames.birthday);
	}

	/**
	 * Get 爱好
	 */
	@Column(name = "HOBBY")
	public String getHobby() {
		return hobby;
	}

	/**
	 * Set 爱好
	 */
	public void setHobby(String hobby) {
		this.hobby = hobby;
		addValidField(FieldNames.hobby);
	}

	/**
	 * Get MSN/QQ号码
	 */
	@Column(name = "MSN_QQ_NO")
	public String getMsnQqNo() {
		return msnQqNo;
	}

	/**
	 * Set MSN/QQ号码
	 */
	public void setMsnQqNo(String msnQqNo) {
		this.msnQqNo = msnQqNo;
		addValidField(FieldNames.msnQqNo);
	}

	/**
	 * Get 联系人身份证号码
	 */
	@Column(name = "ID_CARD")
	public String getIdCard() {
		return idCard;
	}

	/**
	 * Set 联系人身份证号码
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
		addValidField(FieldNames.idCard);
	}

	/**
	 * Get 是否默认
	 */
	@Column(name = "IS_DEFAULT")
	public Integer getIsDefault() {
		return isDefault;
	}

	/**
	 * Set 是否默认
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
		addValidField(FieldNames.isDefault);
	}

	/**
	 * Get remark
	 */
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	/**
	 * Set remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
		addValidField(FieldNames.remark);
	}

	/**
	 * Get 自定义字段3
	 */
	@Column(name = "AUX3")
	public String getAux3() {
		return aux3;
	}

	/**
	 * Set 自定义字段3
	 */
	public void setAux3(String aux3) {
		this.aux3 = aux3;
		addValidField(FieldNames.aux3);
	}

	/**
	 * Get 自定义字段2
	 */
	@Column(name = "AUX2")
	public String getAux2() {
		return aux2;
	}

	/**
	 * Set 自定义字段2
	 */
	public void setAux2(String aux2) {
		this.aux2 = aux2;
		addValidField(FieldNames.aux2);
	}

	/**
	 * Get 自定义字段1
	 */
	@Column(name = "AUX1")
	public String getAux1() {
		return aux1;
	}

	/**
	 * Set 自定义字段1
	 */
	public void setAux1(String aux1) {
		this.aux1 = aux1;
		addValidField(FieldNames.aux1);
	}

	/**
	 * Get 自定义字段4
	 */
	@Column(name = "AUX4")
	public String getAux4() {
		return aux4;
	}

	/**
	 * Set 自定义字段4
	 */
	public void setAux4(String aux4) {
		this.aux4 = aux4;
		addValidField(FieldNames.aux4);
	}

	/**
	 * Get 自定义字段5
	 */
	@Column(name = "AUX5")
	public String getAux5() {
		return aux5;
	}

	/**
	 * Set 自定义字段5
	 */
	public void setAux5(String aux5) {
		this.aux5 = aux5;
		addValidField(FieldNames.aux5);
	}

	/**
	 * Get 状态 ：Active - 有效； Cancel - 作废
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态 ：Active - 有效； Cancel - 作废
	 */
	public void setStatus(String status) {
		this.status = status;
		addValidField(FieldNames.status);
	}

	/**
	 * Get 控制字 ：默认0
	 */
	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	/**
	 * Set 控制字 ：默认0
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
	private String prrmaryKeyName="basContactUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
