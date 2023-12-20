<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.org.makgol.users.vo.UsersResponseVo" %>


<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
      integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
      crossorigin="anonymous" referrerpolicy="no-referrer"/>

<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
        integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<link href="<c:url value='/resources/static/css/header.css' />" rel="stylesheet" type="text/css"/>

<jsp:include page="./modal.jsp"></jsp:include>

<%
    if (application.getAttribute("loginedUserVo") != null) {
        UsersResponseVo loginedUserVo = (UsersResponseVo) application.getAttribute("loginedUserVo");

    }
%>


<header id="header">
    <div class="all_category">
        <div class="show_category">
            <a href="#">
                <i class="fa-solid fa-utensils"></i>
                <p>MENU</p>
            </a>
        </div>
        <ul class="main_category">
            <li>
                <c:choose>
                    <c:when test="${loginedUserVo == null}">
                        <a href="http://3.35.176.200/store/list?x=127.028290548097&y=37.4998293543379&keyword=한식">한식</a>
                    </c:when>
                    <c:otherwise>
                        <a href="http://3.35.176.200/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=한식">한식</a>
                    </c:otherwise>
                </c:choose>

                <%--                <ul class="sub_category">--%>
                <%--                    <li class="menu_option">--%>
                <%--                        <a href="#">찌개류</a>--%>
                <%--                        <ul class="menu">--%>
                <%--                            <li><a href="#">부대찌개</a></li>--%>
                <%--                            <li><a href="#">김치찌개</a></li>--%>
                <%--                            <li><a href="#">된장찌개</a></li>--%>
                <%--                            <li><a href="#">순두부찌개</a></li>--%>
                <%--                            <li><a href="#">해물찌개</a></li>--%>
                <%--                            <li><a href="#">동태찌개</a></li>--%>
                <%--                            <li><a href="#">감자탕</a></li>--%>
                <%--                            <li><a href="#">비지찌개</a></li>--%>
                <%--                        </ul>--%>
                <%--                    </li>--%>
                <%--                    <li class="menu_option">--%>
                <%--                        <a href="#">구이류</a>--%>
                <%--                        <ul class="menu">--%>
                <%--                            <li><a href="#">불고기</a></li>--%>
                <%--                            <li><a href="#">갈비</a></li>--%>
                <%--                            <li><a href="#">삼겹살</a></li>--%>
                <%--                            <li><a href="#">대하구이</a></li>--%>
                <%--                            <li><a href="#">오징어구이</a></li>--%>
                <%--                            <li><a href="#">조개구이</a></li>--%>
                <%--                            <li><a href="#">생선구이</a></li>--%>
                <%--                            <li><a href="#">야채구이</a></li>--%>
                <%--                        </ul>--%>
                <%--                    </li>--%>
                <%--                    <li class="menu_option">--%>
                <%--                        <a href="#">밥류</a>--%>
                <%--                        <ul class="menu">--%>
                <%--                            <li><a href="#">비빔밥</a></li>--%>
                <%--                            <li><a href="#">불고기</a></li>--%>
                <%--                            <li><a href="#">김치볶음밥</a></li>--%>
                <%--                            <li><a href="#">김밥</a></li>--%>
                <%--                            <li><a href="#">주먹밥</a></li>--%>
                <%--                            <li><a href="#">오징어덮밥</a></li>--%>
                <%--                            <li><a href="#">볶음밥</a></li>--%>
                <%--                            <li><a href="#">제육덮밥</a></li>--%>
                <%--                        </ul>--%>
                <%--                    </li>--%>
                <%--                    <li class="menu_option">--%>
                <%--                        <a href="#">면류</a>--%>
                <%--                        <ul class="menu">--%>
                <%--                            <li><a href="#">칼국수</a></li>--%>
                <%--                            <li><a href="#">수제비</a></li>--%>
                <%--                        </ul>--%>
                <%--                    </li>--%>
                <%--                </ul>--%>
            </li>
            <li>
                <c:choose>
                    <c:when test="${loginedUserVo == null}">
                        <a href="http://3.35.176.200/store/list?x=127.028290548097&y=37.4998293543379&keyword=중식">중식</a>
                    </c:when>
                    <c:otherwise>
                        <a href="http://3.35.176.200/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=중식">중식</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <c:choose>
                    <c:when test="${loginedUserVo == null}">
                        <a href="http://3.35.176.200/store/list?x=127.028290548097&y=37.4998293543379&keyword=일식">일식</a>
                    </c:when>
                    <c:otherwise>
                        <a href="http://3.35.176.200/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=일식">일식</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <c:choose>
                    <c:when test="${loginedUserVogit == null}">
                        <a href="http://3.35.176.200/store/list?x=127.028290548097&y=37.4998293543379&keyword=양식">양식</a>
                    </c:when>
                    <c:otherwise>
                        <a href="http://3.35.176.200/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=양식">양식</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <c:choose>
                    <c:when test="${loginedUserVo == null}">
                        <a href="http://3.35.176.200/store/list?x=127.028290548097&y=37.4998293543379&keyword=분식">분식</a>
                    </c:when>
                    <c:otherwise>
                        <a href="http://3.35.176.200/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=분식">분식</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <c:choose>
                    <c:when test="${loginedUserVo == null}">
                        <a href="http://3.35.176.200/store/list?x=127.028290548097&y=37.4998293543379&keyword=카페">카페</a>
                    </c:when>
                    <c:otherwise>
                        <a href="http://3.35.176.200/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=카페">카페</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
    </div>
    <a href="http://3.35.176.200"><img id="logoimage" src="<c:url value='/resources/static/image/default/mainLogo.png' />"></a>
    <div class="userTab">
        <c:choose>

            <c:when test="${not empty loginedUserVo}">
                <div class="welcome">
                    <div class="weather_info">
                        <span class="address"><i
                                class="fa-solid fa-location-dot"></i> ${loginedUserVo.weatherAddr}</span>
                        <span class="temp"></span>
                        <span class="sky"></span>
                        <span class="emoticon"></span>
                    </div>
                    <div class="user_info">
                        <span>${loginedUserVo.name}</span>
                        <img src="${loginedUserVo.photo_path}" alt="프로필사진"/>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="welcome">
                    <div class="weather_info">
                        <span class="address"><i class="fa-solid fa-location-dot"></i> 서울특별시 강남구</span>
                        <span class="temp"></span>
                        <span class="sky"></span>
                        <span class="emoticon"></span>

                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        <ul class="depth1">
            <li>
                <a href="#">커뮤니티</a>
                <ul class="depth2">
                    <li><a href="<c:url value='/board/notice'/>">공지사항</a></li>
                    <li><a href="<c:url value='/board/suggestion'/>">건의사항</a></li>
                    <li><a href="<c:url value='/board/vent'/>">하소연 게시판</a></li>
                    <li><a href="<c:url value='/playground'/>">놀이터</a></li>
                </ul>
            </li>

            <c:choose>
                <c:when test="${loginedUserVo != null}">
                    <c:if test="${loginedUserVo.grade == '관리자'}">
                        <li><a href="<c:url value='/admin/userManagement'/>">회원관리</a></li>
                    </c:if>
                    <li>
                        <a href="<c:url value='/user/myPage'><c:param name="user_id" value="${loginedUserVo.id}" /></c:url>">마이페이지</a>
                    </li>
                    <li><a href="<c:url value=''/>" id="logout_link">로그아웃</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="#" id="register_modal">회원가입</a></li>
                    <li><a href="#" id="login_modal">로그인</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</header>


