<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE !html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>자유게시판에 쓴 글</h1>
<div>
	<c:if test="${freecount == 0}">
	<div class="align-center nope">등록된 게시글이 없습니다.</div>
	</c:if>
	<c:if test="${freecount > 0}">
	<!-- 게시판 시작 -->
			<c:forEach var="board" items="${freelist}">
				<article class="board_view">
						<h2><a href="${pageContext.request.contextPath}/freeBoard/detail.do?post_num=${board.post_num}">${board.title}</a></h2>
						<p class="small">${board.content}</p>
						<time class="small">${board.modify_date}</time>
						${board.id}
					<div class="comments"></div>
				</article>
			</c:forEach>
	<!-- 게시판 끝 -->
		<div class="align-center">${freepagingHtml}</div>
	</c:if>
	<form id="freeSearch_form" action="freedBoardWritedlist.do" id="search_form" method="get"> <!-- get방식으로 http의 post_num= 을 넘겨줌 -->
		<ul class="search">
			<li>
				<select name="freekeyfield" id="freekeyfield">
					<option value="title">제목</option>
					<option value="id">ID</option>
					<option value="content">내용</option>
					<option value="all">전체</option>
				</select>
			</li>
			<li>
				<input type="text" name="freekeyword" id="freekeyword">
			</li>
			<li>
				<input id="freebtn1" type="submit" value="찾기">
			</li>
		</ul>
</form>
</div>
</body>
</html>