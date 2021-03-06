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
 * Model class for 拦截订单
 */
@Entity
@Table(name = "HOLD_ORDER")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class HoldOrderModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "HoldOrder";

	public static final class FieldNames {
		/**
		 * 主键
		 */
		public static final String holdOrderUuid = "holdOrderUuid";
		/**
		 * 订单类型
		 */
		public static final String orderType = "orderType";
		/**
		 * 作业时间
		 */
		public static final String orderDate = "orderDate";
		/**
		 * 作业描述
		 */
		public static final String orderDesc = "orderDesc";
		/**
		 * 拦截订单号
		 */
		public static final String orderNo = "orderNo";
		/**
		 * 订单号UUID
		 */
		public static final String orderUuid = "orderUuid";
		/**
		 * 状态
		 */
		public static final String status = "status";
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
		/**
		 * 客户代码
		 */
		public static final String customerCode = "customerCode";
	}

	// 主键
	private String holdOrderUuid;
	// 订单类型
	private String orderType;
	// 作业时间
	private Date orderDate;
	// 作业描述
	private String orderDesc;
	// 拦截订单号
	private String orderNo;
	// 订单号UUID
	private String orderUuid;
	// 状态
	private String status;
	// 控制字（默认为0）
	private String controlWord;
	// 备注
	private String remark;
	// 自定义字段1
	private String aux1;
	// 自定义字段2
	private String aux2;
	// 自定义字段3
	private String aux3;
	// 自定义字段4
	private String aux4;
	// 自定义字段5
	private String aux5;
	// 公司（仓库）代码
	private String officeCode;
	// 并发访问控制
	private Long recVer;
	// 创建人
	private String creator;
	// 创建时间
	private Date createTime;
	// 修改人
	private String modifier;
	// 修改时间
	private Date modifyTime;

	// 客户代码
	private String customerCode;

	/**
	 * Get 主键
	 */
	@Column(name = "HOLD_ORDER_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getHoldOrderUuid() {
		return holdOrderUuid;
	}

	/**
	 * Set 主键
	 */
	public void setHoldOrderUuid(String holdOrderUuid) {
		this.holdOrderUuid = holdOrderUuid;
		addValidField(FieldNames.holdOrderUuid);
	}

	/**
	 * Get 订单类型 ：
	 */
	@Column(name = "ORDER_TYPE")
	public String getOrderType() {
		return orderType;
	}

	/**
	 * Set 订单类型 ：
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
		addValidField(FieldNames.orderType);
	}

	/**
	 * Get 作业时间
	 */
	@Column(name = "ORDER_DATE")
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * Set 作业时间
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
		addValidField(FieldNames.orderDate);
	}

	/**
	 * Get 作业描述
	 */
	@Column(name = "ORDER_DESC")
	public String getOrderDesc() {
		return orderDesc;
	}

	/**
	 * Set 作业描述
	 */
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
		addValidField(FieldNames.orderDesc);
	}

	/**
	 * Get 拦截订单号
	 */
	@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * Set 拦截订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
		addValidField(FieldNames.orderNo);
	}

	/**
	 * Get 订单号UUID
	 */
	@Column(name = "ORDER_UUID")
	public String getOrderUuid() {
		return orderUuid;
	}

	/**
	 * Set 订单号UUID
	 */
	public void setOrderUuid(String orderUuid) {
		this.orderUuid = orderUuid;
		addValidField(FieldNames.orderUuid);
	}

	/**
	 * Get 状态 ：Active - 有效； Cancel - 作废；Pending － 草稿
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态 ：Active - 有效； Cancel - 作废；Pending － 草稿
	 */
	public void setStatus(String status) {
		this.status = status;
		addValidField(FieldNames.status);
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

	@Column(name = "CUSTOMER_CODE")
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	
	/**
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="holdOrderUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
