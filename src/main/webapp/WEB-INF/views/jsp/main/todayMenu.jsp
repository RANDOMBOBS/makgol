<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.List" %>
<%@ page import="com.org.makgol.category.vo.CategoryListVo" %>



<%--
    List<CategoryListVo> categoryVo = (List<CategoryListVo>) request.getAttribute("categoryVo");
    // 랜덤함수 shuffle() 메소드를 사용하려면 java.util.Collections를 import
    Collections.shuffle(categoryVo);
--%>

<ul class="todaymenu_list_ul">
    <c:forEach var="item" items="${categoryVo}" varStatus="loop" begin="0" end="9">
        <li class="todaymenu_list">
        <c:choose>
        		  <c:when test="${loginedUserVo == null}">
        		  <a href="http://www.makgol.com/store/list?x=127.028290548097&y=37.4998293543379&keyword=${item.menu}"><span class="today_menu_name">${item.menu}</span></a>
        		    <span class="today_menu_gogo">해당메뉴 검색하기></span>
        		    <img class="img" src="<c:url value='/resources/static/image/default/todayMenu_${loop.index + 1}.jpg'/>">
        		  </c:when>
        		  <c:otherwise>
        		  <a href="http://www.makgol.com/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=${item.menu}"><span class="today_menu_name">${item.menu}</span></a>
                    <span class="today_menu_gogo">해당메뉴 검색하기></span>
                    <img class="img" src="<c:url value='/resources/static/image/default/todayMenu_${loop.index + 1}.jpg'/>">
                  </c:otherwise>
        		</c:choose>

        </li>
    </c:forEach>
</ul>
