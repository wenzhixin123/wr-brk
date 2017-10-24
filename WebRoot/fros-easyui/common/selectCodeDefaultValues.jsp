<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.sinotrans.gd.wlp.basicdata.query.CodeDefQueryItem"%>
<%@page import="java.util.List"%>
<%@page import="com.sinotrans.framework.core.support.Condition"%>
<%@page import="com.sinotrans.gd.wlp.basicdata.query.CodeDefQueryCondition"%>
<%@page import="com.sinotrans.framework.core.dao.UniversalDao"%>
<%@page import="com.sinotrans.framework.core.util.ContextUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<script type="text/javascript">

	var selectCodeDefaultValues = {
			
		<%
		CodeDefQueryCondition codeDefQueryCondition = new CodeDefQueryCondition();
		List<CodeDefQueryItem> defaultCodes = ContextUtils.getBeanOfType(UniversalDao.class)
		.createCommonQuery(codeDefQueryCondition, CodeDefQueryItem.class)
		.addCondition(Condition.likeStart("cd.control_word", "1"))
		.query();
		
		for (CodeDefQueryItem code : defaultCodes) {
		%>
			<%=code.getTypeCode()%> : '<%=code.getCodeValue()%>',
		<%
		}
		%>
			
			undefined : undefined
	};

	
</script>
