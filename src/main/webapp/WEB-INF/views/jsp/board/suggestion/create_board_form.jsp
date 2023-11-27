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
			<span>카테고리 선택</span>
			<select id="category">
				<option value="">카테고리 선택</option>
				<option value="notice">공지사항 게시판</option>
				<option value="suggestion">건의사항 게시판</option>
				<option value="vent">하소연 게시판</option>
			</select><br>
			<span>제목</span>
			<input type="text" id="title"	placeholder="글제목">

			<span>작성자</span>
			<input type="text" id="name" value="${name}" readonly disabled> <br>
			<input type="hidden" id="user_id" value="${user_id}">
			<span>내용</span>
			<input type="text" id="contents" placeholder="글내용을 입력해주세요"> <br>
         <p>이미지는 최대 5장까지 첨부가능합니다.</p>
             <div class="image_box">

          <label for="file1" style="display: inline-block">
                       <input
                         type="file"
                         id="file1"
                         onchange="imageURL(this)"
                       />
                       <p>
                         <i class="fa-solid fa-plus"></i>
                         <img class="preview" src="#" width="150" height="200" />
                       </p>
                     </label>
                     <label for="file2">
                       <input
                         type="file"
                         id="file2"
                         onchange="imageURL(this)"
                       />
                       <p>
                         <i class="fa-solid fa-plus"></i>
                         <img class="preview" src="#" width="150" height="200" />
                       </p>
                     </label>
                     <label for="file3">
                       <input
                         type="file"
                         id="file3"
                         onchange="imageURL(this)"
                       />
                       <p>
                         <i class="fa-solid fa-plus"></i>
                         <img class="preview" src="#" width="150" height="200" />
                       </p>
                     </label>
                     <label for="file4">
                       <input
                         type="file"
                         id="file4"
                         onchange="imageURL(this)"
                       />
                       <p>
                         <i class="fa-solid fa-plus"></i>
                         <img class="preview" src="#" width="150" height="200" />
                       </p>
                     </label>
                     <label for="file5">
                       <input
                         type="file"
                         id="file5"
                         onchange="imageURL(this)"
                       />
                       <p>
                         <i class="fa-solid fa-plus"></i>
                         <img class="preview" src="#" width="150" height="200" />
                       </p>
                     </label>


             </div>
             <br />
             <input type="button" value="작성" onclick="CreateBoardForm();" />
             <input type="reset" value="취소" />
           </form>
         </div>

     <script type="text/javascript">

       function imageURL(input) {
         if (input.files && input.files[0]) {
           var reader = new FileReader();
           reader.onload = function (e) {
             jQuery(input).next().children(".preview").attr("src", e.target.result);
             jQuery(input).next().children(".fa-plus").attr("style", "display:none");
             jQuery(input)
               .next()
               .children("img")
               .attr("style", "display:inline-block");
             jQuery(input).parent().next().attr("style", "display:inline-block");
           };
           reader.readAsDataURL(input.files[0]);
         }
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
            let formData = new FormData();
            let user_id = $("#user_id").val()
            let title = $("#title").val();
            let contents =  $("#contents").val();
            let category = $("#category").val();
            let image1 = $("input[type=file]:eq(0)")[0].files[0];
            let image2 = $("input[type=file]:eq(1)")[0].files[0];
            let image3 = $("input[type=file]:eq(2)")[0].files[0];
            let image4 = $("input[type=file]:eq(3)")[0].files[0];
            let image5 = $("input[type=file]:eq(4)")[0].files[0];

            formData.append("user_id", user_id);
            formData.append("title", title);
            formData.append("contents", contents);
            formData.append("category", category);
            formData.append("file1", image1);
            formData.append("file2", image2);
            formData.append("file3", image3);
            formData.append("file4", image4);
            formData.append("file5", image5);

            jQuery.ajax({
                    type: "POST",
                    url: "/board/suggestion/createConfirm",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success:
                        function (data, status) {
                            if (status === "success") {
                                // 회원가입 성공
                                if (data === true) {
                                    alert(data);
                                    window.location.href = "http://localhost:8090";
                                    // 실패
                                } else {
                                    alert(data);
                                }
                            } else {
                                alert("글쓰기 실패")
                                console.error("통신 오류 : " + status);
                            }
                        }
                });
            });// 회원가입 버튼_END

         }
       }
     </script>
</body>
</html>