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
				<input name="findState" type="radio" value="writer">�ۼ���
				<input name="findState" type="radio" value="title">����
				<input name="findState" type="radio" value="sub">����
				<a href="javascript:;" onclick="javascript:   document.getElementById('find').submit();">�˻�</a>
	</form>

</body>
</html>