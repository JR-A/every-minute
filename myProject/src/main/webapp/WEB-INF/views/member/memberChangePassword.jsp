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
		var special = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
		
		$('#form').submit(function(){
			
			
			
			if($('#passwd').val()==''){
				alert('비밀번호를 입력해주세요!')
				$('#passwd').focus();
				return false;
			}
			
			
			if($('#changePasswd').val()==''){
				alert('변경할 비밀번호를 입력해주세요!')
				$('#changePasswd').focus();
				return false;
			}
			
			if($('#changePasswdCk').val()==''){
				alert('비밀번호 확인을 입력해주세요!')
				$('#changePasswdCk').focus();
				return false;
			}
			
			if($('#changePasswd').val().length>15 || $('#changePasswd').val().length<4){
				$('#changePasswd').val('').focus();
				$('#changePasswdCk').val('');
				alert('변경할 비밀번호는 4자리이상 15자리 이하로 입력해주세요!');
				return false;
			}
			
			if($('#changePasswd').val()!=$('#changePasswdCk').val()){
				alert('변경할 비밀번호와 비밀번호 확인이 서로다릅니다.');
				$('#changePasswd').val('').focus();
				$('#changePasswdCk').val('');
				return false;
			}
			
			if(special.test($('#changePasswd').val())==false){
				alert('비밀번호에 특수문자를 포함해주세요.');
				$('#changePasswd').val('').focus();
				$('#changePasswdCk').val('');
				return false;
			}
		
			if($('#changePasswd').val().search(/\s/)!=-1){
				alert('비밀번호에 공백을 포함할수없습니다.');
				$('#changePasswd').val('').focus();
				$('#changePasswdCk').val('');
				return false;
			}
			
			
		});
		
		
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form id="form" action="changePasswd.do" method="post" commandName="memberVO">
	<form:errors element="div" cssClass="error-color error-text"/>
	<ul>
		<li id="nowPassword">
			
			<form:password class="M_input" path="passwd" placeholder="현재 비밀번호"/>
		</li>		
		<li>
			
			<input type="password" class="M_input" id="changePasswd" name="changePasswd" placeholder="새 비밀번호">		
		</li>
		<li>
		<!-- 	(비밀번호변경은 4자리이상 15자리 이하로만 가능합니다) -->
		</li>
		<li>
			
			<input type="password" class="M_input" id="changePasswdCk" placeholder="새 비밀번호 확인">
		</li>
	</ul>
	<div>
		<ul>
			<li>
				<input class="M_Btn" type="submit" value="확인">
			</li>
			<li>	
				<input class="M_Btn" type="button" value="취소" onclick="history.go(-1)">
			</li>
		</ul>
	</div>
</form:form>
</body>
</html>