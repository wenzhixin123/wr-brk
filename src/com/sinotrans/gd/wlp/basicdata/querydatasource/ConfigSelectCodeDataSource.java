package com.sinotrans.gd.wlp.basicdata.querydatasource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sinotrans.framework.common.support.QueryDataSource;
import com.sinotrans.framework.common.support.QueryField;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.Condition;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.basicdata.entity.ConfigSelectCodeEntity;
import com.sinotrans.gd.wlp.common.model.LocPlanConfigModel;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.util.CommonUtil;

@Service
public class ConfigSelectCodeDataSource extends BaseManagerImpl implements
		QueryDataSource {

	@Override
	public List<ConfigSelectCodeEntity> getData(List<QueryField> queryFields,
			String orderBy, PagingInfo pagingInfo) {
		List<ConfigSelectCodeEntity> resultList = new ArrayList<ConfigSelectCodeEntity>();
		SessionContextUserEntity scue = SessionContextUserEntity.currentUser();	
		String configType = "";

		if (!RcUtil.isEmpty(queryFields) && queryFields.size() > 0) {
			for (int i = 0; i < queryFields.size(); i++) {
				if ("configType".equals(queryFields.get(i).getFieldName())) {
					configType = queryFields.get(i).getFieldValue().toString();
				}
			}

			if (!RcUtil.isEmpty(configType)) {
				LocPlanConfigModel lpcExample = new LocPlanConfigModel();
				lpcExample.setOfficeCode(scue.getOfficeCode());
				lpcExample.setConfigType(configType);
				lpcExample.setStatus(CommonUtil.Active);

				for (int i = 0; i < queryFields.size(); i++) {
					if ("locPlanConfigUuid".equals(queryFields.get(i)
							.getFieldName())
							&& "=".equals(queryFields.get(i).getOperator())) {
						lpcExample.setLocPlanConfigUuid(queryFields.get(i)
								.getFieldStringValue());
					} else if ("configCode".equals(queryFields.get(i)
							.getFieldName())
							&& "=".equals(queryFields.get(i).getOperator())) {
						lpcExample.setConfigCode(queryFields.get(i)
								.getFieldStringValue());
					}
				}
				//disable by csl 2014-07-28
				//List<LocPlanConfigModel> lpcList = this.dao.findByExample(lpcExample);
				List<LocPlanConfigModel> lpcList = dao.createCommonQuery(LocPlanConfigModel.class)
					.addDynamicCondition(Condition.eq(LocPlanConfigModel.FieldNames.officeCode, lpcExample.getOfficeCode()))
					.addDynamicCondition(Condition.eq(LocPlanConfigModel.FieldNames.configType, lpcExample.getConfigType()))
					.addDynamicCondition(Condition.eq(LocPlanConfigModel.FieldNames.status, CommonUtil.Active))
					.addDynamicCondition(Condition.eq(LocPlanConfigModel.FieldNames.locPlanConfigUuid,lpcExample.getLocPlanConfigUuid()))
					.addDynamicCondition(Condition.eq(LocPlanConfigModel.FieldNames.configCode, lpcExample.getConfigCode())).query();
				if (!RcUtil.isEmpty(lpcList) && lpcList.size() > 0) {
					for (LocPlanConfigModel lpcModel : lpcList) {
						ConfigSelectCodeEntity csce = new ConfigSelectCodeEntity();
						csce.setConfigCode(lpcModel.getConfigCode());
						csce.setLocPlanConfigUuid(lpcModel
								.getLocPlanConfigUuid());
						csce.setConfigName(lpcModel.getConfigName());
						csce.setConfigNameEn(lpcModel.getConfigNameEn());
						resultList.add(csce);
					}
				}
			}
		}
		return resultList;
	}

	@Override
	public Class<ConfigSelectCodeEntity> getDataItemClass() {
		return ConfigSelectCodeEntity.class;
	}

}
