package com.sinotrans.gd.wlp.system.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.system.model.TemplateTypeModel;

public interface TemplateTypeManager extends BaseManager {

	TemplateTypeModel get(String id);

	List<TemplateTypeModel> getAll();

	List<TemplateTypeModel> findByExample(TemplateTypeModel example);

	TemplateTypeModel save(TemplateTypeModel model);

	List<TemplateTypeModel> saveAll(Collection<TemplateTypeModel> models);

	void remove(TemplateTypeModel model);

	void removeAll(Collection<TemplateTypeModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);
	
	boolean getYanZhentypeCode(String Code);

	TemplateTypeModel saveyanzhen(TemplateTypeModel model);

	boolean removeType(String pkId, long recVer);
	

}
