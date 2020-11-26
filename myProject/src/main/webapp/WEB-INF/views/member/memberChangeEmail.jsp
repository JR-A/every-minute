<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
	<form:errors element="span" cssClass="error-color"/>
	<ul>
		<li>
			<label for="email">현재 이메일</label>
			<form:input path="email"/>	
			<span id="nick1"></span>
		</li>
		<li>
			<label for="changeEmail">변경할 이메일</label>
			<input type="text" id="changeEmail" name="changeEmail">
		</li>
		<li>(변경할 이메일로 승인메일이 전송됩니다.)</li>
	</ul>
	<input type="submit" value="전송">
	<input type="button" onclick="history.go(-1)" value="뒤로">
</form:form>
</body>
</html>