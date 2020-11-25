<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/layout.css" type="text/css">
</head>
<body>
<!-- 타일스를 사용하지 않고 FORWARD 페이지를 보여주는거기 때문에 온전한 html템플릿을 사용해야 합니당~ -->
<div class="one-page">
	<h2>안내</h2>
	<div class="result-display">
		잘못 된 접속입니다. <br>
		<input type="button" value="홈으로" onclick="location.href='${page.Context.request.contextPath}/main/introduce.do'">
	</div>
</div>
</body>
</html>



