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
 * Model class for 费率明细表
 */
@Entity
@Table(name = "CHARGE_RATE_DETAIL")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class ChargeRateDetailModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "ChargeRateDetail";

	public static final class FieldNames {
		/**
		 * 费率明细UUID
		 */
		public static final String chargeRateDetailUuid = "chargeRateDetailUuid";
		/**
		 * 费率UUID
		 */
		public static final String chargeRateUuid = "chargeRateUuid";
		/**
		 * 序号
		 */
		public static final String seqNo = "seqNo";
		/**
		 * 费目代码
		 */
		public static final String chargeCode = "chargeCode";
		/**
		 * 费目名称
		 */
		public static final String chargeName = "chargeName";
		/**
		 * 数量
		 */
		public static final String qty = "qty";
		/**
		 * 包装单位代码
		 */
		public static final String unitCode = "unitCode";
		/**
		 * 包装单位描述
		 */
		public static final String unitDesc = "unitDesc";
		/**
		 * 箱型
		 */
		public static final String containerType = "containerType";
		/**
		 * 币别编码
		 */
		public static final String currencyCode = "currencyCode";
		/**
		 * 币别名称
		 */
		public static final String currencyName = "currencyName";
		/**
		 * 费率
		 */
		public static final String rate = "rate";
		/**
		 * 开始数值
		 */
		public static final String beginValue = "beginValue";
		/**
		 * 结束数值
		 */
		public static final String endValue = "endValue";
		/**
		 * 按天计算
		 */
		public static final String dateFlag = "dateFlag";
		/**
		 * 按数量计算
		 */
		public static final String qtyFlag = "qtyFlag";
		/**
		 * 按体积计算
		 */
		public static final String volumeFlag = "volumeFlag";
		/**
		 * 按净重计算
		 */
		public static final String netWeightFlag = "netWeightFlag";
		/**
		 * 按毛重计算
		 */
		public static final String grossWeightFlag = "grossWeightFlag";
		/**
		 * 按价格计算
		 */
		public static final String priceFlag = "priceFlag";
		/**
		 * 按集装箱计算
		 */
		public static final String containerFlag = "containerFlag";
		/**
		 * 按票数计算
		 */
		public static final String billNoFlag = "billNoFlag";
		/**
		 * 按运输地
		 */
		public static final String placeFlag = "placeFlag";
		/**
		 * 使用ITEM
		 */
		public static final String itemFlag = "itemFlag";
		/**
		 * otherFlag
		 */
		public static final String otherFlag = "otherFlag";
		/**
		 * 表达式
		 */
		public static final String expresion = "expresion";
		/**
		 * 起运地
		 */
		public static final String locationCode = "locationCode";
		/**
		 * 目的地
		 */
		public static final String destinationCode = "destinationCode";
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

	//费率明细UUID
	private String chargeRateDetailUuid;
	//费率UUID
	private String chargeRateUuid;
	//序号
	private Long seqNo;
	//费目代码
	private String chargeCode;
	//费目名称
	private String chargeName;
	//数量
	private Double qty;
	//包装单位代码
	private String unitCode;
	//包装单位描述
	private String unitDesc;
	//箱型
	private String containerType;
	//币别编码
	private String currencyCode;
	//币别名称
	private String currencyName;
	//费率
	private Double rate;
	//开始数值
	private Double beginValue;
	//结束数值
	private Double endValue;
	//按天计算
	private String dateFlag;
	//按数量计算
	private String qtyFlag;
	//按体积计算
	private String volumeFlag;
	//按净重计算
	private String netWeightFlag;
	//按毛重计算
	private String grossWeightFlag;
	//按价格计算
	private String priceFlag;
	//按集装箱计算
	private String containerFlag;
	//按票数计算
	private String billNoFlag;
	//按运输地
	private String placeFlag;
	//使用ITEM
	private String itemFlag;
	//otherFlag
	private String otherFlag;
	//表达式
	private String expresion;
	//起运地
	private String locationCode;
	//目的地
	private String destinationCode;
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
	 * Get 费率明细UUID
	 */
	@Column(name = "CHARGE_RATE_DETAIL_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getChargeRateDetailUuid() {
		return chargeRateDetailUuid;
	}

	/**
	 * Set 费率明细UUID
	 */
	public void setChargeRateDetailUuid(String chargeRateDetailUuid) {
		this.chargeRateDetailUuid = chargeRateDetailUuid;
		addValidField(FieldNames.chargeRateDetailUuid);
	}

	/**
	 * Get 费率UUID
	 */
	@Column(name = "CHARGE_RATE_UUID")
	public String getChargeRateUuid() {
		return chargeRateUuid;
	}

	/**
	 * Set 费率UUID
	 */
	public void setChargeRateUuid(String chargeRateUuid) {
		this.chargeRateUuid = chargeRateUuid;
		addValidField(FieldNames.chargeRateUuid);
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
	 * Get 费目代码
	 */
	@Column(name = "CHARGE_CODE")
	public String getChargeCode() {
		return chargeCode;
	}

	/**
	 * Set 费目代码
	 */
	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
		addValidField(FieldNames.chargeCode);
	}

	/**
	 * Get 费目名称
	 */
	@Column(name = "CHARGE_NAME")
	public String getChargeName() {
		return chargeName;
	}

	/**
	 * Set 费目名称
	 */
	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
		addValidField(FieldNames.chargeName);
	}

	/**
	 * Get 数量
	 */
	@Column(name = "QTY")
	public Double getQty() {
		return qty;
	}

	/**
	 * Set 数量
	 */
	public void setQty(Double qty) {
		this.qty = qty;
		addValidField(FieldNames.qty);
	}

	/**
	 * Get 包装单位代码
	 */
	@Column(name = "UNIT_CODE")
	public String getUnitCode() {
		return unitCode;
	}

	/**
	 * Set 包装单位代码
	 */
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
		addValidField(FieldNames.unitCode);
	}

	/**
	 * Get 包装单位描述
	 */
	@Column(name = "UNIT_DESC")
	public String getUnitDesc() {
		return unitDesc;
	}

	/**
	 * Set 包装单位描述
	 */
	public void setUnitDesc(String unitDesc) {
		this.unitDesc = unitDesc;
		addValidField(FieldNames.unitDesc);
	}

	/**
	 * Get 箱型
	 */
	@Column(name = "CONTAINER_TYPE")
	public String getContainerType() {
		return containerType;
	}

	/**
	 * Set 箱型
	 */
	public void setContainerType(String containerType) {
		this.containerType = containerType;
		addValidField(FieldNames.containerType);
	}

	/**
	 * Get 币别编码
	 */
	@Column(name = "CURRENCY_CODE")
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * Set 币别编码
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
		addValidField(FieldNames.currencyCode);
	}

	/**
	 * Get 币别名称
	 */
	@Column(name = "CURRENCY_NAME")
	public String getCurrencyName() {
		return currencyName;
	}

	/**
	 * Set 币别名称
	 */
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
		addValidField(FieldNames.currencyName);
	}

	/**
	 * Get 费率
	 */
	@Column(name = "RATE")
	public Double getRate() {
		return rate;
	}

	/**
	 * Set 费率
	 */
	public void setRate(Double rate) {
		this.rate = rate;
		addValidField(FieldNames.rate);
	}

	/**
	 * Get 开始数值
	 */
	@Column(name = "BEGIN_VALUE")
	public Double getBeginValue() {
		return beginValue;
	}

	/**
	 * Set 开始数值
	 */
	public void setBeginValue(Double beginValue) {
		this.beginValue = beginValue;
		addValidField(FieldNames.beginValue);
	}

	/**
	 * Get 结束数值
	 */
	@Column(name = "END_VALUE")
	public Double getEndValue() {
		return endValue;
	}

	/**
	 * Set 结束数值
	 */
	public void setEndValue(Double endValue) {
		this.endValue = endValue;
		addValidField(FieldNames.endValue);
	}

	/**
	 * Get 按天计算
	 * ：1- 勾 ，0- 没有勾
	 */
	@Column(name = "DATE_FLAG")
	public String getDateFlag() {
		return dateFlag;
	}

	/**
	 * Set 按天计算
	 * ：1- 勾 ，0- 没有勾
	 */
	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
		addValidField(FieldNames.dateFlag);
	}

	/**
	 * Get 按数量计算
	 * ：1- 勾 ，0- 没有勾
	 */
	@Column(name = "QTY_FLAG")
	public String getQtyFlag() {
		return qtyFlag;
	}

	/**
	 * Set 按数量计算
	 * ：1- 勾 ，0- 没有勾
	 */
	public void setQtyFlag(String qtyFlag) {
		this.qtyFlag = qtyFlag;
		addValidField(FieldNames.qtyFlag);
	}

	/**
	 * Get 按体积计算
	 * ：1- 勾 ，0- 没有勾
	 */
	@Column(name = "VOLUME_FLAG")
	public String getVolumeFlag() {
		return volumeFlag;
	}

	/**
	 * Set 按体积计算
	 * ：1- 勾 ，0- 没有勾
	 */
	public void setVolumeFlag(String volumeFlag) {
		this.volumeFlag = volumeFlag;
		addValidField(FieldNames.volumeFlag);
	}

	/**
	 * Get 按净重计算
	 * ：1- 勾 ，0- 没有勾
	 */
	@Column(name = "NET_WEIGHT_FLAG")
	public String getNetWeightFlag() {
		return netWeightFlag;
	}

	/**
	 * Set 按净重计算
	 * ：1- 勾 ，0- 没有勾
	 */
	public void setNetWeightFlag(String netWeightFlag) {
		this.netWeightFlag = netWeightFlag;
		addValidField(FieldNames.netWeightFlag);
	}

	/**
	 * Get 按毛重计算
	 * ：1- 勾 ，0- 没有勾
	 */
	@Column(name = "GROSS_WEIGHT_FLAG")
	public String getGrossWeightFlag() {
		return grossWeightFlag;
	}

	/**
	 * Set 按毛重计算
	 * ：1- 勾 ，0- 没有勾
	 */
	public void setGrossWeightFlag(String grossWeightFlag) {
		this.grossWeightFlag = grossWeightFlag;
		addValidField(FieldNames.grossWeightFlag);
	}

	/**
	 * Get 按价格计算
	 * ：1- 勾 ，0- 没有勾
	 */
	@Column(name = "PRICE_FLAG")
	public String getPriceFlag() {
		return priceFlag;
	}

	/**
	 * Set 按价格计算
	 * ：1- 勾 ，0- 没有勾
	 */
	public void setPriceFlag(String priceFlag) {
		this.priceFlag = priceFlag;
		addValidField(FieldNames.priceFlag);
	}

	/**
	 * Get 按集装箱计算
	 * ：1- 勾 ，0- 没有勾
	 */
	@Column(name = "CONTAINER_FLAG")
	public String getContainerFlag() {
		return containerFlag;
	}

	/**
	 * Set 按集装箱计算
	 * ：1- 勾 ，0- 没有勾
	 */
	public void setContainerFlag(String containerFlag) {
		this.containerFlag = containerFlag;
		addValidField(FieldNames.containerFlag);
	}

	/**
	 * Get 按票数计算
	 * ：1- 勾 ，0- 没有勾
	 */
	@Column(name = "BILL_NO_FLAG")
	public String getBillNoFlag() {
		return billNoFlag;
	}

	/**
	 * Set 按票数计算
	 * ：1- 勾 ，0- 没有勾
	 */
	public void setBillNoFlag(String billNoFlag) {
		this.billNoFlag = billNoFlag;
		addValidField(FieldNames.billNoFlag);
	}

	/**
	 * Get 按运输地
	 * ：1- 勾 ，0- 没有勾
	 */
	@Column(name = "PLACE_FLAG")
	public String getPlaceFlag() {
		return placeFlag;
	}

	/**
	 * Set 按运输地
	 * ：1- 勾 ，0- 没有勾
	 */
	public void setPlaceFlag(String placeFlag) {
		this.placeFlag = placeFlag;
		addValidField(FieldNames.placeFlag);
	}

	/**
	 * Get 使用ITEM
	 * ：1- 勾 ，0- 没有勾
	 */
	@Column(name = "ITEM_FLAG")
	public String getItemFlag() {
		return itemFlag;
	}

	/**
	 * Set 使用ITEM
	 * ：1- 勾 ，0- 没有勾
	 */
	public void setItemFlag(String itemFlag) {
		this.itemFlag = itemFlag;
		addValidField(FieldNames.itemFlag);
	}

	/**
	 * Get otherFlag
	 */
	@Column(name = "OTHER_FLAG")
	public String getOtherFlag() {
		return otherFlag;
	}

	/**
	 * Set otherFlag
	 */
	public void setOtherFlag(String otherFlag) {
		this.otherFlag = otherFlag;
		addValidField(FieldNames.otherFlag);
	}

	/**
	 * Get 表达式
	 */
	@Column(name = "EXPRESION")
	public String getExpresion() {
		return expresion;
	}

	/**
	 * Set 表达式
	 */
	public void setExpresion(String expresion) {
		this.expresion = expresion;
		addValidField(FieldNames.expresion);
	}

	/**
	 * Get 起运地
	 */
	@Column(name = "LOCATION_CODE")
	public String getLocationCode() {
		return locationCode;
	}

	/**
	 * Set 起运地
	 */
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
		addValidField(FieldNames.locationCode);
	}

	/**
	 * Get 目的地
	 */
	@Column(name = "DESTINATION_CODE")
	public String getDestinationCode() {
		return destinationCode;
	}

	/**
	 * Set 目的地
	 */
	public void setDestinationCode(String destinationCode) {
		this.destinationCode = destinationCode;
		addValidField(FieldNames.destinationCode);
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
	private String prrmaryKeyName="chargeRateDetailUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
