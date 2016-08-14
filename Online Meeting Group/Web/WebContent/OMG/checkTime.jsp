<%@page import="java.util.ArrayList"%>
<%@page import="ac.kr.skuniv.db.ConnectDB"%>
<%@page import="ac.kr.skuniv.db.DataModel"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	ConnectDB connectDB = ConnectDB.getConnectDB();
	String id = (String)session.getAttribute("id");
	int count=0;
	int check[] = connectDB.selectTime(id);
	for(int i=0;i<check.length;i++){
		count = (check[i]==1)?(count+1):count;
	}
	System.out.println("count  : " + count);
	out.println(count);

%>





