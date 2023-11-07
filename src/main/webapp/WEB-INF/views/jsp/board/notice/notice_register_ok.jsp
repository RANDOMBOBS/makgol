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
body{
		text-align:center;
}
h1{
	margin-top:300px
}
ul{
   list-style:none;
   }

</style>
<body>

	<h1>성공</h1>

	<a href="<c:url value='/board/Notice' />">공지사항 리스트로 이동</a>

</body>
</html>