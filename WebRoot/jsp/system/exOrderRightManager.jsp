<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.sinotrans.gd.wlp.util.StringUtil"%>
<%@page import="java.util.Date"%>
<%@ page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>
<%
	String sNowDate = StringUtil.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
	String userComp = SessionContextUserEntity.currentUser().getCustomerCode();
%>
<script type="text/javascript">

	var originalRowsArray;
	
	//查询
	function query() {
		$("#gridResult").datagrid("commonQuery", {
			queryType : "ExOrderRightQuery",
			paramForm : "formQuery"
		});
	};
	//重置
	function clearQueryForm() {
		$("#formQuery").form("clear");
		$("#input_transactionType").val("CFG");
	};
	
	//新增
	function appendRow() {
		if (!$("#gridResult").datagrid("validate")) {
			$.messager.alert("提示", "请输入必填项！", "warning");
			return;
		}
		
		$("#gridResult").datagrid("appendRow", {
			logisticsOrderNo:"xxxxxx",
			transactionType:"CFG",
		});
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
		
		//WLPDC-63
		for(var i = 0; i < rows.length; i++) {
			
			var originalRow;
			var controlword;
			
			originalRow = findOriginalRowById(rows[i].logisticsOrderId);
			
			//insert
			if(originalRow == null) {
				controlword = '000' + rows[i].logisticsControlWord + '0000000000000000';
			//update
			} else {
				controlword = 
					originalRow.logisticsControlWord.substring(0, 3) + 
					rows[i].logisticsControlWord +
					originalRow.logisticsControlWord.substring(4, 20);
			}
			
			rows[i].logisticsControlWord = controlword;
		}
		
		
		DcsLogisticsOrderManager.saveAll(rows, function(result) {
			$("#gridResult").datagrid("refreshSavedData", result);
			//WLPDC-63 TODO workaround solution, refresh data after save
			$("#gridResult").datagrid("reload");
			/*
			for(var i = 0; i < result.length; i++) {
				
				var rowIndex = $("#gridResult").datagrid("getRowIndex",result[i]);
				$("#gridResult").datagrid("refreshRow", rowIndex);
				$("#gridResult").datagrid("updateRow", {
					index : rowIndex,
					row : {
						logisticsControlWord : rows[i].logisticsControlWord.substring(3,4)
					}
				});
			}
			*/
			$.messager.alert("提示", "保存成功", "info");
		});
		
	};
	
	
	$(function() {
		
		//查询数据后自动开始grid编辑
		$("#gridResult").datagrid("options").onLoadSuccess = function(row, data) {
			//WLPDC-63 读取控制字(作业类型)
			var tempRows = $("#gridResult").datagrid("getData").rows;
			originalRowsArray = new Array();
			
			for(var i = 0; i < tempRows.length; i++) {
				appendOriginalRow(tempRows[i]);
				
				$("#gridResult").datagrid("refreshRow",i);
				$("#gridResult").datagrid("updateRow", {
					index : i,
					row :  {
						logisticsControlWord : tempRows[i].logisticsControlWord.substring(3,4)
					}
				});
			}
			
			$.fn.datagrid.defaults.onLoadSuccess.apply(this, [row, data]);
			$("#gridResult").datagrid("selectRow", 0).datagrid("beginEdit", 0);
		};
		
		//开始编辑行，自动定位到第一列
		$("#gridResult").datagrid("options").onBeginEdit = function(rowIndex, rowData) {
			
			$.fn.datagrid.defaults.onBeginEdit.apply(this, [rowIndex, rowData]);
			//$("#gridResult").datagrid("getColumnEditor", "customerCode").select();
		};
	});
	
	function appendOriginalRow(row) {
		originalRowsArray.push({
			logisticsOrderId : row.logisticsOrderId,
			logisticsControlWord : row.logisticsControlWord
		});
	}
	
	function findOriginalRowById(id) {
		for(var i = 0; i < originalRowsArray.length; i++) {
			if(originalRowsArray[i].logisticsOrderId == id) {
				return originalRowsArray[i];
			}
		}
		return null;		
	}
	
</script>
</head>
  
  <body class="easyui-layout">
  	<div region="north" title="查询条件" iconCls="icon-search">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="query()">查询</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="clearQueryForm()">重置</a>
			
		</div>
		<form id="formQuery" class="easyui-form" columns="5">
			<input id="input_transactionType" type="hidden" name="transactionType" value="CFG"/>
			<input id="input_agentCode" name="agentConsigneeCode" class="easyui-combogrid" codetype="ALL_CUSTOMER" title="委托单位"/>
			<input id="input_pickupDepot" name="pickupDepot" class="easyui-combobox" codetype="DEPOT_TYPE" title="所属仓码"/>
			<input id="input_cntrAdminCode" name="cntrAdminCode" class="easyui-combogrid" codetype="ALL_CNTR_ADMIN" title="控箱公司"/>
			<input id="input_deliveryType" name="deliveryType" class="easyui-combogrid" codetype="DELIVERY_TYPE" title="作业项目"/>
			<input id="input_orderType"  name="projectCode" class="easyui-combobox" codetype="INOROUT" title="办单类型"/>
			<input id="input_createTime"  name="createTime" class="easyui-datebox"  title="配置时间"/>
		</form>
	</div>
	
	<div region="center" title="客户信息" iconCls="icon-edit">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow()">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedRows()">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
		</div>
		<table id="gridResult" class="easyui-datagrid" fit="true" singleSelect="false"> 
			<thead>
				<tr>
					<th field="agentConsigneeCode" title="委托单位" editor="{type:'combogrid', options:{required:true}}" codetype="ALL_CUSTOMER"/>
					<th field="pickupDepot"  title="所属仓码" editor="{type:'combobox', options:{required:true}}" codetype="DEPOT_TYPE"/>
					<th field="cntrAdminCode" title="控箱公司"  editor="{type:'combogrid', options:{required:true}}" codetype="ALL_CNTR_ADMIN" />
					<th field="deliveryType"  title="作业项目" editor="{type:'combogrid', options:{required:true}}" codetype="DELIVERY_TYPE"/>
					<th field="projectCode"  title="办单类型" editor="{type:'combobox', options:{required:true}}" codetype="INOROUT"/>
					<th field="logisticsControlWord"  title="作业类型" editor="{type:'combobox', options:{required:true}}" codetype="BUSINESS_TYPE"/>
					<th field="createTime" title="配置时间"/>
					<th field="creator" title="配置人"/>
					<th field="modifyTime" title="修改时间"/>
					<th field="modifier" title="修改人"/>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>	