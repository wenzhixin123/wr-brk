<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.sinotrans.gd.wlp.util.StringUtil, java.util.Date" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/fros-easyui/common/include.jsp" %>

<script type="text/javascript">

//新增保存
function saveAdd(){
	if (! $("#dialogEdit").form("validate")) {
		$.messager.alert("提示", "数据验证错误", "warning");
		return;
	}
	var saveModel = $("#dialogEdit").form("getData");
	DcsManifestManager.saveAdd(saveModel, function(result) {
		$.messager.alert("提示", "保存成功！", "warning");
		$("#dialogEdit").form("clear");
	});	
}


//新增
function editRow() {
	$("#dialogEdit").dialog("open");
};

//编辑窗口确定
function editDialogOk() {
	if (! $("#formEdit").form("validate")) {
		$.messager.alert("提示", "数据验证错误", "warning");
		return;
	}
	$("#dialogEdit").dialog("close");
	$("#gridResult").datagrid("updateRow", {
		index : $("#gridResult").datagrid("getSelectedIndex"),
		row : $("#formEdit").form("getData")
	});
};

function deleteRow(){
	var selectedRow = $("#dcsmanifest").datagrid("getSelected");
	if (selectedRow == null){
		$.messager.alert("提示", "没有选择数据", "warning");
	}else{
		if(confirm("是否确认从数据库删除")){
			DcsManifestManager.deletRecordByID(selectedRow.portManifestId,function(result) {
				query();
				$.messager.alert("提示", "删除完成！", "info");
			});
		}
	}
}

//编辑窗口取消
function editDialogCancel() {
	$("#dialogEdit").dialog("close");
};

function printValidate(){
	 var searchFrom=$("#search2").form("getData");
	 var url = 'printValidate.jsp?id='+searchFrom.portManifestId;
	 window.open (url, '', 'height=600, width=700, top=0,left=0, toolbar=no, menubar=yes, scrollbars=yes, resizable=yes,location=no,status=no');
}


function query() {	
	if (! $("#formQuery").form("validate")) {
		$.messager.alert("提示", "数据验证错误", "warning");
		return;
	}
	var condition=$("#formQuery").form("getData");
	DcsManifestManager.queryByCondition(condition,function(result) {
		$("#dcsmanifest").datagrid("loadData", {
			rows : result
		});
	});
};
//重置
function clearQueryForm() {
	$("#formQuery").form("clear");
	var curDate="<%=StringUtil.DateToString(new Date(), "yyyy-MM-dd") %>";
	$("#etd").datebox("setValue",curDate);
	$("#etdTo").datebox("setValue",curDate);
};

function openUi004() {

	var selectedRow = $("#dcsmanifest").datagrid("getSelected");
	if (selectedRow == null){
		$.messager.alert("提示", "没有选择数据", "warning");
	}else{
		$("#bill01").datagrid("loadData",{
			rows : []
		});
		$("#bill02").datagrid("loadData",{
			rows : []
		});
		$("#so1").datagrid("loadData",{
			rows : []
		});
		$("#so2").datagrid("loadData",{
			rows : []
		});
		DcsBillLadingManager.findByExample2(selectedRow,function(result) {
			$("#bill01").datagrid("loadData", {
				rows : result
			});
			DcsManifestManager.queryByConditionById(selectedRow.portManifestId,function(result) {
				$("#search2").form("setData", result);
			 
			});
		});
		$("#ui004").dialog("open");	
		setTimeout(function() {
			$("#divSO").hide();
		}, 0);
		$('#soBtn').linkbutton('enable');
		$('#billBtn').linkbutton('disable');
		$('#billAdd').linkbutton('enable');
		$('#billDel').linkbutton('enable');
	}
};

function openUi005() {
	getVesselForecastData();
	
	$("#ui005").dialog("open");
};

function getVesselForecastData(){
	var searchFrom=$("#search2").form("getData");
	
	DcsVesselForecastManager.getVesselForecastByPortManifestId(searchFrom.portManifestId,function(result){
		$("#ui005Form").form("clear");
		$("#ui005Form").form("setData", result);
	});
}


function publish(){
	var ui005Form=$("#ui005Form").form("getData");
	DcsVesselForecastManager.publishVesselForecast(ui005Form,function(result){
		getVesselForecastData();
		$.messager.alert("提示", "发布成功！", "info");
	});
}

function getVesselForecasts(){
	var thcs=$("#thcs").form("getData");
	DcsVesselForecastManager.getVesselForecasts(thcs,function(result){
		$("#thxx").datagrid("loadData", {
			rows : result
			});
	});
}

