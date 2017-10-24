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
 * Model class for 应急收、发货单号
 */
@Entity
@Table(name = "BAS_ORDER_NO")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasOrderNoModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasOrderNo";

	public static final class FieldNames {
		/**
		 * UUID主键
		 */
		public static final String basOrderNoUuid = "basOrderNoUuid";
		/**
		 * 应急单号
		 */
		public static final String orderNo = "orderNo";
		/**
		 * 类型
		 */
		public static final String orderType = "orderType";
		/**
		 * 描述
		 */
		public static final String orderDesc = "orderDesc";
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

	//UUID主键
	private String basOrderNoUuid;
	//应急单号
	private String orderNo;
	//类型
	private String orderType;
	//描述
	private String orderDesc;
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
	 * Get UUID主键
	 */
	@Column(name = "BAS_ORDER_NO_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getBasOrderNoUuid() {
		return basOrderNoUuid;
	}

	/**
	 * Set UUID主键
	 */
	public void setBasOrderNoUuid(String basOrderNoUuid) {
		this.basOrderNoUuid = basOrderNoUuid;
		addValidField(FieldNames.basOrderNoUuid);
	}

	/**
	 * Get 应急单号
	 */
	@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * Set 应急单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
		addValidField(FieldNames.orderNo);
	}

	/**
	 * Get 类型
	 */
	@Column(name = "ORDER_TYPE")
	public String getOrderType() {
		return orderType;
	}

	/**
	 * Set 类型
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
		addValidField(FieldNames.orderType);
	}

	/**
	 * Get 描述
	 */
	@Column(name = "ORDER_DESC")
	public String getOrderDesc() {
		return orderDesc;
	}

	/**
	 * Set 描述
	 */
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
		addValidField(FieldNames.orderDesc);
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
	private String prrmaryKeyName="basLotStockUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
