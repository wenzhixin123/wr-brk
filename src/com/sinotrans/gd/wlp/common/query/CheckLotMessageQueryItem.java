package com.sinotrans.gd.wlp.common.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class CheckLotMessageQueryItem extends BaseQueryItem {

	private String lotCode;
	private String itemCode;
	private String goodsDesc;
	private String instockUnitDesc;
	private Double remainQty;

	@Column(name = "LOT_CODE")
	public String getLotCode() {
		return lotCode;
	}

	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
		addValidField("lotCode");
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

	@Column(name = "INSTOCK_UNIT_DESC")
	public String getInstockUnitDesc() {
		return instockUnitDesc;
	}

	public void setInstockUnitDesc(String instockUnitDesc) {
		this.instockUnitDesc = instockUnitDesc;
		addValidField("instockUnitDesc");
	}

	@Column(name = "REMAIN_QTY")
	public Double getRemainQty() {
		return remainQty;
	}

	public void setRemainQty(Double remainQty) {
		this.remainQty = remainQty;
		addValidField("remainQty");
	}

}
