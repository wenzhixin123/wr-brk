package com.sinotrans.gd.wlp.outbound.service.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.model.OperationLog;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.util.EntityUtils;
import com.sinotrans.framework.core.util.MessageUtils;
import com.sinotrans.gd.wlp.basicdata.model.BasCustomerModel;
import com.sinotrans.gd.wlp.common.entity.SubmitOrderToLogisticsOrderEntity;
import com.sinotrans.gd.wlp.common.model.LocPlanConfigModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderDetailModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderModel;
import com.sinotrans.gd.wlp.common.model.SubmitOrderDetailModel;
import com.sinotrans.gd.wlp.common.model.SubmitOrderLogModel;
import com.sinotrans.gd.wlp.common.model.SubmitOrderModel;
import com.sinotrans.gd.wlp.common.query.CommonGetLodSeqNoQueryCondition;
import com.sinotrans.gd.wlp.common.query.CommonGetLodSeqNoQueryItem;
import com.sinotrans.gd.wlp.common.query.HxCheckSodQtyEqualsLodQtyQueryCondition;
import com.sinotrans.gd.wlp.common.query.HxCheckSodQtyEqualsLodQtyQueryItem;
import com.sinotrans.gd.wlp.common.service.LocPlanConfigManager;
import com.sinotrans.gd.wlp.common.service.WmsCommonManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.outbound.entity.CommonErrorEntity;
import com.sinotrans.gd.wlp.outbound.query.CommonGetSodSeqNoQueryCondition;
import com.sinotrans.gd.wlp.outbound.query.CommonGetSodSeqNoQueryItem;
import com.sinotrans.gd.wlp.outbound.service.SubmitOrderOutManager;
import com.sinotrans.gd.wlp.statistics.query.CommonGetItemMasterQueryCondition;
import com.sinotrans.gd.wlp.statistics.query.CommonGetItemMasterQueryItem;
import com.sinotrans.gd.wlp.statistics.query.CommonGetRemainHoldQueryCondition;
import com.sinotrans.gd.wlp.statistics.query.CommonGetRemainHoldQueryItem;
import com.sinotrans.gd.wlp.statistics.query.CommonGetRemainSinworkQueryItem;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.StringUtil;
@Service
public class SubmitOrderOutManagerImpl  extends BaseManagerImpl implements SubmitOrderOutManager{

	
	@Autowired
	private WmsCommonManager wmsCommonManager;
	
	@Autowired
	private LocPlanConfigManager locPlanConfigManager;
	
