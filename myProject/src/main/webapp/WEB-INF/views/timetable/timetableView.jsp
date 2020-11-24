<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl의 core 라이브러리 사용 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/timetable.container.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		//콤보박스에서 학기 선택시 이벤트 연결
		$('#semesters').change(function(){
			$.ajax({
				url: '${pageContext.request.contextPath}/timetable/getListBySemester.do',
				type: 'post',
				data: {semester: $("#semesters option:selected").val()},
				dataType: 'json',
				cache: false,
				timeout: 30000,
				success: function(data){
					$('#timetableList').empty();
					$(data).each(function(index,item){
						var output = '';
						output += '<li><a href="${pageContext.request.contextPath}/timetable/createTimetable.do?t_num=' + item.t_num + '"';	
						if(item.isPrimary == 1){
							output += ' class="primary"';
						}
						output += '>' + item.t_name + '</a></li>';	
						$('#timetableList').append(output);
					});
					$('#timetableList').append('<li class="extension"><a id="create">새 시간표 만들기</a></li>');
				},
				error: function(request,status,error){
					alert(">>code:"+request.status+"\n\n"+">>message:"+request.responseText+"\n\n"+">>error:"+error);
				}
			});
		});
		
		
		//ajax로 만들어질 미래의 버튼이므로 on으로 연결
		//새 시간표 만들기 버튼 클릭이벤트 연결
		$(document).on('click','#create',function(){	
			//서버와 ajax 비동기 통신
			$.ajax({
				url: '${pageContext.request.contextPath}/timetable/createTimetable.do',
				type: 'post',
				data: {semester: $("#semesters option:selected").val()},
				dataType: 'json',
				cache: false,
				timetout: 30000,
				success: function(data){
					alert(data.semester + "학기 시간표 추가 성공!");
					$('#semesters').change();		//콤보박스 change이벤트 발생시키기 -> 현재 선택된 학기로 시간표 불러오기
				},
				error: function(request,status,error){
					alert(">>code:"+request.status+"\n\n"+">>message:"+request.responseText+"\n\n"+">>error:"+error);
				}
			});
		});
	});
</script>
<script>
    var timetableGridInfo = [
    	{"no":"1","rows":[108,120],"hidden":false},
    	{"no":"2","rows":[120,132],"hidden":false},
    	{"no":"3","rows":[132,144],"hidden":false},
    	{"no":"4","rows":[144,156],"hidden":false},
    	{"no":"5","rows":[156,168],"hidden":false},
    	{"no":"6","rows":[168,180],"hidden":false},
    	{"no":"7","rows":[180,192],"hidden":false},
    	{"no":"8","rows":[192,204],"hidden":false},
    	{"no":"9","rows":[204,216],"hidden":false},
    	{"no":"10","rows":[216,228],"hidden":false},
    	{"no":"11","rows":[228,240],"hidden":false},
    	{"no":"12","rows":[240,252],"hidden":false}]
