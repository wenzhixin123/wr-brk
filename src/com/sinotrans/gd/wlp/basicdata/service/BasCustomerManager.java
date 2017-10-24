package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.entity.BasCustomerEntity;
import com.sinotrans.gd.wlp.basicdata.model.BasCustomerModel;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;

public interface BasCustomerManager extends BaseManager {

	BasCustomerModel get(String id);

	List<BasCustomerModel> getAll();
	
	BasCustomerModel findByCustomerCode(String customerCode);

	List<BasCustomerModel> findByExample(BasCustomerModel example);

	BasCustomerModel save(BasCustomerModel model);

	List<BasCustomerModel> saveAll(Collection<BasCustomerModel> models);

	void remove(BasCustomerModel model);

	void removeAll(Collection<BasCustomerModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	List<BasOption> getOptionsByCode(String code, String officeCode,
			String language);

	boolean getYanZhenCustomCode(String userCode);

	BasCustomerModel getModelByCode(String customerCode);

	SinotransPageJson saveBasCustomer(String jsonResult) throws Exception;

	List<BasCustomerEntity> getCustomerEntity(BasCustomerEntity entity);
	
	List<String> gerCustomerByInput(String inputValue);
	
	List<String> gerCustomerByInputFuzzy(String inputValue);
	
	String getCustomsCodeByDesc1stRow(String desc);
	
	String getCustomsDescBySplit(String desc,int num,String regex);
	
	String checkMutAutoSendType(String customerCode);
}
