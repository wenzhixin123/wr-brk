<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@page import="com.sinotrans.gd.wlp.common.web.RcUtil"%>
<%
	String path = request.getContextPath();
	String types = request.getParameter("types");//获取数据类型
	String key = request.getParameter("key");//返回数据到父页面的隐藏key
	String value = request.getParameter("value");//返回数据到父页面的显示value
	String functionName = request.getParameter("functionName");//返回整个数据对象，父页面自行处理（function Name）
	String param = request.getParameter("param");//业务页面传输过来的过滤条件
	String divId = request.getParameter("divId");//divid
	
	
	String active = CommonUtil.Active;
	if(!RcUtil.isEmpty(param)){
		param = "&"+param.replace("|^|","&");		
	}else{
		param = "";
	}
%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<jsp:include page="../../common/imp_easyui.jsp"></jsp:include>
	<link rel="stylesheet" href="<%=path%>/css/system.css" type="text/css"></link>
	<style type="text/css">
body {
	background: white;
}
</style>
	<script type="text/javascript">
	var service_url ="";//请求的URL 
	<%if (CommonUtil.SELECT_CODE_ALL_COUNTRY.equals(types)) {%>
		//查询国家代码
		service_url = '<%=path%>/servlet/SinotransServlet?cmd=query&queryType=BasCountryModel'+'&__status=<%=active%><%=param%>';
	<%} else if (CommonUtil.OPTION_STRATEGY.equals(types)) {%>
		//查询上架拣货策略
		service_url = '<%=path%>/servlet/SinotransServlet?cmd=query&oc=true&queryType=LocPlanConfigModel'+'&__status=<%=active%><%=param%>';
	
	<%} else if (CommonUtil.TRANSACTIONTYPE_ADJ.equals(types)
						|| CommonUtil.TRANSACTIONTYPE_PDN.equals(types)) {%>
		//查询库存 生成移位单明细
		service_url = '<%=path%>/servlet/SinotransServlet?cmd=query&oc=true&queryType=LocPlanConfigModel'+'&__status=<%=active%><%=param%>';
	<%} else if (CommonUtil.TRANSACTIONTYPE_BON.equals(types)) {%>
		//应急发货单号
		service_url = '<%=path%>/servlet/SinotransServlet?cmd=getUrgentOrderNoDatagrive&date='+new Date();
	<%} else if (types.split(",").length > 1) {
					String modelName = types.split(",")[0] + "Model";%>
		service_url = '<%=path%>/servlet/SinotransServlet?cmd=query&queryType='+'<%=modelName%>'+'&__status=<%=active%><%=param%>';
	<%}%>
</script>
	<script type="text/javascript">
