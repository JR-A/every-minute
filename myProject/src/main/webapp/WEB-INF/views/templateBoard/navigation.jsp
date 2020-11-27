<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<div id="submenu">
	<div class="wrap">
		<div class="group">
			<ul>
				<li><a href="${pageContext.request.contextPath}/freeBoard/freeBoardList.do" class="new">자유게시판</a></li>
				<span>|</span>
				<li><a href="${pageContext.request.contextPath}/infoBoard/infoBoardList.do" class="new">정보게시판</a></li>
				<span>|</span>
				
				<c:if test="${!empty customBoardlist}">
					<c:forEach var="customBoard" items="${customBoardlist}">
						<li><a href="${pageContext.request.contextPath}/customBoard/customPostList.do?board_num=${customBoard.board_num}" class="new">
							<c:out value="${customBoard.board_num}"/>
							<c:out value="${customBoard.title}"/>
						</a></li>
						<span>|</span>
					</c:forEach>
				</c:if>
				
				
				
				
				
				<li><a href="${pageContext.request.contextPath}/customBoard/createCustomBoard.do" class="new">게시판 생성</a></li>
				<!-- ======================= 메모 ======================= -->
				<!-- 사용자 정의로 추가되는 커스텀 게시판의 경우 팀원 회의 후 HTML태그 작성  -->
				<!-- ======================= 메모 ======================= -->
				
			</ul>
		</div>
	</div>
</div>