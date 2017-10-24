package com.sinotrans.gd.wlp.system.model;

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
 * Model class for 菜单分组
 */
@Entity
@Table(name = "SYS_MENU_GROUP")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysMenuGroupModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "SysMenuGroup";

	public static final class FieldNames {
		/**
		 * 一级菜单UUID
		 */
		public static final String sysMenuGroupUuid = "sysMenuGroupUuid";
		/**
		 * 模块UUID
		 */
		public static final String sysModuleUuid = "sysModuleUuid";
		/**
		 * preSysMenuGroupUuid
		 */
		public static final String preSysMenuGroupUuid = "preSysMenuGroupUuid";
		/**
		 * 菜单组序号
		 */
		public static final String menuGrpSeq = "menuGrpSeq";
		/**
		 * 菜单分组代码
		 */
		public static final String menuGrpCode = "menuGrpCode";
		/**
		 * 菜单分组中文名
		 */
		public static final String menuGrpName = "menuGrpName";
		/**
		 * 菜单分组英文名
		 */
		public static final String menuGrpNameEn = "menuGrpNameEn";
		/**
		 * 备注
		 */
		public static final String remark = "remark";
		/**
		 * 状态
		 */
		public static final String status = "status";
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

	// 一级菜单UUID
	private String sysMenuGroupUuid;
	// 模块UUID
	private String sysModuleUuid;
	// preSysMenuGroupUuid
	private String preSysMenuGroupUuid;
	// 菜单组序号
	private Long menuGrpSeq;
	// 菜单分组代码
	private String menuGrpCode;
	// 菜单分组中文名
	private String menuGrpName;
	// 菜单分组英文名
	private String menuGrpNameEn;
	// 备注
	private String remark;
	// 状态
	private String status;
	// 控制字
	private String controlWord;
	// 公司（仓库）代码
	private String officeCode;
	// 并发访问控制
	private Long recVer;
	// 创建人
	private String creator;
	// 创建时间
	private Date createTime;
	// 修改人
	private String modifier;
	// 修改时间
	private Date modifyTime;

	/**
	 * Get 一级菜单UUID
	 */
	@Column(name = "SYS_MENU_GROUP_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getSysMenuGroupUuid() {
		return sysMenuGroupUuid;
	}

	/**
	 * Set 一级菜单UUID
	 */
	public void setSysMenuGroupUuid(String sysMenuGroupUuid) {
		this.sysMenuGroupUuid = sysMenuGroupUuid;
		addValidField(FieldNames.sysMenuGroupUuid);
	}

	/**
	 * Get 模块UUID
	 */
	@Column(name = "SYS_MODULE_UUID")
	public String getSysModuleUuid() {
		return sysModuleUuid;
	}

	/**
	 * Set 模块UUID
	 */
	public void setSysModuleUuid(String sysModuleUuid) {
		this.sysModuleUuid = sysModuleUuid;
		addValidField(FieldNames.sysModuleUuid);
	}

	/**
	 * Get preSysMenuGroupUuid
	 */
	@Column(name = "PRE_SYS_MENU_GROUP_UUID")
	public String getPreSysMenuGroupUuid() {
		return preSysMenuGroupUuid;
	}

	/**
	 * Set preSysMenuGroupUuid
	 */
	public void setPreSysMenuGroupUuid(String preSysMenuGroupUuid) {
		this.preSysMenuGroupUuid = preSysMenuGroupUuid;
		addValidField(FieldNames.preSysMenuGroupUuid);
	}

	/**
	 * Get 菜单组序号
	 */
	@Column(name = "MENU_GRP_SEQ")
	public Long getMenuGrpSeq() {
		return menuGrpSeq;
	}

	/**
	 * Set 菜单组序号
	 */
	public void setMenuGrpSeq(Long menuGrpSeq) {
		this.menuGrpSeq = menuGrpSeq;
		addValidField(FieldNames.menuGrpSeq);
	}

	/**
	 * Get 菜单分组代码
	 */
	@Column(name = "MENU_GRP_CODE")
	public String getMenuGrpCode() {
		return menuGrpCode;
	}

	/**
	 * Set 菜单分组代码
	 */
	public void setMenuGrpCode(String menuGrpCode) {
		this.menuGrpCode = menuGrpCode;
		addValidField(FieldNames.menuGrpCode);
	}

	/**
	 * Get 菜单分组中文名
	 */
	@Column(name = "MENU_GRP_NAME")
	public String getMenuGrpName() {
		return menuGrpName;
	}

	/**
	 * Set 菜单分组中文名
	 */
	public void setMenuGrpName(String menuGrpName) {
		this.menuGrpName = menuGrpName;
		addValidField(FieldNames.menuGrpName);
	}

	/**
	 * Get 菜单分组英文名
	 */
	@Column(name = "MENU_GRP_NAME_EN")
	public String getMenuGrpNameEn() {
		return menuGrpNameEn;
	}

	/**
	 * Set 菜单分组英文名
	 */
	public void setMenuGrpNameEn(String menuGrpNameEn) {
		this.menuGrpNameEn = menuGrpNameEn;
		addValidField(FieldNames.menuGrpNameEn);
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
	 * Get 状态 ：Active - 有效； Cancel - 作废
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * Set 状态 ：Active - 有效； Cancel - 作废
	 */
	public void setStatus(String status) {
		this.status = status;
		addValidField(FieldNames.status);
	}

	/**
	 * Get 控制字 ：默认0
	 */
	@Column(name = "CONTROL_WORD")
	public String getControlWord() {
		return controlWord;
	}

	/**
	 * Set 控制字 ：默认0
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
	private String prrmaryKeyName="sysMenuGroupUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}

}
