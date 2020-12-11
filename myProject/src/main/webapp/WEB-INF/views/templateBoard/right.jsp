<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/rightside.css">

<div class="rightside">
	<div class="card">
		<div class="board">
			<h3><a>HOT게시물</a></h3>
		
			<c:if test="${empty f_hotPostList and empty i_hotPostList and empty c_hotPostList}">
               <div>아직 HOT 게시글이 없습니다.</div>
            </c:if>

			<!-- 자유 게시판 - HOT게시글 목록 -->
		  	<c:if test="${!empty f_hotPostList}">
				<c:forEach var="freeBoardVO" items="${f_hotPostList}">
					<a class="article" href="${pageContext.request.contextPath}/freeBoard/detail.do?post_num=${freeBoardVO.post_num}">
						<p class="title">${freeBoardVO.title}</p>				
						<p class="small">${freeBoardVO.content}</p>
						<h4>자유게시판</h4>
						<ul class="status">
							<li class="vote active">${freeBoardVO.like_cnt}</li>
							<li class="comment active">${freeBoardVO.reply_cnt}</li>
						</ul>
						<hr>
					</a>
				</c:forEach>
			</c:if> 
			
			<!-- 정보 게시판 - HOT게시글 목록 -->
			<c:if test="${!empty i_hotPostList}">
				<c:forEach var="infoPost" items="${i_hotPostList}">
					<a class="article" href="${pageContext.request.contextPath}/infoBoard/detail.do?post_num=${infoPost.post_num}">
						<p class="title">${infoPost.title}</p>
						<p class="small">${infoPost.content}</p>
						<h4>정보게시판</h4>
						<ul class="status">
							<li class="vote active">${infoPost.like_cnt}</li>
							<li class="comment active">${infoPost.reply_cnt}</li>
						</ul>
						<hr>
					</a>
				</c:forEach>
			</c:if>			
				
			<!-- 사용자 생성 게시판 - HOT게시글 목록 -->
			<c:if test="${!empty c_hotPostList}">
				<c:forEach var="customPost" items="${c_hotPostList}">
					<a class="article" href="${pageContext.request.contextPath}/customBoard/customPostDetail.do?post_num=${customPost.post_num}&&board_num=${customPost.board_num}">
						<p class="title">${customPost.content}</p>
						<h4>${customPost.title}</h4>
						<ul class="status">
							<li class="vote active">${customPost.like_cnt}</li>
							<li class="comment active">${customPost.comment_cnt}</li>
						</ul>
						<hr>
					</a>
				</c:forEach> 
			</c:if>
		</div>
	</div>
	<div class="card">
		<div class="board">
			<h3><a>오늘의 BEST게시물</a></h3>
			
			<c:if test="${empty best_customPostVO and empty best_freeBoardVO and empty best_infoBoardVO}">
				<div>오늘의 BEST 게시물이 없습니다.</div>
			</c:if>
			
			<!-- 베스트 게시글이 자유게시판 게시글 일 경우 -->
			<c:if test="${!empty best_freeBoardVO}">
				<a class="list" href="${pageContext.request.contextPath}/freeBoard/detail.do?post_num=${best_freeBoardVO.post_num}">
					<time><fmt:parseDate var="dateTempParse" value="${freeBoardVO.modify_date}" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate value="${dateTempParse}" pattern="MM/dd HH:mm" /></time>
					<p>${best_freeBoardVO.content}</p>
					<%-- <time>
						<fmt:formatDate value="${best_freeBoardVO.reg_date}" type="time" pattern="MM/dd HH:mm" />
					</time> --%>
					<hr>
				</a>
			</c:if>
			
			<!-- 베스트 게시글이 정보생성 게시판 게시글 일 경우 -->
			<c:if test="${!empty best_infoBoardVO}">
					<a class="list" href="${pageContext.request.contextPath}/infoBoard/detail.do?post_num=${best_infoBoardVO.post_num}">
					<time><fmt:parseDate var="dateTempParse" value="${infoBoardVO.modify_date}" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate value="${dateTempParse}" pattern="MM/dd HH:mm" /></time>
					<p>${best_infoBoardVO.content}</p>
					<%-- <time>
						<fmt:formatDate value="${best_infoBoardVO.reg_date}" type="time" pattern="MM/dd HH:mm" />
					</time> --%>
					<hr>
				</a>
			</c:if>
			
			<!-- sessionScopex.customPostVO -->
			<!-- 베스트 게시글이 사용자 생성 게시판 게시글 일 경우 -->
			<c:if test="${!empty best_customPostVO}">
				<a class="list" href="${pageContext.request.contextPath}/customBoard/customPostDetail.do?post_num=${best_customPostVO.post_num}&board_num=${best_customPostVO.board_num}">
					<time>
						<fmt:formatDate value="${best_customPostVO.reg_date}" type="time" pattern="MM/dd HH:mm" />
					</time>
					<p>${best_customPostVO.content}</p>
					<hr>
				</a>
			</c:if>
			
			
		</div>
	</div>
</div>


