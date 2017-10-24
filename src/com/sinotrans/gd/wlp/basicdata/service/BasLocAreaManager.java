package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.entity.BasLocAreaEntity;
import com.sinotrans.gd.wlp.basicdata.model.BasLocAreaModel;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;

public interface BasLocAreaManager extends BaseManager {

	BasLocAreaModel get(String id);

	List<BasLocAreaModel> getAll();

	List<BasLocAreaModel> findByExample(BasLocAreaModel example);

	BasLocAreaModel save(BasLocAreaModel model);

	List<BasLocAreaModel> saveAll(Collection<BasLocAreaModel> models);

	void remove(BasLocAreaModel model);

	void removeAll(Collection<BasLocAreaModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	String getJsonSaveData(String jsonResult);

	String removeLocAreaByPk(String id);

	//String removeLocAreaByLI(String id);

	boolean validatelocArea(String locAreaCode, String officeCode);

	/**
	 * 获取系统默认收货区
	 * 
	 * @param officeCode
	 * @return
	 */
	List<BasLocAreaModel> findByR(String officeCode) throws ApplicationException;

	SinotransPageJson saveBasLocArea(BasLocAreaEntity model);
}
