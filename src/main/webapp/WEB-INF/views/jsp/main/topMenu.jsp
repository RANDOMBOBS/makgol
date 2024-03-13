<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<ul class="topmenu_list_ul">
	<c:forEach var="item" items="${categoryVo}" begin="0" end="4" varStatus="loop">
		<li class="topmenu_list">

		<img class="top_1_5img" src="<c:url value='/resources/static/image/default/top_${loop.index + 1}.png'/>">
		<img class="top_img" src="<c:url value='https://${item.photo}'/>">
		<c:choose>
		  <c:when test="${loginedUserVo == null}">
		    <a href="http://www.makgol.com/store/list?x=126.886418278694&y=37.4810758439164&keyword=${item.name}"> <span class="top_menu_name">${item.name}</span> </a>
		  </c:when>
		  <c:otherwise>
            <a href="http://www.makgol.com/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=${item.name}"><span class="top_menu_name">${item.name}</span></a>
          </c:otherwise>
		</c:choose>
		<span class="top_menu_name">${item.name}</span>
		<span class="top_menu_gogo">해당식당 상세보기 >></span>
		</li>
	</c:forEach>
</ul>

<c:choose>
    <c:when test="${loginedUserVo == null}">
        <a href="http://www.makgol.com/store/list?x=126.886418278694&y=37.4810758439164&keyword=${menu}"> 근처 식당 보러가기 <i class="fa-solid fa-angles-right"></i></a>
    </c:when>
    <c:otherwise>
        <a href="http://www.makgol.com/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=${menu}">근처 식당 보러가기 <i class="fa-solid fa-angles-right"></i></a>
    </c:otherwise>
</c:choose>

