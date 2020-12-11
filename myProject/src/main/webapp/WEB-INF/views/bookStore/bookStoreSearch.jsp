<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bookStoreSearch.css">
<div class="page-main-style">
	<form action="bookStoreSearch.do">
		<div class="align-center search_form">
			<input class="search_txt" type="text" name="query" placeholder="ISBN 또는 책제목 검색">
			<input class="search_btn" type="submit" value="검색">
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
					<h2>${ book.title }</h2>
					<p><span class="publisher_title">출판사</span><br>${ book.publisher }</p>
					<h3>${ book.price }원</h3>
				</td>
				<td class="submit" width="30"><input type="submit" value="선택"></td>
			</tr>
		</table>
	</form>
	</c:forEach>
	</c:if>
</div>