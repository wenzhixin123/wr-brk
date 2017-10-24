package com.sinotrans.gd.wlp.basicdata.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;
import com.sinotrans.gd.wlp.common.web.RcUtil;

@SuppressWarnings("serial")
@Entity
public class BarPalletQueryItem extends BaseQueryItem {

	private String itemCode;
	private String goodsDesc;
	private String instockUnitDesc;
	private Double remainQty;
	private String lotCode;

	@Column(name = "ITEM_CODE")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		if (!RcUtil.isEmpty(itemCode)) {
			this.itemCode = itemCode;
		} else {
			this.itemCode = "";
		}
		addValidField("itemCode");
	}

	@Column(name = "GOODS_DESC")
	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		if (!RcUtil.isEmpty(goodsDesc)) {
			this.goodsDesc = goodsDesc;
		} else {
			this.goodsDesc = "";
		}
		addValidField("goodsDesc");
	}

	@Column(name = "INSTOCK_UNIT_DESC")
	public String getInstockUnitDesc() {
		return instockUnitDesc;
	}

	public void setInstockUnitDesc(String instockUnitDesc) {
		if (!RcUtil.isEmpty(instockUnitDesc)) {
			this.instockUnitDesc = instockUnitDesc;
		} else {
			this.instockUnitDesc = "";
		}
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

	@Column(name = "LOT_CODE")
	public String getLotCode() {
		return lotCode;
	}

	public void setLotCode(String lotCode) {
		if (!RcUtil.isEmpty(lotCode)) {
			this.lotCode = lotCode;
		} else {
			this.lotCode = "";
		}
		addValidField("lotCode");
	}

}
