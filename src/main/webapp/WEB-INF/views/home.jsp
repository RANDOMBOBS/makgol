<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>

    <link href="<c:url value='/resources/static/css/main.css' />" rel="stylesheet"
          type="text/css"/>

    <link href="<c:url value='/resources/static/css/todaymenu.css' />" rel="stylesheet"
          type="text/css"/>
    <link href="<c:url value='/resources/static/css/topmenu.css' />" rel="stylesheet"
          type="text/css"/>
    <link href="<c:url value='/resources/static/css/slick.css' />" rel="stylesheet"
          type="text/css"/>

    <title>막내야 골라봐 | 메인 (MAIN)</title>


</head>
<body>
<jsp:include page="jsp/include/header.jsp"></jsp:include>
<section>
    <article id="article1">
        <p class="selectedCategory">오늘의 점심 메뉴는 ?</p>
        <p class="roulette_pin"></p>
        <button id="spin">시작!</button>
        <div class="roulette_position">
            <div class="roulette"></div>
        </div>
    </article>

    <div id="article2">
        <div class="today_menu">
            <div class="todaymenu_main_div">
                <ul class="todaymenu_main_ul">
                    <li class="today">오늘의메뉴</li>
                    <li>
                        <button class="todayBtn" type="button"
                                onclick="korToday()">한식
                        </button>
                    </li>
                    <li>
                        <button class="todayBtn" type="button"
                                onclick="westToday()">양식
                        </button>
                    </li>
                    <li>
                        <button class="todayBtn" type="button"
                                onclick="chiToday()">중식
                        </button>
                    </li>
                    <li>
                        <button class="todayBtn" type="button"
                                onclick="snackToday()">분식
                        </button>
                    </li>
                    <li>
                        <button class="todayBtn" type="button"
                                onclick="jpnToday()">일식
                        </button>
                    </li>
                    <li>
                        <button class="todayBtn" type="button"
                                onclick="cafeToday()">카페
                        </button>
                    </li>
                </ul>
            </div>
            <div class="todaymenu_list">
                <div class="todaymenu_list_div"></div>
            </div>
        </div>
    </div>

    <div id="article3">
        <div class="top_menu">
            <div class="topmenu_main_div">
                <span>Top 5</span>
            </div>
            <div class="topmenu_list">
                <div class="topmenu_list_div">
                </div>
            </div>
        </div>
    </div>

    <div id="event">
        <div><img src="<c:url value='/resources/static/image/Christmas.png' />"></div>
    </div>


</section>
<footer id="footer">
    <h1>Footer</h1>
</footer>


<jsp:include page="script/jsp/main.jsp"></jsp:include>

<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

<script>
    $.noConflict();
    var jQ = jQuery;

    function getAllcategory() {
        jQ.ajax({
            url: "/main/allCategory",
            type: "GET",
            dataType: "html",
            success: function (rdata) {
                jQ(".roulette").html(rdata);
            },
            error: function (error) {
                alert("리스트업오류");
            },
        });
    }

    getAllcategory();

    todayMenuList();

    topMenuList();


    function slickTodaySlider() {
        jQ(".todaymenu_list_ul").slick({
            slidesToShow: 5,
            slidesToScroll: 5,
            dotsClass: "custom-dots",
            dots: true,
            autoplay: true,
            speed: 2000,
            prevArrow:
                "<i class='fa-solid fa-circle-arrow-left'></i>",
            nextArrow:
                "<i class='fa-solid fa-circle-arrow-right'></i>",
        });
    }

    function slickTopSlider() {
        jQ(".topmenu_list_ul").slick({
            prevArrow: "<i class='fa-solid fa-chevron-left'></i>",
            nextArrow: "<i class='fa-solid fa-chevron-right'></i>",
            adaptiveHeight: true,
            centerMode: true,
            centerPadding: '380px',
        }).on('beforeChange', function (event, slick, currentSlide, nextSlide) {
            if (currentSlide !== nextSlide) {
                jQ('.slick-center + .slick-cloned').each(function (index, node) {
                    var $node = jQ(node);

                    setTimeout(function () {
                        $node.addClass('slick-current');
                        $node.addClass('slick-center');
                    });
                });
            }
        });
    }
</script>
</body>
</html>

