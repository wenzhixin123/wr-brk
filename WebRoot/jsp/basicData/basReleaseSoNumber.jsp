<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<%@page import="com.sinotrans.gd.wlp.util.StringUtil,
com.sinotrans.gd.wlp.util.OfficeCodeTypeEnum" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%	    

    SessionContextUserEntity currentUser = SessionContextUserEntity.currentUser();
	String officeCode=currentUser.getOfficeCode();
	String customerCode=currentUser.getCustomerCode();
	
	String ACTION_SINGLE_ENTITY = "SINGLE_ENTITY";
	
	String requestAction = request.getParameter("action")==null?null:request.getParameter("action");
	String requestEntityCode = request.getParameter("entityCode")==null?null:request.getParameter("entityCode");
	
	if(!StringUtil.isNull(requestAction) && !StringUtil.isNull(requestEntityCode)) {
		customerCode = requestEntityCode;
	}
	

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>放柜编号前缀配置</title>
<%@include file="/fros-easyui/common/include.jsp" %>
<script type="text/javascript">
	
	var officeCode = '';
	var customerCode = '';
	var preOfficeCode = '';
	var action = '<%=requestAction%>';
	
	//查询
	function query() {
		$("#gridResult").datagrid("commonQuery", {
			queryType : "BasCntrPrefixQuery",
			paramForm : "formQuery"
		});
	}
	
	//重置
	function clearQueryForm() {
		$("#formQuery").form("clear");
		$("#formQuery").form("setData", {
			cntrAdminCode : customerCode
		});
	};
	
	//新增
	function appendRow() {
		$("#gridResult").datagrid("appendRow", {
			cntrAdminCode : customerCode
		});
	};
	
	//编辑
	/**
	function editRow() {
		var row = $("#gridResult").datagrid("endEdit").datagrid("getSelected");
		if (row) {
			$("#dialogEdit").dialog("open");
			$("#formEdit").form("setData", row);
		}
	};
	*/
	
	//删除
	function deleteSelectedRows() {
		$("#gridResult").datagrid("deleteSelectedRows");
		
	};
	
	//生效
	function activeSelectedRowsStatus() {
		changeSelectedRowsStatus('Active');
	}
	
	//作废
	function cancelSelectedRowsStatus() {
		changeSelectedRowsStatus('Cancel');
	}
	
	//更改所选记录状态
	function changeSelectedRowsStatus(status) {
		var selections = $("#gridResult").datagrid("getSelections");
		if (selections.length > 0) {
			$.each(selections, function(index, selected) {
				var rowIndex = $("#gridResult").datagrid("getRowIndex", selected);

				$("#gridResult").datagrid("updateRow", {
					index : rowIndex,
					row : {
						status : status
					}
				});
			});
		};
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
		
		BasCodeDefManager.saveAllCntrAdminPrefix(rows, function(result) {
			$("#gridResult").datagrid("refreshSavedData", result);
			$.messager.alert("提示", "保存成功", "info");
		});
	};
	
	function setupCustomer() {
		customerCode = '<%=customerCode%>';
		$('#cntrAdminCode').combogrid("setValue",customerCode);
		$("#cntrAdminCode").combogrid("readonly",true);
	
		$("#gridResult").datagrid("options").onBeginEdit = function(rowIndex, rowData) {
			
			$(this).datagrid("getColumnEditor", "cntrAdminCode").combogrid("readonly", true);
			$(this).datagrid("getColumnEditor", "cntrAdminCode").combogrid("disabled", true);
		}
		$('.adminButton').linkbutton('forceDisable');
		
	}
	
	function setupAdmin() {
		if(action == '<%=ACTION_SINGLE_ENTITY%>') {
			customerCode = '<%=customerCode%>';
			$('#cntrAdminCode').combogrid("setValue",customerCode);
			$("#cntrAdminCode").combogrid("readonly",true);
			$("#gridResult").datagrid("getColumnOption", 'cntrAdminCode').editor = null;
		}
	}
	
	$(function() {
		$('#gridResult').datagrid('hideColumn','id');
		$('#gridResult').datagrid('fitColumnWidth');
		<% if(currentUser.isSuperAdmin()){
			out.print("setupAdmin();");
		}else{
			out.print("setupCustomer();");
		}
		%>

		/**
		SysOfficeManager.isMatchOfficeCodeTypeByString('9',function(result) {
			if(result) {//如果是管理员
				setupAdmin();
			} else {//如果是外部用户
				setupCustomer();
			}
		});
		*/
	});
	
	
</script>

</head>
  
  <body class="easyui-layout">
  	<div region="north" title="查询条件" iconCls="icon-search">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="query()">查询</a>
			<a class="easyui-linkbutton" iconCls="myCustomerIcon_clear" onclick="clearQueryForm()">重置</a>
		</div>
		<form id="formQuery" class="easyui-form" columns="3" i18nRoot="DcsFeeDef">
		    <% if(currentUser.isSuperAdmin()){ %>
			<input id="cntrAdminCode" name="cntrAdminCode" class="easyui-combogrid" codetype="ALL_CNTR_ADMIN"/>
			<%}else{ %>
			<input id="cntrAdminCode" name="cntrAdminCode" class="easyui-combogrid" codetype="ALL_CNTR_ADMIN"  disabled="disabled"/>
			<script type="text/javascript">
			   $(function(){
	 		     $('#cntrAdminCode').combogrid('setValue', '<%=customerCode%>');
	 			});
			</script>
			<%} %>
			<input name="status" class="easyui-combobox" codetype="STATUS"  editable=false/>
			
		</form>
	</div>
	
	<div region="center" title="控箱公司缩写管理" iconCls="icon-edit">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow()">新增</a>
			<a class="easyui-linkbutton adminButton" iconCls="myCustomerIcon_remove" onclick="deleteSelectedRows()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
			<a class="easyui-linkbutton adminButton" iconCls="icon-ok" onclick="activeSelectedRowsStatus()">生效</a>
			<a class="easyui-linkbutton adminButton" iconCls="myCustomerIcon_cancel" onclick="cancelSelectedRowsStatus()">作废</a>
		</div>
		<table id="gridResult" class="easyui-datagrid" i18nRoot="DcsFeeDef" fit="true" showcheck="false" showFooter="true" >
			<thead>
				<tr>
					<th field="id" />
					<th field="status" codetype="STATUS"   editor="{type:'combogrid', options:{required:true}}"  />
					<% if(currentUser.isSuperAdmin()){ %>
					<th field="cntrAdminCode" editor="{type:'combogrid',options:{required:true}}" codetype="ALL_CNTR_ADMIN"/>
					<%}else{ %>
					<th field="cntrAdminCode"    editor="{type:'combogrid',options:{required:true}}"  disabled="disabled" codetype="ALL_CNTR_ADMIN" />
					<%} %>
					<th field="prefix"   title="放柜编号前缀字母"  required=true 	editor="{type:'validatebox',options:{required:true}}" />
					
		
				</tr>
			</thead>
		</table>
		
	</div>
</body>
</html>	