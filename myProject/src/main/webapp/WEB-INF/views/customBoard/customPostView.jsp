<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="page-main-style">
	<!-- 게시판 이름과 소제목 출력 -->
	<article>
		<h2>${boardInfo.title}</h2>
		<h4>${boardInfo.subtitle}</h4>
	</article>
	
	<c:if test="${!empty customPost}">
		<!-- 게시글 상세페이지 -->
		<article>
			<!-- 프로필 사진 -->	
				<c:if test="${customPost.anonymous == 0}"> <!-- 익명처리 off -->
					<c:if test="${empty member.photoname}">
					<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" width="70px" height="70px" class="picture medium">	
					</c:if>
					<c:if test="${!empty member.photoname}">
					<img src="${pageContext.request.contextPath}/member/photoView.do" width="70px" height="70px" class="picture medium">
					</c:if>
					
				</c:if>
					
				<c:if test="${customPost.anonymous == 1}"> <!-- 익명처리 on  -->
					<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" width="70px" height="70px" class="picture medium">	
				</c:if>
				
				<!-- 작성자 아이디 -->
				<c:if test="${customPost.anonymous == 0}"> <!-- 익명처리 off -->
					<h3 class="medium">${id}</h3>
				</c:if>
				<c:if test="${customPost.anonymous == 1}"> <!-- 익명처리 on  -->
					<h3 class="medium">익명</h3>
				</c:if>
				
				<!-- 작성일 -->
				<time class="medium"><fmt:formatDate value="${customPost.reg_date}" pattern="MM/dd HH:MM"/></time>
				<hr>
				
				<!-- 내용 -->		
				<p class="medium">
					${customPost.content}
					<!-- 첨부파일 -->
					<c:if test="${!empty customPost.filename}"> <!-- filename이 있으면 image가 있는거 -->
						<div class="align-center">
							<img src="customPostImageView.do?post_num=${customPost.post_num}" style="max-width:500px;"> <!-- 세션에 없기 때문에 get방식으로 넘겨줘야 함 -->
						</div>
					</c:if>
				</p>
			<div class="align-right">
				<!-- 수정 삭제의 경우 - 로그인한 아이디와 작성자가 일치해야 함-->
				<c:if test="${!empty user && user.mem_num == customPost.mem_num}">
					<input type="button" value="삭제" id="delete_btn"> <!-- confirm 창으로 띄우려고 함 -->
					<script type="text/javascript">
						var delete_btn = document.getElementById('delete_btn');
						//이벤트 연결
						delete_btn.onclick = function(){
							var choice = window.confirm('삭제하시겠습니까?');
							if(choice){
								location.href='customPostDelete.do?post_num=${postList.post_num}';
							}
						};
					</script>
				</c:if>
				<input type="button" value="목록" onclick="location.href='/main/main_board.do'">
			</div>
		</article>
	</c:if>
</div>



