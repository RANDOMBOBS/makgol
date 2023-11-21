<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<ul class="topmenu_list_ul">
	<c:forEach var="item" items="${categoryVo}" begin="0" end="4" varStatus="loop">
		<li class="topmenu_list">
		<img class="top_1_5img" src="<c:url value='/resources/static/image/top_${loop.index + 1}.png' />">
		<img class="top_img" src="<c:url value='/resources/static/image/김치찌개.jpg' />">
		<span class="top_menu_name">${item.menu_name}</span>
		<span class="top_menu_gogo">근처식당 찾아보기 >></span>
		</li>
	</c:forEach>
</ul>

