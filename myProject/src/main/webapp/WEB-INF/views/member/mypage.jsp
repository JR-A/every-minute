<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	

	$(document).ready(function(){
		$('#photo_btn').click(function(){
			//이미지 파일 선택 태그가 노출
				$('#photo_choice').show();
				//수정 버튼을 숨김
				$(this).hide();
				$('#default_photo_btn').hide();
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
							photo_path = $('.my-photo1').attr('src');
							//변경된 이미지를 미리보기 셋팅
							$('.my-photo1').attr('src',reader.result);
						};
					}
				});
				
				//이미지 초기화
				$('#photo_reset').click(function(){
					$('.my-photo1').attr('src',photo_path);
					$('#upload').val('');
					$('#photo_choice').hide();
					$('#photo_btn').show();
					$('#default_photo_btn').show();
				});
				
				$('#photo_submit').click(function(){
					if($('#upload').val()==''){
						alert('이미지 파일을 선택하세요');
						$('#upload').focus();
						return;
					}

					//파일 전송
					var form_data = new FormData();
					form_data.append('upload',my_photo);
					$.ajax({
						data:form_data,
						type:'POST',
						url:'updateMyPhoto.do',
						dataType:'json',
						cache:false,
						contentType:false,
						enctype:'multipart/form-data',
						processData:false,
						success:function(data){
							if(data.result == 'logout'){
								alert('로그인 후 사용하세요!');
							}else if(data.result == 'success'){
								alert('프로필 사진이 수정되었습니다.');
								$('#upload').val('');
								$('#photo_choice').hide();
								$('#photo_btn').show();
								$('#default_photo_btn').show();
							}else{
								alert('파일 전송 오류 발생');
							}
						},
						error:function(){
							alert('네트워크 오류 발생');
						}
					});
					});

				//기본 이미지 버튼클릭시
				$('#default_photo_btn').click(function(){
					var form={}
					
					$.ajax({
						data:form,
						type:'POST',
						url:'resetMyPhoto.do',
						cache:false,
						contentType:false,
						processData:false,
						success:function(data){
							if(data.result == 'logout'){
								alert('로그인 후 사용하세요!');
							}else if(data.result == 'success'){
								alert('프로필 사진을 기본 이미지로 바꿧습니다.');
							}else{
								alert('파일 전송 오류 발생');
							}
						},
						error:function(){
							alert('네트워크 오류 발생');
						}
				});			
			});
	});
</script>
</head>
<body>
 <fieldset>
 	<div id="myInfo">
 		<ul>
 			<li><h1>내정보</h1></li>
 			<c:if test="${empty user.photoname}">
				<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" width="100" height="100" class="my-photo1">			
				<div>
				<input type="button" id="photo_btn" value="이미지 수정">
			</div>
			<div id="photo_choice" style="display:none;">
				<input type="file" id="upload" name="upload" accept="image/gif,image/png,image/jpeg">
				<input type="button" value="확인" id="photo_submit">
				<input type="button" value="취소" id="photo_reset">      
			</div>
			</c:if>	
 			<c:if test="${!empty user.photoname}">
				<img src="${pageContext.request.contextPath}/member/photoView.do" width="100" height="100" class="my-photo1">
				<div>
				<input type="button" id="photo_btn" value="이미지 수정">
				<input type="button" id="default_photo_btn" value="기본 이미지">
			</div>
			<div id="photo_choice" style="display:none;">
				<input type="file" id="upload" accept="image/gif,image/png,image/jpeg">
				<input type="button" value="전송" id="photo_submit">
				<input type="button" value="취소" id="photo_reset">      
			</div>
			</c:if>		
 				<li>닉네임:${user.nickname}</li>
 			<c:if test="${!empty user.major}">
 				<li>학과:${user.major}</li>
 			</c:if> 			
 			 <c:if test="${empty user.major}">
 				<li>학과:미입력</li>
 			</c:if>				
 			<c:if test="${!empty user.stu_num}">
 				<li>학번:${user.stu_num}</li>
 			</c:if> 			
 			 <c:if test="${empty user.stu_num}">
 				<li>학번:미입력</li>
 			</c:if> 
 		</ul>
 	</div>
 </fieldset>
 <fieldset>
 	<div id="infoChange">
 		<h2>계정 관리</h2>
 		<ul>
 		<li><a href="${pageContext.request.contextPath}/member/boardManagement.do">게시판 관리</a></li>
 		<li><a href="${pageContext.request.contextPath}/member/changePasswd.do">비밀번호 변경</a></li>
 		<li><a href="${pageContext.request.contextPath}/member/changeEmail.do">이메일 변경</a></li>
 		<li><a href="${pageContext.request.contextPath}/member/changeNickname.do">닉네임 변경</a></li>
 		<li><a href="${pageContext.request.contextPath}/member/deleteMember.do">회원 탈퇴</a></li>
 		</ul>
 	</div>
 </fieldset>
</body>
</html>