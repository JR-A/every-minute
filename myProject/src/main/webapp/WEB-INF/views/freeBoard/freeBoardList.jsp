<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="<c:url value="/resources/js/jquery-3.5.1.min.js" />"></script>
<script src="/js/jquery.timeago.ko.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/freeBoard.css">

	<div class="page-main-style">
	<h1><a href="${pageContext.request.contextPath}/freeBoard/freeBoardList.do">자유게시판</a></h1>
 	<form action="freeBoardList.do" id="search_form" method="get">
		<ul class="search">
			<!-- 검색시 1부터3까지 select 기능 -->
			<li><select name="keyfield" id="keyfield">
					<option value="all">전체</option>
					<option value="hashtag">해시태그</option>
					<option value="title">글 제목</option>
					<option value="content">글 내용</option>
					
			</select></li>

			<!-- 검색 form -->
			<li><input type="text" name="keyword" id="keyword"></li>
			<li><input type="submit" value="찾기"></li>
			<li><input type="button" value="글쓰기" onclick="location.href='freeBoardWrite.do'"></li>
		</ul>
	
	</form>
	<c:if test="${count ==0}">
		<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>

	<c:if test="${count > 0}">
		<article>
			<c:forEach var="freeboard" items="${list}">
				<div class="free_post">
					<a href="detail.do?post_num=${freeboard.post_num}">
						<h3 class="free_title">${freeboard.title}</h3>
						<p class="small">${freeboard.content}</p>
						<time class="large"><fmt:formatDate value="${freeboard.reg_date}" pattern="MM/dd HH:MM"/></time> 익명
					</a>
				</div>
			</c:forEach>
			  <%request.setAttribute("toDay", new java.util.Date());%>
		</article>
		<div class="align-center">${pagingHtml}</div>

	</c:if>


</div>
