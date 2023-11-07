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
	 console.log('noticeAddList() CALLED!!');

	 let form = document.notice_create_form;
	
	 if (form.category.value == '') {
	 alert('문의유형을 골라주세요.');
	 form.category.focus();
	
	 } else if (form.title.value == '') {
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
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>공지사항 작성</h3>
			</div>
			<div class="notice_create_form">
				<form action="<c:url value='/board/noticeAddList'/>" name="notice_create_form" method="post" >
					<span>카테고리선택</span>
					<select name="category">
						<option value="selected" disabled selected>문의유형을 선택해주세요</option>
						<option value="notice">공지사항</option>
						<option value="suggestion">건의사항</option>
						<option value="vent">하소연게시판</option>
					</select> <br> 
					<span>제목</span><input type="text" name="title" placeholder="제목을 입력해주세요."> 
					<span>작성자</span><input type="text" name="name" value="${name}" > <br> 
					<span>내용</span><input type="text" name="contents" placeholder="내용을 입력해주세요."> <br> 
					<input type="button" value="등록" onclick="noticeAddList()"> 
					<input type="reset" value="취소" onclick="history.go(-1)">
				</form>
			</div>
		</div>
	</section>
</body>
</html>