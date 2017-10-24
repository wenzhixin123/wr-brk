<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>外部接口用户授权管理</title>
<%@include file="/fros-easyui/common/include.jsp" %>
<script type="text/javascript">
//查询
function query() {
	$("#gridResult").datagrid("commonQuery", {
		queryType : "ExtApiTokenQuery",
		paramForm : "formQuery"
	});
}

//重置
function clearQueryForm() {
	$("#formQuery").form("clear");
}

//新增
function appendRow() {
	var initObj = new Object();
	initObj.status = 'Pending';
	
	//$("#gridResult").datagrid('getColumnOption', 'usercode').editor = {type:'validatebox',options:{required:true}};
	
	$("#gridResult").datagrid("appendRow", initObj);
}

//保存
function save() {
	var rows = $("#gridResult").datagrid("getChanges");
	updateRows(rows);
}

//删除
function deleteSelectedRows() {
	var row = $("#gridResult").datagrid("getSelected");
	if(row == null || row == ""){
		$.messager.alert("提示", "请选择一条记录！", "warning");
		return;
	}
	
	if(row&&row._class&&row.status=="Active"){
		$.messager.alert("提示", "已生效的记录不能删除！", "warning");
		return;
	}else{
		if(row._class){
			ExtApiTokenManager.deleteClient(row,function(result){
				$('#gridResult').datagrid("deleteSelectedRows");
		    	$('#gridResult').datagrid("clearSelections");
				//$('#gridResult').datagrid("reload");
				$.messager.alert("提示", "删除成功！", "info");
			});
		}else{
			$('#gridResult').datagrid("deleteSelectedRows");
			$('#gridResult').datagrid("clearSelections");
			//$('#gridResult').datagrid("reload");
		}
		
	}
};

//生效
function activeSelectedRowsStatus() {
	var row = $("#gridResult").datagrid("getSelected");
	if(row == null || row == ""){
		$.messager.alert("提示", "请选择一条记录！", "warning");
		return;
	}else if(row.status == "Active"){
		$.messager.alert("提示", "此记录已生效", "warning");
		return;
	}
	row.status = "Active";
	changeSelectedRowsStatus(row);
}

//作废
function cancelSelectedRowsStatus() {
	var row = $("#gridResult").datagrid("getSelected");
	if(row == null || row == ""){
		$.messager.alert("提示", "请选择一条记录！", "warning");
		return;
	}else if(row.status == 'Cancel'){
		$.messager.alert("提示", "此记录已作废", "warning");
		return;
	}
	row.status = 'Cancel';
	changeSelectedRowsStatus(row);
}

function changeSelectedRowsStatus(row){
	row.rowState = "ChangeSta";
	var rows = new Array();
	rows[0] = row;
	updateRows(rows);
}

//重置client
function resetClient(){
	var row = $("#gridResult").datagrid("getSelected");
	if(row == null || row == ""){
		$.messager.alert("提示", "请选择一条记录！", "warning");
		return;
	}
	row.status = "Active";
	row.rowState = "Reset";
	var rows = new Array();
	rows[0] = row;
	updateRows(rows);
}

function updateRows(rows){
	ExtApiTokenManager.saveClient(rows,function(result){
		if(result != null && result.length > 0){
			var pageRows = $("#gridResult").datagrid("getRows");
			for(var i=0;i<result.length;i++){
				for(var j=0;j<pageRows.length;j++){
					if(pageRows[j].usercode == result[i].usercode){
						pageRows[j] = result[i];
						break;
					}
				}
			}
			
			$("#gridResult").datagrid("loadData", {
				rows:pageRows
			});
			
			$("#gridResult").datagrid("reload");
		}
	});
}


$(function() {
	
});
</script>
</head>
<body class="easyui-layout">
	<div region="north" title="查询条件" iconCls="icon-search">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="query()">查询</a>
			<a class="easyui-linkbutton" iconCls="myCustomerIcon_clear" onclick="clearQueryForm()">重置</a>
		</div>
		<form id="formQuery" class="easyui-form" columns="3" i18nRoot="">
			<input id="userCode" name="userCode" type="text" title="用户编码" size="10" style="TEXT-TRANSFORM:uppercase" onkeyup="this.value=this.value.toUpperCase()"/>
			<input id="customerCode" name="customerCode" title="客户公司" class="easyui-combogrid" codetype="ALL_CUSTOMER" />
		</form>
	</div>
	
	<div region="center" title="授权列表" iconCls="icon-edit">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow()">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
			<a class="easyui-linkbutton adminButton" iconCls="myCustomerIcon_remove" onclick="deleteSelectedRows()">删除</a>
			<a class="easyui-linkbutton adminButton" iconCls="icon-ok" onclick="activeSelectedRowsStatus()">生效</a>
			<a class="easyui-linkbutton adminButton" iconCls="myCustomerIcon_cancel" onclick="cancelSelectedRowsStatus()">作废</a>
			<a class="easyui-linkbutton adminButton" iconCls="icon-reload" onclick="resetClient()">重置Client</a>
		</div>
		<table id="gridResult" class="easyui-datagrid" i18nRoot="" fit="true" showcheck="false" showFooter="true" >
			<thead>
				<tr>
					<th field="status" title="状态" codetype="STATUS"  />
					<th field="usercode" title="用户编码" required=true editor="{type:'validatebox',options:{required:true}}"/>
					<th field="username" title="用户名" />
					<th field="customercode" title="客户公司"  codetype="ALL_CUSTOMER" />
					<th field="clientid"   title="ClientId"/>
					<th field="clientsecret"   title="ClientSecret"/>
				</tr>
			</thead>
		</table>
		
	</div>
</body>
</html>