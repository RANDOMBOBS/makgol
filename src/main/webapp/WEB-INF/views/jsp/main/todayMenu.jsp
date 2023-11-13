<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<ul class="todaymenu_list_ul">
  <c:forEach var="item" items="${categoryVo}" begin="0" end="24">
      <li class="todaymenu_list">
        <img class="today_img" src="<c:url value='/resources/static/image/김치찌개.jpg' />">
        <span class="today_menu_name">${item.menu_name}</span>
        <span class="today_menu_gogo">근처식당 찾아보기 >></span>
      </li>
  </c:forEach>
</ul>
