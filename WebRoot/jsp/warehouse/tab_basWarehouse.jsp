<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<script type="text/javascript">

function saveWarehouseForm(){
	var node = $('#warehouseTree').tree('getSelected');
	if(!Boolean(node)||node.attributes._class!="com.sinotrans.gd.wms.common.model.BasWarehouseModel"){
		alert("请先选择一个仓库");
		return;
	}
	if(!$('#warehouseEditForm').form('validate')){return false};
	$("#downloadmap").linkbutton("disable").unbind("click");
	$("#warehouseEditSaveBtn").linkbutton("disable").unbind("click");     //灰掉保存按键
	$("#warehouseEditResetBtn").linkbutton("disable").unbind("click"); 
	$("#savesvgPath1").linkbutton("disable").unbind("click");   
	$("#warehouseEditForm").find('input').attr('readonly','readonly');    //屏蔽Form中所有input字段不可编辑
	
	var $allInput = $('#warehouseEditForm input ,#warehouseEditForm select');
	var objArray = [];
	var formObj={};
	for(var i=0;i<$allInput.size();i=i+1){
		formObj[$allInput[i].id]=$('#'+$allInput[i].id,$('#warehouseEditForm')).val();
	}
	formObj.basWarehouseUuid=$('#w_basWarehouseUuid').val();
	formObj.basWarehouseTypeUuid=$('#w_basWarehouseTypeUuid').val();
	formObj.preBasWarehouseUuid=$('#w_preBasWarehouseUuid').val();
	formObj.status=$('#w_status').val();
	formObj.rowState=$('#w_rowState').val();
	formObj.w_basWarehouseUuid=undefined;
	formObj.w_basWarehouseTypeUuid=undefined;
	formObj.w_preBasWarehouseUuid=undefined;
	formObj.w_status=undefined;
	formObj.w_rowState=undefined;
	formObj.savesvgPath1=undefined;
	formObj.downloadmap=undefined;	
	formObj.mapmassege=undefined;
	formObj.isUpload=undefined;
	objArray.push(formObj);
	var locArea_url="<%=request.getContextPath()%>"+"/servlet/SinotransServlet?cmd=saveBasWarehouse";
	var jsonResult = $.toJSON(objArray);
	WmsCommonManager.object2base64(jsonResult,function(data){
	if(data){	
			$.ajax({ 
				type: "POST",
			    url: locArea_url,
			    data:"jsonResult="+data+"",
			    success: function(result){
						refreshNode(currentNode,"warehouseEditForm");
			    		alert("保存成功");
			    		if($("#isUpload").val()==1){
			    			$("#downloadmap").linkbutton("enable").unbind("click").bind("click");
			    		}else{
			    			$("#downloadmap").linkbutton("disable").unbind("click");
			    		}
			    		$("#warehouseEditSaveBtn").linkbutton("enable").unbind("click").bind("click");     //恢复保存按键
			    		$("#warehouseEditResetBtn").linkbutton("enable").unbind("click").bind("click");
			    		$("#savesvgPath1").linkbutton("enable").unbind("click").bind("click");
						$("#warehouseEditForm").find('input').attr('readonly',false);    //恢复Form中所有input字段可编辑
					},
				error:function (XMLHttpRequest, textStatus, errorThrown){
					alert("Error:"+XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
				}
			});
		 }
	});
}

//上传地图

	function uploadwarehousemap(){
		var ld=[];
	    ld.push(saveBasWarehouseUuid);
		var importFile=$("#barcodeImport").sinotrans_Import({
			path:'<%=path %>',
			fileType:'.svg,.svg',
			businessType:'SVG',
			modelIds:ld.join(","),
			functionName:'callback1'
		});
		importFile.open();
	}

	function callback1(data){
		if(data.result){
			$("#downloadmap").linkbutton("enable").unbind("click").bind("click"); 	//显示下载按键	
			$("#mapmassege").html("该仓库地图上传成功！");
			$("#isUpload").val(1);
			alert(data.msg);
		}else{
			$("#isUpload").val(0);
			alert(data.error);	
		}
	}
	//下载
	function downloadwarehousemap(){
		var downloadaction_url="<%=path%>/downLoadAction.do?basWarehouseUuid="+saveBasWarehouseUuid;
		window.open(downloadaction_url);
	}
function clearWarehouseForm(){
	$('#warehouseEditForm')[0].reset(); 
}
$(function(){
	$('#serviceAging').numberbox();
	$('#warehouseCapacity').numberbox();
	
	var wuuid = $("#w_basWarehouseUuid").val();
	if(!wuuid){
		$("#savesvgPath1").linkbutton("disable").unbind("click");     //灰掉上传地图按键
		$("#downloadmap").linkbutton("disable").unbind("click");     //灰掉下载地图按键
	}
});

//设置表单的样式
$(function(){
	$("#warehouseTable").css({
		'margin-left':'9%'
	});
	$("#warehouseTable td:even").css({
	});
	$("#warehouseTable td:odd ").css({
	});
});

</script>

<div>
	<form action="" name="warehouseEditForm" id="warehouseEditForm">
				<%--按钮--%>
				<div class="myCustomer_toolbar" >
					<a href="#" id="warehouseEditSaveBtn" class="easyui-linkbutton" onclick="saveWarehouseForm()"
							plain="true" iconCls="icon-save" style="float: left">保存</a>
					<div class="datagrid-btn-separator"></div>
					<a href="#" id="warehouseEditResetBtn" class="easyui-linkbutton" onclick="clearWarehouseForm()"
							plain="true" iconCls="myCustomerIcon_clear" style="float: left">清空</a>
					<div class="datagrid-btn-separator"></div>
				</div>
				
				<div>
					<table id="warehouseTable" style="width: 250px;">
							<!-- 隐藏字段 -->
							<tr style="display: none;">
								<td><input type="hidden" id="w_basWarehouseUuid" name="basWarehouseUuid"/></td>
								<td><input type="hidden" id="w_basWarehouseTypeUuid" name="basWarehouseTypeUuid"/></td>
								<td><input type="hidden" id="w_preBasWarehouseUuid" name="preBasWarehouseUuid"/></td>
								<td><input type="hidden" id="w_status" name="status" value="Pending"/></td>
								<td><input type="hidden" id="w_rowState" name="rowState"/></td>
								<td><input type="hidden" id="isUpload"/></td> 
							</tr>
							<tr>
								<td align="right">仓库编号:</td>
								<td align="right">
									<input type="text" id="warehouseCode" name="warehouseCode" class="easyui-validatebox" validType="isUnique" required="true"/>
								</td>
							</tr>
						
							<tr>	
								<td align="right">仓库名称:</td>
								<td align="right">
									<input type="text" id="warehouseName" name="warehouseName" class="easyui-validatebox" />
								</td>
							</tr>
						
							<tr>	
								<td align="right">仓库英文名称:</td>
								<td align="right">
									<input type="text" id="warehouseNameEn" name="warehouseNameEn" class="easyui-validatebox" validType="noChinese"/>
								</td>
							</tr>
						
							<tr>
								<td align="right">仓库容量:</td>
								<td align="right">
									<input type="text" id="warehouseCapacity" name="warehouseCapacity" class="easyui-validatebox" />
								</td>
							</tr>
						
							<tr>	
								<td align="right">仓库地址:</td>
								<td align="right">
									<input type="text" id="warehouseAddress" name="warehouseAddress" class="easyui-validatebox" />
								</td>
							</tr>
						
							<tr>	
								<td align="right">服务区域:</td>
								<td align="right">
									<input type="text" id="serviceArea" name="serviceArea" class="easyui-validatebox" />
								</td>
							</tr>
							
							<tr>
								<td align="right">时效:</td>
								<td align="right">
									<input type="text" id="serviceAging" name="serviceAging" class="easyui-validatebox" />
								</td>
							<tr/>
							
							<tr>
								<td align="right">备注:</td>
								<td align="right">
									<input type="text" id="remark" name="remark" class="easyui-validatebox" />
								</td>
							</tr>
							
							<tr>
								<td align="right">地图信息:</td>
								<td align="center">
									<a id="mapmassege" name="mapmassege" style="color : #000;"></a>
								</td>
							</tr>
							
							<tr>
								<td align="right">
								<a id="savesvgPath1" name="savesvgPath1"  class="easyui-linkbutton"  onclick="uploadwarehousemap()" >上传</a>
								</td>
								
								<td align="right">
									<a id="downloadmap" name="downloadmap"  class="easyui-linkbutton" onclick="downloadwarehousemap()" >下载</a>
								</td>
							</tr>												
							
					</table>
				</div>
			</form>
</div>