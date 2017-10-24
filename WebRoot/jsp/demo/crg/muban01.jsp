<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.net.InetAddress"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>
<script type="text/javascript">

</script>
</head>

<body class="easyui-layout">
	<div region="north" title="出口提单编辑" iconCls="icon-search">
		<div class="datagrid-toolbar">
		 	<a class="easyui-linkbutton" iconCls="icon-search">查询</a>
			<a class="easyui-linkbutton" iconCls="icon-add">新增提单</a>
			<a class="easyui-linkbutton" iconCls="icon-save">保存提单</a>
			<a class="easyui-linkbutton" iconCls="icon-save">提交提单</a>
			<a class="easyui-linkbutton" iconCls="icon-reload">重置</a>
			<a class="easyui-linkbutton" iconCls="icon-remove">删除作业单</a>
		</div>
		<form id="xxform" class="easyui-form" columns="5" i18nRoot="xx">
			<input name="xx" title="提单号" required="true"/>
			<input name="xx" title="序号"/>
			<input name="xx" title="船公司提单号" />
			<input name="xx" title="货名" class="easyui-combogrid" codetype="ALL_CUSTOMER" required="true"/>
			<input name="xx" title="唛头" />
			<input name="xx" title="包装" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="xx" title="件数"/>
			<input name="xx" title="体积"/>
			<input name="xx" title="危险参数" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="xx" title="毛重"/>
			<input name="xx" title="净重"/>
			<input name="xx" title="付款方式" class="easyui-combobox" codetype="STATUS"/>
			<input name="xx" title="装卸条款" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="xx"  title="付货人" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="xx"  title="通知人" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="xx"  title="收货人" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="xx" title="生产工厂" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="xx" title="托运日期" required="true"/>
			<input name="xx" title="到达港" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="xx" title="货物统计编号"/>
			<input name="xx" title="贸易国别" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="xx" title="货物类型" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<div operator="in" title="散货/柜货">
				<input name="xx" type="checkbox"/>散货
				<input name="xx" type="checkbox"/>柜货
			</div> 
			<div operator="in" title="状态" colspan="2">
				<input name="xx" type="checkbox"/>放行
				<input name="xx" type="checkbox"/>换单
				<input name="xx" type="checkbox"/>打印
			</div> 
			<input name="xx" title="海运船名/航次"/>
			<input name="xx" title="头程船名" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="xx" title="航次" />
			<input name="xx" title="备注"/>
			<input name="xx" title="付款人"  value="广运船务有限公司"/>
		</form>
	</div>
	
	<div region="center" border="false">
		<div fit="true" class="easyui-layout">
			<div region="north" iconCls="icon-edit" style="height:200px">
				<div class="datagrid-toolbar">
					<span class="panel-header panel-title" style="float: left; border-style: none; width: 80px;">报关报检资料</span>
				</div>
				<form id="xxform" class="easyui-form" columns="4" i18nRoot="xx">
					<input name="xx" title="发货人" class="easyui-combobox" codetype="STATUS" required="true"/>
					<input name="xx" title="贸易方式" class="easyui-combogrid" codetype="ALL_CUSTOMER" required="true"/>
					<input name="xx" title="启运口岸" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
					<input name="xx" title="所需单证" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
					<input name="xx" title="合同号"/>
					<input name="xx" title="贸易国家" class="easyui-combogrid" codetype="ALL_CUSTOMER" required="true"/>
					<input name="xx" title="抵运口岸" class="easyui-combogrid" codetype="ALL_CUSTOMER" required="true"/>
					<input name="xx" title="附属单据"/>
					<textarea name="remark" title="备注" rowspan="2" colspan="4"></textarea>
				</form>
			</div>
			
				
			<div region="center"  iconCls="icon-edit" >
				<div fit="true" class="easyui-layout">
					<div region="north" iconCls="icon-edit" style="height:200px">
						<div class="datagrid-toolbar">
							<span class="panel-header panel-title" style="float: left; border-style: none; width: 120px;">集装箱明细资料</span>
							<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow('#dcsContainerInfo')">新增</a>
							<a class="easyui-linkbutton" iconCls="icon-redo" onclick="insertRow('#dcsContainerInfo')">插入</a>
							<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedRows('#dcsContainerInfo')">删除</a>
							<a class="easyui-linkbutton" iconCls="icon-reload" onclick="reloadGrid('#dcsContainerInfo')">取消</a>
						</div>
						<table id="dcsContainerInfo" class="easyui-datagrid" i18nRoot="DcsContainerInfo" fit="true" pagination="false" showFooter="true">
							<thead>
								<tr>
									<th field="xx1" editor="text" title="集装箱号"/>
									<th field="xx2" editor="text" title="箱型"/>
									<th field="xx3" editor="text" title="状态"/>
									<th field="xx4" editor="text" title="控箱公司"/>
									<th field="xx5" editor="text" title="箱属公司"/>
									<th field="xx6" editor="text" title="重量（Kg）"/>
								</tr>
							</thead>
						</table>
					</div>
					<div region="center"  iconCls="icon-edit" >
						<div class="datagrid-toolbar">
							<span class="panel-header panel-title" style="float: left; border-style: none; width: 120px;">报关报检货物明细</span>
							<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow('#dcsContainerInfo')">新增</a>
							<a class="easyui-linkbutton" iconCls="icon-redo" onclick="insertRow('#dcsContainerInfo')">插入</a>
							<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedRows('#dcsContainerInfo')">删除</a>
							<a class="easyui-linkbutton" iconCls="icon-reload" onclick="reloadGrid('#dcsContainerInfo')">取消</a>
						</div>
						<table id="dcsContainerInfo2" class="easyui-datagrid" i18nRoot="DcsContainerInfo" fit="true" pagination="false" showFooter="true">
							<thead>
								<tr>
									<th field="xx8" editor="text" title="项号"/>
									<th field="xx9" editor="{type:'text', options:{required:true}}" title="海关商品编码"/>
									<th field="xx10" editor="{type:'text', options:{required:true}}" title="商品名称"/>
									<th field="xx11" editor="text" title="唛头"/>
									<th field="xx12" editor="text" title="商品规格"/>
									<th field="xx13" editor="{type:'text', options:{required:true}}" title="数量"/>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>	

</html>