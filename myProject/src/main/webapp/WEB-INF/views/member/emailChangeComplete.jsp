<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	body{
	margin:15% auto;
	text-align:center;
	}

	div{
	background-color: blanchedalmond;
	}
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member.system.css">
<title>Insert title here</title>
</head>
<body>
	<div>
		<ul style="list-style:none">
			<li>이메일 변경이 완료되었습니다 !</li>
			<li><input type="button" class="R_Btn" onclick="location.href='${pageContext.request.contextPath}/member/memberLogin.do'" value="로그인"></li>
		</ul> 
	</div>
</body>
</html>