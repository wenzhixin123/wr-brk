package com.sinotrans.gd.wlp.system.service.impl;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.sinotrans.framework.core.exception.ApplicationException;
import com.sinotrans.framework.core.service.impl.BaseManagerImpl;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.gd.wlp.basicdata.service.BasPanelManager;
import com.sinotrans.gd.wlp.basicdata.service.BasProvinceManager;
import com.sinotrans.gd.wlp.common.service.BasCommonManager;
import com.sinotrans.gd.wlp.common.service.SQLQueryManager;
import com.sinotrans.gd.wlp.common.web.RcUtil;
import com.sinotrans.gd.wlp.common.web.SinotransDataGrid;
import com.sinotrans.gd.wlp.common.web.SinotransPageJson;
import com.sinotrans.gd.wlp.report.datasource.BaseDataSource;
import com.sinotrans.gd.wlp.system.entity.PrintBaseParamEntity;
import com.sinotrans.gd.wlp.system.entity.PrintReportTemplateEntity;
import com.sinotrans.gd.wlp.system.entity.PrintTemplateForm;
import com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity;
import com.sinotrans.gd.wlp.system.model.PrintReportTemplateModel;
import com.sinotrans.gd.wlp.system.query.PrintReportTemplateQueryCondition;
import com.sinotrans.gd.wlp.system.query.PrintReportTemplateQueryItem;
import com.sinotrans.gd.wlp.system.service.PrintReportTemplateManager;
import com.sinotrans.gd.wlp.util.CommonUtil;
import com.sinotrans.gd.wlp.util.EdiXmlConverter;
import com.sinotrans.gd.wlp.util.ListUtil;
import com.sinotrans.gd.wlp.util.StringUtil;
import com.superlink.extend.util.DWRForIreport;
import com.superlinksoft.jasperreport.service.IreportPrintService;
import com.superlinksoft.jasperreport.utils.BeanToJrxmlUtil;

