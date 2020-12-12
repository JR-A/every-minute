<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var currentPage;
	var count;
	var rowCount; 
	
	//댓글 목록
	function selectData(pageNum){
		currentPage = pageNum;
		
		if(pageNum == 1){
			//처음 호출시는 해당 ID의 div의 내부 내용물을 제거
			$('#output').empty();
		}
		
		$.ajax({
			type:'post',
			data:{pageNum:pageNum},
			url:'writedInfoBoardCommentlist.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
				
				if(count < 0 || list == null){
					alert('목록 호출 오류 발생!');
				}else{
					//댓글 목록 작업
					$(list).each(function(index,item){
						var output = '<div class="item">';
						output+='<h2><a href="${pageContext.request.contextPath}/infoBoard/detail.do?post_num='+item.post_num+'">'+item.title+'</a></h2>';
						if(item.anonymous == 1){
							output += '  <h4><img src="https://cf-fpi.everytime.kr/0.png" width="30" height="30" class="picture large">익명</h4>';
						}else if(item.anonymous == 0){
							output += '  <h4><img src="${pageContext.request.contextPath}/freeBoard/replayImageView.do?mem_num='+item.mem_num+'" width="30" height="30" class="picture large">' + item.id + '</h4>';
						}
						output += '  <div class="sub-item">';
						//output += '    <p>' + item.re_content.replace(/\n/g,'<br>') + '</p>';
						output += '    <p>' + item.content.replace(/</gi,'&lt;').replace(/>/gi,'&gt;') + '</p>';
						output += '<span class="reply-date">'+item.reg_date+'</span>';
						output += '  </div>';
						output += '</div>';
												
						//문서 객체에 추가
						$('#output').append(output);
					
					});
				}
				
			},
			error:function(){
				//로딩 이미지 감추기
				$('#loading').hide();
				alert('네트워크 오류');
			}
		});
	}
	
	//page_num 으로 글목록호출
	selectData($('#page_num').val());
});

</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input id="page_num"type="hidden" value="${page_num}">
	<!-- 댓글 목록 출력 -->
	<div id="output"></div>
	<div class="align-center">${pagingHtml}</div>
</body>
</html>