<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>

<link href="<c:url value='/resources/static/css/header.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/static/css/login.css' />" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include page="../include/header.jsp"></jsp:include>

    <h1>LOGIN</h1>
    <form method="post" action="/user/loginConfirm">
        <label for="email">이메일:</label>
        <input type="text" id="email" name="email" required><br><br>
        
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <input type="submit" value="로그인">
    </form>
</body>
</html>