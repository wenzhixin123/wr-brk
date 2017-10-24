package com.sinotrans.gd.wlp.basicdata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.gd.wlp.basicdata.model.BasPlanTypeModel;
import com.sinotrans.gd.wlp.basicdata.service.BasPlanTypeManager;
import com.sinotrans.gd.wlp.common.service.SQLQueryManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.JsonUtil;

@Service
public class BasPlanTypeManagerImpl extends BaseManagerImpl implements BasPlanTypeManager {

	@Autowired
	private SQLQueryManager sqlQueryManager;
	public BasPlanTypeModel get(String id) {
		return this.dao.get(BasPlanTypeModel.class, id);
	}

	public List<BasPlanTypeModel> getAll() {
		return this.dao.getAll(BasPlanTypeModel.class);
	}

	public List<BasPlanTypeModel> findByExample(BasPlanTypeModel example) {
		return this.dao.findByExample(example);
	}

	public BasPlanTypeModel save(BasPlanTypeModel model) {
		return this.dao.save(model);
	}

	public List<BasPlanTypeModel> saveAll(Collection<BasPlanTypeModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(BasPlanTypeModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<BasPlanTypeModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(BasPlanTypeModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(BasPlanTypeModel.class, ids);
	}

	@Override
	public SinotransPageJson saveBasPlanType(String jsonResult,
			String officeCode) throws Exception {
		SinotransPageJson spj = new SinotransPageJson(true);
		BasPlanTypeModel[] bptmJsonList = (BasPlanTypeModel[])JsonUtil.jsonToBean(jsonResult,
				BasPlanTypeModel[].class,new SimpleDateFormat(RcUtil.yyyy_MM_dd_HH_mm_ss));//将Json转换为BasPlanTypeModel数组
		List<BasPlanTypeModel> bptList = new ArrayList<BasPlanTypeModel>();
		for(BasPlanTypeModel bpt:bptmJsonList) {
			bpt.setOfficeCode(officeCode);
			bpt.setControlWord(CommonUtil.Default_Control_Word);
			bptList.add(bpt);
		}
		this.dao.saveAll(bptList);
		return spj;
	}

	@Override
	public boolean updateStatusBasPlanType(String tableName,
			String updateFieldName, String updateWhereField, String ctmBctuId,
			String status) throws Exception {
		if(!RcUtil.isEmpty(tableName)){
			String updateBctSql = "update "+tableName+" bpt set bpt."+updateFieldName+"='" + status + "' where bpt."+updateWhereField+"='" + ctmBctuId + "'";
			sqlQueryManager.executeSQL(updateBctSql, "", true);
		}
		return true;
	}
	
	@Override
	public boolean removePlanType(String planTypeUuid) throws Exception {
		if(!RcUtil.isEmpty(planTypeUuid)){
			String deletePtuSql = "delete bas_plan_type where plan_type_uuid = '"+planTypeUuid+"'";
			sqlQueryManager.executeSQL(deletePtuSql, "", true);
			return true;
		}else {
			return false;
		}
		
	}

}
