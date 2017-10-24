package com.sinotrans.gd.wlp.basicdata.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@Entity
public class BasCntrPrefixQueryItem extends BaseQueryItem {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	private String id;
	private String status;
	private String cntrAdminCode;
	private String prefix;

	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		addValidField("id");
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		addValidField("status");
	}

	@Column(name = "CNTR_ADMIN_CODE")
	public String getCntrAdminCode() {
		return cntrAdminCode;
	}

	public void setCntrAdminCode(String cntrAdminCode) {
		this.cntrAdminCode = cntrAdminCode;
		addValidField("cntrAdminCode");
	}

	@Column(name = "PREFIX")
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
		addValidField("prefix");
	}

	

}
