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
String officeCode = SessionContextUserEntity.currentUser().getOfficeCode();
 %>
<script type="text/javascript">


	
	function confirm() {	
		var orderNo=$("#orderNo").val();
		var barCode=$("#barCode").val();
		var qty=$("#qty").val(); 
		LogisticsOrderManager.yclOutboundConfirm(orderNo,barCode,qty,'<%=officeCode%>', function(result) {
			if(result.result){		
				alert(result.msg);
			}
			else{
				alert(result.error);
			}		
		});		
	};
	
	

	
	
</script>
</head>
  
  <body class="easyui-layout">
  	<div region="west" title="原材料出库" iconCls="icon-search"  style="width:400px;">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton"  plain="true" onclick="saoma()">扫码</a>
			<a class="easyui-linkbutton"  plain="true" onclick="confirm()">确认</a>
			<a class="easyui-linkbutton"  plain="true" onclick="cancel()">取消</a>
			<a class="easyui-linkbutton"  plain="true" onclick="clearQueryForm()">重置</a>
		</div>
		<form id="formQuery" class="easyui-form" columns="1" i18nRoot="BasCustomer">
			<input name="orderNo" id="orderNo"  title="投料单号"/>
			<input name="barCode" id="barCode"  title="条码"/>
			<input name="qty" id="qty"   title="数量"/>
		</form>
			<div>物料编码：<a href="#" id="itemName" style="font-size:15px"> </a></div>
			<div>物料名称：<a href="#" id="itemCode" style="font-size:15px"> </a></div>
			<div>订单总数量：<a href="#" id="qty1" style="font-size:15px"> </a></div>
	</div>
	
	<div region="center" title="" iconCls="icon-edit">
	
		
	</div>
	
	
</body>
</html>	