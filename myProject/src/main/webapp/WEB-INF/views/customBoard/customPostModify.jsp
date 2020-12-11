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
			<p id="alert">* 해당 게시판은 익명 처리가 불가능합니다 *</p>
	</c:if>
</h2>

<form:form commandName="customPostVO" id="write_customform" action="customPostModify.do" enctype="multipart/form-data">
	<form:hidden path="board_num"/>
	<form:hidden path="post_num"/>
	<input type="hidden" id="writer" name="writer" value="${user.id}"/>
	<!-- 작성내용 -->
	<p class="board_p">
		<form:textarea path="content" cols="100" rows="8" placeholder="글 내용을 입력하세요."></form:textarea>
		<form:errors path="content" cssClass="error-color"/>
	</p>
	
	<!-- 파일업로드 -->
	<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg">
	<c:if test="${!empty customPostVO.filename}">
		<br>
		<span id="alert">(${customPostVO.filename})파일이 등록되어 있습니다. 다시 업로드하면 기존 파일은 삭제됩니다.</span>
	</c:if>
	
	<c:if test="${boardInfo.anonymous == 1}"> <!--  익명 게시판 -->	
		<p class="check_anony">
			<input type="checkbox" id="check_anony" name="anonymous" value="1" checked>익명
		</p>
	</c:if>
	<div class="align-center" id="submitModify">
		<input type="submit" value="등록">
	</div>
</form:form>
