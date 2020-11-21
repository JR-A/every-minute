<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<div class="page-main-style">
	<h2>회원가입</h2>
	<form:form id="register_form" action="memberRegister.do" commandName="memberVO">
		<%-- 필드가 없는 에러 표시 --%>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="id">아이디</label>
				<form:input path="id"/>
				<input type="button" id="confirmId" value="ID중복체크">
				<%-- 로딩gif --%>
				<img id="loading" src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif" width="16" height="16" style="display:none;">
				<span id="message_id"></span> <%-- 아이디 중복체크 메세지 --%>
				<form:errors path="id" cssClass="error-color"/> <%-- 아이디 유효성체크 오류메시지,서버단에서 하는 유효성체크(어노테이션방식,동적으로 메시지 출력됨) --%>
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<form:password path="passwd"/>
				<form:errors path="passwd" cssClass="error-color"/>
			</li>
			<li>
				<label for="email">이메일</label> <%-- 어노테이션 유효성 테스트 위해 email html태그가 아닌 커스텀태그 사용(태그에서 유효성 체크 하지 않고 서버단에서 체크하는것 테스트) --%>
				<form:input path="email"/>
				<input type="button" id="pass_num_send" value="인증번호 발송">
				<form:errors path="email" cssClass="error-color"/>
			</li>
			<li>
				<label for="pass_num">승인번호</label>
				<input type="text" id="pass_num" name="pass_num">
			</li>
			<li>
				<label for="nickname">닉네임</label>
				<form:input path="nickname"/>
				<form:errors path="nickname"/>
			</li>
			<li>
				<label for="major">학과</label>
				<form:input path="major"/>
				<form:errors path="major"/>
			</li>
			<li>
				<label for="stu_num">학번</label>
				<form:input path="stu_num"/>
				<form:errors path="stu_num"/>
			</li>	
			<li>
				<label for="zipcode">우편번호</label>
				<form:input path="zipcode"/>
				<form:errors path="zipcode"/>
			</li>
			<li>
				<label for="address1">주소</label>
				<form:input path="address1"/>
				<form:errors path="address1"/>
			</li>
			<li>
				<label for="address2">나머지 주소</label>
				<form:input path="address2"/>
				<form:errors path="address2"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="홈으로 " onclick="location.href='${pageContext.request.contextPath}/main/main_board.do'">
		</div>
	</form:form>
</div>
</body>
</html>