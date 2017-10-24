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
<script >

$(function(){	
	$('#submitOrderGrid').datagrid({
		onDblClickRow : function(rowIndex, rowData) {
			$('#dataTabs').tabs("select", "订单明细");
			var uuid =rowData.submitOrderUuid;//获取UUID
			$("#submitDetailDatagridIn").datagrid("setQueryFields",[
				{
					fieldName:"submitOrderUuid",
					fieldStringValue:uuid
				}
			]);	 
		   $("#submitDetailDatagridIn").datagrid("commonQuery", {
				queryType :"SubmitOrderDetailModel"
			});	
			
		}
	});
});


	function searchOrder(){
		$("#dataTabs").tabs("select","订单信息列表");
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
		$("#submitOrderGrid").datagrid("commonQuery",{
			queryType:"inboundOrderDataSource",
			paramForm:"searchForm"
		});
	}
	function reset(){
		$("#searchForm").form("clear");
		//setDefDate();
	}
	function addOrder(){
		window.parent.addTabs("入库订单办理","<%=path%>/jsp/order/inboundOrderEdit.jsp?type=1",true,true);
	}
	function editOrder(){
		var selItem=check();
		if(selItem!=null){
				window.parent.addTabs("入库订单编辑","<%=path%>/jsp/order/inboundOrderEdit.jsp?submitOrderUuid="+selItem.submitOrderUuid,true,true);
					
		}
	}
	function check(){
		var selItems=$("#submitOrderGrid").datagrid("getSelections");
		if(selItems!=null&&selItems.length>0){
			if(selItems.length==1){
				return selItems[0];
			}else{
				$.messager.alert("提示","一次只能对一条数据进行操作!","info");
			}
		}else{
			$.messager.alert("提示","请选择您要进行操作的行!","info");
		}
		return null;
	}
	
	//审核
	function submitButton(){
		var uuids=getOrderUuids('<%=CommonUtil.Pending%>');
		
		var submitOrder=$("#submitOrderGrid").datagrid("getSelections");

		if(uuids!=""){
			$.messager.confirm("确认框","您是否确认提交?",function(b){
				if(!b){
					return;
				}
				SubmitOrderManager.auditOrder(submitOrder[0],null,submitOrder[0].submitOrderUuid.toString(),'<%=officeCode%>',function(data){
					if(showMsg(data)){
						gridReload();
					}
				});
			});
		}
	}
	//取消审核
	function cancelSubmitButton(){
		var uuids=getOrderUuids('<%=CommonUtil.Active%>');
		var submitOrder=$("#submitOrderGrid").datagrid("getSelections");
		if(uuids!=""){
			$.messager.confirm("确认框","您是否确认取消提交?",function(b){
				if(!b){
					return;
				}
				SubmitOrderManager.cancelAuditOrder(submitOrder,null,function(data){
					if(showMsg(data)){
						gridReload();
					}
				});
			});
		}
	}
	
	//删除整个加订单
	function removeButton(){
		var uuids=getOrderUuids('<%=CommonUtil.Pending%>');
		if(uuids==null||uuids==""){
			return;
		}
		$.messager.confirm("确认框","您确定删除吗?",function(b){
			if(!b){
				return;
			}
			SubmitOrderManager.delSubmitOrder(uuids.toString(),'<%=officeCode%>',function(returnValue){
				if(showMsg(returnValue)){
					gridReload();
				}
			});
		});		
	}
	function strTrim(obj){
		var str=$(obj).val();
		$(obj).val($.trim(str));
	}
	
	function strUppercase(obj){
		var str=$(obj).val();
		$(obj).val(str.toLocaleUpperCase());
	}
	function gridReload(){
		$("#submitOrderGrid").datagrid("unselectAll");
		$("#submitOrderGrid").datagrid("reload");
	}
	function getOrderUuids(status){
		var orders=$("#submitOrderGrid").datagrid("getSelections");
		var uuids=new Array();
		var errorItems=new Array();
		if(orders&&orders.length>0){
			$.each(orders,function(index,order){
				if(order.transactionStatus!=status){				
					errorItems.push(order.submitOrderNo);
				}else{
					var uuid=order.submitOrderUuid;
					uuids.push(uuid);
				}
			});
		}else{
			$.messager.alert("提示","请选择您要进行操作的行!","info");
		}
		if(errorItems.length>0){
			$.messager.alert("提示","订单:\n"+errorItems.toString()+"\n状态不符合操作要求!");
			return "";
		}
		return uuids.toString();
	}
	function showMsg(returnValue){
		if(returnValue.result){
			$.messager.alert('提示',returnValue.msg,'info');
			return true;
		}else{
			$.messager.alert('提示',returnValue.error,'info');
			return false;
		}
	}
	//控制按钮
	function controlBtns(openBtnId,disBtnId){
		$.each(disBtnId,function(i,btn){
			$("#"+btn).linkbutton("disable").unbind("click");
		});
		$.each(openBtnId,function(i,btn){
			$("#"+btn).linkbutton("enable").unbind("click");
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
		<form id="searchForm" class="easyui-form" columns="4" >
		     <input name="submitOrderNo" id="submitOrderNo" title="入库订单号"  class="easyui-validatebox" onkeyup="strUppercase('#submitOrderNo')" onblur="strTrim('#submitOrderNo')"/>
			 <input name="orderNo" id="orderNo"  class="easyui-validatebox" title="订单号"/>  
			 <input name="cargoConsigneeCode" id="cargoConsigneeCode"   title="供应商" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="itemCode" id="itemCode" title="物料编码"  class="easyui-validatebox"/> 
	        <div title="办单日期">
				<input name="dateBegin" id="dateBegin" class="easyui-datebox"  style="width:90px;"/>
				<img src="<%=path%>/images/right.gif"/>
				<input name="dateEnd" id="dateEnd" class="easyui-datebox"  style="width:90px;"/>	
			</div>
			<input name="transactionStatus" id="transactionStatus" title="状态" class="easyui-combobox"  codetype="STATUS"  panelHeight="auto" />
		</form>
	</div>

	<div region="center" border="false">
		<div class="easyui-tabs" fit="true" border="false" id="dataTabs">
			<div title="订单信息列表">
				<div class="easyui-layout" fit="true">
					<div region="north">
						<div class="datagrid-toolbar">
							<a class="easyui-linkbutton" plain="true" title="新增" iconCls="icon-add" onclick="addOrder();">新增 </a>
							<a class="easyui-linkbutton" id="editBtn" plain="true" title="编辑" iconCls="icon-edit" onclick="editOrder();">编辑 </a>
							<a class="easyui-linkbutton" id="submitBtn" plain="true" title="提交" iconCls="icon-ok" onclick="submitButton()">提交  </a>
							<a class="easyui-linkbutton" id="cancelBtn" plain="true" title="取消提交" iconCls="icon-cancel" onclick="cancelSubmitButton()">取消提交 </a>
							<a class="easyui-linkbutton" id="delBtn" plain="true" title="删除" iconCls="myCustomerIcon_remove" onclick="removeButton();">删除 </a>
						</div>
					</div>
					<div region="center">
						<table class="easyui-datagrid" id="submitOrderGrid" fit="true"  border="false" >
							<thead>
								<tr>
								    <th field="transactionStatus" align="center" width="50px"  title="状态" codetype="STATUS"></th>
								    <th field="submitOrderNo" title="入库订单号">
								    <th field="orderNo" title="企业订单号" ></th>	
								    <th field="workDemand" title="企业订单类型" ></th>	
								    <th field="itemCode" title="物料编码" ></th>									   								  
								    <th field="submitDate" title="办单日期" ></th>	
								     <th field="deliveryDate"  title="到货日期"></th>   
								    <th field="cargoConsigneeDesc" title="供应商" ></th>				
									<th field="deliveryType"  title="业务场景"></th>
									<th field="agentConsigneeDesc"  title="委托单位"></th>
									<th field="cargoConsigneeCode"  title="委托单位代码" ></th>	
									<th field="qty" align="right" title="数量"></th>							  
								    <th field="aux3" title="采购模式" ></th> 
					                 <th field="creator" title="创建人" ></th> 
					              
				                </tr>
							</thead>
						</table>					
					</div>
				</div>
			</div>
			<div title="订单明细">
			<div class="easyui-layout" fit="true">
					<div region="center">
						<table id="submitDetailDatagridIn" class="easyui-datagrid" fit="true" >
							<thead>
								<tr>
		                            <th field="itemCode" title="物料编码"  ></th>
		                            <th field="goodsDesc"  title="物料描述" width="200px;"></th>	
		                            <th field="qty" align="right" title="数量" ></th>	
		                            <th field="confirmedQty" align="right" title="入库数"></th>	
		                            <th field="qtyUnitDesc" title="单位" ></th>		
									<th field="billNo" title="PO号"></th>	
		                            <th field="extItemCode" title="其他物料编码"  ></th>	     	                               														
									<th field="remark" title="备注" width="200px;"></th>																
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