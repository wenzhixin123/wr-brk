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
  
    <title>汇率信息管理</title>
    <jsp:include page="../../common/imp_dwr.jsp"></jsp:include>     
	<!-- jquery easyui -->
	<jsp:include page="../../common/imp_easyui.jsp"></jsp:include>
	<link rel="stylesheet" href="../../css/system.css" type="text/css"></link>
	<script type="text/javascript" src="../../js/util/wlp_formatter.js"></script>
	<script type="text/javascript" src="../../js/util/date.format.js"></script>
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
		var p_modelUuId="basExchangeRateUuid";
		var request_url="<%=request.getContextPath()%>"+"/servlet/SinotransServlet?cmd=saveBasExchangeRate";
		//唯一性校验的全局依赖字段和函数定义
		var v_global_exitsCodes=new Array();
		function fn_validate_isUnique(modelName){
			$.ajax({
              type: "POST",
              url: "<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&queryType="+modelName+"&rows="+1000+"",
              dataType:"json",
              success: function(result){
              	  $(result.rows).each(function(){
              	  	v_global_exitsCodes.push(this.currencyCode);
              	  });
              }
          	});
		}
		//初始化
		fn_validate_isUnique("BasExchangeRateModel");
	</script>
	<script type="text/javascript" src="../../js/util/wlp_baseCRUD.js"></script>
	
	<script type="text/javascript">
	$(function(){
		BaseCRUD('datagird',{modelUuId:p_modelUuId},request_url);
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

        //控件
        $("#exchangeRate").numberbox();
	});

	</script>
	<script type="text/javascript">
	   function validEndTime(date){
	       var lastIndex = $('#datagird').data('lastIndex');
	       var beginTime; 
	       if(lastIndex>=0){
	           if($('#datagird').datagrid('getEditors',lastIndex)[3]){
	      	  beginTime = $('#datagird').datagrid('getEditors',lastIndex)[3].target.datebox("getValue");
	      	 }
	       }
	       if(beginTime && date<=beginTime){
	           return false;
	       }else{
	         return true;
	       }
	    
	   }
	   
	   function validStaTime(date){
	       var lastIndex = $('#datagird').data('lastIndex');
	       var endTime;
	       if(lastIndex>=0){
	         if($('#datagird').datagrid('getEditors',lastIndex)[4]){
		      endTime = $('#datagird').datagrid('getEditors',lastIndex)[4].target.datebox("getValue");
	         }
	       }
	       	 if(date>=endTime && endTime){
	          	 return false;
		       }else{
		         return true;
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
							<label for="currencyCode">
								<bean:message bundle="wlp.basicdata" key="BasExchangeRate.currencyCode"/>:
							</label>
						</td>
						<td>
							<input id="currencyCode" name="currencyCode"  class="easyui-validatebox" />
						</td>
						<td align="center" td_align="left" class="textStyle">
							<label for="exchangeCurrencyCode" >
								<bean:message bundle="wlp.basicdata" key="BasExchangeRate.exchangeCurrencyCode"/>:
							</label>
						</td>
						<td>
							<input id="exchangeCurrencyCode" name="exchangeCurrencyCode" class="easyui-validatebox"  />
						</td>
					</tr>
					<tr>
						<td align="center" td_align="left" class="textStyle">
							<label for="status" >
								<bean:message bundle="wlp.basicdata" key="BasExchangeRate.status"/>:
							</label>
						</td>
						<td>
							<select id="status" name="status" style="width:70%" >
								<option value="">==请选择==</option>
								<option value="Active">生效</option>
								<option value="Pending">草稿</option>
								<option value="Cancel">作废</option>
							</select>
						</td>
						
						<td  align="center" td_align="left" class="textStyle">
							<label for="exchangeRate" >
								<bean:message bundle="wlp.basicdata" key="BasExchangeRate.exchangeRate"/>:
							</label>
						</td>
						<td >
							<input id="exchangeRate" name="exchangeRate"  class="url" value="" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div region="center" border="false">
		<table id="datagird" title="<bean:message bundle="wlp.basicdata" key="editTitle"/>" iconCls="icon-edit"
			url="<%=request.getContextPath()%>/servlet/SinotransServlet?cmd=query&queryType=BasExchangeRateModel" fit="true">
			<thead>
				<tr>
					<th field="status" formatter='fn_global_setBasicdataState'  width="40"  align="center" td_align="left" >
						<bean:message bundle="wlp.basicdata" key="BasExchangeRate.status"/>
					</th>
					<th field="currencyCode"  width="100" align="center" td_align="left"
						editor="{type:'validatebox',options:{required:true,validType:'isUnique'}}">
						<bean:message bundle="wlp.basicdata" key="BasExchangeRate.currencyCode"/>
					</th>
					<th field="exchangeCurrencyCode" width="130"  align="center" td_align="left" editor="{type:'validatebox',options:{required:true,validType:'maxLength[50]'}}">
						<bean:message bundle="wlp.basicdata" key="BasExchangeRate.exchangeCurrencyCode"/>
					</th>
					<th field="exchangeRate" width="100"  align="center" td_align="left" 
						editor="{type:'numberbox',
							options:{precision:6,max:99999999999.999999}}">
						<bean:message bundle="wlp.basicdata" key="BasExchangeRate.exchangeRate"/>
					</th>
					<th field="activeStaDate" width="130"  align="center" td_align="left" formatter='datagridDateFormat'
						editor="{
								type:'datebox',
								options:{
									editable:false,
									formatter:function(date){
										return date.format('isoDate')+' '+date.format('isoTime');},
									validType:'compareToDate[this.validStaTime]'
									}
								}">
						<bean:message bundle="wlp.basicdata" key="BasExchangeRate.activeStaDate"/>
					</th>
					
					<th field="activeEndDate" width="130" align="center" td_align="left" formatter='datagridDateFormat'  
						editor="{
								type:'datebox',
								options:{
									editable:false,
									formatter:function(date){
										return date.format('isoDate')+' '+date.format('isoTime');},
									validType:'compareToDate[this.validEndTime]'
									}
								}">
						<bean:message bundle="wlp.basicdata" key="BasExchangeRate.activeEndDate"/>
					</th>
					<th field="remark" width="200"  align="center" td_align="left" 
						editor="validatebox">
						<bean:message bundle="wlp.basicdata" key="BasExchangeRate.remark"/>
					</th>
				</tr>
			</thead>
		</table>
	 </div>
  </body>
</html:html>
