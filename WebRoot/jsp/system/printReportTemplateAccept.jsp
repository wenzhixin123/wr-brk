<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.sinotrans.framework.core.util.ContextUtils"%>
<%@page import="com.sinotrans.gd.wlp.system.service.PrintReportTemplateManager"%>
<%@page import="com.sinotrans.gd.wlp.common.web.RcUtil"%>
<%@page import="com.sinotrans.gd.wlp.system.entity.PrintReportTemplateEntity"%>
<%@page import="com.sinotrans.gd.wlp.system.service.SysOfficeManager"%>
<%
String path = request.getContextPath();
String tempXMLData="";//数据源
String typeName = request.getParameter("templateType");//模板类型
String reportId = typeName+"default";//报表ID 
String template="";//报表模板文件
String templateName=typeName+"_default";//模板中文名称
String tempXMLFileName=typeName+"_default";//文件名称
String reportName=typeName+"default_V1";//报表名称_带版本号
String controlParam="";//(new为新建，edit为编缉,print为预览,printNow为直接打印)
PrintReportTemplateManager rptm = ContextUtils.getBeanOfType(PrintReportTemplateManager.class);

String modelId = request.getParameter("modelId");
controlParam = request.getParameter("controlParam");//控制参数

reportId = request.getParameter("reportId");//报表ID
tempXMLData = rptm.loadXmlData(modelId,typeName,controlParam);

// modifytime='yyyymmddhhss'
java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddhhmmss");

if(!RcUtil.isEmpty(reportId)){
	PrintReportTemplateEntity prte =  rptm.getReportTemplate(reportId);//加载模板内容
	template = prte.getTemplatecontentString().replaceAll("\n","");
	template = template.replaceAll("\r","");
	templateName = prte.getTemplateName();
	tempXMLFileName = prte.getFileName();
	if(prte.getModifyTime()==null){
		reportName = prte.getFileName();
	}else {
		reportName = sdf.format(prte.getModifyTime())+prte.getFileName();
	}
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
  <head>
    <title>打印功能</title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
 	<script type="text/javascript" src="<%=path %>/js/jquery-1.6.min.js"></script>
	<script type="text/javascript">
		function initReport(){
			if(SinotransWebPrint.isTemplateFileExists()==false){
				SinotransWebPrint.template="<%=template%>";
			}
			SinotransWebPrint.WebStart();
			window.returnValue="";
    		window.close();
		}
		function errorLoad(){
			$("#SinotransWebPrint").hide();
			alert('您的系统还没有安装打印控件，请下载控件!');
		}
		$(function(){
			initReport();
		});
	</script>
    <style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
    </style>
</head>
  <body>
    <!-- 
    <div>
    <p>reportId:</p>
    <p><%=reportId %></p>
    
    <p>templateName:</p>
    <p><%=templateName %></p>
    
    <p>typeName:</p>
    <p><%=typeName %></p>
    
    <p>tempXMLFileName:</p>
    <p><%=tempXMLFileName %></p>
    
    <p>reportName:</p>
    <p><%=reportName %></p>
    
     <p>controlParam:</p>
    <p><%=controlParam %></p>
    
    <p>tempXMLData:</p>
    <p><%=tempXMLData %></p>
    
    <p>template:</p>
    <p><%=template %></p>
    </div> 
   -->
	<table width="70%" align="left" cellpadding="0" cellspacing="0">
		<tr>
		  <td>
		  	<p>
			 <object onerror="errorLoad();" classid="clsid:7c7ccf78-19d2-4081-81ed-4852a728640a" id="SinotransWebPrint">
				<param name="tempXMLData" type="string" value="<%=tempXMLData %>" />
                <param name="templateName" type="string" value="<%=templateName %>" />
                <param name="reportId" type="string" value="<%=reportId %>" />
                <param name="typeName" type="string" value="<%=typeName %>" />
				<param name="tempXMLFileName" type="string" value="<%=tempXMLFileName %>" />
                <param name="reportName" type="string" value="<%=reportName %>" />
                <param name="controlParam" type="string" value="<%=controlParam %>" />
		    </object>
		    </p>
		    </td>
		</tr>
	</table>
  </body>
</html>