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
 * Model class for 船舶资料表
 */
@Entity
@Table(name = "BAS_VESSEL")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasVesselModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasVessel";

	public static final class FieldNames {
		/**
		 * 船舶ID
		 */
		public static final String vesselId = "vesselId";
		/**
		 * 船东代码
		 */
		public static final String linerCode = "linerCode";
		/**
		 * 航线代码
		 */
		public static final String lineCode = "lineCode";
		/**
		 * 船舶代码
		 */
		public static final String vesselCode = "vesselCode";
		/**
		 * 中文名
		 */
		public static final String vesselName = "vesselName";
		/**
		 * 英文名
		 */
		public static final String vesselNameEn = "vesselNameEn";
		/**
		 * 船型
		 */
		public static final String vesselType = "vesselType";
		/**
		 * 船籍（船旗）
		 */
		public static final String countryId = "countryId";
		/**
		 * 船舶出厂号
		 */
		public static final String vesselNo = "vesselNo";
		/**
		 * 船原籍港
		 */
		public static final String registerPort = "registerPort";
		/**
		 * 船净重吨
		 */
		public static final String netRt = "netRt";
		/**
		 * 船总重吨
		 */
		public static final String grsRt = "grsRt";
		/**
		 * 船载重吨
		 */
		public static final String loadWt = "loadWt";
		/**
		 * 船总长度
		 */
		public static final String totalLength = "totalLength";
		/**
		 * 装载总集装箱数
		 */
		public static final String loadTeu = "loadTeu";
		/**
		 * 其中可装吉箱数
		 */
		public static final String loadTeuE = "loadTeuE";
		/**
		 * 船舶海关编号
		 */
		public static final String customsLicenseNo = "customsLicenseNo";
		/**
		 * 边防备案号
		 */
		public static final String boundaryNo = "boundaryNo";
		/**
		 * 船许可证号
		 */
		public static final String vesselLicenseId = "vesselLicenseId";
		/**
		 * 海事备案号
		 */
		public static final String imoLicenseNo = "imoLicenseNo";
		/**
		 * 船舶图片
		 */
		public static final String vesselPic = "vesselPic";
		/**
		 * 业务联系人
		 */
		public static final String contactUser = "contactUser";
		/**
		 * 业务联系人手机
		 */
		public static final String contactMobile = "contactMobile";
		/**
		 * 备注
		 */
		public static final String remarks = "remarks";
		/**
		 * 控制字key1=1为海船
		 */
		public static final String vesselControlWord = "vesselControlWord";
		/**
		 * 公司（仓库）代码
		 */
		public static final String officeCode = "officeCode";
		/**
		 * 版本号[REC_VER]
		 */
		public static final String recVer = "recVer";
		/**
		 * 创建人[CREATOR]
		 */
		public static final String creator = "creator";
		/**
		 * 创建日[CREATE_TIME]
		 */
		public static final String createTime = "createTime";
		/**
		 * 修改人[MODIFIER]
		 */
		public static final String modifier = "modifier";
		/**
		 * 修改时间
		 */
		public static final String modifyTime = "modifyTime";
		/**
		 * 状态
		 */
		public static final String status = "status";
		/**
		 * 助记码
		 */
		public static final String mnemonicCode = "mnemonicCode";
		/**
		 * 海上移动标识号（简称
		 */
		public static final String imoMmsiCode = "imoMmsiCode";
	}

	//船舶ID
	private String vesselId;
	//船东代码
	private String linerCode;
	//航线代码
	private String lineCode;
	//船舶代码
	private String vesselCode;
	//中文名
	private String vesselName;
	//英文名
	private String vesselNameEn;
	//船型
	private String vesselType;
	//船籍（船旗）
	private String countryId;
	//船舶出厂号
	private String vesselNo;
	//船原籍港
	private String registerPort;
	//船净重吨
	private Integer netRt;
	//船总重吨
	private Integer grsRt;
	//船载重吨
	private Integer loadWt;
	//船总长度
	private Double totalLength;
	//装载总集装箱数
	private Integer loadTeu;
	//其中可装吉箱数
	private Integer loadTeuE;
	//船舶海关编号
	private String customsLicenseNo;
	//边防备案号
	private String boundaryNo;
	//船许可证号
	private String vesselLicenseId;
	//海事备案号
	private String imoLicenseNo;
	//船舶图片
	private String vesselPic;
	//业务联系人
	private String contactUser;
	//业务联系人手机
	private String contactMobile;
	//备注
	private String remarks;
	//控制字key1=1为海船
	private String vesselControlWord;
	//公司（仓库）代码
	private String officeCode;
	//版本号[REC_VER]
	private Long recVer;
	//创建人[CREATOR]
	private String creator;
	//创建日[CREATE_TIME]
	private Date createTime;
	//修改人[MODIFIER]
	private String modifier;
	//修改时间
	private Date modifyTime;
	//状态
	private String status;
	//助记码
	private String mnemonicCode;
	//海上移动标识号（简称
	private String imoMmsiCode;

	/**
	 * Get 船舶ID
	 */
	@Column(name = "VESSEL_ID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getVesselId() {
		return vesselId;
	}

	/**
	 * Set 船舶ID
	 */
	public void setVesselId(String vesselId) {
		this.vesselId = vesselId;
		addValidField(FieldNames.vesselId);
	}

	/**
	 * Get 船东代码
	 */
	@Column(name = "LINER_CODE")
	public String getLinerCode() {
		return linerCode;
	}

	/**
	 * Set 船东代码
	 */
	public void setLinerCode(String linerCode) {
		this.linerCode = linerCode;
		addValidField(FieldNames.linerCode);
	}

	/**
	 * Get 航线代码
	 */
	@Column(name = "LINE_CODE")
	public String getLineCode() {
		return lineCode;
	}

	/**
	 * Set 航线代码
	 */
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
		addValidField(FieldNames.lineCode);
	}

	/**
	 * Get 船舶代码
	 */
	@Column(name = "VESSEL_CODE")
	public String getVesselCode() {
		return vesselCode;
	}

	/**
	 * Set 船舶代码
	 */
	public void setVesselCode(String vesselCode) {
		this.vesselCode = vesselCode;
		addValidField(FieldNames.vesselCode);
	}

	/**
	 * Get 中文名
	 */
	@Column(name = "VESSEL_NAME")
	public String getVesselName() {
		return vesselName;
	}

	/**
	 * Set 中文名
	 */
	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
		addValidField(FieldNames.vesselName);
	}

	/**
	 * Get 英文名
	 */
	@Column(name = "VESSEL_NAME_EN")
	public String getVesselNameEn() {
		return vesselNameEn;
	}

	/**
	 * Set 英文名
	 */
	public void setVesselNameEn(String vesselNameEn) {
		this.vesselNameEn = vesselNameEn;
		addValidField(FieldNames.vesselNameEn);
	}

	/**
	 * Get 船型
	 */
	@Column(name = "VESSEL_TYPE")
	public String getVesselType() {
		return vesselType;
	}

	/**
	 * Set 船型
	 */
	public void setVesselType(String vesselType) {
		this.vesselType = vesselType;
		addValidField(FieldNames.vesselType);
	}

	/**
	 * Get 船籍（船旗）
	 */
	@Column(name = "COUNTRY_ID")
	public String getCountryId() {
		return countryId;
	}

	/**
	 * Set 船籍（船旗）
	 */
	public void setCountryId(String countryId) {
		this.countryId = countryId;
		addValidField(FieldNames.countryId);
	}

	/**
	 * Get 船舶出厂号
	 */
	@Column(name = "VESSEL_NO")
	public String getVesselNo() {
		return vesselNo;
	}

	/**
	 * Set 船舶出厂号
	 */
	public void setVesselNo(String vesselNo) {
		this.vesselNo = vesselNo;
		addValidField(FieldNames.vesselNo);
	}

	/**
	 * Get 船原籍港
	 */
	@Column(name = "REGISTER_PORT")
	public String getRegisterPort() {
		return registerPort;
	}

	/**
	 * Set 船原籍港
	 */
	public void setRegisterPort(String registerPort) {
		this.registerPort = registerPort;
		addValidField(FieldNames.registerPort);
	}

	/**
	 * Get 船净重吨
	 */
	@Column(name = "NET_RT")
	public Integer getNetRt() {
		return netRt;
	}

	/**
	 * Set 船净重吨
	 */
	public void setNetRt(Integer netRt) {
		this.netRt = netRt;
		addValidField(FieldNames.netRt);
	}

	/**
	 * Get 船总重吨
	 */
	@Column(name = "GRS_RT")
	public Integer getGrsRt() {
		return grsRt;
	}

	/**
	 * Set 船总重吨
	 */
	public void setGrsRt(Integer grsRt) {
		this.grsRt = grsRt;
		addValidField(FieldNames.grsRt);
	}

	/**
	 * Get 船载重吨
	 */
	@Column(name = "LOAD_WT")
	public Integer getLoadWt() {
		return loadWt;
	}

	/**
	 * Set 船载重吨
	 */
	public void setLoadWt(Integer loadWt) {
		this.loadWt = loadWt;
		addValidField(FieldNames.loadWt);
	}

	/**
	 * Get 船总长度
	 */
	@Column(name = "TOTAL_LENGTH")
	public Double getTotalLength() {
		return totalLength;
	}

	/**
	 * Set 船总长度
	 */
	public void setTotalLength(Double totalLength) {
		this.totalLength = totalLength;
		addValidField(FieldNames.totalLength);
	}

	/**
	 * Get 装载总集装箱数
	 */
	@Column(name = "LOAD_TEU")
	public Integer getLoadTeu() {
		return loadTeu;
	}

	/**
	 * Set 装载总集装箱数
	 */
	public void setLoadTeu(Integer loadTeu) {
		this.loadTeu = loadTeu;
		addValidField(FieldNames.loadTeu);
	}

	/**
	 * Get 其中可装吉箱数
	 */
	@Column(name = "LOAD_TEU_E")
	public Integer getLoadTeuE() {
		return loadTeuE;
	}

	/**
	 * Set 其中可装吉箱数
	 */
	public void setLoadTeuE(Integer loadTeuE) {
		this.loadTeuE = loadTeuE;
		addValidField(FieldNames.loadTeuE);
	}

	/**
	 * Get 船舶海关编号
	 */
	@Column(name = "CUSTOMS_LICENSE_NO")
	public String getCustomsLicenseNo() {
		return customsLicenseNo;
	}

	/**
	 * Set 船舶海关编号
	 */
	public void setCustomsLicenseNo(String customsLicenseNo) {
		this.customsLicenseNo = customsLicenseNo;
		addValidField(FieldNames.customsLicenseNo);
	}

	/**
	 * Get 边防备案号
	 */
	@Column(name = "BOUNDARY_NO")
	public String getBoundaryNo() {
		return boundaryNo;
	}

	/**
	 * Set 边防备案号
	 */
	public void setBoundaryNo(String boundaryNo) {
		this.boundaryNo = boundaryNo;
		addValidField(FieldNames.boundaryNo);
	}

	/**
	 * Get 船许可证号
	 */
	@Column(name = "VESSEL_LICENSE_ID")
	public String getVesselLicenseId() {
		return vesselLicenseId;
	}

	/**
	 * Set 船许可证号
	 */
	public void setVesselLicenseId(String vesselLicenseId) {
		this.vesselLicenseId = vesselLicenseId;
		addValidField(FieldNames.vesselLicenseId);
	}

	/**
	 * Get 海事备案号
	 */
	@Column(name = "IMO_LICENSE_NO")
	public String getImoLicenseNo() {
		return imoLicenseNo;
	}

	/**
	 * Set 海事备案号
	 */
	public void setImoLicenseNo(String imoLicenseNo) {
		this.imoLicenseNo = imoLicenseNo;
		addValidField(FieldNames.imoLicenseNo);
	}

	/**
	 * Get 船舶图片
	 */
	@Column(name = "VESSEL_PIC")
	public String getVesselPic() {
		return vesselPic;
	}

	/**
	 * Set 船舶图片
	 */
	public void setVesselPic(String vesselPic) {
		this.vesselPic = vesselPic;
		addValidField(FieldNames.vesselPic);
	}

	/**
	 * Get 业务联系人
	 */
	@Column(name = "CONTACT_USER")
	public String getContactUser() {
		return contactUser;
	}

	/**
	 * Set 业务联系人
	 */
	public void setContactUser(String contactUser) {
		this.contactUser = contactUser;
		addValidField(FieldNames.contactUser);
	}

	/**
	 * Get 业务联系人手机
	 */
	@Column(name = "CONTACT_MOBILE")
	public String getContactMobile() {
		return contactMobile;
	}

	/**
	 * Set 业务联系人手机
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
		addValidField(FieldNames.contactMobile);
	}

	/**
	 * Get 备注
	 */
	@Column(name = "REMARKS")
	public String getRemarks() {
		return remarks;
	}

	/**
	 * Set 备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
		addValidField(FieldNames.remarks);
	}

	/**
	 * Get 控制字key1=1为海船
	 * ，0为驳船，默认0
	 */
	@Column(name = "VESSEL_CONTROL_WORD")
	public String getVesselControlWord() {
		return vesselControlWord;
	}

	/**
	 * Set 控制字key1=1为海船
	 * ，0为驳船，默认0
	 */
	public void setVesselControlWord(String vesselControlWord) {
		this.vesselControlWord = vesselControlWord;
		addValidField(FieldNames.vesselControlWord);
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
	 * Get 版本号[REC_VER]
	 */
	@Column(name = "REC_VER")
	@Version
	public Long getRecVer() {
		return recVer;
	}

	/**
	 * Set 版本号[REC_VER]
	 */
	public void setRecVer(Long recVer) {
		this.recVer = recVer;
		addValidField(FieldNames.recVer);
	}

	/**
	 * Get 创建人[CREATOR]
	 */
	@Column(name = "CREATOR")
	public String getCreator() {
		return creator;
	}

	/**
	 * Set 创建人[CREATOR]
	 */
	public void setCreator(String creator) {
		this.creator = creator;
		addValidField(FieldNames.creator);
	}

	/**
	 * Get 创建日[CREATE_TIME]
	 */
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * Set 创建日[CREATE_TIME]
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		addValidField(FieldNames.createTime);
	}

	/**
	 * Get 修改人[MODIFIER]
	 */
	@Column(name = "MODIFIER")
	public String getModifier() {
		return modifier;
	}

	/**
	 * Set 修改人[MODIFIER]
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
	 * Get 状态
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态
	 */
	public void setStatus(String status) {
		this.status = status;
		addValidField(FieldNames.status);
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
	 * Get 海上移动标识号（简称
	 * ：MMSI号）
	 */
	@Column(name = "IMO_MMSI_CODE")
	public String getImoMmsiCode() {
		return imoMmsiCode;
	}

	/**
	 * Set 海上移动标识号（简称
	 * ：MMSI号）
	 */
	public void setImoMmsiCode(String imoMmsiCode) {
		this.imoMmsiCode = imoMmsiCode;
		addValidField(FieldNames.imoMmsiCode);
	}

	/**
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="vesselId";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
