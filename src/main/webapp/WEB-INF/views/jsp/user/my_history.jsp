<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.org.makgol.users.vo.UsersResponseVo"%>

<!DOCTYPE html>
<head>
 <meta charset="UTF-8">
  <title>막내야 골라봐 | 내 활동 (MY_HISTORY)</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <link href="<c:url value='/resources/static/css/user.css' />" rel="stylesheet" type="text/css" />


</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
<div id="my_history_page">
    <h1 class="my_history_title">내 활동 이력</h1>
    <div class="user">
        <span class="photo">
            <img src="${loginedUserVo.photo_path}" alt="프로필사진" />
        </span>
        <div class="user_info">
            <div class="user_name_role">
                <span class="name">${loginedUserVo.name}</span>
                <span class="role">${loginedUserVo.grade}</span>
            </div>
            <p class="total_history_list">
                작성글 <span class="posts"></span>개 | 작성댓글 <span class="comments"></span>개 | 공감한 글 <span class="likes"></span>개
            </p>
        </div>
    </div>
     <ul class="my_history_list">
        <li class="myPosts on" onclick="myPostList()">작성한 글</li>
        <li class="myComments" onclick="myCommentList()">작성한 댓글</li>
        <li class="myLikePosts" onclick="myLikePost()">공감한 글</li>
     </ul>
    <div id="my_history"></div>
</div>
<jsp:include page="../../script/jsp/user.jsp"></jsp:include>

<script>
    switch("${show}"){
        case "myPosts" :
            jQ(".myPosts").addClass("on");
            jQ(".myComments").removeClass("on");
            jQ(".myLikePosts").removeClass("on");
            myPostList();
            break;
        case "myComments":
            jQ(".myComments").addClass("on");
            jQ(".myPosts").removeClass("on");
            jQ(".myLikePosts").removeClass("on");
            myCommentList();
            break;
        case "myLikePosts":
            jQ(".myLikePosts").addClass("on");
            jQ(".myPosts").removeClass("on");
            jQ(".myComments").removeClass("on");
            myLikePost();
            break;
    }
    countingPosts();
    countingComments();
    countingLikes();
</script>
</body>
</html>