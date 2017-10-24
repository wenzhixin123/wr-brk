package com.sinotrans.gd.wlp.inbound.service;

import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.model.SubmitOrderDetailModel;
import com.sinotrans.gd.wlp.common.model.SubmitOrderModel;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;

public interface SubmitOrderManager extends BaseManager {

	SubmitOrderModel get(String id);
	

	/**
	* 删除明细(公用)
	* 
	* @param so
	* @param sodList
	* @return
	*/
public SinotransPageJson delSubmitOrderDetail(String doUUid,String dodUuid);

public SubmitOrderModel getSubmitOrderByUuid(String submitOrderUuid);

/**
 * 保存单头和明细
 * @param soModel
 * @param sodModelDeailModelList
 * @param officeCode
 * @return
 * @throws Exception
 */
public SinotransPageJson saveSubmitOrder(SubmitOrderModel soModel,List<SubmitOrderDetailModel> sodModelDeailModelList, String officeCode)
		throws Exception;

/**
 * //回显数据
 * @param submitOrderUuid
 * @return
 */
SinotransPageJson findSubmitOrderByUuid(String submitOrderUuid);

/**
 * 提交
 * @param soModel
 * @param sodModelList
 * @param soUuid
 * @param officeCode
 * @return
 * @throws Exception
 */
SinotransPageJson auditOrder(SubmitOrderModel soModel,List<SubmitOrderDetailModel> sodModelList, String soUuid,String officeCode) throws Exception;
/**
 * 取消提交
 * @param soModels
 * @param sodModelList
 * @return
 */
SinotransPageJson cancelAuditOrder(List<SubmitOrderModel> soModels,List<SubmitOrderDetailModel> sodModelList);

/**
 * 删除入库订单
 * @param soUuid
 * @param officeCode
 * @return
 */
SinotransPageJson delSubmitOrder(String soUuid, String officeCode);
/**
 * 作废入库订单
 * @param soModel
 * @param sodModelList
 * @param soUuid
 * @return
 */
SinotransPageJson cancelOrderInOrOut(SubmitOrderModel soModel,List<SubmitOrderDetailModel> sodModelList, String soUuid);


}
