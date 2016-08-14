
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="ac.kr.skuniv.db.ConnectDB"%>
<%@ page import="ac.kr.skuniv.db.DataModel"%>

<%
	//System.out.println("chattin.jsp 들어옴");
	ConnectDB connectDB = ConnectDB.getConnectDB();
	String encode = (String) session.getAttribute("encode");
		
	
	int check = 0;
	String id = (String)session.getAttribute("id");
	String myname = connectDB.selectUserName(id); 
	if(id==null || id=="" )
		return;
	
	System.out.println("real encode : " + encode);
%>
<html>
<head>
<meta charset="UTF-8">
<title>View Last Meeting</title>
<link rel="stylesheet" href="css/chatting.css">
<link rel="stylesheet" href="css/css.css">
<script src="http://code.jquery.com/jquery-1.7.js"></script>
<script src="last_meeting.js"></script>
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet" type="text/css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>

<body>

	<div class="container theme-showcase" id ="lastchattotal"  style='overflow:scroll; overflow-y: scroll; width:100%; height:80%;'>

		<div class="container" id="parent" ></div>
	</div>
	<br><br><br>
	
	<input type="hidden" id="id" value="<%=id%>">
	<input type="hidden" id="encode" value="<%=encode%>">
	<input type="hidden" id="myname" value="<%=myname%>">
	<div id="messageView"></div>
</body>
</html>
