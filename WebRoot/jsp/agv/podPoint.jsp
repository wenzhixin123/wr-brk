<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="/fros-easyui/common/include.jsp" %>
	<%@page
			import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
	<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
	<%@page import="com.sinotrans.gd.wlp.util.StringUtil"%>
	<%@page import="com.sinotrans.gd.wlp.common.web.RcUtil"%>
	<%
		String path = request.getContextPath();
		SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
		String officeCode = scue.getOfficeCode();
	%>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/bc/sinotrans_print.js"></script>

	<script >
        $(function () {
            $("#insertTaskOrder").dialog("close");
        });

        function searchOrder(){
            $("#sale_order_grid").datagrid("commonQuery", {
                queryType : "PodPointModel"
            });
        }

        function reset(){
            $("#searchForm").form("clear");
            //setDefDate();
        }

        function addPodPoint(){
            $("#dataForm").form("clear");
            $("#insertTaskOrder").dialog("open");
        }


        function confirmBindPodPoint() {
            var model = new Object();
            model.podCode = $("#podCode").val();
            model.pointCode = $("#pointCode").val();

            if(!model.podCode || "" == model.podCode) return $.messager.alert("提示","请填写货架编号!");
            if(!model.pointCode || "" == model.pointCode) return $.messager.alert("提示","请填写储位编号!");

            PodPointManager.confirmBindPodPoint(model,function (spj) {
                if(spj.result){
                    $.messager.alert("提示",spj.msg);
                    searchOrder();
                    $("#insertTaskOrder").dialog("close");
                }else{
                    $.messager.alert("提示",spj.error);
                }
            });
        }


	</script>
</head>
<body class="easyui-layout">
<div region="north" title="查询条件" iconCls="icon-search">
	<div class="datagrid-toolbar">
		<a class="easyui-linkbutton" iconCls="icon-search" plain="true"onclick="searchOrder();">查询</a>
		<a class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="reset();">重置</a>
	</div>
	<form id="searchForm" class="easyui-form" columns="5" >
		<input name="orderNo" id="orderNo"  class="easyui-validatebox" title="销售订单号"/>
		<div title="创建日期">
			<input name="createTimeBegin" id="createTimeBegin" class="easyui-datebox"  style="width:85px;"/>
			<img src="<%=path%>/images/right.gif"/>
			<input name="createTimeEnd" id="createTimeEnd" class="easyui-datebox"  style="width:85px;"/>
		</div>
	</form>
</div>

<div region="center" border="false">
	<div class="easyui-tabs" fit="true" border="false" id="dataTabs">
		<div title="销售订单信息列表">
			<div class="easyui-layout" fit="true">
				<div region="north">
					<div class="datagrid-toolbar">
						<a class="easyui-linkbutton" plain="true" title="新增" iconCls="icon-add" onclick="addPodPoint();">新增 </a>
						<%--<a class="easyui-linkbutton" id="sendTask" plain="true" title="发送任务单" iconCls="icon-edit" onclick="sendTask();">发送绑定信息</a>--%>
					</div>
				</div>
				<div region="center">
					<table class="easyui-datagrid" id="sale_order_grid" fit="true"  border="false"  pageList="[20,50,100]">
						<thead>
						<tr>
							<th field="podCode" title="货架编号"></th>
							<th field="pointCode" title="储位编号"></th>
						</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 设置任务单 -->
<div class="easyui-dialog" id="insertTaskOrder" title="新增任务单" style="width: 550px; height: 450px">
	<div class="easyui-layout" fit="true">
		<div region="center" title="" border="false">
			<div class="datagrid-toolbar">
				<a class="easyui-linkbutton" id="resetLolBtn" plain="true" iconCls="icon-save" onclick="confirmBindPodPoint();">确定</a>
			</div>
			<form id="dataForm" class="easyui-form" columns="2"  i18nRoot="LogisticsOrderHead">
				<input name="podCode" id="podCode"  title="货架编号" />
				<input name="pointCode" id="pointCode"  title="储位编号" />
			</form>
		</div>
	</div>
</div>

</body>
</html>