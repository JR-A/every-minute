<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customBoard.css">
<script src="<c:url value="/resources/js/jquery-3.5.1.min.js" />"></script>

<script type="text/javascript">
$(document).ready(function(){
	var currentPage;
	var count;
	var rowCount;
	//댓글 목록
	function selectData(pageNum,post_num){
		currentPage = pageNum;
		
		if(pageNum == 1){
			//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
			$('#output').empty();
		}
		//로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			type:'post',
			data:{pageNum:pageNum,post_num:post_num},
			url:'listComment.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				//로딩 이미지 감추기
				$('#loading').hide();
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
				
				if(count < 0 || list == null){
					alert('목록 호출 오류 발생!');
				}else{
					//댓글 목록 작업
					$(list).each(function(index,item){
						var output = '<div class="item">';
						if(item.anonymous == 1){
							output += '  <h4><img src="https://cf-fpi.everytime.kr/0.png" width="30" height="30" class="picture large">익명</h4>';
						}else if(item.anonymous == 0){
							output += '  <h4><img src="commentImageView.do?mem_num='+item.mem_num+'" width="30" height="30" class="picture large">' + item.id + '</h4>';
						}
						output += '  <div class="sub-item">';
						//output += '    <p>' + item.re_content.replace(/\n/g,'<br>') + '</p>';
						output += '    <p>' + item.content.replace(/</gi,'&lt;').replace(/>/gi,'&gt;') + '</p>';
						output += '<span class="comment-date">'+item.reg_date+'</span>';
						
						if($('#mem_num').val()==item.mem_num){
							//로그인 한 회원 번호가 댓글 작성자 번호와 같으면
							output += '  <input type="button" data-num="'+item.comment_num+'" data-mem="'+item.mem_num+'" value="수정" class="modify-btn">';
							output += '  <input type="button" data-num="'+item.comment_num+'" data-mem="'+item.mem_num+'" value="삭제" class="delete-btn">';
						}
						output += '      <hr size="1" noshade>';
						output += '  </div>';
						output += '</div>';
												
						//문서 객체에 추가
						$('#output').append(output);
					}); 
					
					
					//paging button 처리
					if(currentPage>=Math.ceil(count/rowCount)){
						//다음 페이지가 없음
						$('.paging-button').hide();
					}else{
						//다음 페이지가 존재
						$('.paging-button').show();
					}
				}
			},
			error:function(){
				//로딩 이미지 감추기
				$('#loading').hide();
				alert('네트워크 오류');
			}
		});
	}
	
	//다음 댓글 보기 버튼 클릭시 데이터 추가
	$('.paging-button input').click(function(){
		var pageNum = currentPage + 1;
		selectData(pageNum,$('#post_num').val());
	});
	
	//댓글 등록
	$('#comment_form').submit(function(event){
		if($('#content').val()==''){
			alert('내용을 입력하세요');
			$('#content').focus();
			return false;
		}
		
		var data = $(this).serialize();
		//등록
		$.ajax({
			type:'post',
			data:data,
			url:'writeComment.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result=='logout'){
					alert('로그인해야 작성할 수 있습니다.');
				}else if(data.result=='success'){
					//폼초기화
					initForm();
					//댓글 작성이 성공하면 새로 삽입한 글을
					//포함해서 첫번째 페이지의 게시글들을 다시
					//호출함
					selectData(1,$('#post_num').val());
				}else{
					alert('등록시 오류 발생!');
				}
			},
			error:function(){
				alert('네트워크 오류!');
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});
	//댓글 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#comment_first .letter-count').text('300/300');
	}
	
	//textarea에 내용 입력시 글자수 체크
	$(document).on('keyup','textarea',function(){
	//남은 글자수를 구함
	var inputLength = $(this).val().length;
	
	if(inputLength>300){//300자를 넘어선 경우
		$(this).val($(this).val().substring(0,300));
	}else{//300자 이하인 경우
		var remain = 300 - inputLength;
		remain += '/300';
		if($(this).attr('id')=='content'){
			//등록폼 글자수
			$('#comment_first .letter-count').text(remain);
		}else{
			//수정폼 글자수
			$('#comment_first .letter-count').text(remain);
		}
	}
});
	
	//댓글 수정 버튼 클릭시 수정폼 노출
	$(document).on('click','.modify-btn',function(){
		
		//댓글 글번호
		var comment_num = $(this).attr('data-num');
		//작성자 아이디
		var mem_num = $(this).attr('data-mem');
		//댓글 내용
		var content = $(this).parent().find('p').html().replace(/<br>/gi,'\n');
		                                             //g:지정문자열 모두, i:대소문자 무시
		//댓글 수정폼 UI
		var modifyUI = '<form id="mre_form">'
			modifyUI += '   <input type="hidden" name="comment_num" id="mre_num" value="'+comment_num+'">';
			modifyUI += '   <input type="hidden" name="mem_num" id="mem_num" value="'+mem_num+'">';
			modifyUI += '   <textarea rows="3" cols="50" name="content" id="mre_content" class="rep-content">'+content+'</textarea>';
			modifyUI += '   <div id="mre_first"><span class="letter-count">300/300</span></div>';      
			modifyUI += '   <div id="mre_second" class="align-right">';
			modifyUI += '      <input type="submit" value="수정">';
			modifyUI += '      <input type="button" value="취소" class="re-reset">';
			modifyUI += '   </div>';
			modifyUI += '   <hr size="1" noshade width="96%">';
			modifyUI += '</form>';
			
	
		//이전에 이미 수정하는 댓글이 있을 경우 수정버튼을 클릭하면
		//숨김 sub-item를 환원시키고 수정폼을 초기화함
		initModifyForm();
		//지금 클릭해서 수정하고자 하는 데이터는 감추기
		//수정버튼을 감싸고 있는 div
		$(this).parent().hide();
			
		//수정폼을 수정하고자하는 데이터가 있는 div에 노출
		$(this).parents('.item').append(modifyUI);
				
		//입력한 글자수 셋팅
		var inputLength = $('#mre_content').val().length;
		var remain = 300 - inputLength;
		remain += '/300';
		
		//문서 객체에 반영
		$('#mre_first .letter-count').text(remain);
	
	});
	
	//수정폼에서 취소 버튼 클릭시 수정폼 초기화
	$(document).on('click','.comment-reset',function(){
		initModifyForm();
	});
	
	//댓글 수정 폼 초기화
	function initModifyForm(){
		$('.sub-item').show();
		$('#mre_form').remove();
	}
	
	//댓글 수정
	$(document).on('submit','#mre_form',function(event){
		if($('#mre_content').val()==''){
			alert('내용을 입력하세요!');
			$('#mre_content').focus();
			return false;
		}
		var result = confirm('수정 하시겠습니까?');
		if(result) {
			
			
		}else{
			return false;
		}
		
		//폼에 입력한 데이터 반환
		var data = $(this).serialize();
		
		//수정
		$.ajax({
			url:'updateComment.do',
			type:'post',
			data:data,
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result=='logout'){
					alert('로그인해야 수정할 수 있습니다.');
				}else if(data.result=='success'){
					//$('#mre_form').parent().find('p').html($('#mre_content').replace(/\n/g,'<br>'));
					$('#mre_form').parent().find('p').html($('#mre_content').val().replace(/</g,'&lt;').replace(/>/g,'&gt;'));
					
					//변경 시간 처리
					var time = new Date();
					var month = time.getMonth() + 1;
					var date = time.getDate();
					var hours = time.getHours();
					var minutes = time.getMinutes();
					
					var clock = '';
					clock += ((month<10) ? '0' : '') +month;
					clock += ((date<10) ? '/0' : '/') +date;
					clock += ((hours<10) ? ' 0' : ' ') +hours;
					clock += ((minutes<10) ? ':0' : ':') +minutes;
					
					$('#mre_form').parent().find('span.comment-date').text(clock);
					
					//수정폼 초기화
					initModifyForm();
				}else if(data.result=='wrongAccess'){
					alert('타인의 글은 수정할 수 없습니다.');
				}else{
					alert('수정 오류 발생');
				}
			},
			error:function(){
				alert('댓글 수정시 네크워크 오류!');
			}
		});
		//기본 이벤트 제거
		event.preventDefault();
	});
	
	//댓글 삭제
	$(document).on('click','.delete-btn',function(){
		var result = confirm('삭제하시겠습니까?');
		if(result) {
			
			
		}else{
			return false;
		}
		
		//댓글 번호
		var comment_num = $(this).attr('data-num');
		//작성자 아이디
		var mem_num = $(this).attr('data-mem');
		
		$.ajax({
			type:'post',
			url:'deleteComment.do',
			data:{comment_num:comment_num,mem_num:mem_num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'logout'){
					alert('로그인해야 삭제할 수 있습니다.');
				}else if(data.result == 'success'){
					alert('삭제 완료!');
					selectData(1,$('#post_num').val());
				}else if(data.result == 'wrongAccess'){
					alert('타인의 글을 삭제할 수 없습니다.');
				}else{
					alert('댓글 삭제시 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생!');
			}
		});
	});
	//초기 데이터(목록) 호출
	selectData(1,$('#post_num').val());
});
</script>

