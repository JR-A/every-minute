<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//submit 이벤트 발생시 아이디 중복 체크 여부 확인
	$('#form_submit').submit(function(){
		if($('#target_id').val()==''){
			alert('아이디를 입력해주세요');
			$('#target_id').focus();
			return false;
		}
		if($('#content').val()==''){
			alert('쪽지 내용을 입력해주세요');
			$('#content').focus();
			return false;
		}
	});	


});
</script>
<div class="page-main-style">
	<h2>쪽지쓰기</h2>
	<form:form commandName="messageVO" action="write.do" id="form_submit">
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
				<label for="content">쪽지 입력</label>
				<form:textarea path="content" style="border:1px solid gray; height: 150px;"/>
				<form:errors path="content" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송 " id="submit">
			<input type="button" value="목록" onclick="location.href='messageList.do'">
		</div>
	</form:form>
</div> 