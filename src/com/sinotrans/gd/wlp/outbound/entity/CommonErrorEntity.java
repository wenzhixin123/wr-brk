package com.sinotrans.gd.wlp.outbound.entity;

import java.io.Serializable;
import java.util.Date;

import com.sinotrans.gd.wlp.common.model.SubmitOrderDetailModel;


@SuppressWarnings("serial")
public class CommonErrorEntity implements Serializable{
	private String errorMsg;
	private String reamrk;	
	private SubmitOrderDetailModel soOrderDetail;
	private String orderNo;
	private Date deliveryDate;
	private String daAux1BatchNo;
	private String itemCode;
	//缺货数量
	private Double lackQty;
	//库存数
	private Double remainQty;
	//计划数
	private Double planQty;
	private String goodsDesc;
	private String batchNo;
	
	private String goodsNature;
	//需求数
	private Double sodQty;
	//办单数
	private Double sumlodQty;
	//欠料数
	private Double oweQty;
	
	
	public Double getSodQty() {
		return sodQty;
	}
	public void setSodQty(Double sodQty) {
		this.sodQty = sodQty;
	}
	public Double getSumlodQty() {
		return sumlodQty;
	}
	public void setSumlodQty(Double sumlodQty) {
		this.sumlodQty = sumlodQty;
	}
	public Double getOweQty() {
		return oweQty;
	}
	public void setOweQty(Double oweQty) {
		this.oweQty = oweQty;
	}
	public String getGoodsNature() {
		return goodsNature;
	}
	public void setGoodsNature(String goodsNature) {
		this.goodsNature = goodsNature;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public CommonErrorEntity() {
		super();
	}
	public CommonErrorEntity(String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}
	public CommonErrorEntity(String errorMsg, String reamrk) {
		super();
		this.errorMsg = errorMsg;
		this.reamrk = reamrk;		
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getReamrk() {
		return reamrk;
	}
	public void setReamrk(String reamrk) {
		this.reamrk = reamrk;
	}
	public SubmitOrderDetailModel getSoOrderDetail() {
		return soOrderDetail;
	}
	public void setSoOrderDetail(SubmitOrderDetailModel soOrderDetail) {
		this.soOrderDetail = soOrderDetail;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public Double getLackQty() {
		return lackQty;
	}
	public void setLackQty(Double lackQty) {
		this.lackQty = lackQty;
	}
	public Double getRemainQty() {
		return remainQty;
	}
	public void setRemainQty(Double remainQty) {
		this.remainQty = remainQty;
	}
	public Double getPlanQty() {
		return planQty;
	}
	public void setPlanQty(Double planQty) {
		this.planQty = planQty;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDaAux1BatchNo() {
		return daAux1BatchNo;
	}
	public void setDaAux1BatchNo(String daAux1BatchNo) {
		this.daAux1BatchNo = daAux1BatchNo;
	}
	
	
}