function openUi006() {
	var searchFrom=$("#search2").form("getData");
	DcsBillLadingManager.getCheckMessage(searchFrom.portManifestId,function(result) {
		$("#check").datagrid("loadData", {
			rows : result
			});

		$("#ui006").dialog("open");
	});	
};

function openOS() {
	$("#so1").datagrid("loadData", {
		rows : []
		});
	$("#so2").datagrid("loadData", {
		rows : []
		});
	var searchFrom=$("#search2").form("getData");
	var condition= new Object();
	condition.portManifestId = searchFrom.portManifestId;
	
	DcsBillLadingManager.querySosByPortManifestId(condition,function(result) {
		$("#so1").datagrid("loadData", {
		rows : result
		});
	});
	
	$("#divSO").show();
	$("#divBill").hide();	
	$('#soBtn').linkbutton('disable');
	$('#billBtn').linkbutton('enable');
	$('#billAdd').linkbutton('disable');
	$('#billDel').linkbutton('disable');

};
function openBill() {
	$("#divSO").hide();
	$("#divBill").show();
	
	$('#soBtn').linkbutton('enable');
	$('#billBtn').linkbutton('disable');
	$('#billAdd').linkbutton('enable');
	$('#billDel').linkbutton('enable');

};

function openUi008() {
	
		var selectedRow = $("#dcsmanifest").datagrid("getSelected");
		if (selectedRow == null){
			$.messager.alert("提示", "没有选择数据", "warning");
		}else{
			DcsBillLadingManager.findByExample2(selectedRow,function(result) {
				$("#add2").datagrid("loadData", {
					rows : result
				});
			});
			$("#inputBill").form("clear");
			$("#add1").datagrid("loadData", {
				rows : []
			});
			$("#ui008").dialog("open");
		}
		
};

function deleteBill(){
	var selectedBill = $("#bill01").datagrid("getSelected");
	if (selectedBill == null){
		$.messager.alert("提示", "没有选择数据", "warning");
	}else{
		var manifest = $("#search2").form("getData");
		var status = manifest.manifestStatus;
		
		if (status =="清洁"){
			$.messager.alert("提示", "已清洁舱单不能修改", "warning");
			return;
		}
		if(!confirm("是否确认从舱单上移除该提单？")){
			return;
		}
		
		DcsBillLadingManager.delBillFromManifest(selectedBill.billLadingNo, manifest.portManifestId,function(result) {
			var selectedRow = $("#dcsmanifest").datagrid("getSelected");
			if (selectedRow == null){
				$.messager.alert("提示", "没有选择数据", "warning");
			}else{
				if (""!=result){
					$.messager.alert("提示", "移除成功！ 请注意，下列拼柜的提运单未移除该舱单："+result, "warning");
				}else{
					$.messager.alert("提示", "移除成功", "info");
				}
				$("#bill01").datagrid("loadData",{
					rows : []
				});
				$("#bill02").datagrid("loadData",{
					rows : []
				});
				$("#so1").datagrid("loadData",{
					rows : []
				});
				$("#so2").datagrid("loadData",{
					rows : []
				});
				DcsBillLadingManager.findByExample2(selectedRow,function(result) {
					$("#bill01").datagrid("loadData", {
						rows : result
					});
				});
			}
		});

	}
}

function closeUi004(){
	$("#ui004").dialog("close");
}
	
function closeUi005(){
	$("#ui005").dialog("close");
}
function closeUi006(){
	$("#ui006").dialog("close");
}
function closeUi007(){
	$("#ui007").dialog("close");
}
function closeUi008(){
	$("#ui008").dialog("close");
}
function saveBillLoding(){
//	$.messager.alert("提示", "没有选择数据", "info");

		var manifest = $("#search2").form("getData");
		
		var status = manifest.manifestStatus;
		
		if (status =="清洁"){
			$.messager.alert("提示", "已清洁舱单不允许修改", "warning");
		}else{
			DcsManifestManager.saveEntity(manifest,function(result) {
				$.messager.alert("提示", "保存成功", "info");});
		}
}

function getContainerByBillNo(billNo){
	$("#bill02").datagrid("loadData", {
		rows : []
		});
	var conditionBill= new Object();
	conditionBill.billNo = billNo;
	DcsBillLadingManager.queryContsByBillNo(conditionBill,function(result) {
		$("#bill02").datagrid("loadData", {
		rows : result
		});
	});
}

