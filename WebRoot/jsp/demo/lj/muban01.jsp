<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.net.InetAddress"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>
<script type="text/javascript">

function editConfig() {

	$("#config").dialog("open");
};

function editConfig2() {

	$("#config2").dialog("open");
};

function editConfig3() {

	$("#config3").dialog("open");
};

</script>
</head>

<body class="easyui-layout">
	<div region="north" title="出口提单配箱" >
		<div class="datagrid-toolbar">
		 	<a class="easyui-linkbutton" iconCls="icon-search"  onclick="editConfig()">按柜号提取作业单信息</a>
		 	<a class="easyui-linkbutton" iconCls="icon-search"  onclick="editConfig()">查询</a>
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="editConfig2()">新增提单</a>
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="editConfig3()">保存提单</a>
			<a class="easyui-linkbutton" iconCls="icon-save">提交至码头</a>
			<a class="easyui-linkbutton" iconCls="icon-remove">删除提单</a>
			<!-- 
			<a class="easyui-linkbutton" iconCls="icon-reload">取消提交</a>
			 -->
			<a class="easyui-linkbutton" >有格式打印</a>
			<a class="easyui-linkbutton" >无格式打印</a>
		</div>
		<form id="xxform" class="easyui-form" columns="5" i18nRoot="xx">
			<input name="xx" title="提运单号" required="true"/>
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
			<input name="xx" title="托运/付款人" />
			<input name="xx" title="托运日期" required="true" class="easyui-datebox"/>
			<input name="xx" title="到达港" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="xx" title="货物统计编号"/>
			<input name="xx" title="贸易国别" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="xx" title="货物类型" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="xx" title="海运目的港" />
			<input name="xx" title="海运船名" />
			<input name="xx" title="海运航次" />
			<input name="xx" title="CLOSINGTIME" class="easyui-datebox"/>
			<div operator="in" title="散货/柜货">
				<input name="xx" type="radio"/>散货
				<input name="xx" type="radio"/>柜货
			</div> 
			<div operator="in" title="状态" >
				<input name="xx" type="checkbox"/>放行
				<input name="xx" type="checkbox"/>换单
				<input name="xx" type="checkbox"/>打印
			</div> 
			<input name="xx" title="驳船船名" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="xx" title="驳船航次" />
			<input name="xx" title="中转港" />
			<input name="xx" title="备注"/>
			<div operator="in" title="审核情况">
				<input name="xx1" type="radio"/>已审核
				<input name="xx1" type="radio"/>未审核
			</div> 
			<div operator="in" title="是否允许海关修改货物">
				<input name="xx1" type="radio"/>允许
				<input name="xx1" type="radio"/>不允许
			</div> 
			<input name="xx" title="提交状态" disabled="true" value="N"/>
			<input name="xx" title="报关单号" disabled="true"/>
			<input name="xx" title="计费" disabled="true" value="N"/>
			<input name="xx" title="报关行"  />
			<input name="xx"   disabled="true" title="操作员" value="操作员1" />
			<div operator="in" title="发送海关">
				<input name="xx1" type="radio"/>发送
				<input name="xx1" type="radio"/>不发送
			</div> 
		</form>
	</div>
	
	<div region="center" border="false">
		<div fit="true" class="easyui-layout">
				
			<div region="center"  iconCls="icon-edit" >
				<div fit="true" class="easyui-layout">
					<div region="center"  iconCls="icon-edit" >
						<div class="datagrid-toolbar">
							<span class="panel-header panel-title" style="float: left; border-style: none; width: 120px;">集装箱明细资料</span>
							<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow('#dcsContainerInfo')">新增集装箱</a>
							<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow('#dcsContainerInfo')">按集装箱号从作业办单提取并追加集装箱</a>
							<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedRows('#dcsContainerInfo')">删除</a>
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
									<th field="xx7" editor="text" title="回箱日期"/>
									<th field="xx8" editor="text" title="备注"/>
									<th field="xx9" editor="text" title="B/L序号"/>
									<th field="xx10" editor="text" title="S/O 号"/>
									<th field="xx11" editor="text" title="来源及流向"/>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>	

<!-- 柜号输入窗口 -->
	<div id="config" class="easyui-dialog" title="柜号输入" iconCls="icon-edit"
			style="width:400px;padding:30px" closed="true" modal="true">
		<form id="formEdit" class="easyui-form" columns="1" i18nRoot="BasProvince">
			<input name="x10" title="柜号" required="true"/>
		</form>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="editDialogOk()">完成</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="editDialogCancel()">取消</a>
		</div>
	</div>
	
	<!-- 提单选择窗口 -->
	<div id="config2" class="easyui-dialog" title="提运单列表" iconCls="icon-edit"
			 style="width:300px"  closed="true" modal="true">
		<div fit="true" class="easyui-layout">
			<div region="center"  iconCls="icon-edit" >
				<table id="dcsContainerInfo2" class="easyui-datagrid" i18nRoot="DcsContainerInfo" fit="true" pagination="false" showFooter="true">
					<thead>
						<tr>
							<th field="xx8" title="提运单号"/>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="editDialogOk()">完成</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="editDialogCancel()">取消</a>
		</div>
	</div>
	
		<!-- 作业单提单选择窗口 -->
	<div id="config3" class="easyui-dialog" title="作业单提运单列表" iconCls="icon-edit"
			 style="width:300px"  closed="true" modal="true">
		<div fit="true" class="easyui-layout">
			<div region="center"  iconCls="icon-edit" >
				<table id="dcsContainerInfo2" class="easyui-datagrid" i18nRoot="DcsContainerInfo" fit="true" pagination="false" showFooter="true">
					<thead>
						<tr>
							<th field="xx8" title="作业单号"/>
							<th field="xx9" title="提运单号"/>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="editDialogOk()">完成</a>
			<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="editDialogCancel()">取消</a>
		</div>
	</div>

</html>