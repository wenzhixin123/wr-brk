<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sinotrans.gd.wlp.common.web.RcUtil"%>
<%@page import="java.util.Locale"%>
<%
	String path = request.getContextPath();
	String errorInfo = request.getParameter("msg");
	String msg = "";
	if("UsernameNotFoundException".equals(errorInfo)){
		msg = "用户名不存在!";
	}else if("BadCredentialsException".equals(errorInfo)){
		msg = "密码输入有误!";
	}else if("ValidationCodeErrorException".equals(errorInfo)){
		msg = "验证码输入有误!";
	}
	request.getSession().setAttribute("REMOTE_COMPUTER_NAME",request.getRemoteHost());
	request.getSession().setAttribute("REMOTE_COMPUTER_ADDR",request.getRemoteAddr());
%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>王牌WMS系统</title>
		<link rel="stylesheet" type="text/css" href="<%=path%>/css/login.css"/>
		<jsp:include page="common/imp_jquery.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
$(function(){
	$("#language").change(function(){
		window.location.href="<%=path%>/changeLanguage.do?language="+$(this).val();
	});
	$("#vCode").click(function(){
		window.location.reload();
	});
	$("#j_username").focus();
	$("#j_username").unbind("keydown").keydown(function(e){
		if(e.keyCode == 13 ) {
			$("#j_password").unbind("foucs").focus();
		}
	});
	$("#j_password").unbind("keydown").keydown(function(e){
		if(e.keyCode == 13 ) {
			$("#language").unbind("foucs").focus();
		}
	});
	$("#language").unbind("keydown").keydown(function(e){
		if(e.keyCode == 13 ) {
			$("#j_validation_code").unbind("foucs").focus();
		}
	});
	$("#j_validation_code").unbind("keydown").keydown(function(e){
		if(e.keyCode == 13 ) {
			$("#form")[0].submit();
		}
	});
	$("#loginButton").unbind("click").click(function(){
		var j_username = $("#j_username").val();
		if(j_username==null||j_username.length<1){
			alert("请输入用户名!");
			return;
		}
		var j_password = $("#j_password").val();
		if(j_password==null||j_password.length<1){
			alert("请输入密码!");
			return;
		}
		$("#form")[0].submit();
	});
	
	//WLPDC-59
	$("#publicButton").unbind("click").click(function(){
		$("#j_username").val('PUBLIC01');
		$("#j_password").val('public');
		$("#form")[0].submit();
	});
	
	$("#publicGYButton").unbind("click").click(function(){
		$("#j_username").val('PUBLICGY');
		$("#j_password").val('public');
		$("#form")[0].submit();
	});
	
	$("#j_username").keyup(function(e){
		var str = $(this).val();
		str = str.toLocaleUpperCase();
		$(this).val(str);
	});
});
</script>
	</head>
	<body>
<form id="form" name="form" method="post" action="login.action">
		<div class="login">
			<div class="loginDiv">
				<div class="sysLogo"></div>
				<div class="loginTop"></div>
				<div class="loginBottom">
					<table width="80%" border="0" cellspacing="0" cellpadding="0"
						align="center">
						<tr>
							<th>
								<bean:message bundle="wlp.common" key="login.username"/>:
							</th>
							<td>
								<label>
									<input type="text" name="j_username" id="j_username"/>
								</label>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message bundle="wlp.common" key="login.password"/>:
							</th>
							<td>
								<label>
									<input type="password" name="j_password" id="j_password"/>
								</label>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message bundle="wlp.common" key="login.language"/>:
							</th>
							<td>
								<label>
									<select id="language" name="language">
										<%
											String language = (String)session.getAttribute("language");
											Locale currentLocale = Locale.getDefault();
											if(RcUtil.isEmpty(language)){
												language = currentLocale.getLanguage();
											}
										 %>
										<option value="zh" <%if(!RcUtil.isEmpty(language) && "zh".equals(language)){out.print("selected");} %>>中文</option>
										<option value="en" <%if(!RcUtil.isEmpty(language) && "en".equals(language)){out.print("selected");} %>>English</option>
									</select>
								</label>
							</td>
						</tr>
						<%-- <tr>
							<th>
								验证码:
							</th>
							<td>
								<input id="j_validation_code" name="j_validation_code" size="5" maxlength="4"/>&nbsp;
								<img src="<%=path %>/ImageValidationCodeServlet" alt="请输入验证码" width="64" height="25" id="vCode"/>
							</td>
						</tr> --%>
						<tr>
							<td>
							</td>
							<td>
								<label>
									<input type="button" name="loginButton" id="loginButton" value="登 录" class="iput_bnt" />
								</label>
								&nbsp;&nbsp;
								<!-- <label>
									<input type="button" name="publicButton" id="publicButton" value="公共服务 " class="iput_bnt" />
								</label> -->
							</td>
						</tr>
						<tr>
							<td>
							</td>
							<td>
								<!-- <label>
									<input type="button" name="publicGYButton" id="publicGYButton" value="广运船务有限公司订舱系统" class="iput_bnt2" />
								</label> -->
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
</form>
<br/>
<div align="center">
	<p>
		&nbsp;&nbsp;
	</p>
	<p>浏览器下载：<a href="http://www.firefox.com.cn/download/">Firefox</a>&nbsp;&nbsp;||&nbsp;&nbsp;<a href="http://www.google.com/chrome/intl/zh-CN/landing_chrome.html?brand=CHTF&utm_campaign=zh_cn&utm_source=zh_cn-ha-apac-zh_cn-sk&utm_medium=ha">Chrome</a></p>
</div>
<%
	if(!RcUtil.isEmpty(msg)){
		out.println("<script language=\"javascript\" type=\"text/javascript\">");
		out.println("alert(\""+msg+"\");");
		out.println("</script>");
	}
%>
</body>
</html:html>