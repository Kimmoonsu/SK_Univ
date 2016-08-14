<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="ac.kr.skuniv.db.ConnectDB" %>

<%
request.setCharacterEncoding("utf-8");
	String encode = request.getParameter("encode");
	ConnectDB connectDB = ConnectDB.getConnectDB();
	int check = connectDB.deleteEnrol(encode);
		
	System.out.println("check : " + check);
	out.println(check);
%>
