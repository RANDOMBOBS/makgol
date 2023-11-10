<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />


<title>막내야 골라봐 | 메인 (MAIN)</title>

<link href="<c:url value='/resources/static/css/main.css' />" rel="stylesheet"
	type="text/css" />
<link href="<c:url value='/resources/static/css/header.css' />" rel="stylesheet"
	type="text/css" />

</head>
<body>
	<jsp:include page="jsp/include/header.jsp"></jsp:include>
	<section>
		<article id="article1">
			<p class="selectedCategory">오늘의 점심 메뉴는 ?</p>
			<p class="roulette_pin"></p>
			<button id="spin">시작!</button>
			<div class="roulette_position">
				<div class="roulette"></div>
			</div>
		</article>

		<div id="todaymenu">
			<h1>오늘의 메뉴</h1>
		</div>

		<div id="top">
			<h1>TOP 5</h1>
		</div>

		<div id="event">
			<h1>광고배너 / 이벤트</h1>
		</div>


	</section>
	<footer id="footer">
		<h1>Footer</h1>
	</footer>

	<jsp:include page="script/jsp/main.jsp"></jsp:include>


	<script>
		$.noConflict();
		var jQ = jQuery;
		function getAllcategory() {
			jQ.ajax({
				url : "/main/allCategory",
				type : "GET",
				dataType : "html",
				success : function(rdata) {
					jQ(".roulette").html(rdata);
				},
				error : function(error) {
					alert("리스트업오류");
				},
			});
		}

		getAllcategory();
	</script>
</body>
</html>

