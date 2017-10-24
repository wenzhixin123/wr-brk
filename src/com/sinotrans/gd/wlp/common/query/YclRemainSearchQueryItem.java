package com.sinotrans.gd.wlp.common.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class YclRemainSearchQueryItem extends BaseQueryItem {

	private String itemCode;
	private String goodsDesc;
	private String warehouseName;
	private String locAreaName;
	private Double sumRemainQty;
	private String basLocAreaUuid;
	private Double remainHoldQty;
	private String extItemCode;
	
	@Column(name="EXT_ITEM_CODE")
	public String getExtItemCode() {
		return extItemCode;
	}

	public void setExtItemCode(String extItemCode) {
		this.extItemCode = extItemCode;
	}

	@Column(name = "BAS_LOC_AREA_UUID")
	public String getBasLocAreaUuid() {
		return basLocAreaUuid;
	}

	public void setBasLocAreaUuid(String basLocAreaUuid) {
		this.basLocAreaUuid = basLocAreaUuid;
	}

	@Column(name = "ITEM_CODE")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
		addValidField("itemCode");
	}

	@Column(name = "GOODS_DESC")
	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
		addValidField("goodsDesc");
	}

	@Column(name = "WAREHOUSE_NAME")
	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
		addValidField("warehouseName");
	}

	@Column(name = "LOC_AREA_NAME")
	public String getLocAreaName() {
		return locAreaName;
	}

	public void setLocAreaName(String locAreaName) {
		this.locAreaName = locAreaName;
		addValidField("locAreaName");
	}

	@Column(name = "SUM_REMAIN_QTY")
	public Double getSumRemainQty() {
		return sumRemainQty;
	}

	public void setSumRemainQty(Double sumRemainQty) {
		this.sumRemainQty = sumRemainQty;
		addValidField("sumRemainQty");
	}

	

	@Column(name = "REMAIN_HOLD_QTY")
	public Double getRemainHoldQty() {
		return remainHoldQty;
	}

	public void setRemainHoldQty(Double remainHoldQty) {
		this.remainHoldQty = remainHoldQty;
	}

}
