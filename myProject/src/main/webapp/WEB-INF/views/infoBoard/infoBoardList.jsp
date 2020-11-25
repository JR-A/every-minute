<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="page-main-style">
	<h2 class="title">정보게시판</h2>
	<form action="infoBoardList.do" id="search_form" method="get"> <!-- get방식으로 http의 post_num= 을 넘겨줌 -->
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="title">제목</option>
					<option value="id">ID</option>
					<option value="content">내용</option>
					<option value="all">전체</option>
				</select>
			</li>
			<li>
				<input type="text" name="keyword" id="keyword">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="목록" onclick="location.href='infoBoardList.do'">
				<input type="button" value="글쓰기" onclick="location.href='write.do'">
			</li>
		</ul>
	</form>

	
	
	<c:if test="${count == 0}">
	<div class="align-center">등록된 게시글이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<!-- 게시판 시작 -->
			<c:forEach var="board" items="${list}">
				<article class="board_view">
					<a class="article">
						<h2><a href="detail.do?post_num=${board.post_num}">${board.title}</a></h2>
						<p class="small">${board.content}</p>
						<time class="small"><fmt:formatDate value="${board.modify_date}" pattern="MM/dd HH:MM"/></time>
						${board.id}
					</a>
					<div class="comments"></div>
				</article>
			</c:forEach>
	<!-- 게시판 끝 -->
		<div class="align-center">${pagingHtml}</div>
	</c:if>
	
</div>