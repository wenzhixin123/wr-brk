<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sinotrans.gd.wlp.util.CommonUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<html:html>
<%
	String path = request.getContextPath();
	String modelId = request.getParameter("modelId");
	String tType = request.getParameter("templateType");
	String controlParam = request.getParameter("controlParam");
	String divId = request.getParameter("divId");
	String parameterStr = request.getParameter("parameterStr");
%>
<head>
	<jsp:include page="../../common/imp_easyui.jsp"></jsp:include>
	<style type="text/css">
#tableQueryForm td {
	font-size: 12px;
}
</style>
<script type="text/javascript" src="../../js/PluginDetect.js"></script>
</head>
<!--  打印模版管理页面-->
<script type="text/javascript">
var $$;
function noresults($$) {
}

$(function(){
	$$ = PluginDetect; 
	var DummyPDF = "/Scripts/empty.pdf"; 
	$$.onDetectionDone("PDFReader", noresults, DummyPDF);

	var lastIndex;
	$("#templateDatagird").datagrid({
		height:320,
		pagination:true,
		striped:true,
		fitColumns:true,
		nowrap:true,
		rownumbers:true,
		showFooter:true,
		pageSize:10, //每页显示记录数
		pageList:[10], //可调整每页显示的记录数
		idField:'reportTemplateUuid',
		toolbar:[{
		  text:'打印',//新增按钮
		  id:'nowPrint',
		  iconCls:'icon-print',
		  handler:function(){
				var row = $('#templateDatagird').datagrid('getSelected');//选择模板
				if(row==null){
					alert('请先选择模板！');
				}else{
					if(row.templateCategory=="FASTREPORT"){
						parent.$('#<%=divId%>').window('close');
						openTemplate(row.reportTemplateUuid,row.templateType,'<%=CommonUtil.REPORT_PRINT_CONTROL_TYPE_PRINTNOW%>');
					}else{
						//alert("暂不支持ireport");
						openIreportTemplate(row.reportTemplateUuid,row.templateType,'<%=CommonUtil.REPORT_PRINT_CONTROL_TYPE_PRINTNOW%>');
						//parent.$('#<%=divId%>').window('close');
						setTimeout(function(){parent.$('#<%=divId%>').window('close');},2500);
					}
				}
		  }
	 	 },'-',{
		  text:'打印预览',//打印按钮
		  iconCls:'icon-preview',
		  id:'previewPrint',
		  handler:function(){
		  		var row = $('#templateDatagird').datagrid('getSelected');//选择模板
				if(row==null){
					alert('请先选择模板！');
				}else{
			  		if(row.templateCategory=="FASTREPORT"){
						parent.$('#<%=divId%>').window('close');
						openTemplate(row.reportTemplateUuid,row.templateType,'<%=CommonUtil.REPORT_PRINT_CONTROL_TYPE_PRINT%>');
			  		}else{
						//alert("暂不支持ireport");
			  			parent.$('#<%=divId%>').window('close');
						openIreportTemplate(row.reportTemplateUuid,row.templateType,'<%=controlParam%>');
					}
				}
			}
	  },'-',{
		  text:'下载控件',
		  iconCls:'icon-download',
		  handler:function(){
		  		window.open("<%=path %>/jsp/system/downloadAction.jsp");
			}
	  }],
	  onLoadSuccess: function (data) {
		  var rows = $("#templateDatagird").datagrid("getRows");
		  if(rows.length==1){
			  $("#templateDatagird").datagrid("selectRow",0);
			  var controlParam="<%=controlParam %>";
			  if(controlParam=="printNow"){
				  $("#nowPrint").click();
			  }else{
				  $("#previewPrint").click();
			  }
		  }
      }
	});
});

function closeWindow(){
	var iframe = $("#pdf");
	if (iframe.attachEvent){
		iframe.attachEvent("onload", function(){
	//parent.$('#<%=divId%>').window('close');
	        alert("Local iframe is now loaded1.");
	    });
	} else {
		iframe.onload = function(){
	//parent.$('#<%=divId%>').window('close');
	        alert("Local iframe is now loaded.");
	    };
	}
	document.body.appendChild(iframe);
}

