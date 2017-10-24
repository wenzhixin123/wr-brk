package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeTypeModel;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;

public interface BasCodeTypeManager extends BaseManager {

	BasCodeTypeModel get(String id);

	List<BasCodeTypeModel> getAll();

	List<BasCodeTypeModel> findByExample(BasCodeTypeModel example);

	BasCodeTypeModel save(BasCodeTypeModel model);

	List<BasCodeTypeModel> saveAll(Collection<BasCodeTypeModel> models);

	void remove(BasCodeTypeModel model);

	void removeAll(Collection<BasCodeTypeModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	/**
	 * 通过字典代码查找类型对象
	 * 
	 * @param codeType
	 * @return
	 */
	BasCodeTypeModel findObject(String codeType, String officeCode);

	/**
	 * 按照字典类型具有分页的对象
	 * @param 
	 * @param officeCode
	 * @return
	 */
	SinotransDataGrid codeTypeSpecial(PagingInfo pagingInfo, String officeCode);
}
