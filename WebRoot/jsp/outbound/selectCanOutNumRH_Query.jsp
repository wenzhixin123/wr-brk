<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.*"%>
<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@page import="com.sinotrans.gd.wlp.basicdata.model.BasCustomerModel"%>
<%@page import="com.sinotrans.gd.wlp.basicdata.model.BasProjectModel"%>
<%@include file="/fros-easyui/common/include.jsp" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- jquery easyui -->
		<style type="text/css">
		#query_tableQueryForm td {
			font-size: 12px;
		}
		body {
			background: white;
		}
		</style>
	</head>
	<!-- 2013年7月1日 增加根据查询条件查询rh可出库数量 2014年1月13日 17:54:29 -->
	<body  class="easyui-layout">
		<div id="queryRequire" iconCls="myCustomerIcon_searchForm" border="false" region="north" >
			<!-- 不被重置的必要查询条件 -->
			
				<div class="myCustomer_toolbar">
					<a id="searchButton" class="easyui-linkbutton" href="#" iconCls="icon-search" plain="true" style="float: left" onclick="submitSelectOutbound()">
						查询</a> <div class="datagrid-btn-separator" style="float: left"></div>
					<!-- 这是分割线 -->
					<a id="resetButton" class="easyui-linkbutton" href="#" iconCls="myCustomerIcon_clear" plain="true" style="float: left"  onclick="javascript:$('#logistics_queryForm').form('clear')">
						重置</a> <div class="datagrid-btn-separator" style="float: left"></div>
					<a id="getButton" class="easyui-linkbutton" href="#" iconCls="icon-ok" plain="true" style="float: left" onclick="selectGetButton()">
						确定 </a> <div class="datagrid-btn-separator" style="float: left"></div>
					<a id="undoButton" class="easyui-linkbutton" href="#" iconCls="icon-undo" plain="true" style="float: left" onclick="undoButton()">
						返回 </a>
				</div>
				<form id="logistics_queryForm" class="easyui-form" columns="3" i18nRoot="OutOrderHeadYcl">
				        <input title="作业单号" id="logisticsOrderNo" name="logisticsOrderNo" class="easyui-validatebox" onkeyup="javascript:this.value=this.value.toLocaleUpperCase();"/>
						<input title="订单号"  id="orderNo"  name="orderNo"  class="easyui-validatebox" />
						<input title="条码"  id="barcode"  name="barcode" class="easyui-validatebox" />
						<input title="批次号"  id="batchNo"  name="batchNo" class="easyui-validatebox" />
						<input title="供应商" name="cargoConsigneeCode" id="cargoConsigneeCode" class="easyui-combogrid" codetype="ALL_SUPPLIERS" />
						<input name="cargoConsigneeDesc" id="cargoConsigneeDesc" type="hidden"/>
						<input title="物料编码" name="itemCode" id="itemCode"   class="easyui-validatebox"/> 
						<input title="规格"  id="spec"  name ="spec" class="easyui-validatebox"/>
						<input title="箱名号" id="packageNo" name="packageNo" class="easyui-validatebox" />
						<div title="作业日期">
						<input name="orderDateStart" id="orderDateStart" class="easyui-datebox"  style="width:90px;"/>
						<img src="<%=path%>/images/right.gif"/>
						<input name="orderDateEnd" id="orderDateEnd" class="easyui-datebox"  style="width:90px;"/>
						</div>
				</form>
		</div>
		<div title="库存信息" region="center" border="false">
						<table class="easyui-datagrid" id="checkGoodsStorageTable" fit="true"  border="false" >
							<thead>
								<tr>
								    <th field="inLogisticsOrderNo" align="center" width="95px"  title="入库单号"></th>
								    <th field="itemCode" title="物料编码" ></th>	
								    <th field="goodsDesc"  title="货物名称"></th>								   								  
								    <th field="qty" title="库存数量" ></th>	
								    <th field="grossWeight"  title="毛重(Kg)"></th>   
								    <th field="unitDesc" title="包装单位" ></th>				
									<th field="remainQtyHoldQtySum"  title="可出库数量"></th>
									<th field="productionDate"  formatter=dataformatter title="生产日期" ></th>	
									<th field="cargoConsigneeDesc" align="right" title="供应商"></th>							  
								    <th field="batchNo" title="批次号" ></th> 
					                <th field="secondQty" title="库存总数量" ></th> 
					                <th field="remainSecondQtyHoldSum" title="可出库总数量" ></th> 
					                <th field="lotCode" title="货位" ></th>
					                <th field="netWeight" title="净重(Kg)" ></th>
					                <th field="packageNo" title="箱名号" ></th>
					                <th field="spec" title="规格" ></th>
					                <th field="orderNo" title="ASN号" ></th>
				                </tr>
							</thead>
						</table>					
		</div>
	</body>
<script type="text/javascript">
	var projectCode="<%=request.getParameter("projectCode")%>";
	var flow="<%=request.getParameter("flow")%>";
	var isNoClase=<%=request.getParameter("isNoClase")%>;
	var goodsNature="<%=request.getParameter("goodsNature")%>";
	var agentConsigneeCode="<%=request.getParameter("agentConsigneeCode")%>";
	var cargoControlCode="<%=request.getParameter("cargoControlCode")%>";
	$(function(){
		setTimeout(function(){
			$("#agentConsigneeCode").val(agentConsigneeCode);
			$("#goodsNature").val(goodsNature);
		},400);
	});


   function dataformatter(value,rowData,rowIndex){
	value=rowData.productionDate;
	if(value){
	    var str = value.substr(0, 10);
	    return str;
	}else{
	   return value;
	}
	}
	//取出查询条件
	function submitSelectOutbound(){
		var start=$("#orderDateStart").datebox("getValue");
		var end=$("#orderDateEnd").datebox("getValue");
		if(start > end){
			$.messager.alert("提示","开始不能大于结束时间!","info");
			return; 
		}
		$("#checkGoodsStorageTable").datagrid("commonQuery",{
			queryType:"rhChoiceStockDataSource",
			paramForm:"logistics_queryForm"
		});
	}
	//返回按钮事件
	function undoButton(){
		parent.$('#outRhCanNum').window('close');
	}
	//确定按钮事件
	function selectGetButton(){
		var rowData = $('#checkGoodsStorageTable').datagrid('getSelections');
		if(rowData!=null){
			window.parent.setCanOutNumGird(rowData);
			if(isNoClase){
				//取消选中全部
				$('#checkGoodsStorageTable').datagrid('unselectAll');
			}else{
				//关闭弹出框
				undoButton();
			}
		}else{
			alert("无效操作!");
		}
	}
</script>
</html>
