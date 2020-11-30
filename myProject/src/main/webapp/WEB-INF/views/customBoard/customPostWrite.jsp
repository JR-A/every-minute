<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customPost.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<div id="container" class="article">

	<!-- 제목&소제목 -->
	<div class="wrap title">
		<c:if test="${boardInfo.anonymous == 0}"> <!-- 실명 게시판 -->	
			<p id="alert">* 해당 게시판은 익명 처리가 불가능합니다 *</p>
		</c:if>
		<h1>
			<a href="customPostList.do">${boardInfo.title}</a>
		</h1>
		<p>${boardInfo.subtitle}</p>
		<!-- <hr> -->
	</div>
	
	<div class="wrap articles">
		<h3>글 작성</h3>
		<form:form commandName="customPostVO" id="write_form" action="customPostWrite.do" enctype="multipart/form-data">
			<input type="hidden" id="board_num" name="board_num" value="${boardInfo.board_num}"/>
			<input type="hidden" id="writer" name="writer" value="${user.id}"/>
			<!-- 작성내용 -->
			<p>
				<form:textarea path="content" cols="100" rows="8" placeholder="글 내용을 입력하세요."></form:textarea>
				<form:errors path="content" cssClass="error-color"/>
			</p>
			<!-- 첨부파일 -->
			<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg">
			<div class="clearBothOnly"></div>
			
			<c:if test="${boardInfo.anonymous == 1}"> <!--  익명 게시판 -->	
				<label class="check_anony">
					<input type="checkbox" id="check_anony" name="anonymous" value="1" checked>익명
				</label>
			</c:if>
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="목록" onclick="location.href='customBoardList.do'">
			</div>
		</form:form>
	</div>
</div>

	<!-- 
	<input type="checkbox" name="anonymous" value="1" checked>	
	<label for="anonymous">익명</label>
	
	<div class="align-center">
		<input type="submit" value="등록">
	</div> -->


