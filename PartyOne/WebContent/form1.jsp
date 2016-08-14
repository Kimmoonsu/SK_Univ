<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%response.setCharacterEncoding("euc-kr"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="form" action="form.do" method="post">
	<table id="form-table"> 
				<tr><td><input class="form-input" type="text" name="location" id="location" value="" placeholder="위치 - 마커 선택"></td></tr>
				<tr><td><input class="form-input" type="text" name="date" id="date" value="" placeholder="날짜  ex.160101"></td></tr>
				<tr><td><input class="form-input" type="text" name="title" id="title" value="" placeholder="제목"></td></tr>
				<tr><td><input class="form-input" type="text" name="sub" id="sub" value=""  placeholder="설명"></td></tr>
			 	<tr><td><input class="form-submit" type="submit"  value="save"> </td></tr>
	</table>
</form>
</body>
</html>