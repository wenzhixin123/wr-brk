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
String submitOrderUuid = request.getParameter("submitOrderUuid");
 %>

<script type="text/javascript">
var submitOrderUuid ='<%=submitOrderUuid%>';

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
			if(rowData.transactionStatus && rowData.transactionStatus!='<%=CommonUtil.Pending%>'){
			 	return false;
			}
			$("#submitDetailDatagridIn").datagrid("getColumnEditor", "goodsDesc").attr("readonly", "readonly");
			$("#submitDetailDatagridIn").datagrid("getColumnEditor", "itemCode").combogrid("options").onSelect = function(codeIndex,codeData) {
				var $combogrid = $(this);
				$("#submitDetailDatagridIn").datagrid("getColumnEditor", "goodsDesc").val(codeData.itemName); //把值给goodDesc
				$("#submitDetailDatagridIn").datagrid("getColumnEditor", "extItemCode").val(codeData.extItemCode); //把值给goodDesc
			};
			$("#submitDetailDatagridIn").datagrid("getColumnEditor", "qtyUnitCode").combo("options").onHidePanel = function() {
					var $combogrid = $(this);
					$("#submitDetailDatagridIn").datagrid("getSelected").qtyUnitDesc=$combogrid.combogrid("getText");
			};
		}
	

	});
  function initCombox(){	
		//setCombobox("<%=CommonUtil.OPTION_OUT_WORK_TYPE%>",'deliveryType');
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
	  WmsCommonManager.systemCodeCodeDef(typeCode,"<%=CommonUtil.Active%>","<%=scue.getOfficeCode()%>",function(spj){
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
		SubmitOrderManager.findSubmitOrderByUuid(submitOrderUuid,function(returnValue){
			if(returnValue.result){				
			
				$("#submitOrderForm").form("setData",returnValue.object);	
							
				setSourceDataGrid("#submitDetailDatagridIn",returnValue.object.submitOrderUuid);
				if(returnValue.object.transactionStatus=='<%=CommonUtil.Cancel%>'){
					disableForm(true);
				}else if(returnValue.object.transactionStatus=='<%=CommonUtil.Active%>'){
					disableForm(true);
				}else if(returnValue.object.transactionStatus=='<%=CommonUtil.Pending%>'){
				    disableForm(false);
				}
				$("#deliveryType").combobox('setValue',returnValue.object.deliveryType);	
				$("#transactionStatus").combobox('setValue',returnValue.object.transactionStatus);
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
			$("#deliveryType,#config_code").combobox({
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
		$("#transactionType").val('<%=CommonUtil.TRANSACTIONTYPE_SOT%>');	
		WmsCommonManager.getDefaultPlanConfig("<%=CommonUtil.OUTV%>","<%=scue.getOfficeCode()%>",function(data){
			disableForm(false);
			$('#submitOrderForm').form("setData",{
				transactionStatus:'<%=CommonUtil.Pending%>'
			});
          $("#config_code").combobox({
				onLoadSuccess:function(none){
						if(none.length>0){
							$("#config_code").combobox('setValue',data.configCode);
						}
					}
				});			
			setTime();
			WmsCommonManager.getDefaultDeliveyType('<%=CommonUtil.OPTION_OUT_WORK_TYPE%>',"<%=scue.getOfficeCode()%>",function(returnValue){
				$("#deliveryType").combobox("setValue",returnValue);	
			});
			
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
						SubmitOrderOutManager.saveSubmitOrderSot(submitOrder,submitOrderDetail,'<%=officeCode%>',function(spj){
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
						SubmitOrderOutManager.auditOrderSot(submitOrder,submitOrderDetail,submitOrder.submitOrderUuid.toString(),"<%=officeCode%>",function(data){
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
					SubmitOrderOutManager.cancelAuditOrderSot(submitOrder,submitOrderDetail,function(data){
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
					SubmitOrderManager.cancelOrderInOrOut(submitOrder,submitOrderDetail,soUuid, function(data){
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
			if(status=='<%=CommonUtil.Pending%>'){
				$.messager.confirm("提示","确认删除吗?",function(b){
					if(b){
						SubmitOrderManager.delSubmitOrder(soUuid.toString(),'<%=officeCode%>',function(data){
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
		  array.push('<%=CommonUtil.ITEM_NATURE_QUALIFIED%>'); 
		  array.push('<%=CommonUtil.ITEM_NATURE_UNQUALIFIED%>'); 
	
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
	<div region="north" title="出库订单信息" iconCls="icon-edit" >
		<div class="datagrid-toolbar" >
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow();" id="addBtn" >新增</a>
			<a class="easyui-linkbutton" plain="true" iconCls="icon-save" onclick="saveHeadAndDetail();" id="saveBtn">保存</a>
			<a class="easyui-linkbutton" id="submitBtn" plain="true" iconCls="icon-ok" onclick="submitBtn();">提交</a>
			<a class="easyui-linkbutton" id="cancelBtn" plain="true" iconCls="icon-cancel" onclick="cancelSubmitBtn();">取消提交</a>	
			<a class="easyui-linkbutton" id="delBtn" plain="true" iconCls="myCustomerIcon_remove" onclick="removeButton();">删除</a>
			<a class="easyui-linkbutton" plain="true" iconCls="myCustomerIcon_cancel" onclick="disannul();" id="disannul">作废</a>
		</div>
		<form id="submitOrderForm" class="easyui-form" i18nRoot="OutOrderHeadYcl" columns="4"   >
			<input name="transactionType" id="transactionType" type="hidden" value="<%=CommonUtil.TRANSACTIONTYPE_SOT %>"/>
			<input name="submitOrderNo" class="easyui-validatebox" readonly="readonly" id="submitOrderNo" title="出库订单号" placeholder="系统自动生成"  />
			<input name="orderNo" class="easyui-validatebox" id="orderNo" title="订单号" />
	    	<input name="deliveryType" title="业务类型" id="deliveryType" class="easyui-combobox" codetype="OUT_WORK_TYPE"  editable="false"/>	
	        <input name="configCode" id="config_code" class="easyui-combobox" required="true" editable='false' title="出库策略" codetype="<%=CommonUtil.OUT_CONFIG%>"/>
	        <input name="cargoConsigneeCode" id="cargoConsigneeCode" required="true" combo="true" class="easyui-combogrid" title="供应商"  codetype="ALL_SUPPLIERS"/>
	        <input name="cargoConsigneeDesc" id="cargoConsigneeDesc" type="hidden"/>
	        <input name="agentConsigneeCode" id="agentConsigneeCode" required="true" combo="true" class="easyui-combogrid" codetype="ALL_CUSTOMER" title="客户"/>
			<input name="agentConsigneeDesc" id="agentConsigneeDesc" type="hidden"/>
	        <input name="submitDate" class="easyui-datetimebox" id="submitDate" required="true" title="办单时间" />
			<input name="transactionStatus" class="easyui-combobox"  id="transactionStatus"  disabled="true" codetype="STATUS" title="状态" />
			<input name="remark" id="remark" class="easyui-validatebox"  editable="false" title="备注"/>
		</form>
	</div>
	<div region="center" iconCls="icon-edit" border="false">
		<div class="easyui-tabs" fit="true" >
			<div title="货物明细" >
				<div class="easyui-layout" fit="true" border="false">
					<div region="north" border="false">
						<div class="datagrid-toolbar">
							<a class="easyui-linkbutton" id="detailAddBtn" plain="true"  iconCls="icon-add" onclick="selectoutRhCanNumQuery();">根据库存新增</a>
							<a class="easyui-linkbutton" id="detailAddBtn1" plain="true"  iconCls="icon-add" onclick="addDetailRow();">新增</a>
							<a class="easyui-linkbutton" id="detailDelBtn" plain="true"  iconCls="myCustomerIcon_remove" onclick="deleteSelectedRows();">删除</a>
							<a class="easyui-linkbutton" id="cancelDelBtn" plain="true"  iconCls="myCustomerIcon_cancel" onclick="cancelSelectedRows();">作废</a>
						</div>
					</div>
					<div region="center" border="false">
						<table id="submitDetailDatagridIn" class="easyui-datagrid" fit="true" i18nRoot="OutOrderDetailYcl">
							<thead>
								<tr>   
									<th field="itemCode" title="物料代码" editor="{type:'combogrid',options:{ required:true}}" codetype="CODE_DESC"    editable="false"></th>
									<th field="goodsDesc"  title="物料名称" editor="{type:'validatebox'}" width="250px;"></th>
								    <th field="qty" title="数量" editor="{type:'numberbox',options:{ required:true,precision:4,min:0.0000001,max:999999999.99,validType:'numberRange[0.0000001,999999999.99]'}}" align="right"></th>
									<th field="qtyUnitCode" title="单位" editor="{type:'combogrid',options:{ required:true}}"  codetype="ALL_UNIT"  editable="false"></th>
								    <th field="batchNo" title="批次号" editor="validatebox" ></th>
									<th field="extItemCode" title="其他物料编码" editor="validatebox" ></th>		
									<th field="spec" title="规格" editor="validatebox" ></th>	
									<th field="model" title="型号" editor="validatebox" ></th>
									<th field="remark" title="备注"  editor="validatebox"  ></th>																		
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
</body>
</html>