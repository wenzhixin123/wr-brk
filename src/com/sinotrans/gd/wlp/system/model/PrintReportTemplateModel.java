package com.sinotrans.gd.wlp.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

import com.sinotrans.framework.core.model.BaseModelClass;
import com.sinotrans.framework.core.model.OperationLog;

/**
 * Model class for 打印模板
 */
@Entity
@Table(name = "REPORT_TEMPLATE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PrintReportTemplateModel extends BaseModelClass implements
		OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "ReportTemplate";

	public static final class FieldNames {
		/**
		 * 主键
		 */
		public static final String reportTemplateUuid = "reportTemplateUuid";
		/**
		 * 模板代码
		 */
		public static final String templateCode = "templateCode";
		/**
		 * 模板类型
		 */
		public static final String templateType = "templateType";
		/**
		 * 模板名称
		 */
		public static final String templateName = "templateName";
		/**
		 * 模板英文描述
		 */
		public static final String templateNameEn = "templateNameEn";
		/**
		 * 模板文件名称
		 */
		public static final String fileName = "fileName";
		/**
		 * 模板版本号
		 */
		public static final String fileVersion = "fileVersion";
		/**
		 * 模板内容
		 */
		public static final String templateContent = "templateContent";
		/**
		 * 编译完成的模板内容
		 */
		public static final String compileTemplate = "compileTemplate";
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
		/**
		 * 客户代码
		 */
		public static final String customerCode = "customerCode";
		/**
		 * 模板分类
		 */
		public static final String templateCategory = "templateCategory";
	}

	// 主键
	private String reportTemplateUuid;
	// 模板代码
	private String templateCode;
	// 模板类型
	private String templateType;
	// 模板名称
	private String templateName;
	// 模板英文描述
	private String templateNameEn;
	// 模板文件名称
	private String fileName;
	// 模板版本号
	private String fileVersion;
	// 模板内容
	private byte[] templateContent;
	// 编译完成的模板内容
	private byte[] compileTemplate;
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
	// 客户代码
	private String customerCode;
	// 模板分类
	private String templateCategory;

	@Column(name = "CUSTOMER_CODE")
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
		addValidField(FieldNames.customerCode);
	}

	/**
	 * Get 主键
	 */
	@Column(name = "REPORT_TEMPLATE_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getReportTemplateUuid() {
		return reportTemplateUuid;
	}

	/**
	 * Set 主键
	 */
	public void setReportTemplateUuid(String reportTemplateUuid) {
		this.reportTemplateUuid = reportTemplateUuid;
		addValidField(FieldNames.reportTemplateUuid);
	}

	/**
	 * Get 模板代码 ：'PT'+ yymmdd + 序号（5）
	 */
	@Column(name = "TEMPLATE_CODE")
	public String getTemplateCode() {
		return templateCode;
	}

	/**
	 * Set 模板代码 ：'PT'+ yymmdd + 序号（5）
	 */
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
		addValidField(FieldNames.templateCode);
	}

	/**
	 * Get 模板类型
	 */
	@Column(name = "TEMPLATE_TYPE")
	public String getTemplateType() {
		return templateType;
	}

	/**
	 * Set 模板类型
	 */
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
		addValidField(FieldNames.templateType);
	}

	/**
	 * Get 模板名称
	 */
	@Column(name = "TEMPLATE_NAME")
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * Set 模板名称
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
		addValidField(FieldNames.templateName);
	}

	/**
	 * Get 模板英文描述
	 */
	@Column(name = "TEMPLATE_NAME_EN")
	public String getTemplateNameEn() {
		return templateNameEn;
	}

	/**
	 * Set 模板英文描述
	 */
	public void setTemplateNameEn(String templateNameEn) {
		this.templateNameEn = templateNameEn;
		addValidField(FieldNames.templateNameEn);
	}

	/**
	 * Get 模板文件名称
	 */
	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	/**
	 * Set 模板文件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
		addValidField(FieldNames.fileName);
	}

	/**
	 * Get 模板版本号
	 */
	@Column(name = "FILE_VERSION")
	public String getFileVersion() {
		return fileVersion;
	}

	/**
	 * Set 模板版本号
	 */
	public void setFileVersion(String fileVersion) {
		this.fileVersion = fileVersion;
		addValidField(FieldNames.fileVersion);
	}

	/**
	 * Get 模板内容
	 */
	@Column(name = "TEMPLATE_CONTENT")
	@Type(type = "org.springframework.orm.hibernate3.support.BlobByteArrayType")
	public byte[] getTemplateContent() {
		return templateContent;
	}

	/**
	 * Set 模板内容
	 */
	public void setTemplateContent(byte[] templateContent) {
		this.templateContent = templateContent;
		addValidField(FieldNames.templateContent);
	}
	
	/**
	 * Get 编译完成的模板
	 */
	@Column(name = "COMPILE_TEMPLATE")
	@Type(type = "org.springframework.orm.hibernate3.support.BlobByteArrayType")
	public byte[] getCompileTemplate() {
		return compileTemplate;
	}

	public void setCompileTemplate(byte[] compileTemplate) {
		this.compileTemplate = compileTemplate;
		addValidField(FieldNames.compileTemplate);
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
	 * Get 状态 ：ACTIVE - 有效； Cancel - 作废
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态 ：ACTIVE - 有效； Cancel - 作废
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
	 * Get 模板分类
	 */
	@Column(name = "template_category")
	public String getTemplateCategory() {
		return templateCategory;
	}

	/**
	 * Set 模板分类
	 */
	public void setTemplateCategory(String templateCategory) {
		this.templateCategory = templateCategory;
		addValidField(FieldNames.templateCategory);
	}

	/**
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="customerCode";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
