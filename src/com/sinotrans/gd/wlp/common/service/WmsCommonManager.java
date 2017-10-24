package com.sinotrans.gd.wlp.common.service;

import java.util.Date;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.basicdata.model.BasCustomerModel;
import com.sinotrans.gd.wlp.common.model.LocPlanConfigModel;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.common.model.LocationTaskModel;

public interface WmsCommonManager extends BaseManager {



	/**
	 * 单号生成规则
	 * @param transactionType
	 * @param officeCode
	 * @return
	 */
	public String generateNumberByRule(String transactionType, String officeCode);

	/**
	 * 获取默认类型 （例如：出入库作业项目类型）
	 * @param typeCode
	 * @param status
	 * @param officeCode
	 * @return
	 */
	public SinotransPageJson systemCodeCodeDef(String typeCode, String status,String officeCode);
	/**
	 * 获取系统默认客户
	 * 
	 * @return
	 * @throws Exception
	 */
	public BasCustomerModel getDefaultCustomer() throws ApplicationException;
	
	/**
	 * 获取数据库日期Y-M-D H-M-S
	 * 
	 * @return
	 */
	Date getDataBaseDateFor_YMD_HMS_Type4Date();

	/**
	 * 获取数据库日期Y-M-D H-M-S
	 * @return
	 */
	String getDataBaseDateFor_YMD_HMS();
	
	/**
	 * 获取默认作业类型
	 * 
	 * @param type
	 * @param officeCode
	 * @return
	 */
	String getDefaultDeliveyType(String type, String officeCode);

	/**
	 *  获取数据库日期Y-M-D
	 * @return
	 */
	String getDataBaseDateFor_Yyyy_Mm_Dd();

	/**
	 * 获取默认出库策略
	 * @param configType
	 * @param officeCode
	 * @return
	 * @throws ApplicationException
	 */
	LocPlanConfigModel getDefaultPlanConfig(String configType, String officeCode)throws ApplicationException;
	
	/**
	 * 赋值lt基本字段
	 * @param ltModel
	 * @return
	 * @throws Exception
	 */
	public LocationTaskModel commonSaveLocationTask(LocationTaskModel ltModel) throws Exception;
	
	/**
	 * 框架控制的字段值置空
	 */
	public void frameFieldToNull(BaseModel model);
}
