<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	.delete_form{
		border: 1px solid #ddd;
		border-radius: 5px;
		margin: auto;
		margin-top: 20px;
		width: 400px;
		padding: 10px;
		color: #666;
	}
	.delete_txt{
		text-align: center;
	}
	.main_btn{
		text-align: center;
		margin-top: 20px;
	}
	.main_btn>input{
		border: none;
		padding: 5px 10px;
		border-radius: 5px;
	}
</style>
<title>회원 탈퇴완료</title>
</head>
<body>
	<div class="delete_form">
		<div class="delete_txt">탈퇴가 완료되었습니다.</div>
		<div class="main_btn">
			<input type="button"onclick="location.href='${pageContext.request.contextPath}/main/introduce.do'" value="메인으로">
		</div>
	</div>
</body>
</html>