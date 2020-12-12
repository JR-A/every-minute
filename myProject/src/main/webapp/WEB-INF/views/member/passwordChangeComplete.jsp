<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	ul{
	 padding-left:288px;
	}
	#text{
	padding-left:59px;
	}
	input[type="button"]{
	margin-top:50px;
	}
		
</style>
<title>비밀번호 변경성공</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member.system.css">
</head>
<body>
<ul>
	 
	 <li id="text">비밀번호를 변경했습니다 다시 로그인 해주세요!</li>
  <li><input type="button" class="M_Btn" onclick="location.href='${pageContext.request.contextPath}/main/introduce.do'" value="메인으로"></li>
</ul>
</body>
</html>