<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.result{
		text-align:center;
		margin:50px 0px;
}
</style>
</head>
<body>
	<jsp:include page="../../include/header.jsp"></jsp:include>
	<div class="result">
	    <h1>글 삭제 성공!</h1>
        <c:url value="/board/suggestion" var="suggestion_url"/>
        <img id="image" src="<c:url value='/resources/static/image/default/ok.gif' />">
        <a href="${suggestion_url}"><br>목록보기</a>
    </div>
    <jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>