<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
		integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<%
	Date currentDate = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String today = dateFormat.format(currentDate);
%>

<c:set var="today" value="<%= today %>"/>



<c:choose>
<c:when test="${not empty boardVos}">
	<h3>' <span>${value}</span> '에 대한 검색결과 입니다.</h3>
	<table>
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
		<c:forEach var="item" items="${boardVos}" varStatus="status">
			<tr>
				<td>${fn:length(boardVos)-(status.index)}</td>
				<td><c:url value='/board/suggestion/detail' var='detail_url'>
						<c:param name='b_id' value='${item.b_id}' />
					</c:url> <a href="${detail_url}">${item.title}</a></td>
				<td>${item.name}</td>
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
</c:when>


<c:otherwise>
<h4>검색된 정보가 없습니다.</h4>
</c:otherwise>
</c:choose>
<a href="#" onclick="allBoardList()">전체목록보기</a><br>



