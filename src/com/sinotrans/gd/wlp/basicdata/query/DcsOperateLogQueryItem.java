package com.sinotrans.gd.wlp.basicdata.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

import com.sinotrans.framework.core.query.BaseQueryItem;
import com.sinotrans.gd.wlp.basicdata.model.DcsOperateLogModel.FieldNames;

@Entity
public class DcsOperateLogQueryItem extends BaseQueryItem {

	




	private String dataSourceUuid;
	private String dateSourceEntity;
	private String businessRefNo;
	private String currentValue;
	private String modifiedValue;
	private String currentModifier;
	private Date currentModifyTime;
	private String opeationType;
	private String creator;
	private Date createTime;

	

	/**
	 * Get 目标记录PK
	 */
	@Column(name = "DATA_SOURCE_UUID")
	public String getDataSourceUuid() {
		return dataSourceUuid;
	}

	/**
	 * Set 目标记录PK
	 */
	public void setDataSourceUuid(String dataSourceUuid) {
		this.dataSourceUuid = dataSourceUuid;
		addValidField(FieldNames.dataSourceUuid);
	}

	/**
	 * Get 目标表名
	 */
	@Column(name = "DATE_SOURCE_ENTITY")
	public String getDateSourceEntity() {
		return dateSourceEntity;
	}

	/**
	 * Set 目标表名
	 */
	public void setDateSourceEntity(String dateSourceEntity) {
		this.dateSourceEntity = dateSourceEntity;
		addValidField(FieldNames.dateSourceEntity);
	}

	/**
	 * Get 业务单号
	 */
	@Column(name = "BUSINESS_REF_NO")
	public String getBusinessRefNo() {
		return businessRefNo;
	}

	/**
	 * Set 业务单号
	 */
	public void setBusinessRefNo(String businessRefNo) {
		this.businessRefNo = businessRefNo;
		addValidField(FieldNames.businessRefNo);
	}

	/**
	 * Get 修改前值
	 */
	@Column(name = "CURRENT_VALUE")
	@Type(type="org.springframework.orm.hibernate3.support.ClobStringType")
	public String getCurrentValue() {
		return currentValue;
	}

	/**
	 * Set 修改前值
	 */
	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
		addValidField(FieldNames.currentValue);
	}

	/**
	 * Get 修改后值
	 */
	@Column(name = "MODIFIED_VALUE")
	@Type(type="org.springframework.orm.hibernate3.support.ClobStringType")
	public String getModifiedValue() {
		return modifiedValue;
	}

	/**
	 * Set 修改后值
	 */
	public void setModifiedValue(String modifiedValue) {
		this.modifiedValue = modifiedValue;
		addValidField(FieldNames.modifiedValue);
	}

	/**
	 * Get 原业务记录的最后修改人
	 */
	@Column(name = "CURRENT_MODIFIER")
	public String getCurrentModifier() {
		return currentModifier;
	}

	/**
	 * Set 原业务记录的最后修改人
	 */
	public void setCurrentModifier(String currentModifier) {
		this.currentModifier = currentModifier;
		addValidField(FieldNames.currentModifier);
	}

	/**
	 * Get 原业务记录的最后修改时间
	 */
	@Column(name = "CURRENT_MODIFY_TIME")
	public Date getCurrentModifyTime() {
		return currentModifyTime;
	}

	/**
	 * Set 原业务记录的最后修改时间
	 */
	public void setCurrentModifyTime(Date currentModifyTime) {
		this.currentModifyTime = currentModifyTime;
		addValidField(FieldNames.currentModifyTime);
	}

	/**
	 * Get 操作类型
	 * ：两种“修改”、“删除”
	 */
	@Column(name = "OPEATION_TYPE")
	public String getOpeationType() {
		return opeationType;
	}

	/**
	 * Set 操作类型
	 * ：两种“修改”、“删除”
	 */
	public void setOpeationType(String opeationType) {
		this.opeationType = opeationType;
		addValidField(FieldNames.opeationType);
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
	
}
