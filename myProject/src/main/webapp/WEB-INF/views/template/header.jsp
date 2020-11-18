<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<h2 class="align-center">회원제 게시판</h2>
<div class="align-right">
	<c:if test="${!empty user && !empty user.photoname}">
	<img src="${pageContext.request.contextPath}/member/photoView.do" width="25" height="25" class="my-photo">
	</c:if>
	<c:if test="${!empty user && empty user.photoname}">
	<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" width="25" height="25" class="my-photo">
	</c:if>
	<c:if test="${!empty user}">
	[<span>${user.id}</span>]
	<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
	<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
	</c:if>
	<c:if test="${empty user}">
	<a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
	<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>	
	</c:if>
	<a href="${pageContext.request.contextPath}/main/main.do">홈으로</a>
</div>










