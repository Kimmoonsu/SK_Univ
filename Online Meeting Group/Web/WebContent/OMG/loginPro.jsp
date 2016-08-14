<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="ac.kr.skuniv.db.ConnectDB"%>

<% request.setCharacterEncoding("utf-8");%>

<%
	//사용자가 입력한 아이디와 비밀번호
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	System.out.println("id : " + id);
	System.out.println("passwd: " + passwd);
	ConnectDB connectDB = ConnectDB.getConnectDB();

	
	int check = connectDB.userCheck(id, passwd);

	if (check == 1) //사용자인증에 성공시 세션속성을 설정
	{
		session.setAttribute("id", id);
		connectDB.getMember(id, passwd);
	}
	System.out.println("msg : " + check);
	out.println(check);
%>