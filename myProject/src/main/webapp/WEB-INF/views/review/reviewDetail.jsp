<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl의 core 라이브러리 사용 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- jstl의 function 라이브러리 사용(length 등) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reviewDetail.container.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//새 강의평 쓰기 버튼 클릭시 강의평작성 폼 띄움
	$(document).on('click','a.writebutton',function(){
		$('form.write.review').css("display", "block");
	});
});
</script>
<div id="container" class="reviewDetail">
	<div class="side head">
    	<h2>${subject.sub_name}</h2>
    	<p>
    		<label>교수명</label>
    		<span>${subject.prof_name}</span>
    	</p>
    	<p>
    		<label>이수구분</label>
    		<span>${subject.sub_category}</span>
    	</p>
    	<p>
    		<label>학점</label>
    		<span>${subject.sub_credit}</span>
    	</p>
    	<hr>
    </div>
    <div class="side review">
    	<h2>강의평</h2>
    	<a class="writebutton">새 강의평 쓰기</a>
    	
    	<div class="rating">
    		<div class="rate">
    			<span>
    				<span class="value">
    					<%-- 강의 평가 목록이 존재하는경우 --%>
    					<%-- 소수점 둘째자리까지 표시 --%>
    					<c:if test="${!empty reviewList && fn:length(reviewList) > 0}"><fmt:formatNumber value="${avgRate}" pattern=".00"></fmt:formatNumber></c:if>
    					<c:if test="${empty reviewList}">0</c:if>
    				</span>
    				<span class="star">
    					<span class="on" style="width : ${avgRate / 5 * 100 }%"></span>
    				</span>
    			</span>
    		</div>
    		<div class="details">
   				<c:if test="${!empty reviewList && fn:length(reviewList) > 0}">
    			<p>
    				<label>과제</label>
    				<span>${homework}</span>
    			</p>
    			<p>
    				<label>조모임</label>
    				<span>${team}</span>
    			</p>
    			<p>
    				<label>학점 비율</label>
    				<span>${grade}</span>
    			</p>
    			<p>
    				<label>출결 비율</label>
    				<span>${attendance}</span>
    			</p>
    			<p>
    				<label>시험</label>
    				<span>${exam}</span>
    			</p>
    			</c:if>
    		</div>
    	</div>
    	<c:if test="${empty reviewList}">
    	<div class="empty">
    		<p>아직 등록된 강의평이 없습니다.</p>	
    	</div>
    	</c:if>
    	<c:if test="${!empty reviewList && fn:length(reviewList) > 0}">
    	<div class="reviews">
    		<c:forEach var="review" items="${reviewList}">
    		<article>
    			<p class="rate">
    				<span class="star">
    					<span class="on" style="width: ${review.rate / 5 * 100}%"></span>
    				</span>
    			</p>
    			<p class="text">${review.content}</p>
    		</article>
    		</c:forEach>
    	</div>
    	</c:if>
    </div>
    <form class="write review" style="display: none;">
    	<input type="hidden" name="sub_num" value="${subject.sub_num}">
    	<input type="hidden" name="homework">
    	<input type="hidden" name="team">
    	<input type="hidden" name="grade">
    	<input type="hidden" name="attendance">
    	<input type="hidden" name="exam">
    	
    	<input type="hidden" name="rate">
    	
      <div class="wrap">
        <h2>새 강의평 쓰기</h2>
        <a class="close">닫기</a>
        <hr>
        <h3>성적 반영</h3>
        <dl>
          <dt>과제</dt>
          <dd>
            <a data-name="assessment_homework" data-value="0">많음</a>
            <a data-name="assessment_homework" data-value="1" class="active">보통</a>
            <a data-name="assessment_homework" data-value="2">없음</a>
          </dd>
          <dt>조모임</dt>
          <dd>
            <a data-name="assessment_team" data-value="0">많음</a>
            <a data-name="assessment_team" data-value="1" class="active">보통</a>
            <a data-name="assessment_team" data-value="2">없음</a>
          </dd>
          <dt>학점 비율</dt>
          <dd>
            <a data-name="assessment_grade" data-value="0">F 폭격기</a>
            <a data-name="assessment_grade" data-value="1" class="active">매우깐깐함</a>
            <a data-name="assessment_grade" data-value="2">비율채워줌</a>
            <a data-name="assessment_grade" data-value="3">학점느님</a>
          </dd>
          <dt>출결</dt>
          <dd>
            <a data-name="assessment_attendance" data-value="0">매우엄격</a>
            <a data-name="assessment_attendance" data-value="1" class="active">항상체크</a>
            <a data-name="assessment_attendance" data-value="2">가끔체크</a>
            <a data-name="assessment_attendance" data-value="3">반영안함</a>
          </dd>
          <dt>시험 횟수</dt>
          <dd>
            <a data-name="exam_times" data-value="0">네번이상</a>
            <a data-name="exam_times" data-value="1">세번</a>
            <a data-name="exam_times" data-value="2" class="active">두번</a>
            <a data-name="exam_times" data-value="3">한번</a>
            <a data-name="exam_times" data-value="4">없음</a>
          </dd>
        </dl>
        <hr>
        <h3>총평</h3>
        <dl>
          <dt>총점</dt>
          <dd>
            <a data-name="rate" data-value="1">1점</a>
            <a data-name="rate" data-value="2">2점</a>
            <a data-name="rate" data-value="3" class="active">3점</a>
            <a data-name="rate" data-value="4">4점</a>
            <a data-name="rate" data-value="5">5점</a>
          </dd>
        </dl>
        <hr>
        <textarea name="content" class="text" placeholder="이 강의에 대한 총평을 작성해주세요.
(등록 후에는 수정 및 삭제가 불가능하므로 신중히 적어주세요.)"></textarea>
        <input type="submit" value="작성하기" class="submit">
      </div>
    </form>
</div>