<%@page import="com.org.makgol.users.vo.UsersResponseVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
   UsersResponseVo loginedUserVo = (UsersResponseVo) session.getAttribute("loginedUserVo");
%>
<ul class="category_list_ul">
  <c:forEach var="item" items="${categoryVo}" begin="0" end="${fn:length(categoryVo) - 1}">
    <li class="menu_list">
         <c:if test="${loginedUserVo != null and loginedUserVo.grade == '관리자'}">
            <form action="<c:url value='/category/cateFile'/>" class="fileUpload" method="post" enctype="multipart/form-data" >
                <input type="file" name="photoFile">
                <input type='hidden' name="menu_name" value="${item.menu_name}">
                <button type="submit" class="form_button">등록</button>
            </form>
        </c:if>
        <img class="img" src="http://localhost:8090${item.photoPath}">
        <span class="menu_name">${item.menu_name}</span>
        <span class="menu_gogo">근처식당 찾아보기 >></span>
    </li>
  </c:forEach>
</ul>

