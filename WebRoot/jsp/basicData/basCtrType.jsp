<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>
<script type="text/javascript">

	$.extend($.fn.validatebox.defaults.rules, { 
		maxLength: { 
			validator: function(value, param) { 
				return /^\d{1,12}(\.\d{1,6})?$/.test(value);
			}, 
			message: '数据必须符合数据库设计NUMBER(18,6)！'} 
		}); 
	
	$.extend($.fn.validatebox.defaults.rules, { 
		maxLengthTwo: { 
			validator: function(value, param) { 
				return /^\d{1,2}(\.\d?)?$/.test(value);
			}, 
			message: '数据必须符合数据库设计NUMBER(2,1)！'} 
		});

	//查询
	function query() {
		$("#gridResult").datagrid("commonQuery", {
			queryType : "BasCtrTypeModel",
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
		var selectedRows = $("#gridResult").datagrid("getSelections");
		for(var i=0; i<selectedRows.length; i++) {
			var rowStatus = selectedRows[i].status;
			if(rowStatus == "Active") {
				alert("不能删除状态为有效的数据！");
				return;
			}
		}
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
		BasCtrTypeManager.saveAll(rows, function(result) {
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
			$("#gridResult").datagrid("getColumnEditor", "typeCode").select();
		};

	});
	
</script>
</head>
  
  <body class="easyui-layout">
  	<div region="north" title="查询条件" iconCls="icon-search">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="query()">查询</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="clearQueryForm()">重置</a>
		</div>
		<form id="formQuery" class="easyui-form" columns="3" i18nRoot="BasCtrType">
			<input name="typeCode"  operator="ilikeAnywhere"/>
			<input name="typeDesc"/>
			<input name="payload"/>
			<input name="teu"/>
			<input name="ctrIso"/>
			<input name="status" class="easyui-combobox" codetype="STATUS" editable="false"/>
		</form>
	</div>
	
	<div region="center" title="箱型信息" iconCls="icon-edit">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow()">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-redo" onclick="insertRow()">插入</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="editRow()">编辑</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedRows()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()">取消</a>
		</div>
		<table id="gridResult" class="easyui-datagrid" i18nRoot="BasCtrType" fit="true">
			<thead>
				<tr>
					<th field="typeCode" editor="{type:'validatebox', options:{required:true}}"/>
					<th field="typeDesc" editor="{type:'validatebox', options:{required:true}}"/>
					<th field="payload" title="有效载重（KG）" editor="{type:'validatebox', options:{validType:'maxLength[]'}}"/>
					<th field="teu" editor="{type:'validatebox', options:{validType:'maxLengthTwo[]'}}"/>
					<th field="ctrIso" editor="text"/>
					<th field="remark" editor="text" width="200"/>
					
					<th field="status" editor="{type:'combobox', options:{editable:false,required:true}}" codetype="STATUS"/>
				</tr>
			</thead>
		</table>
	</div>
	
	<!-- 弹出窗口编辑 -->
	<div id="dialogEdit" class="easyui-dialog" title="箱型信息" iconCls="icon-edit"
			style="width:600px;padding:10px" closed="true" modal="true">
		<form id="formEdit" class="easyui-form" columns="2" i18nRoot="BasCtrType">
			<input name="typeCode" class="easyui-validatebox" required="true"/>
			<input name="typeDesc" class="easyui-validatebox" required="true"/>
			<input name="payload" class="easyui-validatebox"/>
			<input name="teu" class="easyui-validatebox"/>
			<input name="ctrIso" class="easyui-validatebox"/>
			<input name="status" class="easyui-combobox" codetype="STATUS" required="true"  editable="false"/>
			<textarea name="remark" rowspan="2" colspan="2"></textarea>
		</form>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="editDialogOk()">确定</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="editDialogCancel()">取消</a>
		</div>
	</div>
</body>
</html>	