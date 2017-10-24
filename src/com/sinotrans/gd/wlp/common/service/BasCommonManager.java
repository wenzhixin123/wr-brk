/**
 * 
 */
package com.sinotrans.gd.wlp.common.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;

import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.gd.wlp.common.entity.BasOption;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.common.entity.BasOption;

/**
 * @author sky.huang
 * 
 *         系统的公用类
 * 
 */
public interface BasCommonManager extends BaseManager {
	/**
	 * 查询Option选项
	 * 
	 * @param id4name
	 *            模糊搜索名称或者ID
	 * @param types
	 *            获取数据类型
	 * @param office_code
	 *            officecode
	 * @param language
	 *            语言
	 * @return
	 */
	List<BasOption> findOption(String id4name, String types,
			String office_code, String language);
	/**
	 * 生成系统编号
	 * 
	 * @param types
	 *            编号规则S、A、I、E、T、P、C
	 * @param transActionType
	 *            表单操作类型
	 * @param officeCode
	 *            officecode
	 * @return
	 */
	String generateNumber(String types, String transActionType,
			String officeCode);

	/**
	 * 获取数据库日期Y-M-D
	 * 
	 * @return
	 */
	String getDataBaseDateFor_Yyyy_Mm_Dd();

	/**
	 * 获取数据库日期Y-M-D H-M-S
	 * 
	 * @return
	 */
	String getDataBaseDateFor_YMD_HMS();

	/*
	 * 根据某个属性值获取整个行对象
	 * 
	 * @return json
	 */
	String getObjByProperty(Map<String, String> params);

	/*
	 * 效验集装箱号
	 * 
	 * @return boolean
	 */
	boolean ContainerNoCheckDigit(String ls_cntno);

	public List<? extends BaseModel> getRowListByProperty(
			Map<String, String> params);
	
	public SinotransPageJson importFileFactory(String path, String businessType,
			String businessFileType, String modelIds, String fileName,
			InputStream inputStream, String officeCode,
			String agentConsigneeCode, String agentConsigneeDesc,
			Map<String, Object> valueMap) throws Exception;

}
