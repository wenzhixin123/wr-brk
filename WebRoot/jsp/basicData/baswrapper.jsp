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
//查询
function query() {
	
	  $("#gridResult").datagrid("setQueryFields",[
  			{
  				fieldName:"officeCode",
  				fieldStringValue:"<%=officeCode%>"
  			}
  		]);	   
	$("#gridResult").datagrid("commonQuery", {
		queryType : "BasWrapperModel",
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
	BasWrapperManager.saveAll(rows, function(result) {
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
			<a href="#" id="resetButton" class="easyui-linkbutton" plain="true" iconCls="myCustomerIcon_clear" style="float: left" clearQueryForm()">
				重置</a>
				</div>	
			<form id="formQuery" class="easyui-form"   columns="3">
				<input title="包材代码" id="wrapperCode" name="wrapperCode" class="easyui-validatebox" />
				<input title="包材文名" id="wrapperName" name="wrapperName" class="easyui-validatebox" />
				<input title="包材英文名" id="wrapperNameCn" name="wrapperNameCn" class="easyui-validatebox" />
			 
			</form>
       </div>

		<div region="center" title="包材信息" iconCls="icon-edit">
			<div class="datagrid-toolbar">
				<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="appendRow()">新增</a>
				<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteSelectedRows()">删除</a>
				<a class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="save()">保存</a>
				<!-- <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="SelectedActivieRows()">生效</a>
				<a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="invalidate()">作废</a> -->
			</div>
			<table id="gridResult" class="easyui-datagrid"   fit="true" sortName="modifyTime" sortOrder="desc">
				<thead>
					<tr>
						<!-- <th field="wrapperType"   title="包材类型" width="100px;"/> -->
						<th field="wrapperCode" title="编码" editor="{type:'validatebox',options:{required:true}}"/>
						<th field="wrapperName" editor="{type:'validatebox',options:{required:true}}" title="名称" />
						<th field="wrapperNameCn" editor="{type:'validatebox'}" title="英文名" />
						<th field="itemCode" editor="{type:'combogrid'}" title="商品编码" />
						 <th field="mainItem" editor="{type:'combogrid'}" title="主作业项目"  width="100px;"/>
						<th field="unit" editor="{type:'combogrid'}" title="单位"  width="50px;"/>
						<th field="isInt" editor="{type:'checkbox',options:{on:'Y',off:'N'}}" title="整数" width="40px;" />
						  <th field="lengthUnitCode" editor="{type:'combogrid'}" title="长度单位" codetype="ALL_UNIT" />  
						<th field="length" editor="{type:'numberbox',options:{precision:4,min:0,max:999999999.99,validType:'numberRange[0,999999999.99]'}}" title="长" />
						<th field="width" editor="{type:'numberbox',options:{precision:4,min:0,max:999999999.99,validType:'numberRange[0,999999999.99]'}}" title="宽" />
						<th field="height" editor="{type:'numberbox',options:{precision:4,min:0,max:999999999.99,validType:'numberRange[0,999999999.99]'}}" title="高" />
						<th field="grossWeight" editor="{type:'numberbox',options:{precision:4,min:0,max:999999999.99,validType:'numberRange[0,999999999.99]'}}" title="毛重" />
						<th field="netWeight" editor="{type:'numberbox',options:{precision:4,min:0,max:999999999.99,validType:'numberRange[0,999999999.99]'}}" title="净重" hidden="true"/>
						  <th field="volumeUnitCode" editor="{type:'combogrid'}" title="体积单位" codetype="ALL_UNIT" />
					 	 <th field="volume" editor="{type:'numberbox',options:{precision:4,min:0,max:999999999.99,validType:'numberRange[0,999999999.99]'}}" title="体积" />
						<th field="weightUnitCode" editor="{type:'combogrid'}" title="重量单位" codetype="ALL_UNIT"/>  
						<th field="printSpuNum" editor="{type:'numberbox',options:{validType:'numberRange[0,99999]'}}" title="打印内部SPU标签个数" />
						<th field="remark" editor="text" title="备注" /> 
					</tr>
				</thead>
			</table>
		</div>
		
	</body>
</html>