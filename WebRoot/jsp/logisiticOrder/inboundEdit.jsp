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
	<%@ page import="java.util.UUID" %>
	<%
String path = request.getContextPath();
SessionContextUserEntity scue = SessionContextUserEntity.currentUser();	
String officeCode = SessionContextUserEntity.currentUser().getOfficeCode();

String logisticsOrderUuid = request.getParameter("logisticsOrderUuid");
String submitOrderUuid = request.getParameter("submitOrderUuid");
request.setAttribute("logisticsOrderUuid", logisticsOrderUuid);
request.setAttribute("submitOrderUuid", submitOrderUuid);
String logisticsOrderNo = request.getParameter("logisticsOrderNo");
request.setAttribute("logisticsOrderNo", logisticsOrderNo);
 %>

<script type="application/javascript">

	var logisticsEntity = new Object();  //新建一个对象用来存放所有页面信息
	var logisticsOrderUuid="";  //保存成功之后返回的值uuid

	var getQgoodsTypePage="",getQdaoHuoType="",getQdeliveryType="",getCw10="";
	var agentConsigneeCode="",agentConsigneeDesc="",cargoConsigneeCode="",cargoConsigneeDesc="";
	//2014年5月8日 10:38:18 wj+ 深圳物流·是否在提交后关闭此页面
	var activeIsClose="<%=request.getParameter("activeIsClose")%>";

	$(function(){
        $("#submitOrderUuid").val("");
        if("${submitOrderUuid}") $("#submitOrderUuid").val("${submitOrderUuid}");

        $("#logisticsOrderUuid").val("${logisticsOrderUuid}");
		$("#logisticsOrderUuid").val("");
		//从查询页面跳转到修改页面之后使用DWR查询数据
		if("${logisticsOrderUuid}"!=null && "${logisticsOrderUuid}"!=""){
			$("#logisticsOrderUuid").val("${logisticsOrderUuid}");
			queryLogOrderAndDetail();
		}else{
			/*----------------------初始化数据-----------------------------------*/
			WmsCommonManager.getDataBaseDateFor_YMD_HMS(function(result){// 默认取系统数据时间
				$("#orderDate").datebox("setValue",result);
			});
			//初始化默认客户
			ECCommonManager.getDefaultCustomer(function(customer){
				$("#agentConsigneeDesc").combogrid('setValue',customer.customerCode);
				$("#agentConsigneeDesc").combogrid('setText',customer.customerName);
			});
		}
		$('#logisticsOrderNo').attr("readonly","readonly");
	});

    function queryLogOrderAndDetail(){
        LogisticsOrderManager.get("${logisticsOrderUuid}",function(data){
            if(data){
                getUUIDdata(data);
                queryLogisticsDetail();
            }else{
                alert("该单已不存在。");
            }
        });
    }
	function getUUIDdata(data){

		console.info(data);

		logisticsOrderUuid=data.logisticsOrderUuid;
		$("#logisticsOrderUuid").val(data.logisticsOrderUuid);
		$("#logisticsOrderNo").val(data.logisticsOrderNo);
		$("#orderNo").val(data.orderNo);
		$("#telNo").val(data.telNo);//电话
		$("#workDemand").val(data.workDemand);
		$("#flow").val(data.flow==null?"":data.flow); //系统平台
		$("#aux1").val(data.aux1==null?"":data.aux1); //子库
		$("#aux2").val(data.aux2==null?"":data.aux2);
		$("#submitOrderUuid").val(data.submitOrderUuid);//订单号隐藏
		$("#transactionStatus").combobox("setValue",data.transactionStatus); //状态隐藏

		if(data.orderDate!=null&&data.orderDate!=""){
			$("#orderDate").datebox("setValue",data.orderDate);
		}

		if(data.agentConsigneeCode!=null&&data.agentConsigneeCode!=""){
			$("#agentConsigneeCode").combogrid('setValue',data.agentConsigneeCode);
			$("#agentConsigneeDesc").val(data.agentConsigneeDesc);
		}

		$("#functionary").val(data.functionary);
		if(data.cargoConsigneeCode!=null&&data.cargoConsigneeCode!=""){
			$("#cargoConsigneeCode").combogrid('setValue',data.cargoConsigneeCode);
			$("#cargoConsigneeDesc").val(data.cargoConsigneeDesc);
		}

		$("#remark").val(data.remark);
		$("#creator").val(data.creator);

	}

	function queryLogisticsDetail(){
        $("#lodetaildatagird").datagrid("setQueryFields",[
            {
                fieldName:"logisticsOrderUuid",
                fieldStringValue:"${logisticsOrderUuid}"
            }
        ]);
        $("#lodetaildatagird").datagrid("commonQuery",{
            queryType:"LogisticsOrderDetailModel"
        });
    }

	//点击新增时清空数据
	function newButton(){
		confirm('确认框','提示:如果确定本页信息将全部清空', function(r){
			if (r){
				newButtonTwo();
			}
		});
	}

	function newButtonTwo(){
		window.location.href="inboundEdit.jsp";
	}

	function saveHeadAndDetail(){
		isIfJubep("<%=CommonUtil.Active%>");
	}
	/********************************************** 开始 保存按钮事件***********************************************************/
	/******效验部分数据方法*****/
	function isIfJubep(typeStatus){
		var aux1 = $("#aux1").val();
		if (aux1==null||aux1=="") {
			$.messager.confirm("提示","子库数据为空是否继续保存",function(b) {
				if (b) {
					saveTiaoButton(typeStatus);
				}
			});
		}else{
			saveTiaoButton(typeStatus);
		}
	}
	//点击保存触发
	function saveTiaoButton(typeStatus){

		setTimeout(function(){

				if(typeStatus!=null&&typeStatus=="Active"){
					$("#transactionStatus").val(typeStatus);/**<=CommonUtil.Pending>*/
				}else{
					$("#transactionStatus").val('<%=CommonUtil.Pending%>');/**<=CommonUtil.Pending>*/
				}
				var logisticsOrderDetailArray = new Array();
				var logisticsOrderContainerArray = new Array();
				var agentConsigneeCode = $("#agentConsigneeCode").combogrid('getValue');
				var agentConsigneeDesc = $("#agentConsigneeCode").combogrid('getText');
				$("#agentConsigneeCode").val(agentConsigneeCode);
				$("#agentConsigneeDesc").val(agentConsigneeDesc);

//				var validateable=false;
//				//验证表单是否通过
//				validateable=$("#logisticsOrderForm").form('validate'); //控件验证信息

				var cargoConsigneeCode = $("#cargoConsigneeCode").combogrid('getValue');//供应商
				var orderNo = $("#orderNo").val();

				if(!cargoConsigneeCode)
					return alert("“供应商”是必输项、请认真填写!");
				if(!orderNo)
					return alert("“入库凭证”是必输项、请认真填写!");
//				if(!validateable)
//					return alert("*必填数据为空！请审核后重新操作！");

				//获取 Logistics Order 基本信息
				logisticsEntity.logisticsOrderUuid = $("#logisticsOrderUuid").val();
				logisticsEntity.logisticsOrderNo = $("#logisticsOrderNo").val();;
				logisticsEntity.agentConsigneeCode = $("#agentConsigneeCode").combogrid('getValue');
				logisticsEntity.agentConsigneeDesc = $("#agentConsigneeCode").combogrid('getText');
				logisticsEntity.deliveryType = $("#deliveryType").combobox('getValue');
				logisticsEntity.transactionStatus = $("#transactionStatus").combobox("getValue");
				logisticsEntity.orderNo =$("#orderNo").val();
				logisticsEntity.cargoConsigneeCode =$("#cargoConsigneeCode").combogrid('getValue');
				logisticsEntity.cargoConsigneeDesc =$("#cargoConsigneeCode").combogrid('getText');
				logisticsEntity.aux3 =$("#aux3").combobox("getValue");
				logisticsEntity.orderDate = $("#orderDate").datebox('getValue');
				logisticsEntity.functionary =$("#functionary").val();
				logisticsEntity.telNo =$("#telNo").val();
				logisticsEntity.aux1 =$("#aux1").val();
				logisticsEntity.aux2 =$("#aux2").val();
				logisticsEntity.flow =$("#flow").val();
				logisticsEntity.workDemand =$("#workDemand").val();
				logisticsEntity.remark =$("#remark").val();
                logisticsEntity.officeCode = "<%=officeCode%>";

				//logisticsEntity.cw10 = $("#cw10").combobox('getValue');
				//logisticsEntity.unloadPort = $("#unloadPort").combogrid('getValue');

				/*logisticsEntity.deliveryDate = $("#deliveryDate").datebox('getValue');
				logisticsEntity.agentConsigneeCode = $("#agentConsigneeCode").val();
				logisticsEntity.agentConsigneeDesc = $("#agentConsigneeDesc").val();
				logisticsEntity.cargoControlDesc =$("#cargoControlDesc").val();
				logisticsEntity.cargoControlCode = $("#cargoControlCode").val();
				logisticsEntity.cargoControlDesc =$("#cargoControlDesc").combogrid('getText');
				logisticsEntity.cargoControlCode = $("#cargoControlDesc").combogrid('getValue');
				logisticsEntity.flow =$("#flowS").val();
				logisticsEntity.projectCode=$("#projectName").combogrid('getValue');
				logisticsEntity.contractNo =$("#contractNo").val();
				logisticsEntity.projectCode =$("#projectName").combogrid('getValue');
				logisticsEntity.trailingTeam =$("#trailingTeam").val();
				logisticsEntity.tractorNo =$("#tractorNo").val();
				logisticsEntity.workDemand =$("#workDemand").val();
				logisticsEntity.daoHuoType= $("#daoHuoType").combobox('getValue');
				logisticsEntity.goodsTypePage =$("#goodsTypePage").combobox('getValue');
				logisticsEntity.remark =$("#remark").val();

				logisticsEntity.recVer=$("#recVer").val();
				logisticsEntity.submitOrderUuid=$("#submitOrderUuid").val();//采购单号UUID
				logisticsEntity.purchasingOrderNo=$("#purchasingOrderNo").val();//采购单号*/

				//此处是新增项保存
				//得到货物信息数据
				var insertRows = $('#lodetaildatagird').datagrid('getData');//得到此刻表单所有的数据
				if(insertRows){
					$.each(insertRows.rows,function(k,v){
						logisticsOrderDetailArray.push(OutlogisticsContainerHuoWu(v,"<%=CommonUtil.ROW_STATE_ADDED%>"));
					});
					//logisticsEntity.logisticsOrderDetailModel=logisticsOrderDetailArray;
				}

				LogisticsOrderManager.savelogisticAndDetails(logisticsEntity,logisticsOrderDetailArray,function(spj){
                    if(!spj.result) return $.messager.show({msg:spj.error,timeout:2000,showType:'slide'});
                    $.messager.show({msg:spj.msg,timeout:2000,showType:'slide'});
                    queryLogOrderAndDetail();
                    return;
				});
		},200);
	}
	/********************************************** 结束 保存按钮事件***********************************************************/
	function OutlogisticsContainerHuoWu(data,rowState){//封装货物信息
		var lc = new Object();
		lc.logisticsOrderNo=data.logisticsOrderNo;
		lc.batchNo=data.batchNo;
		lc.seqNo=data.seqNo;
		lc.itemCode=data.itemCode;
		lc.itemSeqno=data.itemSeqno;
		lc.goodsDesc=data.goodsDesc;
		lc.marksNumber=data.marksNumber;
		lc.barcode=data.barcode;
		lc.packageNo=data.packageNo;
		lc.qtyUnitCode=data.qtyUnitCode;
		lc.qtyUnitDesc=data.qtyUnitDesc;
		lc.qty=data.qty;
		lc.model=data.model;
		lc.thirdUnitCode=data.thirdUnitCode;
		lc.thirdUnitDesc=data.thirdUnitDesc;
		lc.thirdQty=data.thirdQty;
		lc.spec=data.spec;
		lc.secondUnitCode=data.secondUnitCode;
		lc.secondUnitDesc=data.secondUnitDesc;
		lc.secondQty=data.secondQty;
		//lc.lengthUnitCode=data.lengthUnitCode;
		lc.lengthUnitDesc=data.lengthUnitDesc;
		lc.length=data.length;
		lc.width=data.width;
		lc.height=data.height;
		lc.productionDate=data.productionDate;
		lc.volumeUnitCode=data.volumeUnitCode;
		lc.volumeUnitDesc=data.volumeUnitDesc;
		lc.volume=data.volume;
		lc.weightUnitCode=data.weightUnitCode;
		lc.weightUnitDesc=data.weightUnitDesc;
		lc.netWeight=data.netWeight;
		lc.grossWeight=data.grossWeight;
		lc.remark=data.remark;
		lc.goodsKind=data.goodsKind;
		lc.dangerCode=data.dangerCode;
		lc.barcode=data.barcode;
		lc.inLogisticsOrderDetailUuid=data.inLogisticsOrderDetailUuid;
		lc.inLogisticsOrderNo=data.inLogisticsOrderNo;
		lc.recVer=data.recVer;
		lc.logisticsOrderUuid=data.logisticsOrderUuid;
		lc.logisticsOrderDetailUuid=data.logisticsOrderDetailUuid;
		lc.goodsNature=data.goodsNature;
		lc.unitPrice=data.unitPrice;
		lc.totalPrice=data.totalPrice;
		if(data.logisticsOrderDetailUuid!=null&&data.logisticsOrderDetailUuid!=""){
			if(rowState!="<%=CommonUtil.ROW_STATE_DELETED%>"){
				lc.rowState="<%=CommonUtil.ROW_STATE_MODIFIED%>";
			}else{
				lc.rowState = rowState;
			}
		}else{
			lc.rowState = rowState;
		}
		return lc;
	}

	function addLogisticsOrderDetailDatagird(){
		$("#lodetaildatagird").datagrid("insertRow",{
			index:$("#lodetaildatagird").datagrid("getSelectedIndex"),
			row:{}
		});
	}

	function deleteSelectedRows(){

        var deleteDetails = $("#lodetaildatagird").datagrid("getSelections");
	    
        if(!deleteDetails)
            $.messager.alert('提示','请选择一行数据进行删除','ok');
        $.messager.confirm('提示',"确认删除吗?",function(b){
            if(b){
                var logisticsDetailIds = new Array();
                for(var i = 0;i < deleteDetails.length; i++){
                    var logisticsDetailId = deleteDetails[i].logisticsOrderDetailUuid;
                    if(logisticsDetailId) logisticsDetailIds.push(logisticsDetailId);
                }
                LogisticsOrderDetailManager.removeAllByPk(logisticsDetailIds,function(){
                    queryLogisticsDetail();
                    $.messager.alert('提示','删除成功','ok');
                })
            }
        });


	}
