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
String officeCode = SessionContextUserEntity.currentUser().getOfficeCode();
 %>
<script type="text/javascript">

	$(function(){	
		query();
		$('#gridResult').datagrid({
			onClickRow : function(rowIndex, rowData) {
				var uuid =rowData.basWarehouseUuid;//获取UUID
				$("#gridResult1").datagrid("setQueryFields",[
					{
						fieldName:"basWarehouseUuid",
						fieldStringValue:uuid
					}
				]);	 
			   $("#gridResult1").datagrid("commonQuery", {
					queryType :"BasLocAreaModel"
				});	
				
			}
		});
		
		$('#gridResult1').datagrid({
			onClickRow : function(rowIndex, rowData) {
				var uuid =rowData.basLocAreaUuid;//获取UUID
				$("#gridResult2").datagrid("setQueryFields",[
					{
						fieldName:"basLocAreaUuid",
						fieldStringValue:uuid
					}
				]);	 
			   $("#gridResult2").datagrid("commonQuery", {
					queryType :"BasLotStockModel"
				});	
				
			}
		});
		
	});
	
	function query() {
		$("#gridResult").datagrid("setQueryFields",[
         		{
         			fieldName:"officeCode",
         			fieldStringValue:"<%=officeCode%>"
         		}
         	]);
		$("#gridResult").datagrid("commonQuery", {
			queryType : "BasWarehouseModel",
			paramForm : "formQuery"
		});
		
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
		BasWarehouseManager.saveAll(rows, function(result) {
			$("#gridResult").datagrid("refreshSavedData", result);
			$.messager.alert("提示", "保存成功", "info");
		});
	};
	//删除
	function deleteSelectedRows() {
		$("#gridResult").datagrid("deleteSelectedRows");
	};
	
	
		
	//新增
	function appendRow1() {
		$("#gridResult1").datagrid("appendRow", {
			
		});
	};

	//保存
	function save1() {
		var row = $('#gridResult').datagrid('getSelections');
		if(row.length!=1){
			alert("必须选择一个仓库");
			return;
		}
		
		if (! $("#gridResult1").datagrid("validate")) {
			$.messager.alert("提示", "数据验证错误", "warning");
			return;
		}
		var rows = $("#gridResult1").datagrid("getChanges");
		if (rows.length == 0) {
			$.messager.alert("提示", "未修改数据", "warning");
			return;
		}
		for(var i=0;i<rows.length;i++){
			if(rows[i].status == "" || rows[i].status == null){
				rows[i].basWarehouseUuid=row[0].basWarehouseUuid;
				rows[i].status="Active";
			}
		}
		BasLocAreaManager.saveAll(rows, function(result) {
			$("#gridResult1").datagrid("refreshSavedData", result);
			$.messager.alert("提示", "保存成功", "info");
		});
	};
	//删除
	function deleteSelectedRows1() {
		$("#gridResult1").datagrid("deleteSelectedRows");
	};
	
	//新增
	function appendRow2() {
		$("#gridResult2").datagrid("appendRow", {
			
		});
	};

	//保存
	function save2() {
		var row = $('#gridResult1').datagrid('getSelections');
		if(row.length!=1){
			alert("必须选择一个区域");
			return;
		}
		
		if (! $("#gridResult2").datagrid("validate")) {
			$.messager.alert("提示", "数据验证错误", "warning");
			return;
		}
		var rows = $("#gridResult2").datagrid("getChanges");
		if (rows.length == 0) {
			$.messager.alert("提示", "未修改数据", "warning");
			return;
		}
		for(var i=0;i<rows.length;i++){
			if(rows[i].status == "" || rows[i].status == null){
				rows[i].basLocAreaUuid=row[0].basLocAreaUuid;
				rows[i].status="Active";
			}
		}
		BasLotStockManager.saveAll(rows, function(result) {
			$("#gridResult2").datagrid("refreshSavedData", result);
			$.messager.alert("提示", "保存成功", "info");
		});
	};
	//删除
	function deleteSelectedRows2() {
		$("#gridResult2").datagrid("deleteSelectedRows");
	};
	
</script>
</head>
  
  <body class="easyui-layout">
  	<div region="west" title="仓库列表" iconCls="icon-search"  style="width:400px;">
		
			<a id="newAddButton" class="easyui-linkbutton" plain="true" href="#" iconCls="icon-add" onclick="appendRow()"> 
				新增</a>
			<a id="deleteButton" class="easyui-linkbutton" plain="true" href="#" iconCls="myCustomerIcon_remove" onclick="deleteSelectedRows()"> 
				删除</a>
			<a id="saveButton" class="easyui-linkbutton" plain="true" href="#" iconCls="icon-save" onclick="save()"> 
				保存</a>
		<table id="gridResult" fit="true" class="easyui-datagrid" pagination="false" >
			<thead>
				<tr>
					<th field="warehouseCode"  title="仓库编码" editor="text" required="true"></th>
					<th field="warehouseName"  title="仓库名称" editor="text" required="true"></th>
					<th field="warehouseAddress"   title="仓库地址" editor="text"></th>		
				</tr>
			</thead>
		</table>
	</div>

	<div region="center">
		<div class="easyui-layout" fit="true">
			<div region="north" title="区域信息" style="height:200px;">
	
			<a id="newAddButton" class="easyui-linkbutton" plain="true" href="#" iconCls="icon-add" onclick="appendRow1()"> 新增</a>
			<a id="deleteButton" class="easyui-linkbutton" plain="true" href="#" iconCls="myCustomerIcon_remove" onclick="deleteSelectedRows1()"> 删除</a>
			<a id="saveButton" class="easyui-linkbutton" plain="true" href="#" iconCls="icon-save" onclick="save1()"> 保存</a>
		<table id="gridResult1" fit="true" class="easyui-datagrid" >
			<thead>
				<tr>
					<th field="locAreaCode"  title="区域编码" editor="text" required="true"></th>
					<th field="locAreaName"  title="区域名称" editor="text" required="true"></th>
					<th field="locAreaNameEn"   title="区域描述" editor="text"></th>
					<th field="remark"   title="备注" editor="text"></th>		
				</tr>
			</thead>
		</table>
			</div>
			<div region="center" title="货位信息">
			<a id="newAddButton" class="easyui-linkbutton" plain="true" href="#" iconCls="icon-add" onclick="appendRow2()"> 新增</a>
			<a id="deleteButton" class="easyui-linkbutton" plain="true" href="#" iconCls="myCustomerIcon_remove" onclick="deleteSelectedRows2()"> 删除</a>
			<a id="saveButton" class="easyui-linkbutton" plain="true" href="#" iconCls="icon-save" onclick="save2()"> 保存</a>
		<table id="gridResult2" fit="true" class="easyui-datagrid" >
			<thead>
				<tr>
					<th field="lotCode"  title="货位编号" editor="text" required="true"></th>
					<th field="lotName"   title="货位名称" editor="text"></th>
					<th field="remark" title="备注"  editor="text"></th>		
				</tr>
			</thead>
		</table>
			</div>
			
		</div>
	</div>


</body>
</html>	