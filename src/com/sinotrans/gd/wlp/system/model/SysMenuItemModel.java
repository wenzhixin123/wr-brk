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
 * Model class for 菜单项
 */
@Entity
@Table(name = "SYS_MENU_ITEM")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysMenuItemModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "SysMenuItem";

	public static final class FieldNames {
		/**
		 * 菜单项ID
		 */
		public static final String sysMenuItemUuid = "sysMenuItemUuid";
		/**
		 * 一级菜单ID
		 */
		public static final String sysMenuGroupUuid = "sysMenuGroupUuid";
		/**
		 * 菜单排序号
		 */
		public static final String menuItemSeq = "menuItemSeq";
		/**
		 * 菜单代码
		 */
		public static final String menuItemCode = "menuItemCode";
		/**
		 * 菜单中文名
		 */
		public static final String menuItemName = "menuItemName";
		/**
		 * 菜单英文名
		 */
		public static final String menuItemNameCn = "menuItemNameCn";
		/**
		 * 菜单链接
		 */
		public static final String menuItemUrl = "menuItemUrl";
		/**
		 * 功能提交权限
		 */
		public static final String menuItemAction = "menuItemAction";
		/**
		 * 类
		 */
		public static final String className = "className";
		/**
		 * 是否对话框
		 */
		public static final String isDialog = "isDialog";
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

	// 菜单项ID
	private String sysMenuItemUuid;
	// 一级菜单ID
	private String sysMenuGroupUuid;
	// 菜单排序号
	private Long menuItemSeq;
	// 菜单代码
	private String menuItemCode;
	// 菜单中文名
	private String menuItemName;
	// 菜单英文名
	private String menuItemNameCn;
	// 菜单链接
	private String menuItemUrl;
	// 功能提交权限
	private String menuItemAction;
	// 类
	private String className;
	// 是否对话框
	private Integer isDialog;
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
	 * Get 菜单项ID
	 */
	@Column(name = "SYS_MENU_ITEM_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getSysMenuItemUuid() {
		return sysMenuItemUuid;
	}

	/**
	 * Set 菜单项ID
	 */
	public void setSysMenuItemUuid(String sysMenuItemUuid) {
		this.sysMenuItemUuid = sysMenuItemUuid;
		addValidField(FieldNames.sysMenuItemUuid);
	}

	/**
	 * Get 一级菜单ID
	 */
	@Column(name = "SYS_MENU_GROUP_UUID")
	public String getSysMenuGroupUuid() {
		return sysMenuGroupUuid;
	}

	/**
	 * Set 一级菜单ID
	 */
	public void setSysMenuGroupUuid(String sysMenuGroupUuid) {
		this.sysMenuGroupUuid = sysMenuGroupUuid;
		addValidField(FieldNames.sysMenuGroupUuid);
	}

	/**
	 * Get 菜单排序号
	 */
	@Column(name = "MENU_ITEM_SEQ")
	public Long getMenuItemSeq() {
		return menuItemSeq;
	}

	/**
	 * Set 菜单排序号
	 */
	public void setMenuItemSeq(Long menuItemSeq) {
		this.menuItemSeq = menuItemSeq;
		addValidField(FieldNames.menuItemSeq);
	}

	/**
	 * Get 菜单代码
	 */
	@Column(name = "MENU_ITEM_CODE")
	public String getMenuItemCode() {
		return menuItemCode;
	}

	/**
	 * Set 菜单代码
	 */
	public void setMenuItemCode(String menuItemCode) {
		this.menuItemCode = menuItemCode;
		addValidField(FieldNames.menuItemCode);
	}

	/**
	 * Get 菜单中文名
	 */
	@Column(name = "MENU_ITEM_NAME")
	public String getMenuItemName() {
		return menuItemName;
	}

	/**
	 * Set 菜单中文名
	 */
	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
		addValidField(FieldNames.menuItemName);
	}

	/**
	 * Get 菜单英文名
	 */
	@Column(name = "MENU_ITEM_NAME_CN")
	public String getMenuItemNameCn() {
		return menuItemNameCn;
	}

	/**
	 * Set 菜单英文名
	 */
	public void setMenuItemNameCn(String menuItemNameCn) {
		this.menuItemNameCn = menuItemNameCn;
		addValidField(FieldNames.menuItemNameCn);
	}

	/**
	 * Get 菜单链接
	 */
	@Column(name = "MENU_ITEM_URL")
	public String getMenuItemUrl() {
		return menuItemUrl;
	}

	/**
	 * Set 菜单链接
	 */
	public void setMenuItemUrl(String menuItemUrl) {
		this.menuItemUrl = menuItemUrl;
		addValidField(FieldNames.menuItemUrl);
	}

	/**
	 * Get 功能提交权限
	 */
	@Column(name = "MENU_ITEM_ACTION")
	public String getMenuItemAction() {
		return menuItemAction;
	}

	/**
	 * Set 功能提交权限
	 */
	public void setMenuItemAction(String menuItemAction) {
		this.menuItemAction = menuItemAction;
		addValidField(FieldNames.menuItemAction);
	}

	/**
	 * Get 类 名称
	 */
	@Column(name = "CLASS_NAME")
	public String getClassName() {
		return className;
	}

	/**
	 * Set 类 名称
	 */
	public void setClassName(String className) {
		this.className = className;
		addValidField(FieldNames.className);
	}

	/**
	 * Get 是否对话框
	 */
	@Column(name = "IS_DIALOG")
	public Integer getIsDialog() {
		return isDialog;
	}

	/**
	 * Set 是否对话框
	 */
	public void setIsDialog(Integer isDialog) {
		this.isDialog = isDialog;
		addValidField(FieldNames.isDialog);
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
	private String prrmaryKeyName="sysMenuItemUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
