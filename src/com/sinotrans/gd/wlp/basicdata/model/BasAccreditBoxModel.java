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
 * Model class for 控箱公司授权表
 */
@Entity
@Table(name = "BAS_ACCREDIT_BOX")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasAccreditBoxModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasAccreditBox";

	public static final class FieldNames {
		/**
		 * uuid
		 */
		public static final String bedUuid = "bedUuid";
		/**
		 * 控箱公司
		 */
		public static final String bedControlBoxCompany = "bedControlBoxCompany";
		/**
		 * 委托单位
		 */
		public static final String bedEntrustUnit = "bedEntrustUnit";
		/**
		 * 作业点
		 */
		public static final String bedWorkPiont = "bedWorkPiont";
		/**
		 * 业务类型
		 */
		public static final String bedBusinessType = "bedBusinessType";
		/**
		 * 作业项目
		 */
		public static final String bedWorkProject = "bedWorkProject";
		/**
		 * 预留字段1
		 */
		public static final String bedCol1 = "bedCol1";
		/**
		 * 预留字段2
		 */
		public static final String bedCol2 = "bedCol2";
		/**
		 * 预留字段3
		 */
		public static final String bedCol3 = "bedCol3";
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
		/**
		 * 公司（仓库）代码
		 */
		public static final String officeCode = "officeCode";
		/**
		 * 版本号[REC_VER]
		 */
		public static final String recVer = "recVer";
	}

	//uuid
	private String bedUuid;
	//控箱公司
	private String bedControlBoxCompany;
	//委托单位
	private String bedEntrustUnit;
	//作业点
	private String bedWorkPiont;
	//业务类型
	private String bedBusinessType;
	//作业项目
	private String bedWorkProject;
	//预留字段1
	private String bedCol1;
	//预留字段2
	private String bedCol2;
	//预留字段3
	private String bedCol3;
	//创建人
	private String creator;
	//创建时间
	private Date createTime;
	//修改人
	private String modifier;
	//修改时间
	private Date modifyTime;
	//公司（仓库）代码
	private String officeCode;
	//版本号[REC_VER]
	private Long recVer;

	/**
	 * Get uuid
	 */
	@Column(name = "BED_UUID")
	@Id @GeneratedValue(generator = "UUIDGenerator")
	public String getBedUuid() {
		return bedUuid;
	}

	/**
	 * Set uuid
	 */
	public void setBedUuid(String bedUuid) {
		this.bedUuid = bedUuid;
		addValidField(FieldNames.bedUuid);
	}

	/**
	 * Get 控箱公司
	 */
	@Column(name = "BED_CONTROL_BOX_COMPANY")
	public String getBedControlBoxCompany() {
		return bedControlBoxCompany;
	}

	/**
	 * Set 控箱公司
	 */
	public void setBedControlBoxCompany(String bedControlBoxCompany) {
		this.bedControlBoxCompany = bedControlBoxCompany;
		addValidField(FieldNames.bedControlBoxCompany);
	}

	/**
	 * Get 委托单位
	 */
	@Column(name = "BED_ENTRUST_UNIT")
	public String getBedEntrustUnit() {
		return bedEntrustUnit;
	}

	/**
	 * Set 委托单位
	 */
	public void setBedEntrustUnit(String bedEntrustUnit) {
		this.bedEntrustUnit = bedEntrustUnit;
		addValidField(FieldNames.bedEntrustUnit);
	}

	/**
	 * Get 作业点
	 */
	@Column(name = "BED_WORK_PIONT")
	public String getBedWorkPiont() {
		return bedWorkPiont;
	}

	/**
	 * Set 作业点
	 */
	public void setBedWorkPiont(String bedWorkPiont) {
		this.bedWorkPiont = bedWorkPiont;
		addValidField(FieldNames.bedWorkPiont);
	}

	/**
	 * Get 业务类型
	 */
	@Column(name = "BED_BUSINESS_TYPE")
	public String getBedBusinessType() {
		return bedBusinessType;
	}

	/**
	 * Set 业务类型
	 */
	public void setBedBusinessType(String bedBusinessType) {
		this.bedBusinessType = bedBusinessType;
		addValidField(FieldNames.bedBusinessType);
	}

	/**
	 * Get 作业项目
	 */
	@Column(name = "BED_WORK_PROJECT")
	public String getBedWorkProject() {
		return bedWorkProject;
	}

	/**
	 * Set 作业项目
	 */
	public void setBedWorkProject(String bedWorkProject) {
		this.bedWorkProject = bedWorkProject;
		addValidField(FieldNames.bedWorkProject);
	}

	/**
	 * Get 预留字段1
	 */
	@Column(name = "BED_COL1")
	public String getBedCol1() {
		return bedCol1;
	}

	/**
	 * Set 预留字段1
	 */
	public void setBedCol1(String bedCol1) {
		this.bedCol1 = bedCol1;
		addValidField(FieldNames.bedCol1);
	}

	/**
	 * Get 预留字段2
	 */
	@Column(name = "BED_COL2")
	public String getBedCol2() {
		return bedCol2;
	}

	/**
	 * Set 预留字段2
	 */
	public void setBedCol2(String bedCol2) {
		this.bedCol2 = bedCol2;
		addValidField(FieldNames.bedCol2);
	}

	/**
	 * Get 预留字段3
	 */
	@Column(name = "BED_COL3")
	public String getBedCol3() {
		return bedCol3;
	}

	/**
	 * Set 预留字段3
	 */
	public void setBedCol3(String bedCol3) {
		this.bedCol3 = bedCol3;
		addValidField(FieldNames.bedCol3);
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
	 * 定义主键名称  非持久化字段啊
	 * 
	 */
	private String prrmaryKeyName="basAreaUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
