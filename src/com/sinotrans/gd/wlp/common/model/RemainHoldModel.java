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
 * Model class for 可出库库存表
 */
@Entity
@Table(name = "REMAIN_HOLD")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class RemainHoldModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "RemainHold";

	public static final class FieldNames {
		/**
		 * INSTOCK_DETAIL_NO
		 */
		public static final String remainHoldUuid = "remainHoldUuid";
		/**
		 * 作业单货物明细UUID
		 */
		public static final String inLogisticsOrderDetailUuid = "inLogisticsOrderDetailUuid";
		/**
		 * INSTOCK_QTY
		 */
		public static final String instockQty = "instockQty";
		/**
		 * 第二包装单位
		 */
		public static final String instockUnitCode = "instockUnitCode";
		/**
		 * 第二包装单位
		 */
		public static final String instockUnitDesc = "instockUnitDesc";
		/**
		 * 第二包装数量
		 */
		public static final String instockSecondQty = "instockSecondQty";
		/**
		 * 第二包装数量
		 */
		public static final String instockSecondUnitCode = "instockSecondUnitCode";
		/**
		 * 第二包装单位
		 */
		public static final String instockSecondUnitDesc = "instockSecondUnitDesc";
		/**
		 * instockThirdQty
		 */
		public static final String instockThirdQty = "instockThirdQty";
		/**
		 * 第二包装数量
		 */
		public static final String instockThirdUnitCode = "instockThirdUnitCode";
		/**
		 * 第二包装单位
		 */
		public static final String instockThirdUnitDesc = "instockThirdUnitDesc";
		/**
		 * 净重
		 */
		public static final String instockNetWeight = "instockNetWeight";
		/**
		 * 毛重
		 */
		public static final String instockGrossWeight = "instockGrossWeight";
		/**
		 * INSTOCK_CBM
		 */
		public static final String instockVolume = "instockVolume";
		/**
		 * REMAIN_QTY
		 */
		public static final String remainQty = "remainQty";
		/**
		 * 第二包装数量
		 */
		public static final String remainSecondQty = "remainSecondQty";
		/**
		 * remainThirdQty
		 */
		public static final String remainThirdQty = "remainThirdQty";
		/**
		 * 毛重
		 */
		public static final String remainGrossWeight = "remainGrossWeight";
		/**
		 * 净重
		 */
		public static final String remainNetWeight = "remainNetWeight";
		/**
		 * REMAIN_CBM
		 */
		public static final String remainVolume = "remainVolume";
		/**
		 * holdQty
		 */
		public static final String holdQty = "holdQty";
		/**
		 * holdSecondQty
		 */
		public static final String holdSecondQty = "holdSecondQty";
		/**
		 * holdThirdQty
		 */
		public static final String holdThirdQty = "holdThirdQty";
		/**
		 * 毛重
		 */
		public static final String holdGrossWeight = "holdGrossWeight";
		/**
		 * 净重
		 */
		public static final String holdNetWeight = "holdNetWeight";
		/**
		 * REMAIN_CBM
		 */
		public static final String holdVolume = "holdVolume";
		/**
		 * 批次号
		 */
		public static final String batchNo = "batchNo";
		/**
		 * 货物编码
		 */
		public static final String itemCode = "itemCode";
		/**
		 * 物料序列号
		 */
		public static final String itemSeqno = "itemSeqno";
		/**
		 * 货物编码
		 */
		public static final String extItemCode = "extItemCode";
		/**
		 * 货物名称
		 */
		public static final String goodsDesc = "goodsDesc";
		/**
		 * 唛头
		 */
		public static final String marksNumber = "marksNumber";
		/**
		 * 货物条码
		 */
		public static final String barcode = "barcode";
		/**
		 * 包装编码（箱名号）
		 */
		public static final String packageNo = "packageNo";
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
		 * 货物属性
		 */
		public static final String goodsNature = "goodsNature";
		/**
		 * 货物种类
		 */
		public static final String goodsKind = "goodsKind";
		/**
		 * 物料生产日期
		 */
		public static final String productionDate = "productionDate";
	}

	//INSTOCK_DETAIL_NO
	private String remainHoldUuid;
	//作业单货物明细UUID
	private String inLogisticsOrderDetailUuid;
	//INSTOCK_QTY
	private Double instockQty;
	//第二包装单位
	private String instockUnitCode;
	//第二包装单位
	private String instockUnitDesc;
	//第二包装数量
	private Double instockSecondQty;
	//第二包装数量
	private String instockSecondUnitCode;
	//第二包装单位
	private String instockSecondUnitDesc;
	//instockThirdQty
	private Double instockThirdQty;
	//第二包装数量
	private String instockThirdUnitCode;
	//第二包装单位
	private String instockThirdUnitDesc;
	//净重
	private Double instockNetWeight;
	//毛重
	private Double instockGrossWeight;
	//INSTOCK_CBM
	private Double instockVolume;
	//REMAIN_QTY
	private Double remainQty;
	//第二包装数量
	private Double remainSecondQty;
	//remainThirdQty
	private Double remainThirdQty;
	//毛重
	private Double remainGrossWeight;
	//净重
	private Double remainNetWeight;
	//REMAIN_CBM
	private Double remainVolume;
	//holdQty
	private Double holdQty;
	//holdSecondQty
	private Double holdSecondQty;
	//holdThirdQty
	private Double holdThirdQty;
	//毛重
	private Double holdGrossWeight;
	//净重
	private Double holdNetWeight;
	//REMAIN_CBM
	private Double holdVolume;
	//批次号
	private String batchNo;
	//货物编码
	private String itemCode;
	//物料序列号
	private String itemSeqno;
	//货物编码
	private String extItemCode;
	//货物名称
	private String goodsDesc;
	//唛头
	private String marksNumber;
	//货物条码
	private String barcode;
	//包装编码（箱名号）
	private String packageNo;
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
	//货物属性
	private String goodsNature;
	//货物种类
	private String goodsKind;
	//物料生产日期
	private Date productionDate;

	/**
	 * Get INSTOCK_DETAIL_NO
	 */
	@Column(name = "REMAIN_HOLD_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getRemainHoldUuid() {
		return remainHoldUuid;
	}

	/**
	 * Set INSTOCK_DETAIL_NO
	 */
	public void setRemainHoldUuid(String remainHoldUuid) {
		this.remainHoldUuid = remainHoldUuid;
		addValidField(FieldNames.remainHoldUuid);
	}

	/**
	 * Get 作业单货物明细UUID
	 */
	@Column(name = "IN_LOGISTICS_ORDER_DETAIL_UUID")
	public String getInLogisticsOrderDetailUuid() {
		return inLogisticsOrderDetailUuid;
	}

	/**
	 * Set 作业单货物明细UUID
	 */
	public void setInLogisticsOrderDetailUuid(String inLogisticsOrderDetailUuid) {
		this.inLogisticsOrderDetailUuid = inLogisticsOrderDetailUuid;
		addValidField(FieldNames.inLogisticsOrderDetailUuid);
	}

	/**
	 * Get INSTOCK_QTY
	 */
	@Column(name = "INSTOCK_QTY")
	public Double getInstockQty() {
		return instockQty;
	}

	/**
	 * Set INSTOCK_QTY
	 */
	public void setInstockQty(Double instockQty) {
		this.instockQty = instockQty;
		addValidField(FieldNames.instockQty);
	}

	/**
	 * Get 第二包装单位
	 */
	@Column(name = "INSTOCK_UNIT_CODE")
	public String getInstockUnitCode() {
		return instockUnitCode;
	}

	/**
	 * Set 第二包装单位
	 */
	public void setInstockUnitCode(String instockUnitCode) {
		this.instockUnitCode = instockUnitCode;
		addValidField(FieldNames.instockUnitCode);
	}

	/**
	 * Get 第二包装单位
	 */
	@Column(name = "INSTOCK_UNIT_DESC")
	public String getInstockUnitDesc() {
		return instockUnitDesc;
	}

	/**
	 * Set 第二包装单位
	 */
	public void setInstockUnitDesc(String instockUnitDesc) {
		this.instockUnitDesc = instockUnitDesc;
		addValidField(FieldNames.instockUnitDesc);
	}

	/**
	 * Get 第二包装数量
	 */
	@Column(name = "INSTOCK_SECOND_QTY")
	public Double getInstockSecondQty() {
		return instockSecondQty;
	}

	/**
	 * Set 第二包装数量
	 */
	public void setInstockSecondQty(Double instockSecondQty) {
		this.instockSecondQty = instockSecondQty;
		addValidField(FieldNames.instockSecondQty);
	}

	/**
	 * Get 第二包装数量
	 */
	@Column(name = "INSTOCK_SECOND_UNIT_CODE")
	public String getInstockSecondUnitCode() {
		return instockSecondUnitCode;
	}

	/**
	 * Set 第二包装数量
	 */
	public void setInstockSecondUnitCode(String instockSecondUnitCode) {
		this.instockSecondUnitCode = instockSecondUnitCode;
		addValidField(FieldNames.instockSecondUnitCode);
	}

	/**
	 * Get 第二包装单位
	 */
	@Column(name = "INSTOCK_SECOND_UNIT_DESC")
	public String getInstockSecondUnitDesc() {
		return instockSecondUnitDesc;
	}

	/**
	 * Set 第二包装单位
	 */
	public void setInstockSecondUnitDesc(String instockSecondUnitDesc) {
		this.instockSecondUnitDesc = instockSecondUnitDesc;
		addValidField(FieldNames.instockSecondUnitDesc);
	}

	/**
	 * Get instockThirdQty
	 */
	@Column(name = "INSTOCK_THIRD_QTY")
	public Double getInstockThirdQty() {
		return instockThirdQty;
	}

	/**
	 * Set instockThirdQty
	 */
	public void setInstockThirdQty(Double instockThirdQty) {
		this.instockThirdQty = instockThirdQty;
		addValidField(FieldNames.instockThirdQty);
	}

	/**
	 * Get 第二包装数量
	 */
	@Column(name = "INSTOCK_THIRD_UNIT_CODE")
	public String getInstockThirdUnitCode() {
		return instockThirdUnitCode;
	}

	/**
	 * Set 第二包装数量
	 */
	public void setInstockThirdUnitCode(String instockThirdUnitCode) {
		this.instockThirdUnitCode = instockThirdUnitCode;
		addValidField(FieldNames.instockThirdUnitCode);
	}

	/**
	 * Get 第二包装单位
	 */
	@Column(name = "INSTOCK_THIRD_UNIT_DESC")
	public String getInstockThirdUnitDesc() {
		return instockThirdUnitDesc;
	}

	/**
	 * Set 第二包装单位
	 */
	public void setInstockThirdUnitDesc(String instockThirdUnitDesc) {
		this.instockThirdUnitDesc = instockThirdUnitDesc;
		addValidField(FieldNames.instockThirdUnitDesc);
	}

	/**
	 * Get 净重
	 */
	@Column(name = "INSTOCK_NET_WEIGHT")
	public Double getInstockNetWeight() {
		return instockNetWeight;
	}

	/**
	 * Set 净重
	 */
	public void setInstockNetWeight(Double instockNetWeight) {
		this.instockNetWeight = instockNetWeight;
		addValidField(FieldNames.instockNetWeight);
	}

	/**
	 * Get 毛重
	 */
	@Column(name = "INSTOCK_GROSS_WEIGHT")
	public Double getInstockGrossWeight() {
		return instockGrossWeight;
	}

	/**
	 * Set 毛重
	 */
	public void setInstockGrossWeight(Double instockGrossWeight) {
		this.instockGrossWeight = instockGrossWeight;
		addValidField(FieldNames.instockGrossWeight);
	}

	/**
	 * Get INSTOCK_CBM
	 */
	@Column(name = "INSTOCK_VOLUME")
	public Double getInstockVolume() {
		return instockVolume;
	}

	/**
	 * Set INSTOCK_CBM
	 */
	public void setInstockVolume(Double instockVolume) {
		this.instockVolume = instockVolume;
		addValidField(FieldNames.instockVolume);
	}

	/**
	 * Get REMAIN_QTY
	 */
	@Column(name = "REMAIN_QTY")
	public Double getRemainQty() {
		return remainQty;
	}

	/**
	 * Set REMAIN_QTY
	 */
	public void setRemainQty(Double remainQty) {
		this.remainQty = remainQty;
		addValidField(FieldNames.remainQty);
	}

	/**
	 * Get 第二包装数量
	 */
	@Column(name = "REMAIN_SECOND_QTY")
	public Double getRemainSecondQty() {
		return remainSecondQty;
	}

	/**
	 * Set 第二包装数量
	 */
	public void setRemainSecondQty(Double remainSecondQty) {
		this.remainSecondQty = remainSecondQty;
		addValidField(FieldNames.remainSecondQty);
	}

	/**
	 * Get remainThirdQty
	 */
	@Column(name = "REMAIN_THIRD_QTY")
	public Double getRemainThirdQty() {
		return remainThirdQty;
	}

	/**
	 * Set remainThirdQty
	 */
	public void setRemainThirdQty(Double remainThirdQty) {
		this.remainThirdQty = remainThirdQty;
		addValidField(FieldNames.remainThirdQty);
	}

	/**
	 * Get 毛重
	 */
	@Column(name = "REMAIN_GROSS_WEIGHT")
	public Double getRemainGrossWeight() {
		return remainGrossWeight;
	}

	/**
	 * Set 毛重
	 */
	public void setRemainGrossWeight(Double remainGrossWeight) {
		this.remainGrossWeight = remainGrossWeight;
		addValidField(FieldNames.remainGrossWeight);
	}

	/**
	 * Get 净重
	 */
	@Column(name = "REMAIN_NET_WEIGHT")
	public Double getRemainNetWeight() {
		return remainNetWeight;
	}

	/**
	 * Set 净重
	 */
	public void setRemainNetWeight(Double remainNetWeight) {
		this.remainNetWeight = remainNetWeight;
		addValidField(FieldNames.remainNetWeight);
	}

	/**
	 * Get REMAIN_CBM
	 */
	@Column(name = "REMAIN_VOLUME")
	public Double getRemainVolume() {
		return remainVolume;
	}

	/**
	 * Set REMAIN_CBM
	 */
	public void setRemainVolume(Double remainVolume) {
		this.remainVolume = remainVolume;
		addValidField(FieldNames.remainVolume);
	}

	/**
	 * Get holdQty
	 */
	@Column(name = "HOLD_QTY")
	public Double getHoldQty() {
		return holdQty;
	}

	/**
	 * Set holdQty
	 */
	public void setHoldQty(Double holdQty) {
		this.holdQty = holdQty;
		addValidField(FieldNames.holdQty);
	}

	/**
	 * Get holdSecondQty
	 */
	@Column(name = "HOLD_SECOND_QTY")
	public Double getHoldSecondQty() {
		return holdSecondQty;
	}

	/**
	 * Set holdSecondQty
	 */
	public void setHoldSecondQty(Double holdSecondQty) {
		this.holdSecondQty = holdSecondQty;
		addValidField(FieldNames.holdSecondQty);
	}

	/**
	 * Get holdThirdQty
	 */
	@Column(name = "HOLD_THIRD_QTY")
	public Double getHoldThirdQty() {
		return holdThirdQty;
	}

	/**
	 * Set holdThirdQty
	 */
	public void setHoldThirdQty(Double holdThirdQty) {
		this.holdThirdQty = holdThirdQty;
		addValidField(FieldNames.holdThirdQty);
	}

	/**
	 * Get 毛重
	 */
	@Column(name = "HOLD_GROSS_WEIGHT")
	public Double getHoldGrossWeight() {
		return holdGrossWeight;
	}

	/**
	 * Set 毛重
	 */
	public void setHoldGrossWeight(Double holdGrossWeight) {
		this.holdGrossWeight = holdGrossWeight;
		addValidField(FieldNames.holdGrossWeight);
	}

	/**
	 * Get 净重
	 */
	@Column(name = "HOLD_NET_WEIGHT")
	public Double getHoldNetWeight() {
		return holdNetWeight;
	}

	/**
	 * Set 净重
	 */
	public void setHoldNetWeight(Double holdNetWeight) {
		this.holdNetWeight = holdNetWeight;
		addValidField(FieldNames.holdNetWeight);
	}

	/**
	 * Get REMAIN_CBM
	 */
	@Column(name = "HOLD_VOLUME")
	public Double getHoldVolume() {
		return holdVolume;
	}

	/**
	 * Set REMAIN_CBM
	 */
	public void setHoldVolume(Double holdVolume) {
		this.holdVolume = holdVolume;
		addValidField(FieldNames.holdVolume);
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
	 * Get 货物编码
	 */
	@Column(name = "EXT_ITEM_CODE")
	public String getExtItemCode() {
		return extItemCode;
	}

	/**
	 * Set 货物编码
	 */
	public void setExtItemCode(String extItemCode) {
		this.extItemCode = extItemCode;
		addValidField(FieldNames.extItemCode);
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
	 * Get 货物条码
	 */
	@Column(name = "BARCODE")
	public String getBarcode() {
		return barcode;
	}

	/**
	 * Set 货物条码
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
		addValidField(FieldNames.barcode);
	}

	/**
	 * Get 包装编码（箱名号）
	 */
	@Column(name = "PACKAGE_NO")
	public String getPackageNo() {
		return packageNo;
	}

	/**
	 * Set 包装编码（箱名号）
	 */
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
		addValidField(FieldNames.packageNo);
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
	 * Get 货物属性
	 */
	@Column(name = "GOODS_NATURE")
	public String getGoodsNature() {
		return goodsNature;
	}

	/**
	 * Set 货物属性
	 */
	public void setGoodsNature(String goodsNature) {
		this.goodsNature = goodsNature;
		addValidField(FieldNames.goodsNature);
	}

	/**
	 * Get 货物种类
	 */
	@Column(name = "GOODS_KIND")
	public String getGoodsKind() {
		return goodsKind;
	}

	/**
	 * Set 货物种类
	 */
	public void setGoodsKind(String goodsKind) {
		this.goodsKind = goodsKind;
		addValidField(FieldNames.goodsKind);
	}

	/**
	 * Get 物料生产日期
	 */
	@Column(name = "PRODUCTION_DATE")
	public Date getProductionDate() {
		return productionDate;
	}

	/**
	 * Set 物料生产日期
	 */
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
		addValidField(FieldNames.productionDate);
	}

	
	
	/**
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="remainHoldUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
