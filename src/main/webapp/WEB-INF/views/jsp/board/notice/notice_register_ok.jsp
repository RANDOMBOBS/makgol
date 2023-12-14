<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
.result{
		text-align:center;
		margin:50px 0px;
}
</style>
<body>
    <jsp:include page="../../include/header.jsp"></jsp:include>
    <div class="result">
	<h1>게시글이 등록 되었습니다.</h1>
	<img id="image" src="<c:url value='/resources/static/image/default/ok.gif' />">
	<a href="<c:url value='/board/notice' />"><br>게시글 목록으로 이동</a>
	</div>
    <jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>