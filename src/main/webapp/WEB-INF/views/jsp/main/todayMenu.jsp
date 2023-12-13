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
            <img class="img" src="${item.photoPath}">
            <span class="today_menu_name">${item.menu}</span>
            <span class="today_menu_gogo">해당메뉴 검색하기 >></span>
        </li>
    </c:forEach>
</ul>
