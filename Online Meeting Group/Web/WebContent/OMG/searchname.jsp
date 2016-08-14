<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "ac.kr.skuniv.db.ConnectDB" %>
<%@ page import = "ac.kr.skuniv.db.DataModel" %>

<% request.setCharacterEncoding("utf-8"); %>

<html>
<head>
<body>
<p>
<%
	ConnectDB connectDB = ConnectDB.getConnectDB();
	String name = request.getParameter("search");
	
	DataModel dataModel = connectDB.searchName(name);
	System.out.println("입력한 값 : " + name);
	ArrayList<String> array_name = dataModel.getArray_name();
	ArrayList<String> array_id= dataModel.getArray_id();
	ArrayList<String> array_url= dataModel.getArray_url();
	if (array_name.size()==0)
	{
%>
		입력한 사용자가 없습니다.<br>
	<%
	}
	else
	{
		 for (int i = 0 ; i < array_name.size(); i++)
		{
%>
		 <img alt="" src="Imagefile/<%=array_url.get(i)%>" width="50" height="50">
		<input type="checkbox" id = "selectname" name="selectname" value="<%=array_id.get(i) %>"><%=array_name.get(i) %>
        
        
<%
		}	
	}

%>
</p>
</body>
</head>
</html>
