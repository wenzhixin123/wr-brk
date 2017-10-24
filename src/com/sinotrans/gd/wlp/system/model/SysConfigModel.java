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
 * Model class for SysConfig
 */
@Entity
@Table(name = "SYS_CONFIG")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysConfigModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "SysConfig";

	public static final class FieldNames {
		/**
		 * sysConfigUuid
		 */
		public static final String sysConfigUuid = "sysConfigUuid";
		/**
		 * 配置项
		 */
		public static final String configItem = "configItem";
		/**
		 * 配置值
		 */
		public static final String configValue = "configValue";
		/**
		 * 配置英文值
		 */
		public static final String configValueEn = "configValueEn";
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

	// sysConfigUuid
	private String sysConfigUuid;
	// 配置项
	private String configItem;
	// 配置值
	private String configValue;
	// 配置英文值
	private String configValueEn;
	// 备注
	private String remark;
	// 状态
	private String status;
	// 控制字
	private String controlWord;
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

	/**
	 * Get sysConfigUuid
	 */
	@Column(name = "SYS_CONFIG_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getSysConfigUuid() {
		return sysConfigUuid;
	}

	/**
	 * Set sysConfigUuid
	 */
	public void setSysConfigUuid(String sysConfigUuid) {
		this.sysConfigUuid = sysConfigUuid;
		addValidField(FieldNames.sysConfigUuid);
	}

	/**
	 * Get 配置项
	 */
	@Column(name = "CONFIG_ITEM")
	public String getConfigItem() {
		return configItem;
	}

	/**
	 * Set 配置项
	 */
	public void setConfigItem(String configItem) {
		this.configItem = configItem;
		addValidField(FieldNames.configItem);
	}

	/**
	 * Get 配置值
	 */
	@Column(name = "CONFIG_VALUE")
	public String getConfigValue() {
		return configValue;
	}

	/**
	 * Set 配置值
	 */
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
		addValidField(FieldNames.configValue);
	}

	/**
	 * Get 配置英文值
	 */
	@Column(name = "CONFIG_VALUE_EN")
	public String getConfigValueEn() {
		return configValueEn;
	}

	/**
	 * Set 配置英文值
	 */
	public void setConfigValueEn(String configValueEn) {
		this.configValueEn = configValueEn;
		addValidField(FieldNames.configValueEn);
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
	private String prrmaryKeyName="sysConfigUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
