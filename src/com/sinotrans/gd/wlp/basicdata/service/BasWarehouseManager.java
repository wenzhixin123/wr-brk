package com.sinotrans.gd.wlp.basicdata.service;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.entity.EasyUiTree;
import com.sinotrans.gd.wlp.basicdata.model.BasWarehouseModel;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BasWarehouseManager extends BaseManager {

	BasWarehouseModel get(String id);

	List<BasWarehouseModel> getAll();

	List<BasWarehouseModel> findByExample(BasWarehouseModel example);

	BasWarehouseModel save(BasWarehouseModel model);

	List<BasWarehouseModel> saveAll(Collection<BasWarehouseModel> models);

	void remove(BasWarehouseModel model);

	void removeAll(Collection<BasWarehouseModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	public List<Map<String, String>> findAllToCombobox(String office,
                                                       String userId, String realPath) throws Exception;

	public List<Map<String, String>> createSvgFile(List<String> bolbUuid,
                                                   String officCode, String realPath, List<Map<String, String>> result)
			throws Exception;

	public List<Map<String, String>> findLotByOutbound(String warehouseUuid,
                                                       String officeCode, String logisticsOrderNo, String batchNo,
                                                       String projectCode, String lotCode, String itemCode,
                                                       String marksNumber, String model, String packageNo, String barcode,
                                                       String goodsDesc, String spec, String goodsKind, String billNo,
                                                       String orderDateStart, String orderDateEnd);

	public 	List<EasyUiTree> getTreeData();

	SinotransPageJson getLocAeaName(String officeCode, String basWarehouseUuid);
}
