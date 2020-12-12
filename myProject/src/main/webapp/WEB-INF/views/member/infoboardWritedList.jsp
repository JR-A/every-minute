<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE !html>
<html>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/freeBoard.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	.nope{
	position:absolute;
	padding-left:438px;
	}
</style>
<title>Insert title here</title>
</head>
<body>
<h2 class="title">
		<a
			>정보게시판에 쓴 글</a>
	</h2>
<form action="infoBoardWritedlist.do" id="search_form" method="get">
		<ul class="search_info">
			<li><select name="infokeyfield" id="keyfield">
					<option value="all">전체</option>
					<option value="tag">태그</option>
					<option value="title">글 제목</option>
					<option value="content">글 내용</option>
			</select></li>
			<li><input type="text" name="infokeyword" id="keyword"
				placeholder="검색어를 입력하세요."></li>
			<li><input type="submit" value="" id="submit"></li>
		</ul>
	</form>
<div>
	<c:if test="${infocount == 0}">
	<div class="nope">등록된 게시글이 없습니다.</div>
	</c:if>
		<c:if test="${infocount > 0}">
		<div id="wrap">
			<c:forEach var="infoboard" items="${infolist}">
				<article class="free_post">					
						<div class="article">
						<h2><a href="${pageContext.request.contextPath}/infoBoard/detail.do?post_num=${infoboard.post_num}">${infoboard.title}</a></h2>
							<p class="small">${infoboard.content}</p>
							<time class="small">${infoboard.modify_date}</time>
							<c:if test="${0 eq infoboard.anonymous}">${freeboard.id}</c:if>			
					        <c:if test="${1 eq infoboard.anonymous}">익명</c:if>
							<div class="wrapstatus">
								<ul class="status">
									<li class="vote" id="like_check">${infoboard.like_cnt}</li>
									<li class="comm">${infoboard.reply_cnt}</li>
								</ul>
							</div>
						</div>	
					</a>
				</article>			
			</c:forEach>				
		</div>
		<div class="align-center" id="pagenation">${infopagingHtml}</div>
	</c:if>

</div>
</body>
</html>