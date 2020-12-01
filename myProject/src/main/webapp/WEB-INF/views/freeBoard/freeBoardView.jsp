<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/freeBoard.css">
<script src="<c:url value="/resources/js/jquery-3.5.1.min.js" />"></script>
<<script type="text/javascript">
$(document).ready(function(){
	//좋아요 처리된 형태
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
			url:'listReply.do',
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
							output += '  <h4><img src="https://cf-fpi.everytime.kr/0.png" width="30" height="30" class="picture large"> 익명</h4>';
						}else if(item.anonymous == 0){
							output += '  <h4><img src="replayImageView.do?mem_num='+item.mem_num+'" width="30" height="30" class="picture large">' + item.id + '</h4>';
						}
						output += '  <div class="sub-item">';
						//output += '    <p>' + item.re_content.replace(/\n/g,'<br>') + '</p>';
						output += '    <p>' + item.content.replace(/</gi,'&lt;').replace(/>/gi,'&gt;') + '</p>';
						output += item.reg_date;
						
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
	$('#re_form').submit(function(event){
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
			url:'writeReply.do',
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
		$('#re_first .letter-count').text('300/300');
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
			$('#re_first .letter-count').text(remain);
		}else{
			//수정폼 글자수
			$('#mre_first .letter-count').text(remain);
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
	$(document).on('click','.re-reset',function(){
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
		
		//폼에 입력한 데이터 반환
		var data = $(this).serialize();
		
		//수정
		$.ajax({
			url:'updateReply.do',
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
		//댓글 번호
		var comment_num = $(this).attr('data-num');
		//작성자 아이디
		var mem_num = $(this).attr('data-mem');
		
		$.ajax({
			type:'post',
			url:'deleteReply.do',
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
<h2 class="title"><a href="freeBoardList.do">자유게시판</a></h2>
<div class="page-main-style-detail">

	<article>
		<a class="article2">
			
			<c:if test="${empty freeboard.photoname}">
				<img src="https://cf-fpi.everytime.kr/0.png" width="100" height="100" class="picture large">
			</c:if>
			<c:if test="${!empty freeboard.photoname}">
				<c:if test="${0 eq freeboard.anonymous}">
				<img src="${pageContext.request.contextPath}/member/photoView.do" width="100" height="100" class="picture large">
				</c:if>
				<c:if test="${1 eq freeboard.anonymous}">
				<img src="https://cf-fpi.everytime.kr/0.png" width="100" height="100" class="picture large">
				</c:if>
			</c:if>
			<div class="profile">
					<h3 class="large"><c:if test="${0 eq freeboard.anonymous}">
						${freeboard.id}
				</c:if>
				<c:if test="${1 eq freeboard.anonymous}">
						익명
				</c:if></h3>
				<time class="large"><fmt:formatDate value="${freeboard.modify_date}" pattern="MM/dd HH:MM"/></time>
			</div>
			<ul class="status">
				<li class="messagesend" data-modal="messageSend" data-article-id="76626841" data-is-anonym="0">쪽지</li>
				<li class="abuse">신고</li>
			</ul>
			<hr>
			<h1>${freeboard.title}</h1>
			
				${freeboard.content}
			
			<c:if test="${!empty freeboard.filename}"> <!-- filename이 비어있지 않는다면 아래의 div를 실행 -->
			<div class="align-center">
				<img src="imageView.do?post_num=${freeboard.post_num}" style="max-width: 500px;">
			</div>
			</c:if>

		<div class ="wrapstatus">
			<ul class="status">
				<!--<c:if test="${!empty user}">-->
					<li class="vote" id="like_check" >
					<c:out value="${freeLikeVO.like_check}"/> 
					</li>
					
					<li class="comm">0</li>
				<!--</c:if>-->
			</ul>
		</div>
		<script>

/* 
		$("#like_check").on('click', function() {
		  alert("추천하시겠습니까?");
		}); */
		
		function like_check(){
			$.ajax({
			url: "freeLikeCount.do",
			type: "POST",
			cache: false,
			dataType: "json",
			data: $('#like_check'), //아이디가 like_form인 곳의 모든 정보를 가져와 파라미터 전송 형태(표준 쿼리형태)로 만들어줌
			success:
			function(data){ //ajax통신 성공시 넘어오는 데이터 통째 이름 =data
			alert("'좋아요'가 반영되었습니다!") ; // data중 put한 것의 이름 like
			$("#like_check").html(data.like_check); //id값이 like_result인 html을 찾아서 data.like값으로 바꿔준다.
			},
			error:
			function (request, status, error){
			alert("ajax실패")
			}
			});
			}

	
		</script>
</div>

	<div class="align-right">
		<%--수정 삭제의 경우는 로그인이 되어있고 로그인한 회원번호와 작성자 회원번호가 일치해야 함 --%>
		<c:if test="${!empty user && user.mem_num == freeboard.mem_num}">
		<input type="button" value="수정" onclick="location.href='update.do?post_num=${freeboard.post_num}'">
		<input type="button" value="삭제" id="delete_btn">
		<script>
			var delete_btn = document.getElementById('delete_btn');
			//이벤트 연결
			delete_btn.onclick=function(){
				var choice = window.confirm('삭제하시겠습니까?');
				if(choice){
					location.href='delete.do?post_num=${freeboard.post_num}';
				}
			};
		</script>
		</c:if>
		<input type="button" value="목록" onclick="location.href='freeBoardList.do'">
	</div>
	<!-- 글쓰기 끝 -->
	<!-- 댓글 -->
	<div id="reply_div">
		<span class="reply-title">댓글 달기</span>
		<form id="re_form">
			<input type="hidden" name="post_num"
			       value="${freeboard.post_num}" id="post_num">
			<input type="hidden" name="mem_num"
			       value="${freeboard.mem_num}" id="mem_num">
			<textarea rows="3" cols="50"
			  name="content" id="content"
			  class="rep-content"
			  <c:if test="${empty user}">disabled="disabled"</c:if>
			  ><c:if test="${empty user}">로그인해야 작성할 수 있습니다.</c:if></textarea>              
			<c:if test="${!empty user}">
			<div id="re_first">
				<span class="letter-count">300/300</span>
			</div>
			<div id="re_second" class="align-right">
			    <input type="checkbox" name="anonymous" value="1" checked>익명
				<input type="submit" value="전송">
			</div>
			</c:if>
		</form>
	</div>
	<!-- 댓글 목록 출력 -->
	<div id="output"></div>
	<div class="paging-button" style="display:none;">
		<input type="button" value="다음글 보기">
	</div>
	<div id="loading" style="display:none;">
		<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
	</div>

