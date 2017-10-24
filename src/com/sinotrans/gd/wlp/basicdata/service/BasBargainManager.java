package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasBargainModel;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.OfficeTree;

public interface BasBargainManager extends BaseManager {

	BasBargainModel get(String id);

	List<BasBargainModel> getAll();

	List<BasBargainModel> findByExample(BasBargainModel example);

	BasBargainModel save(BasBargainModel model);

	List<BasBargainModel> saveAll(Collection<BasBargainModel> models);

	void remove(BasBargainModel model);

	void removeAll(Collection<BasBargainModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	List<BasBargainModel> saveAllBasBargain(Collection<BasBargainModel> models);

	boolean getcontractOne(String contranctone);

	String selectbargainCode(String contranctone);

	List<OfficeTree> getBasBargaintree(String jsonResult);
	
	SinotransPageJson saveBasBargain(String jsonResult) throws Exception;

}