function openIreportTemplate(reportId,templateType,controlParam){
	var strFeatureInfo = "dialogWidth:1000px;dialogHeight:1000px;resizable:no;scroll:no;center:yes;help:no;status:no;nadorned:no";
	var src="<%=path%>/jsp/system/ireportTemplateAccept.jsp;jsessionid=<%=session.getId()%>?modelId=<%=modelId %>&reportId="+reportId+"&templateType="+templateType+"&controlParam="+controlParam+"&parameterStr=<%=parameterStr%>";
	if(controlParam=="printNow"){//直接打印
		var msg = '<iframe id="pdf" style="" title="Contract PDF" src="'+src+'" scrolling="auto" height="1" width="1" frameborder="0"></iframe>';
		if($("#pdf").length == 0) {
			var status = $$.isMinVersion("PDFReader", 0);
			$("#hiddenajaxcontent").show();
			$("#pdf").show();
			$("#hiddenajaxcontent").html(msg);
			$("body").css("cursor", "auto");
			document.getElementById("pdf").src= src;
			//setTimeout(function(){$("#hiddenajaxcontent").hide();},1500);
		} else { //reload existing contract
			var status = $$.isMinVersion("PDFReader", 0);
			if (status >= 0) {
				$("#hiddenajaxcontent").show();
				$("#pdf" ).attr( "src", function ( i, val ) { return val; });
				setTimeout(function(){$("#hiddenajaxcontent").hide();},1500);
			} else if (status == -1) { //no PDF
			} else {
				$("#hiddenajaxcontent").show();
				$("#pdf" ).attr( "src", function ( i, val ) { return val; });
				setTimeout(function(){$("#hiddenajaxcontent").hide()},1500);
			}
		}
	}else{
		var ver = getInternetExplorerVersion();
		if ( ver > -1 ) {
			window.open(src,null,strFeatureInfo);
		} else {
			window.showModalDialog(src,null,strFeatureInfo);
		}
	}
}

function getInternetExplorerVersion() {
//Returns the version of Internet Explorer or a -1
//(indicating the use of another browser).
	var rv = -1; // Return value assumes failure.
	if (navigator.appName == 'Microsoft Internet Explorer') {
	 	var ua = navigator.userAgent;
	 	var re  = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
	 	if (re.exec(ua) != null)
	   		rv = parseFloat( RegExp.$1 );
	} else if (navigator.appName == 'Netscape') {
	    var ua = navigator.userAgent;
	    var re  = new RegExp("Trident/.*rv:([0-9]{1,}[\.0-9]{0,})");
	    if (re.exec(ua) != null)
	      	rv = parseFloat( RegExp.$1 );
	}
	return rv;
}

function openTemplate(reportId,templateType,controlParam){
	var strFeatureInfo = "dialogWidth:1px;dialogHeight:1px;resizable:no;scroll:no;center:yes;help:no;status:no;nadorned:no";
	window.showModalDialog("<%=path%>/jsp/system/printReportTemplateAccept.jsp;jsessionid=<%=session.getId()%>?modelId=<%=modelId%>&reportId="+reportId+"&templateType="+templateType+"&controlParam="+controlParam,null,strFeatureInfo);
}
</script>
<body>
		<table id="templateDatagird"  
			singleSelect="true" idField="reportTemplateUuid" collapsible="true"
			url="<%=path%>/servlet/SinotransServlet?cmd=selectPrintReportTemplate&__templateType=<%=tType %>&__status=Active">
			<!-- url="<%=path%>/servlet/SinotransServlet?cmd=query&queryType=PrintReportTemplateModel&__templateType=<%=tType %>&__status=Active"> -->
			<thead>
				<tr>
					<!-- 
					<th field="templateCode" width="60" align="center">
						模板代码
					</th>
					-->
					<th field="templateName" width="50" align="center">
						模版名称
					</th>
					<th field="templateCategory" width="50" align="center">
						模板分类
					</th>
					<th field="templateType" width="50" align="center">
						模板类型
					</th>
					<th field="templateNameEn" width="50" align="center">
						模版英文描述
					</th>
					<!-- <th field="fileName" width="110" align="center">
						模版文件名
					</th> -->
					<th field="fileVersion" width="50" align="center">
						版本号
					</th>
					<th field="creator" width="65" align="center">
						创建人
					</th>
					<!-- 
					<th field="modifier" width="60" align="center">
						修改人
					</th>
					<th field="modifyTime" width="40" align="center">
						修改时间
					</th>
				   -->
				</tr>
			</thead>
		</table>
		<div id="hiddenajaxcontent" style="display: none;">
		<!-- <iframe id="pdf" style="" title="Contract PDF" src="" scrolling="auto" height="1" width="1" frameborder="0"></iframe> -->
		
		</div>
</body>
</html:html>