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
 * Model class for 配置器
 */
@Entity
@Table(name = "EDI_CONFIG")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class EdiConfigModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "EdiConfig";

	public static final class FieldNames {
		/**
		 * 主键
		 */
		public static final String ediConfigUuid = "ediConfigUuid";
		/**
		 * 配置类型（SCHEDULE、SETUP）
		 */
		public static final String configType = "configType";
		/**
		 * 配置描述
		 */
		public static final String configName = "configName";
		/**
		 * 发送方代码（交互双方约定）
		 */
		public static final String senderCode = "senderCode";
		/**
		 * 发送方描述
		 */
		public static final String senderName = "senderName";
		/**
		 * 接收方代码（交互双方约定）
		 */
		public static final String receiptorCode = "receiptorCode";
		/**
		 * 接收方描述
		 */
		public static final String receiptorName = "receiptorName";
		/**
		 * 报文类型
		 */
		public static final String reportTypeUuid = "reportTypeUuid";
		/**
		 * reportName
		 */
		public static final String reportName = "reportName";
		/**
		 * 交互方式（Servlet、FTP、EMAIL、WebService等）
		 */
		public static final String exchangeMode = "exchangeMode";
		/**
		 * 交互的地址、IP
		 */
		public static final String exchangeUrl = "exchangeUrl";
		/**
		 * 登录账号
		 */
		public static final String account = "account";
		/**
		 * 登录密码
		 */
		public static final String password = "password";
		/**
		 * 默认路径/邮件主题（用于发送操作）
		 */
		public static final String defaultPath = "defaultPath";
		/**
		 * 开始时间
		 */
		public static final String startTime = "startTime";
		/**
		 * 结束时间
		 */
		public static final String endTime = "endTime";
		/**
		 * 重复次数
		 */
		public static final String repeatCount = "repeatCount";
		/**
		 * 轮询间隔
		 */
		public static final String repeatInterval = "repeatInterval";
		/**
		 * 表达式
		 */
		public static final String cronExpressions = "cronExpressions";
		/**
		 * 最大查询天数
		 */
		public static final String maxDateBx = "maxDateBx";
		/**
		 * 最大查询记录数
		 */
		public static final String maxRowBx = "maxRowBx";
		/**
		 * TimeOut时间
		 */
		public static final String timeOut = "timeOut";
		/**
		 * 是否自动发送
		 */
		public static final String isAuto = "isAuto";
		/**
		 * 是否删除文件
		 */
		public static final String isDelete = "isDelete";
		/**
		 * 是否发送空报文
		 */
		public static final String isEmpty = "isEmpty";
		/**
		 * 状态
		 */
		public static final String status = "status";
		/**
		 * 控制字
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
	}

	//主键
	private String ediConfigUuid;
	//配置类型（SCHEDULE、SETUP）
	private String configType;
	//配置描述
	private String configName;
	//发送方代码（交互双方约定）
	private String senderCode;
	//发送方描述
	private String senderName;
	//接收方代码（交互双方约定）
	private String receiptorCode;
	//接收方描述
	private String receiptorName;
	//报文类型
	private String reportTypeUuid;
	//reportName
	private String reportName;
	//交互方式（Servlet、FTP、EMAIL、WebService等）
	private String exchangeMode;
	//交互的地址、IP
	private String exchangeUrl;
	//登录账号
	private String account;
	//登录密码
	private String password;
	//默认路径/邮件主题（用于发送操作）
	private String defaultPath;
	//开始时间
	private Date startTime;
	//结束时间
	private Date endTime;
	//重复次数
	private Long repeatCount;
	//轮询间隔
	private Long repeatInterval;
	//表达式
	private String cronExpressions;
	//最大查询天数
	private Long maxDateBx;
	//最大查询记录数
	private Long maxRowBx;
	//TimeOut时间
	private Long timeOut;
	//是否自动发送
	private String isAuto;
	//是否删除文件
	private String isDelete;
	//是否发送空报文
	private String isEmpty;
	//状态
	private String status;
	//控制字
	private String controlWord;
	//备注
	private String remark;
	//自定义字段1
	private String aux1;
	//自定义字段2
	private String aux2;
	//自定义字段3
	private String aux3;
	//自定义字段4
	private String aux4;
	//自定义字段5
	private String aux5;
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
	 * Get 主键
	 */
	@Column(name = "EDI_CONFIG_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getEdiConfigUuid() {
		return ediConfigUuid;
	}

	/**
	 * Set 主键
	 */
	public void setEdiConfigUuid(String ediConfigUuid) {
		this.ediConfigUuid = ediConfigUuid;
		addValidField(FieldNames.ediConfigUuid);
	}

	/**
	 * Get 配置类型（SCHEDULE、SETUP）
	 */
	@Column(name = "CONFIG_TYPE")
	public String getConfigType() {
		return configType;
	}

	/**
	 * Set 配置类型（SCHEDULE、SETUP）
	 */
	public void setConfigType(String configType) {
		this.configType = configType;
		addValidField(FieldNames.configType);
	}

	/**
	 * Get 配置描述
	 */
	@Column(name = "CONFIG_NAME")
	public String getConfigName() {
		return configName;
	}

	/**
	 * Set 配置描述
	 */
	public void setConfigName(String configName) {
		this.configName = configName;
		addValidField(FieldNames.configName);
	}

	/**
	 * Get 发送方代码（交互双方约定）
	 */
	@Column(name = "SENDER_CODE")
	public String getSenderCode() {
		return senderCode;
	}

	/**
	 * Set 发送方代码（交互双方约定）
	 */
	public void setSenderCode(String senderCode) {
		this.senderCode = senderCode;
		addValidField(FieldNames.senderCode);
	}

	/**
	 * Get 发送方描述
	 */
	@Column(name = "SENDER_NAME")
	public String getSenderName() {
		return senderName;
	}

	/**
	 * Set 发送方描述
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
		addValidField(FieldNames.senderName);
	}

	/**
	 * Get 接收方代码（交互双方约定）
	 */
	@Column(name = "RECEIPTOR_CODE")
	public String getReceiptorCode() {
		return receiptorCode;
	}

	/**
	 * Set 接收方代码（交互双方约定）
	 */
	public void setReceiptorCode(String receiptorCode) {
		this.receiptorCode = receiptorCode;
		addValidField(FieldNames.receiptorCode);
	}

	/**
	 * Get 接收方描述
	 */
	@Column(name = "RECEIPTOR_NAME")
	public String getReceiptorName() {
		return receiptorName;
	}

	/**
	 * Set 接收方描述
	 */
	public void setReceiptorName(String receiptorName) {
		this.receiptorName = receiptorName;
		addValidField(FieldNames.receiptorName);
	}

	/**
	 * Get 报文类型
	 */
	@Column(name = "REPORT_TYPE_UUID")
	public String getReportTypeUuid() {
		return reportTypeUuid;
	}

	/**
	 * Set 报文类型
	 */
	public void setReportTypeUuid(String reportTypeUuid) {
		this.reportTypeUuid = reportTypeUuid;
		addValidField(FieldNames.reportTypeUuid);
	}

	/**
	 * Get reportName
	 */
	@Column(name = "REPORT_NAME")
	public String getReportName() {
		return reportName;
	}

	/**
	 * Set reportName
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
		addValidField(FieldNames.reportName);
	}

	/**
	 * Get 交互方式（Servlet、FTP、EMAIL、WebService等）
	 */
	@Column(name = "EXCHANGE_MODE")
	public String getExchangeMode() {
		return exchangeMode;
	}

	/**
	 * Set 交互方式（Servlet、FTP、EMAIL、WebService等）
	 */
	public void setExchangeMode(String exchangeMode) {
		this.exchangeMode = exchangeMode;
		addValidField(FieldNames.exchangeMode);
	}

	/**
	 * Get 交互的地址、IP
	 */
	@Column(name = "EXCHANGE_URL")
	public String getExchangeUrl() {
		return exchangeUrl;
	}

	/**
	 * Set 交互的地址、IP
	 */
	public void setExchangeUrl(String exchangeUrl) {
		this.exchangeUrl = exchangeUrl;
		addValidField(FieldNames.exchangeUrl);
	}

	/**
	 * Get 登录账号
	 */
	@Column(name = "ACCOUNT")
	public String getAccount() {
		return account;
	}

	/**
	 * Set 登录账号
	 */
	public void setAccount(String account) {
		this.account = account;
		addValidField(FieldNames.account);
	}

	/**
	 * Get 登录密码
	 */
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	/**
	 * Set 登录密码
	 */
	public void setPassword(String password) {
		this.password = password;
		addValidField(FieldNames.password);
	}

	/**
	 * Get 默认路径/邮件主题（用于发送操作）
	 */
	@Column(name = "DEFAULT_PATH")
	public String getDefaultPath() {
		return defaultPath;
	}

	/**
	 * Set 默认路径/邮件主题（用于发送操作）
	 */
	public void setDefaultPath(String defaultPath) {
		this.defaultPath = defaultPath;
		addValidField(FieldNames.defaultPath);
	}

	/**
	 * Get 开始时间
	 */
	@Column(name = "START_TIME")
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Set 开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		addValidField(FieldNames.startTime);
	}

	/**
	 * Get 结束时间
	 */
	@Column(name = "END_TIME")
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * Set 结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
		addValidField(FieldNames.endTime);
	}

	/**
	 * Get 重复次数
	 */
	@Column(name = "REPEAT_COUNT")
	public Long getRepeatCount() {
		return repeatCount;
	}

	/**
	 * Set 重复次数
	 */
	public void setRepeatCount(Long repeatCount) {
		this.repeatCount = repeatCount;
		addValidField(FieldNames.repeatCount);
	}

	/**
	 * Get 轮询间隔
	 */
	@Column(name = "REPEAT_INTERVAL")
	public Long getRepeatInterval() {
		return repeatInterval;
	}

	/**
	 * Set 轮询间隔
	 */
	public void setRepeatInterval(Long repeatInterval) {
		this.repeatInterval = repeatInterval;
		addValidField(FieldNames.repeatInterval);
	}

	/**
	 * Get 表达式
	 */
	@Column(name = "CRON_EXPRESSIONS")
	public String getCronExpressions() {
		return cronExpressions;
	}

	/**
	 * Set 表达式
	 */
	public void setCronExpressions(String cronExpressions) {
		this.cronExpressions = cronExpressions;
		addValidField(FieldNames.cronExpressions);
	}

	/**
	 * Get 最大查询天数
	 */
	@Column(name = "MAX_DATE_BX")
	public Long getMaxDateBx() {
		return maxDateBx;
	}

	/**
	 * Set 最大查询天数
	 */
	public void setMaxDateBx(Long maxDateBx) {
		this.maxDateBx = maxDateBx;
		addValidField(FieldNames.maxDateBx);
	}

	/**
	 * Get 最大查询记录数
	 */
	@Column(name = "MAX_ROW_BX")
	public Long getMaxRowBx() {
		return maxRowBx;
	}

	/**
	 * Set 最大查询记录数
	 */
	public void setMaxRowBx(Long maxRowBx) {
		this.maxRowBx = maxRowBx;
		addValidField(FieldNames.maxRowBx);
	}

	/**
	 * Get TimeOut时间
	 */
	@Column(name = "TIME_OUT")
	public Long getTimeOut() {
		return timeOut;
	}

	/**
	 * Set TimeOut时间
	 */
	public void setTimeOut(Long timeOut) {
		this.timeOut = timeOut;
		addValidField(FieldNames.timeOut);
	}

	/**
	 * Get 是否自动发送
	 * ：Y - 自动发送；N - 人工发送
	 */
	@Column(name = "IS_AUTO")
	public String getIsAuto() {
		return isAuto;
	}

	/**
	 * Set 是否自动发送
	 * ：Y - 自动发送；N - 人工发送
	 */
	public void setIsAuto(String isAuto) {
		this.isAuto = isAuto;
		addValidField(FieldNames.isAuto);
	}

	/**
	 * Get 是否删除文件
	 * ：Y - 删除文件
	 */
	@Column(name = "IS_DELETE")
	public String getIsDelete() {
		return isDelete;
	}

	/**
	 * Set 是否删除文件
	 * ：Y - 删除文件
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
		addValidField(FieldNames.isDelete);
	}

	/**
	 * Get 是否发送空报文
	 * ：Y - 发送
	 */
	@Column(name = "IS_EMPTY")
	public String getIsEmpty() {
		return isEmpty;
	}

	/**
	 * Set 是否发送空报文
	 * ：Y - 发送
	 */
	public void setIsEmpty(String isEmpty) {
		this.isEmpty = isEmpty;
		addValidField(FieldNames.isEmpty);
	}

	/**
	 * Get 状态
	 * ：Active - 有效； Cancel - 作废；Pending － 草稿
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态
	 * ：Active - 有效； Cancel - 作废；Pending － 草稿
	 */
	public void setStatus(String status) {
		this.status = status;
		addValidField(FieldNames.status);
	}

	/**
	 * Get 控制字
	 * :（20位，默认0）
	 */
	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	/**
	 * Set 控制字
	 * :（20位，默认0）
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
	
	/**
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="ediConfigUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
