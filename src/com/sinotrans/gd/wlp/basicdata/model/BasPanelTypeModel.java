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
 * Model class for 托盘类型
 */
@Entity
@Table(name = "BAS_PANEL_TYPE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasPanelTypeModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasPanelType";

	public static final class FieldNames {
		/**
		 * 托盘类型UUID
		 */
		public static final String basPanelTypeUuid = "basPanelTypeUuid";
		/**
		 * 托盘类型代码
		 */
		public static final String panelTypeCode = "panelTypeCode";
		/**
		 * 托盘类型描述
		 */
		public static final String panelTypeDesc = "panelTypeDesc";
		/**
		 * 长
		 */
		public static final String length = "length";
		/**
		 * 宽
		 */
		public static final String width = "width";
		/**
		 * 高
		 */
		public static final String height = "height";
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

	// 托盘类型UUID
	private String basPanelTypeUuid;
	// 托盘类型代码
	private String panelTypeCode;
	// 托盘类型描述
	private String panelTypeDesc;
	// 长
	private Double length;
	// 宽
	private Double width;
	// 高
	private Double height;
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
	 * Get 托盘类型UUID
	 */
	@Column(name = "BAS_PANEL_TYPE_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
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
	 * Get 托盘类型代码
	 */
	@Column(name = "PANEL_TYPE_CODE")
	public String getPanelTypeCode() {
		return panelTypeCode;
	}

	/**
	 * Set 托盘类型代码
	 */
	public void setPanelTypeCode(String panelTypeCode) {
		this.panelTypeCode = panelTypeCode;
		addValidField(FieldNames.panelTypeCode);
	}

	/**
	 * Get 托盘类型描述
	 */
	@Column(name = "PANEL_TYPE_DESC")
	public String getPanelTypeDesc() {
		return panelTypeDesc;
	}

	/**
	 * Set 托盘类型描述
	 */
	public void setPanelTypeDesc(String panelTypeDesc) {
		this.panelTypeDesc = panelTypeDesc;
		addValidField(FieldNames.panelTypeDesc);
	}

	/**
	 * Get 长
	 */
	@Column(name = "LENGTH")
	public Double getLength() {
		return length;
	}

	/**
	 * Set 长
	 */
	public void setLength(Double length) {
		this.length = length;
		addValidField(FieldNames.length);
	}

	/**
	 * Get 宽
	 */
	@Column(name = "WIDTH")
	public Double getWidth() {
		return width;
	}

	/**
	 * Set 宽
	 */
	public void setWidth(Double width) {
		this.width = width;
		addValidField(FieldNames.width);
	}

	/**
	 * Get 高
	 */
	@Column(name = "HEIGHT")
	public Double getHeight() {
		return height;
	}

	/**
	 * Set 高
	 */
	public void setHeight(Double height) {
		this.height = height;
		addValidField(FieldNames.height);
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
	private String prrmaryKeyName="basPanelTypeUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
