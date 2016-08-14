<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

  <body class="login-img3-body">
    <div class="container">
      <div class="login-form" >
        <div class="login-wrap">
        	<div>
	            <p class="login-img"><i class="icon_lock_alt"></i></p>
	            <div class="input-group">
	              <span class="input-group-addon"><i class="icon_profile"></i></span>
	              <input type="text" name="id" id="id" class="form-control" placeholder="Username" autofocus>
	            </div>
	            <div class="input-group">
	                <span class="input-group-addon"><i class="icon_key_alt"></i></span>
	                <input type="password" name="passwd" id="passwd" class="form-control" placeholder="Password">
	            </div>
	            <label class="checkbox">
	                <input type="checkbox" value="remember-me"> Remember me
	                <span class="pull-right"> <a href="#"> Forgot Password?</a></span>
	            </label>
	            <input type='button' id="login_btn" class="btn btn-primary btn-lg btn-block" value='로그인'>
 				<input type= 'button' id ="register_btn" class="btn btn-info btn-lg btn-block" value='회원가입'>
 			</div>          
        </div>
      </div> 
    </div>

  </body>
</html>
