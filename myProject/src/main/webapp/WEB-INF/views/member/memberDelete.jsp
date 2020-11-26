<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>계정 삭제</title>
<script type="text/javascript">
	$(document).ready(function(){
	
		$("#dform").submit(function(){
			
		
			
			if($("#passwd").val()==""){
				$("#pass1").text('비밀번호를 입력해주세요').css('color','red')
				$("#passwd").focus();
				return false;
			}
			
				$("#pass1").text('');
			
				if($("#passwdCk").val()==""){
				$("#pass2").text('비밀번호를 입력해주세요').css('color','red')
				$("#passwdCk").focus();
				return false;
			}
				$("#pass2").text('');
				
			if($("#passwd").val()!=$('#passwdCk').val()){
				$('#pass3').text('비밀번호가 서로다릅니다.').css('color','red');
				return false;		
			};
		
				
			   var Confirm = confirm('정말 탈퇴하시겠습니까?');
			   if (Confirm) {
			      return true;
			   }
			   else {
			      return false;
			   }
		});
	

	});

</script>
</head>
<body>
<h1>회원 탈퇴</h1>
<form:form id="dform" action="deleteMember.do" method="post" commandName="memberVO">
	<form:hidden path="mem_num"/>
	<form:errors element="span" cssClass="error-color"/><br>
	<span id="pass3"></span><br>
	<label for="passwd">비밀번호</label>
	<form:password path="passwd"/>
	<span id="pass1"></span><br>
	<label for="passwdCk">비밀번호확인</label>
	<input type="password" id="passwdCk">
	<span id="pass2"></span><br>
	<div>
	<input type="submit" value="확인">
	<input type="button" onclick="history.go(-1)" value="뒤로">
	</div>
</form:form>
</body>
</html>