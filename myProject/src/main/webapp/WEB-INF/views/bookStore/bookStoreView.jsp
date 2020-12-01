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
	<c:if test="${ !empty bookStoreVO.bs_address }">
	<div id="map" style="width:410px;height:300px; margin: 10px auto;"></div>
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
	<div class="align-center">
		<img src="imageView.do?bs_num=${ bookStoreVO.bs_num }" style="max-width: 500px;">
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
