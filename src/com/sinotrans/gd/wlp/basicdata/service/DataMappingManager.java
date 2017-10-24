package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.DataMappingModel;

public interface DataMappingManager extends BaseManager {

	DataMappingModel get(String id);

	List<DataMappingModel> getAll();

	List<DataMappingModel> findByExample(DataMappingModel example);

	DataMappingModel save(DataMappingModel model);

	List<DataMappingModel> saveAll(Collection<DataMappingModel> models);

	void remove(DataMappingModel model);

	void removeAll(Collection<DataMappingModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);
	
	boolean removeType(String pkId,long recVer);
	
	/*boolean updateSysStatusOue(String loUuid, String status, String tableName,
			String statusName, String uuidName);*/
}
