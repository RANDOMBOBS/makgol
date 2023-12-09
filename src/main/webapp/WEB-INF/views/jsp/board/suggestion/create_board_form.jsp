<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% request.setCharacterEncoding("utf-8"); %>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<c:url value='/resources/static/css/board.css' />" rel="stylesheet" type="text/css" />

</head>
<body>
	<jsp:include page="../../include/header.jsp"></jsp:include>

     <div id="board_write">
        <form action="<c:url value='/board/suggestion/createConfirm' />"
              method="post" name="create_board_form" class="write_board_form"  enctype="multipart/form-data">
        <p class="write_board">게시글 작성</p>

        <div class="column board_category">
          <span class="description">카테고리 선택</span>
          <select name="category">
            <option value="">카테고리 선택</option>
            <option value="notice">공지사항 게시판</option>
            <option value="suggestion" selected>건의사항 게시판</option>
            <option value="vent">하소연 게시판</option>
          </select>
        </div>

        <div class="column board_title">
          <span class="description">제목</span>
          <input type="text" name="title" class="title" placeholder="글 제목을 입력해주세요." />
        </div>

        <div class="column user_name">
          <span class="description">작성자</span>
          <input type="text" name="name" value="${loginedUserVo.name}" class="name" readonly disabled />
          <input type="hidden" name="user_id" value="${loginedUserVo.id}" />
        </div>

        <div class="column board_contents">
          <span class="description">내용</span>
          <textarea name="contents" placeholder="글 내용을 입력해주세요." ></textarea>
        </div>

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

        <br />

        <div class="buttons">
          <input type="button" class="ok_board" value="작성" onclick="createBoardForm();" />
          <input type="reset" class="cancel" value="초기화" onclick="resetContents()"/>
          <input type="button" class="board_list" value="글목록" onclick="returnToList()"/>
        </div>
      </form>
    </div>

	<jsp:include page="../../../script/jsp/suggestion.jsp"></jsp:include>
</body>
</html>