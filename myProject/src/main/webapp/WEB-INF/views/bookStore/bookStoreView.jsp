<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main-style">
	<h2>view</h2>
	<ul>
		<li>번호 : ${ bookStoreVO.bs_num }</li>
		<li>작성자 : ${ bookStoreVO.id }</li>
		<li>판매희망가 : ${ bookStoreVO.bs_selling_price }</li>
	</ul>
	<c:if test="${ !empty bookStoreVO.filename }">
	<div class="align-center">
		<img src="imageView.do?bs_num=${ bookStoreVO.bs_num }" style="max-width: 500px;">
	</div>
	</c:if>
	<input type="button" value="목록" onclick="location.href='bookStoreList.do'">
	<c:if test="${ !empty user && user.mem_num == bookStoreVO.mem_num }">
	<input type="button" value="삭제" id="delete_btn">
	<script type="text/javascript">
		var delete_btn = document.getElementById('delete_btn');
		
		delete_btn.onclick=function(){
			var choice = window.confirm('삭제하시겠습니까?');
			if(choice){
				location.href='delete.do?bs_num=${bookStoreVO.bs_num}';
			}
		};
	</script>
	</c:if>
</div>
