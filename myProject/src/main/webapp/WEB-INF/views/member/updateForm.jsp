<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#form').submit(function(){
			if($('#passwd').val()==""){
				alert('비밀번호를 입력해주세요');
				return false;
				}
			});
			
			$('#photo_btn').click(function(){
				//이미지 파일 선택 태그가 노출
					$('#photo_choice').show();
					//수정 버튼을 숨김
					$(this).hide();
				});
					
					//원래 이미지를 보관
					var photo_path;
					//변경 이미지를 보관
					var my_photo;
					$('#upload').change(function(){
						var upload = document.getElementById('upload');
						my_photo = upload.files[0];
						if(my_photo){
							var reader = new FileReader();
							reader.readAsDataURL(my_photo);
							
								//사진 업로드전 미리보기 처리
								reader.onload = function(){
								//원래 이미지 보관
								photo_path = $('.my-photo').attr('src');
								//변경된 이미지를 미리보기 셋팅
								$('.my-photo').attr('src',reader.result);
							};
						}
					});
					
					//이미지 초기화
					$('#photo_reset').click(function(){
						$('.my-photo').attr('src',photo_path);
						$('#upload').val('');
						$('#photo_choice').hide();
						$('#photo_btn').show();
					});
					
					$('#photo_submit').click(function(){
						if($('#upload').val()==''){
							alert('이미지 파일을 선택하세요');
							$('#upload').focus();
							return;
						}
					});
					
			});

	
</script>
<meta charset="UTF-8">
<title>정보수정</title>
</head>
<body>
	<form:form id="form" action="update.do" commandName="memberVO">
		<form:hidden path="mem_num"/>
		<div>
			<c:if test="${empty user.photoname}">
				<img src="${pageContext.request.contextPath}/resources/images/logo.png" width="25" height="25" class="my-photo">
			</c:if>
			<c:if test="${!empty user.photoname}">
				<img src="${pageContext.request.contextPath}/photoView.do" width="25" height="25" class="my-photo">
			</c:if>			
		</div>
			<div>
				<input type="button" id="photo_btn" value="이미지 수정">
			</div>
			<div id="photo_choice" style="display:none;">
				<input type="file" id="upload" accept="image/gif,image/png,image/jpeg">
				<input type="button" value="전송" id="photo_submit">
				<input type="button" value="취소" id="photo_reset">      
			</div>
		<label for="id">아이디</label>
		<form:input path="id" readonly="true"/><br>
		<label for="passwd">비밀번호</label>
		<form:password path="passwd"/><form:errors element="span"/>&nbsp;&nbsp;&nbsp;<span id="passwdER"></span><br>
		<label for="nickname">닉네임</label>
		<form:input path="nickname"/><br>
		<label for="email">이메일</label>
		<form:input path="email"/>
		<div>
			<input type="submit" value="수정">
			<input type="button"  onclick="location.href='${pageContext.request.contextPath}/myPage.do'" value="마이페이지로">
		</div>
	</form:form>
</body>
</html>