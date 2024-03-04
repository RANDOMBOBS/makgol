<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>막내야 골라봐 | 하소연게시판 (VENT)</title>
	<link href="<c:url value='/resources/static/css/board.css' />" rel="stylesheet" type="text/css" />


</head>

<body>

<jsp:include page="../../include/header.jsp"></jsp:include>

<div id="board_list">
	<h1 class="board_list_title">하소연게시판</h1>
	<div class="board_list_contents">
		<form name="search_board_form">
			<select name="search">
				<option value="title">제목</option>
				<option value="titleContents">제목+내용</option>
				<option value="contents">내용</option>
				<option value="name">작성자</option>
			</select>
			<input type="text" name="searchWord" autocomplete='off' placeholder="검색어를 입력해주세요" />
			<button type="button" onclick="searchBoard()">
				<i class="fa-solid fa-magnifying-glass"></i>
			</button>
		</form>
		<div class="board_list"></div>
	</div>
</div>

<jsp:include page="../../../script/jsp/vent.jsp"></jsp:include>

<script>
	// allBoardList(pageGroup, pageNum);
</script>

</body>
</html>