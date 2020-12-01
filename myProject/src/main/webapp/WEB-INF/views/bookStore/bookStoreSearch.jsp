<%@page import="kr.spring.bookstore.controller.BookController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.spring.bookstore.vo.BookVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String query = request.getParameter("query");

BookController con = new BookController();

List<BookVO> books = new ArrayList<BookVO>();
if(query != null && query.length() > 0){
	books = con.getBookList(query);
}
%>
<div class="page-main-style">
	<form>
		<div class="align-center">
			<input type="text" name="query" placeholder="ISBN 또는 책제목 검색">
			<input type="submit" value="검색">
		</div>
	</form>
	<%-- <% if(books.size() == 0) { %>
	<div class="align-center">검색 결과가 없습니다.</div>
	<% } %> --%>
	<% for(BookVO book : books){ %>
	<form>
		<table>
			<tr>
				<td width="100"><img src="<%= book.getThumbnail() %>"></td>
				<td>
					<%= book.getTitle() %><br>
					<%= book.getPublisher() %><br>
					<%= book.getPrice() %><br>
				</td>
				<td width="30"><input type="submit" value="선택"></td>
			</tr>
		</table>
	</form>
	<% } %>
</div>