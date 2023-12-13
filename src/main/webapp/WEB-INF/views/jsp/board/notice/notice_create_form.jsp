<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link href="<c:url value='/resources/static/css/board.css' />" rel="stylesheet" type="text/css" />
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
		<div id="board_write">
				<form action="<c:url value='/board/noticeAddList'/>" name="notice_create_form" class="write_board_form" method="post" >
				    <p class="write_board">공지사항 작성</p>
				    <div class="column board_category">
					    <span class="description">카테고리</span>
					    <input type="text" name="category" class="category" value="공지사항" disabled>
					</div>
					<div class="column board_title">
					    <span class="description">제목</span>
					    <input type="text" name="title" class="title" placeholder="제목을 입력해주세요.">
					</div>
					<div class="column user_name">
					    <span class="description">작성자</span>
					    <input type="text" name="name" value="${loginedUserVo.grade}" class="admin" readonly disabled />
                        <input type="hidden" name="user_id" value="${loginedUserVo.id}" />
                    </div>
                    <div class="column board_contents">
					    <span class="description">내용</span>
					    <textarea name="contents" placeholder="내용을 입력해주세요."></textarea>
					</div>

					<div class="buttons">
					    <input type="button" class="ok_board" value="등록" onclick="noticeAddList()">
					    <input type="reset" class="cancel" value="취소" onclick="history.go(-1)">
					</div>
				</form>
		</div>
	</section>
	<jsp:include page="../../include/footer.jsp"></jsp:include>
</body>
</html>