function getContainerByBillNoForAdd(){
	
	$("#add1").datagrid("loadData", {
		rows : []
		});
	
	var inputBill=$("#inputBill").form("getData");
	var conditionBill= new Object();
	
	conditionBill.billNo = inputBill.billLadingNo;
	DcsBillLadingManager.queryContsByBillNo(conditionBill,function(result) {
		$("#add1").datagrid("loadData", {
		rows : result
		});
	});
}


function addBillLoad(){
	var add1Rows = $("#add1").datagrid("getData").rows;
	
	if(add1Rows.length==0){
		$.messager.alert("提示", "无查询结果用于添加", "warning");
		return;
	}
	
	var search2=$("#search2").form("getData");
	var status = search2.manifestStatus;
	
	if (status =="清洁"){
		$.messager.alert("提示", "已清洁舱单不能修改", "warning");
	}else{
		DcsBillLadingManager.addBillToManifest(add1Rows, search2.portManifestId,function(result) {
			$("#add1").datagrid("loadData", {
				rows : []
			});
			
			if (""!=result){
				$.messager.alert("提示", "添加成功！ 请注意，下列拼柜的提运单未加入该舱单："+result, "warning");
			}else{
				$.messager.alert("提示", "添加成功", "info");
			}
			$("#add2").datagrid("loadData", {
				rows : []
			});
			var selectedRow = $("#dcsmanifest").datagrid("getSelected");
				DcsBillLadingManager.findByExample2(selectedRow,function(result) {
					$("#add2").datagrid("loadData", {
						rows : result
					});
				});
				$("#inputBill").form("clear");
				$("#add1").datagrid("loadData", {
					rows : []
				});
		});
		

	}
		
}

function getContainerBySo(soNo){
	$("#so2").datagrid("loadData", {
		rows : []
		});
	var conditionSo= new Object();
	conditionSo.soNo = soNo;
	var searchFrom=$("#search2").form("getData");
	conditionSo.portManifestId = searchFrom.portManifestId;
	
	DcsBillLadingManager.queryContsBySo(conditionSo,function(result) {
		$("#so2").datagrid("loadData", {
		rows : result
		});
	});
}


function confirmPreAssigned(){
	var manifest = $("#search2").form("getData");
	var status = manifest.manifestStatus;
	
	if (status !="预配"){
		$.messager.alert("提示", "只有预配状态下的舱单可以确认预配", "warning");
	}else{
		DcsManifestManager.confirmPreAssigned(manifest,function(result) {
			 $("#search2").form("setData", result);
			$.messager.alert("提示", "修改成功", "info");});
	}
}

function clear2(){

	var manifest = $("#search2").form("getData");
	var status = manifest.manifestStatus;
	if (status !="确认预配"){
		$.messager.alert("提示", "只有确认预配状态下的舱单可以清洁", "warning");
	}else{
		DcsManifestManager.clear(manifest,function(result) {
			 $("#search2").form("setData", result);
			$.messager.alert("提示", "修改成功", "info");});
	}
}

function unClear(){
	var manifest = $("#search2").form("getData");
	var status = manifest.manifestStatus;
	if (status !="清洁"){
		$.messager.alert("提示", "只有清洁状态下的舱单可以取消清洁", "warning");
	}else{
		DcsManifestManager.unClear(manifest,function(result) {
			 $("#search2").form("setData", result);
			$.messager.alert("提示", "修改成功", "info");});
	}
}

//grid数据格式化
function dateFieldFormatter(value, rowData, rowIndex) {
	//只显示年月日
	return value ? value.substring(0, 10) : value;
};


//grid数据格式化
function dateFieldFormatter2(value) {
	//只显示年月日
	return value ? value.substring(0, 10) : value;
};

$(function(){

	$("#bill01").datagrid("options").onClickRow= function(index,data){  
		$.fn.datagrid.defaults.onClickRow.apply(this, [index,data]);
		getContainerByBillNo(data.billLadingNo);
	};
	
	$("#so1").datagrid("options").onClickRow= function(index,data){  
		$.fn.datagrid.defaults.onClickRow.apply(this, [index,data]);
		getContainerBySo(data.so);
	};
	
	var curDate="<%=StringUtil.DateToString(new Date(), "yyyy-MM-dd") %>";
	$("#etd").datebox("setValue",curDate);
	$("#etdTo").datebox("setValue",curDate);
	
	$('#ui004').window("options").onClose = function(){
   	$.fn.window.defaults.onClose.apply(this);
    	query();
   	};
   	
	$('#ui008').window("options").onClose = function(){
	   	$.fn.window.defaults.onClose.apply(this);
	   	var selectedRow = $("#dcsmanifest").datagrid("getSelected");

		$("#bill01").datagrid("loadData",{
			rows : []
		});
		$("#bill02").datagrid("loadData",{
			rows : []
		});
		$("#so1").datagrid("loadData",{
			rows : []
		});
		$("#so2").datagrid("loadData",{
			rows : []
		});
		DcsBillLadingManager.findByExample2(selectedRow,function(result) {
			$("#bill01").datagrid("loadData", {
				rows : result
			});
		});

	  };

  }
)

