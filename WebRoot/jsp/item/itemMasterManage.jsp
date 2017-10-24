<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>
<%@page
	import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@page import="com.sinotrans.gd.wlp.util.StringUtil"%>
<%@page import="com.sinotrans.gd.wlp.common.web.RcUtil"%>
<%
String path = request.getContextPath();
SessionContextUserEntity scue = SessionContextUserEntity.currentUser();	
String officeCode = scue.getOfficeCode();
 %>
<script>
function query() {
	
	$("#gridResult").datagrid("setQueryFields",[
		{
			fieldName:"officeCode",
			fieldStringValue:"<%=officeCode%>"
		}
	]);	 
	$("#gridResult").datagrid("commonQuery", {
		queryType : "ItemMasterModel",
		paramForm : "formQuery"
	});
	
};

//重置
function clearQueryForm() {
	$("#formQuery").form("clear");
};
//新增
function appendRow() {
	$("#gridResult").datagrid("appendRow", {
		
	});
};

//保存
function save() {
	if (! $("#gridResult").datagrid("validate")) {
		$.messager.alert("提示", "数据验证错误", "warning");
		return;
	}
	var rows = $("#gridResult").datagrid("getChanges");
	if (rows.length == 0) {
		$.messager.alert("提示", "未修改数据", "warning");
		return;
	}
	for(var i=0;i<rows.length;i++){
		if(rows[i].status == "" || rows[i].status == null){
			rows[i].status="Active";
		}
	}
	ItemMasterManager.saveAll(rows, function(result) {
		$("#gridResult").datagrid("refreshSavedData", result);
		$.messager.alert("提示", "保存成功", "info");
	});
};
//删除
function deleteSelectedRows() {
	$("#gridResult").datagrid("deleteSelectedRows");
};
//作废
function cancel() {
	var rows = $("#gridResult").datagrid("getSelections");
	if(rows&&rows.length>0){
		var text='确认作废'+rows.length+'条数据?';
		$.messager.confirm('Prompt',text,function(b){
			if(b){
				for(var i=0;i<rows.length;i++){
						rows[i].status="Cancel";	
				}
				ItemMasterManager.saveAll(rows, function(result) {
					$("#gridResult").datagrid("reload");
					$.messager.alert("提示", "保存成功", "info");
				});
			}
		});
	}else{
		alert("无选中的数据可操作!");
	}
};

//作废
function ok() {
	var rows = $("#gridResult").datagrid("getSelections");
	if(rows&&rows.length>0){
		var text='确认生效'+rows.length+'条数据?';
		$.messager.confirm('Prompt',text,function(b){
			if(b){
				for(var i=0;i<rows.length;i++){
						rows[i].status="Active";	
				}
				ItemMasterManager.saveAll(rows, function(result) {
					$("#gridResult").datagrid("reload");
					$.messager.alert("提示", "保存成功", "info");
				});
			}
		});
	}else{
		alert("无选中的数据可操作!");
	}
};


</script>			
</head>
	<body class="easyui-layout">
			<div id="queryTerm" region="north" title="查询条件" border="false" style="height:160px;">
				<div class="datagrid-toolbar">
			<a href="#" id="searchBtn" class="easyui-linkbutton" plain="true" iconCls="icon-search" style="float: left" onclick="query()">
				查询</a>
			<a href="#" id="resetButton" class="easyui-linkbutton" plain="true" iconCls="myCustomerIcon_clear" style="float: left"  onclick="reset()">
				重置</a>
				</div>
	

			<form id="formQuery" class="easyui-form" i18nRoot="LogisticsOrderOI" columns="4">
				<input title="物料编码" id="itemCode" name="itemCode" class="easyui-validatebox" />
				<input title="物料中文名" id="itemName" name="itemName" class="easyui-validatebox" />
				<input title="物料英文名" id="itemNameEn" name="itemNameEn" class="easyui-validatebox" />
				<input title="其他物料编码" id="extItemCode" name="extItemCode" class="easyui-validatebox" />
				<input id="itemShortname" name="itemShortname" title="物料简称" />
				<input id="customerCode" name="customerCode" title="客户"  class="easyui-combogrid"   codetype="ALL_CUSTOMER"/>
				<input id="cargoConsigneeCode" name="cargoConsigneeCode"  title="供应商" class="easyui-combogrid"   codetype="ALL_CUSTOMER"/>
			</form>
       </div>
			<div region="center" border="false" title="物料" >
				<div class="datagrid-toolbar">
			<a id="newAddButton" class="easyui-linkbutton" plain="true" href="#" iconCls="icon-add" onclick="appendRow()"> 
				新增</a>
			<a id="deleteButton" class="easyui-linkbutton" plain="true" href="#" iconCls="myCustomerIcon_remove" onclick="deleteSelectedRows()"> 
				删除</a>
			<a id="saveButton" class="easyui-linkbutton" plain="true" href="#" iconCls="icon-save" onclick="save()"> 
				保存</a>
			<a id="deleteButton" class="easyui-linkbutton" plain="true" href="#" iconCls="icon-cancel" onclick="cancel()"> 
				作废</a>
			<a id="deleteButton" class="easyui-linkbutton" plain="true" href="#" iconCls="icon-ok" onclick="ok()"> 
				生效</a>
			
		</div>
		<table id="gridResult" fit="true" class="easyui-datagrid" >
			<thead>
				<tr>
					<th field="status"  title="状态" codetype="STATUS" ></th>
					<th field="itemCode"  title="物料编码" editor="{type:'validatebox', options:{required:true}}"></th>
					<th field="customerCode"  title="客户名称" editor="{type:'combogrid', options:{required:true}}" codetype="ALL_CUSTOMER"  </th>
					<th field="itemKindCode"   title="物料种类" editor="{type:'combogrid' }" codetype="ALL_ITEM_KIND"  ></th>
					<th field="itemNatureCode"   title="物料属性" editor="{type:'combogrid' }" codetype="ALL_ITEM_NATURE"></th>
					<th field="itemName"     title="物料中文名称" editor="{type:'validatebox', options:{required:true}}"></th>
					<th field="itemNameEn"  editor="text" title="物料英文名称"  align="center"></th>
					<th field="itemShortname" editor="text"  title="物料简称" align="center"></th>
					<th field="cargoConsigneeCode"   title="供应商名称"   editor="{type:'combogrid' }" codetype="ALL_CUSTOMER" required="true"></th>
					<th field="extItemCode" title="其他物料编码" editor="text"></th>
					<th field="model" title="型号" editor="text"></th>
					<th field="spec" title="规格" editor="text"></th>
					<th field="lengthUnitCode" title="长度单位" editor="text"></th>
					<th field="length" title="长"   width="130px;" editor="text"></th>
					<th field="width" title="宽"   width="130px;" editor="text"></th>
					<th field="height" title="高"   width="130px;" editor="text"></th>
					<th field="weightUnitCode" title="重量单位"   width="130px;" editor="text"></th>
					<th field="netWeight" title="净重"   width="130px;" editor="text"></th>
					<th field="grossWeight" title="毛重"   width="130px;" editor="text"></th>
					<th field="volumeUnitCode" title="体积单位"   width="130px;" editor="text"></th>
					<th field="volume" title="体积"   width="130px;" editor="text"></th>
					<th field="remark" title="备注"   width="130px;" editor="text"></th>
				</tr>
			</thead>
		</table>
			</div>
			
		
	</body>
</html:html>