<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customBoard.css">
<script>
	setTimeout(function(){ location.reload(); }, 3000); // 3000밀리초 = 3초
</script>

<div class="page-main-style">

	<!-- 제목&소제목 -->
	<h2 class="title">
		<a id="title" href="customPostList.do?board_num=${boardInfo.board_num}">${boardInfo.title}</a>
		<br>
		<span id="subtitle">${boardInfo.subtitle}</span>
	</h2>
	
	<form action="customPostList.do" id="search_form" method="get">
		<!-- 유효성 검사를 하지 않을 경우에는 form:hidden 사용금지 -->
		<input type="hidden" name="board_num" value="${boardInfo.board_num}"/> 
		<ul class="search">
			<li>
				<select name="keyfield" id="keyfield">
					<option value="all">전체</option>		
					<option value="hashtag">해시태그</option>
					<option value="content">글 내용</option>
				</select>
			</li>
			<li>
				<input type="text" name="keyword" id="keyword">
			</li>
			<li>
				<input type="submit" value="찾기">
				<input type="button" value="글 작성" onclick="location.href='customPostWrite.do?board_num=${boardInfo.board_num}'">
			</li>
		</ul>
	</form>
	
	<div class="align-right">
		<%--수정 삭제의 경우는 로그인이 되어있고 로그인한 회원번호와 작성자 회원번호가 일치해야 함 --%>
		<c:if test="${!empty user && user.mem_num == boardInfo.mem_num}">
			<ul class="status" style="cursor: pointer;">
				<li><a onclick="location.href='updateCustomBoard.do?board_num=${boardInfo.board_num}'">수정</a></li>
				<li id="delete_btn">삭제</li>
			</ul>
			
			<script>
				var delete_btn = document.getElementById('delete_btn');
				//이벤트 연결
				delete_btn.onclick=function(){
					var choice = window.confirm('게시판을 삭제하시겠습니까?');
					if(choice){
						//게시글이 존재하면 해당 게시판에 있는 게시글 삭제 여부 체크
						var hasPostCount = ${hasPostCount};
						if(hasPostCount>0){
							var truncate = window.confirm('게시글이 존재합니다.<br>게시판에 존재하는 모든 게시글을 삭제하시겠습니까?');
							if(truncate){
								location.href="deleteCustomBoard.do?board_num=${boardInfo.board_num}"; //전체 게시글 삭제 & 게시판 삭제
							}
							lacation.href="customPostList.do?board_num=${boardInfo.board_num}";
						} 
						//게시글이 없다면 바로 삭제
						location.href="deleteCustomBoard.do?board_num=${boardInfo.board_num}";
					}
				};
			</script>
		</c:if>
	</div>	
	
	<!-- 게시글 목록 -->
	<c:if test="${count == 0}">
		<div class="align-center">등록된 게시글이 없습니다.</div>
	</c:if>
	
	<c:if test="${count>0}">
		<c:forEach var="customPost" items="${postList}"> <!-- 해당 게시판에 존재하는 게시글들의 목록 -->
			<article class="board_view">
				<a class="article" href="customPostDetail.do?post_num=${customPost.post_num}&&board_num=${customPost.board_num}">
					<!-- 실명 게시글 -->
					<c:if test="${customPost.anonymous == 0}"> 
						<!-- 프로필 사진 -->	
						<c:if test="${empty member.photoname}">
							<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" class="picture large">	
						</c:if>
						<c:if test="${!empty member.photoname}">
							<img src="${pageContext.request.contextPath}/member/photoView.do" class="picture large">
						</c:if>
						<!-- 작성자 아이디 -->
						<div class="profile">
							<h3 class="large">${customPost.id}</h3>
							<!-- 작성일 -->
							<time class="large"><fmt:formatDate value="${customPost.reg_date}" pattern="MM/dd HH:mm"/></time>
						</div>
					</c:if>
					
					<!-- 익명 게시글 -->
					<c:if test="${customPost.anonymous == 1}">
						<!-- 프로필 사진 -->	
						<c:if test="${empty member.photoname}">
							<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" class="picture large">	
						</c:if>
						<!-- 작성자 아이디 -->
						<div class="profile">
							<h3 class="large">익명</h3>
							<time class="large"><fmt:formatDate value="${customPost.reg_date}" pattern="MM/dd HH:mm"/></time>
						</div>
					</c:if>
					<span id="div_content"></span>
					<!-- 내용 -->		
					
					<p class="small">${customPost.content}</p>
					
					<c:if test="${boardInfo.type == 1}"> <!-- 사진형 게시판일 경우만 목록에 사진 보이게 -->
						<!-- 첨부파일 -->
						<c:if test="${!empty customPost.filename}"> <!-- filename이 있으면 image가 있는거 -->
							<div class="align-left">
								<img src="customPostImageView.do?post_num=${customPost.post_num}&&board_num=${customPost.board_num}" style="max-width:150px;"> <!-- 세션에 없기 때문에 get방식으로 넘겨줘야 함 -->
							</div>
						</c:if>
					</c:if>
					</a>
					
					<div class ="wrapstatus">
						<ul class="status">
								<li class="vote" id="like_check">${customPost.like_cnt}</li>
								<li class="comm">${customPost.comment_cnt}</li>
								<li class="fav">${customPost.fav_cnt}</li>
						</ul>
						<span style="visibility:hidden;">span</span>
					</div>
					
				</article>
			</c:forEach>
		<div class="align-center">${pagingHtml}</div>
	</c:if>
</div>





