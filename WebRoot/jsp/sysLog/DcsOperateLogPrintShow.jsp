<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp"%>
<%@include file="/fros-easyui/common/buttonAuthorization.jsp"%>
<script type="text/javascript">
	/****liuw *****/
	//重置
	function clearQueryForm() {
		$("#formQuery").form("clear");
		//	setDefaultValue();
	};
	//查询
	function query() {
		$("#dcsOperateLog").datagrid("commonQuery", {
			queryType : "DcsOperateLogQuery",
			paramForm : "formQuery"
		});
	};

	//查看数据
	function editRow() {
		var row = $("#dcsOperateLog").datagrid("endEdit").datagrid(
				"getSelected");
		if (row) {
			$("#dialogEdit").dialog("open");
			$("#formEdit").form("setData", row);
		}
	};

	//删除
	function deleteSelectedRows() {
		$("#dcsOperateLog").datagrid("deleteSelectedRows");

	};

	//保存
	function save(b) {
		if (!$("#dcsOperateLog").datagrid("validate")) {
			$.messager.alert("提示", "数据验证错误", "warning");
			return;
		}
		var rows = $("#dcsOperateLog").datagrid("getChanges");
		if (rows.length == 0) {
			if (b) { //如果是保存按钮则提示 数据未改变   
				$.messager.alert("提示", "未修改数据", "warning");
			}
		} else {
			DcsOperateLogManager.saveAll(rows, function(result) {
				$("#dcsOperateLog").datagrid("refreshSavedData", result);
				$.messager.alert("提示", "保存成功", "info");
			});

		}
	};

	//取消
	function reload() {
		$("#dcsOperateLog").datagrid("reload");
	};

	//编辑窗口取消
	function editDialogCancel() {
		$("#dialogEdit").dialog("close");
	};
	$(function() {
		query();
		//查询数据后自动开始grid编辑
		$("#dcsOperateLog").datagrid("options").onLoadSuccess = function(row, data) {
			$.fn.datagrid.defaults.onLoadSuccess.apply(this, [ row, data ]);
			$("#dcsOperateLog").datagrid("selectRow", 0).datagrid("beginEdit", 0);
		};
		$("#businessRefNo").keyup(function(e){
			var str = $(this).val();
			str = str.toLocaleUpperCase();
			$(this).val(str);
		});
	});
</script>
</head>

<body class="easyui-layout">
	<div region="north" title="打印日志" iconCls="icon-search"
		style="height: 100px">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="query()"
				key="Q">查询</a> <a class="easyui-linkbutton" iconCls="icon-reload"
				onclick="clearQueryForm()" key="D">重置</a>
		</div>
		<form id="formQuery" class="easyui-form" columns="4"	i18nRoot="DcsOperateLog">
			<input name ="businessRefNo" title="业务单号"  id="businessRefNo"/>
			<input name="opeationType"  type="hidden" value="print"  /> 
			<!-- <input name="modifyTimeFrom"  title="修改时间" class="easyui-datebox"/>
			<input name="modifyTime"  title="至" class="easyui-datebox"/> -->
		</form>
	</div>
	<div region="center" border="false">
		<div fit="true" class="easyui-layout">
			<div region="center" iconCls="icon-edit" style="height: 130px"
				title="日志信息">
				<div class="datagrid-toolbar">
					<span class="panel-header panel-title"
						style="float: left; border-style: none; width: 70px;">日志信息</span>
					<!-- <!-- <a class="easyui-linkbutton" iconCls="icon-edit"
						onclick="editRow()">查看</a> 
						<a class="easyui-linkbutton"
						iconCls="icon-remove" onclick="deleteSelectedRows()">删除</a>
						
						
						 <a
						class="easyui-linkbutton" iconCls="icon-save" onclick="save(true)">保存</a>
					<a class="easyui-linkbutton" iconCls="icon-reload"
						onclick="reload()">刷新</a> -->
				</div>

				<table id="dcsOperateLog" class="easyui-datagrid" fit="true" pagination="true">
					<thead>
						<tr>
							<th field="dateSourceEntity" title="报表名称" />
							<th field="businessRefNo" title="业务单号" />
							<th field="modifiedValue" title="打印原因" />
							<th field="creator"  title="打印人"  codetype="ALL_USERS"/>

						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- 弹出窗口编辑 -->
	<!-- <div id="dialogEdit" class="easyui-dialog" title="查看日志信息"	iconCls="icon-edit" style="width: 900px; height:500px; padding: 10px" closed="true"
		modal="true">
		<form id="formEdit" class="easyui-form" columns="2"	i18nRoot="DcsOperateLog">
			<input name="dataSourceUuid" title="数据主键id" readonly="readonly" style="width: 300px;" />
			 <input	name="dateSourceEntity" title="实体名称" readonly="readonly"   style="width: 300px;" /> 
				<input	name="opeationType" title="操作类型" readonly="readonly"   style="width: 300px;" /> 
				<input	name="businessRefNo" title="业务单号" readonly="readonly"  style="width: 300px;"  /> 
				 <input name="currentModifier" title="原纪录的修改人" readonly="readonly" codetype="ALL_CUSTOMER"  style="width: 300px;"  />
				 <input name="currentModifyTime" title="原纪录的修改时间" readonly="readonly"  style="width: 300px;"  />
				 <input name="modifyTime" title="记录修改时间" readonly="readonly"  style="width: 300px;"  />
				 <input name="modifier" title="纪录的修改人" readonly="readonly"  style="width: 300px;"  />
				 <textarea	name="currentValue" title="修改之前的值"     colspan="2" style="width: 100%"  rows="10" ></textarea>
				<textarea name="modifiedValue" title="修改之后的值"   colspan="2"  style="width: 100%" rows="10"  ></textarea>
				<input name="logUuid" type="hidden" />
		</form>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-cancel" 	onclick="editDialogCancel()">关闭</a>
		</div>
	</div> -->
</body>

</html>