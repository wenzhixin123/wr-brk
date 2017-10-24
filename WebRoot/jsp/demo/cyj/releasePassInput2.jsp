<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp"%>

<script type="text/javascript">
	//查询
	function query() {

		$("#gridResult").datagrid("commonQuery", {
			queryType : "DcsBillLadingModel",
			paramForm : "formQuery"
		});
	};

	//判断页面是否存在报关号
	function isCstNo(num){
		
		var ladingNo = $("#gridResult").datagrid("getData").rows;
		var flag = false;
		for( var i=0;i<ladingNo.length;i++){
			var value = ladingNo[i].customsClearenceNo;
			if(num == value){
				flag = true;
				break;
			}
		}
		return flag;
	}
	//判断页面是否存在提单号
	function isLadingNo(num){
		
		var ladingNo = $("#gridResult").datagrid("getData").rows;
		var flag = false;
		for( var i=0;i<ladingNo.length;i++){
			var value = ladingNo[i].billLadingNo;
			if(num == value){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	
	//新增
	function appendRowByLadingNo() {

		
		var input = $("#formQuery").form("getData").billLadingNo;
		var exist = isLadingNo(input);
		alert(exist);
		if(exist){
			alert("已存在！");
			return;
		}
		DcsBillLadingManager.findByLadingNo(input, function(result) {
			//var str = result.toJSONstring();
			if ("" == result) {
				alert("系统没有相应提运单，请手动输入后点击保存按钮！");
				//$("#formQuery")
			} else {
				result = eval('(' + result + ')');
				$("#gridResult").datagrid("appendRow", {
					customsClearenceNo : result[0].customsClearenceNo,
					billLadingNo : result[0].billLadingNo,
					isMatch : result[0].matchCustoms,
					type20X : result[0].containerType20X,
					type40X : result[0].containerType40X,
					type45X : result[0].containerType45X,
					dealTime : result[0].dateStr,
					ticks : 1,
					fee : result[0].fee,
				});
			}
		});
	}
		



	function showResult(result) {
		/*
		function(result) {
			$("#gridResult").datagrid("appendRow", {
				customsClearenceNo : result.customsClearenceNo,
				billLadingNo : result.billLadingNo
			});
			}
		 */
	};

	//结算
	function editSettlement() {

		$("#settlement").dialog("open");
	};
	//删除
	function deleteSelectedRows() {
		$("#gridResult").datagrid("deleteSelectedRows");
	};
	//编辑窗口取消
	function editDialogCancel() {
		$("#settlement").dialog("close");
	};
	//重置
	function clearForm(){
		$("#formQuery").form("clear");
	}
</script>
</head>

<body class="easyui-layout">
	<div region="north" title="报关单收集登记">
		<div class="datagrid-toolbar">
			<span class="panel-header panel-title"
				style="float: left; border-style: none; width: 100px;">扫描输入区域</span>
			<a class="easyui-linkbutton" iconCls="icon-save" onclick="getData()">保存</a>
		</div>
		<form id="formQuery" class="easyui-form" columns="1">
			<input name="customsClearenceNo" title="报关单号"
				onkeydown="if(event.keyCode == 13) appendRowByLadingNo();" /> <input
				name="billLadingNo" title="提运单号"
				onkeydown="if(event.keyCode == 13) appendRowByLadingNo();" /> <input name="x3"
				title="柜号" />
		</form>



	</div>
	<div region="center" border="false">
		<div class="datagrid-toolbar">
			<span class="panel-header panel-title"
				style="float: left; border-style: none; width: 100px;">已扫描信息</span>
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteSelectedRows()">删除</a> 
			<a class="easyui-linkbutton" onclick="editSettlement()">结算</a> 
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="clearForm()">重置</a>
		</div>
		<table id="gridResult" class="easyui-datagrid"
			i18nRoot="DcsLogisticsDetail" fit="true" pagination="false"
			showFooter="true">
			<thead>
				<tr>
					<th field="customsClearenceNo" editor="text" title="报关单号" />
					<th field="billLadingNo" editor="text" title="提运单号" />
					<th field="isMatch" editor="text" title="匹配" />
					<th field="type20X" editor="text" title="20'X" />
					<th field="type40X" editor="text" title="40'X" />
					<th field="type45X" editor="text" title="45'X" />
					<th field="dealTime" editor="text" title="时间" />
					<th field="ticks" editor="text" title="票数" />
					<th field="fee" editor="text" title="费用" />
				</tr>
			</thead>
			<tfoot>
				<tr>
					<td field="dealTime">合计</td>
					<td field="ticks" footerType="sum" />
					<td field="fee" footerType="sum" />
				</tr>
			</tfoot>
		</table>
	</div>
</body>

<!-- 弹出结算窗口 -->
<div id="settlement" class="easyui-dialog" title="结算"
	iconCls="icon-edit" style="width: 300px; padding: 10px" closed="true"
	modal="true">
	<form id="formEdit" class="easyui-form" columns="2"
		i18nRoot="BasProvince">
		<input name="x9" title="应付款" value="99.00" disabled="true" />
	</form>
	<div class="dialog-buttons">
		<a class="easyui-linkbutton" iconCls="icon-ok"
			onclick="editDialogOk()">完成</a> <a class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="editDialogCancel()">取消</a>
	</div>
</div>

</html>