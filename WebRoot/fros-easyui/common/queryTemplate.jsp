<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@include file="/fros-easyui/common/include.jsp"%>

		<script type="text/javascript">
		</script>

	</head>

	<body class="easyui-layout">
		<div region="north" title="查询条件" border="false">
			<div class="datagrid-toolbar">
				<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="">查询</a>
				<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="">重置</a>
			</div>
			<form id="form1" class="easyui-form" columns="3" i18nRoot="SysViewButton">
				<input name="creator" operator="ilikeAnywhere" />
				<input name="createTime" operator="ilikeAnywhere" class="easyui-combobox" codetype="ALL_BOM"/>
				<input name="modifier" operator="ilikeAnywhere" class="easyui-combogrid" codetype="ALL_USERS"/>
			</form>
		</div>
		<div region="center" border="false">
			<div class="easyui-tabs" fit="true">
				<div title="tab1">
					<table id="datagrid1" class="easyui-datagrid" fit="true" i18nRoot="SysViewButton">
						<thead>
							<tr>
								<th field="creator" editor="text"/>
								<th field="createTime" editor="text"/>
								<th field="modifier" editor="text"/>
							</tr>
						</thead>
					</table>
				</div>
				<div title="tab2">
					<table class="easyui-datagrid" fit="true"></table>
				</div>
			</div>
		</div>
	</body>
</html>
