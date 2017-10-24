<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>
<script type="text/javascript">
	
	//查询
	function query() {
		
		$("#gridResult").datagrid("commonQuery", {
			queryType : "BasCityModel",
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
		BasCityManager.saveAll(rows, function(result) {
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
			$("#gridResult").datagrid("getColumnEditor", "cityCode").select();
			
			//自动填写省份名称
			$("#gridResult").datagrid("getColumnEditor", "provinceCode").combo("options").onHidePanel = function () {
				$.fn.combogrid.defaults.onHidePanel.apply(this);
				$("#gridResult").datagrid("getColumnEditor", "provinceName")[0].value = $(this).combo("getText");
			};
			
			//自动填写国家名称
			$("#gridResult").datagrid("getColumnEditor", "countryCode").combo("options").onHidePanel = function () {
				$.fn.combogrid.defaults.onHidePanel.apply(this);
				$("#gridResult").datagrid("getColumnEditor", "countryName")[0].value = $(this).combo("getText");
			};
		};
		
		
		//弹出窗口自动填写省份名称
		$("#provinceCode").combogrid("options").onHidePanel = function (value) {
			$("#provinceName").val($("#provinceCode").combogrid("getText"));
		};
		
		
		//弹出窗口自动填写国家名称
		$("#countryCode").combogrid("options").onHidePanel = function (value) {
			$("#countryName").val($("#countryCode").combogrid("getText"));
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
		<form id="formQuery" class="easyui-form" columns="4" i18nRoot="BasCity">
			<input name="cityCode"/>
			<input name="cityName" operator="ilikeAnywhere"/>
			<input name="cityNameEn" operator="ilikeAnywhere"/>
			<input name="provinceCode" class="easyui-combogrid" codetype="ALL_PROVINCE"/>
			<input name="provinceName" operator="ilikeAnywhere"/>
			<input name="countryCode" class="easyui-combogrid" codetype="ALL_COUNTRY"/>
			<input name="countryName" operator="ilikeAnywhere"/>
			<input name="status" class="easyui-combobox" codetype="STATUS" editable="false"/>
		</form>
	</div>
	
	<div region="center" title="城市信息" iconCls="icon-edit">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow()">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-redo" onclick="insertRow()">插入</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="editRow()">编辑</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedRows()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()">取消</a>
		</div>
		<table id="gridResult" class="easyui-datagrid" i18nRoot="BasCity" fit="true">
			<thead>
				<tr>
					<th field="cityCode" editor="{type:'validatebox', options:{required:true, validType:'length[0,50]'}}"/>
					<th field="cityName" editor="{type:'validatebox', options:{required:true, validType:'length[0,150]'}}"/>
					<th field="cityNameEn" editor="{type:'validatebox', options:{required:true, validType:'length[0,150]'}}"/>					
					<th field="provinceCode" editor="{type:'combogrid', options:{required:true}}" codetype="ALL_PROVINCE"/>
					<th field="provinceName" editor="{type:'validatebox', options:{validType:'length[0,150]'}}"/>
					<th field="countryCode" editor="{type:'combogrid', options:{required:true}}" codetype="ALL_COUNTRY"/>
					<th field="countryName" editor="{type:'validatebox', options:{validType:'length[0,150]'}}"/>
					<th field="zipcode" editor="{type:'validatebox', options:{validType:'length[0,50]'}}"/>
					<th field="districtNum" editor="{type:'validatebox', options:{validType:'length[0,20]'}}"/>
					<th field="longitude" editor="{type:'validatebox', options:{validType:'length[0,50]'}}"/>
					<th field="latitude" editor="{type:'validatebox', options:{validType:'length[0,50]'}}"/>
					<th field="remark" editor="{type:'validatebox', options:{validType:'length[0,150]'}}"/>
					<th field="status" editor="{type:'combobox', options:{editable:false,required:true}}" codetype="STATUS"/>					
					<th field="centerCode" editor="{type:'validatebox', options:{validType:'length[0,50]'}}"/>
				</tr>
			</thead>
		</table>
	</div>
	
	<!-- 弹出窗口编辑 -->
	<div id="dialogEdit" class="easyui-dialog" title="城市信息" iconCls="icon-edit"
			style="width:600px;padding:10px" closed="true" modal="true">
		<form id="formEdit" class="easyui-form" columns="2" i18nRoot="BasCity">
			<input name="cityCode" required="true"/>
			<input name="cityName" class="easyui-validatebox" valiType="length:[0,50]" required="true"/>
			<input name="cityNameEn" class="easyui-validatebox" valiType="length:[0,150]"/>	
			<input id="provinceCode" name="provinceCode" class="easyui-combogrid" codetype="ALL_PROVINCE" required="true" onchange="autoGetProvinceName(this.value)"/>
			<input id="provinceName" name="provinceName" class="easyui-validatebox" valiType="length[0,150]"/>
			<input id="countryCode" name="countryCode" class="easyui-combogrid" codetype="ALL_COUNTRY" required="true" onchange="autoGetCountryName(this.value)"/>
			<input id="countryName" name="countryName" class="easyui-validatebox" valiType="length[0,150]"/>
			<input name="zipcode" class="easyui-validatebox" valiType="length:[0,50]"/>
			<input name="districtNum" class="easyui-validatebox" valiType="length:[0,20]"/>
			<input name="longitude" class="easyui-validatebox" valiType="length:[0,50]"/>
			<input name="latitude" class="easyui-validatebox" valiType="length:[0,50]"/>
			<input name="remark" class="easyui-validatebox" valiType="length:[0,150]"/>
			<input name="status" class="easyui-combobox" codetype="STATUS" editable="false" required="true"/>
			<input name="centerCode" class="easyui-validatebox" valiType="length:[0,50]"/>
		</form>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="editDialogOk()">确定</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="editDialogCancel()">取消</a>
		</div>
	</div>
</body>
</html>	