@Service
public class PrintReportTemplateManagerImpl extends BaseManagerImpl implements
		PrintReportTemplateManager {

	private Logger log = Logger.getLogger(PrintReportTemplateManagerImpl.class);
	@Autowired
	private BasCommonManager basCommonManager;
	@Autowired
	private SQLQueryManager sqlQueryManager;
	@Autowired
	private BasPanelManager basPanelManager;
	@Autowired
	private BasProvinceManager basProvinceManager;

	
	public PrintReportTemplateModel get(String id) {
		return this.dao.get(PrintReportTemplateModel.class, id);
	}

	public List<PrintReportTemplateModel> getAll() {
		return this.dao.getAll(PrintReportTemplateModel.class);
	}

	public List<PrintReportTemplateModel> findByExample(
			PrintReportTemplateModel example) {
		return this.dao.findByExample(example);
	}
	
	@Override
	public List<String> findTemplateUuidByModel(
			PrintReportTemplateEntity example) {
		List<String> uuidList = new ArrayList<String>();
		example.setStatus(CommonUtil.Active);
		List<PrintReportTemplateEntity> modelList = this.dao.findByExample(example);
		for(PrintReportTemplateModel model : modelList){
			if(StringUtils.isNotBlank(example.getExtraCondition1())
					&&example.getExtraCondition1().equals(model.getControlWord().substring(0,1))
					||StringUtils.isBlank(example.getExtraCondition1())){
				uuidList.add(model.getReportTemplateUuid());
			}
		}
		return uuidList;
	}

	public PrintReportTemplateModel save(PrintReportTemplateModel model) {
		return this.dao.save(model);
	}

	public PrintReportTemplateModel saveReportTemplate(
			PrintReportTemplateModel model, String filepath,
			InputStream inputStream, String officecode, String operateFlag) {

		if (!RcUtil.isEmpty(filepath)) {
			// 获取模版内容
			String strTemplateContent = this.readFr3File(inputStream);
			if (!RcUtil.isEmpty(model)) {
				model.setTemplateContent(strTemplateContent.getBytes());
			}
		}

		if (CommonUtil.ROW_STATE_ADDED.equals(operateFlag)) {
			model.setTemplateCode(basCommonManager.generateNumber(
					CommonUtil.Number_Report_Template, "", officecode));
			model.setOfficeCode(officecode);
			return this.dao.save(model);
		} else if (CommonUtil.ROW_STATE_MODIFIED.equals(operateFlag)) {
			List<PrintReportTemplateModel> prtmList = new ArrayList<PrintReportTemplateModel>();
			model.setRowState(operateFlag);
			prtmList.add(model);
			this.dao.saveAll(prtmList);
			return model;
		}
		return null;
	}

	/**
	 * (接口实现方法) 只保存模板文件的方法
	 * <p>Title: saveOnlyTemplateFile</p> 
	 * <p>Description: </p> 
	 * @param officeCode
	 * @param form
	 * @return
	 * @throws Exception 
	 * @see com.sinotrans.gd.wlp.system.service.PrintReportTemplateManager#saveOnlyTemplateFile(java.lang.String, com.sinotrans.gd.wlp.system.entity.PrintTemplateForm)
	 */
	public SinotransPageJson  saveOnlyTemplateFile(String officeCode,
			PrintTemplateForm form) throws Exception {
		SinotransPageJson spj = new SinotransPageJson(true);
		PrintReportTemplateModel model = new PrintReportTemplateModel();

		model.setReportTemplateUuid(form.getReportTemplateUuid());
		if(form.getTemplate().getFileData()!=null){
			model.setTemplateContent(form.getTemplate().getFileData()); // 文件
		}
		
		//由jrxml 文件转jasper文件,并保存到数据库
		byte[] compileTemplate = IreportPrintService.conventToJasper(form.getTemplate().getFileData());
		model.setCompileTemplate(compileTemplate);
		
		PrintReportTemplateModel reModel = this.save(model);

		model.setReportTemplateUuid(reModel.getReportTemplateUuid());
		spj.setObject(model);
		
		return spj;
	}
	
	// 保存打印模块（新的）
	public SinotransPageJson savePrintTemplate(String officeCode,
			PrintTemplateForm form) throws Exception {
		SinotransPageJson spj = new SinotransPageJson(true);

		PrintReportTemplateModel model = new PrintReportTemplateModel();

		model.setReportTemplateUuid(form.getReportTemplateUuid());
		if(CommonUtil.ROW_STATE_MODIFIED.equals(form.getRowState())){
			model.setTemplateCode(form.getTemplateCode());
		}else if(CommonUtil.ROW_STATE_ADDED.equals(form.getRowState())){
			model.setTemplateCode(basCommonManager.generateNumber(
					CommonUtil.Number_Report_Template, "", officeCode));
		}
		model.setTemplateName(form.getTemplateName());
		model.setFileName(form.getFileName());
		model.setStatus(form.getStatus());
		model.setTemplateType(form.getTemplateType());
		model.setFileVersion(form.getFileVersion());
		model.setTemplateNameEn(form.getTemplateNameEn());
		model.setRemark(form.getRemark());
		model.setCustomerCode(form.getCustomerCode());
		model.setOfficeCode(officeCode);
		
		String condition = StringUtils.isBlank(form.getPrintExtraCondition()) ? "0" :form.getPrintExtraCondition();
		String controlWord = StringUtils.isBlank(form.getControlWord()) ? CommonUtil.Default_Control_Word.substring(1) : form.getControlWord().substring(1);
		model.setControlWord(condition+controlWord);
		model.setTemplateCategory(form.getTemplateCategory());

		if (!RcUtil.isEmpty(form.getReportTemplateUuid())
				&& RcUtil.isEmpty(form.getTemplate().getFileName())) {
			PrintReportTemplateModel tempModel = this.get(form
					.getReportTemplateUuid());
			if (!RcUtil.isEmpty(tempModel)) {
				model.setTemplateContent(tempModel.getTemplateContent());
			}
		} else {
			byte[] fileData = form.getTemplate().getFileData();
			if(fileData!=null && fileData.length>0){
				model.setTemplateContent(form.getTemplate().getFileData()); // 文件
			}else{
				//如果是IREPORT
				if("IREPORT".equals(form.getTemplateCategory())){
					String jrxml = getJrxml(null, form.getTemplateType(), CommonUtil.REPORT_PRINT_CONTROL_TYPE_NEW);
					model.setTemplateContent(jrxml.getBytes("utf-8"));
				}
			}
		}
		if(model.getTemplateContent().length > 0){
			//由jrxml 文件转jasper文件,并保存到数据库
			byte[] compileTemplate = IreportPrintService.conventToJasper(model.getTemplateContent());
			model.setCompileTemplate(compileTemplate);
		}

		PrintReportTemplateModel reModel = this.save(model);

		model.setReportTemplateUuid(reModel.getReportTemplateUuid());
		spj.setObject(model);

		return spj;
	}
	
	public void saveCompileTomplateJasperFile(PrintReportTemplateModel model){
		if(!RcUtil.isEmpty(model.getReportTemplateUuid())
				&& model.getCompileTemplate().length > 0){
			this.save(model);
		}
	}

	public List<PrintReportTemplateModel> saveAll(
			Collection<PrintReportTemplateModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(PrintReportTemplateModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<PrintReportTemplateModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(PrintReportTemplateModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(PrintReportTemplateModel.class, ids);
	}

	/*
	 * 读取模板文件
	 */
	public String readFr3File(InputStream inputStream) {
		String tempString = null;
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(inputStream,
					"utf-8"));
			// 一次读入一行，直到读入null为文件结束
			int index = 0;
			while ((tempString = reader.readLine()) != null) {
				if (index >= 4
						&& tempString.indexOf("-----------------------------") != 0) {
					sb.append(tempString).append("\n");
				}
				index++;
			}
			tempString = sb.toString();
			reader.close();
		} catch (IOException e) {
			log.error(e);
			tempString = "";
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					tempString = "";
				}
			}
		}
		if (tempString != null && tempString.length() > 0) {
			tempString = tempString.substring(0, tempString.length() - 1);
		}
		return tempString;
	}

	private String BASE64Encoder(String string) {
		return new sun.misc.BASE64Encoder().encode(string.getBytes());
	}

	@Override
	public PrintReportTemplateEntity getReportTemplate(String reportId)
			throws IllegalAccessException, InvocationTargetException {
		PrintReportTemplateEntity prte = new PrintReportTemplateEntity();
		if (!RcUtil.isEmpty(reportId)) {
			PrintReportTemplateModel prtm = get(reportId);
			BeanUtils.copyProperties(prte, prtm);
		}
		return prte;
	}

	private String getFromBASE64(String s) {
		if (RcUtil.isEmpty(s))
			return "";
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return "";
		}
	}

	public String getProjectName(String projectCode, String officeCode) {
		if (StringUtil.isNull(projectCode))
			return "";
		if (StringUtil.isNull(officeCode))
			return "";
		String inSQL = "select bp.project_name  " + " from bas_project bp "
				+ " where bp.project_code='" + projectCode + ""
				+ " ' and bp.office_code='" + officeCode + "' ";

		return sqlQueryManager.getColumnData(inSQL, "", "");
	}
	
	public SinotransDataGrid selectPrintReportTemplate(String templateName,
			String fileName, String templateType, PagingInfo pagingInfo,
			String officecode) {
		StringBuffer sb = new StringBuffer();
		List<PrintReportTemplateQueryItem> resultList = new ArrayList<PrintReportTemplateQueryItem>();
		SinotransDataGrid adb = new SinotransDataGrid();
		PrintReportTemplateQueryCondition condition = new PrintReportTemplateQueryCondition();
		condition.setFileName(fileName);
		condition.setTemplateName(templateName);
		condition.setTemplateType(templateType);
		resultList = this.dao.query(condition,
				PrintReportTemplateQueryItem.class, sb.toString(), null, null,
				pagingInfo);
		if (resultList.size() > 0) {
			adb.setPage(pagingInfo.getCurrentPage());
			adb.setTotal(pagingInfo.getTotalRows());
			adb.setRows(resultList);
		}
		return adb;
	}
	//------
	private String toBASE64Encoder(String string) {
		return new BASE64Encoder().encode(string.getBytes());
	}

