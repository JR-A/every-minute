<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customBoard.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
   $(document).ready(function() {

	   //타입체크
	   var type = $('#type').val();
	   
	   var simple_red = '${pageContext.request.contextPath}/resources/images/customBoard/simpleType1.png';  
	   var simple_gray = '${pageContext.request.contextPath}/resources/images/customBoard/simpleType0.png'; 
	   var image_red = '${pageContext.request.contextPath}/resources/images/customBoard/imgType1.png';  
	   var image_gray = '${pageContext.request.contextPath}/resources/images/customBoard/imgType0.png'; 
	   
	   //기본형 클릭시
	   $('#simpleType').click(function() {
	        if(type == 0) { //기본형 선택되어 있을 경우
	        	$('#simpleImg').attr('src', simple_red); //기본형 이미지 - 빨강
	        	$('#imageImg').attr('src', image_gray); //사진형 이미지 - 회색
	        	type = 0;
	        }
	        else if(type == 1){ //기본형 미선택되어 있을 경우
	        	$('#simpleImg').attr('src', simple_red); 
	        	$('#imageImg').attr('src', image_gray);
	        	type = 0;
	        }
		});

	   //사진형 클릭시
	   $('#imageType').click(function() {
	        if(type == 1){ //사진형으로 선택 되어있을 경우
	        	$('#simpleImg').attr('src', simple_gray);
	        	$('#imageImg').attr('src', image_red);
	        	type = 1;
	        }
	        else if(type == 0){ //기본형으로 되어있을 경우
	        	$('#simpleImg').attr('src', simple_gray);
	        	$('#imageImg').attr('src', image_red);
	        	type = 1;
	         }
		 });  
	   
		//익명허용 여부
		var check = $("input[type='checkbox']");
		check.click(function(){
			$('p#anony').toggle(); //선택한 요소가 보이면 보이지 않게, 보이지 않으면 보이게 
		});
		
		$('#custBoard_form').submit(function(){
			$('#type').val(type);
			if($('#check_anony').is(':checked')){
				$('#anonymous').val(1); //허용
			}else{
				$('#anonymous').val(0); //미허용
			}
		});
   });
	
</script>

<div class="create_custBoard">
	<form:form commandName="customBoardVO" action="createCustomBoard.do" id="custBoard_form">
	
	<!-- type과 anonymous 전달 -->	 
	<form:hidden path="type"/>
	<form:hidden path="anonymous"/>
	
	<div class="customH2Title">	
		<h2 id="customBoard_comm" style="font-size: 24px;">사용자 게시판 만들기</h2>
		<pre class="text_left" style="color: #a6a6a6;">각 테마에 맞는 사용자 게시판을 
직접 생성하여 성향이 맞는 교원과 소통 할 수 있습니다.</pre>
	</div>
		<ul id="createCustomBoard_ul">
			<li>
				<p class="float_left">먼저 게시판 이름이 필요해요 :)</p>
				<form:input class="custom_title" path="title" placeholder="게시판 이름을 입력해 주세요." autocomplete="off"/>
				<form:errors path="title" cssClass="error-color"/>
			</li>
			<li>
				<p class="float_left">게시판 설명</p>
				<form:input class="custom_title" path="subtitle"  placeholder="게시판에 대해 간단하게 설명해주세요" autocomplete="off"/>
				<form:errors path="subtitle" cssClass="error-color"/>
			</li>
		</ul>
	  <div id="anonymous_opt">
	       <input type="checkbox" id="check_anony" checked style="margin-left: 35px;">
	      <label for="check_anony">익명성을 보장하는 익명 게시판인가요?</label>
	  </div>
      <!-- 기본형 -->
      <div id="simpleType">
         <div id="simpleType_img">
            <img id="simpleImg" alt="기본형" src="${pageContext.request.contextPath}/resources/images/customBoard/simpleType1.png">
         </div>
         <div id="Type_text">
            <p><b style="font-size: 120%;line-height: 28px;">기본형</b><br>여러 글을 빠르게 읽을 수 있는 기본 형식</p>
         </div>
      </div>
      <span class="clear-both;"></span>
      <!-- 사진형 -->
      <div id="imageType">
         <div id="imageType_img">
            <img id="imageImg" alt="사진형" src="${pageContext.request.contextPath}/resources/images/customBoard/imgType0.png">
         </div>
         <div id="Type_text" style="display: inline">
            <p><b style="font-size: 120%;line-height: 28px;">사진형</b><br>글 목록에 사진이 노출되는 형식</p>
         </div>         
      </div>
      <span class="clear-both;"></span>
			<input type="submit" class="customOK" value="완료">
	</form:form>
</div>
 