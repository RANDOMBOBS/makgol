<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<link href="<c:url value='/resources/static/css/board.css' />" rel="stylesheet" type="text/css" />
	
</script>
<meta charset="UTF-8">
<title>막내야 골라봐 | 공지사항 (NOTICE)</title>
</head>
<body>
    <jsp:include page="../../include/header.jsp"></jsp:include>
	<section id="board_list">
		<h1 class="board_list_title">공지사항</h1>
		<div class="board_list_contents">
		    <form name="search_notice_form" class="notice_search">
				<input type="text" name="searchWord" autocomplete='off' placeholder="검색할 게시판을 입력하세요.">
				<button type="button" onclick="searchNotice()"><i class="fa-solid fa-magnifying-glass"></i></button>
		    </form>
		    <!-- div .notice_list 안에 ajax 담겨짐 -->
		    <div class="board_list"></div>
		</div>
	</section>

    <jsp:include page="../..//include/footer.jsp"></jsp:include>
	<jsp:include page="../../../script/jsp/notice.jsp"></jsp:include>

	<script>
		// 게시글 전체 리스트 ( ajax )
		function noticeAllList() {
			jQuery.ajax({
				url : "/board/noticeAllList", // Controller
				type : "GET",
				dataType : "html",
				success : function(rdata) {
					jQuery(".board_list").html(rdata);
				},
				error : function(error) {
					alert("오류");
				}
			});
		}
		noticeAllList(); // 최초 리스트 호출
	</script>
</body>
</html>