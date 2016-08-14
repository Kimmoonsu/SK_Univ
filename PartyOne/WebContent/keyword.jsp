<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form name="find" action="find.do" method="post">
				<input type="text" name="keyword"/>
				<input name="findState" type="radio" value="writer">작성자
				<input name="findState" type="radio" value="title">제목
				<input name="findState" type="radio" value="sub">내용
				<a href="javascript:;" onclick="javascript:   document.getElementById('find').submit();">검색</a>
	</form>

</body>
</html>