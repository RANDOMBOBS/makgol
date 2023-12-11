<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>막내야 골라봐 | 회원정보수정 (MODIFY_USER_INFO)</title>
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
          href="https://fonts.googleapis.com/css2?family=Gamja+Flower&display=swap"
          rel="stylesheet"
        />
        <link href="<c:url value='/resources/static/css/user.css' />" rel="stylesheet" type="text/css" />
    </head>

    <body>
    <jsp:include page="../include/header.jsp"></jsp:include>
      <div id="my_page">
        <h1 class="my_page_title">마이페이지</h1>
         <div class="my_page_content">
           <div class="my_info">
             <p class="hole"></p>
             <table class="info_table">
               <tr>
                 <td>
                   <img src="${loginedUserVo.photo_path}" alt="프로필사진" />
                 </td>
               </tr>
               <tr>
                 <td>${loginedUserVo.name} / ${loginedUserVo.grade}</td>
               </tr>
               <tr>
                 <td>${userInfo.email}</td>
               </tr>
               <tr>
                 <td>${userInfo.phone}</td>
               </tr>
               <tr>
                 <td>${userInfo.address}</td>
               </tr>
             </table>
             <span class="mod_my_info">
                <a href="<c:url value='/user/modifyUser'><c:param name='user_id' value='${loginedUserVo.id}' /></c:url>">수정하기</a>
             </span>
           </div>

           <c:url value='/user/myStoreList' var='my_store_url'>
             <c:param name='user_id' value='${loginedUserVo.id}' />
           </c:url>

           <div class="my_history">
             <p class="hole"></p>
             <p class="horizon"></p>
             <p class="vertical"></p>
             <div class="history_table">
               <ul>
                 <li><a href="${my_store_url}"></a></li>
                 <li><img src="../../../fileUpload/default/my_restaurant.png" alt="식당아이콘" /></li>
                 <li>좋아요한 식당</li>
               </ul>
               <ul>
                 <li>
                   <a href="<c:url value='/user/myHistory?show=myPosts'/>"></a>
                 </li>
                 <li><img src="../../../fileUpload/default/my_board.png" alt="게시판아이콘" /></li>
                 <li>작성한 글</li>
               </ul>
               <ul>
                 <li>
                   <a href="<c:url value='/user/myHistory?show=myComments'/>"></a>
                 </li>
                 <li><img src="../../../fileUpload/default/my_comment.png" alt="댓글아이콘" /></li>
                 <li>작성한 댓글</li>
               </ul>
               <ul>
                 <li>
                   <a href="<c:url value='/user/myHistory?show=myLikePosts'/>"></a>
                 </li>
                 <li><img src="../../../fileUpload/default/my_like_board.png" alt="공감아이콘" /></li>
                 <li>공감한 글</li>
               </ul>
             </div>
           </div>
         </div>
       </div>
    </body>
</html>
