<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
 <meta charset="UTF-8">
  <title>막내야 골라봐 | 내 맛집 리스트 (MY_STORE_LIST)</title>
  <link href="<c:url value='/resources/static/css/user.css' />" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
<div id="my_store_page">
    <div class="chatting">
        <div class="chatting_title">
          <span>채팅</span>
          <i class="fa-solid fa-magnifying-glass"></i>
        </div>
        <div class="friends">
          <div class="friend">
            <img src="/resources/static/image/default/my_restaurant.png" alt="photo" />
            <div>
              <p class="title">내가 찜한 식당</p>
              <p class="content">내가 찜한 식당 목록 보여주세요!</p>
            </div>
          </div>
        </div>
    </div>

    <div class="my_store">
        <div class="my_store_title_box">
            <div class="my_store_title">
                <i class="fa-solid fa-chevron-left"></i>
                <span class="title">내가 찜한 식당</span>
                <i class="fa-solid fa-bars"></i>
        </div>
    </div>
    <hr>

    <div class="stores">
        <div class="request">
            <span class="my_message">내가 찜한 식당 목록 보여주세요!</span>
            <img src="${loginedUserVo.photo_path}" alt="" />
        </div>
        <c:forEach var="item" items="${storeVos}">
        <div class="one_store">
            <c:choose>
                <c:when test="${(not empty item.photo) && (item.photo.contains('fname='))}">
                    <img src="<c:url value='https://${item.photo}' />" alt="${item.photo}" />
                </c:when>
                <c:otherwise>
                    <img src="/resources/static/image/default/no_image.jpg" alt="이미지가 없습니다." />
                </c:otherwise>
            </c:choose>
            <div class="store_info">
                <p class="name">${item.name}</p>
                <ul>
                    <li class="address">${item.address}</li>
                    <li class="phone">${item.phone}</li>
                    <li>
                        <p>
                            <a href="#">식당 보러가기 <i class="fa-solid fa-angles-right"></i></a>
                        </p>
                    </li>
                </ul>
            </div>
        </div>
        </c:forEach>
        </div>
    <p class="circle"></p>
    </div>
</div>
</body>
</html>