<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>

<script type="text/javascript">


</script>
</head>

<body class="easyui-layout">
	<div region="north" title="放行条收集查询" >
		<div class="datagrid-toolbar">
		<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">查询条件</span>
			<a class="easyui-linkbutton" iconCls="icon-search" >查询</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="reset()">重置</a>
		</div>
		<form id="declaration" class="easyui-form" columns="3" i18nRoot="DcsLogisticsOrder">
			<input name="x1" title="报关单号"/> 
			<input name="x1" title="提运单号"/> 
			<input name="x2" title="柜号"/> 
			<input name="x3" title="交单日期" class="easyui-datebox"/> 
			<input name="x3" title="到" class="easyui-datebox"/> 
		</form>
	</div>
	<div region="center" border="false">
		<div class="datagrid-toolbar">
		<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">查询结果</span>
			<a class="easyui-linkbutton" iconCls="icon-save" >保存</a>
			<a class="easyui-linkbutton" >导出</a>
		</div>
		<table id="dcsLogisticsDetail" class="easyui-datagrid" i18nRoot="DcsLogisticsDetail" fit="true" pagination="false">
			<thead>
				<tr>
					<th field="x1"  title="放行情况"/> 
					<th field="x2"  title="提运单号"/> 
					<th field="x3"  title="报关单号"/> 
					<th field="x4"  title="申报现场"/> 
					<th field="x5"  title="交单日期"/> 
					<th field="x6"  title="出口口岸"/> 
					<th field="x7"  title="费用情况"/> 
					<th field="x8"  title="作废"/> 
					<th field="x9"  title="创建人"/> 
					<th field="x10"  title="修改人"/> 
					<th field="x11"  title="交单时间"/> 
					<th field="x12"  title="Liner"/> 
					
				</tr> 
			</thead>
		</table>
	</div>
</body>	
</html>