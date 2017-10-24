<%@ page language="java" pageEncoding="UTF-8"%><%@page import="com.sinotrans.gd.wlp.util.CommonUtil,
com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity,
java.io.*,
com.sinotrans.gd.wlp.system.service.PrintReportTemplateManager,
com.sinotrans.framework.core.util.ContextUtils,
com.sinotrans.gd.wlp.common.web.RcUtil"
%><%
	String path = request.getContextPath();
	SessionContextUserEntity scue = SessionContextUserEntity.currentUser();

	String reportId = request.getParameter("reportId");
	String templateType = request.getParameter("templateType");
	String controlParam = request.getParameter("controlParam");
	String jrxml = "";
	
	
	PrintReportTemplateManager mag = ContextUtils.getBeanOfType(PrintReportTemplateManager.class);
	//PrintReportTemplateEntity entiy = mag.getReportTemplate(reportId);
	if(CommonUtil.REPORT_PRINT_CONTROL_TYPE_NEW.equals(controlParam)){
		jrxml = mag.getJrxml(null, templateType, controlParam);
	} else {
		if(!RcUtil.isEmpty(reportId)){
			jrxml = mag.getReportTemplate(reportId).getTemplateString();
		}
	}

	response.setHeader("Content-Type","text/xml; charset=utf-8");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
  

	PrintWriter outInput = response.getWriter();
	outInput.print(jrxml);
	outInput.flush();
	outInput.close();
	%>