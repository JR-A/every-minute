<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
	.form{
		border: 1px solid #ddd;
		border-radius: 5px;
		margin: auto;
		margin-top: 20px;
		width: 400px;
		padding: 10px;
	}
	.txt{
		text-align: center;
	}
	.link{
		margin: auto;
		text-align: center;
		width: 200px;
		padding: 5px;
		margin-top: 20px;
		border: 1px solid #ddd;
		border-radius: 5px;
		background-color: #ddd;
		cursor: pointer;
	}
</style>
</head>
<body>
	<div class="form">
		<div class="txt"><h2>이메일 인증이 완료되었습니다.</h2></div>
		<div class="link" onclick="location.href='${pageContext.request.contextPath}/member/memberLogin.do'">
			로그인하시겠습니까?
		</div>
	</div>
</body>
</html>