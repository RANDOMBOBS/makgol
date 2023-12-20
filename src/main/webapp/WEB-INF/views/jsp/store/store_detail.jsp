<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>업장 상세 페이지</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="<c:url value='/resources/static/css/shop_detail/style.css' />" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
<jsp:include page="./modal/review_image.jsp"></jsp:include>
<article>
    <div class="item_area" id="photo">
        <div class="item_info_head">
            <h1>업장 사진</h1>
        </div>
        <div class="item_info_body">
            <img/>
        </div>
        <div class="item_info_footer"></div>
    </div>
    <div class="item_area" id="intro">
        <div class="item_info_head">
            <h1>업장 상세 정보</h1>
        </div>
        <div class="item_info_body">
            <div id="user_interaction">
                <div>
                    <a><i id="likes" class="fa-solid fa-heart"> 좋아요</i></a>
                    <a><i id="head_office" class="fa-solid fa-house"> 본점 찾아가기</i></a>
                </div>
            </div>
            <div class="info_box" id="basic_info">
                <div class="detail_category">
                    <h3 class="detail_header">기본 정보</h3>
                </div>
                <ul>
                    <li><span>업장이름: </span><span></span></li>
                    <li><span>전화번호: </span><span></span></li>
                    <li><span>카테고리: </span><span></span></li>
                    <li><span>주소정보: </span><span></span></li>
                    <li><span>좋아요수: </span><span></span></li>
                </ul>
                <div id="is_liked" style="visibility: hidden">좋아요한 식당</div>
            </div>
            <div class="info_box" id="menu_info">
                <div class="detail_category">
                    <h3 class="detail_header">메뉴 정보</h3>
                </div>
                <div id="menu_wrapper">
                    <ul id="menu_list"></ul>
                </div>
            </div>
        </div>
        <div class="item_info_footer"></div>
    </div>
    <div class="item_area" id="map">
        <div class="item_info_head">
            <h1>업장 위치</h1>
        </div>
        <div class="item_info_body">
            <div id="distance"></div>
            <div class="guide_line">
                <div class="head_box">
                    <h4>지도 도우미</h4>
                </div>
                <div class="content_box">
                    <ul>
                        <li>
                            <span>빨간색 마커 </span><span>사용자님의 위치입니다</span>
                        </li>
                        <li><span>파란색 마커 </span><span>식당의 위치입니다</span></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="item_info_footer"></div>
    </div>
    <div class="item_area" id="review">
        <div class="item_info_head">
            <h1>업장 리뷰</h1>
        </div>
        <div class="item_info_body">
            <div id="review_wrapper">
                <ul id="review_list"></ul>
                <div id="review_form">
                    <div class="review_profile">
                        <img class="user_image"
                             src="http://13.209.85.63/resources/static/image/default/user_default.jpeg">
                        <c:choose>
                            <c:when test="${loginedUserVo == null}">
                                <span class="user_name">사용자</span>
                            </c:when>
                            <c:otherwise>
                                <span class="user_name">${loginedUserVo.getName()}</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="review_content">
                        <textarea spellcheck="false" id="text_review" class="content"></textarea>
                        <button id="submit_review">작성</button>
                    </div>
                    <button id="upload_image">
                        <img class="review_image" src="https://budongsancanada.com/Images/no_image.jpg">
                    </button>
                </div>
            </div>
        </div>
        <div class="item_info_footer"></div>
    </div>
</article>
<script
        type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5e40f301ec35188c140844617fdf45bf"
></script>
<jsp:include page="../../script/jsp/store_detail/event/page-init.jsp"></jsp:include>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</body>
</html>