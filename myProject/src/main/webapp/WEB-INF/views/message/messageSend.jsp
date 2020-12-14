<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//submit 이벤트 발생시 아이디 중복 체크 여부 확인
	$('#form_submit').submit(function(){
		if($('#content').val()==''){
			alert('답장 내용을 입력해주세요');
			$('#content').focus();
			return false;
		}
	});	


});
</script>
<div class="page-main-style">
	<h2>쪽지쓰기</h2>
	<form:form commandName="messageVO" action="write.do" id="form_submit">
		<input type="hidden" name="target_mem_num" value="${target_mem_num}"/>
		<input type="hidden" name="anonymous" value="${anonymous}"/>
		<input type="hidden" name="parent_msg_num" value="0"/>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="content">쪽지내용</label>
				<form:textarea path="content" style="border:1px solid gray; height: 150px;"/>
				<form:errors path="content" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송" id="submit">
			<input type="button" value="목록" onclick="location.href='messageList.do'">
		</div>
	</form:form>
</div> 