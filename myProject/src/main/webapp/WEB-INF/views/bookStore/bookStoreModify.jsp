<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bookStoreModify.css">
<div class="page-main-style">
	<form:form commandName="bookStoreVO" action="bookStoreUpdate.do">
		<form:hidden path="bs_num"/>
		<div class="align-center complete_form">
			<form:radiobutton path="bs_complete" value="0" label="판매중"/>
			<form:radiobutton path="bs_complete" value="1" label="판매 완료"/>
			<div class="submit_form">
				<input type="submit" class="submit_btn" value="수정">
			</div>
		</div>
	</form:form>
</div>