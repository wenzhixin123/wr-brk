package com.sinotrans.gd.wlp.report.entity;

import java.util.HashMap;

public class PrintSessionEntity {
	private TablePrintEntity tablePrintEntity;
	private HashMap<String,Object> parameters;
	
	public TablePrintEntity getTablePrintEntity() {
		return tablePrintEntity;
	}
	public void setTablePrintEntity(TablePrintEntity tablePrintEntity) {
		this.tablePrintEntity = tablePrintEntity;
	}
	
	public void setParameters(HashMap<String, Object> parameters) {
		this.parameters = parameters;
	}

	public HashMap<String, Object> getParameters() {
		if(parameters==null){
			HashMap<String, Object> parameters = new HashMap<String, Object>();
			this.setParameters(parameters);
			return parameters;
		}else{
			return parameters;
		}
	}
}
