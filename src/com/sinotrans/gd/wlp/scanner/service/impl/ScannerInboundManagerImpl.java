package com.sinotrans.gd.wlp.scanner.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.gd.wlp.basicdata.model.BasLotStockModel;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.scanner.query.KgCpReceQueryCondition;
import com.sinotrans.gd.wlp.scanner.query.KgCpReceQueryItem;
import com.sinotrans.gd.wlp.scanner.query.OutboundLodSumQtyQueryCondition;
import com.sinotrans.gd.wlp.scanner.query.OutboundLodSumQtyQueryItem;
import com.sinotrans.gd.wlp.scanner.service.ScannerInboundManager;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.common.model.LocationTaskModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderLogModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderModel;
import com.sinotrans.gd.wlp.common.model.BarcodeModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderDetailModel;
import com.sinotrans.gd.wlp.common.model.RemainSinworkModel;
import com.sinotrans.gd.wlp.common.query.YclRecevingCheckBarcodeQueryCondition;
import com.sinotrans.gd.wlp.common.query.YclRecevingCheckBarcodeQueryItem;
import com.sinotrans.gd.wlp.common.service.LocPlanConfigManager;
import com.sinotrans.gd.wlp.common.service.StockWorkManager;
import com.sinotrans.gd.wlp.common.service.WmsCommonManager;


@Service
public class ScannerInboundManagerImpl extends BaseManagerImpl implements ScannerInboundManager {

	@Autowired
	private WmsCommonManager wmsCommonManager;
	
