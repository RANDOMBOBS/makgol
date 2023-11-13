<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<ul class="category_list_ul">
  <c:forEach var="item" items="${categoryVo }">
    <li class="menu_list">
      <img src="<c:url value='/resources/image/김치찌개.jpg' />">
      <span class="menu_name">${item.menu}</span>
      <span class="menu_gogo">근처식당 찾아보기 >></span>
    </li>
  </c:forEach>
</ul>
