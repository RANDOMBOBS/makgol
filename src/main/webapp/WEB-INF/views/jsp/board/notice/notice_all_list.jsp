<%@page import="com.org.makgol.users.vo.UsersResponseVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
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
      <th>번호</th>
      <th>제목</th>
      <th>작성자</th>
      <th>작성일</th>
      <th>조회수</th>
      <th>공감</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="item" items="${boardVo}" varStatus="status">
      <tr>
        <td>${fn:length(boardVo)-(status.index)}</td>
        <td>
          <c:url value="/board/detailNotice" var="detail_url">
            <c:param name="b_id" value="${item.b_id}" />
          </c:url>
          <a href="${detail_url}">${item.title}</a>
        </td>
        <td>${item.grade}</td>

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
      </tr>
    </c:forEach>
  </tbody>
</table>

<!-- 괸리자로 로그인시 글쓰기 버튼 보여지며 누르면 페이지 이동 -->
    <%
	    UsersResponseVo loginedUserVo = (UsersResponseVo) application.getAttribute("loginedUserVo");
	    if (loginedUserVo != null) {
	%>
	    <c:if test="${loginedUserVo.grade == '관리자'}">
	        <a href="<c:url value='/board/noticeCreateForm'/>"><i class="fa-regular fa-pen-to-square"></i> 글쓰기</a><br>
	    </c:if>
	<%
	    }
	%>
