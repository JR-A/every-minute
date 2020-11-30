<%@page import="kr.spring.bookstore.controller.BookController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.spring.bookstore.vo.BookVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String query = request.getParameter("query");

BookController con = new BookController();

List<BookVO> books;
try {
	books = con.getBookList(query);
} catch(Exception e) {
	books = new ArrayList<BookVO>();
}
%>
<div class="page-main-style">
	<form>
		<input type="text" name="query" placeholder="ISBM 또는 책제목 검색">
		<input type="submit" value="검색">
		<input type="button" value="목록" onclick="location.href='bookStoreList.do'">
	</form>
	<form action="bookStoreWrite.do" method="post">
			<%
			for(int i=0; i<books.size(); i++) {
				BookVO book = books.get(i);
			%>
			<table>
				<tr>
					<td><a href="<%= book.getUrl() %>"><img alt="photo" src="<%= book.getThumbnail() %>"></a></td>
					<td>
						<input type="text" name="title" value="<%= book.getTitle() %>"><br>
						<input type="text" name="publisher" value="<%= book.getPublisher() %>"><br>
						<input type="text" name="price" value="<%= book.getPrice() %>">
					</td>
					<td><input type="submit" value="선택"></td>
				</tr>
			</table>
			<%
			}
			%>
	</form>
</div>