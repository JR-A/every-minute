<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$('#form').submit(function(){
			if($('#id').val()==''){
			$('#message_id').text('아이디를 입력해주세요').css('color','red');
			$('#id').focus();
			return false;	
		}
		if($('#passwd').val()==''){
			$('#message_passwd').text('비밀번호를 입력해주세요').css('color','red');
			$('#passwd').focus();
			return false;
	
		}				
		});	
	});
</script>
</head>
<body>
<div>
	<h2>로그인</h2>
	<form:form id="form" action="memberLogin.do" commandName="memberVO">
		<%-- 필드가 없는 에러 표시 --%>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="id">아이디</label>
				<form:input path="id"/>
				<span id="message_id"></span>
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<form:password path="passwd"/>
				<span id="message_passwd"></span>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="로그인">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/introduce.do'">
		</div>
	</form:form>
</div>
</body>
</html>