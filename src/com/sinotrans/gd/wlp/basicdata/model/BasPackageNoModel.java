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
 * Model class for 箱名信息表
 */
@Entity
@Table(name = "BAS_PACKAGE_NO")
public class BasPackageNoModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasPackageNo";

	public static final class FieldNames {
		/**
		 * 箱名号UUID
		 */
		public static final String packageUuid = "packageUuid";
		/**
		 * 箱名号
		 */
		public static final String packageNo = "packageNo";
		/**
		 * 箱名描述
		 */
		public static final String packageDesc = "packageDesc";
		/**
		 * 客户代码
		 */
		public static final String customerCode = "customerCode";
		/**
		 * 客户名称
		 */
		public static final String customerName = "customerName";
		/**
		 * 控制字
		 */
		public static final String controlWord = "controlWord";
		/**
		 * 操作状态
		 */
		public static final String status = "status";
		/**
		 * 备注
		 */
		public static final String remark = "remark";
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

	//箱名号UUID
	private String packageUuid;
	//箱名号
	private String packageNo;
	//箱名描述
	private String packageDesc;
	//客户代码
	private String customerCode;
	//客户名称
	private String customerName;
	//控制字
	private String controlWord;
	//操作状态
	private String status;
	//备注
	private String remark;
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
	 * Get 箱名号UUID
	 */
	@Column(name = "PACKAGE_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getPackageUuid() {
		return packageUuid;
	}

	/**
	 * Set 箱名号UUID
	 */
	public void setPackageUuid(String packageUuid) {
		this.packageUuid = packageUuid;
		addValidField(FieldNames.packageUuid);
	}

	/**
	 * Get 箱名号
	 */
	@Column(name = "PACKAGE_NO")
	public String getPackageNo() {
		return packageNo;
	}

	/**
	 * Set 箱名号
	 */
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
		addValidField(FieldNames.packageNo);
	}

	/**
	 * Get 箱名描述
	 */
	@Column(name = "PACKAGE_DESC")
	public String getPackageDesc() {
		return packageDesc;
	}

	/**
	 * Set 箱名描述
	 */
	public void setPackageDesc(String packageDesc) {
		this.packageDesc = packageDesc;
		addValidField(FieldNames.packageDesc);
	}

	/**
	 * Get 客户代码
	 */
	@Column(name = "CUSTOMER_CODE")
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * Set 客户代码
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
		addValidField(FieldNames.customerCode);
	}

	/**
	 * Get 客户名称
	 */
	@Column(name = "CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Set 客户名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
		addValidField(FieldNames.customerName);
	}

	/**
	 * Get 控制字
	 */
	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	/**
	 * Set 控制字
	 */
	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField(FieldNames.controlWord);
	}

	/**
	 * Get 操作状态
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 操作状态
	 */
	public void setStatus(String status) {
		this.status = status;
		addValidField(FieldNames.status);
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
	@Column(name = "CREATE_TIME", updatable=false)
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
	private String prrmaryKeyName="packageUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
