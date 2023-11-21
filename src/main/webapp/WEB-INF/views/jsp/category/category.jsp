<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<link href="<c:url value='/resources/static/css/category.css' />"
	rel="stylesheet" type="text/css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
	<section>
		<div id="category_main_div">
			<ul class="category_main_ul">
				<li><button type="button" class="active" onclick="menuList()">전체보기</button></li>
				<li><button type="button" onclick="korMenu()">한식</button></li>
				<li><button type="button" onclick="westMenu()">양식</button></li>
				<li><button type="button" onclick="chiMenu()">중식</button></li>
				<li><button type="button" onclick="snackMenu()">분식</button></li>
				<li><button type="button" onclick="jpnMenu()">일식</button></li>
				<li><button type="button" onclick="cafeMenu()">카페/디저트</button></li>
			</ul>
		</div>
		<div id="category_list_div"></div>
	</section>
	<jsp:include page="../../script/jsp/category.jsp"></jsp:include>
	<script>
	
	// 버튼 클릭시 색 변화
		$("button").on("click", function() {
			$("button").removeClass("active");
			$(this).addClass("active");
		});

		if("${category}" == "한식"){
			korMenu();
			$("button").removeClass("active");
			$(".category_main_ul button").eq(1).addClass("active");
		} else if("${category}" == "양식"){
			westMenu();
			$("button").removeClass("active");
			$(".category_main_ul button").eq(2).addClass("active");
		} else if("${category}" == "중식"){
			chiMenu();
			$("button").removeClass("active");
			$(".category_main_ul button").eq(3).addClass("active");
		} else if("${category}" == "분식"){
			snackMenu();
			$("button").removeClass("active");
			$(".category_main_ul button").eq(4).addClass("active");
		} else if("${category}" == "일식"){
			jpnMenu();
			$("button").removeClass("active");
			$(".category_main_ul button").eq(5).addClass("active");
		} else if("${category}" == "카페"){
			cafeMenu();
			$("button").removeClass("active");
			$(".category_main_ul button").eq(6).addClass("active");
		} else {
			menuList();
			$("button").removeClass("active");
			$(".category_main_ul button").eq(0).addClass("active");
		}
	</script>
</body>
</html>