<!-- 제목&소제목 -->
<h2 class="title">
	<a id="title" href="customPostList.do?board_num=${boardInfo.board_num}">${boardInfo.title}</a>
	<br>
	<span id="subtitle">${boardInfo.subtitle}</span>
</h2>

<!-- 게시글 상세페이지 -->
<div class="page-main-style-detail">
	<article>
		<a class="article2">
			<c:if test="${!empty customPost}">
				<!-- 프로필 사진 -->	
				<c:if test="${customPost.anonymous == 0}"> <!-- 익명처리 off -->
					<c:if test="${empty member.photoname}">
					<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" class="picture large">	
				</c:if>
				<c:if test="${!empty member.photoname}">
					<img src="${pageContext.request.contextPath}/member/photoView.do" class="picture large">
					</c:if>
				</c:if>
				
				<c:if test="${customPost.anonymous == 1}"> <!-- 익명처리 on  -->
					<img src="${pageContext.request.contextPath}/resources/images/blank.jpg" class="picture large">	
				</c:if>
				
				<!-- 작성자 아이디 -->
				<div class="profile">
					<c:if test="${customPost.anonymous == 0}"> <!-- 익명처리 off -->
						<h3 class="large">${customPost.id}</h3>
					</c:if>
					<c:if test="${customPost.anonymous == 1}"> <!-- 익명처리 on  -->
						<h3 class="large">익명</h3>
					</c:if>
					<!-- 작성일 -->
					<time class="large"><fmt:formatDate value="${customPost.reg_date}" pattern="MM/dd HH:MM"/></time>
				</div>
				
				<ul class="status">
					<li class="massagesend"><a onclick="location.href='massageSend.do'">쪽지</a></li>
					<li class="abuse"><a onclick="location.href='blameCustomBoard.do">신고</a></li>
				</ul>
				<hr>
				<!-- 내용 -->		
				<p class="small">
					${customPost.content}
				</p>
				<ul class="status">
					<li class="attach"><!-- 총 사진 수 --></li>
					<li title="추천" class="vote"><!-- 총 추천 수 --></li>
					<li title="댓글" class="comment"><!-- 총 댓글 수 --></li>
					<li title="즐겨찾기" class="scrap"><!-- 총 즐겨찾기 수 --></li>
				</ul>
				<hr>
				<!-- 첨부파일 -->
				
				<%-- <c:if forEach>사용 첨부파일 list가 있을 때마다 반복 
						<figure class="attach">
							<img src="customPostImageView.do?post_num=${customPost.post_num}" style="max-width:500px;"> 
						<figure>	
						<figcaption>
							${사진에 대한 코멘트} ex)"아이보리 색이고 (위 사진 참고) 이뻐요!!!"<br>"10000원에 팔아요!"
						</figcaption>
					</c:if forEach>
				--%>
				
				<div class="align-center">
					<c:if test="${!empty customPost.filename}"> <!-- filename이 있으면 image가 있는거 -->
						<img src="customPostImageView.do?post_num=${customPost.post_num}&&board_num=${boardInfo.board_num}" style="max-width:500px;"> <!-- 세션에 없기 때문에 get방식으로 넘겨줘야 함 -->
					</c:if>
				</div>
			</c:if>
		</a>
	</article>
