<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.sinotrans.framework.core.util.MessageUtils,
com.sinotrans.gd.wms.system.entity.SessionContextUserEntity,
com.sinotrans.gd.wms.ycl.util.YclCommonUtil,
com.sinotrans.gd.wms.util.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>
<%
	String path = request.getContextPath();
	SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
	String submitOrderUuid = request.getParameter("submitOrderUuid");
	String officeCode = SessionContextUserEntity.currentUser().getOfficeCode();
%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<script type="text/javascript" src="<%=path%>/js/util/myUtil.js"></script>
<script type="text/javascript" src="<%=path%>/js/export2Excel.js"></script>

<script type="text/javascript">
var submitOrderUuid ='<%=submitOrderUuid%>';
//Pending为openBtnId,Active为disBtnId
	var btnIdsOne=['delBtn','submitBtn'];
//Pending为disBtnId,Active为openBtnId
	var btnIdsTwo=['cancelBtn','disannul'];
//所有需要控制的按钮id
var btnIds=['saveBtn','submitBtn','delBtn','cancelBtn','disannul','detailDelBtn','detailAddBtn'];

var sodAux1Data=[
   		      <%=StringUtil.toJsonArray(YclCommonUtil.ORG_ID_MAP)%>];
	$(function(){
		initCombox();
		intReceiptComBox();
		$('#errorDialog').dialog('close');
		if(submitOrderUuid!=null&&submitOrderUuid!='null'){
			reloadDate(submitOrderUuid);
		}else{
			setDefDate();
		}
		$("#submitDetailDatagridIn").datagrid("options").onBeforeEdit = function(rowIndex, rowData) {
			if(rowData.transactionStatus && rowData.transactionStatus!='<%=YclCommonUtil.Pending%>'){
			 	return false;
			}
		}

	});
  function initCombox(){	
		setCombobox("<%=YclCommonUtil.OPTION_OUT_WORK_TYPE%>",'deliveryType');
		setCombobox("<%=YclCommonUtil.OUT_WORK_DEMAND%>",'workDemand');
		setCombobox("<%=YclCommonUtil.FLOW_TYPE_CONFIG%>",'flow');
		$("#aux3").combobox({
		  	data:[
	   		      <%=StringUtil.toJsonArray(YclCommonUtil.ORG_ID_MAP)%>
		              	],
		  	valueField: "id",  
			panelHeight:"auto",
            textField: "text"
		});
		setHiddenVal("#cargoConsigneeCode","#cargoConsigneeDesc");
		setHiddenVal("#agentConsigneeCode","#agentConsigneeDesc");
		
	}
   function intReceiptComBox(){
		$("#jmReceiptType").combobox({
			panelHeight:'auto',
			width:80,
			data:[{'id':'1','text':'出库作业单'},{'id':'2','text':'买卖单'}],
			valueField:'id',
			textField:'text'		
		});
		$("#jmReceiptType").combobox('select',"1");
	}
  function setCombobox(typeCode,comboboxId){
	  YclCommonManager.systemCodeCodeDef(typeCode,"<%=YclCommonUtil.Active%>","<%=scue.getOfficeCode()%>",function(spj){
			if(spj.object){
				$('#'+comboboxId).combobox({ //货物类型
					valueField:"id",
					textField:"text",
					data:spj.object,
					editable:false
				});
			}
		});	
	}
	function setHiddenVal(codeId,descId){		
		$(codeId).combogrid({
    		onSelect:function(index,row){
    			$(descId).val(row.customerName);
    		}
		});
	}
	
	//回显数据
	function reloadDate(submitOrderUuid){
		dwr.engine.setAsync(false);
		HUBOrderManager.findSubmitOrderByUuid(submitOrderUuid,function(returnValue){
			if(returnValue.result){				
			
				$("#submitOrderForm").form("setData",returnValue.object);	
							
				setSourceDataGrid("#submitDetailDatagridIn",returnValue.object.submitOrderUuid);
				if(returnValue.object.transactionStatus=='<%=YclCommonUtil.Cancel%>'){
					controlBtns([],btnIds);
					disableForm(true);
				}else if(returnValue.object.transactionStatus=='<%=YclCommonUtil.Active%>'){
				controlBtns(btnIdsTwo,btnIdsOne);
				disableForm(true);
				}else if(returnValue.object.transactionStatus=='<%=YclCommonUtil.Pending%>'){
					controlBtns(btnIdsOne,btnIdsTwo);
				    disableForm(false);
				}	
<%--				$("#deliveryType").combobox({--%>
<%--					onLoadSuccess:function(none){--%>
<%--							if(none.length>0){--%>
								$("#deliveryType").combobox('setValue',returnValue.object.deliveryType);
								$("#workDemand").combobox('setValue',returnValue.object.workDemand);
								$("#flow").combobox('setValue',returnValue.object.flow);
<%--							}--%>
<%--						}--%>
<%--					});	--%>
<%--				$("#transactionStatus").combobox({--%>
<%--					onLoadSuccess:function(none){--%>
<%--							if(none.length>0){																--%>
								$("#transactionStatus").combobox('setValue',returnValue.object.transactionStatus);
<%--							}--%>
<%--						}--%>
<%--					});	--%>
				$("#config_code").combobox('setValue',returnValue.object.configCode);
			}else{
				$.messager.alert('提示',returnValue.error,'info');
			}
		});
		dwr.engine.setAsync(true);
	}
	
	//查询明细，回显使用
	function setSourceDataGrid(gridId,submitOrderUuid){
		$(gridId).datagrid("acceptChanges");
		$(gridId).datagrid("setQueryFields",[
			{
				fieldName:"submitOrderUuid",
				fieldStringValue:submitOrderUuid
			}
		]);
		$(gridId).datagrid("commonQuery",{
			queryType:"YclSubByUuidQuery"
		});
	}
	
	//新增
	function appendRow(){
		$.messager.confirm('确认框','确认新增将清空界面所有数据!',function(b){
			if(b){
				reset();
			}
		});
	}
	function reset(){	
		setDefDate();
		$("#submitDetailDatagridIn").datagrid("loadData",{total:0,rows:[]});
	}
	
	//form控件控制
	function disableForm(b){
		$('#submitOrderForm').form("readonly",b);
		if(!b){
			$("#deliveryType,#config_code,#workDemand,#flow").combobox({
				"editable":false
			});
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
	//返回入库单头状态
	function getStatus(){
		var editForm=$('#submitOrderForm').form("getData");
		if(editForm){
			var status=editForm.transactionStatus;
			return status;
		}
		return null;
	}
	
	//设置默认数据
	function setDefDate(){
		controlBtns(btnIdsOne,btnIdsTwo);
		$("#transactionType").val('<%=YclCommonUtil.TRANSACTIONTYPE_SOT%>');	
		ECCommonManager.getDefaultPlanConfig("<%=YclCommonUtil.OUTV%>","<%=scue.getOfficeCode()%>",function(data){
			disableForm(false);
			$('#submitOrderForm').form("setData",{
				transactionStatus:'<%=YclCommonUtil.Pending%>'
			});
			//$("#config_code").combobox("setValue",data.configCode);
          $("#config_code").combobox({
				onLoadSuccess:function(none){
						if(none.length>0){
							$("#config_code").combobox('setValue',data.configCode);
						}
					}
				});			
			setTime();
			WmsCommonManager.getDefaultDeliveyType('<%=YclCommonUtil.OPTION_OUT_WORK_TYPE%>',"<%=scue.getOfficeCode()%>",function(returnValue){
				$("#deliveryType").combobox("setValue",returnValue);	
			});
			
			initCombox("#workDemand","<%=YclCommonUtil.OUT_WORK_DEMAND%>");
			initCombox("#flow","<%=YclCommonUtil.FLOW_TYPE_CONFIG%>");
		}); 
	}

	//保存单头和明细
	function saveHeadAndDetail(){
		if(validateMustInput()){
			var submitOrder=$('#submitOrderForm').form('getData');		
			var submitOrderDetail=$('#submitDetailDatagridIn').datagrid("getData").rows;
			var vali=true;
			$("#submitDetailDatagridIn").datagrid('endEdit');
			if(submitOrderDetail!='' && submitOrderDetail.length>0){
				$("#submitDetailDatagridIn").datagrid('acceptChanges');
				$.messager.confirm("确认框","确认保存吗?",function(b){
					if(b){
						$.each(submitOrderDetail,function(index,row){
							if(!row.qty){
							    vali=false;
								return;
							}
						}); 
						if(!vali){
							$.messager.alert('提示','明细数量不能为空!','info');
							return;
							} 						
						submitOrder.deliveryType = $("#deliveryType").combobox("getText"); //设值
					    submitOrder.workDemand = $("#workDemand").combobox("getText");
					    submitOrder.flow = $("#flow").combobox("getText");
						YclOrderManager.saveSubmitOrderSot(submitOrder,submitOrderDetail,'<%=officeCode%>',function(spj){
							if(spj.result){
								 reloadDate(spj.object.submitOrderUuid); //回显数据的函数
								$.messager.alert("提示",'操作成功',"info");
							}else{
								$.messager.alert("提示",data.error,"info");
							}
						});
					}
				});
			}else{
				$.messager.alert('提示','请选择货物明细信息!','info');
			}
		}
	}
	
	//提交单头和明细
	function submitBtn(){
		//判断在控制节点页面是否有配置，无配置则完成提交操作
		//香港经贸--提交生成作业单时提示用户选择单据类型，“出库”和“买卖”作业单。
		//OptionControlManager.getSoSubmitControl("<%=officeCode%>",function(data){
		//	if(data){
		//		$("#receiptDialog").dialog("open");
		//	}else{
		//		toSubmitOrder();
		//	}
		//});
		toSubmitOrder();
	}
	function toSubmitOrder(){
		if(validateMustInput()){
			var submitOrder=$('#submitOrderForm').form('getData');
			if(submitOrder.submitOrderUuid==undefined){
				 $.messager.alert('提示','请先保存数据!','info');
				 return;
			}		
			var submitOrderDetail=$('#submitDetailDatagridIn').datagrid("getData").rows;
			$('#submitDetailDatagridIn').datagrid("endEdit");
			if(submitOrderDetail!='' && submitOrderDetail.length>0){
				$("#submitDetailDatagridIn").datagrid('acceptChanges');
				$.messager.confirm("确认框","确认保存并提交吗?",function(b){
					if(b){
						submitOrder.deliveryType = $("#deliveryType").combobox("getText"); //设值
						submitOrder.workDemand = $("#workDemand").combobox("getText"); //设值
						submitOrder.flow = $("#flow").combobox("getText");
						YclOrderManager.auditOrderSot(submitOrder,submitOrderDetail,submitOrder.submitOrderUuid.toString(),"<%=officeCode%>",function(data){
							if(data.result){
								reloadDate(data.object.submitOrderUuid); //回显数据的函数						
								$.messager.alert("提示",data.msg,"info");
							}else{
								$.messager.alert("提示",data.error,"info");
							}
						});
					}
				});
			}else{
				$.messager.alert('提示','货物明细信息为空!','info');
			}
	  	}
	}
	//取消提交
	function cancelSubmitBtn(){
		if(validateMustInput()){
		var submitOrder=$('#submitOrderForm').form('getData');		
		var submitOrderDetail=$('#submitDetailDatagridIn').datagrid("getData").rows;
		if(submitOrderDetail!='' && submitOrderDetail.length>0){
			$.messager.confirm("确认框","确认取消提交吗?",function(b){
				if(b){
					YclOrderManager.cancelAuditOrderSot(submitOrder,submitOrderDetail,function(data){
						if(data.result){
							reloadDate(data.object.submitOrderUuid); //回显数据的函数					
							$.messager.alert("提示","取消提交成功","info");
						}else{
							$.messager.alert("提示",data.error,"info");
						}
					});
				}
			});
		}else{
			$.messager.alert('提示','货物明细为空!','info');
		}
	  }
	}
	
	//作废
	function disannul(){
		if(validateMustInput()){
		var soUuid=$('#submitOrderForm').form('getData').submitOrderUuid;	
		var submitOrder=$('#submitOrderForm').form('getData');	
		if(soUuid==undefined){
			  $.messager.alert('提示','请先保存数据!','info');
			 return;
		}	
		var submitOrderDetail=$('#submitDetailDatagridIn').datagrid("getData").rows;
		if(submitOrderDetail!='' && submitOrderDetail.length>0){
			$.messager.confirm("确认框","确认作废吗?",function(b){
				if(b){
					var marsk ="disannul";
					YclOrderManager.cancelOrderInOrOut(submitOrder,submitOrderDetail,soUuid, function(data){
						if(data.result){
							reloadDate(data.object.submitOrderUuid); //回显数据的函数				
							$.messager.alert("提示","作废成功","info");
						}else{
							$.messager.alert("提示",data.error,"info");
						}
					});
				}
			});
		}else{
			$.messager.alert('提示','请选择货物明细信息!','info');
		}
	  }
	}
	
	//删除单头和明细
	function removeButton(){
		var soUuid=checkIsExist();
		if(soUuid!=null){
			var status=getStatus();
			if(status=='<%=YclCommonUtil.Pending%>'){
				$.messager.confirm("提示","确认删除吗?",function(b){
					if(b){
						YclOrderManager.delSubmitOrder(soUuid.toString(),'<%=officeCode%>',function(data){
							if(data.result){
								reset();							
								$.messager.alert("提示","删除成功","info");
							}else{
								$.messager.alert("提示",data.error,"info");
							}
						});
					}
				});
			}
		}
	}
	
	function checkIsExist(){
		var submitOrderUuids=$('#submitOrderForm').form("getData").submitOrderUuid;
		if(submitOrderUuids==undefined||submitOrderUuids==''||submitOrderUuids.length==0){
			$.messager.alert('提示','请保存后再执行该操作!','info');
			return null;
		}
		return submitOrderUuids;
	}

	//验证控制
	function validateMustInput(){
		var validateable=$('#submitOrderForm').form('validate');
		if(validateable==false){
			$.messager.alert('提示','*必填数据为空!请审核后重新操作!','info');
			return false;
		}
		var addRows=$('#submitDetailDatagridIn').datagrid("getRows");
		if(addRows.length==0){
			$.messager.alert('提示','请选择货物明细信息!','info');
			return false;
		}else{
			var b=$("#submitDetailDatagridIn").datagrid("validate");
			if (!b) {
				$.messager.alert("提示", "必填数据为空!请审核后重新操作!", "warning");
				return false;
			}						
		}
		return true;
	}

	//删除明细信息
	function deleteSelectedRows(){
		var selItems=$("#submitDetailDatagridIn").datagrid("getSelections");
		if(selItems.length>0){
			$.messager.confirm('提示',"确认删除吗?",function(b){
				if(b){
					var submitOrderDetailUuids=[];
					for(var i=0;i<selItems.length;i++){
						var submitOrderDetailUuid=selItems[i].submitOrderDetailUuid;
						if(submitOrderDetailUuid){
							submitOrderDetailUuids.push(submitOrderDetailUuid);
						}
					}
					if(submitOrderDetailUuids.length>0){					
						var model=$('#submitOrderForm').form('getData');	
						var marks ="inLod";
						YclOrderManager.delSubmitOrderDetail(model.submitOrderUuid,submitOrderDetailUuids.toString(),function(data){
							if(data.result){
								reloadDate(model.submitOrderUuid); //回显数据的函数
								$.messager.alert('提示','删除成功','info');
							}else{
								$.messager.alert('提示',data.error,'info');
							}
						});
					}else{
						$("#submitDetailDatagridIn").datagrid("deleteSelectedRows");
						$.messager.alert('提示','删除成功','ok');
					}
				}
			});
		}else{
			$.messager.alert('提示','请选择商品明细信息!','info');
		}
	}
	
	//设置系统默认时间
	function setTime(){
		WmsCommonManager.getDataBaseDateFor_YMD_HMS(function(result){
			$("#submitDate").datebox("setValue",result);
		});
	}
	
	//货物明细新增行
	function addDetailRow(){
		var addRows=$('#submitDetailDatagridIn').datagrid("getRows");
		if(addRows.length>0){
			if (! $("#submitDetailDatagridIn").datagrid("validate")) {
					$.messager.alert("提示", "必填数据为空!请审核后重新操作!", "warning");
					return false;
			}
		}
		$("#submitDetailDatagridIn").datagrid("insertRow",{
			index:$("#submitDetailDatagridIn").datagrid("getSelectedIndex"),
			row:{}
		});
	}
	//选择库存货物信息
	function selectoutRhCanNumQuery(){
		var array=[]; 
		  array.push('<%=YclCommonUtil.WMS_GOODS_NATURE_DJ%>'); 
		  array.push('<%=YclCommonUtil.ITEM_NATURE_DETECT%>'); 
	
		$("#outRhCanNum").css("display","block");
		if($('#canOutRsframe').length<=0){
			var url = '<%=path%>/jsp/outbound/selectCanOutNumRH_Query.jsp?&goodsNature='+array;			
			$("#outRhCanNum").append('<iframe id="canOutRsframe" scrolling="no" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>');
		}
		$('#outRhCanNum').dialog({
			modal:true
		});
	}
	//回传 选中的数据
	function setCanOutNumGird(rowData){
	 //  _defineSel(rowData);
		$("#submitDetailDatagridIn").datagrid("acceptChanges");
		  _defineSel(rowData);	   
	}
    function _defineSel(selItems){
		var appRow=new Array();
		if(selItems!=null&&selItems.length>0){
			//var oldItems=parent.$('#'+_key+'').datagrid("getData").rows;
			var oldItems = $('#submitDetailDatagridIn').datagrid('getRows');
			var b=true;
			$.each(oldItems,function(i,row){
				$.each(selItems,function(index,selItem){
					if(row.remainHoldUuid==selItem.remainHoldUuid){
						$.messager.alert("提示","不能重复选择相同的数据!","info");
						b=false;
						return false;
					}
				});
				if(!b){
					return false;
				}else{
					appRow.push(row);
				}
			});
			if(b){
				$.each(selItems,function(index,row){
				    row.qty="";  
					appRow.push(row);
				});
				$('#submitDetailDatagridIn').datagrid('loadData',{rows:appRow});
				$('#outRhCanNum').dialog('close');
			}
		}else{
			$.messager.alert("提示","至少选择一行!","info");
		}
	}
  	//审核验证
	function submitCheckButton(){
		var uuids=getOrderUuids('Pending');
		if(uuids==null||uuids==""){
			return;
		}
		HUBOrderManager.submitCheck(uuids.toString(),function(returnValue){
			if(returnValue.result){
				$.messager.alert("提示",returnValue.msg,"info");
			}else{
				//alert(returnValue.object[0].errorMsg);
				//$("#errorDialog").dialog("open");
				$('#errorDialog').dialog({
					 modal:true
				});
				$("#errorDatagrid").datagrid("loadData",{rows:returnValue.object});
				
			}
		});
	}
	function getOrderUuids(status){
		var order=$("#submitOrderForm").form("getData");
		var uuids=new Array();
		if(order){
			var uuid=order.submitOrderUuid;
			if(uuid){
				if(order.transactionStatus!=status){				
					$.messager.alert("提示","订单:"+order.submitOrderNo+"状态不符合操作要求!","info");
					return null;
				}else{
					uuids.push(uuid);
				}
			}else{
				$.messager.alert("提示","数据未保存,请保存后在操作!","info");
				return null;
			}
		}
		return uuids;
	}

	function dataformatter(value,rowData,rowIndex){
		value=rowData.productionDate;
		if(value){
		    var str = value.substr(0, 10);
		    return str;
		}else{
		   return value;
		}
	}

	function importButton(){
		var importFile=$("#importExe").sinotrans_Import({
			path:'<%=path%>',
			fileType:'.xls,.xlsx',
			businessType:'<%=YclCommonUtil.INBOUND_APPLICATION_IMPORT %>',
			functionName:'callback'
		});
		importFile.open();   	
	}

	function callback(data){
		//reset();
		if(data.result){
			if(data.error=="ERROR"){				
				__submitOrderLogListChuShiHua(data.object)
			}else{
				$.messager.alert("提示",data.msg,"info");	
			}
			reloadDate(data.object.submitOrderModel.submitOrderUuid); //回显数据的函数
			//controlBtns(false);
		}else{
			$.messager.alert('提示',data.error,'info');
		}
	}

	//提供导入和导入验证使用的弹出框错误信息列表集合
	function __submitOrderLogListChuShiHua(error){
		//alert(error.orderNo);
		//列表表格
		$("#submitOrderLogList").datagrid({
			height:($(document).height()*0.68),
			collapsible:true,
			rownumbers:true,
			striped:true,
			nowrap:false,
			fitColumns:true,
			columns:[[
				{field:"orderNo",title:"工单",fit:true,width:150,align:"center",td_align:"center"},
				{field:"errorMsg",title:"错误信息",fit:true,width:600,align:"center",td_align:"left"}//,editor:"text"
			]]
		});
		$('#searchOrderDetail').dialog({
			 modal:true
		});
		$("#submitOrderLogList").datagrid("loadData",{rows:error});
	}
	function saveReceiptSo(){
		var jmReceiptType = $("#jmReceiptType").combobox("getValue");
		if(jmReceiptType == '1'){
			toSubmitOrder();
		}else{
			//买卖单
			auditOrderBuy();
		}
		$("#receiptDialog").dialog("close");
	}
	//买卖单
	function auditOrderBuy(){
		if(validateMustInput()){
			var submitOrder=$('#submitOrderForm').form('getData');
			if(submitOrder.submitOrderUuid==undefined){
				 $.messager.alert('提示','请先保存数据!','info');
				 return;
			}		
			var submitOrderDetail=$('#submitDetailDatagridIn').datagrid("getData").rows;
			$('#submitDetailDatagridIn').datagrid("endEdit");
			if(submitOrderDetail!='' && submitOrderDetail.length>0){
				$("#submitDetailDatagridIn").datagrid('acceptChanges');
				$.messager.confirm("确认框","确认保存并提交吗?",function(b){
					if(b){
						submitOrder.deliveryType = $("#deliveryType").combobox("getText"); //设值
						submitOrder.workDemand = $("#workDemand").combobox("getText"); //设值
						submitOrder.flow = $("#flow").combobox("getText");
						JmInboundManager.auditOrderBuy(submitOrder,submitOrderDetail,submitOrder.submitOrderUuid.toString(),"<%=officeCode%>",function(data){
							if(data.result){
								reloadDate(data.object.submitOrderUuid); //回显数据的函数						
								$.messager.alert("提示",data.msg,"info");
							}else{
								$.messager.alert("提示",data.error,"info");
							}
						});
					}
				});
			}else{
				$.messager.alert('提示','货物明细信息为空!','info');
			}
	  	}
		
	}

	function cancelSelectedRows(){
		var selItems=$("#submitDetailDatagridIn").datagrid("getSelections");
		var soUuid=$('#submitOrderForm').form('getData').submitOrderUuid;
		if(selItems.length>0){
			$.messager.confirm('提示',"确认作废吗?",function(b){
				if(b){
					YclOrderManager.cancelSubmitOrderDetail(soUuid,selItems[0].submitOrderDetailUuid,function(spj){
						if(spj.result){
							$.messager.alert('提示',spj.msg,'info');
							$("#submitDetailDatagridIn").datagrid("reload");	
						}else{
							$.messager.alert('提示',spj.error,'info');
						}
					});
				}	
			});
		}else{
			$.messager.alert('提示','请选择物料明细信息!','info');
		}
	}
</script>
<style type="text/css">
	.tdText{
		text-align: right;
	}
</style>
</head>

<body class="easyui-layout">
	<div region="north" title="<bean:message bundle="ycl.yclInboundOrder" key="OutOrderHeadYcl.title" />" iconCls="icon-edit" >
		<div class="datagrid-toolbar" >
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow();" id="addBtn" >
				<bean:message bundle="wms.common" key="global.add" /></a>
			<a class="easyui-linkbutton" plain="true" iconCls="icon-save" onclick="saveHeadAndDetail();" id="saveBtn">
				<bean:message bundle="wms.common" key="global.save" /></a>
			<a class="easyui-linkbutton" id="submitBtn" plain="true" iconCls="icon-ok" onclick="submitBtn();">		
				<bean:message bundle="hub.hubInbound" key="hubglobal.submit" /></a>
			<!--<a class="easyui-linkbutton" id="submitCheck" plain="true" iconCls="icon-ok" onclick="submitCheckButton();">
			提交验证</a>
			-->	
			<a class="easyui-linkbutton" id="cancelBtn" plain="true" iconCls="icon-cancel" onclick="cancelSubmitBtn();">
				<bean:message bundle="hub.hubInbound" key="hubglobal.cancelSubmit" /></a>	
			<a class="easyui-linkbutton" id="delBtn" plain="true" iconCls="myCustomerIcon_remove" onclick="removeButton();">
				<bean:message bundle="wms.common" key="global.del" /></a>
			<a class="easyui-linkbutton" plain="true" iconCls="myCustomerIcon_cancel" onclick="disannul();" id="disannul">
				<bean:message bundle="wms.common" key="global.invalidate" /></a>
			<a  href="javascript:importButton();" id="importExe" class="easyui-linkbutton" plain="true" iconCls="excel_import" >
					导入 </a>
		</div>
		<form id="submitOrderForm" class="easyui-form" i18nRoot="OutOrderHeadYcl" columns="4"   >
			<input name="transactionType" id="transactionType" type="hidden" value="<%=YclCommonUtil.TRANSACTIONTYPE_SOT %>"/>
			<input name="submitOrderNo" class="easyui-validatebox" readonly="readonly" id="submitOrderNo" />
			<input name="orderNo" class="easyui-validatebox" id="orderNo" title="订单号" />
	    	<input name="deliveryType" id="deliveryType" class="easyui-combobox" required="true" editable='false' title="任务类型" />
	        <input name="configCode" id="config_code" class="easyui-combobox" required="true" editable='false' codetype="<%=YclCommonUtil.OUT_CONFIG%>"/>
	        <input name="cargoConsigneeCode" id="cargoConsigneeCode" required="true" combo="true" class="easyui-combogrid" title="供应商"  codetype="<%=YclCommonUtil.ALL_SUPPLIERS %>"/>
	        <input name="cargoConsigneeDesc" id="cargoConsigneeDesc" type="hidden"/>
	        <input name="agentConsigneeCode" id="agentConsigneeCode" required="true" combo="true" class="easyui-combogrid" codetype="<%=YclCommonUtil.ALL_CUSTOMER %>" title="客户"/>
			<input name="agentConsigneeDesc" id="agentConsigneeDesc" type="hidden"/>
			<input id="workDemand" name="workDemand"  class="easyui-combobox"  editable='false'  title="办单类型" />  
			
	        <input name="submitDate" class="easyui-datetimebox" id="submitDate" required="true" />
	        <input name="flow" id="flow" class="easyui-combobox"  editable="false" title="作业类型" />
	        <!-- 
	        <input name="contractNo" id="contractNo" class="easyui-validatebox" title="生产订单号"  editable="false" />
			<input name="aux1" id="aux1" class="easyui-validatebox"  editable="false" />
			<input name="aux2" id="aux2" class="easyui-validatebox"  editable="false" />
			<input name="aux3" id="aux3"  class="easyui-combobox" required="true" />
			<input name="projectCode" id="projectCode" class="easyui-validatebox"  editable="false"/>		
		    <input name="chargeDesc" id="chargerDesc" class="easyui-validatebox" editable="false"/>
		    -->
			<input name="transactionStatus" class="easyui-combobox"  id="transactionStatus"  disabled="true" codetype="<%=YclCommonUtil.SYS_BASE_INFO_STATUS%>"/>
			<input name="remark" id="remark" class="easyui-validatebox"  editable="false"/>
		</form>
	</div>
	<div region="center" iconCls="icon-edit" border="false">
		<div class="easyui-tabs" fit="true" >
			<div title="<bean:message bundle="hub.hubInbound" key="LogisticsOrderDetailHub.title" />" >
				<div class="easyui-layout" fit="true" border="false">
					<div region="north" border="false">
						<div class="datagrid-toolbar">
							<a class="easyui-linkbutton" id="detailAddBtn" plain="true"  iconCls="icon-add" onclick="selectoutRhCanNumQuery();"><bean:message bundle="wms.common" key="global.add" /></a>
							<a class="easyui-linkbutton" id="detailDelBtn" plain="true"  iconCls="myCustomerIcon_remove" onclick="deleteSelectedRows();"><bean:message bundle="wms.common" key="global.del" /></a>
							<a class="easyui-linkbutton" id="cancelDelBtn" plain="true"  iconCls="myCustomerIcon_cancel" onclick="cancelSelectedRows();"><bean:message bundle="wms.common" key="global.invalidate" /></a>
						</div>
					</div>
					<div region="center" border="false">
						<table id="submitDetailDatagridIn" class="easyui-datagrid" fit="true" i18nRoot="OutOrderDetailYcl">
							<thead>
								<tr>
	                                <th field="itemCode" ></th>
	                                <th field="goodsDesc"  width="150px;"></th>	
	                                <th field="qty" editor="{type:'numberbox',options:{ required:true,precision:4,min:0.0000001,max:999999999.99,validType:'numberRange[0.0000001,999999999.99]'}}" align="right"></th>		
									<!--
									<th field="aux3" editor="validatebox" title="投料工位"></th>
									<th field="aux1" editor="validatebox"></th>	
									 <th field="billNo" editor="validatebox"></th>	
									<th field="marksNumber" editor="validatebox"></th>	
									<th field="aux2" editor="validatebox"></th>
									<th field="controlWord" hidden="true"></th>	
									<th field="qtyUnitDesc" ></th>		
									<th field="qtyUnitCode" hidden="true"></th>	
									 -->
									<th field="spec" editor="validatebox"></th>	
								    <th field="batchNo" editor="validatebox"></th>	 
									<th field="productionDate" formatter="dataformatter" title="物料生产日期" ></th>
	                                <th field="createTime" title="投料时间"></th>	                              
									<th field="extItemCode" title="供应商物料编码"></th>
									<th field="remark"  ></th>																
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	  <!-- 库存明细 -->
	 <div id="outRhCanNum" style="width: 800px; height: 500px; display: none;" title="库存货物"></div>
	 <div class="easyui-dialog" id="errorDialog" title="信息列表" style="width: 500px; height: 520px;">
		<table class="easyui-datagrid"  fit="true" id="errorDatagrid" pagination="false" i18nRoot="SubmitOrderBody">
			<thead>
				<tr>
					<th field="errorMsg"></th>
				</tr>
			</thead>
		</table>
	</div>
	
	<!-- 结束 货位填写弹出框 -->
		<!-- 弹出框——提供导入使用(如果导入失败。失败信息回显在这里。) -->
	<div id="searchOrderDetail" style="width: 750px; height:445px;" title="导入失败信息">
		<table id="submitOrderLogList" style="width: auto;font-size:12px;" border="0" fit="true"></table>
	</div>
	
	<!--经贸生成出库单或者买卖单弹出框-->
	<div id="receiptDialog" class="easyui-dialog" title="单据类型" style="width: 280px;height:140px;" modal="true"  resizable="false" 
		maximizable="false" collapsible="false" minimizable="false" closed="true">
		<div class="easyui-layout" fit="true">
			<div region="north" border="false" style="padding:0px;" >
				<div class="datagrid-toolbar">
					<a class="easyui-linkbutton" id="saveEntry" plain="true" onclick="saveReceiptSo()">确定</a>
				</div>
			</div>				
			<div region="center" border="true" >
				<form id="receiptForm" class="easyui-form" columns="2" >
					<input name="jmReceiptType" id=jmReceiptType title="请选择" class="easyui-combobox" />				
				</form>		
			</div>				
		</div>
	</div>
</body>
</html>