package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.basicdata.entity.BasBomEntity;
import com.sinotrans.gd.wlp.basicdata.model.BasBomModel;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;

public interface BasBomManager extends BaseManager {

	BasBomModel get(String id);

	List<BasBomModel> getAll();

	List<BasBomModel> findByExample(BasBomModel example);

	BasBomModel save(BasBomModel model);

	List<BasBomModel> saveAll(Collection<BasBomModel> models);

	void remove(BasBomModel model);

	void removeAll(Collection<BasBomModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	BasBomEntity saveBasBom(String jsonResult, String officeCode)
			throws Exception;

	boolean valitBomCode(String bomCode);

	boolean validateItemCode(String itemCode);

	String queryUuidByCode(String bomTypeCode);

	boolean deleteBomAndBomDetail(String uuid);

	BasBomEntity validateBomAndBomDateil(String jsonResult, String officeCode)
			throws Exception;

	BasBomEntity cancelBomAndBomDateil(String jsonResult, String officeCode)
			throws Exception;

	public SinotransDataGrid queryBom(PagingInfo pagingInfo, String officeCode);
}