</div>

<div class="align-right">
	<c:if test="${!empty user && user.mem_num == customPost.mem_num}">
		<input type="button" value="수정" onclick="location.href='customPostModify.do?post_num=${customPost.post_num}&&board_num=${boardInfo.board_num}'">
		<input type="button" value="삭제" id="delete_btn">
		<script type="text/javascript">
			var delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick=function() {
				var choice = window.confirm('정말 삭제하시겠습니까?');
				if (choice) {
					location.href='deleteCustomPost.do?post_num=${customPost.post_num}';
				}
			}
		</script>
	</c:if>
	
	<%-- <div class ="wrapstatus">
		<ul class="status">
			<!--<c:if test="${!empty user}">-->
				<li class="vote" id="like_check" >
				0
				</li>
				
				<li class="comm" id="count">${freeboard.comment_cnt}</li>
			<!--</c:if>-->
		</ul>
	</div> --%>
</div>

<!-- 댓글 -->
<div id="comment_div">
	<span class="comment-title">댓글 달기</span>
	<form id="comment_form">
		<input type="hidden" name="post_num"
		       value="${customPost.post_num}" id="post_num">      
		<input type="hidden" name="mem_num"
		       value="${user.mem_num}" id="mem_num">
		<textarea rows="3" cols="50"
		  name="content" id="content"
		  class="rep-content"
		  <c:if test="${empty user}">disabled="disabled"</c:if>
		  ><c:if test="${empty user}">로그인해야 작성할 수 있습니다.</c:if></textarea>              
		<c:if test="${!empty user}">
		<div id="comment_first">
			<span class="letter-count">300/300</span>
		</div>
		<div id="comment_second" class="align-right">
		    <input type="checkbox" name="anonymous" value="1" checked>익명
			<input type="submit" value="전송">
		</div>
		</c:if>
	</form>
</div>
<!-- 댓글 목록 출력 -->
<div id="output"></div>
<div class="paging-button" style="display:none;">
	<input type="button" value="댓글 더 보기">
</div>
<div id="loading" style="display:none;">
	<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
</div>




