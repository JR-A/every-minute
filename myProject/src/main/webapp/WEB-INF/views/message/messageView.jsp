<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="page-main-style">
	<h2>쪽지함</h2>
	<ul>
		<li>보낸사람 : ${messageVO.id}</li>
		<li>쪽지 작성일 : ${messageVO.reg_date}</li>
	</ul>

	<br>
	<h4>쪽지 내용</h4>
	<hr size="1" width="100%" noshade="noshade">
	<p>
		${messageVO.content}
	</p>
	<hr size="1" width="100%" noshade="noshade">
		<c:if test="${!empty replyList}">
		<br>
		<h2>답장</h2>
		<hr size="1" width="100%" noshade="noshade">
		<c:forEach var="reply" items="${replyList}">
		<ul>
			<li>${reply.content}</li>
			<li>보낸 날짜 : ${reply.reg_date}</li>
		</ul>
		<hr size="1" width="100%" noshade="noshade">
		</c:forEach>
		</c:if>
	<c:if test="${messageVO.mem_num != user.mem_num}">
	<h2>답장 쓰기</h2>
	<form:form commandName="messageVO" action="write.do">
	    <input type="hidden" name="target_mem_num" value="${messageVO.mem_num}"/>
		<input type="hidden" name="anonymous" value="0"/>
		<input type="hidden" name="parent_msg_num" value="${messageVO.msg_num}"/>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="content">답장 내용</label>
				<textarea rows="4" cols="30" id="content" name="content"></textarea>
				<form:errors path="content" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="쪽지목록" onclick="location.href='messageList.do'">
		</div>
	</form:form>
	</c:if>
	<c:if test="${messageVO.mem_num == user.mem_num}">
		<input type="button" value="쪽지목록" onclick="location.href='messageList.do'">
	</c:if>
</div>