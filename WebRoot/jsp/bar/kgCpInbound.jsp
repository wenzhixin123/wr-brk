<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@page import="com.sinotrans.gd.wlp.util.StringUtil"%>
<%@page import="com.sinotrans.gd.wlp.common.web.RcUtil"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/common/imp_dwr.jsp" %>
<script type="text/javascript" src="../../js/jquery-1.6.min.js"></script>
<title>收货</title>
<%
	String path = request.getContextPath();
	SessionContextUserEntity scue = SessionContextUserEntity.currentUser();	
	String officeCode = SessionContextUserEntity.currentUser().getOfficeCode();
%>
 <style type="text/css">
#tableQueryForm td {
	font-size: 12px;
}
.buttonfont {
	font-size: 14px;
	line-height: 18px;
}
.warningMsg {
	color: red;
	font-size: 12px;
	border: none;
	width: 230px;
}
.inputSize{
	text-transform: uppercase; 
	width: 150px;
}
.msgClass {
	border: none;
	width: 80px;
	height: 15px;
	padding: 0px;
}
#resultTable{
	font-size: 12px;
	font-weight: lighter;
	border-collapse:collapse;
}
</style>
<script type="text/javascript">
	
	var officeCode = "<%=officeCode%>";
	
	
	function cleanAll(){
		$("#warnMsgDiv").hide();
		$("#labOrderNo").text("");
		$("#labTotalQty").text("");
		optDisable(false);
		$("#lineNo").val("");
		$("#packageNo").val("");
		$("#startBarcode").val("");
		$("#endBarcode").val("");
	} 
	
	function optDisable(opt){
		$("#btnConfirm").attr("disabled",opt); 
		$("#btnCancel").attr("disabled",opt);
	}
	
	$(document).ready(function(){
		$("#warnMsgDiv").hide();
		$("#lineNo").select();
		
		$("#btnReset").unbind("click").click(function(){
			cleanAll();
			$("#lineNo").focus();
		});
		
		$("#lineNo").unbind("keydown").keydown(function(e){
			if(e.keyCode == 13 ) {
				$("#warnMsgDiv").hide();
				$("#packageNo").select();
			}
		});
		
		$("#lineNo").unbind("change").change( function() {
			$("#packageNo").select();
		});	
		
		$("#packageNo").unbind("keydown").keydown(function(ex){
			if(ex.keyCode == 13 ) {
				optDisable(false);
				$("#warnMsgDiv").hide();
				$("#labOrderNo").text("");
				$("#labTotalQty").text("");
				$("#startBarcode").val("");
				$("#endBarcode").val("");
				
				var packageNo = $.trim($("#packageNo").val()).toUpperCase();
				if(packageNo == "") {
					$("#warnMsgDiv").show();
					$("#warnMsg").text("请先输入箱名号!");
					$("#packageNo").focus();
					return;
				}
				
				
				$("#startBarcode").focus();
			}
		});
		
		$("#packageNo").unbind("change").change(function() {
			$("#startBarcode").val("");
			$("#endBarcode").val("");
			optDisable(false);
			var packageNo = $.trim($("#packageNo").val()).toUpperCase();
			if(packageNo == "") {
				$("#warnMsgDiv").show();
				$("#warnMsg").text("请先输入箱名号!");
				$("#packageNo").focus();
				return;
			}
			$("#startBarcode").focus();
		});	
		
		
		$("#startBarcode").unbind("keydown").keydown(function(e){
			if(e.keyCode == 13 ) {
				$("#warnMsgDiv").hide();
				var startBarcode = $.trim($("#startBarcode").val()).toUpperCase();
				if(startBarcode == "") {
					$("#warnMsgDiv").show();
					$("#warnMsg").text("请输入首条码!");
					$("#startBarcode").focus();
				}
			}
		});
		
		$("#startBarcode").unbind("change").change( function() {
			var startBarcode = $.trim($("#startBarcode").val()).toUpperCase();
			if(startBarcode != "") {
				checkStartBarcode();
			}
			
		});	
		
		$("#endBarcode").unbind("keydown").keydown(function(e){
			if(e.keyCode == 13 ) {
				$("#warnMsgDiv").hide();
				$("#btnConfirm").focus();
			}
		});
		
		$("#endBarcode").unbind("change").change( function() {
			$("#btnConfirm").focus();
		});	
		
		
		$("#btnConfirm").unbind("click").click(function(){
			$("#warnMsgDiv").hide();
			confirmInbound();
		});
		
		$("#btnCancel").unbind("click").click(function(){
			$("#warnMsgDiv").hide();
			if(confirm("确定取消?")){
				cancelInbound();
			}else{
				$("#startBarcode").select();
			}
		});
	});
	
	function confirmInbound(){
		$("#warnMsgDiv").hide();
		var packageNo = $.trim($("#packageNo").val()).toUpperCase();
		if(packageNo == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请先输入箱名号!");
			$("#packageNo").focus();
			return;
		}
		var startBarcode = $.trim($("#startBarcode").val()).toUpperCase();
		if(startBarcode == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请输入首条码!");
			$("#startBarcode").focus();
			return;
		}
		optDisable(true);
		var lineNo = $.trim($("#lineNo").val()).toUpperCase();
		var endBarcode = $.trim($("#endBarcode").val()).toUpperCase();
		
		DWREngine.setErrorHandler(confirmHandler);
		ScannerInboundManager.cpInboundConfirm(lineNo, "", packageNo, startBarcode, endBarcode, officeCode, function(data){
			if(data.result){
				$("#warnMsgDiv").show();
				$("#warnMsg").text(data.msg);
				ScannerInboundManager.cpInboundCheckStartBarcode(packageNo, startBarcode, officeCode, function(rr){
					if(rr.result){
						if(rr.object != null){
							var resultString = rr.object.ToString();
							$("#labOrderNo").text(resultString.substring(0, resultString.IndexOf("|")));
							$("#labTotalQty").text(resultString.substring(resultString.IndexOf("|") + 1));
						}
					}
				});

				$("#startBarcode").val("");
				$("#endBarcode").val("");
				optDisable(false);
				$("#startBarcode").select();
			}else{
				$("#warnMsgDiv").show();
				$("#warnMsg").text(data.error);
				if(data.object != null){
					$("#btnConfirm").attr("disabled",true); 
					$("#btnCancel").attr("disabled",false);
				}
				$("#startBarcode").select();
			}
		});
	}
	function confirmHandler(msg){
		if(msg==null||msg==""){
			msg="确认操作出现异常!";
		}
		alert(msg);
		optDisable(false);
		$("#warnMsgDiv").show();
		$("#warnMsg").text("确认操作出现异常!");
		$("#startBarcode").select();
	}
	
	function cancelInbound(){
		$("#warnMsgDiv").hide();
		var packageNo = $.trim($("#packageNo").val()).toUpperCase();
		if(packageNo == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请先输入箱名号!");
			$("#packageNo").focus();
			return;
		}
		var startBarcode = $.trim($("#startBarcode").val()).toUpperCase();
		if(startBarcode == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请输入首条码!");
			$("#startBarcode").focus();
			return;
		}
		optDisable(true);
		var endBarcode = $.trim($("#endBarcode").val()).toUpperCase();
		
		DWREngine.setErrorHandler(cancelHandler);
		ScannerInboundManager.cpInboundCancel("", startBarcode, endBarcode, officeCode, function(data){
			optDisable(false);
			$("#endBarcode").val("");
			$("#startBarcode").val("");
			if(data.result){
				$("#warnMsgDiv").show();
				$("#warnMsg").text(data.msg);
				ScannerInboundManager.cpInboundCheckStartBarcode(packageNo, startBarcode, officeCode, function(rr){
					if(rr.result){
						if(rr.object != null){
							var resultString = rr.object.ToString();
							$("#labOrderNo").text(resultString.substring(0, resultString.IndexOf("|")));
							$("#labTotalQty").text(resultString.substring(resultString.IndexOf("|") + 1));
						}
					}
				});
				$("#startBarcode").select();
			}else{
				$("#warnMsgDiv").show();
				$("#warnMsg").text(data.error);
				$("#startBarcode").select();
			}
		});
	}
	function cancelHandler(msg){
		if(msg==null||msg==""){
			msg="取消操作出现异常!";
		}
		alert(msg);
		optDisable(false);
		$("#warnMsgDiv").show();
		$("#warnMsg").text("取消操作出现异常!");
		$("#startBarcode").select();
	}
	
	function checkStartBarcode(){
		$("#warnMsgDiv").hide();
		$("#warnMsg").text("");
		optDisable(false);
		$("#endBarcode").val("");
		$("#labOrderNo").text("");
		$("#labTotalQty").text("");
		
		var packageNo = $.trim($("#packageNo").val()).toUpperCase();
		if(packageNo == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请先输入箱名号!");
			$("#packageNo").focus();
			return;
		}
		
		var startBarcode = $.trim($("#startBarcode").val()).toUpperCase();
		if(startBarcode == "") {
			return;
		}
		DWREngine.setErrorHandler(startBarcodeHandler);
		ScannerInboundManager.cpInboundCheckStartBarcode(packageNo, startBarcode, officeCode, function(data){
			if(data.result){
				if(data.object != null){
					var resultString = data.object.ToString();
					$("#labOrderNo").text(resultString.substring(0, resultString.IndexOf("|")));
					$("#labTotalQty").text(resultString.substring(resultString.IndexOf("|") + 1));
				}
				$("#endBarcode").focus();
			}else{
				$("#warnMsgDiv").show();
				$("#warnMsg").text(data.error);
				$("#startBarcode").val("");
				$("#startBarcode").focus();
			}
		});
	}
	function startBarcodeHandler(msg){
		if(msg==null||msg==""){
			msg="检验条码出现异常!";
		}
		alert(msg);
		$("#warnMsgDiv").show();
		$("#warnMsg").text("检验条码出现异常!");
		$("#warnMsgDiv").show();
		$("#startBarcode").val("");
		$("#startBarcode").focus();
	}
	
