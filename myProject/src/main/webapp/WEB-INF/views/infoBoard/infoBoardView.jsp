<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>																			
    																			
<h2 class="title">																			
	<a href="infoBoardList.do">정보게시판</a>																		
</h2>																			
<div class="page-main-style-detail">																			
	<article>																		
		<a class="article2">																	
			<c:if test="${empty user.photoname}">																
				<img src="https://cf-fpi.everytime.kr/0.png" width="100" height="100" class="picture large">															
			</c:if>																
			<c:if test="${!empty user.photoname}">																
				<c:if test="${0 eq board.anonymous}">															
				<img src="${pageContext.request.contextPath}/member/photoView.do" width="100" height="100" class="picture large">															
				</c:if>															
				<c:if test="${1 eq board.anonymous}">															
				<img src="https://cf-fpi.everytime.kr/0.png" width="100" height="100" class="picture large">															
				</c:if>															
			</c:if>																
			<div class="profile">																
				 <c:if test="${0 eq board.anonymous}">															
                 <h3 class="large"> ${board.id}</h3>																			
	            </c:if>																		
	            <c:if test="${1 eq board.anonymous}">																		
	             <h3 class="large">익명</h3>																		
	            </c:if>																		
				<time class="large">${board.modify_date}</time>															
			</div>
			<!-- 내 글은 쪽지보내기와 신고하기가 보이지 않음 -->
			<!--     			┌로그인됨		 ┌로그인아이디		┌작성자아이디  -->											
			<c:if test="${!empty user && user.mem_num != board.mem_num}">																		
					<ul class="status">																
						<li class="messagesend" data-modal="messageSend" data-article-id="76626841" data-is-anonym="0">쪽지</li>															
						<li class="abuse" id="btnOK">신고</li>															
					</ul>																															
			</c:if>														
			<div class="align-right">																			
			<!-- 수정 삭제의 경우는 로그인이 되어있고 로그인한 회원번호와 작성자 회원번호가 일치해야함 -->																		
			<!--     			┌로그인됨		 ┌로그인아이디		┌작성자아이디  -->											
			<c:if test="${!empty user && user.mem_num == board.mem_num}">																		
				<input type="button" value="수정" onclick="location.href='update.do?post_num=${board.post_num}'">																	
				<input type="button" value="삭제" id="delete_btn">
				<script type="text/javascript">
					var delete_btn = document.getElementById('delete_btn');																
					//이벤트 연결
					delete_btn.onclick=function() {
						var choice = window.confirm('정말 삭제하시겠습니까?');
						if (choice) {															
							location.href='delete.do?post_num=${board.post_num}';														
						}															
					}																
				</script>																	
			</c:if>	
			</div>															
			<hr>																
			<h1>${board.title}</h1>											
			<p>																
				${board.content}
				<c:if test="${!empty board.filename}"> <!-- filename이 비어있지 않는다면 아래의 div를 실행 -->																
					<div class="align-center">															
						<img src="imageView.do?post_num=${board.post_num}" style="max-width: 500px;">														
					</div>															
				</c:if>																
			</p>		
			<div style="text-align: right;">
				<ul class="status">
					<li class="attach"></li>
					<li title="공감" class="vote" id="post_like">${likeCount}</li>
					<li title="댓글" class="comment">${board.reply_cnt}</li>
				</ul>														
			</div>															
			<div class="pointer">																
				<c:if test="${board.tag_num == 1}"> <!-- filename이 비어있지 않는다면 아래의 div를 실행 -->																
					<div><span onclick="Checkbox1()" class="1">도서관</span></div>																
				</c:if>																
				<c:if test="${board.tag_num == 2}"> <!-- filename이 비어있지 않는다면 아래의 div를 실행 -->																
					<div><span onclick="Checkbox2()" class="2">셔틀버스</span></div>																
				</c:if>																
				<c:if test="${board.tag_num == 3}"> <!-- filename이 비어있지 않는다면 아래의 div를 실행 -->																
					<div><span onclick="Checkbox3()" class="3">와이파이</span></div>																
				</c:if>																
				<c:if test="${board.tag_num == 4}"> <!-- filename이 비어있지 않는다면 아래의 div를 실행 -->																
					<div><span onclick="Checkbox4()" class="4">계절학기</span></div>																
				</c:if>																
				<c:if test="${board.tag_num == 5}"> <!-- filename이 비어있지 않는다면 아래의 div를 실행 -->																
					<div><span onclick="Checkbox5()" class="5">학점·성적</span></div>																
				</c:if>																
				<c:if test="${board.tag_num == 6}"> <!-- filename이 비어있지 않는다면 아래의 div를 실행 -->																
					<div><span onclick="Checkbox6()" class="6">기숙사</span></div>																
				</c:if>																
				<c:if test="${board.tag_num == 7}"> <!-- filename이 비어있지 않는다면 아래의 div를 실행 -->																
					<div><span onclick="Checkbox7()" class="7">장학금</span></div>																
				</c:if>																
				<c:if test="${board.tag_num == 8}"> <!-- filename이 비어있지 않는다면 아래의 div를 실행 -->																
					<div><span onclick="Checkbox8()" class="8">수강신청</span></div>																
				</c:if>																
				<c:if test="${board.tag_num == 9}"> <!-- filename이 비어있지 않는다면 아래의 div를 실행 -->																
					<div><span onclick="Checkbox9()" class="9">휴복학</span></div>																
				</c:if>																
				<c:if test="${board.tag_num == 10}"> <!-- filename이 비어있지 않는다면 아래의 div를 실행 -->																
					<div><span onclick="Checkbox10()" class="10">졸업</span></div>																
				</c:if>																
				<c:if test="${board.tag_num == 11}"> <!-- filename이 비어있지 않는다면 아래의 div를 실행 -->																
					<div><span onclick="Checkbox11()" class="11">등록금</span></div>																
				</c:if>																
				<c:if test="${board.tag_num == 12}"> <!-- filename이 비어있지 않는다면 아래의 div를 실행 -->																
					<div><span onclick="Checkbox12()" class="12">학생증</span></div>																
				</c:if>																
			</div>																
		</a>																																	
	</article>																		
