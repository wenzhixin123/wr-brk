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
 * Model class for 巴枪版本更新记录表
 */
@Entity
@Table(name = "BAR_VERSION_UPDATE_INFO")
public class BarVersionUpdateInfoModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BarVersionUpdateInfo";

	public static final class FieldNames {
		/**
		 * 唯一主键
		 */
		public static final String barUuid = "barUuid";
		/**
		 * 仓库代码
		 */
		public static final String officeCode = "officeCode";
		/**
		 * 当前更新文件名称
		 */
		public static final String currentFileName = "currentFileName";
		/**
		 * 上一版本文件名称
		 */
		public static final String historyFileName = "historyFileName";
		/**
		 * 备注
		 */
		public static final String remark = "remark";
		/**
		 * 版本号控制
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

	//唯一主键
	private String barUuid;
	//仓库代码
	private String officeCode;
	//当前更新文件名称
	private String currentFileName;
	//上一版本文件名称
	private String historyFileName;
	//备注
	private String remark;
	//版本号控制
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
	 * Get 唯一主键
	 */
	@Column(name = "BAR_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getBarUuid() {
		return barUuid;
	}

	/**
	 * Set 唯一主键
	 */
	public void setBarUuid(String barUuid) {
		this.barUuid = barUuid;
		addValidField(FieldNames.barUuid);
	}

	/**
	 * Get 仓库代码
	 */
	@Column(name = "OFFICE_CODE")
	public String getOfficeCode() {
		return officeCode;
	}

	/**
	 * Set 仓库代码
	 */
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
		addValidField(FieldNames.officeCode);
	}

	/**
	 * Get 当前更新文件名称
	 */
	@Column(name = "CURRENT_FILE_NAME")
	public String getCurrentFileName() {
		return currentFileName;
	}

	/**
	 * Set 当前更新文件名称
	 */
	public void setCurrentFileName(String currentFileName) {
		this.currentFileName = currentFileName;
		addValidField(FieldNames.currentFileName);
	}

	/**
	 * Get 上一版本文件名称
	 */
	@Column(name = "HISTORY_FILE_NAME")
	public String getHistoryFileName() {
		return historyFileName;
	}

	/**
	 * Set 上一版本文件名称
	 */
	public void setHistoryFileName(String historyFileName) {
		this.historyFileName = historyFileName;
		addValidField(FieldNames.historyFileName);
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
	 * Get 版本号控制
	 */
	@Column(name = "REC_VER")
	@Version
	public Long getRecVer() {
		return recVer;
	}

	/**
	 * Set 版本号控制
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
	private String prrmaryKeyName="barUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
	
}
