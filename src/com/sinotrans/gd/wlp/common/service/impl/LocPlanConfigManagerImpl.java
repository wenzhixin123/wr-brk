package com.sinotrans.gd.wlp.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.exception.SystemException;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.util.SqlUtils;
import com.sinotrans.gd.wlp.common.model.LocPlanConfigDetailModel;
import com.sinotrans.gd.wlp.common.model.LocPlanConfigModel;
import com.sinotrans.gd.wlp.common.service.LocPlanConfigManager;


@Service
public class LocPlanConfigManagerImpl extends BaseManagerImpl implements LocPlanConfigManager {

	
	public LocPlanConfigModel get(String id) {
		return this.dao.get(LocPlanConfigModel.class, id);
	}
	
	@Override
	public String getStrategyConditionSqlByConfigCode(String configCode,
			String officeCode) {
		String locPlanConfigUuid = "";
		List<LocPlanConfigModel> lpcmList = this.dao.createCommonQuery(
				LocPlanConfigModel.class).addCondition(
				Condition.eq(LocPlanConfigModel.FieldNames.configCode,
						configCode)).addCondition(
				Condition.eq(LocPlanConfigModel.FieldNames.officeCode,
						officeCode)).query();
		if (lpcmList != null && lpcmList.size() > 0) {
			locPlanConfigUuid = lpcmList.get(0).getLocPlanConfigUuid();
		}
		return getStrategyConditionSql(locPlanConfigUuid);
	}
	
	/**
	 * 取得策略配置的sql过滤条件
	 * 
	 * @param locPlanConfigUuid
	 * @return
	 */
	public String getStrategyConditionSql(String locPlanConfigUuid) {
		List<LocPlanConfigDetailModel> locPlanConfigDetailModelList = this.dao
				.createCommonQuery(LocPlanConfigDetailModel.class)
				.addCondition(
						Condition
								.eq(
										LocPlanConfigDetailModel.FieldNames.locPlanConfigUuid,
										locPlanConfigUuid))
				.addCondition(
						Condition
								.or(
										Condition
												.isNull(LocPlanConfigDetailModel.FieldNames.fieldCode),
										Condition
												.ne(
														LocPlanConfigDetailModel.FieldNames.fieldCode,
														"ORDER BY")))
				.setOrderBy(LocPlanConfigDetailModel.FieldNames.seqNo).query();

		if (locPlanConfigDetailModelList == null
				|| locPlanConfigDetailModelList.size() == 0) {
			return "0=0";
		}

		for (LocPlanConfigDetailModel locPlanConfigDetailModel : locPlanConfigDetailModelList) {
			if (locPlanConfigDetailModel.getPreLocPlanConfigDetailUui() == null) {
				return getCondition(locPlanConfigDetailModel,
						locPlanConfigDetailModelList).getRunableSql();
			}
		}

		return "0=0";
	}
	
	
	private Condition getCondition(
			LocPlanConfigDetailModel locPlanConfigDetailModel,
			List<LocPlanConfigDetailModel> locPlanConfigDetailModelList) {
		if (locPlanConfigDetailModel.getFieldName() != null) {
			String field1 = SqlUtils
					.convertAllJavaNamesToDbNames(locPlanConfigDetailModel
							.getFieldCode());
			String field2 = SqlUtils
					.convertAllJavaNamesToDbNames(locPlanConfigDetailModel
							.getFieldValue());
			return Condition.sql(field1 + " "
					+ locPlanConfigDetailModel.getOperationFlag() + " "
					+ field2);
		} else {
			List<Condition> subConditionList = new ArrayList<Condition>();
			for (LocPlanConfigDetailModel m : locPlanConfigDetailModelList) {
				if (locPlanConfigDetailModel.getLocPlanConfigDetailUuid()
						.equals(m.getPreLocPlanConfigDetailUui())) {
					subConditionList.add(getCondition(m,
							locPlanConfigDetailModelList));
				}
			}
			if (subConditionList.size() == 0) {
				throw new SystemException(
						"No sub LocPlanConfigDetails found for "
								+ locPlanConfigDetailModel
										.getLocPlanConfigDetailUuid());
			}
			if ("and".equalsIgnoreCase(locPlanConfigDetailModel.getAndorFlag())) {
				return Condition.and(subConditionList);
			} else if ("or".equalsIgnoreCase(locPlanConfigDetailModel
					.getAndorFlag())) {
				return Condition.or(subConditionList);
			} else {
				throw new SystemException("Unsupported and/or flag "
						+ locPlanConfigDetailModel.getAndorFlag());
			}
		}
	}
	
	@Override
	public String getStrategyOrderBySqlByConfigCode(String configCode,
			String officeCode) {
		return getStrategyOrderBySql(getLpcModelUUID(configCode,officeCode));
	}
	
	private String getLpcModelUUID(String configCode,String officeCode){
		String locPlanConfigUuid = "";

		List<LocPlanConfigModel> lpcmList = this.dao.createCommonQuery(
				LocPlanConfigModel.class).addCondition(
				Condition.eq(LocPlanConfigModel.FieldNames.configCode,
						configCode)).addCondition(
				Condition.eq(LocPlanConfigModel.FieldNames.officeCode,
						officeCode)).query();
		if (lpcmList != null && lpcmList.size() > 0) {// 判断是否为空
			locPlanConfigUuid = lpcmList.get(0).getLocPlanConfigUuid();
		} else {
			throw new ApplicationException("没有获得相应的策略ID");
		}
		return locPlanConfigUuid;
	}
	
	/**
	 * 取得策略配置的sql排序条件
	 * 
	 * @param locPlanConfigUuid
	 * @return
	 */
	public String getStrategyOrderBySql(String locPlanConfigUuid) {
		List<LocPlanConfigDetailModel> locPlanConfigDetailModelList = this.dao
				.createCommonQuery(LocPlanConfigDetailModel.class)
				.addCondition(
						Condition
								.eq(
										LocPlanConfigDetailModel.FieldNames.locPlanConfigUuid,
										locPlanConfigUuid)).addCondition(
						Condition.eq(
								LocPlanConfigDetailModel.FieldNames.fieldCode,
								"ORDER BY")).setOrderBy(
						LocPlanConfigDetailModel.FieldNames.seqNo).query();

		if (locPlanConfigDetailModelList == null || locPlanConfigDetailModelList.size() == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		for (LocPlanConfigDetailModel locPlanConfigDetailModel : locPlanConfigDetailModelList) {
			sb.append(locPlanConfigDetailModel.getFieldValue());
			sb.append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	
	@Override
	public String getNextConfigCode(String thisConfigCode, String officeCode) {
		LocPlanConfigModel lpcModel = new LocPlanConfigModel();
		String nextConfigCode = "";
		List<LocPlanConfigModel> lpcModelList = this.dao.createCommonQuery(LocPlanConfigModel.class)
												.addCondition(Condition.eq(LocPlanConfigModel.FieldNames.officeCode, officeCode))
												.addCondition(Condition.eq(LocPlanConfigModel.FieldNames.configCode, thisConfigCode))
												.query();
		if (lpcModelList != null && lpcModelList.size() > 0) {
			lpcModel = lpcModelList.get(0);
			nextConfigCode = lpcModel.getNextConfigCode();
		}else{
//			throw new ApplicationException("出库策略必须设置下一级默认策略.");
		}
		return nextConfigCode;
	}
}
