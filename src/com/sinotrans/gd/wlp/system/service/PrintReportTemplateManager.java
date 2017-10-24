package com.sinotrans.gd.wlp.system.service;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinotrans.framework.core.service.BaseManager;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.system.entity.PrintBaseParamEntity;
import com.sinotrans.gd.wlp.system.entity.PrintReportTemplateEntity;
import com.sinotrans.gd.wlp.system.entity.PrintTemplateForm;
import com.sinotrans.gd.wlp.system.model.PrintReportTemplateModel;

public interface PrintReportTemplateManager extends BaseManager {

	PrintReportTemplateModel get(String id);

	List<PrintReportTemplateModel> getAll();

	List<PrintReportTemplateModel> findByExample(
			PrintReportTemplateModel example);
	
	List<String> findTemplateUuidByModel(
			PrintReportTemplateEntity example);

	PrintReportTemplateModel save(PrintReportTemplateModel model);

	PrintReportTemplateModel saveReportTemplate(PrintReportTemplateModel model,
			String filepath, InputStream inputStream, String officecode,
			String operateFlag);

	List<PrintReportTemplateModel> saveAll(
			Collection<PrintReportTemplateModel> models);

	void remove(PrintReportTemplateModel model);

	void removeAll(Collection<PrintReportTemplateModel> models);

	void removeByPk(String id);

	void removeAllByPk(Collection<String> ids);

	String readFr3File(InputStream inputStream);

	/**
	 * 加载数据对象并转换成XML
	 * 
	 * @param modelId
	 *            数据源ID
	 * @param templateType
	 *            模板类型
	 * @param controlParam
	 *            控制类型
	 * @return
	 */

	/**
	 * 获取报表模板内容方法
	 * 
	 * @param reportId
	 * @return
	 */
	PrintReportTemplateEntity getReportTemplate(String reportId)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 【描述：】只保存模板文件
	 * @Title: saveOnlyTemplateFile 
	 * @Author: Administrator
	 * @Time: 2013年12月12日 下午3:59:05
	 * @param officeCode
	 * @param form
	 * @return
	 * @throws Exception SinotransPageJson
	 */
	 SinotransPageJson saveOnlyTemplateFile(String officeCode,
			PrintTemplateForm form) throws Exception;
	
	public SinotransPageJson savePrintTemplate(String officeCode,
			PrintTemplateForm form) throws Exception;

	/*
	 * 
	 * 查询模板信息
	 */
	SinotransDataGrid selectPrintReportTemplate(String templateName,
			String fileName, String templateType, PagingInfo pagingInfo,
			String officecode);
	
	/**
	 * 【描述：】获取Jrxml内容
	 *@Title: getJrxml 
	 * @Author: Administrator
	 * @Time: 2013年11月29日 下午4:02:51
	 * @param modelId
	 * @param templateType
	 * @param controlParam 控制字 默认为null
	 * @return String
	 */
	 String  getJrxml(String modelId, String templateType, String controlParam) ;
	 
	 byte[]  getZipIreport(String modelId, String templateType, String controlParam) ;
	 /**
	  * 【描述：】加载JavaBean
	  *@Title: loadJavaBean 
	  * @Author: Administrator
	  * @Time: 2013年11月29日 下午4:05:22
	  * @param modelId
	  * @param templateType
	  * @param controlParam
	  * @return Object
	  */
	Object  loadJavaBean(String modelId, String templateType, Map<String, Object> parameters, String controlParam) ;
			
	String loadXmlData(String modelId, String templateType,
			String controlParam) ;
	
	public void saveCompileTomplateJasperFile(PrintReportTemplateModel model);
	
	/**
	 * 当reportId为空时, 自动选择第一个模板进行打印
	 * @param templateType
	 * @param reportId
	 * @param modelId
	 * @param controlParam
	 * @return
	 * @throws Exception
	 */
	public byte[] getPdfBytes(PrintReportTemplateEntity condition, HashMap<String, Object> parameters, String modelId, String controlParam) throws Exception;

	
	public void insertSessionParameter(List<PrintBaseParamEntity> parameters);
}
