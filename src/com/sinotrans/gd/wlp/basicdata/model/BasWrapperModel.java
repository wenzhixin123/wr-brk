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
 * Model class for 包材信息
 */
@Entity
@Table(name = "BAS_WRAPPER")
public class BasWrapperModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasWrapper";

	public static final class FieldNames {
		/**
		 * 包材UUID
		 */
		public static final String basWrapperUuid = "basWrapperUuid";
		/**
		 * 物料主表UUID
		 */
		public static final String itemMasterUuid = "itemMasterUuid";
		/**
		 * 货物编码
		 */
		public static final String itemCode = "itemCode";
		/**
		 * 包材编码
		 */
		public static final String wrapperCode = "wrapperCode";
		/**
		 * 包材名称
		 */
		public static final String wrapperName = "wrapperName";
		/**
		 * 英文名
		 */
		public static final String wrapperNameCn = "wrapperNameCn";
		/**
		 * 长度单位
		 */
		public static final String lengthUnitCode = "lengthUnitCode";
		/**
		 * 长度单位
		 */
		public static final String lengthUnitDesc = "lengthUnitDesc";
		/**
		 * 长
		 */
		public static final String length = "length";
		/**
		 * 宽
		 */
		public static final String width = "width";
		/**
		 * 高
		 */
		public static final String height = "height";
		/**
		 * 体积单位
		 */
		public static final String volumeUnitCode = "volumeUnitCode";
		/**
		 * 体积单位描述
		 */
		public static final String volumeUnitDesc = "volumeUnitDesc";
		/**
		 * VOLUME
		 */
		public static final String volume = "volume";
		/**
		 * 重量单位
		 */
		public static final String weightUnitCode = "weightUnitCode";
		/**
		 * 重量单位描述
		 */
		public static final String weightUnitDesc = "weightUnitDesc";
		/**
		 * 净重
		 */
		public static final String netWeight = "netWeight";
		/**
		 * 毛重
		 */
		public static final String grossWeight = "grossWeight";
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
		/**
		 * 打印内部SPU标签个数
		 */
		public static final String printSpuNum = "printSpuNum";
		/**
		 * 打印华为标签个数
		 */
		public static final String printHwNum = "printHwNum";
		/**
		 * 包材服务类代码
		 */
		public static final String serviceCode = "serviceCode";
		/**
		 * 中心代码
		 */
		public static final String centerCode = "centerCode";
		/**
		 * 主作业项目
		 */
		public static final String mainItem = "mainItem";
		/**
		 * 单位
		 */
		public static final String unit = "unit";
		/**
		 * 是
		 */
		public static final String isInt = "isInt";
		/**
		 * 包材类型
		 */
		public static final String wrapperType = "wrapperType";
	}

	//包材UUID
	private String basWrapperUuid;
	//物料主表UUID
	private String itemMasterUuid;
	//货物编码
	private String itemCode;
	//包材编码
	private String wrapperCode;
	//包材名称
	private String wrapperName;
	//英文名
	private String wrapperNameCn;
	//长度单位
	private String lengthUnitCode;
	//长度单位
	private String lengthUnitDesc;
	//长
	private Double length;
	//宽
	private Double width;
	//高
	private Double height;
	//体积单位
	private String volumeUnitCode;
	//体积单位描述
	private String volumeUnitDesc;
	//VOLUME
	private Double volume;
	//重量单位
	private String weightUnitCode;
	//重量单位描述
	private String weightUnitDesc;
	//净重
	private Double netWeight;
	//毛重
	private Double grossWeight;
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
	//打印内部SPU标签个数
	private Integer printSpuNum;
	//打印华为标签个数
	private Integer printHwNum;
	//包材服务类代码
	private String serviceCode;
	//中心代码
	private String centerCode;
	//主作业项目
	private String mainItem;
	//单位
	private String unit;
	//是
	private String isInt;
	
	//包材类型
    private String wrapperType;

	/**
	 * Get 包材UUID
	 */
	@Column(name = "BAS_WRAPPER_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getBasWrapperUuid() {
		return basWrapperUuid;
	}

	/**
	 * Set 包材UUID
	 */
	public void setBasWrapperUuid(String basWrapperUuid) {
		this.basWrapperUuid = basWrapperUuid;
		addValidField(FieldNames.basWrapperUuid);
	}

	/**
	 * Get 物料主表UUID
	 */
	@Column(name = "ITEM_MASTER_UUID")
	public String getItemMasterUuid() {
		return itemMasterUuid;
	}

	/**
	 * Set 物料主表UUID
	 */
	public void setItemMasterUuid(String itemMasterUuid) {
		this.itemMasterUuid = itemMasterUuid;
		addValidField(FieldNames.itemMasterUuid);
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
	 * Get 包材编码
	 */
	@Column(name = "WRAPPER_CODE")
	public String getWrapperCode() {
		return wrapperCode;
	}

	/**
	 * Set 包材编码
	 */
	public void setWrapperCode(String wrapperCode) {
		this.wrapperCode = wrapperCode;
		addValidField(FieldNames.wrapperCode);
	}

	/**
	 * Get 包材名称
	 */
	@Column(name = "WRAPPER_NAME")
	public String getWrapperName() {
		return wrapperName;
	}

	/**
	 * Set 包材名称
	 */
	public void setWrapperName(String wrapperName) {
		this.wrapperName = wrapperName;
		addValidField(FieldNames.wrapperName);
	}

	/**
	 * Get 英文名
	 */
	@Column(name = "WRAPPER_NAME_CN")
	public String getWrapperNameCn() {
		return wrapperNameCn;
	}

	/**
	 * Set 英文名
	 */
	public void setWrapperNameCn(String wrapperNameCn) {
		this.wrapperNameCn = wrapperNameCn;
		addValidField(FieldNames.wrapperNameCn);
	}

	/**
	 * Get 长度单位
	 */
	@Column(name = "LENGTH_UNIT_CODE")
	public String getLengthUnitCode() {
		return lengthUnitCode;
	}

	/**
	 * Set 长度单位
	 */
	public void setLengthUnitCode(String lengthUnitCode) {
		this.lengthUnitCode = lengthUnitCode;
		addValidField(FieldNames.lengthUnitCode);
	}

	/**
	 * Get 长度单位
	 */
	@Column(name = "LENGTH_UNIT_DESC")
	public String getLengthUnitDesc() {
		return lengthUnitDesc;
	}

	/**
	 * Set 长度单位
	 */
	public void setLengthUnitDesc(String lengthUnitDesc) {
		this.lengthUnitDesc = lengthUnitDesc;
		addValidField(FieldNames.lengthUnitDesc);
	}

	/**
	 * Get 长
	 */
	@Column(name = "LENGTH")
	public Double getLength() {
		return length;
	}

	/**
	 * Set 长
	 */
	public void setLength(Double length) {
		this.length = length;
		addValidField(FieldNames.length);
	}

	/**
	 * Get 宽
	 */
	@Column(name = "WIDTH")
	public Double getWidth() {
		return width;
	}

	/**
	 * Set 宽
	 */
	public void setWidth(Double width) {
		this.width = width;
		addValidField(FieldNames.width);
	}

	/**
	 * Get 高
	 */
	@Column(name = "HEIGHT")
	public Double getHeight() {
		return height;
	}

	/**
	 * Set 高
	 */
	public void setHeight(Double height) {
		this.height = height;
		addValidField(FieldNames.height);
	}

	/**
	 * Get 体积单位
	 */
	@Column(name = "VOLUME_UNIT_CODE")
	public String getVolumeUnitCode() {
		return volumeUnitCode;
	}

	/**
	 * Set 体积单位
	 */
	public void setVolumeUnitCode(String volumeUnitCode) {
		this.volumeUnitCode = volumeUnitCode;
		addValidField(FieldNames.volumeUnitCode);
	}

	/**
	 * Get 体积单位描述
	 */
	@Column(name = "VOLUME_UNIT_DESC")
	public String getVolumeUnitDesc() {
		return volumeUnitDesc;
	}

	/**
	 * Set 体积单位描述
	 */
	public void setVolumeUnitDesc(String volumeUnitDesc) {
		this.volumeUnitDesc = volumeUnitDesc;
		addValidField(FieldNames.volumeUnitDesc);
	}

	/**
	 * Get VOLUME
	 * 体积(立方米)
	 */
	@Column(name = "VOLUME")
	public Double getVolume() {
		return volume;
	}

	/**
	 * Set VOLUME
	 * 体积(立方米)
	 */
	public void setVolume(Double volume) {
		this.volume = volume;
		addValidField(FieldNames.volume);
	}

	/**
	 * Get 重量单位
	 */
	@Column(name = "WEIGHT_UNIT_CODE")
	public String getWeightUnitCode() {
		return weightUnitCode;
	}

	/**
	 * Set 重量单位
	 */
	public void setWeightUnitCode(String weightUnitCode) {
		this.weightUnitCode = weightUnitCode;
		addValidField(FieldNames.weightUnitCode);
	}

	/**
	 * Get 重量单位描述
	 */
	@Column(name = "WEIGHT_UNIT_DESC")
	public String getWeightUnitDesc() {
		return weightUnitDesc;
	}

	/**
	 * Set 重量单位描述
	 */
	public void setWeightUnitDesc(String weightUnitDesc) {
		this.weightUnitDesc = weightUnitDesc;
		addValidField(FieldNames.weightUnitDesc);
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
	 * Get 毛重
	 */
	@Column(name = "GROSS_WEIGHT")
	public Double getGrossWeight() {
		return grossWeight;
	}

	/**
	 * Set 毛重
	 */
	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
		addValidField(FieldNames.grossWeight);
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
	 * Get 打印内部SPU标签个数
	 */
	@Column(name = "PRINT_SPU_NUM")
	public Integer getPrintSpuNum() {
		return printSpuNum;
	}

	/**
	 * Set 打印内部SPU标签个数
	 */
	public void setPrintSpuNum(Integer printSpuNum) {
		this.printSpuNum = printSpuNum;
		addValidField(FieldNames.printSpuNum);
	}

	/**
	 * Get 打印华为标签个数
	 */
	@Column(name = "PRINT_HW_NUM")
	public Integer getPrintHwNum() {
		return printHwNum;
	}

	/**
	 * Set 打印华为标签个数
	 */
	public void setPrintHwNum(Integer printHwNum) {
		this.printHwNum = printHwNum;
		addValidField(FieldNames.printHwNum);
	}

	/**
	 * Get 包材服务类代码
	 * ：折盒子（FOLD_BOX）
	 */
	@Column(name = "SERVICE_CODE")
	public String getServiceCode() {
		return serviceCode;
	}

	/**
	 * Set 包材服务类代码
	 * ：折盒子（FOLD_BOX）
	 */
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
		addValidField(FieldNames.serviceCode);
	}

	/**
	 * Get 中心代码
	 */
	@Column(name = "CENTER_CODE")
	public String getCenterCode() {
		return centerCode;
	}

	/**
	 * Set 中心代码
	 */
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
		addValidField(FieldNames.centerCode);
	}

	/**
	 * Get 主作业项目
	 */
	@Column(name = "MAIN_ITEM")
	public String getMainItem() {
		return mainItem;
	}

	/**
	 * Set 主作业项目
	 */
	public void setMainItem(String mainItem) {
		this.mainItem = mainItem;
		addValidField(FieldNames.mainItem);
	}

	/**
	 * Get 单位
	 */
	@Column(name = "UNIT")
	public String getUnit() {
		return unit;
	}

	/**
	 * Set 单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
		addValidField(FieldNames.unit);
	}

	/**
	 * Get 是
	 * :Y
	 */
	@Column(name = "IS_INT")
	public String getIsInt() {
		return isInt;
	}

	/**
	 * Set 是
	 * :Y
	 */
	public void setIsInt(String isInt) {
		this.isInt = isInt;
		addValidField(FieldNames.isInt);
	}
	
	

	/**
	 * Get 包材类型
	 */
	@Column(name = "WRAPPER_TYPE")
	public String getWrapperType() {
		return wrapperType;
	}

	/**
	 * Set 包材类型
	 */
	public void setWrapperType(String wrapperType) {
		this.wrapperType = wrapperType;
		addValidField(FieldNames.wrapperType);
	}
  
	/**
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="basWrapperUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
