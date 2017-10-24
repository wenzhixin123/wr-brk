package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasCountryModel;
import com.sinotrans.gd.wlp.common.entity.BasOption;

public interface BasCountryManager extends BaseManager {

	BasCountryModel get(String id);

	List<BasCountryModel> getAll();

	List<BasCountryModel> findByExample(BasCountryModel example);

	BasCountryModel save(BasCountryModel model);

	List<BasCountryModel> saveAll(Collection<BasCountryModel> models);

	void remove(BasCountryModel model);

	void removeAll(Collection<BasCountryModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	List<BasOption> getOptionsByCode(String code, String officeCode,
			String language);

	String getNameByCode(String tableName, String codeValue, String language);

	String getPropertyByCode(String tableName, String codeValue,
			String propertyName);

}
