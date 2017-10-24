package com.sinotrans.gd.wlp.scanner.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.gd.wlp.common.model.LocPlanConfigModel;
import com.sinotrans.gd.wlp.common.model.LocationTaskModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderModel;
import com.sinotrans.gd.wlp.common.query.CommonLodRsLtSwQueryCondition;
import com.sinotrans.gd.wlp.common.query.CommonLodRsLtSwQueryItem;
import com.sinotrans.gd.wlp.common.service.StockWorkManager;
import com.sinotrans.gd.wlp.common.service.WmsCommonManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.scanner.query.OutboundLodSumQtyQueryCondition;
import com.sinotrans.gd.wlp.scanner.query.OutboundLodSumQtyQueryItem;
import com.sinotrans.gd.wlp.scanner.query.OutboundLtQueryCondition;
import com.sinotrans.gd.wlp.scanner.query.OutboundLtQueryItem;
import com.sinotrans.gd.wlp.scanner.service.ScannerOutboundManager;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.StringUtil;

@Service
public class ScannerOutboundManagerImpl extends BaseManagerImpl implements ScannerOutboundManager {
	
	@Autowired
	private WmsCommonManager wmsCommonManager;
	
	@Autowired
	private StockWorkManager stockWorkManager;
	
	
	@Override
	public SinotransPageJson outboundCheckBarcode(String logisticsOrderNo, String barcode, String officeCode) {
		SinotransPageJson spj = new SinotransPageJson(true);
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(logisticsOrderNo)) {
			spj.setResult(false);
			spj.setError("出库单号不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(barcode)) {
			spj.setResult(false);
			spj.setError("条码不能为空!");
			return spj;
		}
		
		List<LogisticsOrderModel> loList = this.dao.createCommonQuery(LogisticsOrderModel.class)
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.logisticsOrderNo, logisticsOrderNo))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionStatus, CommonUtil.Active))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SOT))
				.query();
		if(loList.size()>0){
			LogisticsOrderModel loModel = loList.get(0);
			//是否已出库
			OutboundLtQueryCondition condition = new OutboundLtQueryCondition();
			condition.setBarcode(barcode);
			condition.setLogisticsOrderNo(logisticsOrderNo);
			condition.setOfficeCode(officeCode);
			List<OutboundLtQueryItem> items = dao.query(condition, OutboundLtQueryItem.class);
			if(items.size()>0){
				Double ltQty = 0.0;
				for (OutboundLtQueryItem item : items) {
					ltQty += item.getQty();
				}
				spj.setError("该条码已出库!");
				spj.setObject(ltQty);
				return spj;
			}else{
				//没出库,找库存
				
				StringBuffer extraSql = new StringBuffer();
				extraSql.append(" LOD.ITEM_CODE = RS.ITEM_CODE ");
				StringBuffer orderBy = new StringBuffer();
				if (!RcUtil.isEmpty(loModel.getConfigCode())) { // 有策略的话
					List<LocPlanConfigModel> lpcList = dao.createCommonQuery(LocPlanConfigModel.class)
							.addCondition(Condition.eq(LocPlanConfigModel.FieldNames.officeCode, officeCode))
							.addCondition(Condition.eq(LocPlanConfigModel.FieldNames.configCode, loModel.getConfigCode()))
							.addCondition(Condition.eq(LocPlanConfigModel.FieldNames.status, CommonUtil.Active))
							.query();
					if (!RcUtil.isEmpty(lpcList) && lpcList.size() > 0) {
						// 先查询该条码是否已经出库核销，这里不用加策略条件
//						LocPlanConfigModel lpcModel = lpcList.get(0);
//						extraSql.append(" and " + locPlanConfigManager.getStrategyConditionSql(lpcModel.getLocPlanConfigUuid()));
//						String str = locPlanConfigManager.getStrategyOrderBySql(lpcModel.getLocPlanConfigUuid());
//						if (!RcUtil.isEmpty(str)) {
//							orderBy.append(str);
//						}
					}
				} else {
					// 没有策略用in来查
					//extraSql.append(" and lod.In_Logistics_Order_Detail_Uuid = lt.in_logistics_order_detail_uuid ");
				}
				
				//查询拣货记录出库
				String pickSql = " LOD.LOGISTICS_ORDER_DETAIL_UUID=LT.LOGISTICS_ORDER_DETAIL_UUID AND LT.LOC_TASK_TYPE ='" + CommonUtil.PICK + "' ";
				CommonLodRsLtSwQueryCondition rsCondition = new CommonLodRsLtSwQueryCondition();
				rsCondition.setOfficeCode(officeCode);
				rsCondition.setLogisticsOrderNo(logisticsOrderNo);
				rsCondition.setTransactionType(CommonUtil.TRANSACTIONTYPE_SOT);
				rsCondition.setBarcode(barcode);
				List<CommonLodRsLtSwQueryItem> rsItems = dao.query(rsCondition, CommonLodRsLtSwQueryItem.class, pickSql, null, null, null);
				if(rsItems.size()>0){
					Double pickQty = 0.0;
					for (CommonLodRsLtSwQueryItem rsItem : rsItems) {
						pickQty += rsItem.getRemainQty();
					}
					spj.setObject(pickQty);
				}else{
					//用库存记录出库
					pickSql = " AND LOD.LOGISTICS_ORDER_DETAIL_UUID<>LT.LOGISTICS_ORDER_DETAIL_UUID AND LT.LOC_TASK_TYPE<>'" + CommonUtil.PICK + "' ";
					rsItems = dao.query(rsCondition, CommonLodRsLtSwQueryItem.class, extraSql + pickSql, null, null, null);
					if(rsItems.size()>0){
						Double pickQty = 0.0;
						for (CommonLodRsLtSwQueryItem rsItem : rsItems) {
							pickQty += rsItem.getRemainQty();
						}
						spj.setObject(pickQty);
					} else {
						spj.setError("该条码没有库存或与策略不匹配!");
						spj.setResult(false);
					}
				}
			}
		}else{
			spj.setResult(false);
			spj.setError("出库单号无效!");
		}
		return spj;
	}
	
	
	@Override
	public SinotransPageJson confirmOutboundVeri(String logisticsOrderNo, String barcode, String qty, String officeCode) throws Exception {
		SinotransPageJson spj = new SinotransPageJson(true, "出库核销成功!");
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(barcode)) {
			spj.setResult(false);
			spj.setError("条码不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(logisticsOrderNo)) {
			spj.setResult(false);
			spj.setError("出库单号不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(qty)) {
			spj.setResult(false);
			spj.setError("数量不能为空!");
			return spj;
		}
		Double outQty = Double.valueOf(qty);//要出库的数量
		
		List<LogisticsOrderModel> loList = this.dao.createCommonQuery(LogisticsOrderModel.class)
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.logisticsOrderNo, logisticsOrderNo))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionStatus, CommonUtil.Active))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SOT))
				.query();
		if(loList.size()>0){
			LogisticsOrderModel loModel = loList.get(0);
			StringBuffer extraSql = new StringBuffer();
			extraSql.append(" LOD.ITEM_CODE = RS.ITEM_CODE ");
			StringBuffer orderBy = new StringBuffer();
			if (!RcUtil.isEmpty(loModel.getConfigCode())) { // 有策略的话
				List<LocPlanConfigModel> lpcList = dao.createCommonQuery(LocPlanConfigModel.class)
						.addCondition(Condition.eq(LocPlanConfigModel.FieldNames.officeCode, officeCode))
						.addCondition(Condition.eq(LocPlanConfigModel.FieldNames.configCode, loModel.getConfigCode()))
						.addCondition(Condition.eq(LocPlanConfigModel.FieldNames.status, CommonUtil.Active))
						.query();
				if (!RcUtil.isEmpty(lpcList) && lpcList.size() > 0) {
					// 先查询该条码是否已经出库核销，这里不用加策略条件
//					LocPlanConfigModel lpcModel = lpcList.get(0);
//					extraSql.append(" and " + locPlanConfigManager.getStrategyConditionSql(lpcModel.getLocPlanConfigUuid()));
//					String str = locPlanConfigManager.getStrategyOrderBySql(lpcModel.getLocPlanConfigUuid());
//					if (!RcUtil.isEmpty(str)) {
//						orderBy.append(str);
//					}
				}
			} else {
				// 没有策略用in来查
				extraSql.append(" AND LOD.IN_LOGISTICS_ORDER_DETAIL_UUID = LT.IN_LOGISTICS_ORDER_DETAIL_UUID ");
			}
			
			Map<String, Double> hasCompare = new HashMap<String, Double>(); // 记录做过计算的lod
			
			//查询拣货记录出库
			String extSql = " LOD.LOGISTICS_ORDER_DETAIL_UUID=LT.LOGISTICS_ORDER_DETAIL_UUID AND LT.LOC_TASK_TYPE ='" + CommonUtil.PICK + "' ";
			CommonLodRsLtSwQueryCondition rsCondition = new CommonLodRsLtSwQueryCondition();
			rsCondition.setOfficeCode(officeCode);
			rsCondition.setLogisticsOrderNo(logisticsOrderNo);
			rsCondition.setTransactionType(CommonUtil.TRANSACTIONTYPE_SOT);
			rsCondition.setLocTaskType(CommonUtil.PICK);
			rsCondition.setBarcode(barcode);
			List<CommonLodRsLtSwQueryItem> rsItems = dao.query(rsCondition, CommonLodRsLtSwQueryItem.class, extSql, null, null, null);
			if(rsItems.size()>0){
				Double canOutQty = 0.0;
				for (CommonLodRsLtSwQueryItem rsItem : rsItems) {
					if (RcUtil.isEmpty(hasCompare.get(rsItem.getOutLodUuid()))) {
						Double canQty = Double.valueOf(StringUtil.doubleTo0(rsItem.getLodQty()) - StringUtil.doubleTo0(rsItem.getDeliveredQty()));
						canOutQty = Double.valueOf(StringUtil.doubleTo0(canOutQty) + StringUtil.doubleTo0(canQty));
						canOutQty = StringUtil.ObjectToDouble(canOutQty, 6);
						hasCompare.put(rsItem.getOutLodUuid(), canQty);
					}
				}
				if (canOutQty >= outQty) {
					// 开始处理一个个的lod并做保存
					hasCompare = new HashMap<String, Double>();
					for (int i = 0; i < rsItems.size(); i++) {
						// 如果这个lod还没处理过就要处理，过滤处理过的
						if (RcUtil.isEmpty(hasCompare.get(rsItems.get(i).getOutLodUuid())) && outQty > 0) {
							CommonLodRsLtSwQueryItem outItem = rsItems.get(i);
							hasCompare.put(outItem.getOutLodUuid(), outItem.getRemainQty());
							
							Double canQty = Double.valueOf(StringUtil.doubleTo0(outItem.getLodQty()) - StringUtil.doubleTo0(outItem.getDeliveredQty()));
							
							
							Double ltQty = 0.0; //写LT拣货数
							
							if (canQty <= outQty) {
								ltQty = canQty;
								outQty = outQty - canQty;
							} else {
								ltQty = outQty;
								outQty = 0.0;
							}
							
							LocationTaskModel ltModel = new LocationTaskModel();
							RcUtil.copyProperties(ltModel, outItem);
							ltModel.setLastLocationTaskUuid(outItem.getLocationTaskUuid());
							ltModel.setLogisticsOrderDetailUuid(outItem.getOutLodUuid());
							ltModel.setInStockWorkUuid(outItem.getInStockWorkUuid());
							ltModel.setInLogisticsOrderDetailUuid(outItem.getRsInLodUuid());
							ltModel.setQty(ltQty);
							ltModel.setLocTaskType(CommonUtil.OUTV);
							ltModel.setLocTaskDesc("出库核销");
							ltModel.setRemark("bar出库核销");
							ltModel.setBarcode(outItem.getBarcode());
							ltModel.setSourceLotCode(outItem.getLotCode());
							ltModel.setTargetLotCode(outItem.getLotCode());
							ltModel.setOfficeCode(officeCode);
							ltModel.setControlWord(CommonUtil.Default_Control_Word);
							ltModel = wmsCommonManager.commonSaveLocationTask(ltModel);
							stockWorkManager.setStockWork(ltModel);
						}
					}
				} else {
					throw new ApplicationException("出库数大于可出库数:" + canOutQty);
				}
			}else{
				//用库存记录出库
				extSql = " AND LOD.LOGISTICS_ORDER_DETAIL_UUID<>LT.LOGISTICS_ORDER_DETAIL_UUID AND LT.LOC_TASK_TYPE<>'" + CommonUtil.PICK + "' ";
				rsItems = dao.query(rsCondition, CommonLodRsLtSwQueryItem.class, extraSql + extSql, null, null, null);
				if(rsItems.size()>0){
					Double canOutQty = 0.0;
					for (CommonLodRsLtSwQueryItem rsItem : rsItems) {
						if (RcUtil.isEmpty(hasCompare.get(rsItem.getOutLodUuid()))) {
							Double canQty = Double.valueOf(StringUtil.doubleTo0(rsItem.getLodQty()) - StringUtil.doubleTo0(rsItem.getDeliveredQty()));
							canOutQty = Double.valueOf(StringUtil.doubleTo0(canOutQty) + StringUtil.doubleTo0(canQty));
							canOutQty = StringUtil.ObjectToDouble(canOutQty, 6);
							hasCompare.put(rsItem.getOutLodUuid(), canQty);
						}
					}
					if (canOutQty >= outQty) {
						// 开始处理一个个的lod并做保存
						hasCompare = new HashMap<String, Double>();
						for (int i = 0; i < rsItems.size(); i++) {
							// 如果这个lod还没处理过就要处理，过滤处理过的
							if (RcUtil.isEmpty(hasCompare.get(rsItems.get(i).getOutLodUuid())) && outQty > 0) {
								CommonLodRsLtSwQueryItem outItem = rsItems.get(i);
								hasCompare.put(outItem.getOutLodUuid(), outItem.getRemainQty());
								
								Double canQty = Double.valueOf(StringUtil.doubleTo0(outItem.getLodQty()) - StringUtil.doubleTo0(outItem.getDeliveredQty()));
								
								
								Double ltQty = 0.0; //写LT拣货数
								
								if (canQty <= outQty) {
									ltQty = canQty;
									outQty = outQty - canQty;
								} else {
									ltQty = outQty;
									outQty = 0.0;
								}
								
								LocationTaskModel ltModel = new LocationTaskModel();
								RcUtil.copyProperties(ltModel, outItem);
								ltModel.setLastLocationTaskUuid(outItem.getLocationTaskUuid());
								ltModel.setLogisticsOrderDetailUuid(outItem.getOutLodUuid());
								ltModel.setInStockWorkUuid(outItem.getInStockWorkUuid());
								ltModel.setInLogisticsOrderDetailUuid(outItem.getRsInLodUuid());
								ltModel.setQty(ltQty);
								ltModel.setLocTaskType(CommonUtil.OUTV);
								ltModel.setLocTaskDesc("出库核销");
								ltModel.setRemark("bar出库核销");
								ltModel.setBarcode(outItem.getBarcode());
								ltModel.setSourceLotCode(outItem.getLotCode());
								ltModel.setTargetLotCode(outItem.getLotCode());
								ltModel.setOfficeCode(officeCode);
								ltModel.setControlWord(CommonUtil.Default_Control_Word);
								ltModel = wmsCommonManager.commonSaveLocationTask(ltModel);
								stockWorkManager.setStockWork(ltModel);
							}
						}
					} else {
						throw new ApplicationException("出库数大于可出库数:" + canOutQty);
					}
				}
			}
		}else{
			spj.setResult(false);
			spj.setError("出库单号无效!");
		}
		return spj;
	}

	
	
	@Override
	public SinotransPageJson cancelOutboundVeri(String logisticsOrderNo, String barcode, String qty, String officeCode) throws Exception {
		SinotransPageJson spj = new SinotransPageJson(true, "取消出库核销成功!");
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(logisticsOrderNo)) {
			spj.setResult(false);
			spj.setError("出库单号不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(barcode)) {
			spj.setResult(false);
			spj.setError("条码不能为空!");
			return spj;
		}
		
		List<LogisticsOrderModel> loList = this.dao.createCommonQuery(LogisticsOrderModel.class)
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.logisticsOrderNo, logisticsOrderNo))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionStatus, CommonUtil.Active))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SOT))
				.query();
		if(loList.size()>0){
			LogisticsOrderModel loModel = loList.get(0);
			//是否已出库
			OutboundLtQueryCondition condition = new OutboundLtQueryCondition();
			condition.setBarcode(barcode);
			condition.setLogisticsOrderNo(logisticsOrderNo);
			condition.setOfficeCode(officeCode);
			List<OutboundLtQueryItem> items = dao.query(condition, OutboundLtQueryItem.class);
			if(items.size()>0){
				for (OutboundLtQueryItem outItem : items) {
					LocationTaskModel ltModel = new LocationTaskModel();
					RcUtil.copyProperties(ltModel, outItem);
					ltModel.setLastLocationTaskUuid(outItem.getLocationTaskUuid());
					ltModel.setLocTaskType(CommonUtil.CANV);
					ltModel.setLocTaskDesc("取消出库核销");
					ltModel.setRemark("bar出库核销");
					ltModel.setOfficeCode(officeCode);
					ltModel = wmsCommonManager.commonSaveLocationTask(ltModel);
					stockWorkManager.setStockWork(ltModel);
				}
			}else{
				spj.setResult(false);
				spj.setError("找不到出库记录!");
			}
		}
		return spj;
	}


	@Override
	public SinotransPageJson outboundCheckLogisticsOrderNo(String logisticsOrderNo, String officeCode){
		SinotransPageJson spj = new SinotransPageJson(true);
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(logisticsOrderNo)) {
			spj.setResult(false);
			spj.setError("出库单号不能为空!");
			return spj;
		}
		List<LogisticsOrderModel> loList = this.dao.createCommonQuery(LogisticsOrderModel.class)
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.logisticsOrderNo, logisticsOrderNo))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionStatus, CommonUtil.Active))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SOT))
				.query();
		if(loList.size()>0){
			OutboundLodSumQtyQueryCondition condition = new OutboundLodSumQtyQueryCondition(logisticsOrderNo, officeCode);
			List<OutboundLodSumQtyQueryItem> items = dao.query(condition, OutboundLodSumQtyQueryItem.class);
			if(items.size()>0){
				spj.setObject(items);
			}
		}else{
			spj.setResult(false);
			spj.setError("出库单号无效!");
		}
		return spj;
	}
}
