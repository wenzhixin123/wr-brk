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
	String submitOrderUuid = request.getParameter("submitOrderUuid");
	String officeCode = SessionContextUserEntity.currentUser().getOfficeCode();
%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<script type="text/javascript" src="<%=contextPath%>/js/util/myUtil.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/export2Excel.js"></script>
	<script type="text/javascript" src="<%=contextPath%>/js/bc/sinotrans_buttonAuthorize.js"></script>
<script type="text/javascript">
    var submitOrderUuid ='<%=submitOrderUuid%>';
    //Pending为openBtnId,Active为disBtnId
	var btnIdsOne=['saveBtn','delBtn','submitBtn','detailDelBtn','detailAddBtn'];
	//Pending为disBtnId,Active为openBtnId
	var btnIdsTwo=['cancelBtn','disannul'];
	//所有需要控制的按钮id
	var btnIds=['saveBtn','submitBtn','delBtn','cancelBtn','disannul'];
	$(function(){
		initCombox();
		if(submitOrderUuid!=null&&submitOrderUuid!='null'){
			reloadDate(submitOrderUuid);
		}else{
			setDefDate();
			controlBtns(btnIdsOne,btnIdsTwo);
		}
		$("#submitDetailDatagridIn").datagrid("options").onBeforeEdit = function(rowIndex, rowData) {
			if(rowData.transactionStatus && rowData.transactionStatus!='<%=YclCommonUtil.Pending%>'){
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
		setHiddenVal("#agentConsigneeCode","#agentConsigneeDesc");		
		setHiddenVal("#cargoConsigneeCode","#cargoConsigneeDesc");
		setHiddenVal("#projectCode","#projectName");
	});
	
  	function initCombox(){
		initBox("deliveryType","<%=YclCommonUtil.OPTION_IN_WORK_TYPE%>");
		$("#aux3").combobox({
		  	data:[<%=StringUtil.toJsonArray(YclCommonUtil.BUSINESS_MODEL_MAP)%>],
		  	valueField: "id",  
			panelHeight:"auto",
            textField: "text"
		});
	  }
   	function initBox(comBoxId,type){
		YclInboundManager.systemCodeCodeDef(type,"<%=YclCommonUtil.Active%>","<%=officeCode%>",function(spj){
			if(spj.object){
				$('#'+comBoxId).combobox({ //货物类型
					valueField:"id",
					textField:"text",
					panelHeight:"auto",
					data:spj.object,
					editable:false
				});
			}
		});	
	   
	   }
	function getChangeByCW(setWord,valueId){
		var controlWord = $("#controlWord").val();
		// 急料
		if(setWord == "<%=YclCommonUtil.CONTROL_WORD_U %>"){
			controlWord = controlWord.slice(0,0) + valueId + controlWord.slice(1);
		}
		// 试制品
		else if(setWord == "<%=YclCommonUtil.CONTROL_WORD_T %>"){
			controlWord = controlWord.slice(0,1) + valueId + controlWord.slice(2);
		}
		$("#controlWord").val(controlWord);				
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
		//dwr.engine.setAsync(false);
		HUBOrderManager.findSubmitOrderByUuid(submitOrderUuid,function(returnValue){
			if(returnValue.result){
				$("#submitOrderForm").form("setData",returnValue.object);
				setSourceDataGrid("#submitDetailDatagridIn",returnValue.object.submitOrderUuid);
				if(returnValue.object.transactionStatus=='<%=YclCommonUtil.Cancel%>'){
					controlBtns([],btnIds);
					disableForm(true);
				}else if(returnValue.object.transactionStatus=='<%=YclCommonUtil.Pending%>'){
					controlBtns(btnIdsOne,btnIdsTwo);
					disableForm(false);
				}else if(returnValue.object.transactionStatus=='<%=YclCommonUtil.Active%>'){
					controlBtns(btnIdsTwo,btnIdsOne);
					disableForm(true);
				}
				$("#deliveryType").combobox("setText",returnValue.object.deliveryType);
				var controlWord = returnValue.object.controlWord;
				var urgentWord = controlWord.substring(0,1);
				if(urgentWord == "<%=YclCommonUtil.CONTROL_WORD_U%>"){
					$("#urgent").combobox("setValue","<%=YclCommonUtil.CONTROL_WORD_U%>");
				}else{
					$("#urgent").combobox("setValue","0");
				}
				var prototypeWord = controlWord.substring(1,2);
				if(prototypeWord == "<%=YclCommonUtil.CONTROL_WORD_T%>"){
					$("#prototype").combobox("setValue","<%=YclCommonUtil.CONTROL_WORD_T%>");
				}else{
					$("#prototype").combobox("setValue","0");
				}
			}else{
				$.messager.alert('提示',returnValue.error,'info');
			}
		});
		//dwr.engine.setAsync(true);
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
			queryType:"HUBSodBySoUuidQuery"
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
		controlBtns(btnIdsOne,btnIdsTwo);
		disableForm(false);
	}
	
	//form控件控制
	function disableForm(b){
		$('#submitOrderForm').form("readonly",b);
		if(!b){
			$("#deliveryType,#urgent,#prototype").combobox({
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
		ECCommonManager.getDefaultCustomer(function(result){
			$('#submitOrderForm').form("setData",{
				agentConsigneeCode:result.customerCode,
				agentConsigneeDesc:result.customerName,
				transactionType:'<%=YclCommonUtil.TRANSACTIONTYPE_SIN %>',
				transactionStatus:'<%=YclCommonUtil.Pending %>',
				creator:'<%=scue.getUserId()%>'
			});
			setTime();
			$("#controlWord").val("<%=YclCommonUtil.Default_Control_Word %>");
			WmsCommonManager.getDefaultDeliveyType('<%=YclCommonUtil.OPTION_IN_WORK_TYPE%>',"<%=officeCode%>",function(returnValue){
				$("#deliveryType").combobox("setValue",returnValue);	
			});
		});
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
						YclOrderManager.saveSubmitOrder(submitOrder,submitOrderDetail,'<%=officeCode%>',function(spj){
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
						YclOrderManager.auditOrder(submitOrder,submitOrderDetail,submitOrder.submitOrderUuid.toString(),'<%=officeCode%>',function(data){
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
					YclOrderManager.cancelAuditOrder(soModels,submitOrderDetail,function(data){
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
	
		//入库订单导入
	 function importBtn(){
		var importFile=$('#importBtn').sinotrans_Import({
			path:'<%=contextPath%>',
			fileType:'.xls,.xlsx',
			businessType:'<%=HUB_CommonUtil.SO_SD_IMPORT%>',
			modelIds:'',
			functionName:'callback'
		}); 
		importFile.open();
	}
	
	
	//导入的回调函数
	function callback(data){
		if(data.result){
		    if(data.object){
			    if(data.object.submitOrderUuid){
			    	reloadDate(data.object.submitOrderUuid);
				}else{
				    reloadDate(data.object.submitOrderModel.submitOrderUuid);
				}
				$.messager.alert("提示",'导入成功','info');
		    }else{
		    	$.messager.alert("提示",'返回的单头为空','info');
		    }
		}else{
			$.messager.alert("提示",data.error,'info');
		}
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
	var sodAux1Data=[ <%=StringUtil.toJsonArray(YclCommonUtil.ORG_ID_MAP)%>];
	function setAux1(value){
		for(var i = 0; i <sodAux1Data.length; i++){
			if (sodAux1Data[i].id == value){
				return sodAux1Data[i].text;
			}
		}
		return value;
	}
		
</script>
<style type="text/css">
	.tdText{
		text-align: right;
	}
</style>
</head>

<body class="easyui-layout">
	<div region="north" title="<bean:message bundle="ycl.yclInboundOrder" key="InOrderNoHeadYcl.title" />" iconCls="icon-edit" >
		<div class="datagrid-toolbar" >
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="appendRow();" id="addBtn" >
				<bean:message bundle="wms.common" key="global.add" /></a>
			<a class="easyui-linkbutton" plain="true" iconCls="icon-save" onclick="saveHeadAndDetail();" id="saveBtn">
				<bean:message bundle="wms.common" key="global.save" /></a>
			<a class="easyui-linkbutton" id="submitBtn" plain="true" iconCls="icon-ok" onclick="submitBtn();">
				<bean:message  bundle="hub.hubInbound" key="hubglobal.submit" /></a>
			<a class="easyui-linkbutton" id="cancelBtn" plain="true" iconCls="icon-cancel" onclick="cancelSubmitBtn();">
				<bean:message bundle="hub.hubInbound" key="hubglobal.cancelSubmit" /></a>
			<a class="easyui-linkbutton" id="delBtn" plain="true" iconCls="myCustomerIcon_remove" onclick="removeButton();">
				<bean:message bundle="wms.common" key="global.del" /></a>
			<a class="easyui-linkbutton" plain="true" iconCls="myCustomerIcon_cancel" onclick="disannul();" id="disannul">
				<bean:message bundle="wms.common" key="global.invalidate" /></a>
				<!-- 导入 -->
			<%-- <a class="easyui-linkbutton" iconCls="icon_shengcheng" onclick="importBtn();" id="importBtn" >
				<bean:message bundle="wms.common" key="global.add" />导入</a> --%>
			<a id="importBtn" class="easyui-linkbutton" plain="true" iconCls="icon_shengcheng" 
				 onclick="importBtn()">导入</a>
		</div>
		<form id="submitOrderForm" class="easyui-form" i18nRoot="InOrderNoHeadYcl" columns="4"   >
			 <input name="transactionType" id="transactionType" type="hidden" value="<%=YclCommonUtil.TRANSACTIONTYPE_SIN %>"/>
			 <input name="submitOrderNo" id="submitOrderNo" class="easyui-validatebox" readonly="readonly" placeholder="系统自动生成" /><!-- onmouseover="this.title='系统自动生成'" -->
			 <input name="orderNo" id="orderNo" class="easyui-validatebox"  title="订单号"/> 
			 <input name="agentConsigneeCode" id="agentConsigneeCode" required="true" class="easyui-combogrid" codetype="<%=YclCommonUtil.ALL_ENTRUST %>" editable="false"/>
			 <input name="agentConsigneeDesc" id="agentConsigneeDesc" type="hidden"/>	
		     <input name="submitDate" id="submitDate" class="easyui-datetimebox" required="true" editable="false"/>
			 <input name="cargoConsigneeCode" id="cargoConsigneeCode" title="供应商" required="true" class="easyui-combogrid" codetype="<%=YclCommonUtil.ALL_SUPPLIERS %>" editable="false"/>
			 <input name="cargoConsigneeDesc" id="cargoConsigneeDesc" type="hidden"/>
		     <input name="deliveryType" id="deliveryType" class="easyui-combobox"  />	
			 <input name="aux3" id="aux3"  class="easyui-combobox"  />		
			 <input name="deliveryDate" id="deliveryDate" class="easyui-datetimebox" editable="false"  title="到货日期" />
			 <input name="unloadPort" id="unloadPort"  class="easyui-validatebox" />	
		     <input name="telNo" id="telNo"   class="easyui-validatebox" />
		     <input name="functionary" id="functionary"     class="easyui-validatebox"  />	  
		     <input name="creator" id="creator" class="easyui-validatebox" readonly="readonly"//>		      
 			 <input name="controlWord" id="controlWord"   type="hidden" /> 
			 <input name="transactionStatus" id="transactionStatus" class="easyui-combobox" disabled="true" codetype="<%=YclCommonUtil.SYS_BASE_INFO_STATUS%>" />
		</form>
	</div>
	
	<div region="center" iconCls="icon-edit" border="false">
		<!-- <div class="easyui-tabs" fit="true" > -->
			<!--<div title="<bean:message bundle="hub.hubInbound" key="LogisticsOrderDetailHub.title" />" >-->
				<div class="easyui-layout" fit="true" border="false">
					<div region="north" border="false">
						<div class="datagrid-toolbar">
							<a class="easyui-linkbutton" id="detailAddBtn" plain="true"  iconCls="icon-add" onclick="addDetailRow();"><bean:message bundle="wms.common" key="global.add" /></a>
							<a class="easyui-linkbutton" id="detailDelBtn" plain="true"  iconCls="myCustomerIcon_remove" onclick="deleteSelectedRows();"><bean:message bundle="wms.common" key="global.del" /></a>
						</div>
					</div>
					<div region="center" border="false">
						<table id="submitDetailDatagridIn" class="easyui-datagrid" fit="true" i18nRoot="InOrderDetailYCL">
							<thead>
								<tr>
									<th field="itemCode" editor="{type:'combogrid',options:{ required:true}}" codetype="<%=HUB_CommonUtil.CODE_DESC%>"  editable="false"></th>
									<th field="goodsDesc" editor="{type:'validatebox'}" width="250px;"></th>
								    <th field="qty" editor="{type:'numberbox',options:{ required:true,precision:4,min:0.0000001,max:999999999.99,validType:'numberRange[0.0000001,999999999.99]'}}" align="right"></th>
									<th field="qtyUnitCode" editor="{type:'combogrid',options:{ required:true}}" codetype="<%=HUB_CommonUtil.ALL_UNIT%>" editable="false"></th>
								    <th field="batchNo" editor="validatebox" ></th>
									<th field="extItemCode" editor="validatebox" ></th>
									<th field="aux5" editor="validatebox" ></th>			
									<th field="secondQty"  editor="validatebox" align="right" ></th>
									<th field="spec"  editor="validatebox" ></th>	
									<th field="model" editor="validatebox" ></th>
									<th field="remark"  editor="validatebox"  ></th>	
									
								   
																	    							
									<!-- 	
								    <th field="billNo" editor="{type:'validatebox',options:{required:true,editable:false}}"></th>
								    <th field="aux1" editor="{type:'combobox',options:{valueField:'id',textField:'text',data:sodAux1Data,panelHeight:'auto',required:true,editable:false,editable:false}}"  formatter="setAux1" type="hidden"  ></th> 
									<th field="seqNo" editor="validatebox"  ></th>
									<th field="aux2" editor="validatebox"  ></th>
									
									<th field="aux3" editor="validatebox"   ></th>
										 -->					
								</tr>
							</thead>
						</table>
					<!-- </div> -->
				<!-- </div> -->
			</div>
		</div>
	</div>
</body>
</html>