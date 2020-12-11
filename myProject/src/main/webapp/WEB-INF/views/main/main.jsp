<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- jstl의 function 라이브러리 사용(length 등) -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.container.css">

<div id="container" class="community">	
	<div class="leftside">	
		<div class="card pconly">
			<form class="logged">
				<c:if test="${empty user.photoname}">
				<img class="picture" src="${pageContext.request.contextPath}/resources/images/blank.jpg" width="100" height="100">			
				</c:if>
				<c:if test="${!empty user.photoname}">
				<img class="picture" src="${pageContext.request.contextPath}/member/photoView.do" width="100" height="100">				
				</c:if>
				<p class="nickname">${user.nickname}</p>
				<p class="school">${user.stu_num}</p>
				<p class="school">${user.id}</p>
				<hr>
			</form>
		</div>
		<div class="card">
			<div class="menus">
				<a class="myarticle" href="${pageContext.request.contextPath}/member/writedBoardlist.do">내가 쓴 글</a>
				<a class="mycommentarticle" href="${pageContext.request.contextPath}/member/writedCommentList.do">댓글 단 글</a>
				<hr>
			</div>
		</div>
	</div>
	<div class="main">
		<div class="card">
			<div class="board">
				<h3><a href="${pageContext.request.contextPath}/freeBoard/freeBoardList.do">자유 게시판</a></h3>
				<c:if test="${empty freePostTop3List || fn:length(freePostTop3List) <= 0}">
					<div>등록된 게시글이 없습니다.</div>
				</c:if>

				<c:if test="${!empty freePostTop3List && fn:length(freePostTop3List) > 0}">
					<c:forEach var="freeBoardVO" items="${freePostTop3List}">
						<a class="list" href="${pageContext.request.contextPath}/freeBoard/detail.do?post_num=${freeBoardVO.post_num}">	
							<time>
								<%--<fmt:formatDate value="${freeBoardVO.modify_date}" var="formattedDate" type="date" pattern="MM/dd HH:mm"/>${formattedDate}--%>		
								<fmt:parseDate var="dateTempParse" value="${freeBoardVO.modify_date}" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate value="${dateTempParse}" pattern="MM/dd HH:mm" />
							</time>
							<p>${freeBoardVO.title}</p>	
							<hr>
						</a>
					</c:forEach>
				</c:if>
			</div>
		</div>
		<div class="card">
			<div class="board">
				<h3><a href="${pageContext.request.contextPath}/infoBoard/infoBoardList.do">정보 게시판</a></h3>
				<c:if test="${empty infoTop3List || fn:length(infoTop3List) <= 0}">
					<div>등록된 게시글이 없습니다.</div>
				</c:if>
				
				<c:if test="${!empty infoTop3List && fn:length(infoTop3List) > 0}">
					<c:forEach var="infoBoardVO" items="${infoTop3List}">
						<a class="list" href="${pageContext.request.contextPath}/infoBoard/detail.do?post_num=${infoBoardVO.post_num}">
							<time>
								<%--<fmt:formatDate value="${infoBoardVO.modify_date}" var="formattedDate" type="date" pattern="MM/dd HH:mm"/>${formattedDate}	--%>	
							 	<fmt:parseDate var="dateTempParse" value="${infoBoardVO.modify_date}" pattern="yyyy-MM-dd HH:mm:ss" />
								<fmt:formatDate value="${dateTempParse}" pattern="MM/dd HH:mm" />
							</time>
							<p>${infoBoardVO.title}</p>
							<hr>
						</a>
					</c:forEach>
				</c:if>
			</div>
		</div>
		<div class="card">
			<div class="board">
				<h3><a href="#">사용자생성 게시판</a></h3>
				<c:if test="${empty postTop3List || fn:length(postTop3List) <= 0}">
					<div>등록된 게시글이 없습니다.</div>
				</c:if>
				
				<c:if test="${!empty postTop3List && fn:length(postTop3List) > 0}">
					<c:forEach var="customPost" items="${postTop3List}">
						<a class="list" href="${pageContext.request.contextPath}/customBoard/customPostDetail.do?post_num=${customPost.post_num}&board_num=${customPost.board_num}">
							<time>
								<%--<fmt:formatDate value="${customPost.modify_date}" var="formattedDate" type="date" pattern="MM/dd HH:mm"/>${formattedDate}--%>	
								<fmt:formatDate value="${customPost.reg_date}" type="time" pattern="MM/dd HH:mm" />
							</time>
							<p>${customPost.content}</p>
							<hr>
						</a>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
</div>