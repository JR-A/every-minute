<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/freeBoard.css">
<div id="article" class="page-main-style">
	<article>
	<h1>자유게시판</h1>
	<div class="profile">
	<img src="https://cf-fpi.everytime.kr/0.png" class="picture"> 
	<h3 class="fid">${freeboard.id}</h3>
	<time class="large"><fmt:formatDate value="${freeboard.reg_date}" pattern="MM/dd HH:MM"/></time>
	</div>

	<h2>${freeboard.title}</h2>
	<p>
		${freeboard.content}
	</p>
	
	<c:if test="${!empty freeboard.filename}">
	<div class="align-center">
		<img src="imageView.do?post_num=${freeboard.post_num}" style="max-width:500px;">
	</div>
	</c:if>

	<div class="align-right">
		<%--수정 삭제의 경우는 로그인이 되어있고 로그인한 회원번호와 작성자 회원번호가 일치해야 함 --%>
		<c:if test="${!empty user && user.mem_num == freeboard.mem_num}">
		<input type="button" value="수정" onclick="location.href='update.do?post_num=${freeboard.post_num}'">
		<input type="button" value="삭제" id="delete_btn">
		<script>
			var delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick=function(){
				var choice = window.confirm('삭제하시겠습니까?');
				if(choice){
					location.href='delete.do?post_num=${freeboard.post_num}';
				}
			};
		</script>
		</c:if>
		<input type="button" value="목록" onclick="location.href='freeBoardList.do'">
	</div>
	</article>
</div>
