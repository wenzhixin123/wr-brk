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
 * Model class for 条码作业记录
 */
@Entity
@Table(name = "BARCODE_WORK")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BarcodeWorkModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BarcodeWork";

	public static final class FieldNames {
		/**
		 * 条码明细表UUID
		 */
		public static final String barcodeWorkUuid = "barcodeWorkUuid";
		/**
		 * 条码表UUID
		 */
		public static final String barcodeUuid = "barcodeUuid";
		/**
		 * 应急发货单号
		 */
		public static final String orderNo = "orderNo";
		/**
		 * 作业类型
		 */
		public static final String typeWork = "typeWork";
		/**
		 * OUTV
		 */
		public static final String typeDesc = "typeDesc";
		/**
		 * 作业日期
		 */
		public static final String dateWork = "dateWork";
		/**
		 * 条码（包装条码、物料条码、托盘条码等）
		 */
		public static final String barcode = "barcode";
		/**
		 * 数量
		 */
		public static final String qty = "qty";
		/**
		 * 货位
		 */
		public static final String lotCode = "lotCode";
		/**
		 * 仓管员
		 */
		public static final String wrhWorker = "wrhWorker";
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

	//条码明细表UUID
	private String barcodeWorkUuid;
	//条码表UUID
	private String barcodeUuid;
	//应急发货单号
	private String orderNo;
	//作业类型
	private String typeWork;
	//OUTV
	private String typeDesc;
	//作业日期
	private Date dateWork;
	//条码（包装条码、物料条码、托盘条码等）
	private String barcode;
	//数量
	private Double qty;
	//货位
	private String lotCode;
	//仓管员
	private String wrhWorker;
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
	 * Get 条码明细表UUID
	 */
	@Column(name = "BARCODE_WORK_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getBarcodeWorkUuid() {
		return barcodeWorkUuid;
	}

	/**
	 * Set 条码明细表UUID
	 */
	public void setBarcodeWorkUuid(String barcodeWorkUuid) {
		this.barcodeWorkUuid = barcodeWorkUuid;
		addValidField(FieldNames.barcodeWorkUuid);
	}

	/**
	 * Get 条码表UUID
	 */
	@Column(name = "BARCODE_UUID")
	public String getBarcodeUuid() {
		return barcodeUuid;
	}

	/**
	 * Set 条码表UUID
	 */
	public void setBarcodeUuid(String barcodeUuid) {
		this.barcodeUuid = barcodeUuid;
		addValidField(FieldNames.barcodeUuid);
	}

	/**
	 * Get 应急发货单号
	 */
	@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * Set 应急发货单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
		addValidField(FieldNames.orderNo);
	}

	/**
	 * Get 作业类型
	 * ：OUTV - 应急发货
	 */
	@Column(name = "TYPE_WORK")
	public String getTypeWork() {
		return typeWork;
	}

	/**
	 * Set 作业类型
	 * ：OUTV - 应急发货
	 */
	public void setTypeWork(String typeWork) {
		this.typeWork = typeWork;
		addValidField(FieldNames.typeWork);
	}

	/**
	 * Get OUTV
	 * - 应急发货
	 */
	@Column(name = "TYPE_DESC")
	public String getTypeDesc() {
		return typeDesc;
	}

	/**
	 * Set OUTV
	 * - 应急发货
	 */
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
		addValidField(FieldNames.typeDesc);
	}

	/**
	 * Get 作业日期
	 */
	@Column(name = "DATE_WORK")
	public Date getDateWork() {
		return dateWork;
	}

	/**
	 * Set 作业日期
	 */
	public void setDateWork(Date dateWork) {
		this.dateWork = dateWork;
		addValidField(FieldNames.dateWork);
	}

	/**
	 * Get 条码（包装条码、物料条码、托盘条码等）
	 */
	@Column(name = "BARCODE")
	public String getBarcode() {
		return barcode;
	}

	/**
	 * Set 条码（包装条码、物料条码、托盘条码等）
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
		addValidField(FieldNames.barcode);
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
	 * Get 货位
	 */
	@Column(name = "LOT_CODE")
	public String getLotCode() {
		return lotCode;
	}

	/**
	 * Set 货位
	 */
	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
		addValidField(FieldNames.lotCode);
	}

	/**
	 * Get 仓管员
	 */
	@Column(name = "WRH_WORKER")
	public String getWrhWorker() {
		return wrhWorker;
	}

	/**
	 * Set 仓管员
	 */
	public void setWrhWorker(String wrhWorker) {
		this.wrhWorker = wrhWorker;
		addValidField(FieldNames.wrhWorker);
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
	private String prrmaryKeyName="bargainRateUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
