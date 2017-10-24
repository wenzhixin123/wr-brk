<%@ page language="java" pageEncoding="utf-8"%><%@ page import="java.io.*" %><%@ page import="com.sinotrans.framework.web.filter.DESVcodeFilter" %><%  
	String ip = request.getServerName();
	int port = request.getServerPort();
	String app = request.getContextPath();
	
	String reportId = request.getParameter("reportId");
	String templateType = request.getParameter("templateType");
	String controlParam = request.getParameter("controlParam");
	String fileName = request.getParameter("fileName")+".jrxml";
	String jsessionid = session.getId();// request.getParameter("jsessionid");
	String officeCode = request.getParameter("officeCode");
	String desKey = DESVcodeFilter.getSigleInstance().getDES_KEY();
	String download = ip + ":" + port + "/ireport/iReport-5.5.0-sinotrans-32bit-windows-installer.zip";
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Expires", "0");
	response.setHeader("Content-Disposition", "filename=\"open_iReport.jnlp\";");
	response.setContentType("application/x-java-jnlp-file");
	
	OutputStream output = response.getOutputStream();

	String responseData = 
		"<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
		"<jnlp spec=\"1.0+\" codebase=\"http://" + ip + ":" + port + app + "/jnlp/\">" + 
			"<information>" + 
				"<title>Open Ireport</title>" +
				"<vendor>iReport</vendor>" +
				"<homepage href=\"http://" + ip + ":" + port + app + "/jnlp/\"/>" +
				"<description>Open Ireport</description>" +
			"</information>" +
			"<security>" +
				"<all-permissions/>" +
			"</security>" +
			"<resources>" +
				"<j2se version=\"1.6+\"/>" +
				"<jar href=\"openIreport.jar\"/>" +
			"</resources>" +
			"<application-desc main-class=\"com.ireport.OpenIreport\">" +
				"<argument>" + reportId + "</argument>" +
				"<argument>" + templateType + "</argument>" +
				"<argument>" + controlParam + "</argument>" +
				"<argument>" + fileName + "</argument>" +
				"<argument>" + ip + "</argument>" +
				"<argument>" + port + "</argument>" +
				"<argument>" + app + "</argument>" +
				"<argument>" + jsessionid + "</argument>" +
				"<argument>" + officeCode + "</argument>" +
				"<argument>" + desKey + "</argument>" +
				"<argument>" + download + "</argument>" +
			"</application-desc>" +
		"</jnlp>";
		
	output.write(responseData.getBytes());
	output.flush();
	output.close();
/*
output.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>".getBytes());
outInput.println("<jnlp spec=\"1.0+\" codebase=\"http://" + ip + ":" + port + app + "/jnlp/\">");
outInput.println("<information>");
outInput.println("  <title>Open Ireport</title>");
outInput.println("  <vendor>iReport</vendor>");
outInput.println("  <homepage href=\"http://" + ip + ":" + port + app + "/jnlp/\"/>");
outInput.println("  <description>Open Ireport</description>");
outInput.println("</information>");
outInput.println("<security>");
outInput.println("  <all-permissions/>");
outInput.println("</security>");
outInput.println("<resources>");
outInput.println("  <j2se version=\"1.6+\"/>");
outInput.println("  <jar href=\"openIreport.jar\"/>");
outInput.println("</resources>");
   
outInput.println("<application-desc main-class=\"com.ireport.OpenIreport\">");
outInput.println("<argument>"+ reportId +"</argument>");
outInput.println("<argument>"+ templateType +"</argument>");
outInput.println("<argument>"+ controlParam +"</argument>");
outInput.println("<argument>"+ fileName +"</argument>");
outInput.println("<argument>"+ ip +"</argument>");
outInput.println("<argument>"+ port +"</argument>");
outInput.println("<argument>"+ app +"</argument>");
outInput.println("<argument>"+ jsessionid +"</argument>");
outInput.println("<argument>"+ officeCode +"</argument>");
outInput.println("<argument>"+ desKey +"</argument>");
outInput.println("<argument>"+ download +"</argument>");
outInput.println("</application-desc>");
outInput.println("</jnlp>");
outInput.flush();
outInput.close();
*/
%>