<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/customBoard.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<!-- 제목&소제목 -->
<h2 class="titleBoard">
	<a id="title" href="customPostList.do?board_num=${boardInfo.board_num}">${boardInfo.title}</a>
	<br>
	<span id="subtitle">${boardInfo.subtitle}</span>
	<c:if test="${boardInfo.anonymous == 0}"> <!-- 실명 게시판 -->	 
		<p id="anony_alert">* ${boardInfo.title}(은/는) 익명으로 작성할 수 없습니다 *</p>
	</c:if>
</h2>  

<form:form commandName="customPostVO" id="write_customform" action="customPostWrite.do" enctype="multipart/form-data">
	<input type="hidden" id="board_num" name="board_num" value="${boardInfo.board_num}"/>
	<input type="hidden" id="writer" name="writer" value="${user.id}"/>
	<!-- 작성내용 -->
	<p class="board_p">
		<form:textarea path="content" cols="100" rows="8" placeholder="글 내용을 입력하세요."/>
		<form:errors path="content" cssClass="error-color"/>
	</p>
	<div><img class="blah"/></div>
	<div>
		<!-- 첨부파일 -->
		<div class="write_bottom">
			<div class="write_bottom_btn">
				<label class="fileUpload">
					<input type="file" name="upload" id="upload" accept="image/gif,image/png,image/jpeg" onchange="readURL(this);">
				</label>
				<div class="align-right" style="float: right;">
					<input type="submit" class="submit" value="">
					<c:if test="${boardInfo.anonymous==1}"> <!-- 실명게시판 -->
						<input type="checkbox" name="anonymous" value="1" id="anonymous" checked="checked">
						<label for="anonymous">
							<span class="anonymousSpan">익명</span>
						</label>
					</c:if>
					<c:if test="${boardInfo.anonymous==0}"> <!-- 익명게시판 -->
						<input type="checkbox" name="anonymous" value="0" id="anonymous" checked="checked" style="display:none;">
					</c:if>
				</div>
			</div>
		</div>
	</div>
</form:form>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
function readURL(input) {//썸네일 미리보기
    if (input.files && input.files[0]) {
       var reader = new FileReader();
       reader.onload = function(e) {
          $('.blah').attr('src', e.target.result)
          .css("width", "100")
          .css("height", "100")
          .css("object-fit", "contain")
          .css("border", "1px solid #e3e3e3")
          .css("margin-top", 15)
          .css("margin-bottom", 15);
       }
       reader.readAsDataURL(input.files[0]);
    }
 }
</script>
