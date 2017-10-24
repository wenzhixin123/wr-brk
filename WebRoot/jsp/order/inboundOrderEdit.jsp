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
<script >
	
	 var submitOrderUuid ='<%=submitOrderUuid%>';
	$(function(){
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
	
  	 
	
	//回显数据
	function reloadDate(submitOrderUuid){
		//dwr.engine.setAsync(false);
		SubmitOrderManager.findSubmitOrderByUuid(submitOrderUuid,function(returnValue){
			if(returnValue.result){
				$("#submitOrderForm").form("setData",returnValue.object);
				setSourceDataGrid("#submitDetailDatagridIn",returnValue.object.submitOrderUuid);
				if(returnValue.object.transactionStatus=='<%=CommonUtil.Cancel%>'){
					disableForm(true);
				}else if(returnValue.object.transactionStatus=='<%=CommonUtil.Pending%>'){
					disableForm(false);
				}else if(returnValue.object.transactionStatus=='<%=CommonUtil.Active%>'){
					disableForm(true);
				}
				$("#deliveryType").combobox("setText",returnValue.object.deliveryType);
			}else{
				$.messager.alert('提示',returnValue.error,'info');
			}
		});
		//dwr.engine.setAsync(true);
	}
	
   //form控件控制
	function disableForm(b){
		$('#submitOrderForm').form("readonly",b);
		if(!b){
			$("#deliveryType").combobox({
				"editable":false
			});
		}
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
			queryType:"SubmitOrderDetailModel"
		});
	}
	
	//新增
	function appendRow(){
		$.messager.confirm('提示','确认新增将清空界面所有数据!',function(b){
			if(b){
				reset();
			}
		});
		
	}
	function reset(){
		setDefDate();
		$("#submitDetailDatagridIn").datagrid("loadData",{total:0,rows:[]});
		disableForm(false);
	}
	
	//设置默认数据
	function setDefDate(){
		WmsCommonManager.getDefaultCustomer(function(result){
			$('#submitOrderForm').form("setData",{
				agentConsigneeCode:result.customerCode,
				agentConsigneeDesc:result.customerName,
				transactionType:'<%=CommonUtil.TRANSACTIONTYPE_SIN %>',
				transactionStatus:'<%=CommonUtil.Pending %>',
				creator:'<%=scue.getUserId()%>'
			});
			setTime();
			$("#controlWord").val("<%=CommonUtil.Default_Control_Word %>");
			WmsCommonManager.getDefaultDeliveyType('<%=CommonUtil.OPTION_IN_WORK_TYPE%>',"<%=officeCode%>",function(returnValue){
				$("#deliveryType").combobox("setValue",returnValue);	
			});
		});
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
	
	//删除明细信息
	function deleteSelectedRows() {
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
						SubmitOrderManager.delSubmitOrderDetail(model.submitOrderUuid,submitOrderDetailUuids.toString(),function(data){
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
	
	//保存单头和明细
	function saveHeadAndDetail(){
		var vali=true;
		var curr_time = new Date();
		var strDate = curr_time.getYear()+1900+"-";
		strDate += curr_time.getMonth()+1+"-";
		strDate += curr_time.getDate();
		if(validateMustInput()){
			var submitOrder=$('#submitOrderForm').form('getData');		
			var submitOrderDetail=$('#submitDetailDatagridIn').datagrid("getData").rows;
			$("#submitDetailDatagridIn").datagrid('endEdit');
			if(submitOrderDetail!='' && submitOrderDetail.length>0){
				$("#submitDetailDatagridIn").datagrid('acceptChanges');
				$.messager.confirm("提示","确认保存吗?",function(b){
					if(b){				
						submitOrder.deliveryType = $("#deliveryType").combobox("getText"); //设值						
						$.each(submitOrderDetail,function(index,addRow){	
							if(addRow.productionDate){
								d1Arr=addRow.productionDate.split('-');
								d2Arr=strDate.split('-');
								v1=new Date(d1Arr[0],d1Arr[1],d1Arr[2]);
								v2=new Date(d2Arr[0],d2Arr[1],d2Arr[2]);                 											
									if(v1>v2){									
										vali=false;
									
									}
								}
						});	
						if(!vali){
							$.messager.alert('提示','明细生产日期 不能大于系统时间!','info');
							return ;
						}				
						SubmitOrderManager.saveSubmitOrder(submitOrder,submitOrderDetail,'<%=officeCode%>',function(spj){
							if(spj.result){
								reloadDate(spj.object.submitOrderUuid); //回显数据的函数
								$.messager.alert("提示",'操作成功',"info");
							}else{
								$.messager.alert("提示",spj.error,"info");
							}
						});
					}
				});
			}else{
				$.messager.alert('提示','请选择货物明细信息!','info');
			}
		}
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
	
	//提交单头和明细
	function submitBtn(){
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
				$.messager.confirm("提示","您是否确认提交?",function(b){
					if(b){
						submitOrder.deliveryType = $("#deliveryType").combobox("getText"); //设值
						SubmitOrderManager.auditOrder(submitOrder,submitOrderDetail,submitOrder.submitOrderUuid.toString(),'<%=officeCode%>',function(data){
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
		var soModels=new Array();
		soModels.push(submitOrder);
		var submitOrderDetail=$('#submitDetailDatagridIn').datagrid("getData").rows;
		if(submitOrderDetail!='' && submitOrderDetail.length>0){
			$.messager.confirm("提示","您是否确认取消提交?",function(b){
				if(b){
					SubmitOrderManager.cancelAuditOrder(soModels,submitOrderDetail,function(data){
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
			$.messager.alert('提示','货物明细为空!','info');
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
			$.messager.confirm("提示","确认作废吗?",function(b){
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
</script>
<style type="text/css">
	.tdText{
		text-align: right;
	}
</style>
</head>

<body class="easyui-layout">
	<div region="north"  title="单头信息" >
		<div class="datagrid-toolbar" >
			<a class="easyui-linkbutton" plain="true" title="新增" iconCls="icon-add" onclick="appendRow();">新增 </a>
			<a class="easyui-linkbutton" plain="true" title="保存" iconCls="icon-save" onclick="saveHeadAndDetail();">保存</a>
			<a class="easyui-linkbutton" id="submitBtn" plain="true" iconCls="icon-ok" onclick="submitBtn();">提交</a>
			<a class="easyui-linkbutton" id="cancelBtn" plain="true" iconCls="icon-cancel" onclick="cancelSubmitBtn();">取消提交</a>
			<a class="easyui-linkbutton" id="delBtn" plain="true" iconCls="myCustomerIcon_remove" onclick="removeButton();">删除</a>
			<a class="easyui-linkbutton" plain="true" iconCls="myCustomerIcon_cancel" onclick="disannul();" id="disannul">作废</a>
		</div>
		<form id="submitOrderForm" class="easyui-form" i18nRoot="InOrderNoHeadYcl" columns="4"   >
			 <input name="controlWord" id="controlWord"   type="hidden" /> 
			 <input name="transactionType" id="transactionType" type="hidden" value="<%=CommonUtil.TRANSACTIONTYPE_SIN %>"/>
			 <input name="submitOrderNo" title="入库订单号" id="submitOrderNo" class="easyui-validatebox" readonly="readonly" placeholder="系统自动生成" /><!-- onmouseover="this.title='系统自动生成'" -->
			 <input name="orderNo" id="orderNo" class="easyui-validatebox"  title="订单号"/> 
			 <input name="agentConsigneeCode" title="委托单位" id="agentConsigneeCode" required="true" class="easyui-combogrid" codetype="ALL_CUSTOMER" editable="false"/>
			 <input name="agentConsigneeDesc" id="agentConsigneeDesc" type="hidden"/>	
		     <input name="submitDate" id="submitDate" title="办单日期" class="easyui-datetimebox" required="true" editable="false"/>
			 <input name="cargoConsigneeCode" id="cargoConsigneeCode" title="供应商" required="true" class="easyui-combogrid" codetype="ALL_CUSTOMER" editable="false"/>
			 <input name="cargoConsigneeDesc" id="cargoConsigneeDesc" type="hidden"/>
		     <input name="deliveryType" title="业务类型" id="deliveryType" class="easyui-combobox" codetype="IN_WORK_TYPE"  editable="false"/>	
			 <input name="deliveryDate" id="deliveryDate" class="easyui-datetimebox" editable="false"  title="到货日期" />		      
 			 <input name="telNo" id="telNo" class="easyui-validatebox"  title="电话"/>  
			 <input name="transactionStatus" id="transactionStatus" title="状态" class="easyui-combobox" disabled="true" codetype="STATUS"  panelHeight="auto" />
		</form>
	</div>
	
	<div region="center" iconCls="icon-edit" border="false"  title="订单明细">
				<div class="easyui-layout" fit="true" border="false">
					<div region="north" border="false">
						<div class="datagrid-toolbar">
							<a class="easyui-linkbutton" id="detailAddBtn" plain="true"  iconCls="icon-add" onclick="addDetailRow();">增加</a>
							<a class="easyui-linkbutton" id="detailDelBtn" plain="true"  iconCls="myCustomerIcon_remove" onclick="deleteSelectedRows();">删除</a>
						</div>
					</div>
					<div region="center" border="false">
						<table id="submitDetailDatagridIn" class="easyui-datagrid" fit="true" i18nRoot="InOrderDetailYCL">
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
</body>
</html>