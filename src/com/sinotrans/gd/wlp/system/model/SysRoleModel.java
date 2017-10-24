package com.sinotrans.gd.wlp.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.sinotrans.framework.core.model.BaseModelClass;
import com.sinotrans.framework.core.model.OperationLog;
import com.sinotrans.gd.wlp.util.CustomDateSerializer;

/**
 * Model class for 角色
 */
@Entity
@Table(name = "SYS_ROLE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysRoleModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "SysRole";

	public static final class FieldNames {
		/**
		 * 角色UUID
		 */
		public static final String sysRoleUuid = "sysRoleUuid";
		/**
		 * 角色代码
		 */
		public static final String roleCode = "roleCode";
		/**
		 * 角色中文名
		 */
		public static final String roleName = "roleName";
		/**
		 * 角色英文名称
		 */
		public static final String roleNameEn = "roleNameEn";
		/**
		 * 允许查询
		 */
		public static final String canQueryallFlag = "canQueryallFlag";
		/**
		 * 虚拟的
		 */
		public static final String isVirtualFlag = "isVirtualFlag";
		/**
		 * 生效日期
		 */
		public static final String activeDate = "activeDate";
		/**
		 * 失效日期
		 */
		public static final String expiredDate = "expiredDate";
		/**
		 * 删除标志
		 */
		public static final String deletedFlag = "deletedFlag";
		/**
		 * 角色类别
		 */
		public static final String roleType = "roleType";
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

	// 角色UUID
	private String sysRoleUuid;
	// 角色代码
	private String roleCode;
	// 角色中文名
	private String roleName;
	// 角色英文名称
	private String roleNameEn;
	// 允许查询
	private Integer canQueryallFlag;
	// 虚拟的
	private Integer isVirtualFlag;
	// 生效日期
	private Date activeDate;
	// 失效日期
	private Date expiredDate;
	// 删除标志
	private Integer deletedFlag;
	// 角色类别
	private String roleType;
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
	 * Get 角色UUID
	 */
	@Column(name = "SYS_ROLE_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getSysRoleUuid() {
		return sysRoleUuid;
	}

	/**
	 * Set 角色UUID
	 */
	public void setSysRoleUuid(String sysRoleUuid) {
		this.sysRoleUuid = sysRoleUuid;
		addValidField(FieldNames.sysRoleUuid);
	}

	/**
	 * Get 角色代码
	 */
	@Column(name = "ROLE_CODE")
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * Set 角色代码
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
		addValidField(FieldNames.roleCode);
	}

	/**
	 * Get 角色中文名
	 */
	@Column(name = "ROLE_NAME")
	public String getRoleName() {
		return roleName;
	}

	/**
	 * Set 角色中文名
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
		addValidField(FieldNames.roleName);
	}

	/**
	 * Get 角色英文名称
	 */
	@Column(name = "ROLE_NAME_EN")
	public String getRoleNameEn() {
		return roleNameEn;
	}

	/**
	 * Set 角色英文名称
	 */
	public void setRoleNameEn(String roleNameEn) {
		this.roleNameEn = roleNameEn;
		addValidField(FieldNames.roleNameEn);
	}

	/**
	 * Get 允许查询
	 */
	@Column(name = "CAN_QUERYALL_FLAG")
	public Integer getCanQueryallFlag() {
		return canQueryallFlag;
	}

	/**
	 * Set 允许查询
	 */
	public void setCanQueryallFlag(Integer canQueryallFlag) {
		this.canQueryallFlag = canQueryallFlag;
		addValidField(FieldNames.canQueryallFlag);
	}

	/**
	 * Get 虚拟的
	 */
	@Column(name = "IS_VIRTUAL_FLAG")
	public Integer getIsVirtualFlag() {
		return isVirtualFlag;
	}

	/**
	 * Set 虚拟的
	 */
	public void setIsVirtualFlag(Integer isVirtualFlag) {
		this.isVirtualFlag = isVirtualFlag;
		addValidField(FieldNames.isVirtualFlag);
	}

	/**
	 * Get 生效日期
	 */
	@Column(name = "ACTIVE_DATE")
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getActiveDate() {
		return activeDate;
	}

	/**
	 * Set 生效日期
	 */
	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
		addValidField(FieldNames.activeDate);
	}

	/**
	 * Get 失效日期
	 */
	@Column(name = "EXPIRED_DATE")
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getExpiredDate() {
		return expiredDate;
	}

	/**
	 * Set 失效日期
	 */
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
		addValidField(FieldNames.expiredDate);
	}

	/**
	 * Get 删除标志
	 */
	@Column(name = "DELETED_FLAG")
	public Integer getDeletedFlag() {
		return deletedFlag;
	}

	/**
	 * Set 删除标志
	 */
	public void setDeletedFlag(Integer deletedFlag) {
		this.deletedFlag = deletedFlag;
		addValidField(FieldNames.deletedFlag);
	}

	/**
	 * Get 角色类别
	 */
	@Column(name = "ROLE_TYPE")
	public String getRoleType() {
		return roleType;
	}

	/**
	 * Set 角色类别
	 */
	public void setRoleType(String roleType) {
		this.roleType = roleType;
		addValidField(FieldNames.roleType);
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
	private String prrmaryKeyName="sysRoleUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
	

}
