<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@page import="com.sinotrans.gd.wlp.system.entity.SessionContextUserEntity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<html:html>
<%
	String path = request.getContextPath();
	SessionContextUserEntity scue = SessionContextUserEntity.currentUser();
	String officeCode = SessionContextUserEntity.currentUser().getOfficeCode();
%>
<head>
	<jsp:include page="../../common/imp_dwr.jsp"></jsp:include>
	<jsp:include page="../../common/imp_easyui.jsp"></jsp:include>
	<script type="text/javascript"
		src="<%=path%>/js/ajaxfileupload.js"></script>
	<style type="text/css">
#tableQueryForm td {
	font-size: 12px;
}
</style>
</head>
	<!--  打印模版管理页面-->
	<script type="text/javascript">
				var editRowState = "";
				$(function(){
				_chushihuaCustomerCode();
				var lastIndex;
				queryTemplateType();
				
				$('#templateCategory').combobox({
				    data:[{
						value: 'FASTREPORT',
						text: 'FASTREPORT'
					},{
						value: 'IREPORT',
						text: 'IREPORT'
					}],
				    valueField:'value',
				    textField:'text'
				});
				
					$("#templatedatagird").datagrid(
						{
						height:420,
						pagination:true,
						striped:true,
						toolbar:[{
						  text:'新增',//新增按钮
						  iconCls:'icon-add',
						  handler:function(){
							  
							  
							  
							$("#newTemplate").show();
							$("#editTemplate").hide();
							$("#saveTemplate").show();
							$("#updateTemplate").hide();
							editRowState ="<%=CommonUtil.ROW_STATE_ADDED%>";
							$('#templateInfo').dialog({
								draggable:false,
								modal:true
							});
							findTemplateType();
							printExtraCondition();
							cleardata('templateInfoForm');
							$("#status").combobox("select","<%=CommonUtil.Active%>");
							$("#templateType").combobox("select","<%=CommonUtil.REPORT_PRINT_TYPE_BARP%>");
							$("#templateCategory").combobox("select","IREPORT");
							$("#templateCategorySelectTd").show();
							$("#templateCategoryDisplayTd").hide();
							//$("#trUploadXML").hide();
						}
						  },'-',{
							  text:'编辑',//编辑按钮
							  iconCls:'icon-edit',
							  handler:function(){
									var row = $('#templatedatagird').datagrid('getSelected');//selectuserid
									if(row==null){
										alert('请先选择一行数据再进行操作！');
									}else{
									   $("#newTemplate").hide();
									   $("#editTemplate").show();
									   $("#saveTemplate").hide();
									   $("#updateTemplate").show();
									   editRowState="<%=CommonUtil.ROW_STATE_MODIFIED%>";
										$.ajax({
								   			type: "POST",
										    url: "<%=path%>/servlet/SinotransServlet?cmd=findTemplateInfoById&date="+new Date(),
										    data: "templateid="+row.reportTemplateUuid+"",
										    dataType:'json',
										    success: function(data){
										    	$('#templateInfo').dialog({
													draggable:false,
													modal:true
												});
												cleardata('templateInfoForm');
										    	var templatejson = data;
										    	$("#reportTemplateUuid").val(templatejson.reportTemplateUuid);
										    	$("#templateCode").val(templatejson.templateCode);
										    	$("#templateName").val(templatejson.templateName);
										    	$("#templateNameEn").val(templatejson.templateNameEn);
										    	$("#remark").val(templatejson.remark);
										    	$("#fileName").val(templatejson.fileName);
										    	$("#fileVersion").val(templatejson.fileVersion);
										    	$('#status').combobox('select',templatejson.status);
										    	//$("#templateContent").val(templatejson.templateString);
										    	$("#recVer").val(templatejson.recVer);
										    	$("#editTempLateUUID").val(templatejson.reportTemplateUuid);
										    	$("#editTempLateType").val(templatejson.templateType);
										    	$("#controlWord").val(templatejson.controlWord);
										    	if(templatejson.customerCode!=null&&templatejson.customerCode!=""){
													BasQueryUtilManager.getPropertyByCode({modelName:"BasCustomer",keyProperty:"customerCode",valueProperty:"customerName",keyValue:templatejson.customerCode},
													function(rs){
														$('#customerCode').combogrid('setValue',templatejson.customerCode);//公司名称
														$('#customerCode').combogrid('setText',rs);
		           									});
		           								}
										    	findTemplateType(templatejson.templateType);
										    	printExtraCondition(templatejson.controlWord.substr(0,1));
										    	$("#templateCategory").combobox("select",templatejson.templateCategory);
												$("#templateCategoryDisplayTd").html(templatejson.templateCategory);
												$("#templateCategorySelectTd").hide();
												$("#templateCategoryDisplayTd").show();
												if(templatejson.templateCategory=="FASTREPORT"){
													$("#trUploadXML").show();
												}else{
													//$("#trUploadXML").hide();
												}
					   						}
										});
									}
								}
							  },'-',{
						   text:'删除',//删除按钮
						   iconCls:'myCustomerIcon_remove',
						   handler:function(){
								var row = $('#templatedatagird').datagrid('getSelected');
								if(row==null){
									alert('请先选择一行数据再进行操作！');
								}else{
									var index = $('#templatedatagird').datagrid('getRowIndex', row);
									confirm('确定框', '确定删除所选择的信息吗?', function(r){
										if (r){
											//删除用户操作Ajax
											$.ajax({
									   			type: "POST",
											    url: "<%=path%>/servlet/SinotransServlet?cmd=deleteTemplate&date="+new Date(),
											    data: "deleteid="+row.reportTemplateUuid,
											    dataType:'json',
											    success: function(data){
											    	if(data.result){
											    		alert(data.msg);
											    		$('#templatedatagird').datagrid("reload");//根据表格ID刷新页面
											    	}else{
											    		error(data.error);
											    	}
						   						}
											});
										}
									});
								}
							 }						   
						   },
						  '-',{
						  text:'打印预览',//打印按钮
						  iconCls:'icon-print',
						  handler:function(){
						  		var row = $('#templatedatagird').datagrid('getSelected');//selectuserid
								if(row==null){
									alert('请先选择一行数据再进行操作！');
								}else{
								  	if(row.templateCategory=="FASTREPORT"){
										var url ="<%=path%>/jsp/system/printReportTemplateAccept.jsp;jsessionid=<%=session.getId()%>?templateType="+row.templateType+"&controlParam=<%=CommonUtil.REPORT_PRINT_CONTROL_TYPE_PRINT%>&reportId="+row.reportTemplateUuid;
										var strFeatureInfo = "dialogWidth:1px;dialogHeight:1px;resizable:no;scroll:no;center:yes;help:no;status:no;nadorned:no";
										window.showModalDialog(url,null,strFeatureInfo);
										//window.open(url);
								  	}else{
								  		var url ="<%=path%>/jsp/system/ireportTemplateAccept.jsp;jsessionid=<%=session.getId()%>?templateType="+row.templateType+"&controlParam=<%=CommonUtil.REPORT_PRINT_CONTROL_TYPE_PRINT%>&reportId="+row.reportTemplateUuid;
										var strFeatureInfo = "dialogWidth:1000px;dialogHeight:800px;resizable:no;scroll:no;center:yes;help:no;status:no;nadorned:no";
										window.showModalDialog(url,null,strFeatureInfo);
										//window.open(url);
								  		//alert("暂不支持ireport"+row.templateCategory);
								  	}
								}
							}
						  },'-',{
					  text:'通用打印测试',//打印按钮
					  iconCls:'icon-print',
					  handler:function() {
						  
						  var columns = parseInt($('#columns').val());
						  var data = $('#templatedatagird').datagrid('getData');
						  var rows = new Array();
						  var i;
						  for(i = 0; i < data.rows.length; i++) {
							  var cells = new Array();
							  cells[0] = data.rows[i].templateCode;
							  cells[1] = data.rows[i].templateCategory;
							  cells[2] = data.rows[i].templateName;
							  cells[3] = data.rows[i].templateType;
							  cells[4] = data.rows[i].fileVersion;
							  cells[5] = data.rows[i].status;
							  cells[6] = data.rows[i].creator;
							  cells[7] = data.rows[i].createTime;
							  cells[8] = data.rows[i].uuid;
							  cells[9] = data.rows[i].fileName;
							  
							  if(columns > 0) {
								  cells = cells.slice(0, columns);
							  }
							  rows[i] = {
								  cells : cells
					  		  };
						 	}
							var table = {
								rows : rows
							};
							PrintReportTemplateManager.setCommonTemplateData(table, function() {
								var print = $("#printButton").sinotrans_Print({
									path : '<%=path%>',
									modelId : 'dummy',
									jsessionid : '<%=request.getSession().getId() %>',
									controlParam : '<%=CommonUtil.REPORT_PRINT_CONTROL_TYPE_PRINT%>',
									templateType: '<%=CommonUtil.REPORT_PRINT_TYPE_COMMONTEMPLATE%>'
								});
								print.open();
							});
					  }
				  }],					
					onDblClickRow:function(rowIndex,rowData){
							$('#templatedatagird').datagrid('endEdit', lastIndex);
							$('#templatedatagird').datagrid('beginEdit', rowIndex);
							lastIndex = rowIndex;
						},
					onClickRow:function(rowIndex){
							$('#templatedatagird').datagrid('endEdit',lastIndex);
						},
						fitColumns:true,
						nowrap:true,
						rownumbers:true,
						showFooter:true,
						pageSize:10, //每页显示记录数
						pageList:[10,15], //可调整每页显示的记录数
						idField:'reportTemplateUuid'
						});
					$('#templateInfo').dialog();//dialog 格式化
					$('#templateInfo').dialog('close');//初始化时关闭dialog

					$('#status').combobox({
						panelHeight:'auto',
						editable:false
					});
					
					$("#__templateType").combobox({
						onChange:templateselect
					});
				});
	
				//查询按钮事件
			function templateselect(){
				var templateName=$('#__templateName').val();
				var fileName=$('#__fileName').val();
				var templateType=$('#__templateType').combobox("getValue");
				var condition=$('#__condition').val();
				var officeCode= "<%=scue.getOfficeCode()%>";
				if(templateType=="ALL"){
					templateType = "";
				}
				$("#templatedatagird").datagrid('load',{
                	__templateName:templateName,
                	__fileName:fileName,
                	__templateType:templateType,
                	__condition:condition,
                	__officeCode:officeCode
		       	});
			}
				//重置按钮事件
			function cleardata(param){
				$("#"+param).form('clear');
			}
				//关闭按钮事件
			function closeInfo(clo){
				$('#'+clo).dialog('close');
			}
			//字典表查询所需的报表类型
			function queryTemplateType(){
				$("#__templateType").combobox({
					url:'<%=path%>/servlet/SinotransServlet?cmd=queryOption&types=<%=CommonUtil.OPTION_SYSTEM_CODE%>&q=<%=CommonUtil.REPORT_TYPES%>&date='+new Date(),
					width:150,
					valueField:'key',
					textField:'value',
					editable:false,
					panelHeight:'auto'
				});
			}
			//新增修改时：字典表查询所需的报表类型
			function findTemplateType(typeVal){
				$("#templateType").combobox({
					url:'<%=path%>/servlet/SinotransServlet?cmd=queryOption&types=<%=CommonUtil.OPTION_SYSTEM_CODE%>&q=<%=CommonUtil.REPORT_TYPES%>&date='+new Date(),
					width:150,
					valueField:'key',
					textField:'value',
					panelHeight:'auto',
					onLoadSuccess:function(){
						$("#templateType").combobox('setValue',typeVal);
						//$("#templateType").combobox("editable",false);//编辑状态设为只读（需放在findTemplateType后面）
					}
				});
			}
			
			//新增修改时：字典表查询所需的作业项目
			function printExtraCondition(typeVal) {
				$("#printExtraCondition").combobox({
					url:'<%=path%>/servlet/SinotransServlet?cmd=queryOption&types=<%=CommonUtil.OPTION_SYSTEM_CODE%>&q=<%=CommonUtil.PRINT_EXTRA_CONDITION%>&date='+new Date(),
					width:150,
					valueField:'key',
					textField:'value',
					panelHeight:'auto',
					onLoadSuccess:function(){
						$("#printExtraCondition").combobox('setValue',typeVal);
						//$("#templateType").combobox("editable",false);//编辑状态设为只读（需放在findTemplateType后面）
					}
				});
			}
					
