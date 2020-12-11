<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="right-side">
<!-- 디비에서 가져와서 forEach로 보여줘야함! -->
	<div class="board">
		<div id="hotPost" class="card">
			<h3>HOT게시물</h3>
		
			<c:if test="${empty f_hotPostList and empty i_hotPostList and empty c_hotPostList}">
               <div class="align-center">아직 HOT 게시글이 없습니다.</div>
            </c:if>
            
			<!-- 자유 게시판 - HOT게시글 목록 -->
		  	<c:if test="${!empty f_hotPostList}">
				<c:forEach var="freeBoardVO" items="${f_hotPostList}">
					<a class="article" href="${pageContext.request.contextPath}/freeBoard/detail.do?post_num=${freeBoardVO.post_num}">
						<p>${freeBoardVO.content}</p>
						<%-- <time>
							${freeBoardVO.reg_date}
						</time> --%>
						<h4>from 자유게시판</h4>
						<hr>
					</a>
				</c:forEach>
			</c:if> 
			
			<!-- 정보 게시판 - HOT게시글 목록 -->
			<c:if test="${!empty i_hotPostList}">
				<c:forEach var="infoPost" items="${i_hotPostList}">
					<a class="article" href="${pageContext.request.contextPath}/infoBoard/detail.do?post_num=${infoPost.post_num}">
						<p>${infoPost.content}</p>
						<%-- <time>
							<fmt:formatDate value="${infoPost.reg_date}" type="time" pattern="MM/dd HH:mm" />
						</time> --%>
						<h4>from 정보게시판</h4>
						<hr>
					</a>
				</c:forEach>
			</c:if>			
				
			<!-- 사용자 생성 게시판 - HOT게시글 목록 -->
			<c:if test="${!empty c_hotPostList}">
				<c:forEach var="customPost" items="${c_hotPostList}">
					<a class="article" href="${pageContext.request.contextPath}/customBoard/customPostDetail.do?post_num=${customPost.post_num}&&board_num=${customPost.board_num}">
						<p>${customPost.content}</p>
						<time>
							<fmt:formatDate value="${customPost.reg_date}" type="time" pattern="MM/dd HH:mm" />
						</time>
						<h4>from ${customPost.title}</h4>
						<hr>
					</a>
				</c:forEach> 
			</c:if>
		</div>
		
		<div id="bestPost" class="card">
			<h3>오늘의 BEST게시물</h3>
			
			<c:if test="${empty best_customPostVO and empty best_freeBoardVO and empty best_infoBoardVO}">
				<div class="align-center">오늘의 BEST 게시물이 없습니다.</div>
			</c:if>
			<!-- sessionScopex.customPostVO -->
			<!-- 베스트 게시글이 사용자 생성 게시판 게시글 일 경우 -->
			<c:if test="${!empty best_customPostVO}">
				<a class="article" href="${pageContext.request.contextPath}/customBoard/customPostDetail.do?post_num=${best_customPostVO.post_num}&board_num=${best_customPostVO.board_num}">
					<p>${best_customPostVO.content}</p>
					<time>
						<fmt:formatDate value="${best_customPostVO.reg_date}" type="time" pattern="MM/dd HH:mm" />
					</time>
					<hr>
				</a>
			</c:if>
			
			<!-- 베스트 게시글이 자유게시판 게시글 일 경우 -->
			<c:if test="${!empty best_freeBoardVO}">
					<a class="article" href="${pageContext.request.contextPath}/freeBoard/detail.do?post_num=${best_freeBoardVO.post_num}">
					<p>${best_freeBoardVO.content}</p>
					<%-- <time>
						<fmt:formatDate value="${best_freeBoardVO.reg_date}" type="time" pattern="MM/dd HH:mm" />
					</time> --%>
					<hr>
				</a>
			</c:if>
			
			<!-- 베스트 게시글이 정보생성 게시판 게시글 일 경우 -->
			<c:if test="${!empty best_infoBoardVO}">
					<a class="article" href="${pageContext.request.contextPath}/infoBoard/detail.do?post_num=${best_infoBoardVO.post_num}">
					<p>${best_infoBoardVO.content}</p>
					<%-- <time>
						<fmt:formatDate value="${best_infoBoardVO.reg_date}" type="time" pattern="MM/dd HH:mm" />
					</time> --%>
					<hr>
				</a>
			</c:if>
		</div>
	</div>
</div>


