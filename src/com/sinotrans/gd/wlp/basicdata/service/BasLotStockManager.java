package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.entity.BasLotStockEntity;
import com.sinotrans.gd.wlp.basicdata.model.BasLotStockModel;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;

public interface BasLotStockManager extends BaseManager {

	BasLotStockModel get(String id);

	List<BasLotStockModel> getAll();

	List<BasLotStockModel> findByExample(BasLotStockModel example);

	BasLotStockModel save(BasLotStockModel model);

	List<BasLotStockModel> saveAll(Collection<BasLotStockModel> models);

	void remove(BasLotStockModel model);

	void removeAll(Collection<BasLotStockModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	BasLotStockModel getBasLotStockByCondition(String officeCode);

	String getTreeJson(Map<String, String> parameters);

	String getTreeJsonByFK(Map<String, String> parameters);

	String getLocAreaComboboxData(String basWarehouseUuid);

	SinotransPageJson saveBasLotStock(String params, String jsonResult, String officeCode);

	public SinotransPageJson defaultLotStock(String lotStockUuid,
			String basLocAreaUuid);

	public boolean validateLotStock(String lotCode, String officeCode);

	SinotransPageJson saveBasLocStock(List<BasLotStockEntity> blsEntity,
			String type);

	SinotransPageJson setDefaultValue(BasLotStockEntity blsEntity);
	SinotransPageJson updateDefaultLotStock(String officeCode,String locAreaUuid);
}