function openFile(){
	$("#uploadDialog").dialog("open");
}
function uploadDialogCancel() {
	$("#uploadDialog").dialog("close");
};
function generateUUID(){
	var userId = "<%=SessionContextUserEntity.currentUser().getUserId() %>"; 
	var date= new Date();
	return userId + formatDate(date);
}
function formatDate(date){
	date= new Date();
	year = date.getFullYear().toString();
	month = (date.getMonth() + 1).toString();
	d_date = date.getDate().toString();
	hour = date.getHours().toString();
	minute = date.getMinutes().toString();
	second = date.getSeconds().toString();
	millisecond = date.getMilliseconds().toString();
	return year + month + d_date +hour + minute + second + millisecond;
}
function uploadFile(){ 
	  var fileName = $("#uploadFile").val();
	  var fileType = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length);
	  var uuid = generateUUID();
	  //alert (fileName+"-fileType-"+fileType);

	  $("#formUpload").form("submit",{  
		    url:"/wlp/JsonFacadeServlet?uuid=" + uuid + "&type=" + fileType, 
		    onSubmit: function(){  
		    	if(fileType != "xls" && fileType != "xlsx"){ 
		    		$.messager.alert("提示", "文件类型不正确！", "error");
		    		return false;
		    	}
		    },  
		    success:function(data){
		    	DcsContainerInfoManager.doExcleProcessDataFile(uuid,fileType,$("#uploadFile").val() , function(result) {
		    		if(result.result=="success"){
			    		alert ("成功"); 
		    			/*$("#gridUploadSuccess").datagrid("loadData", 
{rows: result.successRecords});
		    			$.messager.alert("提示", "导入成功", "info");
		    			$("#uploadDialog").dialog("close");*/
		    		}else{
			    		alert (result.result);
		    			/*$("#uploadDialog").dialog("close");
		    			$("#gridUploadSuccess").datagrid("loadData", {rows: 

result.successRecords});
		    			$("#uploadFailDialog").dialog("open");
		    			$("#gridFailList").datagrid("options").rowStyler = 

function (index,row,css){
		    			     if (row.frozen==true){
		    			      	return 'background-color:#cfcfcf;';
		    			     }
	    			    };
		    			$("#gridFailList").datagrid("loadData", {rows: 

result.errRecords});*/
		    		}					
				}); 
		    	 
		    }  
		}); 
 
	  } 

 
</script>
</head>

<body class="easyui-layout">
	<div region="north" title="舱单" >
		<div class="datagrid-toolbar">
		<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">舱单查询条件</span>
			<a class="easyui-linkbutton" iconCls="icon-search" onclick="query()" key="Q">查询</a>
			<a class="easyui-linkbutton" iconCls="icon-reload" onclick="clearQueryForm()" key="N">重置</a>
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="openFile()" key="I">导入预配信息</a>
		</div>
		<form id="formQuery" class="easyui-form" columns="4" i18nRoot="DcsLogisticsOrder">
			<input id="etd" name="dateFrom" title="启航日期" class="easyui-datebox" /> 
			<input id="etdTo" name="dateTo" title="至" class="easyui-datebox" /> 
			<input name="portAreaCode" title="装货点"/> 
			<input name="portTransport" title="卸货点" /> 
			<input name="vesselName" title="船名" class="easyui-combogrid" codetype="ALL_VESSEL"/> 
			<input name="voyageNo" title="航次"/> 
			<input name="portDepature" title="装货港" class="easyui-combogrid" codetype="ALL_PORT"/> 
			<input name="portArrival" title="卸货港" class="easyui-combogrid" codetype="ALL_PORT"/>
			<input name="billLadingNo" title="提单号"/> 
			<input name="shippingOrderNo" title="S/O号"/> 
			<input name="containerNo" title="箱号"/>
			<input name="sealNo" title="封号"/>
		</form>
	</div>
	<div region="center" border="false">
		<div class="datagrid-toolbar">
		<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">查询结果</span>
			<a class="easyui-linkbutton" iconCls="icon-add" onclick="editRow()" key="A">新增</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteRow()" key="D">删除</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" onclick="openUi004()" key="E">舱单明细</a>
		</div>
		<table id="dcsmanifest" class="easyui-datagrid" fit="true" pagination="false" singleSelect="true">
			<thead>
				<tr>
					<th field="manifestStatus"  title="舱单状态"/> 
