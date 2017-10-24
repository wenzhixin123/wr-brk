<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>
<script type="text/javascript">
	
	//查询
	function query() {
		$("#gridResult").datagrid("commonQuery", {
			queryType : "BasPortModel",
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
	
	//插入
	function insertRow() {
		$("#gridResult").datagrid("insertRow", {
			index : $("#gridResult").datagrid("getSelectedIndex"),
			row : {
				
			}
		});
	};
	
	//编辑
	function editRow() {
		var row = $("#gridResult").datagrid("endEdit").datagrid("getSelected");
		if (row) {
			$("#dialogEdit").dialog("open");
			$("#formEdit").form("setData", row);
		}
	};
	
	//删除
	function deleteSelectedRows() {
		$("#gridResult").datagrid("deleteSelectedRows");
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
		BasPortManager.saveAll(rows, function(result) {
			$("#gridResult").datagrid("refreshSavedData", result);
			$.messager.alert("提示", "保存成功", "info");
		});
	};
	
	//取消
	function reload() {
		$("#gridResult").datagrid("reload");
	};
	
	//编辑窗口确定
	function editDialogOk() {
		if (! $("#formEdit").form("validate")) {
			$.messager.alert("提示", "数据验证错误", "warning");
			return;
		}
		$("#dialogEdit").dialog("close");
		$("#gridResult").datagrid("updateRow", {
			index : $("#gridResult").datagrid("getSelectedIndex"),
			row : $("#formEdit").form("getData")
		});
	};
	
	//编辑窗口取消
	function editDialogCancel() {
		$("#dialogEdit").dialog("close");
	};
	
	$(function() {
		//查询数据后自动开始grid编辑
		$("#gridResult").datagrid("options").onLoadSuccess = function(row, data) {
			$.fn.datagrid.defaults.onLoadSuccess.apply(this, [row, data]);
			$("#gridResult").datagrid("selectRow", 0).datagrid("beginEdit", 0);
		};
		
		//开始编辑行，自动定位到第一列
		$("#gridResult").datagrid("options").onBeginEdit = function(rowIndex, rowData) {
			$.fn.datagrid.defaults.onBeginEdit.apply(this, [rowIndex, rowData]);
			$("#gridResult").datagrid("getColumnEditor", "portCode").select();
		};

	});
	function mapping(){
		var row = $("#gridResult").datagrid("endEdit").datagrid("getSelected");
		var title = "数据字典映射";
		var url = "jsp/dcs/dcsMappingInfo.jsp?tableName=BAS_PORT&basName="+row.portName;
		
		parent.addTabs(title, url, "icon icon-nav", true);
		parent.addTabs(title, url, false, true); //21Aug, fix slow problem...
		if(parent.getTabIframe(title).contentWindow.queryDetail){
			parent.getTabIframe(title).contentWindow.queryDetail('BAS_PORT',row.portName); //21Aug, fix slow problem...	
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
		<form id="formQuery" class="easyui-form" columns="3" i18nRoot="BasPort">
			<input name="portCode"/>
			<input name="portName" operator="ilikeAnywhere"/>
			<input name="portArea" operator="ilikeAnywhere"/>
			<input name="portKinds" operator="ilikeAnywhere"/>
			<input name="cityCode" class="easyui-combogrid" codetype="ALL_CITY"/>
			<input name="iddAreaCode"/>
		</form>
	</div>
	
	<div region="center" title="港口信息" iconCls="icon-edit">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow()">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-redo" onclick="insertRow()">插入</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="editRow()">编辑</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedRows()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()">取消</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="mapping()">映射</a>
		</div>
		<table id="gridResult" class="easyui-datagrid" i18nRoot="BasPort" fit="true">
			<thead>
				<tr>
					<th field="portCode" editor="{type:'validatebox', options:{required:true}}"/>
					<th field="portArea" editor="{type:'combogrid'}" codetype="GANG_QU_CODE"/>
					<th field="cityCode" editor="text"/>
					<th field="portName" editor="text"/>
					<th field="portNameEn" editor="text"/>
					<th field="portKinds" editor="text"/>
					<th field="portAgent" editor="text"/>
					<th field="mnemonicCode" editor="text"/>
					<th field="iddAreaCode" editor="text"/>
					<th field="msaAreaDesc" editor="{type:'combogrid'}" codetype="MSA_AREA_CODE"/>
					<th field="route" editor="text"/>
					<th field="portRoute" editor="text"/>
					<th field="controlWord" editor="text"/>
					<th field="pubPortId" editor="text"/>
					<th field="pubPortName" editor="text"/>
					<th field="pubPortNameEn" editor="text"/>
					<th field="officeCode" editor="text"/>
					<th field="aux1" editor="text"/>
					<th field="aux2" editor="text"/>
					<th field="aux3" editor="text"/>
					<th field="aux4" editor="text"/>
					<th field="aux5" editor="text"/>
					<th field="remark" editor="text" width="200"/>
				</tr>
			</thead>
		</table>
	</div>
	
	<!-- 弹出窗口编辑 -->
	<div id="dialogEdit" class="easyui-dialog" title="港口信息" iconCls="icon-edit"
			style="width:650px;padding:10px" closed="true" modal="true">
		<form id="formEdit" class="easyui-form" columns="2" i18nRoot="BasPort">
			<input name="portCode" class="easyui-validatebox"/>
			<input name="portArea" class="easyui-validatebox"/>
			<input name="cityCode" class="easyui-validatebox"/>
			<input name="portName" class="easyui-validatebox"/>
			<input name="portNameEn" class="easyui-validatebox"/>
			<input name="portKinds" class="easyui-validatebox"/>
			<input name="portAgent" class="easyui-validatebox"/>
			<input name="iddAreaCode" class="easyui-validatebox"/>
			<input name="route" class="easyui-validatebox"/>
			<input name="portRoute" class="easyui-validatebox"/>
			<input name="pubPortId" class="easyui-validatebox"/>
			<input name="pubPortName" class="easyui-validatebox"/>
			<input name="pubPortNameEn" class="easyui-validatebox""/>
			<input name="officeCode" class="easyui-validatebox"/>
			<input name="aux1" class="easyui-validatebox"/>
			<input name="aux2" class="easyui-validatebox"/>
			<input name="aux3" class="easyui-validatebox"/>
			<input name="aux4" class="easyui-validatebox"/>
			<input name="msaAreaDesc" class="easyui-combogrid" codetype="MSA_AREA_CODE"/>
			
			<div></div>
			<textarea name="remark" rowspan="2" colspan="2"></textarea>
		</form>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="editDialogOk()">确定</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="editDialogCancel()">取消</a>
		</div>
	</div>
</body>
</html>