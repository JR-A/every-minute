<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%-- form 스프링 커스텀태그 사용 --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
body {
    margin: 0;
    height: 100%;
    background: #f5f6f7;
    font-family: Dotum,'돋움',Helvetica,sans-serif;
}
 
 .page-main-style{
 	padding-top:150px;
 } 
 
 ul{
 font-weight:bold;
 }
 
 li{
 padding-bottom:20px;
 font-size:14px;
 }

 label{
 padding-top:5px;
 }	
 li input{
 	height:25px;
 } 

 .message-ck{
 padding-left:128px;
 }
 
 input[type="submit"]{
 border-style:revert;
 width:160px;
 }
 
 input[type="button"]{
 border-style:revert;
 }
 .logo{
 background: transparent url("https://everytime.kr/images/index.login.logo.png") no-repeat;
 width: 360px;
 margin-bottom: 15px;
 height: 60px;
 margin-left:30px;
 }
</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
$(document).ready(function(){

	var checkId = 0; 
	var checkEmail =0; 
	var checkNickname =0;
	var special = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
	var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;



	
	//아이디 중복 체크
	$('#confirmId').click(function(){	
		
		if($('#id').val()==''){
			$('#message_id').css('color','red').text('아이디를 입력해주세요.');
			$('#id').focus();
			return;
		}

		if($('#id').val().search(/\s/)!=-1){
				$('#message_id').css('color','red').text('아이디에 공백을 포함할수없습니다.');
				return;
				}
		

		if(special.test($('#id').val())==true){
			$('#message_id').css('color','red').text('아이디에 특수문자를 포함할수없습니다.');
			return;
			}
		
		if($('#id').val().length<4 || $('#id').val().length>15){
			$('#message_id').css('color','red')
			                .text('아이디는 4자이상 15자이하로 입력가능합니다.');
			$('#id').focus();
			return;
		}
		

		
		
		$('#message_id').text();//메시지 초기화


		$.ajax({
			url:'confirmId.do',
			type:'post',
			data:{id:$('#id').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
		
				if(data.result == 'idNotFound'){
					$('#message_id').css('color','#000').text('등록 가능한 아이디입니다.');
					checkId = 1;
				}else if(data.result == 'idDuplicated'){
					$('#message_id').css('color','red').text('중복된 아이디입니다.');
					$('#id').val('').focus();
					checkId=0;
				}else{
					checkId=0;
					alert('ID중복체크 오류');
				}
			},
			error:function(request, error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				checkId = 0;
				alert('네트워크 오류 발생');
			}
		});
	});

	//아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#register_form #id').keydown(function(){
		checkId = 0;
		$('#message_id').text('');
	});
	
	//submit 이벤트 발생시 아이디 중복 체크 여부 확인
	$('#register_form').submit(function(){
		if(checkId==0){
			$('#message_id').css('color','red')
			                .text('아이디 중복 체크는 필수입니다.');
			$('#id').focus();
			return false;
		}
	});
	
	
	


	

	//이메일 중복 체크
	$('#confirmEmail').click(function(){	
		
		if($('#email').val()==''){
			$('#message_email').css('color','red').text('이메일을 입력해주세요.');
			$('#id').focus();
			return;
		}


		if($('#email').val().search(/\s/)!=-1){
			$('#message_email').css('color','red').text('이메일에 공백을 포함할수없습니다.');
			return;
			}
		
		if(reg_email.test($('#email').val())==false){
			$('#message_email').css('color','red').text('이메일 형식이 잘못됐습니다.');
			return;
			}

		$('#message_email').text();//메시지 초기화


		$.ajax({
			url:'confirmEmail.do',
			type:'post',
			data:{email:$('#email').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
		
				if(data.result == 'emailNotFound'){
					$('#message_email').css('color','#000').text('사용 가능한 이메일입니다.');
					checkEmail = 1;
				}else if(data.result == 'emailDuplicated'){
					$('#message_email').css('color','red').text('중복된 이메일 입니다.');
					$('#email').val('').focus();
					checkEmail=0;
				}else{
					checkEmail=0;
					alert('이메일 중복체크 오류');
				}
			},
			error:function(request, error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				checkEmail = 0;
				alert('네트워크 오류 발생');
			}
		});
	});

	//이메일 중복 안내 메시지 초기화 및 이메일 중복 값 초기화
	$('#register_form #email').keydown(function(){
		checkEmail = 0;
		$('#message_email').text('');
	});
	
	//submit 이벤트 발생시 이메일 중복 체크 여부 확인
	$('#register_form').submit(function(){
		if(checkEmail==0){
			$('#message_email').css('color','red')
			                .text('이메일 중복 체크를 해주세요.');
			$('#email').focus();
			return false;
		}
	});


	//닉네임 중복 체크
	$('#confirmNickname').click(function(){	
		
		if($('#nickname').val()==''){
			$('#message_nickname').css('color','red').text('닉네임을 입력해주세요.');
			$('#nickname').focus();
			return;
		}

		if($('#nickname').val().length<2 || $('#nickname').val().length>10){
			$('#message_nickname').css('color','red')
			                .text('닉네임은 2자이상 10자이하로 입력해주세요.');
			$('#nickname').focus();
			return;
		}

		if($('#nickname').val().search(/\s/)!=-1){
			$('#message_nickname').css('color','red').text('닉네임에 공백을 포함할수없습니다.');
			return;
			}

		if(special.test($('#nickname').val())==true){
			$('#message_nickname').css('color','red').text('닉네임에 특수문자를 포함할수없습니다.');
			return;
			}

		$('#message_nickname').text();//메시지 초기화


		$.ajax({
			url:'confirmNickname.do',
			type:'post',
			data:{nickname:$('#nickname').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
		
				if(data.result == 'nicknameNotFound'){
					$('#message_nickname').css('color','#000').text('사용 가능한 닉네임입니다.');
					checkNickname = 1;
				}else if(data.result == 'nicknameDuplicated'){
					$('#message_nickname').css('color','red').text('중복된 닉네임입니다.');
					$('#nickname').val('').focus();
					checkNickname=0;
				}else{
					checkNickname=0;
					alert('닉네임 중복체크 오류');
				}
			},
			error:function(request, error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				checkNickname = 0;
				alert('네트워크 오류 발생');
			}
		});
	});

	//닉네임 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#register_form #nickname').keydown(function(){
		checkNickname = 0;
		$('#message_nickname').text('');
	});
	
	//submit 이벤트 발생시 아이디 중복 체크 여부 확인
	$('#register_form').submit(function(){
		if(checkNickname==0){
			$('#message_nickname').css('color','red')
			                .text('닉네임 중복 체크 필수');
			$('#nickname').focus();
			return false;
		}
	});	

	
	//비밀번호 특수문자 여부 체크
	$('#register_form').submit(function(){
		if(special.test($('#passwd').val())==false){
			alert('비밀번호에 특수문자를 포함해주세요.');
			$('#passwd').val('').focus();
			return false;
			}
	
		if($('#passwd').val().search(/\s/)!=-1){
			alert('비밀번호에 공백을 포함할수없습니다.');
			$('#passwd').val('').focus();
			return false;
			}
		});

	



});
</script>
</head>
<body>
	
