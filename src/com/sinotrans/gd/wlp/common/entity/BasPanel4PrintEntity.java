/**
 * 
 */
package com.sinotrans.gd.wlp.common.entity;

import java.io.Serializable;
import java.util.List;

import com.sinotrans.gd.wlp.basicdata.model.BasPanelModel;

/**
 * @author lenovo
 * 
 */
@SuppressWarnings("serial")
public class BasPanel4PrintEntity implements Serializable {
	private List<BasPanelModel> basPanelList;

	public List<BasPanelModel> getBasPanelList() {
		return basPanelList;
	}

	public void setBasPanelList(List<BasPanelModel> basPanelList) {
		this.basPanelList = basPanelList;
	}
}
