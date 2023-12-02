<%@page import="com.org.makgol.users.vo.UsersResponseVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


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

		<c:forEach var="item" items="${boardVos}" varStatus="status">
			<tr>
				<td>${fn:length(boardVos)-(status.index)}</td>
				<td><c:url value='/board/suggestion/detail' var='detail_url'>
						<c:param name='b_id' value='${item.b_id}' />
					</c:url> <a href="${detail_url}">${item.title}</a></td>
				<td>${item.name}</td>
				<td>${item.date}</td>
				<td>${item.hit}</td>
				<td>${item.sympathy}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


	<%
	UsersResponseVo loginedUserVo = (UsersResponseVo) session.getAttribute("loginedUserVo");
	if (loginedUserVo != null) {
	%>
	<a href="<c:url value='/board/suggestion/create'/>"><i class="fa-regular fa-pen-to-square"></i> 글쓰기</a>
	<br>
	<%
	}
	%>

