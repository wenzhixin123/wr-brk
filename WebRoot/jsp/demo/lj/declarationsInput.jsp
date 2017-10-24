<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>

<script type="text/javascript">

//结算
function editSettlement() {

		$("#settlement").dialog("open");
};

//配置
function editConfig() {

		$("#config").dialog("open");
};

</script>
</head>

<body class="easyui-layout">
	<div region="north" title="报关单收集登记" >
		<div class="datagrid-toolbar">
		<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">扫描输入区域</span>
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="save()">保存</a>
		</div>
		<form id="declaration" class="easyui-form" columns="1" i18nRoot="DcsLogisticsOrder">
			<input name="x1" title="报关单号"/> 
			<input name="x2" title="提运单号"/> 
			<input name="x3" title="柜号"/> 
		</form>
	</div>
	<div region="center" border="false">
		<div class="datagrid-toolbar">
		<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">已扫描信息</span>
			<a class="easyui-linkbutton" iconCls="icon-remove" >删除</a>
			<a class="easyui-linkbutton" onclick="editSettlement()">结算</a>
		</div>
		<table id="dcsLogisticsDetail" class="easyui-datagrid" i18nRoot="DcsLogisticsDetail" fit="true" pagination="false" showFooter="true">
			<thead>
				<tr>
					<th field="x1" editor="text" title="报关单号"/> 
					<th field="x2" editor="text" title="提运单号"/>
					<th field="x3" editor="text" title="放行"/>
					<th field="x4" editor="text" title="20'X"/>
					<th field="x41" editor="text" title="40'X"/>
					<th field="x42" editor="text" title="45'X"/>
					<th field="x5" editor="text" title="时间"/>
					<th field="x6" editor="text" title="票数"/>
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

</html>