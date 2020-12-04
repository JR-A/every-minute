<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- jstl의 core 라이브러리 사용 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- 시간 포맷 등 -->
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/timetable.container.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/html2canvas.js"></script>
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
		
		//수업 목록에서 검색 버튼 클릭시 수업 목록 열기
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
						output +=	'<td class="subjectTd">';
							if(item.sub_online==1) output += 'O';
							else output += 'X';
						output += 	'</td>';
						output +=	'<td class="subjectTd">';
							if(item.sub_remark!=null) output += item.sub_remark;
						output += 	'</td>';
						output += '</tr>';
						
						$('#subjectList').append(output);
					});
				},
				error: function(request,status,error){
					alert(">>code:"+request.status+"\n\n"+">>message:"+request.responseText+"\n\n"+">>error:"+error);
				}
			});
		});	
		//수업목록의 닫기버튼 클릭시 목록 닫기
		$(document).on('click','a.close',function(){
			var height = $('#container').height() + $('#subjects').height() - $('#main_footer').height();
			$('#subjects').css("display", "none");
			$('#container').css("height", height);
		});
		
		
		//수업 목록에서 마우스오버시 미리보기
		$(document).on('mouseover','#subjectList>tr',function(){
	
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
		//수업목록에서 마우스아웃시 미리보기 삭제
		$(document).on('mouseout','#subjectList>tr',function(){
			$('.preview').remove();
		});
		
		//수업 목록에서 클릭시 시간표에 수업 추가
		$(document).on('click','#subjectList>tr',function(){

			$.ajax({
				url: '${pageContext.request.contextPath}/timetable/insertSubject.do',
				type: 'post',
				data: {sub_num: $(this).attr('id'), t_num: $('#timetableList>li.active').attr('id') },
				dataType: 'json',
				cache: false,
				timeout: 30000,
				success: function(data){
					if(data.result == 'success'){
						location.reload();
						
					}else if(data.result == 'duplicated'){
						alert('이미 추가한 수업입니다!');
					}else if(data.result == 'overlapped'){
						alert('같은 시간에 이미 수업이 있습니다!');
					}else{
						alert('수업 추가시 에러발생!');
					}
				},
				error: function(request,status,error){
					alert(">>code:"+request.status+"\n\n"+">>message:"+request.responseText+"\n\n"+">>error:"+error);
				}
			});
		});
		
		
		//과목에 마우스오버시 삭제버튼 노출
		$(document).on('mouseover','.subject',function(){
			$(this).children(".status").css("display", "block");
		});
		
		//과목에 마우스아웃시 삭제버튼 숨김
		$(document).on('mouseout','.subject',function(){
			$(this).children(".status").css("display", "none");
		});
		
		//과목의 삭제버튼 클릭시 과목 삭제
		$(document).on('click','.del',function(){
			var ans = confirm('이 수업을 삭제하시겠습니까?');
			
			if(ans){
				$.ajax({
					url: '${pageContext.request.contextPath}/timetable/deleteSubject.do',
					type: 'post',
					data: {sub_num: $(this).closest("div").attr('id'), t_num: $('#timetableList>li.active').attr('id') },
					dataType: 'json',
					cache: false,
					timeout: 30000,
					success: function(data){
						if(data.result == 'success'){
							location.reload();
						}
					},
					error: function(request,status,error){
						alert(">>code:"+request.status+"\n\n"+">>message:"+request.responseText+"\n\n"+">>error:"+error);
					}
				});
			}
		});
		
		//직접 추가 버튼 클릭시 폼 노출
		$(document).on('click','#buttonCustom',function(){
			$('#customsubjects').css("display", "block");
		});
		
		//직접 추가 폼의 닫기버튼 클릭시 폼 숨기기
		$(document).on('click','a.close',function(){
			$('a.remove').closest("div").remove();
			$('#customsubjects').css("display", "none");
		});
		
		//직접 추가 폼의 요일 선택시
		$(document).on('click','.weeks>li',function(){
			$(this).siblings().removeClass( 'active' );
			$(this).addClass('active');
		});
		
		//직접 추가 폼의 더입력 버튼 클릭시
		$(document).on('click','a.new',function(){
			var output = '';
			output += '<div class="timeplace" style="display: block;">';
			output += 	'<ol class="weeks">';
			output += 		'<li class="active">월</li><li>화</li><li>수</li><li>목</li><li>금</li>';
			output += 	'</ol>';
			output += 	'<a class="remove">삭제</a>';
			output += 	'<p>';
			output += 		'<select class="starthour"><option value="1" selected="selected">오전 9시</option><option value="2">오전 10시</option><option value="3">오전 11시</option><option value="4">오후 12시</option><option value="5">오후 1시</option><option value="6">오후 2시</option><option value="7">오후 3시</option><option value="8">오후 4시</option><option value="9">오후 5시</option><option value="10">오후 6시</option><option value="11">오후 7시</option><option value="12">오후 8시</option></select><span>~</span><select class="endhour"><option value="1" selected="selected">오전 10시</option><option value="2">오전 11시</option><option value="3">오후 12시</option><option value="4">오후 1시</option><option value="5">오후 2시</option><option value="6">오후 3시</option><option value="7">오후 4시</option><option value="8">오후 5시</option><option value="9">오후 6시</option><option value="10">오후 7시</option><option value="11">오후 8시</option><option value="12">오후 9시</option></select><input type="text" placeholder="예) 종303" class="text place">';
			output += 	'</p>'
			output += '</div>';
			
			$(this).before(output);
		});
	
		//직접 추가 폼의 삭제 버튼 클릭시
		$(document).on('click','a.remove',function(){
			$(this).closest("div").remove();
		});
		
		//직접 추가 폼의 저장버튼 클릭시 커스텀과목 추가
		$(document).on('submit','#customsubjects',function(event){
			var check = true;
			
			//필수 항목을 입력하지 않은 경우
			if($('#customsubjects input[name=csub_name]').val().trim() == ''){
				alert('이름을 입력하세요!')
				$('#customsubjects input[name=csub_name]').focus();
				check = false;
			}
			
			if (!check) { return false; }
			
			var csub_time = '';
			var csub_classRoom = '';
			$('div.timeplace').each(function(index,item){
				if(index > 0) {csub_time += ','; csub_classRoom += ',';}
				csub_time += $(this).find('li.active').text();	//요일
			   
			    var starthour = $(this).find('select.starthour > option:selected').val();
			    var endhour = $(this).find('select.endhour > option:selected').val();
			    
			    var intStarthour = Number(starthour);
			    var intEndhour = Number(endhour);
			    
			    if(intStarthour > intEndhour){
			    	alert('시작 시간은 종료 시간보다 빨라야합니다!');
			    	check = false;
			    	return false;	//each문 탈출, submit종료는 each문 바깥에 작성
			    }
			    
			    //교시 구하기
			   	if(intStarthour >= 10){
			   		switch(intStarthour){
			   		case 10: csub_time += 'a'; break;
			   		case 11: csub_time += 'b'; break;
			   		case 12: csub_time += 'c'; break;
			   		}
			   	}
			   	else{
				    csub_time += intStarthour;
			   	}
			    if(intEndhour != intStarthour){	//끝나는교시와 시작교시가 같으면 끝나는교시 입력하지않음
			    	if(intEndhour >= 10){
				   		switch(intEndhour){
				   		case 10: csub_time += 'a'; break;
				   		case 11: csub_time += 'b'; break;
				   		case 12: csub_time += 'c'; break;
				   		}
				   	}else{
					    csub_time += intEndhour;
				   	}
			    }   	

			    csub_classRoom +=$(this).find('input.text.place').val();	//submit종료
			});
			
			if (!check) { event.preventDefault(); return false; }
			
			var semester =  $("#semester option:selected").val();

			$('input[name=semester]').val(semester);
			$('input[name=t_num]').val($('#timetableList>li.active').attr('id'));
			$('input[name=csub_time]').val(csub_time);
			$('input[name=csub_classRoom]').val(csub_classRoom);
			
			//커스텀 과목 추가
			$.ajax({
				url: '${pageContext.request.contextPath}/timetable/insertCustomSubject.do',
				type: 'post',
				data: $('#customsubjects').serialize() + "&semester="+semester,
				dataType: 'json',
				cache: false,
				timeout: 30000,
				success: function(data){
					if(data.result == 'success'){
						location.reload();
					}else if(data.result == 'overlapped'){
						alert('같은 시간에 이미 수업이 있습니다!');
					}else{
						alert('수업 추가시 에러발생!');
					}
				},
				error: function(request,status,error){
					alert(">>code:"+request.status+"\n\n"+">>message:"+request.responseText+"\n\n"+">>error:"+error);
				}
			});
			event.preventDefault();	//기본 이벤트 삭제
			event.stopPropagation();
		});
		
		
		//시간표의 설정 버튼 클릭시 폼 노출
		$('#buttonTableSetting').click(function(){
			$('#tableSetting').before('<div class="modalwrap"></div>');
			var modal = $(this).data('modal');
			$('#'+modal).css("display", "block");
			
			//시간표 정보 불러오기
			$.ajax({
				url: '${pageContext.request.contextPath}/timetable/selectTimetable.do',
				type: 'post',
				data: {t_num: $('#timetableList>li.active').attr('id') },
				dataType: 'json',
				cache: false,
				timeout: 30000,
				success: function(data){
					$('#tableSetting input[name=t_name]').val(data.t_name);
					$('#tableSetting input[name=t_num]').val(data.t_num);
					$('#tableSetting input[name=semester]').val(data.semester);
					if(data.isPrimary == 1) {
						$('#tableSetting_is_primary').attr('checked', true);
						$('#tableSetting_is_primary').attr('disabled', 'disabled');
					}	
				},
				error: function(request,status,error){
					alert(">>code:"+request.status+"\n\n"+">>message:"+request.responseText+"\n\n"+">>error:"+error);
				}
			});
		});
		
		//시간표 설정 저장버튼 클릭시 설정저장
		$(document).on('submit','#tableSetting',function(event){
			var check = true;
			var submitName = $('#tableSetting input[name=t_name]').val(); //입력된 시간표 이름
			//해당학기의 시간표와 이름이 겹치는지 체크
			$('#timetableList li:not(.active):not(:last-child)').each(function(index,item){
				var tname = $(this).find('a').text();
				if(tname == submitName){
					alert('이미 존재하는 이름입니다.');
					check = false;
				}
			});
			
			if (!check) { event.preventDefault(); return false; }
			
			//현재 기본시간표가 아니고 기본시간표에 체크되어있으면
			if(!$('#tableSetting_is_primary').is('disabled')){				
				if($('#tableSetting_is_primary').is(':checked') == true ){
					//기본시간표를 현재 시간표로 변경
					$('#tableSetting input[name=updatePrimary]').val(1);
				}
			}
			else{
				$('#tableSetting input[name=updatePrimary]').val(0);
			}
			var value = $('#tableSetting input[name=updatePrimary]').val();
			
			//설정 저장
			$.ajax({
				url: '${pageContext.request.contextPath}/timetable/updateTimetable.do',
				type: 'post',
				data: $('#tableSetting').serialize() + "&updatePrimary="+value,
				dataType: 'json',
				cache: false,
				timeout: 30000,
				success: function(data){
					if(data.result == 'success'){
						alert('저장했습니다.');
						location.reload();
					}
					else{
						alert('설정 저장 오류발생!');
					}
				},
				error: function(request,status,error){
					alert(">>code:"+request.status+"\n\n"+">>message:"+request.responseText+"\n\n"+">>error:"+error);
				}
			});
			event.preventDefault();	//기본 submit 이벤트를 제거해주기!!(제거하지 않으면 자기자신(원래페이지 링크) 호출함)
		});
		
		//시간표 설정의 삭제 버튼 클릭시
		$(document).on('click','#buttonDelete',function(){
			var ans = confirm('이 시간표를 삭제하시겠습니까?');
			
			if(ans){
				$.ajax({
					url: '${pageContext.request.contextPath}/timetable/deleteTimetable.do',
					type: 'post',
					data: {t_num: $('#timetableList>li.active').attr('id') },
					dataType: 'json',
					cache: false,
					timeout: 30000,
					success: function(data){
						if(data.result == 'success'){
							var semester =  $("#semester option:selected").val();
							location.href = '${pageContext.request.contextPath}/timetable/timetableView.do?semester='+semester;
						}
					},
					error: function(request,status,error){
						alert(">>code:"+request.status+"\n\n"+">>message:"+request.responseText+"\n\n"+">>error:"+error);
					}
				});
			}
		});
		
		//설정 닫기 버튼 클릭시 폼 숨김
		$(document).on('click','#tableSetting>a.close',function(){
			$('.modalwrap').remove();
			$(this).closest("form").css("display", "none");
		});
		
	});
