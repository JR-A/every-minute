<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="page-main-style">
	<h2>쪽지쓰기</h2>
	<form:form commandName="messageVO" action="write.do">
		<input type="hidden" name="anonymous" value="0"/>
		<input type="hidden" name="parent_msg_num" value="0"/>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
		    <li>
				<label for="target_id">받는 사람</label>
				<form:input path="target_id"/>
				<form:errors path="target_id" cssClass="error-color"/>
			</li>
			<li style="margin-top: 50px; margin-bottom: 50px;">
				<label for="content">보낼 쪽지 입력</label>
				<form:textarea path="content"  style="border:1px solid gray; height: 150px;"/>
				<form:errors path="content" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록" onclick="location.href='messageList.do'">
		</div>
	</form:form>
</div> 