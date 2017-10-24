<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>

<script type="text/javascript">
	
	//查询
	function query() {
		$("#gridResult").datagrid("commonQuery", {
			queryType : "CimCustModel",
			paramForm : "formQuery"
		});
	};
	
	//重置
	function clearQueryForm() {
		$("#formQuery").form("clear");
	};
	
	//新增
	function appendRow() {
		$("#gridResult").datagrid("appendRow", {});
	};
	
	//插入
	function insertRow() {
		$("#gridResult").datagrid("insertRow", {
			index : $("#gridResult").datagrid("getSelectedIndex"),
			row : {}
		});
	};
	
	//删除
	function deleteSelectedRows() {
		$("#gridResult").datagrid("deleteSelectedRows");
	};
	
	//保存
	function save() {
		var rows = $("#gridResult").datagrid("getChanges");
		if (rows.length == 0) {
			$.messager.alert("提示", "未修改数据", "warning");
			return;
		}
		CimCustManager.saveAll(rows, function(result) {
			$("#gridResult").datagrid("refreshSavedData", result);
			$.messager.alert("提示", "保存成功", "info");
		});
	};
	
	//取消
	function reload() {
		$("#gridResult").datagrid("reload");
	};
	
</script>
</head>

<body class="easyui-layout">
	
	<div region="north" title="查询条件" iconCls="icon-search">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="query()">查询</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="clearQueryForm()">重置</a>
		</div>
		<form id="formQuery" class="easyui-form" columns="3" i18nRoot="CimCust">
			<input name="custCode" operator="ilikeAnywhere"/>
			<input name="custNameEn" operator="ilikeAnywhere"/>
			<input name="custName" operator="ilikeAnywhere"/>
		</form>
	</div>
	
	<div region="center" title="客户信息" iconCls="icon-edit">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow()">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-redo" onclick="insertRow()">插入</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedRows()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()">取消</a>
		</div>
		<table id="gridResult" class="easyui-datagrid" i18nRoot="CimCust" fit="true">
			<thead>
				<tr>
					<th field="custCode" editor="text"/>
					<th field="custNameEn" editor="text"/>
					<th field="custName" editor="text"/>
					<th field="custAddr" editor="text"/>
					<th field="zipcode" editor="text"/>
					<th field="stateId" editor="text"/>
					<th field="cityId" editor="text"/>
					<th field="cityName" editor="text"/>
					<th field="insideOffice" editor="text"/>
				</tr>
			</thead>
		</table>
	</div>
		
</body>
</html>