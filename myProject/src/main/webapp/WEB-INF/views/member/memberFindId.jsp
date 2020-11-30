<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.error-color{color:red;}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#form').submit(function(){
				
			var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
			
			if($('#email').val()==""){
				alert("이메일을 입력해주세요!");
				$('#email').focus();
				return false;
			}
			
			if(reg_email.test($('#email').val())==false){
				alert('이메일 형식이 잘못됐습니다.');
				return false;
				}		

		})
		
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form id="form" action="findId.do" method="post" commandName="memberVO">

		<b>가입시 기입했던 이메일을 입력해주세요</b><br>
		<b>이메일로 아이디/비밀번호가 전송됩니다.</b>
	<ul>
		<li>
			<label for="email">이메일</label>
			<form:input path="email"/>	
			<form:errors element="div" cssClass="error-color"/>
		</li> 
	</ul>
	<input type="submit" value="전송">
	<input type="button" onclick="history.go(-1)" value="뒤로">
</form:form>
</body>
</html>