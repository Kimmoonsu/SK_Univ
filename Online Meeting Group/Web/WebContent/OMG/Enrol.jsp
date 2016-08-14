<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="ac.kr.skuniv.db.ConnectDB" %>

<%
	ConnectDB connectDB = ConnectDB.getConnectDB();
	
	request.setCharacterEncoding("utf-8");
	String me = (String)session.getAttribute("id");
	
	String selectName = request.getParameter("selectname");
	String title = request.getParameter("title");
	selectName = selectName.replaceFirst("#", "");
	String month = request.getParameter("month");
	String day = request.getParameter("day");
	String hour = request.getParameter("hour");
	String minute = request.getParameter("minute");
	
	System.out.println("title : " + title);
	System.out.println("selectName: " + selectName);
	String guest[] = selectName.split("#");
	for (int i = 0 ; i < guest.length; i++)
	{
		System.out.println("guest : " + guest[i]);
	}
	System.out.println("month : " + month);
	System.out.println("me : " + me);
	
	int check = connectDB.insertEnrol(me, guest, title, month, day, hour, minute);	
	System.out.println("check : " + check);
	out.println(check);
%>