</script>
<script type="text/javascript">
	window.onload = function(){
		var wrap = document.getElementById('wrap');
		var downloadImg = document.getElementById('downloadImg');
		var buttonExport = document.getElementById('buttonTableExport');
		
		//이미지 다운로드 버튼 클릭시
		buttonExport.onclick = function(){
			//이미지 다운로드 이벤트 발생
			html2canvas(wrap).then(function(canvas) {
			    downloadImg.setAttribute('href', canvas.toDataURL());
			    downloadImg.setAttribute('download', '시간표.png');
			  	
			  	downloadImg.click();
			});
		}
	}
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
			<hr>
			<ol class="buttons threecols">
	          <li id="buttonTableExport"><a class="light image export">이미지</a></li>
	          <li id="buttonTableSetting" data-modal="tableSetting"><a class="light image setting">설정</a></li>
	        </ol>
	        <hr>
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
	<div id="wrap" class="wrap">
	<a id="downloadImg" style="display:none"></a>
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
							<div class="cols" style="width: 149px;">
							<c:if test="${timetableSubjectCount > 0}">
								<c:forEach var="item" items="${timesList}">
								<c:if test="${item.day==i}">
									<div id="${item.sub_num}" class="subject color${item.color}" style="height:${(item.endtime-item.starttime+1)*60+1}px; top: ${(item.starttime-1)*60}px;">
										<ul class="status" style="display: none;">
											<li title="삭제" class="del"></li>
										</ul>
										<h3>${item.sub_name}</h3><p><em>${item.prof_name}</em><span>${item.classRoom}</span></p>
									</div>
								</c:if>
								</c:forEach>
							</c:if>
							</div>
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
	<form:form id="tableSetting" class="modal" style="margin-left: -200px; margin-top: -153.5px; display: none;">
    	<input type="hidden" name="t_num">
    	<input type="hidden" name="semester">
		<input type="hidden" name="updatePrimary" value="0">
		
		<a title="닫기" class="close"></a>
		<h3>시간표 설정</h3>
		<p>
		  <label>이름</label>
		  <input name="t_name" type="text" maxlength="40" class="text"/>
		</p>
		<p>
		  <label>기본</label>
		  <input type="checkbox" id="tableSetting_is_primary"/>
		  <label for="tableSetting_is_primary" class="checkbox">기본시간표 설정</label>
		</p>
		<input id="buttonDelete" type="button" value="삭제" class="button light floatLeft">
      	<input type="submit" value="설정 저장" class="button">
    </form:form>
	<ul id="floating" class="floating">
		<li id="buttonSearch" class="button search">수업 목록에서 검색</li>
		<li id="buttonCustom" class="button custom">직접 추가</li>
	</ul>
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
<form id="customsubjects" style="display:none;" method="post" action="${pageContext.request.contextPath}/timetable/insertCustomSubject.do">
	<input type="hidden" name="semester">
	<input type="hidden" name="t_num">
	<input type="hidden" name="csub_time">
	<input type="hidden" name="csub_classRoom">
	
	<a title="닫기" class="close"></a>
	<h2>새 수업 추가</h2>
	<dl>
		<dt>과목명 (필수)</dt>
		<dd>
			<input type="text" name="csub_name" placeholder="예) 경제학입문" maxlength="32" class="text">
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
					<select class="starthour"><option value="1" selected="selected">오전 9시</option><option value="2">오전 10시</option><option value="3">오전 11시</option><option value="4">오후 12시</option><option value="5">오후 1시</option><option value="6">오후 2시</option><option value="7">오후 3시</option><option value="8">오후 4시</option><option value="9">오후 5시</option><option value="10">오후 6시</option><option value="11">오후 7시</option><option value="12">오후 8시</option></select>        				
       				<span>~</span>
					<select class="endhour"><option value="1" selected="selected">오전 10시</option><option value="2">오전 11시</option><option value="3">오후 12시</option><option value="4">오후 1시</option><option value="5">오후 2시</option><option value="6">오후 3시</option><option value="7">오후 4시</option><option value="8">오후 5시</option><option value="9">오후 6시</option><option value="10">오후 7시</option><option value="11">오후 8시</option><option value="12">오후 9시</option></select>        				
       				<input type="text" placeholder="예) 종303" class="text place">
       			</p>
       		</div>
       		<a class="new"><strong>+</strong>더 입력</a>
    	</dd>
	</dl>
	<div class="clearBothOnly"></div>
	<div class="submit"><input type="submit" value="저장" class="button"></div>
</form>