	/******************* 出库订单 ******************/
	// 保存出库订单
	@Override
	public SinotransPageJson saveSubmitOrderSot(SubmitOrderModel soModel, List<SubmitOrderDetailModel> sodModelList, String officeCode) throws Exception {
		SubmitOrderModel som = new SubmitOrderModel();
		SinotransPageJson spj = new SinotransPageJson();
		if (RcUtil.isEmpty(soModel.getSubmitOrderUuid())) {
			String submitOrderNo = wmsCommonManager.generateNumberByRule(CommonUtil.SEQUENCE_Application, officeCode);
			soModel.setSubmitOrderNo(submitOrderNo);
			soModel.setOfficeCode(officeCode);
			soModel.setTransactionType(CommonUtil.TRANSACTIONTYPE_SOT);
			som = this.dao.save(soModel);
			String dm=som.getDeliveryType();	
			for (SubmitOrderDetailModel sod : sodModelList) {
				sod.setControlWord(CommonUtil.Default_Control_Word);
				sod.setTransactionStatus(CommonUtil.Pending);
				sod.setOfficeCode(officeCode);
				sod.setTransactionType(som.getTransactionType());
				sod.setSubmitOrderNo(som.getSubmitOrderNo());
				sod.setSubmitOrderUuid(som.getSubmitOrderUuid());
				this.dao.save(sod);
			}
		} else {
			som = this.dao.save(soModel);
			String dm=som.getDeliveryType();
			for (SubmitOrderDetailModel sod : sodModelList) {
				   sod.setControlWord(CommonUtil.Default_Control_Word);
				   sod.setTransactionStatus(soModel.getTransactionStatus());
				if (RcUtil.isEmpty(sod.getSubmitOrderDetailUuid())) {
					sod.setOfficeCode(officeCode);
					
					sod.setTransactionType(som.getTransactionType());
					sod.setSubmitOrderNo(som.getSubmitOrderNo());
					sod.setSubmitOrderUuid(som.getSubmitOrderUuid());
				}
				this.dao.save(sod);
			}
		}
		spj.setObject(som);
		spj.setResult(true);
		return spj;
	}
	
	
	//出库订单--- 提交
		@Override
		public SinotransPageJson auditOrderSot(SubmitOrderModel soModel, List<SubmitOrderDetailModel> sodModelList, String soUuid, String officeCode) throws Exception {
			SinotransPageJson spj = new SinotransPageJson(true, "提交成功!");
			SubmitOrderModel submitOrderModel = dao.get(SubmitOrderModel.class, soModel.getSubmitOrderUuid());
			if (!submitOrderModel.getTransactionStatus().equals(soModel.getTransactionStatus())) {
				spj.setError("订单状态已经改变,操作失败!");
				spj.setResult(false);
				return spj;
			}
			if(CommonUtil.Cancel.equals(submitOrderModel.getTransactionStatus())){
				spj.setError("订单状态为作废状态,不允许提交生成作业单   操作失败!");
				spj.setResult(false);
				return spj;
			}
			
			//海信 出库单办理 库存不足发送邮件 
			//根据验证提交方法判断
			if(!"手工办单".equals(soModel.getWorkDemand()) && "原材料出库".equals(soModel.getDeliveryType()) && !CommonUtil.Pending.equals(submitOrderModel.getTransactionStatus())){
				spj.setError("订单已经不是草稿状态,不允许提交生成作业单   操作失败!");
				spj.setResult(false);
				return spj;
			}
			if("手工办单".equals(soModel.getWorkDemand()) || !"原材料出库".equals(soModel.getDeliveryType())){
			//调用提交验证方法 有库存不足提示出去 并发送邮件 不做提交操作
				spj=checkStockIsExist(soUuid);
				if(!spj.isResult()){
					//调用发邮件方法发送邮件 发送完毕后跳出方法不做提交 并提示错误
					/**List<CommonErrorEntity> errorMsgList =(List<CommonErrorEntity>) spj.getObject();				
					yclSubmitOrderManager.yclSendEmailWarningSot(soUuid,errorMsgList,SessionContextUserEntity.currentUser().getOfficeCode());
					*/
					spj.setError("因库存不足,生成作业单失败!");
					spj.setResult(false);
					return spj;
				}
			}
			String loUuid="";
			List<String> loUuids = new ArrayList<String>();
			if(CommonUtil.TRANSACTIONTYPE_SOT.equals(soModel.getTransactionType()) && ("手工办单".equals(soModel.getWorkDemand()) || "成品出库".equals(soModel.getDeliveryType()))){
				if("手工办单".equals(soModel.getWorkDemand()) || CommonUtil.Pending.equals(submitOrderModel.getTransactionStatus())){
					loUuid =generateOutboundOrder(soUuid, CommonUtil.Active, CommonUtil.SEQUENCE_Exit,true,null);
					loUuids.add(loUuid);
				}
				if(RcUtil.isEmpty(loUuids)||loUuids.size()==0){
					throw new ApplicationException("生成出库作业单失败,请检查物料是否有库存!");
				}
			}
			
			SubmitOrderModel som = new SubmitOrderModel();
			if (!RcUtil.isEmpty(soUuid)) {
				soModel.setTransactionStatus(CommonUtil.Active);
				som = this.dao.save(soModel);
				List<SubmitOrderDetailModel> listSod = getSubmitOrderDetailBySo(som.getSubmitOrderUuid());
				if (!RcUtil.isEmpty(listSod) && listSod.size() > 0) {
					for (SubmitOrderDetailModel sod : listSod) {
						sod.setTransactionStatus(CommonUtil.Active);
						this.dao.save(sod);
					}
				}
			}
			String firstOderNo="";
			String lastOrderNo="";
			if(!RcUtil.isEmpty(loUuids) && loUuids.size()>0){
				List<LogisticsOrderModel> listLom = this.dao.createCommonQuery(LogisticsOrderModel.class)
					    .addCondition(Condition.in(LogisticsOrderModel.FieldNames.logisticsOrderUuid, loUuids.toArray()))
						.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode))
						.setOrderBy(LogisticsOrderModel.FieldNames.logisticsOrderNo)
						.query();
				if(!RcUtil.isEmpty(listLom)&&listLom.size()>0){
					firstOderNo=listLom.get(0).getLogisticsOrderNo();
					if(listLom.size()>1){
						lastOrderNo=listLom.get(listLom.size()-1).getLogisticsOrderNo();
						spj.setMsg("共生成"+listLom.size()+"份出库单,出库单号是:"+firstOderNo+"至"+lastOrderNo);
					}else{
						spj.setMsg("共生成"+listLom.size()+"份出库单,出库单号是:"+firstOderNo);
					}
				}
			}
			spj.setObject(som);
			spj.setResult(true);
			return spj;
		}
		
		/**
		 * 验证库存
		 * @param submitOrderUuid
		 * @return
		 * @throws Exception
		 */
		@Override
		public SinotransPageJson checkStockIsExist(String submitOrderUuid)throws Exception {
			if(RcUtil.isEmpty(submitOrderUuid)){
				throw new Exception("The 'submitOrderUuid' argument must not be null.");
			}
			SinotransPageJson spj=new SinotransPageJson(true);
			SubmitOrderModel submitOrderModel = this.dao.get(SubmitOrderModel.class,submitOrderUuid);
			if (!RcUtil.isEmpty(submitOrderModel)) {
				//获取sod信息
				List<SubmitOrderDetailModel> soOrderDetailList = this.dao.createCommonQuery(SubmitOrderDetailModel.class)
				    .addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.submitOrderUuid,submitOrderModel.getSubmitOrderUuid()))
				    .query();
				if (!RcUtil.isEmpty(soOrderDetailList)&& soOrderDetailList.size() > 0) {
					List<CommonErrorEntity> errorMsgList=new ArrayList<CommonErrorEntity>();
					String officeCode = submitOrderModel.getOfficeCode();
					// 获取出库策略
					String outConfigCode = submitOrderModel.getConfigCode();
					String appendSql = " 0=0 "; 
					//查找是否需要控制货位属性
//					SinotransPageJson sinotransPageJson = jumpIsOCPbyOOOqueryItem(CpcCommonUtil.OUTBOUND_LIMIT, CommonUtil.WMS_SYSTEM_CONTROL, officeCode, "Y");
//					if(sinotransPageJson.isResult()==false){
//						appendSql+=" AND (RH.GOODS_NATURE<>'"+CommonUtil.WMS_GOODS_NATURE_DJ+"' OR RH.GOODS_NATURE IS NULL) ";
//					}
					//获取策略条件
					appendSql +=" AND "+ locPlanConfigManager
							.getStrategyConditionSqlByConfigCode(outConfigCode,officeCode);
					String orderByStr = locPlanConfigManager
							.getStrategyOrderBySqlByConfigCode(outConfigCode,officeCode);
					CommonGetRemainHoldQueryCondition condition = new CommonGetRemainHoldQueryCondition(officeCode);
					List<CommonGetRemainHoldQueryItem> rhItemList=new ArrayList<CommonGetRemainHoldQueryItem>();
					List<String> sodUuid=new ArrayList<String>();//sodUuid集合
					for (SubmitOrderDetailModel sodModel : soOrderDetailList) {
						sodUuid.add(sodModel.getSubmitOrderDetailUuid());
					}
					//分批查询关联库存
					List<List<String>> brokenUpList = RcUtil.brokenUpList(sodUuid, 1000);
					for (List<String> list : brokenUpList) {
						String[] uuids = new String[list.size()];
						list.toArray(uuids);
						condition.setSubmitOrderDetailUuid(uuids);
						List<CommonGetRemainHoldQueryItem> tempList = this.dao
						.query(condition,CommonGetRemainHoldQueryItem.class,
								appendSql, null, orderByStr, null);
						rhItemList.addAll(tempList);
					}
					if(!RcUtil.isEmpty(rhItemList) && rhItemList.size() > 0){
						Map<String,Double> remainQtyMap=new HashMap<String,Double>();
						Map<String,List<CommonGetRemainHoldQueryItem>> stockMap=new HashMap<String, List<CommonGetRemainHoldQueryItem>>();
						for (CommonGetRemainHoldQueryItem item : rhItemList) {
							remainQtyMap.put(item.getRemainHoldUuid(), item.getRemainQtyHoldQtySum());
							List<CommonGetRemainHoldQueryItem> rhTempList = stockMap.get(item.getSubmitOrderDetailUuid());
							if(RcUtil.isEmpty(rhTempList)){
								rhTempList=new ArrayList<CommonGetRemainHoldQueryItem>();
							}
							rhTempList.add(item);
							stockMap.put(item.getSubmitOrderDetailUuid(), rhTempList);
						}
						
						for (SubmitOrderDetailModel soOrderDetail : soOrderDetailList) {						
							List<CommonGetRemainHoldQueryItem> rhTempList = stockMap.get(soOrderDetail.getSubmitOrderDetailUuid());
							if(RcUtil.isEmpty(rhTempList)){
								// 出现库存不足
								CommonErrorEntity commonError=new CommonErrorEntity(inventoryShortageExceptionMsg(soOrderDetail, soOrderDetail.getQty()));	
								commonError.setSoOrderDetail(soOrderDetail);
								commonError.setReamrk(Double.toString(soOrderDetail.getQty()));								
								errorMsgList.add(commonError);
								spj.setResult(false);
								continue;//继续下一个物料库存验证2013-06-17
							}
							int count=rhTempList.size();
							double qty=soOrderDetail.getQty();
							double sumQty=0.0;
							for (int i = 0; i < count; i++) {
								CommonGetRemainHoldQueryItem item = rhTempList.get(i);
								double remainQty=remainQtyMap.get(item.getRemainHoldUuid());
								if(RcUtil.isEmpty(remainQty)){
									remainQty=0.0;
								}
								if(remainQty>0.0){
									sumQty+=remainQty;
									if(sumQty>=qty){
										double newQty=remainQty-(sumQty-qty);
										remainQtyMap.put(item.getRemainHoldUuid(), remainQty-newQty);
										break;
									}else{
										double newQty=soOrderDetail.getQty()-remainQty;
										soOrderDetail.setQty(newQty);
										remainQtyMap.put(item.getRemainHoldUuid(), 0.0);
									}
								}
							}
							if(qty>sumQty){
								// 出现库存不足
								CommonErrorEntity commonError=new CommonErrorEntity(inventoryShortageExceptionMsg(soOrderDetail, qty));	
								commonError.setSoOrderDetail(soOrderDetail);
								commonError.setReamrk(Double.toString(qty));							
								errorMsgList.add(commonError);
								spj.setResult(false);
							}
						}
					}else{
						// 出现库存不足
						//CommonErrorEntity commonError=new CommonErrorEntity("没有查询到库存信息");  so_cannot_query_stock_info
						List<SubmitOrderDetailModel> sodModel=this.dao.createCommonQuery(SubmitOrderDetailModel.class).
						addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.submitOrderUuid, submitOrderModel.getSubmitOrderUuid())).query();
						for (SubmitOrderDetailModel submitOrderDetailModel : sodModel) {
							CommonErrorEntity commonError=new CommonErrorEntity(MessageUtils.getMessage("没有查询到库存信息", "so_cannot_query_stock_info"));	
							commonError.setSoOrderDetail(submitOrderDetailModel);
							commonError.setReamrk(Double.toString(submitOrderDetailModel.getQty()));
							errorMsgList.add(commonError);
						}					
						spj.setResult(false);
					}
					if(spj.isResult()==false){
						spj.setObject(errorMsgList);
						spj.setError("库存验证失败!");
					}else{
						spj.setMsg("库存验证成功!");  
					}
				}else{
					spj.setMsg("没有订单明细");
				}
			} else {
				spj.setResult(false);
			}
			return spj;
		}
		
		/**
		 * 库存不足异常详细信息
		 * @param soOrderDetail
		 * @param qty
		 */
		private String inventoryShortageExceptionMsg(
				SubmitOrderDetailModel soOrderDetail, Double qty) {
			double remainQty=qty-soOrderDetail.getQty();
			if(remainQty<0){
				remainQty=0;
			}
			String errorMsg="";
			//2015年3月18日16:17:09 wj+ 深圳物流单独开发-必须使用Y单号提示。无需提示货名或者物料信息。
			String text="";
			if(!RcUtil.isEmpty(soOrderDetail.getInLogisticsOrderDetailUuid())){
				LogisticsOrderDetailModel lodModel=this.dao.get(LogisticsOrderDetailModel.class, soOrderDetail.getInLogisticsOrderDetailUuid());
				String billNo=RcUtil.isEmpty(lodModel.getBillNo()) ? "":lodModel.getBillNo();
				text="入库单"+":\'"+lodModel.getLogisticsOrderNo()+"\';"+"billNo"+":\'"+billNo+"\';";
			}
			if(RcUtil.isEmpty(soOrderDetail.getItemCode())){
				errorMsg="入库作业单:'so_in_logisticsOrderNo';货名为:\'"+ soOrderDetail.getGoodsDesc() + "\'已经没有足够的库存数量,其中计划数为:"+ qty + "实际库存数为:" + remainQty + "缺货数为:"+ soOrderDetail.getQty() + "!";
			}else{
				errorMsg="物料编码为:\'"+ soOrderDetail.getItemCode() + "\'已经没有足够的库存数量,其中计划数为:"+ qty + "实际库存数为:" + remainQty + "缺货数为:"+ soOrderDetail.getQty() + "!";
			}
			return errorMsg;
		}
		
		
		/**
		 * 出库单生成方法
		 */
		@Override
		public String generateOutboundOrder(
				SubmitOrderToLogisticsOrderEntity soToLoEntity) throws Exception {
			if (RcUtil.isEmpty(soToLoEntity)) {
				throw new Exception("The 'soToLoEntity' argument must not be null.");
			}
			String logisticsOrderUuid = null;
			// 获取so
			SubmitOrderModel soModel = soToLoEntity.getSoModel();
			// 获取sod
			List<SubmitOrderDetailModel> sodModelList = soToLoEntity
					.getSodModelList();
			// 是否保留生成的so和sod
			boolean isRetention = soToLoEntity.isRetention();
			// 作业单号生成规则
			String orderNOGenerateRule = soToLoEntity.getOrderNOGenerateRule();
			// so对象和sod集合不能为空
			if (!RcUtil.isEmpty(soModel) && !RcUtil.isEmpty(sodModelList)
					&& sodModelList.size() > 0) {
				soModel.setTransactionStatus(CommonUtil.Active);
				String transactionType = soModel.getTransactionType();
				String submitOrderNo = "";
				// 生成so单号
				if (CommonUtil.TRANSACTIONTYPE_SSP.equals(transactionType)
						|| CommonUtil.TRANSACTIONTYPE_SOT.equals(transactionType)) {
					submitOrderNo = wmsCommonManager.generateNumberByRule(
							CommonUtil.SEQUENCE_Application, soModel
									.getOfficeCode());
				} else {
					submitOrderNo = wmsCommonManager.generateNumberByRule(
							CommonUtil.SEQUENCE_Application, soModel
									.getOfficeCode());
				}
				soModel.setSubmitOrderNo(submitOrderNo);
				SubmitOrderModel newSoModel = dao.save(soModel);
				// 获取sod集合
				List<SubmitOrderDetailModel> newSodModelList = new ArrayList<SubmitOrderDetailModel>();
				if (!RcUtil.isEmpty(newSoModel)) {
					CommonGetSodSeqNoQueryCondition condition = new CommonGetSodSeqNoQueryCondition(
							newSoModel.getSubmitOrderUuid());
					List<CommonGetSodSeqNoQueryItem> seqNoItem = this.dao.query(
							condition, CommonGetSodSeqNoQueryItem.class);
					Long seqNo = seqNoItem.get(0).getSeqNo();
					for (SubmitOrderDetailModel sodModel : sodModelList) {
						if (RcUtil.isEmpty(sodModel.getSubmitOrderDetailUuid())) {
							seqNo++;
							sodModel.setSubmitOrderUuid(newSoModel
									.getSubmitOrderUuid());
							sodModel
									.setSubmitOrderNo(newSoModel.getSubmitOrderNo());
							sodModel.setOfficeCode(newSoModel.getOfficeCode());
							sodModel.setTransactionStatus(newSoModel
									.getTransactionStatus());
							sodModel.setTransactionType(newSoModel
									.getTransactionType());
							sodModel.setSeqNo(seqNo);
							sodModel = this.dao.save(sodModel);
						}
						newSodModelList.add(sodModel);
					}
				}
				if (!RcUtil.isEmpty(newSodModelList) && newSodModelList.size() > 0) {
					logisticsOrderUuid = generateOutboundOrder(newSoModel
							.getSubmitOrderUuid(), CommonUtil.Active,
							orderNOGenerateRule, isRetention,null);
					if (!RcUtil.isEmpty(logisticsOrderUuid)) {
						// 如果不需要保留生成的So单,就删除所有生成的So单记录
						if (!isRetention) {
							List<SubmitOrderDetailModel> soOrderDetailList = this.dao
									.createCommonQuery(SubmitOrderDetailModel.class)
									.addCondition(
											Condition
													.eq(
															SubmitOrderDetailModel.FieldNames.submitOrderUuid,
															newSoModel
																	.getSubmitOrderUuid()))
									.query();
							dao.removeAll(soOrderDetailList);
							List<SubmitOrderLogModel> soOrderLogList = this.dao
									.createCommonQuery(SubmitOrderLogModel.class)
									.addCondition(
											Condition
													.eq(
															SubmitOrderLogModel.FieldNames.submitOrderUuid,
															newSoModel
																	.getSubmitOrderUuid()))
									.query();
							dao.removeAll(soOrderLogList);
							dao.removeByPk(SubmitOrderModel.class, newSoModel
									.getSubmitOrderUuid());
						}
					} else {
						// throw new Exception("生成LO记录失败!");
					}
				}
			}
			return logisticsOrderUuid;
		}
		
		
		
		/**
		 * 原材料出库单生成方法
		 */
		public String generateOutboundOrder(String submitOrderUuid,
				String transactionStatus, String orderNOGenerateRule,
				boolean isRetention,String dpParameterName) throws Exception {
			if (RcUtil.isEmpty(submitOrderUuid)) {
				throw new Exception(
						"The 'submitOrderUuid' argument must not be null.");
			}
			if (RcUtil.isEmpty(orderNOGenerateRule)) {
				throw new Exception(
						"The 'orderNOGenerateRule' argument must not be null.");
			}
			
			String logisticsOrderUuid = null;
			SubmitOrderModel submitOrderModel = dao.get(SubmitOrderModel.class,
					submitOrderUuid);
			List<HxCheckSodQtyEqualsLodQtyQueryItem> soOrderDetailList = null;
			if (!RcUtil.isEmpty(submitOrderModel)) {
				if(!RcUtil.isEmpty(dpParameterName)){
					HxCheckSodQtyEqualsLodQtyQueryCondition getSodCondtion=new HxCheckSodQtyEqualsLodQtyQueryCondition(new String[]{submitOrderModel.getSubmitOrderUuid()}, submitOrderModel.getOfficeCode(),dpParameterName);
					soOrderDetailList = dao.query(getSodCondtion, HxCheckSodQtyEqualsLodQtyQueryItem.class);
				}else{
					HxCheckSodQtyEqualsLodQtyQueryCondition getSodCondtion=new HxCheckSodQtyEqualsLodQtyQueryCondition(new String[]{submitOrderModel.getSubmitOrderUuid()}, submitOrderModel.getOfficeCode());
					soOrderDetailList = dao.query(getSodCondtion, HxCheckSodQtyEqualsLodQtyQueryItem.class);
				}
				if (!RcUtil.isEmpty(soOrderDetailList)
						&& soOrderDetailList.size() > 0) {
					String officeCode = submitOrderModel.getOfficeCode();
					// 获取出库策略
					String outConfigCode = submitOrderModel.getConfigCode();
					if (RcUtil.isEmpty(outConfigCode)) {
						// TODO 这里如果没有出库策略要抛异常，现在暂时不抛
						try {
							// 默认出库策略
							LocPlanConfigModel outvConfigModel = wmsCommonManager
									.getDefaultPlanConfig(CommonUtil.OUTV,
											officeCode);
							outConfigCode = outvConfigModel.getConfigCode();
						} catch (ApplicationException ex) {
							log.error(ex, ex);
							throw new ApplicationException("获取默认出库策略失败,请设置默认出库策略!");
						}
					}
					// 创建lo对象
					LogisticsOrderModel loModel = new LogisticsOrderModel();
					// 如果是生成加工单需要设置controlWord
					RcUtil.copyProperties(loModel, submitOrderModel);
					frameFieldToNull(loModel);
					// submitOrderModel.getCargoConsigneeCode()
					// submitOrderModel.getCargoConsigneeDesc()
					if (!isRetention) {// 如果不需要保留生成的So单,就把lo的SubmitOrderUuid置空
						loModel.setSubmitOrderUuid(null);
					}
					String transactionType = submitOrderModel.getTransactionType();
					// 获取默认委托单位
					BasCustomerModel customer = wmsCommonManager.getDefaultCustomer();
					String controlWord = RcUtil.setKeyBit(14,
							CommonUtil.CONTROL_WORD_GOODS_TYPE_D,
							submitOrderModel.getControlWord());
					if(RcUtil.isEmpty(submitOrderModel.getAgentConsigneeCode())){
						loModel.setAgentConsigneeCode(customer.getCustomerCode());// 默认委托单位
						loModel.setAgentConsigneeDesc(customer.getCustomerName());
					}
					// sot 获取出库订单 没有在默认 buy直接获取默认cargoControlCode
					if (CommonUtil.TRANSACTIONTYPE_SOT.equals(transactionType)) {
						if (!RcUtil.isEmpty(submitOrderModel
								.getCargoConsigneeCode())) {
							loModel.setCargoControlCode(submitOrderModel
									.getCargoConsigneeCode());
							loModel.setCargoControlDesc(submitOrderModel
									.getCargoConsigneeDesc());
						} else {
//							BasCustomerModel emscustomer = getDefaultEmsCustomer();
//							if(RcUtil.isEmpty(emscustomer.getCustomerCode())){
//								throw new ApplicationException("请设置系统默认EMS工厂");
//							}
//							loModel.setCargoControlCode(emscustomer
//									.getCustomerCode());
//							loModel.setCargoControlDesc(emscustomer
//									.getCustomerName());
						}
					} else {
						loModel.setCargoControlCode(customer.getCustomerCode());// ycl交付对象
						loModel.setCargoControlDesc(customer.getCustomerName());// ycl交付对象
					}
					loModel.setControlWord(controlWord);
					// loModel.setCargoConsigneeCode(null);
					// loModel.setCargoConsigneeDesc(null);
					loModel.setOrderDate(submitOrderModel.getSubmitDate());// 委托日期

					// 根据生成规则生成loOrderNo
					String logisticsOrderNo = wmsCommonManager
							.generateNumberByRule(orderNOGenerateRule, officeCode);
					if (RcUtil.isEmpty(logisticsOrderNo)) {
						throw new ApplicationException("根据单号生成规则\'"
								+ orderNOGenerateRule + "\'生成LogisticsOrderNo失败!");
					}
					// 根据SO订单类型设置LO单的类型
					if (CommonUtil.TRANSACTIONTYPE_SOT.equals(transactionType)) {
						loModel.setTransactionType(CommonUtil.TRANSACTIONTYPE_SOT);
					} else if (CommonUtil.TRANSACTIONTYPE_BUY
							.equals(transactionType)) {
						loModel.setTransactionType(CommonUtil.TRANSACTIONTYPE_BUY);
					}
					// 根据出库策略获取拣货策略
					String configCode = locPlanConfigManager.getNextConfigCode(
							outConfigCode, officeCode);
					if (!RcUtil.isEmpty(configCode)) {
						// 设置拣货策略
						loModel.setConfigCode(configCode);
					} else {
						if (CommonUtil.Pending.equals(transactionStatus)) {
							loModel.setConfigCode(null);// 生成的出库单位草稿状态拣货策略可以为空
						}
//						else {
//							throw new ApplicationException("出库策略为:" + outConfigCode
//									+ "没有设置对应的关联策略!");
//						}
					}
					if (!RcUtil.isEmpty(transactionStatus)) {
						loModel.setTransactionStatus(transactionStatus);
					} else {
						loModel.setTransactionStatus(CommonUtil.Pending);
					}
					loModel.setLogisticsOrderNo(logisticsOrderNo);
					if(!RcUtil.isEmpty(dpParameterName)){
						loModel.setProjectCode(dpParameterName);
					}
					// 获取策略条件
					// String appendSql =
					// " lo.agent_consignee_code = so.agent_consignee_code and lod.item_code = sod.item_code";
					String appendSql = locPlanConfigManager
							.getStrategyConditionSqlByConfigCode(outConfigCode,
									officeCode);
					String orderByStr = locPlanConfigManager
							.getStrategyOrderBySqlByConfigCode(outConfigCode,
									officeCode);
					CommonGetRemainHoldQueryCondition condition = new CommonGetRemainHoldQueryCondition(
							officeCode);
					List<CommonGetRemainHoldQueryItem> rhItemList = new ArrayList<CommonGetRemainHoldQueryItem>();
					List<String> sodUuid = new ArrayList<String>();// sodUuid集合
					for (HxCheckSodQtyEqualsLodQtyQueryItem sodModel : soOrderDetailList) {
						sodUuid.add(sodModel.getSubmitOrderDetailUuid());
					}
					// 分批查询关联库存
					List<List<String>> brokenUpList = RcUtil.brokenUpList(sodUuid,
							1000);
					for (List<String> list : brokenUpList) {
						if(RcUtil.isEmpty(list)||list.size()==0){
							continue;
						}
						String[] uuids = new String[list.size()];
						list.toArray(uuids);
						condition.setSubmitOrderDetailUuid(uuids);
						List<CommonGetRemainHoldQueryItem> tempList = this.dao
								.query(condition,
										CommonGetRemainHoldQueryItem.class,
										appendSql, null, orderByStr, null);
						rhItemList.addAll(tempList);
					}
					if (rhItemList.size() == 0) {
						// 出现库存不足抛异常前
						log
								.info("出库生成出库单------检测到订单号:"
										+ submitOrderModel.getSubmitOrderNo()
										+ "没有任何库存信息!");
						//throw new ApplicationException("出库生成出库单------检测到订单号:"
								//+ submitOrderModel.getSubmitOrderNo() + "没有任何库存信息!");
					} else {
						// 保存lo信息
						loModel = this.dao.save(loModel);
					}
					Map<String, Double> remainQtyMap = new HashMap<String, Double>();
					Map<String, List<CommonGetRemainHoldQueryItem>> stockMap = new HashMap<String, List<CommonGetRemainHoldQueryItem>>();
					for (CommonGetRemainHoldQueryItem item : rhItemList) {
						remainQtyMap.put(item.getRemainHoldUuid(), item
								.getRemainQtyHoldQtySum());
						List<CommonGetRemainHoldQueryItem> rhTempList = stockMap
								.get(item.getSubmitOrderDetailUuid());
						if (RcUtil.isEmpty(rhTempList)) {
							rhTempList = new ArrayList<CommonGetRemainHoldQueryItem>();
						}
						rhTempList.add(item);
						stockMap.put(item.getSubmitOrderDetailUuid(), rhTempList);
					}
					for (HxCheckSodQtyEqualsLodQtyQueryItem soOrderDetail : soOrderDetailList) {
						List<CommonGetRemainHoldQueryItem> rhTempList = stockMap
								.get(soOrderDetail.getSubmitOrderDetailUuid());
						if (RcUtil.isEmpty(rhTempList)) {
							if (!isRetention) {// 不保存订单的为原材料导入生成出库单，没库存需要抛异常
								throw new ApplicationException(
										inventoryShortageExceptionMsg(
												soOrderDetail, soOrderDetail
														.getNewQty()));
							} else {
								// 出现库存不足
								saveSolog(submitOrderModel.getSubmitOrderUuid(),
										CommonUtil.TRANSACTIONTYPE_SOT,
										inventoryShortageExceptionMsg(
												soOrderDetail, soOrderDetail
														.getNewQty()),
										"SYSTEM", "自动生成作业单",
										submitOrderModel.getOfficeCode());
								continue;// 继续下一个物料
							}
						}
						List<LogisticsOrderDetailModel> lodModelList = new ArrayList<LogisticsOrderDetailModel>();
						int count = rhTempList.size();
						double qty = soOrderDetail.getNewQty();
						double sumQty = 0.0;
						CommonGetLodSeqNoQueryCondition con = new CommonGetLodSeqNoQueryCondition(
								loModel.getLogisticsOrderUuid());
						List<CommonGetLodSeqNoQueryItem> seqNoItem = this.dao
								.query(con, CommonGetLodSeqNoQueryItem.class);
						Long seqNo = seqNoItem.get(0).getSeqNo();
						for (int i = 0; i < count; i++) {
							seqNo++;
							CommonGetRemainHoldQueryItem item = rhTempList.get(i);
							LogisticsOrderDetailModel lodModel = new LogisticsOrderDetailModel();
							RcUtil.copyProperties(lodModel, item);// copy item
							frameFieldToNull(lodModel);
							// 信息到 lod
							lodModel.setLogisticsOrderUuid(loModel
									.getLogisticsOrderUuid());// loUuid
							lodModel.setLogisticsOrderNo(loModel
									.getLogisticsOrderNo());// loOrderNo
							lodModel.setTransactionType(loModel
									.getTransactionType());// loTranType
							lodModel.setTransactionStatus(loModel
									.getTransactionStatus());// loTranStatus
							lodModel.setOfficeCode(loModel.getOfficeCode());// loOfficeCode
							lodModel.setSeqNo(seqNo);// 设置seqNO
							lodModel.setSubmitOrderDetailUuid(soOrderDetail
									.getSubmitOrderDetailUuid());// sodUuid
							lodModel.setControlWord(soOrderDetail.getControlWord());// 控制字
							lodModel.setRemark(soOrderDetail.getRemark());// remark
							if(!RcUtil.isEmpty(soOrderDetail.getAux1())){
								lodModel.setAux1(soOrderDetail.getAux1());//
							}
							if(!RcUtil.isEmpty(soOrderDetail.getAux2())){
								lodModel.setAux2(soOrderDetail.getAux2());//
							}
							if(!RcUtil.isEmpty(soOrderDetail.getAux3())){
								lodModel.setAux3(soOrderDetail.getAux3());//
							}
							if(!RcUtil.isEmpty(soOrderDetail.getAux4())){
								lodModel.setAux4(soOrderDetail.getAux4());
							}
							if(!RcUtil.isEmpty(soOrderDetail.getAux5())){
								lodModel.setAux5(soOrderDetail.getAux5());
							}
							
							if (!RcUtil.isEmpty(soOrderDetail.getMarksNumber())) {// 唛头
								lodModel.setMarksNumber(soOrderDetail
										.getMarksNumber());
							}
							if (!RcUtil.isEmpty(soOrderDetail.getExtItemCode())) {// 版本
								lodModel.setExtItemCode(soOrderDetail
										.getExtItemCode());
							}
							if (!RcUtil.isEmpty(soOrderDetail.getGoodsDesc())) {// 物料描述
								lodModel.setGoodsDesc(soOrderDetail.getGoodsDesc());
							}
							if (!RcUtil.isEmpty(soOrderDetail.getItemSeqno())) {
								lodModel.setItemSeqno(soOrderDetail.getItemSeqno());// (hub)lineId
							}
							double remainQty = remainQtyMap.get(item
									.getRemainHoldUuid());
							if (RcUtil.isEmpty(remainQty)) {
								remainQty = 0.0;
							}
							if (remainQty > 0.0) {
								sumQty += remainQty;
								if (sumQty >= qty) {
									BigDecimal b1 = new BigDecimal(Double.toString(remainQty));//必须用字符串
									BigDecimal b2 = new BigDecimal(Double.toString((sumQty - qty)));
									BigDecimal b = b1.subtract(b2);//相减
									double newQty = b.doubleValue();//remainQty - (sumQty - qty);
									lodModel.setQty(newQty);
									setLodModelProperty(item, null, lodModel,
											loModel.getAgentConsigneeCode());
									lodModelList.add(lodModel);
									remainQtyMap.put(item.getRemainHoldUuid(),
											remainQty - newQty);
									break;
								} else {
									lodModel.setQty(remainQty);
									setLodModelProperty(item, null, lodModel,
											loModel.getAgentConsigneeCode());
									lodModelList.add(lodModel);
									BigDecimal b1 = new BigDecimal(Double.toString(soOrderDetail.getNewQty()));//必须用字符串
									BigDecimal b2 = new BigDecimal(Double.toString(remainQty));
									BigDecimal b = b1.subtract(b2);//相减
									double newQty = b.doubleValue(); //soOrderDetail.getNewQty()- remainQty;
									soOrderDetail.setQty(newQty);
									remainQtyMap.put(item.getRemainHoldUuid(), 0.0);
								}
							}
							// 如果取完rh集合数量，计划数还大于0就说明库存不足
							if (i == (count - 1) && soOrderDetail.getNewQty() > 0.0) {
								// 出现库存不足抛异常前，在新事务中写入SOL日志记录
								// saveSolOrThrowsException(submitOrderModel,soOrderDetail,qty);
								if (!isRetention) {// 不保存订单的为原材料导入生成出库单，没库存需要抛异常
									throw new ApplicationException(
											inventoryShortageExceptionMsg(
													soOrderDetail, soOrderDetail
															.getNewQty()));
								} else {
									saveSolog(
											submitOrderModel.getSubmitOrderUuid(),
											CommonUtil.TRANSACTIONTYPE_SOT,
											inventoryShortageExceptionMsg(
													soOrderDetail, qty),
											"SYSTEM", "自动生成作业单",
											submitOrderModel.getOfficeCode());
								}
							}
						}
						if (qty > sumQty) {
							// 出现库存不足抛异常前，在新事务中写入SOL日志记录
							// saveSolOrThrowsException(submitOrderModel,soOrderDetail,qty);
							if (!isRetention) {// 不保存订单的为原材料导入生成出库单，没库存需要抛异常
								throw new ApplicationException(
										inventoryShortageExceptionMsg(
												soOrderDetail, soOrderDetail
														.getNewQty()));
							} else {
								saveSolog(submitOrderModel.getSubmitOrderUuid(),
										CommonUtil.TRANSACTIONTYPE_SOT,
										inventoryShortageExceptionMsg(
												soOrderDetail, qty),
										"SYSTEM", "自动生成作业单",
										submitOrderModel.getOfficeCode());
							}
						}
						StringBuffer logBuffer=new StringBuffer();
						for (LogisticsOrderDetailModel lod : lodModelList) {
							logBuffer.append("保存的lod明细对应信息，物料编码：" + lod.getItemCode()
									+ "数量：" + lod.getQty() + ",in_lod_uuid:"
									+ lod.getInLogisticsOrderDetailUuid()
									+ ",rh_uuid:" + lod.getRemainHoldUuid()+"\n");
						}
						log.info(logBuffer.toString());
						for (LogisticsOrderDetailModel lodd : lodModelList) {
							log.info("保存的lod明细对应信息，物料编码：" + lodd.getItemCode()
									+ "数量：" + lodd.getQty() + ",in_lod_uuid:"
									+ lodd.getInLogisticsOrderDetailUuid()
									+ ",rh_uuid:" + lodd.getRemainHoldUuid()+"\n");
							dao.save(lodd);
						}
						// 保存lod信息
//						this.dao.saveAll(lodModelList);
					}
					logisticsOrderUuid = loModel.getLogisticsOrderUuid();// 给返回值赋值
				}
			}
			return logisticsOrderUuid;
		}
		
		public List<SubmitOrderDetailModel> getSubmitOrderDetailBySo(String soUuid) {
			List<SubmitOrderDetailModel> listSod = this.dao.createCommonQuery(SubmitOrderDetailModel.class)
					.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.submitOrderUuid, soUuid)).query();
			return listSod;
		}
		
		/**
		 * 框架控制的字段值置空
		 */
		public void frameFieldToNull(BaseModel model) {
			Class<? extends BaseModel> entityClass = EntityUtils
					.getEntityClass(model.getClass());
			String recVer = EntityUtils.getRecVerFieldName(entityClass);
			String methodName = "set" + recVer.substring(0, 1).toUpperCase()
					+ recVer.substring(1);
			Method[] methods = entityClass.getMethods();
			Method beanMethod = null;
			for (Method method : methods) {
				if (methodName.equals(method.getName())) {
					beanMethod = method;
					break;
				}
			}
			try {
				beanMethod.invoke(model, Long.valueOf(0));
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			if (model instanceof OperationLog) {
				OperationLog ol = (OperationLog) model;
				ol.setCreator(null);
				ol.setCreateTime(null);
				ol.setModifier(null);
				ol.setModifyTime(null);
			}
		}
		
		/**
		 * 库存不足异常详细信息
		 * 
		 * @param soOrderDetail
		 * @param qty
		 */
		private String inventoryShortageExceptionMsg(
				HxCheckSodQtyEqualsLodQtyQueryItem soOrderDetail, Double qty) {
			double remainQty = qty - soOrderDetail.getQty();//计划数-缺货数=可用库存数
			if (remainQty < 0) {
				remainQty = 0;
			}
			String errorMsg = "";
			if (RcUtil.isEmpty(soOrderDetail.getItemCode())) {
				errorMsg = "货名为:\'" + soOrderDetail.getGoodsDesc()
						+ "\'已经没有足够的库存数量,其中计划数为:" + qty + "实际库存数为:" + remainQty
						+ "缺货数为:" + soOrderDetail.getQty() + "!";
			} else {
				errorMsg = "物料编码为:\'" + soOrderDetail.getItemCode()
						+ "\'已经没有足够的库存数量,其中计划数为:" + qty + "实际库存数为:" + remainQty
						+ "缺货数为:" + soOrderDetail.getQty() + "!";
			}
			return errorMsg;
		}
		
		
		/**
		 * 写入错误信息记录submitOrderLog
		 */
		@Override
		public void saveSolog(String submitOrderUuid, String transactionType,
				String workDesc, String workPerson, String remark, String officeCode) {
			SubmitOrderLogModel sogModel = new SubmitOrderLogModel();
			sogModel.setSubmitOrderUuid(submitOrderUuid);
			sogModel.setTransactionType(transactionType);
			sogModel.setWorkDate(dao.getSysDate());
			sogModel.setWorkDesc(workDesc);
			sogModel.setWorkPerson(workPerson);
			sogModel.setRemark(remark);
			sogModel.setOfficeCode(officeCode);
			sogModel.setControlWord(CommonUtil.Default_Control_Word);
			sogModel.setStatus(CommonUtil.Active);
			sogModel.setCreator("SYSTEM");
			sogModel.setCreateTime(dao.getSysDate());
			dao.save(sogModel);
		}
		
		private void setLodModelProperty(CommonGetRemainHoldQueryItem rhItem,
				CommonGetRemainSinworkQueryItem rsItem,
				LogisticsOrderDetailModel lodModel, String agentConsigneeCode) {
			double qty = lodModel.getQty();
			CommonGetItemMasterQueryCondition condition = new CommonGetItemMasterQueryCondition(
					agentConsigneeCode, lodModel.getItemCode());
			List<CommonGetItemMasterQueryItem> itemMasterList = this.dao.query(
					condition, CommonGetItemMasterQueryItem.class);
			CommonGetItemMasterQueryItem itemMaster = null;
			if (!RcUtil.isEmpty(itemMasterList) && itemMasterList.size() > 0) {
				itemMaster = itemMasterList.get(0);
			}
			double grossWeight = 0;// 毛重
			double netWeight = 0;// 净重
			double volume = 0;// 体积
			if (!RcUtil.isEmpty(itemMaster) && itemMaster.getUnitQty() > 0
					&& itemMaster.getVolume() > 0) {
				grossWeight = StringUtil.ObjectToDouble((qty / itemMaster
						.getUnitQty())
						* itemMaster.getGrossWeight(), 6);
				netWeight = StringUtil.ObjectToDouble((qty / itemMaster
						.getUnitQty())
						* itemMaster.getNetWeight(), 6);
				volume = StringUtil.ObjectToDouble((qty / itemMaster.getUnitQty())
						* itemMaster.getVolume(), 6);
			} else {
				double secondQty = 1;// 入库数
				double instockGrossWeight = 0;// 毛重
				double instockNetWeight = 0;// 净重
				double instockVolume = 0;// 体积
				if (!RcUtil.isEmpty(rhItem)) {
					secondQty = rhItem.getInstockQty() <= 0.0 ? 1 : rhItem
							.getInstockQty();// 入库数
					instockGrossWeight = rhItem.getInstockGrossWeight() <= 0 ? 0
							: rhItem.getInstockGrossWeight();// 毛重
					instockNetWeight = rhItem.getInstockNetWeight() <= 0.0 ? 0
							: rhItem.getInstockNetWeight();// 净重
					instockVolume = rhItem.getInstockVolume() <= 0.0 ? 0 : rhItem
							.getInstockVolume();// 体积
				} else {
					secondQty = rsItem.getInstockQty() <= 0.0 ? 1 : rsItem
							.getInstockQty();// 入库数
					instockGrossWeight = rsItem.getInstockGrossWeight() <= 0 ? 0
							: rsItem.getInstockGrossWeight();// 毛重
					instockNetWeight = rsItem.getInstockNetWeight() <= 0.0 ? 0
							: rsItem.getInstockNetWeight();// 净重
					instockVolume = rsItem.getInstockVolume() <= 0.0 ? 0 : rsItem
							.getInstockVolume();// 体积
				}

				grossWeight = StringUtil.ObjectToDouble(
						(instockGrossWeight / secondQty) * qty, 6);
				netWeight = StringUtil.ObjectToDouble(
						(instockNetWeight / secondQty) * qty, 6);
				volume = StringUtil.ObjectToDouble((instockVolume / secondQty)
						* qty, 6);
			}
			lodModel.setGrossWeight(grossWeight);
			lodModel.setNetWeight(netWeight);
			lodModel.setVolume(volume);

		}
		
}
