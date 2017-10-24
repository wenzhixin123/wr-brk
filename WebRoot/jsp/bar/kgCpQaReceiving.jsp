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
<title>QA抽检</title>
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
		optDisable(false);
		$("#apartmentsbody").empty();
		$("#startBarcode").val("");
		$("#endBarcode").val("");
		$("#barcode").val("");
	} 
	
	function optDisable(opt){
		$("#btnConfirm").attr("disabled",opt); 
		$("#btnReceiving").attr("disabled",opt);
	}
	
	$(document).ready(function(){
		$("#warnMsgDiv").hide();
		$("#startBarcode").select();
		
		$("#btnReset").unbind("click").click(function(){
			cleanAll();
			$("#startBarcode").focus();
		});
		
		//只能输入数字
		$("#startBarcode,#endBarcode").keypress(function(event) {   
			var keyCode = event.which;   
            if (keyCode == 46 || (keyCode >= 48 && keyCode <=57) || keyCode == 8){
                return true;   
            }else{  
                return false; 
            }  
        });
		
		$("#startBarcode").unbind("keydown").keydown(function(e){
			if(e.keyCode == 13 ) {
				$("#endBarcode").focus();
			}
		});
		
		$("#startBarcode").unbind("change").change( function() {
			var startBarcode = $.trim($("#startBarcode").val());
			if(startBarcode != "") {
				checkStartBarcode();
			}
			
		});	
		
		$("#endBarcode").unbind("keydown").keydown(function(e){
			if(e.keyCode == 13 ) {
				$("#barcode").focus();
			}
		});
		
		$("#endBarcode").unbind("change").change( function() {
			var endBarcode = $.trim($("#endBarcode").val());
			if(endBarcode != "") {
				checkEndBarcode();
			}
		});	
		
		$("#barcode").unbind("change").change( function() {
			var barcode = $.trim($("#barcode").val()).toUpperCase();
			if(endBarcode != "") {
				checkBarcode();
			}
		});	
		
		$("#barcode").unbind("keydown").keydown(function(e){
			if(e.keyCode == 13 ) {
				$("#endBarcode").focus();
			}
		});
		
		$("#btnConfirm").unbind("click").click(function(){
			confirmPassOrFail();
		});
		
		$("#btnReceiving").unbind("click").click(function(){
			confirmReceiving();
		});
	});
	
	function confirmPassOrFail(){
		$("#warnMsgDiv").hide();
		
		var startBarcode = $.trim($("#startBarcode").val()).toUpperCase();
		if(startBarcode == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请输入首条码编号!");
			$("#startBarcode").focus();
			return;
		}
		var startQty = eval($("#startBarcode").val());
		if(startQty == 0){
			$("#warnMsgDiv").show();
			$("#warnMsg").text("不能为0!");
			$("#startBarcode").select();
			return;
		}

		
		var endBarcode = $.trim($("#endBarcode").val()).toUpperCase();
		if(endBarcode == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请输入尾条码编号!");
			$("#endBarcode").focus();
			return;
		}
		var endQty = eval($("#endBarcode").val());
		if(endQty == 0){
			$("#warnMsgDiv").show();
			$("#warnMsg").text("不能为0!");
			$("#endBarcode").select();
			return;
		}
		
		var barcode = $.trim($("#barcode").val()).toUpperCase();
		if(barcode == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请输入抽检条码!");
			$("#startBarcode").focus();
			return;
		}
		
		var type = "";
		var str = "";

		if ($("#radioFalse").attr('checked')){
			type = "BARCODE_FAIL";
			str = "不合格";
		}else{
			type = "BARCODE_PASS";
			str = "合格";
		}
		
		if(confirm("是否确认" + str + "?")){
			optDisable(true);
			DWREngine.setErrorHandler(confirmPassOrFailHandler);
			ScannerInboundManager.cpReceinvingConfirmStatus(startBarcode, endBarcode, barcode, type, officeCode, function(data){
				optDisable(false);
				if(data.result){
					$("#warnMsgDiv").show();
					$("#warnMsg").text(data.msg);

					if(data.object.object != null && data.object.object.length>0){
			    		var str = "";
			    		$("#apartmentsbody").empty();
						$.each(data.object.items, function(index,obj){
							var batchNo = "";
							var failQty = "";
							var passQty = "";
							var confirmedQty = "";
							
							if(obj.batchNo){
								batchNo = obj.batchNo;
							}
							if(obj.failQty){
								failQty = obj.failQty;
							}
							if(obj.passQty){
								passQty = obj.passQty;
							}
							if(obj.confirmedQty){
								confirmedQty = obj.confirmedQty;
							}
				    		str += "<tr><td align='left'>"+batchNo+"</td>";
				    		str += "<td align='right'>"+failQty+"</td>";
				    		str += "<td align='align'>"+passQty+"</td>";
				    		str += "<td align='left'>"+confirmedQty+"</td></tr>";
			    		});
			    		$("#apartmentsbody").append(str);
					}

					$("#barcode").val("");
					$("#barcode").select();
				}else{
					$("#warnMsgDiv").show();
					$("#warnMsg").text(data.error);
					$("#barcode").val("");
					$("#barcode").select();
				}
			});
			
		}else{
			$("#barcode").val("");
			$("#barcode").select();
		}
		
		
	}
	function confirmPassOrFailHandler(msg){
		if(msg==null||msg==""){
			msg="确认操作异常!";
		}
		alert(msg);
		optDisable(false);
		$("#warnMsgDiv").show();
		$("#warnMsg").text("确认操作异常!");
		$("#barcode").select();
	}
	
	
	
	function confirmReceiving(){
		$("#warnMsgDiv").hide();
		
		var startBarcode = $.trim($("#startBarcode").val()).toUpperCase();
		if(startBarcode == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请输入首条码编号!");
			$("#startBarcode").focus();
			return;
		}
		var startQty = eval($("#startBarcode").val());
		if(startQty == 0){
			$("#warnMsgDiv").show();
			$("#warnMsg").text("不能为0!");
			$("#startBarcode").select();
			return;
		}

		
		var endBarcode = $.trim($("#endBarcode").val()).toUpperCase();
		if(endBarcode == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请输入尾条码编号!");
			$("#endBarcode").focus();
			return;
		}
		var endQty = eval($("#endBarcode").val());
		if(endQty == 0){
			$("#warnMsgDiv").show();
			$("#warnMsg").text("不能为0!");
			$("#endBarcode").select();
			return;
		}
		
		
		var barcode = $.trim($("#barcode").val()).toUpperCase();
		if(barcode == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请输入抽检条码!");
			$("#startBarcode").focus();
			return;
		}
		
		if(confirm("如确认自检完成,将会把这范围批次的成品全部收货! 是否确定?")){
			optDisable(true);
			
			DWREngine.setErrorHandler(confirmReceivingHandler);
			ScannerInboundManager.cpReceinvingConfirmRece(startBarcode, endBarcode, barcode, officeCode, function(data){
				optDisable(false);
				$("#warnMsgDiv").show();
				if(data.result){
					$("#warnMsg").text(data.msg);
				}else{
					$("#warnMsg").text(data.error);
				}
				$("#apartmentsbody").empty();
				$("#labOrderNo").text("");
				$("#barcode").val("");
				$("#barcode").select();
			});
		}else{
			$("#barcode").val("");
			$("#barcode").select();
		}
		
	}
	function confirmReceivingHandler(msg){
		if(msg==null||msg==""){
			msg="自检完成收货操作异常!";
		}
		alert(msg);
		optDisable(false);
		$("#warnMsgDiv").show();
		$("#warnMsg").text("自检完成收货操作异常!");
		$("#barcode").select();
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
			msg="取消操作异常!";
		}
		alert(msg);
		optDisable(false);
		$("#warnMsgDiv").show();
		$("#warnMsg").text("取消操作异常!");
		$("#startBarcode").select();
	}
	
	
	
	function checkStartBarcode(){
		$("#warnMsgDiv").hide();
		$("#endBarcode").val("");
		$("#barcode").val("");
		$("#labOrderNo").text("");
		optDisable(false);
		$("#apartmentsbody").empty();
		
		var startBarcode = $.trim($("#startBarcode").val()).toUpperCase();
		if(startBarcode == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请输入首条码编号!");
			$("#startBarcode").focus();
		}
		var startQty = eval($("#startBarcode").val());
		if(startQty == 0){
			$("#warnMsgDiv").show();
			$("#warnMsg").text("不能为0!");
			$("#startBarcode").focus();
		}else{
			$("#endBarcode").focus();
		}
	}
	
	function checkEndBarcode(){
		$("#warnMsgDiv").hide();
		$("#barcode").val("");
		
		$("#labOrderNo").text("");
		optDisable(false);
		$("#apartmentsbody").empty();
		
		var endBarcode = $.trim($("#endBarcode").val()).toUpperCase();
		if(endBarcode == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请输入尾条码编号!");
			$("#endBarcode").focus();
		}
		var endQty = eval($("#endBarcode").val());
		if(endQty == 0){
			$("#warnMsgDiv").show();
			$("#warnMsg").text("不能为0!");
			$("#endBarcode").focus();
		}else{
			$("#barcode").focus();
		}
	}
	
	
	function checkBarcode(){
		$("#warnMsgDiv").hide();
		$("#labOrderNo").text("");
		optDisable(false);
		$("#apartmentsbody").empty();
		
		var startBarcode = $.trim($("#startBarcode").val()).toUpperCase();
		if(startBarcode == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请输入首条码编号!");
			$("#startBarcode").focus();
			return;
		}
		var startQty = eval($("#startBarcode").val());
		if(startQty == 0){
			$("#warnMsgDiv").show();
			$("#warnMsg").text("不能为0!");
			$("#startBarcode").select();
			return;
		}

		
		var endBarcode = $.trim($("#endBarcode").val()).toUpperCase();
		if(endBarcode == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请输入尾条码编号!");
			$("#endBarcode").focus();
			return;
		}
		var endQty = eval($("#endBarcode").val());
		if(endQty == 0){
			$("#warnMsgDiv").show();
			$("#warnMsg").text("不能为0!");
			$("#endBarcode").select();
			return;
		}
		
		
		var barcode = $.trim($("#barcode").val()).toUpperCase();
		if(barcode == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请输入抽检条码!");
			$("#startBarcode").focus();
			return;
		}
		
		optDisable(true);
		
		DWREngine.setErrorHandler(checkBarcodeHandler);
		ScannerInboundManager.cpReceinvingCheckBarcode(startBarcode, endBarcode, barcode, officeCode, function(data){
			optDisable(false);
			if(data.result){
				
				if(data.object.orderNo != null){
					$("#labOrderNo").text(data.object.orderNo);
				}

				if(data.object.items != null && data.object.items.length>0){
		    		var str = "";
		    		$("#apartmentsbody").empty();
					$.each(data.object.items, function(index,obj){
						var batchNo = "";
						var failQty = "";
						var passQty = "";
						var confirmedQty = "";
						
						if(obj.batchNo){
							batchNo = obj.batchNo;
						}
						if(obj.failQty){
							failQty = obj.failQty;
						}
						if(obj.passQty){
							passQty = obj.passQty;
						}
						if(obj.confirmedQty){
							confirmedQty = obj.confirmedQty;
						}
			    		str += "<tr><td align='left'>"+batchNo+"</td>";
			    		str += "<td align='right'>"+failQty+"</td>";
			    		str += "<td align='align'>"+passQty+"</td>";
			    		str += "<td align='left'>"+confirmedQty+"</td></tr>";
		    		});
		    		$("#apartmentsbody").append(str);
				}
				
				
				//是否已收货
				if (data.object.rece != null) {
					if(confirm("该条码已收货,如果要取消收货,该范围条码都会被取消收货! 是否取消?")){
						 CancelReceiving();
						 return;
					}
                }
				
				
				 //是否合格
                if (data.object.status != null){
                	var status = data.object.status;
                	if(status == "pass"){
                        $("#radioPass").attr('checked',true);
						$("#radioFalse").attr('checked',false);
						
                        $("#warnMsgDiv").show();
        				$("#warnMsg").text("该条码已检验为合格!");
                	}else{
						$("#radioPass").attr('checked',false);
						$("#radioFalse").attr('checked',true);
                        
                        $("#warnMsgDiv").show();
         				$("#warnMsg").text("该条码已检验为不合格!");
                	}
                }
				$("#barcode").select();
			}else{
				$("#warnMsgDiv").show();
				$("#warnMsg").text(data.error);
				$("#barcode").val("");
				$("#barcode").select();
			}
		});
	}
	
	function checkBarcodeHandler(msg){
		if(msg==null||msg==""){
			msg="检验抽检条码出现异常!";
		}
		alert(msg);
		optDisable(false);
		$("#warnMsgDiv").show();
		$("#warnMsg").text("检验抽检条码出现异常!");
		$("#barcode").select();
	}
	
