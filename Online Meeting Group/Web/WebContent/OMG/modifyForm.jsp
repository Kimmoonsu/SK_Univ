<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "ac.kr.skuniv.db.ConnectDB" %>
<%@ page import = "ac.kr.skuniv.db.DataModel" %>

<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<link rel="stylesheet" href="../css/style.css"/>
<script src="../js/jquery-1.11.3.min.js"></script>
<script src="modify.js"></script>

<% request.setCharacterEncoding("utf-8");%>

<% 
  String id = (String)session.getAttribute("id");
  String passwd = request.getParameter("passwd");

  ConnectDB connectDB= ConnectDB.getConnectDB();
  //아이디와 비밀번호에 해당하는 사용자의 정보를 얻어냄
  DataModel dataModel = connectDB.getMember(id,passwd); 
    
  try{//얻어낸 사용자 정보를 화면에 표시
%>

<div id="regForm" class="box">
   <ul>
      <li><p class="center">회원 정보 수정
      <li><label for="id">아이디</label>
          <input id="id" name="id" type="email" size="20" 
           maxlength="50" value="<%=id%>" readonly disabled>
      <li><label for="passwd">비밀번호</label>
          <input id="passwd" name="passwd" type="password" 
           size="20" placeholder="6~16자 숫자/문자" maxlength="16">
      <li><label for="name">이름</label>
          <input id="name" name="name" type="text" 
           size="20" maxlength="10" value="<%=dataModel.getName()%>">
      <li><label for="email">이메일</label>
          <input id="email" name="email" type="text" 
           size="20" maxlength="10" value="<%=dataModel.getEmail()%>">
      <li><label for="department">부서</label>
          <input id="department" name="department" type="text" 
           size="20" maxlength="10" value="<%=dataModel.getDepartment()%>">
      <li><label for="position">직급</label>
          <input id="position" name="position" type="text" 
           size="20" maxlength="10" value="<%=dataModel.getPosition()%>">
                           
                
      <li class="label2"><button id="modifyProcess">수정</button>
          <button id="cancle">취소</button>
   </ul>
</div>
<%}catch(Exception e){}%>