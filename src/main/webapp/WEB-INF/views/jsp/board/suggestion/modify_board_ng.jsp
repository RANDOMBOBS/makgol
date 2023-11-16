<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="<c:url value='/resources/static/css/header.css' />" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include page="../../include/header.jsp"></jsp:include>

	<h3>글수정 오류!!!!!</h3>
	<c:url value="/board/suggestion" var="suggestion_url" />
	<a href="${suggestion_url}">목록보기</a>
</body>
</html>