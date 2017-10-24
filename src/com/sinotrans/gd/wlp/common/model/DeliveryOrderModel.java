package com.sinotrans.gd.wlp.common.model;

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
 * Model class for 派送单
 */
@Entity
@Table(name = "DELIVERY_ORDER")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class DeliveryOrderModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "DeliveryOrder";

	public static final class FieldNames {
		/**
		 * 主键
		 */
		public static final String deliveryOrderUuid = "deliveryOrderUuid";
		/**
		 * 委托单UUID号
		 */
		public static final String submitOrderUuid = "submitOrderUuid";
		/**
		 * 派送单号
		 */
		public static final String deliveryOrderNo = "deliveryOrderNo";
		/**
		 * 出运时间
		 */
		public static final String deliveryDate = "deliveryDate";
		/**
		 * 运输公司代码
		 */
		public static final String expressCode = "expressCode";
		/**
		 * 运输公司名称
		 */
		public static final String expressName = "expressName";
		/**
		 * 车牌号码
		 */
		public static final String tractorNo = "tractorNo";
		/**
		 * 收货人姓名
		 */
		public static final String contactName = "contactName";
		/**
		 * 收货人电话
		 */
		public static final String contactTel = "contactTel";
		/**
		 * 收货人移动电话
		 */
		public static final String mobile = "mobile";
		/**
		 * 收货人所在省
		 */
		public static final String prov = "prov";
		/**
		 * 收货人所在市
		 */
		public static final String city = "city";
		/**
		 * 收货人所在县（区）
		 */
		public static final String district = "district";
		/**
		 * 收货人详细地址
		 */
		public static final String address = "address";
		/**
		 * 邮编
		 */
		public static final String postCode = "postCode";
		/**
		 * 优先排列序号
		 */
		public static final String seqNo = "seqNo";
		/**
		 * 是否自动审核
		 */
		public static final String isAuto = "isAuto";
		/**
		 * 是否确认
		 */
		public static final String isConfirmed = "isConfirmed";
		/**
		 * 完成日期
		 */
		public static final String confirmedDate = "confirmedDate";
		/**
		 * 状态
		 */
		public static final String status = "status";
		/**
		 * 派送状态
		 */
		public static final String deliveryType = "deliveryType";
		/**
		 * 控制字（默认为0）
		 */
		public static final String controlWord = "controlWord";
		/**
		 * 备注
		 */
		public static final String remark = "remark";
		/**
		 * 自定义字段1
		 */
		public static final String aux1 = "aux1";
		/**
		 * 自定义字段2
		 */
		public static final String aux2 = "aux2";
		/**
		 * 自定义字段3
		 */
		public static final String aux3 = "aux3";
		/**
		 * 自定义字段4
		 */
		public static final String aux4 = "aux4";
		/**
		 * 自定义字段5
		 */
		public static final String aux5 = "aux5";
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

	//主键
	private String deliveryOrderUuid;
	//委托单UUID号
	private String submitOrderUuid;
	//派送单号
	private String deliveryOrderNo;
	//出运时间
	private Date deliveryDate;
	//运输公司代码
	private String expressCode;
	//运输公司名称
	private String expressName;
	//车牌号码
	private String tractorNo;
	//收货人姓名
	private String contactName;
	//收货人电话
	private String contactTel;
	//收货人移动电话
	private String mobile;
	//收货人所在省
	private String prov;
	//收货人所在市
	private String city;
	//收货人所在县（区）
	private String district;
	//收货人详细地址
	private String address;
	//邮编
	private String postCode;
	//优先排列序号
	private Long seqNo;
	//是否自动审核
	private String isAuto;
	//是否确认
	private String isConfirmed;
	//完成日期
	private Date confirmedDate;
	//状态
	private String status;
	//派送状态
	private String deliveryType;
	//控制字（默认为0）
	private String controlWord;
	//备注
	private String remark;
	//自定义字段1
	private String aux1;
	//自定义字段2
	private String aux2;
	//自定义字段3
	private String aux3;
	//自定义字段4
	private String aux4;
	//自定义字段5
	private String aux5;
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
	 * Get 主键
	 */
	@Column(name = "DELIVERY_ORDER_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getDeliveryOrderUuid() {
		return deliveryOrderUuid;
	}

	/**
	 * Set 主键
	 */
	public void setDeliveryOrderUuid(String deliveryOrderUuid) {
		this.deliveryOrderUuid = deliveryOrderUuid;
		addValidField(FieldNames.deliveryOrderUuid);
	}

	/**
	 * Get 委托单UUID号
	 */
	@Column(name = "SUBMIT_ORDER_UUID")
	public String getSubmitOrderUuid() {
		return submitOrderUuid;
	}

	/**
	 * Set 委托单UUID号
	 */
	public void setSubmitOrderUuid(String submitOrderUuid) {
		this.submitOrderUuid = submitOrderUuid;
		addValidField(FieldNames.submitOrderUuid);
	}

	/**
	 * Get 派送单号
	 */
	@Column(name = "DELIVERY_ORDER_NO")
	public String getDeliveryOrderNo() {
		return deliveryOrderNo;
	}

	/**
	 * Set 派送单号
	 */
	public void setDeliveryOrderNo(String deliveryOrderNo) {
		this.deliveryOrderNo = deliveryOrderNo;
		addValidField(FieldNames.deliveryOrderNo);
	}

	/**
	 * Get 出运时间
	 */
	@Column(name = "DELIVERY_DATE")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * Set 出运时间
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
		addValidField(FieldNames.deliveryDate);
	}

	/**
	 * Get 运输公司代码
	 */
	@Column(name = "EXPRESS_CODE")
	public String getExpressCode() {
		return expressCode;
	}

	/**
	 * Set 运输公司代码
	 */
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
		addValidField(FieldNames.expressCode);
	}

	/**
	 * Get 运输公司名称
	 */
	@Column(name = "EXPRESS_NAME")
	public String getExpressName() {
		return expressName;
	}

	/**
	 * Set 运输公司名称
	 */
	public void setExpressName(String expressName) {
		this.expressName = expressName;
		addValidField(FieldNames.expressName);
	}

	/**
	 * Get 车牌号码
	 */
	@Column(name = "TRACTOR_NO")
	public String getTractorNo() {
		return tractorNo;
	}

	/**
	 * Set 车牌号码
	 */
	public void setTractorNo(String tractorNo) {
		this.tractorNo = tractorNo;
		addValidField(FieldNames.tractorNo);
	}

	/**
	 * Get 收货人姓名
	 */
	@Column(name = "CONTACT_NAME")
	public String getContactName() {
		return contactName;
	}

	/**
	 * Set 收货人姓名
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
		addValidField(FieldNames.contactName);
	}

	/**
	 * Get 收货人电话
	 */
	@Column(name = "CONTACT_TEL")
	public String getContactTel() {
		return contactTel;
	}

	/**
	 * Set 收货人电话
	 */
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
		addValidField(FieldNames.contactTel);
	}

	/**
	 * Get 收货人移动电话
	 */
	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	/**
	 * Set 收货人移动电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
		addValidField(FieldNames.mobile);
	}

	/**
	 * Get 收货人所在省
	 */
	@Column(name = "PROV")
	public String getProv() {
		return prov;
	}

	/**
	 * Set 收货人所在省
	 */
	public void setProv(String prov) {
		this.prov = prov;
		addValidField(FieldNames.prov);
	}

	/**
	 * Get 收货人所在市
	 */
	@Column(name = "CITY")
	public String getCity() {
		return city;
	}

	/**
	 * Set 收货人所在市
	 */
	public void setCity(String city) {
		this.city = city;
		addValidField(FieldNames.city);
	}

	/**
	 * Get 收货人所在县（区）
	 */
	@Column(name = "DISTRICT")
	public String getDistrict() {
		return district;
	}

	/**
	 * Set 收货人所在县（区）
	 */
	public void setDistrict(String district) {
		this.district = district;
		addValidField(FieldNames.district);
	}

	/**
	 * Get 收货人详细地址
	 */
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	/**
	 * Set 收货人详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
		addValidField(FieldNames.address);
	}

	/**
	 * Get 邮编
	 */
	@Column(name = "POST_CODE")
	public String getPostCode() {
		return postCode;
	}

	/**
	 * Set 邮编
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
		addValidField(FieldNames.postCode);
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
	 * Get 是否确认
	 * ：Y - - 确认
	 */
	@Column(name = "IS_CONFIRMED")
	public String getIsConfirmed() {
		return isConfirmed;
	}

	/**
	 * Set 是否确认
	 * ：Y - - 确认
	 */
	public void setIsConfirmed(String isConfirmed) {
		this.isConfirmed = isConfirmed;
		addValidField(FieldNames.isConfirmed);
	}

	/**
	 * Get 完成日期
	 */
	@Column(name = "CONFIRMED_DATE")
	public Date getConfirmedDate() {
		return confirmedDate;
	}

	/**
	 * Set 完成日期
	 */
	public void setConfirmedDate(Date confirmedDate) {
		this.confirmedDate = confirmedDate;
		addValidField(FieldNames.confirmedDate);
	}

	/**
	 * Get 状态
	 * ：Active - 有效； Cancel - 作废；Pending － 草稿
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态
	 * ：Active - 有效； Cancel - 作废；Pending － 草稿
	 */
	public void setStatus(String status) {
		this.status = status;
		addValidField(FieldNames.status);
	}

	/**
	 * Get 派送状态
	 * ：CONSIGN - 发货、SIGN-签收成功、FAILED-拒签、CONSIGNFAILED-发货失败
	 */
	@Column(name = "DELIVERY_TYPE")
	public String getDeliveryType() {
		return deliveryType;
	}

	/**
	 * Set 派送状态
	 * ：CONSIGN - 发货、SIGN-签收成功、FAILED-拒签、CONSIGNFAILED-发货失败
	 */
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
		addValidField(FieldNames.deliveryType);
	}

	/**
	 * Get 控制字（默认为0）
	 */
	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	/**
	 * Set 控制字（默认为0）
	 */
	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField(FieldNames.controlWord);
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
	private String prrmaryKeyName="deliveryOrderUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
