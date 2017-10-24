<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.sinotrans.gd.wlp.common.web.EC_CommonUtil"%>
<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity" %>
<%@page import="com.sinotrans.gd.wlp.util.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%
	String path = request.getContextPath();
	SessionContextUserEntity scu = SessionContextUserEntity.currentUser();
%>
<html>
	<head>
		<title>打印模板类型管理</title>
		<%@include file="/fros-easyui/common/include.jsp"%>
		<script type="text/javascript">
		  $(function(){
		   	$('#printTemplate').dialog();//初始化添加窗口
			$('#printTemplate').dialog('close');//关闭dialog
			$('#statusC').combobox({
			  	data:[<%=StringUtil.toJsonArray(CommonUtil.statusMap)%>],
			  	valueField:'id',  
                textField:'text'
			});
			});
			
			function chincesStatus(value){
				if(value=='<%=EC_CommonUtil.Active%>'){
					return "有效";
				}else if(value=='<%=EC_CommonUtil.Cancel%>'){
					return "作废";
				}else if(value=='<%=EC_CommonUtil.Pending%>'){
					return "草稿";
				}else{
					return "---";
				}
		   }
			
			//查询
			function query() {
				$("#PrintTemplateDatagrid").datagrid("commonQuery", {
					queryType : "TemplateTypeModel",
					paramForm : "ChaprintTemplateForm"
				});
			}
		    //新增
			function appendRow(){
			  $('#printTemplate').dialog({
				modal:true
			   });
			   saveButton("E");
			   $('#printTemplateForm').form('clear');
			   $('#status').val('Pending');
			   $('#officeCode').val('<%=scu.getOfficeCode()%>');
			}
			//重置
			function clearTriggerForm() {
				$("#ChaprintTemplateForm").form("clear");
			}
			
			//关闭对话框窗口(传入窗口id)
			function closewicket(c){
				$("#"+c).dialog('close');
			}
			
			//去左空格;   
			 function ltrim(s){     
			    return s.replace(/(^\s*)/, "");  
			 }   
			 //去右空格;   
			function rtrim(s){   
			  return s.replace(/(\s*$)/, "");  
			}   
			 //去左右空格;   
			 function trim(s){  
			  return rtrim(ltrim(s));   
			 }
				
			////调用DWR验证userCode是否重复以及转换大写
			function getYanZhenUserCodeFunction(textid,valueUser){
				valueUser=trim(valueUser.toUpperCase());//转换大写并且去掉前后空格
				$("#"+textid).val(valueUser);
				if(/[^\x00-\xff]/g.test(valueUser)){
				 	alert( "含有汉字不能保存");
				 	$("#"+textid).val(null);
				}else {
					if(valueUser!=null&&valueUser!=""){
						ReportTypeManager.getYanZhentypeCode(valueUser,function(boole){
							if(boole){
								$("#"+textid).val(null);
								alert("该编码已存在、请审核后重新填写！");
							}
						});
					}
				}
				
			}
			//保存方法
			function savereportType(){
				 if(!$('#printTemplateForm').form('validate')){alert("请正确填写后信息好在进行保存!");return;}
				 saveButton("D");
					 var TemplateTypeManagerData=$('#printTemplateForm').form('getData');
					 TemplateTypeManager.saveyanzhen(TemplateTypeManagerData,function(result){
						 if(result){
						 	  $.messager.alert("提示", "操作成功", "info");
						 	  $('#printTemplateForm').form('clear');
						 	  closewicket('printTemplate');
						 	  saveButton("E");
						 	  query();
						 }else{
						 	   $.messager.alert(result.error);
						 	   saveButton("E");
						 }
				 });
			}
			//生效
			function submitButtonCommit(){
			var selDate=$('#PrintTemplateDatagrid').datagrid('getSelected');
		    var templateTypeUuid=selDate.templateTypeUuid;
			if(templateTypeUuid){
				$.messager.confirm("提示", "确认生效？", function(b) {
					if(b){
						userdatagirdStatus("<%=EC_CommonUtil.Active%>");
					}
				});
			}else{
				$.messager.alert("提示", "请选择一条数据后在进行操作!", "info");
			}
		}
		//作废
		function cancelSubmitButton(){
		var selDate=$('#PrintTemplateDatagrid').datagrid('getSelected');
		 var templateTypeUuid=selDate.templateTypeUuid;
			if(templateTypeUuid){
				$.messager.confirm("提示", "确认作废?", function(b) {
						if(b){
							userdatagirdStatus("<%=EC_CommonUtil.Cancel%>");
						}
					});
			}else{
				$.messager.alert("提示", "请选择一条数据后在进行操作!", "info");
			}
		}
		
		//修改状态。需要传送的值不为空必须为状态
		function userdatagirdStatus(status){
		var selDate=$('#PrintTemplateDatagrid').datagrid('getSelected');
		var templateTypeUuid=selDate.templateTypeUuid;
			if(templateTypeUuid==null){
				alert('请选择一条数据后在进行操作!');
			}else{
				TemplateTypeManager.updateSysStatusOue(templateTypeUuid,status,"TEMPLATE_TYPE","STATUS","TEMPLATE_TYPE_UUID",function(data){
					if(data){
						$.messager.alert("提示", "操作成功", "info");
						query();
					}else{
						$.messager.alert("提示", data.error, "info");
					}
				});
			}	
		}
		function deleteBtton(){
		var PrintDate=$('#PrintTemplateDatagrid').datagrid('getSelected');
		if(PrintDate){
			if(PrintDate.status=="<%=EC_CommonUtil.Pending%>"){
				TemplateTypeManager.removeType(PrintDate.templateTypeUuid,PrintDate.recVer,function(result){
							 if(result){
							 	  $.messager.alert("提示", "操作成功", "info");
							 	  $('#printTemplateForm').form('clear');
							 	  query();
							 }else{
							 	   $.messager.alert(result.error);
							 }
					 });
			}else{
			     $.messager.alert("提示", "生效,作废的数据不能删除!", "info");
			}
		}else{
			 $.messager.alert("提示", "请选择一条数据进行操作!", "info");
		}
		
		}	
		//编辑
		function editPlanTypeButton(){
			var PrintDate=$('#PrintTemplateDatagrid').datagrid('getSelected');
			if(PrintDate){
			     TemplateTypeManager.get(PrintDate.templateTypeUuid,function(result){
			      $('#printTemplate').dialog({
					modal:true
			   		});
			      $('#printTemplateForm').form('setData',result);   
			     });
			}else{
				 $.messager.alert("提示", "请选择一条数据进行操作!", "info");
			}
		}
		
		//按钮控制 
		function saveButton(dOe){
			if(dOe!=null&&dOe=="D"){
				$("#saveDivButton").linkbutton("disable").unbind("click");
				$("#closeDivButton").linkbutton("disable").unbind("click");
			}else if(dOe!=null&&dOe=="E"){
				$("#saveDivButton").linkbutton("enable").bind("click");
				$("#closeDivButton").linkbutton("enable").bind("click");
			}
		}
		
		////调用DWR验证userCode是否重复以及转换大写
			function getYanZhenPrintTemplateFunction(textid,valueUser){
				valueUser=trim(valueUser.toUpperCase());//转换大写并且去掉前后空格
				$("#"+textid).val(valueUser);
				if(/[^\x00-\xff]/g.test(valueUser)){
				 	alert( "含有汉字不能保存");
				 	$("#"+textid).val(null);
				}else {
					if(valueUser!=null&&valueUser!=""){
						TemplateTypeManager.getYanZhentypeCode(valueUser,function(boole){
							if(boole){
								$("#"+textid).val(null);
								alert("该编码已存在、请审核后重新填写！");
							}
						});
					}
				}
			}
		
	</script>
	</head>
	<body class="easyui-layout">
		<div region="north" border="false" iconCls="myCustomerIcon_searchForm"
			title="查询条件" style="height: 150px">
			<div class="datagrid-toolbar">
				<a class="easyui-linkbutton" iconCls="icon-search" onclick="query();">查询</a>
				<a class="easyui-linkbutton" iconCls="myCustomerIcon_clear" onclick="clearTriggerForm();">重置</a>
				
			</div>
			<div id="dataRelationMappingDiv">
				<form id="ChaprintTemplateForm" class="easyui-form">
					<table id="ChadataRelationMappingTalble" width="880px"  style="width: auto; margin: 0px; padding: 2px 30px;">
						<tr style="width:auto;height:auto;">
							<td align="right" width="80px">模板类型代码: </td>
							<td> <input id="templateTypeCodeC" name="templateTypeCode" /></td>
							<td align="right" width="100px">模板类型名称: </td>
							<td> <input id="templateNameC" name="templateName" /></td>
							<td align="right" width="100px">模板类型英文名: </td>
							<td> <input id="templateNameEnC" name="templateNameEn" /></td>
							<td align="right" width="60px">状态: </td>
						    <td><input id="statusC"  panelHeight="auto"  name="status" class="easyui-combobox" style="width: 75px;" editable=false />
						  </td>
						</tr>
				</table>
			</form>
		</div>
	</div>
	
	<div region="center" border="false">
		<div class="easyui-tabs" fit="true" ><!-- plain="true" -->
			<div title="打印模板类型管理列表">
				<div class="easyui-layout" fit="true">
				<div region="north" border="false">
							<div class="myCustomer_toolbar"  >
								<a id="addPlanTypeBtn" href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" style="float: left" onclick="appendRow()">新增</a>
									<div class="datagrid-btn-separator"></div>
								<a id="savePlanTypeBtn" href="#" class="easyui-linkbutton" plain="true" iconCls="icon-edit" style="float: left" onclick="editPlanTypeButton()">编辑</a>
									<div class="datagrid-btn-separator"></div>
								<a id="deletePlanTypeBtn" class="easyui-linkbutton" href="#" iconCls="myCustomerIcon_remove" plain="true" style="float: left" onclick="deleteBtton()">删除</a>
									<div class="datagrid-btn-separator"></div>
								<a id="summitPlanTypeBtn" class="easyui-linkbutton" href="#" plain="true" style="float: left;" iconCls="icon-ok" onclick="submitButtonCommit()">生效</a>
									<div class="datagrid-btn-separator"></div>
								<a id="cancelPlanTypeBtn" class="easyui-linkbutton" href="#" plain="true" style="float: left;" iconCls="myCustomerIcon_cancel" onclick="cancelSubmitButton()">作废</a>
								<div class="datagrid-btn-separator"></div>
							</div>
						</div>
					<div region="center" border="false" >
						<table  id="PrintTemplateDatagrid" class="easyui-datagrid" fit="true" fitColumns="true" rownumbers="true" singleSelect="true"  >
							<thead>
								<tr>
									<th field="status" title="状态" width="40" align="center" formatter=chincesStatus></th>
									<th field="templateTypeCode" title="模板类型代码" width="100" align="left"  ></th>
									<th field="templateName" title="模板类型名称" width="100" align="left"  ></th>
									<th field="templateNameEn" title="模板类型英文名" width="100" align="left" ></th>
									<th field="remark" title="备注" width="100" align="left" ></th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 弹出框 -->
		<div id="printTemplate"
			style="padding: 5px; width: 670px; height: 180px;" title="打印模板类型管理">
			<form name="printTemplateForm" id="printTemplateForm">
				<div>
					<table id="tableprintTemplate" style="width: 100%;">
						<tr>
						    <td align="right"><font color="red">*</font>模板类型代码:</td>
						    <td><input type="text" id="templateTypeCode" name="templateTypeCode"  class="easyui-validatebox"  onchange="getYanZhenPrintTemplateFunction(this.id,this.value)" style="TEXT-TRANSFORM:uppercase;ime-mode:disabled" required="true"/>
							    <input type="hidden" id="templateTypeUuid" name="templateTypeUuid"/>
							    <input type="hidden" id="recVer" name="recVer" />
							     <input type="hidden" id="status" name="status" value="Pending" />
							     <input type="hidden" id="officeCode" name="officeCode"/>
						    </td>
						    <td  align="right"><font color="red">*</font>模板类型名称:</td>
							    <td><input type="text" id="templateName" name="templateName"  class="easyui-validatebox"  required="true"/></td>
						</tr>
						<tr>
						    <td  align="right">模板类型英文名称:</td>
						    <td><input type="text" id="templateNameEn"  class="easyui-validatebox"  name="templateNameEn"/>
						    </td>
						 	<td  align="right">备注:</td>
						    <td> <input type="text" id="remark"  class="easyui-validatebox"  name="remark"/></td>
						</tr>
						<tr>
							
						</tr>
						<!-- 分割线 -->
						<tr>
							<td colspan="4">
								<hr/>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<a href="#" id="saveDivButton" class="easyui-linkbutton" name="saveDivButton"
									onclick="savereportType()">保存</a>
								<a href="#" class="easyui-linkbutton" id="closeDivButton"
									onclick="closewicket('printTemplate')">关闭</a>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>