package com.sinotrans.gd.wlp.basicdata.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@Entity
public class CodeTypeByCodeValueQueryItem extends BaseQueryItem {

	private String basCodeTypeUuid;
	private String typeCode;
	private String typeName;
	private String status;
	private String controlWord;
	private String createTime;

	@Column(name = "BASCODETYPEUUID")
	public String getBasCodeTypeUuid() {
		return basCodeTypeUuid;
	}

	public void setBasCodeTypeUuid(String basCodeTypeUuid) {
		this.basCodeTypeUuid = basCodeTypeUuid;
		addValidField("basCodeTypeUuid");
	}

	@Column(name = "TYPECODE")
	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
		addValidField("typeCode");
	}

	@Column(name = "TYPENAME")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
		addValidField("typeName");
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		addValidField("status");
	}

	@Column(name = "CONTROLWORD")
	public String getControlWord() {
		return controlWord;
	}

	public void setControlWord(String controlWord) {
		this.controlWord = controlWord;
		addValidField("controlWord");
	}

	@Column(name = "CREATE_TIME")
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
		addValidField("createTime");
	}

}
