<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%
	String sid = request.getSession().getId();
	out.print(sid);
	out.flush();
%>