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
	        alert("click ->" + type);
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
	        alert("click ->" + type);
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
			alert($('#anonymous').val());
		});
		
   });
	
</script>

<div class="update_custBoard">
	<form:form commandName="customBoardVO" action="updateCustomBoard.do" id="custBoard_form">
	
	<!-- type과 anonymous 전달 -->	 
	<form:hidden path="type"/>
	<form:hidden path="anonymous"/>
	<form:hidden path="board_num"/>
	
	<div class="align-center">
		<input type="button" class="delete_btn" onclick="location.href='${pageContext.request.contextPath}/main/main_board.do'" alt="삭제 버튼">	
		<h2 id="customBoard_comm">게시판 수정</h2>
		<input type="submit" value="수정">
	</div>
		<ul id="updateCustomBoard_ul">
			<li>
				<form:input path="title" autocomplete="off" style="height:40px; width:450px; margin-bottom:15px;"/>
				<form:errors path="title" cssClass="error-color"/>
			</li>
			<li>
				<form:input path="subtitle" autocomplete="off" style="height:80px; width:500px"/>
				<form:errors path="subtitle" cssClass="error-color"/>
			</li>
		</ul>	
		
		
      <h2>형식</h2>
	  <c:if test="${customBoardVO.type == 0}">
	      <!-- 기본형 -->
	      <div id="simpleType">
	         <div id="simpleType_img">
	            <img id="simpleImg" alt="기본형" src="${pageContext.request.contextPath}/resources/images/customBoard/simpleType1.png">
	         </div>
	         <div id="Type_text">
	            <p><b style="font-size: 130%;">기본형</b><br>여러 글을 빠르게 읽을 수 있는 기본 형식</p>
	         </div>
	      </div>
	      <span class="clear-both;"></span>
	      <!-- 사진형 -->
	      <div id="imageType">
	         <div id="imageType_img">
	            <img id="imageImg" alt="사진형" src="${pageContext.request.contextPath}/resources/images/customBoard/imgType0.png">
	         </div>
	         <div id="Type_text" style="display: inline">
	            <p><b style="font-size: 130%;">사진형</b><br>글 목록에 사진이 노출되는 형식</p>
	         </div>         
	      </div>
	      <span class="clear-both;"></span>
	  </c:if>
	  <c:if test="${customBoardVO.type == 1}">
	      <!-- 기본형 -->
	      
	      <div id="simpleType">
	         <div id="simpleType_img">
	            <img id="simpleImg" alt="기본형" src="${pageContext.request.contextPath}/resources/images/customBoard/simpleType0.png">
	         </div>
	         <div id="Type_text">
	            <p><b style="font-size: 130%;">기본형</b><br>여러 글을 빠르게 읽을 수 있는 기본 형식</p>
	         </div>
	      </div>
	      <span class="clear-both;"></span>
	      <!-- 사진형 -->
	      <div id="imageType">
	         <div id="imageType_img">
	            <img id="imageImg" alt="사진형" src="${pageContext.request.contextPath}/resources/images/customBoard/imgType1.png">
	         </div>
	         <div id="Type_text" style="display: inline">
	            <p><b style="font-size: 130%;">사진형</b><br>글 목록에 사진이 노출되는 형식</p>
	         </div>         
	      </div>
	      <span class="clear-both;"></span>
	  </c:if>

		
	  <h2>추가설정</h2>
	  <div id="anonymous_opt">
	      <h4 id="anonymouse_check">익명허용</h4> 
		  <c:if test="${customBoardVO.anonymous == 0}"> <!-- 익명 불허 게시판 -->
			  <label class="switch">
	 			 <input type="checkbox" id="check_anony">
				 <span class="slider round"></span>
			  </label>
			 <p id="anony">OFF</p>
			 <p id="anony" style="display:none;">ON</p>
		  </c:if>
		  <c:if test="${customBoardVO.anonymous == 1}"> <!-- 익명 허용 게시판 -->
			  <label class="switch">
	 			 <input type="checkbox" id="check_anony" checked>
				 <span class="slider round"></span>
			  </label>
			  <p id="anony" style="display:none;">OFF</p>
			  <p id="anony">ON</p>
		  </c:if>
	  </div>
			
	</form:form>
</div>
 