$(function(){
	//加载数据
	$('#dataTable').datagrid({
		url:service_url,
		pagination:true,
		rownumbers:true,
		height:280,
		pageSize:10,
		pageList:[10],
		onDblClickRow:function(rowIndex){
			 var rowData = $('#dataTable').datagrid('getSelected');
			 
			 <%if (CommonUtil.SELECT_CODE_ALL_COUNTRY.equals(types)) {
			 	String keys [] = key.split(",");
			 	for(String k : keys){
			 		out.println("$(window.parent.document).find('#"+k+"').val(rowData.countryCode);");
			 	}
			 	String values [] = value.split(",");
			 	for(String v : values){
			 		out.println("$(window.parent.document).find('#"+v+"').val(rowData.countryName);");
			 	}
			 %>
			 <%
			 } else if (CommonUtil.OPTION_STRATEGY.equals(types)) {%>
			 	$(window.parent.document).find('#<%=key%>').val(rowData.configCode);
			 	var combo = $(window.parent.document).find('#<%=value%>').attr("combo");
			 	if(combo){
			 		window.parent.setComboText("<%=value%>",rowData.configName);
			 	}else{
			 		$(window.parent.document).find('#<%=value%>').val(rowData.configName);
			 	}
			 <%} else if (CommonUtil.TRANSACTIONTYPE_ADJ.equals(types)
						|| CommonUtil.TRANSACTIONTYPE_PDN.equals(types)) {%>
				 //$(window.parent.document).find('#<%=key%>').val(rowData.seqNo);
				// $(window.parent.document).find('#<%=value%>').val(rowData.configName);
			 		
			 <%} else if (CommonUtil.TRANSACTIONTYPE_BON.equals(types)) {%>
					 $(window.parent.document).find('#<%=key%>').val(rowData.basOrderNoUuid);
					 $(window.parent.document).find('#<%=value%>').val(rowData.orderNo);
			 <%} else if (types.split(",").length > 1) {
				String keys [] = key.split(",");
			 	for(String k : keys){
			 		out.println("$(window.parent.document).find('#"+k+"').val(rowData['"+types.split(",")[1]+"'])");
			 	}
			 	String values [] = value.split(",");
			 	for(String v : values){
			 		out.println("$(window.parent.document).find('#"+v+"').val(rowData['"+types.split(",")[2]+"'])");
			 		out.println("var "+v+" = $(window.parent.document).find('#"+v+"');");
			 		out.println("if("+v+".attr('combo')){");//下拉联想控件
			 		out.println("window.parent.setComboValue("+v+".attr('id'),rowData['"+types.split(",")[1]+"'])");//父窗口必须存在setComboValue方法
			 		out.println("window.parent.setComboText("+v+".attr('id'),rowData['"+types.split(",")[2]+"'])");
	 				out.println("}");
			 	}
			 %>
			 <%}%>
			
			 <%if (!RcUtil.isEmpty(functionName)) {%>
			 	window.parent.<%=functionName%>(rowData);//运行父窗口处理返回对象function
			 <%}%>
			 parent.$('#<%=divId%>').window('close');//关闭Div 窗口
			 parent.$("#queryDiv").hide();
		}
	});
	//搜索按钮事件
	$("#search").click(function(){
		<%if (CommonUtil.SELECT_CODE_ALL_COUNTRY.equals(types)) {%>
			var condition = $("#__condition").val();
			var countryCode = $("#__countryCode").val();
			var countryName = $("#__countryName").val();
			$("#dataTable").datagrid('load',{
					"__countryName":countryName,
					"__countryCode":countryCode,
	               	"__condition":condition
	      	});
      	<%} else if (CommonUtil.OPTION_STRATEGY.equals(types)) {%>
      	var condition = $("#__condition").val();
			var countryCode = $("#__configCode").val();
			var countryName = $("#__configName").val();
			$("#dataTable").datagrid('load',{
					"__configName":countryName,
					"__configCode":countryCode,
	               	"__condition":condition
	      	});
		<%} else if (CommonUtil.TRANSACTIONTYPE_ADJ.equals(types)
						|| CommonUtil.TRANSACTIONTYPE_PDN.equals(types)) {%>
	      	var seqNo = $("#__seqNo").val();
			var sourceLotCode= $("#__sourceLotCode").val();
			var targetLotCode = $("#__targetLotCode").val();
			$("#dataTable").datagrid('load',{
					"__seqNo":seqNo,
					"__sourceLotCode":sourceLotCode,
	               	"__targetLotCode":targetLotCode
	      	});
		<%}else if(CommonUtil.TRANSACTIONTYPE_BON.equals(types)){%>
			var orderNo = $("#__orderNo").val();
			$("#dataTable").datagrid('load',{
					"orderNo":orderNo
	      	});
		<%} else if (types.split(",").length > 1) {%>
			var formObj ={};
			$.each($('#queryTable input'),function(i,v){
				formObj[this.id]=$(this).val();
				if(i==0){
					formObj.__condition=this.id.substring(2)+"_ilikeAnywhere";
				}else{
					formObj.__condition+=":"+this.id.substring(2)+"_ilikeAnywhere";
				}
			});
			$("#dataTable").datagrid('load',formObj);
		<%}%>
	});
	//清除表单内容
	$("#reset").click(function(){
		$("#queryForm").form("clear");
	});
	//清空父页面内容文本框
	$("#empty").click(function(){
		<%
			String kk [] = key.split(",");
	 		for(String k : kk){
		%>
			$(window.parent.document).find('#<%=k%>').val('');
		<%}%>
		<%
			String vv [] = value.split(",");
		 	for(String v : vv){
		%>
			var <%=v%> = $(window.parent.document).find('#<%=v%>');
			if(<%=v%>.attr('combo')){//下拉联想控件
				window.parent.clearCombo(<%=v%>.attr('id'));//父窗口必须存在clearCombo方法
			}else{
				<%=v%>.val('');
			}
	 	<%}%>
	 	parent.$('#<%=divId%>').window('close');//关闭Div 窗口
	 	parent.$("#queryDiv").hide();
	});
});
function text1_onmouseover(it){
    it.focus();   
    it.select();  
 }   
function text1_onmouseout(it){
    it.onblur;
 }
 function orderTypeHuoQu(value,rowData,rowIndex){
 	if(rowData.orderType=='<%=CommonUtil.TRANSACTIONTYPE_SOT%>'){
 		value="应急发货";
 	}else if(rowData.orderType=='<%=CommonUtil.TRANSACTIONTYPE_SIN%>'){
 		value="应急收货";
 	}
 	return value;
 }
