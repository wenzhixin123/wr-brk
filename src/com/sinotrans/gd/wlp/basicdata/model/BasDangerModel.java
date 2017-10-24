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
 * Model class for 危险品参数
 */
@Entity
@Table(name = "BAS_DANGER")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasDangerModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasDanger";

	public static final class FieldNames {
		/**
		 * basDangerUuid
		 */
		public static final String basDangerUuid = "basDangerUuid";
		/**
		 * 危险品国际编号
		 */
		public static final String unCode = "unCode";
		/**
		 * 国内危险品编码
		 */
		public static final String cnCode = "cnCode";
		/**
		 * 危险品中文名称
		 */
		public static final String unName = "unName";
		/**
		 * 英文名称
		 */
		public static final String unNameEn = "unNameEn";
		/**
		 * 危品类别
		 */
		public static final String classNo = "classNo";
		/**
		 * IMDG规则页码
		 */
		public static final String imdgNo = "imdgNo";
		/**
		 * 医疗急救指南号
		 */
		public static final String mfagNo = "mfagNo";
		/**
		 * 危险品应急措施号
		 */
		public static final String emsNo = "emsNo";
		/**
		 * 备注
		 */
		public static final String remark = "remark";
		/**
		 * 状态
		 */
		public static final String status = "status";
		/**
		 * 中心代码
		 */
		public static final String centerCode = "centerCode";
		/**
		 * 控制字
		 */
		public static final String controlWord = "controlWord";
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

	//basDangerUuid
	private String basDangerUuid;
	//危险品国际编号
	private String unCode;
	//国内危险品编码
	private String cnCode;
	//危险品中文名称
	private String unName;
	//英文名称
	private String unNameEn;
	//危品类别
	private String classNo;
	//IMDG规则页码
	private String imdgNo;
	//医疗急救指南号
	private String mfagNo;
	//危险品应急措施号
	private String emsNo;
	//备注
	private String remark;
	//状态
	private String status;
	//中心代码
	private String centerCode;
	//控制字
	private String controlWord;
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
	 * Get basDangerUuid
	 */
	@Column(name = "BAS_DANGER_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getBasDangerUuid() {
		return basDangerUuid;
	}

	/**
	 * Set basDangerUuid
	 */
	public void setBasDangerUuid(String basDangerUuid) {
		this.basDangerUuid = basDangerUuid;
		addValidField(FieldNames.basDangerUuid);
	}

	/**
	 * Get 危险品国际编号
	 */
	@Column(name = "UN_CODE")
	public String getUnCode() {
		return unCode;
	}

	/**
	 * Set 危险品国际编号
	 */
	public void setUnCode(String unCode) {
		this.unCode = unCode;
		addValidField(FieldNames.unCode);
	}

	/**
	 * Get 国内危险品编码
	 */
	@Column(name = "CN_CODE")
	public String getCnCode() {
		return cnCode;
	}

	/**
	 * Set 国内危险品编码
	 */
	public void setCnCode(String cnCode) {
		this.cnCode = cnCode;
		addValidField(FieldNames.cnCode);
	}

	/**
	 * Get 危险品中文名称
	 */
	@Column(name = "UN_NAME")
	public String getUnName() {
		return unName;
	}

	/**
	 * Set 危险品中文名称
	 */
	public void setUnName(String unName) {
		this.unName = unName;
		addValidField(FieldNames.unName);
	}

	/**
	 * Get 英文名称
	 */
	@Column(name = "UN_NAME_EN")
	public String getUnNameEn() {
		return unNameEn;
	}

	/**
	 * Set 英文名称
	 */
	public void setUnNameEn(String unNameEn) {
		this.unNameEn = unNameEn;
		addValidField(FieldNames.unNameEn);
	}

	/**
	 * Get 危品类别
	 */
	@Column(name = "CLASS_NO")
	public String getClassNo() {
		return classNo;
	}

	/**
	 * Set 危品类别
	 */
	public void setClassNo(String classNo) {
		this.classNo = classNo;
		addValidField(FieldNames.classNo);
	}

	/**
	 * Get IMDG规则页码
	 */
	@Column(name = "IMDG_NO")
	public String getImdgNo() {
		return imdgNo;
	}

	/**
	 * Set IMDG规则页码
	 */
	public void setImdgNo(String imdgNo) {
		this.imdgNo = imdgNo;
		addValidField(FieldNames.imdgNo);
	}

	/**
	 * Get 医疗急救指南号
	 */
	@Column(name = "MFAG_NO")
	public String getMfagNo() {
		return mfagNo;
	}

	/**
	 * Set 医疗急救指南号
	 */
	public void setMfagNo(String mfagNo) {
		this.mfagNo = mfagNo;
		addValidField(FieldNames.mfagNo);
	}

	/**
	 * Get 危险品应急措施号
	 */
	@Column(name = "EMS_NO")
	public String getEmsNo() {
		return emsNo;
	}

	/**
	 * Set 危险品应急措施号
	 */
	public void setEmsNo(String emsNo) {
		this.emsNo = emsNo;
		addValidField(FieldNames.emsNo);
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
	 * Get 中心代码
	 */
	@Column(name = "CENTER_CODE")
	public String getCenterCode() {
		return centerCode;
	}

	/**
	 * Set 中心代码
	 */
	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
		addValidField(FieldNames.centerCode);
	}

	/**
	 * Get 控制字
	 * ：默认0
	 */
	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	/**
	 * Set 控制字
	 * ：默认0
	 */
	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField(FieldNames.controlWord);
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

	
	/**
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="basDangerUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
