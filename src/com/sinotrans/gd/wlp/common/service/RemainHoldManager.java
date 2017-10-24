package com.sinotrans.gd.wlp.common.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.common.model.BarcodeModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderDetailModel;
import com.sinotrans.gd.wlp.common.model.RemainHoldModel;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;

public interface RemainHoldManager extends BaseManager {



	RemainHoldModel get(String id);

	RemainHoldModel save(RemainHoldModel model);

	List<RemainHoldModel> saveAll(Collection<RemainHoldModel> models);

	void remove(RemainHoldModel model);

	void removeAll(Collection<RemainHoldModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	
}
