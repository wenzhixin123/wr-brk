<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>
<script type="text/javascript">
	
	//查询
	function query() {
		$("#gridResult").datagrid("commonQuery", {
			queryType : "BasReleaseExcelTemplateQuery",
			paramForm : "formQuery"
		});
	}
	
	//重置
	function clearQueryForm() {
		$("#formQuery").form("clear");
	};
	
	//新增
	function appendRow() {
		$("#tempInfo").datagrid("loadData",{
			rows : []
		});
		$('#templateNameAdd').combobox({
		    data:[{  "v":"放柜计划导入模板",
					    "text":"放柜计划导入模板"
					}],
		    valueField:'v',
		    textField:'text',
		    panelHeight:67,
		    editable:false
			});
		$("#dialogAdd").dialog("open");
		$('#templateNameAdd').combobox('setValue',"放柜计划导入模板");
		addEncodeMapping('#tempInfo');
	};
	
	//新增保存
	function saveDialogOk() {
		if (! $("#formAdd").form("validate")) {
			$.messager.alert("提示", "数据验证错误", "warning");
			return;
		}
		var rows = $("#tempInfo").datagrid("getData").rows;
		var encodeArr=getEncodes(rows);
		if(encodeArr==''){
			return;
		}
		var temp =$("#formAdd").form("getData");
		if(temp.linerCode==null){
			temp.linerCode="00041"; //默认为通用模板
		}
		temp.remark=encodeArr;
		BasCodeDefManager.saveExcelTemplate(temp, function(result) {
			if(result != null){
				$.messager.alert("提示", "保存成功", "info");
				query();
				dialogCancel('#dialogAdd');
			} else {
				$.messager.alert("提示", "保存失败，该模板已经存在", "info");
			}
		});
	}
	
	function setBasicdataState(v,r,i){
		if(v=='Active') return '<span style="color:green;">启用</span>';
		if(v=='Cancel') return '<span style="color:red;">停用</span>';
		if(v=='undefined') return '';
		return v;
	}
	
	/*
	 * 对编码对应关系进行验证
	 */
	function getEncodes(rows){
		var encodeList = new Array();
		var numlist = new Array();
		var encodes = '';
		var checkNum = false;
		$.each(rows,function(index,row){
			if(row.mappingUnit != null){
				if(row.mappingUnit > 0){
					encodeList.push(row);
					numlist.push(row.mappingUnit);
				} else {
					checkNum = true;
				}
			}
		});
		if(encodeList.length == 0 || checkNum || checkRepeat(numlist)){
			$.messager.alert("提示", "保存不成功,输入列号重复或小于零", "warning");
			return '';
		}
		//对象数组排序
		//var rowList = encodeList.sort(function(a, b){
        //    return (a.mappingUnit - b.mappingUnit);
        //});
		$.each(encodeList,function(index,row){
			if(encodes.length>0){ encodes = encodes + ','; }
			encodes = encodes + row.encodeCode +':'+row.mappingUnit;
		});
		return encodes;
	}
	
	/* 判断数组是否有重复项,true为有重复项*/
	function checkRepeat(arr){
		var nary=arr.sort();
		for(var i=0;i<nary.length-1;i++){
			if (nary[i]==nary[i+1]){
				//$.messager.alert("提示", "重复列号"+ nary[i], "warning");
				return true;
			}
		}
		return false;
	}
	
	//编辑
	function editRow() {
		$("#editTempInfo").datagrid("loadData",{
			rows : []
		});
		$('#templateNameEdit').combobox({
		    data:[{  "v":"放柜计划导入模板",
					    "text":"放柜计划导入模板"
					}],
		    valueField:'v',
		    textField:'text',
		    panelHeight:67,
		    editable:false
			});
		var row = $("#gridResult").datagrid("endEdit").datagrid("getSelected");
		if (row) {
			$("#dialogEdit").dialog("open");
			$("#formEdit").form("setData", row);
		    $('#templateNameEdit').combobox('setValue',row.templateName);
			addEncodeMapping('#editTempInfo');
			var encodes = row.remark.split(",");
			if(encodes != null){
				var rows = $("#editTempInfo").datagrid("getData").rows;
				$.each(encodes,function(encIndex,encode){
					var enc = encode.split(":");
					$.each(rows,function(rowIndex,row){
						if(row.encodeCode==enc[0]){
							row.mappingUnit=enc[1];
						}
					});
				});
				$("#editTempInfo").datagrid("loadData",{
					rows : rows
				});
			}
		} else {
			$.messager.alert("提示", "请先选择要编辑的模板", "info");
		}
	};

	//编辑窗口确定
	function editDialogOk() {
		if (! $("#editTempInfo").form("validate")) {
			$.messager.alert("提示", "数据验证错误", "warning");
			return;
		}
		var rows = $("#editTempInfo").datagrid("getData").rows;
		var encodeArr=getEncodes(rows);
		if(encodeArr==''){
			return;
		}
		var temp =$("#formEdit").form("getData");
		if(temp.linerCode==null){
			temp.linerCode="00041"; //默认为通用模板
		}
		temp.remark=encodeArr;
		BasCodeDefManager.updateExcelTemplate(temp, function(result) {
			if(result != null){
				$.messager.alert("提示", "保存成功", "info");
				query();
				dialogCancel('#dialogEdit');
			} else {
				$.messager.alert("提示", "保存失败，该模板已经被删除", "info");
			}
		});
	};
	
	//删除
	function deleteSelectedRows() {
		var row = $("#gridResult").datagrid("endEdit").datagrid("getSelected");
		if (row) {
			if(row.status=="Active"){
				$.messager.alert("提示", "请先将模板停用再选择删除", "info");
				return;
			}
			BasCodeDefManager.removeExcelTemplateBy(row.defId, function(result) {
				$.messager.alert("提示", "模板删除成功", "info");
				query();
			});
		} else {
			$.messager.alert("提示", "请先选择要删除的模板", "info");
		}
	};
	
	//添加窗口 编辑窗口取消
	function dialogCancel(dialogName) {
		$(dialogName).dialog("close");
	};
	
	$(function() {
		$('#templateNameQuery').combobox({
		    data:[{    "v":"",
					    "text":"全部"
					},
			    	{  "v":"放柜计划导入模板",
					    "text":"放柜计划导入模板"
					}],
		    valueField:'v',
		    textField:'text',
		    panelHeight:67,
		    editable:false
			});
		
		query();
		//查询数据后自动开始grid编辑
		//$("#gridResult").datagrid("options").onLoadSuccess = function(row, data) {
		//	$.fn.datagrid.defaults.onLoadSuccess.apply(this, [row, data]);
		//	$("#gridResult").datagrid("selectRow", 0).datagrid("beginEdit", 0);
		//};
	});
	
	function addEncodeMapping(aTable){
		$(aTable).datagrid("appendRow", {encodeCode:'bookingDate',encodeName:'放柜日期'});
		$(aTable).datagrid("appendRow", {encodeCode:'bookingNumber',encodeName:'提单号'});
		$(aTable).datagrid("appendRow", {encodeCode:'count20',encodeName:"20'"});
		$(aTable).datagrid("appendRow", {encodeCode:'count40',encodeName:"40'"});
		$(aTable).datagrid("appendRow", {encodeCode:'count40hq',encodeName:"40'HQ"});
		$(aTable).datagrid("appendRow", {encodeCode:'count45',encodeName:"45'"});
		$(aTable).datagrid("appendRow", {encodeCode:'expiryDate',encodeName:'有效期'});
		$(aTable).datagrid("appendRow", {encodeCode:'bkgRemark',encodeName:'Remark'});
		$(aTable).datagrid("appendRow", {encodeCode:'locationCode',encodeName:'码头'});
	}
	
	function updateTempStatus(status){
		var row = $("#gridResult").datagrid("endEdit").datagrid("getSelected");
		if (row) {
			row.status=status;
			BasCodeDefManager.updateExcelTemplate(row, function(result) {
				if(result != null){
					$.messager.alert("提示", "修改状态成功", "info");
					query();
				} else {
					$.messager.alert("提示", "修改状态失败，该模板已经被删除", "info");
				}
			});
		}else{
			$.messager.alert("提示", "请先选择要更改状态的行", "info");
		}
	}
	
