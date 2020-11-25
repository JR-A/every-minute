<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="page-main-style">
	<h2>글 수정</h2>
	<form:form commandName="freeboardVO" action="update.do"
								enctype="multipart/form-data">
	<form:hidden path="post_num"/>
	<form:errors element="div" cssClass="error-color"/>
			<ul>
			<li>
				<label for="title">제목</label>
				<form:input path="title"/>
				<form:errors path="title" cssClass="error-color"/>
			</li>
			<li>
				<label for="content">내용</label>
				<form:textarea path="content"/>
				<form:errors path="content" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload">이미지 파일 업로드</label>
				<input type="file" name="upload" id="upload"
										accept="image/gif,image/png,image/jpeg">
				<c:if test="${!empty freeBoardVO.filename}">
				<br>
				<span>(${FreeBoardVO.filename}) 파일이 등록되어 있습니다.
				다시 업로드하면 기존 파일은 삭제됩니다.</span>
				</c:if>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록"
					onclick="location.href='freeBoardList.do'">
			
		</div>
	</form:form>
</div>