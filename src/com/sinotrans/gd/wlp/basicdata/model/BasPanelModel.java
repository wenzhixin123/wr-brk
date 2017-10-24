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
 * Model class for 托盘信息
 */
@Entity
@Table(name = "BAS_PANEL")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasPanelModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasPanel";

	public static final class FieldNames {
		/**
		 * 托盘UUID
		 */
		public static final String basPanelUuid = "basPanelUuid";
		/**
		 * 托盘类型UUID
		 */
		public static final String basPanelTypeUuid = "basPanelTypeUuid";
		/**
		 * 托盘号
		 */
		public static final String panelPackageNo = "panelPackageNo";
		/**
		 * 货盘描述
		 */
		public static final String panelPackageDesc = "panelPackageDesc";
		/**
		 * 客户代码
		 */
		public static final String customerCode = "customerCode";
		/**
		 * 客户名称
		 */
		public static final String customerName = "customerName";
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
		/**
		 * 状态
		 */
		public static final String status = "status";
		/**
		 * 控制字
		 */
		public static final String controlWord = "controlWord";
	}

	// 托盘UUID
	private String basPanelUuid;
	// 托盘类型UUID
	private String basPanelTypeUuid;
	// 托盘号
	private String panelPackageNo;
	// 货盘描述
	private String panelPackageDesc;
	// 客户代码
	private String customerCode;
	// 客户名称
	private String customerName;
	// 备注
	private String remark;
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
	// 状态
	private String status;
	// 控制字
	private String controlWord;

	/**
	 * Get 托盘UUID
	 */
	@Column(name = "BAS_PANEL_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getBasPanelUuid() {
		return basPanelUuid;
	}

	/**
	 * Set 托盘UUID
	 */
	public void setBasPanelUuid(String basPanelUuid) {
		this.basPanelUuid = basPanelUuid;
		addValidField(FieldNames.basPanelUuid);
	}

	/**
	 * Get 托盘类型UUID
	 */
	@Column(name = "BAS_PANEL_TYPE_UUID")
	public String getBasPanelTypeUuid() {
		return basPanelTypeUuid;
	}

	/**
	 * Set 托盘类型UUID
	 */
	public void setBasPanelTypeUuid(String basPanelTypeUuid) {
		this.basPanelTypeUuid = basPanelTypeUuid;
		addValidField(FieldNames.basPanelTypeUuid);
	}

	/**
	 * Get 托盘号
	 */
	@Column(name = "PANEL_PACKAGE_NO")
	public String getPanelPackageNo() {
		return panelPackageNo;
	}

	/**
	 * Set 托盘号
	 */
	public void setPanelPackageNo(String panelPackageNo) {
		this.panelPackageNo = panelPackageNo;
		addValidField(FieldNames.panelPackageNo);
	}

	/**
	 * Get 货盘描述
	 */
	@Column(name = "PANEL_PACKAGE_DESC")
	public String getPanelPackageDesc() {
		return panelPackageDesc;
	}

	/**
	 * Set 货盘描述
	 */
	public void setPanelPackageDesc(String panelPackageDesc) {
		this.panelPackageDesc = panelPackageDesc;
		addValidField(FieldNames.panelPackageDesc);
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
	 * Get 状态
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态
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
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="basPanelUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}


}
