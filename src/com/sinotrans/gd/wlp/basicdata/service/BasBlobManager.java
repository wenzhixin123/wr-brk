package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasBlobModel;

public interface BasBlobManager extends BaseManager {

	BasBlobModel get(String id);

	List<BasBlobModel> getAll();

	List<BasBlobModel> findByExample(BasBlobModel example);

	BasBlobModel save(BasBlobModel model);

	List<BasBlobModel> saveAll(Collection<BasBlobModel> models);

	void remove(BasBlobModel model);

	void removeAll(Collection<BasBlobModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	public byte[] findFileDataByFk(String uuid, String officeCode);

	void saveWarehouseMessage(String params, String jsonResult);

	String queryUuidByDataUuid(String preDataUuid);

	public List<BasBlobModel> findFileByFk(String uuid);

	public List<BasBlobModel> findFileByFks(List<String> uuids,
			String officeCode);
	
	public BasBlobModel queryModelByAll(String preDataUuid);

}
