<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<%@page import="com.sinotrans.gd.wlp.common.web.RcUtil"%>
<%@page import="com.sinotrans.gd.wlp.common.web.EC_CommonUtil"%>
<%@page import="com.sinotrans.gd.wlp.common.web.HUB_CommonUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<html:html>
<%
	//从js传输过来的参数
	String path = request.getContextPath();
	String fileType = request.getParameter("fileType");
	String businessType = request.getParameter("businessType");
	String divId = request.getParameter("divId");
	String functionName = request.getParameter("functionName");
	String modelIds=request.getParameter("modelIds");
	String versionType=RcUtil.isEmpty(request.getParameter("versionType"))?"":request.getParameter("versionType");
	
	Boolean result = (Boolean)request.getAttribute("result");
	String error = (String)request.getAttribute("error");
	String msg = (String)request.getAttribute("msg");
	
	
	if(RcUtil.isEmpty(divId)){
		divId = (String)request.getAttribute("divId");
	}
	if(RcUtil.isEmpty(fileType)){
		fileType = (String)request.getAttribute("fileType");
	}
	if(RcUtil.isEmpty(businessType)){
		businessType = (String)request.getAttribute("businessType");
	}
	if(RcUtil.isEmpty(functionName)){
		functionName = (String)request.getAttribute("functionName");
	}
	if(RcUtil.isEmpty(modelIds)){
		modelIds=(String)request.getAttribute("modelIds");
	}
	if(RcUtil.isEmpty(versionType)){
		versionType=(String)request.getAttribute("versionType");
	}
%>
<head>
	<jsp:include page="../../common/imp_easyui.jsp"></jsp:include>
	<script type="text/javascript" src="<%=path%>/fros-easyui/js/dwr-2.0.5/dwr_engine.js"></script>
	<script type="text/javascript" src="<%=path%>/fros-easyui/js/dwr-2.0.5/dwr_util.js"></script>
	<script type="text/javascript" src="<%=path%>/dwr_interfaces.js"></script>
	<script type="text/javascript">
	$(function(){
		fileEach();
		$("#uploadButton").click(function(){
			$("form")[0].submit();
			$(this).linkbutton('disable').unbind("click");
		});
		if('<%=versionType %>'!="null"){
			loadOfficeCode();
		}
	});
	function pd(format){
		<%
			if(!RcUtil.isEmpty(fileType)){
				String ft [] = fileType.split(",");
				out.print("if(");
				int index =1;
				for(String ext : ft){
				//	if(index % 2 ==0 ){
				//		out.print("format.toLowerCase()!=\""+ext+"\".toLowerCase() ");
				//	}else{
				//		out.print("format.toLowerCase()!=\""+ext+"\".toLowerCase() &&");
				//	}
				//	index ++;
					if(index==ft.length){
						out.print("format.toLowerCase()!=\""+ext+"\".toLowerCase()");
					}else{
						out.print("format.toLowerCase()!=\""+ext+"\".toLowerCase() &&");
					}
					index ++;
				}
				out.println("){");
				out.println("alert('只支持 "+fileType+" 格式文件');");
				out.println("return false;");
				out.println("}");
			}else{
		%>
			if(format!=".xls".toLowerCase() && format!=".xlsx".toLowerCase()){
				alert("只支持 <%=fileType%> 格式文件");
				return false;
			}
		<%}%>
		return true;
	}
	function fileEach(){
		$(":file").each(function(){
			$(this).change(function(){
				var fileName = $(this).val();
				if(fileName!=null && fileName.length > 0){
					var index = fileName.toLowerCase().indexOf(".");
					if(!pd(fileName.substr(index,fileName.length))){
						var idForName = $(this).attr("id");
						var inputFile = $("<a>请选择文件:</a><input type=\"file\" name=\""+idForName+"\" id=\""+idForName+"\" title=\"请选择文件\"/>");
						$("#uploadDiv").html(inputFile);
						fileEach();
					}
				}
			});			
		});
	}
	
	function loadOfficeCode(){
		ECInboundManager.getSubOfficeCode(function(data){
			var result=eval(data);
			$("#officeCode").combotree("tree").tree("loadData",result);
			if(result[0].children==""){
				$("#officeCode").combotree("setValue",result[0].id);
				$('#officeCode').combotree('disable');
			}
		});
		$("#officeCode").combotree({
				checkbox:true,
				onClick:function(node){
					var isleaf=$('.tree').tree('isLeaf',node.target);
					if(!isleaf){
						$.messager.alert("提示",'此节点不是菜单节点、请明确后重新选择！',"info");
						$('#officeCode').combotree('clear');
					}
				}	
		});
	}
	</script>
</head>
<body>
<%if(!RcUtil.isEmpty(result)){ %>
<script type="text/javascript">
	var flag = <%=result%>;
	if(!flag){
		alert("<%=error%>");
	}else{
		<%if (!RcUtil.isEmpty(functionName)) {
			out.print("window.parent."+functionName+"("+request.getAttribute("spj")+")");//运行父窗口处理返回对象function
		}%>
		parent.$("#<%=divId%>").window("close");
	}
</script>
<%}%>
	<html:form action="/upload.do" enctype="multipart/form-data">
		<div>
			<div id="uploadDiv">请选择文件:<input type="file" id="busFile" name="busFile" title="请选择文件"  /></div>
			<br />
			<br />
			<%
				if(CommonUtil.INBOUND_IMPORT.equals(businessType)){
			%>
			请选择类型:
			<select id="businessFileType" name="businessFileType" class="easyui-combobox" panelHeight="auto" editable="false">
				<option value="0">主设备送货单</option>
				<option value="1">配套设备送货单</option>
				<option value="2">无订单工程物料申请单</option>
				<option value="3">移动明细清单</option>
			</select>
			<%}else if(CommonUtil.INBOUND_JPG_PNG.equals(businessType)){%>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				描述：<input type="text" id="remark" name="remark" size="15"/>
			<%}else if(HUB_CommonUtil.HUB_LOGISTICSORDER_IMPORT.equals(businessType)){%>
				请选择类型:
				<select id="businessFileType" name="businessFileType" class="easyui-combobox" panelHeight="auto" editable="false">
					<option value="0">入库作业单</option>
					<option value="1">关联文件</option>
				</select>
			<%}%>
			<%if(EC_CommonUtil.EC_CHOOSEOFFICECODE.equals(versionType)){%>
				&nbsp;&nbsp;所属仓库:<input name="officeCode" id="officeCode" class="easyui-combotree" panelHeight="83" style="width:210px;" />
			<%}%>
		</div>
		<br/>
		<br/>
		<div align="center">
			<input type="hidden" id="divId" name="divId" value="<%=divId %>"/>
			<input type="hidden" id="fileType" name="fileType" value="<%=fileType %>"/>
			<input type="hidden" id="businessType" name="businessType" value="<%=businessType %>"/>
			<input type="hidden" id="functionName" name="functionName" value="<%=functionName %>"/>
			<input type="hidden" id="modelIds" name="modelIds" value="<%=modelIds %>"/>
			<input type="hidden" id="versionType" name="versionType" value="<%=versionType %>"/>
			<a id="uploadButton" name="uploadButton" href="#" class="easyui-linkbutton">&nbsp;&nbsp;上&nbsp;&nbsp;传&nbsp;&nbsp;</a>
		</div>
	</html:form>
</body>
</html:html>