<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>
<script type="text/javascript">
	
	//entity 查询
	function queryByEntity(){
		BasCustomerManager.getCustomerEntity(getData(), function(result) {
			$("#gridResult").datagrid("loadData", {
				rows : result
			});
		});
	};
	
	//获取查询表单数据
	function getData() {
		return $("#formQuery").form("getData");
	};
	
	//查询
	function query() {
		$("#gridResult").datagrid("commonQuery", {
			queryType : "BasCustomerModel",
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
		row.mutAutoType = row.controlWord.substring(14,15);
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
		//alert(rows[0].customerType);
		if (rows.length == 0) {
			$.messager.alert("提示", "未修改数据", "warning");
			return;
		}
		BasCustomerManager.saveAll(rows, function(result) {
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
		var row = $("#formEdit").form("getData");
		if(row.mutAutoType!=null&&row.mutAutoType!=''){
			var controlWord = row.controlWord;
			row.controlWord = controlWord.substring(0,14)+row.mutAutoType+controlWord.substring(15);
		}
		$("#gridResult").datagrid("updateRow", {index : $("#gridResult").datagrid("getSelectedIndex"),row : row});
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
			//$("#gridResult").datagrid("getColumnEditor", "customerCode").select();
		};

	});

	function mapping(){
		var row = $("#gridResult").datagrid("endEdit").datagrid("getSelected");
		var title = "数据字典映射";
		var url = "jsp/dcs/dcsMappingInfo.jsp?tableName=BAS_CUSTOMER&basName="+row.customerName;
		
		parent.addTabs(title, url, "icon icon-nav", true);
		parent.addTabs(title, url, false, true); //21Aug, fix slow problem...
		if(parent.getTabIframe(title).contentWindow.queryDetail){
			parent.getTabIframe(title).contentWindow.queryDetail('BAS_CUSTOMER',row.customerName); //21Aug, fix slow problem...	
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
		<form id="formQuery" class="easyui-form" columns="4" i18nRoot="BasCustomer">
			<input name="customerCode" id="customerCode"/>
			<input name="customerName" id="customerName" operator="ilikeAnywhere" />
			<input name="mnemonicCode" id="mnemonicCode" operator="ilikeAnywhere" />
			<input name="aux3" id="aux3" operator="ilikeAnywhere" title="客户简称"/>
			<input name="siteCode" id="siteCode" />
			<input name="cityCode" id="cityCode" class="easyui-combogrid" codetype="ALL_CITY"/>
			<input name="provinceCode" id="provinceCode" class="easyui-combogrid" codetype="ALL_PROVINCE"/>
			<input name="countryCode" id="countryCode" class="easyui-combogrid" codetype="ALL_COUNTRY"/>
			<input name="status" id="status" class="easyui-combobox" codetype="STATUS"/>
			<input name="customerType" id="customerType" class="easyui-combogrid" codetype="CUSTOMER_TYPE" operator="ilikeAnywhere" title="客户类型"/>
		</form>
	</div>
	
	<div region="center" title="客户信息" iconCls="icon-edit">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow()">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-redo" onclick="insertRow()">插入</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="editRow()">编辑</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedRows()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()">取消</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="mapping()">映射</a>
		</div>
		<table id="gridResult" class="easyui-datagrid" i18nRoot="BasCustomer" fit="true">
			<thead>
				<tr>
					<th field="customerCode" editor="{type:'validatebox', options:{required:true}}"/>
					<th field="customerName" editor="text"/>
					<th field="customerType" title="客户类型" editor="{type:'combobox',options:{multiple:true}}" codetype="CUSTOMER_TYPE" width="200"/>
					<th field="customerNameEn" editor="text"/>
					<th field="mnemonicCode" editor="text"/>
					<th field="aux3" title="客户简称" editor="text"/>
					<th field="address1" editor="text"/>
					<th field="address2" editor="text"/>
					<th field="address3" editor="text"/>
					<th field="address4" editor="text"/>
					<th field="postalCode" editor="text"/>
					<th field="telNo1" editor="text"/>
					<th field="telNo2" editor="text"/>
					<th field="faxNo1" editor="text"/>
					<th field="faxNo2" editor="text"/>
					<th field="email" editor="text"/>
					<th field="countryCode" editor="combogrid" codetype="ALL_COUNTRY"/>
					<th field="provinceCode" editor="combogrid" codetype="ALL_PROVINCE"/>
					<th field="cityCode" editor="combogrid" codetype="ALL_CITY"/>
					<th field="siteCode" editor="text"/>
					<th field="centerCode" editor="text"/>
					<th field="aux1" editor="text"/>
					<th field="aux2" editor="text"/>
					<th field="aux4" editor="text"/>
					<th field="aux5" editor="text"/>
					<th field="officeCode" editor="text"/>
					<th field="remark" editor="text" width="200"/>
					<th field="status" editor="{type:'combobox', options:{editable:false,required:true}}" codetype="STATUS"/>
				</tr>
			</thead>
		</table>
	</div>
	
	<!-- 弹出窗口编辑 -->
	<div id="dialogEdit" class="easyui-dialog" title="客户信息" iconCls="icon-edit"
			style="width:600px;padding:10px" closed="true" modal="true">
		<form id="formEdit" class="easyui-form" columns="2" i18nRoot="BasCustomer">
			<input name="customerCode" class="easyui-validatebox" required="true"/>
			<input name="customerName" class="easyui-validatebox"/>
			<input name="customerNameEn" class="easyui-validatebox"/>
			<input name="mnemonicCode" class="easyui-validatebox"/>
			<input name="mutAutoType" title="舱单互认自动预配" class="easyui-combobox" codetype="MUTUAL_SENDTO_TYPE"/>
			<input name="address1" class="easyui-validatebox"/>
			<input name="address2" class="easyui-validatebox"/>
			<input name="address3" class="easyui-validatebox"/>
			<input name="address4" class="easyui-validatebox"/>
			<input name="postalCode" class="easyui-validatebox"/>
			<input name="telNo1" class="easyui-validatebox"/>
			<input name="telNo2" class="easyui-validatebox"/>
			<input name="faxNo1" class="easyui-validatebox"/>
			<input name="faxNo2" class="easyui-validatebox"/>
			<input name="email" class="easyui-validatebox"/>
			<input name="countryCode" class="easyui-combogrid" codetype="ALL_COUNTRY"/>
			<input name="provinceCode" class="easyui-combogrid" codetype="ALL_PROVINCE"/>
			<input name="cityCode" class="easyui-combogrid" codetype="ALL_CITY"/>
			<input name="siteCode" class="easyui-validatebox"/>
			<input name="centerCode" class="easyui-validatebox"/>
			<input name="customerType" title="客户类型" class="easyui-combobox" multiple="true" codetype="CUSTOMER_TYPE"/>
			<input name="aux1" class="easyui-validatebox"/>
			<input name="aux2" class="easyui-validatebox"/>
			<input name="aux3" class="easyui-validatebox"/>
			<input name="aux4" class="easyui-validatebox"/>
			<input name="aux5" class="easyui-validatebox"/>
			<input name="officeCode" class="easyui-validatebox"/>
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