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



		<c:if test="${not empty boardVo.images}">
			<c:forEach var="item" items="${boardVo.images}">
			<tr>
				<td colspan="2"><img src="http://localhost:8090${item}"></td>
			</tr>
			</c:forEach>
		</c:if>
	</table>


	<div>
		<p>
			<label for="like" >
			    <input type="checkbox" id="like" style="display: none" data-b-id="${boardVo.id}" data-user-id="${loginedUsersRequestVo.id}"/>
			    <i class="fa-regular fa-thumbs-up">${boardVo.sympathy}</i>
			</label>
		</p>

		<c:url value="/board/suggestion" var="suggestion_url" />
		<a href="${suggestion_url}">목록</a>

		<c:url value="/board/suggestion/modify" var="modify_url">
			<c:param name="b_id" value="${boardVo.id}" />
			<c:param name="name" value="${boardVo.name}" />
			<c:param name="images" value="${boardVo.images}" />
		</c:url>

		<c:url value="/board/suggestion/delete" var="delete_url">
    			<c:param name="b_id" value="${boardVo.id}" />
    			<c:param name="images" value="${boardVo.images}" />
    		</c:url>

		<c:if test="${boardVo.user_id == loginedUsersRequestVo.getId()}">
			<a href="${modify_url}">수정</a>
			<a href="#javaScript" onclick="boardDelete()">삭제</a>
		</c:if>

	</div>



	<form name="create_comment_form">
		<p>댓글</p>
		<c:choose>
			<c:when test="${loginedUsersRequestVo != null}">
				<input type="hidden" name="board_id" value="${boardVo.id}" />
				<input type="hidden" name="user_id"
					value="${loginedUsersRequestVo.getId()}" />
				<input type="hidden" name="grade" value="${loginedUsersRequestVo.getGrade()}">
				<input type="text" name="nickname" placeholder="닉네임" />
				<br />
				<input type="text" name="content" placeholder="댓글을 입력해주세요." />
				<input type="button" value="등록" onclick="createCommentForm()" />
				<br />
			</c:when>

			<c:otherwise>
				<input type="hidden" name="board_id" value="${boardVo.id}" />
				<input type="text" name="nickname" placeholder="로그인 후 댓글 작성이 가능합니다."
					disabled />
				<br />
			</c:otherwise>
		</c:choose>
	</form>

	<div class="boardCommentList"></div>


	<jsp:include page="../../../script/jsp/suggestion.jsp"></jsp:include>


	<script>
    console.log("유저아이디"+user_id, "게시판번호"+b_id)
		if (user_id) {
			userLikeStatus(b_id, user_id);
		}
		comList();


	function boardDelete(){
            if (window.confirm('글을 삭제하시겠습니까?')) {
                window.location.href = "${delete_url}";
            }
        }


	</script>


</body>
</html>
