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
	<h3>글 삭제 오류!!!!!</h3>
	<c:url value="/board/suggestion" var="suggestion_url" />
	<a href="${suggestion_url}">목록보기</a>
</body>
</html>