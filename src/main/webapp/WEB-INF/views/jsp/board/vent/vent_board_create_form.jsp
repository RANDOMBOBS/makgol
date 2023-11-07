<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% request.setCharacterEncoding("utf-8"); %>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	
	function VentBoardForm() {

		let form = document.vent_board_create_form;
		
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

</head>
<body>

	<div>
		<form action="<c:url value='/board/vent/createConfirm' />"
			method="post" name="vent_board_create_form">
			<span>카테고리 선택</span>
			<select name="category" >
				<option value="">카테고리 선택</option>
				<option value="notice">공지사항 게시판</option>
				<option value="suggestion">건의사항 게시판</option>
				<option value="vent">하소연 게시판</option>
			</select><br>
			<span>제목</span>
			<input type="hidden" name="user_id" value="1">
			<input type="text" name="title"	placeholder="글제목">
			
			<span>작성자</span>
			<input type="text" name="name" value="${name}" readonly disabled> <br>
			
			<span>내용</span>
			<input type="text" name="contents" placeholder="글내용을 입력해주세요"> <br>
			
		
			<input type="button" value="작성" onclick="VentBoardForm();">
			<input type="reset" value="취소">
		</form>
	</div>

</body>
</html>