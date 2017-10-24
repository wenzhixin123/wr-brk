package com.sinotrans.gd.wlp.common.entity;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class BarClientCommonEntity implements Serializable{
	//用户编号
	public String userId;
	//用户名称
	public String userName;
	//仓库代码
    public String officeCode;
    //作业单号
    public String orderNo;
    //条码
    public String barcode;
    //目的条码
    public String targetBarcode;
    //数量
    public Double qty;
    //货位
    public String lotCode;
    //创建时间
    public Date createTime;
    //修改时间
    public Date ModifyTime;
    //作业时间
    public Date LocTaskDate;
    //自定义字段1
    public String aux1;
    public String aux2;
    public String aux3;
    public String aux4;
    public String aux5;
    
    public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getTargetBarcode() {
		return targetBarcode;
	}
	public void setTargetBarcode(String targetBarcode) {
		this.targetBarcode = targetBarcode;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public String getLotCode() {
		return lotCode;
	}
	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return ModifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		ModifyTime = modifyTime;
	}
	public Date getLocTaskDate() {
		return LocTaskDate;
	}
	public void setLocTaskDate(Date locTaskDate) {
		LocTaskDate = locTaskDate;
	}
	public String getAux1() {
		return aux1;
	}
	public void setAux1(String aux1) {
		this.aux1 = aux1;
	}
	public String getAux2() {
		return aux2;
	}
	public void setAux2(String aux2) {
		this.aux2 = aux2;
	}
	public String getAux3() {
		return aux3;
	}
	public void setAux3(String aux3) {
		this.aux3 = aux3;
	}
	public String getAux4() {
		return aux4;
	}
	public void setAux4(String aux4) {
		this.aux4 = aux4;
	}
	public String getAux5() {
		return aux5;
	}
	public void setAux5(String aux5) {
		this.aux5 = aux5;
	}
	
    
}
