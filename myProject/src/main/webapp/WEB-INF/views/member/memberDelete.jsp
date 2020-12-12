<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/member.system.css">
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
<form:form id="dform" action="deleteMember.do" method="post" commandName="memberVO">
	<form:hidden path="mem_num"/>
	<form:errors element="span" cssClass="error-color error-text"/><br>
	<span id="pass3"></span><br>
	
	<form:password class="M_input" path="passwd" placeholder="비밀번호"/>
	<span id="pass1"></span><br>
	
	<input type="password" class="M_input" id="passwdCk" placeholder="비밀번호 확인">
	<span id="pass2"></span><br>
	<div id="M_delete_btns">
	<input type="submit" class="M_Btn" value="회원 탈퇴">
	<input type="button" class="M_Btn" onclick="history.go(-1)" value="취소">
	</div>
</form:form>
</body>
</html>