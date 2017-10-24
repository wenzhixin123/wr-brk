package com.sinotrans.gd.wlp.basicdata.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@SuppressWarnings("serial")
@Entity
public class BasLotStockQueryItem extends BaseQueryItem {

	private String locTypeName;
	private String basLotStockUuid;
	private String basLocAreaUuid;
	private String basBasLocTypeUuid;
	private String lotCode;
	private String lotName;
	private Long pri;
	private Long putPri;
	private Long pickPri;
	private Long maxPalletFloor;
	private Double maxVoluem;
	private Double maxWeight;
	private Long maxPalletQty;
	private Double length;
	private Double width;
	private Double height;
	private Double lotX;
	private Double lotY;
	private Double lotZ;
	private String lockFlag;
	private String remark;
	private String aux1;
	private String aux2;
	private String aux3;
	private String aux4;
	private String aux5;
	private String status;
	private String controlWord;
	private String officeCode;
	private Long recVer;
	private String creator;
	private Date createTime;
	private String modifier;
	private Date modifyTime;

	@Column(name = "LOC_TYPE_NAME")
	public String getLocTypeName() {
		return locTypeName;
	}

	public void setLocTypeName(String locTypeName) {
		this.locTypeName = locTypeName;
		addValidField("locTypeName");
	}

	@Column(name = "BAS_LOT_STOCK_UUID")
	public String getBasLotStockUuid() {
		return basLotStockUuid;
	}

	public void setBasLotStockUuid(String basLotStockUuid) {
		this.basLotStockUuid = basLotStockUuid;
		addValidField("basLotStockUuid");
	}

	@Column(name = "BAS_LOC_AREA_UUID")
	public String getBasLocAreaUuid() {
		return basLocAreaUuid;
	}

	public void setBasLocAreaUuid(String basLocAreaUuid) {
		this.basLocAreaUuid = basLocAreaUuid;
		addValidField("basLocAreaUuid");
	}

	@Column(name = "BAS_BAS_LOC_TYPE_UUID")
	public String getBasBasLocTypeUuid() {
		return basBasLocTypeUuid;
	}

	public void setBasBasLocTypeUuid(String basBasLocTypeUuid) {
		this.basBasLocTypeUuid = basBasLocTypeUuid;
		addValidField("basBasLocTypeUuid");
	}

	@Column(name = "LOT_CODE")
	public String getLotCode() {
		return lotCode;
	}

	public void setLotCode(String lotCode) {
		this.lotCode = lotCode;
		addValidField("lotCode");
	}

	@Column(name = "LOT_NAME")
	public String getLotName() {
		return lotName;
	}

	public void setLotName(String lotName) {
		this.lotName = lotName;
		addValidField("lotName");
	}

	@Column(name = "PRI")
	public Long getPri() {
		return pri;
	}

	public void setPri(Long pri) {
		this.pri = pri;
		addValidField("pri");
	}

	@Column(name = "PUT_PRI")
	public Long getPutPri() {
		return putPri;
	}

	public void setPutPri(Long putPri) {
		this.putPri = putPri;
		addValidField("putPri");
	}

	@Column(name = "PICK_PRI")
	public Long getPickPri() {
		return pickPri;
	}

	public void setPickPri(Long pickPri) {
		this.pickPri = pickPri;
		addValidField("pickPri");
	}

	@Column(name = "MAX_PALLET_FLOOR")
	public Long getMaxPalletFloor() {
		return maxPalletFloor;
	}

	public void setMaxPalletFloor(Long maxPalletFloor) {
		this.maxPalletFloor = maxPalletFloor;
		addValidField("maxPalletFloor");
	}

	@Column(name = "MAX_VOLUEM")
	public Double getMaxVoluem() {
		return maxVoluem;
	}

	public void setMaxVoluem(Double maxVoluem) {
		this.maxVoluem = maxVoluem;
		addValidField("maxVoluem");
	}

	@Column(name = "MAX_WEIGHT")
	public Double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(Double maxWeight) {
		this.maxWeight = maxWeight;
		addValidField("maxWeight");
	}

	@Column(name = "MAX_PALLET_QTY")
	public Long getMaxPalletQty() {
		return maxPalletQty;
	}

	public void setMaxPalletQty(Long maxPalletQty) {
		this.maxPalletQty = maxPalletQty;
		addValidField("maxPalletQty");
	}

	@Column(name = "LENGTH")
	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
		addValidField("length");
	}

	@Column(name = "WIDTH")
	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
		addValidField("width");
	}

	@Column(name = "HEIGHT")
	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
		addValidField("height");
	}

	@Column(name = "LOT_X")
	public Double getLotX() {
		return lotX;
	}

	public void setLotX(Double lotX) {
		this.lotX = lotX;
		addValidField("lotX");
	}

	@Column(name = "LOT_Y")
	public Double getLotY() {
		return lotY;
	}

	public void setLotY(Double lotY) {
		this.lotY = lotY;
		addValidField("lotY");
	}

	@Column(name = "LOT_Z")
	public Double getLotZ() {
		return lotZ;
	}

	public void setLotZ(Double lotZ) {
		this.lotZ = lotZ;
		addValidField("lotZ");
	}

	@Column(name = "LOCK_FLAG")
	public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
		addValidField("lockFlag");
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
		addValidField("remark");
	}

	@Column(name = "AUX1")
	public String getAux1() {
		return aux1;
	}

	public void setAux1(String aux1) {
		this.aux1 = aux1;
		addValidField("aux1");
	}

	@Column(name = "AUX2")
	public String getAux2() {
		return aux2;
	}

	public void setAux2(String aux2) {
		this.aux2 = aux2;
		addValidField("aux2");
	}

	@Column(name = "AUX3")
	public String getAux3() {
		return aux3;
	}

	public void setAux3(String aux3) {
		this.aux3 = aux3;
		addValidField("aux3");
	}

	@Column(name = "AUX4")
	public String getAux4() {
		return aux4;
	}

	public void setAux4(String aux4) {
		this.aux4 = aux4;
		addValidField("aux4");
	}

	@Column(name = "AUX5")
	public String getAux5() {
		return aux5;
	}

	public void setAux5(String aux5) {
		this.aux5 = aux5;
		addValidField("aux5");
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		addValidField("status");
	}

	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField("controlWord");
	}

	@Column(name = "OFFICE_CODE")
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
		addValidField("officeCode");
	}

	@Column(name = "REC_VER")
	public Long getRecVer() {
		return recVer;
	}

	public void setRecVer(Long recVer) {
		this.recVer = recVer;
		addValidField("recVer");
	}

	@Column(name = "CREATOR")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
		addValidField("creator");
	}

	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		addValidField("createTime");
	}

	@Column(name = "MODIFIER")
	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
		addValidField("modifier");
	}

	@Column(name = "MODIFY_TIME")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
		addValidField("modifyTime");
	}

}
