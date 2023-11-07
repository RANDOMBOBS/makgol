<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="<c:url value='/resources/css/common/common.css' />" rel="stylesheet" type="text/css">
<link href="<c:url value='/resources/css/admin/modify_book_form.css' />" rel="stylesheet" type="text/css">

<script type="text/javascript">


// 미입력 방지
	function modifyNotice() {
		let form = document.notice_modify_form;
		if (form.title.value == '') {
			alert('수정할 제목을 입력해주세요.');
			form.title.focus();
		} else if (form.contents.value == '') {
			alert('수정할 내용을 입력해주세요.');
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
			</div>
			<div class="notice_modify_form">
				<form action="<c:url value='/board/vent/modifyVentConfirm' />" name="notice_modify_form" method="post" >
					<input type="hidden" name="b_id" value="${boardVo.b_id }">
					<span>제목</span><input type="text" name="title" value="${boardVo.title}" placeholder="제목을 입력해주세요."> 
					<span>작성자</span><input type="text" name="name" value="${boardVo.name}" readonly disabled> <br>
					<span>내용</span><input type="text" name="contents" value="${boardVo.contents}" placeholder="내용을 입력해주세요."> <br>
					<input type="button" value="등록" onclick="modifyNotice()"> 
					<input type="reset" value="취소" onclick="history.go(-1)">
				</form>
			</div>
		</div>
	</section>
</body>
</html>