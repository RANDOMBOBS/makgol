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
                <div id="option">
                    <p>찾기 옵션</p>
                    <div class="buttons">
                        <label for="find_default">기본</label>
                        <input id="find_default" type="radio" name="check_info" value="기본" checked="checked">
                        <label for="find_distance">거리순</label>
                        <input id="find_distance" type="radio" name="check_info" value="거리순">
                        <label for="find_likes">좋아요순</label>
                        <input id="find_likes" type="radio" name="check_info" value="좋아요순">
                        <label for="find_open">영업여부</label>
                        <input id="find_open" type="radio" name="check_info" value="영업순">
                    </div>
                </div>
            </div>
        </div>
        <div class="shop_info_list"></div>
        <div class="shop_info_footer"></div>
    </div>
    <div class="dibs_shop_area">
        <div class="dibs_shop_head"></div>
        <div class="dibs_shop_list"></div>
        <div class="dibs_shop_footer"></div>
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
<script
        type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5e40f301ec35188c140844617fdf45bf"
></script>
<jsp:include page="../../script/jsp/store_list/event/page-init.jsp"></jsp:include>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
</body>
</html>
