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
	function setAux1(value){
		for(var i = 0; i <lodAux1Data.length; i++){
			if (lodAux1Data[i].id == value){
				return lodAux1Data[i].text;
			}
		}
			return value;
	}
	$(function(){
	   //按钮权限
		$.extend($("#submitOrderGrid").datagrid("options"), {
			onSelect:function(rowIndex,selItem){
				$.fn.datagrid.defaults.onSelect.apply(this, [rowIndex,selItem]);
				selectControl(selItem);
			},
			onUnselect:function(rowIndex,selItem){
				$.fn.datagrid.defaults.onUnselect.apply(this, [rowIndex,selItem]);
				var item=$('#submitOrderGrid').datagrid("getSelected");
				if(item){
					selectControl(item);
				}else{
					controlBtns([],btnIds);
				}
			},onSelectAll:function(rows){
				$.fn.datagrid.defaults.onSelectAll.apply(this, [rows]);
				selectControl(rows[0]);
			},
			onUnselectAll:function(rows){
				$.fn.datagrid.defaults.onUnselectAll.apply(this, [rows]);
				controlBtns([],btnIds);
			},
			onLoadSuccess:function(rows){
			  var rows=$("#submitOrderGrid").datagrid('getData').rows;
			   if(rows.length>0){//显示文本框全部数据
				    $.each(rows,function(index,item){
				    	var remark=rows[index].flow;
				    	if(remark&&remark.length>0){
				    		var tdDiv=$("tr[datagrid-row-index="+(item.rownum-1)+"] td[field='flow'] div");
							Tipped.create(tdDiv,remark, { skin: 'white' });	
						}
				    });
			    }
			}
		});
		$.extend($("#logisticDetailDatagridIn").datagrid("options"), {
		    onLoadSuccess:function(rows){
		      var rows=$("#logisticDetailDatagridIn").datagrid('getData').rows;
				if(rows.length>0){//显示文本框全部数据
				    $.each(rows,function(index,item){
				    	var remark=rows[index].goodsDesc;
				    	var remarks=rows[index].remark;
				    	if(remark&&remark.length>0){
				    		var tdDiv=$("tr[datagrid-row-index="+(item.rownum-1)+"] td[field='goodsDesc'] div");
							   Tipped.create(tdDiv,remark, { skin: 'white' });	
							var tdDivs=$("tr[datagrid-row-index="+(item.rownum-1)+"] td[field='remark'] div");
							   Tipped.create(tdDivs,remarks, { skin: 'white' });	
						}
				    });
			    }		
		    }
		});
		$("#transactionStatus").combobox({
			data:[{"id":'<%=CommonUtil.Pending%>',"text":"草稿"},
				  {"id":'<%=CommonUtil.Active%>',"text":"生效"},
				  {"id":'<%=CommonUtil.Cancel%>',"text":"作废"}
				 ],
			editable:false
		});
		$('#submitOrderGrid').datagrid({
			onDblClickRow : function(rowIndex, rowData) {
				$('#dataTabs').tabs("select", "订单明细");
				var uuid =rowData.LogisticsOrderUuid;//获取UUID
				$("#logisticDetailDatagridIn").datagrid("setQueryFields",[
					{
						fieldName:"LogisticsOrderUuid",
						fieldStringValue:uuid
					}
				]);	 
			   $("#logisticDetailDatagridIn").datagrid("commonQuery", {
					queryType :"LogisticsOrderDetailModel"
				});	
				
			}
		});
	});
	function selectControl(object){
		if(object){
			var cWord = object.controlWord.substring(2,3);
			 if(object.transactionStatus=='<%=CommonUtil.Cancel%>'||cWord=='0'){
				controlBtns([],btnIds);
			}else if(object.transactionStatus=='<%=CommonUtil.Pending%>'){
				controlBtns(btnIdsOne,btnIdsTwo);
			}else if(object.transactionStatus=='<%=CommonUtil.Active%>'){
				controlBtns(btnIdsTwo,btnIdsOne);
			}
		}
	}
	function setDefDate(){
		WmsCommonManager.getDataBaseDateFor_Yyyy_Mm_Dd(function(result){
			$("#dateBegin").datebox("setValue",result);
			$("#dateEnd").datebox("setValue",result);
			$("#cat").datebox("setValue",result);
			$("#can").datebox("setValue",result);
			controlBtns([],btnIds);
		});
	}
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
			queryType:"LogisticsOrderModel",
			paramForm:"searchForm"
		});
	}
	function reset(){
		$("#searchForm").form("clear");
		setDefDate();
		$("#ct").attr("checked",true);
	}
	function addOrder(){
		window.parent.addTabs("入库作业单办理","<%=path%>/jsp/logisiticOrder/inboundEdit.jsp?type=1",true,true);
	}
	function editOrder(){
		var selItem=check();
		if(selItem!=null){
			window.parent.addTabs("入库作业单编辑","<%=path%>/jsp/logisiticOrder/inboundEdit.jsp?logisticsOrderUuid="+selItem.logisticsOrderUuid,true,true);
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
				YclOrderManager.auditOrder(submitOrder[0],null,submitOrder[0].submitOrderUuid.toString(),'<%=officeCode%>',function(data){
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
				YclOrderManager.cancelAuditOrder(submitOrder,null,function(data){
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
			YclOrderManager.delSubmitOrder(uuids.toString(),'<%=officeCode%>',function(returnValue){
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
		controlBtns([],btnIds);
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
	<div region="north" title="查询" border="false" iconCls="myCustomerIcon_searchForm">
		<div class="datagrid-toolbar">
			<a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchOrder();"> 查询 </a>
			<a class="easyui-linkbutton" iconCls="myCustomerIcon_clear" plain="true" onclick="reset();"> 重置 </a>
			
		</div>
		<form id="searchForm" class="easyui-form" columns="4" i18nRoot="InOrderNoHeadYcl">
			<input name="submitOrderNo" id="submitOrderNo" title="入库订单号"  class="easyui-validatebox" onkeyup="strUppercase('#submitOrderNo')" onblur="strTrim('#submitOrderNo')"/>
			<input name="orderNo" id="orderNo"  class="easyui-validatebox" title="订单号"/>  
			<input name="cargoConsigneeCode" id="cargoConsigneeCode"  combo="true" class="easyui-combogrid" title="供应商"  codetype="ALL_CUSTOMER"/>
			<div title="办单日期">
				<input name="dateBegin" id="dateBegin" class="easyui-datebox"  style="width:90px;"/>
				<img src="<%=path%>/images/right.gif"/>
				<input name="dateEnd" id="dateEnd" class="easyui-datebox"  style="width:90px;"/>	
			</div>
			<input name="itemCode" id="itemCode"  title="物料代码" class="easyui-validatebox"/> 
			<!-- <input id="ct" name="ct" type="checkbox" value="true" checked="checked"  > -->
			<input name="transactionStatus"  title="状态" id="transactionStatus" class="easyui-combobox" valueField="id" textField="text" panelHeight="auto" />
			<input name="transactionType"  id="transactionType" type="hidden" value="SIN" />
		</form>
	</div>
	<div region="center" border="false">
		<div class="easyui-tabs" fit="true" border="false" id="dataTabs">
			<div title="订单信息列表">
				<div class="easyui-layout" fit="true">
					<div region="north">
						<div class="datagrid-toolbar">
							<a class="easyui-linkbutton" plain="true"  iconCls="icon-add" onclick="addOrder();">
							增加
							</a>
							<a class="easyui-linkbutton" id="editBtn" plain="true"  iconCls="icon-edit" onclick="editOrder();">
							编辑
							</a>
							<a class="easyui-linkbutton" id="submitBtn" plain="true" iconCls="icon-ok" onclick="submitButton()"> 
							提交
							</a>
							<a class="easyui-linkbutton" id="cancelBtn" plain="true" iconCls="icon-cancel" onclick="cancelSubmitButton()"> 
							取消提交
							</a>
							<a class="easyui-linkbutton" id="delBtn" plain="true" iconCls="myCustomerIcon_remove" onclick="removeButton();">
							删除
							</a>
						</div>
					</div>
					<div region="center">
						<table class="easyui-datagrid" id="submitOrderGrid" fit="true"  border="false" >
							<thead>
								<tr>
								    <th field="transactionStatus" title="状态" align="center" width="50px" codetype="STATUS"></th>
								     <th field="status" title="作业状态" align="center" width="50px" ></th>
								    <th field="logisticsOrderNo"  title="入库单号">
								    <%--<th field="logisticsOrderUuid" title="" type="hidden">--%>
								    <th field="orderNo" title="企业订单号"></th>
								    <th field="itemCode" title="物料代码"></th>									   								  
								    <th field="orderDate" title="办单日期" ></th>	
								     <th field="deliveryDate" title="出运日期"></th>   
								    <th field="cargoConsigneeDesc" title="供应商"></th>				
									<th field="deliveryType" title="类型"></th>
									<th field="agentConsigneeDesc" title="委托单位"></th>
					                 <th field="creator" title="创建人"></th> 
					                <th field="qty" title="数量" align="right"></th>
				                </tr>
							</thead>
						</table>					
					</div>
				</div>
			</div>
			<div title="入库作业单明细">
			<div class="easyui-layout" fit="true">
					<div region="center">
						<table id="logisticDetailDatagridIn" class="easyui-datagrid" fit="true" i18nRoot="InOrderDetailYCL">
							<thead>
								<tr>
		                            <th field="itemCode" title="物料代码"></th>
		                            <th field="goodsDesc" title="物料描述" width="200px;"></th>	
		                            <th field="qty" title="数量" align="right"></th>	
		                            <th field="confirmedQty" align="right" title="入库数"></th>	
		                            <th field="qtyUnitDesc" title="数量"></th>		
		                            <th field="extItemCode" title="其他物料编码" ></th>	
								    <th field="seqNo" title="序列号"></th> 	 	                               														
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