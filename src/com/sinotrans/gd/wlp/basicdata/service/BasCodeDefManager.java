package com.sinotrans.gd.wlp.basicdata.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.basicdata.model.BasCodeDefModel;
import com.sinotrans.gd.wlp.basicdata.query.BasCodeTypeByTypeCodeAndCodeValueQueryItem;
import com.sinotrans.gd.wlp.basicdata.query.BasReleaseExcelTemplateQueryCondition;
import com.sinotrans.gd.wlp.basicdata.web.CntrAdminPrefixWebVO;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;

public interface BasCodeDefManager extends BaseManager {

	BasCodeDefModel get(String id);

	List<BasCodeDefModel> getAll();

	List<BasCodeDefModel> findByExample(BasCodeDefModel example);

	BasCodeDefModel save(BasCodeDefModel model);

	List<BasCodeDefModel> saveAll(Collection<BasCodeDefModel> models);

	void remove(BasCodeDefModel model);

	void removeAll(Collection<BasCodeDefModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	String getDefaultCodeDefJSON(String codeTypeUuid);
	
	String getDisplayCodeByCodeValue(String codeValue);

	/**
	 * 根据字典类型Code 获取字典内容
	 * 
	 * @param typeCode
	 *            类型Code
	 * @param officeCode
	 *            仓库代码
	 * @param flagStatus
	 *            是否关联有效状态
	 * @return
	 */
	List<BasCodeDefModel> findByTypeCode(String typeCode, String officeCode,
			boolean flagStatus);

	/**
	 * 根据字典类型Code 获取字典内容(无需加OFFICE_CODE)
	 * 
	 * @param typeCode
	 *            类型Code
	 * @param flagStatus
	 *            是否关联有效状态
	 * @return
	 */
	List<BasCodeDefModel> findByTypeCode(String typeCode, boolean flagStatus);

	/**
	 * 根据特定codeType查出具有分页的codeDef
	 * 
	 * @param pagingInfo
	 * @param officeCode
	 * @return
	 */
	public SinotransDataGrid codeDefSpecial(String basCodeTypeUuid,
			String codeValue, String codeNumber, String modifiable,
			String status, PagingInfo pagingInfo, String officeCode);
	
	/**
	 * 根据特定codeType,codeValue 查出displayValue
	 * 
	 * @param codeType
	 * @param codeValue
	 * @return
	 */
	public String getDisplayValue(String codeType,String codeValue);
	/**
	 * 根据特定codeType,codeValue 查出codeValue
	 * 
	 * @param codeType
	 * @param displayValue
	 * @return
	 */
	public String getCodeValue(String codeType, String displayValue) ;
	
	/**
	 * 根据codeTypeUuid查询某些值是否在codeValue中
	 * @param codeTypeUuid
	 * @param codeValue
	 * @return
	 */
	List<BasCodeDefModel> queryCodeValueByCodeType(String codeTypeUuid,Object[] codeValue);
	
	boolean checkCodeDefType(String codeType,String codeDef);
	
	public  List<BasCodeDefModel> saveAllCntrAdminPrefix(List<CntrAdminPrefixWebVO> webVoList);
	
	/**
	 * 添加新导入Excel模板
	 * @param template
	 * @return
	 */
	public BasCodeDefModel saveExcelTemplate(BasReleaseExcelTemplateQueryCondition template);
	
	/**
	 * 更新导入Excel模板
	 * @param template
	 * @return
	 */
	public BasCodeDefModel updateExcelTemplate(BasReleaseExcelTemplateQueryCondition template);
	
	/**
	 * 删除导入Excel模板
	 * @param template
	 * @return
	 */
	public void removeExcelTemplateBy(String id);
	
	/**
	 * 
	 * @param shipperCode
	 * @return
	 */
	public HashMap<String, Integer> getOffsetsByShipperCode(String shipperCode);
	
	public List<String> queryCodeValueByInput(String typeCode , String inputValue);
	
	/**
	 * 根据特定codeType,codeValue 查出BasCodeDefModel
	 * 
	 * @param codeType
	 * @param codeValue
	 * @return
	 */
	public BasCodeDefModel getCodeDefModelByCodeTypeValue(String codeType, String codeValue,String status);
	
	public BasCodeDefModel getCodeValueByCodeType(String codeType,String displayEn);

	BasCodeDefModel getPortAreaCodeByInput(String str);
	/**
	 * 根据字典类型，字段值查找字典相关信息
	 * 
	 * @param typeCode
	 * @param codeValue
	 * @param officeCode
	 * @return
	 */
	BasCodeTypeByTypeCodeAndCodeValueQueryItem findByTypeCodeAndCodeValue(String typeCode, String codeValue, String officeCode);
	
}
