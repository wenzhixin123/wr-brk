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
 * Model class for 信息发布表
 */
@Entity
@Table(name = "SYS_NEWS")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysNewsModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "SysNews";

	public static final class FieldNames {
		/**
		 * sysNewsUuid
		 */
		public static final String sysNewsUuid = "sysNewsUuid";
		/**
		 * 序号
		 */
		public static final String seqNo = "seqNo";
		/**
		 * 发布日期
		 */
		public static final String dateWork = "dateWork";
		/**
		 * 标题
		 */
		public static final String title = "title";
		/**
		 * 内容
		 */
		public static final String content = "content";
		/**
		 * 类型
		 */
		public static final String newsType = "newsType";
		/**
		 * 是否有附件
		 */
		public static final String ifFiles = "ifFiles";
		/**
		 * 经手人
		 */
		public static final String functionary = "functionary";
		/**
		 * 连接地址
		 */
		public static final String urlAddress = "urlAddress";
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

	//sysNewsUuid
	private String sysNewsUuid;
	//序号
	private Long seqNo;
	//发布日期
	private Date dateWork;
	//标题
	private String title;
	//内容
	private byte[] content;
	//类型
	private String newsType;
	//是否有附件
	private String ifFiles;
	//经手人
	private String functionary;
	//连接地址
	private String urlAddress;
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
	 * Get sysNewsUuid
	 */
	@Column(name = "SYS_NEWS_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getSysNewsUuid() {
		return sysNewsUuid;
	}

	/**
	 * Set sysNewsUuid
	 */
	public void setSysNewsUuid(String sysNewsUuid) {
		this.sysNewsUuid = sysNewsUuid;
		addValidField(FieldNames.sysNewsUuid);
	}

	/**
	 * Get 序号
	 */
	@Column(name = "SEQ_NO")
	public Long getSeqNo() {
		return seqNo;
	}

	/**
	 * Set 序号
	 */
	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
		addValidField(FieldNames.seqNo);
	}

	/**
	 * Get 发布日期
	 */
	@Column(name = "DATE_WORK")
	public Date getDateWork() {
		return dateWork;
	}

	/**
	 * Set 发布日期
	 */
	public void setDateWork(Date dateWork) {
		this.dateWork = dateWork;
		addValidField(FieldNames.dateWork);
	}

	/**
	 * Get 标题
	 */
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	/**
	 * Set 标题
	 */
	public void setTitle(String title) {
		this.title = title;
		addValidField(FieldNames.title);
	}

	/**
	 * Get 内容
	 */
	@Column(name = "CONTENT")
	@Type(type="org.springframework.orm.hibernate3.support.BlobByteArrayType")
	public byte[] getContent() {
		return content;
	}

	/**
	 * Set 内容
	 */
	public void setContent(byte[] content) {
		this.content = content;
		addValidField(FieldNames.content);
	}

	/**
	 * Get 类型
	 */
	@Column(name = "NEWS_TYPE")
	public String getNewsType() {
		return newsType;
	}

	/**
	 * Set 类型
	 */
	public void setNewsType(String newsType) {
		this.newsType = newsType;
		addValidField(FieldNames.newsType);
	}

	/**
	 * Get 是否有附件
	 */
	@Column(name = "IF_FILES")
	public String getIfFiles() {
		return ifFiles;
	}

	/**
	 * Set 是否有附件
	 */
	public void setIfFiles(String ifFiles) {
		this.ifFiles = ifFiles;
		addValidField(FieldNames.ifFiles);
	}

	/**
	 * Get 经手人
	 */
	@Column(name = "FUNCTIONARY")
	public String getFunctionary() {
		return functionary;
	}

	/**
	 * Set 经手人
	 */
	public void setFunctionary(String functionary) {
		this.functionary = functionary;
		addValidField(FieldNames.functionary);
	}

	/**
	 * Get 连接地址
	 */
	@Column(name = "URL_ADDRESS")
	public String getUrlAddress() {
		return urlAddress;
	}

	/**
	 * Set 连接地址
	 */
	public void setUrlAddress(String urlAddress) {
		this.urlAddress = urlAddress;
		addValidField(FieldNames.urlAddress);
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
	private String prrmaryKeyName="sysNewsUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
