<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member.system.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#form').submit(function(){
		if($('#nickname').val()<2||$('#nickname').val()>10){
			alert('닉네임 글자수는 2자이상 10자리 이하입니다.');	
			return false;
		}
		
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form id="form" action="changeNickname.do" method="post" commandName="MemberVO">
	<ul>
		<li>

			<input type="text" class="M_input" id="nickname" name="nickname" placeholder="변경할 닉네임">
		</li>
	</ul>
	<input type="submit" class="nickname_M_Btn" value="확인">
	<input type="button" class="nickname_M_Btn" onclick="history.go(-1)" value="취소">
	
	
</form:form>
</body>
</html>