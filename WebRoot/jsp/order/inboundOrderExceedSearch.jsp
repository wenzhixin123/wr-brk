<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.sinotrans.gd.wms.hub.util.HUB_CommonUtil"%>
<%@page import="com.sinotrans.framework.core.util.MessageUtils"%>
<%@page import="com.sinotrans.gd.wms.system.entity.SessionContextUserEntity"%>
<%@page import="com.sinotrans.gd.wms.ycl.util.YclCommonUtil"%>
<%@page import="com.sinotrans.gd.wms.util.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>
<%
	SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
	String officeCode = SessionContextUserEntity.currentUser().getOfficeCode();
%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<script type="text/javascript"src="<%=contextPath %>/js/util/wms_commAlertToFunction.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/util/myUtil.js"></script>
	
<script type="text/javascript">
var office ="<%=scue.getOfficeCode() %>";
var tyoe=null;
var cfQuery=null;
var sfQuery=null;
     $(function(){
    	setDefDate();
    	provinceToCity();
    	setDeliveryStateComb();//发货状态
    	var logisticsdatagird='#submitOrderGrid';
    	
		$(logisticsdatagird).datagrid("options").onLoadSuccess=function(rowIndex, rowData){
			
			if(!tyoe){
			var rows=$(logisticsdatagird).datagrid('getData');
			var qty=0;
			var sumQty=0;
			var confirmedQty=0;
				if(rows.rows.length>0){
				$(logisticsdatagird).datagrid({showFooter:true});
					 $.each(rows.rows,function(index,rowData){
						qty+=parseFloat(rowData.qty);
						confirmedQty+=parseFloat(rowData.confirmedQty);
					}); 
					tyoe="OK";
					var orderJason=$("#searchForm").form('getData');
					dwr.engine.setAsync(false);
					
					YclOrderManager.yclOutboundOrderExceedTotal(orderJason,office,deliveryState,function(data){
				 		if(data.result){
				 			sumQty=data.object.qtySum;
				 			totalConfirmedQty=data.object.totalConfirmedQty;
				 		}
				 		
				 		});
					dwr.engine.setAsync(true);
					$(logisticsdatagird).datagrid('loadData',{rows:rows.rows,footer:[
					 {"itemCode":"单页统计","qty":qty,"confirmedQty":confirmedQty},
					 {"itemCode":"总数统计","qty":sumQty,"confirmedQty":totalConfirmedQty}
					 ]});
				}else{
					$(logisticsdatagird).datagrid({showFooter:false});
					
				}
			}else{
					tyoe=null;
			}
		}
    });
    
    function setDeliveryStateComb(){
    	$("#deliveryState").combobox({
			data:[{'id':'cf','text':'超发'},
				  {'id':'sf','text':'少发'},
				  {'id':'wf','text':'未发'}
				 ],
			valueField:'id',
			textField:'text',
			editable:true,
			panelHeight : 'auto',
			width : 60
		});
	}
    
    
    function reset(){
		$("#searchForm").form("clear");
		setDefDate();
	
	}
    function setDefDate(){
		WmsCommonManager.getDataBaseDateFor_Yyyy_Mm_Dd(function(result){
			$("#dateBegin").datebox("setValue",result);
			$("#dateEnd").datebox("setValue",result);
			
		});
	}
    function searchOrder(){
		$("#dataTabs").tabs("select","入库订单信息列表");
	    var a=$("#orderNo").val().length;
      	if(a>9){
    		//$.messager.alert("提示","ASN号不能大于9位!","info");
    		//return;
        }
        var start=$("#dateBegin").datebox("getValue");
		var end=$("#dateEnd").datebox("getValue");
		if(start > end){
			$.messager.alert("提示","开始不能大于结束时间!","info");
			return; 
		}	
		/**	
			if($("input[name='cf']")[0].checked==true && $("input[name='sf']")[0].checked==true){
				$.messager.alert("提示","不能同时选择超发与少发!","info");
				return;
			}	
			cfQuert=$("input[name='cf']")[0].checked;
			sfQuert=$("input[name='sf']")[0].checked;
		*/
		  deliveryState = $("#deliveryState").combobox('getValue');
		 
		$("#submitOrderGrid").datagrid("commonQuery",{
			queryType:"yclOutboundOrderExceedDataSource",
			paramForm:"searchForm"
		});
		tyoe=null;
	}
	//后台加载任务分类
	function provinceToCity(){
		BasCodeDefManager.findByTypeCode('<%=YclCommonUtil.OPTION_OUT_WORK_DEMAND%>','<%=officeCode%>',true,function(spj){
			if(spj.length>0){
				var city=new Array();
				for(var i=0;i<spj.length;i++){
					var obj=new Object();
					obj.id=spj[i].displayValue;
					obj.text=spj[i].displayValue;
					city.push(obj);
				};
				
				$("#workDemandTypeCode").combobox({valueField:"id",textField:"text",editable:false});
				$("#workDemandTypeCode").combobox("loadData",city);
			};
		});
	}
