<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<span class="resultMenu">${menu}</span>
당첨 !
<c:url value="/category/rouletteResult" var="category_url">
    <c:param name="category" value="${menu}"/>
</c:url>
<c:choose>
    <c:when test="${loginedUserVo == null}">
        <a href="http://3.36.125.239/store/list?x=127.028290548097&y=37.4998293543379&keyword=${menu}"> 근처 식당 보러가기 <i class="fa-solid fa-angles-right"></i></a>
    </c:when>
    <c:otherwise>
        <a href="http://3.36.125.239/store/list?x=${loginedUserVo.longitude}&y=${loginedUserVo.latitude}&keyword=${menu}">근처 식당 보러가기 <i class="fa-solid fa-angles-right"></i></a>
    </c:otherwise>
</c:choose>