<script>
    $.noConflict();
    var jQ = jQuery;
    var currentURL = window.location.href;


    if (${loginedUserVo != null} &&
    ${loginedUserVo.grade == '블랙리스트'})
    {
        alert("접근이 제한된 사용자입니다.");
        jQ.ajax({
            url: "/user/blackList",
            type: "GET",
            success: function (rdata) {
                console.log("성공")

                location.href = "/"
            },
            error: function (error) {
                console.log("실패")
            }
        });
    }


    jQ("#logout_link").on("click", function () {
        jQ(this).attr("href", "/user/logout?link=" + encodeURIComponent(currentURL));
    });

    jQ(".show_category").on("click", function () {
        jQ(this).next().toggleClass("on");
    });

    jQ(".main_category > li").hover(
        function () {
            jQ(this).find(".sub_category").css({display: "flex"});
            jQ(".main_category").css({overflow: "visible"});
        },
        function () {
            jQ(this).find(".sub_category").css({display: "none"});
            jQ(".main_category").css({overflow: "hidden"});
        }
    );

    jQ(".userTab .depth1 li").mouseover(function () {
        jQ(this).find("ul").stop().slideDown();
    });

    jQ(".userTab .depth1 li").mouseout(function () {
        jQ(this).find("ul").stop().slideUp();
    });

    const registerModalButtonEle = document.querySelector("#register_modal");
    registerModalButtonEle.addEventListener("click", () => {
        const modalCoverEle = document.querySelector(".modal_cover");
        const registerModalEle = document.querySelector(".register_modal");

        modalCoverEle.style.display = "block";
        registerModalEle.style.display = "block";
    })

    const loginModalButtonEle = document.querySelector("#login_modal");
    loginModalButtonEle.addEventListener("click", () => {
        const modalCoverEle = document.querySelector(".modal_cover");
        const loginModalEle = document.querySelector(".login_modal");

        modalCoverEle.style.display = "block";
        loginModalEle.style.display = "block";
    });
