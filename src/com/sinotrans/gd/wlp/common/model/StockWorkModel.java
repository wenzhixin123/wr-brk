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
 * Model class for 库内操作记录表
 */
@Entity
@Table(name = "STOCK_WORK")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class StockWorkModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "StockWork";

	public static final class FieldNames {
		/**
		 * UUID
		 */
		public static final String stockWorkUuid = "stockWorkUuid";
		/**
		 * 作业任务表UUID
		 */
		public static final String locationTaskUuid = "locationTaskUuid";
		/**
		 * 库存操作STOCK_WORK的UUID
		 */
		public static final String inStockWorkUuid = "inStockWorkUuid";
		/**
		 * 操作时间
		 */
		public static final String stockDate = "stockDate";
		/**
		 * 操作类型
		 */
		public static final String stockType = "stockType";
		/**
		 * 操作描述
		 */
		public static final String stockDesc = "stockDesc";
		/**
		 * 数量
		 */
		public static final String qty = "qty";
		/**
		 * 第二包装数量
		 */
		public static final String secondQty = "secondQty";
		/**
		 * 第三包装数量
		 */
		public static final String thirdQty = "thirdQty";
		/**
		 * 净重
		 */
		public static final String netWeight = "netWeight";
		/**
		 * 重量
		 */
		public static final String grossWeight = "grossWeight";
		/**
		 * 体积
		 */
		public static final String volume = "volume";
		/**
		 * 上条次操记录UUID
		 */
		public static final String lastStockWorkUuid = "lastStockWorkUuid";
		/**
		 * 货位
		 */
		public static final String lotCode = "lotCode";
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

	//UUID
	private String stockWorkUuid;
	//作业任务表UUID
	private String locationTaskUuid;
	//库存操作STOCK_WORK的UUID
	private String inStockWorkUuid;
	//操作时间
	private Date stockDate;
	//操作类型
	private String stockType;
	//操作描述
	private String stockDesc;
	//数量
	private Double qty;
	//第二包装数量
	private Double secondQty;
	//第三包装数量
	private Double thirdQty;
	//净重
	private Double netWeight;
	//重量
	private Double grossWeight;
	//体积
	private Double volume;
	//上条次操记录UUID
	private String lastStockWorkUuid;
	//货位
	private String lotCode;
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
	 * Get UUID
	 */
	@Column(name = "STOCK_WORK_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getStockWorkUuid() {
		return stockWorkUuid;
	}

	/**
	 * Set UUID
	 */
	public void setStockWorkUuid(String stockWorkUuid) {
		this.stockWorkUuid = stockWorkUuid;
		addValidField(FieldNames.stockWorkUuid);
	}

	/**
	 * Get 作业任务表UUID
	 */
	@Column(name = "LOCATION_TASK_UUID")
	public String getLocationTaskUuid() {
		return locationTaskUuid;
	}

	/**
	 * Set 作业任务表UUID
	 */
	public void setLocationTaskUuid(String locationTaskUuid) {
		this.locationTaskUuid = locationTaskUuid;
		addValidField(FieldNames.locationTaskUuid);
	}

	/**
	 * Get 库存操作STOCK_WORK的UUID
	 */
	@Column(name = "IN_STOCK_WORK_UUID")
	public String getInStockWorkUuid() {
		return inStockWorkUuid;
	}

	/**
	 * Set 库存操作STOCK_WORK的UUID
	 */
	public void setInStockWorkUuid(String inStockWorkUuid) {
		this.inStockWorkUuid = inStockWorkUuid;
		addValidField(FieldNames.inStockWorkUuid);
	}

	/**
	 * Get 操作时间
	 */
	@Column(name = "STOCK_DATE")
	public Date getStockDate() {
		return stockDate;
	}

	/**
	 * Set 操作时间
	 */
	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
		addValidField(FieldNames.stockDate);
	}

	/**
	 * Get 操作类型
	 */
	@Column(name = "STOCK_TYPE")
	public String getStockType() {
		return stockType;
	}

	/**
	 * Set 操作类型
	 */
	public void setStockType(String stockType) {
		this.stockType = stockType;
		addValidField(FieldNames.stockType);
	}

	/**
	 * Get 操作描述
	 */
	@Column(name = "STOCK_DESC")
	public String getStockDesc() {
		return stockDesc;
	}

	/**
	 * Set 操作描述
	 */
	public void setStockDesc(String stockDesc) {
		this.stockDesc = stockDesc;
		addValidField(FieldNames.stockDesc);
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
	 * Get 第二包装数量
	 */
	@Column(name = "SECOND_QTY")
	public Double getSecondQty() {
		return secondQty;
	}

	/**
	 * Set 第二包装数量
	 */
	public void setSecondQty(Double secondQty) {
		this.secondQty = secondQty;
		addValidField(FieldNames.secondQty);
	}

	/**
	 * Get 第三包装数量
	 */
	@Column(name = "THIRD_QTY")
	public Double getThirdQty() {
		return thirdQty;
	}

	/**
	 * Set 第三包装数量
	 */
	public void setThirdQty(Double thirdQty) {
		this.thirdQty = thirdQty;
		addValidField(FieldNames.thirdQty);
	}

	/**
	 * Get 净重
	 */
	@Column(name = "NET_WEIGHT")
	public Double getNetWeight() {
		return netWeight;
	}

	/**
	 * Set 净重
	 */
	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
		addValidField(FieldNames.netWeight);
	}

	/**
	 * Get 重量
	 */
	@Column(name = "GROSS_WEIGHT")
	public Double getGrossWeight() {
		return grossWeight;
	}

	/**
	 * Set 重量
	 */
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
		addValidField(FieldNames.grossWeight);
	}

	/**
	 * Get 体积
	 */
	@Column(name = "VOLUME")
	public Double getVolume() {
		return volume;
	}

	/**
	 * Set 体积
	 */
	public void setVolume(Double volume) {
		this.volume = volume;
		addValidField(FieldNames.volume);
	}

	/**
	 * Get 上条次操记录UUID
	 */
	@Column(name = "LAST_STOCK_WORK_UUID")
	public String getLastStockWorkUuid() {
		return lastStockWorkUuid;
	}

	/**
	 * Set 上条次操记录UUID
	 */
	public void setLastStockWorkUuid(String lastStockWorkUuid) {
		this.lastStockWorkUuid = lastStockWorkUuid;
		addValidField(FieldNames.lastStockWorkUuid);
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
	@Column(name = "CREATE_TIME", updatable=false)
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
	private String prrmaryKeyName="stockWorkUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
