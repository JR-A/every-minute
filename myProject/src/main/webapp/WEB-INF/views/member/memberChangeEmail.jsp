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
				
			var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
			
			if($('#email').val()==""){
				alert("현재 이메일을 입력해주세요!");
				$('#email').focus();
				return false;
			}
			
			if(reg_email.test($('#email').val())==false){
				alert('현재 이메일 형식이 잘못됐습니다.');
				return false;
				}
			
			
			if($('#changeEmail').val()==''){
				alert('변경할 이메일을 입력해주세요!');
				$('#changeEmail').focus();
				return false;
			}
		
			

			
			if(reg_email.test($('#changeEmail').val())==false){
				alert('변경할 이메일 형식이 잘못됐습니다.');
				return false;
				}
		})
		
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form id="form" action="changeEmail.do" method="post" commandName="memberVO">
	<form:errors element="span" cssClass="error-color error-text"/>
	<ul>
		<li>
			
			<form:input class="M_input" path="email" placeholder="현재 이메일"/>	
			<span id="nick1"></span>
		</li>
		<li>
			
			<input type="text" class="M_input" id="changeEmail" name="changeEmail" placeholder="새 이메일">
		</li>
		<li style="
    padding-left: 74px;
		">(변경할 이메일로 승인메일이 전송됩니다.)</li>
	</ul>
	<ul>
		<li>
			<input class="M_Btn" type="submit" value="확인">
		</li>
		<li>
			<input  class="M_Btn" type="button" onclick="history.go(-1)" value="취소">
		</li>
	</ul>
</form:form>
</body>
</html>