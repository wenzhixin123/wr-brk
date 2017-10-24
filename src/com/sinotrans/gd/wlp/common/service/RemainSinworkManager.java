package com.sinotrans.gd.wlp.common.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.common.model.BarcodeModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderDetailModel;
import com.sinotrans.gd.wlp.common.model.RemainSinworkModel;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;

public interface RemainSinworkManager extends BaseManager {



	RemainSinworkModel get(String id);

	RemainSinworkModel save(RemainSinworkModel model);

	List<RemainSinworkModel> saveAll(Collection<RemainSinworkModel> models);

	void remove(RemainSinworkModel model);

	void removeAll(Collection<RemainSinworkModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	
}
