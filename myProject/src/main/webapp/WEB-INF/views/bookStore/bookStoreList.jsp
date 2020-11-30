<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main-style">
	<div class="align-right">
		<c:if test="${ !empty user }">
		<input type="button" value="판매등록" onclick="location.href='bookStoreWrite.do'">
		</c:if>
	</div>
	
	<c:if test="${ count == 0 }">
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${ count > 0 }">
	<div>
		<table>
			<c:forEach var="bookStoreVO" items="${ list }">
			<tr>
				<td>판매희망가 : <a href="bookStoreView.do?bs_num=${ bookStoreVO.bs_num }">${ bookStoreVO.bs_selling_price }</a></td>
				<td>${ bookStoreVO.bs_comment }</td>
				<td>${ bookStoreVO.bs_condition }</td>
				<td>${ bookStoreVO.bs_method }</td>
				<td>${ bookStoreVO.bs_address }</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="align-center">${ pagingHtml }</div>
	</c:if>
</div>