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
 * Model class for 港口表
 */
@Entity
@Table(name = "BAS_PORT")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasPortModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasPort";

	public static final class FieldNames {
		/**
		 * 港口ID
		 */
		public static final String portId = "portId";
		/**
		 * 港口代码
		 */
		public static final String portCode = "portCode";
		/**
		 * 港口所在区域
		 */
		public static final String portArea = "portArea";
		/**
		 * 港口所在城市
		 */
		public static final String cityCode = "cityCode";
		/**
		 * 港口名称
		 */
		public static final String portName = "portName";
		/**
		 * 港口英文名
		 */
		public static final String portNameEn = "portNameEn";
		/**
		 * 港口类型
		 */
		public static final String portKinds = "portKinds";
		/**
		 * 港口代理
		 */
		public static final String portAgent = "portAgent";
		/**
		 * IDD区域码
		 */
		public static final String iddAreaCode = "iddAreaCode";
		/**
		 * 路由
		 */
		public static final String route = "route";
		/**
		 * 港口路由
		 */
		public static final String portRoute = "portRoute";
		/**
		 * 备注
		 */
		public static final String remark = "remark";
		/**
		 * 港口控制字
		 */
		public static final String controlWord = "controlWord";
		/**
		 * 统一港口代码
		 */
		public static final String pubPortId = "pubPortId";
		/**
		 * 统一港口名
		 */
		public static final String pubPortName = "pubPortName";
		/**
		 * 统一港口名(EN)
		 */
		public static final String pubPortNameEn = "pubPortNameEn";
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
		 * recVer
		 */
		public static final String recVer = "recVer";
		/**
		 * creator
		 */
		public static final String creator = "creator";
		/**
		 * createTime
		 */
		public static final String createTime = "createTime";
		/**
		 * modifier
		 */
		public static final String modifier = "modifier";
		/**
		 * 修改时间
		 */
		public static final String modifyTime = "modifyTime";
		/**
		 * 助记码
		 */
		public static final String mnemonicCode = "mnemonicCode";
		/**
		 * 海事管辖区
		 */
		public static final String msaAreaDesc = "msaAreaDesc";
	}

	//港口ID
	private String portId;
	//港口代码
	private String portCode;
	//港口所在区域
	private String portArea;
	//港口所在城市
	private String cityCode;
	//港口名称
	private String portName;
	//港口英文名
	private String portNameEn;
	//港口类型
	private String portKinds;
	//港口代理
	private String portAgent;
	//IDD区域码
	private String iddAreaCode;
	//路由
	private String route;
	//港口路由
	private String portRoute;
	//备注
	private String remark;
	//港口控制字
	private String controlWord;
	//统一港口代码
	private String pubPortId;
	//统一港口名
	private String pubPortName;
	//统一港口名(EN)
	private String pubPortNameEn;
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
	//recVer
	private Long recVer;
	//creator
	private String creator;
	//createTime
	private Date createTime;
	//modifier
	private String modifier;
	//修改时间
	private Date modifyTime;
	//助记码
	private String mnemonicCode;
	//海事管辖区
	private String msaAreaDesc;

	/**
	 * Get 港口ID
	 */
	@Column(name = "PORT_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getPortId() {
		return portId;
	}

	/**
	 * Set 港口ID
	 */
	public void setPortId(String portId) {
		this.portId = portId;
		addValidField(FieldNames.portId);
	}

	/**
	 * Get 港口代码
	 */
	@Column(name = "PORT_CODE")
	public String getPortCode() {
		return portCode;
	}

	/**
	 * Set 港口代码
	 */
	public void setPortCode(String portCode) {
		this.portCode = portCode;
		addValidField(FieldNames.portCode);
	}

	/**
	 * Get 港口所在区域
	 */
	@Column(name = "PORT_AREA")
	public String getPortArea() {
		return portArea;
	}

	/**
	 * Set 港口所在区域
	 */
	public void setPortArea(String portArea) {
		this.portArea = portArea;
		addValidField(FieldNames.portArea);
	}

	/**
	 * Get 港口所在城市
	 */
	@Column(name = "CITY_CODE")
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * Set 港口所在城市
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
		addValidField(FieldNames.cityCode);
	}

	/**
	 * Get 港口名称
	 */
	@Column(name = "PORT_NAME")
	public String getPortName() {
		return portName;
	}

	/**
	 * Set 港口名称
	 */
	public void setPortName(String portName) {
		this.portName = portName;
		addValidField(FieldNames.portName);
	}

	/**
	 * Get 港口英文名
	 */
	@Column(name = "PORT_NAME_EN")
	public String getPortNameEn() {
		return portNameEn;
	}

	/**
	 * Set 港口英文名
	 */
	public void setPortNameEn(String portNameEn) {
		this.portNameEn = portNameEn;
		addValidField(FieldNames.portNameEn);
	}

	/**
	 * Get 港口类型
	 * ：1位：1=港口；2位：1=地点；3位：1=码头
	 */
	@Column(name = "PORT_KINDS")
	public String getPortKinds() {
		return portKinds;
	}

	/**
	 * Set 港口类型
	 * ：1位：1=港口；2位：1=地点；3位：1=码头
	 */
	public void setPortKinds(String portKinds) {
		this.portKinds = portKinds;
		addValidField(FieldNames.portKinds);
	}

	/**
	 * Get 港口代理
	 */
	@Column(name = "PORT_AGENT")
	public String getPortAgent() {
		return portAgent;
	}

	/**
	 * Set 港口代理
	 */
	public void setPortAgent(String portAgent) {
		this.portAgent = portAgent;
		addValidField(FieldNames.portAgent);
	}

	/**
	 * Get IDD区域码
	 */
	@Column(name = "IDD_AREA_CODE")
	public String getIddAreaCode() {
		return iddAreaCode;
	}

	/**
	 * Set IDD区域码
	 */
	public void setIddAreaCode(String iddAreaCode) {
		this.iddAreaCode = iddAreaCode;
		addValidField(FieldNames.iddAreaCode);
	}

	/**
	 * Get 路由
	 */
	@Column(name = "ROUTE")
	public String getRoute() {
		return route;
	}

	/**
	 * Set 路由
	 */
	public void setRoute(String route) {
		this.route = route;
		addValidField(FieldNames.route);
	}

	/**
	 * Get 港口路由
	 */
	@Column(name = "PORT_ROUTE")
	public String getPortRoute() {
		return portRoute;
	}

	/**
	 * Set 港口路由
	 */
	public void setPortRoute(String portRoute) {
		this.portRoute = portRoute;
		addValidField(FieldNames.portRoute);
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
	 * Get 港口控制字
	 * ：1位：0=有效，1=作废；4位：0=临时，1=正式
	 */
	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	/**
	 * Set 港口控制字
	 * ：1位：0=有效，1=作废；4位：0=临时，1=正式
	 */
	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField(FieldNames.controlWord);
	}

	/**
	 * Get 统一港口代码
	 */
	@Column(name = "PUB_PORT_ID")
	public String getPubPortId() {
		return pubPortId;
	}

	/**
	 * Set 统一港口代码
	 */
	public void setPubPortId(String pubPortId) {
		this.pubPortId = pubPortId;
		addValidField(FieldNames.pubPortId);
	}

	/**
	 * Get 统一港口名
	 */
	@Column(name = "PUB_PORT_NAME")
	public String getPubPortName() {
		return pubPortName;
	}

	/**
	 * Set 统一港口名
	 */
	public void setPubPortName(String pubPortName) {
		this.pubPortName = pubPortName;
		addValidField(FieldNames.pubPortName);
	}

	/**
	 * Get 统一港口名(EN)
	 */
	@Column(name = "PUB_PORT_NAME_EN")
	public String getPubPortNameEn() {
		return pubPortNameEn;
	}

	/**
	 * Set 统一港口名(EN)
	 */
	public void setPubPortNameEn(String pubPortNameEn) {
		this.pubPortNameEn = pubPortNameEn;
		addValidField(FieldNames.pubPortNameEn);
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
	 * Get recVer
	 */
	@Column(name = "REC_VER")
	@Version
	public Long getRecVer() {
		return recVer;
	}

	/**
	 * Set recVer
	 */
	public void setRecVer(Long recVer) {
		this.recVer = recVer;
		addValidField(FieldNames.recVer);
	}

	/**
	 * Get creator
	 */
	@Column(name = "CREATOR")
	public String getCreator() {
		return creator;
	}

	/**
	 * Set creator
	 */
	public void setCreator(String creator) {
		this.creator = creator;
		addValidField(FieldNames.creator);
	}

	/**
	 * Get createTime
	 */
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * Set createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		addValidField(FieldNames.createTime);
	}

	/**
	 * Get modifier
	 */
	@Column(name = "MODIFIER")
	public String getModifier() {
		return modifier;
	}

	/**
	 * Set modifier
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
	 * Get 助记码
	 */
	@Column(name = "MNEMONIC_CODE")
	public String getMnemonicCode() {
		return mnemonicCode;
	}

	/**
	 * Set 助记码
	 */
	public void setMnemonicCode(String mnemonicCode) {
		this.mnemonicCode = mnemonicCode;
		addValidField(FieldNames.mnemonicCode);
	}
	
	/**
	 * Get 海事管辖区
	 */
	@Column(name = "MSA_AREA_DESC")
	public String getMsaAreaDesc() {
		return msaAreaDesc;
	}

	/**
	 * Set 海事管辖区
	 */
	public void setMsaAreaDesc(String msaAreaDesc) {
		this.msaAreaDesc = msaAreaDesc;
		addValidField(FieldNames.msaAreaDesc);
	}



	/**
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="portId";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}


}
