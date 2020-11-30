<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main-style">
	<form:form commandName="bookStoreVO" action="bookStoreWrite.do" enctype="multipart/form-data">
		<form:hidden path="bs_num"/>
		<ul>
			<li>
				<form:label path="bs_selling_price">판매희망가</form:label>
				<form:input path="bs_selling_price"/>
			</li>
			<li>
				<form:label path="bs_comment">간략한 내용</form:label>
				<form:input path="bs_comment"/>
			</li>
			<li>
				<form:label path="bs_condition">책 상태</form:label>
				<form:input path="bs_condition"/>
			</li>
			<li>
				<form:label path="bs_method">거래 방법</form:label>
				<form:input path="bs_method"/>
				<%-- <ul>
					<li><form:checkbox path="bs_method" value="직거래" label="직거래"/></li>
					<li><form:checkbox path="bs_method" value="택배" label="택배"/></li>
				</ul> --%>
			</li>
			<li>
				<form:label path="bs_address">직거래 장소</form:label>
				<form:input path="bs_address"/>
				<input type="button" onclick="searchMap()" value="주소 검색"><br>
				<div id="map" style="width: 410px; height: 300px; margin-top: 10px;"></div>
			</li>
			<li>
				<label for="upload">책 사진</label>
				<input type="file" id="upload" name="upload" accept="image/gif,image/png,image/jpeg">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" id="" value="등록">
			<input type="button" value="목록" onclick="location.href='bookStoreList.do'">
		</div>
	</form:form>
</div>
<script src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=52fbe82671d809b72a435af5d996bef6&libraries=services"></script>
<script type="text/javascript">
	
</script>
<script type="text/javascript">
	var mapContainer = document.getElementById('map'),
		mapOption = {
		    center: new daum.maps.LatLng(37.537187, 127.005476),
		    level: 5
		};
		
		var map = new daum.maps.Map(mapContainer, mapOption);
		var geocoder = new daum.maps.services.Geocoder();
		var marker = new daum.maps.Marker({
		position: new daum.maps.LatLng(37.537187, 127.005476),
		map: map
		});
		
		function searchMap() {
		new daum.Postcode({
		    oncomplete: function(data) {
		        var addr = data.address;
		
		        document.getElementById("bs_address").value = addr;
		        geocoder.addressSearch(data.address, function(results, status) {
		            if (status === daum.maps.services.Status.OK) {
		
		                var result = results[0];
		
		                var coords = new daum.maps.LatLng(result.y, result.x);
		                map.setCenter(coords);
		                marker.setPosition(coords)
		            }
		        });
		    }
		}).open();
	}
</script>