<!-- 					<th field="manifestControlWord"  title="test"/> -->
					<th field="manifestType"  title="进出口" codetype="INOROUT"/> 
					<th field="flowType"  title="类型"  codetype="MANIFEST_TYPE"/> 
					<th field="vesselId"  title="船名" codetype="ALL_VESSEL"/> 
					<th field="voyageNo"  title="航次" /> 
					<th field="portDeparture"  title="装货港" codetype="ALL_PORT"/> 
					<th field="portArrival"  title="卸货港" codetype="ALL_PORT"/> 
					<th field="dateDeparture"  title="启航日期"  formatter="dateFieldFormatter"/> 
					<th field="dateArrival"  title="预抵日期" formatter="dateFieldFormatter"/> 
					<th field="loaded20"  title="20L"/> 
					<th field="loaded40"  title="40L"/> 
					<th field="empty20"  title="20E"/>
					<th field="empty40"  title="40E"/> 
					<th field="totalLoaded"  title="重票数"/> 
					<th field="totalEmpty"  title="吉票数"/> 
					<th field="loadedweight"  title="重柜货重"/> 
					<th field="customsManifestNo"  title="海关舱单号"/> 
					<th field="linerName"  title="驳船公司" codetype="ALL_CARRIAGE" /> 
					<th field="creator"  title="创建人"/> 
					<th field="createTime"  title="创建时间" /> 
				    <th field="portManifestId"  title="舱单头ID"/>
				</tr> 
			</thead>
		</table>
	</div>
