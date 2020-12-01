<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-main-style">
	<input type="hidden" id="isbn" value="${ bookStoreVO.isbn }">
	<table>
			<tr>
				<td id="thumbnail" width="100"></td>
				<td>
					<p id="title"></p>
					<p id="authors"></p>
					<p id="publisher"></p>
					<p id="price"></p>
				</td>
			</tr>
		</table>
	<ul>
		<li>작성자 : ${ bookStoreVO.id }</li>
		<li>판매희망가 : ${ bookStoreVO.bs_selling_price }</li>
		<li>책 상태 : ${ bookStoreVO.bs_condition }</li>
		<li>거래 방법 : ${ bookStoreVO.bs_method }</li>
		<li>간략한 내용 : ${ bookStoreVO.bs_comment }</li>
	</ul>
	<c:if test="${ !empty bookStoreVO.bs_address }">
	<div>직거래 장소</div>
	<div id="map" style="width:100%;height:300px; margin: 10px auto;"></div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=52fbe82671d809b72a435af5d996bef6&libraries=services"></script>
	<script type="text/javascript">
		var mapContainer = document.getElementById('map'),
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667),
		        level: 3
		    };  
		
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		var geocoder = new kakao.maps.services.Geocoder();
		
		geocoder.addressSearch('${bookStoreVO.bs_address}', function(result, status) {
		
		     if (status === kakao.maps.services.Status.OK) {
		
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });
		
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">직거래 장소</div>'
		        });
		        infowindow.open(map, marker);
		
		        map.setCenter(coords);
		    } 
		});    
	</script>
	</c:if>
	<c:if test="${ !empty bookStoreVO.filename }">
	<div>책 사진</div>
	<div class="align-center">
		<img src="imageView.do?bs_num=${ bookStoreVO.bs_num }" style="max-width: 970px;">
	</div>
	</c:if>
	<input type="button" value="목록" onclick="location.href='bookStoreList.do'">
	<c:if test="${ !empty user && user.mem_num == bookStoreVO.mem_num }">
	<input type="button" value="수정" onclick="location.href='bookStoreUpdate.do?bs_num=${bookStoreVO.bs_num}'">
	<input type="button" value="삭제" id="delete_btn">
	<script type="text/javascript">
		var delete_btn = document.getElementById('delete_btn');
		
		delete_btn.onclick=function(){
			var choice = window.confirm('삭제하시겠습니까?');
			if(choice){
				location.href='bookStoreDelete.do?bs_num=${bookStoreVO.bs_num}';
			}
		};
	</script>
	</c:if>
</div>
<script src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script>
	$(document).ready(function(){
		var data = $("#isbn").val();
		var result = data.split(" ");
		
		$.ajax({
			method : "GET",
			url : "https://dapi.kakao.com/v3/search/book?target=title,isbn",
			data : { query: result[0] },
			headers: { Authorization: "KakaoAK 01be56c57f3e1447f4b6d6cad08f3f3b" }
		}).done(function(msg){
			console.log(msg);
			$("#thumbnail").append("<img src='" + msg.documents[0].thumbnail + "'/>")
			$("#title").append(msg.documents[0].title);
			$("#authors").append(msg.documents[0].authors);
			$("#publisher").append(msg.documents[0].publisher);
			$("#price").append(msg.documents[0].price);
		});
	});
</script>