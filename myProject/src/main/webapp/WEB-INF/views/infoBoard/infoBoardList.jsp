<%@ page language="java" contentType="text/html; charset=UTF-8"							
    pageEncoding="UTF-8"%>							
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>							
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 							
							
<h2 class="title">							
	<a href="infoBoardList.do">정보게시판</a>						
</h2>							
<div class="page-main-style-info">							
	<div class="pointer">						
		<div><span onclick="oneCheckbox1()" class="1">도서관</span></div>					
		<div><span onclick="oneCheckbox2()" class="2">셔틀버스</span></div>					
		<div><span onclick="oneCheckbox3()" class="3">와이파이</span></div>					
		<div><span onclick="oneCheckbox4()" class="4">계절학기</span></div>					
		<div><span onclick="oneCheckbox5()" class="5">학점·성적</span></div>					
		<div><span onclick="oneCheckbox6()" class="6">기숙사</span></div>					
		<div><span onclick="oneCheckbox7()" class="7">장학금</span></div>					
		<div><span onclick="oneCheckbox8()" class="8">수강신청</span></div>					
		<div><span onclick="oneCheckbox9()" class="9">휴복학</span></div>					
		<div><span onclick="oneCheckbox10()" class="10">졸업</span></div>					
		<div><span onclick="oneCheckbox11()" class="11">등록금</span></div>					
		<div><span onclick="oneCheckbox12()" class="12">학생증</span></div>					
	</div>						
	<form action="infoBoardList.do" id="search_form" method="get"> <!-- get방식으로 http의 post_num= 을 넘겨줌 -->						
		<ul class="search_info">					
			<li>				
				<select name="keyfield" id="keyfield">			
					<option value="all">전체</option>		
					<option value="tag">태그</option>		
					<option value="title">글 제목</option>		
					<option value="content">글 내용</option>		
				</select>			
			</li>				
			<li>				
				<input type="text" name="keyword" id="keyword" placeholder="검색어를 입력하세요.">			
			</li>				
			<li>				
				<input type="submit" value="" id="submit">			
			</li>				
		</ul>					
		<input type="button" value="새 글을 작성해주세요!" onclick="location.href='write.do'" id="writeBtn">					
	</form>						
							
	<c:if test="${count == 0}">						
	<div class="align-center">등록된 게시글이 없습니다.</div>						
	</c:if>						
	<c:if test="${count > 0}">						
		<div id="wrap">						
			<c:forEach var="board" items="${list}">				
				<article class="board_view">			
					<a href="detail.do?post_num=${board.post_num}">		
						<div class="article">
							<h2>${board.title}</h2>
							<p class="small">${board.content}</p>
							<time class="small">${board.modify_date}</time>
							<c:if test="${0 eq board.anonymous}">${board.id}</c:if>			
					        <c:if test="${1 eq board.anonymous}">익명</c:if>		
					        <div style="float: right;">
								<ul class="status">
									<li class="attach"></li>
									<li title="공감" class="vote">${board.like_cnt}</li>
									<li title="댓글" class="comment">${board.reply_cnt}</li>
								</ul>
							</div>	
						</div>	
					</a>
				</article>			
			</c:forEach>				
		</div>				
		<div class="align-center" id="pagenation">${pagingHtml}</div>					
	</c:if>						
</div>							
							
							
							
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/infoBoard.js"></script>								
<script type="text/javascript">						
	$(function() {						
		var sBtn = $(".pointer > div"); //  ul > li 이를 sBtn으로 칭한다. (클릭이벤트는 li에 적용 된다.)					
		sBtn.find("span").click(function() { // sBtn에 속해 있는  a 찾아 클릭 하면.					
			sBtn.removeClass("active"); // sBtn 속에 (active) 클래스를 삭제 한다.				
			$(this).parent().addClass("active"); // 클릭한 a에 (active)클래스를 넣는다.				
		})					
	})						
</script>							