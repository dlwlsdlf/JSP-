<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>JSP게시판 웹 사이트</title>
</head>
<body>

	<%
		session.invalidate(); //이 페이지에 접속한 회원이 세션을 빼앗기도록 만들어 로그아웃
	%>
	<script>
		location.href ="main.jsp";
	</script>
</body>
</html>