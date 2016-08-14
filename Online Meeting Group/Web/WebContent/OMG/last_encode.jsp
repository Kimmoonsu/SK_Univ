<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "ac.kr.skuniv.db.ConnectDB" %>
<%@ page import = "ac.kr.skuniv.db.DataModel" %>
<% request.setCharacterEncoding("utf-8"); %>
<%
	String encode = request.getParameter("encode");
	System.out.println("지난회의에서 받아온 encode값 : " + encode);
	session.setAttribute("encode", encode);
	out.println(1);
%>
