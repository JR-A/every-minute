<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl의 core 라이브러리 사용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- 시간 포맷 등 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/timetable.container.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

	$(document).ready(function(){

		//콤보박스에서 학기 선택시 이벤트 연결
		$('#semester').change(function(){
			$('#semesterSelectForm').submit();
		});
		
		//ajax로 만들어질 미래의 버튼이므로 on으로 연결
		//새 시간표 만들기 버튼 클릭이벤트 연결
		$(document).on('click','#create',function(){	
			//서버와 ajax 비동기 통신
			$.ajax({
				url: '${pageContext.request.contextPath}/timetable/createTimetable.do',
				type: 'post',
				data: {semester: $("#semester option:selected").val()},
				dataType: 'json',
				cache: false,
				timeout: 30000,
				success: function(data){
					$('#semester').change();	//콤보박스 change이벤트 발생시키기 -> 현재 선택된 학기의 시간표목록 불러오기
				},
				error: function(request,status,error){
					alert(">>code:"+request.status+"\n\n"+">>message:"+request.responseText+"\n\n"+">>error:"+error);
				}
			});
		});
		
		//수업 목록에서 검색 버튼 클릭시
		$('#buttonSearch').click(function(){
			$.ajax({
				url: '${pageContext.request.contextPath}/timetable/loadSubjects.do',
				type: 'post',
				dataType: 'json',
				cache: false,
				timeout: 30000,
				success: function(data){
					var height = $('#container').height() - $('#subjects').height() + $('#main_footer').height();
					$('#subjects').css("display", "block");
					$('#container').css("height", height);
					
					$(data).each(function(index,item){
						var output = '';
						output += '<tr id='+ item.sub_num +' class="subjectTr">';
						output +=	'<td class="subjectTd">'+ item.sub_num +'</td>';
						output +=	'<td class="subjectTd">'+ item.sub_category +'</td>';
						output +=   '<td class="subjectTd">'+ item.sub_name +'</td>';
						output += 	'<td class="subjectTd">'+ item.prof_name +'</td>';
						output += 	'<td class="subjectTd">'+ item.sub_credit +'</td>';
						output +=	'<td class="subjectTd">'+ item.sub_time +'</td>';
						output +=	'<td class="subjectTd">'+ item.sub_classRoom +'</td>';
						output +=	'<td class="subjectTd">'+ item.sub_capacity +'</td>';
						if(item.sub_online==1){
							output +=	'<td class="subjectTd">O</td>';
						}else{
							output +=	'<td class="subjectTd">X</td>';
						}
						output +=	'<td class="subjectTd">'+ item.sub_remark + '</td>';
						output += '</tr>';
						
						$('#subjectList').append(output);
					});
				},
				error: function(request,status,error){
					alert(">>code:"+request.status+"\n\n"+">>message:"+request.responseText+"\n\n"+">>error:"+error);
				}
			});
		});	
		//수업목록의 닫기버튼 클릭시
		$(document).on('click','a.close',function(){
			var height = $('#container').height() + $('#subjects').height() - $('#main_footer').height();
			$('#subjects').css("display", "none");
			$('#container').css("height", height);
		});
		
		
		//수업 목록에서 마우스오버시 이벤트 연결
		//이미 수업이 추가된 시간표에는 정상작동하지만, 과목 추가되지 않은 시간표에서는 동작하지 않음
		$(document).on('mouseover','#subjectList>tr',function(){
			//미리보기
			$.ajax({
				url: '${pageContext.request.contextPath}/timetable/previewSubject.do',
				type: 'post',
				data: {sub_num: $(this).attr('id')},
				dataType: 'json',
				cache: false,
				timeout: 30000,
				success: function(data){
					for(var i=0; i<5; i++){
						$(data).each(function(index,item){
							if(item.day == i){
								var output = '';
								output += '<div class="preview subject color0" style="height:'+ ((item.endtime - item.starttime + 1)*60 + 1) +'px;';
								output += ' top:'+ (item.starttime-1)*60 +'px;">';
								output += 	'<ul class="status" style="display: none;">';
								output += 		'<li title="삭제" class="del"></li>';
								output += 	'</ul>';
								output += 	'<h3>' + item.sub_name +'</h3><p><em>' + item.prof_name + '</em><span>' + item.classRoom +'</span></p>';
								output += '</div>';
								$('#day'+i+' .cols').append(output);
							}
						});
					}
				},
				error: function(request,status,error){
					alert(">>code:"+request.status+"\n\n"+">>message:"+request.responseText+"\n\n"+">>error:"+error);
				}
			});
			
		});
		//수업목록에서 마우스아웃시 이벤트 연결
		$(document).on('mouseout','#subjectList>tr',function(){
			$('.preview').remove();
		});
		
	});
