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
            $("#resetTaskPriority").dialog("close");

//            $("#taskTyp").combobox({
//                panelHeight:'auto',
//                width:80,
//                data:[{'id':'inStock','text':'入库'},
//                    {'id':'outStock','text':'出库'},],
//                valueField:'id',
//                textField:'text'
//            });

            $("#podDir").combobox({
                panelHeight:'auto',
                width:80,
                data:[{'id':'180','text':'左'},
                    {'id':'0','text':'右'},
                    {'id':'90','text':'上'},
                    {'id':'-90','text':'下'},],
                valueField:'id',
                textField:'text'
            });


        });
		function searchOrder(){
            $("#sale_order_grid").datagrid("commonQuery", {
                queryType : "ReqTaskQuery",
                paramForm : "formQuery"
            });

        }

        function reset(){
            $("#searchForm").form("clear");
            //setDefDate();
        }
        function addTaskOrder(){
            $("#dataForm").form("clear");
		    $("#insertTaskOrder").dialog("open");
		}

		function confirmInsertTaskOrder() {
			var model = new Object();
			model.taskTyp = $("#taskTyp").val();
			model.userCallCode = $("#userCallCode").val();
			model.userCallCodePath = $("#userCallCodePath").val();
			model.podCode = $("#podCode").val();
			model.podDir = $("#podDir").combobox("getValue");
			model.priority = $("#priority").val();
			model.taskCode = $("#taskCode").val();
			model.robotCode = $("#robotCode").val();
			model.clientCode = $("#clientCode").val();
			model.tokenCode = $("#tokenCode").val();

            ReqTaskManager.saveReqTask(model,function (spj) {
                if(spj.result){
                    $.messager.alert("提示",spj.msg);
                    searchOrder();
                    $("#insertTaskOrder").dialog("close");
				}else{
                    $.messager.alert("提示",spj.error);
				}
            });
        }
        //发送任务
        function  sendTask() {

           var rows = $("#sale_order_grid").datagrid("getSelections");
           if(!rows || rows.length == 0) return $.messager.alert("提示","请选择大于一行数据进行操作!");
		   
           var taskIds = new Array();
           $.each(rows,function (index,item) {
               console.info(item.reqTaskId);
			 taskIds[index] = item.reqTaskId;
           });
            ReqTaskManager.sendReqTask(taskIds,function (spj) {
                if(spj.result){
                    $.messager.alert("提示",spj.msg);
                    searchOrder();
                }else{
                    $.messager.alert("提示",spj.error);
                    searchOrder();
                }
            });

        }
		//删除任务
        function  cancelTask() {
            var rows = $("#sale_order_grid").datagrid("getSelections");
            if(!rows || rows.length == 0) return $.messager.alert("提示","请选择大于一行数据进行操作!");

            var taskIds = new Array();
            $.each(rows,function (index,item) {
                console.info(item.reqTaskId);
                taskIds[index] = item.reqTaskId;
            });
            ReqTaskManager.cancelReqTask(taskIds,function (spj) {
                if(spj.result){
                    $.messager.alert("提示",spj.msg);
                    searchOrder();
                }else{
                    $.messager.alert("提示",spj.error);
                    searchOrder();
                }
            });

        }
		//设置任务优先级
        function  editPriority() {
            var rows = $("#sale_order_grid").datagrid("getSelections");
            if(!rows || rows.length == 0) return $.messager.alert("提示","请选择大于一行数据进行操作!");
            if(rows.length > 1) return $.messager.alert("提示","请选择一行数据进行操作!");

            $("#priorityFrom").form("clear");
            $("#resetTaskPriority").dialog("open");
        }

        function resetPriority(){
            var rows = $("#sale_order_grid").datagrid("getSelections");
            var taskId = rows[0].reqTaskId;
            var priority = $("#reset_priority").val();

            if(!priority || "" == priority) return $.messager.alert("提示","请输入优先级值!");
            ReqTaskManager.resetPriority(taskId,priority,function (spj) {
                if(spj.result){
                    $.messager.alert("提示",spj.msg);
                    $("#resetTaskPriority").dialog("close");
                    searchOrder();
                }else{
                    $.messager.alert("提示",spj.error);
                    $("#resetTaskPriority").dialog("close");
                    searchOrder();
                }
            });
		}
		//批量查询任务单状态
		function queryTaskStatus(){
            var rows = $("#sale_order_grid").datagrid("getSelections");
            if(!rows || rows.length == 0) return $.messager.alert("提示","请选择大于一行数据进行操作!");

            var taskIds = new Array();
            $.each(rows,function (index,item) {
                console.info(item.reqTaskId);
                taskIds[index] = item.reqTaskId;
            });
            ReqTaskManager.queryReqTask(taskIds,function (spj) {
                if(spj.result){
                    $.messager.alert("提示",spj.msg);
                    searchOrder();
                }else{
                    $.messager.alert("提示",spj.error);
                }
            });
        }

        function  continueNextAction() {
            var rows = $("#sale_order_grid").datagrid("getSelections");
            if(!rows || rows.length == 0) return $.messager.alert("提示","请选择大于一行数据进行操作!");
            if(rows.length > 1) return $.messager.alert("提示","请选择一行数据进行操作!");

            var taskCode = rows[0].taskCode;
            ReqTaskManager.continueTaskNextAction(taskCode,function (spj) {
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
		<input name="orderStcokStatus" id="orderStcokStatus" class="easyui-combobox"  title="库内状态"/>
		<input name="brmOrderType" id="brmOrderType" class="easyui-combobox"  title="销售单类型"/>
		<input name="control5" id="control5" title="打印状态"  class="easyui-combobox"/>
		<input name="transactionStatus" id="transactionStatus" title="销售状态" class="easyui-combobox"/>
		<input name="sendee" id="sendee" title="收件人" class="easyui-validatebox" />
		<input name="telephone" id="telephone" title="手机号" class="easyui-validatebox" />
		<div title="办单日期">
			<input name="dateBegin" id="dateBegin" class="easyui-datebox"  style="width:85px;"/>
			<img src="<%=path%>/images/right.gif"/>
			<input name="dateEnd" id="dateEnd" class="easyui-datebox"  style="width:85px;"/>
		</div>
		<div title="出库日期">
			<input name="cutOffDateBegin" id="cutOffDateBegin" class="easyui-datebox"  style="width:85px;"/>
			<img src="<%=path%>/images/right.gif"/>
			<input name="cutOffDateEnd" id="cutOffDateEnd" class="easyui-datebox"  style="width:85px;"/>
		</div>
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
						<a class="easyui-linkbutton" plain="true" title="新增" iconCls="icon-add" onclick="addTaskOrder();">新增 </a>
						<a class="easyui-linkbutton" id="queryTaskStatus" plain="true" title="查询任务单状态" iconCls="icon-edit" onclick="queryTaskStatus();">查询任务单状态</a>
						<a class="easyui-linkbutton" id="sendTask" plain="true" title="发送任务单" iconCls="icon-edit" onclick="sendTask();">发送任务单</a>
						<a class="easyui-linkbutton" id="cancelTask" plain="true" title="取消任务单" iconCls="icon-edit" onclick="cancelTask();">取消任务单</a>
						<a class="easyui-linkbutton" id="editPriority" plain="true" title="设置优先级" iconCls="icon-edit" onclick="editPriority();">设置优先级</a>
						<a class="easyui-linkbutton" id="continueNextAction" plain="true" title="手工继续下一个动作" iconCls="icon-edit" onclick="continueNextAction();">手工继续下一个动作</a>
					</div>
				</div>
				<div region="center">
					<table class="easyui-datagrid" id="sale_order_grid" fit="true"  border="false"  pageList="[20,50,100]">
						<thead>
							<tr>
								<th field="taskCode" title="任务单号"></th>
								<th field="taskStatus" title="任务状态"></th>
								<th field="reqTime" title="请求时间"></th>
								<th field="taskTyp" title="任务类型"></th>
								<th field="podCode" title="货架编号"></th>
								<th field="userCallCode" title="呼叫号"></th>
								<th field="userCallCodePath" title="途径呼叫号"></th>
								<th field="podDir" title="方位"></th>
								<th field="priority" title="优先级"></th>
								<th field="robotCode" title="机器人编号"></th>
								<th field="clientCode" title="客户端编号"></th>
								<th field="tokenCode" title="令牌号"></th>
								<th field="currentCallCode" title="当前到达机台编号"></th>
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
				<a class="easyui-linkbutton" id="resetLolBtn" plain="true" iconCls="icon-save" onclick="confirmInsertTaskOrder();">确定</a>
			</div>
			<form id="dataForm" class="easyui-form" columns="2"  i18nRoot="LogisticsOrderHead">
				<input name="taskTyp" id="taskTyp" title="任务类型"/>
				<input title="用户呼叫编号" name="userCallCode" id="userCallCode"/>
				<textarea name="userCallCodePath" id="userCallCodePath" rowspan="5" colspan="1" title="途径呼叫号"></textarea>
				<input title="货架编号" name="podCode" id="podCode"/>
				<input name="podDir" id="podDir"  title="方位" class="easyui-combobox" />
				<input name="priority" id="priority"  title="优先级" />
				<input name="taskCode" id="taskCode"  title="任务编号"/>
				<input name="robotCode" id="robotCode"  title="机器人编号"/>
				<input name="clientCode" id="clientCode"  title="客户端编号"/>
				<input name="tokenCode" id="tokenCode"  title="令牌号"/>
			</form>
		</div>
	</div>
</div>

<!-- 重置任务级别 -->
<div class="easyui-dialog" id="resetTaskPriority" title="重置任务级别" style="width: 550px; height: 450px">
	<div class="easyui-layout" fit="true">
		<div region="center" title="" border="false">
			<div class="datagrid-toolbar">
				<a class="easyui-linkbutton" id="resetPriority" plain="true" iconCls="icon-save" onclick="resetPriority();">确定</a>
			</div>
			<form id="priorityFrom" class="easyui-form" columns="2"  i18nRoot="LogisticsOrderHead">
				<input name="reset_priority" id="reset_priority"  title="优先级" />
			</form>
		</div>
	</div>
</div>

</body>
</html>