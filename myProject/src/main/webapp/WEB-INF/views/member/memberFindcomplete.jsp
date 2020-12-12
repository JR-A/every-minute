<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member.system.css">
<style type="text/css">
.error-color{color:red;}

#find_form{
	width:447px;
	margin:15% auto;
	border: 1px solid #d6d6d6;
	padding:10px 10px 10px 30px;
	
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="container" class="login">
	<form:form id="find_form" action="findId.do" method="post" commandName="memberVO">

		
		
	<ul>
		<li><strong>입력하신 이메일로 아이디/비밀번호가 전송됬습니다.</strong></li>
	</ul>
	<input type="button" class="M_Btn" onclick="location.href='${pageContext.request.contextPath}/member/memberLogin.do'" value="로그인">
	</form:form>
</div>
</body>
</html>