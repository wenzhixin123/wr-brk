<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>
<%@include file="/fros-easyui/common/buttonAuthorization.jsp" %>
<script type="text/javascript">

/****liuw *****/
 //重置
	function clearQueryForm() {
		$("#formQuery").form("clear");
	//	setDefaultValue();
	};
	//查询
	function query() {
		$("#dcsLogisticsDetail").datagrid("commonQuery", {
			queryType : "BasAccreditBoxQuery",
			paramForm : "formQuery"
		});
	};
	//新增
	function appendRow() {
		if (! $("#dcsLogisticsDetail").datagrid("validate")) {
			$.messager.alert("提示", "数据验证错误", "warning");
			return;
		}
		$("#dcsLogisticsDetail").datagrid("appendRow", {
			
		});
	};
	
	//编辑
	function editRow() {
		var row = $("#dcsLogisticsDetail").datagrid("endEdit").datagrid("getSelected");
		if (row) {
			$("#dialogEdit").dialog("open");
			$("#formEdit").form("setData", row);
		}
	};
	
	//删除
	function deleteSelectedRows() {
		$("#dcsLogisticsDetail").datagrid("deleteSelectedRows");
		
	};
	
	
	//保存
	function save(b) {
		if (! $("#dcsLogisticsDetail").datagrid("validate")) {
			$.messager.alert("提示", "数据验证错误", "warning");
			return;
		}
		var rows = $("#dcsLogisticsDetail").datagrid("getChanges");
		if (rows.length == 0) {
			if(b){ //如果是保存按钮则提示 数据未改变   
			$.messager.alert("提示", "未修改数据", "warning");
			}
		}else{
			//先查询控箱公司与委托单位在作业点是否有映射
			BasAccreditBoxManager.queryMapping(rows,function(result){
				if(result == null){
					BasAccreditBoxManager.saveAll(rows, function(result) {
						$("#dcsLogisticsDetail").datagrid("refreshSavedData", result);
						$.messager.alert("提示", "保存成功", "info");
					});
				}else{
					$.messager.alert("提示", result, "warning");
				}
			});
		}
	};
	
	//取消
	function reload() {
		$("#dcsLogisticsDetail").datagrid("reload");
	};
	//编辑窗口确定
	function editDialogOk() {
		if (! $("#formEdit").form("validate")) {
			$.messager.alert("提示", "数据验证错误", "warning");
			return;
		}
		$("#dialogEdit").dialog("close");
		$("#dcsLogisticsDetail").datagrid("updateRow", {
			index : $("#dcsLogisticsDetail").datagrid("getSelectedIndex"),
			row : $("#formEdit").form("getData")
		});
		save(false);
	};
	//编辑窗口取消
	function editDialogCancel() {
		$("#dialogEdit").dialog("close");
	};
	function copyCodeDef(){
		
		if (! $("#dcsLogisticsDetail").datagrid("validate")) {
			$.messager.alert("提示", "数据验证错误", "warning");
			return;
		}
		var row = $("#dcsLogisticsDetail").datagrid("endEdit").datagrid("getSelected");
		if(row){
			$("#dcsLogisticsDetail").datagrid("appendRow", {
				codeValue:row.codeValue,
				displayValue:row.displayValue,
				centerCode:row.centerCode,
				displayValueEn:row.displayValueEn,
				remark:row.remark
			});
		}else{
			$.messager.alert("提示", "请选择一条数据进行复制", "warning");
			return;
		}
	}
	$(function() {
		//查询数据后自动开始grid编辑
		//query();
	
		/* $("#dcsLogisticsDetail").datagrid("options").onLoad = function(row, data) {
			$.fn.datagrid.defaults.onLoadSuccess.apply(this, [row, data]);
			//$("#dcsLogisticsDetail").datagrid("selectRow", 0).datagrid("beginEdit", 0);
		}; */
		
		//开始编辑行，自动定位到第一列
		/* $("#dcsLogisticsDetail").datagrid("options").onBeginEdit = function(rowIndex, rowData) {
			$.fn.datagrid.defaults.onBeginEdit.apply(this, [rowIndex, rowData]);
			$("#dcsLogisticsDetail").datagrid("getColumnEditor", "typeCode").select();
		}; */

	});
 
</script> 
</head>

<body class="easyui-layout" >
	<div region="north" title="控箱授权" iconCls="icon-search" style="height:100px">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="query()" key="Q">查询</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="clearQueryForm()" key="D">重置</a>
		</div>
		<form id="formQuery"   class="easyui-form"   columns="2" i18nRoot="BasAccreditBox" >
			<input name="codeValue" title="委托单位" class="easyui-combogrid" codetype="ALL_CUSTOMER"/> 
			<input name="centerCode" title="作业点" class="easyui-combobox" codetype="PORT_AREA_CODE"/> 
		</form>
	</div>
	<div region="center" border="false">
		<div fit="true" class="easyui-layout">
			<div region="center" iconCls="icon-edit" style="height:130px" title="客户信息">
				<div class="datagrid-toolbar">
				<!--  	<span class="panel-header panel-title" style="float: left; border-style: none; width: 70px;">客户信息</span>-->
					<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow()" >新增</a>
					<a class="easyui-linkbutton" iconCls="icon-edit" onclick="editRow()" >编辑</a>
					<a class="easyui-linkbutton" iconCls="icon-save" onclick="save(true)" >保存</a>
					<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedRows()"  >删除</a>
					<a class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()"  >取消</a>
					<a class="easyui-linkbutton" iconCls="icon-edit" onclick="copyCodeDef()"  >复制</a>
				</div>
				<table id="dcsLogisticsDetail" class="easyui-datagrid" fit="true" pagination="true">
					<thead>
						<tr>
							<th field="codeValue" title="委托单位" editor="{type:'combogrid', options:{required:true}}" codetype="ALL_CNTR_ADMIN"/>
							<th field="displayValue" title="控箱公司" editor="{type:'combogrid', options:{required:true}}" codetype="ALL_CUSTOMER"/>
							<th field="centerCode" title="作业点" editor="{type:'combobox', options:{required:true}}" codetype="PORT_AREA_CODE"/>
							<th field="displayValueEn" title="业务类型" editor="{type:'combobox',options:{required:true}}" codetype="BUSINESS_TYPE"/>
							<th field="remark" title="作业项目" editor="{type:'combobox',options:{required:true}}"  codetype="DELIVERY_TYPE"/>
						</tr>  
					</thead>
				</table>
			</div>
			</div>
		</div>
		<!-- 弹出窗口编辑 -->
	<div id="dialogEdit" class="easyui-dialog" title="修改控箱授权" iconCls="icon-edit"
			style="width:600px;padding:10px" closed="true" modal="true">
		<form id="formEdit" class="easyui-form" columns="2" i18nRoot="BasAccreditBox">
			<input name="displayValue" title="控箱公司" class="easyui-combogrid" codetype="ALL_CNTR_ADMIN"/> 
			<input name="displayValueEn" title="业务类型" class="easyui-combobox"   codetype="BUSINESS_TYPE"/> 
			<input name="codeValue"  title="委托单位" class="easyui-combogrid"   codetype="ALL_CUSTOMER"/> 
			<input name="centerCode"  title="作业点" class="easyui-combobox" codetype="PORT_AREA_CODE"/> 
			<input name="remark"  title="作业项目" class="easyui-combobox" codetype="DELIVERY_TYPE"/> 
			<input name="basCodeDefUuid" type="hidden" /> 
		</form>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="editDialogOk()">确定</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="editDialogCancel()">取消</a>
		</div>
	</div>
</body>	

</html>