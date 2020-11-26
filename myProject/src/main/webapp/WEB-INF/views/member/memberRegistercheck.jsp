<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
이메일 인증후 로그인이 가능합니다. 
<input type="button" value="로그인" onclick="location.href='${pageContext.request.contextPath}/member/memberLogin.do'">
</body>
</html>