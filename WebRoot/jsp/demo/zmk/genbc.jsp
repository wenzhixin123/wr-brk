<html>
<body>
<%
    String url = "genbc?type=code128&msg=123abc456&fmt=png";
%>
<img width="250" height="80" src="<%= request.getContextPath() + "/" + url%>"/>
</body>
<script>
</script>
</html>