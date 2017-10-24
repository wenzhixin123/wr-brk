package com.sinotrans.gd.wlp.basicdata.entity;

import java.util.List;

import com.sinotrans.gd.wlp.basicdata.model.BasBomDetailModel;
import com.sinotrans.gd.wlp.basicdata.model.BasBomModel;

@SuppressWarnings("serial")
public class BasBomEntity extends BasBomModel {

	private List<BasBomDetailModel> basBomDetailModel; // BasBomDetail 信息

	private String bomTypeCode;

	private String bomTypeName;

	public List<BasBomDetailModel> getBasBomDetailModel() {
		return basBomDetailModel;
	}

	public void setBasBomDetailModel(List<BasBomDetailModel> basBomDetailModel) {
		this.basBomDetailModel = basBomDetailModel;
	}

	public String getBomTypeCode() {
		return bomTypeCode;
	}

	public void setBomTypeCode(String bomTypeCode) {
		this.bomTypeCode = bomTypeCode;
	}

	public String getBomTypeName() {
		return bomTypeName;
	}

	public void setBomTypeName(String bomTypeName) {
		this.bomTypeName = bomTypeName;
	}

}