private EdiXmlConverter ediXml;
	
	private EdiXmlConverter getEdiXmlConverter(){
		if( ediXml == null){
			ediXml = new EdiXmlConverter();
			ediXml.setElementNameUppered(false);
			ediXml.setConvertElementName(false); 
		}
		return ediXml;
	}
	
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
	public String  getJrxml(String modelId, String templateType, String controlParam) {
		
		Object javaBean = this.loadJavaBean(modelId, templateType, null, controlParam);
		
		String jrxml = BeanToJrxmlUtil.getJrxmlFromBean(javaBean);
		
		return jrxml;
		
	}
	
	public byte[]  getZipIreport(String modelId, String templateType, String controlParam) {
		return null;
	}
	
	/**
	 * 【描述：】
	 * @Title: loadJavaBean
	 * @Author: jim.chen
	 * @Time: 2013年12月18日 上午10:01:59
	 * @param modelId
	 * @param dataSourceName
	 * @param controlParam
	 * @return Object
	 */
	public Object loadJavaBean(String modelId, String templateType, Map<String, Object> parameters, String controlParam) {
		log.debug("modelId:" + modelId + " templateType:" + templateType + " controlParam:" + controlParam);
		Object returnObj = null;
		BaseDataSource<?> ds = null;
				
		/*if (CommonUtil.REPORT_PRINT_TYPE_DCSBILLEDIT.equals(templateType)) {// 出口提单配箱打印
			ds = new DcsBillPrintDS();
		} else if (CommonUtil.REPORT_PRINT_TYPE_DCSMANIFEST.equals(templateType)) {// 配舱打印
			ds = new DcsManifestPrintDS();
		} else if (CommonUtil.REPORT_PRINT_TYPE_RELEASE.equals(templateType)) {// 放柜纸打印
			ds = new ReleasePrintDS();
		} else if (CommonUtil.REPORT_PRINT_TYPE_DCSORDERPRINT.equals(templateType)) {// 作业单总单打印
			ds = new DcsLogisticsOrderEntityDS();
		} else if (CommonUtil.REPORT_PRINT_TYPE_DCSEXPSPLITORDERPRINT.equals(templateType)) {// 作业单分拆单打印
			ds = new DcsLogisticsOrderEntityDS();
		} else if (CommonUtil.REPORT_PRINT_TYPE_BATCH_DCSEXPSPLITORDERPRINT.equals(templateType)) {// 作业单分拆单批量打印
			ds = new DcsLogisticsOrderListEntityDS();
		} else if (CommonUtil.REPORT_PRINT_TYPE_LOADMANIFEST.equals(templateType)) {// 装载舱单打印
			ds = new DcsLoadManfestDS();
		} */
		if ((CommonUtil.REPORT_PRINT_CONTROL_TYPE_PRINTNOW
				.equals(controlParam) || CommonUtil.REPORT_PRINT_CONTROL_TYPE_PRINT
				.equals(controlParam))
				&& !RcUtil.isEmpty(modelId)) {// 打印&打印预览
			returnObj = ds.getDataSource(modelId, parameters);
		}else{
			returnObj = ds.getEmptyDataSource();
		}
		return returnObj;
	}
	
	@Override
	public String loadXmlData(String modelId, String templateType,
			String controlParam)  {
		String data_String = "";
		
		Object  obj = this.loadJavaBean(modelId, templateType, null, controlParam);

		try {
			data_String = toBASE64Encoder(getEdiXmlConverter()
					.buildXmlString(obj));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data_String;
	}

	@Override
	public byte[] getPdfBytes(PrintReportTemplateEntity condition, HashMap<String, Object> parameters, String modelId, String controlParam) throws Exception {

		Object tempBean = loadJavaBean(modelId, condition.getTemplateType(), parameters, controlParam);
		byte[] templateContent = null;//报表模板文件
		byte[] pdfBytes = null;

		PrintReportTemplateEntity printReportTemplate = null;
		
		//如果没有指定模板id, 默认使用第一个模板
		if(!RcUtil.isEmpty(condition.getReportTemplateUuid())){
			printReportTemplate = getReportTemplate(condition.getReportTemplateUuid());//加载模板内容
			
		} else {
			List<PrintReportTemplateModel> resultList = findByExample(condition);
			
			if(ListUtil.isEmpty(resultList)) {
				throw new ApplicationException("不存在模板类型为" + condition.getTemplateType() + "的模板");
			} else {
				for(PrintReportTemplateModel templateModel:resultList){
					if(StringUtils.isNotBlank(condition.getExtraCondition1())
							&&condition.getExtraCondition1().equals(templateModel.getControlWord().substring(0,1))
							||StringUtils.isBlank(condition.getExtraCondition1())){
						printReportTemplate = new PrintReportTemplateEntity();
						BeanUtils.copyProperties(printReportTemplate, templateModel);
					}
				}
			}
			
		}
		
		if(printReportTemplate.getCompileTemplate().length > 0){
			templateContent = printReportTemplate.getCompileTemplate();
		} else {
			//由jrxml 文件转jasper文件,并保存到数据库
			
			try {
				templateContent = IreportPrintService.conventToJasper(printReportTemplate.getTemplateContent());
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
			}
			printReportTemplate.setCompileTemplate(templateContent);
			saveCompileTomplateJasperFile(printReportTemplate);
		}

		if(parameters == null) {
			parameters = new HashMap<String, Object>();
		}
		
		parameters.put("userInfo", SessionContextUserEntity.currentUser());
		DWRForIreport DWR = new DWRForIreport();
		parameters.put("DWR", DWR);
		
		if(CommonUtil.REPORT_PRINT_CONTROL_TYPE_PRINTNOW.equals(controlParam)){
			pdfBytes = IreportPrintService.getPdfAndPrintAsBytes(templateContent, tempBean, parameters);
		}else{
			pdfBytes = IreportPrintService.getPdfBytes(templateContent, tempBean, parameters);
		}
		
		return pdfBytes;
	}
	@Override
	public void insertSessionParameter(List<PrintBaseParamEntity> parameters) {
		if(parameters!=null&&parameters.size()>0){
			for(PrintBaseParamEntity param:parameters){
				if(param!=null){
				   if(StringUtils.isNotBlank(param.getKey())&&StringUtils.isNotBlank(param.getValue())){
				      SessionContextUserEntity.currentUser().getPrintSessionEntity().getParameters().put(param.getKey(), param.getValue());
				   }else if(StringUtils.isNotBlank(param.getKey())&&param.getValueArray()!=null&&param.getValueArray().size()>0){
					   SessionContextUserEntity.currentUser().getPrintSessionEntity().getParameters().put(param.getKey(), param.getValueArray());
				   }
				}
			}
		}
	}
}
