<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>


<c:choose>
<c:when test="${boardVos} != null">
<h3>검색하신 글 목록입니다.</h3>
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
</c:when>

<c:otherwise>
<h3>검색된 정보가 없습니다.</h3>
</c:otherwise>
</c:choose>
<a href="#javascript" onclick="allBoardList()">전체목록보기</a><br>



	<jsp:include page="../../../script/jsp/suggestion.jsp"></jsp:include>
