<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.Locale"%>
<%@page import="com.sinotrans.gd.wms.util.CommonUtil"%>
<%@page import="com.sinotrans.gd.wms.system.entity.SessionContextUserEntity"%>
<%
	String path = request.getContextPath();
	SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html:html>
  <head>
  
    <title>货位与物料种类</title>
    
    <jsp:include page="../../common/imp_dwr.jsp"></jsp:include>
	<!-- jquery easyui -->
	<jsp:include page="../../common/imp_easyui.jsp"></jsp:include>
	<link rel="stylesheet" href="../../css/system.css" type="text/css"></link>
	<style type="text/css">
		body {
			background:white;
		}

	</style>
	<script type="text/javascript">
		//按钮的国际化字段定义
		var text_newBtn='<bean:message bundle="wms.common" key="global.add"/>';
		var text_delBtn='<bean:message bundle="wms.common" key="global.del"/>';
		var text_saveBtn='<bean:message bundle="wms.common" key="global.save"/>';
		var text_returnBtn='<bean:message bundle="wms.common" key="global.undo"/>';
		var text_validateBtn='<bean:message bundle="wms.common" key="global.active"/>';
		var text_cancelBtn='<bean:message bundle="wms.common" key="global.invalidate"/>';
		var text_importBtn='<bean:message bundle="wms.common" key="global.excelImport"/>';
		var text_exportBtn='<bean:message bundle="wms.common" key="global.excelExport"/>';
		//与model相关的字段定义
		var fn_validate_isUnique;
		var p_modelUuId="locItemRefUuid";
		var request_url="<%=request.getContextPath()%>"+"/servlet/SinotransServlet?cmd=saveLocItemRef";
	</script>
	<script type="text/javascript" src="../../js/util/wms_baseCRUD.js"></script>
	<script type="text/javascript" src="../../js/util/wms_combogrid.js"></script>
	<script type="text/javascript">
	$(function(){
		BaseCRUD('datagird',{
			modelUuId:p_modelUuId,
			interceptor:function(options,easyUIFunc){
			
				for (var i = 0; i < 7; i++) {
					options.toolbar.pop();
				}
				
				//双击事件
				options.onDblClickRow=function(rowIndex,rowData){
				 	var dataGridId = "datagird";
					$("#"+dataGridId).datagrid('endEdit', $('#'+dataGridId).data('lastIndex'));
					$("#"+dataGridId).datagrid('beginEdit', rowIndex);
					$("#"+dataGridId).datagrid('clearSelections');
					$('#'+dataGridId).data('lastIndex',rowIndex);
					$("#"+dataGridId).datagrid('selectRow', rowIndex);
				}
				
				//删除按钮
				options.toolbar[2].text=text_delBtn;
				options.toolbar[2].iconCls="myCustomerIcon_remove";
				options.toolbar[2].handler=function(){
						var dataGridId="datagird";
						var _rows=$("#"+dataGridId).datagrid('getSelections');
						if (_rows.length>0){
							$.messager.confirm('确定框', '确定删除'+_rows.length+'条记录吗?', function(r){
									if (r){
										var tempArray=[];
										$.each(_rows,function(i,v){
											v._class=undefined;
											v.createTime=undefined;
											v.modifyTime=undefined;
											v.rowState="Deleted";
											v.status=undefined;
											v.locAreaName=undefined;
											v.itemKindName=undefined;
											v.locItemRefModel=undefined;
											tempArray.push(v);
										});
										var jsonResult= $.toJSON(tempArray);
										//使用ajax提交到服务器保存数据
										$.ajax({
											type: "POST",
										    url: "<%=request.getContextPath()%>"+"/servlet/SinotransServlet?cmd=saveLocItemRef",
										    data:"jsonResult="+jsonResult+"",
										    success: function(result){
										    		var rs = $.parseJSON(result);
										   	 		if(rs&&rs.error){
										    			error('执行删除时发生错误:'+rs.error);
										    			$("#"+dataGridId).datagrid('rejectChanges');
										    			return;
						    						}	
											    	if(tempArray.length>0){
											    		alert('删除成功');
											    		//执行删除
														$(tempArray).each(function(){
															$("#"+dataGridId).datagrid('deleteRow',$("#"+dataGridId).datagrid('getRowIndex',this));
														});
											    	}
											    	tempArray=[];//清空数组
											    	$("#"+dataGridId).datagrid({});
											    	$("#"+dataGridId).datagrid("clearSelections");
												},
											error:function (XMLHttpRequest, textStatus, errorThrown){
												alert("Error:"+XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
											}
										});
										
									}
							});
						}else{
							 alert("请选择所要删除的行！");
						}
				
				
				};
								
				//保存按钮
				options.toolbar[4].text=text_saveBtn;
				options.toolbar[4].iconCls="icon-save";
				options.toolbar[4].handler=function(){
					var tempArray =[];
					if($("#datagird").data('lastIndex')){
						if(!$("#datagird").datagrid('validateRow',$("#datagird").data('lastIndex'))){
							alert('请输入必输项');
							return;
						}
					}
					//若是编辑的则可以把该行取消编辑
					$("#datagird").datagrid('endAllEdits');	
					var selectedRow = $("#datagird").datagrid('getSelected');
					var insertRows = $("#datagird").datagrid('getChanges','inserted'); //得到新增的数据
					var updateRows = $("#datagird").datagrid('getChanges','updated');  //得到修改的数据
					//保存修改的数据 
					if (updateRows.length>0) {
						for ( var i = 0; i < updateRows.length; i++) {
							var row = updateRows[i];
							updateRows[i]._class=undefined;
							updateRows[i].createTime=undefined;
							updateRows[i].modifyTime=undefined;
							updateRows[i].rowState="Modified";
							updateRows[i].status=undefined;
							updateRows[i].locAreaName=undefined;
							updateRows[i].itemKindName=undefined;
							updateRows[i].locItemRefModel=undefined;
							tempArray.push(updateRows[i]);
						}
					}
					//保存新增的数据
					$("#datagird").datagrid('acceptChanges');
					$.each(insertRows,function(k,v){
						if(v[p_modelUuId]==undefined){ //新增
							v._class=undefined;
							v.createTime=undefined;
							v.modifyTime=undefined;
							v.rowState="Added";
							v.status=undefined;
							v.locAreaName=undefined;
							v.itemKindName=undefined;
							tempArray.push(v);
						}
					});
					
					//保存数据
					var jsonResult= $.toJSON(tempArray);
					//使用ajax提交到服务器保存数据
					$.ajax({
						type: "POST",
					    url: "<%=request.getContextPath()%>"+"/servlet/SinotransServlet?cmd=saveLocItemRef",
					    data:"jsonResult="+jsonResult+"",
					    success: function(result){
					    		var rs = $.parseJSON(result);
					   	 		if(rs&&rs.error){
					    			error('执行保存时发生错误:'+rs.error);
					    			$("#"+dataGridId).datagrid('rejectChanges');
					    			return;
	    						}	
						    	if(tempArray.length>0){
						    		alert('保存成功');
						    	}
						    	tempArray=[];//清空数组
						    	$("#datagird").datagrid({});
						    	$("#datagird").datagrid("clearSelections");
							},
						error:function (XMLHttpRequest, textStatus, errorThrown){
							alert("Error:"+XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
						}
					});
					
					
				}
			}
		},request_url);
	});
	// 查询按钮和清空按钮的click事件
	$(function(){
		$('#searchBtn').click(function(){ 
			var fieldObj=fn_jsUtil_getSearchFormObj('queryForm');
            $("#datagird").datagrid('load',fieldObj);
    	});

    	$('#resetButton').click(function(){
        		$('#queryForm').form('clear');
        		$("#status").combobox("setValue","");
        });
	});
	</script>
	
	
  </head>
  
  <body class="easyui-layout">
		<div id="gridContainer" region="center" border="false">
				<table id="datagird" title="<bean:message bundle="wms.basicdata" key="editTitle"/>" iconCls="icon-edit"	 fit="true"
					url="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=getLocItemRefJSONByEntity&queryType=LocItemRefModel&__condition=officeCode_=&__officeCode=<%=scue.getOfficeCode() %>">
					<thead>
						<tr>
							<th field="itemKindName"  width="125px" align="center" td_align="left"
								editor="{type: '_combogrid',options:{
								        required:true,
										url:'<%=request.getContextPath()%>'+'/servlet/SinotransServlet?cmd=query&queryType=ItemKindModel&__status=<%=CommonUtil.Active%>',
										idField:'itemKindName',
										textField:'itemKindName',
										value:'itemKindName',
										datagridId:'datagird',
										hiddenFields:{'itemKindUuid':'itemKindUuid'},
										displayField:['itemKindCode','itemKindName','itemKindNameEn'],
										displayTitle:'<bean:message bundle='wms.item' key='ItemKind.itemKindCode'/>,<bean:message bundle='wms.item' key='ItemKind.itemKindName'/>,<bean:message bundle='wms.item' key='ItemKind.itemKindNameEn'/>,状态'
									}}">
								<bean:message bundle="wms.basicdata" key="LocItemRef.itemKindName"/>
							</th>
							<th field="locAreaName" width="125px"  align="center" td_align="left" 
								editor="{type: '_combogrid',options:{
								            required:true,
											url:'<%=request.getContextPath()%>'+'/servlet/SinotransServlet?cmd=query&oc=true&queryType=BasLocAreaModel&__status=<%=CommonUtil.Active%>',
											idField:'locAreaName',
											textField:'locAreaName',
											value:'locAreaName',
											hiddenFields:{'basLocAreaUuid':'basLocAreaUuid'},
											datagridId:'datagird',
											displayField:['locAreaCode','locAreaName','locAreaNameEn'],
											displayTitle:'<bean:message bundle='wms.basicdata' key='BasLocArea.locAreaCode'/>,<bean:message bundle='wms.basicdata' key='BasLocArea.locAreaName'/>,<bean:message bundle='wms.basicdata' key='BasLocArea.locAreaNameEn'/>,状态'
										}}">
								<bean:message bundle="wms.basicdata" key="LocItemRef.locAreaName"/>
							</th>
						</tr>
					</thead>
				</table>
		 </div>
  </body>
</html:html>
