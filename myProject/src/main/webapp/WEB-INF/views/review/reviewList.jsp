<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl의 core 라이브러리 사용 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- jstl의 function 라이브러리 사용(length 등) -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/review.container.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

</script>
<div id="container" class="lectureindex">
	<form class="search">
		<input type="search" name="keyword" placeholder="과목명, 교수명으로 검색" class="keyword" autocomplete="off">
		<input type="submit" class="submit">
	</form>
	<div class="section">
		<h2>내 강의평</h2>

		<c:if test="${!empty subjectList && fn:length(subjectList) > 0}">
		<div class="mylectures">
			<c:forEach var="subject" items="${subjectList}">
				<a class="lecture" href="${pageContext.request.contextPath}/review/reviewDetail.do?sub_num=${subject.sub_num}">
					<h3>
						<span class="name">${subject.sub_name}</span>
						<span class="professor">${subject.prof_name}</span>
					</h3>
					<span class="button write">평가하기</span>
				</a>
			</c:forEach>
		</div>
		</c:if>
		<c:if test="${empty subjectList}">
		<div class="empty">
			<p>아직 확인할 수 있는 과목이 없습니다.</p>
		</div>
		</c:if>
	</div>
	<div class="section">
		<h2>최근 강의평</h2>
		<div class="reviews">
			<c:if test="${!empty reviewList && fn:length(reviewList) > 0}">
			<c:forEach var="review" items="${reviewList}">
				<a class="review" href="${pageContext.request.contextPath}/review/reviewDetail.do?sub_num=${review.sub_num}">
					<h3>${review.sub_name} : ${review.prof_name}</h3>
					<p class="rate">
						<span class="star">
							<span class="on" style="width: ${review.rate / 5 * 100}%"></span>
						</span>
					</p>
					<p class="text">${review.content}</p>
				</a>
			</c:forEach>
			</c:if>
			<c:if test="${empty reviewList}">
			<div class="empty">
				<p>등록된 강의평이 없습니다.</p>
			</div>
			</c:if>
		</div>
	</div>
</div>