
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>



<c:forEach var="item" items="${commentVos}">
	<div class="one_comment">
		<ul>
			<li><img src="http://localhost:8090${item.photo_path}"/></li>
			<li>닉네임은 ${item.getNickname()}</li>
			<li>내용은 ${item.getContent()}</li>
			<li>작성일은 ${item.getDate()}</li>
			<c:if test="${item.getUser_id() == loginedUserVo.getId()}">
				<li><input type="button" value="수정" onclick="modComment(this)" /></li>
				<li><input type="button" value="삭제"
					onclick="delComment(${item.getId()})" /></li>
			</c:if>
		</ul>
		<div style="display: none" class="modCancle">
			<form name="modify_comment_form" method="POST">
				<p>수정박스입니다.</p>
				<input type="text" name="nickname" value='${item.getNickname()}' /><br />
				<input type="text" name="content" value='${item.getContent()}' /><br />
				<input type="hidden" name="id" value='${item.getId()}' /><br /> <input
					type="button" value="수정할게용" onclick="modifyCommentForm(this)">
				<input type="button" value="취소" onclick="modifyCancle(this)">
				<br>
			</form>
		</div>
	</div>
</c:forEach>
