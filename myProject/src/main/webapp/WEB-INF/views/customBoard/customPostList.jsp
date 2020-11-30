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
		<!-- 검색 -->
		<div class="pagination">
			<form action="customPostList.do" id="searchArticleForm" class="search" method="get">
				<select name="keyfield" id="keyfield">
					<option value="1">전체</option>
					<option value="2">해시태그</option>
					<option value="3">글 내용</option>
				</select>
				<input name="keyword" placeholder="검색어를 입력하세요." class="text">
				<input type="submit" value="찾기">
			</form>
			
			<!-- 글 작성 버튼-->
			<div class="align-right">
				<c:if test ="${!empty user}">
				<input type="button" value="글 작성" onclick="location.href='customPostWrite.do?board_num=${boardInfo.board_num}'">
				</c:if>
			</div>
		</div>
		
		<!-- 게시글 목록 -->
		<div id="container" class="articles">
			<hr>
			<c:if test="${count == 0}">
				<div class="align-center">등록된 게시글이 없습니다.</div>
			</c:if>
			
			<c:if test="${count>0}">
				<article>
					<c:forEach var="customPost" items="${postList}"> <!-- 해당 게시판에 존재하는 게시글들의 목록 -->
						<a class="article" href="customPostDetail.do?post_num=${customPost.post_num}&&board_num=${customPost.board_num}"> <!-- 해당 글번호의 상세페이지 -->
						
						<c:if test="${customPost.anonymous == 0}"> <!-- 실명 게시글 -->
							<!-- 프로필 사진 -->	
							<c:if test="${empty member.photoname}">
								<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" class="picture medium">	
							</c:if>
							<c:if test="${!empty member.photoname}">
								<img src="${pageContext.request.contextPath}/member/photoView.do" class="picture medium">
							</c:if>
							<!-- 작성자 아이디 -->
							<h3 class="medium">${id}</h3>
						</c:if>
						
						<c:if test="${customPost.anonymous == 1}"> <!-- 익명 게시글 -->
							<!-- 프로필 사진 -->	
							<c:if test="${empty member.photoname}">
								<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" class="picture medium">	
							</c:if>
							<!-- 작성자 아이디 -->
							<h3 class="medium">익명</h3>
						</c:if>
						
						<!-- 작성일 -->
						<time class="medium"><fmt:formatDate value="${customPost.reg_date}" pattern="MM/dd HH:MM"/></time>
						<hr>
						
						<!-- 내용 -->		
						<p class="medium">
							${customPost.content}
							<!-- 첨부파일 -->
							<c:if test="${!empty customPost.filename}"> <!-- filename이 있으면 image가 있는거 -->
								<div class="align-center">
									<img src="customPostImageView.do?post_num=${customPost.post_num}" style="max-width:500px;"> <!-- 세션에 없기 때문에 get방식으로 넘겨줘야 함 -->
								</div>
							</c:if>
						</p>
					</a>
					<hr>
				</c:forEach>
				</article>
				<div class="align-center">${pagingHtml}</div>
			</c:if>
		</div>	
</div>







