<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- 게시판 이름과 소제목  -->
<article>
	<h2>${boardInfo.title}</h2>
	<h4>${boardInfo.subtitle}</h4>
</article>
	
<!-- 작성내용 -->
<div>
	<form:form commandName="customPostVO" action="customPostWrite.do" enctype="multipart/form-data">
		<form:hidden path="board_num"/> 
		<form:errors element="div" cssClass="error-color"/>
		
		<p>
			<form:input path="content" placeholder="글 내용"/>
			<form:errors path="content" cssClass="error-color"/>
		</p>
		
		<input type="file" name="upload" id="upload" style="display:none" accept="image/gif,image/png,image/jpeg">
		<%-- <div class="button" onclick="onclick=document.all.file.click()">
            <img id="fileImg" src="${pageContext.request.contextPath}/resources/images/customBoard/fileImg.png">
		</div> --%>
		
		<c:if test="${!empty infoBoardVO.filename}">
			<br>
			<span>(${infoBoardVO.filename})파일이 등록되어 있습니다 다시 업로드하면 기존 파일은 삭제됩니다.</span>
		</c:if>
		
		<div class="align-center">
			<input type="submit" value="등록">
			<input type="button" value="목록" onclick="location.href='infoBoardList.do'">
		</div>
		
	</form:form>
</div>



