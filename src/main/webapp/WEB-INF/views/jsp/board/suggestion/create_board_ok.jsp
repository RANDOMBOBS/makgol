<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<jsp:include page="../../include/header.jsp"></jsp:include>
	<h1>글쓰기성공!</h1>
    <c:url value="/board/suggestion" var="suggestion_url"/>
    <img id="image" src="<c:url value='/resources/static/image/default/ok.gif' />">
    <a href="${suggestion_url}">목록보기</a>
    <jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>