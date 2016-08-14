<%@page import="java.net.URLDecoder"%>
<%@ page import="com.oreilly.servlet.MultipartRequest,com.oreilly.servlet.multipart.DefaultFileRenamePolicy,java.util.*,java.io.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "ac.kr.skuniv.db.ConnectDB" %>


<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="../css/style.css"/>
<script src="../js/jquery-1.11.3.min.js"></script>

<% request.setCharacterEncoding("utf-8");%>
<!--<jsp:useBean id="member" class="ac.kr.skuniv.db.DataModel">
    <jsp:setProperty name="member" property="*" />
</jsp:useBean>-->

<%  
	
	ConnectDB connectDB = ConnectDB.getConnectDB();
	  //사용자가 입력한 데이터저장빈 객체를 가지고 회원가입 처리 메소드호출
	//connectDB.insertMember(member);
	
	String realFolder = "";
	String filename1 = "";
	int maxSize = 1024*1024*5;
	String encType = "utf-8";
	String savefile = "img";
  	ServletContext scontext = getServletContext();
 	String savePath = connectDB.savePath; 
	System.out.println("사진 저장");
	System.out.println("=====사진 들어옴====");
	try{
		  MultipartRequest multi=new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy());
		  
		  String id = multi.getParameter("id");
		  String passwd = multi.getParameter("passwd");
		  String name = multi.getParameter("name");
		  String department = multi.getParameter("department");
		  String position = multi.getParameter("position");
		  String email = multi.getParameter("email");
		  
		  System.out.println("id : " + id);
		  System.out.println("passwd : " + passwd);
		  System.out.println("name : " + name);
		  System.out.println("department : " + department);
		  System.out.println("position : " + position);
		  System.out.println("email : " + email);
		  Enumeration<?> files = multi.getFileNames();
		     String file1 = (String)files.nextElement();
		     filename1 = multi.getFilesystemName(file1);
		     System.out.println("filename : " +filename1);
		     connectDB.insertMember(id, passwd, name, department, position, email, filename1);  
		 } catch(Exception e) {
		  e.printStackTrace();
		 }
  
%>