<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.org.makgol.users.vo.UsersRequestVo"%>

<!DOCTYPE html>
 <meta charset="UTF-8">
  <title>막내야 골라봐 | 내 맛집 리스트 (MY_STORE_LIST)</title>


</head>
<body>
<jsp:include page="../include/header.jsp"></jsp:include>
 <p>내가 찜한 맛집 리스트</p>
    <table>
    <c:forEach var="item" items="${storeVos}">
       <tr>
        <td class="store_photo"><img src="<c:url value='/resources/static/image/default/김치찌개.jpg' />" alt="${item.name}" /></td>
        <td class="store_info">
          <ul>
            <li>${item.name}</li>
            <li>${item.address}</li>
            <li>${item.phone}</li>
          </ul>
        </td>
        <td><a href="#">식당 보러가기</a></td>
      </tr>
      </c:forEach>
    </table>
</body>
</html>