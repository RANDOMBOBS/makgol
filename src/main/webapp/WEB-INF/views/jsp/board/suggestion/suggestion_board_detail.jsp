<%@page import="io.opentelemetry.exporter.logging.SystemOutLogExporter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>글 보기</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<link rel="stylesheet"
    	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
    	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
    	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link href="<c:url value='/resources/static/css/header.css' />" rel="stylesheet" type="text/css" />

<style>
ul {
	text-align: left;
	display: flex;
	justify-content: space-between;
	width: 800px;
	list-style: none;
}

ul img {
	width: 20px;
	height: 20px;
}

label {
	cursor: pointer;
}
</style>
</head>
<body>
	<jsp:include page="../../include/header.jsp"></jsp:include>


	<table>
		<tr>
			<td>${boardVo.category}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${boardVo.title}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${boardVo.name}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${boardVo.date}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${boardVo.contents}</td>
		</tr>


		<c:if test="${not empty boardVo.attachment}">
			<tr>
				<img src="<c:url value="file:///C:/makgol/board/upload//${boardVo.attachment}"/>">
			</tr>
		</c:if>
	</table>


	<div>
		<p>
			<label for="like">
			<input type="checkbox" id="like" style="display: none" data-b_id="${boardVo.b_id}" data-user_id="${loginedUsersRequestVo.id}" />
			<i class="fa-regular fa-thumbs-up">${boardVo.sympathy}</i>
			</label>
		</p>

		<c:url value="/board/suggestion" var="suggestion_url" />
		<a href="${suggestion_url}">목록</a>

		<c:url value="/board/suggestion/modify" var="modify_url">
			<c:param name="b_id" value="${boardVo.b_id}" />
			<c:param name="name" value="${boardVo.name}" />
			<c:param name="attachment" value="${boardVo.attachment}" />
		</c:url>

		<c:url value="/board/suggestion/delete" var="delete_url">
			<c:param name="b_id" value="${boardVo.b_id}" />
			<c:param name="attachment" value="${boardVo.attachment}" />
		</c:url>

		<c:if test="${boardVo.user_id == loginedUsersRequestVo.getId()}">
			<a href="${modify_url}">수정</a>
			<a href="${delete_url}">삭제</a>
		</c:if>
	</div>

	<form name="create_comment_form">
		<p>댓글</p>
		<c:choose>
			<c:when test="${loginedUsersRequestVo != null}">
				<input type="hidden" name="board_id" value="${boardVo.b_id}" />
				<input type="hidden" name="user_id"
					value="${loginedUsersRequestVo.getId()}" />
				<input type="text" name="nickname" placeholder="닉네임" />
				<br />
				<input type="text" name="content" placeholder="댓글을 입력해주세요." />
				<input type="button" value="등록" onclick="createCommentForm()" />
				<br />
			</c:when>

			<c:otherwise>
				<input type="hidden" name="board_id" value="${boardVo.b_id}" />
				<input type="text" name="nickname" placeholder="로그인 후 댓글 작성이 가능합니다."
					disabled />
				<br />
				<a href="/user/login">로그인하러가기</a>
				<br />

			</c:otherwise>
		</c:choose>
	</form>

	<div class="boardCommentList"></div>

	<jsp:include page="../../../script/jsp/suggestion.jsp"></jsp:include>



	<script>
		let b_id = jQ('input[type=checkbox]').attr("data-b_id")
		let user_id = jQ('input[type=checkbox]').attr("data-user_id")
		let likeData = { b_id : b_id, user_id : user_id }
		if (user_id) {
			userLikeStatus(b_id, user_id);
		}
		comList();
	</script>
</body>
</html>
