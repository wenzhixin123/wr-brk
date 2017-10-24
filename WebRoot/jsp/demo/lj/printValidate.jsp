
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.sinotrans.gd.wlp.util.StringUtil, java.util.Date" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>

<script type="text/javascript">
/*
	var forPrintRows = window.opener.rows2;	
	$(function (){
		$("#check").datagrid("loadData", {
			rows : forPrintRows
			});
	});
*/
	
	$(function(){
		var id = '<%=request.getParameter("id") %>';
		DcsBillLadingManager.getCheckMessage(id,function(result) {
			$("#check").datagrid("loadData", {
				rows : result
				});
	});
	});

</script>
</head>
<body class="easyui-layout" fit="true">
	<div region="center" iconCls="icon-edit" fit="true">
		
			<table id="check" class="easyui-datagrid" fit="true" pagination="false" singleSelect="true">
				<thead>
					<tr>
						<th field="billNo" title="提运单号"/>
						<th field="status" title="作业状况"/>
						<th field="createFee" title="港建费"/>
						<th field="inMsg" title="内装信息"/>
						<th field="admin" title="控箱公司"/>
						<th field="pierPaper" title="码头纸"/>
						<th field="stockpiling" title="堆存费" />
					</tr>
				</thead>
			</table>
	</div>
</body>	

</html>