<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bookStoreSearch.css">
<div class="page-main-style">
	<form action="bookStoreSearch.do">
		<div class="align-center">
			<input type="text" name="query" placeholder="ISBN 또는 책제목 검색">
			<input type="submit" value="검색">
		</div>
	</form>
	
	<c:if test="${fn:length(list) == 0}">
	<div class="align-center">검색 결과가 없습니다.</div>
	</c:if>
	
	<c:if test="${fn:length(list) > 0}">
	<c:forEach var="book" items="${ list }">
	<form action="bookStoreWrite.do" method="get">
	    <input type="hidden" name="isbn" value="${ book.isbn }">
		<table>
			<tr>
				<td width="100"><img src="${ book.thumbnail }"></td>
				<td>
					<p>${ book.title }</p>
					<p>${ book.publisher }</p>
					<p>${ book.price }</p>
				</td>
				<td class="submit" width="30"><input type="submit" value="선택"></td>
			</tr>
		</table>
	</form>
	</c:forEach>
	</c:if>
</div>