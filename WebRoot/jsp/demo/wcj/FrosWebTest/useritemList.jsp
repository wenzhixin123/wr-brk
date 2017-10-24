<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>

<script type="text/javascript">
	
	//查询
	function query() {
		$("#gridResult").datagrid("commonQuery", {
			queryType : "SysUserModel",
			paramForm : "formQuery"
		});
		$("#gridResultGrouped").datagrid("commonQuery", {
			queryType : "SysUserModel",
			paramForm : "formQuery"
		});
		$("#treegridResult").treegrid("commonQuery", {
			queryType : "SysUserModel",
			paramForm : "formQuery"
		});
	};
	
	//重置
	function clearQueryForm() {
		$("#formQuery").form("clear");
	};
	
	//新增
	function appendRow() {
		$("#gridResult").datagrid("appendRow", {});
	};
	
	//插入
	function insertRow() {
		$("#gridResult").datagrid("insertRow", {
			index : $("#gridResult").datagrid("getSelectedIndex"),
			row : {}
		});
	};
	
	//编辑
	function editRow() {
		var row = $("#gridResult").datagrid("endEdit").datagrid("getSelected");
		if (row) {
			$("#dialogEdit").data("fromGrid", $("#gridResult"));
			$("#dialogEdit").dialog("open").dialog("setTitle", "编辑用户信息");
			$("#formEdit").form("setData", row).form("readonly", false);
		}
	};
	
	//删除
	function deleteSelectedRows() {
		$("#gridResult").datagrid("deleteSelectedRows");
	};
	
	//查看
	function viewRow() {
		var row = $("#gridResult").datagrid("endEdit").datagrid("getSelected");
		if (row) {
			$("#dialogEdit").dialog("open").dialog("setTitle", "查看用户信息");
			$("#formEdit").form("setData", row).form("readonly");
		}
	}
	
	//保存
	function save() {
		var rows = $("#gridResult").datagrid("getChanges");
		if (rows.length == 0) {
			$.messager.alert("提示", "未修改数据", "warning");
			return;
		}
		SysUserManager.saveAll(rows, function(result) {
			$("#gridResult").datagrid("refreshSavedData", result);
			$.messager.alert("提示", "保存成功", "info");
		});
	};
	
	//取消
	function reload() {
		$("#gridResult").datagrid("reload");
	};
	
	var codeIndex = 1;
	
	//treegrid新增
	function appendRowTreegrid() {
		var selected = $("#treegridResult").treegrid("getSelected");
		$("#treegridResult").treegrid("append", {
			parent : selected ? selected.userUuid : null,
			data : {}
		});
	};
	
	//treegrid编辑
	function editRowTreegrid() {
		var row = $("#treegridResult").treegrid("endEdit").treegrid("getSelected");
		if (row) {
			$("#dialogEdit").data("fromGrid", $("#treegridResult"));
			$("#dialogEdit").dialog("open").dialog("setTitle", "编辑用户信息");
			$("#formEdit").form("setData", row).form("readonly", false);
		}
	};
	
	//treegrid删除
	function deleteSelectedRowsTreegrid() {
		$("#treegridResult").treegrid("removeSelectedNodes");
	};
	
	//treegrid查看
	function viewRowTreegrid() {
		var row = $("#treegridResult").treegrid("endEdit").treegrid("getSelected");
		if (row) {
			$("#dialogEdit").dialog("open").dialog("setTitle", "查看用户信息");
			$("#formEdit").form("setData", row).form("readonly");
		}
	}
	
	//treegrid保存
	function saveTreegrid() {
		var rows = $("#treegridResult").treegrid("getChanges");
		if (rows.length == 0) {
			$.messager.alert("提示", "未修改数据", "warning");
			return;
		}
		SysUserManager.saveAllInTree(rows, function(result) {
			$("#treegridResult").treegrid("refreshSavedData", result);
			$.messager.alert("提示", "保存成功", "info");
		});
	};
	
	//treegrid取消
	function reloadTreegrid() {
		$("#treegridResult").treegrid("reload");
	};
	
	//编辑窗口确定
	function editDialogOk() {
		$("#dialogEdit").dialog("close");
		var $datagrid = $("#dialogEdit").data("fromGrid");
		if ($datagrid.hasClass("easyui-treegrid")) {
			$datagrid.treegrid("updateRow", {
				id : $datagrid.treegrid("getSelectedId"),
				row : $("#formEdit").form("getData")
			});
		} else {
			$datagrid.datagrid("updateRow", {
				index : $datagrid.datagrid("getSelectedIndex"),
				row : $("#formEdit").form("getData")
			});
		}
	};
	
	//编辑窗口取消
	function editDialogCancel() {
		$("#dialogEdit").dialog("close");
	};
	
	//grid数据格式化
	function dateFieldFormatter(value, rowData, rowIndex) {
		//只显示年月日
		return value ? value.substring(0, 10) : value;
	};

	$(function() {
		//设置grid固定查询条件
		/*
		$("#gridResult").datagrid("setQueryFields", [{
			//只查询有效用户
			fieldName : "status",
			fieldValue : "Active"
		}, {
			//只查询当前用户所在公司的用户
			fieldName : "officeCode",
			fieldType : "session",
			fieldStringValue : "officeCode"
		}]);
		*/
		/*
		//查询条件中公司和部门选择联动
		$("#formQuery input[comboname='officeCode']").combogrid("options").onHidePanel = function () {
			//IMPORTANT: 执行默认事件
			$.fn.combogrid.defaults.onHidePanel.apply(this);
			$("#formQuery input[comboname='departmentCode']").combogrid("setQueryFields", [{
				fieldName : "preOfficCode",
				fieldValue : $(this).combogrid("getValue")
			}])
			.combogrid("grid").datagrid("load");
		};
		
		//grid编辑中公司和部门选择联动
		$("#gridResult").datagrid("options").onBeforeEdit = function(rowIndex, rowData) {
			//IMPORTANT: 执行默认事件
			$.fn.datagrid.defaults.onBeforeEdit.apply(this, [rowIndex, rowData]);
			$("#gridResult").datagrid("getColumnEditor", "departmentCode").combogrid("setQueryFields", [{
				fieldName : "preOfficCode",
				fieldValue : rowData.officeCode
			}])
			.combogrid("grid").datagrid("load");
			//这里用 .combogrid("options").onHidePanel 则不会触发，原因未知
			$("#gridResult").datagrid("getColumnEditor", "officeCode").combo("options").onHidePanel = function () {
				//IMPORTANT: 执行默认事件
				$.fn.combogrid.defaults.onHidePanel.apply(this);
				$("#gridResult").datagrid("getColumnEditor", "departmentCode").combogrid("setQueryFields", [{
					fieldName : "preOfficCode",
					fieldValue : $(this).combogrid("getValue")
				}])
				.combogrid("grid").datagrid("load");
			};
		};
		
		//编辑窗口中公司和部门选择联动
		$("#formEdit input[comboname='officeCode']").combogrid("options").onHidePanel = function () {
			//IMPORTANT: 执行默认事件
			$.fn.combogrid.defaults.onHidePanel.apply(this);
			$("#formEdit input[comboname='departmentCode']").combogrid("setQueryFields", [{
				fieldName : "preOfficCode",
				fieldValue : $(this).combogrid("getValue")
			}])
			.combogrid("grid").datagrid("load");
		};
		
		$("#dialogEdit").dialog("options").onOpen = function () {
			//IMPORTANT: 执行默认事件
			$.fn.dialog.defaults.onOpen.apply(this);
			$("#formEdit input[comboname='departmentCode']").combogrid("setQueryFields", [{
				fieldName : "preOfficCode",
				fieldValue : $("#gridResult").datagrid("getSelected").officeCode
			}])
			.combogrid("grid").datagrid("load");
		};
		*/
	});
	
