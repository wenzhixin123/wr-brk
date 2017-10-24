<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.sinotrans.gd.wms.ph.util.PhCommconUtil"%>
<%
	String path = request.getContextPath();
%>

<script>
//设置查询条件的状态下拉框
$(function(){
	$('#s_status').combobox({
    data:[
    	{
		    "v":"",
		    "text":"全部"
		},
    	{
		    "v":"Active",
		    "text":"生效"
		},{
		    "v":"Pending",
		    "text":"草稿"
		},{
		    "v":"Cancel",
		    "text":"作废"
		}
	],
    valueField:'v',
    textField:'text',
    panelHeight:90,
    editable:false
	});

	initLotType();
});
	//默认操作
	function defaultclick(){
		var rows = $('#lotStockGrid').datagrid('getSelections'); //获取被选择行的数据 
		if(rows && rows.length>0){
			if(rows.length>1){
				alert("只能默认一条");
			}else{
			    if(rows[0].status=="Cancel"||rows[0].status=="Pending"){ 
				    alert("只有生效的数据才能默认!"); 
				    return;
			    }
				var lotStockUuid = rows[0].basLotStockUuid;
				if(!Boolean(lotStockUuid)){
					alert("请先保存！");
					return;
				}else{
					$.messager.confirm('确定框', '确定设置这条记录为默认吗?',function(r){
						if(r){
							$.ajax({
								url:"<%=path%>/servlet/SinotransServlet?cmd=warehose_defaultLot",
								data:{lotStockUuid:lotStockUuid,basLocAreaUuid:basLocAreaUuid},
								dataType:"json",
								type:"POST",
								success:function(data){
										alert("默认成功！");
										$('#lotStockGrid').datagrid('load');
								}
							});
						}
					});
				}
				
			}
		}else{
			alert("请先选择一条数据！");
		}
	}
	//显示  默认
	function setdefaultview(value,rowData,rowIndex){
		  if(rowData){
			var w = rowData.controlWord.substring(1,2);
			if(w=="D"){
				return '默认';
			}
			if(w=="0") return '';
		}else{
			return '';
		}
	}
	/****************导出功能*****************/
	//导出功能
	function exportExcel(){
		var model = new Object();
			model.lotCode=$('#s_lotCode').val();
			model.lotName=$('#s_lotName').val();
			model.status=$('#s_status').combo("getValue");
			model.pri=$('#s_pri').val();
			model.basBasLocTypeUuid=$("#locTypeName1").combogrid('getValue');
			//model.condition="lotName_ilikeAnywhere";
			model.officeCode=officeTABCode;
			model.basLocAreaUuid=__basLocAreaTABUuid;
		
	    var values = [];
	    for (var property in model){
		    var objValue = model[property];
			values.push({
				fieldName:property,
				fieldType:null,
				fieldStringValue:null,
				fieldValue:objValue,
				operator:""
			});
		}
		
		var jParam = new Object();
		var queryInfo = {
				queryType:"BasLotStockQuery",
				queryFields:values,
				orderBy:"",
				pagingInfo:{
						pageSize:"",
						currentPage:"",
						totalRows:0
					},
				fieldCodeTypes:{}
		};
		var fieldDefinitions = [
           		getFieldDefinition("lotCode","货位编号",130),
           		getFieldDefinition("lotName","货位名称",130)
		];
		jParam.queryInfo= queryInfo;
		jParam.fieldDefinitions = fieldDefinitions;
		exportToExcel('<%=path%>','区域货位导出',jParam);
	}
	function getFieldDefinition(fieldName,label,width){
		var fd = {
                "beanName": "",
                "fieldName": fieldName,
                "label": label,
                "fieldType": "",
                "sortable": false,
                "nullable": false,
                "length": 0,
                "precision": 0,
                "scale": 0,
                "width": width
            };
        return fd;
	}
	function printLot(){
		var rows = $("#lotStockGrid").datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示',"请选择数据",'info');
			return;
		}
		if(rows.length>1){
			$.messager.alert('提示',"只能选择一条数据打印",'info');
			return;
		}
		var print = $("#printBtn").sinotrans_Print({
			path : '<%=path%>',
			modelId : rows[0].lotCode,
			templateType: '<%=PhCommconUtil.PRINT_LOT%>'
		});
		print.open();
	}
