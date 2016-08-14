<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ac.kr.skuniv.db.ConnectDB"%>
<%@ page import="ac.kr.skuniv.db.DataModel"%>

<%
	String id = request.getParameter("id");
	String encode = request.getParameter("encode");
	String msgText = request.getParameter("msgText");
	System.out.println("=====inputMessage 실행중====");
	System.out.println("id = " + id);
	System.out.println("encode = " + encode);
	System.out.println("msgText = " + msgText);
	
	ConnectDB connectDB = ConnectDB.getConnectDB();
	String url = connectDB.selectURL(id);
	String name = connectDB.selectUserName(id);
	String current_contents = connectDB.selectCurrentContents(encode);
	connectDB.insertMessage(encode, name, msgText,current_contents, url);
%>
<html>

<body>나다!</body>
</html>