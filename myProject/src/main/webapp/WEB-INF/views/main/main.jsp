<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customBoard.css">
<div class="member_info">	
	<article id="main_profile">
		<ul>
			<c:if test="${empty user.photoname}">
			<li>
			<img id="main_profile_image" src="${pageContext.request.contextPath}/resources/images/blank.jpg" width="100" height="100">	
			</li>			
			</c:if>
			<c:if test="${!empty user.photoname}">
			<li>
			<img id="main_profile_image" src="${pageContext.request.contextPath}/member/photoView.do" width="100" height="100">	
			</li>			
			</c:if>			
			<li>${user.nickname}님 로그인중</li>
		</ul>
	</article>
	<ul id="ul_for_button">
		<li><input type="button" class="member_buttons" onclick="location.href='${pageContext.request.contextPath}/member/writedBoardlist.do'" value="내가 쓴글"></li>
		<li><input type="button" class="member_buttons" onclick="location.href='${pageContext.request.contextPath}/member/writedCommentList.do'" value="댓글 단 글"></li>
		<li><input type="button" class="member_buttons" onclick="" value="내 스크랩"></li>
	</ul>
</div>
<div class="main_board_list">
	<!-- 게시판별로 가장 최근에 작성된 게시글 3개만 표출-->
	<div class="list">
		<div id="freeBoard_List" class="card">
			<h3>자유게시판</h3>
			<ul>
				<li><a href="#">게시글1  n분 전</a></li>
				<li><a href="#">게시글2  n분 전</a></li>
				<li><a href="#">게시글3  n분 전</a></li>
			</ul>
		</div>
		<div id="infoBoard_List" class="card">
			<h3>정보게시판</h3>
			<ul>
				<li><a href="#">게시글1    방금</a></li>
				<li><a href="#">게시글2  n분 전</a></li>
				<li><a href="#">게시글3  n분 전</a></li>
			</ul>
		</div>
		<div id="customBoard_List" class="card">
			<div class="board">
				<h3 id="customBoard_title">사용자생성 게시판</h3>
							
				<!-- 게시글 목록 -->
				<c:if test="${postTop3List == null}">
					<div class="align-center">등록된 게시글이 없습니다.</div>
				</c:if>
				
				<c:if test="${postTop3List != null}"> <!-- article 클래스가 반복/ 시간은 몇분 전으로 표시 -->
					<c:forEach var="customPost" items="${postTop3List}">
						<a class="article" href="customPostDetail.do?post_num=${customPost.post_num}&&board_num=${customPost.board_num}">
							<p>${customPost.content}</p>
							<h4>${customPost.title}</h4>
							<!-- 첨부파일 -->
							<c:if test="${!empty customPost.filename}"> <!-- filename이 있으면 image가 있는거 -->
								<img class="thumbnail" alt="첨부사진" src="customPostImageView.do?post_num=${customPost.post_num}&&board_num=${customPost.board_num}"> <!-- 세션에 없기 때문에 get방식으로 넘겨줘야 함 -->
							</c:if>
							<time><fmt:formatDate value="${customPost.reg_date}" type="time" pattern="MM/dd HH:mm"/></time>
							<ul class="status">
								<li class="like active"><!-- 좋아요 수 --></li>
								<li class="comment active"><!-- 댓글 수 --></li>
							</ul>
							<hr>
						</a>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
</div>
