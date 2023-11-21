<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>막내야 골라봐 | 건의게시판 (SUGGESTION)</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

	<link rel="stylesheet"
    	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
    	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
    	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link href="<c:url value='/resources/static/css/header.css' />" rel="stylesheet" type="text/css" />

</head>

<body>

	<jsp:include page="../../include/header.jsp"></jsp:include>

	<h3>건의게시판</h3>
	<div class="board_list"></div>

	<form name="search_board_form">
		<select name="search">
			<option value="">선택</option>
			<option value="title">글제목</option>
			<option value="contents">글내용</option>
			<option value="name">작성자</option>
		</select> <input type="text" name="searchWord" placeholder="검색어를 입력해주세요" /> <input
			type="button" value="검색" onclick="searchBoard()" />
	</form>

	<jsp:include page="../../../script/jsp/suggestion.jsp"></jsp:include>


	<script>
		allBoardList();
	</script>

</body>
</html>