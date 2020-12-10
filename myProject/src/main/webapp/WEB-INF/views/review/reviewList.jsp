<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl의 core 라이브러리 사용 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <!-- jstl의 function 라이브러리 사용(length 등) -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/review.container.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	//검색 영역 제외한 곳 클릭시
	$('html').click(function(e) { 
		if(!$(e.target).hasClass("lectures")) { 
			$('.lectures').remove();
		} 
	});

	//검색 버튼 클릭시
	$(document).on('submit','#searchForm',function(event){
		var check = true;
		//검색어가 2글자 미만인 경우
		if($('#searchForm input[name=keyword]').val().length < 2){
			alert('검색어는 2글자 이상 입력해주세요!');
			$('#searchForm input[name=keyword]').focus();
			check = false;
		}
		
		if (!check) { return false; }
		
		//강의 검색(과목명, 교수명으로 검색)
		$.ajax({
			url: '${pageContext.request.contextPath}/review/search.do',
			type: 'post',
			data: $('#searchForm').serialize(),
			dataType: 'json',
			cache: false,
			timeout: 30000,
			success: function(data){
				//검색 결과 나타내기
				if(data.result == 'success') {
					var output = '<div class="lectures">';
					$(data.list).each(function(index, item){
						output += '<a class="lecture" href="${pageContext.request.contextPath}/review/reviewDetail.do?sub_num=' + item.sub_num + '">';
						output += 	'<h3>';
						output += 		'<p class="name">';
						output += 			 item.sub_name;
						output += 		'</p>';
						output += 		'<p class="professor">' + item.prof_name + '</p>';
						output += 	'</h3>';
						output += 	'<p class="rate">';
						output += 		'<span class="star"><span class="on" style="width: ' + (item.totalRate / 5 * 100) + '%;"></span></span>';
						output += 	'</p>';
						output +='</a>';
					});
					output += '</div>';
					
					$('#searchForm').append(output);
					//클래스 추가시 딜레이 주기
					setTimeout(function(){
				       $(".lectures").addClass("act");
				   	}, 10);
					
				}else if(data.result == 'empty'){	//검색된 데이터가 존재하지 않는경우
					alert('검색 결과가 존재하지 않습니다.');
				}else{
					alert('검색 오류 발생!');
				}
			},
			error: function(request,status,error){
				alert(">>code:"+request.status+"\n\n"+">>message:"+request.responseText+"\n\n"+">>error:"+error);
			}
		});
		
		event.preventDefault();	//기본 이벤트 삭제
		event.stopPropagation();
	});
	
	
	
});
</script>
<div id="container" class="lectureindex">
	<form id="searchForm" class="search">
		<input type="search" name="keyword" placeholder="과목명, 교수명으로 검색" class="keyword" autocomplete="off">
		<input id="searchBtn" type="submit" class="submit">
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