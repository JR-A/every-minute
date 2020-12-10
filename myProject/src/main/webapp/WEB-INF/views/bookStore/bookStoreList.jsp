<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<div class="page-main-style">
	<div>
		<form action="bookStoreList.do" id="search_form" method="get">
			<ul class="search">
				<li>
					<select name="keyfield" id="keyfield">
						<option value="all">전체</option>
						<option value="isbn">ISBN</option>
						<option value="bs_selling_price">가격</option>
					</select>
				</li>
				<li><input type="text" name="keyword" id="keyword"></li>
				<li>
					<input type="submit" value="검색">
					<input type="button" value="목록" onclick="location.href='bookStoreList.do'">
				</li>
			</ul>
		</form>
	</div>
	<div class="align-right">
		<c:if test="${ !empty user }">
		<input type="button" value="판매등록" onclick="location.href='bookStoreSearch.do'">
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
				<td>
				<a href="bookStoreView.do?bs_num=${ bookStoreVO.bs_num }">ISBN : ${ bookStoreVO.isbn }</a>
				</td>
				<td>판매희망가 : ${ bookStoreVO.bs_selling_price }</td>
				<c:if test="${ bookStoreVO.bs_complete == 0 }">
				<td>판매중</td>
				</c:if>
				<c:if test="${ bookStoreVO.bs_complete > 0 }">
				<td>판매 완료</td>
				</c:if>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="align-center">${ pagingHtml }</div>
	</c:if>
</div>