</script>

</head>

<body class="easyui-layout">
	<!-- 单头 -->
	<div region="north" title="入库单单头" iconCls="icon-edit">
		<div class="datagrid-toolbar" >
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="newButton();" id="addBtn" > 增加</a>
			<a class="easyui-linkbutton" plain="true" iconCls="icon-save" onclick="saveHeadAndDetail();" id="saveBtn"> 保存 </a>
			<a class="easyui-linkbutton" id="submitBtn" plain="true" iconCls="icon-ok" onclick="submitBtn();"> 提交 </a>
			<a class="easyui-linkbutton" id="delBtn" plain="true" iconCls="myCustomerIcon_remove" onclick="removeButton();">删除 </a>		
		</div>
		<form id="logisticsOrderForm" class="easyui-form" i18nRoot="YclLogisticsOrder" columns="4"   >
			<input name="logisticsOrderUuid" id="logisticsOrderUuid" type="hidden"/>
			<input name="submitOrderUuid" id="submitOrderUuid" type="hidden"/>
			<input name="logisticsOrderNo" title="入库单号" id="logisticsOrderNo" class="easyui-validatebox" readonly="readonly" />
			<input name="agentConsigneeCode" title="委托单位" id="agentConsigneeCode" required="true" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="agentConsigneeDesc" title="" id="agentConsigneeDesc" type="hidden"/>
			<input name="deliveryType" title="作业项目" id="deliveryType" class="easyui-combobox" />
			<input name="transactionStatus" id="transactionStatus" class="easyui-combobox" disabled="true" codetype="STATUS" />
			<input name="orderNo" title="订单号" id="orderNo" class="easyui-validatebox" required="true" />
			<input name="cargoConsigneeCode" title="供应商" id="cargoConsigneeCode" required="true" class="easyui-combogrid" codetype="ALL_CUSTOMER"/>
			<input name="cargoConsigneeDesc" id="cargoConsigneeDesc" type="hidden"/>
			<input name="aux3" id="aux3" class="easyui-combobox" editable="false" panelHeight="auto"  />
			<input name="orderDate" title="办单日期" id="orderDate" class="easyui-datetimebox" />
			<input name="functionary" title="经办人" id="functionary" class="easyui-validatebox" type="hidden"/>
			<input name="creator" id="creator" class="easyui-validatebox" readonly="readonly" />
			<input name="telNo" title="联系电话" id="telNo" class="easyui-validatebox" />
			<input id="controlWord" name="controlWord" type="hidden"/>
			<input name="aux1" id="aux1" class="easyui-validatebox"  type="hidden"//>
			<input name="aux2" id="aux2" class="easyui-validatebox" placeholder="送货人联系电话"  type="hidden"//>
			<input name="flow" id="flow" class="easyui-validatebox" type="hidden"/>
			<input name="workDemand" id="workDemand" class="easyui-validatebox" type="hidden"/>
			<input name="remark" title="备注" id="remark"/>
		</form>
	</div>
	<!-- 明细 -->
	<div region="center" iconCls="icon-edit" >
		<div class="easyui-tabs" fit="true" >
			<div title="入库单明细" >
				<div class="easyui-layout" fit="true" >
					<div region="north">
						<div class="datagrid-toolbar">
							<a class="easyui-linkbutton" id="detailAddBtn" plain="true"  iconCls="icon-add" onclick="addLogisticsOrderDetailDatagird();">
								增加</a>
							<a class="easyui-linkbutton" id="detailDelBtn" plain="true"  iconCls="myCustomerIcon_remove" onclick="deleteSelectedRows();">
								删除</a>
						</div>
					</div>
					<div region="center" >
						<table id="lodetaildatagird" class="easyui-datagrid" fit="true" i18nRoot="YclLogisticsOrderDetail">
							<thead>
								<tr>
									<th field="itemCode" title=" 物料编号" editor="{type:'combogrid',options:{ required:true}}" codetype="CODE_DESC" editable="false"></th>
									<th field="goodsDesc" title="货物名称"  editor="{type:'validatebox',options:{ required:false}}" width="250px;"></th>
									<th field="qty" title="包装数量" editor="{type:'numberbox',options:{ required:true,precision:4,min:0.0000001,max:999999999.99,validType:'numberRange[0.0000001,999999999.99]'}}" align="right"></th>
									<th field="qtyUnitCode" title="包装单位" editor="{type:'combogrid',options:{required:true}}" codetype="ALL_UNIT" editable="false"></th>
									<th field="productionDate" title="" editor="{type:'datebox',options:{disabled:false,required:false,formatter:formatDate}}"></th>
									<th field="packageNo" title="箱名号"  editor="{type:'validatebox',options:{required:false}}"  title="箱名号" ></th>
									<th field="barcode" editor="validatebox" title="条码"></th>
									<th field="spec" title="规格" editor="validatebox" ></th>
									<th field="batchNo" title="批次号" editor="validatebox" ></th>
									<th field="grossWeight" title="总毛重" editor="{type:'numberbox',options:{precision:4}}" align="right" ></th>
									<th field="netWeight"  title="总净重" editor="{type:'numberbox',options:{precision:4}}" align="right"  ></th>
									<th field="remark" title="备注" editor="validatebox" ></th>
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