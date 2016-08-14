<?xml version="1.0" encoding="euc-kr" ?>
<%@ page contentType = "text/xml; charset=euc-kr" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.List" %>
<%@ page import = "ac.kr.skuniv.db.ConnectDB" %>
<%@ page import = "ac.kr.skuniv.db.DataModel" %>


<%
	String encode = request.getParameter("encode");
	System.out.println("encode : "+encode);
	ConnectDB connectDB = ConnectDB.getConnectDB();	
	DataModel dataModel = connectDB.selectContents(encode);
%>
<Contents>
	<%for (int i = 0 ; i < dataModel.chat_name.size(); i++)
	 {	
	%>
	<Message>
		<name><%=dataModel.chat_name.get(i)%></name>
	 	<text><%=dataModel.chat_msg.get(i)%></text>
	 	<time><%=dataModel.chat_time.get(i)%></time>
	 	<url><%=dataModel.chat_url.get(i)%></url>
	</Message>
	<%} %>
</Contents>