package com.sinotrans.gd.wlp.common.entity;

import java.util.List;

import com.sinotrans.gd.wlp.common.model.SubmitOrderContainerModel;
import com.sinotrans.gd.wlp.common.model.SubmitOrderDetailModel;
import com.sinotrans.gd.wlp.common.model.SubmitOrderModel;


public class SubmitOrderToLogisticsOrderEntity {

	/**So对象,必须带出库策略*/
	private SubmitOrderModel soModel;
	/**Sod对象*/
	private List<SubmitOrderDetailModel> sodModelList;
	/**Soc对象*/
	private List<SubmitOrderContainerModel> socModelList;
	/**SoUuid*/
	private String submitOrderUuid;
	/**LoUuid*/
	private String logisticsOrderUuid;
	/**是否保留生成的So单信息,true 保留，false 不保留*/
	private boolean retention;
	/**拣货策略*/
	private String configCode;
	/**lo单号生成规则*/
	private String orderNOGenerateRule;
	/**根据RH生成作业单或者根据RS生成作业单(RH--false、RS--true)*/
	private boolean isRhOrRs=false;
	
	/**
	 * 基础构造方法1
	 * @param soModel So对象,必须带出库策略
	 * @param sodModelList
	 * @param isRetention 是否保留生成的So单信息
	 * @param configCode 拣货策略(该参数现在暂不使用)
	 * @param orderNOGenerateRule lo单号生成规则
	 */
	public SubmitOrderToLogisticsOrderEntity(SubmitOrderModel soModel,
			List<SubmitOrderDetailModel> sodModelList, boolean retention,
			String configCode, String orderNOGenerateRule) {
		super();
		this.soModel = soModel;
		this.sodModelList = sodModelList;
		this.retention = retention;
		this.configCode = configCode;
		this.orderNOGenerateRule = orderNOGenerateRule;
	}
	/**
	 * 基础构造方法2(出库策略带有关联的拣货策略)
	 * @param soModel So对象,必须带出库策略
	 * @param sodModelList
	 * @param isRetention 是否保留生成的So单信息
	 * @param orderNOGenerateRule lo单号生成规则
	 */
	public SubmitOrderToLogisticsOrderEntity(SubmitOrderModel soModel,
			List<SubmitOrderDetailModel> sodModelList, boolean retention, String orderNOGenerateRule) {
		super();
		this.soModel = soModel;
		this.sodModelList = sodModelList;
		this.retention = retention;
		this.orderNOGenerateRule = orderNOGenerateRule;
	}
	public SubmitOrderToLogisticsOrderEntity(SubmitOrderModel soModel,
			List<SubmitOrderDetailModel> sodModelList,
			List<SubmitOrderContainerModel> socModelList,
			String submitOrderUuid, String logisticsOrderUuid,
			boolean retention, String configCode, String orderNOGenerateRule) {
		super();
		this.soModel = soModel;
		this.sodModelList = sodModelList;
		this.socModelList = socModelList;
		this.submitOrderUuid = submitOrderUuid;
		this.logisticsOrderUuid = logisticsOrderUuid;
		this.retention = retention;
		this.configCode = configCode;
		this.orderNOGenerateRule = orderNOGenerateRule;
	}
	public SubmitOrderModel getSoModel() {
		return soModel;
	}
	public void setSoModel(SubmitOrderModel soModel) {
		this.soModel = soModel;
	}
	public List<SubmitOrderDetailModel> getSodModelList() {
		return sodModelList;
	}
	public void setSodModelList(List<SubmitOrderDetailModel> sodModelList) {
		this.sodModelList = sodModelList;
	}
	public List<SubmitOrderContainerModel> getSocModelList() {
		return socModelList;
	}
	public void setSocModelList(List<SubmitOrderContainerModel> socModelList) {
		this.socModelList = socModelList;
	}
	public String getSubmitOrderUuid() {
		return submitOrderUuid;
	}
	public void setSubmitOrderUuid(String submitOrderUuid) {
		this.submitOrderUuid = submitOrderUuid;
	}
	public String getLogisticsOrderUuid() {
		return logisticsOrderUuid;
	}
	public void setLogisticsOrderUuid(String logisticsOrderUuid) {
		this.logisticsOrderUuid = logisticsOrderUuid;
	}
	
	public boolean isRetention() {
		return retention;
	}

	public void setRetention(boolean retention) {
		this.retention = retention;
	}

	public String getConfigCode() {
		return configCode;
	}
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}
	public String getOrderNOGenerateRule() {
		return orderNOGenerateRule;
	}
	public void setOrderNOGenerateRule(String orderNOGenerateRule) {
		this.orderNOGenerateRule = orderNOGenerateRule;
	}
	/**根据RH生成作业单或者根据RS生成作业单(RH--false、RS--true),默认RH*/
	public boolean isRhOrRs() {
		return isRhOrRs;
	}
	/**
	 * 根据RH生成作业单或者根据RS生成作业单(RH--false、RS--true),默认RH
	 * @param isRhOrRs
	 */
	public void setRhOrRs(boolean isRhOrRs) {
		this.isRhOrRs = isRhOrRs;
	}
	
}
