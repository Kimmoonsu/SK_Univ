<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.List" %>
<%@ page import = "ac.kr.skuniv.db.ConnectDB" %>
<%@ page import = "ac.kr.skuniv.db.DataModel" %>

<%
	String encode = request.getParameter("encode");
	System.out.println("encode : "+encode);
	ConnectDB connectDB = ConnectDB.getConnectDB();	
	int check = connectDB.finishEnrol(encode);
	System.out.println("check : "  + check);
	out.println(check);
%>


