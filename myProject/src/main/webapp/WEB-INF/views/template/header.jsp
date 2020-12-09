<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrap">
      <div id="logo">
        <a href="${pageContext.request.contextPath}/main/main_board.do"><img src="https://everytime.kr/images/new/nav.logo.png"></a>
        <p><span class="name multiple">에브리미닛</span><span class="subname">Ezen대학교</span></p>
      </div>
      <div id="account">
      	<c:if test="${!empty user && !empty user.photoname}">
			<img src="${pageContext.request.contextPath}/member/photoView.do" width="25" height="25" class="my-photo">
		</c:if>
		<c:if test="${!empty user && empty user.photoname}">
			<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" width="25" height="25" class="my-photo">
		</c:if>
		<c:if test="${!empty user}">
	        <a href="${pageContext.request.contextPath}/message/messageList.do" title="쪽지함" class="icon message">쪽지함</a>
	        <a href="${pageContext.request.contextPath}/member/myPage.do" title="내 정보" class="icon my">내 정보</a>
	        <a href="${pageContext.request.contextPath}/member/logout.do" title="로그아웃" class="icon my">로그아웃</a>
      	</c:if>
      </div>
      <ul id="menu">
        <li><a href="${pageContext.request.contextPath}/main/main_board.do">게시판</a></li>
        <li><a href="${pageContext.request.contextPath}/timetable/timetableView.do">시간표</a></li>
        <li><a href="${pageContext.request.contextPath}/lecture/lectureView.do">강의평가</a></li>
        <li><a href="${pageContext.request.contextPath}/calculator/calculatorView.do">학점계산기</a></li>
        <li><a href="${pageContext.request.contextPath}/bookStore/bookStoreList.do">책방</a></li>
      </ul>
    </div>