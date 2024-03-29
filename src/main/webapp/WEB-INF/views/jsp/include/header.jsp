<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.org.makgol.users.vo.UsersResponseVo" %>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.net.URLEncoder" %>
<%@page import="java.net.URLDecoder" %>
<%@page import="java.nio.charset.StandardCharsets" %>


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
         UsersResponseVo userVo = new UsersResponseVo();

         userVo.setLongitude(126.886418278694);
         userVo.setLatitude(37.4810758439164);

         List<String> names = new ArrayList<>();
         List<String> values = new ArrayList<>();
         Cookie[] cookies = request.getCookies(); // 쿠키들
         if (cookies != null) {
             for (Cookie cookie : cookies) {
                names.add(cookie.getName());
                values.add(URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8));
             }
         }

      for (int i = 0; i < names.size(); i++) {
          String name = names.get(i);
          String value = values.get(i);
          if (name.equals("id")) {
              userVo.setId(Integer.parseInt(value));
          } else if (name.equals("name")) {
              userVo.setName(value);
          } else if (name.equals("photo_path")) {
              userVo.setPhoto_path(value);
          } else if (name.equals("grade")) {
              userVo.setGrade(value);
          } else if (name.equals("userX")) {
              userVo.setLongitude(Double.parseDouble(value));
          } else if (name.equals("userY")) {
              userVo.setLatitude(Double.parseDouble(value));
          } else if (name.equals("weatherAddr")) {
              userVo.setWeatherAddr(value);
          } else if (name.equals("valueX")) {
              userVo.setValueX(Integer.parseInt(value));
          } else if (name.equals("valueY")) {
              userVo.setValueY(Integer.parseInt(value));
          }
      }
    request.setAttribute("loginedUserVo", userVo);
    UsersResponseVo loginedUserVo = (UsersResponseVo) request.getAttribute("loginedUserVo");
%>


<script>
var xValue = "<%= userVo.getLongitude() %>";
var yValue = "<%= userVo.getLatitude() %>";
</script>
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
                    <c:when test="${loginedUserVo.longitude == 0.0}">
                        <a href="http://www.makgol.com/store/list?x=126.886418278694&y=37.4810758439164&keyword=한식">한식</a>
                    </c:when>
                    <c:otherwise>
                        <a href="http://www.makgol.com/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=한식">한식</a>
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
                    <c:when test="${loginedUserVo.longitude == 0.0}">
                        <a href="http://www.makgol.com/store/list?x=126.886418278694&y=37.4810758439164&keyword=중식">중식</a>
                    </c:when>
                    <c:otherwise>
                        <a href="http://www.makgol.com/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=중식">중식</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <c:choose>
                    <c:when test="${loginedUserVo.longitude == 0.0}">
                        <a href="http://www.makgol.com/store/list?x=126.886418278694&y=37.4810758439164&keyword=일식">일식</a>
                    </c:when>
                    <c:otherwise>
                        <a href="http://www.makgol.com/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=일식">일식</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <c:choose>
                    <c:when test="${loginedUserVo.longitude == 0.0}">
                        <a href="http://www.makgol.com/store/list?x=126.886418278694&y=37.4810758439164&keyword=양식">양식</a>
                    </c:when>
                    <c:otherwise>
                        <a href="http://www.makgol.com/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=양식">양식</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <c:choose>
                    <c:when test="${loginedUserVo.longitude == 0.0}">
                        <a href="http://www.makgol.com/store/list?x=126.886418278694&y=37.4810758439164&keyword=분식">분식</a>
                    </c:when>
                    <c:otherwise>
                        <a href="http://www.makgol.com/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=분식">분식</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <c:choose>
                    <c:when test="${loginedUserVo.longitude == 0.0}">
                        <a href="http://www.makgol.com/store/list?x=126.886418278694&y=37.4810758439164&keyword=카페">카페</a>
                    </c:when>
                    <c:otherwise>
                        <a href="http://www.makgol.com/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=카페">카페</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
    </div>
    <a href="http://www.makgol.com"><img id="logoimage" src="<c:url value='/resources/static/image/default/mainLogo.png' />"></a>
    <div class="userTab">
        <c:choose>

            <c:when test="${loginedUserVo.name != null}">
                <div class="welcome">
                    <div class="weather_info">
                        <span class="address"><i
                                class="fa-solid fa-location-dot"></i><c:out value="${loginedUserVo.weatherAddr}"/></span>
                        <span class="temp"></span>
                        <span class="sky"></span>
                        <span class="emoticon"></span>
                    </div>
                    <div class="user_info">
                        <span><c:out value="${loginedUserVo.name}"/></span>
                        <img src="<c:out value='${loginedUserVo.photo_path}'/>" alt="프로필사진"/>
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
                    <%--<!--<li><a href="<c:url value='/board/notice'/>">공지사항</a></li>-->--%>
                    <%--<!--<li><a href="<c:url value='/board/suggestion'/>">건의사항</a></li>-->--%>
                    <li><a href="<c:url value='/board/vent'/>">하소연 게시판</a></li>
                    <%--<!--<li><a href="<c:url value='/playground'/>">놀이터</a></li>-->--%>
                </ul>
            </li>

            <c:choose>
                <c:when test="${loginedUserVo.name != null}">
                    <c:if test="${loginedUserVo.grade == '관리자'}">
                        <li><a href="<c:url value='/admin/userManagement'/>">회원관리</a></li>
                    </c:if>
                    <li>
                        <a href="<c:url value='/user/myPage'><c:param name="user_id" value="${loginedUserVo.id}" /></c:url>">마이페이지</a>
                    </li>
                    <li><a href="<c:url value=''/>" id="logout_link" onclick="deleteDibsShop()">로그아웃</a></li>
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
    function deleteDibsShop() {
        localStorage.removeItem("dibsShops")
    }
</script>

<script>
    $.noConflict();
    var jQ = jQuery;
    var currentURL = window.location.href;

    if (${loginedUserVo != null} && ${loginedUserVo.grade == '블랙리스트'}) {
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

        <c:choose>
            <c:when test="${loginedUserVo.valueX == 0}">
                valueX = 61;
                valueY = 126;
            </c:when>
            <c:otherwise>
                valueX = ${loginedUserVo.valueX}
                valueY = ${loginedUserVo.valueY}
            </c:otherwise>
        </c:choose>

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
                let weatherDatas = data.response.body.items.item
                let temp = weatherDatas.filter((item, index) => item.category === "T1H");
                let T1H = temp[0].fcstValue;
                let precipitation = weatherDatas.filter((item, index) => item.category === "RN1")
                let RN1 = precipitation[0].fcstValue;
                let skyCondition = weatherDatas.filter((item, index) => item.category === "SKY")
                let SKY = skyCondition[0].fcstValue;
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
                let PTY = data.response.body.items.item[6].fcstValue;
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