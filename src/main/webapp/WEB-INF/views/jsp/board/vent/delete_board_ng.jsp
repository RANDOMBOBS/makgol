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

	<h3>글 삭제 오류!!!!!</h3>
	<c:url value="/board/vent" var="vent_url" />
	<a href="${vent_url}">목록보기</a>
</body>
</html>