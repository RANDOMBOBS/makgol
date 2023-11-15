<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.org.makgol.users.vo.UsersRequestVo"%>

<!DOCTYPE html>
 <meta charset="UTF-8">
  <title>막내야 골라봐 | 내 활동 (MY_HISTORY)</title>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
        integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link href="<c:url value='/resources/static/css/header.css' />" rel="stylesheet" type="text/css" />

</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
<h1>내 활동 이력</h1>
 <ul>
      <li class="myPosts on">작성한 글</li>
      <li class="myComments">작성한 댓글</li>
      <li class="likeComments">공감한 글</li>
    </ul>
    <div id="my_history"></div>

	<jsp:include page="../../script/jsp/user.jsp"></jsp:include>

<script>
allPostList();
</script>
</body>
</html>