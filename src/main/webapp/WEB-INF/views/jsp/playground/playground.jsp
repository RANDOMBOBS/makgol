<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="../../../../../resources/static/css/game/playground.css">
    <link rel="stylesheet" href="../../../../../resources/static/css/header.css">

</head>

<body>
	<jsp:include page="../include/header.jsp"></jsp:include>
		<div id="first">
			<jsp:include page="./ghostleg.jsp"></jsp:include>
		</div>
	<script src="../../../../../resources/static/js/game/ghostleg/app.js"></script>
<div id="2nd"></div>
		<div id="dinogame">
			<jsp:include page="./dino.jsp"></jsp:include>
		</div>


</body>
</html>