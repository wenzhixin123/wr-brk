<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sinotrans.gd.wlp.system.entity.SystemNewsEntity"%>
<%@page import="com.sinotrans.gd.wlp.common.web.RcUtil"%>
<%
String path = request.getContextPath();
SystemNewsEntity systemNewsEntity = (SystemNewsEntity)request.getSession().getAttribute("systemNewsEntity");
String uuid=systemNewsEntity.getSysNewsUuid();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
  <head>
    <title></title>
	<jsp:include page="../../common/imp_dwr.jsp"></jsp:include>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.6.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.json-2.3.js"></script>
	
	<script language="javascript" type="text/javascript">
	var uuid = '<%=systemNewsEntity.getBasBlobUuid()%>';
	$(function(){
		sysNewsInit(uuid);
	});
	
	function downloadFile() {
		if(uuid && uuid!="null"){
			var url= "<%=path%>/sysNewsDownFileAction.do?basBlobUuid="+uuid;
			window.open(url);
		}else{
			alert("还未上传附件!");
		}
	}
		
		//控制下载的显示
		function sysNewsInit(uuid){
			if(uuid && uuid!="null"){
			 	document.getElementById("downLoadFile").style.display = "block";
			}else{
				document.getElementById("downLoadFile").style.display = "none";

			}
		}
	</script>
  </head>
  <body>
      <div class="" style="width:1000px;  margin:0 auto; border-style:solid;border-width:1pt; border-color: lightskyblue">
	  	<div id="content" style="width:900px;  margin:0 auto; " >
		  	<table style="width:900px;">
		  	    <tr>
		  	   		 <td>
		  	   		     <p id="newsTitle" style="font-size: 25px; color:blue; text-align:center;  padding:30px 0px 0px 0px;" ><%=systemNewsEntity.getTitle()%></p>
		  	   		 </td>
		  	    </tr>
			  	<tr>
				  	<td  align="left">
				  	<a><img width="900px"  src="<%=path %>/jsp/news/ueditor/dialogs/image/line.jpg"/></a>
				  	<%  String date = RcUtil.date2String(systemNewsEntity.getCreateTime(),RcUtil.yyyy_MM_dd_HH_mm_ss);%>
				  	 <p style="padding:5px 1px 30px 350px; font-size:11pt;">日期:<%= date%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布人:<%=systemNewsEntity.getUserName()%></p>
				  	</td>
			  	</tr>
			  	<tr>
				  	<td>
			  		<%=systemNewsEntity.getStrContent()%>
				  	</td>
			  	</tr>
			  	<tr>
			  		<td align="right" id="downLoadFile"  >
			  			<a  style="font-size:13px; color: blue; cursor:pointer; " onclick="downloadFile()" >【下载附件】</a>
			  		</td>
			  	</tr>
		  	</table>
	  	</div>
     </div>
  </body>
</html>
