<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main-style">
	<form:form commandName="bookStoreVO" action="bookStoreUpdate.do">
		<form:hidden path="bs_num"/>
		<ul>
			<li><form:radiobutton path="bs_complete" value="0" label="판매중"/></li>
			<li><form:radiobutton path="bs_complete" value="1" label="판매 완료"/></li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정">
		</div>
	</form:form>
</div>