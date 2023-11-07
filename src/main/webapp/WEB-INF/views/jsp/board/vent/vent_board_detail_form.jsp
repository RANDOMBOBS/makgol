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
<meta charset="UTF-8">
<title>글 보기</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
	function createCommentForm() {

		let form = document.create_comment_form;

		if (form.nickname.value == '') {
			alert('닉네임을 입력해주세요.');
			form.nickname.focus();

		} else if (form.content.value == '') {
			alert('댓글을 입력해주세요');
			form.content.focus();

		} else {
			form.submit();
		}

	}

	function comlist() {
		$.ajax({
			type : "POST",
			url : "/board/vent/commentList",
			data : JSON.stringify(commentData),
			contentType : "application/json",
			success : function(data) {
				// 성공적인 응답을 처리합니다.
				console.log(data);
			},
			error : function(error) {
				// 오류 응답을 처리합니다.
				console.log(error);
			}
		});
	}
</script>
<style>
ul {
	text-align: left;
	display: flex;
	justify-content: space-between;
	width: 800px;
	list-style: none;
}
</style>

</head>
<body>

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
	</table>

	<div>
		<c:url value="/board/vent" var="vent_url" />
		<a href="${vent_url}">목록</a>

		<!-- 로그인한 아이디와 작성자가 같을때만 보이기 -->
		<c:url value='/board/vent/modify' var='modify_url'>
			<c:param name='b_id' value='${boardVo.b_id}' />
		</c:url>

		<c:url value='/board/vent/delete' var='delete_url'>
			<c:param name='b_id' value='${boardVo.b_id}' />
		</c:url>
		<a href="${modify_url}">수정</a> <a href="${delete_url}">삭제</a>
	</div>

	
    <c:url value='/board/vent/createComment' var='create_url'>
		<c:param name='b_id' value='${boardVo.b_id}' />
	</c:url>

	<!-- <form action="${create_url}" method="post" name="create_comment_form">
		<p>댓글</p>
		<input type="hidden" name="board_id" value="${boardVo.b_id}" /> <input
			type="text" name="nickname" placeholder="닉네임"><br> <input
			type="text" name="content" placeholder="댓글을 입력해주세요."> <input
			type="button" value="등록" onclick="createCommentForm()"> <br>
	</form>
    -->

	<!-- <c:forEach var="item" items="${commentVos}">
		<ul>
			<li>${item.getNickname()}</li>
			<li>${item.getContent()}</li>
			<li>${item.getDate()}</li>
			<li><a href="${modify_url}">수정</a></li>
			<li><a href="${delete_url}">삭제</a></li>
    -->
		<!-- 댓글 작성한 사람만 보이게 하기 -->
		
	<!--		<c:url value='/board/suggestion/modifyComment' var='modify_url'>
				<c:param name='id' value='${commentVo.id}' />
			</c:url>
			<c:url value='/board/suggestion/deleteComment' var='delete_url'>
				<c:param name='id' value='${commentVo.id}' />
			</c:url>
		
	
				</ul>
		
	</c:forEach> 
    -->
</body>