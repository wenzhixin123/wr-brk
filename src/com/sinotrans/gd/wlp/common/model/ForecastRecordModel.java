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
 * Model class for 预约表
 */
@Entity
@Table(name = "FORECAST_RECORD")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class ForecastRecordModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "ForecastRecord";

	public static final class FieldNames {
		/**
		 * UUID
		 */
		public static final String cfrUuid = "cfrUuid";
		/**
		 * 委托单UUID号
		 */
		public static final String submitOrderUuid = "submitOrderUuid";
		/**
		 * 客户委托单号
		 */
		public static final String orderNo = "orderNo";
		/**
		 * 作业单UUID
		 */
		public static final String logisticsOrderUuid = "logisticsOrderUuid";
		/**
		 * 预约号
		 */
		public static final String forecastNo = "forecastNo";
		/**
		 * 时间段设置主键（外键）
		 */
		public static final String cfsUuid = "cfsUuid";
		/**
		 * 车牌号
		 */
		public static final String tractorNo = "tractorNo";
		/**
		 * 预约（电话/短信）电话号码
		 */
		public static final String forecastTel = "forecastTel";
		/**
		 * 取消预约（电话/短信）电话号码
		 */
		public static final String unforecastTel = "unforecastTel";
		/**
		 * 经办人
		 */
		public static final String functionary = "functionary";
		/**
		 * 短信回复手机
		 */
		public static final String masMobil = "masMobil";
		/**
		 * 开始时间
		 */
		public static final String startDate = "startDate";
		/**
		 * 结束时间
		 */
		public static final String endDate = "endDate";
		/**
		 * 控制字
		 */
		public static final String controlWord = "controlWord";
		/**
		 * 备注
		 */
		public static final String remark = "remark";
		/**
		 * 预约单位代码
		 */
		public static final String customerCode = "customerCode";
		/**
		 * 预约单位名称
		 */
		public static final String customerDesc = "customerDesc";
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
		/**
		 * 预约类型(YY-临时领货预约领货
		 */
		public static final String forecastType = "forecastType";
		/**
		 * 状态
		 */
		public static final String status = "status";
	}

	//UUID
	private String cfrUuid;
	//委托单UUID号
	private String submitOrderUuid;
	//客户委托单号
	private String orderNo;
	//作业单UUID
	private String logisticsOrderUuid;
	//预约号
	private String forecastNo;
	//时间段设置主键（外键）
	private String cfsUuid;
	//车牌号
	private String tractorNo;
	//预约（电话/短信）电话号码
	private String forecastTel;
	//取消预约（电话/短信）电话号码
	private String unforecastTel;
	//经办人
	private String functionary;
	//短信回复手机
	private String masMobil;
	//开始时间
	private Date startDate;
	//结束时间
	private Date endDate;
	//控制字
	private String controlWord;
	//备注
	private String remark;
	//预约单位代码
	private String customerCode;
	//预约单位名称
	private String customerDesc;
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
	//预约类型(YY-临时领货预约领货
	private String forecastType;
	//状态
	private String status;

	/**
	 * Get UUID
	 */
	@Column(name = "CFR_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getCfrUuid() {
		return cfrUuid;
	}

	/**
	 * Set UUID
	 */
	public void setCfrUuid(String cfrUuid) {
		this.cfrUuid = cfrUuid;
		addValidField(FieldNames.cfrUuid);
	}

	/**
	 * Get 委托单UUID号
	 */
	@Column(name = "SUBMIT_ORDER_UUID")
	public String getSubmitOrderUuid() {
		return submitOrderUuid;
	}

	/**
	 * Set 委托单UUID号
	 */
	public void setSubmitOrderUuid(String submitOrderUuid) {
		this.submitOrderUuid = submitOrderUuid;
		addValidField(FieldNames.submitOrderUuid);
	}

	/**
	 * Get 客户委托单号
	 */
	@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * Set 客户委托单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
		addValidField(FieldNames.orderNo);
	}

	/**
	 * Get 作业单UUID
	 */
	@Column(name = "LOGISTICS_ORDER_UUID")
	public String getLogisticsOrderUuid() {
		return logisticsOrderUuid;
	}

	/**
	 * Set 作业单UUID
	 */
	public void setLogisticsOrderUuid(String logisticsOrderUuid) {
		this.logisticsOrderUuid = logisticsOrderUuid;
		addValidField(FieldNames.logisticsOrderUuid);
	}

	/**
	 * Get 预约号
	 */
	@Column(name = "FORECAST_NO")
	public String getForecastNo() {
		return forecastNo;
	}

	/**
	 * Set 预约号
	 */
	public void setForecastNo(String forecastNo) {
		this.forecastNo = forecastNo;
		addValidField(FieldNames.forecastNo);
	}

	/**
	 * Get 时间段设置主键（外键）
	 */
	@Column(name = "CFS_UUID")
	public String getCfsUuid() {
		return cfsUuid;
	}

	/**
	 * Set 时间段设置主键（外键）
	 */
	public void setCfsUuid(String cfsUuid) {
		this.cfsUuid = cfsUuid;
		addValidField(FieldNames.cfsUuid);
	}

	/**
	 * Get 车牌号
	 */
	@Column(name = "TRACTOR_NO")
	public String getTractorNo() {
		return tractorNo;
	}

	/**
	 * Set 车牌号
	 */
	public void setTractorNo(String tractorNo) {
		this.tractorNo = tractorNo;
		addValidField(FieldNames.tractorNo);
	}

	/**
	 * Get 预约（电话/短信）电话号码
	 */
	@Column(name = "FORECAST_TEL")
	public String getForecastTel() {
		return forecastTel;
	}

	/**
	 * Set 预约（电话/短信）电话号码
	 */
	public void setForecastTel(String forecastTel) {
		this.forecastTel = forecastTel;
		addValidField(FieldNames.forecastTel);
	}

	/**
	 * Get 取消预约（电话/短信）电话号码
	 */
	@Column(name = "UNFORECAST_TEL")
	public String getUnforecastTel() {
		return unforecastTel;
	}

	/**
	 * Set 取消预约（电话/短信）电话号码
	 */
	public void setUnforecastTel(String unforecastTel) {
		this.unforecastTel = unforecastTel;
		addValidField(FieldNames.unforecastTel);
	}

	/**
	 * Get 经办人
	 */
	@Column(name = "FUNCTIONARY")
	public String getFunctionary() {
		return functionary;
	}

	/**
	 * Set 经办人
	 */
	public void setFunctionary(String functionary) {
		this.functionary = functionary;
		addValidField(FieldNames.functionary);
	}

	/**
	 * Get 短信回复手机
	 */
	@Column(name = "MAS_MOBIL")
	public String getMasMobil() {
		return masMobil;
	}

	/**
	 * Set 短信回复手机
	 */
	public void setMasMobil(String masMobil) {
		this.masMobil = masMobil;
		addValidField(FieldNames.masMobil);
	}

	/**
	 * Get 开始时间
	 */
	@Column(name = "START_DATE")
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Set 开始时间
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
		addValidField(FieldNames.startDate);
	}

	/**
	 * Get 结束时间
	 */
	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Set 结束时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
		addValidField(FieldNames.endDate);
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
	 * Get 预约单位代码
	 */
	@Column(name = "CUSTOMER_CODE")
	public String getCustomerCode() {
		return customerCode;
	}

	/**
	 * Set 预约单位代码
	 */
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
		addValidField(FieldNames.customerCode);
	}

	/**
	 * Get 预约单位名称
	 */
	@Column(name = "CUSTOMER_DESC")
	public String getCustomerDesc() {
		return customerDesc;
	}

	/**
	 * Set 预约单位名称
	 */
	public void setCustomerDesc(String customerDesc) {
		this.customerDesc = customerDesc;
		addValidField(FieldNames.customerDesc);
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
	 * Get 预约类型(YY-临时领货预约领货
	 * ，LS-临时领货)
	 */
	@Column(name = "FORECAST_TYPE")
	public String getForecastType() {
		return forecastType;
	}

	/**
	 * Set 预约类型(YY-临时领货预约领货
	 * ，LS-临时领货)
	 */
	public void setForecastType(String forecastType) {
		this.forecastType = forecastType;
		addValidField(FieldNames.forecastType);
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
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="cfrUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
