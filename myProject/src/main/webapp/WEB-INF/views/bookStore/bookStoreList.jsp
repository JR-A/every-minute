<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bookStoreList.css">
<div class="page-main-style">
	<div>
		<form action="bookStoreList.do" id="search_form" method="get">
			<div class="top">
				<select class="option" name="keyfield" id="keyfield">
					<option value="all">전체</option>
					<option value="isbn">ISBN</option>
					<option value="bs_selling_price">가격</option>
				</select>
				<input type="text" name="keyword" id="keyword" placeholder="ISBN 또는 판매희망가로 검색">
				<input class="search_btn" type="submit" value="검색">
			</div>
		</form>
		<div class="menu">
			<input class="nav1" type="button" value="목록" onclick="location.href='bookStoreList.do'">
			<input class="nav2" type="button" value="판매등록" onclick="location.href='bookStoreSearch.do'">
		</div>
	</div>
	<c:if test="${ count == 0 }">
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${ count > 0 }">
	<div>
		<table>
			<c:forEach var="bookStoreVO" items="${ list }">
			<tr>
				<td onclick="location.href='bookStoreView.do?bs_num=${ bookStoreVO.bs_num }'">
				<div class="info">
					<div>ISBN : ${ bookStoreVO.isbn }</div>
					<div>거래 방법 : ${ bookStoreVO.bs_method }</div>
					<div>판매희망가 : ${ bookStoreVO.bs_selling_price }</div>
				</div>
				<div class="complete">
					<c:if test="${ bookStoreVO.bs_complete == 0 }"><div>판매중</div></c:if>
					<c:if test="${ bookStoreVO.bs_complete > 0 }"><div>판매 완료</div></c:if>
				</div>
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="align-center">${ pagingHtml }</div>
	</c:if>
</div>
