<%@page import="com.org.makgol.users.vo.UsersRequestVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>


<table>
	<thead>
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>공감수</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>

		<c:forEach var="item" items="${boardVos}" varStatus="status">
			<tr>
				<td>${fn:length(boardVos)-(status.index)}</td>
				<td><c:url value='/board/suggestion/detail' var='detail_url'>
						<c:param name='b_id' value='${item.b_id}' />
					</c:url> <a href="${detail_url}">${item.title}</a></td>
				<td>${item.sympathy}</td>
				<td>${item.name}</td>
				<td>${item.date}</td>
				<td>${item.hit}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


	<%
	UsersRequestVo loginedUserVo = (UsersRequestVo) session.getAttribute("loginedUsersResponseVoVo");
	if (loginedUserVo != null) {
	%>
	<a href="<c:url value='/board/suggestion/create'/>">글쓰기</a>
	<br>
	<%
	}
	%>