</script>	
		<div title="" iconCls=""  closable="true" style="">
				<%--按钮--%>
				<div class="myCustomer_toolbar" >
					<a href="#" id="searchBtn" class="easyui-linkbutton"
							plain="true" iconCls="icon-search" style="float: left">查询</a>
					<div class="datagrid-btn-separator"></div>
					<a href="#" id="resetButton" class="easyui-linkbutton"
							plain="true" iconCls="myCustomerIcon_clear" style="float: left">重置</a>
					<div class="datagrid-btn-separator"></div>
					<a href="#" id="defaultButton" class="easyui-linkbutton"
							plain="true" iconCls="icon_set_default" style="float: left" onclick="defaultclick()">默认</a>
					<div class="datagrid-btn-separator"></div>
					<a href="#" id="insectImportMetdod" class="easyui-linkbutton"
							plain="true" iconCls="icon_shengcheng" style="float: left" onclick="barcodeImportMethod()">导入</a>
					<div class="datagrid-btn-separator"></div>
					<a class="easyui-linkbutton" id="printBtn" plain="true"
									iconCls="icon-print" onclick="printLot();"> 打印 </a>
					<div class="datagrid-btn-separator"></div>
					<a id="excelExportOutbound" class="easyui-linkbutton" plain="true" iconCls="excel_export" style="float: left" onclick="exportExcel()">
								导出</a> 
					<div class="datagrid-btn-separator"></div>
					<a href="#" id="hiddenMsg" class="easyui-linkbutton"
							plain="true" iconCls="" style="float: left;display:none;color:red;">请选择一个仓库区域</a>
				</div>
				<%--查询表单--%>
				<div id="formContainer" >
					<form id="queryForm">
							<table id="searForm" width="800px" style="margin:0;padding:0;">
								<tr>
									<td align="center" class="textStyle">
										<label for="lotCode">
										货位编号:
										</label>
									</td>
									<td>
										<input id="s_lotCode" name="lotCode"  class="easyui-validatebox" validType="noChinese"/>
									</td>
									<td align="center" class="textStyle">
										<label for="lotName" >
										货位名称:
										</label>
									</td>
									<td>
										<input id="s_lotName" name="lotName" class="easyui-validatebox"  />
									</td>
									
									<td align="center" class="textStyle">
										<label for="lotName" >
										货位类型:
										</label>
									</td>
									<td align="left">
										<input id="basBasLocTypeUuid1" name="basBasLocTypeUuid1"   type="hidden" class="easyui-validatebox" />
										<input id="locTypeName1" name="locTypeName1"  combo="true"  class="easyui-validatebox" style="width:150px" />
									</td>
								</tr>
								<tr>
									<td align="center" class="textStyle">
										<label for="status" >
										状态:
										</label>
									</td>
									<td>
										<select id="s_status" name="status" style="width:70%" >
											<option value="">==请选择==</option>
											<option value="Active">生效</option>
											<option value="Pending">草稿</option>
											<option value="Cancel">作废</option>
										</select>
									</td>
									
									<td  align="center" class="textStyle">
										<label for="pri" >
											优先级:
										</label>
									</td>
									<td >
										<input id="s_pri" name="pri"   value="" />
									</td>
								</tr>
								
								
							</table>
					</form>
				</div>
		<div id="gridContainer" style="height:auto;">
						<table id="lotStockGrid" title="" iconCls="icon-edit"	 collapsible="true">
							<thead>
								<tr>
									<th field="status" formatter="fn_global_setBasicdataState" width="40"  align="center"  td_align="left" sortable="true">
										状态
									</th>
									<th field="def" formatter="setdefaultview" width="40"  align="center"  td_align="left">
										默认
									</th>
									<th field="lotCode"  width="80" align="center" td_align="left"
										editor="{type:'validatebox',options:{required:true,validType:'isUnique'}}">
										货位编号
									</th>
									<th field="lotName" width="150" align="center" td_align="left"  editor="validatebox">
										货位名称
									</th>
									<th field="locTypeName" width="150" align="center" td_align="left"  editor="validatebox">
										货位类型
									</th>
									<th field="locAreaCode"  width="80" align="center" td_align="left"
										editor="{type:'validatebox',options:{required:true,validType:'isUnique'}}">
										区域编号
									</th>
									<th field="locAreaName"  width="150" align="center" td_align="left"
										editor="{type:'validatebox',options:{required:true,validType:'isUnique'}}">
										区域名称
									</th>									
									<th field="pri" width="80"  align="center" td_align="right" editor="validatebox">
										优先级
									</th>
									<th field="putPri" width="80"  align="center" td_align="right"
										editor="validatebox">
										上架优先级
									</th>
									<th field=pickPri width="80"  align="center" td_align="right"
										editor="validatebox">
										拣货优先级
									</th>
									<th field="maxPalletFloor" width="80"  align="center" td_align="right"
										editor="validatebox">
										最大堆放层数
									</th>
									<th field="maxVoluem" width="80"  align="center" td_align="right"
										editor="validatebox">
										最大体积
									</th>
									<th field="maxWeight" width="80"  align="center" td_align="right"
										editor="validatebox">
										最大重量
									</th>
									<th field="maxPalletQty" width="80"  align="center" td_align="right"
										editor="validatebox">
										最大托盘数
									</th>
									<th field="length" width="80"  align="center" td_align="right"
										editor="validatebox">
										长
									</th>
									<th field="width" width="80"  align="center" td_align="right"
										editor="validatebox">
										宽
									</th>
									<th field="height" width="80"  align="center" td_align="right"
										editor="validatebox">
										高
									</th>
									<th field="lotX" width="80"  align="center" td_align="right"
										editor="validatebox">
										货架位置-行
									</th>
									<th field="lotY" width="80"  align="center" td_align="right"
										editor="validatebox">
										货架位置-列
									</th>
									<th field="lotZ" width="80"  align="center" td_align="right"
										editor="validatebox">
										货架位置-高
									</th>
									<th field="lockFlag" width="80" align="center" td_align="left"  formatter="lockFlagFormatter"
										editor="validatebox">
										锁定
									</th>
									<th field="remark" width="80"  align="center" td_align="left"
										editor="validatebox">
										备注
									</th>
								</tr>
							</thead>
						</table>
		 </div>
				
		</div>
