<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value='/resources/static/css/modal/index.css'/>" rel="stylesheet" type="text/css"/>
    <link href="<c:url value='/resources/static/css/shop_detail/modal/list_review_image.css' />" rel="stylesheet"
          type="text/css"/>
    <link href="<c:url value='/resources/static/css/shop_detail/modal/upload_review_image.css'/>" rel="stylesheet"
          type="text/css"/>
</head>
<body>
<div class="modal_cover">
    <div class="list_modal">
        <div class="modal_head">
            <h1>List Review Image</h1>
            <button class="close_button">X</button>
        </div>
        <div class="list_modal_body">
            <%--            <div class="button_area">--%>
            <%--                <button><i class="fa-solid fa-angle-left"></i></button>--%>
            <%--            </div>--%>
            <div class="review_image_list"></div>
            <%--            <div class="button_area">--%>
            <%--                <button><i class="fa-solid fa-angle-right"></i></button>--%>
            <%--            </div>--%>
        </div>
    </div>
    <div class="upload_modal">
        <div class="modal_head">
            <h1>Upload Review Image</h1>
            <button class="close_button">X</button>
        </div>
        <div class="image_info">이미지는 최대 5장까지 첨부가능합니다.</div>
        <div class="upload_image_list">
            <div class="image">
                <label for="file1">
                    <input type="file" name="file1" id="file1" onchange="imageURL(this)"/>
                    <div class="no_image">
                        <i class="fa-solid fa-plus"></i>
                        <img class="preview" src="#" width="150" height="200"/>
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
                        <img class="preview" src="#" width="150" height="200"/>
                    </div>
                </label>
                <span class="delete_image" onclick="deleteImage(this)">
                    <i class="fa-solid fa-xmark"></i>
                </span>
            </div>

            <div class="image">
                <label for="file3">
                    <input type="file" name="file3" id="file3" onchange="imageURL(this)"/>
                    <div class="no_image">
                        <i class="fa-solid fa-plus"></i>
                        <img class="preview" src="#" width="150" height="200"/>
                    </div>
                </label>
                <span class="delete_image" onclick="deleteImage(this)">
                    <i class="fa-solid fa-xmark"></i>
                </span>
            </div>

            <div class="image">
                <label for="file4">
                    <input type="file" name="file4" id="file4" onchange="imageURL(this)"/>
                    <div class="no_image">
                        <i class="fa-solid fa-plus"></i>
                        <img class="preview" src="#" width="150" height="200"/>
                    </div>
                </label>
                <span class="delete_image" onclick="deleteImage(this)">
                <i class="fa-solid fa-xmark"></i>
              </span>
            </div>

            <div class="image">
                <label for="file5">
                    <input type="file" name="file5" id="file5" onchange="imageURL(this)"/>
                    <div class="no_image">
                        <i class="fa-solid fa-plus"></i>
                        <img class="preview" src="#" width="150" height="200"/>
                    </div>
                </label>
                <span class="delete_image" onclick="deleteImage(this)">
                <i class="fa-solid fa-xmark"></i>
              </span>
            </div>
        </div>
        <div class="button_area">
            <button id="upload_review_image">업로드</button>
        </div>
    </div>
</div>
</body>
</html>