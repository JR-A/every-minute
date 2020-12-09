																				
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
																				
	//#넣기																			
    function insertText(){																				
    	 var txtArea = document.getElementById('txtForm');																			
    	 var txtValue = txtArea.value;																			
    	 var selectPos = txtArea.selectionStart; // 커서 위치 지정																			
    	 var beforeTxt = txtValue.substring(0, selectPos);  // 기존텍스트 ~ 커서시작점 까지의 문자																			
    	 var afterTxt = txtValue.substring(txtArea.selectionEnd, txtValue.length);   // 커서끝지점 ~ 기존텍스트 까지의 문자																			
    	 var addTxt = document.getElementById('addInput').value; // 추가 입력 할 텍스트																			
																				
    	 txtArea.value = beforeTxt + addTxt + afterTxt;																			
																				
    	 selectPos = selectPos + addTxt.length;																			
    	 txtArea.selectionStart = selectPos; // 커서 시작점을 추가 삽입된 텍스트 이후로 지정																			
    	 txtArea.selectionEnd = selectPos; // 커서 끝지점을 추가 삽입된 텍스트 이후로 지정																			
    	 txtForm.focus();																			
    }																				
																				
    //체크박스 하나만 선택																				
	function oneCheckbox(a){																			
	    var obj = document.getElementsByClassName("oneCheck");																			
	    for(var i=0; i<obj.length; i++){
	        if(obj[i] != a){																			
	            obj[i].checked = false;
	        }																			
	    }																			
	}
    $(function(){
        var sBtn = $("ul > li");    //  ul > li 이를 sBtn으로 칭한다. (클릭이벤트는 li에 적용 된다.)
        sBtn.find("a").click(function(){   // sBtn에 속해 있는  a 찾아 클릭 하면.
         sBtn.removeClass("active");     // sBtn 속에 (active) 클래스를 삭제 한다.
         $(this).parent().addClass("active"); // 클릭한 a에 (active)클래스를 넣는다.
        })
       })
	$().ready(function () {																			
		   var $container = $('.page-main-style-info');																		
		   																		
		$container.on('change', '.search_info > li > select[name="keyfield"]', function () {																		
		   var $form = $container.find('.search_info');																		
		   var $keyword = $form.find('input[name="keyword"]');																		
		   if ($(this).val() === 'tag') {																		
		      $keyword.attr('placeholder', '#에브리타임');																		
		   } else {																		
		      $keyword.attr('placeholder', '검색어를 입력하세요.');																		
		   }																		
		   $keyword.val('');																		
		   });																		
		});																		
	//========================================================infoBoardList 태그선택 ===================																			
	function oneCheckbox1(){																			
	    var tnum = document.getElementsByClassName("1");																			
	    var tnum = 1;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터 벨류,변수																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('#wrap').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.modify_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('#wrap').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function oneCheckbox2(){																			
	    var tnum = document.getElementsByClassName("2");																			
	    var tnum = 2;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			//		 ┌key  ┌변수															
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('#wrap').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('#wrap').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function oneCheckbox3(){																			
	    var tnum = document.getElementsByClassName("3");																			
	    var tnum = 3;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('#wrap').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('#wrap').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function oneCheckbox4(){																			
	    var tnum = document.getElementsByClassName("4");																			
	    var tnum = 4;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('#wrap').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('#wrap').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function oneCheckbox5(){																			
	    var tnum = document.getElementsByClassName("5");																			
	    var tnum = 5;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('#wrap').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('#wrap').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function oneCheckbox6(){																			
	    var tnum = document.getElementsByClassName("6");																			
	    var tnum = 6;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('#wrap').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('#wrap').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function oneCheckbox7(){																			
	    var tnum = document.getElementsByClassName("7");																			
	    var tnum = 7;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('#wrap').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('#wrap').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function oneCheckbox8(){																			
	    var tnum = document.getElementsByClassName("8");																			
	    var tnum = 8;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('#wrap').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('#wrap').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function oneCheckbox9(){																			
	    var tnum = document.getElementsByClassName("9");																			
	    var tnum = 9;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('#wrap').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('#wrap').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function oneCheckbox10(){																			
	    var tnum = document.getElementsByClassName("10");																			
	    var tnum = 10;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('#wrap').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('#wrap').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function oneCheckbox11(){																			
	    var tnum = document.getElementsByClassName("11");																			
	    var tnum = 11;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('#wrap').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('#wrap').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function oneCheckbox12(){																			
	    var tnum = document.getElementsByClassName("12");																			
	    var tnum = 12;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('#wrap').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('#wrap').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
	//========================================================infoBoardList 태그선택 끝 ===================																			
																				
																				
	//========================================================infoBoardList search_info js 시작 ===================																			
	$().ready(function () {																			
		var $container = $('.page-main-style');																		
																				
	$container.on('change', '.search > li > select[name="keyfield"]', function () {																			
		var $form = $container.find('.search');																		
		var $keyword = $form.find('input[name="keyword"]');																		
		if ($(this).val() === 'tag') {																		
			$keyword.attr('placeholder', '#에브리타임');																	
			  if($.trim($('#keyword').val()) == '#'){																	
				   alert("성명을 입력하십시오.");																
				   $('#keyword').focus();																
				   return false;																
				  };																
		} else {																		
			$keyword.attr('placeholder', '검색어를 입력하세요.');																	
		}																		
		$keyword.val('');																		
		});																		
	});																			
	//========================================================infoBoardList search_info js 끝 ===================																			
																				
	//========================================================infoBoardView 태그선택 ===================																				
	function Checkbox1(){																		
	    var tnum = document.getElementsByClassName("1");																			
	    var tnum = 1;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('.page-main-style-detail').empty();																
				$('.align-right').empty();																
				$('.reply_init').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('.page-main-style-detail').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function Checkbox2(){																			
	    var tnum = document.getElementsByClassName("2");																			
	    var tnum = 2;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			//		 ┌key  ┌변수															
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('.page-main-style-detail').empty();																
				$('.align-right').empty();																
				$('.reply_init').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('.page-main-style-detail').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function Checkbox3(){																			
	    var tnum = document.getElementsByClassName("3");																			
	    var tnum = 3;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('.page-main-style-detail').empty();																
				$('.align-right').empty();																
				$('.reply_init').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('.page-main-style-detail').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function Checkbox4(){																			
	    var tnum = document.getElementsByClassName("4");																			
	    var tnum = 4;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('.page-main-style-detail').empty();																
				$('.align-right').empty();																
				$('.reply_init').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('.page-main-style-detail').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function Checkbox5(){																			
	    var tnum = document.getElementsByClassName("5");																			
	    var tnum = 5;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('.page-main-style-detail').empty();																
				$('.align-right').empty();																
				$('.reply_init').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('.page-main-style-detail').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function Checkbox6(){																			
	    var tnum = document.getElementsByClassName("6");																			
	    var tnum = 6;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('.page-main-style-detail').empty();																
				$('.align-right').empty();																
				$('.reply_init').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('.page-main-style-detail').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function Checkbox7(){																			
	    var tnum = document.getElementsByClassName("7");																			
	    var tnum = 7;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('.page-main-style-detail').empty();																
				$('.align-right').empty();																
				$('.reply_init').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('.page-main-style-detail').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function Checkbox8(){																			
	    var tnum = document.getElementsByClassName("8");																			
	    var tnum = 8;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('.page-main-style-detail').empty();																
				$('.align-right').empty();																
				$('.reply_init').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('.page-main-style-detail').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function Checkbox9(){																			
	    var tnum = document.getElementsByClassName("9");																			
	    var tnum = 9;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('.page-main-style-detail').empty();																
				$('.align-right').empty();																
				$('.reply_init').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('.page-main-style-detail').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function Checkbox10(){																			
	    var tnum = document.getElementsByClassName("10");																			
	    var tnum = 10;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('.page-main-style-detail').empty();																
				$('.align-right').empty();																
				$('.reply_init').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('.page-main-style-detail').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function Checkbox11(){																			
	    var tnum = document.getElementsByClassName("11");																			
	    var tnum = 11;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('.page-main-style-detail').empty();																
				$('.align-right').empty();																
				$('.reply_init').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('.page-main-style-detail').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
																				
	function Checkbox12(){																			
	    var tnum = document.getElementsByClassName("12");																			
	    var tnum = 12;																			
	     																			
	    																			
	    var pageSize = 10;																			
		var pageBlock = 10;																		
		var currentPage;																		
		var totalItem;																		
	    																			
	    $.ajax({																			
			url:'tagSelect.do',																	
			type:'post',//전송 방식																	
			data:{tag_num:tnum},//서버쪽에 전달할 데이터																	
			dataType: 'json',//서버에서 전송하는 데이터의 형식(json)																	
			cache:false,																	
			timeout:30000,																	
			success:function(data){//서버로부터 데이터가 성공적으로 도착하면 호출되는 함수																	
				//태그 초기화																
				$('.page-main-style-detail').empty();																
				$('.align-right').empty();																
				$('.reply_init').empty();																
																				
				$(data.list).each(function(index,item){																
					var output = '';															
					output += '<article class="board_view">';															
					output += '<a href="detail.do?post_num='+item.post_num+'"><div class="article">'															
					output += '<h2>'+item.title+'</h2>';															
					output += '<p class="small">'+item.content+'</p>';															
					output += '<span class="small">'+item.reg_date+'</span>';															
					output += item.id;															
					output += '</div></a>'															
					output += '</article>';															
																				
					//문서 객체에 추가															
					$('.page-main-style-detail').append(output);															
				});																
																				
					//ul 태그 초기화															
					$('#pagenation').empty();															
					totalItem = data.count;															
					if(totalItem == 0){															
						return;														
					}															
																				
					var totalPage = Math.ceil(totalItem/pageSize);															
																				
					if(currentPage == undefined || currentPage == ''){															
						currentPage = 1;														
					}															
					//현재 페이지가 전체 페이지 수보다 크면 전체 페이지수로 설정															
					if(currentPage > totalPage){															
						currentPage = totalPage;														
					}															
																				
					//시작 페이지와 마지막 페이지 값을 구하기															
					var startPage = 															
						Math.floor((currentPage-1)/pageBlock)*pageBlock + 1;														
					var endPage = startPage + pageBlock - 1;															
																				
					//마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정															
					if(endPage > totalPage){															
						endPage = totalPage;														
					}															
																				
					var add='';															
					if(startPage>pageBlock){															
						add += '<li data-page='+(startPage-1)+'>[이전]</li>';														
					}															
																				
					for(var i=startPage;i<=endPage;i++){															
						add += '<li data-page='+i+'>'+i+'</li>';														
					}															
					if(endPage < totalPage){															
						add += '<li data-page='+(startPage+pageBlock)+'>[다음]</li>';;														
					}															
					//ul 태그에 생성한 li를 추가															
					$('#pagenation').append(add);															
																				
			},																	
			error:function(){																	
				alert('네트워크 오류 발생');																
			}																	
		});																		
	}																			
	//========================================================infoBoardView 태그선택 ===================																			
																	
