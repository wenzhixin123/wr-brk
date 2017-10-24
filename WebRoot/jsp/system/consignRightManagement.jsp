<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>
<%@ page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity" %>

<% 
String officeCode = SessionContextUserEntity.currentUser().getOfficeCode();
String userType = SessionContextUserEntity.currentUser().getUserType();
String userComp = SessionContextUserEntity.currentUser().getCustomerCode();
if("DOCC".equals(officeCode)){
	officeCode = "";
}
%>

<script type="text/javascript">
var officeCode = "<%=officeCode%>";
var userComp = "<%=userComp%>";
var userType = "<%=userType%>";
//查询
function query() {
	$("#gridResult").datagrid("commonQuery", {
		queryType : "DcsLogisticsOrderQuery",
		paramForm : "formQuery"
	});
};

//重置
function clearQueryForm() {
	$("#formQuery").form("clear");
	$("#officeCode").val(officeCode);
};

//新增
function appendRow() {
	parent.addTabs("新增录入", "jsp/dcs/dcsOrderEdit.jsp", "icon icon-nav", true);
};

//编辑
function editRow() {
	var selected = $("#gridResult").datagrid("endEdit").datagrid("getSelected");
	if (selected) {
		var orderNo = selected.logisticsOrderNo;
		var order=orderNo.substring(0,2);
		if(order=="CX"){
			var title = "出口作业办单";
			var url = "jsp/dcs/dcsOrderEdit.jsp?orderno="+selected.logisticsOrderNo;
			
			//add by wqs  2012-08-21   begin--   刷新
			
			//parent.addTabs(title, url, "icon icon-nav", true);
			parent.addTabs(title, url, false, true); //21Aug, fix slow problem...
			if(parent.getTabIframe(title).contentWindow.loadByOrderNo){
				parent.getTabIframe(title).contentWindow.loadByOrderNo(orderNo); //21Aug, fix slow problem...	
			}	
		}else if(order=="CC"){
			var title = "进口作业办单";
			var url = "jsp/dcs/dcsImpOrderEdit.jsp?orderno="+selected.logisticsOrderNo;
			
			//add by wqs  2012-08-21   begin--   刷新
			
			//parent.addTabs(title, url, "icon icon-nav", true);
			parent.addTabs(title, url, false, true); //21Aug, fix slow problem...
			if(parent.getTabIframe(title).contentWindow.loadByImpOrderNo){
				parent.getTabIframe(title).contentWindow.loadByImpOrderNo(orderNo); //21Aug, fix slow problem...	
			}	
		}
		
		
		//--end
	}
};

//删除
function deleteSelectedRows() {
	var selections = $("#gridResult").datagrid("getSelections");
	if (selections.length > 0) {
		$.messager.confirm("提示", "确认删除？", function(b) {
			if (b) {
				$("#gridResult").datagrid("deleteSelectedRows");
				var pks = [];
				$.each(selections, function(index, selected) {
					pks.push(selected.logisticsOrderId)
				});
				LogisticsOrderManager.removeAllByPk(pks, function(result) {
					$.messager.alert("提示", "删除成功", "info");
				});
			}
		})
	};
};

//取消
function reload() {
	$("#gridResult").datagrid("reload");
};

$(function() {
	$("#gridResult").datagrid("options").onDblClickRow = function(rowIndex, rowData) {
		$.fn.datagrid.defaults.onDblClickRow.apply(this, [rowIndex, rowData]);
		editRow();
	};
	$("#dateFrom").datebox("setValue",curDateTime());
	$("#dateTo").datebox("setValue",curDateTime());
});

function curDateTime(){  
	var d = new Date();   
	var year = d.getFullYear();   
	var month = d.getMonth()+1;   
	var date = d.getDate();   
	var dateTime= year;  
	if(month>9)  
		dateTime +="-"+month;  
	else  
		dateTime +="-0"+month;  
	if(date>9)  
		dateTime +="-"+date;  
	else  
		dateTime +="-0"+date;  
	return dateTime;   
} 

function initQuery(){
	//add by dj 20121022,如果为外部用户
	if(userType == "EXTERNAL"){

		$("#transactionType").combobox("setValue","CIF");
		$("#querytype").combobox("setValue","CX");
		$("#agentConsigneeCode").combogrid("setValue",userComp);

		$("#transactionType").combobox("readonly");
		$("#querytype").combobox("readonly");
		$("#agentConsigneeCode").combogrid("readonly");
	}
}
	
</script>
</head>

<body class="easyui-layout" onload="initQuery()">
	
	<div region="north" title="查询条件" iconCls="icon-search">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="query()" key="Q">查询</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="clearQueryForm()" key="R">重置</a>
		</div>
		<form id="formQuery" class="easyui-form" columns="5" i18nRoot="DcsLogisticsOrder">
			<input id="officeCode" name="officeCode" type="hidden" value="<%=officeCode%>"/>
			<input id="agentConsigneeCode" name="agentConsigneeCode" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="portAreaCode" title="所属仓码" class="easyui-combobox" codetype="PORT_AREA_CODE"/>
			<input name="cntrAdminCode" class="easyui-combogrid" codetype="ALL_CNTR_ADMIN"/>
			<input id="transactionType" name="transactionType" title="作业项目" class="easyui-combobox" codetype="DELIVERY_TYPE"/>
			<input name="displayValueEn" title="业务类型" class="easyui-combobox"   codetype="BUSINESS_TYPE"/>
		</form>
	</div>
	
	<div region="center" title="配置信息" iconCls="icon-edit">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow()" key="E">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()" key="F">保存</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="reload()" key="F">取消</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="deleteSelectedRows()" key="F">删除</a>
		</div>
		<table id="gridResult" class="easyui-datagrid" i18nRoot="DcsLogisticsOrder" fit="true">
			<thead>
				<tr>
					<th field="logisticsOrderNo" />
					<th field="orderDocumentNo" title="委托单位"/>
					<th field="pickupOrderNo" title="所属仓码"/>
					<th field="cntrAdminCode" title="控箱公司" codetype="ALL_CUSTOMER"/>
					<th field="deliveryType" title="作业项目"/>
					<th field="returnOrderNo" title="业务类型"/>
					<th field="billno" title="创建人"/>
					<th field="containerno" title="创建时间"/>
					<th field="transactor" title="修改人"/>
					<th field="logisticsOrderDate" title="修改时间"/>
				</tr>
			</thead>
		</table>
	</div>
	
</body>
</html>