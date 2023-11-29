<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<c:url value='/resources/static/css/board.css' />" rel="stylesheet" type="text/css" />

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

	<div id="suggestion_board">
	  <form
			action="<c:url value='/board/suggestion/modifyConfirm' ><c:param name='id' value='${boardVo.b_id}' />
		    </c:url>" method="post" name="modify_board_form" enctype="multipart/form-data">
		<p class="write_board">게시글 수정</p>

		<div class="column board_category">
         <span class="description">카테고리 선택</span>
		 <select name="category">
			<option value="">카테고리 선택</option>
			<option value="notice"	<c:if test="${boardVo.category == 'notice'}">selected</c:if>>공지사항 게시판</option>
			<option value="suggestion" <c:if test="${boardVo.category == 'suggestion'}">selected</c:if>>건의사항 게시판</option>
			<option value="vent" <c:if test="${boardVo.category == 'vent'}"> selected </c:if>>하소연 게시판</option>
		 </select>
		</div>

		<div class="column board_title">
		  <span class="description">제목</span>
          <input type="text" name="title" class="title" value="${boardVo.title}" placeholder="글 제목을 입력해주세요.">
        </div>

        <div class="column user_name">
		  <span class="description">작성자</span>
          <input type="text" name="name" value="${boardVo.name}" class="name" readonly disabled>
        </div>

        <div class="column board_contents">
          <span class="description">내용</span>
          <textarea name="contents" placeholder="글 내용을 입력해주세요">${boardVo.contents}</textarea>
        </div>

        <input type="hidden" name="oldImages" value="${boardVo.images}">

        <p class="image_info">이미지는 최대 5장까지 첨부가능합니다.</p>

		<div class="image_box">
            <div class="image">
              <label for="file1">
                <input type="file" name="file1" id="file1" onchange="imageURL(this)"/>
                  <div class="no_image">
                    <i class="fa-solid fa-plus"></i>
                    <img class="preview" src="#" width="150" height="200" />
                  </div>
              </label>
              <span class="delete_image" onclick="deleteImage(this)">
                <i class="fa-solid fa-xmark"></i>
              </span>
            </div>

            <div class="image">
              <label for="file2">
                <input type="file" name="file2" id="file2" onchange="imageURL(this)"/>
                  <div class="no_image">
                    <i class="fa-solid fa-plus"></i>
                    <img class="preview" src="#" width="150" height="200" />
                  </div>
              </label>
              <span class="delete_image" onclick="deleteImage(this)">
                <i class="fa-solid fa-xmark"></i>
              </span>
            </div>

            <div class="image">
              <label for="file3">
                <input type="file" name="file3" id="file3" onchange="imageURL(this)" />
                <div class="no_image">
                  <i class="fa-solid fa-plus"></i>
                  <img class="preview" src="#" width="150" height="200" />
                </div>
              </label>
              <span class="delete_image" onclick="deleteImage(this)">
                <i class="fa-solid fa-xmark"></i>
              </span>
            </div>

            <div class="image">
              <label for="file4">
                <input type="file" name="file4" id="file4" onchange="imageURL(this)" />
                <div class="no_image">
                  <i class="fa-solid fa-plus"></i>
                  <img class="preview" src="#" width="150" height="200" />
                </div>
              </label>
              <span class="delete_image" onclick="deleteImage(this)">
                <i class="fa-solid fa-xmark"></i>
              </span>
            </div>

            <div class="image">
              <label for="file5">
                <input type="file" name="file5" id="file5" onchange="imageURL(this)" />
                <div class="no_image">
                  <i class="fa-solid fa-plus"></i>
                  <img class="preview" src="#" width="150" height="200" />
                </div>
              </label>
              <span class="delete_image" onclick="deleteImage(this)">
                <i class="fa-solid fa-xmark"></i>
              </span>
            </div>
        </div>

        <br>

        <div class="buttons">
			<input type="button" class="ok_board" value="수정" onclick="ModifyBoardForm();">
			<input type="button" value="취소" onclick="history.go(-1)"/>
		</div>

		 <c:forEach var="index" begin="0" end="4">
             <c:if test="${not empty boardVo.images[index]}">
                 <input type="hidden" class="oldImage" name="oldImage${index + 1}" value="${boardVo.images[index]}">
             </c:if>
         </c:forEach>

	  </form>
	</div>

	<jsp:include page="../../../script/jsp/suggestion.jsp"></jsp:include>


<script>
    <c:if test="${not empty boardVo.images}">
     <c:forEach var="item" items="${boardVo.images}" varStatus="status">
        jQ(".image_box .image:eq(${status.index})").children().find("img").attr("src", "http://localhost:8090${item}")
        jQ(".image_box .image:eq(${status.index})").children().find("img").attr("style", "display:block")
        jQ(".image_box .image:eq(${status.index})").find(".delete_image").attr("style", "display:flex")
     </c:forEach>
    </c:if>
</script>
</body>
</html>