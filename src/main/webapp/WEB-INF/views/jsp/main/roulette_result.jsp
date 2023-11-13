<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<span class="resultMenu">${menu}</span>
당첨 !
<c:url value="/category/rouletteResult" var="category_url">
	<c:param name="category" value="${menu}" />
</c:url>
<a href="${category_url}" class="letsMenu">메뉴 보러가기</a>
