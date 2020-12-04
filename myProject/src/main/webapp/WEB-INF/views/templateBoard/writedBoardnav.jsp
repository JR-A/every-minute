<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE !html >
<html>
<style>
#writedList{
	text-align:center;
	padding-right:100px;
}

#freel{
	padding-right:20px;
	
}
#infol{
	padding-right:20px;
	padding-left:20px;
}
#customl{
	padding-left:20px;
}
.atag{
	font-size:16px;
}
.nope{
	padding-top:35px;
}

</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<nav id="writedList">
<a id="freel" class="atag" href="${pageContext.request.contextPath}/member/freedBoardWritedlist.do">자유게시판에 쓴 글</a>|
<a id="infol" class="atag" href="${pageContext.request.contextPath}/member/infoBoardWritedlist.do">정보게시판에 쓴 글</a>|
<a id="customl" class="atag" href="${pageContext.request.contextPath}/member/customBoardWritedlist.do">사용자게시판에 쓴 글</a>
</nav>

</body>
</html>