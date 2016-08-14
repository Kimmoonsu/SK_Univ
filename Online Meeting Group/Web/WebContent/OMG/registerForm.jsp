<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
    <link rel="shortcut icon" href="img/favicon.png">

    <title>Login page</title>

    <!-- Bootstrap CSS -->    
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="css/elegant-icons-style.css" rel="stylesheet" />
    <link href="css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/style-responsive.css" rel="stylesheet" />
	<script src="http://code.jquery.com/jquery-1.7.js"></script>
<script src="login.js"></script>	
<script src="http://code.jquery.com/jquery-1.7.js"></script>
<script src="register.js"></script>

<script>
$(document).ready(function (event) {
	
	$('#passwd').keypress(function() {
						var inputLength = $(this).val().length;
	
	var remain = 10 - inputLength;
	if (remain < 0)
	{
		alert("10자 넘어섰습니다.");
		return false;
	}
	});

});
</script>

</head>
<body class="register-img-body">

<form method="post" accept-charset="utf-8" enctype="multipart/form-data" id='regform'>
	<br><br><br>
   <ul>
      <li><label for="id">아이디</label>
          <input id="id" name="id" type="text" maxlength="50"  autofocus>
          <input type='button' id="checkId" class="btn btn-danger" value="ID중복확인">
      <li><label for="passwd">비밀번호</label>
          <input id="passwd" name="passwd" type="password" 
           size="20" placeholder="6~16자 숫자/문자" maxlength="16">
      <li><label for="repass">비밀번호 재입력</label>
          <input id="repass" name="repass" type="password" 
           size="20" placeholder="비밀번호재입력" maxlength="16">
      <li><label for="name">이름</label>
          <input id="name" name="name" type="text"
           size="20" placeholder="홍길동" >
      <li><label for="department">부서</label>
          <input id="department" name="department" type="text"
           size="20" placeholder="개발1팀" >
      <li><label for="position">직급</label>
          <input id="position" name="position" type="text"
           size="20" placeholder="사원" >
      <li><label for="email">이메일</label>
          <input id="email" name="email" type="text"
           size="20" placeholder="example@naver.com" >
      <li><label for="profile">profile</label>
          
           <div class="form-group">
                 <label for="exampleInputFile">File input</label>
                 <input type="file" id="profile">
                 <p class="help-block">Example block-level help text here.</p>
           </div>
      <li class="label2"><input type='submit' id="process" class="btn btn-default" value='가입하기'>
          <input type='button' id="cancle" class="btn btn-primary" value='취소'>
   </ul>
</form>
</body>
</html>