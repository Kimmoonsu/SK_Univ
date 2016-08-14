<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "ac.kr.skuniv.db.ConnectDB" %>
<%@ page import = "ac.kr.skuniv.db.DataModel" %>
<% request.setCharacterEncoding("utf-8"); %>
<%
	ConnectDB connectDB = ConnectDB.getConnectDB();
	String id = (String)session.getAttribute("id"); 
	System.out.println("내 아이디 : " + id);
	DataModel dataModel = connectDB.selectMyEnrol(id);
	ArrayList<String> array_encode = dataModel.getArray_encode();
	ArrayList<String> array_title = dataModel.getArray_title();
	ArrayList<String> array_owner = dataModel.getArray_owner();
	ArrayList<String> array_guest = dataModel.getArray_guest();
	ArrayList<String> array_guest2 = dataModel.getArray_guest2();
	ArrayList<String> array_guest3 = dataModel.getArray_guest3();
	ArrayList<String> array_guest4 = dataModel.getArray_guest4();
	ArrayList<String> array_guest5 = dataModel.getArray_guest5();
	ArrayList<String> array_date = dataModel.getArray_date();
	
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="enrol.js"></script>
</head>
<link rel="stylesheet" type="text/css" href="css/postit.css">
	<body >
		<%
			for (int i = 0 ; i < array_title.size(); i++)
			{
				
		%>
		
		<div class="quote-container" id ="enrolmain<%=i%>">
			<i class="pin"></i>
			<blockquote class="note yellow">
			<input type="hidden" id ="encode<%=i%>" value="<%=array_encode.get(i)%>">
			회의 날짜 : <%= array_date.get(i) %><br><br>
			개시자 : <%= array_owner.get(i) %><br><br>
			참여자: 
			<%	if (array_guest.get(i)!=null){	 %><%= array_guest.get(i) %><%} %>
			<%	if (array_guest2.get(i)!=null){	 %><br>&nbsp;<%= array_guest2.get(i) %><%} %>
			<%	if (array_guest3.get(i)!=null){	 %><br>&nbsp;<%= array_guest3.get(i) %><%} %>
			<%	if (array_guest4.get(i)!=null){	 %><br>&nbsp;<%= array_guest4.get(i) %><%} %>
			<%	if (array_guest5.get(i)!=null){	 %><br>&nbsp;<%= array_guest5.get(i) %><%} %>
			<cite class="author" id ="title"><%= array_title.get(i) %></cite>
			<div id = "deleteEnrol<%=i%>"></div>
			</blockquote>
		</div>
		<%		
			}
		%>
	</body>
</html>


