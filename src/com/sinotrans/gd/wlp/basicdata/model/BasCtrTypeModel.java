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
 * Model class for 箱型表
 */
@Entity
@Table(name = "BAS_CTR_TYPE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class BasCtrTypeModel extends BaseModelClass implements OperationLog {

	private static final long serialVersionUID = 1L;

	public static final String MODEL_NAME = "BasCtrType";

	public static final class FieldNames {
		/**
		 * 箱型UUID
		 */
		public static final String basCtrTypeUuid = "basCtrTypeUuid";
		/**
		 * 箱型
		 */
		public static final String typeCode = "typeCode";
		/**
		 * 箱型描述
		 */
		public static final String typeDesc = "typeDesc";
		/**
		 * 长
		 */
		public static final String length = "length";
		/**
		 * 宽
		 */
		public static final String width = "width";
		/**
		 * 高
		 */
		public static final String height = "height";
		/**
		 * 皮重
		 */
		public static final String tareWeight = "tareWeight";
		/**
		 * 有效载重
		 */
		public static final String payload = "payload";
		/**
		 * 标准箱
		 */
		public static final String teu = "teu";
		/**
		 * 国际编码
		 */
		public static final String ctrIso = "ctrIso";
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

	// 箱型UUID
	private String basCtrTypeUuid;
	// 箱型
	private String typeCode;
	// 箱型描述
	private String typeDesc;
	// 长
	private Double length;
	// 宽
	private Double width;
	// 高
	private Double height;
	// 皮重
	private Double tareWeight;
	// 有效载重
	private Double payload;
	// 标准箱
	private Double teu;
	// 国际编码
	private String ctrIso;
	// 备注
	private String remark;
	// 状态
	private String status;
	// 中心代码
	private String centerCode;
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
	 * Get 箱型UUID
	 */
	@Column(name = "BAS_CTR_TYPE_UUID")
	@Id
	@GeneratedValue(generator = "UUIDGenerator")
	public String getBasCtrTypeUuid() {
		return basCtrTypeUuid;
	}

	/**
	 * Set 箱型UUID
	 */
	public void setBasCtrTypeUuid(String basCtrTypeUuid) {
		this.basCtrTypeUuid = basCtrTypeUuid;
		addValidField(FieldNames.basCtrTypeUuid);
	}

	/**
	 * Get 箱型
	 */
	@Column(name = "TYPE_CODE")
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * Set 箱型
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
		addValidField(FieldNames.typeCode);
	}

	/**
	 * Get 箱型描述
	 */
	@Column(name = "TYPE_DESC")
	public String getTypeDesc() {
		return typeDesc;
	}

	/**
	 * Set 箱型描述
	 */
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
		addValidField(FieldNames.typeDesc);
	}

	/**
	 * Get 长
	 */
	@Column(name = "LENGTH")
	public Double getLength() {
		return length;
	}

	/**
	 * Set 长
	 */
	public void setLength(Double length) {
		this.length = length;
		addValidField(FieldNames.length);
	}

	/**
	 * Get 宽
	 */
	@Column(name = "WIDTH")
	public Double getWidth() {
		return width;
	}

	/**
	 * Set 宽
	 */
	public void setWidth(Double width) {
		this.width = width;
		addValidField(FieldNames.width);
	}

	/**
	 * Get 高
	 */
	@Column(name = "HEIGHT")
	public Double getHeight() {
		return height;
	}

	/**
	 * Set 高
	 */
	public void setHeight(Double height) {
		this.height = height;
		addValidField(FieldNames.height);
	}

	/**
	 * Get 皮重
	 */
	@Column(name = "TARE_WEIGHT")
	public Double getTareWeight() {
		return tareWeight;
	}

	/**
	 * Set 皮重
	 */
	public void setTareWeight(Double tareWeight) {
		this.tareWeight = tareWeight;
		addValidField(FieldNames.tareWeight);
	}

	/**
	 * Get 有效载重
	 */
	@Column(name = "PAYLOAD")
	public Double getPayload() {
		return payload;
	}

	/**
	 * Set 有效载重
	 */
	public void setPayload(Double payload) {
		this.payload = payload;
		addValidField(FieldNames.payload);
	}

	/**
	 * Get 标准箱
	 */
	@Column(name = "TEU")
	public Double getTeu() {
		return teu;
	}

	/**
	 * Set 标准箱
	 */
	public void setTeu(Double teu) {
		this.teu = teu;
		addValidField(FieldNames.teu);
	}

	/**
	 * Get 国际编码
	 */
	@Column(name = "CTR_ISO")
	public String getCtrIso() {
		return ctrIso;
	}

	/**
	 * Set 国际编码
	 */
	public void setCtrIso(String ctrIso) {
		this.ctrIso = ctrIso;
		addValidField(FieldNames.ctrIso);
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
	private String prrmaryKeyName="basCtrTypeUuid";
	@Transient
	public String getPrrmaryKeyName() {
		return prrmaryKeyName;
	}

	public void setPrrmaryKeyName(String prrmaryKeyName) {
		this.prrmaryKeyName = prrmaryKeyName;
	}
}
