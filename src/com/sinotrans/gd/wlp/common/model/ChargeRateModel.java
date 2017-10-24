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
 * Model class for 费率表
 */
@Entity
@Table(name = "CHARGE_RATE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class ChargeRateModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "ChargeRate";

	public static final class FieldNames {
		/**
		 * 费率UUID
		 */
		public static final String chargeRateUuid = "chargeRateUuid";
		/**
		 * 费率代码
		 */
		public static final String chargeRateCode = "chargeRateCode";
		/**
		 * 费率名称
		 */
		public static final String chargeRateName = "chargeRateName";
		/**
		 * 收付标识
		 */
		public static final String rpType = "rpType";
		/**
		 * 作业类型
		 */
		public static final String workType = "workType";
		/**
		 * 作业项目
		 */
		public static final String workItem = "workItem";
		/**
		 * 货物种类代码
		 */
		public static final String itemKindCode = "itemKindCode";
		/**
		 * 货物种类描述
		 */
		public static final String itemKindName = "itemKindName";
		/**
		 * 货物属性代码
		 */
		public static final String itemNatureCode = "itemNatureCode";
		/**
		 * 货物属性描述
		 */
		public static final String itemNatureName = "itemNatureName";
		/**
		 * 货物类型
		 */
		public static final String goodsType = "goodsType";
		/**
		 * 贸易方式
		 */
		public static final String tradeType = "tradeType";
		/**
		 * 运输方式
		 */
		public static final String transportType = "transportType";
		/**
		 * 批次号
		 */
		public static final String batchNo = "batchNo";
		/**
		 * 货物名称
		 */
		public static final String goodsDesc = "goodsDesc";
		/**
		 * 货物编码
		 */
		public static final String itemCode = "itemCode";
		/**
		 * 物料序列号
		 */
		public static final String itemSeqno = "itemSeqno";
		/**
		 * 唛头
		 */
		public static final String marksNumber = "marksNumber";
		/**
		 * 型号
		 */
		public static final String model = "model";
		/**
		 * 规格
		 */
		public static final String spec = "spec";
		/**
		 * 危险品UN代码
		 */
		public static final String dangerCode = "dangerCode";
		/**
		 * 包装类型（箱型）
		 */
		public static final String packageType = "packageType";
		/**
		 * BOM_CODE
		 */
		public static final String bomCode = "bomCode";
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

	//费率UUID
	private String chargeRateUuid;
	//费率代码
	private String chargeRateCode;
	//费率名称
	private String chargeRateName;
	//收付标识
	private String rpType;
	//作业类型
	private String workType;
	//作业项目
	private String workItem;
	//货物种类代码
	private String itemKindCode;
	//货物种类描述
	private String itemKindName;
	//货物属性代码
	private String itemNatureCode;
	//货物属性描述
	private String itemNatureName;
	//货物类型
	private String goodsType;
	//贸易方式
	private String tradeType;
	//运输方式
	private String transportType;
	//批次号
	private String batchNo;
	//货物名称
	private String goodsDesc;
	//货物编码
	private String itemCode;
	//物料序列号
	private String itemSeqno;
	//唛头
	private String marksNumber;
	//型号
	private String model;
	//规格
	private String spec;
	//危险品UN代码
	private String dangerCode;
	//包装类型（箱型）
	private String packageType;
	//BOM_CODE
	private String bomCode;
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
	 * Get 费率UUID
	 */
	@Column(name = "CHARGE_RATE_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
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
	 * Get 费率代码
	 */
	@Column(name = "CHARGE_RATE_CODE")
	public String getChargeRateCode() {
		return chargeRateCode;
	}

	/**
	 * Set 费率代码
	 */
	public void setChargeRateCode(String chargeRateCode) {
		this.chargeRateCode = chargeRateCode;
		addValidField(FieldNames.chargeRateCode);
	}

	/**
	 * Get 费率名称
	 */
	@Column(name = "CHARGE_RATE_NAME")
	public String getChargeRateName() {
		return chargeRateName;
	}

	/**
	 * Set 费率名称
	 */
	public void setChargeRateName(String chargeRateName) {
		this.chargeRateName = chargeRateName;
		addValidField(FieldNames.chargeRateName);
	}

	/**
	 * Get 收付标识
	 * ：AR--应收、AP--应付、DD--代收代付
	 */
	@Column(name = "RP_TYPE")
	public String getRpType() {
		return rpType;
	}

	/**
	 * Set 收付标识
	 * ：AR--应收、AP--应付、DD--代收代付
	 */
	public void setRpType(String rpType) {
		this.rpType = rpType;
		addValidField(FieldNames.rpType);
	}

	/**
	 * Get 作业类型
	 * ：SIN-进库、SOT-出库、PDN -加工、ADJ – 移位、CHK --盘点
	 */
	@Column(name = "WORK_TYPE")
	public String getWorkType() {
		return workType;
	}

	/**
	 * Set 作业类型
	 * ：SIN-进库、SOT-出库、PDN -加工、ADJ – 移位、CHK --盘点
	 */
	public void setWorkType(String workType) {
		this.workType = workType;
		addValidField(FieldNames.workType);
	}

	/**
	 * Get 作业项目
	 * ：卸车入库、出库装车等
	 */
	@Column(name = "WORK_ITEM")
	public String getWorkItem() {
		return workItem;
	}

	/**
	 * Set 作业项目
	 * ：卸车入库、出库装车等
	 */
	public void setWorkItem(String workItem) {
		this.workItem = workItem;
		addValidField(FieldNames.workItem);
	}

	/**
	 * Get 货物种类代码
	 */
	@Column(name = "ITEM_KIND_CODE")
	public String getItemKindCode() {
		return itemKindCode;
	}

	/**
	 * Set 货物种类代码
	 */
	public void setItemKindCode(String itemKindCode) {
		this.itemKindCode = itemKindCode;
		addValidField(FieldNames.itemKindCode);
	}

	/**
	 * Get 货物种类描述
	 */
	@Column(name = "ITEM_KIND_NAME")
	public String getItemKindName() {
		return itemKindName;
	}

	/**
	 * Set 货物种类描述
	 */
	public void setItemKindName(String itemKindName) {
		this.itemKindName = itemKindName;
		addValidField(FieldNames.itemKindName);
	}

	/**
	 * Get 货物属性代码
	 */
	@Column(name = "ITEM_NATURE_CODE")
	public String getItemNatureCode() {
		return itemNatureCode;
	}

	/**
	 * Set 货物属性代码
	 */
	public void setItemNatureCode(String itemNatureCode) {
		this.itemNatureCode = itemNatureCode;
		addValidField(FieldNames.itemNatureCode);
	}

	/**
	 * Get 货物属性描述
	 */
	@Column(name = "ITEM_NATURE_NAME")
	public String getItemNatureName() {
		return itemNatureName;
	}

	/**
	 * Set 货物属性描述
	 */
	public void setItemNatureName(String itemNatureName) {
		this.itemNatureName = itemNatureName;
		addValidField(FieldNames.itemNatureName);
	}

	/**
	 * Get 货物类型
	 * ：D-普通货物、5-保税货物、6-监管货物
	 */
	@Column(name = "GOODS_TYPE")
	public String getGoodsType() {
		return goodsType;
	}

	/**
	 * Set 货物类型
	 * ：D-普通货物、5-保税货物、6-监管货物
	 */
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
		addValidField(FieldNames.goodsType);
	}

	/**
	 * Get 贸易方式
	 * ：
	 */
	@Column(name = "TRADE_TYPE")
	public String getTradeType() {
		return tradeType;
	}

	/**
	 * Set 贸易方式
	 * ：
	 */
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
		addValidField(FieldNames.tradeType);
	}

	/**
	 * Get 运输方式
	 * ：0-汽车到货、1–船到货、2–火车到货、3–空运、4–货柜到货
	 */
	@Column(name = "TRANSPORT_TYPE")
	public String getTransportType() {
		return transportType;
	}

	/**
	 * Set 运输方式
	 * ：0-汽车到货、1–船到货、2–火车到货、3–空运、4–货柜到货
	 */
	public void setTransportType(String transportType) {
		this.transportType = transportType;
		addValidField(FieldNames.transportType);
	}

	/**
	 * Get 批次号
	 */
	@Column(name = "BATCH_NO")
	public String getBatchNo() {
		return batchNo;
	}

	/**
	 * Set 批次号
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
		addValidField(FieldNames.batchNo);
	}

	/**
	 * Get 货物名称
	 */
	@Column(name = "GOODS_DESC")
	public String getGoodsDesc() {
		return goodsDesc;
	}

	/**
	 * Set 货物名称
	 */
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
		addValidField(FieldNames.goodsDesc);
	}

	/**
	 * Get 货物编码
	 */
	@Column(name = "ITEM_CODE")
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * Set 货物编码
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
		addValidField(FieldNames.itemCode);
	}

	/**
	 * Get 物料序列号
	 */
	@Column(name = "ITEM_SEQNO")
	public String getItemSeqno() {
		return itemSeqno;
	}

	/**
	 * Set 物料序列号
	 */
	public void setItemSeqno(String itemSeqno) {
		this.itemSeqno = itemSeqno;
		addValidField(FieldNames.itemSeqno);
	}

	/**
	 * Get 唛头
	 */
	@Column(name = "MARKS_NUMBER")
	public String getMarksNumber() {
		return marksNumber;
	}

	/**
	 * Set 唛头
	 */
	public void setMarksNumber(String marksNumber) {
		this.marksNumber = marksNumber;
		addValidField(FieldNames.marksNumber);
	}

	/**
	 * Get 型号
	 */
	@Column(name = "MODEL")
	public String getModel() {
		return model;
	}

	/**
	 * Set 型号
	 */
	public void setModel(String model) {
		this.model = model;
		addValidField(FieldNames.model);
	}

	/**
	 * Get 规格
	 */
	@Column(name = "SPEC")
	public String getSpec() {
		return spec;
	}

	/**
	 * Set 规格
	 */
	public void setSpec(String spec) {
		this.spec = spec;
		addValidField(FieldNames.spec);
	}

	/**
	 * Get 危险品UN代码
	 */
	@Column(name = "DANGER_CODE")
	public String getDangerCode() {
		return dangerCode;
	}

	/**
	 * Set 危险品UN代码
	 */
	public void setDangerCode(String dangerCode) {
		this.dangerCode = dangerCode;
		addValidField(FieldNames.dangerCode);
	}

	/**
	 * Get 包装类型（箱型）
	 */
	@Column(name = "PACKAGE_TYPE")
	public String getPackageType() {
		return packageType;
	}

	/**
	 * Set 包装类型（箱型）
	 */
	public void setPackageType(String packageType) {
		this.packageType = packageType;
		addValidField(FieldNames.packageType);
	}

	/**
	 * Get BOM_CODE
	 */
	@Column(name = "BOM_CODE")
	public String getBomCode() {
		return bomCode;
	}

	/**
	 * Set BOM_CODE
	 */
	public void setBomCode(String bomCode) {
		this.bomCode = bomCode;
		addValidField(FieldNames.bomCode);
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
	private String prrmaryKeyName="chargeRateUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
