package com.sinotrans.gd.wlp.system.query;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.sinotrans.framework.core.query.BaseQueryItem;

@Entity
public class ExtApiTokenQueryItem extends BaseQueryItem {

	private String usercode;
	private String username;
	private String customercode;
	private String status;
	private String clientid;
	private String clientsecret;

	@Column(name = "USERCODE")
	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
		addValidField("usercode");
	}

	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		addValidField("username");
	}

	@Column(name = "CUSTOMERCODE")
	public String getCustomercode() {
		return customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
		addValidField("customercode");
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		addValidField("status");
	}

	@Column(name = "CLIENTID")
	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
		addValidField("clientid");
	}

	@Column(name = "CLIENTSECRET")
	public String getClientsecret() {
		return clientsecret;
	}

	public void setClientsecret(String clientsecret) {
		this.clientsecret = clientsecret;
		addValidField("clientsecret");
	}

}
