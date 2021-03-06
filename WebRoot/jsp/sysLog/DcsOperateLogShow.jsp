<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
			queryType : "DcsOperateLogNoPrintQuery",
			paramForm : "formQuery"
		});
	};

	//查看数据
	function editRow() {
		var row = $("#dcsOperateLog").datagrid("endEdit").datagrid(
				"getSelected");
		var currentValue = $.parseJSON(row.currentValue);
		var modifiedValue = $.parseJSON(row.modifiedValue);

		var oldstr = "";
		var newstr = "";
		for ( var key in currentValue) {
			oldstr += " {field:'" + key + "',title:'" + key + "',width:100}";
			//	oldstr+=" {field:'"+key+"',title:'"+ currentValue[key]+"',width:100}";
			oldstr += ","
		}

		if (oldstr.trim().length > 0) {
			oldstr = oldstr.substr(0, oldstr.length - 1);
		}
		for ( var key in modifiedValue) {
			newstr += " {field:'" + key + "',title:'" + key + "',width:100}";
			//	newstr+=" {field:'"+key+"',title:'"+ modifiedValue[key]+"',width:100}";
			newstr += ","
		}
		if (newstr.trim().length > 0) {
			newstr = newstr.substr(0, newstr.length - 1);
		}
		
		oldstr = "[" + oldstr + "]";
		newstr = "[" + newstr + "]";

		if (row) {
			$("#dialogEdit").dialog("open");
			$("#formEdit").form("setData", row);
		}

		
		$('#olddcsOperateLog').datagrid({
			columns : [ eval(oldstr) ]
		});

		$('#olddcsOperateLog').datagrid("loadData", {
			rows : [ currentValue ]
		});
		
		$("#newdcsOperateLog").datagrid({
			columns : [ eval(newstr) ]
		});
		$('#newdcsOperateLog').datagrid("loadData", {
			rows : [ modifiedValue ]
		});
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
		//查询数据后自动开始grid编辑
		$("#dcsOperateLog").datagrid("options").onLoadSuccess = function(row,
				data) {
			$.fn.datagrid.defaults.onLoadSuccess.apply(this, [ row, data ]);
			$("#dcsOperateLog").datagrid("selectRow", 0).datagrid("beginEdit",
					0);
		};
	});
</script>
</head>

<body class="easyui-layout">
	<div region="north" title="系统日志" iconCls="icon-search" style="height: 100px">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="query()" key="Q">查询</a> <a class="easyui-linkbutton"
				iconCls="icon-reload" onclick="clearQueryForm()" key="D">重置</a>
		</div>
		<form id="formQuery" class="easyui-form" columns="4" i18nRoot="DcsOperateLog">
			<input name="businessRefNo" title="业务单号" /> <input name="dateSourceEntity" title="实体名称" /> <input
				name="opeationType" class="easyui-combobox" title="操作类型" codetype="OPERATE_TYPE" />
		</form>
	</div>
	<div region="center" border="false">
		<div fit="true" class="easyui-layout">
			<div region="center" iconCls="icon-edit" style="height: 130px" title="日志信息">
				<div class="datagrid-toolbar">
					<span class="panel-header panel-title" style="float: left; border-style: none; width: 70px;">日志信息</span> <a
						class="easyui-linkbutton" iconCls="icon-edit" onclick="editRow()">查看</a> <a class="easyui-linkbutton"
						iconCls="icon-remove" onclick="deleteSelectedRows()">删除</a> <a class="easyui-linkbutton" iconCls="icon-save"
						onclick="save(true)">保存</a> <a class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()">取消</a>
				</div>

				<table id="dcsOperateLog" class="easyui-datagrid" fit="true" pagination="true">
					<thead>
						<tr>
							<!-- 	<th field="logUuid" title="日志id" />
							<th field="dataSourceUuid" title="数据主键id" />
							<th field="dateSourceEntity" title="实体名称" /> -->
							<th field="opeationType" title="操作类型" codetype="OPERATE_TYPE" />
							<th field="businessRefNo" title="业务单号" />
							<th field="creator" title="修改人" />
							<th field="createTime" title="修改时间" />
							<th field="currentModifier" title="原记录的修改人" />
							<th field="currentModifyTime" title="原记录的修改时间" />
							<th field="currentValue" title="修改之前的值" />
							<th field="modifiedValue" title="修改之后的值" />
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<!-- 弹出窗口编辑 -->
	<div id="dialogEdit" class="easyui-dialog" title="查看日志信息" iconCls="icon-edit"
		style="width: 900px; height: 500px; padding: 10px" closed="true" modal="true">
		<!-- <form id="formEdit" class="easyui-form" columns="2" i18nRoot="DcsOperateLog">
			<input name="dataSourceUuid" title="数据主键id" readonly="readonly" style="width: 300px;" />
			 <input name="dateSourceEntity" title="实体名称" readonly="readonly" style="width: 300px;" />
			  <input name="opeationType" title="操作类型" readonly="readonly" style="width: 300px;" />
				<input name="businessRefNo" title="业务单号" readonly="readonly" style="width: 300px;" />
				 <input name="currentModifier"	title="原纪录的修改人" readonly="readonly" codetype="ALL_CUSTOMER" style="width: 300px;" />
				 <input name="currentModifyTime" title="原纪录的修改时间" readonly="readonly" style="width: 300px;" />
				 <input name="modifyTime" title="记录修改时间" readonly="readonly" style="width: 300px;" />
				 <input name="modifier" title="纪录的修改人" readonly="readonly" style="width: 300px;" />
			<textarea name="currentValue" title="修改之前的值" colspan="2" style="width: 100%" rows="10"></textarea>
			<textarea name="modifiedValue" title="修改之后的值" colspan="2" style="width: 100%" rows="10"></textarea>
			<input name="logUuid" type="hidden" />
		</form> -->
		<div style="padding: 8px; font-size: 15px">
			<b>原来的值:</b>
		</div>
		<div style="height: 100px">
			<table id="olddcsOperateLog" class="easyui-datagrid" fit="true" pagination="true" height="100" style="height: 100px">
				<thead>
					<tr>


					</tr>
				</thead>
			</table>
		</div>
		<div style="padding: 8px; font-size: 15px">
			<b>修改后的值:</b>
		</div>
		<div style="height: 100px">
			<table id="newdcsOperateLog" class="easyui-datagrid" fit="true" pagination="true" height="100" style="height: 100px">
				<thead>
					<tr>


					</tr>
				</thead>
			</table>
		</div>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="editDialogCancel()">关闭</a>
		</div>
	</div>
</body>

</html>