</script>

<div id="container" class="timetable" style="height: 800px;">
	<hr>
	<aside>
		<form id="semesterSelectForm" class="select" action="${pageContext.request.contextPath}/timetable/timetableView.do">
			<select name="semester" id="semester">
				<option value="2020-2" <c:if test="${semester=='2020-2'}">selected</c:if>>2020년 2학기</option>
				<option value="2020-1" <c:if test="${semester=='2020-1'}">selected</c:if>>2020년 1학기</option>
				<option value="2019-2" <c:if test="${semester=='2019-2'}">selected</c:if>>2019년 2학기</option>
				<option value="2019-1" <c:if test="${semester=='2019-1'}">selected</c:if>>2019년 1학기</option>
				<option value="2018-2" <c:if test="${semester=='2018-2'}">selected</c:if>>2018년 2학기</option>
				<option value="2018-1" <c:if test="${semester=='2018-1'}">selected</c:if>>2018년 1학기</option>
				<option value="2017-2" <c:if test="${semester=='2017-2'}">selected</c:if>>2017년 2학기</option>
				<option value="2017-1" <c:if test="${semester=='2017-1'}">selected</c:if>>2017년 1학기</option>
				<option value="2016-2" <c:if test="${semester=='2016-2'}">selected</c:if>>2016년 2학기</option>
				<option value="2016-1" <c:if test="${semester=='2016-1'}">selected</c:if>>2016년 1학기</option>
				<option value="2015-2" <c:if test="${semester=='2017-2'}">selected</c:if>>2015년 2학기</option>
				<option value="2015-1" <c:if test="${semester=='2017-1'}">selected</c:if>>2015년 1학기</option>
			</select>
		</form>
		<div class="title">
			<a class="haburger"></a>
			<h1 id="tableName">${timetable.t_name}</h1>
			<div class="description">
				<ul class="info">
					<li>
						<span id="tableCredit">${timetableCredit} 학점</span>
					</li>
					<li>
						<time id="tableUpdateAt">
						<c:if test="${timetable.modify_date == null}">방금 변경</c:if>
						<c:if test="${timetable.modify_date != null}">
						<fmt:formatDate value="${timetable.modify_date}" var="formattedDate" type="date" pattern="MM/dd HH:mm"/>${formattedDate} 변경
						</c:if>
						</time>
					</li>
				</ul>
			</div>
		</div>
		<div class="menu">
			<ol id="timetableList">
				<%-- 시간표가 존재하면 목록 불러오기 --%>
				<c:if test="${timetableCount > 0}">
				<c:forEach var="timetable" items="${timetableList}">
				<li id="${timetable.t_num}" <c:if test="${timetable.t_num == selectedT_num}">class="active"</c:if>>
					<a  href="${pageContext.request.contextPath}/timetable/timetableView.do?semester=${timetable.semester}&t_num=${timetable.t_num}" 
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
						<c:forEach var="i" begin="0" end="4">
						<td id="day${i}">
							<c:if test="${timetableSubjectCount > 0}">	
							<div class="cols" style="width: 149px;">
								<c:forEach var="item" items="${timesList}">
								<c:if test="${item.day==i}">
									<div class="subject color${item.color}" style="height:${(item.endtime-item.starttime+1)*60+1}px; top: ${(item.starttime-1)*60}px;">
										<ul class="status" style="display: none;">
											<li title="삭제" class="del"></li>
										</ul>
										<h3>${item.sub_name}</h3><p><em>${item.prof_name}</em><span>${item.classRoom}</span></p>
									</div>
								</c:if>
								</c:forEach>
							</div>
							</c:if>
							<div class="grids">
								<c:forEach var="j" begin="0" end="11">
									<div class="grid"></div>
								</c:forEach>
							</div>
						</td>
						</c:forEach>
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
	<ul id="floating" class="floating">
		<li id="buttonSearch" class="button search">수업 목록에서 검색</li>
		<li id="buttonCustom" class="button custom">직접 추가</li>
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
	</form>

</div>
<div id="subjects" style="display:none;">
	<a class="close">닫기</a>
	<div class="list">
		<table>
			<thead>
				<tr>
					<th>과목번호</th>
					<th>이수구분</th>
					<th>과목명</th>
					<th>교수명</th>
					<th>학점</th>
					<th>시간</th>
					<th>강의실</th>
					<th>정원</th>
					<th>사이버강의</th>
					<th>비고</th>
				</tr>
			</thead>
	
			<tbody id="subjectList">
				
			</tbody>
			<tfoot><tr><td colspan="14"></td></tr></tfoot>
		</table>
	</div>
</div>