//新增进数据库并进行页面刷新
function saveTemplate(operateType){
	if(operateType=="NEW"){
		//var templateContent = $("#templateContent").val();
		$("#rowState").val(editRowState);
		var template = $("#template").val();		
		if(template){
			if($("#templateInfoForm").form("validate")){
				$("#templateInfoForm").form("submit",{
					url:"<%=path %>/printTemplateAction.do",
					success:function(data){
						var obj = $.evalJSON(data);
						if(obj.result){
							alert("保存成功！");
							$('#templatedatagird').datagrid("reload");//根据表格ID刷新页面	
							$('#templateInfo').dialog('close');
						}else{
							alert("保存失败");
						}
					}
				});
			}else{
				alert("还有必填项没填！");
			}
		}else{
			alert("请选择上传模板");
		}
	}else{
		$("#rowState").val(editRowState);
		if($("#templateInfoForm").form("validate")){
			$("#templateInfoForm").form("submit",{
				url:"<%=path %>/printTemplateAction.do",
				success:function(data){
					var obj = $.evalJSON(data);
					if(obj.result){
						alert("保存成功！");
						$('#templatedatagird').datagrid("reload");//根据表格ID刷新页面	
						$('#templateInfo').dialog('close');
					}else{
						alert("保存失败");
					}
				}
			});
		}else{
			alert("还有必填项没填！");
		}
	}

	/*
	if($("#templateInfoForm").form("validate")){
		var savaUrl = encodeURI("<%=path%>/servlet/SinotransServlet?cmd=saveTemplate&date="+new Date()+
				"&templateCode="+$("#templateCode").val()+"&templateName="+$("#templateName").val()+"&templateType="+$("#templateType").combobox("getValue")+
				"&fileName="+$("#fileName").val()+"&fileVersion="+$("#fileVersion").val()+"&status="+$("#status").combobox("getValue")+"&templateNameEn="+
				$("#templateNameEn").val()+"&template="+$("#template").val()+"&remark="+$("#remark").val()+"&reportTemplateUuid="+$("#reportTemplateUuid").val()+"&rowState="+editRowState);
		alert(savaUrl);
		$.ajaxFileUpload({
			url: savaUrl,
			dataType: 'json',//返回数据类型
			fileElementId:'template',//input框的ID
			secureuri:false,
			success : function (data, status){
				if(data.result){
					alert(data.msg);
					$('#templatedatagird').datagrid("reload");//根据表格ID刷新页面	
					$('#templateInfo').dialog('close');
		         }else{ 
		        	 error(data.error); 
		       	 } 
			}
		});
	}else{
		alert("还有必填项没填！");
	}
	*/
}
			//验证上传文件格式	
