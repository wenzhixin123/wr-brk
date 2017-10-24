/**
 * 
 */
package com.sinotrans.gd.wlp.common.entity;

import java.io.Serializable;
import java.util.List;

import com.sinotrans.gd.wlp.basicdata.model.BasOrderNoModel;

/**
 * @author lenovo
 * 
 */
@SuppressWarnings("serial")
public class BasOrderNoPrintEntity implements Serializable {
	private List<BasOrderNoModel> basOrderNoList;

	public List<BasOrderNoModel> getBasOrderNoList() {
		return basOrderNoList;
	}

	public void setBasOrderNoList(List<BasOrderNoModel> basOrderNoList) {
		this.basOrderNoList = basOrderNoList;
	}
}
