<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<div class="member_info">
	<article>
		<ul>
			<li>
			<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" width="130" height="130">	
			</li>
			<li>닉네임</li>
			<li>이름</li>
			<li>아이디</li>
			<input type="button" onclick="#" value="내 정보">
			<input type="button" onclick="#" value="로그아웃">
		</ul>
	</article>
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