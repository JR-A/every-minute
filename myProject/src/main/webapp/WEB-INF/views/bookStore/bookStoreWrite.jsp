<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bookStoreWrite.css">
<div class="page-main-style">
	<form>
		<input type="hidden" id="searchIsbn" value="${ isbn }">
		<table>
			<tr>
				<td id="thumbnail" width="100"></td>
				<td>
					<h2 id="title"></h2>
					<p id="authors"><span class="authors_title">저자</span><br></p>
					<p id="publisher"><span class="publisher_title">출판사</span><br></p>
					<h3 id="price"></h3>
				</td>
			</tr>
		</table>
	</form>
	<form:form commandName="bookStoreVO" action="bookStoreWrite.do" enctype="multipart/form-data">
		<form:hidden path="bs_num"/>
		<ul>
			<li class="list">
				<form:label path="bs_selling_price">판매희망가</form:label>
				<input type="text" id="bs_selling_price" name="bs_selling_price" placeholder="예) 15000">
			</li>
			<li class="list">
				<form:label path="bs_comment">간략한 내용</form:label>
				<input type="text" id="bs-comment" name="bs_comment" placeholder="예) 필기량 많음, 저녁 6시 이후 거래 가능">
			</li>
			<li class="list">
				<form:label path="bs_condition">책 상태</form:label>
				<form:radiobutton path="bs_condition" value="하" label="하"/>
				<form:radiobutton path="bs_condition" value="중" label="중"/>
				<form:radiobutton path="bs_condition" value="상" label="상"/>
			</li>
			<li class="list">
				<form:label path="bs_method">거래 방법</form:label>
				<form:checkbox path="bs_method" value="직거래" label="직거래"/>
				<form:checkbox path="bs_method" value="택배" label="택배"/>
			</li>
			<li class="list" id="map_data">
				<form:label path="bs_address">직거래 장소</form:label>
				<div class="search_map">
					<input type="text" name="bs_address" id="bs_address" placeholder="주소">
					<input class="map_btn" type="button" onclick="searchMap()" value="주소 검색">
				</div>
				<div id="map"></div>
			</li>
			<li class="list">
				<label for="upload">책 사진</label>
				<input type="file" id="upload" name="upload" accept="image/gif,image/png,image/jpeg">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" id="submit_btn" value="등록">
			<input type="button" class="back_btn" value="목록" onclick="location.href='bookStoreList.do'">
		</div>
	</form:form>
</div>
<script src="${ pageContext.request.contextPath }/resources/js/jquery-3.5.1.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=52fbe82671d809b72a435af5d996bef6&libraries=services"></script>
<script>
	$(document).ready(function(){
		var data = $("#searchIsbn").val();
		var result = data.split(" ");
		
		$.ajax({
			method : "GET",
			url : "https://dapi.kakao.com/v3/search/book?target=isbn",
			data : { query: result[1] },
			headers: { Authorization: "KakaoAK 01be56c57f3e1447f4b6d6cad08f3f3b" }
		}).done(function(msg){
			console.log(msg);
			$("#thumbnail").append("<img src='" + msg.documents[0].thumbnail + "'/>")
			$("#title").append(msg.documents[0].title);
			$("#authors").append(msg.documents[0].authors);
			$("#publisher").append(msg.documents[0].publisher);
			$("#price").append(msg.documents[0].price + "원");
			$("#bookStoreVO").append("<input type='hidden' name='isbn' value='" + result[1] + "'>");
		});
		
		$("#submit_btn").on('click', function(){
			var methodArr = [];
			
			$("input[name=bs_method]:checked").each(function(){
				methodArr.push($(this).val());
			});
			
			if($("#bs_selling_price").val()==""){
				alert("판매희망가를 적어주세요.");
				$("#bs_selling_price").focus();
				return false;
			}
			
			if($("input[name=bs_condition]:radio:checked").length<1){
				alert("책 상태를 선택하세요.");
				return false;
			}
			
			if(!$("input[name=bs_method]").is(":checked")){
				alert("거래 방법을 선택하세요.");
				return false;
			}
			
			if($("#bs_method1").is(":checked")){
				if($("#bs_address").val()==""){
					alert("주소를 검색해주세요");
					$("#bs_address").focus();
					return false;
				}
			}
			
			var choice = window.confirm("게시글 등록 후 판매 여부 외에는 수정이 불가합니다.\n그래도 등록하시겠습니까?");
			if(choice){
				return true;
			}else{
				return false;
			}
		});
		
		$("#map_data").css("display", "none");
		$("#bs_method1").change(function(){
			if($("#bs_method1").is(":checked")){
				$("#map_data").css("display", "block");
			}else{
				$("#map_data").css("display", "none");
			}
		});
	});
</script>
<script>
	var mapContainer = document.getElementById('map'),
		mapOption = {
		    center: new daum.maps.LatLng(37.537187, 127.005476),
		    level: 3
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
