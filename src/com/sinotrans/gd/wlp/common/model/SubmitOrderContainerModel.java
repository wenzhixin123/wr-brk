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
 * Model class for 委托单集装箱信息
 */
@Entity
@Table(name = "SUBMIT_ORDER_CONTAINER")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SubmitOrderContainerModel extends BaseModelClass implements
		OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "SubmitOrderContainer";

	public static final class FieldNames {
		/**
		 * 主键
		 */
		public static final String submitOrderConUuid = "submitOrderConUuid";
		/**
		 * 委托单UUID号
		 */
		public static final String submitOrderUuid = "submitOrderUuid";
		/**
		 * 委托单号
		 */
		public static final String submitOrderNo = "submitOrderNo";
		/**
		 * 集装箱号
		 */
		public static final String containerNo = "containerNo";
		/**
		 * 集装箱规格型号
		 */
		public static final String containerType = "containerType";
		/**
		 * 箱状态
		 */
		public static final String containerState = "containerState";
		/**
		 * 封条号
		 */
		public static final String sealNo = "sealNo";
		/**
		 * 第二封条号
		 */
		public static final String subSealNo = "subSealNo";
		/**
		 * SO号
		 */
		public static final String soNo = "soNo";
		/**
		 * 控箱公司
		 */
		public static final String adminCode = "adminCode";
		/**
		 * 控箱公司描述
		 */
		public static final String adminDesc = "adminDesc";
		/**
		 * 箱属公司
		 */
		public static final String ownerCode = "ownerCode";
		/**
		 * 箱属公司描述
		 */
		public static final String ownerDesc = "ownerDesc";
		/**
		 * 使用单位
		 */
		public static final String usingCode = "usingCode";
		/**
		 * 使用部门描述
		 */
		public static final String usingDesc = "usingDesc";
		/**
		 * 场内堆位
		 */
		public static final String pilePlace = "pilePlace";
		/**
		 * 控制字（默认为0）
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

	// 主键
	private String submitOrderConUuid;
	// 委托单UUID号
	private String submitOrderUuid;
	// 委托单号
	private String submitOrderNo;
	// 集装箱号
	private String containerNo;
	// 集装箱规格型号
	private String containerType;
	// 箱状态
	private String containerState;
	// 封条号
	private String sealNo;
	// 第二封条号
	private String subSealNo;
	// SO号
	private String soNo;
	// 控箱公司
	private String adminCode;
	// 控箱公司描述
	private String adminDesc;
	// 箱属公司
	private String ownerCode;
	// 箱属公司描述
	private String ownerDesc;
	// 使用单位
	private String usingCode;
	// 使用部门描述
	private String usingDesc;
	// 场内堆位
	private String pilePlace;
	// 控制字（默认为0）
	private String controlWord;
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

	/**
	 * Get 主键
	 */
	@Column(name = "SUBMIT_ORDER_CON_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getSubmitOrderConUuid() {
		return submitOrderConUuid;
	}

	/**
	 * Set 主键
	 */
	public void setSubmitOrderConUuid(String submitOrderConUuid) {
		this.submitOrderConUuid = submitOrderConUuid;
		addValidField(FieldNames.submitOrderConUuid);
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
	 * Get 集装箱号
	 */
	@Column(name = "CONTAINER_NO")
	public String getContainerNo() {
		return containerNo;
	}

	/**
	 * Set 集装箱号
	 */
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
		addValidField(FieldNames.containerNo);
	}

	/**
	 * Get 集装箱规格型号
	 */
	@Column(name = "CONTAINER_TYPE")
	public String getContainerType() {
		return containerType;
	}

	/**
	 * Set 集装箱规格型号
	 */
	public void setContainerType(String containerType) {
		this.containerType = containerType;
		addValidField(FieldNames.containerType);
	}

	/**
	 * Get 箱状态 ：F-重箱、E-吉箱
	 */
	@Column(name = "CONTAINER_STATE")
	public String getContainerState() {
		return containerState;
	}

	/**
	 * Set 箱状态 ：F-重箱、E-吉箱
	 */
	public void setContainerState(String containerState) {
		this.containerState = containerState;
		addValidField(FieldNames.containerState);
	}

	/**
	 * Get 封条号
	 */
	@Column(name = "SEAL_NO")
	public String getSealNo() {
		return sealNo;
	}

	/**
	 * Set 封条号
	 */
	public void setSealNo(String sealNo) {
		this.sealNo = sealNo;
		addValidField(FieldNames.sealNo);
	}

	/**
	 * Get 第二封条号
	 */
	@Column(name = "SUB_SEAL_NO")
	public String getSubSealNo() {
		return subSealNo;
	}

	/**
	 * Set 第二封条号
	 */
	public void setSubSealNo(String subSealNo) {
		this.subSealNo = subSealNo;
		addValidField(FieldNames.subSealNo);
	}

	/**
	 * Get SO号
	 */
	@Column(name = "SO_NO")
	public String getSoNo() {
		return soNo;
	}

	/**
	 * Set SO号
	 */
	public void setSoNo(String soNo) {
		this.soNo = soNo;
		addValidField(FieldNames.soNo);
	}

	/**
	 * Get 控箱公司
	 */
	@Column(name = "ADMIN_CODE")
	public String getAdminCode() {
		return adminCode;
	}

	/**
	 * Set 控箱公司
	 */
	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
		addValidField(FieldNames.adminCode);
	}

	/**
	 * Get 控箱公司描述
	 */
	@Column(name = "ADMIN_DESC")
	public String getAdminDesc() {
		return adminDesc;
	}

	/**
	 * Set 控箱公司描述
	 */
	public void setAdminDesc(String adminDesc) {
		this.adminDesc = adminDesc;
		addValidField(FieldNames.adminDesc);
	}

	/**
	 * Get 箱属公司
	 */
	@Column(name = "OWNER_CODE")
	public String getOwnerCode() {
		return ownerCode;
	}

	/**
	 * Set 箱属公司
	 */
	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
		addValidField(FieldNames.ownerCode);
	}

	/**
	 * Get 箱属公司描述
	 */
	@Column(name = "OWNER_DESC")
	public String getOwnerDesc() {
		return ownerDesc;
	}

	/**
	 * Set 箱属公司描述
	 */
	public void setOwnerDesc(String ownerDesc) {
		this.ownerDesc = ownerDesc;
		addValidField(FieldNames.ownerDesc);
	}

	/**
	 * Get 使用单位
	 */
	@Column(name = "USING_CODE")
	public String getUsingCode() {
		return usingCode;
	}

	/**
	 * Set 使用单位
	 */
	public void setUsingCode(String usingCode) {
		this.usingCode = usingCode;
		addValidField(FieldNames.usingCode);
	}

	/**
	 * Get 使用部门描述
	 */
	@Column(name = "USING_DESC")
	public String getUsingDesc() {
		return usingDesc;
	}

	/**
	 * Set 使用部门描述
	 */
	public void setUsingDesc(String usingDesc) {
		this.usingDesc = usingDesc;
		addValidField(FieldNames.usingDesc);
	}

	/**
	 * Get 场内堆位
	 */
	@Column(name = "PILE_PLACE")
	public String getPilePlace() {
		return pilePlace;
	}

	/**
	 * Set 场内堆位
	 */
	public void setPilePlace(String pilePlace) {
		this.pilePlace = pilePlace;
		addValidField(FieldNames.pilePlace);
	}

	/**
	 * Get 控制字（默认为0）
	 */
	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	/**
	 * Set 控制字（默认为0）
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
	private String prrmaryKeyName="submitOrderConUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}


}
