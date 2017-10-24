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
 * Model class for 文件管理
 */
@Entity
@Table(name = "BAS_FILE_MANAGE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasFileManageModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasFileManage";

	public static final class FieldNames {
		/**
		 * 文件UUID
		 */
		public static final String basFileManageUuid = "basFileManageUuid";
		/**
		 * 文件名代码（序列号）
		 */
		public static final String fileCode = "fileCode";
		/**
		 * 文件名称
		 */
		public static final String fileName = "fileName";
		/**
		 * 英文名
		 */
		public static final String fileNameEn = "fileNameEn";
		/**
		 * 文档类型
		 */
		public static final String fileType = "fileType";
		/**
		 * 文件路径
		 */
		public static final String filePath = "filePath";
		/**
		 * 打开方式
		 */
		public static final String openMethod = "openMethod";
		/**
		 * 业务调用UUID
		 */
		public static final String sourceUuid = "sourceUuid";
		/**
		 * 业务调用单号
		 */
		public static final String sourceNo = "sourceNo";
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

	// 文件UUID
	private String basFileManageUuid;
	// 文件名代码（序列号）
	private String fileCode;
	// 文件名称
	private String fileName;
	// 英文名
	private String fileNameEn;
	// 文档类型
	private String fileType;
	// 文件路径
	private String filePath;
	// 打开方式
	private String openMethod;
	// 业务调用UUID
	private String sourceUuid;
	// 业务调用单号
	private String sourceNo;
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
	 * Get 文件UUID
	 */
	@Column(name = "BAS_FILE_MANAGE_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getBasFileManageUuid() {
		return basFileManageUuid;
	}

	/**
	 * Set 文件UUID
	 */
	public void setBasFileManageUuid(String basFileManageUuid) {
		this.basFileManageUuid = basFileManageUuid;
		addValidField(FieldNames.basFileManageUuid);
	}

	/**
	 * Get 文件名代码（序列号）
	 */
	@Column(name = "FILE_CODE")
	public String getFileCode() {
		return fileCode;
	}

	/**
	 * Set 文件名代码（序列号）
	 */
	public void setFileCode(String fileCode) {
		this.fileCode = fileCode;
		addValidField(FieldNames.fileCode);
	}

	/**
	 * Get 文件名称
	 */
	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	/**
	 * Set 文件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
		addValidField(FieldNames.fileName);
	}

	/**
	 * Get 英文名
	 */
	@Column(name = "FILE_NAME_EN")
	public String getFileNameEn() {
		return fileNameEn;
	}

	/**
	 * Set 英文名
	 */
	public void setFileNameEn(String fileNameEn) {
		this.fileNameEn = fileNameEn;
		addValidField(FieldNames.fileNameEn);
	}

	/**
	 * Get 文档类型
	 */
	@Column(name = "FILE_TYPE")
	public String getFileType() {
		return fileType;
	}

	/**
	 * Set 文档类型
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
		addValidField(FieldNames.fileType);
	}

	/**
	 * Get 文件路径
	 */
	@Column(name = "FILE_PATH")
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Set 文件路径
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		addValidField(FieldNames.filePath);
	}

	/**
	 * Get 打开方式
	 */
	@Column(name = "OPEN_METHOD")
	public String getOpenMethod() {
		return openMethod;
	}

	/**
	 * Set 打开方式
	 */
	public void setOpenMethod(String openMethod) {
		this.openMethod = openMethod;
		addValidField(FieldNames.openMethod);
	}

	/**
	 * Get 业务调用UUID
	 */
	@Column(name = "SOURCE_UUID")
	public String getSourceUuid() {
		return sourceUuid;
	}

	/**
	 * Set 业务调用UUID
	 */
	public void setSourceUuid(String sourceUuid) {
		this.sourceUuid = sourceUuid;
		addValidField(FieldNames.sourceUuid);
	}

	/**
	 * Get 业务调用单号
	 */
	@Column(name = "SOURCE_NO")
	public String getSourceNo() {
		return sourceNo;
	}

	/**
	 * Set 业务调用单号
	 */
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
		addValidField(FieldNames.sourceNo);
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
	private String prrmaryKeyName="basFileManageUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