function checkFile(obj,filter){
    var file = obj.value.match(/[^\/\\]+$/gi)[0];
    var rx = new RegExp('\\.(' + (filter?filter:'') + ')$','gi');
    if ( false && filter&&file&&!file.match(rx)){
       alert("只能选择fr3文件类型！");
       //重新构建input file
       document.getElementById("uploadXMLModelID").innerHTML="<input type='file' size=\"70\" contenteditable='false' onchange=\"checkFile(this,'fr3')\" name='template' id='template' title='选择文件' />";
       return false;  
    }else{
       $("#fileName").val(file.split(".")[0]);
    }
}
function editTemplate(){
	if($("#templateInfoForm").form("validate")){
		var templateCategory=$('#templateCategory').combobox("getValue");
		var strFeatureInfo = "dialogWidth:1px;dialogHeight:1px;resizable:no;scroll:no;center:yes;help:no;status:no;nadorned:no";
		var reportId = $("#editTempLateUUID").val();
		var templateType = $("#editTempLateType").val();
		var fileName = $("#templateCode").val();
		var officeCode="<%=officeCode%>";
		if(templateCategory=="FASTREPORT"){
			var url = "<%=path%>/jsp/system/printReportTemplateAccept.jsp;jsessionid=<%=session.getId()%>?templateType="+templateType+"&reportId="+reportId+"&controlParam=<%=CommonUtil.REPORT_PRINT_CONTROL_TYPE_EDIT%>";
			window.showModalDialog(url,null,strFeatureInfo);		
		}else{
			$("#rowState").val("Modified");
			$("#templateInfoForm").form("submit",{
				url:"<%=path %>/printTemplateAction.do",
				success:function(data){
					var obj = $.evalJSON(data);
					if(obj.result){
						var newUrl = "<%=path%>/jnlp/dowload_jnlp.jsp?jsessionid=<%=session.getId()%>&templateType="+templateType+"&reportId="+reportId+"&fileName="+fileName+"&officeCode="+officeCode+"&controlParam=<%=CommonUtil.REPORT_PRINT_CONTROL_TYPE_EDIT%>";
						$('#templateInfo').dialog("close");
						window.showModalDialog(newUrl,null,strFeatureInfo);	
					}else{
						alert("新建模板失败");
					}
				}
			});	
		}
	}else{
		alert("还有必填项没填！");
	}
}	  		
function addTemplate(){
	if($("#templateInfoForm").form("validate")){
		var templateCategory=$('#templateCategory').combobox("getValue");
		if(templateCategory=="FASTREPORT"){
			var strFeatureInfo = "dialogWidth:1px;dialogHeight:1px;resizable:no;scroll:no;center:yes;help:no;status:no;nadorned:no";
			window.showModalDialog("<%=path%>/jsp/system/printReportTemplateAccept.jsp;jsessionid=<%=session.getId()%>?templateType="+$("#templateType").combobox("getValue")+"&controlParam=<%=CommonUtil.REPORT_PRINT_CONTROL_TYPE_NEW%>",null,strFeatureInfo);
		}else{
			$("#rowState").val("Added");
			$("#templateInfoForm").form("submit",{
				url:"<%=path %>/printTemplateAction.do",
				success:function(data){
					var obj = $.evalJSON(data);
					if(obj.result){
						var strFeatureInfo = "dialogWidth:1px;dialogHeight:1px;resizable:no;scroll:no;center:yes;help:no;status:no;nadorned:no";
						var templateType = $("#templateType").combobox("getValue");
						var fileName = obj.object.templateCode;
						var reportId = obj.object.reportTemplateUuid;
						var officeCode="<%=officeCode%>";
						var newUrl = "<%=path%>/jnlp/dowload_jnlp.jsp?templateType="+templateType+"&reportId="+reportId+"&fileName="+fileName+"&officeCode="+officeCode+"&controlParam=<%=CommonUtil.REPORT_PRINT_CONTROL_TYPE_EDIT%>";
					
						$('#templateInfo').dialog("close");
						window.showModalDialog(newUrl,null,strFeatureInfo);
					}else{
						alert("新建模板失败");
					}
				}
			});	
		}
	}else{
		alert("还有必填项没填！");
	}
}

