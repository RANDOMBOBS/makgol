<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<!-- 미 입력시 alert 문으로 빈공간 알려줌 -->
<script type="text/javascript">

	function noticeAddList() {

	 let form = document.notice_create_form;
	
	 if (form.title.value == '') {
	 alert('제목을 입력해주세요.');
	 form.title.focus();
	 } else if (form.contents.value == '') {
	 alert('내용을 입력해주세요.');
	 form.contents.focus();
	 } else {
	 form.submit();
	 }
	 }
</script>

</head>
<body>
    <jsp:include page="../../include/header.jsp"></jsp:include>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>공지사항 작성</h3>
			</div>
			<div class="notice_create_form">
				<form action="<c:url value='/board/noticeAddList'/>" name="notice_create_form" method="post" >
					<span>카테고리</span><input type="text" name="category" value="공지사항" disabled> <br>
					<span>제목</span><input type="text" name="title" placeholder="제목을 입력해주세요.">
					<span>작성자</span>
					<input type="text" name="name" value="${loginedUserVo.grade}" class="name" readonly disabled />
                    <input type="hidden" name="user_id" value="${loginedUserVo.id}" />
					<span>내용</span><textarea name="contents" placeholder="내용을 입력해주세요."></textarea> <br>
					<input type="button" value="등록" onclick="noticeAddList()">
					<input type="reset" value="취소" onclick="history.go(-1)">
				</form>
			</div>
		</div>
	</section>
</body>
</html>