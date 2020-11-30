<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customPost.css">

<div id="container" class="article">
	<!-- 제목&소제목 -->
	<div class="wrap title">
		<h1>
			<a href="customPostList.do">${boardInfo.title}</a>
		</h1>
		<p>${boardInfo.subtitle}</p>
		<!-- <hr> -->
	</div>
	
	<!-- 게시글 상세페이지 -->
	<div class="wrap articles">
		<article>
			<a class="article">
				<c:if test="${!empty customPost}">
				
					<!-- 프로필 사진 -->	
					<c:if test="${customPost.anonymous == 0}"> <!-- 익명처리 off -->
						<c:if test="${empty member.photoname}">
						<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" class="picture large">	
						</c:if>
						<c:if test="${!empty member.photoname}">
						<img src="${pageContext.request.contextPath}/member/photoView.do" class="picture large">
						</c:if>
					</c:if>
					<c:if test="${customPost.anonymous == 1}"> <!-- 익명처리 on  -->
						<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" class="picture large">	
					</c:if>
					
					<!-- 작성자 아이디 -->
					<div class="profile">
						<c:if test="${customPost.anonymous == 0}"> <!-- 익명처리 off -->
							<h3 class="large">${id}</h3>
						</c:if>
						<c:if test="${customPost.anonymous == 1}"> <!-- 익명처리 on  -->
							<h3 class="large">익명</h3>
						</c:if>
						<!-- 작성일 -->
						<time class="large"><fmt:formatDate value="${customPost.reg_date}" pattern="MM/dd HH:MM"/></time>
					</div>
					<ul class="status">
						<li class="massagesend"><a onclick="location.href='massageSend.do?id=${id}&&anonymous=${customPost.anonymous}'">쪽지</a></li>
						<li class="abuse">신고</li>
					</ul>
					<hr>
					<!-- 내용 -->		
					<p class="large">
						${customPost.content}
					</p>
					<ul class="status">
						<li class="attach"><!-- 총 사진 수 --></li>
						<li title="추천" class="vote"><!-- 총 추천 수 --></li>
						<li title="댓글" class="comment"><!-- 총 댓글 수 --></li>
						<li title="즐겨찾기" class="scrap"><!-- 총 즐겨찾기 수 --></li>
					</ul>
					<hr>
					<!-- 첨부파일 -->
					<div class="ataches full">
						<%-- <c:if forEach>사용 첨부파일 list가 있을 때마다 반복 
								<figure class="attach">
									<img src="customPostImageView.do?post_num=${customPost.post_num}" style="max-width:500px;"> 
								<figure>	
								<figcaption>
									${사진에 대한 코멘트} ex)"아이보리 색이고 (위 사진 참고) 이뻐요!!!"<br>"10000원에 팔아요!"
								</figcaption>
							</c:if forEach>
						--%>
						<c:if test="${!empty customPost.filename}"> <!-- filename이 있으면 image가 있는거 -->
							<img src="customPostImageView.do?post_num=${customPost.post_num}" style="max-width:500px;"> <!-- 세션에 없기 때문에 get방식으로 넘겨줘야 함 -->
						</c:if>
					</div>
					<hr>
				<%-- 	<form:hidden path="anonymouse"/> <!-- 게시글의 댓글 익명허용 여부 --> --%>
				</c:if>
			</a>
		</article>
	</div>
</div>




