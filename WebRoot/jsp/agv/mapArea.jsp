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
        });
        function searchOrder(){
            $("#sale_order_grid").datagrid("commonQuery", {
                queryType : "MapAreaQuery",
                paramForm : "formQuery"
            });

        }
        function reset(){
            $("#searchForm").form("clear");
            //setDefDate();
        }
        //查询地码信息
        function queryTaskStatus(){
            MapAreaManager.synchronyMapArea(function (spj) {
                if(spj.result){
                    $.messager.alert("提示",spj.msg);
                    searchOrder();
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
		<div title="信息列表">
			<div class="easyui-layout" fit="true">
				<div region="north">
					<div class="datagrid-toolbar">
						<a class="easyui-linkbutton" id="queryTaskStatus" plain="true" title="查询任务单状态" iconCls="icon-edit" onclick="queryTaskStatus();">查询地码信息</a>
					</div>
				</div>
				<div region="center">
					<table class="easyui-datagrid" id="sale_order_grid" fit="true"  border="false"  pageList="[20,50,100]">
						<thead>
						<tr>
							<th field="areaCode" title="库区"></th>
							<th field="cooX" title="X坐标"></th>
							<th field="cooY" title="Y坐标"></th>
							<th field="dataTyp" title="地码类型"></th>
							<th field="direction" title="工作台方向"></th>
							<th field="mapDataCode" title="地码编号"></th>
							<th field="userCallCode" title="呼叫号"></th>
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