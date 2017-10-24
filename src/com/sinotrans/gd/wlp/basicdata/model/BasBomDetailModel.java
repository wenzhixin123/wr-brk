package com.sinotrans.gd.wlp.basicdata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.sinotrans.framework.core.model.BaseModelClass;
import com.sinotrans.framework.core.model.OperationLog;

/**
 * Model class for 物料清单组件
 */
@Entity
@Table(name = "BAS_BOM_DETAIL")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasBomDetailModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasBomDetail";

	public static final class FieldNames {
		/**
		 * 物料清单UUID
		 */
		public static final String basBomDetailUuid = "basBomDetailUuid";
		/**
		 * 物料清单UUID
		 */
		public static final String basBomUuid = "basBomUuid";
		/**
		 * 自关联
		 */
		public static final String preBasBomDetailUuid = "preBasBomDetailUuid";
		/**
		 * 物料编码
		 */
		public static final String itemCode = "itemCode";
		/**
		 * 物料序列号
		 */
		public static final String itemSeqno = "itemSeqno";
		/**
		 * 物料编码
		 */
		public static final String extItemCode = "extItemCode";
		/**
		 * 货物名称
		 */
		public static final String goodsDesc = "goodsDesc";
		/**
		 * 数量
		 */
		public static final String qty = "qty";
		/**
		 * 包装单位代码（打印标签用）
		 */
		public static final String qtyUnitCode = "qtyUnitCode";
		/**
		 * 包装单位描述（打印标签用）
		 */
		public static final String qtyUnitDesc = "qtyUnitDesc";
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
		 * 作废日期
		 */
		public static final String cancelDate = "cancelDate";
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

	//物料清单UUID
	private String basBomDetailUuid;
	//物料清单UUID
	private String basBomUuid;
	//自关联
	private String preBasBomDetailUuid;
	//物料编码
	private String itemCode;
	//物料序列号
	private String itemSeqno;
	//物料编码
	private String extItemCode;
	//货物名称
	private String goodsDesc;
	//数量
	private Double qty;
	//包装单位代码（打印标签用）
	private String qtyUnitCode;
	//包装单位描述（打印标签用）
	private String qtyUnitDesc;
	//备注
	private String remark;
	//状态
	private String status;
	//控制字
	private String controlWord;
	//作废日期
	private Date cancelDate;
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
	 * Get 物料清单UUID
	 */
	@Column(name = "BAS_BOM_DETAIL_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getBasBomDetailUuid() {
		return basBomDetailUuid;
	}

	/**
	 * Set 物料清单UUID
	 */
	public void setBasBomDetailUuid(String basBomDetailUuid) {
		this.basBomDetailUuid = basBomDetailUuid;
		addValidField(FieldNames.basBomDetailUuid);
	}

	/**
	 * Get 物料清单UUID
	 */
	@Column(name = "BAS_BOM_UUID")
	public String getBasBomUuid() {
		return basBomUuid;
	}

	/**
	 * Set 物料清单UUID
	 */
	public void setBasBomUuid(String basBomUuid) {
		this.basBomUuid = basBomUuid;
		addValidField(FieldNames.basBomUuid);
	}

	/**
	 * Get 自关联
	 * ，可以将组件分拆
	 */
	@Column(name = "PRE_BAS_BOM_DETAIL_UUID")
	public String getPreBasBomDetailUuid() {
		return preBasBomDetailUuid;
	}

	/**
	 * Set 自关联
	 * ，可以将组件分拆
	 */
	public void setPreBasBomDetailUuid(String preBasBomDetailUuid) {
		this.preBasBomDetailUuid = preBasBomDetailUuid;
		addValidField(FieldNames.preBasBomDetailUuid);
	}

	/**
	 * Get 物料编码
	 */
	@Column(name = "ITEM_CODE")
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * Set 物料编码
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
	 * Get 物料编码
	 */
	@Column(name = "EXT_ITEM_CODE")
	public String getExtItemCode() {
		return extItemCode;
	}

	/**
	 * Set 物料编码
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
	 * Get 包装单位代码（打印标签用）
	 */
	@Column(name = "QTY_UNIT_CODE")
	public String getQtyUnitCode() {
		return qtyUnitCode;
	}

	/**
	 * Set 包装单位代码（打印标签用）
	 */
	public void setQtyUnitCode(String qtyUnitCode) {
		this.qtyUnitCode = qtyUnitCode;
		addValidField(FieldNames.qtyUnitCode);
	}

	/**
	 * Get 包装单位描述（打印标签用）
	 */
	@Column(name = "QTY_UNIT_DESC")
	public String getQtyUnitDesc() {
		return qtyUnitDesc;
	}

	/**
	 * Set 包装单位描述（打印标签用）
	 */
	public void setQtyUnitDesc(String qtyUnitDesc) {
		this.qtyUnitDesc = qtyUnitDesc;
		addValidField(FieldNames.qtyUnitDesc);
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
	 * Get 作废日期
	 */
	@Column(name = "CANCEL_DATE")
	public Date getCancelDate() {
		return cancelDate;
	}

	/**
	 * Set 作废日期
	 */
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
		addValidField(FieldNames.cancelDate);
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

	
	
	
	
}
