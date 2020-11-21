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
<div>
	<h2>로그인</h2>
	<form:form action="memberLogin.do" commandName="memberVO">
		<%-- 필드가 없는 에러 표시 --%>
		<form:errors element="div" cssClass="error-color"/>
		<ul>
			<li>
				<label for="id">아이디</label>
				<form:input path="id"/>
				<form:errors path="id" cssClass="error-color"/> <%-- 아이디 유효성체크(입력여부) --%>
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<form:password path="passwd"/>
				<form:errors path="passwd" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="로그인">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/introduce.do'">
		</div>
	</form:form>
</div>
</body>
</html>