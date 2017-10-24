package com.sinotrans.gd.wlp.common.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.sinotrans.framework.core.model.BaseModelClass;
import com.sinotrans.framework.core.model.OperationLog;
import com.sinotrans.gd.wlp.util.CustomDateYMDHMSSerializer;

/**
 * Model class for 委托订单
 */
@Entity
@Table(name = "SUBMIT_ORDER")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SubmitOrderModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "SubmitOrder";

	public static final class FieldNames {
		/**
		 * 委托单UUID号
		 */
		public static final String submitOrderUuid = "submitOrderUuid";
		/**
		 * 委托单号
		 */
		public static final String submitOrderNo = "submitOrderNo";
		/**
		 * 客户委托单号
		 */
		public static final String orderNo = "orderNo";
		/**
		 * 项目编号
		 */
		public static final String projectCode = "projectCode";
		/**
		 * 合同号
		 */
		public static final String contractNo = "contractNo";
		/**
		 * 委托单位编码
		 */
		public static final String agentConsigneeCode = "agentConsigneeCode";
		/**
		 * 委托单位名称
		 */
		public static final String agentConsigneeDesc = "agentConsigneeDesc";
		/**
		 * 货主或工厂编码
		 */
		public static final String cargoConsigneeCode = "cargoConsigneeCode";
		/**
		 * 货主或工厂名称
		 */
		public static final String cargoConsigneeDesc = "cargoConsigneeDesc";
		/**
		 * 结算单位代码
		 */
		public static final String payerCode = "payerCode";
		/**
		 * 结算单位名称
		 */
		public static final String payerDesc = "payerDesc";
		/**
		 * 下委托日期
		 */
		public static final String submitDate = "submitDate";
		/**
		 * 出运时间
		 */
		public static final String deliveryDate = "deliveryDate";
		/**
		 * 取消日期
		 */
		public static final String cancelDate = "cancelDate";
		/**
		 * 结算日期
		 */
		public static final String cutOffDate = "cutOffDate";
		/**
		 * 经办人
		 */
		public static final String functionary = "functionary";
		/**
		 * 联系电话
		 */
		public static final String telNo = "telNo";
		/**
		 * 作业要求
		 */
		public static final String workDemand = "workDemand";
		/**
		 * 流向
		 */
		public static final String flow = "flow";
		/**
		 * 结算要求
		 */
		public static final String chargeDesc = "chargeDesc";
		/**
		 * vesselName
		 */
		public static final String vesselName = "vesselName";
		/**
		 * voyage
		 */
		public static final String voyage = "voyage";
		/**
		 * 装卸货港
		 */
		public static final String unloadPort = "unloadPort";
		/**
		 * 运输公司
		 */
		public static final String trailingTeam = "trailingTeam";
		/**
		 * 车牌号码
		 */
		public static final String tractorNo = "tractorNo";
		/**
		 * 运输方式
		 */
		public static final String deliveryType = "deliveryType";
		/**
		 * 上架和拣货策略号
		 */
		public static final String configCode = "configCode";
		/**
		 * 控制字
		 */
		public static final String controlWord = "controlWord";
		/**
		 * UUID
		 */
		public static final String cfrUuid = "cfrUuid";
		/**
		 * 状态
		 */
		public static final String transactionStatus = "transactionStatus";
		/**
		 * 操作类型
		 */
		public static final String transactionType = "transactionType";
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
		/**
		 * 审核人
		 */
		public static final String cutOffName = "cutOffName";
		/**
		 * 作废人
		 */
		public static final String cancelName = "cancelName";
		/**
		 * 包装方案
		 */
		public static final String packagePlan = "packagePlan";
		/**
		 * "订单单次下发ITEM数量
		 */
		public static final String orderItemCount = "orderItemCount";
		/**
		 * "订单总共ITEM数量
		 */
		public static final String totalOrderItemCount = "totalOrderItemCount";
		/**
		 * 包裹数量
		 */
		public static final String packageCount = "packageCount";
		/**
		 * "预期送达开始时间
		 */
		public static final String expectStartTime = "expectStartTime";
		/**
		 * "预期送达结束时间
		 */
		public static final String expectEndTime = "expectEndTime";
		/**
		 * 预计开始入库时间
		 */
		public static final String expectTime = "expectTime";

		/**
		 * 控货人代码
		 */
		public static final String cargoControlCode = "cargoControlCode";

		/**
		 * 控货人描述
		 */
		public static final String cargoControlDesc = "cargoControlDesc";

		public static final String countryCode = "countryCode";
		/**
		 * 去向
		 */
		public static final String direction = "direction";
		
		/**
		 * 车型
		 */
		public static final String tractorType = "tractorType";
		
		/**
		 *截仓时间
		 */
		public static final String cutDate = "cutDate";
		
		/**
		 *合计重量
		 */
		public static final String totalWeight = "totalWeight";
		/**
		 * 验证码
		 */
		public static final String verifyCode = "verifyCode";
		
		public static final String customsType ="customsType";
		
		public static final String businessType="businessType";
	}

	// 控货人描述
	private String cargoControlCode;
	// 控货人代码
	private String cargoControlDesc;
	// 国家
	private String countryCode;

	// 委托单UUID号
	private String submitOrderUuid;
	// 委托单号
	private String submitOrderNo;
	// 客户委托单号
	private String orderNo;
	// 项目编号
	private String projectCode;
	// 合同号
	private String contractNo;
	// 委托单位编码
	private String agentConsigneeCode;
	// 委托单位名称
	private String agentConsigneeDesc;
	// 货主或工厂编码
	private String cargoConsigneeCode;
	// 货主或工厂名称
	private String cargoConsigneeDesc;
	// 结算单位代码
	private String payerCode;
	// 结算单位名称
	private String payerDesc;
	// 下委托日期
	private Date submitDate;
	// 出运时间
	private Date deliveryDate;
	// 取消日期
	private Date cancelDate;
	// 结算日期
	private Date cutOffDate;
	// 经办人
	private String functionary;
	// 联系电话
	private String telNo;
	// 作业要求
	private String workDemand;
	// 流向
	private String flow;
	// 结算要求
	private String chargeDesc;
	// vesselName
	private String vesselName;
	// voyage
	private String voyage;
	// 装卸货港
	private String unloadPort;
	// 运输公司
	private String trailingTeam;
	// 车牌号码
	private String tractorNo;
	// 运输方式
	private String deliveryType;
	// 上架和拣货策略号
	private String configCode;
	// 控制字
	private String controlWord;
	// UUID
	private String cfrUuid;
	// 状态
	private String transactionStatus;
	// 操作类型
	private String transactionType;
	// 备注
	private String remark;
	// 自定义字段1
	private String aux1;
	// 自定义字段2
	private String aux2;
	// 自定义字段3
	private String aux3;
	// 自定义字段4
	private String aux4;
	// 自定义字段5
	private String aux5;
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
	// 审核人
	private String cutOffName;
	// 作废人
	private String cancelName;
	// 包装方案
	private String packagePlan;
	// "订单单次下发ITEM数量
	private Long orderItemCount;
	// "订单总共ITEM数量
	private Long totalOrderItemCount;
	// 包裹数量
	private Long packageCount;
	// "预期送达开始时间
	private Date expectStartTime;
	// "预期送达结束时间
	private Date expectEndTime;
	// 预计开始入库时间
	private Date expectTime;
	//车型
    private String tractorType;
    //截仓时间
  	private Date cutDate;
  	//合计重量
  	private Double totalWeight;
  //去向
  	private String direction;
  //验证码
  	private String verifyCode;
  	private String customsType;
  	private String businessType;


	/**
	 * Get 委托单UUID号
	 */
	@Column(name = "SUBMIT_ORDER_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
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
	 * Get 委托单号
	 */
	@Column(name = "SUBMIT_ORDER_NO")
	public String getSubmitOrderNo() {
		return submitOrderNo;
	}

	/**
	 * Set 委托单号
	 */
	public void setSubmitOrderNo(String submitOrderNo) {
		this.submitOrderNo = submitOrderNo;
		addValidField(FieldNames.submitOrderNo);
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
	 * Get 项目编号
	 */
	@Column(name = "PROJECT_CODE")
	public String getProjectCode() {
		return projectCode;
	}

	/**
	 * Set 项目编号
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
		addValidField(FieldNames.projectCode);
	}

	/**
	 * Get 合同号
	 */
	@Column(name = "CONTRACT_NO")
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * Set 合同号
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
		addValidField(FieldNames.contractNo);
	}

	/**
	 * Get 委托单位编码
	 */
	@Column(name = "AGENT_CONSIGNEE_CODE")
	public String getAgentConsigneeCode() {
		return agentConsigneeCode;
	}

	/**
	 * Set 委托单位编码
	 */
	public void setAgentConsigneeCode(String agentConsigneeCode) {
		this.agentConsigneeCode = agentConsigneeCode;
		addValidField(FieldNames.agentConsigneeCode);
	}

	/**
	 * Get 委托单位名称
	 */
	@Column(name = "AGENT_CONSIGNEE_DESC")
	public String getAgentConsigneeDesc() {
		return agentConsigneeDesc;
	}

	/**
	 * Set 委托单位名称
	 */
	public void setAgentConsigneeDesc(String agentConsigneeDesc) {
		this.agentConsigneeDesc = agentConsigneeDesc;
		addValidField(FieldNames.agentConsigneeDesc);
	}

	/**
	 * Get 货主或工厂编码
	 */
	@Column(name = "CARGO_CONSIGNEE_CODE")
	public String getCargoConsigneeCode() {
		return cargoConsigneeCode;
	}

	/**
	 * Set 货主或工厂编码
	 */
	public void setCargoConsigneeCode(String cargoConsigneeCode) {
		this.cargoConsigneeCode = cargoConsigneeCode;
		addValidField(FieldNames.cargoConsigneeCode);
	}

	/**
	 * Get 货主或工厂名称
	 */
	@Column(name = "CARGO_CONSIGNEE_DESC")
	public String getCargoConsigneeDesc() {
		return cargoConsigneeDesc;
	}

	/**
	 * Set 货主或工厂名称
	 */
	public void setCargoConsigneeDesc(String cargoConsigneeDesc) {
		this.cargoConsigneeDesc = cargoConsigneeDesc;
		addValidField(FieldNames.cargoConsigneeDesc);
	}

	/**
	 * Get 结算单位代码
	 */
	@Column(name = "PAYER_CODE")
	public String getPayerCode() {
		return payerCode;
	}

	/**
	 * Set 结算单位代码
	 */
	public void setPayerCode(String payerCode) {
		this.payerCode = payerCode;
		addValidField(FieldNames.payerCode);
	}

	/**
	 * Get 结算单位名称
	 */
	@Column(name = "PAYER_DESC")
	public String getPayerDesc() {
		return payerDesc;
	}

	/**
	 * Set 结算单位名称
	 */
	public void setPayerDesc(String payerDesc) {
		this.payerDesc = payerDesc;
		addValidField(FieldNames.payerDesc);
	}

	/**
	 * Get 下委托日期
	 */
	@Column(name = "SUBMIT_DATE")
	@JsonSerialize(using = CustomDateYMDHMSSerializer.class)
	public Date getSubmitDate() {
		return submitDate;
	}

	/**
	 * Set 下委托日期
	 */
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
		addValidField(FieldNames.submitDate);
	}

	/**
	 * Get 出运时间
	 */
	@Column(name = "DELIVERY_DATE")
	@JsonSerialize(using = CustomDateYMDHMSSerializer.class)
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * Set 出运时间
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
		addValidField(FieldNames.deliveryDate);
	}

	/**
	 * Get 取消日期
	 */
	@Column(name = "CANCEL_DATE")
	@JsonSerialize(using = CustomDateYMDHMSSerializer.class)
	public Date getCancelDate() {
		return cancelDate;
	}

	/**
	 * Set 取消日期
	 */
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
		addValidField(FieldNames.cancelDate);
	}

	/**
	 * Get 结算日期
	 */
	@Column(name = "CUT_OFF_DATE")
	@JsonSerialize(using = CustomDateYMDHMSSerializer.class)
	public Date getCutOffDate() {
		return cutOffDate;
	}

	/**
	 * Set 结算日期
	 */
	public void setCutOffDate(Date cutOffDate) {
		this.cutOffDate = cutOffDate;
		addValidField(FieldNames.cutOffDate);
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
	 * Get 联系电话
	 */
	@Column(name = "TEL_NO")
	public String getTelNo() {
		return telNo;
	}

	/**
	 * Set 联系电话
	 */
	public void setTelNo(String telNo) {
		this.telNo = telNo;
		addValidField(FieldNames.telNo);
	}

	/**
	 * Get 作业要求
	 */
	@Column(name = "WORK_DEMAND")
	public String getWorkDemand() {
		return workDemand;
	}

	/**
	 * Set 作业要求
	 */
	public void setWorkDemand(String workDemand) {
		this.workDemand = workDemand;
		addValidField(FieldNames.workDemand);
	}

	/**
	 * Get 流向
	 */
	@Column(name = "FLOW")
	public String getFlow() {
		return flow;
	}

	/**
	 * Set 流向
	 */
	public void setFlow(String flow) {
		this.flow = flow;
		addValidField(FieldNames.flow);
	}

	/**
	 * Get 结算要求
	 */
	@Column(name = "CHARGE_DESC")
	public String getChargeDesc() {
		return chargeDesc;
	}

	/**
	 * Set 结算要求
	 */
	public void setChargeDesc(String chargeDesc) {
		this.chargeDesc = chargeDesc;
		addValidField(FieldNames.chargeDesc);
	}

	/**
	 * Get vesselName
	 */
	@Column(name = "VESSEL_NAME")
	public String getVesselName() {
		return vesselName;
	}

	/**
	 * Set vesselName
	 */
	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
		addValidField(FieldNames.vesselName);
	}

	/**
	 * Get voyage
	 */
	@Column(name = "VOYAGE")
	public String getVoyage() {
		return voyage;
	}

	/**
	 * Set voyage
	 */
	public void setVoyage(String voyage) {
		this.voyage = voyage;
		addValidField(FieldNames.voyage);
	}

	/**
	 * Get 装卸货港
	 */
	@Column(name = "UNLOAD_PORT")
	public String getUnloadPort() {
		return unloadPort;
	}

	/**
	 * Set 装卸货港
	 */
	public void setUnloadPort(String unloadPort) {
		this.unloadPort = unloadPort;
		addValidField(FieldNames.unloadPort);
	}

	/**
	 * Get 运输公司
	 */
	@Column(name = "TRAILING_TEAM")
	public String getTrailingTeam() {
		return trailingTeam;
	}

	/**
	 * Set 运输公司
	 */
	public void setTrailingTeam(String trailingTeam) {
		this.trailingTeam = trailingTeam;
		addValidField(FieldNames.trailingTeam);
	}

	/**
	 * Get 车牌号码
	 */
	@Column(name = "TRACTOR_NO")
	public String getTractorNo() {
		return tractorNo;
	}

	/**
	 * Set 车牌号码
	 */
	public void setTractorNo(String tractorNo) {
		this.tractorNo = tractorNo;
		addValidField(FieldNames.tractorNo);
	}

	/**
	 * Get 运输方式
	 */
	@Column(name = "DELIVERY_TYPE")
	public String getDeliveryType() {
		return deliveryType;
	}

	/**
	 * Set 运输方式
	 */
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
		addValidField(FieldNames.deliveryType);
	}

	/**
	 * Get 上架和拣货策略号
	 */
	@Column(name = "CONFIG_CODE")
	public String getConfigCode() {
		return configCode;
	}

	/**
	 * Set 上架和拣货策略号
	 */
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
		addValidField(FieldNames.configCode);
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
	 * Get UUID
	 */
	@Column(name = "CFR_UUID")
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
	 * Get 状态 ：Active - 有效； Cancel - 作废；Pending － 草稿
	 */
	@Column(name = "TRANSACTION_STATUS")
	public String getTransactionStatus() {
		return transactionStatus;
	}

	/**
	 * Set 状态 ：Active - 有效； Cancel - 作废；Pending － 草稿
	 */
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
		addValidField(FieldNames.transactionStatus);
	}

	/**
	 * Get 操作类型 ：SIN - 进库委托单；SOT - 出库委托单；SSP-销售委托单
	 */
	@Column(name = "TRANSACTION_TYPE")
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * Set 操作类型 ：SIN - 进库委托单；SOT - 出库委托单；SSP-销售委托单
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
		addValidField(FieldNames.transactionType);
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
	@Column(name = "CREATOR", updatable=false)
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
	@JsonSerialize(using = CustomDateYMDHMSSerializer.class)
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
	@JsonSerialize(using = CustomDateYMDHMSSerializer.class)
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
	 * Get 审核人
	 */
	@Column(name = "CUT_OFF_NAME")
	public String getCutOffName() {
		return cutOffName;
	}

	/**
	 * Set 审核人
	 */
	public void setCutOffName(String cutOffName) {
		this.cutOffName = cutOffName;
		addValidField(FieldNames.cutOffName);
	}

	/**
	 * Get 作废人
	 */
	@Column(name = "CANCEL_NAME")
	public String getCancelName() {
		return cancelName;
	}

	/**
	 * Set 作废人
	 */
	public void setCancelName(String cancelName) {
		this.cancelName = cancelName;
		addValidField(FieldNames.cancelName);
	}

	/**
	 * Get 包装方案
	 */
	@Column(name = "PACKAGE_PLAN")
	public String getPackagePlan() {
		return packagePlan;
	}

	/**
	 * Set 包装方案
	 */
	public void setPackagePlan(String packagePlan) {
		this.packagePlan = packagePlan;
		addValidField(FieldNames.packagePlan);
	}

	/**
	 * Get "订单单次下发ITEM数量 本消息的order_item数量 当distribute_type为1时为必选 WLB"
	 */
	@Column(name = "ORDER_ITEM_COUNT")
	public Long getOrderItemCount() {
		return orderItemCount;
	}

	/**
	 * Set "订单单次下发ITEM数量 本消息的order_item数量 当distribute_type为1时为必选 WLB"
	 */
	public void setOrderItemCount(Long orderItemCount) {
		this.orderItemCount = orderItemCount;
		addValidField(FieldNames.orderItemCount);
	}

	/**
	 * Get "订单总共ITEM数量 总共ITEM数量 当distribute_type为1时为必选 WLB"
	 */
	@Column(name = "TOTAL_ORDER_ITEM_COUNT")
	public Long getTotalOrderItemCount() {
		return totalOrderItemCount;
	}

	/**
	 * Set "订单总共ITEM数量 总共ITEM数量 当distribute_type为1时为必选 WLB"
	 */
	public void setTotalOrderItemCount(Long totalOrderItemCount) {
		this.totalOrderItemCount = totalOrderItemCount;
		addValidField(FieldNames.totalOrderItemCount);
	}

	/**
	 * Get 包裹数量 WLB
	 */
	@Column(name = "PACKAGE_COUNT")
	public Long getPackageCount() {
		return packageCount;
	}

	/**
	 * Set 包裹数量 WLB
	 */
	public void setPackageCount(Long packageCount) {
		this.packageCount = packageCount;
		addValidField(FieldNames.packageCount);
	}

	/**
	 * Get "预期送达开始时间 入库单常用 YYYY-MM-DD hh:mm:ss WLB"
	 */
	@Column(name = "EXPECT_START_TIME")
	@JsonSerialize(using = CustomDateYMDHMSSerializer.class)
	public Date getExpectStartTime() {
		return expectStartTime;
	}

	/**
	 * Set "预期送达开始时间 入库单常用 YYYY-MM-DD hh:mm:ss WLB"
	 */
	public void setExpectStartTime(Date expectStartTime) {
		this.expectStartTime = expectStartTime;
		addValidField(FieldNames.expectStartTime);
	}

	/**
	 * Get "预期送达结束时间 入库单常用 YYYY-MM-DD hh:mm:ss WLB"
	 */
	@Column(name = "EXPECT_END_TIME")
	@JsonSerialize(using = CustomDateYMDHMSSerializer.class)
	public Date getExpectEndTime() {
		return expectEndTime;
	}

	/**
	 * Set "预期送达结束时间 入库单常用 YYYY-MM-DD hh:mm:ss WLB"
	 */
	public void setExpectEndTime(Date expectEndTime) {
		this.expectEndTime = expectEndTime;
		addValidField(FieldNames.expectEndTime);
	}

	/**
	 * Get 预计开始入库时间 WLB
	 */
	@Column(name = "EXPECT_TIME")
	@JsonSerialize(using = CustomDateYMDHMSSerializer.class)
	public Date getExpectTime() {
		return expectTime;
	}

	/**
	 * Set 预计开始入库时间 WLB
	 */
	public void setExpectTime(Date expectTime) {
		this.expectTime = expectTime;
		addValidField(FieldNames.expectTime);
	}

	@Column(name = "CARGO_CONTROL_CODE")
	public String getCargoControlCode() {
		return cargoControlCode;
	}

	public void setCargoControlCode(String cargoControlCode) {
		this.cargoControlCode = cargoControlCode;
		addValidField(FieldNames.cargoControlCode);
	}

	@Column(name = "CARGO_CONTROL_DESC")
	public String getCargoControlDesc() {
		return cargoControlDesc;
	}

	public void setCargoControlDesc(String cargoControlDesc) {
		this.cargoControlDesc = cargoControlDesc;
		addValidField(FieldNames.cargoControlDesc);
	}

	@Column(name = "COUNTRY_CODE")
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
		addValidField(FieldNames.countryCode);
	}
	
	/**
	 * Get 车型
	 */
	@Column(name = "TRACTOR_TYPE")
	public String getTractorType() {
		return tractorType;
	}

	/**
	 * Set 车型
	 */
	public void setTractorType(String tractorType) {
		this.tractorType = tractorType;
		addValidField(FieldNames.tractorType);
	}
	/**
	 * Get 截仓时间
	 */
	@Column(name = "CUT_DATE")
	public Date getCutDate() {
		return cutDate;
	}

	/**
	 * Set 截仓时间
	 */
	public void setCutDate(Date cutDate) {
		this.cutDate = cutDate;
		addValidField(FieldNames.cutDate);
	}
	/**
	 * Get 合计重量
	 */
	@Column(name = "TOTAL_WEIGHT")
	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
		addValidField(FieldNames.totalWeight);
	}
	
	/**
	 * Get 去向
	 */
	@Column(name = "DIRECTION")
	public String getDirection() {
		return direction;
	}

	/**
	 * Set 去向
	 */
	public void setDirection(String direction) {
		this.direction = direction;
		addValidField(FieldNames.direction);
	}
	/**
	 * Get 验证码
	 */
	@Column(name = "VERIFY_CODE")
	public String getVerifyCode() {
		return verifyCode;
	}

	/**
	 * Set 验证码
	 */
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
		addValidField(FieldNames.verifyCode);
	}
	
	/**
	 */
	@Column(name = "CUSTOMS_TYPE")
	public String getCustomsType() {
		return customsType;
	}

	/**
	 */
	public void setCustomsType(String customsType) {
		this.customsType = customsType;
		addValidField(FieldNames.customsType);
	}
	
	@Column(name = "BUSINESS_TYPE")
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
		addValidField(FieldNames.businessType);
	}
	
	
	
	/**
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="submitOrderUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
