<%@ page language="java" import="java.util.*"  pageEncoding="utf-8"%><%@page
 import="com.sinotrans.gd.wlp.system.service.SysOfficeManager,
com.sinotrans.framework.core.util.ContextUtils,
com.sinotrans.gd.wlp.system.service.PrintReportTemplateManager,
com.sinotrans.gd.wlp.common.web.RcUtil,
com.sinotrans.gd.wlp.system.entity.PrintReportTemplateEntity,
com.superlinksoft.jasperreport.service.*,
java.util.ArrayList,
com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity,
com.sinotrans.gd.wlp.util.Encode64,
org.json.*,
com.superlink.extend.util.DWRForIreport"%><%
	String path = request.getContextPath();
	String systemPath = "http://"+request.getServerName() + ":" + request.getServerPort() + path;
	String templateType = request.getParameter("templateType"); //模板类型
	String reportId = request.getParameter("reportId"); //报表ID
	String controlParam = request.getParameter("controlParam"); //控制参数(new为新建，edit为编缉,print为预览,printNow为直接打印)
	String modelId = request.getParameter("modelId");
	HashMap<String, Object> parameters = SessionContextUserEntity.currentUser().getPrintSessionEntity().getParameters();
	String reportName = "";
	
	byte[] pdfBytes = null;
	
	PrintReportTemplateManager printReportTemplateManager = ContextUtils.getBeanOfType(PrintReportTemplateManager.class);
	
	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddhhmmss");
	
	reportName = templateType + sdf.format(new Date());
	
	PrintReportTemplateEntity condition = new PrintReportTemplateEntity();
	
	condition.setTemplateType(templateType);
	condition.setReportTemplateUuid(reportId);
	
	parameters.put("SYSTEM_PATH", systemPath);
	
	try {
		//todo should able to pass parameter
		pdfBytes = printReportTemplateManager.getPdfBytes(condition, parameters, modelId, controlParam);
	
	} catch(Exception ex) {
		String lmsg = ex.getLocalizedMessage();
		String msg = ex.getMessage();
		response.setHeader("Content-Type", "text/html; charset=utf-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "0");
		out.write("LocalizedMessage:" + lmsg + "\n");
		out.write("errorMessage:" + msg + "\n");
		out.flush();
		return;
	}finally{
		SessionContextUserEntity.currentUser().getPrintSessionEntity().setParameters(new HashMap<String, Object>());
	}
	
	response.setContentType("application/pdf");  
	response.setContentLength(pdfBytes.length);  
	response.setHeader("Content-disposition", "inline; filename=" + reportName + ".pdf");  
	response.setHeader("Cache-Control", "no-cache");  
	response.setDateHeader("Expires", 0);  
	response.setHeader("Pragma", "no-cache");  
	response.setHeader("Cache-control", "private");  
	
	out.clear();
	out = pageContext.pushBody();
	try {
		ServletOutputStream outputStream = response.getOutputStream();  
		outputStream.write(pdfBytes); // pdfBytes为pdf文件二进制数据流
		outputStream.flush();  
		outputStream.close();
	} catch(java.io.IOException e) {
		
	}
%>