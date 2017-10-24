package com.sinotrans.gd.wlp.inbound.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.model.OperationLog;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.util.EntityUtils;
import com.sinotrans.gd.wlp.basicdata.model.ItemNatureModel;
import com.sinotrans.gd.wlp.common.model.BarcodeModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderDetailModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderModel;
import com.sinotrans.gd.wlp.common.model.SubmitOrderDetailModel;
import com.sinotrans.gd.wlp.common.model.SubmitOrderLogModel;
import com.sinotrans.gd.wlp.common.model.SubmitOrderModel;
import com.sinotrans.gd.wlp.common.service.SQLQueryManager;
import com.sinotrans.gd.wlp.common.service.WmsCommonManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.inbound.service.SubmitOrderManager;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class SubmitOrderManagerImpl extends BaseManagerImpl implements SubmitOrderManager {

	@Autowired
	private WmsCommonManager wmsCommonManager;
	
	@Autowired
	private SQLQueryManager sqlQueryManager;
	
	@Override
	public SinotransPageJson delSubmitOrderDetail(String submitOrderUuid, String submitOrderDetailUuids) {
		SinotransPageJson spj = new SinotransPageJson(true);
		String[] uuids = getStrArray(submitOrderDetailUuids);
		SubmitOrderModel soModel = getSubmitOrderByUuid(submitOrderUuid);
		if (RcUtil.isEmpty(soModel)) {
			spj.setError("订单不存在或者已经被删除!");
			spj.setResult(false);
			return spj;
		}
		List<SubmitOrderDetailModel> sodModelList = getSubmitOrderDetailBySoUuid(submitOrderUuid);
		if (uuids.length >= sodModelList.size()) {
			spj.setError("订单至少需要保留一条货物明细,如果要删除所有的货物明细请直接删除整个订单!");
			spj.setResult(false);
			return spj;
		}
		SubmitOrderDetailModel sodm = null;
		for (String uuid : uuids) {
			SubmitOrderDetailModel sod = this.dao.get(SubmitOrderDetailModel.class, uuid);
			if (RcUtil.isEmpty(sod)) {
				throw new ApplicationException("源货物信息不存在或者已经被删除!");
			}
			List<LogisticsOrderDetailModel> lodList=this.dao.createCommonQuery(LogisticsOrderDetailModel.class).
			addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.submitOrderDetailUuid, sod.getSubmitOrderDetailUuid())).
			addCondition(Condition.eq(LogisticsOrderDetailModel.FieldNames.officeCode, sod.getOfficeCode())).query();
			if( !RcUtil.isEmpty(lodList) &&lodList.size()>0){
				throw new ApplicationException("此明细已经生成作业明细,不能删除!");
			}else{
				if (RcUtil.isEmpty(sodm)) {
					sodm = sod;
				}
				this.dao.remove(sod);
			}
		}
		spj.setMsg("删除成功!");
		return spj;
	}
	
	/******************* 入库订单 ******************/
	// 保存入库订单
	@Override
	public SinotransPageJson saveSubmitOrder(SubmitOrderModel soModel, List<SubmitOrderDetailModel> sodModelDeailModelList, String officeCode) throws Exception {
		SinotransPageJson spj = new SinotransPageJson();
		if (RcUtil.isEmpty(soModel.getSubmitOrderNo())) {
			String submitOrderNo = wmsCommonManager.generateNumberByRule(CommonUtil.SEQUENCE_Sourcing, officeCode);
			soModel.setSubmitOrderNo(submitOrderNo);
			soModel.setOfficeCode(officeCode);
			if (sodModelDeailModelList.size() > 0) {
				SubmitOrderDetailModel sodModel = sodModelDeailModelList.get(0);
				String poNo = sodModel.getBillNo();
				if (!RcUtil.isEmpty(poNo)) {
//					setCargoControl(soModel, poNo,spj);
					if(!spj.isResult()){
						return spj;
					}
				}
			} else {
				spj.setError("订单明细为空,保存失败!");
				spj.setResult(false);
				return spj;
			}
			soModel = this.dao.save(soModel);
			for (SubmitOrderDetailModel sod : sodModelDeailModelList) {
				sod.setTransactionStatus(CommonUtil.Pending);
				sod.setTransactionType(soModel.getTransactionType());
				sod.setOfficeCode(officeCode);
				sod.setSubmitOrderNo(soModel.getSubmitOrderNo());
				sod.setSubmitOrderUuid(soModel.getSubmitOrderUuid());
				if(!RcUtil.isEmpty(sod.getAux5())){
					sod.setAux5(sod.getAux5());
				}else{
					sod.setAux5(soModel.getCargoConsigneeCode());
				}			
				sod.setControlWord(CommonUtil.Default_Control_Word);
				this.dao.save(sod);
			}
		} else {
			soModel = this.dao.save(soModel);
			for (SubmitOrderDetailModel sod : sodModelDeailModelList) {
				if (RcUtil.isEmpty(sod.getSubmitOrderDetailUuid())) {
					sod.setSubmitOrderNo(soModel.getSubmitOrderNo());
					sod.setTransactionStatus(CommonUtil.Pending);
					sod.setTransactionType(soModel.getTransactionType());
					sod.setOfficeCode(officeCode);
					sod.setSubmitOrderUuid(soModel.getSubmitOrderUuid());
					if(!RcUtil.isEmpty(sod.getAux5())){
						sod.setAux5(sod.getAux5());
					}else{
						sod.setAux5(soModel.getCargoConsigneeCode());
					}	
					sod.setControlWord(CommonUtil.Default_Control_Word);
				}
				this.dao.save(sod);
			}
		}
		spj.setObject(soModel);
		spj.setResult(true);
		return spj;
	}
	
	// 提交入库订单
		@Override
		public SinotransPageJson auditOrder(SubmitOrderModel soModel, List<SubmitOrderDetailModel> sodModelList, String soUuid, String officeCode) throws Exception {
			SinotransPageJson spj = new SinotransPageJson(true, "提交成功");
			SubmitOrderModel submitOrderModel = dao.get(SubmitOrderModel.class, soUuid);
			if (!submitOrderModel.getTransactionStatus().equals(soModel.getTransactionStatus())) {
				spj.setError("订单状态已经改变,操作失败!");
				spj.setResult(false);
				return spj;
			}
			checkAuitInbountOrder(submitOrderModel);
			if (!RcUtil.isEmpty(soUuid)) {
				if(RcUtil.isEmpty(submitOrderModel.getOrderNo())){
					// ASN单号为空需要wms为其生成
					// asn=K+yymdd(月：A代表10月，B代表11月，C代表12月)+3位(大写字母1位+数字2位)
//					String asn = generateInboundAsn();
//					submitOrderModel.setOrderNo(asn);//回写订单ASN号
					//YclCommonUtil.CONTROL_WORD_A这里的第四位A代表ASN为系统生成
					submitOrderModel.setControlWord(RcUtil.setKeyBit(4, CommonUtil.CONTROL_WORD_A, submitOrderModel.getControlWord()));
				}
				submitOrderModel.setTransactionStatus(CommonUtil.Active);
				List<SubmitOrderDetailModel> sodModels = dao.createCommonQuery(SubmitOrderDetailModel.class)
					.addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.submitOrderUuid, soUuid))
					.query();
				for (SubmitOrderDetailModel sodModel : sodModels) {
					if (RcUtil.isEmpty(sodModel.getPackageNo())) {// 09条码为空需要wms为其生成
						// 09+物料编码+1+asn号
						//String barcode = generateInboundBarcode(sodModel.getItemCode(), submitOrderModel.getOrderNo());
						//sodModel.setPackageNo(barcode);
					}
					sodModel.setTransactionStatus(submitOrderModel.getTransactionStatus());
				}
				if(RcUtil.isEmpty(soModel.getDeliveryType())){
					spj.setError("作业项目不能为空!");
					spj.setResult(false);
					return spj;
				}
				if("成品入库".equals(soModel.getDeliveryType())){
					spj = generateInboudOrder(submitOrderModel,sodModels, CommonUtil.SEQUENCE_Inbound, CommonUtil.Active);
				}
				if(spj.isResult()){
					submitOrderModel=dao.save(submitOrderModel);
					dao.saveAll(sodModels);
				}
			}
			spj.setObject(submitOrderModel);
			//spj.setMsg("审核成功,生成的入库单号:"+sinotransPageJson.getMsg());
			//spj.setResult(true);
			return spj;
		}
		
		
		// 取消入库订单
		@Override
		public SinotransPageJson cancelAuditOrder(List<SubmitOrderModel> soModels, List<SubmitOrderDetailModel> sodModelList) {
			SinotransPageJson spj = new SinotransPageJson(true);
			if(!RcUtil.isEmpty(soModels)&&soModels.size()>0){
				SubmitOrderModel returnSoModel=soModels.get(0);
				List<SubmitOrderModel> newSoModels=new ArrayList<SubmitOrderModel>();
				List<String> msgList=checkSoGeneterLo(soModels, newSoModels);
				String errorMsg=null;
				if (msgList.size() > 0) {
					StringBuffer tempMsg = new StringBuffer();
					tempMsg.append("单号：");
					for (int i=0;i<msgList.size();i++) {
						tempMsg.append(msgList.get(i)+",");
						if(i%2==0){
							tempMsg.append("<br/>");
						}
					}
					tempMsg.append("已经生成下一步单据,取消提交失败!");
					errorMsg=tempMsg.toString();
				}
				int size = newSoModels.size();
				if(size>0){
					if(size==1){
						SubmitOrderModel soModel=newSoModels.get(0);
						soModel.setTransactionStatus(CommonUtil.Pending);
						returnSoModel = this.dao.save(soModel);
						String sql = "update submit_order_detail sod set sod.transaction_status='"
							+ soModel.getTransactionStatus()
							+ "' where sod.submit_order_uuid='"
							+ soModel.getSubmitOrderUuid() + "'";
						String msg = sqlQueryManager.executeSQL(sql, "", false);
						if (RcUtil.isEmpty(msg)) {
							log.debug("===========submit_order_detail更新为" + soModel.getTransactionStatus() + "状态成功");
						}
					}else{
						for (SubmitOrderModel soModel : soModels) {
							soModel.setTransactionStatus(CommonUtil.Pending);
							soModel = this.dao.save(soModel);
							String sql = "update submit_order_detail sod set sod.transaction_status='"
								+ soModel.getTransactionStatus()
								+ "' where sod.submit_order_uuid='"
								+ soModel.getSubmitOrderUuid() + "'";
							String msg = sqlQueryManager.executeSQL(sql, "", false);
							if (RcUtil.isEmpty(msg)) {
								log.debug("===========submit_order_detail更新为" + soModel.getTransactionStatus() + "状态成功");
							}
						}
					}
					if(!RcUtil.isEmpty(errorMsg)){
						spj.setMsg(errorMsg);
					}else{
						spj.setMsg("取消提交成功!");
					}
				}else{
					if(!RcUtil.isEmpty(errorMsg)){
						spj.setMsg(errorMsg);
					}else{
						spj.setResult(false);
						spj.setError("取消提交失败!");
					}
				}
				spj.setObject(returnSoModel);
			}
			return spj;
		}
		
		
		/******************************* 公用，其他方法 ********************************/
		// 删除订单
		@Override
		public SinotransPageJson delSubmitOrder(String soUuid, String officeCode) {
			SinotransPageJson spj = new SinotransPageJson(true);
			String[] uuids = getStrArray(soUuid);
			for (String uuid : uuids) {
				SubmitOrderModel so = getSubmitOrderByUuid(uuid);
				if (!CommonUtil.Pending.equals(so.getTransactionStatus())) {
					throw new ApplicationException("订单:" + so.getSubmitOrderNo() + "状态是草稿状态才能删除!");
				}
				List<SubmitOrderDetailModel> sodList = this.dao.createCommonQuery(SubmitOrderDetailModel.class).addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.submitOrderUuid, so.getSubmitOrderUuid())).query();
				List<SubmitOrderLogModel> solList = this.dao.createCommonQuery(SubmitOrderLogModel.class).addCondition(Condition.eq(SubmitOrderLogModel.FieldNames.submitOrderUuid, so.getSubmitOrderUuid())).query();
				if (!RcUtil.isEmpty(solList) && solList.size() > 0) {
					// 删除订单的时候删除日志记录
					String sql = " delete from submit_order_log sol where sol.submit_order_uuid = '" + so.getSubmitOrderUuid() + "'";
					sqlQueryManager.executeSQL(sql, "", false);
					
					//海信 同步删除default_aux xgq 2014年11月27日21:00:37 
					String auxSql = " delete from default_aux da where da.order_uuid= '" + so.getSubmitOrderUuid() + "'";
					sqlQueryManager.executeSQL(auxSql, "", false);
				}
				if (RcUtil.isEmpty(sodList) || sodList.size() == 0) {
					this.dao.removeByPk(SubmitOrderModel.class, so.getSubmitOrderUuid());
					spj.setMsg("删除成功!");
					continue;
				}
				String sql = " delete from submit_order_detail sod where sod.submit_order_uuid = '" + so.getSubmitOrderUuid() + "'";
				String msg = sqlQueryManager.executeSQL(sql, "", false);
				if (RcUtil.isEmpty(msg)) {
					log.debug("==========================订单明细删除成功");
				} else {
					log.debug("==========================" + msg);
				}
				this.dao.removeByPk(SubmitOrderModel.class, so.getSubmitOrderUuid());
				spj.setMsg("删除成功!");
			}
			return spj;
		}		
	
		
		// 作废订单
		@Override
		public SinotransPageJson cancelOrderInOrOut(SubmitOrderModel soModel, List<SubmitOrderDetailModel> sodModelList, String soUuid) {
			SinotransPageJson spj = new SinotransPageJson();
			SubmitOrderModel som = new SubmitOrderModel();
			SubmitOrderModel submitOrderModel = dao.get(SubmitOrderModel.class, soModel.getSubmitOrderUuid());
			if (!submitOrderModel.getTransactionStatus().equals(soModel.getTransactionStatus())) {
				spj.setError("订单状态已经改变,操作失败!");
				spj.setResult(false);
				return spj;
			}
			if (!RcUtil.isEmpty(soUuid)) {
				soModel.setTransactionStatus(CommonUtil.Cancel);
				som = this.dao.save(soModel);
				for (SubmitOrderDetailModel sod : sodModelList) {
					sod.setTransactionStatus(CommonUtil.Cancel);
					this.dao.save(sod);
				}
			}
			spj.setObject(som);
			spj.setMsg("作废成功");
			spj.setResult(true);
			return spj;
		}	
		
	@Override
	public SinotransPageJson findSubmitOrderByUuid(String submitOrderUuid) {
		SinotransPageJson spj = new SinotransPageJson(true);
		SubmitOrderModel so = getSubmitOrderByUuid(submitOrderUuid);
		spj.setObject(so);
		spj.setMsg("获取成功!");
		return spj;
	}
	
	@Override
	public SubmitOrderModel getSubmitOrderByUuid(String submitOrderUuid) {
		List<SubmitOrderModel> soList = this.dao.createCommonQuery(SubmitOrderModel.class)
				.addCondition(Condition.eq(SubmitOrderModel.FieldNames.submitOrderUuid, submitOrderUuid)).query();
		if (soList.size() > 0) {
			return soList.get(0);
		} else {
			throw new ApplicationException("订单不存在或者已经被删除!");
		}
	}
	
	private String[] getStrArray(String strs) {
		if (RcUtil.isEmpty(strs)) {
			throw new ApplicationException("操作失败,无法获取前台数据!");
		}
		String[] uuids = RcUtil.split(strs, ",");
		return uuids;
	}
	/**
	 * 根据订单头uuid获取订单明细
	 * 
	 * @param submitOrderUuid
	 * @return
	 */
	private List<SubmitOrderDetailModel> getSubmitOrderDetailBySoUuid(String submitOrderUuid) {
		List<SubmitOrderDetailModel> sodModelList = this.dao.createCommonQuery(SubmitOrderDetailModel.class).addCondition(Condition.eq(SubmitOrderDetailModel.FieldNames.submitOrderUuid, submitOrderUuid)).query();
		return sodModelList;
	}
	
	private void checkAuitInbountOrder(SubmitOrderModel soModel){
    	if(RcUtil.isEmpty(soModel.getDeliveryType())){
			throw new ApplicationException("作业项目为空不能提交,请审核");
		}
		if(RcUtil.isEmpty(soModel.getSubmitDate())){
			throw new ApplicationException("办单日期为空不能提交,请审核");
		}
		//if(RcUtil.isEmpty(soModel.getAux3())){
		//	throw new ApplicationException("采购模式为空不能提交,请审核");
		//}
		if(RcUtil.isEmpty(soModel.getAgentConsigneeCode())&&RcUtil.isEmpty(soModel.getAgentConsigneeDesc())){
			throw new ApplicationException("委托单位为空不能提交,请审核");
		}
		if(RcUtil.isEmpty(soModel.getCargoConsigneeCode())&&RcUtil.isEmpty(soModel.getCargoConsigneeDesc())){
			throw new ApplicationException("供应商为空不能提交,请审核");
		}
    }
	
	/**
	 * 生成入库作业单
	 * 
	 * @param _submitOrderUuid
	 * @param _orderNOGenerateRule
	 * @param _transactionStatus
	 * @return
	 * @throws Exception
	 */
	private SinotransPageJson generateInboudOrder(SubmitOrderModel _submitOrderModel,List<SubmitOrderDetailModel> sodModelList,String _orderNOGenerateRule,String filePath) throws Exception {
		if (RcUtil.isEmpty(_submitOrderModel)) {
			throw new Exception("The '_submitOrderModel' argument must not be null.");
		}
		if (RcUtil.isEmpty(_orderNOGenerateRule)) {
			throw new Exception("The 'orderNOGenerateRule' argument must not be null.");
		}
		SinotransPageJson spj = new SinotransPageJson(true);
		LogisticsOrderModel loModel = new LogisticsOrderModel();
		BeanUtils.copyProperties(_submitOrderModel,loModel);
		loModel.setOrderDate(_submitOrderModel.getSubmitDate());// 委托日期
		loModel.setTransactionType(CommonUtil.TRANSACTIONTYPE_SIN);// SIN
		String logisticsOrderNo = wmsCommonManager.generateNumberByRule(_orderNOGenerateRule, _submitOrderModel.getOfficeCode());
		loModel.setLogisticsOrderNo(logisticsOrderNo);
		loModel.setTransactionStatus(CommonUtil.Active);
		loModel.setAux4(null);//
		loModel.setAux5(null);//
		loModel.setCargoControlCode(_submitOrderModel.getAux4());// 控货方code
		loModel.setCargoControlDesc(_submitOrderModel.getAux5());// 控货方desc
		frameFieldToNull(loModel);
		Map<String, String> uuidMap = new HashMap<String, String>();
		List<SubmitOrderDetailModel> sodModels = new ArrayList<SubmitOrderDetailModel>();
		boolean isExist=false;
		for (SubmitOrderDetailModel sodModel : sodModelList) {
			for (SubmitOrderDetailModel temp : sodModelList) {
				if(!RcUtil.isEmpty(sodModel.getBillNo())){//PO号不能为空
					if(!RcUtil.isEmpty(sodModel.getAux2())){//箱名号
						//入库ASN到货有箱名号，生成入库单时按PO+箱名号+ITEM合并
						if (sodModel.getBillNo().equals(temp.getBillNo()) && 
								sodModel.getItemCode().equals(temp.getItemCode()) && 
								sodModel.getAux2().equals(temp.getAux2()) && 
								!sodModel.getSubmitOrderDetailUuid().equals(temp.getSubmitOrderDetailUuid())) {
							if (RcUtil.isEmpty(uuidMap.get(sodModel.getSubmitOrderDetailUuid()))) {
								double qty = sodModel.getQty() + temp.getQty();
								sodModel.setQty(qty);
								isExist=true;
							}
							uuidMap.put(temp.getSubmitOrderDetailUuid(), sodModel.getSubmitOrderDetailUuid());
						}
					}else{//入库PO到货没箱名号，生成入库单时直接按PO+ITEM合并
						if (sodModel.getBillNo().equals(temp.getBillNo()) && 
								sodModel.getItemCode().equals(temp.getItemCode()) && 
								!sodModel.getSubmitOrderDetailUuid().equals(temp.getSubmitOrderDetailUuid())) {
							if (RcUtil.isEmpty(uuidMap.get(sodModel.getSubmitOrderDetailUuid()))) {
								double qty = sodModel.getQty() + temp.getQty();
								sodModel.setQty(qty);
								isExist=true;
							}
							uuidMap.put(temp.getSubmitOrderDetailUuid(), sodModel.getSubmitOrderDetailUuid());
						}
					}
				}
			}
			if(isExist){
				sodModels.add(sodModel);
				isExist=false;
			}
		}
		for (SubmitOrderDetailModel sodModel : sodModelList) {
			if (RcUtil.isEmpty(uuidMap.get(sodModel.getSubmitOrderDetailUuid()))) {
				sodModels.add(sodModel);
			}
		}
		if (sodModels.size() == 0) {
			return printLogAndThrowEx("入库订单物料明细为空,无法生成入库作业单!");
		}
		for (SubmitOrderDetailModel sodModel : sodModels) {
			String barcode = sodModel.getAux2();
			if(!RcUtil.isEmpty(barcode)){
				List<BarcodeModel> bcModelList = dao.createCommonQuery(BarcodeModel.class)
					.addCondition(Condition.eq(BarcodeModel.FieldNames.status, CommonUtil.Active))
					.addCondition(Condition.eq(BarcodeModel.FieldNames.barcode, barcode))
					.addCondition(Condition.eq(BarcodeModel.FieldNames.officeCode, sodModel.getOfficeCode())).query();
				if (!RcUtil.isEmpty(bcModelList) && bcModelList.size() > 0) {
					BarcodeModel bModel = bcModelList.get(0);
					spj=printLogAndThrowEx(barcode + "此箱号条码已存在入库单" + bModel.getLogisticsOrderNo() + "中!");
					break;
				}
			}
		}
		if(!spj.isResult()){
			loModel.setTransactionStatus(CommonUtil.Pending);
		}
		loModel = dao.save(loModel);// 生成入库作业单单头
		spj.setMsg("提交成功,生成的入库单号:"+loModel.getLogisticsOrderNo());
		for (SubmitOrderDetailModel sodModel : sodModels) {
			LogisticsOrderDetailModel lodModel = new LogisticsOrderDetailModel();
			BeanUtils.copyProperties(sodModel,lodModel);// copy sodModel信息到 lod
			lodModel.setAux1(sodModel.getAux1());//aux1账套号
			lodModel.setLogisticsOrderUuid(loModel.getLogisticsOrderUuid());// loUuid
			lodModel.setLogisticsOrderNo(loModel.getLogisticsOrderNo());// loOrderNo
			lodModel.setTransactionType(loModel.getTransactionType());// loTranType
			lodModel.setTransactionStatus(loModel.getTransactionStatus());// loTranStatus
			lodModel.setOfficeCode(loModel.getOfficeCode());// loOfficeCode
			if("成品入库".equals(loModel.getDeliveryType())){
				lodModel.setQty(0.0);
				lodModel.setControlWord(RcUtil.setKeyBit(3, CommonUtil.CONTROL_WORD_F, lodModel.getControlWord()));
			}
			lodModel.setSeqNo(sodModel.getSeqNo());// 设置PO行
			frameFieldToNull(lodModel);
			lodModel=dao.save(lodModel);// 生成入库作业单明细
//			if(YclCommonUtil.Active.equals(loModel.getTransactionStatus())&&!RcUtil.isEmpty(lodModel.getBarcode())){
//				yclInboundManager.saveBarcode(lodModel, loModel.getOfficeCode());
//			}
		}
		return spj;
	}
	
	/**
	 * 框架控制的字段值置空
	 */
	private void frameFieldToNull(BaseModel model) {
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
	 * 输出日志
	 * 
	 * @param errorMsg
	 */
	protected SinotransPageJson printLogAndThrowEx(String errorMsg) {
		SinotransPageJson spj = new SinotransPageJson(false);
		log.error(errorMsg);
		spj.setError(errorMsg);
		return spj;
	}
	
	private List<String> checkSoGeneterLo(List<SubmitOrderModel> oldSoModels,List<SubmitOrderModel> newSoModels){
		List<String> list=new ArrayList<String>();
		for (SubmitOrderModel soModel : oldSoModels) {
			List<LogisticsOrderModel> listLoModel=dao.createCommonQuery(LogisticsOrderModel.class)
				.addCondition(Condition.eq(LogisticsOrderModel.FieldNames.submitOrderUuid, soModel.getSubmitOrderUuid()))
				.query();
			if(listLoModel.size()>0){
				list.add(soModel.getSubmitOrderNo());
			}else{
				newSoModels.add(soModel);
			}
		}
		return list;
	}

	@Override
	public SubmitOrderModel get(String id) {
		return this.dao.get(SubmitOrderModel.class, id);
	}
}
