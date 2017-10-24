package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasVesselModel;

public interface BasVesselManager extends BaseManager {

	BasVesselModel get(String id);

	List<BasVesselModel> getAll();

	List<BasVesselModel> findByExample(BasVesselModel example);

	BasVesselModel save(BasVesselModel model);

	List<BasVesselModel> saveAll(Collection<BasVesselModel> models);

	void remove(BasVesselModel model);

	void removeAll(Collection<BasVesselModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);
	
	BasVesselModel findByCode(String vesselCode);

	String findVesselNameEnByCode(String vesselCode);

	BasVesselModel findByLoacalCode(String localVesselCode,String depot);
	
	BasVesselModel findByLicenseCode(String customsLicenseCode);
}
