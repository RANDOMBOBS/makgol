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

	<div id="modify_board">
	  <form
			action="<c:url value='/board/suggestion/modifyConfirm' ><c:param name='id' value='${boardVo.b_id}' />
		    </c:url>" method="post" name="modify_board_form" enctype="multipart/form-data">
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
		<div class="image_box">
          <c:choose>
            <c:when test="${not empty boardVo.images}">
             <c:forEach var="item" items="${boardVo.images}"  varStatus="status">
                 <div class="image">
                    <label for="file${status.index+1}">
                      <input type="file" id="file${status.index+1}" name="file${status.index+1}" onchange="imageURL(this)" />
                      <div class="no_image">
                        <i class="fa-solid fa-plus"></i>
                        <img class="preview" src="http://localhost:8090${item}" width="150" height="200" />
                      </div>
                    </label>
                    <span class="delete_image" onclick="deleteImage(this)">
                      <i class="fa-solid fa-xmark"></i>
                    </span>
                 </div>
             </c:forEach>
		    </c:when>

            <c:otherwise>
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

            </c:otherwise>
          </c:choose>
        </div>
			<input type="button" value="수정" onclick="ModifyBoardForm();">
			<input type="button" value="취소" onclick="history.go(-1)"/>
	  </form>
	</div>

	<jsp:include page="../../../script/jsp/suggestion.jsp"></jsp:include>

</body>
</html>