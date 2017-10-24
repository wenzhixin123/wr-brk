<%@ page language="java" pageEncoding="UTF-8"%><%@page import="com.sinotrans.gd.wlp.util.CommonUtil,
com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity,
java.io.*,
com.sinotrans.gd.wlp.system.service.PrintReportTemplateManager,
com.sinotrans.framework.core.util.ContextUtils,
com.superlinksoft.jasperreport.service.JavaBeanDescribeService,
com.sinotrans.gd.wlp.common.web.RcUtil"
%><%
	String path = request.getContextPath();
	SessionContextUserEntity scue = SessionContextUserEntity.currentUser();

	
	String templateType =  request.getParameter("templateType");
	String controlParam = CommonUtil.REPORT_PRINT_CONTROL_TYPE_NEW;
	String description = "";
	String modelId = null;
	
	PrintReportTemplateManager mag = ContextUtils.getBeanOfType(PrintReportTemplateManager.class);

	Object bean = mag.loadJavaBean(modelId, templateType, null, controlParam);
	//description +=JavaBeanDescribeService.getFieldListContentOfClass(bean.getClass());
	
	description +=JavaBeanDescribeService.getFieldListContentOfBean(bean);
	
	  
	String flieName = bean.getClass().getName();
	response.setHeader("Content-Type","text/js; charset=utf-8");
	response.setHeader("Content-Disposition","attachment; filename="+flieName+".properties");
	response.setHeader("Content-Length", String.valueOf(description.length()));
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	System.out.print(request.getContextPath()+"\n");
	System.out.print(request.getPathInfo() +"\n");
	System.out.print(request.getRequestURL() +"\n");
	System.out.print(request.getRequestURI() +"\n");
	System.out.print(request.getServletPath() +"\n");
     
	PrintWriter outInput = response.getWriter();
	outInput.print(description);
	outInput.flush();
	outInput.close();
	%>