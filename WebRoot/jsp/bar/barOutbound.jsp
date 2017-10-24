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
<title>出库核销</title>
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
		optDisable(false);
		$("#apartmentsbody").empty();
		$("#barcode").val("");
		$("#qty").val("");
		$("#logisticsOrderNo").val("");
	} 
	
	function optDisable(opt){
		$("#btnConfirm").attr("disabled",opt); 
		$("#btnCancel").attr("disabled",opt);
	}
	
	$(document).ready(function(){
		$("#warnMsgDiv").hide();
		$("#logisticsOrderNo").select();
		
		$("#btnReset").unbind("click").click(function(){
			cleanAll();
			$("#logisticsOrderNo").focus();
		});
		
		$("#logisticsOrderNo").unbind("keydown").keydown(function(e){
			if(e.keyCode == 13 ) {
				$("#barcode").select();
			}
		});
		
		$("#logisticsOrderNo").unbind("change").change( function() {
			checkLogisticsOrderNo();
		});	
		
		$("#barcode").unbind("keydown").keydown(function(ex){
			if(ex.keyCode == 13 ) {
				$("#qty").select();
			}
		});
		
		$("#barcode").unbind("change").change(function() {
			checkBarcode();
		});	
		
		//只能输入数字
		$("#qty").keypress(function(event) {   
			var keyCode = event.which;   
            if (keyCode == 46 || (keyCode >= 48 && keyCode <=57) || keyCode == 8){
                return true;   
            }else{  
                return false; 
            }  
        });
		
		$("#qty").unbind("keydown").keydown(function(e){
			if(e.keyCode == 13 ) {
				$("#btnConfirm").focus();
			}
		});
		
		$("#qty").unbind("change").change( function() {
			$("#btnConfirm").focus();
		});	
		

		$("#btnConfirm").unbind("click").click(function(){
			$("#warnMsgDiv").hide();
			confirmOutbound();
		});
		
		$("#btnCancel").unbind("click").click(function(){
			$("#warnMsgDiv").hide();
			if(confirm("确定取消?")){
				cancelOutbound();
			}else{
				$("#qty").select();
			}
		});
	});
	
	
	
	function checkLogisticsOrderNo(){
		$("#warnMsgDiv").hide();
		$("#labOrderNo").text("");
		$("#warnMsgDiv").hide();
		optDisable(false);
		$("#apartmentsbody").empty();
		$("#barcode").val("");
		$("#qty").val("");
		
		var logisticsOrderNo = $.trim($("#logisticsOrderNo").val()).toUpperCase();
		if(logisticsOrderNo == "") {
			$("#logisticsOrderNo").focus();
			return;
		}
		
		ScannerOutboundManager.outboundCheckLogisticsOrderNo(logisticsOrderNo, officeCode, function(data){
			if(data.result){
				if(data.object != null && data.object.length>0){
		    		var str = "";
		    		$("#apartmentsbody").empty();
					$.each(data.object, function(index,obj){
						var itemCode = "";
						var goodsDesc = "";
						var qtyUnitDesc = "";
						var qty = "";
						var deliveredQty = "";
						
						if(obj.itemCode){
							itemCode = obj.itemCode;
						}
						if(obj.goodsDesc){
							goodsDesc = obj.goodsDesc;
						}
						if(obj.qtyUnitDesc){
							qtyUnitDesc = obj.qtyUnitDesc;
						}
						if(obj.qty){
							qty = obj.qty;
						}
						if(obj.deliveredQty){
							deliveredQty = obj.deliveredQty;
						}
			    		str += "<tr><td align='left'>"+itemCode+"</td>";
			    		str += "<td align='left'>"+goodsDesc+"</td>";
			    		str += "<td align='align'>"+qtyUnitDesc+"</td>";
			    		str += "<td align='right'>"+qty+"</td>";
			    		str += "<td align='right'>"+deliveredQty+"</td></tr>";
		    		});
		    		$("#apartmentsbody").append(str);
				}
				$("#barcode").focus();
			}else{
				$("#warnMsgDiv").show();
				$("#warnMsg").text(data.error);
				$("#logisticsOrderNo").val("");
				$("#logisticsOrderNo").focus();
			}
		});
	}
	
	function checkBarcode(){
		$("#warnMsgDiv").hide();
		$("#warnMsg").text("");
		optDisable(false);
		$("#qty").val("");
		
		var logisticsOrderNo = $.trim($("#logisticsOrderNo").val()).toUpperCase();
		if(logisticsOrderNo == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请先输入出库单号!");
			$("#logisticsOrderNo").focus();
			return;
		}
		
		var barcode = $.trim($("#barcode").val()).toUpperCase();
		if(barcode == "") {
			return;
		}
		DWREngine.setErrorHandler(barcodeHandler);
		ScannerOutboundManager.outboundCheckBarcode(logisticsOrderNo, barcode, officeCode, function(data){
			if(data.result){
				if(data.object != null){
					$("#qty").val(data.object);
				}
				$("#qty").select();
			}else{
				$("#warnMsgDiv").show();
				$("#warnMsg").text(data.error);
				if(data.object != null){
					$("#qty").val(data.object);
					$("#btnConfirm").attr("disabled",true); 
					$("#qty").attr("disabled",true); 
					$("#barcode").select();
				}else{
					$("#barcode").val("");
					$("#barcode").focus();
				}
			}
		});
	}
	function barcodeHandler(msg){
		if(msg==null||msg==""){
			msg="检验条码出现异常!";
		}
		alert(msg);
		$("#warnMsgDiv").show();
		$("#warnMsg").text("检验条码出现异常!");
		$("#warnMsgDiv").show();
		$("#barcode").val("");
		$("#barcode").focus();
	}
	
	function confirmOutbound(){
		$("#warnMsgDiv").hide();
		var logisticsOrderNo = $.trim($("#logisticsOrderNo").val()).toUpperCase();
		if(logisticsOrderNo == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请先输入出库单号!");
			$("#logisticsOrderNo").focus();
			return;
		}
		var barcode = $.trim($("#barcode").val()).toUpperCase();
		if(barcode == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请输入条码!");
			$("#barcode").focus();
			return;
		}
		var qty = $.trim($("#qty").val());
		if(qty == 0){
			$("#warnMsgDiv").show();
			$("#warnMsg").text("数量不能为0!");
			$("#qty").val("");
			$("#qty").select();
			return;
		}
		
		optDisable(true);
		
		DWREngine.setErrorHandler(confirmHandler);
		ScannerOutboundManager.confirmOutboundVeri(logisticsOrderNo, barcode, qty, officeCode, function(data){
			optDisable(false);
			if(data.result){
				$("#warnMsgDiv").show();
				$("#warnMsg").text(data.msg);
				ScannerOutboundManager.outboundCheckLogisticsOrderNo(logisticsOrderNo, officeCode, function(rr){
					if(rr.result){
						if(rr.object != null && rr.object.length>0){
				    		var str = "";
				    		$("#apartmentsbody").empty();
							$.each(rr.object, function(index,obj){
								var itemCode = "";
								var goodsDesc = "";
								var qtyUnitDesc = "";
								var qty = "";
								var deliveredQty = "";
								
								if(obj.itemCode){
									itemCode = obj.itemCode;
								}
								if(obj.goodsDesc){
									goodsDesc = obj.goodsDesc;
								}
								if(obj.qtyUnitDesc){
									qtyUnitDesc = obj.qtyUnitDesc;
								}
								if(obj.qty){
									qty = obj.qty;
								}
								if(obj.deliveredQty){
									deliveredQty = obj.deliveredQty;
								}
					    		str += "<tr><td align='left'>"+itemCode+"</td>";
					    		str += "<td align='left'>"+goodsDesc+"</td>";
					    		str += "<td align='align'>"+qtyUnitDesc+"</td>";
					    		str += "<td align='right'>"+qty+"</td>";
					    		str += "<td align='right'>"+deliveredQty+"</td></tr>";
				    		});
				    		$("#apartmentsbody").append(str);
						}
					}
				});
				
				$("#warnMsgDiv").show();
				$("#warnMsg").text(data.msg);
				$("#qty").val("");
				$("#barcode").val("");
				$("#barcode").focus();
				
			}else{
				$("#warnMsgDiv").show();
				$("#warnMsg").text(data.error);
				$("#qty").val("");
				$("#barcode").val("");
				$("#barcode").select();
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
		$("#qty").val("");
		$("#barcode").val("");
		$("#barcode").select();
	}
	
	function cancelOutbound(){
		$("#warnMsgDiv").hide();
		var logisticsOrderNo = $.trim($("#logisticsOrderNo").val()).toUpperCase();
		if(logisticsOrderNo == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请先输入出库单号!");
			$("#logisticsOrderNo").focus();
			return;
		}
		var barcode = $.trim($("#barcode").val()).toUpperCase();
		if(barcode == "") {
			$("#warnMsgDiv").show();
			$("#warnMsg").text("请输入条码!");
			$("#barcode").focus();
			return;
		}

		optDisable(true);
		
		DWREngine.setErrorHandler(cancelHandler);
		ScannerOutboundManager.cancelOutboundVeri(logisticsOrderNo, barcode, officeCode, function(data){
			optDisable(false);
			if(data.result){
				$("#warnMsgDiv").show();
				$("#warnMsg").text(data.msg);
				ScannerOutboundManager.outboundCheckLogisticsOrderNo(logisticsOrderNo, officeCode, function(rr){
					if(rr.result){
						if(rr.object != null && rr.object.length>0){
				    		var str = "";
				    		$("#apartmentsbody").empty();
							$.each(rr.object, function(index,obj){
								var itemCode = "";
								var goodsDesc = "";
								var qtyUnitDesc = "";
								var qty = "";
								var deliveredQty = "";
								
								if(obj.itemCode){
									itemCode = obj.itemCode;
								}
								if(obj.goodsDesc){
									goodsDesc = obj.goodsDesc;
								}
								if(obj.qtyUnitDesc){
									qtyUnitDesc = obj.qtyUnitDesc;
								}
								if(obj.qty){
									qty = obj.qty;
								}
								if(obj.deliveredQty){
									deliveredQty = obj.deliveredQty;
								}
								str += "<tr><td align='left'>"+itemCode+"</td>";
					    		str += "<td align='left'>"+goodsDesc+"</td>";
					    		str += "<td align='align'>"+qtyUnitDesc+"</td>";
					    		str += "<td align='right'>"+qty+"</td>";
					    		str += "<td align='right'>"+deliveredQty+"</td></tr>";
				    		});
				    		$("#apartmentsbody").append(str);
						}
					}
				});
				
				$("#warnMsgDiv").show();
				$("#warnMsg").text(data.msg);
				$("#qty").val("");
				$("#barcode").val("");
				$("#barcode").focus();
				
			}else{
				$("#warnMsgDiv").show();
				$("#warnMsg").text(data.error);
				$("#qty").val("");
				$("#barcode").val("");
				$("#barcode").select();
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
		$("#qty").val("");
		$("#barcode").val("");
		$("#barcode").select();
	}
	
	
	
</script>
</head>
<body>
	<form id="baseForm">
		<table id="tableQueryForm" width="350px" >
			<tr align="left" >
				<td width="55px" >
					出库单号:
				</td>
				<td align="left">
					<input id="logisticsOrderNo" name="logisticsOrderNo" class="inputSize"/>
				</td>
			</tr>
			
			<tr align="left">
				<td>
					出库条码:
				</td>
				<td align="left">
					<input id="barcode" name="barcode"  class="inputSize"/>
				</td>
			</tr>
			<tr align="left">
				<td>
					出库数量:
				</td>
				<td align="left">
					<input id="qty" name="qty"  class="inputSize"/>
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
			<table id="resultTable"  border="1" cellpadding="0" cellspacing="0" align="left">
	  		    <thead>
	    		  <tr>
			        <th width="80px">物料编码</th>
			        <th width="100px">物料名称</th>
			        <th width="40px">单位</th>
			        <th width="60px">总计划数</th>			      
			        <th width="60px">已出库数</th>
	   			  </tr>
	  		  </thead>
	  		<tbody id="apartmentsbody" align="center" height="20">
	  		</tbody>
         </table>
		</table>
	</form>
</body>
</html>	