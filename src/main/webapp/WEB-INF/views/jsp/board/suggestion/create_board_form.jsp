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

     <div id="create_board">
        	<form action="<c:url value='/board/suggestion/createConfirm' />"
        			method="post" name="create_board_form"  enctype="multipart/form-data">
            <p class="new_board">게시글 쓰기</p>
            <div class="column board_category">
              <span class="description">카테고리 선택</span>
              <select name="category">
                <option value="">카테고리 선택</option>
                <option value="notice">공지사항 게시판</option>
                <option value="suggestion">건의사항 게시판</option>
                <option value="vent">하소연 게시판</option>
              </select>
            </div>
            <div class="column board_title">
              <span class="description">제목</span>
              <input
                type="text"
                name="title"
                class="title"
                placeholder="글 제목을 입력해주세요."
              />
            </div>
            <div class="column user_name">
              <span class="description">작성자</span>
              <input
                type="text"
                name="name"
                value="${name}"
                class="name"
                readonly
                disabled
              />
              <input type="hidden" name="user_id" value="${user_id}" />
            </div>
            <div class="column board_contents">
              <span class="description">내용</span>
              <textarea
                name="contents"
                placeholder="글 내용을 입력해주세요."
              ></textarea>
            </div>
            <p class="image_info">이미지는 최대 5장까지 첨부가능합니다.</p>
            <div class="image_box">
              <div class="image">
                <label for="file1">
                  <input
                    type="file"
                    id="file1"
                    name="file1"
                    onchange="imageURL(this)"
                  />
                  <div class="no_image">
                    <i class="fa-solid fa-plus"></i>
                    <img class="preview" src="#" width="150" height="200" />
                  </div>
                </label>
                <span class="delete_image" onclick="deleteImage(this)"
                  ><i class="fa-solid fa-xmark"></i
                ></span>
              </div>

              <div class="image">
                <label for="file2">
                  <input
                    type="file"
                    name="file2"
                    id="file2"
                    onchange="imageURL(this)"
                  />
                  <div class="no_image">
                    <i class="fa-solid fa-plus"></i>
                    <img class="preview" src="#" width="150" height="200" />
                  </div>
                </label>
                <span class="delete_image" onclick="deleteImage(this)"
                  ><i class="fa-solid fa-xmark"></i
                ></span>
              </div>

              <div class="image">
                <label for="file3">
                  <input
                    type="file"
                    name="file3"
                    id="file3"
                    onchange="imageURL(this)"
                  />
                  <div class="no_image">
                    <i class="fa-solid fa-plus"></i>
                    <img class="preview" src="#" width="150" height="200" />
                  </div>
                </label>
                <span class="delete_image" onclick="deleteImage(this)"
                  ><i class="fa-solid fa-xmark"></i
                ></span>
              </div>

              <div class="image">
                <label for="file4">
                  <input
                    type="file"
                    id="file4"
                    name="file4"
                    onchange="imageURL(this)"
                  />
                  <div class="no_image">
                    <i class="fa-solid fa-plus"></i>
                    <img class="preview" src="#" width="150" height="200" />
                  </div>
                </label>
                <span class="delete_image" onclick="deleteImage(this)"
                  ><i class="fa-solid fa-xmark"></i
                ></span>
              </div>

              <div class="image">
                <label for="file5">
                  <input
                    type="file"
                    id="file5"
                    name="file5"
                    onchange="imageURL(this)"
                  />
                  <div class="no_image">
                    <i class="fa-solid fa-plus"></i>
                    <img class="preview" src="#" width="150" height="200" />
                  </div>
                </label>
                <span class="delete_image" onclick="deleteImage(this)"
                  ><i class="fa-solid fa-xmark"></i
                ></span>
              </div>
            </div>
            <br />
            <div class="buttons">
              <input
                type="button"
                class="create"
                value="작성"
                onclick="CreateBoardForm();"
              />
              <input type="reset" class="cancel" value="초기화" />
              <input type="button" class="board_list" value="글목록" />
            </div>
          </form>
        </div>

        <script type="text/javascript">
          function imageURL(input) {
            if (input.files && input.files[0]) {
              var reader = new FileReader();
              reader.onload = function (e) {
                jQuery(input)
                  .next()
                  .children(".preview")
                  .attr("src", e.target.result);
                jQuery(input)
                  .next()
                  .children(".fa-plus")
                  .attr("style", "display:none");
                jQuery(input).parent().next().attr("style", "display:block");
                jQuery(input).next().children("img").attr("style", "display:flex");
              };
              reader.readAsDataURL(input.files[0]);
            }
          }

      function deleteImage(input) {
        jQuery(input)
          .prev()
          .children()
          .find(".preview")
          .attr("style", "display:none");
        jQuery(input)
          .prev()
          .children()
          .find(".fa-plus")
          .attr("style", "display:block");
        jQuery(input).attr("style", "display:none");
        jQuery(input).prev().find("input").val("");
      }

          function CreateBoardForm() {
            let form = document.create_board_form;
            if (form.category.value == "") {
              alert("카테고리를 선택해주세요.");
              form.category.focus();
            } else if (form.title.value == "") {
              alert("제목을 입력해주세요");
              form.title.focus();
            } else if (form.contents.value == "") {
              alert("글 내용을 입력해주세요.");
              form.contents.focus();
            } else {
              form.submit();
            }
          }
        </script>
      </body>
</html>