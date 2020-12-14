<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!-- 쪽지함 스타일 css--------------------------- -->
<style>

 #title{
  line-height:10;
 text-align:center;
 }
 
 #title2{
 text-align:center;
 font-size:12px;
 margin:0px;
 padding:0;
 
 }
 
 #title3{
 text-align:left;
 height:20px;
 }
 
 .content{
 line-height:20;
 }
 
 .list{

 font-size:15px;
 text-align: right;
 }
 
 .date{
 line-height:5;
 }
 
 .content{
 line-height:5;
 }
 
 
</style>
<!-- -------------------------------------------------- -->


<div class="page-main-style">
	<h3 id="title">쪽지함</h3>
	<br>
	<div id="title2">
	<h3>쪽지 내용</h3>
	</div>
	<hr size="1" width="100%" noshade="noshade">
	<ul class="list">
		<li>보낸사람 : ${messageVO.id}</li>
		<li>쪽지 작성일 : ${messageVO.reg_date}</li>
	</ul>
	<p class="content">
		${messageVO.content}
	</p>
		<c:if test="${!empty replyList}">
		<br>
		<div id="title3">
		<h3 style="margin-top:70px;">답장</h3>
		</div>
		<hr size="1" width="100%" noshade="noshade">
		<c:forEach var="reply" items="${replyList}">
		<ul>
			<li id="content">${reply.content}</li>
			<li id="date" style="float:right; position: relative; bottom: 27px">보낸 날짜 : ${reply.reg_date}</li>
		</ul>
		<hr size="1" width="100%" noshade="noshade">
		</c:forEach>
		</c:if>
	<c:if test="${messageVO.mem_num != user.mem_num}">
	<div class="reply">
	<h2 style="margin-top: 80px;">답장 쓰기</h2>
	</div>
	<form:form commandName="messageVO" action="write.do">
	    <input type="hidden" name="target_mem_num" value="${messageVO.mem_num}"/>
		<input type="hidden" name="anonymous" value="0"/>
		<input type="hidden" name="parent_msg_num" value="${messageVO.msg_num}"/>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="content" style="line-height:5; font-size:15px; font-weight: 800;">답장 내용</label>
				<textarea rows="4" cols="30" id="content" name="content" style="border: 1px solid gray; height:150px;"></textarea>
				<form:errors path="content" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송" style="margin-top:50px;">
			<input type="button" value="쪽지목록" onclick="location.href='messageList.do'">
			<input type="button" value="삭제" onclick="location.href='messageList.do'" id="delete_btn">
		</div>
	</form:form>
	</c:if>
	<c:if test="${messageVO.mem_num == user.mem_num}">
	    <input type="button" value="삭제" onclick="location.href='messageList.do'" id="delete_btn" style="float:right;">
		<input type="button" value="쪽지목록" onclick="location.href='messageList.do'" style="float:right;">
	</c:if>
	<script type="text/javascript">
		var delete_btn = document.getElementById('delete_btn');
		//이벤트 연결
		delete_btn.onclick=function() {
			var choice = window.confirm('정말 삭제하시겠습니까?');
			if (choice) {
				location.href='delete.do?msg_num=${messageVO.msg_num}';
			}
		}
	</script>
</div>