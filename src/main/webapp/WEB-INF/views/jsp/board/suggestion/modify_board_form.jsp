<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
			action="<c:url value='/board/suggestion/modifyConfirm' ><c:param name='id' value='${boardVo.b_id}' />
		</c:url>"
			method="post" name="modify_board_form" enctype="multipart/form-data">
			<span>카테고리 선택</span>
			<select name="category">
				<option value="">카테고리 선택</option>
				<option value="notice"	<c:if test="${boardVo.category == 'notice'}">selected</c:if>>공지사항 게시판</option>
				<option value="suggestion" <c:if test="${boardVo.category == 'suggestion'}">selected</c:if>>건의사항 게시판</option>
				<option value="vent" <c:if test="${boardVo.category == 'vent'}"> selected </c:if>>하소연 게시판</option>
			</select><br>
			
			<span>제목</span> <input type="text" name="title" 	value="${boardVo.title}" placeholder="글제목"><br>
			<span>작성자</span> <input type="text" name="name" value="${boardVo.name}" readonly disabled><br>
			<span>내용</span><input type="text" name="contents" value="${boardVo.contents}" placeholder="글내용을 입력해주세요"><br>
			<span>내용</span><input type="hidden" name="oldImages" value="${boardVo.images}"><br>
			<p>이미지는 최대 5장까지 첨부가능합니다.</p>

            <c:if test="${not empty boardVo.images}">
                <c:forEach var="item" items="${boardVo.images}"  varStatus="status">
                    <label for="file${status.index+1}"
                        <span><img src="http://localhost:8090${item}"></span>
                        <input type="file" id="file${status.index+1}" name="file${status.index+1}">
                    </label>
                    <br>
                </c:forEach>
		    </c:if>



			<span>첨부파일 2</span>
			<input type="file" name="file2"><br>
			<span>첨부파일 3</span>
			<input type="file" name="file3"><br>
			<span>첨부파일 4</span>
			<input type="file" name="file4"><br>
			<span>첨부파일 5</span>
			<input type="file" name="file5"><br>
			<input type="button" value="수정" onclick="ModifyBoardForm();">
			<input type="button" value="취소" onclick="history.go(-1)"/>
		</form>
	</div>
</body>
</html>