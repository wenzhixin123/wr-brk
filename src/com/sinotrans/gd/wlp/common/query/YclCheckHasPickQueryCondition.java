package com.sinotrans.gd.wlp.common.query;

import com.sinotrans.framework.core.query.BaseQueryCondition;

@SuppressWarnings("serial")
public class YclCheckHasPickQueryCondition extends BaseQueryCondition {

	public YclCheckHasPickQueryCondition() {
		super();
	}

	public YclCheckHasPickQueryCondition(String logisticsOrderNo,
			String barcode, String panelNo, String status, String locTaskType,
			String locTaskCancelType, String pickLocTaskType, String officeCode) {
		super();
		this.logisticsOrderNo = logisticsOrderNo;
		this.barcode = barcode;
		this.panelNo = panelNo;
		this.status = status;
		this.locTaskType = locTaskType;
		this.locTaskCancelType = locTaskCancelType;
		this.pickLocTaskType = pickLocTaskType;
		this.officeCode = officeCode;
	}

	private String barcode;
	private String panelNo;
	private String status;
	private String locTaskType;
	private String locTaskCancelType;
	private String officeCode;
	private String pickLocTaskType;
	private String logisticsOrderNo;
	private String barcodeOrPanelNo;
	private String locPlanNo;
	private String packageNo;
	private String[] lodUuids;
	private String remainSinworkUuid;
	private String barcodeOrPackageNo;
	

	public String getBarcodeOrPackageNo() {
		return barcodeOrPackageNo;
	}

	public void setBarcodeOrPackageNo(String barcodeOrPackageNo) {
		this.barcodeOrPackageNo = barcodeOrPackageNo;
	}

	public String getRemainSinworkUuid() {
		return remainSinworkUuid;
	}

	public void setRemainSinworkUuid(String remainSinworkUuid) {
		this.remainSinworkUuid = remainSinworkUuid;
	}

	public String[] getLodUuids() {
		return lodUuids;
	}

	public void setLodUuids(String[] lodUuids) {
		this.lodUuids = lodUuids;
	}

	public String getPackageNo() {
		return packageNo;
	}

	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}

	public String getLocPlanNo() {
		return locPlanNo;
	}

	public void setLocPlanNo(String locPlanNo) {
		this.locPlanNo = locPlanNo;
	}

	public String getBarcodeOrPanelNo() {
		return barcodeOrPanelNo;
	}

	public void setBarcodeOrPanelNo(String barcodeOrPanelNo) {
		this.barcodeOrPanelNo = barcodeOrPanelNo;
	}

	public String getLogisticsOrderNo() {
		return logisticsOrderNo;
	}

	public void setLogisticsOrderNo(String logisticsOrderNo) {
		this.logisticsOrderNo = logisticsOrderNo;
	}

	public String getPickLocTaskType() {
		return pickLocTaskType;
	}

	public void setPickLocTaskType(String pickLocTaskType) {
		this.pickLocTaskType = pickLocTaskType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getPanelNo() {
		return panelNo;
	}

	public void setPanelNo(String panelNo) {
		this.panelNo = panelNo;
	}

	public String getLocTaskType() {
		return locTaskType;
	}

	public void setLocTaskType(String locTaskType) {
		this.locTaskType = locTaskType;
	}

	public String getLocTaskCancelType() {
		return locTaskCancelType;
	}

	public void setLocTaskCancelType(String locTaskCancelType) {
		this.locTaskCancelType = locTaskCancelType;
	}

}