</script>
</head>
<body>
	<form id="queryForm">
		<div class="easyui-panel">
			<div style="background: #efefef; width: 100%; float: left">
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-search" style="float: left" id="search">搜索</a>
				<div class="datagrid-btn-separator"></div>
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="icon-undo" style="float: left" id="reset">重置</a>
				<div class="datagrid-btn-separator"></div>
				<a href="#" class="easyui-linkbutton" plain="true"
					iconCls="myCustomerIcon_clear" style="float: left" id="empty">清空</a>
			</div>
			<%
				if (CommonUtil.OPTION_STRATEGY.equals(types)) {
			%>
			<table id="queryTable" style="width: 95%; float: left">
				<tr align="right">
					<td width="15%" align="right">
						<label>
							策略代码:
						</label>
					</td>
					<td width="35%" align="left">
						<input id="__configCode" name="__configCode" size="25"
							class="easyui-validatebox" onkeyup="this.value=this.value.toUpperCase()"/>
						<input type="hidden" id="__condition" name="__condition"
							value="configName_ilikeAnywhere:configCode_ilikeAnywhere" />
					</td>
					<td width="15%" align="right">
						<label>
							策略名称:
						</label>
					</td>
					<td width="35%" align="left">
						<input id="__configName" name="__configName" size="25" />
					</td>
				</tr>
			</table>
		</div>
		<table id="dataTable" style="width: auto; height: auto"
			idField="locPlanConfigUuid" striped="true" singleSelect="true">
			<thead>
				<tr>
					<th field="configCode" width="120px" align="center" td_align="left" >
						策略代码
					</th>
					<th field="configName" width="220px" align="center" td_align="left" >
						策略描述
					</th>
					<th field="configNameEn" width="220px" align="center" td_align="left" >
						策略英文名称
					</th>
				</tr>
			</thead>
		</table>
		<%
			} else if (CommonUtil.TRANSACTIONTYPE_ADJ.equals(types)
						|| CommonUtil.TRANSACTIONTYPE_PDN.equals(types)) {
		%>
		<table id="queryTable" style="width: 95%; float: left">
			<tr align="right">
				<td width="13%" align="right">
					<label>
						序号:
					</label>
				</td>
				<td width="20%" align="left">
					<input id="__seqNo" name="__seqNo" size="17"
						class="easyui-validatebox" />
				</td>
				<td width="13%" align="right">
					<label>
						源货位:
					</label>
				</td>
				<td width="20%" align="left">
					<input id="__sourceLotCode" name="__sourceLotCode" size="17" />
				</td>
				<td width="13%" align="right">
					<label>
						目的货位:
					</label>
				</td>
				<td width="20%" align="left">
					<input id="__targetLotCode" name="__targetLotCode" size="17" />
				</td>
			</tr>
		</table>
		</div>
		<table id="dataTable" style="width: auto; height: auto"
			idField="lcoPlanConfigUuid" striped="true" singleSelect="true">
			<thead>
				<tr>
					<th field="seqNo" width="100%" align="center">
						序号
					</th>
					<th field="batchNo" width="100%" align="center">
						批次号
					</th>
					<th field="itemCode" width="100%" align="center">
						物料编码
					</th>
					<th field="itemSeqno" width="100%" align="center">
						物料属性编码
					</th>
					<th field="goodsDesc" width="100%" align="center">
						货物名称
					</th>
					<th field="marksNumber" width="100%" align="center">
						唛头
					</th>
					<th field="model" width="100%" align="center">
						站型
					</th>
					<th field="spec" width="100%" align="center">
						站号
					</th>
					<th field="packageNo" width="100%" align="center">
						箱单号
					</th>
					<th field="qtyUnitDesc" width="100%" align="center">
						包装单位
					</th>
					<th field="qty" width="100%" align="center">
						包装数量
					</th>
					<th field="decondUnitDesc" width="100%" align="center">
						单位
					</th>
					<th field="secondQty" width="100%" align="center">
						总数量
					</th>
					<th field="netWeight" width="100%" align="center">
						净重
					</th>
					<th field="grossWeight" width="100%" align="center">
						毛重
					</th>
					<th field="length" width="100%" align="center">
						长
					</th>
					<th field="width" width="100%" align="center">
						宽
					</th>
					<th field="height" width="100%" align="center">
						高
					</th>
					<th field="volume" width="100%" align="center">
						体积
					</th>
					<th field="thirdUnitCode" width="100%" align="center">
						规格单位
					</th>
					<th field="thirdQty" width="100%" align="center">
						规格数量
					</th>
					<th field="goodsKind" width="100%" align="center">
						货物种类
					</th>
					<th field="dangerCode" width="100%" align="center">
						货物种类
					</th>
					<th field="barcode" width="100%" align="center">
						货物种类
					</th>
					<th field="dangerCode" width="100%" align="center">
						危险品参数
					</th>
					<th field="sourceLotCode" width="100%" align="center">
						源货位
					</th>
					<th field="targetLotCode" width="100%" align="center">
						目的货位
					</th>
					<th field="remark" width="100%" align="center">
						备注
					</th>
				</tr>
			</thead>
		</table>
		<%
			}else if (CommonUtil.TRANSACTIONTYPE_BON.equals(types)) { %>
			<table id="queryTable" style="width: 95%; float: left">
				<tr align="right">
					<td width="13%" align="right">
						<label>
							应急单号:
						</label>
					</td>
					<td width="20%" align="left">
						<input id="__orderNo" name="__orderNo" size="17"
							class="easyui-validatebox" />
					</td>
					<td width="13%" align="right">
					</td>
					<td width="20%" align="left">
					</td>
					<td width="13%" align="right">
					</td>
					<td width="20%" align="left">
					</td>
				</tr>
			</table>
		</div>
		<table id="dataTable" style="width: auto; height: auto"
			idField="locPlanConfigUuid" striped="true" singleSelect="true">
			<thead>
				<tr>
					<th field="orderNo" width="120px" align="center" td_align="left" >
						应急单号
					</th>
					<th field="orderType" width="60px" align="center" td_align="left" formatter="orderTypeHuoQu">
						类型
					</th>
					<th field="orderDesc" width="150px" align="center" td_align="left" >
						描述
					</th>
					<th field="remark" width="220px" align="center" td_align="left" >
						备注
					</th>
				</tr>
			</thead>
		</table>
	<% }  else if (types.split(",").length > 1) {
					String[] keys = types.split(",");
					String tableName = keys[0];
					String code = keys[1];
					String name = keys[2];
					String nameEn = keys[3];
					String bundle="wlp."+"basicdata";
					if(keys.length>4){
						bundle="wlp."+keys[4];
					}
					String codeKey = tableName + "." + code;
					String nameKey = tableName + "." + name;
					String nameEnKey = tableName + "." + nameEn;
		%>
		<table id="queryTable" style="width: 95%; float: left">
			<tr align="right">
				<td width="13%" align="right">
					<label>
						<bean:message bundle="<%=bundle%>" key="<%=codeKey%>" />:
					</label>
				</td>
				<td width="20%" align="left">
					<input id='<%="__" + code%>' name='<%="__" + code%>' size="17"
						class="easyui-validatebox" onkeyup="this.value=this.value.toUpperCase()" onmouseover="return  text1_onmouseover(this);" onblur="text1_onmouseout(this)" />
				</td>

				<td width="13%" align="right">
					<label>
						<bean:message bundle="<%=bundle%>" key="<%=nameKey %>" />:
					</label>
				</td>
				<td width="20%" align="left">
					<input id='<%="__" + name%>' name='<%="__" + name%>' size="17" onmouseover="return  text1_onmouseover(this);" onblur="text1_onmouseout(this)"/>
				</td>
			</tr>
			<tr>
				<td width="13%" align="right">
					<label>
						<bean:message bundle="<%=bundle%>" key="<%=nameEnKey %>" />:
					</label>
				</td>
				<td width="20%" align="left">
					<input id='<%="__" + nameEn%>' name='<%="__" + nameEn%>' size="17" onmouseover="return   text1_onmouseover(this);" onblur="text1_onmouseout(this)" />
				</td>
			</tr>
		</table>
		</div>
		<table id="dataTable" style="width: auto; height: auto"
			idField="lcoPlanConfigUuid" striped="true" singleSelect="true">
			<thead>
				<tr>
					<th field="<%=code%>"  align="center" td_align="left" width="120px" align="center">
						<bean:message bundle="<%=bundle%>" key="<%=codeKey %>" />
					</th>
					<th field="<%=name%>" align="center" td_align="left" width="220px" align="center">
						<bean:message bundle="<%=bundle%>" key="<%=nameKey %>" />
					</th>
					<th field="<%=nameEn%>" align="center" td_align="left" width="220px" align="center">
						<bean:message bundle="<%=bundle%>" key="<%=nameEnKey %>" />
					</th>
				</tr>
			</thead>
		</table>
		<%
			}%>
	</form>
</body>
</html:html>