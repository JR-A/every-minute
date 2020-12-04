<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     



<div id="submenu">
	<div class="wrap">
		<div class="group">
			<ul>			
				<span>|</span>
				<c:if test="${!empty customBoardlist}">
					<c:forEach var="customBoard" items="${customBoardlist}">
						<li><a href="${pageContext.request.contextPath}/member/customBoardWritedlist.do?board_num=${customBoard.board_num}" class="new">
							<c:out value="${customBoard.board_num}"/>
							<c:out value="${customBoard.title}"/>
						</a></li>							
						<span>|</span>
					</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>
</div>