</script>
</head>
<body>
	<form id="baseForm">
		<table id="tableQueryForm" width="250px" >
			<tr align="left">
				<td>
					首条码编号:
				</td>
				<td align="left">
					<input id="startBarcode" name="startBarcode"  class="inputSize"/>
				</td>
			</tr>	
			<tr align="left">
				<td>
					尾条码编号:
				</td>
				<td align="left">
					<input id="endBarcode" name="endBarcode" class="inputSize" />
				</td>
			</tr>
			<tr align="left">
				<td>
					抽检条码:
				</td>
				<td align="left">
					<input id="barcode" name="barcode" class="inputSize" />
				</td>
			</tr>
			<tr align="left">
				<td colspan="2" >
					销售订单号:<span id="labOrderNo" class="msgClass"></span>
				</td>
			</tr>
			<tr align="left">
				<td colspan="2" >
					<input type="radio" name="radio" id="radioPass" CHECKED>合格
					<input type="radio" name="radio" id="radioFalse" >不合格
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
					<input type="button" id="btnReceiving" class="buttonfont" value="自检完成" />
					<input type="button" id="btnReset" class="buttonfont" value="清空" />
				</td>
			</tr>
		</table>
		<table id="resultTable"  border="1" cellpadding="0" cellspacing="0" align="left">
	  		    <thead>
	    		  <tr>
			        <th width="80px">抽检批次</th>
			        <th width="60px">合格数</th>
			        <th width="60px">不合格数</th>			      
			        <th width="60px">收货数</th>
	   			  </tr>
	  		  </thead>
	  		<tbody id="apartmentsbody" align="center" height="20">
	  		</tbody>
         </table>
	</form>
</body>
</html>	