function _chushihuaCustomerCode(){
	<%
		String agentConsigneeDesc = CommonUtil.SELECT_CODE_ALL_CUSTOMER;
		String agentConsigneeDescmodelName = agentConsigneeDesc.split(",")[0] + "Model";
		%>
		var customerRow = [{name:'customerCode',value:'客户代码'},{name:'customerName',value:'中文名称'},{name:'customerNameEn',value:'客户名称'}];
		
		combogridSetValueAndTest('customerCode','',customerRow,'<%=path%>','<%=agentConsigneeDescmodelName%>','',false);
		
}

function setBasicdataState(v,r,i){
	if(v=='Active') return '<span style="color:green;">有效</span>';
	if(v=='Cancel') return '<span style="color:red;">作废</span>';
	if(v=='undefined') return '';
	return v;
}

	</script>
	<body class="easyui-layout">
	<!--  <div split="true" title="打印模版管理" style="overflow: hidden;">-->
		<div id="TemplatequeryTerm" region="north" title="查询条件"
			border="false" style="height:120px;">
			<form id="TemplatequeryForm">
				<div style="background: #efefef; width: 100%; float: left">
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="icon-search" style="float: left"
						onclick="templateselect()">查询</a>
					<div class="datagrid-btn-separator"></div>
					<!-- 这是分割线 -->
					<a href="#" class="easyui-linkbutton" plain="true"
						iconCls="myCustomerIcon_clear" style="float: left"
						onclick="cleardata('TemplatequeryForm')">重置</a>
				</div>
				<!-- legend>表单验证</legend -->
				<table id="tableQueryForm" style="width: 95%; float: left">
					<input type="hidden" id="__condition" name="__condition"
						value="templateName_ilikeAnywhere:fileName_ilikeAnywhere:templateType1_eq" />
					<tr align="right">
						<td width="10%" align="right">
							<label>
								模板名称:
							</label>
						</td>
						<td width="20%" align="left">
							<input id="__templateName" name="__templateName" size="25"
								class="easyui-validatebox"/>
						</td>
						<td width="10%" align="right">
							<label>
								文件名称:
							</label>
						</td>
						<td width="20%" align="left">
							<input id="__fileName" name="__fileName" size="25" />
						</td>
						<td width="10%" align="right">
							<label>
								模板类型:
							</label>
						</td>
						<td width="20%" align="left">
							<select id="__templateType" class="easyui-combobox"
								name="__templateType" style="width: 200px;" size="25">
							</select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label>
								列数:
							</label>
						</td>
						<td width="20%" align="left">
							<input id="columns" class="easyui-numberbox"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div region="center" border="false">
			<table id="templatedatagird" title="打印模版列表" iconCls="icon-edit" border="false"
				singleSelect="true" idField="itemid" fit="true"
				 url="<%=path%>/servlet/SinotransServlet?cmd=selectPrintReportTemplate">
				 <!--
				url="<%=path%>/servlet/SinotransServlet?cmd=query&queryType=PrintReportTemplateModel&oc=true">
				  -->
				<thead>
					<tr>
						<th field="templateCode" width="60" align="center">
							模板代码
						</th>
						<th field="templateCategory" width="50" align="center">
							模板分类
						</th>
						<th field="templateType" width="50" align="center">
							模板类型
						</th>
						<th field="templateName" width="50" align="center">
							模版名称
						</th>
						<th field="templateNameEn" width="50" align="center">
							英文描述
						</th>
						<th field="fileName" width="110" align="center">
							文件名
						</th>
						<th field="status" width="20" formatter="setBasicdataState" align="center">
							状态
						</th>
						<th field="fileVersion" width="30" align="center">
							版本号
						</th>
						<th field="creator" width="50" align="center">
							创建人
						</th>
						<th field="modifier" width="50" align="center">
							修改人
						</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="templateInfo"
			style="padding: 5px; width: 800px; height: 300px;" title="编辑打印模版">
			<p id="results">
				<b> </b>
			</p>
			<div>
				<form name="templateInfoForm" id="templateInfoForm" action="<%=path %>/printTemplateAction.do" method="post" 
					enctype="multipart/form-data">
					
					<table id="tableQueryForm" style="width: 100%;">
						<tr>
							<td align="right">
								模板代码:
							</td>
							<td align="left">
								<input type="hidden" id="reportTemplateUuid" name="reportTemplateUuid" />
								<input type="hidden" id="recVer" name="recVer" />
								<input type="hidden" id="editTempLateUUID" name="editTempLateUUID" value=""/>
								<input type="hidden" id="editTempLateType" name="editTempLateType" value=""/>
								<input type="hidden" id="rowState" name="rowState" value=""/>
								<input type="text" size="15" id="templateCode" name="templateCode" readonly />
								<input type="hidden" id="controlWord" name="controlWord" value=""/>
							</td>
							<td align="right" style="width:70px">
								模板名称:
							</td>
							<td align="left">
								<input type="text" size="15" id="templateName" name="templateName" class="easyui-validatebox"  required="true"/>
							</td>
							<td align="right" style="width:70px">
								模版类型:
							</td>
							<td align="left">
								<select id="templateType" name="templateType" class="easyui-combobox" required="true">
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								文件名:
							</td>
							<td align="left">
								<input type="text" size="15" id="fileName" name="fileName" class="easyui-validatebox" 
									readonly />
							</td>
							<td align="right">
								版 本 号:
							</td>
							<td align="left">
								<input type="text" size="15" id="fileVersion" name="fileVersion" class="easyui-validatebox" required="true"/>
							</td>
							<td align="right">
								状态:
							</td>
							<td align="left">
								<select id="status" name="status" style="width: 60%" class="easyui-combobox" required="true">
									<option value="<%=CommonUtil.Active%>">有效</option>
									<option value="<%=CommonUtil.Cancel%>">作废</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								英文名称:
							</td>
							<td align="left" >
								<input type="text" id="templateNameEn" class="easyui-validatebox"  
									name="templateNameEn" required="true" />
							</td>
							<td align="right">
								客户类型:
							</td>
							<td align="left">
								<input type="text" id="customerCode" class="easyui-combogrid" 
									name="customerCode"/>
							</td>
							<td align="right">
								模板分类:
							</td>
							<td align="left" id="templateCategorySelectTd">
								<select id="templateCategory" name="templateCategory" class="easyui-combobox" required="true" panelHeight="50">
								</select>
							</td>
							<td align="left" id="templateCategoryDisplayTd"></td>
						</tr>
						<tr>
							<td align="right">
								作业项目:
							</td>
							<td align="left">
								<select id="printExtraCondition" name="printExtraCondition" class="easyui-combobox" required="true">
								</select>
							</td>
						</tr>
						<tr>
							<td align="right">
								模板描述:
							</td>
							<td align="left" colspan="5">
								<input type="text" style="width:500px" id="remark" name="remark"/>
							</td>
						</tr>
						<tr id="trUploadXML">
							<td align="right">
								上传模版:
							</td>
							<td align="left" colspan="5" id="uploadXMLModelID">
								<input type="file" size="70" id="template" name="template"
									title="选择文件" contenteditable="false"
									onchange="checkFile(this,'fr3')" />
							</td>
						</tr>
						<!-- <tr>
							<td align="right">
								模板内容:
							</td>
							<td align="left" colspan="5">
								<textarea rows="8" cols="80" id="templateContent"
									name="templateContent" readonly="true"></textarea>
							</td>
						</tr> -->
						<tr>
							<td align="center" colspan="6">
								<a href="#" class="easyui-linkbutton" onclick="addTemplate();" id="newTemplate">新增模版</a>
								<a href="#" class="easyui-linkbutton" onclick="editTemplate();" id="editTemplate">编辑模版</a>
								<a href="#" id="saveTemplate" name="saveTemplate" class="easyui-linkbutton" onclick="saveTemplate('NEW');">保存</a>
								<a href="#" id="updateTemplate" name="updateTemplate" class="easyui-linkbutton" onclick="saveTemplate('EDIT');">修改</a>
								<a href="#" class="easyui-linkbutton" onclick="closeInfo('templateInfo');">关闭</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		<!-- </div> -->
		</div>
</body>
</html:html>
<script type="text/javascript">
$(function(){
	$("#newTemplate").hide();
	$("#editTemplate").hide();
	$("#saveTemplate").hide();
	$("#updateTemplate").hide();
});
</script>