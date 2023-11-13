<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.org.makgol.users.vo.UsersRequestVo"%>

<%
UsersRequestVo loginedUsersRequestVo = (UsersRequestVo) session.getAttribute("loginedUsersRequestVo");
UsersRequestVo blackList = (UsersRequestVo) session.getAttribute("blackList");
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
            <a href="#">한식</a>
            <ul class="sub_category">
              <li class="menu_option">
                <a href="#">찌개류</a>
                <ul class="menu">
                  <li><a href="#">부대찌개</a></li>
                  <li><a href="#">김치찌개</a></li>
                  <li><a href="#">된장찌개</a></li>
                  <li><a href="#">순두부찌개</a></li>
                  <li><a href="#">해물찌개</a></li>
                  <li><a href="#">동태찌개</a></li>
                  <li><a href="#">감자탕</a></li>
                  <li><a href="#">비지찌개</a></li>
                </ul>
              </li>
              <li class="menu_option">
                <a href="#">구이류</a>
                <ul class="menu">
                  <li><a href="#">불고기</a></li>
                  <li><a href="#">갈비</a></li>
                  <li><a href="#">삼겹살</a></li>
                  <li><a href="#">대하구이</a></li>
                  <li><a href="#">오징어구이</a></li>
                  <li><a href="#">조개구이</a></li>
                  <li><a href="#">생선구이</a></li>
                  <li><a href="#">야채구이</a></li>
                </ul>
              </li>
              <li class="menu_option">
                <a href="#">밥류</a>
                <ul class="menu">
                  <li><a href="#">비빔밥</a></li>
                  <li><a href="#">불고기</a></li>
                  <li><a href="#">김치볶음밥</a></li>
                  <li><a href="#">김밥</a></li>
                  <li><a href="#">주먹밥</a></li>
                  <li><a href="#">오징어덮밥</a></li>
                  <li><a href="#">볶음밥</a></li>
                  <li><a href="#">제육덮밥</a></li>
                </ul>
              </li>
              <li class="menu_option">
                <a href="#">면류</a>
                <ul class="menu">
                  <li><a href="#">칼국수</a></li>
                  <li><a href="#">수제비</a></li>
                </ul>
              </li>
            </ul>
          </li>
          <li>
            <a href="#">중식</a>
          </li>
          <li>
            <a href="#">일식</a>
          </li>
          <li>
            <a href="#">양식</a>
          </li>
          <li>
            <a href="#">분식</a>
          </li>
          <li>
            <a href="#">카페</a>
          </li>
        </ul>
      </div>
      <p class="img"></p>
      <div class="userTab">
        <ul class="depth1">
          <li>
            <a href="#">COMMUNITY</a>
            <ul class="depth2">
              <li><a href="<c:url value='/board/notice'/>">공지사항</a></li>
              <li><a href="<c:url value='/board/suggestion'/>">건의사항</a></li>
              <li><a href="<c:url value='/board/vent'/>">하소연 게시판</a></li>
            </ul>
          </li>
      <c:choose>
          <c:when test="${loginedUsersRequestVo != null}">
              <c:if test="${loginedUsersRequestVo.getGrade() == '관리자'}">
                  <li><a href="<c:url value='/admin/userManagement'/>">회원관리</a></li>
              </c:if>
              <li><a href="<c:url value='/user/myPage'/>">MYPAGE</a></li>
              <li><a href="<c:url value='/user/logout'/>">LOGOUT</a></li>
          </c:when>
          <c:otherwise>
              <li><a href="<c:url value='/user/join'/>">JOIN</a></li>
              <li><a href="<c:url value='/user/login'/>">LOGIN</a></li>
          </c:otherwise>
      </c:choose>

        </ul>
      </div>
    </header>
    
    
    <script>
    $.noConflict();
    var jQ = jQuery;

    let black = "${blackList}";
   if(black) {
    alert("블랙리스트입니다.")

    jQ.ajax({
    url : "/user/logout",
    type : "GET",
    success : function(rdata) {
              console.log("세션 초기화 성공")
	           },
	error : function(error) {
			console.log("세션 초기화 실패")
			}
			 });
   }

    jQ(".show_category").on("click", function () {
        jQ(this).next().toggleClass("on");
      });

      jQ(".main_category > li").hover(
        function () {
          jQ(this).find(".sub_category").css({ display: "flex" });
          jQ(".main_category").css({ overflow: "visible" });
        },
        function () {
          jQ(this).find(".sub_category").css({ display: "none" });
          jQ(".main_category").css({ overflow: "hidden" });
        }
      );

      jQ(".userTab .depth1 li").mouseover(function () {
        jQ(this).find("ul").stop().slideDown();
      });

      jQ(".userTab .depth1 li").mouseout(function () {
        jQ(this).find("ul").stop().slideUp();
      });

      </script>