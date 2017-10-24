package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasPlanTypeModel;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;

public interface BasPlanTypeManager extends BaseManager {

	BasPlanTypeModel get(String id);

	List<BasPlanTypeModel> getAll();

	List<BasPlanTypeModel> findByExample(BasPlanTypeModel example);

	BasPlanTypeModel save(BasPlanTypeModel model);

	List<BasPlanTypeModel> saveAll(Collection<BasPlanTypeModel> models);

	void remove(BasPlanTypeModel model);

	void removeAll(Collection<BasPlanTypeModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);
	
	/**
	 * 操作 BasPlanTypeModel 策略类型列表 (增 删 改)
	 * @param jsonResult
	 * @param officeCode
	 * @return
	 * @throws Exception
	 */
	SinotransPageJson saveBasPlanType(String jsonResult,String officeCode) throws Exception;
	/**
	 * 修改 BasPlanTypeModel Status 状态
	 * @param tableName
	 * @param updateFieldName
	 * @param updateWhereField
	 * @param ctmBctuId
	 * @param status
	 * @return
	 */
	public boolean updateStatusBasPlanType(String tableName,String updateFieldName,String updateWhereField,String ctmBctuId,String status)throws Exception;
	
	/**
	 * 删除 BasPlanTypeModel 对象
	 * @param planTypeUuid
	 * @return
	 * @throws Exception
	 */
	public boolean removePlanType(String planTypeUuid) throws Exception;
}
