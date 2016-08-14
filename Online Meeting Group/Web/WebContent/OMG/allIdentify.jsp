<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "ac.kr.skuniv.db.ConnectDB" %>
<%@ page import = "ac.kr.skuniv.db.DataModel" %>
<% request.setCharacterEncoding("utf-8"); %>
<%
	ConnectDB connectDB = ConnectDB.getConnectDB();
	//String id = (String)session.getAttribute("id"); 
	//System.out.println("내 아이디 : " + id);
	DataModel dataModel = connectDB.getTMember();
	ArrayList<String> array_name = dataModel.getArray_name();
	ArrayList<String> array_department = dataModel.getArray_dept();
	ArrayList<String> array_position = dataModel.getArray_position();
	ArrayList<String> array_email = dataModel.getArray_email();
	ArrayList<String> array_url = dataModel.getArray_url();
%>    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
 <HEAD>
  <style>
	.div{
		left: 220;
		width:400px; height:250px;	
		background-color:#FFFFFF;
		position:absolute;
		text-align:left;
		filter:alpha(opacity=50);
		border-right:20px solid gold;
		border-bottom:1px solid gold;
	}
	hr{
		width: 250;
		color:gold;
		text-align:right;
		margin-right:0;
	}
	.logo{
		filter:alpha(opacity=50);
		-moz-opacity:0.5;
		-khtml-opacity: 0.5;
		opacity: 0.5;
		align: right;
	}
 </style>
  <TITLE> New Document </TITLE>
  <META NAME="Generator" CONTENT="EditPlus">
  <META NAME="Author" CONTENT="">
  <META NAME="Keywords" CONTENT="">
  <META NAME="Description" CONTENT="">
  <script src="http://code.jquery.com/jquery-1.7.js"></script>
 <script src="http://code.jquery.com/jquery-1.7.js"></script>
<script>
		$(document).ready(function(){
		$('img').next().toggle(function(){
			$('#card').attr('visibility', 'hidden');
			$(this).next().hide('slow');
			$(this).animate({
				top:300
			},'slow');
			},
		function(){
			$(this).next().show('slow');
			$('#card').attr('visibility', 'visible');
			$(this).animate({
				top: 0
			},'slow');
		});
	});
</script>

 </HEAD>

 <BODY bgcolor="#D4F4FA">
 <h1>전체명함보기</h1>
 <%
 			System.out.println("namesize : " + array_name.size());
			for (int i = 0 ; i < array_name.size(); i++)
			{
		%>
  
  <img alt="" src="Imagefile/<%=array_url.get(i) %>" width="150" height="200">
		 <h1><%= array_name.get(i) %></h1>
		<div  class="div" style="top:<%=305*i+140 %>px;
		left:370px; " >
		<blockquote class="">
		<img class="logo" src="img/logo.png" width=100 height=100 align="right">
		<br><br><br><br>
			<h3><%= array_name.get(i) %></h3>
			<%= array_department.get(i) %>/
			<%= array_position.get(i) %><br><br>
			<%= array_email.get(i) %><br><br>
			</blockquote>
		</div>
		<br><br>
		<%		
			}
		%>
		
	</body>
</html>