</div>																									
	<!-- 댓글 목록 출력 -->																		
	<div id="output"></div>																		
	<div class="paging-button" style="display:none;">																		
		<input type="button" value="다음글 보기">																	
	</div>																		
	<div id="loading" style="display:none;">																		
		<img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">																	
	</div>
	<!-- 댓글 -->																		
	<div class="reply_init">																		
		<div id="reply_div">															
			<form id="re_form">																
				<input type="hidden" name="post_num"															
				       value="${board.post_num}" id="post_num">															
				<input type="hidden" name="mem_num"															
				       value="${user.mem_num}" id="mem_num"><!-- 수정 -->															
				<input type="text"
			  name="content" id="content"
			  class="rep-content" placeholder="댓글은 최대 300자까지 작성 가능합니다."
			  <c:if test="${empty user}">disabled="disabled"</c:if>
			><c:if test="${empty user}">로그인해야 작성할 수 있습니다.</c:if></input>
			<c:if test="${!empty user}">													
					<div id="re_second" class="align-right">													
					    <input type="checkbox" name="anonymous" value="1" id="anonymous" checked="checked">
					    <label for="anonymous">
			  				<span class="anonymousSpan">익명</span>
						</label>
						<input type="submit" class="submit" value="">													
					</div>															
				</c:if>															
			</form>																
		</div>
	</div>																																				
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/infoBoard.js"></script>		
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/infoboard_comment.js"></script>																	
<script>
//========================================================신고 작업 시작==============================																			
// 버튼 클릭시 ajax 실행  		

$("#btnOK").click(function(){  																			
																			
    var post_num="${board.post_num}";  																			
    var board_mem_num="${board.mem_num}";  																			
    var target_mem_num="${user.mem_num}";  																			
    alert('정말 신고하시겠습니까?');																			
    $.ajax({      																			
        type:'post',																			
		data:{																	
            post_num:post_num,																			
            board_mem_num:board_mem_num,																			
            target_mem_num:target_mem_num																			
            },																			
		url:'post_blame.do',																	
		dataType:'json',																	
		cache:false,																	
		timeout:30000,																	
		success:function(data){																	
			if(data.result=='success'){																
				alert('신고 성공');															
			}else{																
				alert('신고 오류 발생!');															
			}																
		},																	
		error:function(){																	
			alert('네트워크 오류!');																
		}																	
    });  																			
      																			
});														
//======================================================== 신고 작업 끝 ==============================

//======================================================== 추천 작업 시작 ==============================


	//댓글 추천 등록
	$(document).on('click','#info_like_cntR',function(event){
		var choice = window.confirm('이 댓글을 추천하시겠습니까?!!');
		if(choice){
			var comment_num = $(this).attr('data-like');
			$.ajax({
				type:'post',
				data:{comment_num:comment_num},
				url:'insertReplyLike.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result == 'success'){
						alert('추천 되었습니다');
						selectData(1,$('#post_num').val());
					}else if(data.result == 'LikeFound'){
						alert('이미 추천하셨습니다.');
					}else if(data.result == 'logout'){
						alert('로그인해야 사용할 수 있습니다.');
					}else if(data.result == 'SameID'){
						alert('자신의 글에는 추천 할 수 없습니다.');
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
	//추천 등록
	$(document).on('click','#post_like',function(event){
		var choice = window.confirm('이 글을 추천하시겠습니까?');
		if(choice){
			var post_num = ${board.post_num};
			$.ajax({
				type:'post',
				data:{post_num:post_num},
				url:'view_like.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){
					if(data.result == 'success'){
						alert('추천 되었습니다');
						location.reload();	
					}else if(data.result == 'LikeFound'){
						alert('이미 추천하셨습니다.');
					}else if(data.result == 'logout'){
						alert('로그인해야 사용할 수 있습니다.');
					}else if(data.result == 'SameID'){
						alert('자신의 글에는 추천 할 수 없습니다.');
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
//======================================================== 추천 작업 끝 ==============================

</script>
