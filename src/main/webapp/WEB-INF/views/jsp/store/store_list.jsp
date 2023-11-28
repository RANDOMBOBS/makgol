<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR"/>
    <title>업장 리스트 페이지</title>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <link href="<c:url value='/resources/static/css/shop_list/style.css' />" rel="stylesheet" type="text/css"/>
</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
<div id="map">
    <div class="shop_info_area">
        <div class="shop_info_head">
            <div class="head_box">
                <h1 id="search_keyword"></h1>
                <div class="buttons">
                    <button class="button_style">다른 메뉴</button>
                    <select class="button_style" id="option" name="option">
                        <option value="">찾기 옵션</option>
                        <option value="distance">거리순</option>
                        <option value="like">좋아요순</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="shop_info_list"></div>
        <div class="shop_info_footer"></div>
    </div>
    <div class="guide_line">
        <div class="head_box">
            <h4>지도 도우미</h4>
        </div>
        <div class="content_box">
            <p>식당 항목에 마우스를 올리면</p>
            <p>식당 위치가 표시됩니다</p>
            <ul>
                <li><span>빨간색 마커 </span><span>사용자님의 위치입니다</span></li>
                <li><span>파란색 마커 </span><span>식당의 위치입니다</span></li>
                <li><span>초록색 마커 </span><span>선택한 식당의 위치입니다</span></li>
            </ul>
        </div>
    </div>
</div>
<jsp:include page="../../script/jsp/store_list/event/page-init.jsp"></jsp:include>
<jsp:include page="../../script/jsp/store_list/event/select-option.jsp"></jsp:include>
<script
        type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5e40f301ec35188c140844617fdf45bf"
></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</body>
</html>
