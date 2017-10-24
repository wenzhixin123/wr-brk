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
<script type="text/javascript">

    $(function(){
    	$("#remainDatagrid").datagrid({
			onDblClickRow:function(rowIndex,rowData){
				var loNo = $("#logisticsOrderNo").val();
				var orderNo = $("#orderNo").val();
				var barcode=$("#barcode").val();
				var goodsKind = $("#goodsKind").val();
				var cargoCode = $("#cargoConsigneeCode").combogrid("getValue");
				var panelNo = $("#panelNo").val();
				var lotCode = $("#lotCode").val();
				$("#remainDetailDatagrid").datagrid("setQueryFields",[
					{
						fieldName : "officeCode",
						fieldStringValue : "<%=scue.getOfficeCode()%>"
					},
					{
						fieldName : "basLocAreaUuid",
						fieldStringValue : rowData.basLocAreaUuid
					},
					{
						fieldName : "itemCode",
						fieldStringValue : rowData.itemCode
					},
					{
						fieldName : "logisticsOrderNo",
						fieldStringValue : loNo
					},
					{
						fieldName : "orderNo",
						fieldStringValue : orderNo
					},
					{
						fieldName : "barcode",
						fieldStringValue : barcode
					},
					{
						fieldName : "extItemCode",
						fieldStringValue : rowData.extItemCode
					},
					{
						fieldName : "goodsKind",
						fieldStringValue : goodsKind
					},
					{
						fieldName : "cargoConsigneeCode",
						fieldStringValue : cargoCode
					},
					{
						fieldName : "panelNo",
						fieldStringValue : panelNo
					},
					{
						fieldName : "lotCode",
						fieldStringValue : lotCode
					}
				]);
				$("#remainDetailDatagrid").datagrid("commonQuery", {
					queryType : "yclRemainStockDeatilQueryDataSource"
		     	});
		     	$("#orderTabs").tabs("select","物料详细状态信息");
		     	showFooterfunction();
			}
		});
    });
		
	function query(){
		$("#remainDatagrid").datagrid("setQueryFields",[
        		{
        			fieldName:"officeCode",
        			fieldStringValue:"<%=officeCode%>"
        		}
        	]);	 
		$("#remainDatagrid").datagrid("commonQuery", {
			queryType : "YclRemainSearchQuery",
			paramForm : "formQuery"
	    });
	    $("#orderTabs").tabs("select","库存信息列表");
	    $("#remainDetailDatagrid").datagrid("loadData",{total:0,rows:[]});
	   
	}
	function clearForm(){
		$("#formQuery").form("clear");
		
	}
	
</script>
</head>

<body class="easyui-layout">
	<div region="north" title="查询条件" border="false">
			<div class="datagrid-toolbar">
				<a class="easyui-linkbutton" iconCls="icon-search" plain="true"
					id="btnQuery" onclick="query();">查询</a>
				<a class="easyui-linkbutton" iconCls="myCustomerIcon_clear" plain="true" id="resert" onclick="clearForm();">重置</a>
			</div>
			<form class="easyui-form"  columns="4" id="formQuery">
				<input name="warehouseCode" class="easyui-combogrid"  title="仓库"   id="warehouseCode"/>
				<input name="locAreaCode" class="easyui-combobox" id="locAreaCode"  title="货区" panelHeight="auto"/>
				<input name="lotCode" id="lotCode"  title="货位" />
				<input name="orderNo" id="orderNo" title="订单号"/>
				<input name="cargoConsigneeCode" id="cargoConsigneeCode"  title="供应商" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
				<input name="barcode" id="barcode" title="条码"/>
				<input name="goodsKind" id="goodsKind" title="物料种类" class="easyui-combogrid" codetype="ALL_ITEM_KIND"/>
				<input name="goodsNature" id="goodsNature" panelHeight="auto" title="物料属性" class="easyui-combogrid" codetype="ALL_ITEM_KIND"/>
				<textarea rows="1" cols="1" name="itemCode"  title="物料编码" id="itemCode" style="width: 150px; height: 35px; font-size: 12px;" class="easyui-validatebox"></textarea>
			</form>
	</div>
	<div region="center" border="false">
			<div class="easyui-tabs" fit="true" id="orderTabs">
				<div title="库存信息列表">
					<div class="easyui-layout" fit="true">
						<div region="center" border="false">
							<table id="remainDatagrid" style="width: auto;" fit="true" class="easyui-datagrid" showFooter="true">
								<thead>
									<tr>
										<th field="itemCode"  align="left" title="物料编码"  ></th>
										<th field="goodsDesc"  align="left"  title="物料描述" width=500px;></th>
										<th field="warehouseName"  align="left"  title="仓库"></th>
										<th field="locAreaName"  align="left"   title="区域"></th>
										<th field="sumRemainQty"  align="right"  title="实际库存"></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div title="物料详细状态信息">
					<div class="easyui-layout" fit="true">
						<div region="north" border="false">
						</div>
						<div region="center" border="false">
							<table id="remainDetailDatagrid"  fit="true" class="easyui-datagrid" showFooter="true">
								<thead>
									<tr>
										<th field="itemCode"  align="left" title="物料编码"></th>
										<th field="goodsDesc"  align="left"  title="物料描述" width=200px;></th>		 
										<th field="lotCode"  align="left" title="货位"></th>
										<th field="barcode"  align="left" title="条码"></th>
										 <th field="goodsNature" align="left" title="物料属性"   width=60px;></th>
										<th field="goodsKind" align="left" title="物料种类"  width=60px;></th>
										<th field="panelNo"  align="left" title="托盘号"></th>
										<th field="remainQty"  align="right" title="实际库存"></th>
										<th field="instockUnitDesc" title="单位" width=50px;></th>
										<th field="logisticsOrderNo" title="入库单号" width=110px;></th>
										<th field="orderNo"  align="left" title="订单号" width=80px;></th>	 
										<th field="cutOffDate" align="left" title="入库日期" width=80px;></th>
										<th field="cargoConsigneeDesc" title="供应商代码"></th>
										<th field="customerName" align="left" title="供应商"></th>
										
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