<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	String contextPath = request.getContextPath();
	String locale = LocaleContextHolder.getLocale().toString();
	if (locale.equalsIgnoreCase("zh") || locale.equalsIgnoreCase("cn")) {
		locale = "zh_CN";
	}
	if (! locale.equalsIgnoreCase("en") && ! locale.equalsIgnoreCase("zh_CN")) {
		locale = "en";
	}
%>


<%@page import="java.util.Date"%><script type="text/javascript">
	var contextPath = "<%=contextPath%>";
	var DWR_SERVICE_PATH = contextPath + '/dwr2';
</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/fros-easyui/js/jquery-easyui-1.2.4/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/fros-easyui/js/jquery-easyui-1.2.4/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/fros-easyui/js/fros-easyui/easyui.fros.css" />

<script type="text/javascript" src="<%=contextPath%>/fros-easyui/js/dwr-2.0.5/dwr_engine.js?date=<%=new Date().getTime() %>"></script>
<script type="text/javascript" src="<%=contextPath%>/fros-easyui/js/dwr-2.0.5/dwr_util.js?date=<%=new Date().getTime() %>"></script>
<script type="text/javascript" src="<%=contextPath%>/dwr_interfaces.js?date=<%=new Date().getTime() %>"></script>

<script type="text/javascript" src="<%=contextPath%>/fros-easyui/js/jquery-1.7.1/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=contextPath%>/fros-easyui/js/jquery-plugins/jquery.json-2.3.min.js"></script>

<script type="text/javascript" src="<%=contextPath%>/fros-easyui/js/jquery-easyui-1.2.4/jquery.easyui-1.2.4.js"></script>
<script type="text/javascript" src="<%=contextPath%>/fros-easyui/js/jquery-easyui-1.2.4/locale/easyui-lang-<%= locale %>.js"></script>

<script type="text/javascript" src="<%=contextPath%>/fros-easyui/common/combogridOptions.js"></script>
<script type="text/javascript" src="<%=contextPath%>/fros-easyui/js/fros-easyui/jquery.easyui.fros.js"></script>
<script type="text/javascript" src="<%=contextPath%>/fros-easyui/js/fros-easyui/locale/easyui.fros-lang-<%= locale %>.js"></script>
<script type="text/javascript" src="<%=contextPath%>/i18n_<%= locale %>.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/util/wlp_formatter.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/bc/sinotrans_import.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/util/wlp_validate.js"></script>
<script type="text/javascript" charset="UTF-8" src="<%=contextPath %>/js/ExportTemplateExcel.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/index.js"></script>

<script type="text/javascript" src="<%=contextPath%>/js/bc/sinotrans_print.js"></script>
<!-- EnterAsTab -->
<script type="text/javascript" src="<%=contextPath%>/fros-easyui/common/enterAsTab.js"></script>


