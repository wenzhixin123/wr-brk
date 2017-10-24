<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>

<script type="text/javascript">


//查询
function query() {
	
	//alert("query !");
	$("#gridResult").datagrid("commonQuery", {
		queryType : "",
		paramForm : "formQuery"
	});
};
//结算
function editSettlement() {

		$("#settlement").dialog("open");
};

//配置
function editConfig() {

		$("#config").dialog("open");
};

//重置form
function resetForm(){
	$("#releaseQueryForm").form("clear");
}

</script>
</head>

<body class="easyui-layout">
	<div region="north" title="报关单收集登记" >
		<div class="datagrid-toolbar">
		<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">扫描输入区域</span>
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="query()">查询</a>
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="resetForm()">重置</a>
			<a class="easyui-linkbutton" onclick="editSettlement()">结算</a>
			
		</div>
		<form id="releaseQueryForm" class="easyui-form" columns="1" i18nRoot="DcsLogisticsOrder">
			<input name="clearenceNo" title="报关单号"/> 
			<input name="ladingNo" title="提运单号"/> 
			<input name="boardNo" title="柜号"/> 
		</form>
	</div>
	<div region="center" border="false">
		<div class="datagrid-toolbar">
		<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">已扫描信息</span>
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedDcsLogisticsDetailRows()">删除</a>
		</div>
		<table id="dcsLogisticsDetail" class="easyui-datagrid" i18nRoot="DcsLogisticsDetail" fit="true" pagination="false" showFooter="true">
			<thead>
				<tr>
					<th field="x1" editor="text" title="报关单号"/> 
					<th field="x2" editor="text" title="提运单号"/>
					<th field="x3" editor="text" title="匹配"/>
					<th field="x4" editor="text" title="柜号"/>
					<th field="x5" editor="text" title="时间"/>
					<th field="x6" editor="text" title="票数"/>
					<th field="x7" editor="text" title="柜数"/>
					<th field="x8" editor="text" title="费用"/>
				</tr> 
			</thead>
			<tfoot>
				<tr>
					<td field="x5">合计</td>
					<td field="x6" footerType="sum"/>
					<td field="x7" footerType="sum"/>
					<td field="x8" footerType="sum"/>
				</tr>
			</tfoot>
		</table>
	</div>
</body>	

<!-- 弹出结算窗口 -->
	<div id="settlement" class="easyui-dialog" title="结算" iconCls="icon-edit"
			style="width:300px;padding:10px" closed="true" modal="true">
		<form id="formEdit" class="easyui-form" columns="2" i18nRoot="BasProvince">
			<input name="x9" title="应付款" value="99.00"  disabled="true"/>
		</form>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="editDialogOk()">完成</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="editDialogCancel()">取消</a>
		</div>
	</div>


<!-- 弹出配置窗口 -->
	<div id="config" class="easyui-dialog" title="配置" iconCls="icon-edit"
			style="width:300px;padding:30px" closed="true" modal="true">
		<form id="formEdit" class="easyui-form" columns="1" i18nRoot="BasProvince">
			<input name="x10" title="1票/元" />
			<input name="x11" title="小柜/元" />
			<input name="x12" title="大柜/元" />
		</form>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="editDialogOk()">完成</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="editDialogCancel()">取消</a>
		</div>
	</div>
</html>