</script>
<div id="container" class="timetable" style="height: 800px;">
	<hr>
	<aside>
		<form class="select">
			<select name="semesters" id="semesters">
				<option value="2020-2">2020년 2학기</option>
				<option value="2020-1">2020년 1학기</option>
				<option value="2019-2">2019년 2학기</option>
				<option value="2019-1">2019년 1학기</option>
				<option value="2018-2">2018년 2학기</option>
				<option value="2018-1">2018년 1학기</option>
				<option value="2017-2">2017년 2학기</option>
				<option value="2017-1">2017년 1학기</option>
				<option value="2016-2">2016년 2학기</option>
				<option value="2016-1">2016년 1학기</option>
				<option value="2015-2">2015년 2학기</option>
				<option value="2015-1">2015년 1학기</option>
			</select>
		</form>
		<div class="title">
			<a class="haburger"></a>
			<h1 id="tableName">시간표</h1>
			<div class="description">
				<ul class="info">
					<li style="display:none;">
						<span id="tableCredit">0</span>
						" 학점"
					</li>
					<li>
						<time id="tableUpdateAt">19/12/23 00:40</time>
						" 변경"
					</li>
				</ul>
			</div>
		</div>
		<div class="menu">
			<ol id="timetableList">
				<%-- 시간표가 존재하면 목록 불러오기 --%>
				<c:if test="${timetableCount > 0}">
				<c:forEach var="timetable" items="${timetableList}">
				<li>
					<a href="${pageContext.request.contextPath}/timetable/createTimetable.do?t_num=${timetable.t_num}" 
					<c:if test="${timetable.isPrimary == 1}">class="primary"</c:if>>${timetable.t_name}</a>
				</li>
				</c:forEach>
				</c:if>
				<li class="extension">
					<a id="create">새 시간표 만들기</a>
				</li>
			</ol>
		</div>
	</aside>
	<div class="wrap">
		<div class="tablehead">
			<table class="tablehead">
				<tbody>
					<tr>
						<th></th>
						<td>월</td>
						<td>화</td>
						<td>수</td>
						<td>목</td>
						<td>금</td>
						<th></th>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="tablebody">
			<table class="tablebody">
				<tbody>
					<tr>
						<th>
							<div class="hours">		
								<c:forEach var="i" begin="0" end="11">
								<div class="hour" style="height: 60px; top: ${60*i}px; line-height: 60px;">${i+1}교시</div>
								</c:forEach>
							</div>
						</th>
						<td>
							<div class="cols" style="width:113px;">
							
							</div>
							<div class="grids">
								<c:forEach var="i" begin="0" end="11">
									<div class="grid"></div>
								</c:forEach>
							</div>
						</td>
						<td>
							<div class="cols" style="width:113px;">
							
							</div>
							<div class="grids">
								<c:forEach var="i" begin="0" end="11">
									<div class="grid"></div>
								</c:forEach>
							</div>
						</td>
						<td>
							<div class="cols" style="width:113px;">
							
							</div>
							<div class="grids">
								<c:forEach var="i" begin="0" end="11">
									<div class="grid"></div>
								</c:forEach>
							</div>
						</td>
						<td>
							<div class="cols" style="width:113px;">
							
							</div>
							<div class="grids">
								<c:forEach var="i" begin="0" end="11">
									<div class="grid"></div>
								</c:forEach>
							</div>
						</td>
						<td>
							<div class="cols" style="width:113px;">
							
							</div>
							<div class="grids">
								<c:forEach var="i" begin="0" end="11">
									<div class="grid"></div>
								</c:forEach>
							</div>
						</td>
						<td style="display:none;">
							<div class="cols" style="width:113px;">
							
							</div>
							<div class="grids">
								<c:forEach var="i" begin="0" end="11">
									<div class="grid"></div>
								</c:forEach>
							</div>
						</td>
						<td style="display:none;">
							<div class="cols" style="width:113px;">
							
							</div>
							<div class="grids">
								<c:forEach var="i" begin="0" end="11">
									<div class="grid"></div>
								</c:forEach>
							</div>
						</td>
						<th>
							<div class="times">
								<div class="time">오전 9시</div>
								<div class="time">오전 10시</div>
								<div class="time">오전 11시</div>
								<div class="time">오후 12시</div>
								<div class="time">오후 1시</div>
								<div class="time">오후 2시</div>
								<div class="time">오후 3시</div>
								<div class="time">오후 4시</div>
								<div class="time">오후 5시</div>
								<div class="time">오후 6시</div>
								<div class="time">오후 7시</div>
								<div class="time">오후 8시</div>
							</div>
						</th>
					</tr>
				</tbody>
			</table>
			<div class="nontimes">
			
			</div>
		</div>
	</div>
	<ul class="floating">
		<li class="button search">수업 목록에서 검색</li>
		<li class="button custom">직접 추가</li>
	</ul>
	<form id="customsubjects" style="display:none; left:539.5px">
		<input type="hidden" name="id">
		<a title="닫기" class="close"></a>
		<h2>새 수업 추가</h2>
		<dl>
			<dt>과목명 (필수)</dt>
			<dd>
				<input type="text" name="name" placeholer="예) 경제학입문" maxlength="32" class="text">
			</dd>
			<dt>교수명</dt>
			<dd>
				<input type="text" name="prof_name" placeholder="예) 홍길동" maxlength="15" class="text">
			</dd>
			<dt>시간/장소</dt>
			<dd class="timeplaces">
        		<div class="timeplace">
        			<ol class="weeks">
        				<li class="active">월</li>
        				<li>화</li>
        				<li>수</li>
        				<li>목</li>
        				<li>금</li>
        			</ol>
        			<p>
						<select class="starthour"><option value="9" selected="selected">오전 9시</option><option value="10">오전 10시</option><option value="11">오전 11시</option><option value="12">오후 12시</option><option value="13">오후 1시</option><option value="14">오후 2시</option><option value="15">오후 3시</option><option value="16">오후 4시</option><option value="17">오후 5시</option><option value="18">오후 6시</option><option value="19">오후 7시</option><option value="20">오후 8시</option><option value="21">오후 9시</option><option value="22">오후 10시</option><option value="23">오후 11시</option></select>        				
        				<select class="startminute"><option value="0">0분</option><option value="5">5분</option><option value="10">10분</option><option value="15">15분</option><option value="20">20분</option><option value="25">25분</option><option value="30">30분</option><option value="35">35분</option><option value="40">40분</option><option value="45">45분</option><option value="50">50분</option><option value="55">55분</option></select>
        				<span>~</span>
        				<select class="endhour"><option value="9">오전 9시</option><option value="10" selected="selected">오전 10시</option><option value="11">오전 11시</option><option value="12">오후 12시</option><option value="13">오후 1시</option><option value="14">오후 2시</option><option value="15">오후 3시</option><option value="16">오후 4시</option><option value="17">오후 5시</option><option value="18">오후 6시</option><option value="19">오후 7시</option><option value="20">오후 8시</option><option value="21">오후 9시</option><option value="22">오후 10시</option><option value="23">오후 11시</option></select>
        				<select class="endminute"><option value="0">0분</option><option value="5">5분</option><option value="10">10분</option><option value="15">15분</option><option value="20">20분</option><option value="25">25분</option><option value="30">30분</option><option value="35">35분</option><option value="40">40분</option><option value="45">45분</option><option value="50">50분</option><option value="55">55분</option></select>
        				<input type="text" placeholder="예) 종303" class="text place">
        			</p>
        		</div>
        		<a class="new"><strong>+</strong> 더 입력</a>
     		</dd>
		</dl>
		<div class="clearBothOnly"></div>
		<div class="submit"><input type="submit" value="저장" class="button"></div>
		<div class="subjects" style="display:none;">
			<div class="list">
				<div class="thead"></div>
				<table>
					<thead>
						<tr>
							<th>과목번호<div>과목번호</div></th>
							<th>이수구분<div>이수구분</div></th>
							<th>과목명<div>과목명</div></th>
							<th>교수명<div>교수명</div></th>
							<th>학점<div>학점</div></th>
							<th>시간<div>시간</div></th>
							<th>강의실<div>강의실</div></th>
							<th>정원<div>정원</div></th>
							<th>사이버강의<div>사이버강의</div></th>
							<th>비고<div>비고</div></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>0</td>
							<td>교선</td>
							<td class="bold">한자의원리</td>
							<td>황수연</td>
							<td>3</td>
							<td>월78,금2</td>
							<td>C719,C719</td>
							<td>90</td>
							<td>0</td>
							<td class="small"></td>
						</tr>
					</tbody>
					<tfoot><tr><td colspan="14"></td></tr></tfoot>
				</table>
			</div>
		</div>
	</form>
</div>