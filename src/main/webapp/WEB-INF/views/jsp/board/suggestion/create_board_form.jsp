<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% request.setCharacterEncoding("utf-8"); %>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet"
    	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
    	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
    	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link href="<c:url value='/resources/static/css/header.css' />" rel="stylesheet" type="text/css" />


</head>
<body>
	<jsp:include page="../../include/header.jsp"></jsp:include>

	<div>
		<form action="<c:url value='/board/suggestion/createConfirm' />"
			method="post" name="create_board_form"  enctype="multipart/form-data">
			<span>카테고리 선택</span>
			<select name="category" >
				<option value="">카테고리 선택</option>
				<option value="notice">공지사항 게시판</option>
				<option value="suggestion">건의사항 게시판</option>
				<option value="vent">하소연 게시판</option>
			</select><br>
			<span>제목</span>
			<input type="text" name="title"	placeholder="글제목">

			<span>작성자</span>
			<input type="text" name="name" value="${name}" readonly disabled> <br>
			<input type="hidden" name="user_id" value="${user_id}">			
			<span>내용</span>
			<input type="text" name="contents" placeholder="글내용을 입력해주세요"> <br>
			<input type="file" name="file"><br>

			<input type="button" value="작성" onclick="CreateBoardForm();">
			<input type="reset" value="취소">
		</form>
	</div>
<script type="text/javascript">

	function CreateBoardForm() {

		let form = document.create_board_form;

		if (form.category.value == '') {
			alert('카테고리를 선택해주세요.');
			form.category.focus();

		} else if (form.title.value == '') {
			alert('제목을 입력해주세요');
			form.title.focus();

		} else if (form.contents.value == '') {
			alert('글 내용을 입력해주세요.');
			form.contents.focus();

		}  else {
			form.submit();
		}

	}
</script>
</body>
</html>