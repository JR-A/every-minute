<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="page-main-style">
	<h3>자유게시판</h3>
	<form:form commandName="freeBoardVO" action="freeBoardWrite.do"
										enctype="multipart/form-data">
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
				
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송" id="submit">
			<input type="button" value="목록"
					onclick="location.href='freeBoardList.do'">
			
		</div>
	</form:form>
	
</div>