	@Autowired
	private StockWorkManager stockWorkManager;
	
	
	@Override
	public SinotransPageJson cpInboundCheckStartBarcode(String packageNo, String startBarcode, String officeCode) {
		SinotransPageJson spj = new SinotransPageJson(true);
		if(RcUtil.isEmpty(packageNo)){
			spj.setResult(false);
			spj.setError("箱号不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(startBarcode)){
			spj.setResult(false);
			spj.setError("首条码不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		if(startBarcode.length() > 12){
			
		}else{
			spj.setResult(false);
			spj.setError("首条码长度不足!");
			return spj;
		}
		String orderNo = startBarcode.substring(0, startBarcode.length() - 9);
		List<LogisticsOrderModel> loList = this.dao.createCommonQuery(LogisticsOrderModel.class)
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionStatus, CommonUtil.Active))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.orderNo, orderNo)).query();
		if(!RcUtil.isEmpty(loList) && loList.size() > 0){
			LogisticsOrderModel loModel = loList.get(0);
			List<BarcodeModel> bList = this.dao.createCommonQuery(BarcodeModel.class)
					.addCondition(Condition.eq(BarcodeModel.FieldNames.logisticsOrderNo, loModel.getLogisticsOrderNo()))
					.addCondition(Condition.eq(BarcodeModel.FieldNames.panelNo, packageNo))
					.addCondition(Condition.eq(BarcodeModel.FieldNames.status, CommonUtil.Active))
					.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.officeCode, officeCode)).query();
			if(bList.size() > 0){
				spj.setObject(orderNo + "|" +  bList.size());
			} else {
				spj.setObject(orderNo + "|0");
			}
		} else {
			spj.setResult(false);
			spj.setError("找不到销售订单号!" + orderNo);
			return spj;
		}
		return spj;
	}

	@Override
	public SinotransPageJson cpInboundConfirm(String lineNo, String batchNo, String packageNo, String startBarcode, String endBarcode,
			String qtyStr, String officeCode) {
		SinotransPageJson spj = new SinotransPageJson(true, "入库成功!");
		if(RcUtil.isEmpty(startBarcode)){
			spj.setResult(false);
			spj.setError("首条码不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(endBarcode)){
			endBarcode = startBarcode;
		}
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}

		if(startBarcode.length() > 12){
			
		}else{
			spj.setResult(false);
			spj.setError("首条码长度不足!");
			return spj;
		}
		
		if(startBarcode.length()!=endBarcode.length()){
			spj.setError("首尾条码位数不同 !");
			spj.setResult(false);
			return spj;
		}
		
		int start5=Integer.parseInt(startBarcode.substring(startBarcode.length()-5, startBarcode.length()));
		int end5=Integer.parseInt(endBarcode.substring(endBarcode.length()-5, endBarcode.length()));
		if(start5>end5){
			spj.setError("首条码流水号不能大于尾条码流水号!");
			spj.setResult(false);
			return spj;
		}

		String orderNo = startBarcode.substring(0, startBarcode.length() - 9);
		String end_orderNo = endBarcode.substring(0, endBarcode.length() - 9);
		if(!orderNo.equals(end_orderNo)){
			spj.setResult(false);
			spj.setError("首尾条码的销售单号不一致!");
			return spj;
		}
		
		LogisticsOrderModel loModel = new LogisticsOrderModel();
		List<LogisticsOrderModel> loModelList = this.dao.createCommonQuery(LogisticsOrderModel.class)
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionStatus, CommonUtil.Active))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.orderNo, orderNo)).query();
		if(!RcUtil.isEmpty(loModelList) && loModelList.size() > 0){
			loModel = loModelList.get(0);
			String controlWord = loModel.getControlWord();
			if (CommonUtil.CONTROL_WORD_F.equals(controlWord.substring(2, 3))) {
				spj.setError("作业单已完结!");
				spj.setResult(false);
				return spj;
			}
		} else {
			spj.setResult(false);
			spj.setError("找不到销售订单号!" + orderNo);
			return spj;
		}
		
		List<String> barcodeList = new ArrayList<String>();
		for (int i = start5; i < end5+1; i++) {
			barcodeList.add((startBarcode.substring(0, startBarcode.length()-5)+ String.format("%05d", i)));
		}
		
		String[] barcodeArray = new String[barcodeList.size()];
		barcodeList.toArray(barcodeArray);
		// 循环这些可以超发的LOD
		List<BarcodeModel> barcodeAllList = 
				this.dao.createCommonQuery(BarcodeModel.class)
				.addCondition(Condition.in(BarcodeModel.FieldNames.barcode, barcodeArray)).query();
		if(barcodeAllList.size()>0){
			for (BarcodeModel barcodeModel : barcodeAllList) {
				String barcode = barcodeModel.getBarcode();
				String seqNo = barcode.substring(barcode.length()-5, barcode.length());
				List<RemainSinworkModel> rsList = this.dao.createCommonQuery(RemainSinworkModel.class)
						.addCondition(Condition.eq(RemainSinworkModel.FieldNames.officeCode, officeCode))
						.addCondition(Condition.eq(RemainSinworkModel.FieldNames.barcode, startBarcode)).query();
				if(!RcUtil.isEmpty(rsList) && rsList.size() > 0){
					throw new ApplicationException("条码"+barcode+"已存在库存中!");
				}
				
				BarcodeModel bModel = new BarcodeModel();
				List<BarcodeModel> bList = this.dao.createCommonQuery(BarcodeModel.class)
						.addCondition(Condition.eq(BarcodeModel.FieldNames.officeCode, officeCode))
						.addCondition(Condition.eq(BarcodeModel.FieldNames.barcode, barcode)).query();
				if(bList.size() < 1){
					bModel.setBarcode(barcode);
					bModel.setControlWord(CommonUtil.Default_Control_Word);
					bModel.setCreatorDate(dao.getSysDate());
					bModel.setOfficeCode(officeCode);
					bModel.setStatus(CommonUtil.Active);
				} else {
					bModel = bList.get(0);
					
					if(!RcUtil.isEmpty(bModel.getPanelNo()) && bModel.getPanelNo().equals(packageNo)){
						spj.setError("条码" + seqNo + "已关联这个箱号!");
						spj.setObject(CommonUtil.Active);
						spj.setResult(false);
						return spj;
					} else if(!RcUtil.isEmpty(bModel.getPanelNo()) && !bModel.getPanelNo().equals(packageNo)){
						throw new ApplicationException("条码"+seqNo+"已关联其他箱号:" + bModel.getPanelNo());
					}
				}
				
				bModel.setPanelNo(packageNo);
				bModel.setSpec(lineNo);
				bModel.setQty(1.0);
				dao.save(bModel);
			}
		} else {
			throw new ApplicationException("首尾条码还系统没有生成!");
		}
		
		return spj;
	}

	@Override
	public SinotransPageJson cpInboundCancel(String batchNo, String startBarcode, String endBarcode, String officeCode) {
		SinotransPageJson spj = new SinotransPageJson(true, "取消成功!");
		if(RcUtil.isEmpty(startBarcode)){
			spj.setResult(false);
			spj.setError("首条码不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(endBarcode)){
			endBarcode = startBarcode;
		}
		if(RcUtil.isEmpty(batchNo)) {
			spj.setResult(false);
			spj.setError("箱号不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		
		
		if(startBarcode.length() > 12){
			
		}else{
			spj.setResult(false);
			spj.setError("首条码长度不足!");
			return spj;
		}
		
		if(startBarcode.length()!=endBarcode.length()){
			spj.setError("首尾条码位数不同 !");
			spj.setResult(false);
			return spj;
		}
		
		int start5=Integer.parseInt(startBarcode.substring(startBarcode.length()-5, startBarcode.length()));
		int end5=Integer.parseInt(endBarcode.substring(endBarcode.length()-5, endBarcode.length()));
		if(start5>end5){
			spj.setError("首条码流水号不能大于尾条码流水号!");
			spj.setResult(false);
			return spj;
		}

		String orderNo = startBarcode.substring(0, startBarcode.length() - 9);
		String end_orderNo = endBarcode.substring(0, endBarcode.length() - 9);
		if(!orderNo.equals(end_orderNo)){
			spj.setResult(false);
			spj.setError("首尾条码的销售单号不一致!");
			return spj;
		}
		
		LogisticsOrderModel loModel = new LogisticsOrderModel();
		List<LogisticsOrderModel> loModelList = this.dao.createCommonQuery(LogisticsOrderModel.class)
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionStatus, CommonUtil.Active))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.orderNo, orderNo)).query();
		if(!RcUtil.isEmpty(loModelList) && loModelList.size() > 0){
			loModel = loModelList.get(0);
			String controlWord = loModel.getControlWord();
			if (CommonUtil.CONTROL_WORD_F.equals(controlWord.substring(2, 3))) {
				spj.setError("作业单已完结!");
				spj.setResult(false);
				return spj;
			}
		} else {
			spj.setResult(false);
			spj.setError("找不到销售单号!" + orderNo);
			return spj;
		}
		
		List<String> barcodeList = new ArrayList<String>();
		for (int i = start5; i < end5+1; i++) {
			barcodeList.add((startBarcode.substring(0, startBarcode.length()-5)+ String.format("%05d", i)));
		}
		String[] barcodeArray = new String[barcodeList.size()];
		barcodeList.toArray(barcodeArray);
		// 循环这些可以超发的LOD
		List<BarcodeModel> barcodeAllList = 
				this.dao.createCommonQuery(BarcodeModel.class)
				.addCondition(Condition.in(BarcodeModel.FieldNames.barcode, barcodeArray)).query();
		if(barcodeAllList.size()>0){
			for (BarcodeModel barcodeModel : barcodeAllList) {
				barcodeModel.setLogisticsOrderDetailUuid(null);
				barcodeModel.setPanelNo("");
				barcodeModel.setBatchNo("");
				barcodeModel.setSpec("");
				barcodeModel.setQty(0.0);
			}
			dao.saveAll(barcodeAllList);
		}else{
			throw new ApplicationException("首尾条码还系统没有生成!");
		}
		
		return spj;
	}

	private String createSeqStr(int qty){
		if(qty<10) {
			return "0000" + qty;
		}else if(qty<100) {
			return "000" + qty;
		}else if(qty<1000) {
			return "00" + qty;
		}else if(qty<10000) {
			return "0" + qty;
		}
		return qty + "";
	}
	
	@Override
	public SinotransPageJson cpReceinvingCheckBarcode(String startQty, String endQty, String barcode, String officeCode) {
		SinotransPageJson spj = new SinotransPageJson(true);
		if(RcUtil.isEmpty(startQty)){
			spj.setResult(false);
			spj.setError("首条码编号不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(endQty)){
			spj.setResult(false);
			spj.setError("尾条码编号不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(barcode)){
			spj.setResult(false);
			spj.setError("条码不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		
		if(barcode.length() > 12){
			
		}else{
			spj.setResult(false);
			spj.setError("条码长度不足!");
			return spj;
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<BarcodeModel> bList = this.dao.createCommonQuery(BarcodeModel.class)
				.addCondition(Condition.eq(BarcodeModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(BarcodeModel.FieldNames.barcode, barcode)).query();
		if(!RcUtil.isEmpty(bList) && bList.size() > 0){
			BarcodeModel bModel = bList.get(0);
			if(RcUtil.isEmpty(bModel.getPanelNo())){
				spj.setResult(false);
				spj.setError("该条码没有关联箱号,请先入库!");
				return spj;
			}
		}else{
			spj.setResult(false);
			spj.setError("系统没生成此条码!");
			return spj;
		}
		
		
		int iqcBarcodeSeqNo=Integer.parseInt(barcode.substring(barcode.length()-5, barcode.length()));
		int start5 = Integer.valueOf(startQty);
		int end5 = Integer.valueOf(endQty);
		if(start5 <= iqcBarcodeSeqNo && end5 >= iqcBarcodeSeqNo){
			
		} else {
			spj.setResult(false);
			spj.setError("抽检条码不是在首尾条码范围内!");
			return spj;
		}
		
		//条码日期
		//String barcodeDate = barcode.substring(barcode.length()-9, barcode.length()-5);
		
		//销售单号
		String orderNo = barcode.substring(0, barcode.length() - 9);
		String batchNo = start5 + "-" + end5;
		List<LogisticsOrderModel> loList = this.dao.createCommonQuery(LogisticsOrderModel.class)
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionStatus, CommonUtil.Active))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.orderNo, orderNo)).query();
		if(!RcUtil.isEmpty(loList) && loList.size() > 0){
			LogisticsOrderModel loModel = loList.get(0);
			resultMap.put("orderNo", orderNo);
			
			//该范围内是否有收货记录
			String startBarcode = barcode.substring(0, barcode.length() - 5) + createSeqStr(start5);
			String endBarcode = barcode.substring(0, barcode.length() - 5) + createSeqStr(end5);
			YclRecevingCheckBarcodeQueryCondition condition = new YclRecevingCheckBarcodeQueryCondition(loModel.getLogisticsOrderNo(), null, CommonUtil.RECE, officeCode);
			condition.setStartBarcode(startBarcode);
			condition.setEndBarcode(endBarcode);
			List<YclRecevingCheckBarcodeQueryItem> receItems = dao.query(condition, YclRecevingCheckBarcodeQueryItem.class);
			if(!RcUtil.isEmpty(receItems) && receItems.size() > 0){
				spj.setMsg("该范围条码存在收货记录!");
				resultMap.put("rece", CommonUtil.Active);
				
				for (YclRecevingCheckBarcodeQueryItem receItem : receItems) {
					if(!receItem.getBatchNo().equals(batchNo)){
						spj.setResult(false);
						spj.setError("该范围条码存在不同抽检批次");
						return spj;
					}
				}
			}
			
			
			//是否已检验
			List<LogisticsOrderLogModel> logList = this.dao.createCommonQuery(LogisticsOrderLogModel.class)
					.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.logisticsOrderUuid, loModel.getLogisticsOrderUuid()))
					.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.officeCode, officeCode))
					.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.remark, barcode))
					.setOrderBy("workDate desc").query();
			if(!RcUtil.isEmpty(logList) && logList.size() > 0){
				LogisticsOrderLogModel lolModel = logList.get(0);
				if(lolModel.getTransactionType().equals("BARCODE_FAIL")){
					resultMap.put("status", "fail");
				} else {
					resultMap.put("status", "pass");
				}
			}
			
			KgCpReceQueryCondition fondition = new KgCpReceQueryCondition(orderNo, officeCode);
			List<KgCpReceQueryItem> items = dao.query(fondition, KgCpReceQueryItem.class);
			if(items.size()>0){
				resultMap.put("items", items);
			}
			
			spj.setObject(resultMap);
		}else{
			spj.setResult(false);
			spj.setError("找不到销售单号!" + orderNo);
			return spj;
		}
		return spj;
	}

	@Override
	public SinotransPageJson cpReceinvingConfirmStatus(String startQty, String endQty, String iqcBarcode, String type, String officeCode) {
		SinotransPageJson spj = new SinotransPageJson(true, "操作成功!");
		if(RcUtil.isEmpty(startQty)){
			spj.setResult(false);
			spj.setError("首条码编号不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(endQty)){
			spj.setResult(false);
			spj.setError("尾条码编号不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(iqcBarcode)){
			spj.setResult(false);
			spj.setError("抽检条码不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		
		if(iqcBarcode.length() > 12){
			
		}else{
			spj.setResult(false);
			spj.setError("抽检条码长度不足!");
			return spj;
		}
		

		int iqcBarcodeSeqNo=Integer.parseInt(iqcBarcode.substring(iqcBarcode.length()-5, iqcBarcode.length()));
		int start5 = Integer.valueOf(startQty);
		int end5 = Integer.valueOf(endQty);
		if(start5 <= iqcBarcodeSeqNo && end5 >= iqcBarcodeSeqNo){
			
		} else {
			spj.setResult(false);
			spj.setError("抽检条码不是在首尾条码范围内!");
			return spj;
		}

		BarcodeModel bModel =  new BarcodeModel();
		List<BarcodeModel> bList = this.dao.createCommonQuery(BarcodeModel.class)
				.addCondition(Condition.eq(BarcodeModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(BarcodeModel.FieldNames.barcode, iqcBarcode)).query();
		if(!RcUtil.isEmpty(bList) && bList.size() > 0){
			bModel = bList.get(0);
			if(RcUtil.isEmpty(bModel.getPanelNo())){
				spj.setResult(false);
				spj.setError("该条码没有关联箱号,请先入库!");
				return spj;
			}
		}else{
			spj.setResult(false);
			spj.setError("系统没生成此条码!");
			return spj;
		}
		
		//用首编码和尾编码自定义一个批次
		String batchNo = start5 + "-" + end5;
		
		//条码日期
		//String barcodeDate = iqcBarcode.substring(iqcBarcode.length()-9, iqcBarcode.length()-5);
			
		//销售单
		String orderNo = iqcBarcode.substring(0, iqcBarcode.length() - 9);
		
		List<LogisticsOrderModel> loList = this.dao.createCommonQuery(LogisticsOrderModel.class)
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionStatus, CommonUtil.Active))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.orderNo, orderNo)).query();
		if(!RcUtil.isEmpty(loList) && loList.size() > 0){
			LogisticsOrderModel loModel = loList.get(0);
			List<LogisticsOrderLogModel> logList = this.dao.createCommonQuery(LogisticsOrderLogModel.class)
					.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.logisticsOrderUuid, loModel.getLogisticsOrderUuid()))
					.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.officeCode, officeCode))
					.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.remark, iqcBarcode))
					.setOrderBy("workDate desc").query();
			if(!RcUtil.isEmpty(logList) && logList.size() > 0){
				LogisticsOrderLogModel lolModel = logList.get(0);
				if(!batchNo.equals(lolModel.getAux1())){
					throw new ApplicationException("该条码已抽检,但不是同一批次抽检!");
				}
			}
			
			SessionContextUserEntity scue = SessionContextUserEntity.currentUser();	
			
			LogisticsOrderLogModel lolModel = new LogisticsOrderLogModel();
			lolModel.setLogisticsOrderUuid(loModel.getLogisticsOrderUuid());
			lolModel.setRemark(iqcBarcode);
			lolModel.setAux1(batchNo);
			lolModel.setOfficeCode(officeCode);
			lolModel.setStatus(CommonUtil.Active);
			lolModel.setWorkDate(dao.getSysDate());
			lolModel.setTransactionType(type);
			lolModel.setWorkPerson(scue.getUsername());
			lolModel.setWorkDesc("QA抽检");
			dao.save(lolModel);
			
			
			KgCpReceQueryCondition condition = new KgCpReceQueryCondition(orderNo, officeCode);
			List<KgCpReceQueryItem> items = dao.query(condition, KgCpReceQueryItem.class);
			if(items.size()>0){
				spj.setObject(items);
			}
		}else{
			spj.setResult(false);
			spj.setError("找不到销售订单号!" + orderNo);
		}
		return spj;
	}

	@Override
	public SinotransPageJson cpReceinvingConfirmRece(String startQty, String endQty, String iqcBarcode, String officeCode) throws Exception {
		SinotransPageJson spj = new SinotransPageJson(true, "自检完成操作成功!");
		if(RcUtil.isEmpty(startQty)){
			spj.setResult(false);
			spj.setError("首条码编号不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(endQty)){
			spj.setResult(false);
			spj.setError("尾条码编号不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(iqcBarcode)){
			spj.setResult(false);
			spj.setError("抽检条码不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		
		if(iqcBarcode.length() > 12){
			
		}else{
			spj.setResult(false);
			spj.setError("抽检条码长度不足!");
			return spj;
		}
		

		int iqcBarcodeSeqNo=Integer.parseInt(iqcBarcode.substring(iqcBarcode.length()-5, iqcBarcode.length()));
		int start5 = Integer.valueOf(startQty);
		int end5 = Integer.valueOf(endQty);
		if(start5 <= iqcBarcodeSeqNo && end5 >= iqcBarcodeSeqNo){
			
		} else {
			spj.setResult(false);
			spj.setError("抽检条码不是在首尾条码范围内!");
			return spj;
		}

		BarcodeModel bModel =  new BarcodeModel();
		List<BarcodeModel> bList = this.dao.createCommonQuery(BarcodeModel.class)
				.addCondition(Condition.eq(BarcodeModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(BarcodeModel.FieldNames.barcode, iqcBarcode)).query();
		if(!RcUtil.isEmpty(bList) && bList.size() > 0){
			bModel = bList.get(0);
			if(RcUtil.isEmpty(bModel.getPanelNo())){
				spj.setResult(false);
				spj.setError("该条码没有关联箱号,请先入库!");
				return spj;
			}
		}else{
			spj.setResult(false);
			spj.setError("系统没生成此条码!");
			return spj;
		}
		
		//用首编码和尾编码自定义一个批次
		String batchNo = start5 + "-" + end5;
		
		
		String orderNo = iqcBarcode.substring(0, iqcBarcode.length() - 9);

		String lotCode = "QA";
		List<BasLotStockModel> lotList = this.dao.createCommonQuery(BasLotStockModel.class)
				.addCondition(Condition.likeStart(BasLotStockModel.FieldNames.controlWord, "CD"))
				.addCondition(Condition.eq(BasLotStockModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(BasLotStockModel.FieldNames.status, CommonUtil.Active)).query();
		if (lotList.size() > 0) {
			lotCode = lotList.get(0).getLotCode();
		}
		
		List<LogisticsOrderModel> loList = this.dao.createCommonQuery(LogisticsOrderModel.class)
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionStatus, CommonUtil.Active))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.orderNo, orderNo)).query();
		if(!RcUtil.isEmpty(loList) && loList.size() > 0){
			LogisticsOrderModel loModel = loList.get(0);
			
			String startBarcode = iqcBarcode.substring(0, iqcBarcode.length() - 5) + createSeqStr(start5);
			String endBarcode = iqcBarcode.substring(0, iqcBarcode.length() - 5) + createSeqStr(end5);
			//该范围内是否有收货记录
			YclRecevingCheckBarcodeQueryCondition condition = new YclRecevingCheckBarcodeQueryCondition(loModel.getLogisticsOrderNo(), null, CommonUtil.RECE, officeCode);
			condition.setStartBarcode(startBarcode);
			condition.setEndBarcode(endBarcode);
			List<YclRecevingCheckBarcodeQueryItem> receItem = dao.query(condition, YclRecevingCheckBarcodeQueryItem.class);
			if(!RcUtil.isEmpty(receItem) && receItem.size() > 0){
				throw new ApplicationException("该批次范围条码存在收货记录!");
			}
			
			//检验这个批次是否有不合格产品
			List<LogisticsOrderLogModel> logList = this.dao.createCommonQuery(LogisticsOrderLogModel.class)
					.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.logisticsOrderUuid, loModel.getLogisticsOrderUuid()))
					.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.officeCode, officeCode))
					.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.transactionType, "BARCODE_FAIL"))
					.addCondition(Condition.between(LogisticsOrderLogModel.FieldNames.remark, startBarcode, endBarcode)).query();
			if(!RcUtil.isEmpty(logList) && logList.size() > 0){
				for (LogisticsOrderLogModel logModel : logList) {
					List<LogisticsOrderLogModel> bLogList = this.dao.createCommonQuery(LogisticsOrderLogModel.class)
							.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.logisticsOrderUuid, loModel.getLogisticsOrderUuid()))
							.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.officeCode, officeCode))
							.addCondition(Condition.eq(LogisticsOrderLogModel.FieldNames.remark, logModel.getRemark()))
							.setOrderBy("workDate desc").query();
					if(!RcUtil.isEmpty(bLogList) && bLogList.size() > 0){
						LogisticsOrderLogModel lModel = bLogList.get(0);
						if(!RcUtil.isEmpty(lModel.getTransactionType()) && lModel.getTransactionType().equals("BARCODE_FAIL"))
							throw new ApplicationException("该批次范围的条码"+bLogList.get(0).getRemark()+"不合格,不能收货!");
						}
				}
			}
			int i = start5;
			int addQty = end5 - start5 + 1;
			LogisticsOrderDetailModel lodModel = new LogisticsOrderDetailModel();
			List<LogisticsOrderDetailModel> lodList = this.dao.createCommonQuery(LogisticsOrderDetailModel.class)
					.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.logisticsOrderNo, loModel.getLogisticsOrderNo()))
					.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
					.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionStatus, CommonUtil.Active))
					.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.batchNo, batchNo))
					.addCondition(Condition.sql(" SUBSTR(CONTROL_WORD, 3, 1)<>'F' ", null))
					.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.officeCode, officeCode)).query();
			if(lodList.size() > 0){
				lodModel = lodList.get(0);
				lodModel.setQty(lodModel.getQty() + addQty);
			}else{
				//找到用来生成条码的lod
				lodList = this.dao.createCommonQuery(LogisticsOrderDetailModel.class)
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.logisticsOrderNo, loModel.getLogisticsOrderNo()))
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionStatus, CommonUtil.Active))
						.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.officeCode, officeCode))
						.addCondition(Condition.sql(" SUBSTR(CONTROL_WORD, 3, 1)='F' ", null))
						.query();
				if(lodList.size() > 0){
					lodModel = lodList.get(0);
					LogisticsOrderDetailModel saveLodModel = new LogisticsOrderDetailModel();
					RcUtil.copyProperties(saveLodModel, lodModel);
					saveLodModel.setLogisticsOrderDetailUuid(null);
					saveLodModel.setLogisticsOrderNo(loModel.getLogisticsOrderNo());
					saveLodModel.setLogisticsOrderUuid(loModel.getLogisticsOrderUuid());
					saveLodModel.setSubmitOrderDetailUuid(lodModel.getSubmitOrderDetailUuid());
					saveLodModel.setTransactionType(CommonUtil.TRANSACTIONTYPE_SIN);
					saveLodModel.setTransactionStatus(CommonUtil.Active);
					saveLodModel.setOfficeCode(officeCode);
					saveLodModel.setControlWord(CommonUtil.Default_Control_Word);
					saveLodModel.setItemCode(lodModel.getItemCode());
					saveLodModel.setBatchNo(batchNo);
					saveLodModel.setQty(addQty+0.0);
					lodModel = dao.save(saveLodModel);
				}else{
					lodList = this.dao.createCommonQuery(LogisticsOrderDetailModel.class)
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.logisticsOrderNo, loModel.getLogisticsOrderNo()))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionStatus, CommonUtil.Active))
							.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.officeCode, officeCode))
							.query();
					if(lodList.size() == 1){
						//改成完结状态
						lodModel = lodList.get(0);
						lodModel.setControlWord(RcUtil.setKeyBit(3, "F", CommonUtil.Default_Control_Word));
						lodModel = dao.save(lodModel);
						
						LogisticsOrderDetailModel saveLodModel = new LogisticsOrderDetailModel();
						RcUtil.copyProperties(saveLodModel, lodModel);
						saveLodModel.setLogisticsOrderDetailUuid(null);
						saveLodModel.setLogisticsOrderNo(loModel.getLogisticsOrderNo());
						saveLodModel.setLogisticsOrderUuid(loModel.getLogisticsOrderUuid());
						saveLodModel.setSubmitOrderDetailUuid(lodModel.getSubmitOrderDetailUuid());
						saveLodModel.setTransactionType(CommonUtil.TRANSACTIONTYPE_SIN);
						saveLodModel.setTransactionStatus(CommonUtil.Active);
						saveLodModel.setOfficeCode(officeCode);
						saveLodModel.setControlWord(CommonUtil.Default_Control_Word);
						saveLodModel.setItemCode(lodModel.getItemCode());
						saveLodModel.setBatchNo(batchNo);
						saveLodModel.setQty(addQty+0.0);
						lodModel = dao.save(saveLodModel);
						
						//spj = cpReceinvingConfirmRece(startQty, endQty, iqcBarcode, officeCode);
					} else if(lodList.size() > 1){
						spj.setResult(false);
						spj.setError("只能有一条货物明细!");
						return spj;
					} else {
						spj.setResult(false);
						spj.setError("找不到生成条码的货物明细!");
						return spj;
					}
				}
			}
			
			//把这一批次范围的条码全部收货
			for (i = start5; i <= end5; i++) {
				String receBarcode = iqcBarcode.substring(0, iqcBarcode.length() - 5) + createSeqStr(i);
				bList = this.dao.createCommonQuery(BarcodeModel.class)
						.addCondition(Condition.eq(BarcodeModel.FieldNames.logisticsOrderNo, loModel.getLogisticsOrderNo()))
						.addCondition(Condition.eq(BarcodeModel.FieldNames.status, CommonUtil.Active))
						.addCondition(Condition.eq(BarcodeModel.FieldNames.officeCode, officeCode))
						.addCondition(Condition.eq(BarcodeModel.FieldNames.barcode, receBarcode))
						.query();
				if(bList.size()>0){
					/*
					if(addQty != bAllList.size()){
						throw new ApplicationException("收货条码数:" + addQty + ",系统只找到"+bAllList.size()+"条, 不能收货!");
					}
					*/
					BarcodeModel barcodeItem = bList.get(0);
					LocationTaskModel ltModel = new LocationTaskModel();
					RcUtil.copyProperties(ltModel, lodModel);
					ltModel.setLogisticsOrderDetailUuid(lodModel.getLogisticsOrderDetailUuid());
					ltModel.setInLogisticsOrderDetailUuid(lodModel.getLogisticsOrderDetailUuid());
					ltModel.setUnitCode(lodModel.getQtyUnitCode());
					ltModel.setUnitDesc(lodModel.getQtyUnitDesc());
					ltModel.setLocTaskType(CommonUtil.RECE);
					ltModel.setLocTaskDesc(CommonUtil.MSRECE);
					ltModel.setRemark("QA抽检成品收货");
					ltModel.setBarcode(barcodeItem.getBarcode());
					ltModel.setPackageNo(barcodeItem.getPanelNo());
					ltModel.setQty(1.0);
					ltModel.setBatchNo(batchNo);
					ltModel.setPackageNo(barcodeItem.getPanelNo());//关联入库时的箱号
					ltModel.setTargetLotCode(lotCode);
					ltModel.setOfficeCode(officeCode);
					ltModel.setGoodsNature("Y");
					ltModel.setControlWord(CommonUtil.Default_Control_Word);
					ltModel = wmsCommonManager.commonSaveLocationTask(ltModel);
					stockWorkManager.setStockWork(ltModel);
					
					barcodeItem.setBatchNo(batchNo);
					barcodeItem.setQty(1.0);
					barcodeItem.setLogisticsOrderDetailUuid(lodModel.getLogisticsOrderDetailUuid());
					barcodeItem.setStatus(CommonUtil.Active);
						
					
					dao.save(barcodeItem);
				}
				
				if(i == end5){
					break;
				}
			}
			
			
		} else {
			spj.setResult(false);
			spj.setError("找不到销售订单号!" + orderNo);
		}
		return spj;
	}

	@Override
	public SinotransPageJson cpReceinvingCancelRece(String startQty, String endQty, String iqcBarcode, String officeCode) throws Exception {
		SinotransPageJson spj = new SinotransPageJson(true, "取消收货操作成功!");
		if(RcUtil.isEmpty(iqcBarcode)){
			spj.setResult(false);
			spj.setError("条码不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(officeCode)) {
			spj.setResult(false);
			spj.setError("officeCode不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(startQty)){
			spj.setResult(false);
			spj.setError("首条码编号不能为空!");
			return spj;
		}
		if(RcUtil.isEmpty(endQty)){
			spj.setResult(false);
			spj.setError("尾条码编号不能为空!");
			return spj;
		}
		if(iqcBarcode.length() > 12){
			
		}else{
			spj.setResult(false);
			spj.setError("首条码长度不足!");
			return spj;
		}
	
		int start5 = Integer.valueOf(startQty);
		int end5 = Integer.valueOf(endQty);
		String orderNo = iqcBarcode.substring(0, iqcBarcode.length() - 9);
		String batchNo = start5 + "-" + end5;
		List<LogisticsOrderModel> loList = this.dao.createCommonQuery(LogisticsOrderModel.class)
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.officeCode, officeCode))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.transactionStatus, CommonUtil.Active))
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.orderNo, orderNo)).query();
		if(!RcUtil.isEmpty(loList) && loList.size() > 0){
			LogisticsOrderModel loModel = loList.get(0);
			String startBarcode = iqcBarcode.substring(0, iqcBarcode.length() - 5) + createSeqStr(start5);
			String endBarcode = iqcBarcode.substring(0, iqcBarcode.length() - 5) + createSeqStr(end5);
			YclRecevingCheckBarcodeQueryCondition condition = new YclRecevingCheckBarcodeQueryCondition(loModel.getLogisticsOrderNo(), null, CommonUtil.RECE, officeCode);
			condition.setStartBarcode(startBarcode);
			condition.setEndBarcode(endBarcode);
			List<YclRecevingCheckBarcodeQueryItem> receItems = dao.query(condition, YclRecevingCheckBarcodeQueryItem.class);
			if(!RcUtil.isEmpty(receItems) && receItems.size() > 0){
				for (YclRecevingCheckBarcodeQueryItem receLtItem : receItems) {
					
					if(!RcUtil.isEmpty(receLtItem.getBatchNo()) && !receLtItem.getBatchNo().equals(batchNo)){
						throw new ApplicationException("该收货范围条码存在不同抽检批次");
					}
					
					
					LocationTaskModel ltModel = new LocationTaskModel();
					RcUtil.copyProperties(ltModel, receLtItem);
					ltModel.setLastLocationTaskUuid(receLtItem.getLocationTaskUuid());
					ltModel.setLocTaskDesc(CommonUtil.MSCANR);
					ltModel.setLocTaskType(CommonUtil.CANR);
					ltModel.setRemark("QA抽检成品取消收货");
					ltModel.setOfficeCode(officeCode);
					ltModel = wmsCommonManager.commonSaveLocationTask(ltModel);
					stockWorkManager.setStockWork(ltModel);
				}
			} else {
				spj.setResult(false);
				spj.setError("找不到该条码范围的收货记录!");
				return spj;
			}
			//一个批次一条LOD
			List<LogisticsOrderDetailModel> lodList = this.dao.createCommonQuery(LogisticsOrderDetailModel.class)
					.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.logisticsOrderNo, loModel.getLogisticsOrderNo()))
					.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionType, CommonUtil.TRANSACTIONTYPE_SIN))
					.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.transactionStatus, CommonUtil.Active))
					.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.batchNo, batchNo))
					.addCondition(Condition.sql(" SUBSTR(CONTROL_WORD, 3, 1)<>'F' ", null))
					.addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.officeCode, officeCode)).query();
			if(lodList.size() > 0){
				for (LogisticsOrderDetailModel lodModel : lodList) {
					lodModel.setControlWord(RcUtil.setKeyBit(3, "F", CommonUtil.Default_Control_Word));
					lodModel.setTransactionStatus(CommonUtil.Cancel);
				}
				dao.saveAll(lodList);
			}
		}
		
		return spj;
	}
	
}