</script>
</head>
<body>
	<form id="baseForm">
		<table id="tableQueryForm" width="350px" >
			<tr align="left" >
				<td width="40px" >
					生产线:
				</td>
				<td align="left">
					<input id="lineNo" name="lineNo" class="inputSize"/>
				</td>
			</tr>
			
			<tr align="left">
				<td>
					箱名号:
				</td>
				<td align="left">
					<input id="packageNo" name="packageNo"  class="inputSize"/>
				</td>
			</tr>
			<tr align="left">
				<td>
					首条码:
				</td>
				<td align="left">
					<input id="startBarcode" name="startBarcode"  class="inputSize"/>
				</td>
			</tr>	
			<tr align="left">
				<td>
					尾条码:
				</td>
				<td align="left">
					<input id="endBarcode" name="endBarcode" class="inputSize" />
				</td>
			</tr>
			<tr align="left">
				<td colspan="2">
					<div id="warnMsgDiv">提示:<span id="warnMsg" class="warningMsg"></span></div>
				</td>
			</tr>
			<tr align="left">
				<td colspan="2" >
					<input type="button" id="btnConfirm" class="buttonfont" value="确认" />
					<input type="button" id="btnCancel" class="buttonfont" value="取消" />
					<input type="button" id="btnReset" class="buttonfont" value="清空" />
				</td>
			</tr>
			<tr align="left">
				<td colspan="2" >
					销售订单号:<span id="labOrderNo" class="msgClass"></span>
				</td>
			</tr>
			<tr align="left">
				<td colspan="2" >
					已扫条码数:<span id="labTotalQty" class="msgClass"></span> 
				</td>
			</tr>
		</table>
	</form>
</body>
</html>	