</body>	

		<!-- 编辑窗口 -->
	<div id="ui004" class="easyui-dialog" title="舱单明细" iconCls="icon-edit"
			 style="width:1000px; height:700px"  closed="true" modal="true">
		<div fit="true" class="easyui-layout">
			<div region="north" title="舱单头"  style="height:200px">
				<div class="datagrid-toolbar">
					<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">舱单头信息</span>
					<a class="easyui-linkbutton" iconCls="icon-save" onclick="saveBillLoding()" Key="S">保存舱单头信息</a>
					<a class="easyui-linkbutton" onclick="openUi005()" Key="T">同行舱位信息</a>
					<a class="easyui-linkbutton" onclick="openUi006()" Key="K">校验</a>
					<a class="easyui-linkbutton" onclick="confirmPreAssigned()" Key="Y">确认预配</a>
					<a class="easyui-linkbutton" onclick="clear2()"  Key="L">清洁</a>
					<a class="easyui-linkbutton" onclick="unClear()"  Key="U">取消清洁</a>
					<a class="easyui-linkbutton" iconCls="icon-print" Key="P">打印</a>
				</div>
				<form id="search2" class="easyui-form" columns="4" i18nRoot="DcsLogisticsOrder">
					<input id="manifestStatus" name="manifestStatus" title="舱单状态" disabled="true"/> 
					<input name="manifestType" title="进出口" disabled="true" codetype="INOROUT"  class="easyui-combobox"/> 
					<input name="flowType"  title="类型"  codetype="MANIFEST_TYPE" class="easyui-combogrid"/> 
					<input name="vesselId"  title="船名" required="true"  class="easyui-combogrid" codetype="ALL_VESSEL"/> 
					<input name="voyageNo"  title="航次" required="true" /> 
					<input name="portDeparture" title="装货港" required="true" class="easyui-combogrid" codetype="ALL_PORT"/> 
					<input name="portArrival"  title="卸货港" required="true" class="easyui-combogrid" codetype="ALL_PORT"/> 
					<input name="dateDeparture"  title="启航日期" class="easyui-datebox"/>
					<input name="dateArrival"  title="抵港日期" class="easyui-datebox"/>
					<input name="loaded20"  title="20L" disabled="true"/> 
					<input name="loaded40"  title="40L" disabled="true"/> 
					<input name="empty20"  title="20E" disabled="true"/>
					<input name="empty40"  title="40E" disabled="true"/> 
					<input name="totalLoaded"  title="重票数" disabled="true"/> 
					<input name="totalEmpty"  title="吉票数" disabled="true"/> 
					<input name="loadedweight"  title="重柜货重" disabled="true"/> 
					<input name="customsManifestNo"  title="海关舱单号" editor="text" /> 
					<input name="linerName" title="驳船公司" class="easyui-combogrid" codetype="ALL_CARRIAGE"/>
					<input name="portManifestId" title="舱单头ID" disabled="true"/>
				</form>
			</div>
			<div region="center"  iconCls="icon-edit" fit="true">
				<div class="datagrid-toolbar">
					<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">舱单明细</span>
					<a id="soBtn" class="easyui-linkbutton" iconCls="icon-reload" onclick="openOS()" Key="O">S/O号汇总信息</a>
					<a id="billBtn" class="easyui-linkbutton" iconCls="icon-reload" onclick="openBill()" Key="B">提运单号汇总信息</a>
					<a id="billAdd" class="easyui-linkbutton" onclick="openUi008()" iconCls="icon-add" Key="A">添加提运单</a>
					<a id="billDel" class="easyui-linkbutton"  iconCls="icon-remove" onclick="deleteBill()" key="D">移除提运单</a>
				</div>
				<div fit="true" class="easyui-layout" id="divBill">
					<div region="north" style="width:1000px; height:200px" >
						<table id="bill01" class="easyui-datagrid" i18nRoot="DcsContainerInfo" fit="true" pagination="false" singleSelect="true">
							<thead>
								<tr>
									<th field="billLadingNo" title="提运单号"/>
									<th field="chargeStatus" title="计费"/>
									<th field="paymentCode" title="付费人"   />
									<th field="goodsSummarizedName" title="货物名称"/>
									<th field="goodsTotalPackage" title="件数"/>
									<th field="goodsTotalWeight" title="毛重"/>
									<th field="finalDestinationCode" title="目的港" codetype="ALL_PORT"/>
									<th field="shipperCode" title="付货人" codetype="ALL_CUSTOMER" />
									<th field="consigneeCode" title="收货人" codetype="ALL_CUSTOMER"/>
								</tr>
							</thead>
						</table>
					</div>
					<div region="center" fit="true" >
						<table id="bill02" class="easyui-datagrid" i18nRoot="DcsContainerInfo" fit="true" pagination="false" singleSelect="true">
							<thead>
								<tr>
								 	<th field="containerNo" title="箱号" />
									<th field="shippingOrderNo" title="S/O 号" />
									<th field="containerType" title="箱型"/>
									<th field="sealNo" title="封条号"/>
									<th field="zj" title="重吉"/>
									<th field="cntrAdminCode" title="控箱公司"/>
									<th field="cntrOwnerCode" title="箱属" />
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<div id="divSO" fit="true" class="easyui-layout"> <!-- SO 汇总信息 -->
					<div region="north" style="height:200px">
						<table id="so1" class="easyui-datagrid" i18nRoot="DcsContainerInfo" fit="true" pagination="false" singleSelect="true">
							<thead>
								<tr>
									<th field="so" title="S/O号"/>
									<th field="zj" title="重吉"/> <!-- 多个集装箱有重有吉，按重算 -->
								<!--	<th field="client" title="委托人"/>  导入中的以上交收货人 XX -->
									<th field="client" title="Liner"/> <!-- 多个，显示第一个 -->
									<th field="arrival" title="装货点" />
									<th field="transport" title="卸货点"/><!-- 导入中的以上交 XX -->
									<th field="goosName" title="货名"/> <!-- 多个提运单，显示第一个 -->
									<th field="wt" title="毛重"/>
									<th field="pc" title="件数"/>
								</tr>
							</thead>
						</table>
					</div>
					<div region="center" fit="true">
						<table id="so2" class="easyui-datagrid" i18nRoot="DcsContainerInfo" fit="true" pagination="false" singleSelect="true">
							<thead>
								<tr>
								 	<th field="containerNo" title="箱号"/>
								 	<th field="billNo" title="提运单号"/>
									<th field="CType" title="箱型"/>
									<th field="sealNo" title="封条号"/>
									<th field="zj" title="重吉"/>
									<th field="cntrAdminCode" title="控箱公司"/>
									<th field="client" title="箱属" />
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			
			
		</div>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="closeUi004()" Key="R">完成</a>
		</div>
	</div>
	
			<!-- 同行舱位信息 -->
	<div id="ui005" class="easyui-dialog" title="同行舱位信息" iconCls="icon-edit"
			 style="width:1000px; height:500px"  closed="true" modal="true">
		<div fit="true" class="easyui-layout">
			<div region="north" title="本船舱位信息"  style="height:150px">
				<div class="datagrid-toolbar">
					<a class="easyui-linkbutton"  iconCls="icon-save" onclick="publish()" Key="S">发布该舱单信息</a>
				</div>
				<form id="ui005Form" class="easyui-form" columns="3" i18nRoot="DcsLogisticsOrder">
					<input name="portManifestId"  title="舱单头ID" disabled="true"/>
			<!-- 	<input name="manifestStatus"  title="舱单状态"disabled="true"/> 
					<input name="manifestType"  title="进出口"disabled="true"/> 
					<input name="vesselId"  title="船名" disabled="true"/> 
					<input name="voyageNo"  title="航次" disabled="true"/> 
					<input name="portDeparture" title="装货港" disabled="true"/> 
					<input name="portArrival"  title="卸货港" disabled="true"/>  -->
					<input name="publishAvailableE20" title="可载量"  class="easyui-numberbox"/>
					<input name="publishAvailableF20" title="未配舱位" class="easyui-numberbox"/>
				<!-- 	<input name="finishedDate" title="装卸结束时间" class="easyui-datetimebox"/>-->
				</form>
			</div>
			<div region="center"  iconCls="icon-edit" fit="true">
				<div fit="true" class="easyui-layout">
					<div region="north"  style="height:100px">
						<div class="datagrid-toolbar">
							<a class="easyui-linkbutton"  iconCls="icon-search" onclick="getVesselForecasts()" Key="Q">查询</a>
						</div>
						<form id="thcs" class="easyui-form" columns="4" i18nRoot="DcsLogisticsOrder">
							<input name="dateFrom"  title="启航日期" class="easyui-datebox" /> 
							<input name="dateTo"  title="至" class="easyui-datebox" /> 
							<input name="departure" title="启航港" codetype="ALL_PORT"  class="easyui-combogrid" /> 
							<input name="arrival"  title="抵达港" codetype="ALL_PORT"  class="easyui-combogrid" /> 
						</form>
					</div>
					<div region="center" fit="true" >
						<table id="thxx" class="easyui-datagrid" i18nRoot="DcsContainerInfo" fit="true" pagination="false" singleSelect="true">
							<thead>
								<tr>
								 	<th field="vesselId" title="船名" codetype="ALL_VESSEL"/>
									<th field="voyageNo" title="航次" />
									<th field="dateDeparture" title="启航日期"  formatter="dateFieldFormatter"/>
									<th field="dateArrival" title="抵港日期"  formatter="dateFieldFormatter"/>
									<th field="portDeparture" title="启航港" codetype="ALL_PORT"/>
									<th field="portArrival" title="抵达港" codetype="ALL_PORT"/>
									<th field="publishAvailableE20" title="可载量" />
									<th field="publishAvailableF20" title="未配舱位" />
									<th field=vesselBerthEtd title="截止时间"  formatter="dateFieldFormatter"/>
									<th field="creator" title="创建人" />
									<th field="createTime" title="创建时间" />
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="closeUi005()" Key="R">完成</a>
		</div>
	</div>
	