</script>
<style type="text/css">
	.tdText{
		text-align: right;
	}
</style>
</head>

<body class="easyui-layout">
	<div region="north" title="<bean:message bundle='hub.hubOrder' key='SubmitOrderHead.titleHeadQuery'/>" border="false" iconCls="myCustomerIcon_searchForm">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchOrder();">
			<bean:message bundle="wms.common" key="global.search" /></a>
			<a class="easyui-linkbutton" iconCls="myCustomerIcon_clear" plain="true" onclick="reset();">
			<bean:message bundle="wms.common" key="global.reset" /></a>
			
		</div>
		<form id="searchForm" class="easyui-form" columns="5" >
			<input name="orderNo" id="orderNo"  class="easyui-validatebox" title='工单号'/>
			<input name="officeCode" id="officeCode" type="hidden" value="<%=scue.getOfficeCode() %>"/>
			<input name="itemCode" id="itemCode"  class="easyui-validatebox" title="物料编码"/> 
			<div>
			<input name="dateBegin" id="dateBegin" class="easyui-datebox"  style="width:90px;" title="办单日期"/>
			<img src="<%=contextPath%>/images/icon/right.gif"/>
			<input name="dateEnd" id="dateEnd" class="easyui-datebox"  style="width:90px;"/>	
			</div>
			<input name="workDemandTypeCode" id="workDemandTypeCode" class="easyui-combobox"  editable='false' title="作业要求" />
			<!-- <input id="cf" name="cf" title='　　超发' type="checkbox" value="true" >
				<input id="sf" name="sf" title='　　少发'  type="checkbox" value="true" >
			-->
			<input id="deliveryState" name="deliveryState" class="easyui-combobox" title="发货状态"/>
		</form>
	</div>
	<div region="center" border="false" >
		<div class="easyui-tabs" fit="true" border="false" id="dataTabs">
			<div title="订单信息列表">
				<div class="easyui-layout" fit="true">
					<div region="center">
						<table class="easyui-datagrid" id="submitOrderGrid" fit="true" i18nRoot="InOrderNoHeadYcl" border="false" showFooter="true">
							<thead>
								<tr>
								    <th field="orderNo" title='工单号'>
					                <th field="itemCode" title='物料号'></th> 
					                <th field="goodsDesc" title='物料描述' width="200px"></th> 
					                <th field="cargoConsigneeCode" title='供应商代码' ></th> 
					                <th field="cargoConsigneeDesc" title='供应商名称' width="200px"></th>   
					                <th field="qty" title='需求数'></th>   
					                <th field="confirmedQty" title='实发数'></th>
					                <th field="cyQty" title='差异数'></th>
					                <th field="workDemand" title="作业要求"></th>
					                <th field="daBatchNo" title="批次号"></th>  
				                </tr>
							</thead>
							<tfoot>
								<tr>
									<td field="goodsDesc">单页统计</td>
								</tr>
								<tr>
									<td field="goodsDesc">总数统计</td>
								</tr>
							</tfoot>
						</table>					
					</div>
				</div>
			</div>
		
		</div>
	</div>
</body>
</html>