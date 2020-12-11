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
		
		//게시글 신고하기
		//$(document).on('click','.blame',function(event){
		$('.blame').click(function(){
			var choice = window.confirm('해당 게시글을 신고하시겠습니까?');
			if(choice){
				var post_num = ${customPost.post_num};
				$.ajax({
					type:'post',
					data:{post_num:post_num},
					url:'insertPostBlame.do',
					dataType:'json',
					cache:false,
					timeout:30000,
					success:function(data){
						if(data.result == 'success'){
							alert('정상적으로 신고가 접수 되었습니다');
						}else if(data.result == 'BlameFound'){
							alert('이미 신고하셨습니다.');
						} else{
							alert('신고 접수 오류 발생');
						}
					},
					error:function(){
						alert('네트워크 오류');
					}
				});
			}
		});
		
		//추천 갯수 읽기
		function getLike_count(){
			var post_num = ${customPost.post_num};
			$.ajax({
				type:'post',
				data:{post_num:post_num},
				url:'getPostLikeCount.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					$('#like_check').text(data.like_post_cnt);
				},
				error:function(){
					alert('네트워크 오류');
				}
			});
		}
		
		//추천 등록
		$(document).on('click','#like_check',function(event){
			var choice = window.confirm('이 글을 추천하시겠습니까?');
			if(choice){
				var post_num = ${customPost.post_num};
				$.ajax({
					type:'post',
					data:{post_num:post_num},
					url:'insertPostLike.do',
					dataType:'json',
					cache:false,
					timeout:30000,
					success:function(data){
						if(data.result == 'success'){
							getLike_count();
							alert('추천 되었습니다');
						} else if(data.result == 'LikeFound'){
							alert('이미 추천하셨습니다.');
							 var delete_choice = window.confirm('추천을 취소하시겠습니까?');
							 if(delete_choice){
								 var post_num = ${customPost.post_num};
								 $.ajax({
									 type:'post',
								 	 data:{post_num:post_num},
								 	 url:'deletePostLike.do',
									 dataType:'json',
									 cache:false,
									 timeout:30000,
									 success:function(data){
										 if(data.result == 'success'){
											 getLike_count();
											 alert('추천이 취소되었습니다');
										 }
									 }
								 });
							}
						}else if(data.result == 'SameID'){
							alert('자신의 글을 추천할 수 없습니다.');
						}else{
							alert('추천 등록 오류 발생');
						}
					},
					error:function(){
						alert('네트워크 오류');
					}
				});
			}
		});
		
		//초기 추천 갯수 읽기
		getLike_count();
		
		//즐겨찾기 총 개수 읽어오는 함수
		function getFav_count(){
			var post_num = ${customPost.post_num};
			$.ajax({
				type:'post',
				data:{post_num:post_num},
				url:'getFavCount.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					$('#fav_check').text(data.fav_cnt);
				},
				error:function(){
					alert('네트워크 오류');
				}
			});
		}
		
		//즐겨찾기 추가
		$(document).on('click','#fav_check',function(event){
			var choice = window.confirm('즐겨찾기에 추가하시겠습니까?');
			if(choice){
				var post_num = ${customPost.post_num};
				$.ajax({
					type:'post',
					data:{post_num:post_num},
					url:'insertFavorite.do',
					dataType:'json',
					cache:false,
					timeout:30000,
					success:function(data){
						if(data.result == 'success'){
							getFav_count();
							alert('즐겨찾기에 추가되었습니다');
						}else if(data.result == 'FavFound'){
							alert('이미 즐겨찾기에 등록하셨습니다');
							var delete_choice = window.confirm('즐겨찾기를 취소하시겠습니까?');
							 if(delete_choice){
								 var post_num = ${customPost.post_num};
								 $.ajax({
									 type:'post',
								 	 data:{post_num:post_num},
								 	 url:'deleteFavorite.do',
									 dataType:'json',
									 cache:false,
									 timeout:30000,
									 success:function(data){
										 if(data.result == 'success'){
											 getFav_count();
											 alert('즐겨찾기 목록에서 삭제되었습니다');
										 }
									 }
								 });
							 }
						}else{
							alert('즐겨찾기 추가 오류 발생');
						}
					},
					error:function(){
						alert('네트워크 오류');
					}
				});
			}
		});
		
		//초기 추천 갯수 읽기
		getFav_count();
		
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
						//댓글 목록 
						$(list).each(function(index,item){
							var output = '<div class="item">';
							if(item.anonymous == 1){ //댓글이 익명일 경우 - 프로필 사진은 고정
								if(item.post_mem_num == item.mem_num){ //게시글 작성자가 댓글 작성자와 동일할 때 - (글쓴이)표시
									output += '  <h4 style="color: #0ca5af;""><img src="${pageContext.request.contextPath}/resources/images/customBoard/profile0.png" width="30" height="30" class="picture large">  익명(글쓴이)</h4>';
								}
								else{
									output += '  <h4><img src="${pageContext.request.contextPath}/resources/images/customBoard/profile0.png" width="30" height="30" class="picture large">  익명</h4>';
								}
							}else if(item.anonymous == 0){ //댓글이 익명이 아닐 경우
								//게시글 작성자일 경우
								if(item.post_mem_num == item.mem_num){ 
									if(item.photoname == null){ //등록한 프로필 사진이 없을 경우
										output += '  <h4><img src="${pageContext.request.contextPath}/resources/images/customBoard/profile0.png" width="30" height="30" class="picture large">      ' + item.id + '</h4>';
									}
									else{ //프로필 사진이 있을 경우
										output += '  <h4 style="color: #0ca5af;""><img src="commentImageView.do?mem_num='+item.mem_num+'" width="30" height="30" class="picture large">      ' + item.id + '(글쓴이)</h4>';
									}
								}
								else{
									if(item.photoname == null){ //등록한 프로필 사진이 없을 경우
										output += '  <h4><img src="${pageContext.request.contextPath}/resources/images/customBoard/profile0.png" width="30" height="30" class="picture large">      ' + item.id + '</h4>';
									}
									else{ //프로필 사진이 있을 경우
										output += '  <h4><img src="commentImageView.do?mem_num='+item.mem_num+'" width="30" height="30" class="picture large">      ' + item.id + '</h4>';
									}
								}
							}
							output += '  <div class="sub-item">';
							//output += '    <p>' + item.re_content.replace(/\n/g,'<br>') + '</p>';
							output += '    <p>' + item.content.replace(/</gi,'&lt;').replace(/>/gi,'&gt;') + '</p>';
							output += '<span class="comment-date">'+item.reg_date+'</span>';
							
							
							if($('#mem_num').val()!=item.mem_num){
								//로그인 한 회원 번호가 댓글 작성자 번호와 다르면 
								output += '  <input type="button" data-num="'+item.comment_num+'" data-mem="'+item.mem_num+'" value="공감" class="like-btn">';
								output += '  <input type="button" data-num="'+item.comment_num+'" data-mem="'+item.mem_num+'" value="쪽지" class="message-btn" onclick="location.href=\'../message/sendMessage.do?anony='+item.anonymous+'&taregt_mem_num='+item.mem_num+'\'">';
								output += '  <input type="button" data-num="'+item.comment_num+'" data-mem="'+item.mem_num+'" value="신고" class="blame-btn">';
							}
							if($('#mem_num').val()==item.mem_num){
								//로그인 한 회원 번호가 댓글 작성자 번호와 같으면
								output += '  <input type="button" data-num="'+item.comment_num+'" data-mem="'+item.mem_num+'" value="공감" class="like-btn">';
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
						location.reload(); //해당 페이지 reload()
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
		
		//댓글 추천 등록
		$(document).on('click','.like-btn',function(){
			var result = confirm('해당 댓글을 추천하시겠습니까?');
			
			if(result) {
				
			}else{
				return false;
			}
			
			//댓글 번호
			var comment_num = $(this).attr('data-num');
			
			$.ajax({
				type:'post',
				data:{comment_num:comment_num},
				url:'insertCommentLike.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result == 'success'){
						//getCommentLike_count();  댓글에 달린 총 추천 수
						alert('댓글이 추천 되었습니다');
					} else if(data.result == 'LikeFound'){
						alert('이미 추천하셨습니다.');
						 var delete_choice = window.confirm('해당 댓글의 추천을 취소하시겠습니까?');
						 if(delete_choice){
							 $.ajax({
								 type:'post',
							 	 data:{comment_num:comment_num},
							 	 url:'deleteCommentLike.do',
								 dataType:'json',
								 cache:false,
								 timeout:30000,
								 success:function(data){
									 if(data.result == 'success'){
										 getLike_count();
										 alert('해당 댓글의 추천이 취소되었습니다');
									 }
								 }
							 });
						}
					}else if(data.result == 'SameID'){
						alert('자신이 작성한 댓글은 추천할 수 없습니다.');
					}else{
						alert('추천 등록 오류 발생');
					}
				},
				error:function(){
					alert('네트워크 오류');
				}
			});
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
					if(data.result == 'success'){
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
		
		//댓글 신고
		$(document).on('click','.blame-btn',function(){
			var result = confirm('해당 댓글을 신고하시겠습니까?');
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
				data:{comment_num:comment_num},
				url:'insertCommentBlame.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result == 'success'){
						alert('정상적으로 신고가 접수 되었습니다');
					}else if(data.result == 'BlameFound'){
						alert('이미 신고하셨습니다.');
					}else{
						alert('신고 접수 오류 발생');
					}
				},
				error:function(){
					alert('네트워크 오류');
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
			<ul class="status">
				<!-- 쪽지/신고 -->
				<c:if test="${customPost.mem_num != user.mem_num}">
					<li class="messagesend" onclick="location.href='../message/sendMessage.do?anony=${customPost.anonymous}&&taregt_mem_num=${customPost.mem_num}';">쪽지</li>
					<li class="blame">신고</li>
				</c:if>
				<!-- 수정/삭제 -->
				<c:if test="${user.mem_num == customPost.mem_num}">
					<li onclick="location.href='customPostModify.do?post_num=${customPost.post_num}&&board_num=${boardInfo.board_num}'">수정</li>
					<li id="delete_post_btn">삭제</li>
					<script type="text/javascript">
						var delete_post_btn = document.getElementById('delete_post_btn');
						//이벤트 연결
						delete_post_btn.onclick=function() {
							var choice = window.confirm('해당 게시글을 삭제하시겠습니까?');
							if (choice) {
								var commCount = ${commCount};
								if(commCount>0){ //댓글 있음
									var truncate = window.confirm('해당 게시글에 댓글이 존재합니다.\n정말로 게시글을 삭제하시겠습니까?');
									if(truncate){								
										location.href='customPostDeleteIncludeComm.do?post_num=${customPost.post_num}';
									}else{
										location.href='customPostDetail.do?post_num=${customPost.post_num}&&board_num=${customPost.board_num}';
									}
								}
								//댓글 없음
								else{ 
									location.href='customPostDelete.do?post_num=${customPost.post_num}';
								}
							}
						};
					</script>
				</c:if>
			</ul>
			
			<div class ="wrapstatus">
				<ul class="status">
						<li class="vote" id="like_check">0</li>
						<li class="comm">${customPost.comment_cnt}</li>
						<c:if test="${user.mem_num != customPost.mem_num}">
							<li class="fav" id="fav_check">0</li>
						</c:if>
				</ul>
			</div>
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
					<time class="large"><fmt:formatDate value="${customPost.reg_date}" pattern="MM/dd HH:mm"/></time>
				</div>
				
				<hr>
				<!-- 내용 -->		
				<p class="small">
					${customPost.content}
				</p>
				<div class="align-center">
					<c:if test="${!empty customPost.filename}"> <!-- filename이 있으면 image가 있는거 -->
						<img src="customPostImageView.do?post_num=${customPost.post_num}&&board_num=${boardInfo.board_num}" style="max-width:500px;"> <!-- 세션에 없기 때문에 get방식으로 넘겨줘야 함 -->
					</c:if>
				</div>
			</c:if>
		</a>
	</article>
</div>

<!-- 댓글 -->
<div id="comment_div">
	<span class="comment-title">댓글 달기</span>
	<form id="comment_form">
		<input type="hidden" name="post_num"
		       value="${customPost.post_num}" id="post_num"> <!-- 게시글 번호 -->
		<input type="hidden" name="mem_num"
		       value="${user.mem_num}" id="mem_num"> <!-- 로그인 한 회원 -->
		<textarea rows="3" cols="50" name="content" id="content" class="rep-content"></textarea>              
		<c:if test="${!empty user}">
		<div id="comment_first">
			<span class="letter-count">300/300</span>
		</div>
		<div id="comment_second" class="align-right">
			<c:if test="${boardInfo.anonymous == 1}"> <!-- 익명 허용 게시판 -->
				<input type="checkbox" name="anonymous" value="1" checked>익명
			</c:if>
			<c:if test="${boardInfo.anonymous == 0}"> <!-- 익명 미허용 게시판 -->
				<span id="anony_alert">* ${boardInfo.title}(은/는) 익명으로 작성할 수 없습니다 *</span>
			</c:if>
			<input type="submit" value="전송">
		</div>
		
		</c:if>
	</form>
</div>

<!-- 댓글 목록 -->
<div id="output"></div>
<div class="paging-button" style="display:none;">
	<input type="button" value="댓글 더 보기">
</div>
<div id="loading" style="display:none;">
	<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
</div>