</script>

<script>
    function getWeather() {
        let coordinate = [];
        let valueX = "";
        let valueY = "";
        let sky = "";
        let rainSnow = "";

        <c:if test="${not empty loginedUserVo}">
        valueX = ${loginedUserVo.valueX}
            valueY = ${loginedUserVo.valueY}
                </c:if>

                <c:if test="${empty loginedUserVo}">
                valueX = 61;
        valueY = 126;
        </c:if>

        let date = new Date();
        let year = date.getFullYear();
        let month = date.getMonth() + 1;
        if (month > 0 && month < 10) {
            month = "0" + month;
        }
        let day = date.getDate();
        if (day > 0 && day < 10) {
            day = "0" + day;
        }
        let hour = date.getHours();
        if (hour > 0 && hour < 10) {
            hour = "0" + hour;
        } else if (hour == 0) {
            hour = "00";
        }
        let minute = date.getMinutes();
        if (minute > 0 && minute < 10) {
            minute = "0" + minute;
        } else if (minute == 0) {
            minute = "00";
        }


        let nowTime = hour.toString() + minute.toString();
        let nowHourMinute = (parseInt(nowTime) - 44).toString();
        if (nowHourMinute < 0) {
            nowHourMinute = "23" + nowHourMinute;
            day = day - 1;
            if (day < 1) {
                month = month - 1;
                switch (month) {
                    case 2:
                        day = 28;
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        day = 30;
                        break;
                    default :
                        day = 31;
                        break;
                }
                if (month < 1) {
                    year = year - 1;
                    month = 12;
                }
            }
        } else if (nowHourMinute < 100) {
            nowHourMinute = "00" + nowHourMinute;
        } else if (nowHourMinute < 1000) {
            nowHourMinute = "0" + nowHourMinute
        }
        if (month > 0 && month < 10) {
            month = "0" + month;
        }
        if (hour > 0 && hour < 10) {
            hour = "0" + hour;
        } else if (hour == 0) {
            hour = "00";
        }
        if (minute > 0 && minute < 10) {
            minute = "0" + minute;
        } else if (minute == 0) {
            minute = "00";
        }
        let nowHour = nowHourMinute.substring(0, 2);
        let baseDate = year + month.toString() + day;
        let baseTime = nowHour + "30";
        console.log("date" + date)
        console.log("year" + year)
        console.log("month" + month)
        console.log("day" + day)
        console.log("hour" + hour)
        console.log("minute" + minute)
        console.log("nowTime" + nowTime)
        console.log("nowHourMinute" + nowHourMinute)
        console.log("nowHour" + nowHour)
        console.log("baseDate" + baseDate)
        console.log("baseTime" + baseTime)
        console.log(valueX);
        console.log(valueY);
        jQ.ajax({
            method: "GET",
            url: "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst",
            data: {
                serviceKey:
                    "vKlgMc+1x/SWkkhtWG5Zxi8kOiLSZmhXjwE3eqvf8ItyyUqUaUtqFrHz1vVnObXn6jP+S2ML37kV49u/BtFXGw==",
                pageNo: "1",
                numOfRows: "60",
                dataType: "JSON",
                base_date: baseDate,
                base_time: baseTime,
                nx: valueX,
                ny: valueY,
            },
            success: function (data) {
                console.log(data)
                let weatherDatas = data.response.body.items.item
                let temp = weatherDatas.filter((item, index) => item.category === "T1H");
                let T1H = temp[0].fcstValue;
                console.log("기온은?" + T1H);
                let precipitation = weatherDatas.filter((item, index) => item.category === "RN1")
                let RN1 = precipitation[0].fcstValue;
                console.log("강수량은?" + RN1);
                let skyCondition = weatherDatas.filter((item, index) => item.category === "SKY")
                let SKY = skyCondition[0].fcstValue;
                console.log("하늘상태는?" + SKY);
                switch (SKY) {
                    case "1" :
                        sky = "맑음";
                        break;
                    case "3" :
                        sky = "구름많음"
                        break;
                    case "4" :
                        sky = "흐림"
                        break;
                    default :
                        sky = ""
                        break;
                }
                console.log("하늘 상태 한글로?" + sky);
                let PTY = data.response.body.items.item[6].fcstValue;
                console.log("눈이오나요 비가오나요?" + PTY);
                switch (PTY) {
                    case "1" :
                    case "5" :
                        rainSnow = "비"
                        break;
                    case "2" :
                    case "6" :
                        rainSnow = "진눈깨비(눈+비)"
                        break;
                    case "3" :
                        rainSnow = "눈"
                        break;
                    default :
                        rainSnow = ""
                        break;
                }

                if (rainSnow != "") {
                    sky = rainSnow;
                }
                console.log("뭐가 오나요?" + rainSnow)
                console.log("하늘 상태 한글로?" + sky);

                jQ(".temp").text(T1H + "℃, ");
                if (RN1 != "강수없음") {
                    jQ(".sky").text(sky + " " + RN1);
                } else {
                    jQ(".sky").text(sky);
                }
                if (sky == "맑음") {
                    jQ("section #article1").prop("style", "background-image: url(/resources/static/image/default/sunny.jpeg)");
                    jQ(".emoticon").html("<i class='fa-solid fa-sun' style='color:#ffa500'></i>");
                } else if (sky == "구름많음") {
                    jQ("section #article1").prop("style", "background-image: url(../../../resources/static/image/default/cloudy.jpg)");
                    jQ(".emoticon").html("<i class='fa-solid fa-cloud-sun' style='color:#4bb4ff' ></i>")
                } else if (sky == "흐림") {
                    jQ("section #article1").prop("style", "background-image: url(/resources/static/image/default/blur.jpg)");
                    jQ(".emoticon").html("<i class='fa-solid fa-cloud'  style='color:#6f6f6f'></i>")
                } else if (sky == "비") {
                    jQ("section #article1").prop("style", "background-image: url(/resources/static/image/default/rain.jpg)");
                    jQ(".emoticon").html("<i class='fa-solid fa-cloud-showers-heavy'  style='color:#074dbb'></i>")
                } else if (sky == "눈") {
                    jQ("section #article1").prop("style", "background-image: url(../../../resources/static/image/default/snow.jpg)");
                    jQ(".emoticon").html("<i class='fa-solid fa-snowflake'  style='color:#7fb1ff'></i>")
                } else if (sky == "진눈깨비(눈+비)") {
                    jQ("section #article1").prop("style", "background-image: url(../../../resources/static/image/default/rain.jpg)");
                    jQ(".emoticon").html("<i class='fa-solid fa-cloud-meatball'  style='color:#8cb9ff'></i>")
                }

            },
            error: function (xhr, status, error) {
                console.error(xhr, status, error);
            },
        });
    }

    getWeather();
</script>