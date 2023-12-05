<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.Date" %>

<%
   Date currentDate = new Date();
   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
   String today = dateFormat.format(currentDate);
%>

<c:set var="today" value="<%= today %>"/>

<table>
    <colgroup>
        <col />
        <col />
        <col />
        <col />
        <col />
        <col />
    </colgroup>
    <thead>
        <tr>
            <th>
            <input type="checkbox" id="allMyCheckbox" onclick="allMyCheckbox()" />
            </th>
            <th>카테고리</th>
            <th>제목</th>
            <th>작성일</th>
            <th>조회수</th>
            <th>공감</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${boardVos}">
          <tr>
            <td><input type="checkbox" class="eachCheckbox"/></td>
            <td>${item.category}</td>
            <td><c:url value='/board/suggestion/detail' var='detail_url'>
                        <c:param name='b_id' value='${item.b_id}' />
                </c:url> <a href="${detail_url}">${item.title}</a></td>
            <c:choose>
                 <c:when test="${fn:contains(item.date, today)}">
                    <td>${item.date.substring(11,16)}</td>
                 </c:when>
                 <c:otherwise>
                    <td>${item.date.substring(0,10)}</td>
                 </c:otherwise>
            </c:choose>
            <td>${item.hit}</td>
            <td>${item.sympathy}</td>
            <input type="hidden" value="${item.b_id}">
          </tr>
        </c:forEach>
    </tbody>
</table>

    <button type="button" class="postDelete" onclick="deleteBoard()">삭제</button>

