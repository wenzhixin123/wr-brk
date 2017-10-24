package com.sinotrans.gd.wlp.outbound.service;

import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.entity.SubmitOrderToLogisticsOrderEntity;
import com.sinotrans.gd.wlp.common.model.SubmitOrderDetailModel;
import com.sinotrans.gd.wlp.common.model.SubmitOrderModel;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;

public interface SubmitOrderOutManager extends BaseManager {

	/**
	 * 保存出库订单
	 * @param soModel
	 * @param sodModelList
	 * @param officeCode
	 * @return
	 * @throws Exception
	 */
	public SinotransPageJson saveSubmitOrderSot(SubmitOrderModel soModel,List<SubmitOrderDetailModel> sodModelList, String officeCode)throws Exception;

	/**
	 * 提交出库订单
	 * @param soModel
	 * @param sodModelList
	 * @param soUuid
	 * @param officeCode
	 * @return
	 * @throws Exception
	 */
	public SinotransPageJson auditOrderSot(SubmitOrderModel soModel,List<SubmitOrderDetailModel> sodModelList, String soUuid,String officeCode) throws Exception;

	/**
	 * 提交出库订单验证库存
	 * @param submitOrderUuid
	 * @return
	 * @throws Exception
	 */
	public SinotransPageJson checkStockIsExist(String submitOrderUuid)throws Exception;

	/**
	 * 出库单生成方法
	 * @param soToLoEntity
	 * @return
	 * @throws Exception
	 */
	public String generateOutboundOrder(SubmitOrderToLogisticsOrderEntity soToLoEntity)
			throws Exception;

	/**
	 * 写入错误信息记录submitOrderLog
	 * @param submitOrderUuid
	 * @param transactionType
	 * @param workDesc
	 * @param workPerson
	 * @param remark
	 * @param officeCode
	 */
	public void saveSolog(String submitOrderUuid, String transactionType,
			String workDesc, String workPerson, String remark, String officeCode);

}
