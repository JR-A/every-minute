<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/freeBoard.css">
<script src="<c:url value="/resources/js/jquery-3.5.1.min.js" />"></script>

<script type="text/javascript">
	$().ready(function () {
		   var $container = $('.page-main-style');
		   
		$container.on('change', '.search > li > select[name="keyfield"]', function () {
		   var $form = $container.find('.search');
		   var $keyword = $form.find('input[name="keyword"]');
		   if ($(this).val() === 'tag') {
		      $keyword.attr('placeholder', '#에브리미닛');
		   } else {
		      $keyword.attr('placeholder', '검색어를 입력하세요.');
		   }
		   $keyword.val('');
		   });
		});
	</script>


<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/freeBoard.css">

	<div class="page-main-style">
	<h2 class="title"><a href="${pageContext.request.contextPath}/freeBoard/freeBoardList.do">자유게시판</a></h2>
 	<form action="freeBoardList.do" id="search_form" method="get">
		<ul class="search">
			<!-- 검색시 1부터3까지 select 기능 -->
			<li><select name="keyfield" id="keyfield">
					<option value="all">전체</option>
					<option value="tag">해시태그</option>
					<option value="title">글 제목</option>
					<option value="content">글 내용</option>
					
			</select></li>



			<!-- 검색 form -->
			<li><input type="text" name="keyword" id="keyword"></li>
			<li><input type="submit" value="찾기"></li>
			<li><input type="button" value="글쓰기" onclick="location.href='freeBoardWrite.do'"></li>
		</ul>
	
	</form>
	<c:if test="${count ==0}">
		<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>

	<c:if test="${count > 0}">
			<c:forEach var="freeboard" items="${list}">
				<article class="free_post">
					<a href="detail.do?post_num=${freeboard.post_num}">
						<h2><a href="detail.do?post_num=${freeboard.post_num}">${freeboard.title}</a></h2>
						<p class="small">${freeboard.content}</p>
						<h2 class= "small">${freeboard.modify_date}</h2>

				<time class="111">
				<c:if test="${0 eq freeboard.anonymous}">
						${freeboard.id}
				</c:if>
				<c:if test="${1 eq freeboard.anonymous}">
						익명
				</c:if>
				</time>
				
				
					</a>
		<div class ="wrapstatus">
			<ul class="status">
					<li class="vote" id="like_check">${freeboard.like_cnt}</li>
					<li class="comm">${freeboard.reply_cnt}</li>
			</ul>
		</div>
		
			
			</article>
			</c:forEach>
			
	

		<div class="align-center">${pagingHtml}</div>

	</c:if>


</div>
