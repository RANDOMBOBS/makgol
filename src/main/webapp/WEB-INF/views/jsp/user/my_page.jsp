<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>막내야 골라봐 | 회원정보수정 (MODIFY_USER_INFO)</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="<c:url value='/resources/static/css/header.css' />" rel="stylesheet" type="text/css"/>
</head>

<body>

<jsp:include page="../include/header.jsp"></jsp:include>

<h1>마이페이지</h1>


<div
        id="my_page"
        style="display: flex; width: 800px; justify-content: space-between"
>
    <div id="my_info">
        <span>내 정보</span>
        <span><a href="#">수정하기</a></span>

        <table>
            <tr>
                <td>
                    http://localhost:8090/${loginedUsersRequestVo.photo_path}
                </td>
            </tr>
            <tr>
                <td>김효진</td>
            </tr>
            <tr>
                <td>kyg0328@naver.com</td>
            </tr>
            <tr>
                <td>+82 10-2642-4077</td>
            </tr>
            <tr>
                <td>경기도 군포시 광정로 119, 731동 1201호</td>
            </tr>
        </table>
    </div>
    <div id="my_history">
        <span>내 활동이력</span>
        <table>
            <tr>
                <td>좋아요한 식당</td>
                <td><a href="#">보러가기</a></td>
            </tr>

            <tr>
                <td>작성한 글</td>
                <td><a href="#">보러가기</a></td>
            </tr>
            <tr>
                <td>작성한 댓글</td>
                <td><a href="#">보러가기</a></td>
            </tr>
            <tr>
                <td>공감한 글</td>
                <td><a href="#">보러가기</a></td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
