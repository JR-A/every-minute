<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl의 core 라이브러리 사용 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- jstl의 function 라이브러리 사용(length 등) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/reviewDetail.container.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//새 강의평 쓰기 버튼 클릭시 폼 노출
	$(document).on('click','a.writebutton',function(){
		$('#writeReview').css("display", "block");
	});
	
	//새 강의평쓰기 폼의 닫기버튼 클릭시
	$(document).on('click','a.close',function(){
		//폼 초기화
		$('#writeReview input[name=sub_num]').val(0);
		$('#writeReview input[name=homework]').val(1);
		$('#writeReview input[name=team]').val(1);
		$('#writeReview input[name=grade]').val(1);
		$('#writeReview input[name=attendance]').val(1);
		$('#writeReview input[name=exam]').val(2);
		$('#writeReview input[name=rate]').val(3);
		$('#writeReview textarea[name=content]').val('');
		
		$('.wrap dl dd a').siblings().removeClass('active');
		$('#writeReview a[data-name="assessment_homework"]:nth-child(2)').addClass('active');
		$('#writeReview a[data-name="assessment_team"]:nth-child(2)').addClass('active');
		$('#writeReview a[data-name="assessment_grade"]:nth-child(2)').addClass('active');
		$('#writeReview a[data-name="assessment_attendance"]:nth-child(2)').addClass('active');
		$('#writeReview a[data-name="exam_times"]:nth-child(3)').addClass('active');
		$('#writeReview a[data-name="rate"]:nth-child(3)').addClass('active');
		
		$('#writeReview').css("display", "none");
	});
	
	//새 강의평쓰기 폼의 평가문항 클릭시
	$(document).on('click','.wrap dl dd a',function(){
		$(this).siblings().removeClass('active');
		$(this).addClass('active');
	});
	
	//새 강의평쓰기 폼의 전송 버튼 클릭시
	$(document).on('submit','#writeReview',function(event){
		var check = true;
		//강의평 글자수가 20자 미만인 경우
		if($('#writeReview textarea[name=content]').val().length < 20){
			alert('성의 있는 강의평을 남겨주세요!')
			$('#writeReview textarea[name=content]').focus();
			check = false;
		}
		
		if (!check) { return false; }
		
		var ans = confirm('강의평을 등록하시겠습니까?\n\n※등록 후에는 수정하거나 삭제할 수 없습니다.\n\n※허위/중복/저작권침해/성의없는 정보를 작성할 경우, 서비스 이용이 제한될 수 있습니다.');
		
		if(ans){
			//각 항목의 점수를 input데이터에 저장
			$('.wrap dl dd').each(function(index, item){
				$('#writeReview input[data-order="'+index+'"]').val($(this).find('a.active').data('value'));
			});
			
			//새 강의평 추가	
			$.ajax({
				url: '${pageContext.request.contextPath}/review/insertReview.do',
				type: 'post',
				data: $('#writeReview').serialize(),
				dataType: 'json',
				cache: false,
				timeout: 30000,
				success: function(data){
					if(data.result == 'success') {
						alert('강의평이 등록되었습니다!');
						location.reload();
					}else{
						alert('강의평 등록 오류 발생!');
					}
				},
				error: function(request,status,error){
					alert(">>code:"+request.status+"\n\n"+">>message:"+request.responseText+"\n\n"+">>error:"+error);
				}
			});

		}
		
		event.preventDefault();	//기본 이벤트 삭제
		event.stopPropagation();
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
    <form id="writeReview" class="write review" style="display: none;">
    	<input type="hidden" name="sub_num" value="${subject.sub_num}">
    	<input data-order="0" type="hidden" name="homework">
    	<input data-order="1" type="hidden" name="team">
    	<input data-order="2" type="hidden" name="grade">
    	<input data-order="3" type="hidden" name="attendance">
    	<input data-order="4" type="hidden" name="exam">
    	<input data-order="5" type="hidden" name="rate">
    	
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
(등록 후에는 수정 및 삭제가 불가능하므로 신중히 적어주세요. 최소 20자 이상)"></textarea>
        <input type="submit" value="작성하기" class="submit">
      </div>
    </form>
</div>