<div class="page-main-style">
	
	
	<form:form id="register_form" action="memberRegister.do" commandName="memberVO">
		<h1 class="logo"></h1>
		<%-- 필드가 없는 에러 표시 --%>
		<form:errors element="div" cssClass="error-color"/>		
		<ul>
			<li>
				
				<label for="id">아이디</label>
				<form:input path="id"/>
				<input type="button" id="confirmId" value="ID중복체크">
				<%-- 로딩gif --%>
				<img id="loading" src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif" width="16" height="16" style="display:none;">
				<span id="message_id" class="message-ck"></span> <%-- 아이디 중복체크 메세지 --%>
				<form:errors class="error-m" path="id" cssClass="error-color"/> <%-- 아이디 유효성체크 오류메시지,서버단에서 하는 유효성체크(어노테이션방식,동적으로 메시지 출력됨) --%>
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<form:password path="passwd"/>
				<span id="message_passwd" class="message-ck"></span>
				<form:errors class="error-m" path="passwd" cssClass="error-color"/>
			</li>
			<li>
				<label for="email">이메일</label> <%-- 어노테이션 유효성 테스트 위해 email html태그가 아닌 커스텀태그 사용(태그에서 유효성 체크 하지 않고 서버단에서 체크하는것 테스트) --%>
				<form:input path="email"/>
				<input type="button" id="confirmEmail" value="이메일 중복체크">
				<span id="message_email" class="message-ck"></span>
				<form:errors class="error-m" path="email" cssClass="error-color"/>
			</li>
			<li>(가입후 이메일로 확인메일이 전송됩니다)</li>
			<li>
				<label for="nickname">닉네임</label>
				<form:input path="nickname"/>
				<input type="button" id="confirmNickname" value="닉네임 중복체크">
				<span id="message_nickname" class="message-ck"></span>
				<form:errors class="error-m" path="nickname"/>
			</li>
			<li>
				<label for="major">학과</label>
				<form:input path="major"/>
				<form:errors path="major"/>
			</li>
			<li>
				<label for="stu_num">학번</label>
				<form:input path="stu_num"/>
				<form:errors path="stu_num"/>
			</li>	
			<li>
				<label for="zipcode">우편번호</label>
				<form:input path="zipcode"  placeholder="우편번호" readonly="true"/>
				 <input type="button"  onclick="addressApi();" value="우편번호 찾기">  
				<form:errors class="error-m" path="zipcode"/>
			</li>
			<li>
				<label for="address1">주소</label>
				<form:input path="address1" placeholder="주소" readonly="true"/>
				<form:errors path="address1"/>
			</li>
			<li>
				<label for="address2">나머지 주소</label>
				<form:input path="address2"/>
				<form:errors path="address2"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="가입하기">
			<!-- <input type="button" value="홈으로 " onclick="location.href='${pageContext.request.contextPath}/main/introduce.do'"> -->
		</div>
	</form:form>
</div>
</body>
<script type="text/javascript">
function addressApi() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }
            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipcode').value = data.zonecode;
            document.getElementById("address1").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("address2").focus();
        }
    }).open();
}
</script>
</html>