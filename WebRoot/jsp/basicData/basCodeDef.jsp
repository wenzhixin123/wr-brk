<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.Locale"%>
<%
	String path = request.getContextPath();
%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html:html>
  <head>
  
    <title>数据字典类型</title>
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
		var text_newBtn='<bean:message bundle="wlp.common" key="global.add"/>';
		var text_delBtn='<bean:message bundle="wlp.common" key="global.del"/>';
		var text_saveBtn='<bean:message bundle="wlp.common" key="global.save"/>';
		var text_returnBtn='<bean:message bundle="wlp.common" key="global.undo"/>';
		var text_validateBtn='<bean:message bundle="wlp.common" key="global.active"/>';
		var text_cancelBtn='<bean:message bundle="wlp.common" key="global.invalidate"/>';
		var text_importBtn='<bean:message bundle="wlp.common" key="global.excelImport"/>';
		var text_exportBtn='<bean:message bundle="wlp.common" key="global.excelExport"/>';
		//与model相关的字段定义
		var p_modelUuId="basCodeTypeUuid";
		var request_url="<%=request.getContextPath()%>"+"/servlet/SinotransServlet?cmd=saveBasCodeType";
		//唯一性校验的全局依赖字段和函数定义
		var v_global_exitsCodes=new Array();
		function fn_validate_isUnique(modelName){
			$.ajax({
              type: "POST",
              url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&oc=true&queryType="+modelName+"&rows="+1000+"",
              dataType:"json",
              success: function(result){
              	  $(result.rows).each(function(){
              	  	v_global_exitsCodes.push(this.typeCode);
              	  });
              }
          	});
		}
		//初始化
		fn_validate_isUnique("BasCodeTypeModel");
	</script>
	<script type="text/javascript" src="../../js/util/wlp_baseCRUD.js"></script>
	
	<script type="text/javascript">
	var lastIndex1;
	var tempArray=new Array(); //存放临时model的数组对象
	$(function(){
		BaseCRUD('datagird',{
			modelUuId:p_modelUuId,
			interceptor:function(options,easyUIFunc){
				//options.singleSelect=true;
				for(var i=0; i<3;i++){
					options.toolbar.pop();
				}
				
				//删除按钮
				options.toolbar[2].handler=function(){
					//生效的时候不允许删除
						var _rows=$("#datagird").datagrid('getAllSelected');
						var _isActive=false;
						$.each(_rows,function(){
							if(this.status=="Active"||this.status=="Cancel"){
								_isActive=true;
								return false;
							}
						});
						if(_isActive){
							alert('已生效或已作废的记录不能够删除!');
							return;
						}
						if (_rows.length>0){
							$.messager.confirm('确定框', '确定删除'+_rows.length+'条记录吗?', function(r){
									if (r){
										//缓冲删除的数据
										if (_rows.length>0) {
												for ( var j = 0; j < _rows.length; j++) {
													_rows[j]._class=undefined;
													_rows[j].createTime=undefined;
													_rows[j].modifyTime=undefined;
													_rows[j].rowState="Deleted";
													_rows[j].control_Word=undefined;
													if(notNullFieldsObj!=undefined){
														$.extend(_rows[j],notNullFieldsObj);
													}
													tempArray.push(_rows[j]);
												}
										}
										
										var jsonResult= $.toJSON(tempArray);
										LogisticsOrderManager.object2base64(jsonResult,function(data){
										if(data){										
												//使用ajax提交到服务器保存数据
												$.ajax({
													type: "POST",
												    url: request_url,
												    data:"jsonResult="+data+"",
												    success: function(result){
												    		var rs = $.parseJSON(result);
												   	 		if(rs&&rs.error){
												    			alert('执行删除时发生错误:'+rs.error);
												    			$("#datagird").datagrid('rejectChanges');
												    			return;
								    						}
													    	var model = p_modelUuId.split('Uuid');
													   		model=model[0].substring(0,1).toUpperCase()+model[0].substring(1)+"Model";
													   		v_global_exitsCodes=[];
														    if(fn_validate_isUnique){
										   						fn_validate_isUnique(model);
										   					}
													    	if(tempArray.length>0){
													    		alert('删除成功');
													    		//执行删除
																$(tempArray).each(function(){
																	$("#datagird").datagrid('deleteRow',$("#datagird").datagrid('getRowIndex',this));
																});
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
										});
									}
							});
						}else{
							 alert("请选择所要删除的行！");
						}
				};
				
				//保存按钮
				options.toolbar[4].handler=function(){
						$("#text_saveBtn").linkbutton("disable").unbind("click");
						var validated= false;
						if($("#datagird").data('lastIndex')){
							var editRowsIndex=$("#datagird").datagrid('getEditRowsIndex');
							$.each(editRowsIndex,function(){
								if(!$("#datagird").datagrid('validateRow',this)){
									validated=true;
								}
							});
						}
						if(validated){
							$("#text_saveBtn").linkbutton("enable").unbind("click").bind("click");
							alert("验证失败:请正确填写所输入的数据");
							return;
						}
						//若结束所有编辑
						$("#datagird").datagrid('endAllEdits');
						//$("#"+dataGridId).datagrid('endEdit',$('#'+dataGridId).data('lastIndex'));	
						var selectedRow = $("#datagird").datagrid('getSelected');
						var data = $("#datagird").datagrid('getData');
						var updateRows = $("#datagird").datagrid('getChanges','updated');  //得到修改的数据
						var deleteRows = $("#datagird").datagrid('getChanges','deleted');  //得到删除的数据
						var insertRows = $("#datagird").datagrid('getChanges','inserted'); //得到新增的数据
		
						//保存修改的数据 
						if (updateRows.length>0) {
							for ( var i = 0; i < updateRows.length; i++) {
								var row = updateRows[i];
								updateRows[i]._class=undefined;
								updateRows[i].createTime=undefined;
								updateRows[i].modifyTime=undefined;
								updateRows[i].rowState="Modified";
								if(selectedRow.controlWord){
									updateRows[i].controlWord = updateRows[i].control_Word + updateRows[i].controlWord.substring(1);
								}else{
									updateRows[i].controlWord = updateRows[i].control_Word;
								}
								updateRows[i].control_Word = undefined;
								if(notNullFieldsObj!=undefined){
									$.extend(updateRows[i],notNullFieldsObj);
								}
								tempArray.push(updateRows[i]);
								
							}
						}
				
						//保存删除的数据
						if (deleteRows.length>0) {
								for ( var j = 0; j < deleteRows.length; j++) {
									deleteRows[j]._class=undefined;
									deleteRows[j].createTime=undefined;
									deleteRows[j].modifyTime=undefined;
									deleteRows[j].rowState="Deleted";
									if(deleteRows[j].controlWord){
										deleteRows[j].controlWord = deleteRows[j].control_Word + deleteRows[j].controlWord.substring(1);
									}else{
										deleteRows[j].controlWord = deleteRows[j].control_Word;
									}
									deleteRows[j].control_Word = undefined;
									if(notNullFieldsObj!=undefined){
										$.extend(deleteRows[j],notNullFieldsObj);
									}
									tempArray.push(deleteRows[j]);
								}
						}
				
						//保存新增的数据
						$("#datagird").datagrid('acceptChanges');
						$.each(insertRows,function(k,v){
						if(v[p_modelUuId]==undefined){ //新增
							v._class=undefined;
							v.createTime=undefined;
							v.modifyTime=undefined;
							if(v.controlWord){
								v.controlWord = v.control_Word + v.controlWord.substring(1);
							}else{
								v.controlWord = v.control_Word;
							}
							v.control_Word = undefined;
							v.rowState="Added";
							//必输字段的控制,约定优于配置,基础数据的所有必输字段都排在第2位,所以用fields[1]来取得
							var fields=$("#datagird").datagrid('getColumnFields');
							var needField=fields[3];
							if(v[needField]!=undefined){
								if(notNullFieldsObj!=undefined){
									$.extend(v,notNullFieldsObj);
								}
								tempArray.push(v);
							}
						}
					}); 
						
					var jsonResult= $.toJSON(tempArray);
					if(tempArray.length<=0){
						$("#text_saveBtn").linkbutton("enable").unbind("click").bind("click");
						return;
					}
					LogisticsOrderManager.object2base64(jsonResult,function(data){
					if(data){						
							//使用ajax提交到服务器保存数据
							$.ajax({
								type: "POST",
							    url: request_url,
							    data:"jsonResult="+data+"",
							    success: function(result){
							           	$("#text_saveBtn").linkbutton("enable").unbind("click").bind("click");
							    		var rs = $.parseJSON(result);
							   	 		if(rs&&rs.error){
							    			alert('执行保存时发生错误:'+rs.error);
							    			$("#datagird").datagrid('rejectChanges');
							    			return;
										}
								    	var model = p_modelUuId.split('Uuid');
								   		model=model[0].substring(0,1).toUpperCase()+model[0].substring(1)+"Model";
								   		v_global_exitsCodes=[];
								   		if(fn_validate_isUnique){
								   			fn_validate_isUnique(model);
								   		}
								    	if(tempArray.length>0){
								    		alert('保存成功');
								    	}
								    	tempArray=[];//清空数组
								    	$("#datagird").datagrid({});
								    	$("#datagird").datagrid("clearSelections");
									},
								error:function (XMLHttpRequest, textStatus, errorThrown){
									$("#text_saveBtn").linkbutton("enable").unbind("click").bind("click");
									alert("Error:"+XMLHttpRequest+"\n"+textStatus+"\n"+errorThrown);
								}
							});
						 }
					});
				};
				
				//生效按钮
				options.toolbar[8].handler=function(){
						var validated= false;
						if($("#datagird").datagrid('getEditRowsIndex').length>0){
							var editRowsIndex=$("#datagird").datagrid('getEditRowsIndex');
							$.each(editRowsIndex,function(){
								if(!$("#datagird").datagrid('validateRow',this)){
									validated=true;
								}
							});
						}
						if(validated){
							alert("验证失败:请输入必输项!");
							return;
						}
							
						$("#datagird").datagrid('endAllEdits');
						var rows = $("#datagird").datagrid('getAllSelected');
						if(rows.length==0){
							alert("请至少选择一条记录!");
							return;
						}
						var _opts = $("#datagird").data('_time_201111271153_options');
						var flag=true;
						var flag1=false;
						$.each(rows,function(){
								if(this.status!="Active"){
									flag=false;
								}
			//					if(this.status=="Cancel"){
			//						alert("作废的记录不能够再生效");
			//						flag1=true;
			//						return false;
			//					}
							});
						if(flag){
							alert("所选记录都已生效");
							return;
						}
						if(flag1){
							return;
						}
						if(rows.length>0){
							$.messager.confirm('确定框', '确定生效'+rows.length+'条记录吗?', function(r){
								if(r){
									$.each(rows,function(i,v){
										//生效操作,设置状态为生效
										this.status='Active';
										var selectedRow = this;
										var index = $("#datagird").datagrid('getRowIndex',this);
										if(selectedRow._class){
											selectedRow.rowState="Modified";
										}else{
											selectedRow.rowState="Added";
										}
										selectedRow._class=undefined;
										selectedRow.createTime=undefined;
										selectedRow.modifyTime=undefined;
										if(selectedRow.controlWord && selectedRow.control_Word!=undefined){
											selectedRow.controlWord = selectedRow.control_Word + selectedRow.controlWord.substring(1);
										}
										selectedRow.control_Word = undefined;
										tempArray.push(selectedRow);
										$("#datagird").datagrid('refreshRow',index);
									});
									var jsonResult = $.toJSON(tempArray);
									LogisticsOrderManager.object2base64(jsonResult,function(data){
									if(data){									
											//使用ajax提交到服务器保存数据
											$.ajax({
												type: "POST",
											    url: request_url,
											    data:"jsonResult="+data+"",
											    success: function(result){
											    		var rs = $.parseJSON(result);
											   	 		if(rs&&rs.error){
											    			alert('执行生效时发生错误:'+rs.error);
											    			$("#datagird").datagrid('rejectChanges');
											    			return;
							    						}
												    	var model = p_modelUuId.split('Uuid');
												   		model=model[0].substring(0,1).toUpperCase()+model[0].substring(1)+"Model";
												   		v_global_exitsCodes=[];
												   		if(fn_validate_isUnique){
									   						fn_validate_isUnique(model);
									   					}
												    	if(tempArray.length>0){
												    		alert('生效成功');
												    	}
												    	tempArray=[];//清空数组
												    	$("#datagird").datagrid("reload");
												    	$("#datagird").datagrid("clearSelections");
													}
											});
										}
									});
								}
							});
						}else{
							alert("请选择要生效的记录！");			
						}
						return;
				};
				
				//作废按钮
				options.toolbar[10].handler=function(){
						if($("#datagird").datagrid('getEditRowsIndex').length>0){
							alert("保存后才能进行作废操作!");
							return;
						}
						var validated=false;
						if($("#datagird").datagrid('getEditRowsIndex').length>0){
							var editRowsIndex=$("#datagird").datagrid('getEditRowsIndex');
							$.each(editRowsIndex,function(){
								if(!$("#datagird").datagrid('validateRow',this)){
									validated=true;
								}
							});
						}
						if(validated){
							alert("验证失败:请输入必输项!");
							return;
						}
						$("#datagird").datagrid('endAllEdits');
						var rows = $("#datagird").datagrid('getAllSelected');
						var _opts = $("#datagird").data('_time_201111271153_options');
						if(rows.length==0){
							alert("请至少选择一条记录!");
							return;
						}
						var flag;
						if(rows.length==1){
							if(rows[0].status=="Cancel"){
								alert("该记录已作废!");
								flag = true; 
								return;
							}
						}
						
						if(rows.length>0){
							var flag=false;
							$.each(rows,function(){
								if(this.status=="Pending"){
									flag=true;
								}
							});
							if(flag){
								alert("生效的记录才能作废!");
								return;
							}
							$.messager.confirm('确定框', '确定作废这'+rows.length+'条记录吗?', function(r){
								if(r){
									$.each(rows,function(i,v){
										//作废操作,设置状态为作废
										this.status='Cancel';
										var selectedRow = this;
										var index = $("#datagird").datagrid('getRowIndex',this);
										selectedRow._class=undefined;
										selectedRow.createTime=undefined;
										selectedRow.modifyTime=undefined;
										selectedRow.rowState="Modified";
										if(selectedRow.controlWord && selectedRow.control_Word!=undefined){  //修改控制字段
											selectedRow.controlWord = selectedRow.control_Word + selectedRow.controlWord.substring(1);
										}
										selectedRow.control_Word = undefined;
										tempArray.push(selectedRow);
										$("#datagird").datagrid('refreshRow',index);
									});
									var jsonResult = $.toJSON(tempArray);
									LogisticsOrderManager.object2base64(jsonResult,function(data){
									if(data){										
											//使用ajax提交到服务器保存数据
											$.ajax({
												type: "POST",
											    url: request_url,
											    data:"jsonResult="+data+"",
											    success: function(result){
											    		var rs = $.parseJSON(result);
											   	 		if(rs&&rs.error){
											    			alert('执行作废时发生错误:'+rs.error);
											    			$("#datagird").datagrid('rejectChanges');
											    			return;
							    						}
												    	var model = p_modelUuId.split('Uuid');
												   		model=model[0].substring(0,1).toUpperCase()+model[0].substring(1)+"Model";
												   		v_global_exitsCodes=[];
												    	if(fn_validate_isUnique){
									   						fn_validate_isUnique(model);
									   					}
												    	if(tempArray.length>0){
												    		alert('作废成功');
												    	}
												    	tempArray=[];//清空数组
												    	$("#datagird").datagrid("reload");
												    	$("#datagird").datagrid("clearSelections");
													}
											});
										}
									});
								}
							});
						}else{
							alert("请选择要作废的记录！");			
						}
						return;
					};
					
			//双击事件	
			 options.onDblClickRow=function(rowIndex,row){
			    var controlword = row.controlWord.substring(0,1);
			    
				 if (lastIndex1 != rowIndex){
					var dataGridId= 'datagird';
					$("#"+dataGridId).datagrid('endEdit', lastIndex1);
					$("#"+dataGridId).datagrid('beginEdit', rowIndex);
					var editors = $("#datagird").datagrid('getEditors',rowIndex);
			        editors[6].target.combobox('setValue',controlword);
				    $("#"+dataGridId).datagrid('clearSelections');
					$('#'+dataGridId).data('lastIndex',rowIndex);
					$("#"+dataGridId).datagrid('selectRow', rowIndex);
					
					var _i = 2;
					var fields=$("#"+dataGridId).datagrid('getColumnFields',false);
					var currentModifyRow=$(' .datagrid-view2 tr').filter('.datagrid-row-selected').filter('.datagrid-row-editing');
					$("tr[datagrid-row-index="+rowIndex+"] td[field="+fields[_i]+"] input",$(".datagrid-view2 .datagrid-body")).removeClass("validatebox-text validatebox-invalid").hide();
					var text=$("td[field="+fields[_i]+"] div",$(".datagrid-view2 .datagrid-body")).get(rowIndex);
					$("td",text).append(row[fields[_i]]);
				}
				lastIndex1 = rowIndex;
			 
			}
			
			}
		},request_url);
	});
	// 查询按钮和清空按钮的click事件
	$(function(){
		$('#searchBtn').unbind("click").click(function(){ 
			var fieldObj=fn_jsUtil_getSearchFormObj('queryForm');
            $("#datagird").datagrid('load',fieldObj);
    	});

    	$('#resetButton').unbind("click").click(function(){
       		$('#queryForm').form('clear');
       		$("#status").combobox("setValue","");
        });
	});
	
	//设置查询表单的样式
	$(function(){
		
		$("#queryForm").css({
				width:"99.6%",
				heigh:"99%",
				margin:"2px 1px 2px 2px"
			});
			
		$("#queryForm td:even").css({
				width:"9%",
				border:"1px,soild,red"
			});
			
		$(".textStyle").css({
			"font-size": "12px",
			"text-align":"right"
		});
		$("#queryForm td:odd").css({
		});
		
		$("#queryForm td:odd:even").css({
			width:"20%"
		});
		$("#queryForm td:even:odd").css({
			width:"11%"
		});
	});
	
	//类型 
	function if_configure(a,value,c){
		if(value.controlWord!=undefined){
	    	  var control_word = value.controlWord;
	    	  var cword = control_word.substring(0,1);
	    	  if(cword=="U") { 
	    	  	 return "用户";
	    	   }
	    	  if(cword=="0") {
	    	  	return "系统";
	    	  }
    	 }
	}
	
	</script>
	
	
  </head>
  
  <body class="easyui-layout">
	<div region="north" border="false" iconCls="myCustomerIcon_searchForm" title="<bean:message bundle="wlp.common" key="global.searchTitle"/>" style="height:150px;">
		<%--按钮--%>
		<div class="myCustomer_toolbar">
			<a href="#" id="searchBtn" class="easyui-linkbutton"
					plain="true" iconCls="icon-search" style="float: left"><bean:message bundle="wlp.common" key="global.search"/></a>
			<div class="datagrid-btn-separator"></div>
			<a href="#" id="resetButton" class="easyui-linkbutton"
					plain="true" iconCls="myCustomerIcon_clear" style="float: left"><bean:message bundle="wlp.common" key="global.clear"/></a>
			<div class="datagrid-btn-separator"></div>
		</div>
		<%--查询表单--%>
		<div id="formContainer" >
			<form id="queryForm">
				<table id="searForm" width="880px" style="margin:0;padding:0;">
					<tr>
						<td align="center" td_align="left" class="textStyle">
							<label for="typeCode">
								<bean:message bundle="wlp.basicdata" key="BasCodeType.typeCode"/>:
							</label>
						</td>
						<td>
							<input id="typeCode" name="typeCode"  class="easyui-validatebox" validType='noChinese'/>
						</td>
						<td align="center" td_align="left" class="textStyle">
							<label for="typeName" >
								<bean:message bundle="wlp.basicdata" key="BasCodeType.typeName"/>:
							</label>
						</td>
						<td>
							<input id="typeName" name="typeName" class="easyui-validatebox"  />
						</td>
					</tr>
					<tr>
						<td align="center" td_align="left" class="textStyle">
							<label for="status" >
								<bean:message bundle="wlp.basicdata" key="BasCodeType.status"/>:
							</label>
						</td>
						<td>
							<select id="status" name="status" style="width:50px">
								<option value="">==请选择==</option>
								<option value="Active">生效</option>
								<option value="Pending">草稿</option>
								<option value="Cancel">作废</option>
							</select>
						</td>
						<td  align="center" td_align="left" class="textStyle">
							<label for="dataType" >
								<bean:message bundle="wlp.basicdata" key="BasCodeType.dataType"/>:
							</label>
						</td>
						<td >
							<input id="dataType" name="dataType"   value="" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div region="center" border="false">
		<table id="datagird" title="<bean:message bundle="wlp.basicdata" key="editTitle"/>" iconCls="icon-edit"
			url="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&oc=true&queryType=BasCodeTypeModel" fit="true">
			<thead>
				<tr>
					<th field="status" formatter='fn_global_setBasicdataState'  width="40"  align="center" td_align="left" >
						<bean:message bundle="wlp.basicdata" key="BasCodeType.status"/>
					</th>
					<th field="typeCode"  width="180" align="center" td_align="left"
						editor="{type:'validatebox',options:{required:true,validType:'isUnique'}}">
						<bean:message bundle="wlp.basicdata" key="BasCodeType.typeCode"/>
					</th>
					<th field="typeName" width="160"  align="center" td_align="left" editor="{type:'validatebox',options:{required:true}}">
						<bean:message bundle="wlp.basicdata" key="BasCodeType.typeName"/>
					</th>
					<th field="typeWidth" width="80"  align="center" td_align="left" editor="numberbox">
						<bean:message bundle="wlp.basicdata" key="BasCodeType.typeWidth"/>
					</th>
					<th field="dataType" width="100"  align="center" td_align="left"	editor="validatebox">
						<bean:message bundle="wlp.basicdata" key="BasCodeType.dataType"/>
					</th>
					<th field="typeDesc" width="230" align="center" td_align="left"  editor="validatebox">
						<bean:message bundle="wlp.basicdata" key="BasCodeType.typeDesc"/>
					</th>
					<th field="remark" width="120"  align="center" td_align="left"	editor="validatebox">
						<bean:message bundle="wlp.basicdata" key="BasCodeType.remark"/>
					</th>
					<th field="control_Word" width="80"  align="center" td_align="center"	
						editor="{type:'combobox',options:{
								panelHeight:46,
								editable:false,
								data:[{modifyText:'用户',value:'U'},{modifyText:'系统',value:'0'}],valueField:'value',textField:'modifyText'}}"
						formatter="if_configure">
						类型
					</th>
				</tr>
			</thead>
		</table>
	</div>
  </body>
</html:html>
