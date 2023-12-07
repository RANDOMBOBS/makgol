<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.Date" %>

<%
    Date currentDate = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String today = dateFormat.format(currentDate);
%>

<c:set var="today" value="<%= today %>"/>

<table class="my_post_table">
    <colgroup>
        <col />
        <col />
        <col />
        <col />
        <col />
        <col />
        <col />
    </colgroup>
    <thead>
      <tr>
        <th><input type="checkbox" id="allMyCheckbox" onclick="allMyCheckbox()" /></th>
        <th>카테고리</th>
        <th>제목</th>
        <th>작성일</th>
        <th>작성자</th>
        <th>조회수</th>
        <th>공감</th>
      </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${boardVos}">
          <tr>
            <td data-like_id="${item.id}" data-board_id="${item.b_id}"><input type="checkbox" class="eachCheckbox"/></td>
              <c:choose>
                  <c:when test="${item.category == 'suggestion'}">
                      <td>건의사항</td>
                  </c:when>
                  <c:when test="${item.category == 'notice'}">
                      <td>공지사항</td>
                  </c:when>
                  <c:otherwise>
                      <td>하소연게시판</td>
                  </c:otherwise>
              </c:choose>
            <td>
                <c:url value='/board/suggestion/detail' var='detail_url'>
                   <c:param name='b_id' value='${item.b_id}' />
                </c:url>
                <a href="${detail_url}">${item.title}</a>
            </td>
              <c:choose>
                  <c:when test="${fn:contains(item.date, today)}">
                      <td>${item.date.substring(11,16)}</td>
                  </c:when>
                  <c:otherwise>
                      <td>${item.date.substring(0,10)}</td>
                  </c:otherwise>
              </c:choose>
            <td>${item.name}</td>
            <td>${item.hit}</td>
            <td>${item.sympathy}</td>
          </tr>
        </c:forEach>
    </tbody>
</table>

<button type="button" onclick="deleteLike()">공감취소</button>

