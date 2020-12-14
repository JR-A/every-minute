<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
    margin: 0;
    height: 100%;
    background: #f5f6f7;
    font-family: Dotum,'돋움',Helvetica,sans-serif;
}
 
 .page-main-style{
 	padding-top:150px;
 	display:flex;
 	justify-content:center;
 } 
 
 ul{
 font-weight:bold;
 text-align:center;
 }
 
 li{
 padding-bottom:20px;
 font-size:14px;
 }

 label{
 padding-top:5px;
 }	
 li input{
 	height:25px;
 } 

 .message-ck{
 padding-left:128px;
 }
 
 

 .logo{
 background: transparent url(../resources/images/index.login.logo.png) no-repeat;
 width: 360px;
 margin-bottom: 15px;
 height: 60px;
 margin-left:30px;
 }
 #register_form{
	width:600px;
	margin:0 auto;
	border: 1px solid #d6d6d6;
	padding:10px 10px 10px 30px;
	
}
 
 #register_form label{
 position: absolute;
 }
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css">
</head>

<body>
<div class="page-main-style">
	
	
	<form id="register_form">
		<h1 class="logo"></h1>
		<ul>
			<li>이메일로 승인번호를 보냈습니다.</li>
			<li><input type="button" class="R_Btn" onclick="location.href='${pageContext.request.contextPath}/member/memberLogin.do'"value="로그인"></li>
		</ul>
		</form>
	
</div>
</body>
</html>