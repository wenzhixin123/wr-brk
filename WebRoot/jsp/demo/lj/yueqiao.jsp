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
	<div region="north" title="出口约桥" >
		<div class="datagrid-toolbar">
			<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">查询条件</span>
			<a class="easyui-linkbutton" iconCls="icon-search"  >查询</a>
			<a class="easyui-linkbutton" iconCls="icon-reload">重置</a>
			<a class="easyui-linkbutton" onclick="editConfig()">配置</a>
		</div>
		<form id="declaration" class="easyui-form" columns="3" i18nRoot="DcsLogisticsOrder">
			<input name="x1" title="驳船船名"/> 
			<input name="x2" title="驳船航次"/> 
			<input name="x3" title="装货点"/>
			<input name="x3" title="卸货点"/>
			<input name="x3" title="箱号"/>
			<input name="x3" title="LINER"/>
			<input name="x3" title="SO号"/>
			<input name="x3" title="大船船名/航次"/>
			<input name="x3" title="约桥状态 "/>
		</form>
	</div>
	<div region="center" border="false">
		<div class="datagrid-toolbar">
		<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">查询结果</span>
			<a class="easyui-linkbutton" >导出约桥表</a>
			<a class="easyui-linkbutton" >保存 Job No。</a>
			
		</div>
		<table id="dcsLogisticsDetail" class="easyui-datagrid" i18nRoot="DcsLogisticsDetail" fit="true" pagination="false" >
			<thead>
				<tr>
					<th field="x1" editor="text" title="SO号"/> 
					<th field="x2" editor="text" title="箱号"/>
					<th field="x3" editor="text" title="驳船船名"/>
					<th field="x4" editor="text" title="驳船航次"/>
					<th field="x5" editor="text" title="装货点"/>
					<th field="x6" editor="text" title="卸货点"/>
					<th field="x7" editor="text" title="LINER"/>
					<th field="x9" editor="text" title="约桥Job No."/>
					<th field="x10" editor="text" title="货类"/>
					<th field="x11" editor="text" title="大船船名/航次"/>
					<th field="x12" editor="text" title="约桥状态"/>
				</tr> 
			</thead>
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
			style="width:400px;padding:30px" closed="true" modal="true">
		<form id="formEdit" class="easyui-form" columns="1" i18nRoot="BasProvince">
			<input name="x10" title="收件人" />
			<input name="x11" title="联系人和电话 1" />
			<input name="x12" title="联系人和电话2" />
		</form>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="editDialogOk()">完成</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="editDialogCancel()">取消</a>
		</div>
	</div>
</html>