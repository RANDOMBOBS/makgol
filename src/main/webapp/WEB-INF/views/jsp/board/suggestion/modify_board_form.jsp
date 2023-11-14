<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript">
	function ModifyBoardForm() {
		let form = document.modify_board_form;
		if (form.title.value == '') {
			alert('제목을 입력해주세요');
			form.title.focus();
		} else if (form.contents.value == '') {
			alert('글 내용을 입력해주세요.');
			form.contents.focus();
		} else {
		   if (window.confirm('글을 수정하시겠습니까?')) {
			form.submit();
		    }
		}

	}
</script>


</head>
<body>
	<jsp:include page="../../include/header.jsp"></jsp:include>

	<div>
		<form
			action="<c:url value='/board/suggestion/modifyConfirm' ><c:param name='b_id' value='${boardVo.b_id}' />
		</c:url>"
			method="post" name="modify_board_form" enctype="multipart/form-data">
			<span>카테고리 선택</span>
			<select name="category" readonly disabled>
				<option value="">카테고리 선택</option>
				<option value="notice"	<c:if test="${boardVo.category == 'notice'}">selected</c:if>>공지사항 게시판</option>
				<option value="suggestion" <c:if test="${boardVo.category == 'suggestion'}">selected</c:if>>건의사항 게시판</option>
				<option value="vent" <c:if test="${boardVo.category == 'vent'}"> selected </c:if>>하소연 게시판</option>
			</select><br>
			
			<span>제목</span> <input type="text" name="title" 	value="${boardVo.title}" placeholder="글제목"><br>
			<span>작성자</span> <input type="text" name="name" value="${boardVo.name}" readonly disabled><br>
			<span>내용</span><input type="text" name="contents" value="${boardVo.contents}" placeholder="글내용을 입력해주세요"><br>
			<span>내용</span><input type="hidden" name="oldFile" value="${boardVo.attachment}"><br>
			<span>내용</span><input type="file" name="file" ><br>
			<input type="button" value="수정" onclick="ModifyBoardForm();">
			<input type="button" value="취소" onclick="history.go(-1)"/>
		</form>
	</div>
</body>
</html>