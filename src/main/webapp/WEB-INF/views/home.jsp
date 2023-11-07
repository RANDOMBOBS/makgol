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



<title>막내야 골라봐 | 메인 (MAIN)</title>

<link href="<c:url value='/resources/static/css/main.css' />" rel="stylesheet"
	type="text/css" />

<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

h2 {
	text-align: center;
}

#header {
	height: 100px;
	line-height: 100px;
	border: 1px solid #000;
}

#todaymenu {
	height: 300px;
	line-height: 300px;
	border: 1px solid #000;
	margin: 100px;
}

#top {
	height: 300px;
	line-height: 300px;
	border: 1px solid #000;
	margin: 0 100px 100px 100px;
}

#event {
	height: 200px;
	line-height: 200px;
	border: 1px solid #000;
	margin: 0 100px;
}

h1 {
	text-align: center;
}

#random {
	height: 650px;
	line-height: 650px;
}

#footer {
	height: 400px;
	line-height: 400px;
	border: 1px solid #000;
}

#pratice {
	display: flex;
	justify-content: space-around;
}

#boards {
	display: flex;
	justify-content: space-around;
	height: 800px;
	line-height: 800px;
}
</style>
</head>
<body>
	<h1 id="header">Header</h1>

	<div id="pratice">
		<h2>이동용</h2>
		<a href="<c:url value='/board/Notice'/>">공지사항</a>
		<a href="<c:url value='/board/suggestion'/>">건의사항</a>
		<a href="<c:url value='/board/vent'/>">하소연게시판</a>
		<a href="<c:url value='/user/login'/>">로그인</a>
		<a href="<c:url value='/user/join'/>">회원가입</a>
	</div>

	<section>
		<article id="article1">
			<p class="selectedCategory">오늘의 점심 메뉴는 ?</p>
			<p class="roulette_pin"></p>
			<button id="spin">시작!</button>
			<div class="roullette_position">
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

		<div id="boards">
			<div>
				<a href="<c:url value='/board/Notice'/>">공지사항</a>
			</div>
			<div>
				<a href="<c:url value='/category/categoryMain'/>">카테고리 리스트 </a>
			</div>
			<div>
				<a href="<c:url value='/board/suggestion'/>">건의사항</a>
			</div>
			<div>
				<a href="<c:url value='#'/>">하소연게시판 아직 구현 X </a>
			</div>
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
				url : "/makgol/main/allCategory",
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
