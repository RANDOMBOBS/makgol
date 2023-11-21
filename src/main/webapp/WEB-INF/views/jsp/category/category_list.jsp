<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<ul class="category_list_ul">
  <c:forEach var="item" items="${categoryVo }" begin="0" end="15">
    <li class="menu_list">
    <form class="fileUpload" action="/upload" method="post" enctype="multipart/form-data">
        <input type="file" id="imgUpload" style="display:none;"></input>
        <label for="imgUpload"><i class="fa-solid fa-image"></i></label>
    </form>
      <img src="<c:url value='/resources/static/image/default/김치찌개.jpg' />">
      <span class="menu_name">${item.menu_name}</span>
      <span class="menu_gogo">근처식당 찾아보기 >></span>
    </li>
  </c:forEach>
</ul>