</script>
</head>
  
  <body class="easyui-layout">
  	<div region="north" title="查询条件" iconCls="icon-search">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="query()">查询</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="clearQueryForm()">重置</a>
		</div>
		<form id="formQuery" class="easyui-form" columns="2" i18nRoot="BasDanger">
			<input name="createTime" title="创建时间" class="easyui-datebox"/>
			<input name="endTime" title="至" class="easyui-datebox"/>
			<input id="templateNameQuery" name="templateName" title="模板名称">
			<input name="linerCode"  title="Liner" class="easyui-combogrid" codetype="ALL_LINER"/>
		</form>
	</div>
	
	<div region="center" title="模板列表" iconCls="icon-edit" >
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow()">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="editRow()">编辑</a>
			<a class="easyui-linkbutton" iconCls="myCustomerIcon_remove" onclick="deleteSelectedRows()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="updateTempStatus('Active')">启用</a>
			<a class="easyui-linkbutton" iconCls="myCustomerIcon_cancel" onclick="updateTempStatus('Cancel')">停用</a>
		</div>
		<table id="gridResult" class="easyui-datagrid" i18nRoot="BasDanger" fit="true" singleSelect="true">
			<thead>
				<tr>
					<th field="linerCode" title="Liner" class="easyui-combogrid" codetype="ALL_LINER"/>
					<th field="templateName" title="模板名称"/>
					<th field="status" title="状态" formatter="setBasicdataState" align="center"/>
					<th field="createTime" title="创建时间"/>
				</tr>
			</thead>
		</table>
	</div>
	
	<!-- 弹出窗口添加-->
	<div id="dialogAdd" class="easyui-dialog" title="添加模板" iconCls="icon-edit"
			style="width:550px;padding:10px;height:470px" closed="true" modal="true">
		<form id="formAdd" class="easyui-form" columns="2">	
			<input id="addLinerCode" name="linerCode" class="easyui-combogrid" codetype="ALL_LINER" title="Liner"/>
    		<input id="templateNameAdd" name="templateName" title="模板名称">
			<div title="状态">
			    <input name="status" id="statusActive" type="radio" value="Active" operator="isNull" onclick="change()" checked="checked"/>启用
			    <input name="status" id="statusCancel" type="radio" value="Cancel" onclick="change()"/>停用
			</div>
		</form>
		<div region="center" border="false" style="width:500px; height:300px">
		<div class="datagrid-toolbar">
		<span class="panel-header panel-title" style="float: left; border-style:none; width:100px;">编码对应关系</span>
		</div>
		 <table id="tempInfo" class="easyui-datagrid"  i18nRoot="BasDanger" fit="true" pagination="false">
			<thead>
				<tr>
				    <th field="encodeName"  title="字段名称" width="200"/>
					<th field="mappingUnit" title="对应 Excel 列" width="200" 
					editor="{type:'numberbox',options:{validType:'length[1,2]'}}"/>
				</tr>
			</thead>
		 </table>
		</div>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="saveDialogOk()">确定</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="dialogCancel('#dialogAdd')">取消</a>
		</div>
	</div>
	
	<!-- 弹出窗口编辑 -->
	<div id="dialogEdit" class="easyui-dialog" title="编辑模板" iconCls="icon-edit"
			style="width:550px;padding:10px;height:470px" closed="true" modal="true">
		<form id="formEdit" class="easyui-form" columns="2" i18nRoot="BasDanger">
			<input name="defId" type="hidden"/>
			<input id="addLinerCode" name="linerCode" class="easyui-combogrid" codetype="ALL_LINER" title="Liner"/>
    		<input id="templateNameEdit" name="templateName" title="模板名称">
			<div title="状态">
			    <input name="status" id="statusActive" type="radio" value="Active" operator="isNull" onclick="change()" checked="checked"/>启用
			    <input name="status" id="statusCancel" type="radio" value="Cancel" onclick="change()"/>停用
			</div>
		</form>
		<div region="center" border="false" style="width:500px; height:300px">
		<div class="datagrid-toolbar">
		<span class="panel-header panel-title" style="float: left; border-style:none; width:100px;">编码对应关系</span>
		</div>
		 <table id="editTempInfo" class="easyui-datagrid"  i18nRoot="tempInfo" fit="true" pagination="false">
			<thead>
				<tr>
				    <th field="encodeName"  title="字段名称" width="200"/>
					<th field="mappingUnit" title="对应 Excel 列" width="200" 
					editor="{type:'numberbox',options:{validType:'length[1,2]'}}"/>
				</tr>
			</thead>
		 </table>
		</div>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="editDialogOk()">保存</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="dialogCancel('#dialogEdit')">取消</a>
		</div>
	</div>
	
</body>
</html>	