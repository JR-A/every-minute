<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE !html>
<html>
<head>
<style type="text/css">
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>정보게시판에 쓴 글</h1>
<div>
<c:if test="${infocount == 0}">
	<div class="align-center nope">등록된 게시글이 없습니다.</div>
	</c:if>
	<c:if test="${infocount > 0}">
	<!-- 게시판 시작 -->
			<c:forEach var="board" items="${infolist}">
				<article class="board_view">
						<h2><a href="${pageContext.request.contextPath}/infoBoard/detail.do?post_num=${board.post_num}">${board.title}</a></h2>
						<p class="small">${board.content}</p>
						<time class="small"><fmt:formatDate value="${board.modify_date}" pattern="yyyy-MM-dd HH:MM:ss"/></time>
						${board.id}
					<div class="comments"></div>
				</article>
			</c:forEach>
	<!-- 게시판 끝 -->
		<div class="align-center">${infopagingHtml}</div>
	</c:if>
	<form action="writeBoardList.do" id="search_form" method="get"> <!-- get방식으로 http의 post_num= 을 넘겨줌 -->
		<ul class="search">
			<li>
				<select name="infokeyfield" id="infokeyfield">
					<option value="title">제목</option>
					<option value="id">ID</option>
					<option value="content">내용</option>
					<option value="all">전체</option>
				</select>
			</li>
			<li>
				<input type="text" name="infokeyword" id="infokeyword">
			</li>
			<li>
				<input type="submit" value="찾기">
			</li>
		</ul>
</form>
</div>
</body>
</html>