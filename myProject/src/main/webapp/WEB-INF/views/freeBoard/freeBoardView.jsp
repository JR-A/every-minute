<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/freeBoard.css">
<div class="page-main-style-detail">
	<article>
		<a class="article2">
			<c:if test="${empty member.photoname}">
				<img src="https://cf-fpi.everytime.kr/0.png" width="100" height="100" class="picture large">
			</c:if>
			<c:if test="${!empty member.photoname }">
				<img src="${pageContext.request.contextPath}/member/photoView.do" width="100" height="100" class="picture large">
			</c:if>
			<div class="profile">
				<h3 class="large">	<c:if test="${0 eq freeboard.anonymous}">
						${freeboard.id}
				</c:if>
				<c:if test="${1 eq freeboard.anonymous}">
						익명
				</c:if></h3>
				<time class="large"><fmt:formatDate value="${freeboard.modify_date}" pattern="MM/dd HH:MM"/></time>
			</div>
			<ul class="status">
				<li class="messagesend" data-modal="messageSend" data-article-id="76626841" data-is-anonym="0">쪽지</li>
				<li class="abuse">신고</li>
			</ul>
			<hr>
			<h1>${freeboard.title}</h1>
			<p class="">
				${freeboard.content}
			</p>
			<c:if test="${!empty freeboard.filename}"> <!-- filename이 비어있지 않는다면 아래의 div를 실행 -->
			<div class="align-center">
				<img src="imageView.do?post_num=${freeboard.post_num}" style="max-width: 500px;">
			</div>
			</c:if>
		</a>
	</article>
</div>

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
	<!-- 게시물 끝  -->
	<!-- 게시물 끝 -->
<div id="reply">
 <ol class="replyList">
 <c:forEach items="${replyLis}" var="replyLis">
 <li>
  <p>
   작성자 : ${replyLis.coment_num}<br />
   작성 날짜 :  <fmt:formatDate value="${repList.regDate}" pattern="yyyy-MM-dd" />
  </p>
  
  <p>${replyList.content}</p>
 </li>
 </c:forEach>   
 </ol>
</div>