<!-- 校验 -->
	<div id="ui006" class="easyui-dialog" title="校验" iconCls="icon-edit"
			 style="width:700px; height:400px"  closed="true" modal="true">
		<div fit="true" class="easyui-layout">
			<div region="center"  iconCls="icon-edit" >
				<div class="datagrid-toolbar">
					<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">舱单校验</span>
					<a class="easyui-linkbutton" iconCls="icon-print" onclick="printValidate()" Key="P">打印</a>
				</div>
				<table id="check" class="easyui-datagrid" i18nRoot="DcsContainerInfo" fit="true" pagination="false" showFooter="true">
					<thead>
						<tr>
							<th field="billNo" title="提运单号"/>
							<th field="status" title="作业状况"/>
							<th field="createFee" title="港建费"/>
							<th field="inMsg" title="内装信息"/>
							<th field="admin" title="控箱公司"/>
							<th field="pierPaper" title="码头纸"/>
							<th field="stockpiling" title="堆存费" />
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="closeUi006()" Key="R">完成</a>
		</div>
	</div>
	
		<!-- 编辑 -->
	<div id="ui008" class="easyui-dialog" title="添加提运单" iconCls="icon-edit"
			 style="width:700px; height:600px"   closed="true" modal="true">
		<div fit="true" class="easyui-layout">
			<div region="north" style="width:700px; height:250px">
				<div fit="true" class="easyui-layout">
					<div region="north" style="width:700px; height:70px" >
						<div class="datagrid-toolbar">
						<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">查询条件</span>
							<a class="easyui-linkbutton" iconCls="icon-search" onclick="getContainerByBillNoForAdd()" Key="Q">查询</a>
						</div>
						<form id="inputBill" class="easyui-form" columns="4" i18nRoot="DcsLogisticsOrder">
							<input name="billLadingNo" title="提运单号"/>
						</form>
					</div>
					<div region="center"  iconCls="icon-edit" style="width:700px; height:150px">
						<div class="datagrid-toolbar">
							<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">查询结果</span>
							<a class="easyui-linkbutton" iconCls="icon-add"  onclick="addBillLoad()" Key="A">新增提运单到舱单</a>
						</div>
						<table id="add1" class="easyui-datagrid" i18nRoot="DcsContainerInfo" fit="true" pagination="false" >
							<thead>
								<tr>
									<th field="containerNo" title="集装箱号"/>
									<th field="billNo" title="提运单号"/>
									<th field="shippingOrderNo" title="S/O 号" editor="text"/>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<div region="center"  iconCls="icon-edit" >
				<div class="datagrid-toolbar">
					<span class="panel-header panel-title" style="float: left; border-style: none; width: 100px;">舱单提运单列表列表</span>
				</div>
				<table id="add2" class="easyui-datagrid" i18nRoot="DcsContainerInfo" fit="true" pagination="false" showFooter="true">
					<thead>
						<tr>
							<th field="billLadingNo" title="提运单号"/>
							<th field="goodsSummarizedName" title="货物名称"/>
							<th field="finalDestinationCode" title="目的港"/>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="closeUi008()" Key="R">完成</a>
		</div>
	</div>
	
	
	<!-- 弹出新增窗口编辑 -->
