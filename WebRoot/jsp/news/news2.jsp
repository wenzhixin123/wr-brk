<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String uuid = request.getParameter("uuid");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<style type="text/css">
		body {
			background: white;
		}
	</style>

  </head>
  <body>

		<iframe id="showNews"  width="100%" height="100%" frameborder="0" src="<%=path%>/showNewsAction.do?uuid=<%=uuid %>">
		</iframe>
  </body>
</html>
