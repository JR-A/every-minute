<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE !html>
<html>
<style>
#writedList{
	text-align:center;
	padding-right:150px;
}

#freel{
	padding-right:20px;
	
}
#infol{
	padding-right:20px;
	padding-left:20px;
}
#customl{
	padding-left:20px;
}
.atag{
	cursor:pointer;
	font-size:16px;
}
.nope{
	padding-top:35px;
}

</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 자유게시판 목록 -->
<div id="freeboardWrited" style="display:none">
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
	<form id="freeSearch_form" > <!-- get방식으로 http의 post_num= 을 넘겨줌 -->
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
				<input id="freebtn1" type="button" value="찾기">
			</li>
		</ul>
</form>
</div>

<div id="infoboardWrited" style="display:none;">
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

<div id="customboardWrited" style="display:none;">

</div>



</body>
</html>