<div id="dialogEdit" class="easyui-dialog" title="新增" iconCls="icon-edit"
			 style="width:700px; height:400px"  closed="true" modal="true">
		<div fit="true" class="easyui-layout">
			<div region="center"  iconCls="icon-edit" >
				<div class="datagrid-toolbar">
					<a class="easyui-linkbutton"  iconCls="icon-save" onclick="saveAdd()" Key="S">保存</a>
				</div>
				<form id="formEdit" class="easyui-form" columns="2">
					<!-- <input name="portManifestId" title="仓单号" class="" validType="length[0,50]" required="true"/> -->
					<input name="manifestType" title="进出口" class="easyui-combobox" required="true" codetype="INOROUT"/>
					<input name="vesselId"  title="船名" required="true"  class="easyui-combogrid" codetype="ALL_VESSEL"/>
					<input name="voyageNo" title="航次" class="easyui-validatebox" required="true"/>
					<input name="linerCode" title="船公司" class="easyui-combogrid" required="true" codetype="ALL_CARRIAGE"/>
					<input name="portDeparture" title="启航港口"  required="true" class="easyui-combogrid" codetype="ALL_PORT"/>
					<input name="portArrival" title="到达港口" required="true" class="easyui-combogrid" codetype="ALL_PORT"/>
					<input name="X1" title="航线" class="easyui-validatebox"/>
					<input name="portAreaCode" title="装卸货地点" class="easyui-validatebox"/>
					<input name="dateDeparture" title="启航日期" class="easyui-datebox"/>
					<input name="dateArrival" title="预抵日期" class="easyui-datebox"/>
					<input name="customsManifestNo" title="海关仓单号" class="easyui-validatebox"/>
					<!-- <input name="" title="船员列表" class="" validType="length[0,150]"/> -->
					<textarea name="workDesc" title="作业要求" rowspan="2" colspan="2"></textarea>
				</form>
			</div>
		</div>
	<div class="dialog-buttons">
			<a class="easyui-linkbutton" iconCls="icon-ok" onclick="editDialogCancel()" Key="R">完成</a>
	</div>
          <div id="uploadDialog" class="easyui-dialog" title="Excel导入" style="width:600px;padding:10px" closed="true" modal="true">
			<form id="formUpload" method="post" enctype="MULTIPART/FORM-DATA"> 
				<div style="margin-bottom:5px">
					<!--	liner:<select name="liner">
						<option value="MOL" selected="selected">MOL</option>
							<option value="NYK">NYK</option>		

					    </select> -->
				</div>    
				 <input type="file" id="uploadFile" name="浏览" size="60"/>
				<ul style="list-style-type:none">
					<li><a class="easyui-linkbutton" style="float:right; margin:10px; margin-right:60px" iconCls="icon-cancel" onclick="uploadDialogCancel()" key="Q">取消</a></li>
					<li><a class="easyui-linkbutton" style="float:right; margin:10px;" iconCls="icon-ok" onclick="uploadFile()" key="R">确认</a></li>
				</ul>
			</form>
		</div>
	
	
</div>
	
</html>