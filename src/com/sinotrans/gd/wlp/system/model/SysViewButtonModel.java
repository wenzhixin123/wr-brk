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
 * Model class for 界面按钮
 */
@Entity
@Table(name = "SYS_VIEW_BUTTON")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class SysViewButtonModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "SysViewButton";

	public static final class FieldNames {
		/**
		 * 界面按钮UUID
		 */
		public static final String sysViewButtonUuid = "sysViewButtonUuid";
		/**
		 * 菜单项UUID
		 */
		public static final String sysMenuItemUuid = "sysMenuItemUuid";
		/**
		 * 按钮代码
		 */
		public static final String btnCode = "btnCode";
		/**
		 * 按钮名
		 */
		public static final String btnName = "btnName";
		/**
		 * 按钮英文名
		 */
		public static final String btnNameEn = "btnNameEn";
		/**
		 * 按钮链接
		 */
		public static final String btnUrl = "btnUrl";
		/**
		 * 按钮提交权限
		 */
		public static final String btnAction = "btnAction";
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

	// 界面按钮UUID
	private String sysViewButtonUuid;
	// 菜单项UUID
	private String sysMenuItemUuid;
	// 按钮代码
	private String btnCode;
	// 按钮名
	private String btnName;
	// 按钮英文名
	private String btnNameEn;
	// 按钮链接
	private String btnUrl;
	// 按钮提交权限
	private String btnAction;
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
	 * Get 界面按钮UUID
	 */
	@Column(name = "SYS_VIEW_BUTTON_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getSysViewButtonUuid() {
		return sysViewButtonUuid;
	}

	/**
	 * Set 界面按钮UUID
	 */
	public void setSysViewButtonUuid(String sysViewButtonUuid) {
		this.sysViewButtonUuid = sysViewButtonUuid;
		addValidField(FieldNames.sysViewButtonUuid);
	}

	/**
	 * Get 菜单项UUID
	 */
	@Column(name = "SYS_MENU_ITEM_UUID")
	public String getSysMenuItemUuid() {
		return sysMenuItemUuid;
	}

	/**
	 * Set 菜单项UUID
	 */
	public void setSysMenuItemUuid(String sysMenuItemUuid) {
		this.sysMenuItemUuid = sysMenuItemUuid;
		addValidField(FieldNames.sysMenuItemUuid);
	}

	/**
	 * Get 按钮代码
	 */
	@Column(name = "BTN_CODE")
	public String getBtnCode() {
		return btnCode;
	}

	/**
	 * Set 按钮代码
	 */
	public void setBtnCode(String btnCode) {
		this.btnCode = btnCode;
		addValidField(FieldNames.btnCode);
	}

	/**
	 * Get 按钮名
	 */
	@Column(name = "BTN_NAME")
	public String getBtnName() {
		return btnName;
	}

	/**
	 * Set 按钮名
	 */
	public void setBtnName(String btnName) {
		this.btnName = btnName;
		addValidField(FieldNames.btnName);
	}

	/**
	 * Get 按钮英文名
	 */
	@Column(name = "BTN_NAME_EN")
	public String getBtnNameEn() {
		return btnNameEn;
	}

	/**
	 * Set 按钮英文名
	 */
	public void setBtnNameEn(String btnNameEn) {
		this.btnNameEn = btnNameEn;
		addValidField(FieldNames.btnNameEn);
	}

	/**
	 * Get 按钮链接
	 */
	@Column(name = "BTN_URL")
	public String getBtnUrl() {
		return btnUrl;
	}

	/**
	 * Set 按钮链接
	 */
	public void setBtnUrl(String btnUrl) {
		this.btnUrl = btnUrl;
		addValidField(FieldNames.btnUrl);
	}

	/**
	 * Get 按钮提交权限
	 */
	@Column(name = "BTN_ACTION")
	public String getBtnAction() {
		return btnAction;
	}

	/**
	 * Set 按钮提交权限
	 */
	public void setBtnAction(String btnAction) {
		this.btnAction = btnAction;
		addValidField(FieldNames.btnAction);
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
	private String prrmaryKeyName="sysViewButtonUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}


}