</script>
</head>

<body class="easyui-layout">
	
	<div region="north" title="查询条件" iconCls="icon-search">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="query()">查询</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="clearQueryForm()">重置</a>
		</div>
		<form id="formQuery" class="easyui-form" columns="3" i18nRoot="SysUser">
		<!-- 支持参数orientation 可为 horizontal(default) 或者 vertical -->
		<!-- i18nRoot 可写多个，逗号分隔，下面input如果有title属性，则优先使用 -->
			<input name="userName" operator="ilikeAnywhere"/>
			<input name="userType" class="easyui-combobox" codeType="USER_TYPE"/>
			<input name="status" class="easyui-combobox" codeType="STATUS" multiple="true" operator="in"/>
			<div>
				<input name="officeCode" class="easyui-combogrid" codeType="ALL_ORGS"/>
				<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick=""></a>
			</div>
			<div>
				<input name="departmentCode" class="easyui-combogrid" codeType="ALL_DEPARTMENTS" multiple="true" operator="in"/>
				<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick=""></a>
			</div>
			<div>
				<input name="createTime" class="easyui-datebox" operator="dateBegin" style="width:85px;"/> -
				<input name="createTime" class="easyui-datebox" operator="dateEnd" style="width:85px;"/>
			</div>
		</form>
	</div>
	
	<div region="center">
		<div class="easyui-tabs" fit="true" border="false">
			<div title="用户信息" iconCls="icon-edit">
				<div class="datagrid-toolbar">
					<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow()">新增</a>
					<a class="easyui-linkbutton" iconCls="icon-redo" onclick="insertRow()">插入</a>
					<a class="easyui-linkbutton" iconCls="icon-edit" onclick="editRow()">编辑</a>
					<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedRows()">删除</a>
					<a class="easyui-linkbutton" iconCls="icon-tip" onclick="viewRow()">查看</a>
					<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
					<a class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()">取消</a>
				</div>
				<table id="gridResult" class="easyui-datagrid" i18nRoot="SysUser" fit="true">
					<thead>
						<tr>
							<th field="userCode" editor="text"/>
							<th field="userName" editor="text"/>
							<th field="userNameEn" editor="text"/>
							<th field="userType" editor="combobox" codeType="USER_TYPE"/>
							<th field="status" editor="combobox" codeType="STATUS"/>
							<th field="officeCode" editor="combogrid" codeType="ALL_ORGS"/>
							<th field="departmentCode" editor="combogrid" codeType="ALL_DEPARTMENTS"/>
							<th field="createTime"/>  <!-- formatter="dateFieldFormatter"    grid数据格式化 --> 
							<th field="modifyTime"/>  <!-- formatter="dateFieldFormatter"    grid数据格式化  -->
							<th field="recVer" align="right"/>
							
							<th field="position" editor="text"/>
							<th field="firstName" editor="text"/>
							<th field="middleName" editor="text"/>
							<th field="lastName" editor="text"/>
							<th field="sex" editor="text"/>
							<th field="personalInfo" editor="text"/>
							<th field="personalPic" editor="text"/>
							<th field="idCard" editor="text"/>
							<th field="marital" editor="text"/>
							<th field="contactAddress" editor="text"/>
							<th field="nativeAddress" editor="text"/>
							<th field="homeAddress" editor="text"/>
							<th field="education" editor="text"/>
							<th field="telephone1" editor="text"/>
							<th field="telephone2" editor="text"/>
							<th field="fax" editor="text"/>
							<th field="bpcall" editor="text"/>
							<th field="mobile" editor="text"/>
							<th field="zipCode" editor="text"/>
							<th field="hireDate" editor="text"/>
							<th field="fireDate" editor="text"/>
							<th field="homeTelphone" editor="text"/>
							<th field="birthDay" editor="text"/>
							<th field="email" editor="text"/>
							<th field="password" editor="text"/>
							<th field="customerCode" editor="text"/>
							<th field="defaultLang" editor="text"/>
							<th field="remark" editor="text"/>
						</tr>
					</thead>
				</table>
			</div>
		
			<div title="用户信息（分组）" iconCls="icon-edit">
				<table id="gridResultGrouped" class="easyui-datagrid" i18nRoot="SysUser" fit="true"
						sortName="officeCode, departmentCode" pagination="false"
						groupField="officeCode, departmentCode">
					<thead>
						<tr>
							<th field="officeCode" codeType="ALL_ORGS"/>
							<th field="departmentCode" codeType="ALL_DEPARTMENTS"/>
							<th field="userCode" editor="text"/>
							<th field="userName" editor="text"/>
							<th field="userNameEn" editor="text"/>
							<th field="userType" editor="combobox" codeType="USER_TYPE"/>
							<th field="status" editor="combobox" codeType="STATUS"/>
							<th field="createTime"/>  <!-- formatter="dateFieldFormatter"    grid数据格式化 --> 
							<th field="modifyTime"/>  <!-- formatter="dateFieldFormatter"    grid数据格式化  -->
							<th field="recVer" align="right"/>
						</tr>
					</thead>
				</table>
			</div>
		
			<div title="用户信息（TreeGrid）" iconCls="icon-edit">
				<div class="datagrid-toolbar">
					<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRowTreegrid()">新增</a>
					<a class="easyui-linkbutton" iconCls="icon-edit" onclick="editRowTreegrid()">编辑</a>
					<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedRowsTreegrid()">删除</a>
					<a class="easyui-linkbutton" iconCls="icon-tip" onclick="viewRowTreegrid()">查看</a>
					<a class="easyui-linkbutton" iconCls="icon-save" onclick="saveTreegrid()">保存</a>
					<a class="easyui-linkbutton" iconCls="icon-reload" onclick="reloadTreegrid()">取消</a>
				</div>
				<table id="treegridResult" class="easyui-treegrid" i18nRoot="SysUser" fit="true"
						pagination="false"
						idField="userUuid" parentIdField="preUserUuid" treeField="userName">
					<thead>
						<tr>
							<th field="userName"/>
							<th field="userCode" editor="text"/>
							<th field="userNameEn" editor="text"/>
							<th field="userType" editor="combobox" codeType="USER_TYPE"/>
							<th field="status" editor="combobox" codeType="STATUS"/>
							<th field="officeCode" editor="combogrid" codeType="ALL_ORGS"/>
							<th field="departmentCode" editor="combogrid" codeType="ALL_DEPARTMENTS"/>
							<th field="createTime"/>  <!-- formatter="dateFieldFormatter"    grid数据格式化 --> 
							<th field="modifyTime"/>  <!-- formatter="dateFieldFormatter"    grid数据格式化  -->
							<th field="recVer" align="right"/>
						</tr>
					</thead>
				</table>
			</div>
		
			
		</div>
	</div>
	
	<!-- 弹出窗口编辑 -->
	<div id="dialogEdit" class="easyui-dialog" title="用户信息" iconCls="icon-edit"
			style="width:900px;padding:10px" closed="true" modal="true">
		<form id="formEdit" class="easyui-form" columns="3" i18nRoot="SysUser">
			<input name="userCode"/>
			<input name="userName"/>
			<input name="userNameEn"/>
			<input name="userType" class="easyui-combobox" codeType="USER_TYPE"/>
			<input name="status" class="easyui-combobox" codeType="STATUS"/>
			<input name="officeCode" class="easyui-combogrid" codeType="ALL_ORGS"/>
			<input name="departmentCode" class="easyui-combogrid" codeType="ALL_DEPARTMENTS"/>
			<input name="position"/>
			<input name="firstName"/>
			<input name="middleName"/>
			<input name="lastName"/>
			<input name="sex"/>
			<input name="personalInfo"/>
			<input name="personalPic"/>
			<input name="idCard"/>
			<input name="marital"/>
			<input name="nativeAddress"/>
			<div></div>
			<input name="homeAddress" colspan="3"/>
			<input name="education"/>
			<input name="telephone1"/>
			<input name="telephone2"/>
			<input name="fax"/>
			<input name="bpcall"/>
			<input name="mobile"/>
			<input name="zipCode"/>
			<input name="hireDate"/>
			<input name="fireDate"/>
			<input name="homeTelphone"/>
			<input name="birthDay" class="easyui-datebox"/>
			<input name="email"/>
			<input name="password"/>
			<input name="customerCode"/>
			<input name="defaultLang"/>
			<input name="remark" colspan="3"/>
			<input name="userUuid" readonly="true"/>
			<input name="createTime" class="easyui-datetimebox" readonly="true"/>
			<input name="modifyTime" class="easyui-datetimebox" readonly="true"/>
			<input name="recVer" class="easyui-numberbox" readonly="true"/>
		</form>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="editDialogOk()">确定</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="editDialogCancel()">取消</a>
		</div>
	</div>
	
	
</body>
</html>