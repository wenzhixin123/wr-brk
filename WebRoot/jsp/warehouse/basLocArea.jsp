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
  
    <title>LocArea资料管理</title>
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
		var p_modelUuId="basLocAreaUuid";
		var request_url="<%=request.getContextPath()%>"+"/servlet/SinotransServlet?cmd=saveBasLocArea";
		//唯一性校验的全局依赖字段和函数定义
		var v_global_exitsCodes=new Array();
		function fn_validate_isUnique(modelName){
			$.ajax({
              type: "POST",
              url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&oc=true&queryType="+modelName+"&rows="+1000+"",
              dataType:"json",
              success: function(result){
              	  $(result.rows).each(function(){
              	  	v_global_exitsCodes.push(this.locAreaCode);
              	  });
              }
          	});
		}
		function fn_formateArea(value){
			if(value==null){
				return;
			}
			var needChr=value.split("",1);
			if(needChr[0]=='R'){
				return '收获区';
			}else if(needChr[0]=='P'){
				return '拣货区';
			}else if(needChr[0]=='M'){
				return '加工区';
			}else if(needChr[0]=='0'){
				return '无';
			}else{
				return value;
			}
		}
		//初始化
		fn_validate_isUnique("BasLocAreaModel");
	</script>
	<script type="text/javascript" src="../../js/util/wms_baseCRUD.js"></script>
	
	<script type="text/javascript">
	//datagrid定义
	$(function(){
		BaseCRUD('datagird',{
					modelUuId:p_modelUuId,
					interceptor:function(options,easyUIFunc){
						<%--options.onDblClickRow=function(rowIndex,rowData){
							parent.window.addTabs("物料详单管理","<%=path%>/jsp/basicData/basLocAreaDetail.jsp?basLocAreaUuid="+rowData.basLocAreaUuid,"",true);
						}--%>
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
		<!--  <div title="<bean:message bundle="wms.basicdata" key="searchTitle"/>" iconCls=""  closable="true" style="height: 100%;width: 100%">-->
			<div id="" style="height:135px;" region="north" iconCls="myCustomerIcon_searchForm" title="<bean:message bundle="wms.common" key="global.searchTitle"/>" border="false">
				<%--按钮--%>
				<div class="myCustomer_toolbar">
					<a href="#" id="searchBtn" class="easyui-linkbutton"
							plain="true" iconCls="icon-search" style="float: left"><bean:message bundle="wms.common" key="global.search"/></a>
					<div class="datagrid-btn-separator"></div>
					<a href="#" id="resetButton" class="easyui-linkbutton"
							plain="true" iconCls="myCustomerIcon_clear" style="float: left"><bean:message bundle="wms.common" key="global.reset"/></a>
					<div class="datagrid-btn-separator"></div>
				</div>
				<%--查询表单--%>
				<div id="formContainer" >
					<form id="queryForm">
							<table id="searForm" style="width: 100%;margin:0;padding:0;">
								<tr>
									<td align="center" class="textStyle">
										<label for="locAreaCode">
											<bean:message bundle="wms.basicdata" key="BasLocArea.locAreaCode"/>:
										</label>
									</td>
									<td>
										<input id="locAreaCode" name="locAreaCode"  class="easyui-validatebox" validType="noChinese"/>
									</td>
									<td align="center" class="textStyle">
										<label for="locAreaName" >
											<bean:message bundle="wms.basicdata" key="BasLocArea.locAreaName"/>:
										</label>
									</td>
									<td>
										<input id="locAreaName" name="locAreaName" class="easyui-validatebox"  />
									</td>
								</tr>
								<tr>
									<td align="center" class="textStyle">
										<label for="status" >
											<bean:message bundle="wms.basicdata" key="BasLocArea.status"/>:
										</label>
									</td>
									<td>
										<select id="status" name="status" style="width:70%" >
											<option value="">==请选择==</option>
											<option value="生效">生效</option>
											<option value="草稿">草稿</option>
											<option value="作废">作废</option>
										</select>
									</td>
									
									<td  align="center" class="textStyle">
										<label for="countryNameEn" >
											<bean:message bundle="wms.basicdata" key="BasLocArea.locAreaNameEn"/>:
										</label>
									</td>
									<td >
										<input id="locAreaNameEn" name="locAreaNameEn"  class="url" value="" />
									</td>
								</tr>
								
								
							</table>
					</form>
				</div>
			</div>
		<div id="gridContainer" region="center" border="false">
						<table id="datagird" title="<bean:message bundle="wms.basicdata" key="editTitle"/>" iconCls="icon-edit"
							url="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&oc=true&queryType=BasLocAreaModel" fit="true">
							<thead>
								<tr>
									<th field="status" formatter='fn_global_setBasicdataState' width="40px"  align="center" sortable="true">
										<bean:message bundle="wms.basicdata" key="BasLocArea.status"/>
									</th>
									<th field="locAreaCode"  width="150px" align="center" td_align="left"
										editor="{type:'validatebox',options:{required:true,validType:'isUnique'}}">
										<bean:message bundle="wms.basicdata" key="BasLocArea.locAreaCode"/>
									</th>
									<th field="locAreaName" width="200px"  align="center" td_align="left" editor="validatebox">
										<bean:message bundle="wms.basicdata" key="BasLocArea.locAreaName"/>
									</th>
									<th field="locAreaNameEn" width="200px"  align="center" td_align="left" editor="{type:'validatebox',options:{minLength:5}}">
										<bean:message bundle="wms.basicdata" key="BasLocArea.locAreaNameEn"/>
									</th>
									<th field="controlWord" width="100px"  align="left"  formatter='fn_formateArea'>
										区域类型
									</th>
									<th field="remark" width="300px"  align="center" td_align="left"
										editor="validatebox">
										<bean:message bundle="wms.basicdata" key="BasLocArea.remark"/>
									</th>
								</tr>
							</thead>
						</table>
		 </div>
		<!--  </div>-->

  </body>
</html:html>
