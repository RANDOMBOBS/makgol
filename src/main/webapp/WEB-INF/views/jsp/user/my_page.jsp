<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>막내야 골라봐 | 회원정보수정 (MODIFY_USER_INFO)</title>
<link href="<c:url value='/resources/static/css/user.css' />" rel="stylesheet" type="text/css" />

</head>

<body>

<jsp:include page="../include/header.jsp"></jsp:include>

<h1 class="my_page_title">마이페이지</h1>

 <div id="my_page">
      <div class="my_info">
        <p class="hole"></p>
        <table class="info_table">
          <tr>
            <td>
              <img src="http://localhost:8090${loginedUserVo.photo_path}" alt="프로필사진" />
            </td>
          </tr>
          <tr>
            <td>${loginedUserVo.name}</td>
          </tr>
          <tr>
            <td>${loginedUserVo.grade}</td>
          </tr>
          <tr>
            <td>${loginedUserVo.email}</td>
          </tr>
          <tr>
            <td>${loginedUserVo.phone}</td>
          </tr>
          <tr>
            <td>${loginedUserVo.address}</td>
          </tr>
        </table>
        <span class="mod_my_info"><a href="<c:url value='/user/modifyUser'/>">수정하기</a></span>
      </div>

      <c:url value='/user/myStoreList' var='my_store_url'>
        <c:param name='user_id' value='${loginedUserVo.id}' />
     </c:url>

      <div class="my_history">
        <p class="hole"></p>
        <table class="history_table">
          <colgroup>
            <col />
            <col />
          </colgroup>
          <tr>
            <td><i class="fa-solid fa-heart"></i> 좋아요한 식당</td>
            <td><a href="${my_store_url}">보러가기</a></td>
          </tr>
          <tr>
            <td><i class="fa-regular fa-clipboard"></i> 작성한 글</td>
            <td><a href="<c:url value='/user/myHistory?show=myPosts'/>">보러가기</a></td>
          </tr>
          <tr>
            <td><i class="fa-solid fa-pencil"></i> 작성한 댓글</td>
            <td><a href="<c:url value='/user/myHistory?show=myComments'/>">보러가기</a></td>
          </tr>
          <tr>
            <td><i class="fa-solid fa-thumbs-up"></i> 공감한 글</td>
            <td><a href="<c:url value='/user/myHistory?show=myLikePosts'/>">보러가기</a></td>
          </tr>
        </table>
      </div>
    </div>
  </body>
</html>
