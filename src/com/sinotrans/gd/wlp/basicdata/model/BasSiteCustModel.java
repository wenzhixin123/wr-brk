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
 * Model class for 区域与客户关联表
 */
@Entity
@Table(name = "BAS_SITE_CUST")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasSiteCustModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasSiteCust";

	public static final class FieldNames {
		/**
		 * Phisical
		 */
		public static final String basSiteCustUuid = "basSiteCustUuid";
		/**
		 * 地点UUID
		 */
		public static final String basSiteUuid = "basSiteUuid";
		/**
		 * 类型
		 */
		public static final String dateType = "dateType";
		/**
		 * 优先排列序号
		 */
		public static final String seqNo = "seqNo";
		/**
		 * 组织编码
		 */
		public static final String locCode = "locCode";
		/**
		 * 组织全称
		 */
		public static final String locName = "locName";
		/**
		 * customerCode
		 */
		public static final String customerCode = "customerCode";
		/**
		 * 中文名称
		 */
		public static final String customerName = "customerName";
		/**
		 * 是否自动审核
		 */
		public static final String isAuto = "isAuto";
		/**
		 * 首重
		 */
		public static final String firstWeight = "firstWeight";
		/**
		 * 续重
		 */
		public static final String secondWeight = "secondWeight";
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

	//Phisical
	private String basSiteCustUuid;
	//地点UUID
	private String basSiteUuid;
	//类型
	private String dateType;
	//优先排列序号
	private Long seqNo;
	//组织编码
	private String locCode;
	//组织全称
	private String locName;
	//customerCode
	private String customerCode;
	//中文名称
	private String customerName;
	//是否自动审核
	private String isAuto;
	//首重
	private Double firstWeight;
	//续重
	private Double secondWeight;
	//备注
	private String remark;
	//状态
	private String status;
	//控制字
	private String controlWord;
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
	 * Get Phisical
	 * Primary Key
	 */
	@Column(name = "BAS_SITE_CUST_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getBasSiteCustUuid() {
		return basSiteCustUuid;
	}

	/**
	 * Set Phisical
	 * Primary Key
	 */
	public void setBasSiteCustUuid(String basSiteCustUuid) {
		this.basSiteCustUuid = basSiteCustUuid;
		addValidField(FieldNames.basSiteCustUuid);
	}

	/**
	 * Get 地点UUID
	 * D
	 */
	@Column(name = "BAS_SITE_UUID")
	public String getBasSiteUuid() {
		return basSiteUuid;
	}

	/**
	 * Set 地点UUID
	 * D
	 */
	public void setBasSiteUuid(String basSiteUuid) {
		this.basSiteUuid = basSiteUuid;
		addValidField(FieldNames.basSiteUuid);
	}

	/**
	 * Get 类型
	 * ：SETUP_CU - 设置快递公司、CHANGE_CU - 选择快递公司
	 */
	@Column(name = "DATE_TYPE")
	public String getDateType() {
		return dateType;
	}

	/**
	 * Set 类型
	 * ：SETUP_CU - 设置快递公司、CHANGE_CU - 选择快递公司
	 */
	public void setDateType(String dateType) {
		this.dateType = dateType;
		addValidField(FieldNames.dateType);
	}

	/**
	 * Get 优先排列序号
	 */
	@Column(name = "SEQ_NO")
	public Long getSeqNo() {
		return seqNo;
	}

	/**
	 * Set 优先排列序号
	 */
	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
		addValidField(FieldNames.seqNo);
	}

	/**
	 * Get 组织编码
	 */
	@Column(name = "LOC_CODE")
	public String getLocCode() {
		return locCode;
	}

	/**
	 * Set 组织编码
	 */
	public void setLocCode(String locCode) {
		this.locCode = locCode;
		addValidField(FieldNames.locCode);
	}

	/**
	 * Get 组织全称
	 */
	@Column(name = "LOC_NAME")
	public String getLocName() {
		return locName;
	}

	/**
	 * Set 组织全称
	 */
	public void setLocName(String locName) {
		this.locName = locName;
		addValidField(FieldNames.locName);
	}

	/**
	 * Get customerCode
	 */
	@Column(name = "CUSTOMER_CODE")
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * Set customerCode
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
		addValidField(FieldNames.customerCode);
	}

	/**
	 * Get 中文名称
	 */
	@Column(name = "CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Set 中文名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
		addValidField(FieldNames.customerName);
	}

	/**
	 * Get 是否自动审核
	 * ：Y - 自动审核
	 */
	@Column(name = "IS_AUTO")
	public String getIsAuto() {
		return isAuto;
	}

	/**
	 * Set 是否自动审核
	 * ：Y - 自动审核
	 */
	public void setIsAuto(String isAuto) {
		this.isAuto = isAuto;
		addValidField(FieldNames.isAuto);
	}

	/**
	 * Get 首重
	 */
	@Column(name = "FIRST_WEIGHT")
	public Double getFirstWeight() {
		return firstWeight;
	}

	/**
	 * Set 首重
	 */
	public void setFirstWeight(Double firstWeight) {
		this.firstWeight = firstWeight;
		addValidField(FieldNames.firstWeight);
	}

	/**
	 * Get 续重
	 */
	@Column(name = "SECOND_WEIGHT")
	public Double getSecondWeight() {
		return secondWeight;
	}

	/**
	 * Set 续重
	 */
	public void setSecondWeight(Double secondWeight) {
		this.secondWeight = secondWeight;
		addValidField(FieldNames.secondWeight);
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
	private String prrmaryKeyName="basSiteCustUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
