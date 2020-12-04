<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member.login.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){	
	$('#id').keydown(function(){
		$('#message_id').text('');
		$('#message').text('');
	});
	
	$('#passwd').keydown(function(){
		$('#message_passwd').text('');
		$('#message').text('');
	});
	
	$('#form').submit(function(){
		if($('#id').val()==''){
			$('#message_id').text('아이디를 입력해주세요').css('color','#c62917');
			$('#id').focus();
			return false;
		}
		
		if($('#passwd').val()==''){
			$('#message_passwd').text('비밀번호를 입력해주세요').css('color','#c62917');
			$('#passwd').focus();
			return false;
		}
	});

});
</script>
</head>
<body>
<div id="container" class="login">
	<h1 class="logo">
		<a href="${pageContext.request.contextPath}/main/introduce.do">에브리미닛</a>
	</h1>
	<form:form id="form" action="memberLogin.do" commandName="memberVO">
		<%-- 필드가 없는 에러 표시 --%>
		<form:errors id="message" element="div" class="errorColor"/>
		<p class="input">
			<form:input id="id" path="id" placeholder="아이디"/>
		</p>
		<span id="message_id"></span>
		<p class="input">
			<form:password id="passwd" path="passwd" placeholder="비밀번호"/>
		</p>
		<span id="message_passwd"></span>
		<p class="submit">
			<input class="text" type="submit" value="로그인">
		</p>
		<p class="find">
			<a href="${pageContext.request.contextPath}/member/findId.do">아이디/비밀번호 찾기</a>
		</p>
		<p class="register">
	        <span>에브리미닛에 처음이신가요?</span>
	        <a href="${pageContext.request.contextPath}/member/memberRegister.do">회원가입</a>
	    </p>
	</form:form>
</div>
</body>
</html>