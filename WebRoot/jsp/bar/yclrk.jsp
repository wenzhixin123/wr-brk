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
	

	
	//查询
	function saoma() {		
		var barCode=$("#barCode").val();
		var qty=$("#qty").val(); 
		LogisticsOrderManager.addLodCheckBarcode(barCode,'<%=officeCode%>', function(result) {
			if(result.result){		
				$("#orderNo").text(result.object.orderNo);
				$("#itemCode").text(result.object.itemCode);
				$("#qty1").text(result.object.qty);
				$("#ltMsg").text(result.object.ltMsg);
				$("#aux5").text(result.object.aux5);
				$("#batchNo").text(result.object.batchNo);
			
				
				
			}
			else{
				alert(result.error);
			}
			
		});
		
	};
	
	
	function confirm() {		
		var barCode=$("#barCode").val();
		var qty=$("#qty").val(); 
		LogisticsOrderManager.addLodConfirm(barCode,qty,'<%=officeCode%>', function(result) {
			if(result.result){		
				alert(result.msg);
			}
			else{
				alert(result.error);
			}
			
		});
		
	};
	
	
	function iqcsaoma() {		
		var barCode=$("#barCodeIqc").val();
		var qty=$("#qtyIqc").val(); 
		LogisticsOrderManager.yclIqcBarcode(barCode,'<%=officeCode%>', function(result) {
			if(result.result){		
				$("#qtyIqc").text(result.object.qty);
			}
			else{
				alert(result.error);
			}
			
		});
		
	};
	//重置
	function clearQueryForm() {
		$("#formQuery").form("clear");
	};
	
	function  shouhuoconfirm(){
		var barCode=$("#barCodeRece").val();
		var qty=$("#qtyRece").val(); 
		var packageNo=$("#containerNo").val(); 
		var lotCode=$("#loc").val(); 
		var goodsNature="良品"
		LogisticsOrderManager.receivingConfirm(" ",packageNo,barCode,qty,lotCode,goodsNature,'<%=officeCode%>', function(result) {
			if(result.result){		
				alert(result.msg);
			}
			else{
				alert(result.error);
			}
			
		});
	}
	
	
</script>
</head>
  
  <body class="easyui-layout">
  	<div region="west" title="扫码入库" iconCls="icon-search"  style="width:400px;">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton"  plain="true" onclick="saoma()">扫码</a>
			<a class="easyui-linkbutton"  plain="true" onclick="confirm()">确认</a>
			<a class="easyui-linkbutton"  plain="true" onclick="cancel()">取消</a>
			<a class="easyui-linkbutton"  plain="true" onclick="clearQueryForm()">重置</a>
			
		</div>
		<form id="formQuery" class="easyui-form" columns="1" i18nRoot="BasCustomer">
			<input name="barCode" id="barCode"  title="条码"/>
			<input name="qty" id="qty"   title="数量"/>
			<div>采购订单号：<a href="#" id="orderNo" style="font-size:15px"> </a></div>
			<div>物料编码：<a href="#" id="itemCode" style="font-size:15px"> </a></div>
			<div>订单总数量：<a href="#" id="qty1" style="font-size:15px"> </a></div>
			<div>扫描记录：<a href="#" id="ltMsg" style="font-size:15px"> </a></div>
			<div>供应商：<a href="#" id="aux5" style="font-size:15px"> </a></div>
			<div>批次号：<a href="#" id="batchNo" style="font-size:15px"> </a></div>
	
		</form>
	</div>
	
	<div region="center" title="IQC送检" iconCls="icon-edit">
	
	<div class="datagrid-toolbar">
		<a class="easyui-linkbutton"  plain="true" onclick="iqcsaoma()">扫码</a>
			<a class="easyui-linkbutton"  plain="true" onclick="saoma()">确定</a>
			<a class="easyui-linkbutton"  plain="true" onclick="confirm()">收货</a>
			
		</div>
		<form id="formQuery" class="easyui-form" columns="1" i18nRoot="BasCustomer">
			<input name="barCode" id="barCodeIqc"  title="条码"/>
			<a>
			<div>数量：<a href="#" id="qtyIqc" style="font-size:15px"> </a></div>
			</a>
			
			<input type="radio"  checked="checked" name="sex" title="合格"/>
            <input type="radio"   name="sex" title="不合格"/>
            
			
		</form>
		
	</div>
	<div region="east" title="原材料收货" iconCls="icon-edit" style="width:400px;">
	
	<div class="datagrid-toolbar">
			<a class="easyui-linkbutton"  plain="true" onclick="shouhuosaoma()">扫码</a>
			<a class="easyui-linkbutton"  plain="true" onclick="shouhuoconfirm()">收货</a>
			<a class="easyui-linkbutton"  plain="true" onclick="confirm()">确定</a>
			<a class="easyui-linkbutton"  plain="true" onclick="cancel()">取消</a>
			<a class="easyui-linkbutton"  plain="true" onclick="clearQueryForm()">重置</a>	
		</div>
		<form id="formQuery" class="easyui-form" columns="1" i18nRoot="BasCustomer">
			<input name="barCodeRece" id="barCodeRece"  title="条码"/>
			<input name="qtyRece" id="qtyRece"   title="数量"/>
			<input name="containerNo" id="containerNo"   title="箱名号"/>
			<input name="loc" id="loc"   title="货位"/>
			<select id="cargoType" class="easyui-combobox" name="cargoType" title="货物属性" style="width:100px;">
							<option value="1">良品</option>
							<option value="0">不良品</option>
					</select>
			<a>
			</br>
			长物料号：
			</br>
			货名：
			</br>
			订单总数量：
			</br>
			供应商：
			</br>
			批次号：
			</br>
			</a>
		</form>
		
	</div>
	
</body>
</html>	