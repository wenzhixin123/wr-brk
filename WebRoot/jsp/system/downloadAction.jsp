<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="java.net.URLEncoder" />
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
	</head>
<body>
		<%
			response.setContentType("application/x-download");
			String url = "/SinotransFastReport.zip";
			String fileName = "SinotransFastReport.zip";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			RequestDispatcher rd = application.getRequestDispatcher(url);
			rd.forward(request, response);
			response.flushBuffer();
			response.reset();
	        out.clear();
	        out=pageContext.pushBody();
		%>
</body>
</html>