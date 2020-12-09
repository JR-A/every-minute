<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customBoard.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<!-- 제목&소제목 -->
<h2 class="title">
	<a id="title" href="customPostList.do?board_num=${boardInfo.board_num}">${boardInfo.title}</a>
	<br>
	<span id="subtitle">${boardInfo.subtitle}</span>
	<c:if test="${boardInfo.anonymous == 0}"> <!-- 실명 게시판 -->	
		<p id="anony_alert">* ${boardInfo.title}(은/는) 익명으로 작성할 수 없습니다 *</p>
	</c:if>
</h2>

<form:form commandName="customPostVO" id="write_customform" action="customPostWrite.do" enctype="multipart/form-data">
	<input type="hidden" id="board_num" name="board_num" value="${boardInfo.board_num}"/>
	<input type="hidden" id="writer" name="writer" value="${user.id}"/>
	<!-- 작성내용 -->
	<p class="board_p">
		<form:textarea path="content" cols="100" rows="8" placeholder="글 내용을 입력하세요."/>
		<form:errors path="content" cssClass="error-color"/>
	</p>
	<!-- 첨부파일 -->
	<input multiple="multiple" type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg">
	
	<c:if test="${boardInfo.anonymous == 1}"> <!--  익명 게시판 -->	
		<p class="check_anony">
			<input type="checkbox" id="check_anony" name="anonymous" value="1" checked>익명
		</p>
	</c:if>
	<div class="align-center">
		<input type="submit" value="등록">
	</div>
</form:form>

	<!-- 
	<input type="checkbox" name="anonymous" value="1" checked>	
	<label for="anonymous">익명</label>
	
	<div class="align-center">
		<input type="submit" value="등록">
	</div> -->


