<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>

<script type="text/javascript">
	
	//查询
	function query() {
		$("#gridResult").datagrid("commonQuery", {
			queryType : "TestProvinceModel",
			paramForm : "formQuery"
		});
		alert();
	};
	
	//重置
	function clearQueryForm() {
		$("#formQuery").form("clear");
	};
	
	//新增
	function appendRow() {
		$("#gridResult").datagrid("appendRow", {
			countryCode : "CN",
			countryCodeName : "中国"
		});
	};
	
	//插入
	function insertRow() {
		$("#gridResult").datagrid("insertRow", {
			index : $("#gridResult").datagrid("getSelectedIndex"),
			row : {
				countryCode : "CN",
				countryCodeName : "中国"
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
		TestProvinceManager.saveAll(rows, function(result) {
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
	
	//测试Map
	function testMap() {
		TestProvinceManager.testMap(function(result) {
			alert(result);
		});
	}
	
	$(function() {
		//查询数据后自动开始grid编辑
		$("#gridResult").datagrid("options").onLoadSuccess = function(row, data) {
			$.fn.datagrid.defaults.onLoadSuccess.apply(this, [row, data]);
			$("#gridResult").datagrid("selectRow", 0).datagrid("beginEdit", 0);
		};
		
		//开始编辑行，自动定位到第一列
		$("#gridResult").datagrid("options").onBeginEdit = function(rowIndex, rowData) {
			$.fn.datagrid.defaults.onBeginEdit.apply(this, [rowIndex, rowData]);
			$("#gridResult").datagrid("getColumnEditor", "provinceCode").select();
			//自动填写国家名称
			$("#gridResult").datagrid("getColumnEditor", "countryCode").combo("options").onHidePanel = function () {
				$.fn.combogrid.defaults.onHidePanel.apply(this);
				$("#gridResult").datagrid("getColumnEditor", "countryCodeName")[0].value = $(this).combo("getText");
			};
		};

		//编辑form中自动填写国家名称
		$("#formEdit input[comboname='countryCode']").combogrid("options").onHidePanel = function () {
			$.fn.combogrid.defaults.onHidePanel.apply(this);
			$("#formEdit input[name='countryCodeName']")[0].value = $(this).combogrid("getText");
		};

	});
	
</script>
</head>

<body class="easyui-layout">
	
	<div region="north" title="查询条件" iconCls="icon-search">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="query()">查询</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="clearQueryForm()">重置</a>
			<a class="easyui-linkbutton" onclick="testMap()">测试Map</a>
		</div>
		<form id="formQuery" class="easyui-form" columns="3" i18nRoot="BasProvince">
			<input name="provinceCode" operator="ilikeAnywhere"/>
			<input name="provinceName" operator="ilikeAnywhere"/>
			<input name="provinceNameEn" operator="ilikeAnywhere"/>
			<input name="countryCode" class="easyui-combogrid" codetype="ALL_COUNTRY"/>
			<input name="status" class="easyui-combobox" codetype="STATUS"/>
		</form>
	</div>
	
	<div region="center" title="省份信息" iconCls="icon-edit">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow()">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-redo" onclick="insertRow()">插入</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="editRow()">编辑</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedRows()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()">取消</a>
		</div>
		<table id="gridResult" class="easyui-datagrid" i18nRoot="BasProvince" fit="true">
			<thead>
				<tr>
					<th field="provinceCode" editor="{type:'validatebox', options:{required:true}}"/>
					<th field="provinceName" editor="{type:'validatebox', options:{required:true}}"/>
					<th field="provinceNameEn" editor="{type:'validatebox', options:{required:true}}"/>
					<th field="provinceCapital" editor="text" title="省会"/>
					<th field="countryCode" editor="combogrid" codetype="ALL_COUNTRY"/>
					<th field="countryCodeName" editor="text"/>
					<th field="remark" editor="text" width="200"/>
					<th field="status" editor="{type:'combobox', options:{required:true}}" codetype="STATUS"/>
				</tr>
			</thead>
		</table>
	</div>
		
	<!-- 弹出窗口编辑 -->
	<div id="dialogEdit" class="easyui-dialog" title="省份信息" iconCls="icon-edit"
			style="width:600px;padding:10px" closed="true" modal="true">
		<form id="formEdit" class="easyui-form" columns="2" i18nRoot="BasProvince">
			<input name="provinceCode" class="easyui-validatebox" required="true"/>
			<input name="provinceName" class="easyui-validatebox" required="true"/>
			<input name="provinceNameEn" class="easyui-validatebox" required="true"/>
			<input name="provinceCapital"/>
			<input name="countryCode" class="easyui-combogrid" codetype="ALL_COUNTRY"/>
			<input name="countryCodeName"/>
			<input name="status" class="easyui-combobox" codetype="STATUS" required="true"/>
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