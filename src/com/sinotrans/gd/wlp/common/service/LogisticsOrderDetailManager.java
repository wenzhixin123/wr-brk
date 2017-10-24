package com.sinotrans.gd.wlp.common.service;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.basicdata.model.BasCustomerModel;
import com.sinotrans.gd.wlp.basicdata.model.BasProjectModel;
import com.sinotrans.gd.wlp.basicdata.model.BasUnitModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderDetailModel;
import com.sinotrans.gd.wlp.common.model.LogisticsOrderModel;
import com.sinotrans.gd.wlp.common.model.SubmitOrderModel;


/**
 * @author sky
 * 
 *         作业单操作（入库、移位、加工、出库）
 * 
 */
public interface LogisticsOrderDetailManager extends BaseManager {

	LogisticsOrderDetailModel get(String id);

	List<LogisticsOrderDetailModel> getAll();

	List<LogisticsOrderDetailModel> findByExample(LogisticsOrderDetailModel example);

	LogisticsOrderDetailModel save(LogisticsOrderDetailModel model);

	List<LogisticsOrderDetailModel> saveAll(Collection<LogisticsOrderDetailModel> models);

	void remove(LogisticsOrderDetailModel model);

	void removeAll(Collection<LogisticsOrderDetailModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	

}