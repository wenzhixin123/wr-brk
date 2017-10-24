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
	<script type="text/javascript">
	function query() {
		
		$("#gridResult").datagrid("setQueryFields",[
			{
				fieldName:"officeCode",
				fieldStringValue:"<%=officeCode%>"
			}
		]);	 
		$("#gridResult").datagrid("commonQuery", {
			queryType : "BasLocTypeModel",
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
		BasLocTypeManager.saveAll(rows, function(result) {
			$("#gridResult").datagrid("refreshSavedData", result);
			$.messager.alert("提示", "保存成功", "info");
		});
	};
	//删除
	function deleteSelectedRows() {
		$("#gridResult").datagrid("deleteSelectedRows");
	};
		
		
	</script>
  </head>
  
  <body class="easyui-layout">
		<div id="queryTerm" region="north" title="查询条件" border="false" style="height:160px;">
				<div class="datagrid-toolbar">
			<a href="#" id="searchBtn" class="easyui-linkbutton" plain="true" iconCls="icon-search" style="float: left" onclick="query()">
				查询</a>
			<a href="#" id="resetButton" class="easyui-linkbutton" plain="true" iconCls="myCustomerIcon_clear" style="float: left"  onclick="clearQueryForm()">
				重置</a>
				</div>	
			<form id="formQuery" class="easyui-form" i18nRoot="LogisticsOrderOI" columns="3">
				<input title="货位类型代码" id="locTypeCode" name="locTypeCode" class="easyui-validatebox" />
				<input title="货位类型名称" id="locTypeName" name="locTypeName" class="easyui-validatebox" />
				<input title="货位类型描述" id="locTypeNameEn" name="locTypeNameEn" class="easyui-validatebox" />
			</form>
       </div>
		
		<div id="gridContainer" region="center" title="物料种类列表" border="false">
			<div class="datagrid-toolbar">
			<a id="newAddButton" class="easyui-linkbutton" plain="true" href="#" iconCls="icon-add" onclick="appendRow()"> 
				新增</a>
			<a id="deleteButton" class="easyui-linkbutton" plain="true" href="#" iconCls="myCustomerIcon_remove" onclick="deleteSelectedRows()"> 
				删除</a>
			<a id="saveButton" class="easyui-linkbutton" plain="true" href="#" iconCls="icon-save" onclick="save()"> 
				保存</a>
		</div>
			<table id="gridResult" fit="true" class="easyui-datagrid" >
			<thead>
				<tr>	 
					<!-- <th field=itemKindCode  title="物料种类代码" editor="{type:'validatebox', options:{required:true}} "></th>
					<th field="itemKindName"  title="物料种类中文名" editor="{type:'validatebox', options:{required:true}} "></th>
					<th field="itemKindNameEn"   title="物料种类英文名" editor="text"></th>	 --> 
					<th field=locTypeCode  title="货位类型代码" editor="{type:'validatebox', options:{required:true}} "></th>
					<th field="locTypeName"  title="货位类型名称" editor="{type:'validatebox', options:{required:true}} "></th>
					<th field="locTypeNameEn"   title="货位类型描述" editor="text"></th>
					<th field="pri"   title="优先级" editor="text"></th>
					<th field="putPri"   title="上架优先级" editor="text" required="true"></th>
					<th field="pickPri"   title="拣货优先级"  align="center" editor="text" required="true"></th>
					<th field="maxPalletFloor"  title="最大堆放层数" align="center" editor="text" required="true"></th>
					<th field="maxVoluem"  title="最大体积" align="center" editor="text" required="true"></th>
					<th field="maxWeight"  title="最大重量" align="center" editor="text" required="true"></th>
					<th field="maxPalletQty"  title="最大托盘数" align="center" editor="text" required="true"></th>
					<th field="length"  title="长" align="center" editor="text" required="true"></th>
					<th field="width"  title="宽" align="center" editor="text" required="true"></th>
					<th field="height"  title="高" align="center" editor="text" required="true"></th>
					<th field="remark"  title="备注" align="center" editor="text" required="true"></th>
					
				</tr>
			</thead>
		</table>
		</div>
  </body>
</html:html>
