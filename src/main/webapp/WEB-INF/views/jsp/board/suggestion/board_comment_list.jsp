
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
			<li class="photo"><img src="/fileUpload/default/admin.png"/></li>
			<li class="nickname">${item.getNickname()}</li>
			<li class="date">${item.getDate()}</li>
			<c:if test="${item.getUser_id() == loginedUserVo.getId()}">
				<li class="button">
					<input type="button" value="수정" onclick="modComment(this)" />
					<input type="button" value="삭제" onclick="delComment(${item.getId()})" />
				</li>
			</c:if>
		</ul>
		<p class="content">${item.getContent()}</p>

		<div style="display: none" class="mod_cancle">
			<form name="modify_comment_form" method="POST">
				<input type="text" name="nickname" class="nickname" value='${item.getNickname()}' /><br />
				<textarea name="content">${item.getContent()}</textarea>
				<input type="hidden" name="id" value='${item.getId()}' />
				<div class="buttons">
					<input type="button" value="수정" onclick="modifyCommentForm(this)">
					<input type="button" value="취소" onclick="modifyCancle(this)">
				</div>
			</form>
		</div>
	</div>
</c:forEach>
