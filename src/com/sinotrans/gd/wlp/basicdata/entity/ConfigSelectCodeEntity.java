/**
 * 
 */
package com.sinotrans.gd.wlp.basicdata.entity;

import java.io.Serializable;

/**
 * @author sky
 * 
 */
@SuppressWarnings("serial")
public class ConfigSelectCodeEntity implements Serializable {
	private String locPlanConfigUuid;
	private String configCode;
	private String configName;
	private String configNameEn;

	
	public String getConfigNameEn() {
		return configNameEn;
	}

	public void setConfigNameEn(String configNameEn) {
		this.configNameEn = configNameEn;
	}

	public String getLocPlanConfigUuid() {
		return locPlanConfigUuid;
	}

	public void setLocPlanConfigUuid(String locPlanConfigUuid) {
		this.locPlanConfigUuid = locPlanConfigUuid;
	}

	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}
}
