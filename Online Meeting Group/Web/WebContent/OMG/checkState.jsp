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
		out.println(check);
	}
	for (int i = 0; i < array_state.size(); i++)
	{
		if (array_state.get(i) == 1)
		{
			encode = array_encode.get(i);
			check = 1;
			out.println(check);
			break;//일단 하나만 띄움
		}
		
	}
	System.out.println("real encode : " + encode);
%>