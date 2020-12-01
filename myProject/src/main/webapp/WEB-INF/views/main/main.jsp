<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<li><input type="button" class="member_buttons" onclick="" value="내가 쓴글"></li>
		<li><input type="button" class="member_buttons" onclick="" value="댓글 단 글"></li>
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
			<h3>사용자생성 게시판</h3>
			<ul>
				<li><a href="#">게시글1    방금</a></li>
				<li><a href="#">게시글2  n분 전</a></li>
				<li><a href="#">게시글3  n분 전</a></li>
			</ul>
		</div>
	</div>
</div>