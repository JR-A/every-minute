<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <div class="page-main-style">
	<h2>쪽지함 목록</h2>
	<div class="align-right">
		<c:if test="${!empty user}">
		<input type="button" value="받은 쪽지함" onclick="location.href='messageList.do'">
		<input type="button" value="보낸 쪽지함" onclick="location.href='messageSendList.do'">
		<input type="button" value="쪽지보내기" onclick="location.href='write.do'">
		</c:if>
	</div>
	<c:if test="${count == 0}">
	<div class="align-center">등록된 쪽지가 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
		<table>
			<tr>
				<th>받은 사람</th>
				<th>내용</th>
				<th>날짜</th>
				<th>읽기 여부</th>
			</tr>
			<c:forEach var="message" items="${list}">
			<tr>
				<td>
				<c:if test="${message.anonymous == 0}">${message.target_id}</c:if>
				<c:if test="${message.anonymous == 1}">익명</c:if>
				</td>
				<td><a href="messageView.do?msg_num=${message.msg_num}">${message.content}</a></td>
				<td>${message.reg_date}</td>
				<td><c:if test="${message.msg_check == 0}">읽지 않음</c:if>
				<c:if test="${message.msg_check == 1}">읽음</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">${pagingHtml}</div>
	</c:if>
</div>






