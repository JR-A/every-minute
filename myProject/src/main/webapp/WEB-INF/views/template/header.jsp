<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrap">
      <div id="logo">
        <a href="/"><img src="https://everytime.kr/images/new/nav.logo.png"></a>
        <p><span class="name multiple">에브리미닛</span><span class="subname">대학교 정보</span></p>
      </div>
      <div id="account">
        <a href="/message" title="쪽지함" class="icon message">쪽지함</a>
        <a href="/my" title="내 정보" class="icon my">내 정보</a>
        <a href="/logout" title="로그아웃" class="icon my">로그아웃</a>
        <input type="hidden" id="userUserid" value="kidhsa">
        <input type="hidden" id="userSchool" value="22">
        <input type="hidden" id="userCampus" value="38">
      </div>
      <ul id="menu">
        <li><a href="#">게시판</a></li>
        <li><a href="${pageContext.request.contextPath}/timetable/timetableView.do">시간표</a></li>
        <li><a href="#">강의평가</a></li>
        <li><a href="#">학점계산기</a></li>
        <li><a href="#">책방</a></li>
      </ul>
    </div>
<div class="align-right">
	<c:if test="${!empty user && !empty user.photoname}">
	<img src="${pageContext.request.contextPath}/member/photoView.do" width="25" height="25" class="my-photo">
	</c:if>
	<c:if test="${!empty user && empty user.photoname}">
	<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" width="25" height="25" class="my-photo">
	</c:if>
	<%-- 회원번호 가지고있다고 가정 --%>
	<c:if test="${!empty user}">
	[<span>${user.id}</span>]
	<a href="${pageContext.request.contextPath}/member/messageBox.do">쪽지함</a>
	<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
	<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
	</c:if>
	<%--
	<c:if test="${empty user}">
	<a href="${pageContext.request.contextPath}/member/registerUser.do">회원가입</a>
	<a href="${pageContext.request.contextPath}/member/login.do">로그인</a>	
	</c:if>
	<a href="${pageContext.request.contextPath}/main/main.do">홈으로</a>
	--%>
</div>










