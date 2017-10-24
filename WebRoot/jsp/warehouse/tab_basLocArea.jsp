<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Locale"%>
<%
	String path = request.getContextPath();
%>

<!-- 仓库区域编辑 -->
<script type="text/javascript">

function saveLocAreaForm(){
	var node = $('#warehouseTree').tree('getSelected');
	if(!Boolean(node)){
		alert("请先选一个仓库,再点新增区域!");
		return;
	}
	if(!$("#controlLocArea").val()&&node.attributes._class=="com.sinotrans.gd.wms.common.model.BasWarehouseModel"){
	   alert("请先点 新增区域!");
	   return;
	}
	if(!$('#locAreaEditForm').form('validate')){return false;};
	$("#editResetBtn").linkbutton("disable").unbind("click");     
	$("#editSaveBtn").linkbutton("disable").unbind("click");     //灰掉保存按键
	$("#locAreaEditForm").find('input').attr('readonly','readonly');    //屏蔽Form中所有input字段不可编辑
	
	var $allInput = $('#locAreaEditForm input ,#locAreaEditForm select');
	var objArray = [];
	var formObj={};
	for(var i=0;i<$allInput.size();i=i+1){
		if(i==11||i==12){
			formObj[$allInput[i].id]=$($allInput[i].id,$('#locAreaEditForm')).val();
		}else{
		    formObj[$allInput[i].id]=$('#'+$allInput[i].id,$('#locAreaEditForm')).val();
		}
	}
	formObj.basLocAreaUuid=$('#_basLocAreaUuid').val();
	formObj.basWarehouseUuid=$('#_basWarehouseUuid').val();
	formObj.status=$('#_status').val();
	formObj.recVer=$('#_recVer').val();
	formObj.rowState=$('#_rowState').val();
	formObj.remark=$('#_remark').val();
	formObj.controlWord=$.trim($("#_controlWord").combobox("getValue"));
	formObj._basLocAreaUuid=undefined;
	formObj._basWarehouseUuid=undefined;
	formObj._status=undefined;
	formObj._recVer=undefined;
	formObj._rowState=undefined;
	formObj._remark=undefined;
	formObj.controlLocArea=undefined;
	formObj._controlWord=undefined;
	objArray.push(formObj);
	var locArea_url="<%=request.getContextPath()%>"+"/servlet/SinotransServlet?cmd=saveBasLocArea";
	var jsonResult = $.toJSON(objArray);
	WmsCommonManager.object2base64(jsonResult,function(data){
	if(data){	
			$.ajax({ 
				type: "POST",
				cache:false,
			    url: locArea_url,
			    data:"jsonResult="+data+"",
			    dataType:"json",
			    success: function(result){
			    		//只有在新增的时候才刷新子节点
			    		if(result['BasLocAreaModelList']){
			    			if(currentNode.attributes._class=="com.sinotrans.gd.wms.common.model.BasWarehouseModel"){
				    			refreshChildNode(currentNode,result['BasLocAreaModelList'][0]);
					    	}
				    	}
						refreshNode(currentNode,"locAreaEditForm");
			    		alert("保存成功");
			    		$("#editResetBtn").linkbutton("enable").unbind("click").bind("click"); 
			    		$("#editSaveBtn").linkbutton("enable").unbind("click").bind("click");     //恢复保存按键
						$("#locAreaEditForm").find('input').attr('readonly',false);    //屏蔽Form中所有input字段不可编辑
					},
				error:function (XMLHttpRequest, textStatus, errorThrown){
				    $("#editResetBtn").linkbutton("enable").unbind("click").bind("click"); 
			    	$("#editSaveBtn").linkbutton("enable").unbind("click").bind("click");     //恢复保存按键
					$("#locAreaEditForm").find('input').attr('readonly',false);    //屏蔽Form中所有input字段不可编辑
					alert("Error:"+XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
				}
			});
		}
	});
}

function clearLocAreaForm(){
	$('#locAreaEditForm')[0].reset();
	$('#_controlWord').combobox("setValue","0");  
}
//设置表单的样式
$(function(){
	$("#locAreaTable").css({
		'margin-left':'9%'
	});
	$("#locAreaTable td:even").css({
	});
	$("#locAreaTable td:odd input").css({
		'margin-left':'100px'
	});
});

</script>

<div>
	<form action="" name="locAreaEditForm" id="locAreaEditForm">
				<%--按钮--%>
				<div class="myCustomer_toolbar" >
					<a href="#" id="editSaveBtn" class="easyui-linkbutton" onclick="saveLocAreaForm()"
							plain="true" iconCls="icon-save" style="float: left">保存</a>
					<div class="datagrid-btn-separator"></div>
					<a href="#" id="editResetBtn" class="easyui-linkbutton" onclick="clearLocAreaForm()"
							plain="true" iconCls="myCustomerIcon_clear" style="float: left">清空</a>
					<div class="datagrid-btn-separator"></div>
				</div>
				
				<div>
					<table id="locAreaTable" style="width: 230px;">
							<!-- 隐藏字段 -->
							<tr style="display: none;">
								<td><input type="hidden" id="_basLocAreaUuid" name="basLocAreaUuid"/></td>
								<td><input type="hidden" id="_basWarehouseUuid" name="basWarehouseUuid"/></td>
								<td><input type="hidden" id="_status" name="status" value="Active"/></td>
								<td><input type="hidden" id="_recVer" name="recVer" value="0"/></td>
								<td><input type="hidden" id="_rowState" name="rowState"/></td>
								<td><input type="hidden" id="controlLocArea" name="controlLocArea"/><td>
							</tr>
							<tr>
								<td align="right">区域代码:</td>
								<td align="left">
									<input type="text" id="locAreaCode" name="locAreaCode" class="easyui-validatebox" required="true"  onchange="valitlocCode(this.id,this.value)" style="TEXT-TRANSFORM:uppercase;ime-mode:disabled"/>
								</td>
							</tr>
							<tr>	
								<td align="right">区域名称:</td>
								<td align="left">
									<input type="text" id="locAreaName" name="locAreaName" class="easyui-validatebox" />
								</td>
							</tr>
							<tr>	
								<td align="right">区域描述:</td>
								<td align="left">
									<input type="text" id="locAreaNameEn" name="locAreaNameEn" class="easyui-validatebox" />
								</td>
							</tr>
						
							<tr>
								<td align="right">备注:</td>
								<td align="left">
									<input type="text" id="_remark" name="remark" class="easyui-validatebox" />
								</td>
							</tr>
							
							<tr>
								<td align="right">区域类型:</td>
								<td align="left">
									<input type="text" id="_controlWord" name="_controlWord" style="width:60px"/>
								</td>
							</tr>
							
					</table>
				</div>
			</form>
</div>