package com.sinotrans.gd.wlp.common.service.impl;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.model.OperationLog;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.util.EntityUtils;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeDefModel;
import com.sinotrans.gd.wlp.basicdata.model.BasCustomerModel;
import com.sinotrans.gd.wlp.basicdata.query.BasCodeTypeByTypeCodeAndCodeValueQueryCondition;
import com.sinotrans.gd.wlp.basicdata.query.BasCodeTypeByTypeCodeAndCodeValueQueryItem;
import com.sinotrans.gd.wlp.basicdata.service.BasCodeDefManager;
import com.sinotrans.gd.wlp.common.entity.EasyUiTree;
import com.sinotrans.gd.wlp.common.model.LocPlanConfigModel;
import com.sinotrans.gd.wlp.common.model.LocationTaskModel;
import com.sinotrans.gd.wlp.common.query.GetCodeDefQueryCondition;
import com.sinotrans.gd.wlp.common.query.GetCodeDefQueryItem;
import com.sinotrans.gd.wlp.common.query.SystemCodeCodeDefQueryCondition;
import com.sinotrans.gd.wlp.common.query.SystemCodeCodeDefQueryItem;
import com.sinotrans.gd.wlp.common.service.SQLQueryManager;
import com.sinotrans.gd.wlp.common.service.WmsCommonManager;
import com.sinotrans.gd.wlp.common.web.EC_CommonUtil;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class WmsCommonManagerImpl extends BaseManagerImpl implements WmsCommonManager {

	@Autowired
	private SQLQueryManager sqlQueryManager;
	
	@Autowired
	private BasCodeDefManager basCodeDefManager;
	
	
	
	
	public String generateNumberByRule(String transactionType, String officeCode) {
		synchronized (this) {
			String result = "";

			// 如果是下面这些要生成lt表编号的话就直接返回UUID
			if (!RcUtil.isEmpty(transactionType)
					&& (CommonUtil.SEQUENCE_UUID.equals(transactionType))) {
				result = UUID.randomUUID().toString();
				return result;
			}
			if (CommonUtil.SEQUENCE_LT.equals(transactionType)) {// TL
				// 表所有记录都成UUID
				result = UUID.randomUUID().toString();
				return result;
			}
			if (!RcUtil.isEmpty(transactionType)) {
				// // 查询字典获取规则信息
				// List<BasCodeDefModel> codeDefList = basCodeDefManager
				// .findByTypeCode(EC_CommonUtil.TABLE_SEQUENCE_PK,
				// officeCode, true);
				// if (!RcUtil.isEmpty(codeDefList) && codeDefList.size() > 0) {
				// // 循环字典信息，看需要生成哪种单的编号
				// for (int i = 0; i < codeDefList.size(); i++) {
				// String codeValue = codeDefList.get(i).getCodeValue();

				// 查询字典信息
				GetCodeDefQueryCondition codeDefCondition = new GetCodeDefQueryCondition(
						transactionType, EC_CommonUtil.TABLE_SEQUENCE_PK,
						officeCode);
				List<GetCodeDefQueryItem> codeDefItem = this.dao.query(
						codeDefCondition, GetCodeDefQueryItem.class);

				if (!RcUtil.isEmpty(codeDefItem) && codeDefItem.size() > 0) {

					// if (transactionType.equals(codeValue)) {
					String ruleStr = codeDefItem.get(0).getRemark(); // 规则
					String tableMsg = codeDefItem.get(0).getDisplayValueEn(); // 表和查询信息
					String type = codeDefItem.get(0).getDisplayValue(); // 用来做为条件的值，比如transactionType这些要的

					// 处理规则字符串
					if (!RcUtil.isEmpty(ruleStr)) {
						String[] ruleStrs = ruleStr.split("_"); // 它会以
						// Y_yyyymmdd_5
						// 类似这样的形式做为规则传来
						if (!RcUtil.isEmpty(ruleStrs) && ruleStrs.length >= 3) {
							SimpleDateFormat sdf = new SimpleDateFormat(
									ruleStrs[1]);
							String dateStr = sdf.format(this.dao.getSysDate());
							String firstStr = ruleStrs[0] + dateStr; // 前面几位的字符，就是除了后面数字位
							Integer count = Integer.valueOf(ruleStrs[2]); // 后面数字的位数

							// 处理表信息的字符串
							if (!RcUtil.isEmpty(tableMsg)) {
								String[] tableStrs = tableMsg.split("#");

								if (!RcUtil.isEmpty(tableStrs)
										&& tableStrs.length >= 2) {
									String tableName = tableStrs[0];
									String numberCol = tableStrs[1];
									String typeCol = "";

									if (tableStrs.length >= 3) {
										typeCol = tableStrs[2];
									}
									if (!RcUtil.isEmpty(type)) {
										String[] typeStrs = type.split(",");
										if (!RcUtil.isEmpty(typeStrs)
												&& typeStrs.length > 0) {
											type = "";
											for (int typeIndex = 0; typeIndex < typeStrs.length; typeIndex++) {
												if (typeIndex == typeStrs.length - 1) {
													type += "'"
															+ typeStrs[typeIndex]
															+ "'";
												} else {
													type += "'"
															+ typeStrs[typeIndex]
															+ "',";
												}
											}
										} else {
											type = "''";
										}
									} else {
										type = "''";
									}

									String sqlStr = "";

									sqlStr += "select CONCAT  ( '"
											+ firstStr
											+ "' , lpad(CONVERT(IFNULL(max(substr(" 
											+ numberCol + ", "
											+ (firstStr.length() + 1) + ", "
											+ count + ")),0),SIGNED)+1," + count
											+ ",'0') )  ";
									sqlStr += "  from " + tableName + " ";
									sqlStr += "   where " + numberCol
											+ " like '" + firstStr + "%'";
									sqlStr += " and office_code='" + officeCode
											+ "' AND LENGTH("+numberCol+") ="+(firstStr.length()+count);

									if (!RcUtil.isEmpty(typeCol)) {
										sqlStr += " and " + typeCol + " in ("
												+ type + ") ";
									}
									result = sqlQueryManager.getColumnData(
											sqlStr, "", "");
								}
							}
						} else if (!RcUtil.isEmpty(ruleStrs)
								&& ruleStrs.length >= 2) {// **_*格式进入
							String firstStr = ruleStrs[0];
							Integer count = Integer.valueOf(ruleStrs[1]); // 后面数字的位数

							// 处理表信息的字符串
							if (!RcUtil.isEmpty(tableMsg)) {
								String[] tableStrs = tableMsg.split("#");

								if (!RcUtil.isEmpty(tableStrs)
										&& tableStrs.length >= 2) {
									String tableName = tableStrs[0];
									String numberCol = tableStrs[1];
									String typeCol = "";

									if (tableStrs.length >= 3) {
										typeCol = tableStrs[2];
									}
									if (!RcUtil.isEmpty(type)) {
										String[] typeStrs = type.split(",");
										if (!RcUtil.isEmpty(typeStrs)
												&& typeStrs.length > 0) {
											type = "";
											for (int typeIndex = 0; typeIndex < typeStrs.length; typeIndex++) {
												if (typeIndex == typeStrs.length - 1) {
													type += "'"
															+ typeStrs[typeIndex]
															+ "'";
												} else {
													type += "'"
															+ typeStrs[typeIndex]
															+ "',";
												}
											}
										} else {
											type = "''";
										}
									} else {
										type = "''";
									}

									String sqlStr = "";

									sqlStr += "select '"
											+ firstStr
											+ "' || lpad(to_number(nvl(max(substr("
											+ numberCol + ", "
											+ (firstStr.length() + 1) + ", "
											+ count + ")),0))+1," + count
											+ ",'0')  ";
									sqlStr += "  from " + tableName + " ";
									sqlStr += "   where " + numberCol
											+ " like '" + firstStr + "%'";
									sqlStr += " and office_code='" + officeCode
											+ "' AND LENGTH("+numberCol+") ="+(firstStr.length()+count);

									if (!RcUtil.isEmpty(typeCol)) {
										sqlStr += " and " + typeCol + " in ("
												+ type + ") ";
									}

									result = sqlQueryManager.getColumnData(
											sqlStr, "", "");
								}
							}
						}
					}
				}
				// }
				// }

			}
			return result;
		}
	}

	
	
	/**
	 * 货物类型-到货方式等（二期框架没有配置、所以单独提供）
	 */
	@Override
	public SinotransPageJson systemCodeCodeDef(String typeCode, String status,String officeCode) {
		SinotransPageJson spj = new SinotransPageJson();
		SystemCodeCodeDefQueryCondition condition = new SystemCodeCodeDefQueryCondition(
				typeCode, status, officeCode);
		List<SystemCodeCodeDefQueryItem> itemList = new ArrayList<SystemCodeCodeDefQueryItem>();
		itemList = this.dao.query(condition, SystemCodeCodeDefQueryItem.class);
		List<EasyUiTree> comboxList = new ArrayList<EasyUiTree>();
		for (SystemCodeCodeDefQueryItem systemCodeCodeDefQueryItem : itemList) {
			EasyUiTree combox = new EasyUiTree();
			combox.setId(systemCodeCodeDefQueryItem.getCodeValue());
			combox.setText(systemCodeCodeDefQueryItem.getDisplayValue());
			combox.setRemark(systemCodeCodeDefQueryItem.getRemark());
			if (!RcUtil.isEmpty(systemCodeCodeDefQueryItem.getControlWord())
					&& "1".equals(systemCodeCodeDefQueryItem.getControlWord()
							.substring(0, 1))) {
				combox.setState("true");
			}
			comboxList.add(combox);
		}
		spj.setObject(comboxList);
		spj.setResult(true);
		return spj;
	}



	/**
	 * 
	 * 获取系统默认用户
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public BasCustomerModel getDefaultCustomer() throws ApplicationException {
		SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
		BasCustomerModel bcm = new BasCustomerModel();
		List<BasCodeDefModel> bcdmList = basCodeDefManager.findByTypeCode(
				EC_CommonUtil.SYS_DEFAULT_CUSTOMER, scue.getOfficeCode(), true);
		if (bcdmList == null || bcdmList.size() < 1) {
		} else {
			BasCodeDefModel bcdm = bcdmList.get(0);
			List<BasCustomerModel> bcmList = dao.createCommonQuery(BasCustomerModel.class)
				.addCondition(Condition.eq(BasCustomerModel.FieldNames.status, EC_CommonUtil.ACTIVE))
				.addCondition(Condition.eq(BasCustomerModel.FieldNames.customerCode, bcdm.getCodeValue()))
				.addCondition(Condition.eq(BasCustomerModel.FieldNames.officeCode,bcdm.getOfficeCode()))
				.query();
			if (bcmList != null && bcmList.size() > 0) {
				bcm = bcmList.get(0);
			}
		}
		return bcm;
	}



	@Override
	public Date getDataBaseDateFor_YMD_HMS_Type4Date() {
		return dao.getSysDate();
	}
	
	@Override
	public String getDataBaseDateFor_YMD_HMS() {
		String yyyyMmDdHhMmSs = RcUtil.date2String(dao.getSysDate(),
				RcUtil.yyyy_MM_dd_HH_mm_ss);
		return yyyyMmDdHhMmSs;
	}
	
	/**
	 * 获取数据库日期Y-M-D
	 * 
	 * @return
	 */
	@Override
	public String getDataBaseDateFor_Yyyy_Mm_Dd() {
		String yyyyMmDd = RcUtil.date2String(dao.getSysDate(),
				RcUtil.yyyy_MM_dd);
		return yyyyMmDd;
	}
	
	@Override
	public String getDefaultDeliveyType(String type, String officeCode) {
		String result = "";
		BasCodeTypeByTypeCodeAndCodeValueQueryCondition condition = new BasCodeTypeByTypeCodeAndCodeValueQueryCondition();
		condition.setTypeCode(type);
		condition.setOfficeCode(officeCode);
		condition.setControlWord("1");// 默认
		List<BasCodeTypeByTypeCodeAndCodeValueQueryItem> resultItem =  this.dao.query(condition, BasCodeTypeByTypeCodeAndCodeValueQueryItem.class);
		if (RcUtil.isEmpty(resultItem) || resultItem.size()==0) {
			List<BasCodeDefModel> basCdModelList = basCodeDefManager.findByTypeCode(type, officeCode, true);
			if (basCdModelList == null || basCdModelList.size() == 0) {
				throw new ApplicationException("请增加出入库作业类型");
			} else {
				result = basCdModelList.get(0).getCodeValue();
			}
		} else {
			result = resultItem.get(0).getCodeValue();
		}
		return result;
	}
	
	
	/**
	 * 获取默认策略
	 */
	private String controlWord = "D0000000000000000000";

	@Override
	public LocPlanConfigModel getDefaultPlanConfig(String configType,
			String officeCode) throws ApplicationException {
		LocPlanConfigModel lpcm = new LocPlanConfigModel();
		if(RcUtil.isEmpty(configType)){
			throw new ApplicationException("configType 不能为空！");
		}
		if(RcUtil.isEmpty(officeCode)){
			throw new ApplicationException("officeCode 不能为空！");
		}
		List<LocPlanConfigModel> lpcmList = dao.createCommonQuery(LocPlanConfigModel.class)
							.addCondition(Condition.eq(LocPlanConfigModel.FieldNames.configType, configType))
							.addCondition(Condition.eq(LocPlanConfigModel.FieldNames.officeCode, officeCode))
							.addCondition(Condition.eq(LocPlanConfigModel.FieldNames.status, EC_CommonUtil.ACTIVE))
							.addCondition(Condition.eq(LocPlanConfigModel.FieldNames.controlWord, controlWord)).query();
		
		if (lpcmList != null && lpcmList.size() > 0) {
			lpcm = lpcmList.get(0);
		} else {
			throw new ApplicationException("找不到对应的策略信息");
		}
		return lpcm;
	}


	@Override
	public LocationTaskModel commonSaveLocationTask(LocationTaskModel ltModel) throws Exception {
		LocationTaskModel saveLt = new LocationTaskModel();
		SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
		BeanUtils.copyProperties(ltModel,saveLt);
		RcUtil.toZero(saveLt);
		Date dateTime = dao.getSysDate();
		saveLt.setLocTaskDate(dateTime);
		saveLt.setCreateTime(null);
		saveLt.setCreator(null);
		saveLt.setModifyTime(null);
		saveLt.setModifier(null);
		saveLt.setRecVer(0L);
		saveLt.setLocationTaskUuid("");
		saveLt.setStatus(CommonUtil.Active);
		saveLt.setCreator(scue.getUserId());
		saveLt.setWrhWorker(scue.getUsername());
		String locTaskNo = generateNumberByRule(CommonUtil.SEQUENCE_UUID, scue.getOfficeCode());
		saveLt.setLocTaskNo(locTaskNo);
		frameFieldToNull(saveLt);
		saveLt = dao.save(saveLt);
		return saveLt;
	}
	
	
	@Override
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
	
	
}