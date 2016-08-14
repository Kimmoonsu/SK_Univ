<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="ac.kr.skuniv.db.ConnectDB"%>
<%@ page import="ac.kr.skuniv.db.DataModel"%>


<%
	System.out.println("chattin.jsp 들어옴");
	ConnectDB connectDB = ConnectDB.getConnectDB();
	int check = 0;
	String id = (String)session.getAttribute("id");
	
	String myname = connectDB.selectUserName(id); 
	if(id==null || id=="" )
		return;
	DataModel dataModel = connectDB.selectMyEnrol(id);
	ArrayList<Integer> array_state = dataModel.getArray_state();
	ArrayList<String> array_encode = dataModel.getArray_encode();
	String encode = null;
	if (array_state.size() == 0)
	{
		
	}
	for (int i = 0; i < array_state.size(); i++)
	{
		if (array_state.get(i) == 1)
		{
			encode = array_encode.get(i);
			break;//일단 하나만 띄움
		}
		
	}
	System.out.println("real encode : " + encode);
%>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up/Login Box</title>
<link rel="stylesheet" href="css/chatting.css">
<link rel="stylesheet" href="css/css.css">
<script src="http://code.jquery.com/jquery-1.7.js"></script>
<script src="chatting.js"></script>
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet" type="text/css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>

<body>
	<script>
		
	</script>
	<div class="container theme-showcase" id ="chattotal"  style='overflow:scroll; overflow-y: scroll; width:100%; height:80%;'>
		<div class="" id="parent" ></div>
	</div>
	<br><br><br>
	<form id="msgsubmit" method="post">
		<input name="text" type="text" id = "msgText"
			style="opacity: 0.7; background-color: rgb(255, 255, 255); background-position: initial initial; background-repeat: initial initial;">

		<input type="submit" id="msgbtn" value="전송">
		<input type="button" id="finish" value="회의종료">
	</form>
	
	<input type="hidden" id="id" value="<%=id%>">
	<input type="hidden" id="encode" value="<%=encode%>">
	<input type="hidden" id="myname" value="<%=myname%>">
	<div id="messageView"></div>
</body>
</html>
